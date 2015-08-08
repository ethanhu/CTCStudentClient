package ctc.tdcs.ui;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.CoolItem;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import ctc.tdcs.TdcsEnvInit;
import ctc.constant.Constants;
import ctc.pojobean.Train;
import ctc.tdcs.tdcsdbserver.DatabaseServer;
import ctc.tdcs.Layout.MouseAction;
import ctc.tdcs.Layout.TdcsLayout;
import ctc.tdcs.Util.UtilForStatics;
import ctc.tdcs.data.BaseParam;
import ctc.tdcs.data.TrainPlanToTaskPlan;
import ctc.util.DateUtil;


public class TdcsToolbarFactory {
	
	private static TdcsToolbarFactory thisData = null;
	
	public static TdcsToolbarFactory getInstance(){
		if (thisData == null){
			thisData = new TdcsToolbarFactory();
		}
		return thisData;
	}
	
	TdcsEnvInit tdcsEnvInit = new TdcsEnvInit();
	
	public TdcsToolbarFactory() {}

	
	//定时器
	//记录是否选取开始按钮 true为选取
	private static boolean startFlag = false;
	
	public static boolean isStartFlag() {
		return startFlag;
	}
	
	public static void setStartFlag(boolean startFlag) {
		TdcsToolbarFactory.startFlag = startFlag;
	}

	//////////////////////////////////////////////////////////////////
	//当前时间,虚拟时间及时间步长
	private static Text timeText,virtualTimeText,timeStepText;
	private static int timeOffset = 0; //虚拟时间virtualTimeText - 服务器的当前时间 timeText = 分钟数
	
	public int getTimeOffset() {
		return timeOffset;
	}

	public void setTimeOffset(int timeOffset) {
		TdcsToolbarFactory.timeOffset = timeOffset;
	}

	//设置当前时间
	public void setCurrentTime(String timeStr){
		timeText.setText(timeStr);
	}
	//获取当前时间
	public String getCurrentTime(){
		return timeText.getText();
	}
	/*public void setTimeStep(String timeStr){
		timeStepText.setText(timeStr);
	}
	public String getTimeStep(){
		return timeStepText.getText();
	}*/
	public void setVRTime(String timeStr){
		virtualTimeText.setText(timeStr);
	}
	public String getVRTime(){
		return virtualTimeText.getText();
	}
	
	public void updateTime(int timeInc)
	{
		startFlag = false;
		
		int vtime = DateUtil.StrToTime(getVRTime());
		int ctime = DateUtil.StrToTime(getCurrentTime());
		
		//将时间值转换为小时分钟表示
		String vtimeStr = DateUtil.formatedTimeStr(DateUtil.timeToStr(vtime + timeInc));
		String ctimeStr = DateUtil.formatedTimeStr(DateUtil.timeToStr(ctime + timeInc));
		
		setCurrentTime(ctimeStr);
		setVRTime(vtimeStr);	
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////// 
	private Slider slider;
	private Combo combo;
	private Shell shell;
	
	// 创建toolbar
	public  CoolBar create(Shell shell) {
           
		this.shell = shell;
		
		//创建图片
		createImages(shell);

		CoolBar coolbar = createCoolBar(shell);
		coolbar.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		initMenu(false);
		
		return coolbar;
	}
	
	private  CoolBar createCoolBar(Shell shell) {
		CoolBar coolbar = new CoolBar(shell, SWT.NONE);
		// Create toolbar coolitem
		final CoolItem item = new CoolItem(coolbar, SWT.DROP_DOWN);
		item.setControl(createToolBar(coolbar));
		calcSize(item);

		// Add a listener to handle clicks on the chevron button
		item.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				calcSize(item);
			}
		});

		// Create combo coolitem
		CoolItem item2 = new CoolItem(coolbar, SWT.NONE);
		item2.setControl(createCombo(coolbar));
		calcSize(item2);

		// Create a slider coolitem
		item2 = new CoolItem(coolbar, SWT.NONE);
		item2.setControl(createStackedSliders(coolbar));
		calcSize(item2);

		/**xbm2010-4-20添加判断条件*/
		if(! tdcsEnvInit.isRoleFlag()){//对于教师，标记为true，不显示以下内容
			//当前时间和虚拟时间提示
			item2 = new CoolItem(coolbar, SWT.NONE);
			item2.setControl(createTimeText(coolbar));
			calcSize(item2);
		}
		//时间步长
		/*item2 = new CoolItem(coolbar, SWT.NONE);
		item2.setControl(createTimeStepText(coolbar));
		calcSize(item2);
		*/
		// Create button coolitem
		item2 = new CoolItem(coolbar, SWT.NONE);
		item2.setControl(createExitButtons(coolbar));
		calcSize(item2);
		
		return coolbar;
	}
		
	private Control createTimeText(Composite composite) {
		Composite c = new Composite(composite, SWT.NONE);
		c.setLayout(new GridLayout(2, false));
		Label label = new Label(c, SWT.NONE);
		label.setText("虚拟时间:");
		virtualTimeText = new Text(c, SWT.READ_ONLY);//SWT.BORDER ||SWT.PUSH  SWT.BORDER|
		virtualTimeText.setText("00:00");//执行绘图功能
		
		label = new Label(c, SWT.NONE);
		label.setText("当前时间:");
		timeText = new Text(c, SWT.READ_ONLY);//SWT.BORDER ||SWT.PUSH  SWT.BORDER|
		timeText.setBackground(ColorConstants.lightGreen);
		//timeText.setSize(250, 5);
		timeText.setText("00:00");//执行绘图功能
		return c;
	}
/*	private  Control createTimeStepText(Composite composite) {
		Composite c = new Composite(composite, SWT.NONE);
		c.setLayout(new GridLayout(2, false));
		Label label = new Label(c, SWT.NONE);
		label.setText("时间步长:");
		timeStepText = new Text(c, SWT.READ_ONLY);//SWT.BORDER ||SWT.PUSH  SWT.BORDER|
		timeStepText.setText("00");//执行绘图功能
		
		return c;
	}
	*/
	private  Control createExitButtons(Composite composite) {
		Composite c = new Composite(composite, SWT.NONE);
		c.setLayout(new GridLayout(1, false));
		Button button = new Button(c, SWT.PUSH);
		button.setText("退出");//执行绘图功能  
		button.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e){
				//组内TDCS发送关闭实验，应该是
	        	new ctc.tdcs.tdcsdbserver.DatabaseServer().TDCSCommandForStop();
				//shell.dispose();
			}
		});
		return c;
	}
	private  Control createStackedSliders(Composite composite) {
		Composite c = new Composite(composite, SWT.NONE);
		c.setLayout(new GridLayout(1, false));
		slider = new Slider(c, SWT.NONE);
		slider.setMinimum(1); //最小值
		slider.setMaximum(50);//最大值
		slider.setIncrement(1);//滚动间隔值
		slider.setPageIncrement(1);//翻页间隔值
		slider.setSelection(25);//初始值
		slider.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e){
				TdcsLayout.getInstance().zoomInOut(slider.getSelection());
			}
		});
		/*final Scale scale =  new Scale(c, SWT.HORIZONTAL);
		scale.setBounds(0,0,1,1200 );
		//设置Scale的最大值
		scale.setMaximum(50);
		//设置Scale的最小值
		scale.setMinimum(10);
		//设置Scale的增量值
		scale.setPageIncrement(2);
		//添加Scale的事件监听器
		scale.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event ) {
				int perspectiveValue = scale.getMaximum() - scale.getSelection() +	scale.getMinimum();
				TdcsLayoutGraph.getInstance().zoomInOut(perspectiveValue);
			}
		});*/
		return c;
	}

	private  Control createCombo(final Composite composite) {
		Composite c = new Composite(composite,SWT.NONE);
		c.setLayout(new GridLayout(3, false));
		Label label = new Label(c,SWT.NONE);
		label.setText("区段");
		combo = new Combo(c, SWT.DROP_DOWN);
		
		/**xbm2010-4-20添加判断条件*/
		if( tdcsEnvInit.isRoleFlag()){//对于教师，标记为true，需要从数据库获取所有已经定义好的区段名称信息
			//从库中获取所有区段信息
			String [] items = BaseParam.getAllDistrictName();
			if(items == null || items.length == 0){
				combo.add("");
			}else
			{
				for (int i = 0; i < items.length; i++)
					combo.add(items[i]);
			}
		}else//组内的TDCS
		{
			combo.add(tdcsEnvInit.getDistrictName() );
		}

		
		combo.addSelectionListener(new SelectionAdapter() { 
			public void widgetSelected(SelectionEvent e) {  
				BaseParam.setCurrentDistrictName(((Combo)e.widget).getText());  
			}  
		});  

		Button button = new Button(c, SWT.PUSH);
		button.setText("显示");//执行绘图功能
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				if(combo.getText().length() != 0){
					TdcsLayout.getInstance().drawTdcs();
					slider.setSelection(25);//初始值
					
					
					/**xbm2010-4-20添加判断条件 */
					if(! tdcsEnvInit.isRoleFlag()){//组内TDCS
						String timeStr = "24:00";//DateUtil.getCurrentTimeString();
						setVRTime(timeStr);
						setCurrentTime(timeStr);
						BaseParam.setTimeStep(0);
						initZNTDCS();
					}
					else//教师标记为true
					{
						initTDCS();
					}
					
				}
			}
		});
		return c;
	}
	private  void calcSize(CoolItem item) {
		Control control = item.getControl();
		Point pt = control.computeSize(SWT.DEFAULT, SWT.DEFAULT);
		pt = item.computeSize(pt.x, pt.y);
		item.setSize(pt);
	}
	
    //图形按钮
	private  final String IMAGE_PATH = System.getProperty("user.dir")+ "/resources/images/";
	
	//要在toolbar buttons中放置的图片
	private Image SAVE,ADJUST,PARALLEL,DELETE,ADD,STATICS,RUN,SCHEDULE,ERROR,STOP;
	private static ToolItem saveItem,adjustItem,parallelItem,deleteItem,addItem,runItem,staticsItem,scheduleItem,errorItem,stopItem;
	

	private  Control createToolBar(Composite composite) {
		ToolBar toolBar = new ToolBar(composite, SWT.NONE);
		//保存
		saveItem = createItemHelper(toolBar,SAVE, "保存信息到数据库");
		saveItem.setText("保存");
		saveItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
			//	ToolItem item = (ToolItem) event.widget;
			//	if (item.getSelection())//选中
			//	{
					BaseParam.setOperationType(Constants.TDCS_MENU_TOOL_NO);
					new UtilForStatics(true).saveToDB();
					
				//}
			}
		});
		
		adjustItem = createItemHelper(toolBar,ADJUST, "到站或离站时间调整");
		adjustItem.setText("移线");
		adjustItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
			//	ToolItem item = (ToolItem) event.widget;
				//if (item.getSelection())//选中
				//{
					BaseParam.setOperationType(Constants.TDCS_MENU_TOOL_RECTANGLE_ADJUST);
				//}
				
			}
		});
		
		/*parallelItem = createItemHelper(toolBar,PARALLEL, "对车次进行平移");
		parallelItem.setText("平移");
		parallelItem.setEnabled(false);
		parallelItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				ToolItem item = (ToolItem) event.widget;
				if (item.getSelection())//选中
				{
					BaseParam_x.setOperationType(Constants.TDCS_MENU_TOOL_LINE_PARALLEL);
				}
				
			}
		});
		*/
		
		deleteItem = createItemHelper(toolBar,DELETE, "删除车次");
		deleteItem.setText("删线");
		deleteItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
			//	ToolItem item = (ToolItem) event.widget;
				///if (item.getSelection())//选中
			//	{
					BaseParam.setOperationType(Constants.TDCS_TRAIN_TYPE_DELETE);
			//	}
				
			}
		});
		
		addItem = createItemHelper(toolBar,ADD, "添加车次");
		addItem.setText("加开");
		addItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				//ToolItem item = (ToolItem) event.widget;//对于SWT.RADIO按钮使用注释掉的代码
			//	if (item.getSelection())//选中
			//	{
					TdcsInputTrainDialog dlgX = new TdcsInputTrainDialog(shell);//TdcsLayoutGraph_x.getPShell()
					Train trainInfo = dlgX.open();
					if (trainInfo != null){
						MouseAction.setTrainDirection(trainInfo.getTrain_direction());
						MouseAction.setTrainName(trainInfo.getTrain_name());
						BaseParam.setOperationType(Constants.TDCS_TRAIN_TYPE_NEW);
					}
				//}
				
			}
		});
		
		staticsItem = createItemHelper(toolBar,STATICS,"统计车次信息");
		staticsItem.setText("统计");
		staticsItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				new TdcsStaticsDialog(shell).open();//TdcsLayoutGraph_x.getPShell()
			}
		});
		/**xbm2010-4-20添加判断条件*/
		if(! tdcsEnvInit.isRoleFlag()){//对于教师，标记为true，不显示以下内容
			runItem = createItemHelper(toolBar,RUN, "启动综合实验");
			runItem.setText("开始");
			runItem.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent event) {
					new TdcsRunCommandDialog(shell).open();
				}
			});

			stopItem = createItemHelper(toolBar,STOP, "关闭实验器");
			stopItem.setText("停止");
			stopItem.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent event) {
					//stop2run();
					BaseParam.setDrawCurrentTimeXFlag(false);
					new DatabaseServer().TDCSCommandForStop();

				}
			});

			scheduleItem = createItemHelper(toolBar,SCHEDULE, "对计划进行一次变更后需要选取此命令发向组内成员");
			scheduleItem.setText("发送计划变更命令");
			scheduleItem.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent event) {
					DatabaseServer.getInstance().TDCSCommandForTrain();//发送改动后的车次信息到服务器
					//BaseParam.clearTdcsTrainList();//清空原有车次信息
				}
			});

			errorItem = createItemHelper(toolBar,ERROR, "目前还没有实现");
			errorItem.setText("故障注入");
			errorItem.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent event) {

				}
			});
		}
		/*item = new ToolItem(toolBar, SWT.PUSH);
		item.setImage(SAVE);
		*/
		return toolBar;
	}
	
	
	private  ToolItem createItemHelper(ToolBar toolbar, Image image, String text) {
		ToolItem item = new ToolItem(toolbar,SWT.PUSH);//SWT.RADIO
		if (image == null) {
			item.setText(text);
		} else {
			item.setImage(image);
			item.setToolTipText(text);
		}
		return item;
	}
	// 添加图片到coolbar buttons
	private  void createImages(Composite composite) {

		if (SAVE == null) {
			Display display = composite.getDisplay();
			SAVE = new Image(display, (IMAGE_PATH + "save.png"));
			ADJUST = new Image(display, (IMAGE_PATH + "adjust.png"));
			PARALLEL = new Image(display, (IMAGE_PATH + "parallel.jpg"));
			DELETE = new Image(display, (IMAGE_PATH + "delete.png"));
			ADD = new Image(display, (IMAGE_PATH + "add.png"));
			STATICS = new Image(display, (IMAGE_PATH + "statics.png"));
			RUN = new Image(display, (IMAGE_PATH + "run.jpg"));
			STOP = new Image(display, (IMAGE_PATH + "stop.jpg"));
			SCHEDULE = new Image(display, (IMAGE_PATH + "schedule.jpg"));
			ERROR = new Image(display, (IMAGE_PATH + "error.png"));
		}
	}
	
	
	//启动界面时菜单的显示
	private void initMenu(boolean flag){
		saveItem.setEnabled(flag);
		adjustItem.setEnabled(flag);
		deleteItem.setEnabled(flag);
		addItem.setEnabled(flag);
		staticsItem.setEnabled(flag);

		if(! tdcsEnvInit.isRoleFlag()){//教师标记为true
			scheduleItem.setEnabled(flag);
			errorItem.setEnabled(flag);
			stopItem.setEnabled(false);
			runItem.setEnabled(flag);
		}
	}

	
	//教师TDCS选取显示按钮后
	private void initTDCS(){
		saveItem.setEnabled(true);
		adjustItem.setEnabled(true);
		deleteItem.setEnabled(true);
		addItem.setEnabled(true);
		staticsItem.setEnabled(true);

	//	scheduleItem.setEnabled(false);
	//	errorItem.setEnabled(false);
	//	stopItem.setEnabled(false);
		//runItem.setEnabled(false);

	}
	
	//组内TDCS选取显示按钮后，组内TDCS所要显示或屏蔽掉的菜单项
	private void initZNTDCS(){
		saveItem.setEnabled(false);//保存不显示
		adjustItem.setEnabled(false);//移线
		deleteItem.setEnabled(false);//删除线
		addItem.setEnabled(false);//加开
		staticsItem.setEnabled(true);//统计
		runItem.setEnabled(true);//开始
		stopItem.setEnabled(false);//停止
		scheduleItem.setEnabled(false);//发送调度命令
		errorItem.setEnabled(false);//故障
	}

	//当组内TDCS选取启动后的操作
	public void run2stop(){

		if(! tdcsEnvInit.isRoleFlag())//教师标记为true
		{
			adjustItem.setEnabled(true);//移线
			deleteItem.setEnabled(true);//删除线
			addItem.setEnabled(true);//加开
			runItem.setEnabled(false);//开始
			stopItem.setEnabled(true);//停止
			scheduleItem.setEnabled(true);//发送调度命令

			startFlag = true;
		}
	}
    //当TDCS选取停止，系统退出了，所以此代码段目前没有用
	private void stop2run(){
		/**xbm2010-4-20添加判断条件*/
		if(! tdcsEnvInit.isRoleFlag()){//对于教师，标记为true，不显示以下内容
			saveItem.setEnabled(true);
			stopItem.setEnabled(false);
			startFlag = false;
			runItem.setEnabled(true);
		}
	}
	
}


