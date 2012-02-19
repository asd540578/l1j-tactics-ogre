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
import java.util.List;
import java.util.Map;

import l1j.server.server.templates.L1MobSkill;
import l1j.server.server.utils.L1QueryUtil;
import l1j.server.server.utils.L1QueryUtil.EntityFactory;
import l1j.server.server.utils.collections.Lists;
import l1j.server.server.utils.collections.Maps;

public class MobSkillTable {
	private static MobSkillTable _instance;

	private final Map<Integer, List<L1MobSkill>> _mobskills = Maps.newHashMap();

	public static void initialize() {
		if (_instance != null) {
			throw new DataTableAlreadyInitializedException(MobSkillTable.class);
		}
		_instance = new MobSkillTable();
	}

	public static MobSkillTable getInstance() {
		return _instance;
	}

	private MobSkillTable() {
		load();
	}

	private static class NpcIdFactory implements EntityFactory<Integer> {
		@Override
		public Integer fromResultSet(ResultSet rs) throws SQLException {
			return rs.getInt("mobid");
		}
	}

	private void load() {
		List<Integer> npcIds = L1QueryUtil.selectAll(new NpcIdFactory(),
				"SELECT DISTINCT mobid FROM mobskill");
		for (int npcId : npcIds) {
			List<L1MobSkill> skills = L1QueryUtil.selectAll(
					new L1MobSkill.Factory(),
					"SELECT * FROM mobskill where mobid = ? order by actNo",
					npcId);
			_mobskills.put(npcId, skills);
		}
	}

	public List<L1MobSkill> get(int id) {
		List<L1MobSkill> result = _mobskills.get(id);
		if (result == null) {
			result = Lists.newArrayList();
		}
		return result;
	}
}
