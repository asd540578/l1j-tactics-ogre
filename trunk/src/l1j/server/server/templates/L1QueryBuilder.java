/**
 * 
 */
package l1j.server.server.templates;

import java.util.List;

import l1j.server.server.utils.StringUtil;
import l1j.server.server.utils.collections.Lists;

public class L1QueryBuilder {
	private final String _tableName;
	private final int _id;
	private List<String> _columns = Lists.newArrayList();
	private List<Object> _values = Lists.newArrayList();
	private final int _columnBits;
	private String _query = null;

	public L1QueryBuilder(String tableName, int id, int columnBits) {
		_tableName = tableName;
		_id = id;
		_columnBits = columnBits;
	}

	void addColumn(String columnName, int bit, Object value) {
		if (_query != null) {
			throw new IllegalStateException();
		}
		if (0 == (_columnBits & bit)) {
			return;
		}
		_columns.add(String.format("%s = ?", columnName));
		_values.add(value);
	}

	public void buildQuery() {
		_query = "UPDATE " + _tableName + " SET "
				+ StringUtil.join(_columns, ", ") + " WHERE id = ?";
		_values.add(_id);
	}

	public String getQuery() {
		if (_query == null) {
			throw new IllegalStateException();
		}
		return _query;
	}

	public Object[] getArgs() {
		if (_query == null) {
			throw new IllegalStateException();
		}
		return _values.toArray();
	}
}