package l1j.server.server.datatables;

import java.util.Map;

import l1j.server.server.templates.L1CookingRecipe;
import l1j.server.server.utils.collections.Maps;

public class CookingRecipeTable {
	private static Map<Integer, L1CookingRecipe> _recipes = null;

	private CookingRecipeTable() {
	}

	public static void initialize() {
		if (_recipes != null) {
			throw new DataTableAlreadyInitializedException(
					CookingRecipeTable.class);
		}
		_recipes = load();
	}

	private static Map<Integer, L1CookingRecipe> load() {
		Map<Integer, L1CookingRecipe> result = Maps.newHashMap();

		for (L1CookingRecipe recipe : L1CookingRecipe.all()) {
			result.put(recipe.getId(), recipe);
		}
		return result;
	}

	public static L1CookingRecipe findById(int id) {
		return _recipes.get(id);
	}
}
