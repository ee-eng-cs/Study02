package kp;

import java.util.Objects;
import java.util.logging.Handler;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import kp.c_d_i.ResearchCDIStarter;
import kp.r_e_s_t_f_u_l.ResearchRestful;
import kp.util.LoggingFormatter;
import kp.valid.ResearchValidation;

/**
 * Main Application for Study 02.
 *
 */
public class MainApp02 {

	private static final Logger logger = Logger.getLogger(MainApp02.class.getName());

	private static final String[] MENU_ARR = { /*-*/
			"Validation", "CDI", "RESTful Web Services", "Exit"/*-*/
	};

	/**
	 * The main method.
	 * 
	 * @param args the arguments
	 * @throws Exception the exception
	 */
	public static void main(String[] args) throws Exception {

		initLogging();

		final ResearchValidation researchValidation = new ResearchValidation();
		final ResearchCDIStarter researchCDIStarter = new ResearchCDIStarter();
		final ResearchRestful researchRestful = new ResearchRestful();

		while (true) {
			int n = JOptionPane.showOptionDialog(null, null, "Study 02", 0, JOptionPane.PLAIN_MESSAGE, null, MENU_ARR,
					MENU_ARR[MENU_ARR.length - 1]);
			switch (n) {
			case 0:
				researchValidation.process();
				break;
			case 1:
				researchCDIStarter.startIt();
				break;
			case 2:
				researchRestful.process();
				break;
			default:
				System.exit(0);
			}
		}
	}

	/**
	 * Initializes logging.
	 * 
	 */
	private static void initLogging() {

		Logger parentLogger = logger.getParent();
		Handler[] handlers = parentLogger.getHandlers();
		while (handlers.length == 0) {
			parentLogger = parentLogger.getParent();
			if (Objects.isNull(parentLogger)) {
				System.out.println("Logging problem: handlers not found!");
				return;
			}
			handlers = parentLogger.getHandlers();
		}
		for (Handler handler : handlers) {
			handler.setFormatter(new LoggingFormatter());
		}
	}
}