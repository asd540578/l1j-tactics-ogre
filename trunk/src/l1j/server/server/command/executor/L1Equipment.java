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

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import l1j.server.server.model.Instance.L1ItemInstance;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.serverpackets.S_SystemMessage;

import org.apache.commons.lang3.StringUtils;

public class L1Equipment implements L1CommandExecutor {
	private static Logger _log = Logger.getLogger(L1Equipment.class.getName());

	private L1Equipment() {
	}

	public static L1CommandExecutor getInstance() {
		return new L1Equipment();
	}

	@Override
	public void execute(L1PcInstance pc, String cmdName, String arg) {
		try {
			StringBuilder msg = new StringBuilder();
			String newline = System.getProperty("line.separator");
			pc.sendPackets(new S_SystemMessage("-- equipment: " + pc.getName()
					+ " --"));

			List<String> list = getOutput(pc);
			for (int i = 0; i < list.size(); ++i) {
				msg.append(list.get(i) + newline);
			}

			pc.sendPackets(new S_SystemMessage(msg.toString()));
		} catch (Exception e) {
			pc.sendPackets(new S_SystemMessage(cmdName + " コマンドエラー"));
		}
	}

	public List<String> getOutput(L1PcInstance pc) {
		List<String> list = new ArrayList<String>();
		List<L1ItemInstance> val = new ArrayList<L1ItemInstance>();
		String[] col = new String[] { "weapon", "shield", "armor", "helm",
				"boots", "cloak", "amulet", "earring", "belt", "glove",
				"ring(1)", "ring(2)", "ring(3)" };
		val.add(pc.getInventory().getEquippedWeapon());
		val.add(pc.getInventory().getEquippedShield());
		val.add(pc.getInventory().getEquippedArmor());
		val.add(pc.getInventory().getEquippedHelm());
		val.add(pc.getInventory().getEquippedBoots());
		val.add(pc.getInventory().getEquippedCloak());
		val.add(pc.getInventory().getEquippedAmulet());
		val.add(pc.getInventory().getEquippedEarring());
		val.add(pc.getInventory().getEquippedBelt());
		val.add(pc.getInventory().getEquippedGlove());
		L1ItemInstance[] ring = pc.getInventory().getRingEquipped();
		val.add(ring[0]);
		val.add(ring[1]);
		val.add(pc.getInventory().getEquippedRing2());

		for (int i = 0; i < val.size() && i < col.length; ++i) {
			list.add(StringUtils.rightPad(col[i], 8) + ":"
					+ (val.get(i) == null ? "none" : val.get(i).getLogName()));
		}
		return list;
	}
}
