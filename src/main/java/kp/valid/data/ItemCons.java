package kp.valid.data;

import javax.validation.constraints.Pattern;

/**
 * ItemCons - interface with constraints.
 *
 */
public interface ItemCons {

	/**
	 * Sets the value.
	 * 
	 * @param val the value
	 */
	void setVal(String val);

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	/*- property-level constraint */
	@Pattern(regexp = ".*Ret")
	String getVal();
}