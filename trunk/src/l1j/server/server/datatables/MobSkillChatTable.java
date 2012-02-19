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

import l1j.server.server.templates.L1MobSkillChat;
import l1j.server.server.utils.L1QueryUtil;
import l1j.server.server.utils.L1QueryUtil.EntityFactory;
import l1j.server.server.utils.collections.Maps;

/* t.s 2011/09/27 add start */
/**
 * モンスタースキル実行時のチャットテーブル
 */
public class MobSkillChatTable {
	private static MobSkillChatTable _instance;

	private final Map<Integer, L1MobSkillChat > _mobchat = Maps
			.newHashMap();

	public static void initialize() {
		if (_instance != null) {
			throw new DataTableAlreadyInitializedException(
					MobSkillChatTable.class);
		}
		_instance = new MobSkillChatTable();
	}

	public static MobSkillChatTable getInstance() {
		return _instance;
	}

	private MobSkillChatTable() {
		load();
	}

	private static class MobIdFactory implements EntityFactory<Integer> {
		@Override
		public Integer fromResultSet(ResultSet rs) throws SQLException {
			return rs.getInt("skillid");
		}
	}

	private void load() {
		List<Integer> skillIds = L1QueryUtil.selectAll(new MobIdFactory(),
				"SELECT DISTINCT skillid FROM mobskill_chat");
		for (int skillId : skillIds) {
			L1MobSkillChat skillchat = L1QueryUtil
					.selectFirst(
							new L1MobSkillChat.Factory(),
							"SELECT * FROM mobskill_chat where skillid = ?",
							skillId);
			_mobchat.put(skillId, skillchat);
		}
	}

	/**
	 * NPCのあるスキルで使用するスキルチャット
	 *
	 * @param skill
	 * @return スキルで使用するスキルチャット
	 */
	public L1MobSkillChat get(int skillId) {
		// スキルを実行するNPCが持つスキルチャット一覧
		L1MobSkillChat chat = _mobchat.get(skillId);

		if (null == chat) // 使用スキルチャット無し
			return null;

		return chat;
	}
}
/* t.s 2011/09/27 add end */