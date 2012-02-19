package l1j.server.server.templates;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import l1j.server.L1DatabaseFactory;
import l1j.server.server.utils.SQLUtil;
import l1j.server.server.utils.collections.Lists;

public class L1CharacterSkill {
	private static Logger _log = Logger.getLogger(L1CharacterSkill.class
			.getName());

	private int _id;
	private int _charcterId;
	private int _skillId;
	private String _skillName;
	private boolean _isActive;
	private int _activeTimeLeft;

	public L1CharacterSkill(int id, int charcterId, int skillId,
			String skillName, boolean isActive, int activeTimeLeft) {
		_id = id;
		_charcterId = charcterId;
		_skillId = skillId;
		_skillName = skillName;
		_isActive = isActive;
		_activeTimeLeft = activeTimeLeft;
	}

	private static L1CharacterSkill fromResultSet(ResultSet rs)
			throws SQLException {
		int id = rs.getInt("id");
		int charcterId = rs.getInt("char_obj_id");
		int skillId = rs.getInt("skill_id");
		String skillName = rs.getString("skill_name");
		boolean isActive = rs.getBoolean("is_active");
		int activeTimeLeft = rs.getInt("activetimeleft");

		return new L1CharacterSkill(id, charcterId, skillId, skillName,
				isActive, activeTimeLeft);
	}

	public static List<L1CharacterSkill> findByCharcterId(int id) {
		List<L1CharacterSkill> result = Lists.newArrayList();

		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = L1DatabaseFactory.getInstance().getConnection();
			pstm = con
					.prepareStatement("SELECT * FROM character_skills WHERE char_obj_id = ?");
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

	public static Logger getLog() {
		return _log;
	}

	public int getId() {
		return _id;
	}

	public int getCharcterId() {
		return _charcterId;
	}

	public int getSkillId() {
		return _skillId;
	}

	public String getSkillName() {
		return _skillName;
	}

	public boolean isActive() {
		return _isActive;
	}

	public int getActiveTimeLeft() {
		return _activeTimeLeft;
	}
}
