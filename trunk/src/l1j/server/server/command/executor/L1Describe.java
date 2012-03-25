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
package l1j.server.server.command.executor;

import java.util.StringTokenizer;
import java.util.logging.Logger;

import l1j.server.server.Attribute;
import l1j.server.server.datatables.NpcTable;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.serverpackets.S_SystemMessage;
import l1j.server.server.templates.L1Npc;

public class L1Describe implements L1CommandExecutor
{
	private static Logger _log = Logger.getLogger(L1Describe.class.getName());

	private L1Describe()
	{
	}

	public static L1CommandExecutor getInstance()
	{
		return new L1Describe();
	}

	@Override
	public void execute(L1PcInstance pc, String cmdName, String arg)
	{
		try
		{
			StringBuilder msg = new StringBuilder();
			final String BR = System.getProperty("line.separator");
			int hpr = 0, mpr = 0;

			try // モンスターの情報
			{
				StringTokenizer st = new StringTokenizer(arg);
				String targetName = st.nextToken();
				if (pc.isGm()) // ＧＭのみ参照可
				{
					int npcId = NpcTable.getInstance().findNpcIdByNameWithoutSpace(targetName);
					L1Npc npc = NpcTable.getInstance().getTemplate(npcId);
					msg.append("-- キャラクター --" + BR);
					msg.append(" " + BR);
					msg.append("名前:" + npc.get_name() + (npc.getBoss()? "(ボス)" : "") + BR);
					msg.append("所属:" + npc.get_family() + BR);
					msg.append("レベル:" + npc.get_level() + "(" + npc.get_randomlevel() + ")" + BR);
					msg.append("性向値:" + npc.get_lawful() + "(" + npc.get_randomlawful() + ")" + BR);
					msg.append("経験値:" + npc.get_exp() + "(" + npc.get_randomexp() + ")" + BR);
					msg.append("NPCID:" + npc.get_npcId() + BR);
					msg.append("GFXID:" + npc.get_gfxid() + BR);
					msg.append("S/B:" + npc.get_size());
					msg.append(" " + BR);

 					msg.append("-- ステータス --" + BR);
 					msg.append(" " + BR);
					msg.append("STR:" + npc.get_str() + " ");
					msg.append("DEX:" + npc.get_dex() + " ");
					msg.append("INT:" + npc.get_int() + " ");
					msg.append(" " + BR);
					msg.append("CON:" + npc.get_con() + " ");
					msg.append("WIS:" + npc.get_wis() + " ");
					msg.append(" " + BR);
					msg.append("AC:" + npc.get_ac() + "(" + npc.get_randomac() + ") ");
					msg.append("MR:" + npc.get_mr() + " ");
					msg.append(" " + BR);
					msg.append("HP: " + npc.get_hp() + "(" + npc.get_randomhp() + ") ");
					msg.append("MP: " + npc.get_mp() + "(" + npc.get_randommp() + ") ");
					msg.append(" " + BR);
					msg.append("HPR: " + npc.get_hpr() + "(" + npc.get_hprinterval() + "msec) ");
					msg.append("MPR: " + npc.get_mpr() + "(" + npc.get_mprinterval() + "msec) ");
					msg.append(" " + BR + BR);
					msg.append("攻撃速度:" + npc.get_atkspeed() + BR);
					msg.append("攻撃速度（補助）:" + npc.getAltAtkSpeed() + BR);
					msg.append("魔法速度:" + npc.getAtkMagicSpeed() + BR);
					msg.append("魔法速度（補助）:" + npc.getSubMagicSpeed() + BR);
					msg.append("軽減ダメージ:" + npc.get_damagereduction() + BR);

					msg.append("攻撃距離:" + npc.get_ranged() + BR);
					msg.append("TU:" + ((npc.get_IsTU() == true)? "可" : "不可") + BR);
					msg.append("Erase:" + ((npc.get_IsErase() == true)? "可" : "不可") + BR);
					msg.append("家族とアクティブ:" + npc.get_agrofamily() + BR);
					msg.append("麻痺攻撃:" + npc.get_paralysisatk() + BR);
					msg.append("移動速度:" + npc.get_passispeed() + BR);
					msg.append("毒攻撃:" + npc.get_poisonatk() + BR);
					msg.append(" " + BR);
					npc.get_size();
					msg.append("種別:" + ((npc.get_undead() == 1)? "アンデット" : "通常") + BR);
					msg.append("弱点属性:" + Attribute.getAttribute(npc.get_weakAttr()) + BR);
					msg.append(" " + BR);
					msg.append("リーダーチェンジ:" + npc.getChangeHead() + BR);
					msg.append("実体:" + npc.getImpl() + BR);
					msg.append("カルマ:" + npc.getKarma() + BR);
					msg.append("変身グラフィック:" + npc.getTransformGfxId() + BR);
					msg.append("変身先:" + npc.getTransformId() + BR);
					msg.append("ライトサイズ:" + npc.getLightSize() + BR);
					msg.append(" " + BR);
				}
			}
			catch (Exception e) // 自分の情報
			{
				hpr = pc.getHpr() + pc.getInventory().hpRegenPerTick();
				mpr = pc.getMpr() + pc.getInventory().mpRegenPerTick();

				msg.append("-- キャラクター --" + BR);
				msg.append(" " + BR);
				msg.append("名前:" + pc.getName() + BR);
				msg.append("所属クラン:" + pc.getClanname() + BR);
				msg.append("所持アイテム数:" + pc.getInventory().getSize() + BR);
				msg.append("エリクサー使用回数:" + pc.getElixirStats() + BR);
				msg.append(" "+ BR);

				msg.append("-- ステータス --" + BR);
				msg.append(" " + BR);
				msg.append("STR:" + pc.getStr() + " ");
				msg.append("DEX:" + pc.getDex() + " ");
				msg.append("INT:" + pc.getInt() + " ");
				msg.append(" " + BR);
				msg.append("CON:" + pc.getCon() + " ");
				msg.append("WIS:" + pc.getWis() + " ");
				msg.append("CHA:" + pc.getCha() + " ");
				msg.append(" " + BR);
				msg.append("AC:" + pc.getAc() + " ");
				msg.append("MR:" + pc.getMr() + " ");
				msg.append("SP:" + pc.getSp() + " ");
				msg.append("ER:" + pc.getEr() + " ");
				msg.append(" " + BR);
				msg.append("HPR: " + hpr + " ");
				msg.append("MPR: " + mpr + " ");
				msg.append("Karma:" + pc.getKarma() + " ");
				msg.append(" " + BR + BR);
				msg.append("近距離ダメージ:" + pc.getDmgup() + " ");
				msg.append("近距離命中:" + pc.getHitup() + " ");
				msg.append(" " + BR);
				msg.append("遠距離ダメージ:" + pc.getBowDmgup() + " ");
				msg.append("遠距離命中:" + pc.getBowHitup() + " ");
				msg.append(" " + BR);
				msg.append("軽減ダメージ:" + pc.getDamageReductionByArmor() + " / ");
				msg.append(" " + BR + BR);
				msg.append("凍結耐性:" + pc.getRegistFreeze() + " ");
				msg.append("スタン耐性:" + pc.getRegistStun() + " ");
				msg.append(" " + BR);
				msg.append("石化耐性:" + pc.getRegistStone() + " ");
				msg.append("睡眠耐性:" + pc.getRegistSleep() + " ");
				msg.append(" " + BR);
				msg.append("ホールド耐性:" + pc.getRegistSustain() + " ");
				msg.append("暗闇耐性:" + pc.getRegistBlind() + " ");
				msg.append(" " + BR + BR);
				msg.append("火耐性:" + pc.getFire());
				msg.append("水耐性:" + pc.getWater());
				msg.append("土耐性:" + pc.getEarth());
				msg.append("風耐性:" + pc.getWind());
				msg.append(" " + BR + BR);
			}
			pc.sendPackets(new S_SystemMessage(msg.toString()));
		}
		catch (Exception e)
		{
			pc.sendPackets(new S_SystemMessage(cmdName + " コマンドエラー"));
		}
	}
}
