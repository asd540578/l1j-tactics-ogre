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
package l1j.server.server.datatables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import l1j.server.server.templates.L1SkillEffect;
import l1j.server.server.utils.KeyValuePair;
import l1j.server.server.utils.L1QueryUtil;
import l1j.server.server.utils.L1QueryUtil.EntityFactory;
import l1j.server.server.utils.collections.Maps;

/* t.s 2011/09/23 add start */
public class SkillEffectTable
{
	private static Logger _log = Logger.getLogger(SkillEffectTable.class
			.getName());

	private static SkillEffectTable _instance;
	private final Map<KeyValuePair<Integer, Integer>, L1SkillEffect> _skill_effect = Maps
			.newHashMap();

	public static void initialize()
	{
		if (_instance != null)
		{
			throw new DataTableAlreadyInitializedException(MobSkillTable.class);
		}
		_instance = new SkillEffectTable();
	}

	public static SkillEffectTable getInstance()
	{
		return _instance;
	}

	private SkillEffectTable()
	{
		load();
	}

	private static class KeyValuePairFactory implements
			EntityFactory<KeyValuePair<Integer, Integer>>
	{
		@Override
		public KeyValuePair<Integer, Integer> fromResultSet(ResultSet rs)
				throws SQLException
		{
			KeyValuePair<Integer, Integer> res = new KeyValuePair<Integer, Integer>(
					rs.getInt("id"), rs.getInt("type"));
			return res;
		}
	}

	private void load()
	{
		List<KeyValuePair<Integer, Integer>> keys = L1QueryUtil.selectAll(
				new KeyValuePairFactory(),
				"SELECT DISTINCT id ,type FROM skill_effect");
		for (KeyValuePair<Integer, Integer> id : keys)
		{
			L1SkillEffect skills = L1QueryUtil.selectFirst(
					new L1SkillEffect.Factory(),
					"SELECT * FROM skill_effect where id = ? and type = ?",
					id.first, id.second);
			_skill_effect.put(id, skills);
		}
	}

	/**
	 *
	 * @param id
	 * @param type
	 * @return
	 */
	public L1SkillEffect findSkillEffect(int id, int type)
	{
		L1SkillEffect effect = null;
		for (KeyValuePair<Integer, Integer> x : _skill_effect.keySet())
		{
			if (x.first.equals(id) && x.second.equals(type))
			{
				effect = _skill_effect.get(x);
				break;
			}
		}
		return effect;
	}

	/**
	 *
	 * @return
	 */
	public List<L1SkillEffect> findAllSkillEffect()
	{
		Collection<L1SkillEffect> cl = _skill_effect.values();
		List<L1SkillEffect> effects = new ArrayList<L1SkillEffect>();
		for (L1SkillEffect effect : cl)
		{
			effects.add(effect);
		}
		return effects;
	}

	public boolean existSkillEffect(int id)
	{
		boolean exist = false;
		for (KeyValuePair<Integer, Integer> x : _skill_effect.keySet())
		{
			if (x.first.equals(id))
			{
				exist = true;
				break;
			}
		}
		return exist;
	}
}
/* t.s 2011/09/23 add end */
