package ctc.sics;

import java.awt.Dimension;
import java.awt.Toolkit;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;

import ctc.sics.db.DataBase;
import ctc.sics.ui.*;
import ctc.transport.MinaClient;

public class SicsMain {

	final String IMAGE_PATH = System.getProperty("user.dir") + "/resources/images/ctc.png";
	// public static final Display display;// 声明一个Display对象
	public static Shell shell;
	public static SicsMain app;
	public SicsMainWindow mainWindow;
	public MinaClient minaClient;
	public static DataBase db;
	public static String stationName = "";

	public SicsMain() {
	}

	public SicsMain(MinaClient newMinaClient, DataBase newDb) {
		minaClient = newMinaClient;
		db = newDb;
		// System.out.println("DB跳转:" + db);
	}

	public void run() {
		final Display display = Display.getDefault();

		// 定制窗口右上角的按钮
		shell = new Shell(display);
		Image TRAY = new Image(shell.getDisplay(), IMAGE_PATH);
		shell.setImage(TRAY);

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		shell.setSize(screen.width, screen.height - 70);

		shell.setLocation((screen.width - shell.getBounds().width) / 2, 0);
		//shell.setText(stationName);
		shell.setText("分散自律调度集中仿真软件-学生客户端");
		
		shell.setBackground(ColorConstants.white);

		mainWindow = new SicsMainWindow(shell, minaClient, db); // 应用主窗口

		shell.open(); // 显示主窗口

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();// If no more entries in event queue

		}
		display.dispose();
	}

	/**
	 * Gets the main window
	 * 
	 * @return PasswordMainWindow
	 */
	public SicsMainWindow getMainWindow() {
		return mainWindow;
	}

	public void about() {
		SicsAboutDialog dialog = new SicsAboutDialog(shell);
		dialog.open();
	}

	public void closeWindow() {
		if (db != null) {
			db.closeMinaClient();
		}
		mainWindow.getSystemTray().trayDispose();// 释放托盘及其相关资源
		shell.dispose();
		System.exit(1);// 退出主程序
	}

	public static void start(String title) {
		stationName = title;
		app = new SicsMain();
		app.run();
	}

	public static SicsMain getApp() {
		return app;
	}

	public static void main(String[] args) {
		app = new SicsMain();
		app.run();
	}

	public static Shell getShell() {
		return shell;
	}

	public static void setShell(Shell shell) {
		SicsMain.shell = shell;
	}

	public static DataBase getDb() {
		return db;
	}

	public static void setDb(DataBase db) {
		SicsMain.db = db;
	}

	public static String getStationName() {
		return stationName;
	}

	public static void setStationName(String stationName) {
		SicsMain.stationName = stationName;
	}

	public static void setApp(SicsMain app) {
		SicsMain.app = app;
	}

}
