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
import l1j.server.server.model.L1Character;
import l1j.server.server.model.Instance.L1NpcInstance;

public class S_PetCtrlMenu extends ServerBasePacket
{

	public S_PetCtrlMenu(L1Character cha, L1NpcInstance npc, boolean open)
	{
		int index = open ? 1 : cha.getPetList().size() - 1;
		writeC(Opcodes.S_OPCODE_PETCTRL);
		writeC(0x0c);

		for (L1NpcInstance temp : cha.getPetList().values())
		{
			if (npc.equals(temp))
			{
				writeH(index * 3);
				writeD(!open ? 0x00000001 : 0x00000000);
				writeD(npc.getId());

				if (open)
				{
					writeD(0x00000000);
					writeH(npc.getX());
					writeH(npc.getY());
					writeS(npc.getName());
				}
				else
				{
					writeS(null);
				}

				break;
			}

			index = open ? ++index : --index;
		}
	}

	@Override
	public byte[] getContent()
	{
		return getBytes();
	}

}