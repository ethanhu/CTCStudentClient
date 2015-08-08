package ctc.sics.station.elements.semaphore;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;

import ctc.sics.station.data.*;
import ctc.sics.station.elements.common.*;

/**
 * 双信号灯的信号机，开口向左
 * 
 * @author 胡恩召
 * 
 */
public class SemaphoreDoubleL extends Ellipse {

	BaseParam baseData = BaseParam.getInstance();
	public Figure panel = baseData.getPanel();
	public int lineWidth = baseData.getLineWidth();
	public Color lineColor = baseData.getTrackInitColor();
	public Color backgroundColor = baseData.getBackgroundColor();	
	public int labHeight = baseData.getLabelHeight();
	public int dis = baseData.getDistanceLen();
	public Font font1 = baseData.getFont1();
	public Font font2 = baseData.getFont4();
	public int slen = baseData.getSlineLength();
	public String trainName; //车次名
	public String getTrainName() {
		return trainName;
	}
	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}
	public int color = ParamFlag.sep_green;
	public int status = 0;	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	int d = baseData.getSemaphoreLightDiameter();
		
	public String name; // 信号机名称
	public PolylineConnection semaphoreLine = new PolylineConnection(); // 信号机标志线
	public Ellipse semaphoreL = new Ellipse(); // 左信号灯
	public Ellipse semaphoreR = new Ellipse(); // 右信号灯

	/**
	 * 新建一个信号机
	 * 
	 */
	public SemaphoreDoubleL(String semaphoreName, int initX, int initY, String locationType) {

		new SemaphoreNameLabel(semaphoreName, initX - 2*d, initY, locationType);
					
		this.name = semaphoreName;
		
		semaphoreLine.setForegroundColor(ColorConstants.blue);
		semaphoreLine.setStart(new Point(initX, initY));
		semaphoreLine.setEnd(new Point(initX, initY + d));
		semaphoreLine.setLineWidth(2);
		panel.add(semaphoreLine);

		semaphoreL.setBackgroundColor(ColorConstants.white); // 设置颜色
		semaphoreL.setSize(d, d);// 设置信号机直径
		semaphoreL.setLocation(new Point(initX - 2 * d, initY)); // 设置位置
		panel.add(semaphoreL);

		semaphoreR.setBackgroundColor(ColorConstants.red); // 设置颜色
		semaphoreR.setSize(d, d);// 设置信号机直径
		semaphoreR.setLocation(new Point(initX - d, initY)); // 设置位置
		panel.add(semaphoreR);

	}

	public SemaphoreDoubleL(Figure pan, String semaphoreName, int initX, int initY, String locationType) {

		new SemaphoreNameLabel(semaphoreName, initX - 2*d, initY, locationType);
					
		this.name = semaphoreName;
		
		semaphoreLine.setForegroundColor(ColorConstants.blue);
		semaphoreLine.setStart(new Point(initX, initY));
		semaphoreLine.setEnd(new Point(initX, initY + d));
		semaphoreLine.setLineWidth(2);
		pan.add(semaphoreLine);

		semaphoreL.setBackgroundColor(ColorConstants.white); // 设置颜色
		semaphoreL.setSize(d, d);// 设置信号机直径
		semaphoreL.setLocation(new Point(initX - 2 * d, initY)); // 设置位置
		pan.add(semaphoreL);

		semaphoreR.setBackgroundColor(ColorConstants.red); // 设置颜色
		semaphoreR.setSize(d, d);// 设置信号机直径
		semaphoreR.setLocation(new Point(initX - d, initY)); // 设置位置
		pan.add(semaphoreR);

	}
	
	/**
	 * 信号机颜色
	 * 
	 * @param color
	 */
	public void setColorStatus(int color) {

		this.color = color;
		
		switch(color){
		
		case ParamFlag.sep_white: //白色
			semaphoreR.setBackgroundColor(ColorConstants.white); 
			semaphoreL.setBackgroundColor(ColorConstants.white);
			break;		
			
		case ParamFlag.sep_green: //绿色
			semaphoreR.setBackgroundColor(ColorConstants.green); // 设置颜色
			semaphoreL.setBackgroundColor(ColorConstants.white);
			break;	
			
		case ParamFlag.sep_red: //红色
			semaphoreR.setBackgroundColor(ColorConstants.red);
			semaphoreL.setBackgroundColor(ColorConstants.white);
			break;	
			
		case ParamFlag.sep_yellow: //黄色
			semaphoreR.setBackgroundColor(ColorConstants.yellow); 
			semaphoreL.setBackgroundColor(ColorConstants.white);
			break;	
			
		case ParamFlag.sep_blue: //蓝色
			semaphoreR.setBackgroundColor(ColorConstants.blue); 
			semaphoreL.setBackgroundColor(ColorConstants.white);
			break;	
			
		case ParamFlag.sep_green_yellow: //蓝色
			semaphoreR.setBackgroundColor(ColorConstants.green); 
			semaphoreL.setBackgroundColor(ColorConstants.yellow);
			break;	
		}
	}

	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PolylineConnection getSemaphoreLine() {
		return semaphoreLine;
	}

	public void setSemaphoreLine(PolylineConnection semaphoreLine) {
		this.semaphoreLine = semaphoreLine;
	}

	public Ellipse getSemaphoreL() {
		return semaphoreL;
	}

	public void setSemaphoreL(Ellipse semaphoreL) {
		this.semaphoreL = semaphoreL;
	}

	public Ellipse getSemaphoreR() {
		return semaphoreR;
	}

	public void setSemaphoreR(Ellipse semaphoreR) {
		this.semaphoreR = semaphoreR;
	}

}
