package l1j.server.server.templates;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import l1j.server.server.IdFactory;
import l1j.server.server.utils.L1QueryUtil;
import l1j.server.server.utils.L1QueryUtil.EntityFactory;

public class L1BeginnerItem {
	private int _id;
	private int _itemId;
	private int _itemCount;
	private int _chargeCount;
	private int _enchantLevel;
	private String _classInitial;

	private static class Factory implements EntityFactory<L1BeginnerItem> {
		@Override
		public L1BeginnerItem fromResultSet(ResultSet rs) throws SQLException {
			L1BeginnerItem result = new L1BeginnerItem();
			result._id = rs.getInt("id");
			result._itemId = rs.getInt("item_id");
			result._itemCount = rs.getInt("item_count");
			result._chargeCount = rs.getInt("charge_count");
			result._enchantLevel = rs.getInt("enchant_level");
			result._classInitial = rs.getString("class_initial");
			return result;
		}
	}

	public static List<L1BeginnerItem> findByClass(String classNameInitial) {
		return L1QueryUtil.selectAll(new Factory(),
				"SELECT * FROM beginner_items WHERE class_initial IN(?, ?)",
				"A", classNameInitial);
	}

	public int getId() {
		return _id;
	}

	public int getItemId() {
		return _itemId;
	}

	public int getItemCount() {
		return _itemCount;
	}

	public int getChargeCount() {
		return _chargeCount;
	}

	public int getEnchantLevel() {
		return _enchantLevel;
	}

	public String getClassInitial() {
		return _classInitial;
	}

	/**
	 * このアイテムを対象プレイヤーのインベントリデータベースへコミットします。
	 * 
	 * @param characterId
	 *            対象プレイヤー
	 */
	public void storeToInventory(int characterId) {
		L1InventoryItem item = new L1InventoryItem();
		item.setId(IdFactory.getInstance().nextId());
		item.setOwnerId(characterId);
		item.setItemId(_itemId);
		item.setItemCount(_itemCount);
		item.setChargeCount(_chargeCount);
		item.setEnchantLevel(_enchantLevel);
		item.save();
	}
}
