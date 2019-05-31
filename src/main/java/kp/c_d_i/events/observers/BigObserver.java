package kp.c_d_i.events.observers;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import kp.c_d_i.events.Payload;

/**
 * Big Observer.
 *
 */
public class BigObserver extends BasicObserver {

	@Inject
	private Logger logger;

	@Inject
	private List<List<String>> report;

	/**
	 * Shows the payload list.
	 * 
	 * @param payloadList the payload list
	 */
	public void showPayloadList(@Observes List<Payload> payloadList) {

		final List<String> row = new ArrayList<String>();
		row.add(this.getClass().getSimpleName());
		row.add("showPayloadList");
		row.add(String.format("content[%s]", payloadList.get(0).getContent()));
		report.add(row);
		logger.info(String.format("showPayloadList(): content[%s]", payloadList.get(0).getContent()));
	}

	/**
	 * Shows the text list.
	 * 
	 * @param textList the text list
	 */
	public void showTextList(@Observes List<String> textList) {

		final List<String> row = new ArrayList<String>();
		row.add(this.getClass().getSimpleName());
		row.add("showTextList");
		row.add(String.format("content[%s]", textList.get(0)));
		report.add(row);
		logger.info(String.format("showTextList(): content[%s]", textList.get(0)));
	}
}