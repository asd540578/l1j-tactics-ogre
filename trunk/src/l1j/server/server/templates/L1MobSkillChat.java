package l1j.server.server.templates;

import java.sql.ResultSet;
import java.sql.SQLException;

import l1j.server.server.utils.L1QueryUtil.EntityFactory;

public class L1MobSkillChat {
	private int _skillId;
	private String _note;
	private String _chat;
	private int _type;
	private int _probability;

	public int getSkillId() {
		return _skillId;
	}

	public void setSkillId(int skillId) {
		this._skillId = skillId;
	}

	public String getNote() {
		return _note;
	}

	public void setNote(String note) {
		this._note = note;
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		this._type = type;
	}

	public String getChat() {
		return _chat;
	}

	public void setChat(String chat) {
		this._chat = chat;
	}

	public int getProbability() {
		return _probability;
	}

	public void setProbability(int probability) {
		this._probability = probability;
	}

	private L1MobSkillChat() {
	}

	public static class Factory implements EntityFactory<L1MobSkillChat> {
		@Override
		public L1MobSkillChat fromResultSet(ResultSet rs) throws SQLException {
			L1MobSkillChat result = new L1MobSkillChat();
			result._skillId = rs.getInt("skillid");
			result._note = rs.getString("note");
			result._type = rs.getInt("type");
			result._chat = rs.getString("chat");
			result._probability = rs.getInt("probability");

			return result;
		}
	}
}
