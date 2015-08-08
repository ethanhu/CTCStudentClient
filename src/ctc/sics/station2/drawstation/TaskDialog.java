package ctc.sics.station2.drawstation;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;


/**
 * 显示任务列表
 * @author 胡恩召
 *
 */
public class TaskDialog  extends Dialog {

	private static TaskDialog taskDialog = null;

	public static TaskDialog getInstance(Shell shell) {
		if (taskDialog == null) {
			taskDialog = new TaskDialog(shell);
		}
		return taskDialog;
	}

	
	// 构造函数
	public TaskDialog(Shell shell) {
		super(shell, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
	}

	// 打开对话框
	public void open() {
				
		Display display = getParent().getDisplay();

		// 创建一个对话框窗口
		Shell shell = new Shell(getParent(), getStyle());

		shell.setText("调度任务");
		createContents(shell); //创建内容
		shell.pack();

		// 使对话框窗口处于屏幕中间
		Rectangle displayBounds = display.getPrimaryMonitor().getBounds();
		Rectangle shellBounds = shell.getBounds();// 获取屏幕高度和宽度
		int x = displayBounds.x + (displayBounds.width - shellBounds.width) >> 1;
		int y = displayBounds.y + (displayBounds.height - shellBounds.height) >> 1;
		shell.setLocation(x, y);// 定位窗口坐标

		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	// 创建对话框内容
	private void createContents(final Shell shell) {

	    Table table = new Table(shell, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
	    table.setLinesVisible(true);
	    table.setHeaderVisible(true);

	    String[] titles = { "车次", "状态", "方向", "类型", "计划时间", "实际时间", "进路" };
	    for (int i = 0; i < titles.length; i++) {
	      TableColumn column = new TableColumn(table, SWT.NONE);
	      column.setText(titles[i]);
	    }

	    for (int i = 0; i < 100; i++) {
	      TableItem item = new TableItem(table, SWT.NONE);
	      item.setText(0, "x");
	      item.setText(1, "y");
	      item.setText(2, "!");
	      item.setText(3, "this stuff behaves the way I expect");
	      item.setText(4, "almost everywhere");
	      item.setText(5, "some.folder");
	      item.setText(6, "line " + i + " in nowhere");
	    }

	    for (int i=0; i<titles.length; i++) {
	      table.getColumn (i).pack ();
	    }     
	    
	    table.setSize(table.computeSize(SWT.DEFAULT, 200));
	    		
	}


}

