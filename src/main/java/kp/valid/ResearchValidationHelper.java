package kp.valid;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

/**
 * Research Validation Helper.
 *
 */
public class ResearchValidationHelper {

	private static final Color DIALOG_COLOR = new ColorUIResource(176, 224, 230);

	public static final String[] MENU_ARR = { /*-*/
			"Validate Item", "Validate Box of Items", /*-*/
			"Validate Method", "Go Back"/*-*/
	};
	private static final Vector<String> COLUMN_NAMES = new Vector<String>(
			Arrays.asList(new String[] { "No.", "Validated Value" }));

	/**
	 * Shows buttons.
	 * 
	 * @return the result
	 */
	public int showButtons() {

		final Object optionPaneBackground = UIManager.get("OptionPane.background");
		final Object panelBackground = UIManager.get("Panel.background");
		UIManager.put("OptionPane.background", DIALOG_COLOR);
		UIManager.put("Panel.background", DIALOG_COLOR);
		final int result = JOptionPane.showOptionDialog(null, null, "Bean Validation", 0, JOptionPane.PLAIN_MESSAGE,
				null, MENU_ARR, MENU_ARR[MENU_ARR.length - 1]);
		UIManager.put("OptionPane.background", optionPaneBackground);
		UIManager.put("Panel.background", panelBackground);
		return result;
	}

	/**
	 * Shows validate results.
	 * 
	 * @param rowData      the rowData
	 * @param headersArray the headersArray
	 * @param colWidthArr  the colWidthArr
	 * @param dimension    the dimension
	 * @param title        the title
	 */
	public void showValidateResults(Vector<Vector<String>> rowData, String[] headersArray, int[] colWidthArr,
			Dimension dimension, String title) {

		final JTable tableRes = new JTable(rowData, new Vector<String>(Arrays.asList(headersArray)));
		for (int i = 0; i < colWidthArr.length; i++) {
			tableRes.getColumnModel().getColumn(i).setMinWidth(colWidthArr[i]);
			tableRes.getColumnModel().getColumn(i).setMaxWidth(colWidthArr[i]);
		}
		tableRes.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
		tableRes.setPreferredScrollableViewportSize(dimension);
		JOptionPane.showMessageDialog(null, new JScrollPane(tableRes), title, JOptionPane.PLAIN_MESSAGE);
	}

	/**
	 * Shows validate results.
	 * 
	 * @param rowData          the rowData
	 * @param headersArray     the headersArray
	 * @param colWidthArr      the colWidthArr
	 * @param dimension        the dimension
	 * @param title            the title
	 * @param valuesToValidate the valuesToValidate
	 */
	public void showValidateResults(Vector<Vector<String>> rowData, String[] headersArray, int[] colWidthArr,
			Dimension dimension, String title, String[] valuesToValidate) {

		final JTabbedPane tabbedPane = new JTabbedPane();
		addResultsTab(rowData, headersArray, colWidthArr, dimension, tabbedPane);
		addValuesTab(dimension, valuesToValidate, tabbedPane);
		JOptionPane.showMessageDialog(null, tabbedPane, title, JOptionPane.PLAIN_MESSAGE);
	}

	/**
	 * Adds results tab.
	 * 
	 * @param rowData      the rowData
	 * @param headersArray the headersArray
	 * @param colWidthArr  the colWidthArr
	 * @param dimension    the dimension
	 * @param tabbedPane   the tabbedPane
	 */
	private void addResultsTab(Vector<Vector<String>> rowData, String[] headersArray, int[] colWidthArr,
			Dimension dimension, JTabbedPane tabbedPane) {

		final JTable tableRes = new JTable(rowData, new Vector<String>(Arrays.asList(headersArray)));
		for (int i = 0; i < colWidthArr.length; i++) {
			tableRes.getColumnModel().getColumn(i).setMinWidth(colWidthArr[i]);
			tableRes.getColumnModel().getColumn(i).setMaxWidth(colWidthArr[i]);
		}
		tableRes.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
		tableRes.setPreferredScrollableViewportSize(dimension);
		final JScrollPane scrollPaneRes = new JScrollPane(tableRes);
		tabbedPane.addTab("Validation Results", null, scrollPaneRes, null);
	}

	/**
	 * Adds values tab.
	 * 
	 * @param dimension        the dimension
	 * @param valuesToValidate the valuesToValidate
	 * @param tabbedPane       the tabbedPane
	 */
	private void addValuesTab(Dimension dimension, String[] valuesToValidate, JTabbedPane tabbedPane) {

		final Vector<Vector<String>> rowValData = new Vector<Vector<String>>();
		int number = 1;
		for (String val : valuesToValidate) {
			final Vector<String> cellValData = new Vector<String>();
			cellValData.add(String.valueOf(number++));
			cellValData.add(val);
			rowValData.add(cellValData);
		}
		final JTable tableVal = new JTable(rowValData, COLUMN_NAMES);
		tableVal.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
		tableVal.setPreferredScrollableViewportSize(dimension);
		final JScrollPane scrollPaneVal = new JScrollPane(tableVal);
		tabbedPane.addTab("Validated Values", null, scrollPaneVal, null);
	}
}
