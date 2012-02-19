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
 * along with this program; if not, Delete to the Free Software
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
import l1j.server.server.templates.L1BoardTopic;

// Referenced classes of package l1j.server.server.clientpackets:
// ClientBasePacket

public class C_BoardDelete extends ClientBasePacket {

	private static final String C_BOARD_DELETE = "[C] C_BoardDelete";
	private static Logger _log = Logger
			.getLogger(C_BoardDelete.class.getName());

	public C_BoardDelete(byte decrypt[], ClientThread client) {
		super(decrypt);
		int objId = readD();
		int topicId = readD();

		L1Object obj = L1World.getInstance().findObject(objId);
		if (obj == null) {
			_log.warning("不正なNPCID : " + objId);
			return;
		}
		L1BoardTopic topic = L1BoardTopic.findById(topicId);
		if (topic == null) {
			logNotExist(topicId);
			return;
		}
		String name = client.getActiveChar().getName();
		if (!name.equals(topic.getName())) {
			logIllegalDeletion(topic, name);
			return;
		}

		topic.delete();
	}

	private void logNotExist(int topicId) {
		_log
				.warning(String
						.format(
								"Illegal board deletion request: Topic id <%d> does not exist.",
								topicId));
	}

	private void logIllegalDeletion(L1BoardTopic topic, String name) {
		_log
				.warning(String
						.format(
								"Illegal board deletion request: Name <%s> expected but was <%s>.",
								topic.getName(), name));
	}

	@Override
	public String getType() {
		return C_BOARD_DELETE;
	}

}
