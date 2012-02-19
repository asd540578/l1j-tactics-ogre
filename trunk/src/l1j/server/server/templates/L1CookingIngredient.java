package l1j.server.server.templates;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import l1j.server.server.utils.L1QueryUtil;
import l1j.server.server.utils.L1QueryUtil.EntityFactory;

public class L1CookingIngredient {
	private final int _id;
	private final int _cookingRecipeId;
	private final int _itemId;
	private final int _amount;

	private L1CookingIngredient(int id, int cookingRecipeId, int itemId,
			int amount) {
		super();
		this._id = id;
		this._cookingRecipeId = cookingRecipeId;
		this._itemId = itemId;
		this._amount = amount;
	}

	private static class Factory implements EntityFactory<L1CookingIngredient> {
		@Override
		public L1CookingIngredient fromResultSet(ResultSet rs)
				throws SQLException {
			int id = rs.getInt("id");
			int cookingRecipeId = rs.getInt("cooking_recipe_id");
			int itemId = rs.getInt("item_id");
			int amount = rs.getInt("amount");
			return new L1CookingIngredient(id, cookingRecipeId, itemId, amount);
		}
	}

	public int getId() {
		return _id;
	}

	public int getCookingRecipeId() {
		return _cookingRecipeId;
	}

	public int getItemId() {
		return _itemId;
	}

	public int getAmount() {
		return _amount;
	}

	public static List<L1CookingIngredient> findByCookingRecipeId(
			int cookingRecipeId) {
		return L1QueryUtil
				.selectAll(
						new Factory(),
						"SELECT * FROM cooking_ingredients WHERE cooking_recipe_id = ?",
						cookingRecipeId);
	}
}
