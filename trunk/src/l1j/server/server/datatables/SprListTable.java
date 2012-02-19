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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import l1j.server.L1DatabaseFactory;
import l1j.server.server.utils.SQLUtil;

public class SprListTable {
	private static Logger _log = Logger.getLogger(SprListTable.class.getName());

	private static SprListTable _instance;

	private final ArrayList<Integer> _sprList =
		new ArrayList<Integer>();

	public static SprListTable getInstance() {
		if (_instance == null) {
			_instance = new SprListTable();
		}
		return _instance;
	}

	private SprListTable() {
		loadPolymorphs();
	}

	private void loadPolymorphs() {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {

			con = L1DatabaseFactory.getInstance().getConnection();
			pstm = con.prepareStatement("SELECT * FROM spr_list order by sprid");
			rs = pstm.executeQuery();
			while (rs.next()) {
				int sprid = rs.getInt("sprid");
				_sprList.add(sprid);
			}
		} catch (SQLException e) {
			_log.log(Level.SEVERE, "error while creating spr_list table", e);
		} finally {
			SQLUtil.close(rs);
			SQLUtil.close(pstm);
			SQLUtil.close(con);
		}
	}

	public ArrayList<Integer> getTemplate() {
		return _sprList;
	}

}
