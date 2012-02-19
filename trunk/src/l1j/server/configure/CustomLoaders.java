package l1j.server.configure;

import java.util.Calendar;

import l1j.server.configure.Annotations.Configure;

class CustomLoaders {
	interface CustomLoader {
		Object loadValue(Configure config, Class<?> type, String value);
	}

	static class DefaultLoader implements CustomLoader {
		@Override
		public Object loadValue(Configure config, Class<?> type, String value) {
			Object result = null;

			if (type.equals(int.class)) {
				result = Integer.parseInt(value);
			} else if (type.equals(String.class)) {
				result = value;
			} else if (type.equals(boolean.class)) {
				result = Boolean.parseBoolean(value);
			} else if (type.equals(double.class)) {
				result = Double.parseDouble(value);
			}
			return result;
		}
	}

	static class WarTimeLoader implements CustomLoader {
		@Override
		public Object loadValue(Configure config, Class<?> type, String value) {
			String time = value.replaceFirst("^(\\d+)d|h|m$", "$1"); // 数値だけ抜き出す
			return Integer.parseInt(time);
		}
	}

	static class WarTimeUnitLoader implements CustomLoader {
		@Override
		public Object loadValue(Configure config, Class<?> type, String value) {
			String unit = value.replaceFirst("^\\d+(d|h|m)$", "$1"); // ユニットだけ抜き出す
			if (unit.equals("d")) {
				return Calendar.DATE;
			}
			if (unit.equals("h")) {
				return Calendar.HOUR_OF_DAY;
			}
			if (unit.equals("m")) {
				return Calendar.MINUTE;
			}
			throw new IllegalArgumentException();
		}
	}
}
