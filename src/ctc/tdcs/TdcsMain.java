package ctc.tdcs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;


import ctc.tdcs.TdcsMain;
import ctc.tdcs.data.BaseParam;
import ctc.tdcs.tdcsdbserver.MinaCommunicationHandler;
import ctc.tdcs.ui.*;
import ctc.transport.MinaClient;


public class TdcsMain {

	public static MinaCommunicationHandler minaCommunicationHandler; //服务器连接基类
	final String IMAGE_PATH = System.getProperty("user.dir") + "/resources/images/ctc.png";
	
	public TdcsMain(MinaClient minaClient){
		minaCommunicationHandler = new MinaCommunicationHandler(minaClient);
	}

	// 初始化主程序
	public void run() {
		
		Display display = Display.getDefault();//getParent().getDisplay();
		//Shell shell = new Shell(this.getParent(), getStyle());
		//Shell shell = new Shell(SWT.CLOSE | SWT.MIN |SWT.ON_TOP);//| SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL
		Shell shell = new Shell(SWT.CLOSE | SWT.MIN);//| SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL
		
		//shell.setMinimized(true);
		
		Image TRAY = new Image(shell.getDisplay(), IMAGE_PATH);
		shell.setImage(TRAY);
		
		Rectangle displayBounds = display.getPrimaryMonitor().getBounds(); //获取屏幕的大小		
		//shell.setSize(displayBounds.width, displayBounds.height); //高度 宽度    
		shell.setBounds(displayBounds);//设置程序运行界面的大小 
		
		Rectangle shellBounds = shell.getBounds();// 获取屏幕高度和宽度		
		int x = displayBounds.x + (displayBounds.width - shellBounds.width) >> 1;// 使窗口处于屏幕中间
		int y = displayBounds.y + (displayBounds.height - shellBounds.height) >> 1;
		shell.setLocation(x, y); // 定位窗口坐标	
		
		//程序主窗口		
		new TdcsMainWindow(shell); //应用主窗口 
		
		//监听关闭窗口事件,对应窗口右上角的关闭按钮
		shell.addShellListener(new org.eclipse.swt.events.ShellAdapter() {
	        public void shellClosed(org.eclipse.swt.events.ShellEvent e) {
	        	//组内TDCS发送关闭实验，应该是
	        	new ctc.tdcs.tdcsdbserver.DatabaseServer().TDCSCommandForStop();
	        }
	    });
		
		//shell.layout();
		shell.open(); // 显示主窗口
		   
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		//display.dispose();
	}
  
}