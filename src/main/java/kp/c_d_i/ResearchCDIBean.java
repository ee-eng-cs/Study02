package kp.c_d_i;

import static kp.c_d_i.producers.ForeseeableDate.NEAR;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.event.Event;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;

import kp.c_d_i.alternatives.BasicBean;
import kp.c_d_i.alternatives.Script;
import kp.c_d_i.decorators.PlainBean;
import kp.c_d_i.events.Payload;
import kp.c_d_i.interceptors.ElapsedBean;
import kp.c_d_i.producers.Foreseeable;

/**
 * Research CDI Bean.
 *
 */
public class ResearchCDIBean {

	@Inject
	private Logger logger;

	@Inject
	private List<List<String>> report;

	@Inject
	private ResearchCDIHelper researchCDIHelper;

	@Inject
	private BasicBean basicBean;

	@Inject
	@Script
	private BasicBean basicBeanWithQualifier;

	@Inject
	@Any
	private Instance<BasicBean> basicBeanInstance;

	@Inject
	private PlainBean plainBean;

	@Inject
	private Event<Payload> payloadEvent;

	@Inject
	private Event<List<Payload>> payloadListEvent;

	@Inject
	private Event<List<String>> textListEvent;

	@Inject
	@Foreseeable
	private String foreseeableF;

	@Inject
	@Foreseeable(NEAR)
	private String foreseeableN;

	@Inject
	@Foreseeable(pattern = "dd MMM HH:mm")
	private Instance<String> foreseeableInstance;

	@Inject
	private ElapsedBean elapsedBean;

	@SuppressWarnings(value = { "all" })
	private static class ScriptQualifier extends AnnotationLiteral<Script> implements Script {
	};

	/**
	 * Processes the CDI beans.
	 *
	 */
	public void process() {

		LOOP: while (true) {
			int result = researchCDIHelper.showButtons();
			switch (result) {
			case 0:
				alternativeAndQualifiedBeans();
				break;
			case 1:
				decoratedBeans();
				break;
			case 2:
				interceptedBeans();
				break;
			case 3:
				fireEvents();
				break;
			default:
				// Go Back
				break LOOP;
			}
		}
	}

	/**
	 * Researches the alternative and the qualified beans.
	 * 
	 */
	private void alternativeAndQualifiedBeans() {

		/*- switch off by commenting out <alternatives> element in the beans.xml file */
		report.clear();
		basicBean.show("ABC");
		basicBeanWithQualifier.show("KLM");
		/*- getting bean from instance with qualifier (equivalent to injected 'basicBeanWithQualifier') */
		final BasicBean basicBeanSelected = basicBeanInstance.select(new ScriptQualifier()).get();
		basicBeanSelected.show("XYZ");

		report.add(Arrays.asList(new String[3]));

		note();

		researchCDIHelper.showResults(ResearchCDIHelper.MENU_ARR[0]);
		logger.info("alternativeAndQualifiedBeans():");
	}

	/**
	 * Takes notes about foreseeables.
	 * 
	 */
	private void note() {

		final List<String> row1 = new ArrayList<String>();
		row1.add(this.getClass().getSimpleName());
		row1.add("note");
		row1.add(String.format("foreseeable[%s](String)", foreseeableF));
		report.add(row1);

		final List<String> row2 = new ArrayList<String>();
		row2.add(this.getClass().getSimpleName());
		row2.add("note");
		row2.add(String.format("foreseeable[%s](String)", foreseeableN));
		report.add(row2);

		final List<String> row3 = new ArrayList<String>();
		row3.add(this.getClass().getSimpleName());
		row3.add("note");
		row3.add(String.format("foreseeable[%s](Instance<String>)", foreseeableInstance.get()));
		report.add(row3);
	}

	/**
	 * Researches the decorated beans.
	 * 
	 */
	private void decoratedBeans() {

		/*- switch off by commenting out <decorators> element in the beans.xml file */
		report.clear();
		plainBean.show("ABC");
		researchCDIHelper.showResults(ResearchCDIHelper.MENU_ARR[1]);
		logger.info("decoratedBeans():");
	}

	/**
	 * Researches the intercepted beans.
	 * 
	 */
	private void interceptedBeans() {

		report.clear();
		for (int i = 1; i <= 10; i++) {
			elapsedBean.pausedMilli();
			elapsedBean.pausedMicro();
			elapsedBean.pausedNano();
			elapsedBean.notPaused();
		}
		researchCDIHelper.showResults(ResearchCDIHelper.MENU_ARR[2]);
		logger.info("interceptedBeans():");
	}

	/**
	 * Researches firing the events.
	 * 
	 */
	private void fireEvents() {

		report.clear();

		final Payload payload = new Payload("ABC");
		payloadEvent.fire(payload);
		report.add(Arrays.asList(new String[3]));

		final List<Payload> payloadList = new ArrayList<>();
		payloadList.add(new Payload("KLM"));
		payloadListEvent.fire(payloadList);
		report.add(Arrays.asList(new String[3]));

		final List<String> textList = new ArrayList<>();
		textList.add("XYZ");
		textListEvent.fire(textList);

		researchCDIHelper.showResults(ResearchCDIHelper.MENU_ARR[3]);
		logger.info("fireEvents():");
	}
}