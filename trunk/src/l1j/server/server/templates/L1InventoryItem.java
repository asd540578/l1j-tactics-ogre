package l1j.server.server.templates;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Logger;

import l1j.server.L1DatabaseFactory;
import l1j.server.server.datatables.InnKeyTable;
import l1j.server.server.datatables.ItemTable;
import l1j.server.server.model.Instance.L1ItemInstance;
import l1j.server.server.utils.L1QueryUtil;
import l1j.server.server.utils.L1SQLException;
import l1j.server.server.utils.SQLUtil;
import l1j.server.server.utils.L1QueryUtil.EntityFactory;
import l1j.server.server.utils.collections.Lists;

public class L1InventoryItem {
	private static final Logger _log = Logger.getLogger(L1InventoryItem.class
			.getName());

	private int _storedId = 0;
	private int _id;
	private int _ownerId;
	private int _location;
	private int _itemId;
	private int _itemCount;
	private boolean _isEquipped;
	private int _enchantLevel;
	private boolean _isIdentified;
	private int _durability;
	private int _chargeCount;
	private int _remainingTime;
	private Timestamp _lastUsed;
	private boolean _isSealed;
	private int _attrEnchantKind;
	private int _attrEnchantLevel;
	private int _updatedColumnBits = 0;

	private static final int COL_ID = 1 << 0;
	private static final int COL_OWNER_ID = 1 << 1;
	private static final int COL_LOCATION = 1 << 2;
	private static final int COL_ITEM_ID = 1 << 3;
	private static final int COL_ITEM_COUNT = 1 << 4;
	private static final int COL_IS_EQUIPPED = 1 << 5;
	private static final int COL_ENCHANT_LEVEL = 1 << 6;
	private static final int COL_IS_IDENTIFIED = 1 << 7;
	private static final int COL_DURABILITY = 1 << 8;
	private static final int COL_CHARGE_COUNT = 1 << 9;
	private static final int COL_REMAINING_TIME = 1 << 10;
	private static final int COL_LAST_USED = 1 << 11;
	private static final int COL_IS_SEALLED = 1 << 12;
	private static final int COL_ATTR_ENCHANT_KIND = 1 << 13;
	private static final int COL_ATTR_ENCHANT_LEVEL = 1 << 14;

	public static final int LOC_NONE = -1;
	public static final int LOC_CHARACTER = 0;
	public static final int LOC_WAREHOUSE = 1;
	public static final int LOC_ELF_WAREHOUSE = 2;
	public static final int LOC_CLAN_WAREHOUSE = 3;

	public L1InventoryItem() {
	}

	private void setColumnBits(int bit) {
		_updatedColumnBits |= bit;
	}

	public int getId() {
		return _id;
	}

	public void setId(int id) {
		_id = id;
		setColumnBits(COL_ID);
	}

	public int getOwnerId() {
		return _ownerId;
	}

	public void setOwnerId(int ownerId) {
		_ownerId = ownerId;
		setColumnBits(COL_OWNER_ID);
	}

	public int getLocation() {
		return _location;
	}

	public void setLocation(int location) {
		_location = location;
		setColumnBits(COL_LOCATION);
	}

	public int getItemId() {
		return _itemId;
	}

	public void setItemId(int itemId) {
		_itemId = itemId;
		setColumnBits(COL_ITEM_ID);
	}

	public int getItemCount() {
		return _itemCount;
	}

	public void setItemCount(int itemCount) {
		_itemCount = itemCount;
		setColumnBits(COL_ITEM_COUNT);
	}

	public boolean isEquipped() {
		return _isEquipped;
	}

	public void setEquipped(boolean isEquipped) {
		_isEquipped = isEquipped;
		setColumnBits(COL_IS_EQUIPPED);
	}

	public int getEnchantLevel() {
		return _enchantLevel;
	}

	public void setEnchantLevel(int enchantLevel) {
		_enchantLevel = enchantLevel;
		setColumnBits(COL_ENCHANT_LEVEL);
	}

	public boolean isIdentified() {
		return _isIdentified;
	}

	public void setIdentified(boolean isIdentified) {
		_isIdentified = isIdentified;
		setColumnBits(COL_IS_IDENTIFIED);
	}

	public int getDurability() {
		return _durability;
	}

	public void setDurability(int durability) {
		_durability = durability;
		setColumnBits(COL_DURABILITY);
	}

	public int getChargeCount() {
		return _chargeCount;
	}

	public void setChargeCount(int chargeCount) {
		_chargeCount = chargeCount;
		setColumnBits(COL_CHARGE_COUNT);
	}

	public int getRemainingTime() {
		return _remainingTime;
	}

	public void setRemainingTime(int remainingTime) {
		_remainingTime = remainingTime;
		setColumnBits(COL_REMAINING_TIME);
	}

	public Timestamp getLastUsed() {
		return _lastUsed;
	}

	public void setLastUsed(Timestamp lastUsed) {
		_lastUsed = lastUsed;
		setColumnBits(COL_LAST_USED);
	}

	public boolean isSealed() {
		return _isSealed;
	}

	public void setSealed(boolean isSealed) {
		_isSealed = isSealed;
		setColumnBits(COL_IS_SEALLED);
	}

	public int getAttrEnchantKind() {
		return _attrEnchantKind;
	}

	public void setAttrEnchantKind(int attrEnchantKind) {
		_attrEnchantKind = attrEnchantKind;
		setColumnBits(COL_ATTR_ENCHANT_KIND);
	}

	public int getAttrEnchantLevel() {
		return _attrEnchantLevel;
	}

	public void setAttrEnchantLevel(int attrEnchantLevel) {
		_attrEnchantLevel = attrEnchantLevel;
		setColumnBits(COL_ATTR_ENCHANT_LEVEL);
	}

	private static class Factory implements
			L1QueryUtil.EntityFactory<L1InventoryItem> {
		@Override
		public L1InventoryItem fromResultSet(ResultSet rs) throws SQLException {
			L1InventoryItem result = new L1InventoryItem();
			result._id = result._storedId = rs.getInt("id");
			result._ownerId = rs.getInt("owner_id");
			result._location = rs.getInt("location");
			result._itemId = rs.getInt("item_id");
			result._itemCount = rs.getInt("item_count");
			result._isEquipped = rs.getBoolean("is_equipped");
			result._enchantLevel = rs.getInt("enchant_level");
			result._isIdentified = rs.getBoolean("is_identified");
			result._durability = rs.getInt("durability");
			result._chargeCount = rs.getInt("charge_count");
			result._remainingTime = rs.getInt("remaining_time");
			result._lastUsed = rs.getTimestamp("last_used");
			result._isSealed = rs.getBoolean("is_sealed");
			result._attrEnchantKind = rs.getInt("attr_enchant_kind");
			result._attrEnchantLevel = rs.getInt("attr_enchant_level");
			return result;
		}
	}

	public static L1InventoryItem findById(int id) {
		return L1QueryUtil.selectFirst(new Factory(),
				"SELECT * FROM inventory_items WHERE id = ?", id);
	}

	public static List<L1InventoryItem> findByOwnerId(int ownerId) {
		return L1QueryUtil.selectAll(new Factory(),
				"SELECT * FROM inventory_items WHERE owner_id = ?", ownerId);
	}

	public static List<L1InventoryItem> findByOwnerIdAndLocation(int ownerId,
			int location) {
		return L1QueryUtil
				.selectAll(
						new Factory(),
						"SELECT * FROM inventory_items WHERE owner_id = ? AND location = ?",
						ownerId, location);
	}

	private L1QueryBuilder buildQuery() {
		L1QueryBuilder qb = new L1QueryBuilder("inventory_items", _storedId,
				_updatedColumnBits);
		qb.addColumn("id", COL_ID, _id);
		qb.addColumn("owner_id", COL_OWNER_ID, _ownerId);
		qb.addColumn("location", COL_LOCATION, _location);
		qb.addColumn("item_id", COL_ITEM_ID, _itemId);
		qb.addColumn("item_count", COL_ITEM_COUNT, _itemCount);
		qb.addColumn("is_equipped", COL_IS_EQUIPPED, _isEquipped);
		qb.addColumn("enchant_level", COL_ENCHANT_LEVEL, _enchantLevel);
		qb.addColumn("is_identified", COL_IS_IDENTIFIED, _isIdentified);
		qb.addColumn("durability", COL_DURABILITY, _durability);
		qb.addColumn("charge_count", COL_CHARGE_COUNT, _chargeCount);
		qb.addColumn("remaining_time", COL_REMAINING_TIME, _remainingTime);
		qb.addColumn("last_used", COL_LAST_USED, _lastUsed);
		qb.addColumn("is_sealed", COL_IS_SEALLED, _isSealed);
		qb.addColumn("attr_enchant_kind", COL_ATTR_ENCHANT_KIND,
				_attrEnchantKind);
		qb.addColumn("attr_enchant_level", COL_ATTR_ENCHANT_LEVEL,
				_attrEnchantLevel);

		qb.buildQuery();
		return qb;
	}

	private void insert(Connection con) {
		String sql = "INSERT inventory_items VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		L1QueryUtil.execute(con, sql, _id, _ownerId, _location, _itemId,
				_itemCount, _isEquipped, _enchantLevel, _isIdentified,
				_durability, _chargeCount, _remainingTime, _lastUsed,
				_isSealed, _attrEnchantKind, _attrEnchantLevel);
		_storedId = _id;
	}

	private void update(Connection con) {
		L1QueryBuilder qb = buildQuery();
		L1QueryUtil.execute(con, qb.getQuery(), qb.getArgs());
	}

	private boolean isStoreNecessary() {
		if (_updatedColumnBits == 0) {
			return false;
		}
		if (_storedId == 0 && _location == LOC_NONE) {
			return false;
		}
		return true;
	}

	public void save() {
		if (!isStoreNecessary()) {
			return;
		}

		Connection con = null;
		try {
			con = L1DatabaseFactory.getInstance().getConnection();
			save(con);
		} catch (SQLException e) {
			throw new L1SQLException(e);
		} finally {
			SQLUtil.close(con);
		}
	}

	public void save(Connection con) {
		if (!isStoreNecessary()) {
			return;
		}

		if (_location == LOC_NONE || _itemCount == 0) {
			delete(con);
			return;
		}

		if (_storedId == 0) {
			insert(con);
		} else {
			update(con);
		}
		_updatedColumnBits = 0;
		_storedId = _id;
	}

	public void delete() {
		if (_storedId == 0) {
			return;
		}
		Connection con = null;
		try {
			con = L1DatabaseFactory.getInstance().getConnection();
			delete(con);
		} catch (SQLException e) {
			throw new L1SQLException(e);
		} finally {
			SQLUtil.close(con);
		}
	}

	public void delete(Connection con) {
		if (_storedId == 0) {
			return;
		}
		L1QueryUtil.execute(con, "DELETE FROM inventory_items WHERE id = ?",
				_storedId);
		_storedId = 0;
	}

	public static void deleteAll(int ownerId) {
		Connection con = null;
		try {
			con = L1DatabaseFactory.getInstance().getConnection();
			deleteAll(con, ownerId);
		} catch (SQLException e) {
			throw new L1SQLException(e);
		} finally {
			SQLUtil.close(con);
		}
	}

	public static void deleteAll(Connection con, int ownerId) {
		L1QueryUtil.execute(con,
				"DELETE FROM inventory_items WHERE owner_id = ?", ownerId);
	}

	private static class CountFactory implements EntityFactory<Integer> {
		@Override
		public Integer fromResultSet(ResultSet rs) throws SQLException {
			return rs.getInt("cnt");
		}
	}

	public static int countByOwnerId(int ownerId) {
		Integer result = L1QueryUtil.selectFirst(new CountFactory(),
				"SELECT COUNT(*) FROM inventory_items WHERE owner_id = ?",
				ownerId);
		return result == null ? 0 : result;
	}

	public static List<L1ItemInstance> instantiate(List<L1InventoryItem> items) {
		List<L1ItemInstance> result = Lists.newArrayList();

		for (L1InventoryItem item : items) {
			int itemId = item.getItemId();
			L1Item itemTemplate = ItemTable.getInstance().getTemplate(itemId);
			if (itemTemplate == null) {
				_log.warning(String.format("item id:%d not found", itemId));
				continue;
			}
			L1ItemInstance instance = new L1ItemInstance(item);
			if (instance.getItem().getItemId() == 40312) { // 宿屋のキー記録
				InnKeyTable.hasKey(instance);
			}
			result.add(instance);
		}
		return result;
	}
}
