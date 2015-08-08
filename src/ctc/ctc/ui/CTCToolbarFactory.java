package ctc.ctc.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.*;

/**
 * This class contains the toolbar for the Password application
 */
public class CTCToolbarFactory {

	private static final String IMAGE_PATH = System.getProperty("user.dir")+"/resources/images/";
	
//	private static final String IMAGE_PATH = System.getProperty("user.dir") + "/CTCStudentClient/resources/images/";//HU

	//要在toolbar buttons中放置的图片
	private static Image SAVE;
	private static Image VIEWDATA;
	private static Image PRINT;
	private static Image ADD;
	private static Image DELETE;
	private static Image UNDO;
	private static Image ZOOMIN;
	private static Image ZOOMOUT;	
	private static Image HELP;

	public CTCToolbarFactory(){}
	//创建toolbar
	public static ToolBar create(Composite composite) {

		// coolbar buttons创建一个存放图片
		createImages(composite);
		// 创建工具栏
		ToolBar toolbar = new ToolBar(composite, SWT.HORIZONTAL);
		//toolbar.setLayoutData(gridData);
		createItems(toolbar);
		return toolbar;
	}

	// 创建items
	private static void createItems(ToolBar toolbar) {

		// 保存
		ToolItem item = createItemHelper(toolbar, SAVE, "保存列车调度信息");
		item.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				//TdcsMain.getApp().saveTdcsInfo();
			}
		});

		// 显示
		item = createItemHelper(toolbar, VIEWDATA, "显示列车时刻表");
		item.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				//TdcsMain.getApp().viewTdcsInfo();
			}
		});

		// 打印
		item = createItemHelper(toolbar, PRINT, "打印列车时刻表");
		item.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				//TdcsMain.getApp().printTdcsInfo();
			}
		});

		// 添加
		item = createItemHelper(toolbar, ADD, "添加列车调度信息");
		item.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				//DrawInit.addNewTrain();
			}
		});

		// 删除
		item = createItemHelper(toolbar, DELETE, "删除列车调度信息");
		item.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				//TdcsMain.getApp().deleteTdcsInfo();
			}
		});

		// 撤销
		item = createItemHelper(toolbar, UNDO, "撤销上一步操作");
		item.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				//TdcsMain.getApp().undoPreStep();
			}
		});

		// 放大
		item = createItemHelper(toolbar, ZOOMIN, "放大视图");
		item.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				//TdcsMain.getApp().zoomIn();
			}
		});

		// 缩小
		item = createItemHelper(toolbar, ZOOMOUT, "缩小视图");
		item.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				//TdcsMain.getApp().zoomOut();
			}
		});

		// 帮助
		item = createItemHelper(toolbar, HELP, "帮助");
		item.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				//TdcsMain.getApp().help();
			}
		});


	}

	/**
	 * Helper method to create a toolbar item
	 * 
	 * @param toolbar
	 *            the parent toolbar
	 * @param image
	 *            the image to use
	 * @param text
	 *            the text to use
	 * @return ToolItem
	 */
	private static ToolItem createItemHelper(ToolBar toolbar, Image image,
			String text) {

		ToolItem item = new ToolItem(toolbar, SWT.PUSH);
		if (image == null) {
			item.setText(text);
		} else {
			item.setImage(image);
			item.setToolTipText(text);
		}
		return item;
	}

	// 添加图片到coolbar buttons
	private static void createImages(Composite composite) {

		if (SAVE == null) {
			Display display = composite.getDisplay();

			SAVE = new Image(display, (IMAGE_PATH + "save.png"));
			VIEWDATA = new Image(display, (IMAGE_PATH + "viewdata.png"));
			PRINT = new Image(display, (IMAGE_PATH + "print.png"));
			ADD = new Image(display, (IMAGE_PATH + "add.png"));
			DELETE = new Image(display, (IMAGE_PATH + "delete.png"));
			UNDO = new Image(display, (IMAGE_PATH + "undo.png"));
			ZOOMIN = new Image(display, (IMAGE_PATH + "zoom_in.png"));
			ZOOMOUT = new Image(display, (IMAGE_PATH + "zoom_out.png"));
			HELP = new Image(display, (IMAGE_PATH + "help.png"));
		}
	}
}