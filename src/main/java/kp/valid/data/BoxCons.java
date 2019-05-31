package kp.valid.data;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 * BoxCons - contains the list with validation cascading.
 *
 */
public class BoxCons {

	@NotNull
	@Valid
	private final List<ItemCons> list;

	@DecimalMin("10.00")
	@DecimalMax("100.00")
	private final BigDecimal decimal;

	/**
	 * Constructor.
	 * 
	 * @param list    the list
	 * @param decimal the decimal
	 */
	public BoxCons(List<ItemCons> list, BigDecimal decimal) {
		super();
		this.list = list;
		this.decimal = decimal;
	}
}