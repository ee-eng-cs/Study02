package kp.valid.data;

import java.util.logging.Logger;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * OperCons - interface with constraints on method.
 *
 */
public interface OperCons {
	Logger logger = Logger.getLogger(OperCons.class.getName());

	/**
	 * Processes the value.
	 * 
	 * @param value the value
	 * @return the value
	 */
	@Max(1)
	default int process(@Min(2) Integer value) {

		logger.info(String.format("process(): value[%d]", value));
		return value;
	}
}