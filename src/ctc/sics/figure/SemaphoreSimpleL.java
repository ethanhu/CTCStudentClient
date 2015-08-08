package ctc.sics.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Point;

/**
 * 向左的单个信号灯
 * @author 胡恩召
 *
 */
public class SemaphoreSimpleL extends Ellipse {

	public String semaphoreName; // 信号机名称
	public PolylineConnection semaphoreLine = new PolylineConnection(); // 信号机标志线
	public Ellipse semaphoreL = new Ellipse(); //左信号灯
	public Ellipse semaphoreR = new Ellipse(); //右信号灯
	
	public String getSemaphoreName() {
		return semaphoreName;
	}

	public void setSemaphoreName(String semaphoreName) {
		this.semaphoreName = semaphoreName;
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

	/**
	 * 新建一个信号机
	 * @param d 灯直径
	 * @param initX 信号机坐标X
	 * @param initY 信号机坐标Y
	 * @param panel 信号机父容器
	 */
	public SemaphoreSimpleL(String semaphoreName, int d, int initX, int initY, Figure panel){
		this.semaphoreName = semaphoreName;
		semaphoreLine.setForegroundColor(ColorConstants.black);		
		semaphoreLine.setStart(new Point(initX, initY));
		semaphoreLine.setEnd(new Point(initX, initY + d));
		semaphoreLine.setLineWidth(2);
		panel.add(semaphoreLine);
		
		semaphoreL.setBackgroundColor(ColorConstants.red); // 设置颜色
		semaphoreL.setSize(d, d);// 设置信号机直径
		semaphoreL.setLocation(new Point(initX - d, initY)); // 设置位置
		panel.add(semaphoreL);	
		
	}
	
	/**
	 * 信号机颜色
	 * @param color
	 */
	public void semaphoreColor(String color){
		
		if(color.equalsIgnoreCase("red")){ //红色
			semaphoreL.setBackgroundColor(ColorConstants.red); // 设置颜色
		}
				
		if(color.equalsIgnoreCase("white")){ //白色
			semaphoreL.setBackgroundColor(ColorConstants.white); // 设置颜色
		}
		
		if(color.equalsIgnoreCase("green")){ //绿色
			semaphoreL.setBackgroundColor(ColorConstants.green); // 设置颜色
		}
		
		
		if(color.equalsIgnoreCase("yellow")){ //黄色
			semaphoreL.setBackgroundColor(ColorConstants.yellow); // 设置颜色
		}
		
	}

}

