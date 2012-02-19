package l1j.server.server.utils;

import java.util.List;

public class StringUtil {
	public static String[] newArray(Object... obj) {
		String[] result = new String[obj.length];
		for (int i = 0; i < obj.length; i++) {
			result[i] = obj[i].toString();
		}
		return result;
	}

	public static String complementClassName(String className,
			String defaultPackage) {
		// .が含まれていればフルパスと見なしてそのまま返す
		if (className.contains(".")) {
			return className;
		}

		// デフォルトパッケージ名を補完
		return defaultPackage + "." + className;
	}

	public static <T> String join(List<T> list, String with) {
		StringBuffer buf = new StringBuffer();
		for (T s : list) {
			if (buf.length() > 0) {
				buf.append(with);
			}
			buf.append(s);
		}
		return buf.toString();
	}
}
