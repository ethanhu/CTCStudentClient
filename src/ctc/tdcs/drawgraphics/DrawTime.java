package ctc.tdcs.drawgraphics;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.geometry.Point;

import ctc.tdcs.Layout.TdcsLayout;
import ctc.tdcs.Util.UtilForTimeRectangle;
import ctc.tdcs.data.BaseParam;
import ctc.tdcs.elements.LabelFigure;
import ctc.tdcs.elements.LineFigure;

public class DrawTime {

	private TdcsLayout tdcsLayoutGraph = TdcsLayout.getInstance();
	
	private int hours = 24;
	private int minuteStep = 0;
	private int initY = 75;  //初始刻度的Y坐标起点
	private int hoursWidth = 2;//绘制小时刻度的线宽
	private int width = 100;
	private int height = 20;
	private static int hoursYOffset = 10;
	private static int minutesYOffset = 5;

	public DrawTime(){
		hours = BaseParam.getHours();
		hoursWidth = BaseParam.getHoursWidth();
		width = BaseParam.getTimeNameWidth();
		height = BaseParam.getTimeNameHeight();
		hoursYOffset = BaseParam.getHoursYOffset();
		minutesYOffset = BaseParam.getMinutesYOffset();
		minuteStep = BaseParam.getMinuteStep();
		int timeNameHeight = BaseParam.getTimeNameHeight();
		initY  = BaseParam.getOriginalY()+ BaseParam.getTimeYOffset()+ hoursYOffset + timeNameHeight;
	}

	//画时间刻度
	public void startDraw(){
		
		int initX = UtilForTimeRectangle.getInitX();//初始刻度的X坐标起点
		
		//定义连线
		LineFigure line = new LineFigure();  
		
	//	int currentTime = BaseParam.getCurrentTime();
		
		// 绘制时间轴线
		
		line.setEndpoints(new Point(initX, initY), new Point(initX + hours*60*minuteStep, initY));
		tdcsLayoutGraph.panelTop.add(line);	
		
		// 绘制刻度
		for(int sum = 0; sum <= hours*60; sum++){			
			line = new LineFigure();
			if(sum%60 == 0){
				line.setLineWidth(hoursWidth);
			}
			if(sum%10 == 0){
				line.setEndpoints(new Point(initX + sum*minuteStep, initY - hoursYOffset),new Point(initX + sum*minuteStep, initY));
			}else{
				line.setEndpoints(new Point(initX + sum*minuteStep, initY - minutesYOffset),new Point(initX + sum*minuteStep, initY));
			}
			tdcsLayoutGraph.panelTop.add(line);
		}
		
		// 绘制时间
		int y = initY - 25;
		int x = initX - 10;
		for(int h = 0; h <= hours; h++){
			LabelFigure time = new LabelFigure(false,String.valueOf(h),width,height, x+ h*minuteStep*60,y);
			time.setBackgroundColor(ColorConstants.cyan);
			tdcsLayoutGraph.panelTop.add(time);
		}	
	}

	
}
