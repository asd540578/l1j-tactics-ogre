package l1j.server.server.model.inventory;

import java.util.List;

import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1ItemInstance;
import l1j.server.server.templates.L1InventoryItem;

public class L1WarehouseInventory extends L1Inventory {
	private static final long serialVersionUID = 1L;
	private final int _ownerId;
	private final int _ownerLocation;

	public L1WarehouseInventory(int ownerId, int ownerLocation) {
		_ownerId = ownerId;
		_ownerLocation = ownerLocation;
	}

	// DBの読込
	@Override
	public void loadItems() {
		List<L1InventoryItem> inventoryItems = L1InventoryItem
				.findByOwnerIdAndLocation(_ownerId, _ownerLocation);

		List<L1ItemInstance> items = L1InventoryItem
				.instantiate(inventoryItems);
		for (L1ItemInstance item : items) {
			_items.add(item);
			L1World.getInstance().storeObject(item);
		}
	}

	// DBへ登録
	@Override
	public void insertItem(L1ItemInstance item) {
		item.setOwner(_ownerId, _ownerLocation);
		item.save();
	}

	// DBを更新
	@Override
	public void updateItem(L1ItemInstance item) {
		item.save();
	}

	// DBのクラン倉庫のアイテムを全て削除(血盟解散時のみ使用)
	public synchronized void deleteAllItems() {
		L1InventoryItem.deleteAll(_ownerId);
	}

	@Override
	public int getOwnerLocation() {
		return _ownerLocation;
	}
}