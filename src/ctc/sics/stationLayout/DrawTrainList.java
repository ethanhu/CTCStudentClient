package ctc.sics.stationLayout;

import java.util.ArrayList;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Point;
import ctc.pojobean.Plan;

import ctc.sics.SicsMain;

import ctc.sics.figure.*;
import ctc.sics.road.*;


public class DrawTrainList {
	
	public static StationLabel lab;
	public static Label lab2;
	public static ArrayList<StationLabel> trainLabelList;
	
	public static int initX = 5;
	public static int initY = 200;
	
	public static PolylineConnection line;
	public static Figure trainPanel;
	public static int labWidth = 35;
	public static int labHeigth = 20;
	public static int stepX = labWidth + 5;
	public static int stepY = labHeigth + 5;
	
	//绘制 属于本站的车次信息
	public static void drawTrainList(){
		
		int trainNum = 10;
		
		if((SicsMain.db == null)||(SicsMain.db.getPlanInfoList() == null)){			
			return;
		}		
		trainNum = SicsMain.db.getPlanInfoList().size();
		
		//System.out.println("\n\n--SicsMain.db.getPlanInfoList().size() = " + SicsMain.db.getPlanInfoList().size());
		
		trainLabelList = new ArrayList<StationLabel>(trainNum);
		
		PolylineConnection line1 = new PolylineConnection(); 
		line1.setForegroundColor(DrawStation.trackInitColor);
		line1.setLineWidth(2);
		line1.setStart(new Point(initX - 3, initY - 3));
		line1.setEnd(new Point(stepX*3 + 5, initY - 3));
		DrawStation.panel.add(line1);
	
		lab = new StationLabel();
		lab.setText("本站信息");
		lab.setSize(60,labHeigth);
		lab.setLocation(new Point(initX + 30, initY));
		DrawStation.panel.add(lab);
		initY = initY + stepY;
		
		lab = new StationLabel();
		lab.setText("车次");
		lab.setSize(labWidth,labHeigth);
		lab.setLocation(new Point(initX, initY));
		DrawStation.panel.add(lab);
		initX = initX+ stepX;
		
		lab = new StationLabel();
		lab.setText("到站");
		lab.setSize(labWidth,labHeigth);
		lab.setLocation(new Point(initX, initY));
		DrawStation.panel.add(lab);
		initX = initX+ stepX;
		
		lab = new StationLabel();
		lab.setText("离站");
		lab.setSize(labWidth,labHeigth);
		lab.setLocation(new Point(initX, initY));
		DrawStation.panel.add(lab);
		initX = 5;
		initY = initY + stepY;
		
		for(int i = 0; i < trainNum; i++){
			
			Plan plan = SicsMain.db.getPlanInfoList().get(i);
			
			lab2 = new StationLabel();
			//lab2.setLableName(plan.getTrain_name());
			lab2.setText(plan.getTrain_name());
			lab2.setSize(labWidth,labHeigth);
			lab2.setLocation(new Point(initX, initY));
			//trainLabelList.add(lab2);
			DrawStation.panel.add(lab2);
			initX = initX+ stepX;
			
			lab = new StationLabel();
			lab.setLableName(plan.getTrain_name());
			lab.setText(plan.getPlan_arrivestationtime().substring(0,5));
			lab.setSize(labWidth,labHeigth);
			lab.setLocation(new Point(initX, initY));
			trainLabelList.add(lab);
			DrawStation.panel.add(lab);			
			initX = initX+ stepX;
			
			lab = new StationLabel();
			lab.setLableName(plan.getTrain_name());
			lab.setText(plan.getPlan_leavestationtime().substring(0,5));
			lab.setSize(labWidth,labHeigth);
			lab.setLocation(new Point(initX, initY));
			trainLabelList.add(lab);
			DrawStation.panel.add(lab);			
			initX = 5;
			initY = initY + stepY;
			
			System.out.println("车站" + plan.getStation_name() + "  到达时间：" + plan.getPlan_arrivestationtime() + "  离开时间：" + plan.getPlan_leavestationtime());
						
		}
		
		line = new PolylineConnection(); 
		line.setForegroundColor(ColorConstants.blue);
		line.setLineWidth(2);
		line.setStart(new Point(2, 197));
		line.setEnd(new Point(2, initY + 10));
		DrawStation.panel.add(line);
		
		line = new PolylineConnection(); 
		line.setForegroundColor(ColorConstants.blue);
		line.setLineWidth(2);
		line.setStart(new Point(stepX*3 + 5, 197));
		line.setEnd(new Point(stepX*3 + 5, initY + 10));
		DrawStation.panel.add(line);
		
		line = new PolylineConnection(); 
		line.setForegroundColor(ColorConstants.blue);
		line.setLineWidth(2);
		line.setStart(new Point(3, initY + 10));
		line.setEnd(new Point(stepX*3 + 5, initY + 10));
		DrawStation.panel.add(line);		
		
	}

	/**
	 * 改变trainName的颜色
	 * @param trainName
	 */
	public static void changeTrainLabel(String trainName){
		
		System.out.println("改变颜色_changeTrainLabel");
		
		/*
		 		
		if(trainLabelList == null){
			DrawTrainList.drawTrainList();
		}
		
		int len = trainLabelList.size();
		if(len >= 1){
			for(int i = 0; i < len; i++){
				if(trainLabelList.get(i).getLableName().equalsIgnoreCase(trainName)){
					System.out.println("找到相应的train：" + trainName);
					trainLabelList.get(i).getLab().setForegroundColor(ColorConstants.red);
				}
			}
		}
	*/	
		
		lab2.setForegroundColor(ColorConstants.red);
		
	}
	
	
}
