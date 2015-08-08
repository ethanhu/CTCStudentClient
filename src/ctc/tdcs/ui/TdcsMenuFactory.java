package ctc.tdcs.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

public class TdcsMenuFactory {

	Menu menu = null;

	public Menu getMenu() {
		return menu;
	}

	final private Shell shell;
	public TdcsMenuFactory(final Shell shell) {
		
		this.shell = shell;
		// 创建menu，BAR用于主菜单
		menu = new Menu(shell, SWT.BAR);

		//-----------------系统-----------------//
		// 创建文件一级菜单，CASCADE表示有子菜单
		MenuItem item = new MenuItem(menu, SWT.CASCADE);
		item.setText("系统(S)");
		//item.setAccelerator(SWT.ALT + 'S');
		Menu dropMenu = new Menu(shell, SWT.DROP_DOWN);
		item.setMenu(dropMenu);

		// 系统->保存列车调度信息
		item = new MenuItem(dropMenu, SWT.NULL);// RADIO选择后，前面会显示一个圆点
		item.setText("退出\tCtrl+Q");
		item.setAccelerator(SWT.CTRL + 'q');
		item.addSelectionListener(new SelectionAdapter() {// 添加事件监听器
		public void widgetSelected(SelectionEvent event) {
			 shell.dispose();//退出操作窗口
			//System.exit(1);//退出主程序
		}
		});
		// Create Help
	    item = new MenuItem(menu, SWT.CASCADE);
	    item.setText("帮助");
	    dropMenu = new Menu(shell, SWT.DROP_DOWN);
	    item.setMenu(dropMenu);
	    
	    // Create Help->About
	    item = new MenuItem(dropMenu, SWT.NULL);
	    item.setText("关于\tCtrl+A");
	    item.setAccelerator(SWT.CTRL + 'A');
	    item.addSelectionListener(new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent event) {
	    	  TdcsAboutDialog dialog = new TdcsAboutDialog(shell);
	    	  dialog.open();
	      }
	    });

	}

}
