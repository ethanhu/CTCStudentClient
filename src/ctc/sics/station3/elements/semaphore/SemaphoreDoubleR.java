package ctc.sics.station3.elements.semaphore;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Point;

import ctc.sics.station3.data.*;
import ctc.sics.station3.elements.common.*;

/**
 * 开口向右的双信号灯
 * @author 胡恩召
 *
 */
public class SemaphoreDoubleR extends Ellipse {


	BaseParam baseDataForStation = BaseParam.getInstance();	

	public int status = 0;	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	public String name; // 信号机名称
	public PolylineConnection semaphoreLine = new PolylineConnection(); // 信号机标志线
	public Ellipse semaphoreL = new Ellipse(); //左信号灯
	public Ellipse semaphoreR = new Ellipse(); //右信号灯
	
	
	/**
	 * 新建一个信号机
	 */
	public SemaphoreDoubleR(String semaphoreName, int initX, int initY, String locationType){
	
		int d = baseDataForStation.getSemaphoreLightDiameter();
		Figure panel = baseDataForStation.getPanel();		
		this.name = semaphoreName;
		
		new SemaphoreNameLabel(semaphoreName, initX, initY, locationType);
		
		semaphoreLine.setForegroundColor(ColorConstants.blue);		
		semaphoreLine.setStart(new Point(initX, initY));
		semaphoreLine.setEnd(new Point(initX, initY + d));
		semaphoreLine.setLineWidth(2);
		panel.add(semaphoreLine);
			
		semaphoreL.setBackgroundColor(ColorConstants.red); // 设置颜色
		semaphoreL.setSize(d, d);// 设置信号机直径
		semaphoreL.setLocation(new Point(initX, initY)); //设置位置
		panel.add(semaphoreL);
		
		semaphoreR.setBackgroundColor(ColorConstants.white); // 设置颜色
		semaphoreR.setSize(d, d);// 设置信号机直径
		semaphoreR.setLocation(new Point(initX + d, initY)); // 设置位置
		panel.add(semaphoreR);	
		
	}
	
	/**
	 * 信号机颜色
	 * @param color
	 */
	public void setColorStatus(int color) {

		switch(color){
		
		case ParamFlag.sep_white: //白色
			this.status = 0;
			semaphoreL.setBackgroundColor(ColorConstants.white); 
			break;		
			
		case ParamFlag.sep_green: //绿色
			this.status = 0;
			semaphoreL.setBackgroundColor(ColorConstants.green); // 设置颜色
			break;	
			
		case ParamFlag.sep_red: //红色
			this.status = 0;
			semaphoreL.setBackgroundColor(ColorConstants.red); 
			break;	
			
		case ParamFlag.sep_yellow: //黄色
			this.status = 0;
			semaphoreL.setBackgroundColor(ColorConstants.yellow); 
			break;	
			
		case ParamFlag.sep_blue: //蓝色
			semaphoreL.setBackgroundColor(ColorConstants.blue); 
			break;	
		}
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

