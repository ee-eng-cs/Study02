package kp.c_d_i;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.jboss.weld.inject.WeldInstance;

/**
 * Research CDI starter.
 *
 */
public class ResearchCDIStarter {
	/*- Weld is executed in Java SE environment: EJB beans are not supported. */

	/**
	 * Boots <B>Weld SE</B> container.
	 * 
	 */
	public void startIt() {

		final Weld weld = new Weld();
		try (WeldContainer container = weld.initialize()) {
			final WeldInstance<ResearchCDIBean> instance = container.select(ResearchCDIBean.class);
			final ResearchCDIBean researchCDIBean = instance.get();
			/*
			 * run in container
			 */
			researchCDIBean.process();
		}
	}
}