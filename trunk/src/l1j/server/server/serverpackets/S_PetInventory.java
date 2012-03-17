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

import java.util.List;

import l1j.server.server.Opcodes;
import l1j.server.server.model.Instance.L1ItemInstance;
import l1j.server.server.model.Instance.L1NpcInstance;
import l1j.server.server.model.Instance.L1PetInstance;

// Referenced classes of package l1j.server.server.serverpackets:
// ServerBasePacket

public class S_PetInventory extends ServerBasePacket {

	private static final String S_PET_INVENTORY = "[S] S_PetInventory";

	private byte[] _byte = null;

	public S_PetInventory(L1NpcInstance pet) {
		List<L1ItemInstance> itemList = pet.getInventory().getItems();

		writeC(Opcodes.S_OPCODE_SHOWRETRIEVELIST);
		writeD(pet.getId());
		writeH(itemList.size());
		writeC(0x0b);

		for (Object itemObject : itemList) {
			L1ItemInstance petItem = (L1ItemInstance) itemObject;
			if (petItem == null) {
				continue;
			}
			writeD(petItem.getId());
			writeC(0x02); // 值:0x00 無、0x01:武器類、0x02:防具類、0x16:牙類 、0x33:POT類
			writeH(petItem.get_gfxid());
			writeC(petItem.getStatusForPacket());
			writeD(petItem.getCount());

			// 装備中ペット装備
			if (petItem.getItem().getType2() == 0
					&& petItem.getItem().getType() == 11
					&& petItem.isEquipped()) {
				writeC(petItem.isIdentified() ? 3 : 2);
			} else {
				writeC(petItem.isIdentified() ? 1 : 0);
			}
			writeS(petItem.getViewName());

		}
		writeC(pet.getAc()); // ペットＡＣ
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
		return S_PET_INVENTORY;
	}
}