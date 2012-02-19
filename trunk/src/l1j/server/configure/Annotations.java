package l1j.server.configure;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import l1j.server.configure.CustomLoaders.CustomLoader;
import l1j.server.configure.CustomLoaders.DefaultLoader;

class Annotations {
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	@interface Configure {
		String file();

		String key() default "";

		boolean allowDefaultValue() default true;

		Class<? extends CustomLoader> loader() default DefaultLoader.class;

		boolean isOptional() default false;
	}
}
