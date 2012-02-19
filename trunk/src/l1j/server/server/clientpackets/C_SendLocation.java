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
import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.serverpackets.S_SendLocation;

public class C_SendLocation extends ClientBasePacket {

	private static final String C_SEND_LOCATION = "[C] C_SendLocation";
	private static Logger _log = Logger.getLogger(C_SendLocation.class.getName());

	public C_SendLocation(byte abyte0[], ClientThread client) {
		super(abyte0);
		int type = readC();

		// クライアントがアクティブ,ノンアクティブ転換時に
		// オペコード 0x57 0x0dパケットを送ってくるが詳細不明の為無視
		// マップ座標転送時は0x0bパケット
		if (type == 0x0d) {
			return;
		}

		String name = readS();
		int mapId = readH();
		int x = readH();
		int y = readH();
		int msgId = readC();

		if (name.isEmpty()) {
			return;
		}
		L1PcInstance target = L1World.getInstance().getPlayer(name);
		if (target != null) {
			L1PcInstance pc = client.getActiveChar();
			String sender = pc.getName();
			target.sendPackets(new S_SendLocation(type, sender, mapId, x, y, msgId));
			//将来的にtypeを使う可能性があるので送る
		}
	}

	@Override
	public String getType() {
		return C_SEND_LOCATION;
	}
}

