package ctc.tdcs.Layout;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

import ctc.tdcs.Util.UtilForMouseAction;
import ctc.tdcs.Util.UtilForTimeRectangle;
import ctc.tdcs.data.BaseParam;
import ctc.tdcs.drawgraphics.*;
import ctc.tdcs.tdcsdbserver.DatabaseServer;



public class TdcsLayout  {

	private static TdcsLayout thisData = null;
	public static TdcsLayout getInstance(){
		if (thisData == null){
			thisData = new TdcsLayout();
		}
		return thisData;
	}
	
	public TdcsLayout(){}
	
	BaseParam baseParam = BaseParam.getInstance();
	DatabaseServer databaseServer = new DatabaseServer().getInstance();	
		
	private static FigureCanvas cvLeft,cvTop,cvRight;
	public static ZoomContainer panelLeft,panelRight,panelTop;
	private static Shell pShell;
	private Composite leftShell,topShell,rightShell;
	private int figureWidth,figureHeight;
	private static int screenWidth,screenHeight;//父Shell的宽度和高度
	private static int screenX,screenY;////父Shell的左上角的坐标
	private static int minuteStep;
	private static int timeHeight;//时间轴的高度
	private static int leftRight;//车站名的宽度
	
    private static ScrollBar hsb, vsb;
    
	public TdcsLayout(Shell parentShell){
		
		pShell = parentShell;
			
		timeHeight  = BaseParam.gettimeHeight();
		minuteStep = BaseParam.getMinuteStep();
		leftRight = BaseParam.getLeftRight() - 15; //原来没有 -10 2010-2-3修改后没有测试
		
		screenX = pShell.getBounds().x;
		BaseParam.setOriginalX(screenX);
		screenY = pShell.getBounds().y;
		BaseParam.setOriginalX(screenY);
		screenWidth = pShell.getBounds().width - 100;
		screenHeight = pShell.getBounds().height - timeHeight - 150;
	
	}

	public void drawInit(){
		int rightX = screenY + 2*leftRight;

		//时间
		topShell = new Composite(pShell,SWT.NONE |SWT.DOUBLE_BUFFERED);
		//topShell.setLayout(new GridLayout());
		cvTop = new FigureCanvas(topShell);
		//cvTop.setBackground(ColorConstants.red);
		cvTop.setVerticalScrollBarVisibility(FigureCanvas.NEVER);
		cvTop.setHorizontalScrollBarVisibility(FigureCanvas.NEVER);
		cvTop.setBounds(rightX + 2*minuteStep,screenY,screenWidth - leftRight,timeHeight);//height
		
		
		Composite bottomShell = new  Composite(pShell,SWT.NONE);
		bottomShell.setLayout(new GridLayout(2, false));
		
		int bottomShellY = screenY + timeHeight - 10;
		
		//车站名
		leftShell = new  Composite(bottomShell,SWT.NONE |SWT.DOUBLE_BUFFERED);
		cvLeft = new FigureCanvas(leftShell);
		cvLeft.setVerticalScrollBarVisibility(FigureCanvas.NEVER);
		cvLeft.setHorizontalScrollBarVisibility(FigureCanvas.NEVER);
		cvLeft.setBounds(screenX,bottomShellY,leftRight,screenHeight - bottomShellY);
		//(x, y, width, height)
		
		//时间网格
		rightShell = new  Composite(bottomShell,SWT.NONE |SWT.DOUBLE_BUFFERED);
		cvRight = new FigureCanvas(rightShell);
		cvRight.setBackground(ColorConstants.white);
		cvRight.setBounds(rightX - leftRight, bottomShellY, screenWidth-leftRight,screenHeight - bottomShellY);
		cvRight.setVerticalScrollBarVisibility(FigureCanvas.ALWAYS);//ALWAYS
		cvRight.setHorizontalScrollBarVisibility(FigureCanvas.ALWAYS);
		
		hsb = cvRight.getHorizontalBar();
		hsb.addListener(SWT.Selection, new Listener() {
        public void handleEvent(Event event) {
        	int vSelection = hsb.getSelection();
        	cvTop.scrollToX(vSelection);
        	cvRight.scrollToX(vSelection);
        }
		});
		
		vsb = cvRight.getVerticalBar();
		vsb.addListener(SWT.Selection, new Listener() {
        public void handleEvent(Event event) {
        	int vSelection = vsb.getSelection();
        	cvRight.scrollToY(vSelection);
        	cvLeft.scrollToY(vSelection);
        }
      });
	
	}
	
	private void createPanel(){
		
		//创建应用程序中的最顶层图形 IFigure可以是图形，也可以是容器
		panelTop = new ZoomContainer();
		panelTop.setLayoutManager(new XYLayout());
		panelTop.setMinimumSize(new Dimension(5,5));
		panelTop.setPreferredSize(figureWidth, figureHeight);
		cvTop.setContents(panelTop);
		
		panelLeft = new ZoomContainer();
		panelLeft.setLayoutManager(new XYLayout());
		panelLeft.setMinimumSize(new Dimension(5,5));
		panelLeft.setPreferredSize(figureWidth, figureHeight);
		cvLeft.setContents(panelLeft);
		
		panelRight = new ZoomContainer();
		XYLayout xyLayout = new XYLayout();
		panelRight.setLayoutManager(xyLayout);
		panelRight.setMinimumSize(new Dimension(5,5));
		panelRight.setPreferredSize(figureWidth, figureHeight);
		cvRight.setContents(panelRight);

		//负责处理拖动车次线的操作
		new MouseAction(panelRight);
	}
	
	//屏幕放大或缩小
	public void zoomInOut(int value){
		float z = (value + 10) * 0.02f;
		panelLeft.setZoom(z);
		panelRight.setZoom(z);
		panelTop.setZoom(z);
	}
	
	//绘制行车调度界面
	public void drawTdcs(){

		String selectedDistrictName = BaseParam.getCurrentDistrictName();
		if(selectedDistrictName == null || selectedDistrictName.length() ==0)
			return;
		
	    //首先清除原有内容或恢复到默认值
		BaseParam.paramInit();
	
		//注意调用顺序, 不能改变   原因是从数据库中获取数据的关系决定
		databaseServer.getAllStationInfoByDistrict(selectedDistrictName);//获取给定区段内所有车站信息,并排序
		databaseServer.getAllPlanTrainInfoByDistrict(selectedDistrictName);//根据区段获得属于区段内的所有计划车次信息
		//初始化有关参数
		new UtilForTimeRectangle().initParam();
		
					
		figureWidth = BaseParam.getHours()*60*BaseParam.getMinuteStep()+ 50; //图形的宽
		figureHeight = BaseParam.getSumStationDistance();  //图形的高 
		
		createPanel();
		
		//创建应用程序中的其他图形，并放置于应用程序的顶层图形中
    	DrawTime drawTime = new DrawTime();
		drawTime.startDraw();//绘制时间刻度
		
		new DrawStationName().startDraw(); //绘制车站名称

		new DrawTimeRectangle().drawRectangle();//绘制时间矩形
		
		DrawTdcsLine drawTdcsLine = new DrawTdcsLine();
		drawTdcsLine.startDraw();//绘制车站横线和时间竖线
				
		new DrawPlanTrain().drawPlanTrain();//绘制计划的车次
		
		new UtilForMouseAction().init();//初始鼠标处理所用到的数据
		
	}
	
	//向左移动水平滚动条
	static int i = 1;
    public void setHorizontalBar(int currentTimeX,int backStep){//这里是实验取值
    	
    	int hX = currentTimeX /*+ backStep * minuteStep*/ - screenWidth/2;// timeX ++ (i++);
    	cvRight.scrollToX(hX);
    	cvTop.scrollToX(hX);
    	
    }


}



/*
 private Listener getHorizontalScrollListener(){
		return new Listener(){
			public void handleEvent(Event e){
				int currentSelection = horizontalBar.getSelection();
				//clearCanvas();
				//TdcsDataset.setTimeXOffset(currentSelection);
				//DrawTime drawTime = new DrawTime();
				//drawTime.startDraw();//绘制时间刻度
			}};
	}
	private ScrollBar horizontalBar;//水平滚动条
}

*/


/*
  private static FigureCanvas cv,cvLeft,cvTop,cvRight;
	public static ZoomContainer panel,panelLeft,panelRight,panelTop; //IFigure
	private Shell pShell;
	private Composite treeShell,trainShell,shell,leftShell,topShell,rightShell;
	private Group group;
	
    保留, 可能会用到
 	group = new Group(shell, SWT.SHADOW_IN);
		GridLayout layout = new GridLayout();
		
		group.setText("已定义区段");
		group.setLayout(layout);
	
	//LightweightSystem是gef图形系统的”shell”及”display” 
	//canvas = new LightweightSystem(cv);
	//private static LightweightSystem canvas;
	//清空画板上原有内容 使用LightweightSystem
	private void clearCanvas(){
		//IFigure对象代表图形
		/*IFigure iFigure = canvas.getRootFigure();
		ArrayList list = new ArrayList();
		list = (ArrayList) iFigure.getChildren();
		list.clear();		
	}

//树的实现部分代码 代码没有问题
		/*
		treeShell = new  Composite(group,SWT.NONE);
		final Tree tree = new Tree(treeShell, SWT.SINGLE|SWT.BORDER);//SWT.MULTI|SWT.CHECKSWT.FULL_SELECTION | 
    	tree.setLocation(x,y);
    	tree.setSize(150,height);
		TreeItem treeItem1 = new TreeItem(tree, SWT.NONE);
		treeItem1.setText("区段名");
		
		//从库中获取所有区段信息
		String [] items = TdcsDataset.getAllDistrictName();
		if(items != null && items.length != 0){
			for (int i = 0; i < items.length; i++)
			{
				TreeItem treeItem11 = new TreeItem(treeItem1,SWT.NONE);
				treeItem11.setText(items[i]);
			}
		}
		tree.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				TreeItem ti = (TreeItem) e.item;
				populateList(ti.getText());
			}

			private void populateList(String itemStr) {
				if (itemStr.contains("-")){
					TdcsDataset.setCurrentDistrictName(itemStr);
				}
				else
					TdcsDataset.setCurrentDistrictName("");
			}
		    });
*/