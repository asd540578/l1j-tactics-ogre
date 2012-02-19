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

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Logger;

import l1j.server.Base64;
import l1j.server.server.IdFactory;
import l1j.server.server.utils.L1QueryUtil;
import l1j.server.server.utils.L1QueryUtil.EntityFactory;

/**
 * ログインの為の様々なインターフェースを提供する.
 */
public class L1Account {
	private int _id;

	/** アカウント名. */
	private String _name;

	/** 接続先のIPアドレス. */
	private String _ip;

	/** パスワード(暗号化されている). */
	private String _password;

	/** 最終アクティブ日. */
	private Timestamp _lastActivatedAt;

	/** アクセスレベル(GMか？). */
	private int _accessLevel;

	/** 接続先のホスト名. */
	private String _host;

	/** アクセス禁止の有無(Trueで禁止). */
	private boolean _isBanned;

	/** キャラクターの追加スロット数 */
	private int _characterSlot;

	/** アカウントが有効か否か(Trueで有効). */
	private boolean _isValid = false;

	/** メッセージログ用. */
	private static Logger _log = Logger.getLogger(L1Account.class.getName());

	/**
	 * コンストラクタ.
	 */
	private L1Account() {
	}

	/**
	 * パスワードを暗号化する.
	 * 
	 * @param rawPassword
	 *            平文のパスワード
	 * @return String
	 */
	private static String encodePassword(final String rawPassword) {
		try {
			byte[] buf = rawPassword.getBytes("UTF-8");
			buf = MessageDigest.getInstance("SHA").digest(buf);

			return Base64.encodeBytes(buf);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	static L1Account create(int id, final String name,
			final String rawPassword, final String ip, final String host) {
		String password = encodePassword(rawPassword);
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		String sql = "INSERT accounts SET id = ?, name = ?, password = ?, last_activated_at = ?, access_level = ?, ip = ?, host = ?, is_banned = ?, character_slot = ?";
		L1QueryUtil.execute(sql, id, name, password, currentTime, 0, ip, host,
				false, 0);
		_log.info("created new account for " + name);
		return findById(id);
	}

	/**
	 * アカウントを新規作成する.
	 * 
	 * @param name
	 *            アカウント名
	 * @param rawPassword
	 *            平文パスワード
	 * @param ip
	 *            接続先のIPアドレス
	 * @param host
	 *            接続先のホスト名
	 * @return Account
	 */
	public static L1Account create(final String name, final String rawPassword,
			final String ip, final String host) {
		return create(IdFactory.getInstance().nextId(), name, rawPassword, ip,
				host);
	}

	public static List<L1Account> findAll() {
		return L1QueryUtil.selectAll(new Factory(), "SELECT * FROM accounts");
	}

	public static List<L1Account> findByCharacterLevel(int minLevel,
			int maxLevel) {
		return L1QueryUtil
				.selectAll(
						new Factory(),
						"SELECT * FROM accounts WHERE name IN (SELECT DISTINCT(account_id) FROM characters WHERE level BETWEEN ? AND ?)",
						minLevel, maxLevel);
	}

	public static L1Account findById(int id) {
		return L1QueryUtil.selectFirst(new Factory(),
				"SELECT * FROM accounts WHERE id = ?", id);
	}

	/**
	 * アカウント名からアカウント情報を検索する
	 * 
	 * @param name
	 *            アカウント名
	 * @return Account
	 */
	public static L1Account findByName(final String name) {
		return L1QueryUtil.selectFirst(new Factory(),
				"SELECT * FROM accounts WHERE name = ?", name);
	}

	/**
	 * 最終ログイン日をDBに反映する.
	 * 
	 */
	public void updateLastActivatedTime() {
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		L1QueryUtil.execute(
				"UPDATE accounts SET last_activated_at = ? WHERE id = ?",
				currentTime, _id);
	}

	/**
	 * スロット数をDBに反映する.
	 * 
	 */
	public void updateCharacterSlot() {
		L1QueryUtil.execute(
				"UPDATE accounts SET character_slot = ? WHERE id = ?",
				_characterSlot, _id);
		_log.fine("update characterslot for " + _name);
	}

	/**
	 * キャラクター所有数をカウントする.
	 * 
	 * @return int
	 */
	public int countCharacters() {
		return L1QueryUtil.selectFirst(new CountFactory(),
				"SELECT count(*) as cnt FROM characters WHERE account_id = ?",
				_id);
	}

	/**
	 * アカウントを無効にする.
	 * 
	 * @param name
	 *            アカウント名
	 */
	public static void ban(final String name) {
		L1QueryUtil.execute(
				"UPDATE accounts SET is_banned = 1, WHERE name = ?", name);
	}

	/**
	 * 入力されたパスワードとDB上のパスワードを照合する.
	 * 
	 * @param rawPassword
	 *            平文パスワード
	 * @return boolean
	 * @throws IllegalStateException
	 *             このオブジェクトに対しメソッドを2回以上呼び出した場合
	 */
	public boolean validatePassword(final String rawPassword) {
		if (_isValid) { // 認証成功後に再度認証された場合は例外。
			throw new IllegalStateException("Account was validated twice.");
		}
		_isValid = _password.equals(encodePassword(rawPassword));
		if (_isValid) {
			_password = null; // 認証が成功した場合、パスワードを破棄する。
		}
		return _isValid;
	}

	public int getId() {
		return _id;
	}

	/**
	 * アカウントが有効かどうかを返す(Trueで有効).
	 * 
	 * @return boolean
	 */
	public boolean isValid() {
		return _isValid;
	}

	/**
	 * アカウントがゲームマスタかどうか返す(Trueでゲームマスタ).
	 * 
	 * @return boolean
	 */
	public boolean isGameMaster() {
		return 0 < _accessLevel;
	}

	/**
	 * アカウント名を取得する.
	 * 
	 * @return String
	 */
	public String getName() {
		return _name;
	}

	/**
	 * 接続先のIPアドレスを取得する.
	 * 
	 * @return String
	 */
	public String getIp() {
		return _ip;
	}

	/**
	 * 最終ログイン日を取得する.
	 */
	public Timestamp getLastActivatedAt() {
		return _lastActivatedAt;
	}

	/**
	 * アクセスレベルを取得する.
	 * 
	 * @return int
	 */
	public int getAccessLevel() {
		return _accessLevel;
	}

	/**
	 * ホスト名を取得する.
	 * 
	 * @return String
	 */
	public String getHost() {
		return _host;
	}

	/**
	 * アクセス禁止情報を取得する.
	 * 
	 * @return boolean
	 */
	public boolean isBanned() {
		return _isBanned;
	}

	/**
	 * キャラクターの追加スロット数を取得する.
	 * 
	 * @return int
	 */
	public int getCharacterSlot() {
		return _characterSlot;
	}

	public void setCharacterSlot(int i) {
		_characterSlot = i;
	}

	private static class CountFactory implements EntityFactory<Integer> {
		@Override
		public Integer fromResultSet(ResultSet rs) throws SQLException {
			return rs.getInt("cnt");
		}
	}

	private static class Factory implements EntityFactory<L1Account> {
		@Override
		public L1Account fromResultSet(ResultSet rs) throws SQLException {
			L1Account result = new L1Account();
			result._id = rs.getInt("id");
			result._name = rs.getString("name");
			result._password = rs.getString("password");
			result._lastActivatedAt = rs.getTimestamp("last_activated_at");
			result._accessLevel = rs.getInt("access_level");
			result._ip = rs.getString("ip");
			result._host = rs.getString("host");
			result._isBanned = rs.getBoolean("is_banned");
			result._characterSlot = rs.getInt("character_slot");
			return result;
		}
	}
}
