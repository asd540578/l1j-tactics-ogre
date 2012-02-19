package l1j.server.configure;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.regex.Pattern;

import l1j.server.configure.Annotations.Configure;
import l1j.server.configure.CustomLoaders.CustomLoader;
import l1j.server.server.utils.ReflectionUtil;
import l1j.server.server.utils.collections.Maps;

class ConfigLoader {
	private Map<File, L1Properties> _propsMap = Maps.newHashMap();

	private L1Properties loadProps(File propsFile) throws IOException {
		L1Properties result = L1Properties.load(propsFile);
		_propsMap.put(propsFile, result);
		return result;
	}

	private L1Properties getProps(String path) throws IOException {
		File propsFile = new File(path);
		L1Properties result = _propsMap.get(propsFile);
		if (result == null) {
			result = loadProps(propsFile);
		}
		return result;
	}

	String makeDefaultKey(String fieldName) {
		if (!Pattern.matches("^[A-Z0-9]+(_[A-Z0-9]+)*$", fieldName)) {
			throw new IllegalArgumentException(fieldName);
		}
		StringBuilder result = new StringBuilder();
		for (String seq : fieldName.split("_")) {
			result.append(seq.charAt(0));
			result.append(seq.substring(1).toLowerCase());
		}
		return result.toString();
	}

	private void loadValue(Field f, Configure config) throws IOException,
			IllegalAccessException {
		L1Properties props = getProps(config.file());
		if (props.isNull() && !config.isOptional()) { // 読み込みに失敗していて、任意設定ではない
			throw new IOException(props.getException());
		}
		String key = config.key().isEmpty() ? makeDefaultKey(f.getName())
				: config.key();
		String value = props.getProperty(key, config.allowDefaultValue());
		if (value == null) { // デフォルト値のままにする
			return;
		}
		CustomLoader loader = ReflectionUtil.newInstance(config.loader());

		f.set(null, loader.loadValue(config, f.getType(), value));
	}

	public void load(Class<?> configClass) {
		try {
			for (Field f : configClass.getFields()) {
				Configure config = f.getAnnotation(Configure.class);
				if (config == null) {
					continue; // 対応するアノテーションが無い
				}
				loadValue(f, config);
			}
		} catch (Exception e) { // めんどいからException
			throw new RuntimeException(e);
		}
	}
}
