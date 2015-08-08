package ctc.ctc;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;

import ctc.ctc.db.CTCDataBase;
import ctc.ctc.drawctc.InitCTC;
import ctc.pojobean.Plan;
import ctc.transport.MinaClient;

public class CTCMain {

	final String IMAGE_PATH = System.getProperty("user.dir") + "/resources/images/ctc.png";
	public static Shell shell;
	public static CTCMain app;
	public MinaClient minaClient;
	public static CTCDataBase db;
	public static String ctcTitle = "";

	public CTCMain() {
	}

	public CTCMain(MinaClient newMinaClient, CTCDataBase newDb) {
		minaClient = newMinaClient;
		db = newDb;	
	}

	public void run() {
		final Display display = Display.getDefault();

		// 定制窗口右上角的按钮
		shell = new Shell(display);
		Image TRAY = new Image(shell.getDisplay(), IMAGE_PATH);
		shell.setImage(TRAY);

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		shell.setSize(screen.width, screen.height - 30);

		shell.setLocation((screen.width - shell.getBounds().width) / 2, 0);
		//shell.setText(ctcTitle);
		shell.setText("电务维修机  " + ctcTitle);
		shell.setBackground(ColorConstants.white);

		new InitCTC(shell); // 应用主窗口

		shell.open(); // 显示主窗口

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();// If no more entries in event queue

		}
		display.dispose();
	}

	public void closeWindow() {
		if (db != null) {
			db.closeMinaClient();
		}
		shell.dispose();
		System.exit(1);// 退出主程序
	}

	public static void start(String title) {
		ctcTitle = title;
		app = new CTCMain();
		app.run();
	}

	public static CTCMain getApp() {
		return app;
	}

	public static void main(String[] args) {
		app = new CTCMain();
		app.run();
	}

	public static CTCDataBase getDb() {
		return db;
	}

	public static void setDb(CTCDataBase db) {
		CTCMain.db = db;
	}

	public static String getCtcTitle() {
		return ctcTitle;
	}

	public static void setCtcTitle(String ctcTitle) {
		CTCMain.ctcTitle = ctcTitle;
	}

	
}
