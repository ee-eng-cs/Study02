package kp.c_d_i.interceptors;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Target;

import javax.interceptor.InterceptorBinding;

/**
 * Time Elapsed Qualifier.
 *
 */
@Inherited
@InterceptorBinding
@Retention(RUNTIME)
@Target({ METHOD, TYPE })
public @interface Elapsed {
}