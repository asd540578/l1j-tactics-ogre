/*
 * This program is free software; you can redistribute it and/or modify
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

package l1j.server.server.model.Instance;

import java.util.logging.Logger;

import l1j.server.server.GeneralThreadPool;
import l1j.server.server.datatables.NPCTalkDataTable;
import l1j.server.server.model.L1Attack;
import l1j.server.server.model.L1NpcTalkData;
import l1j.server.server.model.L1Quest;
import l1j.server.server.model.L1Teleport;
import l1j.server.server.model.L1World;
import l1j.server.server.model.npc.L1NpcHtml;
import l1j.server.server.random.RandomGenerator;
import l1j.server.server.random.RandomGeneratorFactory;
import l1j.server.server.serverpackets.S_NPCTalkReturn;
import l1j.server.server.templates.L1Npc;

// Referenced classes of package l1j.server.server.model:
// L1NpcInstance, L1Teleport, L1NpcTalkData, L1PcInstance,
// L1TeleporterPrices, L1TeleportLocations

public class L1TeleporterInstance extends L1NpcInstance {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public L1TeleporterInstance(L1Npc template) {
		super(template);
	}

	@Override
	public void onAction(L1PcInstance pc) {
		onAction(pc, 0);
	}

	@Override
	public void onAction(L1PcInstance pc, int skillId) {
		L1Attack attack = new L1Attack(pc, this, skillId);
		attack.calcHit();
		attack.action();
		attack.addChaserAttack();
		attack.calcDamage();
		attack.calcStaffOfMana();
		attack.addPcPoisonAttack(pc, this);
		attack.commit();
	}

	@Override
	public void onTalkAction(L1PcInstance pc) {
		int objid = getId();
		L1NpcTalkData talking = NPCTalkDataTable.getInstance().getTemplate(
				getNpcTemplate().get_npcId());
		int npcid = getNpcTemplate().get_npcId();
		L1Quest quest = pc.getQuest();
		String htmlid = null;

		if (talking != null) {
			if (npcid == 50014) { // ディロン
				if (pc.isWizard()) { // ウィザード
					if (quest.get_step(L1Quest.QUEST_LEVEL30) == 1
							&& !pc.getInventory().checkItem(40579)) { // アンデッドの骨
						htmlid = "dilong1";
					} else {
						htmlid = "dilong3";
					}
				}
			} else if (npcid == 70779) { // ゲートアント
				if (pc.getTempCharGfx() == 1037) { // ジャイアントアント変身
					htmlid = "ants3";
				} else if (pc.getTempCharGfx() == 1039) {// ジャイアントアントソルジャー変身
					if (pc.isCrown()) { // 君主
						if (quest.get_step(L1Quest.QUEST_LEVEL30) == 1) {
							if (pc.getInventory().checkItem(40547)) { // 住民たちの遺品
								htmlid = "antsn";
							} else {
								htmlid = "ants1";
							}
						} else { // Step1以外
							htmlid = "antsn";
						}
					} else { // 君主以外
						htmlid = "antsn";
					}
				}
			} else if (npcid == 70853) { // フェアリープリンセス
				if (pc.isElf()) { // エルフ
					if (quest.get_step(L1Quest.QUEST_LEVEL30) == 1) {
						if (!pc.getInventory().checkItem(40592)) { // 呪われた精霊書
							RandomGenerator random = RandomGeneratorFactory
									.getSharedRandom();
							if (random.nextInt(100) < 50) { // 50%でダークマールダンジョン
								htmlid = "fairyp2";
							} else { // ダークエルフダンジョン
								htmlid = "fairyp1";
							}
						}
					}
				}
			} else if (npcid == 50031) { // セピア
				if (pc.isElf()) { // エルフ
					if (quest.get_step(L1Quest.QUEST_LEVEL45) == 2) {
						if (!pc.getInventory().checkItem(40602)) { // ブルーフルート
							htmlid = "sepia1";
						}
					}
				}
			} else if (npcid == 50043) { // ラムダ
				if (quest.get_step(L1Quest.QUEST_LEVEL50) == L1Quest.QUEST_END) {
					htmlid = "ramuda2";
				} else if (quest.get_step(L1Quest.QUEST_LEVEL50) == 1) { // ディガルディン同意済み
					if (pc.isCrown()) { // 君主
						if (_isNowDely) { // テレポートディレイ中
							htmlid = "ramuda4";
						} else {
							htmlid = "ramudap1";
						}
					} else { // 君主以外
						htmlid = "ramuda1";
					}
				} else {
					htmlid = "ramuda3";
				}
			}
			// 歌う島のテレポーター
			else if (npcid == 50082) {
				if (pc.getLevel() < 13) {
					htmlid = "en0221";
				} else {
					if (pc.isElf()) {
						htmlid = "en0222e";
					} else if (pc.isDarkelf()) {
						htmlid = "en0222d";
					} else {
						htmlid = "en0222";
					}
				}
			}
			// バルニア
			else if (npcid == 50001) {
				if (pc.isElf()) {
					htmlid = "barnia3";
				} else if (pc.isKnight() || pc.isCrown()) {
					htmlid = "barnia2";
				} else if (pc.isWizard() || pc.isDarkelf()) {
					htmlid = "barnia1";
				}
			}

			// html表示
			if (htmlid != null) { // htmlidが指定されている場合
				pc.sendPackets(new S_NPCTalkReturn(objid, htmlid));
			} else {
				if (pc.getLawful() < -1000) { // プレイヤーがカオティック
					pc.sendPackets(new S_NPCTalkReturn(talking, objid, 2));
				} else {
					pc.sendPackets(new S_NPCTalkReturn(talking, objid, 1));
				}
			}
		} else {
			_log.finest((new StringBuilder())
					.append("No actions for npc id : ").append(objid)
					.toString());
		}
	}

	@Override
	public void onFinalAction(L1PcInstance pc, String action) {
		int objid = getId();
		L1NpcTalkData talking = NPCTalkDataTable.getInstance().getTemplate(
				getNpcTemplate().get_npcId());
		if (action.equalsIgnoreCase("teleportURL")) {
			L1NpcHtml html = new L1NpcHtml(talking.getTeleportURL());
			pc.sendPackets(new S_NPCTalkReturn(objid, html));
			// TODO テレポーター金額表示 start
			String[] price = null;
			int npcid = getNpcTemplate().get_npcId();
			switch (npcid) {
			case 50015: { // 話せる島－ルーカス
				price = new String[] { "1500" };
			}
				break;
			case 50020: { // ケント－スタンリー
				price = new String[] { "50", "50", "50", "120", "120", "120",
						"120", "180", "180", "200", "200", "600", "7100" };
			}
				break;
			case 50024: { // グルーディン-アスター
				price = new String[] { "55", "55", "55", "132", "132", "198",
						"198", "198", "264", "264", "264", "220", "220", "550",
						"7480" };
			}
				break;
			case 50036: { // ギラン-ウィルマ
				price = new String[] { "52", "52", "52", "126", "126", "126",
						"126", "189", "189", "315", "315", "735", "7770" };
			}
				break;
			case 50039: { // ウェルダン-レスリー
				price = new String[] { "51", "51", "123", "123", "185", "185",
						"185", "247", "247", "412", "412", "824", "7931" };
			}
				break;
			case 50044: { // アデン-シリウス
				price = new String[] { "54", "129", "129", "194", "194", "194",
						"259", "259", "324", "540", "540", "927", "7992" };
			}
				break;
			case 50046: { // アデン-エレリス
				price = new String[] { "54", "129", "129", "194", "194", "194",
						"259", "259", "324", "540", "540", "927", "7992" };
			}
				break;
			case 50051: { // オーレン-キリウス
				price = new String[] { "50", "120", "180", "180", "240", "240",
						"240", "300", "300", "500", "500", "900", "8000" };
			}
				break;
			case 50054: { // ウィンダーウッド-トレイ
				price = new String[] { "70", "70", "168", "168", "168", "252",
						"252", "336", "420", "280", "280", "700", "9100" };
			}
				break;
			case 50056: { // シルバーナイトタウン-メット
				price = new String[] { "55", "55", "55", "132", "132", "132",
						"198", "198", "198", "1000", "360", "330", "330",
						"770", "7480" };
			}
				break;
			case 50066: { // ハイネ-リオル
				price = new String[] { "50", "50", "50", "120", "120", "120",
						"180", "180", "240", "400", "400", "800", "7100" };
			}
				break;
			case 50068: { // 　沈黙の洞窟-ディアノス
				price = new String[] { "1500", "800", "600", "1800", "1800",
						"1000" };
			}
				break;
			case 50079: { // 　ディアド要塞-ダニエル
				price = new String[] { "550", "550", "550", "600", "600",
						"600", "650", "700", "750", "750", "500", "500", "700" };
			}
				break;
			case 80146: { // 　シルベリア-シャリエル
				price = new String[] { "50", "50", "50", "120", "180", "180",
						"240", "240", "240", "300", "300", "500", "500", "900",
						"8000" };
			}
				break;
			case 80132: { // 　ベヒモス-デカピア
				price = new String[] { "50", "50", "50", "50", "120", "120",
						"180", "180", "180", "240", "240", "400", "400", "800",
						"7700" };
			}

				break;
			case 50026: { // グルーディン-市場テレポーター
				price = new String[] { "550", "700", "810" };
			}
				break;
			case 50033: { // ギラン-市場テレポーター
				price = new String[] { "560", "720", "560" };
			}
				break;
			case 50049: { // オーレン-市場テレポーター
				price = new String[] { "1150", "980", "590" };
			}
				break;
			case 50059: { // シルバーナイトタウン-市場テレポーター
				price = new String[] { "580", "680", "680" };
			}
				break;
			default: {
				price = new String[] { "" };
			}
			}
			pc.sendPackets(new S_NPCTalkReturn(objid, html, price));
			// TODO テレポーター金額表示end
		}
		// TODO テレポーター狩場金額表示 start
		else if (action.equalsIgnoreCase("teleportURLA")) {
			String html = "";
			String[] price = null;
			int npcid = getNpcTemplate().get_npcId();
			switch (npcid) {
			case 80146: { // 　シルベリア-シャリエル
				html = "sharial3";
				price = new String[] { "220", "330", "330", "330", "440",
						"440", "550", "550", "550", "550" };
			}
				break;
			case 80132: { // 　ベヒモス-デカピア
				html = "dekabia3";
				price = new String[] { "100", "220", "220", "220", "330",
						"330", "330", "440", "440" };
			}
				break;
			case 50079: { // 　ディアド要塞-ダニエル
				html = "telediad1";
				price = new String[] { "700", "800", "800", "1000" };
			}
				break;
			default: {
				price = new String[] { "" };
			}
			}
			pc.sendPackets(new S_NPCTalkReturn(objid, html, price));
			// TODO テレポーター狩場金額 end
			// } else if (action.equalsIgnoreCase("teleportURLA")) {

			// L1NpcHtml html = new L1NpcHtml(talking.getTeleportURLA());
			// pc.sendPackets(new S_NPCTalkReturn(objid, html));
		} else if (action.equalsIgnoreCase("teleportURLC")) {
			String html = "";
			// TODO テレポーター金額表示 start
			String[] price = null;
			int npcid = getNpcTemplate().get_npcId();
			switch (npcid) {
			case 50020: { // ケント－スタンリー
				html = "guide_1_2";
				price = new String[] { "334", "334", "334", "334", "766", "766" }; // 税率18%
			}
				break;
			case 50024: { // グルーディン-アスター
				html = "guide_1_2";
				price = new String[] { "334", "334", "334", "334", "766", "766" }; // 税率18%
			}
				break;
			case 50036: { // ギラン-ウィルマ
				html = "guide_1_2";
				price = new String[] { "310", "310", "310", "310", "710", "710" }; // 税率10%
			}
				break;
			case 50039: { // ウェルダン-レスリー
				html = "guide_1_2";
				price = new String[] { "310", "310", "310", "310", "710", "710" }; // 税率10%
			}
				break;
			case 50044: { // アデン-シリウス
				html = "guide_1_2";
				price = new String[] { "334", "334", "334", "334", "766", "766" }; // 税率18%
			}
				break;
			case 50046: { // アデン-エレリス
				html = "guide_1_2";
				price = new String[] { "334", "334", "334", "334", "766", "766" }; // 税率18%
			}
				break;
			case 50051: { // オーレン-キリウス
				html = "guide_1_2";
				price = new String[] { "310", "310", "310", "310", "710", "710" }; // 税率10%
			}
				break;
			case 50054: { // ウィンダーウッド-トレイ
				html = "guide_1_2";
				price = new String[] { "434", "434", "434", "434", "994", "994" }; // 税率50%
			}
				break;
			case 50056: { // シルバーナイトタウン-メット
				html = "guide_1_2";
				price = new String[] { "434", "434", "434", "434", "994", "994" }; // 税率50%
			}
				break;
			case 50066: { // ハイネ-リオル
				html = "guide_1_2";
				price = new String[] { "310", "310", "310", "310", "710", "710" }; // 税率10%
			}
				break;
			default: {
				price = new String[] { "" };
			}
			}
			pc.sendPackets(new S_NPCTalkReturn(objid, html, price));
		} else if (action.equalsIgnoreCase("teleportURLB")) {
			String html = "";
			String[] price = null;
			int npcid = getNpcTemplate().get_npcId();
			switch (npcid) {
			case 50020: { // ケント－スタンリー
				html = "guide_1_1";
				price = new String[] { "324", "324", "324", "324" }; // 税率18%
			}
				break;
			case 50024: { // グルーディン-アスター
				html = "guide_1_1";
				price = new String[] { "324", "324", "324", "324" }; // 税率18%
			}
				break;
			case 50036: { // ギラン-ウィルマ
				html = "guide_1_1";
				price = new String[] { "300", "300", "300", "300" }; // 税率10%
			}
				break;
			case 50039: { // ウェルダン-レスリー
				html = "guide_1_1";
				price = new String[] { "300", "300", "300", "300" }; // 税率10%
			}
				break;
			case 50044: { // アデン-シリウス
				html = "guide_1_1";
				price = new String[] { "324", "324", "324", "324" }; // 税率18%
			}
				break;
			case 50046: { // アデン-エレリス
				html = "guide_1_1";
				price = new String[] { "324", "324", "324", "324" }; // 税率18%
			}
				break;
			case 50051: { // オーレン-キリウス
				html = "guide_1_1";
				price = new String[] { "300", "300", "300", "300" }; // 税率10%
			}
				break;
			case 50054: { // ウィンダーウッド-トレイ
				html = "guide_1_1";
				price = new String[] { "420", "420", "420", "420" }; // 税率50%
			}
				break;
			case 50056: { // シルバーナイトタウン-メット
				html = "guide_1_1";
				price = new String[] { "420", "420", "420", "420" }; // 税率50%
			}
				break;
			case 50066: { // ハイネ-リオル
				html = "guide_1_1";
				price = new String[] { "300", "300", "300", "300" }; // 税率10%
			}
				break;
			default: {
				price = new String[] { "" };
			}
			}
			pc.sendPackets(new S_NPCTalkReturn(objid, html, price));
		} else if (action.equalsIgnoreCase("teleportURLD")) {
			String html = "";
			String[] price = null;
			int npcid = getNpcTemplate().get_npcId();
			switch (npcid) {
			case 50020: { // ケント－スタンリー
				html = "guide_1_3";
				price = new String[] { "345", "345", "345", "345", "453",
						"777", "453" }; // 税率18%
			}
				break;
			case 50024: { // グルーディン-アスター
				html = "guide_1_3";
				price = new String[] { "345", "345", "345", "345", "453",
						"777", "453" }; // 税率18%
			}
				break;
			case 50036: { // ギラン-ウィルマ
				html = "guide_1_3";
				price = new String[] { "320", "320", "320", "320", "420",
						"720", "420" }; // 税率10%
			}
				break;
			case 50039: { // ウェルダン-レスリー
				html = "guide_1_3";
				price = new String[] { "320", "320", "320", "320", "420",
						"720", "420" }; // 税率10%
			}
				break;
			case 50044: { // アデン-シリウス
				html = "guide_1_3";
				price = new String[] { "345", "345", "345", "345", "453",
						"777", "453" }; // 税率18%
			}
				break;
			case 50046: { // アデン-エレリス
				html = "guide_1_3";
				price = new String[] { "345", "345", "345", "345", "453",
						"777", "453" }; // 税率18%
			}
				break;
			case 50051: { // オーレン-キリウス
				html = "guide_1_3";
				price = new String[] { "320", "320", "320", "320", "420",
						"720", "420" }; // 税率10%
			}
				break;
			case 50054: { // ウィンダーウッド-トレイ
				html = "guide_1_3";
				price = new String[] { "448", "448", "448", "448", "588",
						"1008", "588" }; // 税率50%
			}
				break;
			case 50056: { // シルバーナイトタウン-メット
				html = "guide_1_3";
				price = new String[] { "448", "448", "448", "448", "588",
						"1008", "588" }; // 税率50%
			}
				break;
			case 50066: { // ハイネ-リオル
				html = "guide_1_3";
				price = new String[] { "320", "320", "320", "320", "420",
						"720", "420" }; // 税率10%
			}
				break;
			default: {
				price = new String[] { "" };
			}
			}
			pc.sendPackets(new S_NPCTalkReturn(objid, html, price));
		} else if (action.equalsIgnoreCase("teleportURLF")) {
			String html = "";
			String[] price = null;
			int npcid = getNpcTemplate().get_npcId();
			switch (npcid) {
			case 50020: { // ケント－スタンリー
				html = "guide_2_2";
				price = new String[] { "442", "442", "658", "550" }; // 税率18%
			}
				break;
			case 50024: { // グルーディン-アスター
				html = "guide_2_2";
				price = new String[] { "442", "442", "658", "550" }; // 税率18%
			}
				break;
			case 50036: { // ギラン-ウィルマ
				html = "guide_2_2";
				price = new String[] { "410", "410", "610", "510" }; // 税率10%
			}
				break;
			case 50039: { // ウェルダン-レスリー
				html = "guide_2_2";
				price = new String[] { "410", "410", "610", "510" }; // 税率10%
			}
				break;
			case 50044: { // アデン-シリウス
				html = "guide_2_2";
				price = new String[] { "442", "442", "658", "550" }; // 税率18%
			}
				break;
			case 50046: { // アデン-エレリス
				html = "guide_2_2";
				price = new String[] { "442", "442", "658", "550" }; // 税率18%
			}
				break;
			case 50051: { // オーレン-キリウス
				html = "guide_2_2";
				price = new String[] { "410", "410", "610", "510" }; // 税率10%
			}
				break;
			case 50054: { // ウィンダーウッド-トレイ
				html = "guide_2_2";
				price = new String[] { "574", "574", "854", "714" }; // 税率50%
			}
				break;
			case 50056: { // シルバーナイトタウン-メット
				html = "guide_2_2";
				price = new String[] { "574", "574", "854", "714" }; // 税率50%
			}
				break;
			case 50066: { // ハイネ-リオル
				html = "guide_2_2";
				price = new String[] { "410", "410", "610", "510" }; // 税率10%
			}
				break;
			default: {
				price = new String[] { "" };
			}
			}
			pc.sendPackets(new S_NPCTalkReturn(objid, html, price));
		} else if (action.equalsIgnoreCase("teleportURLE")) {
			String html = "";
			String[] price = null;
			int npcid = getNpcTemplate().get_npcId();
			switch (npcid) {
			case 50020: { // ケント－スタンリー
				html = "guide_2_1";
				price = new String[] { "432", "432", "540", "540" }; // 税率18%
			}
				break;
			case 50024: { // グルーディン-アスター
				html = "guide_2_1";
				price = new String[] { "432", "432", "540", "540" }; // 税率18%
			}
				break;
			case 50036: { // ギラン-ウィルマ
				html = "guide_2_1";
				price = new String[] { "400", "400", "500", "500" }; // 税率10%
			}
				break;
			case 50039: { // ウェルダン-レスリー
				html = "guide_2_1";
				price = new String[] { "400", "400", "500", "500" }; // 税率10%
			}
				break;
			case 50044: { // アデン-シリウス
				html = "guide_2_1";
				price = new String[] { "432", "432", "540", "540" }; // 税率18%
			}
				break;
			case 50046: { // アデン-エレリス
				html = "guide_2_1";
				price = new String[] { "432", "432", "540", "540" }; // 税率18%
			}
				break;
			case 50051: { // オーレン-キリウス
				html = "guide_2_1";
				price = new String[] { "400", "400", "500", "500" }; // 税率10%
			}
				break;
			case 50054: { // ウィンダーウッド-トレイ
				html = "guide_2_1";
				price = new String[] { "560", "560", "700", "700" }; // 税率50%
			}
				break;
			case 50056: { // シルバーナイトタウン-メット
				html = "guide_2_1";
				price = new String[] { "560", "560", "700", "700" }; // 税率50%
			}
				break;
			case 50066: { // ハイネ-リオル
				html = "guide_2_1";
				price = new String[] { "400", "400", "500", "500" }; // 税率10%
			}
				break;
			default: {
				price = new String[] { "" };
			}
			}
			pc.sendPackets(new S_NPCTalkReturn(objid, html, price));
		} else if (action.equalsIgnoreCase("teleportURLG")) {
			String html = "";
			String[] price = null;
			int npcid = getNpcTemplate().get_npcId();
			switch (npcid) {
			case 50020: { // ケント－スタンリー
				html = "guide_2_3";
				price = new String[] { "453", "777", "669", "669", "669" }; // 税率18%
			}
				break;
			case 50024: { // グルーディン-アスター
				html = "guide_2_3";
				price = new String[] { "453", "777", "669", "669", "669" }; // 税率18%
			}
				break;
			case 50036: { // ギラン-ウィルマ
				html = "guide_2_3";
				price = new String[] { "420", "720", "620", "620", "620" }; // 税率10%
			}
				break;
			case 50039: { // ウェルダン-レスリー
				html = "guide_2_3";
				price = new String[] { "420", "720", "620", "620", "620" }; // 税率10%
			}
				break;
			case 50044: { // アデン-シリウス
				html = "guide_2_3";
				price = new String[] { "453", "777", "669", "669", "669" }; // 税率18%
			}
				break;
			case 50046: { // アデン-エレリス
				html = "guide_2_3";
				price = new String[] { "453", "777", "669", "669", "669" }; // 税率18%
			}
				break;
			case 50051: { // オーレン-キリウス
				html = "guide_2_3";
				price = new String[] { "420", "720", "620", "620", "620" }; // 税率10%
			}
				break;
			case 50054: { // ウィンダーウッド-トレイ
				html = "guide_2_3";
				price = new String[] { "588", "1008", "868", "868", "868" }; // 税率50%
			}
				break;
			case 50056: { // シルバーナイトタウン-メット
				html = "guide_2_3";
				price = new String[] { "588", "1008", "868", "868", "868" }; // 税率50%
			}
				break;
			case 50066: { // ハイネ-リオル
				html = "guide_2_3";
				price = new String[] { "420", "720", "620", "620", "620" }; // 税率10%
			}
				break;
			default: {
				price = new String[] { "" };
			}
			}
			pc.sendPackets(new S_NPCTalkReturn(objid, html, price));
		} else if (action.equalsIgnoreCase("teleportURLI")) {
			String html = "";
			String[] price = null;
			int npcid = getNpcTemplate().get_npcId();
			switch (npcid) {
			case 50020: { // ケント－スタンリー
				html = "guide_3_2";
				price = new String[] { "550", "550", "550", "550", "1090",
						"874", "658" }; // 税率18%
			}
				break;
			case 50024: { // グルーディン-アスター
				html = "guide_3_2";
				price = new String[] { "550", "550", "550", "550", "1090",
						"874", "658" }; // 税率18%
			}
				break;
			case 50036: { // ギラン-ウィルマ
				html = "guide_3_2";
				price = new String[] { "510", "510", "510", "510", "1010",
						"810", "610" }; // 税率10%
			}
				break;
			case 50039: { // ウェルダン-レスリー
				html = "guide_3_2";
				price = new String[] { "510", "510", "510", "510", "1010",
						"810", "610" }; // 税率10%
			}
				break;
			case 50044: { // アデン-シリウス
				html = "guide_3_2";
				price = new String[] { "550", "550", "550", "550", "1090",
						"874", "658" }; // 税率18%
			}
				break;
			case 50046: { // アデン-エレリス
				html = "guide_3_2";
				price = new String[] { "550", "550", "550", "550", "1090",
						"874", "658" }; // 税率18%
			}
				break;
			case 50051: { // オーレン-キリウス
				html = "guide_3_2";
				price = new String[] { "510", "510", "510", "510", "1010",
						"810", "610" }; // 税率10%
			}
				break;
			case 50054: { // ウィンダーウッド-トレイ
				html = "guide_3_2";
				price = new String[] { "714", "714", "714", "714", "1414",
						"1134", "854" }; // 税率50%
			}
				break;
			case 50056: { // シルバーナイトタウン-メット
				html = "guide_3_2";
				price = new String[] { "714", "714", "714", "714", "1414",
						"1134", "854" }; // 税率50%
			}
				break;
			case 50066: { // ハイネ-リオル
				html = "guide_3_2";
				price = new String[] { "510", "510", "510", "510", "1010",
						"810", "610" }; // 税率10%
			}
				break;
			default: {
				price = new String[] { "" };
			}
			}
			pc.sendPackets(new S_NPCTalkReturn(objid, html, price));
		} else if (action.equalsIgnoreCase("teleportURLH")) {
			String html = "";
			String[] price = null;
			int npcid = getNpcTemplate().get_npcId();
			switch (npcid) {
			case 50020: { // ケント－スタンリー
				html = "guide_3_1";
				price = new String[] { "540", "540", "549", "864" }; // 税率18%
			}
				break;
			case 50024: { // グルーディン-アスター
				html = "guide_3_1";
				price = new String[] { "540", "540", "549", "864" }; // 税率18%
			}
				break;
			case 50036: { // ギラン-ウィルマ
				html = "guide_3_1";
				price = new String[] { "500", "500", "500", "800" }; // 税率10%
			}
				break;
			case 50039: { // ウェルダン-レスリー
				html = "guide_3_1";
				price = new String[] { "500", "500", "500", "800" }; // 税率10%
			}
				break;
			case 50044: { // アデン-シリウス
				html = "guide_3_1";
				price = new String[] { "540", "540", "549", "864" }; // 税率18%
			}
				break;
			case 50046: { // アデン-エレリス
				html = "guide_3_1";
				price = new String[] { "540", "540", "549", "864" }; // 税率18%
			}
				break;
			case 50051: { // オーレン-キリウス
				html = "guide_3_1";
				price = new String[] { "500", "500", "500", "800" }; // 税率10%
			}
				break;
			case 50054: { // ウィンダーウッド-トレイ
				html = "guide_3_1";
				price = new String[] { "700", "700", "700", "1120" }; // 税率50%
			}
				break;
			case 50056: { // シルバーナイトタウン-メット
				html = "guide_3_1";
				price = new String[] { "700", "700", "700", "1120" }; // 税率50%
			}
				break;
			case 50066: { // ハイネ-リオル
				html = "guide_3_1";
				price = new String[] { "500", "500", "500", "800" }; // 税率10%
			}
				break;
			default: {
				price = new String[] { "" };
			}
			}
			pc.sendPackets(new S_NPCTalkReturn(objid, html, price));
		} else if (action.equalsIgnoreCase("teleportURLK")) {
			String html = "";
			String[] price = null;
			int npcid = getNpcTemplate().get_npcId();
			switch (npcid) {
			case 50020: { // ケント－スタンリー
				html = "guide_4";
				price = new String[] { "561", "561", "561", "561", "561",
						"777", "777" }; // 税率18%
			}
				break;
			case 50024: { // グルーディン-アスター
				html = "guide_4";
				price = new String[] { "561", "561", "561", "561", "561",
						"777", "777" }; // 税率18%
			}
				break;
			case 50036: { // ギラン-ウィルマ
				html = "guide_4";
				price = new String[] { "520", "520", "520", "520", "520",
						"720", "720" }; // 税率10%
			}
				break;
			case 50039: { // ウェルダン-レスリー
				html = "guide_4";
				price = new String[] { "520", "520", "520", "520", "520",
						"720", "720" }; // 税率10%
			}
				break;
			case 50044: { // アデン-シリウス
				html = "guide_4";
				price = new String[] { "561", "561", "561", "561", "561",
						"777", "777" }; // 税率18%
			}
				break;
			case 50046: { // アデン-エレリス
				html = "guide_4";
				price = new String[] { "561", "561", "561", "561", "561",
						"777", "777" }; // 税率18%
			}
				break;
			case 50051: { // オーレン-キリウス
				html = "guide_4";
				price = new String[] { "520", "520", "520", "520", "520",
						"720", "720" }; // 税率10%
			}
				break;
			case 50054: { // ウィンダーウッド-トレイ
				html = "guide_4";
				price = new String[] { "728", "728", "728", "728", "728",
						"1008", "1008" }; // 税率50%
			}
				break;
			case 50056: { // シルバーナイトタウン-メット
				html = "guide_4";
				price = new String[] { "728", "728", "728", "728", "728",
						"1008", "1008" }; // 税率50%
			}
				break;
			case 50066: { // ハイネ-リオル
				html = "guide_4";
				price = new String[] { "520", "520", "520", "520", "520",
						"720", "720" }; // 税率10%
			}
				break;
			default: {
				price = new String[] { "" };
			}
			}
			pc.sendPackets(new S_NPCTalkReturn(objid, html, price));
		} else if (action.equalsIgnoreCase("teleportURLJ")) {
			String html = "";
			String[] price = null;
			int npcid = getNpcTemplate().get_npcId();
			switch (npcid) {
			case 50020: { // ケント－スタンリー
				html = "guide_3_3";
				price = new String[] { "561", "561", "561", "561", "561",
						"777", "777" }; // 税率18%
			}
				break;
			case 50024: { // グルーディン-アスター
				html = "guide_3_3";
				price = new String[] { "561", "561", "561", "561", "561",
						"777", "777" }; // 税率18%
			}
				break;
			case 50036: { // ギラン-ウィルマ
				html = "guide_3_3";
				price = new String[] { "520", "520", "520", "520", "520",
						"720", "720" }; // 税率10%
			}
				break;
			case 50039: { // ウェルダン-レスリー
				html = "guide_3_3";
				price = new String[] { "520", "520", "520", "520", "520",
						"720", "720" }; // 税率10%
			}
				break;
			case 50044: { // アデン-シリウス
				html = "guide_3_3";
				price = new String[] { "561", "561", "561", "561", "561",
						"777", "777" }; // 税率18%
			}
				break;
			case 50046: { // アデン-エレリス
				html = "guide_3_3";
				price = new String[] { "561", "561", "561", "561", "561",
						"777", "777" }; // 税率18%
			}
				break;
			case 50051: { // オーレン-キリウス
				html = "guide_3_3";
				price = new String[] { "520", "520", "520", "520", "520",
						"720", "720" }; // 税率10%
			}
				break;
			case 50054: { // ウィンダーウッド-トレイ
				html = "guide_3_3";
				price = new String[] { "728", "728", "728", "728", "728",
						"1008", "1008" }; // 税率50%
			}
				break;
			case 50056: { // シルバーナイトタウン-メット
				html = "guide_3_3";
				price = new String[] { "728", "728", "728", "728", "728",
						"1008", "1008" }; // 税率50%
			}
				break;
			case 50066: { // ハイネ-リオル
				html = "guide_3_3";
				price = new String[] { "520", "520", "520", "520", "520",
						"720", "720" }; // 税率10%
			}
				break;
			default: {
				price = new String[] { "" };
			}
			}
			pc.sendPackets(new S_NPCTalkReturn(objid, html, price));
		}
		if (action.startsWith("teleport ")) {
			_log.finest((new StringBuilder()).append("Setting action to : ")
					.append(action).toString());
			doFinalAction(pc, action);
		}
	}

	private void doFinalAction(L1PcInstance pc, String action) {
		int objid = getId();

		int npcid = getNpcTemplate().get_npcId();
		String htmlid = null;
		boolean isTeleport = true;

		if (npcid == 50014) { // ディロン
			if (!pc.getInventory().checkItem(40581)) { // アンデッドのキー
				isTeleport = false;
				htmlid = "dilongn";
			}
		} else if (npcid == 50043) { // ラムダ
			if (_isNowDely) { // テレポートディレイ中
				isTeleport = false;
			}
		} else if (npcid == 50625) { // 古代人（Lv50クエスト古代の空間2F）
			if (_isNowDely) { // テレポートディレイ中
				isTeleport = false;
			}
		}

		if (isTeleport) { // テレポート実行
			try {
				// ミュータントアントダンジョン(君主Lv30クエスト)
				if (action.equalsIgnoreCase("teleport mutant-dungen")) {
					// 3マス以内のPc
					for (L1PcInstance otherPc : L1World.getInstance()
							.getVisiblePlayer(pc, 3)) {
						if (otherPc.getClanid() == pc.getClanid()
								&& otherPc.getId() != pc.getId()) {
							L1Teleport.teleport(otherPc, 32740, 32800,
									(short) 217, 5, true);
						}
					}
					L1Teleport.teleport(pc, 32740, 32800, (short) 217, 5, true);
				}
				// 試練のダンジョン（ウィザードLv30クエスト）
				else if (action.equalsIgnoreCase("teleport mage-quest-dungen")) {
					L1Teleport.teleport(pc, 32791, 32788, (short) 201, 5, true);
				} else if (action.equalsIgnoreCase("teleport 29")) { // ラムダ
					L1PcInstance kni = null;
					L1PcInstance elf = null;
					L1PcInstance wiz = null;
					// 3マス以内のPc
					for (L1PcInstance otherPc : L1World.getInstance()
							.getVisiblePlayer(pc, 3)) {
						L1Quest quest = otherPc.getQuest();
						if (otherPc.isKnight() // ナイト
								&& quest.get_step(L1Quest.QUEST_LEVEL50) == 1) { // ディガルディン同意済み
							if (kni == null) {
								kni = otherPc;
							}
						} else if (otherPc.isElf() // エルフ
								&& quest.get_step(L1Quest.QUEST_LEVEL50) == 1) { // ディガルディン同意済み
							if (elf == null) {
								elf = otherPc;
							}
						} else if (otherPc.isWizard() // ウィザード
								&& quest.get_step(L1Quest.QUEST_LEVEL50) == 1) { // ディガルディン同意済み
							if (wiz == null) {
								wiz = otherPc;
							}
						}
					}
					if (kni != null && elf != null && wiz != null) { // 全クラス揃っている
						L1Teleport.teleport(pc, 32723, 32850, (short) 2000, 2,
								true);
						L1Teleport.teleport(kni, 32750, 32851, (short) 2000, 6,
								true);
						L1Teleport.teleport(elf, 32878, 32980, (short) 2000, 6,
								true);
						L1Teleport.teleport(wiz, 32876, 33003, (short) 2000, 0,
								true);
						TeleportDelyTimer timer = new TeleportDelyTimer();
						GeneralThreadPool.getInstance().execute(timer);
					}
				} else if (action.equalsIgnoreCase("teleport barlog")) {
					// 古代人（Lv50クエスト古代の空間2F）
					L1Teleport
							.teleport(pc, 32755, 32844, (short) 2002, 5, true);
					TeleportDelyTimer timer = new TeleportDelyTimer();
					GeneralThreadPool.getInstance().execute(timer);
				}
			} catch (Exception e) {
			}
		}
		if (htmlid != null) { // 表示するhtmlがある場合
			pc.sendPackets(new S_NPCTalkReturn(objid, htmlid));
		}
	}

	class TeleportDelyTimer implements Runnable {

		public TeleportDelyTimer() {
		}

		public void run() {
			try {
				_isNowDely = true;
				Thread.sleep(900000); // 15分
			} catch (Exception e) {
				_isNowDely = false;
			}
			_isNowDely = false;
		}
	}

	private boolean _isNowDely = false;
	private static Logger _log = Logger
			.getLogger(l1j.server.server.model.Instance.L1TeleporterInstance.class
					.getName());
}