package ctc.rsb.ui;

import org.eclipse.swt.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

import ctc.sics.SicsMain;
import ctc.sics.db.DataBase;
import ctc.transport.MinaClient;
import ctc.util.*;


//import ctc.sics.stationLayout.StationInit; //原车站

//import ctc.sics.station1.layout.StationInit; //车站1

import ctc.sics.station.layout.StationInit; //车站1


public class SicsMainWindow {
	
	private static Shell shell;
	private static SystemTray sysTray;
	private static MinaClient minaClient;
	private static DataBase db;
	public static Composite comShell;
	
	public SicsMainWindow(Shell newShell,MinaClient newMinaClient,DataBase newDb) {

 		minaClient = newMinaClient;
 		db = newDb;
		sysTray = new SystemTray(db);
 
		shell = newShell;
				
		createContents();

		//监听关闭窗口事件,对应窗口右上角的关闭按钮
		shell.addShellListener(new org.eclipse.swt.events.ShellAdapter() {
			public void shellClosed(org.eclipse.swt.events.ShellEvent e) {
				SicsMain.getApp().closeWindow();
			}
		});
	}

	public static Shell getShell() {
		return shell;
	}
	
	public static void setShell(Shell shell) {
		SicsMainWindow.shell = shell;
	}

	public SystemTray getSystemTray() {
		return sysTray;
	}

	// 创建主窗体内容
	private void createContents() {
		
		GridData gridDataCanvas = new GridData(GridData.FILL_BOTH); 
		comShell = new  Composite(shell,SWT.NONE);
		comShell.setSize(shell.getBounds().width, shell.getBounds().height);
		
		new StationInit(shell,comShell);
		
		comShell.setLayoutData(gridDataCanvas);		
		shell.pack();	
		
	}

}
