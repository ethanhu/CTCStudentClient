package ctc.ctc.rsb.layout;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Color;

import ctc.ctc.rsb.data.BaseParam;
import ctc.sics.station.data.ParamFlag;
import ctc.sics.station.elements.common.CutLine;
import ctc.sics.station.elements.common.EndLine;
import ctc.sics.station.elements.common.LabelFree;
import ctc.sics.station.elements.common.StationButton;
import ctc.sics.station.elements.common.TrackLine;
import ctc.sics.station.elements.common.TurnoutButton;
import ctc.sics.station.elements.semaphore.*;

public class DrawRSB {
	
	BaseParam baseData = BaseParam.getInstance();
	public Figure panel = baseData.getPanel();
	
	//--------基本参数----------//	
	public int X = baseData.getCenterX(); //中心X
	public int Y = baseData.getCenterY(); //中心Y
	public int stepY = 160;
	public int d = baseData.getSemaphoreLightDiameter();
	public int dis = baseData.getDistanceLen(); //label与物体的距离
	
	public TrackLine trackLine; // 股道线
	public SemaphoreDoubleR semaphoreDoubleR;
	public SemaphoreDoubleL semaphoreDoubleL;
	
	public CutLine cutLine; //绝缘线
	public static LabelFree label;
	int rowHeight = baseData.getRowSpacing();
	int labelWidth = baseData.getLabelWidth();
	int labelHeight = baseData.getLabelHeight();
	int panelWidth = baseData.getPanel().getBounds().width;
	int panelHeight = baseData.getPanel().getBounds().height;
	

	//区间1
	public List<TrackLine> disListS1 = baseData.getDisListS1();
	public List<TrackLine> disListX1 = baseData.getDisListX1();
	public List<SemaphoreDoubleL> sepListS1 = baseData.getSepListS1();
	public List<SemaphoreDoubleR> sepListX1 = baseData.getSepListX1();
	//区间2
	public List<TrackLine> disListS2 = baseData.getDisListS2();
	public List<TrackLine> disListX2 = baseData.getDisListX2();
	public List<SemaphoreDoubleL> sepListS2 = baseData.getSepListS2();
	public List<SemaphoreDoubleR> sepListX2 = baseData.getSepListX2();
	//区间3
	public List<TrackLine> disListS3 = baseData.getDisListS3();
	public List<TrackLine> disListX3 = baseData.getDisListX3();
	public List<SemaphoreDoubleL> sepListS3 = baseData.getSepListS3();
	public List<SemaphoreDoubleR> sepListX3 = baseData.getSepListX3();
	//区间4
	public List<TrackLine> disListS4 = baseData.getDisListS4();
	public List<TrackLine> disListX4 = baseData.getDisListX4();	
	public List<SemaphoreDoubleL> sepListS4 = baseData.getSepListS4();
	public List<SemaphoreDoubleR> sepListX4 = baseData.getSepListX4();
	
	
	//绘制区段名称
	public void drawDistrictName(){
		
		String districtName = "实验区段";	
		Label districtNameLabel = new Label(districtName);
		districtNameLabel.setSize(districtName.length()*25, 30);	
		districtNameLabel.setFont(baseData.getFont2());
		districtNameLabel.setLocation(new Point(X - districtNameLabel.getSize().width/2, 10));
		districtNameLabel.setBorder(new LineBorder());
		panel.add(districtNameLabel);
		
	}
	
	//绘制区段1
	public void drawDistrict1(){
		
		int len = (panelWidth - 100)/20;
		int X_X = X - 10*len; //下行
		int X_S = X + 9*len; //上行
		int initY = 100;
		
		String districtName = "车站1  <——>  车站2";	
		Label districtNameLabel = new Label(districtName);
		districtNameLabel.setSize(districtName.length()*25, 20);	
		districtNameLabel.setFont(baseData.getFont5());
		districtNameLabel.setLocation(new Point(X - districtNameLabel.getSize().width/2, initY - 40));
		panel.add(districtNameLabel);
		
		//下行
		for(int i = 0; i < 20; i++){
			//股道线
			trackLine = new TrackLine(panel, String.valueOf(i+1)+"G", X_X + i*len, initY, len, "X");
			disListX1.add(trackLine);
			//信号机
			semaphoreDoubleR = new SemaphoreDoubleR(panel, "XS"+String.valueOf(i+1), X_X + i*len, initY - d - dis, "X");
			semaphoreDoubleR.setColorStatus(ParamFlag.sep_green);
			sepListX1.add(semaphoreDoubleR);
		}
		
		//上行
		for(int i = 0; i < 20; i++){
			trackLine = new TrackLine(panel, String.valueOf(i+1)+"G", X_S - i*len, initY + rowHeight, len, "S");
			disListS1.add(trackLine);
			semaphoreDoubleL = new SemaphoreDoubleL(panel,"S2LQ", X_S + len - i*len, initY + len + dis, "S");
			semaphoreDoubleL.setColorStatus(ParamFlag.sep_green);
			sepListS1.add(semaphoreDoubleL);		
		}		
	}
		
	//绘制区段2
	public void drawDistrict2(){
		
		int len = (panelWidth - 100)/20;
		int X_X = X - 10*len; //下行
		int X_S = X + 9*len; //上行
		int initY = 100;
		
		String districtName = "车站2  <——>  车站3";	
		Label districtNameLabel = new Label(districtName);
		districtNameLabel.setSize(districtName.length()*25, 20);	
		districtNameLabel.setFont(baseData.getFont5());
		districtNameLabel.setLocation(new Point(X - districtNameLabel.getSize().width/2, initY - 40 + stepY));
		panel.add(districtNameLabel);
		
		//下行
		for(int i = 0; i < 20; i++){
			//股道线
			trackLine = new TrackLine(panel, String.valueOf(i+1)+"G", X_X + i*len, initY + stepY, len, "X");
			disListX2.add(trackLine);
			//信号机
			semaphoreDoubleR = new SemaphoreDoubleR(panel, "XS"+String.valueOf(i+1), X_X + i*len, initY - d - dis + stepY, "S");
			semaphoreDoubleR.setColorStatus(ParamFlag.sep_green);
			sepListX2.add(semaphoreDoubleR);
		}
		
		//上行
		for(int i = 0; i < 20; i++){
			trackLine = new TrackLine(panel, String.valueOf(i+1)+"G", X_S - i*len, initY + rowHeight + stepY, len, "X");
			disListS2.add(trackLine);
			semaphoreDoubleL = new SemaphoreDoubleL(panel,"S2LQ", X_S + len - i*len, initY + len + dis + stepY, "X");
			semaphoreDoubleL.setColorStatus(ParamFlag.sep_green);
			sepListS2.add(semaphoreDoubleL);		
		}		
	}
	
	//绘制区段3
	public void drawDistrict3(){
		
		int len = (panelWidth - 100)/20;
		int X_X = X - 10*len; //下行
		int X_S = X + 9*len; //上行
		int initY = 100;
		
		String districtName = "车站3  <——>  车站4";	
		Label districtNameLabel = new Label(districtName);
		districtNameLabel.setSize(districtName.length()*25, 20);	
		districtNameLabel.setFont(baseData.getFont5());
		districtNameLabel.setLocation(new Point(X - districtNameLabel.getSize().width/2, initY - 40 + 2*stepY));
		panel.add(districtNameLabel);
		
		//下行
		for(int i = 0; i < 20; i++){
			//股道线
			trackLine = new TrackLine(panel, String.valueOf(i+1)+"G", X_X + i*len, initY + 2*stepY, len, "X");
			disListX3.add(trackLine);
			//信号机
			semaphoreDoubleR = new SemaphoreDoubleR(panel, "XS"+String.valueOf(i+1), X_X + i*len, initY - d - dis + 2*stepY, "S");
			semaphoreDoubleR.setColorStatus(ParamFlag.sep_green);
			sepListX3.add(semaphoreDoubleR);
		}
		
		//上行
		for(int i = 0; i < 20; i++){
			trackLine = new TrackLine(panel, String.valueOf(i+1)+"G", X_S - i*len, initY + rowHeight + 2*stepY, len, "X");
			disListS3.add(trackLine);
			semaphoreDoubleL = new SemaphoreDoubleL(panel,"S2LQ", X_S + len - i*len, initY + len + dis + 2*stepY, "X");
			semaphoreDoubleL.setColorStatus(ParamFlag.sep_green);
			sepListS3.add(semaphoreDoubleL);		
		}		
	}
	
	//绘制区段4
	public void drawDistrict4(){
		
		int len = (panelWidth - 100)/20;
		int X_X = X - 10*len; //下行
		int X_S = X + 9*len; //上行
		int initY = 100;
		
		String districtName = "车站4  <——>  车站5";	
		Label districtNameLabel = new Label(districtName);
		districtNameLabel.setSize(districtName.length()*25, 20);	
		districtNameLabel.setFont(baseData.getFont5());
		districtNameLabel.setLocation(new Point(X - districtNameLabel.getSize().width/2, initY - 40 + 3*stepY));
		panel.add(districtNameLabel);
		
		//下行
		for(int i = 0; i < 20; i++){
			//股道线
			trackLine = new TrackLine(panel, String.valueOf(i+1)+"G", X_X + i*len, initY + 3*stepY, len, "X");
			disListX4.add(trackLine);
			//信号机
			semaphoreDoubleR = new SemaphoreDoubleR(panel, "XS"+String.valueOf(i+1), X_X + i*len, initY - d - dis + 3*stepY, "S");
			semaphoreDoubleR.setColorStatus(ParamFlag.sep_green);
			sepListX4.add(semaphoreDoubleR);
		}
		
		//上行
		for(int i = 0; i < 20; i++){
			trackLine = new TrackLine(panel, String.valueOf(i+1)+"G", X_S - i*len, initY + rowHeight + 3*stepY, len, "X");
			disListS4.add(trackLine);
			semaphoreDoubleL = new SemaphoreDoubleL(panel,"S2LQ", X_S + len - i*len, initY + len + dis + 3*stepY, "X");
			semaphoreDoubleL.setColorStatus(ParamFlag.sep_green);
			sepListS4.add(semaphoreDoubleL);		
		}		
	}
	
}
