package ctc.ctc.drawctc;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

import ctc.ctc.CTCMain;
import ctc.ctc.drawctc.ctcmain.data.BaseParam;
import ctc.ctc.drawctc.ctcmain.drawctcmain.DrawCTCGraph;


public class InitCTC {

	BaseParam baseData = BaseParam.getInstance();	
	
 	public InitCTC(Shell shell) {

 		baseData.setMainShell(shell);
				
		createContents();

		//监听关闭窗口事件,对应窗口右上角的关闭按钮
		shell.addShellListener(new org.eclipse.swt.events.ShellAdapter() {
			public void shellClosed(org.eclipse.swt.events.ShellEvent e) {
				CTCMain.getApp().closeWindow();
			}
		});
	}

	// 创建主窗体内容
	private void createContents() {

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		TabFolder tabFolder = new TabFolder(baseData.getMainShell(), SWT.NONE);// 声明一个选项卡容器
		tabFolder.setBounds(5, 5, screen.width - 10, screen.height - 10); // 设置选项卡的位置和大小
	
		// 声明第1个选项页	
		TabItem station1 = new TabItem(tabFolder, SWT.NONE);	
		station1.setText("车站一"); // 设置选项页的标题
		{
			Composite stationCom1 = new  Composite(tabFolder,SWT.NONE);
			stationCom1.setSize(baseData.getMainShell().getBounds().width -5 , baseData.getMainShell().getBounds().height - 5);
			new ctc.sics.station1.layout.StationInit(baseData.getMainShell(), stationCom1);				
			station1.setControl(stationCom1); // 让station1控制comShell		
			
		}
		
	
		// 声明第2个选项页
		TabItem station2 = new TabItem(tabFolder, SWT.NONE);		
		station2.setText("车站二"); // 设置选项页的标题
		{
			Composite station2Shell = new  Composite(tabFolder,SWT.NONE);
			station2Shell.setSize(baseData.getMainShell().getBounds().width -5 , baseData.getMainShell().getBounds().height - 5);
			new ctc.sics.station2.layout.StationInit(baseData.getMainShell(),station2Shell);				
			station2.setControl(station2Shell); // 让station1控制comShell		
			
		}
		


		// 声明第3个选项页
		TabItem station3 = new TabItem(tabFolder, SWT.NONE);		
		station3.setText("车站三"); // 设置选项页的标题
		{
			Composite station3Shell = new  Composite(tabFolder,SWT.NONE);
			station3Shell.setSize(baseData.getMainShell().getBounds().width -5 , baseData.getMainShell().getBounds().height - 5);
			new ctc.sics.station3.layout.StationInit(baseData.getMainShell(),station3Shell);				
			station3.setControl(station3Shell); // 让station1控制comShell		
			
		}
		
		// 声明第4个选项页	
		TabItem station4 = new TabItem(tabFolder, SWT.NONE);	
		station4.setText("车站四"); // 设置选项页的标题
		{
			Composite station4Shell = new  Composite(tabFolder,SWT.NONE);
			station4Shell.setSize(baseData.getMainShell().getBounds().width -5 , baseData.getMainShell().getBounds().height - 5);
			new ctc.sics.station4.layout.StationInit(baseData.getMainShell(),station4Shell);				
			station4.setControl(station4Shell); // 让station1控制comShell		
			
		}
		
		// 声明第5个选项页
		TabItem station5 = new TabItem(tabFolder, SWT.NONE);		
		station5.setText("车站五"); // 设置选项页的标题
		{
			Composite station5Shell = new  Composite(tabFolder,SWT.NONE);
			station5Shell.setSize(baseData.getMainShell().getBounds().width -5 , baseData.getMainShell().getBounds().height - 5);
			new ctc.sics.station5.layout.StationInit(baseData.getMainShell(),station5Shell);				
			station5.setControl(station5Shell); // 让station1控制comShell		
			
		}
		

		// 声明第6个选项页
		TabItem rsb = new TabItem(tabFolder, SWT.NONE);		
		rsb.setText("区间"); // 设置选项页的标题
		{
			Composite rsbShell = new  Composite(tabFolder,SWT.NONE);
			rsbShell.setSize(baseData.getMainShell().getBounds().width -5 , baseData.getMainShell().getBounds().height - 5);
			new ctc.ctc.rsb.RSBInit(baseData.getMainShell(),rsbShell);				
			rsb.setControl(rsbShell); 	
			
		}
		
		
		TabItem ctc = new TabItem(tabFolder, SWT.NONE);// 声明第1个选项页		
		ctc.setText("CTC"); // 设置选项页的标题
		{
			baseData.setCtcCom(new Composite(tabFolder,SWT.NONE));
			baseData.getCtcCom().setSize(baseData.getMainShell().getBounds().width -5 , baseData.getMainShell().getBounds().height - 5);
			new DrawCTCGraph(baseData.getCtcCom());				
			ctc.setControl(baseData.getCtcCom()); 	
			
		}

	}
	
}
