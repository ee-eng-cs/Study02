package kp.valid;

import java.awt.Dimension;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;
import java.util.logging.Logger;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;

import kp.valid.data.BoxCons;
import kp.valid.data.ItemCons;
import kp.valid.data.OperCons;
import kp.valid.data.impl.ItemConsImplCons;
import kp.valid.data.impl.ItemConsImplNoCons;

/**
 * Research Validation.
 *
 */
public class ResearchValidation {

	private static final Logger logger = Logger.getLogger(ResearchValidation.class.getName());

	private static final ResearchValidationHelper researchValidationHelper = new ResearchValidationHelper();
	private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
	private static final ExecutableValidator executableValidator = validator.forExecutables();

	private static final String[] DATA_FLD_RET = { "xxxxxx", "Fldxxx", "xxxRet", "FldRet" };

	private static final String[] VALIDATE_ITEM_HEADERS = { /*-*/
			"Class Name", "Validated Value", /*-*/
			"Valid", "Constraint Violation Error Message"/*-*/
	};
	private static final int[] VALIDATE_ITEM_COL_W = { 185, 95, 40, 200 };
	private static final Dimension VALIDATE_ITEM_DIM = new Dimension(520, 180);

	private static final String[] VALIDATE_BOX_HEADERS = { /*-*/
			"No.", "PropertyPath", /*-*/
			"Validated Value", "Constraint Violation Error Message"/*-*/
	};
	private static final int[] VALIDATE_BOX_COL_W = { 35, 115, 95, 390 };
	private static final Dimension VALIDATE_BOX_DIM = new Dimension(640, 115);

	private static final String[] VALIDATE_METHOD_HEADERS = { /*-*/
			"Constraints Placed On", "Validated Value", /*-*/
			"Valid", "Constraint Violation Error Message"/*-*/
	};
	private static final int[] VALIDATE_METHOD_COL_W = { 135, 95, 40, 350 };
	private static final Dimension VALIDATE_METHOD_DIM = new Dimension(625, 85);

	/**
	 * Processes the validation.
	 * 
	 */
	public void process() {

		LOOP: while (true) {
			int result = researchValidationHelper.showButtons();
			switch (result) {
			case 0:
				validateItem();
				break;
			case 1:
				validateBoxOfItems();
				break;
			case 2:
				validateOperMethod();
				break;
			default:
				// Go Back
				break LOOP;
			}
		}
	}

	/**
	 * Validates the item.
	 * 
	 */
	private void validateItem() {

		final Vector<Vector<String>> rowData = new Vector<Vector<String>>();
		for (String val : DATA_FLD_RET) {
			if (!val.equals(DATA_FLD_RET[0])) {
				rowData.add(new Vector<String>());
			}
			for (ItemCons itemCons : new ItemCons[] { new ItemConsImplCons(), new ItemConsImplNoCons() }) {
				itemCons.setVal(val);
				final Set<ConstraintViolation<ItemCons>> violations = validator.validate(itemCons);
				readViolations(rowData, val, itemCons, violations);
			}
		}
		researchValidationHelper.showValidateResults(rowData, VALIDATE_ITEM_HEADERS, VALIDATE_ITEM_COL_W,
				VALIDATE_ITEM_DIM, ResearchValidationHelper.MENU_ARR[0], DATA_FLD_RET);
		logger.info("validateItem()");
	}

	/**
	 * Reads violations.
	 * 
	 * @param rowData    the rowData
	 * @param val        the val
	 * @param itemCons   the itemCons
	 * @param violations the violations
	 */
	private void readViolations(Vector<Vector<String>> rowData, String val, ItemCons itemCons,
			Set<ConstraintViolation<ItemCons>> violations) {

		final Vector<String> cellData = new Vector<String>();
		rowData.add(cellData);
		cellData.add(itemCons.getClass().getSimpleName());
		if (violations.isEmpty()) {
			cellData.add(val);
			cellData.add("YES");
			return;
		}
		for (ConstraintViolation<ItemCons> violation : violations) {
			cellData.add(violation.getInvalidValue().toString());
			cellData.add("NO");
			cellData.add(violation.getMessage());
		}
	}

	/**
	 * Validates the box of items.
	 * 
	 */
	private void validateBoxOfItems() {

		final Vector<Vector<String>> rowData = new Vector<Vector<String>>();
		final BoxCons boxConsEmpty = new BoxCons(null, BigDecimal.ONE);
		validateBoxOfItems(boxConsEmpty, rowData, "1.");
		final List<ItemCons> list = new ArrayList<>();
		for (String val : DATA_FLD_RET) {
			final ItemConsImplCons itemConsImplCons = new ItemConsImplCons();
			itemConsImplCons.setVal(val);
			list.add(itemConsImplCons);
		}
		rowData.add(new Vector<String>());
		final BoxCons boxConsFull = new BoxCons(list, BigDecimal.TEN);
		validateBoxOfItems(boxConsFull, rowData, "2.");

		researchValidationHelper.showValidateResults(rowData, VALIDATE_BOX_HEADERS, VALIDATE_BOX_COL_W,
				VALIDATE_BOX_DIM, ResearchValidationHelper.MENU_ARR[1], DATA_FLD_RET);
		logger.info("validateBoxOfItems()");
	}

	/**
	 * Validates the box of items.
	 * 
	 * @param boxCons the boxCons
	 * @param rowData the rowData
	 * @param label   the label
	 */
	private void validateBoxOfItems(BoxCons boxCons, Vector<Vector<String>> rowData, String label) {

		final Set<ConstraintViolation<BoxCons>> violations = validator.validate(boxCons);
		final Map<String, List<ConstraintViolation<BoxCons>>> map = new TreeMap<>();
		for (ConstraintViolation<BoxCons> violation : violations) {
			List<ConstraintViolation<BoxCons>> list = map.get(violation.getPropertyPath().toString());
			list = Objects.nonNull(list) ? list : new ArrayList<ConstraintViolation<BoxCons>>();
			list.add(violation);
			map.put(violation.getPropertyPath().toString(), list);
		}
		int number = 1;
		for (List<ConstraintViolation<BoxCons>> list : map.values()) {
			for (ConstraintViolation<BoxCons> violation : list) {
				readViolation(rowData, label, violation, number);
				number++;
			}
		}
	}

	/**
	 * Reads violations.
	 * 
	 * @param rowData   the rowData
	 * @param label     the label
	 * @param violation the violation
	 * @param number    the number
	 */
	private void readViolation(Vector<Vector<String>> rowData, String label, ConstraintViolation<BoxCons> violation,
			int number) {

		final Vector<String> cellData = new Vector<String>();
		cellData.add(label.concat(String.valueOf(number)));
		cellData.add(violation.getPropertyPath().toString());
		final String invVal = Objects.nonNull(violation.getInvalidValue()) ? violation.getInvalidValue().toString()
				: "null";
		cellData.add(invVal);
		cellData.add(violation.getMessage());
		rowData.add(cellData);
	}

	/**
	 * Validates the method.
	 * 
	 */
	private void validateOperMethod() {

		final OperCons operCons = new OperCons() {
		};
		operCons.process(0);
		Method method = null;
		try {
			method = OperCons.class.getMethod("process", Integer.class);
		} catch (NoSuchMethodException | SecurityException e) {
			logger.severe(String.format("validateOperMethod() Exception[%s]", e.getMessage()));
			System.exit(0);
		}
		final Vector<Vector<String>> rowData = new Vector<Vector<String>>();
		validateOperMethodParameterValue(method, rowData);
		rowData.add(new Vector<String>());
		validateOperMethodReturnValue(method, rowData);
		researchValidationHelper.showValidateResults(rowData, VALIDATE_METHOD_HEADERS, VALIDATE_METHOD_COL_W,
				VALIDATE_METHOD_DIM, ResearchValidationHelper.MENU_ARR[2]);
		logger.info("validateOperMethod()");
	}

	/**
	 * Validates the parameter value of the method.
	 * 
	 * @param method  the method
	 * @param rowData the rowData
	 */
	private void validateOperMethodParameterValue(Method method, Vector<Vector<String>> rowData) {

		final OperCons operCons = new OperCons() {
		};
		for (int i = 1; i <= 2; i++) {
			final Set<ConstraintViolation<OperCons>> violations = executableValidator.validateParameters(operCons,
					method, new Integer[] { i }/*- parameter values */);
			readViolations(violations, rowData, "parameters", i);
		}
	}

	/**
	 * Validates the return value of the method.
	 * 
	 * @param method  the method
	 * @param rowData the rowData
	 */
	private void validateOperMethodReturnValue(Method method, Vector<Vector<String>> rowData) {

		final OperCons operCons = new OperCons() {
		};
		for (int i = 2; i >= 1; i--) {
			final Set<ConstraintViolation<OperCons>> violations = executableValidator.validateReturnValue(operCons,
					method, i /*- return value */);
			readViolations(violations, rowData, "return value", i);
		}
	}

	/**
	 * Reads violations.
	 * 
	 * @param violations the violations
	 * @param rowData    the rowData
	 * @param label      the label
	 * @param value      the value
	 */
	private void readViolations(Set<ConstraintViolation<OperCons>> violations, Vector<Vector<String>> rowData,
			String label, int value) {

		final Vector<String> cellData = new Vector<String>();
		rowData.add(cellData);
		cellData.add(label);
		if (violations.isEmpty()) {
			cellData.add(String.valueOf(value));
			cellData.add("YES");
			return;
		}
		for (ConstraintViolation<OperCons> violation : violations) {
			cellData.add(violation.getInvalidValue().toString());
			cellData.add("NO");
			cellData.add(violation.getMessage());
		}
	}
}