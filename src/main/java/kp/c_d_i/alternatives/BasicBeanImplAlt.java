package kp.c_d_i.alternatives;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

/**
 * Alternative Basic Bean Implementation.
 *
 */
@Alternative
public class BasicBeanImplAlt implements BasicBean {

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
		row.add(this.getClass().getSimpleName());
		row.add("show");
		row.add(String.format("content[%s] (Alternative Bean)", content));
		report.add(row);
		logger.info(String.format("show(): content[%s]", content));
	}
}