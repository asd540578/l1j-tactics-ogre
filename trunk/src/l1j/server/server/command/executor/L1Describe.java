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

import java.util.logging.Logger;

import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.serverpackets.S_SystemMessage;

public class L1Describe implements L1CommandExecutor {
	private static Logger _log = Logger.getLogger(L1Describe.class.getName());

	private L1Describe() {
	}

	public static L1CommandExecutor getInstance() {
		return new L1Describe();
	}

	@Override
	public void execute(L1PcInstance pc, String cmdName, String arg) {
		try {
			StringBuilder msg = new StringBuilder();
			pc.sendPackets(new S_SystemMessage("-- describe: " + pc.getName()
					+ " --"));
			int hpr = pc.getHpr() + pc.getInventory().hpRegenPerTick();
			int mpr = pc.getMpr() + pc.getInventory().mpRegenPerTick();
			msg.append("Dmg: +" + pc.getDmgup() + " / ");
			msg.append("Hit: +" + pc.getHitup() + " / ");
			msg.append("BowDmg: +" + pc.getBowDmgup() + " / ");
			msg.append("BowHit: +" + pc.getBowHitup() + " / ");
			/* t.s 2011/09/06 mod start */
			msg.append("DamageReduction: +" + pc.getDamageReductionByArmor()
					+ " / ");
			/* t.s 2011/09/06 mod end */
			msg.append("MR: " + pc.getMr() + " / ");
			msg.append("HPR: " + hpr + " / ");
			msg.append("MPR: " + mpr + " / ");
			msg.append("凍結耐性: " + pc.getRegistFreeze() + " / ");
			msg.append("スタン耐性: " + pc.getRegistStun() + " / ");
			msg.append("石化耐性: " + pc.getRegistStone() + " / ");
			msg.append("睡眠耐性: " + pc.getRegistSleep() + " / ");
			msg.append("ホールド耐性: " + pc.getRegistSustain() + " / ");
			msg.append("闇耐性: " + pc.getRegistBlind() + " / ");
			msg.append("Karma: " + pc.getKarma() + " / ");
			msg.append("Item: " + pc.getInventory().getSize() + " / ");
			pc.sendPackets(new S_SystemMessage(msg.toString()));
		} catch (Exception e) {
			pc.sendPackets(new S_SystemMessage(cmdName + " コマンドエラー"));
		}
	}
}
