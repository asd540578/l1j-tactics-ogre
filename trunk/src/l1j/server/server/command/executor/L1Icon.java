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

import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.serverpackets.S_SystemMessage;
import l1j.server.server.serverpackets.S_SkillIconGFX;

public class L1Icon implements L1CommandExecutor {
	private static Logger _log = Logger.getLogger(L1Icon.class.getName());

	private L1Icon() {
	}

	public static L1CommandExecutor getInstance() {
		return new L1Icon();
	}

	@Override
	public void execute(L1PcInstance pc, String cmdName, String arg) {
		try {
			StringTokenizer st = new StringTokenizer(arg);
			int sprid = Integer.parseInt(st.nextToken(), 10);
			int count = Integer.parseInt(st.nextToken(), 10);
			for (int i = 0; i < count; i++) {
				pc.sendPackets(new S_SkillIconGFX(sprid, 900));
				  //pc.sendPackets(new S_SkillBrave(pc.getId(), sprid,
						 //20));
				//pc.sendPackets(new S_SkillHaste(pc.getId(), sprid,
						//20));				
				//pc.sendPackets(new S_PacketBox(53, sprid, 100));
				//pc.sendPackets(new S_SkillIconAura(sprid, 12));				
				pc.sendPackets(new S_SystemMessage("" + sprid));
				sprid = sprid + 1;
				for (int loop = 0; loop < 1; loop++) { // 2秒待機
					Thread.sleep(2000);
				}
			}
		} catch (Exception e) {
			pc.sendPackets(new S_SystemMessage(cmdName
							+ " id 送信数 と入力して下さい。"));
		}
	}
}