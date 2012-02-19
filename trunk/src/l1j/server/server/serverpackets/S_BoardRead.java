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
import l1j.server.server.templates.L1BoardTopic;

public class S_BoardRead extends ServerBasePacket {
	private static final String S_BoardRead = "[S] S_BoardRead";

	private byte[] _byte = null;

	public S_BoardRead(int number) {
		buildPacket(number);
	}

	private void buildPacket(int number) {
		L1BoardTopic topic = L1BoardTopic.findById(number);
		writeC(Opcodes.S_OPCODE_BOARDREAD);
		writeD(number);
		writeS(topic.getName());
		writeS(topic.getTitle());
		writeS(topic.getDate());
		writeS(topic.getContent());

	}

	@Override
	public byte[] getContent() {
		if (_byte == null) {
			_byte = getBytes();
		}
		return _byte;
	}

	@Override
	public String getType() {
		return S_BoardRead;
	}
}
