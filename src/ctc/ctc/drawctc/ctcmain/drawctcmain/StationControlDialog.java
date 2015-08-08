package ctc.ctc.drawctc.ctcmain.drawctcmain;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import ctc.ctc.ctc2sics.CTCToSICS;

public class StationControlDialog extends Dialog {
	
	public StationControlDialog(Shell parent) {
		super(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
	}

	public void openSQZK(final String stationName) {
		
		Display display = getParent().getDisplay();

		final Shell shell = new Shell(getParent(), getStyle());

		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		gridLayout.horizontalSpacing = 80;
		gridLayout.makeColumnsEqualWidth = true;
		shell.setLayout(gridLayout);

		shell.setText("收到 " + stationName + " 站控申请");

		final Button acceptButton = new Button(shell, SWT.NONE);
		acceptButton.setText("批准站控申请");	

		acceptButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				
				System.out.println("批准站控申请");
				
				CTCToSICS.sendStationControlResponseMessageASYN(stationName, true, "SQZK");
				shell.close();
			}
		});

		final Button rejectButton = new Button(shell, SWT.NONE);
		rejectButton.setText("拒绝站控申请");
		rejectButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {

				System.out.println("拒绝站控申请");
				
				CTCToSICS.sendStationControlResponseMessageASYN(stationName, false, "SQZK");
				shell.close();
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
	
	public void openFCZK(final String stationName) {
		
		Display display = getParent().getDisplay();

		final Shell shell = new Shell(getParent(), getStyle());

		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		gridLayout.horizontalSpacing = 80;
		gridLayout.makeColumnsEqualWidth = true;
		shell.setLayout(gridLayout);

		shell.setText("收到 " + stationName + " 非常站控申请");

		final Button acceptButton = new Button(shell, SWT.NONE);		
		acceptButton.setText("确认收到非常站控申请");	
		
		acceptButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
							
				CTCToSICS.sendStationControlResponseMessageASYN(stationName, true, "FCZK");
				shell.close();
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
	
}
