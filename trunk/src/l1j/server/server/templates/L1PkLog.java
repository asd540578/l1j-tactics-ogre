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
package l1j.server.server.templates;

import java.util.Date;

import l1j.server.server.datatables.PkHistoryTable;

/* t.s 2011/09/23 add start */
public class L1PkLog {

	private static final long serialVersionUID = 1L;

	public L1PkLog() {
		this._recordTimestamp = new Date();
	}

	public L1PkLog(String killer, String killerClan, int killerLevel,
			String dead, String deadClan, int deadLevel, int mapid,
			String locationname) {
		this._recordTimestamp = new Date();
		this._killerName = killer;
		this._killerClan = killerClan;
		this._killerLevel = killerLevel;
		this._deadName = dead;
		this._deadLevel = deadLevel;
		this._deadClan = deadClan;
		this._mapid = mapid;
		this._locationname = locationname;
	}

	private Date _recordTimestamp;
	private String _killerName;
	private int _killerLevel;
	private String _killerClan;
	private String _deadName;
	private int _deadLevel;
	private String _deadClan;
	private int _mapid;
	private String _locationname;

	public Date getRecordTimestamp() {
		return _recordTimestamp;
	}

	public void setRecordTimestamp(Date recordTimestamp) {
		this._recordTimestamp = recordTimestamp;
	}

	public String getKillerName() {
		return _killerName;
	}

	public void setKillerName(String killerName) {
		this._killerName = killerName;
	}

	public int getKillerLevel() {
		return _killerLevel;
	}

	public void setKillerLevel(int killerLevel) {
		this._killerLevel = killerLevel;
	}

	public String getDeadName() {
		return _deadName;
	}

	public void setDeadName(String deadName) {
		this._deadName = deadName;
	}

	public int getDeadLevel() {
		return _deadLevel;
	}

	public void setDeadLevel(int deadLevel) {
		this._deadLevel = deadLevel;
	}

	public int getMapid() {
		return _mapid;
	}

	public void setMapid(int mapid) {
		this._mapid = mapid;
	}

	public String getLocationName() {
		return _locationname;
	}

	public void setLocationName(String locationname) {
		this._locationname = locationname;
	}

	public String getKillerClan() {
		return _killerClan;
	}

	public void setKillerClan(String killerClan) {
		this._killerClan = killerClan;
	}

	public String getDeadClan() {
		return _deadClan;
	}

	public void setDeadClan(String deadClan) {
		this._deadClan = deadClan;
	}

	/**
	 * KILLログを生成する
	 * 
	 * @return ログ
	 */
	public String buildKillMessage() {

		String msg = "No Killer Information";
		if (this._killerName != null && this._killerClan != null)
			msg = buildMessage(true, this._killerName, this._killerClan);
		return msg;
	}

	/**
	 * Deathログを生成する
	 * 
	 * @return ログ
	 */
	public String buildDeathMessage() {

		String msg = "No Dead Information";
		if (this._deadName != null && this._deadClan != null)
			msg = buildMessage(false, this._deadName, this._deadClan);

		return msg;
	}

	/**
	 * ログを生成する
	 * 
	 * @return ログ
	 */
	private String buildMessage(boolean isKill, String name, String clanName) {
		PkHistoryTable history = PkHistoryTable.getInstance();
		int Kill = history.findKillHistory(name).size();
		int Death = history.findDeathHistory(name).size();

		StringBuilder builder = new StringBuilder();
		builder.append((isKill == true) ? "【殺害者】 " : "【死亡者】 ");
		builder.append(name);
		builder.append(" [" + clanName + "]");
		builder.append("Kill/Death(" + Kill + "/" + Death + ")");
		return builder.toString();
	}

	/**
	 * ログをデータベースに記録する
	 */
	public void storePkHistory() {
		PkHistoryTable history = PkHistoryTable.getInstance();
		history.addHistory(this);
	}
}
/* t.s 2011/09/23 add end */