package kp.valid.data.impl;

import kp.valid.data.ItemCons;

/**
 * ItemConsImplNoCons - implementation without constraints.
 *
 */
public class ItemConsImplNoCons implements ItemCons {

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
	public String getVal() {
		return val;
	}
}