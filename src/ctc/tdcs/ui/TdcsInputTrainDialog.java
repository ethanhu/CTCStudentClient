package ctc.tdcs.ui;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import ctc.pojobean.Train;
import ctc.tdcs.data.BaseParam;

public class TdcsInputTrainDialog extends Dialog {

	private Train trainInfo;// = new Train();

	private static Map<String,Integer> newTrainNameDirectionMap = new HashMap<String,Integer>();//新输入的车次信息
    private Shell shell;
    
    public TdcsInputTrainDialog(Shell shell) {
		super(shell, SWT.SYSTEM_MODAL | SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		this.shell = shell;
		
	}
	
	private void showMsg(String str){
		MessageBox mb = new MessageBox(shell, SWT.ABORT | SWT.ICON_INFORMATION);
		mb.setText("提示信息");//消息框的标题
		mb.setMessage(str);//消息框的提示文字
		mb.open();
	}

	// 打开对话框
	public Train open() {
		
		Display display = getParent().getDisplay();

		// 创建一个对话框窗口
		Shell shell = new Shell(getParent(), getStyle());

		shell.setText("车次设置");
		createContents(shell);
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
		return trainInfo;
	}

	// 创建对话框内容
	private void createContents(final Shell shell) {

		// 建立一个默认的GridLayout布局
		GridLayout gridLayout = new GridLayout();
		gridLayout.marginRight = 60;
		gridLayout.numColumns = 2;// 列数目
		gridLayout.makeColumnsEqualWidth = true; // 强制全部的列拥有相同的宽度
		gridLayout.horizontalSpacing = 10;// 控制一行中两个网格间组件的宽度,像素为单位
		gridLayout.verticalSpacing = 20;// 一列中两个网络间组件的宽度,像素为单位
		gridLayout.marginHeight = 20;// 控制顶部和底部组件离边缘的距离空间,以像素为单位.
		gridLayout.marginWidth = 20;// 控制左边和右边组件离边缘的距离空间,以像素为单位.

		// 为Shell设置布局对象
		shell.setLayout(gridLayout);

		// 为shell生成一个背景色
	//	final Color bkColor = new Color(Display.getCurrent(), 200, 110, 100);
		//shell.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_LIST_BACKGROUND));

		Label label = new Label(shell, SWT.NONE);
		// 创建默认GridData对象.
		GridData data = new GridData(SWT.CENTER, SWT.CENTER, true, false);
		label.setLayoutData(data);
		label.setText("车次:");
		final Text trainName = new Text(shell, SWT.BORDER);
		trainName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label label2 = new Label(shell, SWT.NONE);
		label2.setLayoutData(data);
		label2.setText("方向:");
		final Combo direction = new Combo(shell,SWT.READ_ONLY);
		String [] items = new String[]{"上行","下行"};//上行0和下行1
		direction.setItems(items);
		direction.select(1);
		direction.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		
		// Create the OK button
		final Button ok = new Button(shell, SWT.RIGHT);
		ok.setAlignment(SWT.RIGHT);
		ok.setText("确定");
		ok.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));
		ok.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				//设置车次
				String name = trainName.getText().trim();
				int trainDirection = direction.getSelectionIndex();
				if(name.length() == 0)
				{
					showMsg("请输入车次信息!");
					return;
				}
				else
				if (! judgeExist(name, trainDirection) )
				{
					trainInfo = new Train();
					trainInfo.setTrain_name(name);
					trainInfo.setTrain_direction(trainDirection);
					newTrainNameDirectionMap.put(name,trainDirection);
					//bkColor.dispose();
					shell.close();
				}
				else
				{
					showMsg("车次定义有冲突!");
					return;
				}
			}
		});

		// Create the Cancel button
		final Button cancel = new Button(shell, SWT.PUSH);
		cancel.setAlignment(SWT.RIGHT);
		cancel.setText("取消");
		cancel.setLayoutData(new GridData());

		// 当主窗口关闭时，会触发DisposeListener。这里用来释放Panel的背景色。
		shell.addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				//bkColor.dispose();
				shell.close();
			}
		});

		cancel.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				trainInfo = null;
			//	bkColor.dispose();
				shell.close();
			}
		});

		// Allow user to press Enter to accept and close
		shell.setDefaultButton(ok);
	}
	
    //true 有冲突， false没有冲突
	private boolean judgeExist(String name,Integer trainDirection){
		boolean resultFlag = true;
		
		for(Object key : BaseParam.getTrainNameDirectionMap().keySet()){
		       if (((String)key).equalsIgnoreCase(name))
		       {
		    	   Integer value = BaseParam.getTrainNameDirectionMap().get(key);
		    	   if (value.compareTo(trainDirection) != 0)//车次方向不同
		    		   return resultFlag;	   
		       }
		 }
		//System.out.println("newTrainNameDirectionMap::"+newTrainNameDirectionMap.size());
		for(Object key : newTrainNameDirectionMap.keySet()){
		       if (((String)key).equalsIgnoreCase(name))
		       {
		    	   Integer value = newTrainNameDirectionMap.get(key);
		    	   if (value.compareTo(trainDirection) != 0)//车次方向不同
		    		   return resultFlag;	   
		       }
		 }
		
		return !resultFlag;
	}
	
	public static void deleteTrain(String trainName){
		if (BaseParam.getTrainNameDirectionMap().containsKey(trainName))
			BaseParam.getTrainNameDirectionMap().remove(trainName);
		if (newTrainNameDirectionMap.containsKey(trainName))
			newTrainNameDirectionMap.remove(trainName);
	}
}
