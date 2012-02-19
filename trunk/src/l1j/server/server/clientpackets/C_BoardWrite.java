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

package l1j.server.server.clientpackets;

import java.util.logging.Logger;

import l1j.server.server.ClientThread;
import l1j.server.server.model.L1Object;
import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.model.item.L1ItemId;
import l1j.server.server.templates.L1BoardTopic;

// Referenced classes of package l1j.server.server.clientpackets:
// ClientBasePacket

public class C_BoardWrite extends ClientBasePacket {
	private static final String C_BOARD_WRITE = "[C] C_BoardWrite";
	private static Logger _log = Logger.getLogger(C_BoardWrite.class.getName());

	public C_BoardWrite(byte decrypt[], ClientThread client) {
		super(decrypt);
		int id = readD();
		String title = readS();
		String content = readS();

		L1Object tg = L1World.getInstance().findObject(id);
		if (tg == null) {
			_log.warning("不正なNPCID : " + id);
			return;
		}

		L1PcInstance pc = client.getActiveChar();
		L1BoardTopic.create(pc.getName(), title, content);
		pc.getInventory().consumeItem(L1ItemId.ADENA, 300);
	}

	@Override
	public String getType() {
		return C_BOARD_WRITE;
	}
}
