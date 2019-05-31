package kp.c_d_i.interceptors;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 * Elapsed Time Interceptor.
 *
 */
@Elapsed
@Interceptor
public class ElapsedInterceptor {
	/*-
	 CDI interceptors were enabled in the file 'beans.xml'.
	*/

	@Inject
	private Logger logger;

	@Inject
	private List<List<String>> report;

	private static final int REFERENCE_PAUSE = 1;

	/**
	 * Intercepting method.
	 * 
	 * @param invocationContext the invocation context
	 * @return the result
	 */
	@AroundInvoke
	public Object measure(InvocationContext invocationContext) {

		Object result = null;
		final long start = System.nanoTime();
		try {
			result = invocationContext.proceed();
		} catch (Exception e) {
			logger.severe(String.format("measure(): exception[%s]", e.getMessage()));
			System.exit(1);
		}
		final long diff = System.nanoTime() - start;

		final List<String> row = new ArrayList<String>();
		row.add(this.getClass().getSimpleName());
		row.add("measure");
		row.add(String.format("method[%11s], %s", invocationContext.getMethod().getName(), formatElapsed(diff)));
		report.add(row);

		logger.info(String.format("measure(): Method[%s], time elapsed [%d]ns (reference), [%d]ns (after invoke)",
				invocationContext.getMethod().getName(), getReference(), diff));
		return result;
	}

	/**
	 * Gets reference measure.
	 * 
	 * @return the difference
	 */
	private long getReference() {

		long start = System.nanoTime(), diff;
		do {
			diff = System.nanoTime() - start;
		} while (diff < REFERENCE_PAUSE);
		return diff;
	}

	/**
	 * Formats the elapsed time.
	 * 
	 * @param time the time
	 * @return the formated elapsed time
	 */
	private String formatElapsed(long time) {

		long nanos = time % 1_000;
		long micros = time / 1_000 % 1_000;
		long millis = time / 1_000_000 % 1_000;

		if (millis > 0) {
			return String.format("elapsed[%3dms %3dμs %3dns]", millis, micros, nanos);
		} else if (micros > 0) {
			return String.format("elapsed[      %3dμs %3dns]", micros, nanos);
		} else {
			return String.format("elapsed[            %3dns]", nanos);
		}
	}
}