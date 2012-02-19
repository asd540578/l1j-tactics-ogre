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

import static l1j.server.server.model.skill.L1SkillId.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import l1j.server.L1DatabaseFactory;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.model.skill.executor.L1BuffSkillExecutor;
import l1j.server.server.templates.L1CharacterBuff;
import l1j.server.server.templates.L1Skill;
import l1j.server.server.utils.SQLUtil;
import l1j.server.server.utils.collections.Lists;

public class CharBuffTable {
	private CharBuffTable() {
	}

	private static Logger _log = Logger
			.getLogger(CharBuffTable.class.getName());

	private static final int[] BUFF_SKILL_IDS = { STATUS_BRAVE, STATUS_HASTE,
			STATUS_ELFBRAVE, STATUS_RIBRAVE, STATUS_BLUE_POTION,
			STATUS_CHAT_PROHIBITED };

	private static List<Integer> buffSkillIds() {
		List<Integer> result = SkillTable.getInstance().findBuffSkillIds();
		for (int id : BUFF_SKILL_IDS) {
			result.add(id);
		}
		return result;
	}

	private static void store(int objId, int skillId, int time, int polyId,
			int attrKind) {
		java.sql.Connection con = null;
		PreparedStatement pstm = null;
		try {
			con = L1DatabaseFactory.getInstance().getConnection();
			pstm = con
					.prepareStatement("INSERT INTO character_buffs SET char_obj_id=?, skill_id=?, remaining_time=?, poly_id=?, attr_kind=?");
			pstm.setInt(1, objId);
			pstm.setInt(2, skillId);
			pstm.setInt(3, time);
			pstm.setInt(4, polyId);
			pstm.setInt(5, attrKind);
			pstm.execute();
		} catch (SQLException e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		} finally {
			SQLUtil.close(pstm);
			SQLUtil.close(con);
		}
	}

	public static void delete(int charId) {
		java.sql.Connection con = null;
		PreparedStatement pstm = null;
		try {
			con = L1DatabaseFactory.getInstance().getConnection();
			pstm = con
					.prepareStatement("DELETE FROM character_buffs WHERE char_obj_id=?");
			pstm.setInt(1, charId);
			pstm.execute();
		} catch (SQLException e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		} finally {
			SQLUtil.close(pstm);
			SQLUtil.close(con);

		}
	}

	private static boolean saveByExecutor(int skillId, L1PcInstance pc) {
		L1Skill skill = SkillTable.getInstance().findBySkillId(skillId);
		if (skill == null) {
			return false;
		}
		L1BuffSkillExecutor exe = skill.newBuffSkillExecutor();
		if (exe == null) {
			return false;
		}

		L1CharacterBuff buff = exe.getCharacterBuff(pc);
		if (buff == null) {
			return false;
		}

		store(buff.getCharcterId(), buff.getSkillId(), buff.getRemainingTime(),
				buff.getPolyId(), buff.getAttrKind());
		return true;
	}

	public static void save(L1PcInstance pc) {
		for (int skillId : buffSkillIds()) {
			int timeSec = pc.getSkillEffectTimeSec(skillId);
			if (0 < timeSec) {
				if (saveByExecutor(skillId, pc)) {
					continue;
				}
				int polyId = 0;
				if (skillId == SHAPE_CHANGE) {
					polyId = pc.getTempCharGfx();
				}
				store(pc.getId(), skillId, timeSec, polyId, 0);
			}
		}
	}

	private static L1CharacterBuff fromResultSet(ResultSet rs)
			throws SQLException {
		int charcterId = rs.getInt("char_obj_id");
		int skillId = rs.getInt("skill_id");
		int remainingTime = rs.getInt("remaining_time");
		int polyId = rs.getInt("poly_id");
		int attrKind = rs.getInt("attr_kind");
		return new L1CharacterBuff(charcterId, skillId, remainingTime, polyId,
				attrKind);
	}

	public static List<L1CharacterBuff> findByCharacterId(int id) {
		List<L1CharacterBuff> result = Lists.newArrayList();

		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = L1DatabaseFactory.getInstance().getConnection();
			pstm = con
					.prepareStatement("SELECT * FROM character_buffs WHERE char_obj_id = ?");
			pstm.setInt(1, id);
			rs = pstm.executeQuery();
			while (rs.next()) {
				result.add(fromResultSet(rs));
			}
		} catch (SQLException e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		} finally {
			SQLUtil.close(rs);
			SQLUtil.close(pstm);
			SQLUtil.close(con);
		}
		return result;
	}
}
