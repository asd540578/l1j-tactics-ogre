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

import l1j.server.server.Opcodes;

/**
 * 3.0cのUnKnown1から改名
 * 正式にLoginGameと命名
 */

public class S_LoginGame extends ServerBasePacket {
	public S_LoginGame() {
        /*
         * 【Server】 id:41 size:8 time:1314325723125
         *  0000:       29 03 00 ac c2 7c 00 c1
         */
        writeC(Opcodes.S_OPCODE_LOGINTOGAME);
        writeC(0x03);
        writeC(0x00);
        writeC(0xac);
        writeC(0xc2);
        writeC(0x7c);
        writeC(0x00);
        writeC(0xc1);

	}

	@Override
	public byte[] getContent() {
		return getBytes();
	}
}
