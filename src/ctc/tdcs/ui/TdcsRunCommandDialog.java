package ctc.tdcs.ui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import com.swtdesigner.SWTResourceManager;

import ctc.tdcs.tdcsdbserver.DatabaseServer;


public class TdcsRunCommandDialog extends Dialog {

	private Shell shell;
	DatabaseServer databaseServer = DatabaseServer.getInstance();	
	
	public TdcsRunCommandDialog(Shell shell) {
		super(shell, SWT.SYSTEM_MODAL | SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		this.shell = shell;
	}
	
	public void open() {

		Display display = getParent().getDisplay();
		Shell shell = new Shell(getParent(), getStyle());
		shell.setText("启动实验");
		
		createContents(shell);
		
		shell.pack();

		// 使对话框窗口处于屏幕中间
		Rectangle displayBounds = display.getPrimaryMonitor().getBounds();
		Rectangle shellBounds = shell.getBounds();// 获取屏幕高度和宽度
		int x = displayBounds.x + (displayBounds.width - shellBounds.width) >> 1;
		int y = displayBounds.y + (displayBounds.height - shellBounds.height) >> 1;
		shell.setSize(340, 180);//width, height
		shell.setLocation(x, y);// 定位窗口坐标

		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	private void createContents(final Shell comp) {
		
		//建立GridLayout布局
		GridLayout gridLayout = new GridLayout();
		gridLayout.marginRight = 30;
		gridLayout.numColumns = 2;// 列数目
		gridLayout.makeColumnsEqualWidth = true; // 强制全部的列拥有相同的宽度
		gridLayout.horizontalSpacing = 10;// 控制一行中两个网格间组件的宽度,像素为单位
		gridLayout.verticalSpacing = 20;// 一列中两个网络间组件的宽度,像素为单位
		gridLayout.marginHeight = 20;// 控制顶部和底部组件离边缘的距离空间,以像素为单位.
		gridLayout.marginWidth = 20;// 控制左边和右边组件离边缘的距离空间,以像素为单位.
		comp.setLayout(gridLayout);
		
		GridData data = new GridData(SWT.LEFT,SWT.CENTER, true, false);
		final Label label_4 = new Label(comp, SWT.NONE);
		label_4.setFont(SWTResourceManager.getFont("", 10, SWT.BOLD));
		label_4.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false));
		label_4.setText("虚拟时间:");
		final DateTime time = new DateTime (comp, SWT.TIME |SWT.SHORT );//SWT.CALENDAR SWT.DATE  SWT.MEDIUM 含秒
		data = new GridData(SWT.LEFT,SWT.CENTER, true, false);
		data.heightHint = 20;
		data.widthHint = 90;
		time.setLayoutData(data);
		
		data = new GridData(SWT.LEFT,SWT.CENTER, true, false);
		final Label label = new Label(comp, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("", 10, SWT.BOLD));
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false));
		label.setText("时间步长(分钟):");
		final Text minute = new Text(comp, SWT.BORDER);
		data = new GridData(SWT.LEFT,SWT.CENTER, true, false);
	    data.widthHint = 85;
	    minute.setLayoutData(data);
	    minute.addVerifyListener(listener);//检查监听器,每输入一个字符都回触发
	    minute.setText("1");//默认值
	    minute.setTextLimit(2);  //最都只能输入3位数字
		
		new Label(comp, SWT.NONE);
		ToolBar toolBar_2 = new ToolBar(comp, SWT.FLAT);
		final ToolItem startItem = new ToolItem(toolBar_2, SWT.PUSH);
		final ToolItem cancelItem = new ToolItem(toolBar_2, SWT.PUSH);
		
		startItem.setText("启动");
		startItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				String timeStr = time.getHours () + ":" + time.getMinutes();
				if(timeStr.length() >0){
					if(! databaseServer.TDCSCommandForRun(timeStr,minute.getText()))
						showMsg("启动失败！（不同角色学员还没有全部登录）");
					else{
						//DatabaseServer.getInstance().TDCSCommandForTrain();//发送计划信息
						showMsg("成功启动！");						
						TdcsToolbarFactory.getInstance().run2stop();						
						comp.close();
					}
			    }else{
					showMsg("请输入虚拟时间！");
				}
			}
		});
		
		cancelItem.setText("取消");
		cancelItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				comp.close();
			}
		});
		
		ToolItem helpItem = new ToolItem(toolBar_2, SWT.PUSH);
		helpItem.setText("提示");
		helpItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				 String str = "\n\r在进行启动之前\n\r" +
				 			 "确保组内TDCS，RSB，CTC，5个站机用户都已登录\n\r";
				 showMsg(str);
			}
		});
	}

	VerifyListener listener = new VerifyListener() {
		//doit属性如果为true,则字符允许输入,反之不允许
		public void verifyText(VerifyEvent e) {   
			//输入控制键，输入中文，输入字符，输入数字 正整数验证   
			Pattern pattern = Pattern.compile("[0-9]\\d*"); //正则表达式  
			Matcher matcher = pattern.matcher(e.text);   
			if (matcher.matches()) //处理数字   
			{
				e.doit = true;
			}
			else if (e.text.length() > 0) //字符: 包含中文、空格   
				e.doit = false;
			else//控制键  
				e.doit = true;   
		}   
	};
	
	private void showMsg(String str){
		MessageBox mb = new MessageBox(shell, SWT.ABORT | SWT.ICON_INFORMATION);
		mb.setText("提示信息");
		mb.setMessage(str);
		mb.open();
	}
	
}