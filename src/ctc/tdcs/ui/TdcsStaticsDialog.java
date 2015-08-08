package ctc.tdcs.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.widgets.Button;
import org.eclipse.draw2d.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import ctc.constant.Constants;
import ctc.tdcs.Util.UtilForStatics;
import ctc.tdcs.data.BaseParam;
import ctc.tdcs.data.PlanForStatics;
import ctc.tdcs.data.TrainPlanToTaskPlan;
import ctc.tdcs.data.TrainStation;
import ctc.util.JsonUtil;

import org.eclipse.draw2d.geometry.*;


public class TdcsStaticsDialog extends Dialog {

	public TdcsStaticsDialog(Shell shell) {
		super(shell,SWT.SYSTEM_MODAL| SWT.DIALOG_TRIM |SWT.APPLICATION_MODAL);//SWT.MODELESS 
	}
	
	private Shell mainshell;
	private	Composite contentShell;
	private	Composite commandShell;
	private int width = 800;//800;
	private int height = 800;//800;
	private int x = 0;
	private int y = 0;
	private int commandHeight = 40;
	private int commandX = width /2 - 200;
	private int space = 5;
	
	private int contentY = commandHeight + 5;
	private int contentX = width/2;
	private int contentLeftX = 70;
	private int titleVSpace = 30;
	private int contentVSpace = 25;
   
	private FigureCanvas canvas;
	private IFigure panel;
	private Font font = new Font(Display.getDefault(), "Tahoma", 12, SWT.COLOR_RED);
	
	//保存排号序的车站名 同下行车次的首站到终点站的顺序一致
	private String[] sortedStationNameArray;
	private List<String> sortedStationNameList = new ArrayList<String>();
	private List<String> trainNameList = new ArrayList<String>();
	private String[] trainNameArray;
	
	private UtilForStatics utilForStatics = new UtilForStatics();
	
	private void initSet(){
		x = 0;
		y = 0;
		commandHeight = 40;
		commandX = width /2 - 200;
		space = 5;
		
		contentY = commandHeight + 5;
		contentX = width/2;
		contentLeftX = 70;
		titleVSpace = 30;
		contentVSpace = 25;
	}
	
	private void initParam(){
		//获取车站名
		sortedStationNameList = BaseParam.getSortedStationNameList();
		if(sortedStationNameList != null){
			sortedStationNameArray = sortedStationNameList.toArray(new String[sortedStationNameList.size()+ 1]);
			sortedStationNameArray[sortedStationNameArray.length - 1] = "所有车站";
		}
		
	   //获取区段内所有车次信息	
		trainNameList = BaseParam.getTrainNameList();
		if(trainNameList != null){
			trainNameArray = trainNameList.toArray(new String[trainNameList.size() + 1]);
			trainNameArray[trainNameArray.length - 1] = "所有车次";
		}
		
	}
	// 打开对话框
	public void open() {
		
		initParam();

		Display display = getParent().getDisplay();

		// 创建一个对话框窗口
		mainshell = new Shell(getParent(), getStyle());
		
		mainshell.setText("信息检索");
		
		createContents();
		
		mainshell.pack();
		mainshell.setSize(width,height);//宽高

		//使对话框窗口处于屏幕中间
		Rectangle displayBounds = display.getPrimaryMonitor().getBounds();
		Rectangle shellBounds = mainshell.getBounds();// 获取屏幕高度和宽度
		int x = displayBounds.x + (displayBounds.width - shellBounds.width) >> 1;
		int y = displayBounds.y + (displayBounds.height - shellBounds.height) >> 1;
		mainshell.setLocation(x, y);//定位窗口坐标

		// 当主窗口关闭时，会触发DisposeListener。这里用来释放Panel的背景色。
		mainshell.addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				mainshell.dispose();
			}
		});
		
		
		mainshell.open();

		while (!mainshell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		//display.dispose();
	}
	
	
	/**
	 * 统计车次信息（界面中显示结果）
	 * @param station
	 * @param train
	 * @param directionIndex
	 */
	private void showStationTrain(String station,String train,int directionIndex)
	{
		if (sortedStationNameList == null || sortedStationNameList.isEmpty())
			return;
		
		Map<TrainStation,PlanForStatics> stationTrainsMap = new HashMap<TrainStation,PlanForStatics>();
		if (station.equalsIgnoreCase("所有车站"))
			station = null;
		if (train.equalsIgnoreCase("所有车次"))
			train = null;	
					
		stationTrainsMap = utilForStatics.getStationTrainsMap(station,train,directionIndex);
				
		int stationNum = 1;
		if (station == null)
			stationNum = sortedStationNameList.size();

		int xSpache = 160;
		contentX -= 80;
		int x = contentLeftX; 
		
		for(int index = 0; index < stationNum; index++){
			String stationName = station;
			
			if (station == null)
				stationName = sortedStationNameList.get(index);
		
			contentY +=titleVSpace;
			
			//显示车站名
			org.eclipse.draw2d.Label stationNameLabel = new org.eclipse.draw2d.Label();
			stationNameLabel.setSize(80,20);
			stationNameLabel.setText(stationName);	
			stationNameLabel.setFont(font);
			stationNameLabel.setLocation(new Point(contentX,contentY));
			panel.add(stationNameLabel);
			
			contentY +=contentVSpace;
		    //显示标题	
			org.eclipse.draw2d.Label Label0 = new org.eclipse.draw2d.Label();
			Label0.setText("车次");	
			Label0.setLocation(new Point(x,contentY));
			panel.add(Label0);
			
			x +=xSpache;
			org.eclipse.draw2d.Label Label1 = new org.eclipse.draw2d.Label();
			Label1.setText("方向");	
			Label1.setLocation(new Point(x,contentY));
			panel.add(Label1);
			
			org.eclipse.draw2d.Label Label2 = new org.eclipse.draw2d.Label();
			x +=xSpache;
			Label2.setText("接车时间");	
			Label2.setLocation(new Point(x,contentY));
			panel.add(Label2);
			
			org.eclipse.draw2d.Label Label3 = new org.eclipse.draw2d.Label();
			x +=xSpache;
			Label3.setText("发车时间");	
			Label3.setLocation(new Point(x,contentY));
			panel.add(Label3);
			
			//显示具体车次内容
			if ( (stationTrainsMap != null ) && (! stationTrainsMap.isEmpty()) )
			{//if
				PlanForStatics value;
				for(TrainStation key : stationTrainsMap.keySet()){//for
					x = contentLeftX - 10; 
					if ( (key.getStationName()).equalsIgnoreCase(stationName) )
					{
						value = stationTrainsMap.get(key);
						
						//不显示已经删除的车次信息
						if ( ((value.getArriveTrainType() == Constants.TDCS_TRAIN_TYPE_DELETE)&& (value.getLeaveTrainType() == Constants.TDCS_TRAIN_TYPE_DELETE)) ||
							 ((stationName.equalsIgnoreCase(UtilForStatics.getFirstStation())) && (value.getLeaveTrainType() == Constants.TDCS_TRAIN_TYPE_DELETE)) ||
							 ((stationName.equalsIgnoreCase(UtilForStatics.getLastStation())) && (value.getArriveTrainType() == Constants.TDCS_TRAIN_TYPE_DELETE))
							)
						{
							x = contentLeftX + 10;
							continue;
						}
						
						contentY +=contentVSpace;
						
	                     
						//车次
						org.eclipse.draw2d.Label label = new org.eclipse.draw2d.Label();
						label.setText(value.getTrainName());
						label.setSize(80,30);
						label.setLocation(new Point(x,contentY));
						panel.add(label);

						//方向
						x +=xSpache;
						org.eclipse.draw2d.Label label1 = new org.eclipse.draw2d.Label();
						label.setSize(80,30);
						int direction = value.getTrainDirection();
						if (direction == 0)//上行0，下行1
							label1.setText("上行");	
						else
							label1.setText("下行");
						label1.setLocation(new Point(x,contentY));
						panel.add(label1);
						
						//接车时间
						x +=xSpache;
						org.eclipse.draw2d.Label label2 = new org.eclipse.draw2d.Label();
						label2.setSize(80,30);
						String timeStr = null;
						if (value.getArriveTrainType() != Constants.TDCS_TRAIN_TYPE_DELETE)
							timeStr = value.getArriveTime();

						label2.setText(timeStr);	
						label2.setLocation(new Point(x,contentY));
						panel.add(label2);

						//发车时间
						x +=xSpache;
						org.eclipse.draw2d.Label label3 = new org.eclipse.draw2d.Label();
						label3.setSize(80,30);
						timeStr = null;//"00:00:00";
						if( value.getLeaveTrainType() != Constants.TDCS_TRAIN_TYPE_DELETE) 
							timeStr = value.getLeaveTime();
						label3.setText(timeStr);
						label3.setLocation(new Point(x,contentY));
						panel.add(label3);
					}
				}//for
			}//if
			x = contentLeftX; 
		}///for

		//TrainPlanToTaskPlan.getInstance().getAllTrainPlanTask();		
	}
	
	
		
	private void drawCommandUI() {
		Label stationName = new Label(commandShell, 0);
		stationName.setText("车站:");
		stationName.setSize(30, 20);
		stationName.setLocation(commandX, 10);
		final Combo station = new Combo(commandShell, SWT.READ_ONLY);
		if( (sortedStationNameArray != null)&&(sortedStationNameArray.length > 0) ){
			station.setItems(sortedStationNameArray);
			station.select(sortedStationNameArray.length - 1);
		}
		station.setSize(80, 20);
		commandX = commandX + 30;
		station.setLocation(commandX, 7);

		Label trainName = new Label(commandShell, 0);
		trainName.setText("车次:");
		trainName.setSize(30, 20);
		commandX = commandX + 95;
		trainName.setLocation(commandX, 10);
		final Combo train = new Combo(commandShell, SWT.READ_ONLY);
		if( (trainNameArray != null)&&(trainNameArray.length > 0) ){
			train.setItems(trainNameArray);
			train.select(trainNameArray.length - 1);
		}
		train.setSize(80, 20);
		commandX = commandX + 30 ;
		train.setLocation(commandX, 7);

		Label directionName = new Label(commandShell, 0);
		directionName.setText("方向:");
		directionName.setSize(30, 20);
		commandX = commandX + 95;
		directionName.setLocation(commandX, 10);
		final Combo direction = new Combo(commandShell, SWT.READ_ONLY);
		direction.setItems(new String[] { "上行", "下行","双向" });//上行0，下行1
		direction.select(2);
		direction.setSize(55, 20);
		commandX = commandX + 30;
		direction.setLocation(commandX, 7);

		Button operateButton = new Button(commandShell, SWT.PUSH);
		operateButton.setText("查询");
		operateButton.setSize(30, 20);
		commandX = commandX + 80;
		operateButton.setLocation(commandX, 8);

		operateButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				String stationName = station.getText();
				String trainName = train.getText();
				int directionIndex = direction.getSelectionIndex();
				initSet();
				//clearCanvas();
				createPanel();
				showStationTrain(stationName,trainName,directionIndex);
			}
		});
	}
	
//////////////////////////////////////////////////////////
	private void createContents() {
	
		//操作部分容器
		commandShell = new Composite(mainshell,SWT.BORDER);
		commandShell.setBounds(x,y,width,commandHeight);
		drawCommandUI();
		
		//用于显示信息的容器
		
		
		contentShell = new Composite(mainshell,SWT.BORDER);//|SWT.H_SCROLL | SWT.V_SCROLL
		
		contentShell.setBounds(x + space*2,y + space,width - space*5 ,height- commandHeight - space*2);
		contentShell.setLayout(new FillLayout());
		
		canvas = new FigureCanvas(contentShell);
		//canvas.setScrollBarVisibility(FigureCanvas.NEVER);
		canvas.setBackground(ColorConstants.white);
		canvas.setBounds(x,y, width, height);
		canvas.setSize(width - space*6,height- space*12);
		
		//LightweightSystem是gef图形系统的”shell”及”display” 
	    //canvas = new LightweightSystem(cv);
		
		createPanel();
		
	}
	
	private void createPanel(){
		
		//创建应用程序中的最顶层图形 IFigure可以是图形，也可以是容器
		panel = new Figure();
		panel.setLayoutManager(new XYLayout());
		
		panel.setMinimumSize(new Dimension(5,5));
		panel.setPreferredSize(new Dimension(width - space*2,height - space*2));
		panel.setSize(width - space*2,height - space*2);
		
		canvas.setContents(panel);
	
		/*
		 ScrollPane scrollpane = new ScrollPane();
        scrollpane.setScrollBarVisibility(ScrollPane.ALWAYS);
        
        //scrollpane.setLayoutManager(new DelegatingLayout());
        scrollpane.setLayoutManager(new FlowLayout());//FlowLayout.VERTICAL
        
		scrollpane.setContents(panel);
		//scrollpane.setBorder(new SimpleLoweredBorder());

		canvas.setContents(scrollpane);  
		*/
		//将panel图形放置于LightweightSystem的RootFigure里 
		//if (canvas != null)
		//	canvas.setContents(scrollpane);
			//canvas.getRootFigure().add(panel);
		//canvas.getRootFigure().add(scrollpane);
	}
	
	//清空画板上原有内容
	/*private LightweightSystem canvas;
	private void clearCanvas(){
		//IFigure对象代表图形
		if (canvas == null)
			return;
		//RootFigure是LightweightSystem中的根图形元素
		IFigure iFigure = canvas.getRootFigure();
		ArrayList list = new ArrayList();
		if (iFigure != null)
		{
			list = (ArrayList) iFigure.getChildren();
			list.clear();
		}
	}*/
}



