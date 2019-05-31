package kp.c_d_i.alternatives;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

/**
 * Basic Bean Implementation with Qualifier 'Script'.
 * 
 * Used Unicode Mathematical Alphanumeric Symbols.
 *
 */
@Script
public class BasicBeanImplScript implements BasicBean {

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

		final String asScript = content.toUpperCase().replace("A", "𝒜").replace("B", "ℬ").replace("C", "𝒞")
				.replace("D", "𝒟").replace("E", "ℰ").replace("F", "ℱ").replace("G", "𝒢").replace("H", "ℋ")
				.replace("I", "ℐ").replace("J", "𝒥").replace("K", "𝒦").replace("L", "ℒ").replace("M", "ℳ")
				.replace("N", "𝒩").replace("O", "𝒪").replace("P", "𝒫").replace("Q", "𝒬").replace("R", "ℛ")
				.replace("S", "𝒮").replace("T", "𝒯").replace("U", "𝒰").replace("V", "𝒱").replace("W", "𝒲")
				.replace("X", "𝒳").replace("Y", "𝒴").replace("Z", "𝒵");

		final List<String> row = new ArrayList<String>();
		row.add(this.getClass().getSimpleName());
		row.add("show");
		row.add(String.format("content[%s]", asScript));
		report.add(row);
		logger.info(String.format("show(): content[%s]", content));
	}
}