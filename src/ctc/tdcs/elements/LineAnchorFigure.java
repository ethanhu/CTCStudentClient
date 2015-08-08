package ctc.tdcs.elements;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.swt.graphics.Color;

import ctc.tdcs.Util.UtilForTimeRectangle;
import ctc.tdcs.data.BaseParam;
import ctc.util.DateUtil;

public class LineAnchorFigure extends RectangleFigure {//

	private static Color classColor = new Color(null,255,255,206);

	private String trainName; //车次
	private String stationName; //车站名
	private int offsetY; //屏幕上Y轴的值
	private int minuteNo; //分钟序号 直接 从数据库得到的的车次到站或离站的时间转换为分钟表示的序号
	private int currentTime;//移动过程中最新的时间值  分钟*minuteStep  DrawPlanTrain设置的即为次格式：如117分钟
	private int oldTime;//原始的时间值  分钟*minuteStep
	private int trainDirection;//车次方向（上行0和下行1）
	private int timeType;//时间类别 到站/离站等
 
	private int TrainType;//车次类别，新加的，从数据库读的等  对原计划车次进行调整 ...

	public LineAnchorFigure() 
	{
		ToolbarLayout layout = new ToolbarLayout();
		setLayoutManager(layout);	
		setBorder(new LineBorder(ColorConstants.black,1));
		setBackgroundColor(classColor);
		setSize(BaseParam.getMinuteStep(),BaseParam.getMinuteStep());
		setOpaque(true);
	}
	
	public int getTrainType() {
		return TrainType;
	}

	public void setTrainType(int trainType) {
		TrainType = trainType;
	}
	public Label getTimeLabel() {
		String timeStr = DateUtil.timeToStr(UtilForTimeRectangle.XToStr(getCurrentTime()));
		Label timeLabel = new Label(timeStr);
		return timeLabel;
	}
	
	public int getTimeType() {
		return timeType;
	}

	public void setTimeType(int timeType) {
		this.timeType = timeType;
	}

	public int getTrainDirection() {
		return trainDirection;
	}

	public void setTrainDirection(int trainDirection) {
		this.trainDirection = trainDirection;
	}

	public int getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(int currentTime) {
		this.currentTime = currentTime;
	}

	public int getOldTime() {
		return oldTime;
	}

	public void setOldTime(int oldTime) {
		this.oldTime = oldTime;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public int getOffsetY() {
		return offsetY;
	}

	public void setOffsetY(int offsetY) {
		this.offsetY = offsetY;
	}

	public int getMinuteNo() {
		return minuteNo;
	}
	public void setMinuteNo(int minuteNo) {
		this.minuteNo = minuteNo;
	}


	public String getTrainName() {
		return trainName;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

}
