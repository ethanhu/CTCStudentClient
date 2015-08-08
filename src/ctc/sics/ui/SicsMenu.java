package ctc.sics.ui;

import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;
import ctc.sics.SicsMain;

/**
 * This class contains the menu for the Password application
 */
public class SicsMenu {
	Menu menu = null;

	public Menu getMenu() {
		return menu;
	}

	public SicsMenu(final Shell shell) {
		
		// 创建menu，BAR用于主菜单
		menu = new Menu(shell, SWT.BAR);

		//-----------------系统-----------------//
		// 创建文件一级菜单，CASCADE表示有子菜单
		MenuItem item = new MenuItem(menu, SWT.CASCADE);
		item.setText("系统(S)");
		//item.setAccelerator(SWT.ALT + 'S');
		Menu dropMenu = new Menu(shell, SWT.DROP_DOWN);
		item.setMenu(dropMenu);

		// 系统->退出
		item = new MenuItem(dropMenu, SWT.NULL);
		item.setText("退出\tAlt+F4");
		item.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				SicsMain.getApp().closeWindow();
				shell.close();
			}
		});

		//-----------------帮助-----------------//
		item = new MenuItem(menu, SWT.CASCADE);
		item.setText("帮助(H)");
		dropMenu = new Menu(shell, SWT.DROP_DOWN);
		item.setMenu(dropMenu);

		// 帮助->关于
		item = new MenuItem(dropMenu, SWT.NULL);
		item.setText("关于\tCtrl+A");
		item.setAccelerator(SWT.CTRL + 'A');
		item.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				SicsMain.getApp().about();
			}
		});
	}

}
