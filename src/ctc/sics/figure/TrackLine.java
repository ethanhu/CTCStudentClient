package ctc.sics.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Color;

/**
 * 站的股道线
 * @author 胡恩召
 *
 */
public class TrackLine  extends PolylineConnection {
	
	public int status = 0; //使用状态：可使用0, 锁定中1, 车占用2, 坏了:-1
	
	public String lineName;
	public PolylineConnection line = new PolylineConnection();
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	
	public PolylineConnection getLine() {
		return line;
	}

	public void setLine(PolylineConnection line) {
		this.line = line;
	}

	/**
	 * 新建一条直线
	 * @param length 直线长度
	 * @param lineWidth 直线宽度
	 * @param color 直线颜色
	 * @param initX 直线的X坐标
	 * @param initY 直线的Y坐标
	 * @param panel 直线的父容器
	 */
	public TrackLine(int length, int lineWidth, Color color, int initX, int initY, Figure panel){
		
		line.setStart(new Point(initX, initY));
		line.setEnd(new Point(initX + length, initY));
		line.setLineWidth(lineWidth);
		line.setForegroundColor(color);
		panel.add(line);
		
	}
	
	/**
	 * 设置线的状态
	 * @param colorStatus 颜色状态
	 */
	public void setTrackLineStatus(String colorStatus){
		
		if(colorStatus.equalsIgnoreCase("black")){ //黑色
			status = -1;
			line.setForegroundColor(ColorConstants.black);		
		}
		
		if(colorStatus.equalsIgnoreCase("blue")){ //蓝色
			status = 0;
			line.setForegroundColor(ColorConstants.blue);
		}	
			
		if(colorStatus.equalsIgnoreCase("green")){ // 绿色
			status = 1;
			line.setForegroundColor(ColorConstants.green);
		}	
				
		if(colorStatus.equalsIgnoreCase("red")){ // 红色
			status = 2;
			line.setForegroundColor(ColorConstants.red);
		}	
	
	}
	
}
