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
 package l1j.server.server.templates;

import l1j.server.server.datatables.ItemTable;
import l1j.server.server.model.game.L1BugBearRace;

public class L1ShopItem {
	private static final long serialVersionUID = 1L;

	private final int _itemId;

	private L1Item _item;

	private final int _price;

	private final int _packCount;

	public L1ShopItem(int itemId, int price, int packCount) {
		_itemId = itemId;
		_item = ItemTable.getInstance().getTemplate(itemId);
		_price = price;
		_packCount = packCount;
	}

	public int getItemId() {
		return _itemId;
	}

	public L1Item getItem() {
		return _item;
	}

	public int getPrice() {
		return _price;
	}

	public int getPackCount() {
		return _packCount;
	}
	//XXX レースチケット用
	public void setName(int num){
		int trueNum=L1BugBearRace.getInstance().getRunner(num).getNpcId()-91350+1;
		_item=(L1Item) _item.clone();
		String temp=""+_item.getIdentifiedNameId()+" "+
				L1BugBearRace.getInstance().getRound()+"-"+trueNum;
		_item.setName(temp);
		_item.setUnidentifiedNameId(temp);
		_item.setIdentifiedNameId(temp);
	}
}
