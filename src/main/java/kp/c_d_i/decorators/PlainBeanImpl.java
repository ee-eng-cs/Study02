package kp.c_d_i.decorators;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

/**
 * Plain Bean Implementation.
 *
 */
public class PlainBeanImpl implements PlainBean {

	@Inject
	private Logger logger;

	@Inject
	private List<List<String>> report;

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public void show(String content) {

		final List<String> row = new ArrayList<String>();
		row.add("PlainBeanImpl");
		row.add("show");
		row.add(String.format("content[%s]", content));
		report.add(row);
		logger.info(String.format("show(): class name[%s], content[%s]", this.getClass().getSimpleName(), content));
	}
}