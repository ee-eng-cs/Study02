package kp.c_d_i;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.inject.Inject;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

/**
 * Research CDI Helper.
 *
 */
public class ResearchCDIHelper {

	@Inject
	private List<List<String>> report;

	public static final String[] MENU_ARR = { /*-*/
			"Alternative & Qualified Beans", "Decorated Beans", /*-*/
			"Intercepted Beans", "Events", "Go Back"/*-*/
	};
	private static final Color DIALOG_COLOR = new ColorUIResource(255, 228, 225);
	private static final String TITLE = "CDI using Weld SE Container";
	private static final String[] HEADERS = { "Class Name", "Method Name", "Message" };
	private static final int[] COL_WIDTH = { 200, 160, 475 };
	private static final Dimension DIMEN = new Dimension(840, 120);

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
		final int result = JOptionPane.showOptionDialog(null, null, TITLE, 0, JOptionPane.PLAIN_MESSAGE, null, MENU_ARR,
				MENU_ARR[MENU_ARR.length - 1]);
		UIManager.put("OptionPane.background", optionPaneBackground);
		UIManager.put("Panel.background", panelBackground);
		return result;
	}

	/**
	 * Shows results.
	 * 
	 * @param title the title
	 */
	public void showResults(String title) {

		final Vector<Vector<String>> rowData = new Vector<Vector<String>>();
		for (List<String> row : report) {
			final Vector<String> cellData = new Vector<String>();
			rowData.add(cellData);
			cellData.add(row.get(0));
			cellData.add(row.get(1));
			cellData.add(row.get(2));
		}
		final JTable tableRes = new JTable(rowData, new Vector<String>(Arrays.asList(HEADERS)));
		for (int i = 0; i < COL_WIDTH.length; i++) {
			tableRes.getColumnModel().getColumn(i).setMinWidth(COL_WIDTH[i]);
			tableRes.getColumnModel().getColumn(i).setMaxWidth(COL_WIDTH[i]);
		}
		tableRes.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
		tableRes.setPreferredScrollableViewportSize(DIMEN);
		JOptionPane.showMessageDialog(null, new JScrollPane(tableRes), title, JOptionPane.PLAIN_MESSAGE);
	}
}