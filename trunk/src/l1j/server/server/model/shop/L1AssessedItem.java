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

package l1j.server.server.model.shop;

import l1j.server.server.datatables.RaceTicketTable;
import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1ItemInstance;
import l1j.server.server.templates.L1RaceTicket;

public class L1AssessedItem {
	private final int _targetId;
	private final int _assessedPrice;

	L1AssessedItem(int targetId, int assessedPrice) {
		_targetId = targetId;
		//XXX
		L1ItemInstance item = (L1ItemInstance) L1World.getInstance().findObject(getTargetId());
		if(item.getItemId()==40309){//レースチケット
			L1RaceTicket ticket=RaceTicketTable.getInstance().getTemplate(_targetId);
			int price=0;
			if(ticket!=null){
				price=(int) (assessedPrice*ticket.get_allotment_percentage()*ticket.get_victory());
			}
			_assessedPrice = price;
		}else{
			_assessedPrice = assessedPrice;
		}
	}

	public int getTargetId() {
		return _targetId;
	}

	public int getAssessedPrice() {
		return _assessedPrice;
	}
}
