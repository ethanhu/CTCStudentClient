package ctc.tdcs.data;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

import ctc.constant.Constants;
import ctc.pojobean.*;
import ctc.tdcs.Util.UtilForStatics;
import ctc.tdcs.elements.LineAnchorFigure;
import ctc.tdcs.elements.LineFigure;
import ctc.tdcs.elements.TimeRectangleFigure;
import ctc.tdcs.ui.TdcsToolbarFactory;
import ctc.tdcs.TdcsEnvInit;


public class BaseParam {
	
	private static BaseParam thisData = null;
	public static BaseParam getInstance(){
		if (thisData == null){
			thisData = new BaseParam();
		}
		return thisData;
	}
	////////////////////////////////////////////////////////////////////////////////
	private static TdcsToolbarFactory tdcsToolbarFactory = TdcsToolbarFactory.getInstance();
	
	private static int timeStamp = Constants.TIMESTAMP;//60*1000;//延时x秒
	private static int timeStep = 1;//时间步长

	public static int getTimeStep() {
		return timeStep;
	}
	public static void setTimeStep(int timeStep) {
		BaseParam.timeStep = timeStep;
	}
	//系统定时器
	private static Timer timer;

	public static void closeTimer() {
		if (timer != null)
			timer.cancel();
	}
	
	public static void setTimer() {
		if (timer == null)
			timer = new Timer();

		//有关定时器的代码
		timer.schedule(new java.util.TimerTask() {
			public void run() {
				Display.getDefault().asyncExec(
						new Runnable() {
							public void run() {
								if (BaseParam.isDrawCurrentTimeXFlag())
									tdcsToolbarFactory.updateTime(timeStep);
							}
						});

			}// run
		}, 0, timeStamp);
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/*保存是排号序的车站名   按照stationdistrictrelation中所定义耳朵车站之间的关系由DatabaseServer生成
	    默认的处理是与定义在此区段内的下行车次所经过的车站之间的关系一致.是上行车次所经过的车站之间的关系逆序
	*/
	private static List<String> sortedStationNameList = new ArrayList<String>();
	
	//保存的是某一车次和车次方向
	private static Map<String,Integer> trainNameDirectionMap = new HashMap<String,Integer>();//某一车次的车次方向
		
	private static String userName = TdcsEnvInit.getUserName();//当前操作者的名称
	private static String currentDistrictName = "";//当前选中的区段名称
	
	private static String [] allDistrictName;//所有区段名称
	//区段中车站信息 已经按车站的先后排好序(首站->终点站)
	private static List<StationDistrictRelation> sortedStationDistrictRelationList = new ArrayList<StationDistrictRelation>();
	
	private static List<FromatedTrain> trainPlanFromatList = new ArrayList<FromatedTrain>();//车次信息list(绘图的格式)
	private static List<String> trainNameList = new ArrayList<String>(); //存放给定区段内所有车次的车次名称
	private static List<LineFigure> trainList = new ArrayList<LineFigure>(); //存放给定区段内所有车次的车次信息

    //设置组件的一些特性
	private static ToolbarLayout layout = new ToolbarLayout();//组件布局方式
	private static int textAlignment = PositionConstants.LEFT;//设置label中文字的对齐方式
	private static Color boardColor = ColorConstants.black;//组件边框颜色
	private static Color foregroundColor = ColorConstants.darkGreen;//组件前景默认颜色
	private static Color timeRectangleColor = ColorConstants.lightGreen;//lightGreen;
	private static Color planTrainDownColor = ColorConstants.blue;//计划车次线的默认颜色
	private static Color planTrainUpColor = ColorConstants.red;//计划车次线的默认颜色
	private static Color runTrainLineColor = ColorConstants.gray;//实际运行车次线的默认颜色
	private static Color backgroundColor = ColorConstants.white;//组件背景默认颜色
	private static int lineWidth = 1;//设置组件的默认线宽, 绘制分钟刻度,时间轴线的线宽
	
	//绘制车站名称
	private static int stationNameXOffset = 0;
	private static int stationNameYOffset = 80;
	private static int stationNameWidth = 80;
	private static int stationNameHeight = 20;
	
	private static int sumStationDistance = 0; //区段的总距离(Y)	
	
	//绘制时间刻度与绘制时间竖线所用参数
	private static int hours = 24; //小时计量方法
	private static int hoursWidth = 2;//绘制小时刻度的线宽
	private static int minuteStep = 6; //分钟之间的坐标之差（矩形的边长）
	private static int timeXOffset = 0;//时间框内偏移量
	private static int timeYOffset = 0;//时间框内偏移量
	private static int timeNameWidth = 20;
	private static int timeNameHeight = 10;
	private static int hoursYOffset = 10;
	private static int minutesYOffset = 5;
	private static int timeHeight = timeNameHeight + timeYOffset + hoursYOffset + 10;//时间框的高度
	
	//父shell左上角坐标
	private static int originalX = 2; //初始刻度的X坐标起点
	private  static int originalY = 4;  //初始刻度的Y坐标起点
	
	//左右分割线
	private  static int leftRight = 80;
	
	//表示实验是否开始 即是否开始绘制表示当前时间的红线
	private static boolean drawCurrentTimeXFlag = false;

	//所有车站的时间矩形
	private static List<TimeRectangleFigure> timeRectangleList = new ArrayList<TimeRectangleFigure>();
	private static List<LineAnchorFigure> lineAnchorList = new ArrayList<LineAnchorFigure>();	
	
	//用户所选操作类别 即当前操作的类型
	private static int operationType = Constants.TDCS_MENU_TOOL_NO;
	//用于动态更新车次信息
	private static List<TDCSPlan> tdcsTrainList = new ArrayList<TDCSPlan>();
	public static List<TDCSPlan> getTdcsTrainList() {
		return tdcsTrainList;
	}
	public static void setTdcsTrainList(List<TDCSPlan> tdcsTrainList) {
		BaseParam.tdcsTrainList = tdcsTrainList;
	}
	public static void clearTdcsTrainList() {
		BaseParam.tdcsTrainList.clear();
	}
	public static void appendTdcsTrainList(TDCSPlan tdcsTrainList) {
		BaseParam.tdcsTrainList.add(tdcsTrainList);
	}
	
	//首先清除原有内容或恢复到默认值
	public static void paramInit(){
		BaseParam.clearTrainPlanFromatList();//格式化后的车次信息，用于绘图
		UtilForStatics.clearScheduleTrainMap();//本地保存的调整后的车次信息
		BaseParam.clearTrainList();//原计划车次信息
		BaseParam.clearTrainNameList();//车站名称
		BaseParam.clearTimeRectangleList();//时间矩阵
		BaseParam.clearLineAnchorList();//
		BaseParam.clearTdcsTrainList();
		BaseParam.setOperationType(Constants.TDCS_MENU_TOOL_NO);
	}
	

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static List<String> getSortedStationNameList() {
		return sortedStationNameList;
	}
	public static boolean isDrawCurrentTimeXFlag() {
		return drawCurrentTimeXFlag;
	}
	public static void setDrawCurrentTimeXFlag(boolean drawCurrentTimeXFlag) {
		BaseParam.drawCurrentTimeXFlag = drawCurrentTimeXFlag;
	}

	public static void setSortedStationNameList(List<String> sortedStationNameList) {
		BaseParam.sortedStationNameList = sortedStationNameList;
	}

	public static List<LineAnchorFigure> getLineAnchorList() {
		return lineAnchorList;
	}
	public static void setLineAnchorList(List<LineAnchorFigure> lineAnchorList) {
		BaseParam.lineAnchorList = lineAnchorList;
	}
	public static void appendLineAnchorList(LineAnchorFigure timeRectangleList) {
		BaseParam.lineAnchorList.add(timeRectangleList);
	}
	public static void clearLineAnchorList() {
		BaseParam.lineAnchorList.clear();
	}
	
	public static Map<String, Integer> getTrainNameDirectionMap() {
		return trainNameDirectionMap;
	}
	public static void setTrainNameDirectionMap(Map<String, Integer> trainNameDirectionMap) {
		BaseParam.trainNameDirectionMap = trainNameDirectionMap;
	}
	//返回车次的方向（上行0，下行1）
	public static int getTrainDirectionByTrainName(String trainName) {
		if( (trainNameDirectionMap == null) ||
			(trainNameDirectionMap.size() ==0) ||
			(! trainNameDirectionMap.containsKey(trainName)) )
			return -1;
		else{
			return trainNameDirectionMap.get(trainName);
		}
	}

	public static Color getTimeRectangleColor() {
		return timeRectangleColor;
	}
	public static void setTimeRectangleColor(Color timeRectangleColor) {
		BaseParam.timeRectangleColor = timeRectangleColor;
	}
	public static int getOperationType() {
		return operationType;
	}
	public static void setOperationType(int operationType) {
		BaseParam.operationType = operationType;
	}

	public static Color getPlanTrainDownColor() {
		return planTrainDownColor;
	}
	public static void setPlanTrainDownColor(Color planTrainDownColor) {
		BaseParam.planTrainDownColor = planTrainDownColor;
	}
	public static Color getPlanTrainUpColor() {
		return planTrainUpColor;
	}
	public static void setPlanTrainUpColor(Color planTrainUpColor) {
		BaseParam.planTrainUpColor = planTrainUpColor;
	}
	public static String getUserName() {
		return userName;
	}
	public static void setUserName(String userName) {
		BaseParam.userName = userName;
	}

	public static Color getRunTrainLineColor() {
		return runTrainLineColor;
	}
	public static void setRunTrainLineColor(Color runTrainLineColor) {
		BaseParam.runTrainLineColor = runTrainLineColor;
	}
	public static ToolbarLayout getLayout() {
		return layout;
	}

	public static int gettimeHeight() {
		return timeHeight;
	}
	public static void settimeHeight(int timeHeight) {
		BaseParam.timeHeight = timeHeight;
	}
	public static int getLeftRight() {
		return leftRight;
	}

	public static void setLeftRight(int leftRight) {
		BaseParam.leftRight = leftRight;
	}
	public static int getSumStationDistance() {
		return sumStationDistance;
	}
	public static void setSumStationDistance(int sumStationDistance) {
		BaseParam.sumStationDistance = sumStationDistance;
	}
	
	public static int getHoursYOffset() {
		return hoursYOffset;
	}
	public static void setHoursYOffset(int hoursYOffset) {
		BaseParam.hoursYOffset = hoursYOffset;
	}
	public static int getMinutesYOffset() {
		return minutesYOffset;
	}
	public static void setMinutesYOffset(int minutesYOffset) {
		BaseParam.minutesYOffset = minutesYOffset;
	}
	public static int getTimeNameWidth() {
		return timeNameWidth;
	}
	public static void setTimeNameWidth(int timeNameWidth) {
		BaseParam.timeNameWidth = timeNameWidth;
	}
	public static int getTimeNameHeight() {
		return timeNameHeight;
	}
	public static void setTimeNameHeight(int timeNameHeight) {
		BaseParam.timeNameHeight = timeNameHeight;
	}
	public static int getStationNameWidth() {
		return stationNameWidth;
	}
	public static void setStationNameWidth(int stationNameWidth) {
		BaseParam.stationNameWidth = stationNameWidth;
	}
	public static int getStationNameHeight() {
		return stationNameHeight;
	}
	public static void setStationNameHeight(int stationNameHeight) {
		BaseParam.stationNameHeight = stationNameHeight;
	}
	public static int getStationNameXOffset() {
		return stationNameXOffset;
	}
	public static void setStationNameXOffset(int stationNameXOffset) {
		BaseParam.stationNameXOffset = stationNameXOffset;
	}
	public static int getStationNameYOffset() {
		return stationNameYOffset;
	}
	public static void setStationNameYOffset(int stationNameYOffset) {
		BaseParam.stationNameYOffset = stationNameYOffset;
	}
	public static int getTimeXOffset() {
		return timeXOffset;
	}
	public static void setTimeXOffset(int timeXOffset) {
		BaseParam.timeXOffset = timeXOffset;
	}
	public static int getTimeYOffset() {
		return timeYOffset;
	}
	public static void setTimeYOffset(int timeYOffset) {
		BaseParam.timeYOffset = timeYOffset;
	}
	public static void setLayout(ToolbarLayout layout) {
		BaseParam.layout = layout;
	}
	public static int getTextAlignment() {
		return textAlignment;
	}
	public static void setTextAlignment(int textAlignment) {
		BaseParam.textAlignment = textAlignment;
	}
	public static Color getBoardColor() {
		return boardColor;
	}
	public static void setBoardColor(Color boardColor) {
		BaseParam.boardColor = boardColor;
	}
	public static int getLineWidth() {
		return lineWidth;
	}
	public static void setLineWidth(int lineWidth) {
		BaseParam.lineWidth = lineWidth;
	}
	public static Color getForegroundColor() {
		return foregroundColor;
	}
	public static void setForegroundColor(Color foregroundColor) {
		BaseParam.foregroundColor = foregroundColor;
	}
	public static Color getBackgroundColor() {
		return backgroundColor;
	}
	public static void setBackgroundColor(Color backgroundColor) {
		BaseParam.backgroundColor = backgroundColor;
	}
	public static int getHoursWidth() {
		return hoursWidth;
	}
	public static void setHoursWidth(int hoursWidth) {
		BaseParam.hoursWidth = hoursWidth;
	}
	
	public static int getOriginalX() {
		return originalX;
	}
	public static void setOriginalX(int originalX) {
		BaseParam.originalX = originalX;
	}
	public static int getOriginalY() {
		return originalY;
	}
	public static void setOriginalY(int originalY) {
		BaseParam.originalY = originalY;
	}
	public static int getHours() {
		return hours;
	}
	public static void setHours(int hours) {
		BaseParam.hours = hours;
	}


	public static int getMinuteStep() {
		return minuteStep;
	}
	public static void setMinuteStep(int minuteStep) {
		BaseParam.minuteStep = minuteStep;
	}

	//currentDistrictName
	public static String getCurrentDistrictName() {
		return currentDistrictName;
	}
	public static void setCurrentDistrictName(String currentDistrictName) {
		BaseParam.currentDistrictName = currentDistrictName;
	}

	//allDistrictName
	public static String [] getAllDistrictName(){
		return allDistrictName;
	}
	public static void setAllDistrictName(List<District> list) {
		if (list == null)
			return;
		
		allDistrictName = new String[list.size()];

		for(int i = 0; i < list.size(); i++)
		{
			District data = list.get(i);
			allDistrictName[i] = data.getDistrict_name();
		}
	}
	
   //sortedStationDistrictRelationList	
	public static List<StationDistrictRelation> getSortedStationDistrictRelationList() {
		return sortedStationDistrictRelationList;
	}
	public static void setSortedStationDistrictRelationList(
			List<StationDistrictRelation> sortedStationDistrictRelationList) {
		BaseParam.sortedStationDistrictRelationList = sortedStationDistrictRelationList;
	}
	
	////////////////////////////////////////////////////////////////////////////////////
	public static List<FromatedTrain> getTrainPlanFromatList() {
		return trainPlanFromatList;
	}
	public static void setTrainPlanFromatList(List<FromatedTrain> trainPlanFromatList) {
		BaseParam.trainPlanFromatList = trainPlanFromatList;
	}
	public static void appendTrainPlanFromatList(FromatedTrain trainPlanFromatList) {
		BaseParam.trainPlanFromatList.add(trainPlanFromatList);
	}
	public static void clearTrainPlanFromatList() {
		BaseParam.trainPlanFromatList.clear();
	}
	
	public static List<TimeRectangleFigure> getTimeRectangleList() {
		return timeRectangleList;
	}
	public static void setTimeRectangleList(List<TimeRectangleFigure> timeRectangleList) {
		BaseParam.timeRectangleList = timeRectangleList;
	}
	public static void appendTimeRectangleList(TimeRectangleFigure timeRectangleList) {
		BaseParam.timeRectangleList.add(timeRectangleList);
	}
	public static void clearTimeRectangleList() {
		BaseParam.timeRectangleList.clear();
	}

	
	//TrainList
	public static List<LineFigure> getTrainList() {
		return trainList;
	}
	public static void setTrainList(List<LineFigure> trainList) {
		BaseParam.trainList = trainList;
	}
	public static void appendTrainList(LineFigure trainListMember) {
		BaseParam.trainList.add(trainListMember);
	}
	public static void clearTrainList() {
		BaseParam.trainList.clear();
	}
	///////////////////////////
	//trainNameList
	public static List<String> getTrainNameList() {
		return trainNameList;
	}
	public static void setTrainNameList(List<String> trainNameList) {
		BaseParam.trainNameList = trainNameList;
	}
	public static void appendTrainNameList(String trainNameList) {
		BaseParam.trainNameList.add(trainNameList);
	}
	public static void clearTrainNameList() {
		BaseParam.trainNameList.clear();
	}

}