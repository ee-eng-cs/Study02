package kp.c_d_i.events.observers;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import kp.c_d_i.events.Payload;

/**
 * Basic Observer.
 *
 */
abstract public class BasicObserver {

	@Inject
	private Logger logger;

	@Inject
	private List<List<String>> report;

	/**
	 * Shows the payload.
	 * 
	 * @param payload the payload
	 */
	public void showPayload(@Observes Payload payload) {

		final List<String> row = new ArrayList<String>();
		row.add(this.getClass().getSimpleName());
		row.add("showPayload");
		row.add(String.format("content[%s]", payload.getContent()));
		report.add(row);
		logger.info(String.format("showPayload(): content[%s]", payload.getContent()));
	}
}
