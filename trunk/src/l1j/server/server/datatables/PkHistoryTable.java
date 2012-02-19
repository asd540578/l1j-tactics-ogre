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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import l1j.server.L1DatabaseFactory;
import l1j.server.server.templates.L1PkLog;
import l1j.server.server.utils.SQLUtil;

/* t.s 2011/09/23 add start */
public class PkHistoryTable {
	private static final long serialVersionUID = 1L;

	private static Logger _log = Logger.getLogger(PkHistoryTable.class.getName());

	private static PkHistoryTable _instance;

	public static PkHistoryTable getInstance() {
		if (_instance == null) {
			_instance = new PkHistoryTable();
		}
		return _instance;
	}

	private PkHistoryTable() {
	}

	/*
	 * PK履歴を追加する
	 */
	public void addHistory(L1PkLog log) {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		// ＤＢへ登録する内容
		String killerName = log.getKillerName();
		int killerLevel = log.getKillerLevel();
		String killerClan = log.getKillerClan();
		String deathName = log.getDeadName();
		int deathLevel = log.getDeadLevel();
		String deathClan = log.getDeadClan();
		Date rts = log.getRecordTimestamp();
		Timestamp timestamp = new Timestamp(rts.getTime());

		int mapid = log.getMapid();
		String locationname = log.getLocationName();
		try {
			con = L1DatabaseFactory.getInstance().getConnection();
			pstm = con
					.prepareStatement("insert into pk_history values (?,?,?,?,?,?,?,?,?)");
			int paramIndex = 1;
			pstm.setTimestamp(paramIndex++, timestamp);
			pstm.setString(paramIndex++, killerName);
			pstm.setInt(paramIndex++, killerLevel);
			pstm.setString(paramIndex++, killerClan);
			pstm.setString(paramIndex++, deathName);
			pstm.setInt(paramIndex++, deathLevel);
			pstm.setString(paramIndex++, deathClan);
			pstm.setInt(paramIndex++, mapid);
			pstm.setString(paramIndex++, locationname);
			pstm.executeUpdate();

		} catch (NullPointerException e) {
			_log.log(Level.SEVERE, new StringBuilder()
					.append("PK履歴の追加に失敗しました。").toString());
		} catch (SQLException e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		} finally {
			SQLUtil.close(rs);
			SQLUtil.close(pstm);
			SQLUtil.close(con);
		}
	}

	/*
	 * 全履歴を参照する
	 *
	 * @return 全PK履歴
	 */
	public List<L1PkLog> findAllHistory() {
		return findHistory("select * from pk_history", null);
	}

	/*
	 * 対象のPK履歴を検索する
	 *
	 * @return 対象のPK履歴
	 */
	public List<L1PkLog> findKillHistory(String killerName) {
		List<Object> obj = new ArrayList<Object>();
		obj.add(killerName);
		return findHistory("select * from pk_history where killer_name = ?",
				obj);
	}

	/*
	 * 対象の死亡履歴を検索する
	 *
	 * @return 対象の死亡履歴
	 */
	public List<L1PkLog> findDeathHistory(String deadName) {
		List<Object> obj = new ArrayList<Object>();
		obj.add(deadName);
		return findHistory("select * from pk_history where dead_name = ?", obj);
	}

	private List<L1PkLog> findHistory(String query, List<Object> param) {
		List<L1PkLog> result = new ArrayList<L1PkLog>();

		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = L1DatabaseFactory.getInstance().getConnection();
			pstm = con.prepareStatement(query);
			int paramIndex = 0, index = 0;
			while (true) {
				index = query.indexOf("?", index); // パラメータ部分を検索する
				if (index < 0) // 検索の結果存在しなければ終了
					break;
				pstm.setObject(paramIndex + 1, param.get(paramIndex));
				++paramIndex;
				++index;
			}
			rs = pstm.executeQuery();
			while (rs.next()) {
				L1PkLog log = new L1PkLog();
				log.setKillerName(rs.getString("killer_name"));
				log.setKillerLevel(rs.getInt("killer_level"));
				log.setKillerName(rs.getString("killer_clan"));
				log.setDeadName(rs.getString("dead_name"));
				log.setDeadLevel(rs.getInt("dead_level"));
				log.setDeadName(rs.getString("dead_clan"));
				log.setMapid(rs.getInt("mapid"));
				log.setLocationName(rs.getString("locationname"));
				result.add(log);
			}
		} catch (NullPointerException e) {
			_log.log(Level.SEVERE,
					new StringBuilder().append("PK履歴の読み込みに失敗しました。").toString());
		} catch (SQLException e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		} catch (Exception e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		} finally {
			SQLUtil.close(rs);
			SQLUtil.close(pstm);
			SQLUtil.close(con);
		}
		return result;
	}
}
/* t.s 2011/09/23 add end */
