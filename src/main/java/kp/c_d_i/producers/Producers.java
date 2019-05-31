package kp.c_d_i.producers;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 * Producers for CDI research.
 *
 */
class Producers {

	/**
	 * Produces logger.
	 * 
	 * @param injectionPoint the injection point
	 * @return the logger
	 */
	@Produces
	public Logger getLogger(InjectionPoint injectionPoint) {

		final String category = injectionPoint.getMember().getDeclaringClass().getName();
		return Logger.getLogger(category);
	}

	/**
	 * Produces report.
	 */
	@Produces
	public static final List<List<String>> report = new ArrayList<>();

	/**
	 * Produces formated foreseeable.
	 * 
	 * @param injectionPoint the injection point
	 * @return the foreseeable
	 */
	@Produces
	@Foreseeable
	@Dependent
	public String getForeseeable(InjectionPoint injectionPoint) {

		final Foreseeable foreseeable = injectionPoint.getAnnotated().getAnnotation(Foreseeable.class);
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(foreseeable.pattern());
		return foreseeable.value().localDateTime.format(formatter);
	}
}