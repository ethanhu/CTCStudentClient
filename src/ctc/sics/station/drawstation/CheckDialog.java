package ctc.sics.station.drawstation;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;


//操作检测
public class CheckDialog   extends Dialog {

	private static CheckDialog checkDialog = null;
	public static Boolean result = false;
	
	public static CheckDialog getInstance(Shell shell) {
		if (checkDialog == null) {
			checkDialog = new CheckDialog(shell);
		}
		return checkDialog;
	}

	
	// 构造函数
	public CheckDialog(Shell shell) {
		super(shell, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
	}

	// 打开对话框
	public void open() {
				
		Display display = getParent().getDisplay();

		// 创建一个对话框窗口
		Shell shell = new Shell(getParent(), getStyle());
		shell.setText("请输入口令");
		createContents(shell); //创建内容
		shell.pack();

		// 使对话框窗口处于屏幕中间
		Rectangle displayBounds = display.getPrimaryMonitor().getBounds();
		Rectangle shellBounds = shell.getBounds();// 获取屏幕高度和宽度
		int x = displayBounds.x + (displayBounds.width - shellBounds.width) >> 1;
		int y = displayBounds.y + (displayBounds.height - shellBounds.height) >> 1;
		shell.setLocation(x, y);// 定位窗口坐标
		shell.setSize(200, 150);
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	// 创建对话框内容
	private void createContents(final Shell shell) {
		
		result = false;
		
		Label lab = new Label(shell, SWT.ABORT);
		lab.setText("请输入保护口令：");
		lab.setSize(160, 20);
		lab.setLocation(20, 10);
		
		final Text password = new Text(shell, SWT.BORDER|SWT.PASSWORD);
		password.setSize(160, 20);
		password.setLocation(20, 40);		
		
		Button ok = new Button(shell, SWT.BORDER);
		ok.setSize(70, 30);
		ok.setText("确定");
		ok.setLocation(20, 70);
		    
		ok.addSelectionListener(new SelectionAdapter() {
		      public void widgetSelected(SelectionEvent event) {
		       if(password.getText().equalsIgnoreCase("123")){
		    	   result = true;
		       }
		       shell.close();
		      }
		    });
		
		Button cancel = new Button(shell, SWT.BORDER);
		cancel.setSize(70, 30);
		cancel.setText("取消");
		cancel.setLocation(110, 70);	
		cancel.addSelectionListener(new SelectionAdapter() {
		      public void widgetSelected(SelectionEvent event) {
		        shell.close();
		      }
		    });
			
		
	    shell.addDisposeListener(new DisposeListener(){
	        public void widgetDisposed(DisposeEvent e) {
	            shell.close();
	        }
	    });
	    
	}


	public static Boolean getResult() {
		return result;
	}

	public static void setResult(Boolean result) {
		CheckDialog.result = result;
	}
}

