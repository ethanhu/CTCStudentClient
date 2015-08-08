package ctc.tdcs.ui;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.Shell;

import ctc.tdcs.Layout.TdcsLayout;
import ctc.tdcs.data.BaseParam;
import ctc.tdcs.tdcsdbserver.DatabaseServer;

public class TdcsMainWindow {

	public static Shell shell;

	public TdcsMainWindow(Shell pShell) {
		//shell = new Shell(Display.getCurrent(),SWT.CLOSE | SWT.MIN |SWT.ON_TOP);//shell是程序的主窗口
		shell = pShell;
		shell.setLayout(new GridLayout(1, false));
		shell.setText("组内TDCS");
		
		//监听关闭窗口事件,对应窗口右上角的关闭按钮
		shell.addShellListener(new org.eclipse.swt.events.ShellAdapter() {
	        public void shellClosed(org.eclipse.swt.events.ShellEvent e) {
	        	BaseParam.setDrawCurrentTimeXFlag(false);
	        	new DatabaseServer().TDCSCommandForStop();	
	        }
	    });
		
		createContents(); 
	}
	
	
	public static Shell getShell() {
		return shell;
	}


	public static void setShell(Shell shell) {
		TdcsMainWindow.shell = shell;
	}


	// 创建主窗体内容 菜单, 工具条  内容板等
	private void createContents() {
		
		//获取区间内相关信息， 并保存在TdcsDataset类中
		DatabaseServer thisServer = new DatabaseServer().getInstance();
		thisServer.getAllDistrictInfo();//获取所有区段信息
		
		
		//创建菜单栏
		TdcsMenuFactory tdcsMenu = new TdcsMenuFactory(shell);
		shell.setMenuBar(tdcsMenu.getMenu());

	   //构造工具栏
		CoolBar toolbar = new TdcsToolbarFactory().create(shell);
		//toolbar.pack();
		
		//主界面 初始
		TdcsLayout graph = new TdcsLayout(shell);
		graph.drawInit();
	}
	
}

