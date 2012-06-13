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

import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.serverpackets.S_SystemMessage;

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
		if(pc.isGm())
		{
			try
			{
				StringTokenizer st = new StringTokenizer(arg);
				L1PcInstance cha = L1World.getInstance().getPlayer(st.nextToken());
				if(cha == null)
				{
					return;
				}
				StringBuilder msg = new StringBuilder();
				final String BR = System.getProperty("line.separator");

				int hpr = 0, mpr = 0;
				hpr = cha.getHpr() + cha.getInventory().hpRegenPerTick();
				mpr = cha.getMpr() + cha.getInventory().mpRegenPerTick();

				msg.append("-- キャラクター --" + BR);
				msg.append(" " + BR);
				msg.append("名前:" + cha.getName() + BR);
				msg.append("所属クラン:" + cha.getClanname() + BR);
				msg.append("所持アイテム数:" + cha.getInventory().getSize() + BR);
				msg.append("エリクサー使用回数:" + cha.getElixirStats() + BR);
				msg.append(" "+ BR);

				msg.append("-- ステータス --" + BR);
				msg.append(" " + BR);
				msg.append("STR:" + cha.getStr() + " ");
				msg.append("DEX:" + cha.getDex() + " ");
				msg.append("INT:" + cha.getInt() + " ");
				msg.append(" " + BR);
				msg.append("CON:" + cha.getCon() + " ");
				msg.append("WIS:" + cha.getWis() + " ");
				msg.append("CHA:" + cha.getCha() + " ");
				msg.append(" " + BR);
				msg.append("AC:" + cha.getAc() + " ");
				msg.append("MR:" + cha.getMr() + " ");
				msg.append("SP:" + cha.getSp() + " ");
				msg.append("ER:" + cha.getEr() + " ");
				msg.append(" " + BR);
				msg.append("HP: " + cha.getCurrentHp() + " ");
				msg.append("HP(MAX): " + cha.getMaxHp() + " ");
				msg.append("HP(Base): " + cha.getBaseMaxHp());
				msg.append(" " + BR);
				msg.append("MP: " + cha.getCurrentMp() + " ");
				msg.append("MP(MAX): " + cha.getMaxMp() + " ");
				msg.append("MP(Base): " + cha.getBaseMaxMp());
				msg.append(" " + BR);
				msg.append("HPR: " + hpr + " ");
				msg.append("MPR: " + mpr + " ");
				msg.append("Karma:" + cha.getKarma() + " ");
				msg.append(" " + BR + BR);
				msg.append("近距離ダメージ:" + cha.getDmgup() + " ");
				msg.append("近距離命中:" + cha.getHitup() + " ");
				msg.append(" " + BR);
				msg.append("遠距離ダメージ:" + cha.getBowDmgup() + " ");
				msg.append("遠距離命中:" + cha.getBowHitup() + " ");
				msg.append(" " + BR);
				msg.append("軽減ダメージ:" + cha.getDamageReductionByArmor() + " / ");
				msg.append(" " + BR + BR);
				msg.append("凍結耐性:" + cha.getRegistFreeze() + " ");
				msg.append("スタン耐性:" + cha.getRegistStun() + " ");
				msg.append(" " + BR);
				msg.append("石化耐性:" + cha.getRegistStone() + " ");
				msg.append("睡眠耐性:" + cha.getRegistSleep() + " ");
				msg.append(" " + BR);
				msg.append("ホールド耐性:" + cha.getRegistSustain() + " ");
				msg.append("暗闇耐性:" + cha.getRegistBlind() + " ");
				msg.append(" " + BR + BR);
				msg.append("火耐性:" + cha.getFire());
				msg.append("水耐性:" + cha.getWater());
				msg.append("土耐性:" + cha.getEarth());
				msg.append("風耐性:" + cha.getWind());
				msg.append(" " + BR + BR);

				pc.sendPackets(new S_SystemMessage(msg.toString()));
			}
			catch (Exception e)
			{
				pc.sendPackets(new S_SystemMessage(cmdName + " コマンドエラー"));
			}
		}
		else
		{
    		try
    		{
    			StringBuilder msg = new StringBuilder();
    			final String BR = System.getProperty("line.separator");
    			int hpr = 0, mpr = 0;

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
				msg.append("BaseHP: " + pc.getBaseMaxHp());
				msg.append("HP: " + pc.getCurrentHp() + "/" + pc.getMaxHp());
				msg.append("BaseMP: " + pc.getBaseMaxMp());
				msg.append("MP: " + pc.getCurrentMp() + "/" + pc.getMaxMp());
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

    			pc.sendPackets(new S_SystemMessage(msg.toString()));
    		}
    		catch (Exception e)
    		{
    			pc.sendPackets(new S_SystemMessage(cmdName + " コマンドエラー"));
    		}
		}
	}
}
