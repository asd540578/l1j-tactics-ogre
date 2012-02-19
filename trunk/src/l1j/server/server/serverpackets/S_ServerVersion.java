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
 package l1j.server.server.serverpackets;

import l1j.server.configure.Config;
import l1j.server.server.Opcodes;

public class S_ServerVersion extends ServerBasePacket {
    private static final int CLIENT_LANGUAGE = Config.CLIENT_LANGUAGE;
    private static final int uptime = (int) (System.currentTimeMillis() / 1000);

    /*
     * [來源:Server]<位址:17>{長度:32}(時間:1314150068749) 0000: 11 00 38 32 c7 a8 00 a7
     * c6 a8 00 ba 6e cf 77 ad ..82........n.w. 0010: cd a8 00 71 23 53 4e 00 00
     * 03 00 00 00 00 08 00 ...q#SN.........
     */
	public S_ServerVersion() {
		writeC(Opcodes.S_OPCODE_SERVERVERSION);
		writeC(0x00);
		writeC(0x02);
        writeD(0x00a8c732); // server verion 3.5C Taiwan Server
        writeD(0x00a8c6a7); // cache verion 3.5C Taiwan Server
        writeD(0x77cf6eba); // auth verion 3.5C Taiwan Server
        writeD(0x00a8cdad); // npc verion 3.5C Taiwan Server
        writeD(uptime);
        writeC(0x00); // unknown
        writeC(0x00); // unknown
        writeC(CLIENT_LANGUAGE); // Country: 0.US 3.Taiwan 4.Janpan 5.China
        writeD(0x00000000);
        writeC(0xae); // unknown
        writeC(0xb2); // unknown
	}

	@Override
	public byte[] getContent() {
		return getBytes();
	}
}
