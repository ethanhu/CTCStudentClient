package ctc.tdcs.elements;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.swt.graphics.Color;

import ctc.tdcs.Util.UtilForTimeRectangle;
import ctc.tdcs.data.BaseParam;
import ctc.util.DateUtil;

public class TimeRectangleFigure extends RectangleFigure {//

	private String stationName; //车站名
	private int offsetY; //屏幕上Y轴的值
	private int minuteNo; //分钟序号 直接 从数据库得到的的车次到站或离站的时间转换为分钟表示的序号
	private int currentTime;//移动过程中最新的时间值  分钟*minuteStep
	
	private Color color = ColorConstants.white;
	
	public TimeRectangleFigure(){
		color = BaseParam.getTimeRectangleColor();
		setForegroundColor(color);//ColorConstants.lightGreen
		setBackgroundColor(color);//ColorConstants.white
		//setVisible(false);//屏幕不显示
		//setVisible(true);//屏幕显示
	}
	
	public int getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(int currentTime) {
		this.currentTime = currentTime;
	}

	public Label getTimeLabel() {
		String timeStr = DateUtil.timeToStr(UtilForTimeRectangle.XToStr(getCurrentTime()));
		Label timeLabel = new Label(timeStr);
		return timeLabel;
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

}
