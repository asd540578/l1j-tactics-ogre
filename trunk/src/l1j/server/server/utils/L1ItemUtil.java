package l1j.server.server.utils;

import l1j.server.server.datatables.ItemTable;
import l1j.server.server.model.L1MessageId;
import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1ItemInstance;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.model.inventory.L1Inventory;
import l1j.server.server.serverpackets.S_ServerMessage;
import l1j.server.server.templates.L1Item;

public class L1ItemUtil {

	public static void createNewItem(L1PcInstance pc, int itemId, int count) {
		L1ItemInstance item = ItemTable.getInstance().createItem(itemId);
		item.setCount(count);
		if (pc.getInventory().checkAddItem(item, count) == L1Inventory.OK) {
			pc.getInventory().storeItem(item);
		} else { // 持てない場合は地面に落とす 処理のキャンセルはしない（不正防止）
			L1World.getInstance().getInventory(pc.getX(), pc.getY(),
					pc.getMapId()).storeItem(item);
		}
		pc.sendPackets(new S_ServerMessage(L1MessageId.OBTAINED, item
				.getLogName()));
	}

	public static boolean isFood(L1Item item) {
		return item.getType2() == 0 && item.getType() == 7;
	}

}
