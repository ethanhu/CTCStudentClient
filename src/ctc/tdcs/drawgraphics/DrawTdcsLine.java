package ctc.tdcs.drawgraphics;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Color;

import ctc.pojobean.StationDistrictRelation;
import ctc.tdcs.Layout.TdcsLayout;
import ctc.tdcs.Util.UtilForTimeRectangle;
import ctc.tdcs.data.BaseParam;
import ctc.tdcs.elements.LineFigure;

public class DrawTdcsLine {

	private int initX = 100;
	private int initY = 100;
	private int hours = 24;
	
	private static int minuteStep = 0;
	private int hoursWidth = 2;//绘制小时刻度的线宽
	
	private TdcsLayout tdcsLayoutGraph = TdcsLayout.getInstance();
	private BaseParam tdcsDataset = BaseParam.getInstance();
	private List<StationDistrictRelation> sortedStationDistrictRelationList = new ArrayList<StationDistrictRelation>();
	private LineFigure line; 
	private int stationNum;
	
	private Color color = ColorConstants.black;
	
	public DrawTdcsLine(){
		sortedStationDistrictRelationList = BaseParam.getSortedStationDistrictRelationList();
		hours = BaseParam.getHours();
		minuteStep = BaseParam.getMinuteStep();
		initX  = UtilForTimeRectangle.getInitX();
		initY  = UtilForTimeRectangle.getInitY();
		hoursWidth = BaseParam.getHoursWidth();
		color = BaseParam.getTimeRectangleColor();
	}
	// 画车站横线
	public void startDraw(){
		if(sortedStationDistrictRelationList == null || sortedStationDistrictRelationList.size() == 0 )
			return;
	/*	
		stationNum = sortedStationDistrictRelationList.size();
		int y = initY; 
		for(int i = 0; i < stationNum; i++){
			y = y + sortedStationDistrictRelationList.get(i).getPredistance();
			line = new LineFigure_x();
			//line.setLineWidth(1);
			line.setForegroundColor(color);
			line.setEndpoints(new Point(initX, y), new Point(initX + hours*60*minuteStep,y));
			
			TdcsLayoutGraph_x.panelRight.add(line);
		}
		*/
		drawTime();
		
	//	drawCurrentTime();
	}
	
	private int sumStationDistance ;
	// 画时间竖线
	private void drawTime(){
		//区段内站的总距离
		sumStationDistance = BaseParam.getSumStationDistance(); 
		for(int sum = 0; sum <= hours*6; sum++){				
			line = new LineFigure();
			if(sum%6 == 0){
				line.setLineWidth(hoursWidth);
			}
			line.setEndpoints(new Point(initX + sum*10*minuteStep, initY),new Point(initX + sum*10*minuteStep, initY + sumStationDistance));
			//System.out.println("Line001:" + line.getEnd().x+"::"+line.getEnd().y);
			
			TdcsLayout.panelRight.add(line);
		}		
	}

}
