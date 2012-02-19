package l1j.server.configure;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

class L1Properties {
	private static Logger _log = Logger.getLogger(L1Properties.class.getName());
	private final Properties _props = new Properties();

	private L1Properties() {
	}

	private L1Properties(File file) throws IOException {
		InputStream is = new FileInputStream(file);
		_props.load(is);
		is.close();
	}

	public static L1Properties load(File file) {
		_log.fine("Loading properties file: " + file.getName());
		try {
			return new L1Properties(file);
		} catch (IOException e) {
			return new NullProperties(e);
		}
	}

	private void notifyLoadingDefault(String key, boolean allowDefaultValue) {
		if (!allowDefaultValue) {
			// デフォルト値が許可されていない。エラー
			throw new RuntimeException(key
					+ " does not exists. It has not default value.");
		}
		// デフォルト値をロードすることを通知
		_log.info(key + " does not exists. Server use default value.");
	}

	public String getProperty(String key, boolean allowDefaultValue) {
		if (!_props.containsKey(key)) {
			notifyLoadingDefault(key, allowDefaultValue);
			return null;
		}
		return _props.getProperty(key);
	}

	public boolean isNull() {
		return false;
	}

	public IOException getException() {
		throw new UnsupportedOperationException();
	}

	private static class NullProperties extends L1Properties {
		private IOException _e;

		public NullProperties(IOException e) {
			_e = e;
		}

		@Override
		public String getProperty(String key, boolean allowDefaultValue) {
			return null;
		}

		@Override
		public boolean isNull() {
			return true;
		}

		@Override
		public IOException getException() {
			return _e;
		}
	}
}
