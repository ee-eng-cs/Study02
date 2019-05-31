package kp.valid.data.impl;

import javax.validation.constraints.Pattern;

import kp.valid.data.ItemCons;

/**
 * ItemConsImplCons - implementation with constraints.
 *
 */
public class ItemConsImplCons implements ItemCons {

	/*- field-level constraint */
	@Pattern(regexp = "Fld.*")
	private String val;

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public void setVal(String val) {
		this.val = val;
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	/*- property-level constraint */
	public @Pattern(regexp = ".*Ret") String getVal() {
		return val;
	}
}