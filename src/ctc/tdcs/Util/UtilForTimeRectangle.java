package ctc.tdcs.Util;


import ctc.tdcs.data.BaseParam;

public class UtilForTimeRectangle {
	
	private static int initX = 100;
	private static int initY = 100;
	private static int timeRectangleLocationX;
	private static int minuteStep = 0;
	
	public UtilForTimeRectangle()	{ }
	
	public void initParam(){
		initX  = BaseParam.getOriginalX() + BaseParam.getTimeXOffset();
		initY  = BaseParam.getOriginalY()+ BaseParam.getStationNameYOffset() + BaseParam.getStationNameHeight()/2;
		minuteStep = BaseParam.getMinuteStep();
		timeRectangleLocationX = initX  - minuteStep;
		timeRectangleLocationX =0;
	}
	/*类TimeRectangleFigure中变量: minuteNo(分钟序号)代表直接 从数据库得到的的车次到站或离站的时间转换为分钟表示的序号
	对应于界面上的坐标为:类DrawTimeRectangle中(locationX + x*minuteStep, initY - minuteStep)
	locationX即为本类中的timeRectangleLocationX ,initY为本类中initY加上不同站之间的距离 ,minuteStep为本类中的minuteStep
	*/
	public static int minueNoToX(int minute){
		return minute*minuteStep + timeRectangleLocationX;	
	}
	
	public static int getInitX() {
		return initX;
	}
	public static int getInitY() {
		return initY;
	}
	
	public static void setInitX(int initX) {
		UtilForTimeRectangle.initX = initX;
	}
	public static void setInitY(int initY) {
		UtilForTimeRectangle.initY = initY;
	}
	public static int getTimeRectangleLocationX() {
		return timeRectangleLocationX;
	}
	public static void setTimeRectangleLocationX(int timeRectangleLocationX) {
		UtilForTimeRectangle.timeRectangleLocationX = timeRectangleLocationX;
	}
	public static int getMinuteStep() {
		return minuteStep;
	}
	public static void setMinuteStep(int minuteStep) {
		UtilForTimeRectangle.minuteStep = minuteStep;
	}
	
    //DrawTimeRectangle中定义时间矩阵的大小的规则是:timeRectangleLocationX = locationX
	//timeRectangle.setLocation(new Point(locationX + x*minuteStep - minuteStep/2, initY - minuteStep/2))
	
    //X为界面上移动鼠标的x值即分钟数,转换系统的时间值坐标 可以将时间：127转换为00:01:00时间表示方式
	public static int XToStr(int x){
		return (int)(x - initX)/minuteStep;
	}
	
	///*数据库中时间格式（字符串）：00：00：00 //plan.getPlan_arrivestationtime() 转换后时间格式（整数）分钟表示
	//次方法是将上述分钟表示转为整数X坐标（屏幕上的表示） 如经timeToX转换后的分钟说是20，屏幕坐标应该是117
	public static int timeToX(int x){
		return (int)(timeRectangleLocationX + x*minuteStep - minuteStep/2);
	}
	//是方法timeToX(int x)的逆方法， 如屏幕值是117，转换后是20
	public static int xToTime(int x){
		return (int)((x - timeRectangleLocationX + minuteStep/2)/minuteStep);
	}
	
}
