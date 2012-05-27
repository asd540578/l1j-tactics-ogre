/* This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA
 * 02111-1307, USA.
 *
 * http://www.gnu.org/copyleft/gpl.html
 */

package l1j.server.server.clientpackets;

import static l1j.server.server.model.skill.L1SkillId.*;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import l1j.server.L1DatabaseFactory;
import l1j.server.configure.Config;
import l1j.server.server.ActionCodes;
import l1j.server.server.ClientThread;
import l1j.server.server.WarTimeController;
import l1j.server.server.datatables.CharBuffTable;
import l1j.server.server.datatables.CharacterTable;
import l1j.server.server.datatables.GetBackRestartTable;
import l1j.server.server.datatables.SkillTable;
import l1j.server.server.model.Getback;
import l1j.server.server.model.L1CastleLocation;
import l1j.server.server.model.L1Clan;
import l1j.server.server.model.L1PolyMorph;
import l1j.server.server.model.L1War;
import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.model.Instance.L1SummonInstance;
import l1j.server.server.model.skill.L1SkillUse;
import l1j.server.server.model.skill.executor.L1BuffSkillExecutor;
import l1j.server.server.serverpackets.S_ActiveSpells;
import l1j.server.server.serverpackets.S_AddSkill;
import l1j.server.server.serverpackets.S_Bookmarks;
import l1j.server.server.serverpackets.S_CharTitle;
import l1j.server.server.serverpackets.S_CharacterConfig;
import l1j.server.server.serverpackets.S_InitialAbilityGrowth;
import l1j.server.server.serverpackets.S_InvList;
import l1j.server.server.serverpackets.S_Karma;
import l1j.server.server.serverpackets.S_LoginGame;
import l1j.server.server.serverpackets.S_MapID;
import l1j.server.server.serverpackets.S_OwnCharPack;
import l1j.server.server.serverpackets.S_OwnCharStatus;
import l1j.server.server.serverpackets.S_SPMR;
import l1j.server.server.serverpackets.S_ServerMessage;
import l1j.server.server.serverpackets.S_SkillBrave;
import l1j.server.server.serverpackets.S_SkillHaste;
import l1j.server.server.serverpackets.S_SkillIconGFX;
import l1j.server.server.serverpackets.S_SkillSound;
import l1j.server.server.serverpackets.S_SummonPack;
import l1j.server.server.serverpackets.S_War;
import l1j.server.server.serverpackets.S_Weather;
import l1j.server.server.serverpackets.S_bonusstats;
import l1j.server.server.templates.L1BookMark;
import l1j.server.server.templates.L1CharacterBuff;
import l1j.server.server.templates.L1CharacterSkill;
import l1j.server.server.templates.L1GetBackRestart;
import l1j.server.server.templates.L1Skill;
import l1j.server.server.utils.SQLUtil;

// Referenced classes of package l1j.server.server.clientpackets:
// ClientBasePacket
//
public class C_LoginToServer extends ClientBasePacket {

	private static final String C_LOGIN_TO_SERVER = "[C] C_LoginToServer";
	private static Logger _log = Logger.getLogger(C_LoginToServer.class
			.getName());

	public C_LoginToServer(byte abyte0[], ClientThread client)
			throws FileNotFoundException, Exception {
		super(abyte0);

		String login = client.getAccountName();

		String charName = readS();

		if (client.getActiveChar() != null) {
			_log.info("同一IDでの重複接続の為(" + client.getHostname()
					+ ")との接続を強制切断しました。");
			client.close();
			return;
		}

		L1PcInstance pc = L1PcInstance.load(charName);
		if (pc == null || !login.equals(pc.getAccountName())) {
			_log.info("無効なログインリクエスト: char=" + charName + " account=" + login
					+ " host=" + client.getHostname());
			client.close();
			return;
		}

		if (Config.LEVEL_DOWN_RANGE != 0) {
			if (pc.getHighLevel() - pc.getLevel() >= Config.LEVEL_DOWN_RANGE) {
				_log
						.info("レベルダウンの許容範囲を超えたキャラクターのログインリクエスト: char="
								+ charName + " account=" + login + " host="
								+ client.getHostname());
				client.kick();
				return;
			}
		}

		_log.info("キャラクターログイン: char=" + charName + " account=" + login
				+ " host=" + client.getHostname());

		int currentHpAtLoad = pc.getCurrentHp();
		int currentMpAtLoad = pc.getCurrentMp();
		pc.clearSkillMastery();
		pc.setOnlineStatus(1);
		CharacterTable.updateOnlineStatus(pc);
		L1World.getInstance().storeObject(pc);

		pc.setNetConnection(client);
		pc.setPacketOutput(client);
		client.setActiveChar(pc);

		/** 初期ステータスボーナス */
		S_InitialAbilityGrowth AbilityGrowth = new S_InitialAbilityGrowth(pc);
		pc.sendPackets(AbilityGrowth);

		// 3.0c
		/*
		 * S_Unknown1 s_unknown1 = new S_Unknown1(); pc.sendPackets(s_unknown1);
		 * // // S_Unknown2 s_unknown2 = new S_Unknown2();
		 * pc.sendPackets(s_unknown2);
		 */
		pc.sendPackets(new S_LoginGame()); // 3.3c
		pc.sendPackets(new S_Karma(pc)); // カルマ値を表示
		bookmarks(pc);

		// ミニゲーム実行中判定
		if (pc.getMapId() == 5143) {
			pc.setMiniGamePlaying(1);
		} else {
			pc.setMiniGamePlaying(0);
		}

		// リスタート先がgetback_restartテーブルで指定されていたら移動させる
		GetBackRestartTable gbrTable = GetBackRestartTable.getInstance();
		L1GetBackRestart[] gbrList = gbrTable.getGetBackRestartTableList();
		for (L1GetBackRestart gbr : gbrList) {
			if (pc.getMapId() == gbr.getArea()) {
				pc.setX(gbr.getLocX());
				pc.setY(gbr.getLocY());
				pc.setMap(gbr.getMapId());
				break;
			}
		}

		// altsettings.propertiesでGetBackがtrueなら街に移動させる
		if (Config.GET_BACK) {
			int[] loc = Getback.GetBack_Location(pc, true);
			pc.setX(loc[0]);
			pc.setY(loc[1]);
			pc.setMap((short) loc[2]);
		}

		// 戦争中の旗内に居た場合、城主血盟でない場合は帰還させる。
		int castle_id = L1CastleLocation.getCastleIdByArea(pc);
		if (0 < castle_id) {
			if (WarTimeController.getInstance().isNowWar(castle_id)) {
				L1Clan clan = L1World.getInstance().getClan(pc.getClanname());
				if (clan != null) {
					if (clan.getCastleId() != castle_id) {
						// 城主クランではない
						int[] loc = new int[3];
						loc = L1CastleLocation.getGetBackLoc(castle_id);
						pc.setX(loc[0]);
						pc.setY(loc[1]);
						pc.setMap((short) loc[2]);
					}
				} else {
					// クランに所属して居ない場合は帰還
					int[] loc = new int[3];
					loc = L1CastleLocation.getGetBackLoc(castle_id);
					pc.setX(loc[0]);
					pc.setY(loc[1]);
					pc.setMap((short) loc[2]);
				}
			}
		}

		L1World.getInstance().addVisibleObject(pc);

		pc.beginGameTimeCarrier();

		pc.sendPackets(new S_OwnCharStatus(pc));

		pc.sendPackets(new S_MapID(pc.getMap().getBaseMapId(), pc.getMap()
				.isUnderwater()));

		pc.sendPackets(new S_OwnCharPack(pc));

		pc.sendPackets(new S_SPMR(pc));

		// XXX タイトル情報はS_OwnCharPackに含まれるので多分不要
		S_CharTitle s_charTitle = new S_CharTitle(pc.getId(), pc.getTitle());
		pc.sendPackets(s_charTitle);
		pc.broadcastPacket(s_charTitle);

		pc.sendVisualEffectAtLogin(); // クラウン、毒、水中等の視覚効果を表示

		pc.sendPackets(new S_Weather(L1World.getInstance().getWeather()));

		items(pc);
		skills(pc);
		buff(client, pc);

		pc.sendPackets(new S_ActiveSpells(pc));

		if (pc.getCurrentHp() > 0) {
			pc.setDead(false);
			pc.setStatus(0);
		} else {
			pc.setDead(true);
			pc.setStatus(ActionCodes.ACTION_Die);
		}

		if (pc.getLevel() >= 51 && pc.getLevel() - 50 > pc.getBonusStats()) {
			if ((pc.getBaseStr() + pc.getBaseDex() + pc.getBaseCon()
					+ pc.getBaseInt() + pc.getBaseWis() + pc.getBaseCha()) < 210) {
				pc.sendPackets(new S_bonusstats(pc.getId(), 1));
			}
		}

		if (Config.CHARACTER_CONFIG_IN_SERVER_SIDE) {
			pc.sendPackets(new S_CharacterConfig(pc.getId()));
		}

		serchSummon(pc);

		WarTimeController.getInstance().checkCastleWar(pc);

		if (pc.getClanid() != 0) { // クラン所属中
			L1Clan clan = L1World.getInstance().getClan(pc.getClanname());
			if (clan != null) {
				if (pc.getClanid() == clan.getClanId() && // クランを解散して、再度、同名のクランが創設された時の対策
						pc.getClanname().toLowerCase().equals(
								clan.getClanName().toLowerCase())) {
					L1PcInstance[] clanMembers = clan.getOnlineClanMember();
					for (L1PcInstance clanMember : clanMembers) {
						if (clanMember.getId() != pc.getId()) {
							clanMember.sendPackets(new S_ServerMessage(843, pc
									.getName())); // 只今、血盟員の%0%sがゲームに接続しました。
						}
					}

					// 全戦争リストを取得
					for (L1War war : L1World.getInstance().getWarList()) {
						boolean ret = war.CheckClanInWar(pc.getClanname());
						if (ret) { // 戦争に参加中
							String enemy_clan_name = war.GetEnemyClanName(pc
									.getClanname());
							if (enemy_clan_name != null) {
								// あなたの血盟が現在_血盟と交戦中です。
								pc.sendPackets(new S_War(8, pc.getClanname(),
										enemy_clan_name));
							}
							break;
						}
					}
				} else {
					pc.setClanid(0);
					pc.setClanname("");
					pc.setClanRank(0);
					pc.save(); // DBにキャラクター情報を書き込む
				}
			}
		}

		if (pc.getPartnerId() != 0) { // 結婚中
			L1PcInstance partner = (L1PcInstance) L1World.getInstance()
					.findObject(pc.getPartnerId());
			if (partner != null && partner.getPartnerId() != 0) {
				if (pc.getPartnerId() == partner.getId()
						&& partner.getPartnerId() == pc.getId()) {
					pc.sendPackets(new S_ServerMessage(548)); // あなたのパートナーは今ゲーム中です。
					partner.sendPackets(new S_ServerMessage(549)); // あなたのパートナーはたった今ログインしました。
				}
			}
		}

		if (currentHpAtLoad > pc.getCurrentHp()) {
			pc.setCurrentHp(currentHpAtLoad);
		}
		if (currentMpAtLoad > pc.getCurrentMp()) {
			pc.setCurrentMp(currentMpAtLoad);
		}
		pc.startHpRegeneration();
		pc.startMpRegeneration();
		pc.startObjectAutoUpdate();
		client.CharReStart(false);
		pc.beginExpMonitor();
		pc.save(); // DBにキャラクター情報を書き込む

		pc.sendPackets(new S_OwnCharStatus(pc));

		if (pc.getHellTime() > 0) {
			pc.beginHell(false);
		}
	}

	private void items(L1PcInstance pc) {
		// DBからキャラクターと倉庫のアイテムを読み込む
		CharacterTable.getInstance().restoreInventory(pc);

		pc.sendPackets(new S_InvList(pc.getInventory().getItems()));
	}

	private void bookmarks(L1PcInstance pc) {

		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {

			con = L1DatabaseFactory.getInstance().getConnection();
			pstm = con
					.prepareStatement("SELECT * FROM character_teleport WHERE char_id=? ORDER BY name ASC");
			pstm.setInt(1, pc.getId());

			rs = pstm.executeQuery();
			while (rs.next()) {
				L1BookMark bookmark = new L1BookMark();
				bookmark.setId(rs.getInt("id"));
				bookmark.setCharId(rs.getInt("char_id"));
				bookmark.setName(rs.getString("name"));
				bookmark.setLocX(rs.getInt("locx"));
				bookmark.setLocY(rs.getInt("locy"));
				bookmark.setMapId(rs.getShort("mapid"));
				S_Bookmarks s_bookmarks = new S_Bookmarks(bookmark.getName(),
						bookmark.getMapId(), bookmark.getId());
				pc.addBookMark(bookmark);
				pc.sendPackets(s_bookmarks);
			}

		} catch (SQLException e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		} finally {
			SQLUtil.close(rs);
			SQLUtil.close(pstm);
			SQLUtil.close(con);
		}
	}

	private void skills(L1PcInstance pc) {
		List<L1CharacterSkill> skills = L1CharacterSkill.findByCharcterId(pc
				.getId());
		for (L1CharacterSkill skill : skills) {
			pc.setSkillMastery(skill.getSkillId());
		}

		pc.sendPackets(new S_AddSkill(skills));
	}

	private void serchSummon(L1PcInstance pc) {
		for (L1SummonInstance summon : L1World.getInstance().getAllSummons()) {
			if (summon.getMaster().getId() == pc.getId()) {
				summon.setMaster(pc);
				pc.addPet(summon);
				for (L1PcInstance visiblePc : L1World.getInstance()
						.getVisiblePlayer(summon)) {
					visiblePc.sendPackets(new S_SummonPack(summon, visiblePc));
				}
			}
		}
	}

	private boolean buffByExecutor(L1PcInstance pc, L1CharacterBuff buff) {
		L1Skill skill = SkillTable.getInstance().findBySkillId(
				buff.getSkillId());
		if (skill == null) {
			return false;
		}
		L1BuffSkillExecutor exe = skill.newBuffSkillExecutor();
		if (exe == null) {
			return false;
		}
		pc.setSkillEffect(buff.getSkillId(), buff.getRemainingTime() * 1000 ,buff.getUserLevel());
		exe.restoreEffect(pc, buff);
		return true;
	}

	private void buff(ClientThread clientthread, L1PcInstance pc) {
		for (L1CharacterBuff buff : CharBuffTable.findByCharacterId(pc.getId())) {
			int skillId = buff.getSkillId();
			int remainingTime = buff.getRemainingTime();
			if (buffByExecutor(pc, buff)) {
				continue;
			}

			if (skillId == SHAPE_CHANGE) { // 変身
				if (pc.getMiniGamePlaying() == 0) {
					L1PolyMorph.doPoly(pc, buff.getPolyId(), remainingTime,
							L1PolyMorph.MORPH_BY_LOGIN);
				}
			} else if (skillId == STATUS_BRAVE) { // ブレイブ ポーション等
				if (pc.getMiniGamePlaying() == 0) {
					pc.sendPackets(new S_SkillBrave(pc.getId(), 1,
							remainingTime));
					pc.broadcastPacket(new S_SkillBrave(pc.getId(), 1, 0));
					pc.setBraveSpeed(1);
					pc.setSkillEffect(skillId, remainingTime * 1000 ,0);
				}
			} else if (skillId == STATUS_ELFBRAVE) { // エルヴンワッフル
				pc.sendPackets(new S_SkillBrave(pc.getId(), 3, remainingTime));
				pc.broadcastPacket(new S_SkillBrave(pc.getId(), 3, 0));
				pc.setBraveSpeed(1);
				pc.setSkillEffect(skillId, remainingTime * 1000 ,0);
			} else if (skillId == STATUS_RIBRAVE) { // ユグドラの実
				pc.sendPackets(new S_SkillSound(pc.getId(), 7110));
				pc.broadcastPacket(new S_SkillSound(pc.getId(), 7110));
				pc.setSkillEffect(skillId, remainingTime * 1000 ,0);
			} else if (skillId == STATUS_HASTE) { // グリーン ポーション
				if (pc.getMiniGamePlaying() == 0) {
					pc.sendPackets(new S_SkillHaste(pc.getId(), 1,
							remainingTime));
					pc.broadcastPacket(new S_SkillHaste(pc.getId(), 1, 0));
					pc.setMoveSpeed(1);
					pc.setSkillEffect(skillId, remainingTime * 1000 ,0);
				} else if (pc.getMiniGamePlaying() == 1) {
					pc.setMiniGamePlaying(0);
				}
			} else if (skillId == STATUS_BLUE_POTION) { // ブルーポーション
				pc.sendPackets(new S_SkillIconGFX(34, remainingTime));
				pc.setSkillEffect(skillId, remainingTime * 1000 ,0);
			} else if (skillId == STATUS_CHAT_PROHIBITED) { // チャット禁止
				pc.sendPackets(new S_SkillIconGFX(36, remainingTime));
				pc.setSkillEffect(skillId, remainingTime * 1000 ,0);
			} else {
				L1SkillUse l1skilluse = new L1SkillUse();
				l1skilluse.handleCommands(clientthread.getActiveChar(),
						skillId, pc.getId(), pc.getX(), pc.getY(), null,
						remainingTime, L1SkillUse.TYPE_LOGIN);
			}
		}
	}

	@Override
	public String getType() {
		return C_LOGIN_TO_SERVER;
	}
}
