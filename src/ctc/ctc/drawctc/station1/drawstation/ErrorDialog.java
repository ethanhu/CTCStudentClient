package ctc.ctc.drawctc.station1.drawstation;

import org.eclipse.draw2d.Figure;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;


import ctc.ctc.ctc2sics.CTCToSICS;
import ctc.ctc.drawctc.station1.data.*;
import ctc.ctc.drawctc.station1.layout.StationModel;
import ctc.ctc.drawctc.station1.elements.semaphore.*;
import ctc.ctc.drawctc.station1.elements.common.*;
import ctc.ctc.drawctc.station1.elements.turnout.*;

public class ErrorDialog extends Dialog {
	public ErrorDialog(Shell parent) {
		super(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
	}

	BaseParam baseData = BaseParam.getInstance();
	StationModel stationModel = StationModel.getInstance();

	public void open() {
		
		final String[] MENU1 = { "股道", "道岔", "信号机" };

		final String[] MENU2_1 = { "I", "II", "3", "4", "5", "6" };

		final String[] MENU2_2 = { "1/3_5/7", "2/4_6/8", "11", "12", "13", "15", "17" };

		final String[] MENU2_3 = { "S1", "S2", "S3", "S4", "S5", "S6", "X1", "X2", "X3", "X4", "X5", "X6" };

		final String[] MENU3_1 = { "股道断裂", "股道维修" };

		final String[] MENU3_2 = { "挤岔", "断裂" };

		final String[] MENU3_3 = { "灯丝断裂", "线路故障" };

		
		Display display = getParent().getDisplay();

		final Shell shell = new Shell(getParent(), getStyle());

		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 4;
		gridLayout.horizontalSpacing = 80;
		gridLayout.makeColumnsEqualWidth = true;
		shell.setLayout(gridLayout);

		shell.setText("故障类型");

		final Label label1 = new Label(shell, SWT.NONE);
		label1.setText("类型");

		final Label label2 = new Label(shell, SWT.NONE);
		label2.setText("名称");

		final Label label3 = new Label(shell, SWT.NONE);
		label3.setText("故障名称");

		final Button button1 = new Button(shell, SWT.NONE);
		button1.setText("设置故障");

		final Combo combo1 = new Combo(shell, SWT.NONE);
		combo1.setBounds(10, 10, 80, 25);

		combo1.setItems(MENU1);
		combo1.select(0);

		final Combo combo2 = new Combo(shell, SWT.NONE);
		combo2.setBounds(100, 10, 80, 25);
		combo2.setItems(MENU2_1);
		combo2.select(0);

		final Combo combo3 = new Combo(shell, SWT.NONE);
		combo3.setBounds(200, 10, 80, 25);
		combo3.setItems(MENU3_1);
		combo3.select(0);

		final Button button2 = new Button(shell, SWT.NONE);
		button2.setText("恢复故障");

		combo1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				String selectedItem = combo1.getItem(combo1.getSelectionIndex());

				if (selectedItem.equals("股道")) {
					combo2.removeAll();
					combo2.setItems(MENU2_1);

					combo3.removeAll();
					combo3.setItems(MENU3_1);
				} else if (selectedItem.equals("道岔")) {
					combo2.removeAll();
					combo2.setItems(MENU2_2);

					combo3.removeAll();
					combo3.setItems(MENU3_2);
				} else if (selectedItem.equals("信号机")) {
					combo2.removeAll();
					combo2.setItems(MENU2_3);

					combo3.removeAll();
					combo3.setItems(MENU3_3);
				}
				combo2.select(0);
				combo3.select(0);
			}
		});

		button1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {

				SZGZ(combo2.getText());

			}
		});

		button2.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				HFGZ(combo2.getText());
			}
		});
		shell.pack();
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	// 设置故障
	public void SZGZ(String name) {

		//System.out.println("设置故障");

		CTCToSICS.sendErrorMessageASYN("标准站一", name, false);
		
		Figure fig = stationModel.getFigureObjectByName(name);
		if (fig == null) {
			return;
		}

		if (fig instanceof TrackLine) {
			if (((TrackLine) fig).getName().equalsIgnoreCase(name)) {
				((TrackLine) fig).setColorStatus(ParamFlag.trackline_black);
			}
		} else if (fig instanceof TurnoutLS) {
			if (((TurnoutLS) fig).getName().equalsIgnoreCase(name)) {
				((TurnoutLS) fig).setColorStatus(ParamFlag.turnout_dw_black);
			}
		} else if (fig instanceof TurnoutLX) {
			if (((TurnoutLX) fig).getName().equalsIgnoreCase(name)) {
				((TurnoutLX) fig).setColorStatus(ParamFlag.turnout_dw_black);
			}
		} else if (fig instanceof TurnoutRS) {
			if (((TurnoutRS) fig).getName().equalsIgnoreCase(name)) {
				((TurnoutRS) fig).setColorStatus(ParamFlag.turnout_dw_black);
			}
		} else if (fig instanceof TurnoutRX) {
			if (((TurnoutRX) fig).getName().equalsIgnoreCase(name)) {
				((TurnoutRX) fig).setColorStatus(ParamFlag.turnout_dw_black);
			}
		} else if (fig instanceof TurnoutDouble) {
			if (((TurnoutDouble) fig).getName().equalsIgnoreCase(name)) {
				((TurnoutDouble) fig).setColorStatus(ParamFlag.turnout_dw_black);
			}
		} else if (fig instanceof TurnoutDoubleR) {
			if (((TurnoutDoubleR) fig).getName().equalsIgnoreCase(name)) {
				((TurnoutDoubleR) fig).setColorStatus(ParamFlag.turnout_dw_black);
			}
		} else if (fig instanceof TurnoutDoubleL) {
			if (((TurnoutDoubleL) fig).getName().equalsIgnoreCase(name)) {
				((TurnoutDoubleL) fig).setColorStatus(ParamFlag.turnout_dw_black);
			}
		} else if (fig instanceof SemaphoreDoubleL) {
			if (((SemaphoreDoubleL) fig).getName().equalsIgnoreCase(name)) {
				((SemaphoreDoubleL) fig).setColorStatus(ParamFlag.sep_white);
			}
		} else if (fig instanceof SemaphoreDoubleR) {
			if (((SemaphoreDoubleR) fig).getName().equalsIgnoreCase(name)) {
				((SemaphoreDoubleR) fig).setColorStatus(ParamFlag.sep_white);
			}
		} else if (fig instanceof SemaphoreSimpleL) {
			if (((SemaphoreSimpleL) fig).getName().equalsIgnoreCase(name)) {
				((SemaphoreSimpleL) fig).setColorStatus(ParamFlag.sep_white);
			}
		} else if (fig instanceof SemaphoreSimpleR) {
			if (((SemaphoreSimpleR) fig).getName().equalsIgnoreCase(name)) {
				((SemaphoreSimpleR) fig).setColorStatus(ParamFlag.sep_white);
			}
		}

	}

	// 恢复故障
	public void HFGZ(String name) {

		//System.out.println("恢复故障");
		CTCToSICS.sendErrorMessageASYN("标准站一", name, true);
		
		Figure fig = stationModel.getFigureObjectByName(name);
		if (fig == null) {
			return;
		}

		if (fig instanceof TrackLine) {
			if (((TrackLine) fig).getName().equalsIgnoreCase(name)) {
				((TrackLine) fig).setColorStatus(ParamFlag.trackline_bule);
			}
		} else if (fig instanceof TurnoutLS) {
			if (((TurnoutLS) fig).getName().equalsIgnoreCase(name)) {
				((TurnoutLS) fig).setColorStatus(ParamFlag.turnout_dw_bule);
			}
		} else if (fig instanceof TurnoutLX) {
			if (((TurnoutLX) fig).getName().equalsIgnoreCase(name)) {
				((TurnoutLX) fig).setColorStatus(ParamFlag.turnout_dw_bule);
			}
		} else if (fig instanceof TurnoutRS) {
			if (((TurnoutRS) fig).getName().equalsIgnoreCase(name)) {
				((TurnoutRS) fig).setColorStatus(ParamFlag.turnout_dw_bule);
			}
		} else if (fig instanceof TurnoutRX) {
			if (((TurnoutRX) fig).getName().equalsIgnoreCase(name)) {
				((TurnoutRX) fig).setColorStatus(ParamFlag.turnout_dw_bule);
			}
		} else if (fig instanceof TurnoutDouble) {
			if (((TurnoutDouble) fig).getName().equalsIgnoreCase(name)) {
				((TurnoutDouble) fig).setColorStatus(ParamFlag.turnout_dw_bule);
			}
		} else if (fig instanceof TurnoutDoubleR) {
			if (((TurnoutDoubleR) fig).getName().equalsIgnoreCase(name)) {
				((TurnoutDoubleR) fig).setColorStatus(ParamFlag.turnout_dw_bule);
			}
		} else if (fig instanceof TurnoutDoubleL) {
			if (((TurnoutDoubleL) fig).getName().equalsIgnoreCase(name)) {
				((TurnoutDoubleL) fig).setColorStatus(ParamFlag.turnout_dw_bule);
			}
		} else if (fig instanceof SemaphoreDoubleL) {
			if (((SemaphoreDoubleL) fig).getName().equalsIgnoreCase(name)) {
				((SemaphoreDoubleL) fig).setColorStatus(ParamFlag.sep_red);
			}
		} else if (fig instanceof SemaphoreDoubleR) {
			if (((SemaphoreDoubleR) fig).getName().equalsIgnoreCase(name)) {
				((SemaphoreDoubleR) fig).setColorStatus(ParamFlag.sep_red);
			}
		} else if (fig instanceof SemaphoreSimpleL) {
			if (((SemaphoreSimpleL) fig).getName().equalsIgnoreCase(name)) {
				((SemaphoreSimpleL) fig).setColorStatus(ParamFlag.sep_red);
			}
		} else if (fig instanceof SemaphoreSimpleR) {
			if (((SemaphoreSimpleR) fig).getName().equalsIgnoreCase(name)) {
				((SemaphoreSimpleR) fig).setColorStatus(ParamFlag.sep_red);
			}
		}

	}

}
