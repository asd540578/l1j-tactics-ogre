package l1j.server.server.utils;

import java.sql.SQLException;

/**
 * java.sql.SQLException例外を、非チェック例外へとラップします。
 * 
 * @see SQLException
 * @see L1QueryUtil
 */
public class L1SQLException extends RuntimeException {
	private static final long serialVersionUID = -2466780963010001937L;

	public L1SQLException(SQLException e) {
		super(e);
	}

	public L1SQLException(String message, SQLException e) {
		super(message, e);
	}
}