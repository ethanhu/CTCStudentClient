package ctc.tdcs.drawgraphics;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import ctc.pojobean.StationDistrictRelation;
import ctc.tdcs.Layout.MouseAction;
import ctc.tdcs.Layout.TdcsLayout;
import ctc.tdcs.Util.UtilForTimeRectangle;
import ctc.tdcs.data.BaseParam;
import ctc.tdcs.elements.LineAnchorFigure;
import ctc.tdcs.elements.TimeRectangleFigure;


//绘制时间矩形 以时间矩形的中心点为直线的起点和终点
public class DrawTimeRectangle {
	
	private int hours = 24;
	
	private int stationNum;
	private List<StationDistrictRelation> sortedStationDistrictRelationList = new ArrayList<StationDistrictRelation>();
	
	TdcsLayout tdcsLayoutGraph = TdcsLayout.getInstance();
	BaseParam tdcsDataset = BaseParam.getInstance();
	
	public DrawTimeRectangle(){ 
		sortedStationDistrictRelationList = BaseParam.getSortedStationDistrictRelationList();
		hours = BaseParam.getHours();
	}
	
	public void drawRectangle(){
	
		if(sortedStationDistrictRelationList == null || sortedStationDistrictRelationList.size() == 0 )
			return;
		
		stationNum = sortedStationDistrictRelationList.size();
		
		int locationX = UtilForTimeRectangle.getTimeRectangleLocationX();
		int initY = UtilForTimeRectangle.getInitY();
		int minuteStep = UtilForTimeRectangle.getMinuteStep();
		
		for(int i = 0; i < stationNum; i++){
			
			initY = initY + sortedStationDistrictRelationList.get(i).getPredistance();
			
			// 绘制第y个车站横线上的时间矩形 (x为分钟数)
			for(int x = 0; x <= hours * 60; x++){
				
				TimeRectangleFigure timeRectangle = new TimeRectangleFigure();
			
				timeRectangle.setMinuteNo(x);
				timeRectangle.setOffsetY(initY);
				timeRectangle.setStationName(sortedStationDistrictRelationList.get(i).getStation_name());
				// 确定矩形的位置和大小
				//timeRectangle.setLocation(new Point(locationX + x*minuteStep, initY - minuteStep));
				//timeRectangle.setSize(minuteStep*2, minuteStep*2);//宽度 高度minuteStep*2
				timeRectangle.setLocation(new Point(locationX + x*minuteStep - minuteStep/2, initY - minuteStep/2));
				timeRectangle.setSize(minuteStep, minuteStep);
		
				timeRectangle.setCurrentTime(locationX + x*minuteStep);
				timeRectangle.setToolTip(timeRectangle.getTimeLabel());
				//添加一个车站的时间矩形
				BaseParam.appendTimeRectangleList(timeRectangle);
				//负责处理车站直线上操作事件
				new MouseAction(timeRectangle,false);
				TdcsLayout.panelRight.add(timeRectangle);
				
				//以上是显示时间轴， 以下是用于调整车次时间等信息
				
				LineAnchorFigure lineAnchor = new LineAnchorFigure();
				
				lineAnchor.setMinuteNo(x);
				lineAnchor.setOffsetY(initY);
				lineAnchor.setStationName(sortedStationDistrictRelationList.get(i).getStation_name());
				lineAnchor.setCurrentTime(locationX + x*minuteStep);
				lineAnchor.setToolTip(timeRectangle.getTimeLabel());
				lineAnchor.setLocation(new Point(locationX + x*minuteStep - minuteStep/2, initY - minuteStep/2));
				lineAnchor.setSize(minuteStep, minuteStep);
				BaseParam.appendLineAnchorList(lineAnchor);
			}
		}	
	}	

	
}
