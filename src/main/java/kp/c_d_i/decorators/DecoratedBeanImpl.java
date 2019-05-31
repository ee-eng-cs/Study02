package kp.c_d_i.decorators;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

/**
 * Plain Bean Implementation Decorated.
 *
 */
@Decorator
public class DecoratedBeanImpl implements PlainBean {
	@Inject
	private Logger logger;

	@Inject
	private List<List<String>> report;

	@Inject
	@Delegate
	@Any
	private PlainBean plainBean;

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public void show(String content) {

		final List<String> row1 = new ArrayList<String>();
		row1.add(this.getClass().getSimpleName());
		row1.add("show");
		row1.add("before plain bean calls");
		report.add(row1);
		logger.info("show(): before plain bean calls");

		plainBean.show(content);

		final List<String> row2 = new ArrayList<String>();
		row2.add(this.getClass().getSimpleName());
		row2.add("show");
		row2.add("after 1st plain bean call (content unchanged)");
		report.add(row2);
		logger.info("show(): after 1st plain bean call");

		plainBean.show(new StringBuilder(content).reverse().toString());

		final List<String> row3 = new ArrayList<String>();
		row3.add(this.getClass().getSimpleName());
		row3.add("show");
		row3.add("after 2nd plain bean call (content reversed)");
		report.add(row3);
		logger.info("show(): after 2nd plain bean call");
	}
}