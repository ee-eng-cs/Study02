package kp.c_d_i.producers;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;

/**
 * The qualifier for formated foreseeable date and time.
 *
 */
@Qualifier
@Documented
@Retention(RUNTIME)
@Target({ FIELD, METHOD, PARAMETER, TYPE })
public @interface Foreseeable {
	@Nonbinding
	public ForeseeableDate value() default ForeseeableDate.FAR;

	@Nonbinding
	public String pattern() default "yyyy-MM-dd HH:mm:ss";
}