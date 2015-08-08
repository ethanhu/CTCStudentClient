package ctc.sics.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Point;

/**
 * 右倾斜双开道岔
 * @author 胡恩召
 *
 */
public class TurnoutDoubleR extends PolylineConnection {

	public int status = 0; //使用状态：可使用0, 锁定中1, 坏了:-1
	
	public String turnoutName; // 道岔名称
	public PolylineConnection mainLine = new PolylineConnection(); // 主道岔
	public PolylineConnection initLineS = new PolylineConnection(); // 初始道岔显示小线段(上)
	public PolylineConnection initLineX = new PolylineConnection(); // 初始道岔显示小线段(下)
	public PolylineConnection changeLineS = new PolylineConnection(); // 道岔定位后显示的小线段(上)
	public PolylineConnection changeLineX = new PolylineConnection(); // 道岔定位后显示的小线段(下)
	public PolylineConnection trackLineS = new PolylineConnection(); // 道岔反位后隐藏的小线段(上)
	public PolylineConnection trackLineX = new PolylineConnection(); // 道岔反位后隐藏的小线段(下)


	public int turnoutStatus = 1; //道岔状态：正位1, 反位0
	
	public int getTurnoutStatus() {
		return turnoutStatus;
	}

	public void setTurnoutStatus(int turnoutStatus) {
		this.turnoutStatus = turnoutStatus;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTurnoutName() {
		return turnoutName;
	}

	public void setTurnoutName(String turnoutName) {
		this.turnoutName = turnoutName;
	}

	public PolylineConnection getMainLine() {
		return mainLine;
	}

	public void setMainLine(PolylineConnection mainLine) {
		this.mainLine = mainLine;
	}

	public PolylineConnection getInitLineS() {
		return initLineS;
	}

	public void setInitLineS(PolylineConnection initLineS) {
		this.initLineS = initLineS;
	}

	public PolylineConnection getInitLineX() {
		return initLineX;
	}

	public void setInitLineX(PolylineConnection initLineX) {
		this.initLineX = initLineX;
	}

	public PolylineConnection getChangeLineS() {
		return changeLineS;
	}

	public void setChangeLineS(PolylineConnection changeLineS) {
		this.changeLineS = changeLineS;
	}

	public PolylineConnection getChangeLineX() {
		return changeLineX;
	}

	public void setChangeLineX(PolylineConnection changeLineX) {
		this.changeLineX = changeLineX;
	}

	public PolylineConnection getTrackLineS() {
		return trackLineS;
	}

	public void setTrackLineS(PolylineConnection trackLineS) {
		this.trackLineS = trackLineS;
	}

	public PolylineConnection getTrackLineX() {
		return trackLineX;
	}

	public void setTrackLineX(PolylineConnection trackLineX) {
		this.trackLineX = trackLineX;
	}

	/**
	 * 新建一个道岔
	 * @param length 道岔拐角处的伸出长度
	 * @param lineWidth 线宽度
	 * @param rowSpacing 股道线之间的行距
	 * @param initX 道岔的X坐标
	 * @param initY 道岔的Y坐标
	 * @param panel 道岔的父容器
	 */
	public TurnoutDoubleR(int length, int lineWidth, int rowSpacing, int initX, int initY, Figure panel) {

		mainLine.setForegroundColor(ColorConstants.blue);
		mainLine.setStart(new Point(initX + rowSpacing/2 - length, initY - rowSpacing/2 + length));
		mainLine.setEnd(new Point(initX - rowSpacing/2 + length, initY + rowSpacing/2 - length));
		mainLine.setLineWidth(lineWidth);
		panel.add(mainLine);

		initLineS.setForegroundColor(ColorConstants.blue);
		initLineS.setStart(new Point(initX + rowSpacing/2 - length , initY - rowSpacing/2 + length));
		initLineS.setEnd(new Point(initX + rowSpacing/2, initY - rowSpacing/2 + length));
		initLineS.setLineWidth(lineWidth);
		panel.add(initLineS);

		changeLineS.setForegroundColor(ColorConstants.white);
		changeLineS.setStart(new Point(initX + rowSpacing/2 - length , initY - rowSpacing/2 + length));
		changeLineS.setEnd(new Point(initX + rowSpacing/2, initY - rowSpacing/2));
		changeLineS.setLineWidth(lineWidth);
		panel.add(changeLineS);
		
		initLineX.setForegroundColor(ColorConstants.blue);
		initLineX.setStart(new Point(initX - rowSpacing/2 + length, initY + rowSpacing/2 - length));
		initLineX.setEnd(new Point(initX - rowSpacing/2, initY + rowSpacing/2 - length));
		initLineX.setLineWidth(lineWidth);
		panel.add(initLineX);

		changeLineX.setForegroundColor(ColorConstants.white);
		changeLineX.setStart(new Point(initX - rowSpacing/2 + length, initY + rowSpacing/2 - length));
		changeLineX.setEnd(new Point(initX - rowSpacing/2, initY + rowSpacing/2));
		changeLineX.setLineWidth(lineWidth);
		panel.add(changeLineX);
		
		trackLineS.setForegroundColor(ColorConstants.blue);
		trackLineS.setStart(new Point(initX + rowSpacing/2, initY - rowSpacing/2));
		trackLineS.setEnd(new Point(initX + rowSpacing/2 - length, initY - rowSpacing/2));
		trackLineS.setLineWidth(lineWidth);
		panel.add(trackLineS);
		
		trackLineX.setForegroundColor(ColorConstants.blue);
		trackLineX.setStart(new Point(initX - rowSpacing/2, initY + rowSpacing/2));
		trackLineX.setEnd(new Point(initX - rowSpacing/2 + length, initY + rowSpacing/2));
		trackLineX.setLineWidth(lineWidth);
		panel.add(trackLineX);
		
	}
	
	/**
	 * 设置道岔的正位、反位
	 * @param colorStatus 颜色状态
	 */
	public void setTurnoutDoubleRStatus(String colorStatus){
		

		if(colorStatus.equalsIgnoreCase("blue")){ //蓝色初始状态
			mainLine.setForegroundColor(ColorConstants.blue);
			initLineS.setForegroundColor(ColorConstants.blue);
			changeLineS.setForegroundColor(ColorConstants.white);										
			trackLineS.setForegroundColor(ColorConstants.blue);
			initLineX.setForegroundColor(ColorConstants.blue);
			changeLineX.setForegroundColor(ColorConstants.white);
			trackLineX.setForegroundColor(ColorConstants.blue);
			this.turnoutStatus = 1;
		}
					
		if(colorStatus.equalsIgnoreCase("blueZ")){ //蓝色正位
			mainLine.setForegroundColor(ColorConstants.blue);
			initLineS.setForegroundColor(ColorConstants.blue);
			changeLineS.setForegroundColor(ColorConstants.white);										
			trackLineS.setForegroundColor(ColorConstants.blue);
			initLineX.setForegroundColor(ColorConstants.blue);
			changeLineX.setForegroundColor(ColorConstants.white);
			trackLineX.setForegroundColor(ColorConstants.blue);
			this.turnoutStatus = 1;
		}
		
		if(colorStatus.equalsIgnoreCase("blueF")){ //蓝色反位
			mainLine.setForegroundColor(ColorConstants.blue);
			initLineS.setForegroundColor(ColorConstants.white);
			changeLineS.setForegroundColor(ColorConstants.blue);										
			trackLineS.setForegroundColor(ColorConstants.white);
			initLineX.setForegroundColor(ColorConstants.white);
			changeLineX.setForegroundColor(ColorConstants.blue);
			trackLineX.setForegroundColor(ColorConstants.white);
			this.turnoutStatus = 0;
		}
		
		if(colorStatus.equalsIgnoreCase("greenZ")){ //绿色正位
			mainLine.setForegroundColor(ColorConstants.green);
			initLineS.setForegroundColor(ColorConstants.green);
			changeLineS.setForegroundColor(ColorConstants.white);										
			trackLineS.setForegroundColor(ColorConstants.green);
			initLineX.setForegroundColor(ColorConstants.green);
			changeLineX.setForegroundColor(ColorConstants.white);
			trackLineX.setForegroundColor(ColorConstants.green);
			this.turnoutStatus = 1;
		}
		
		if(colorStatus.equalsIgnoreCase("greenZS")){ //绿色正位(上)
			mainLine.setForegroundColor(ColorConstants.blue);
			initLineS.setForegroundColor(ColorConstants.blue);
			changeLineS.setForegroundColor(ColorConstants.white);										
			trackLineS.setForegroundColor(ColorConstants.green);
			initLineX.setForegroundColor(ColorConstants.blue);
			changeLineX.setForegroundColor(ColorConstants.white);
			trackLineX.setForegroundColor(ColorConstants.blue);
			this.turnoutStatus = 1;
		}
		
		if(colorStatus.equalsIgnoreCase("greenZX")){ //绿色正位(下)
			mainLine.setForegroundColor(ColorConstants.blue);
			initLineS.setForegroundColor(ColorConstants.blue);
			changeLineS.setForegroundColor(ColorConstants.white);										
			trackLineS.setForegroundColor(ColorConstants.blue);
			initLineX.setForegroundColor(ColorConstants.blue);
			changeLineX.setForegroundColor(ColorConstants.white);
			trackLineX.setForegroundColor(ColorConstants.green);
			this.turnoutStatus = 1;
		}
		
		if(colorStatus.equalsIgnoreCase("greenF")){ //绿色反位
			mainLine.setForegroundColor(ColorConstants.green);
			initLineS.setForegroundColor(ColorConstants.white);
			changeLineS.setForegroundColor(ColorConstants.green);										
			trackLineS.setForegroundColor(ColorConstants.white);
			initLineX.setForegroundColor(ColorConstants.white);
			changeLineX.setForegroundColor(ColorConstants.green);
			trackLineX.setForegroundColor(ColorConstants.white);
			this.turnoutStatus = 0;
		}
		
		if(colorStatus.equalsIgnoreCase("redZ")){ //红色正位
			mainLine.setForegroundColor(ColorConstants.red);
			initLineS.setForegroundColor(ColorConstants.red);
			changeLineS.setForegroundColor(ColorConstants.white);										
			trackLineS.setForegroundColor(ColorConstants.red);
			initLineX.setForegroundColor(ColorConstants.red);
			changeLineX.setForegroundColor(ColorConstants.white);
			trackLineX.setForegroundColor(ColorConstants.red);
			this.turnoutStatus = 1;
		}
		
		if(colorStatus.equalsIgnoreCase("redZS")){ //红色正位(上)
			mainLine.setForegroundColor(ColorConstants.blue);
			initLineS.setForegroundColor(ColorConstants.blue);
			changeLineS.setForegroundColor(ColorConstants.white);										
			trackLineS.setForegroundColor(ColorConstants.red);
			initLineX.setForegroundColor(ColorConstants.blue);
			changeLineX.setForegroundColor(ColorConstants.white);
			trackLineX.setForegroundColor(ColorConstants.blue);
			this.turnoutStatus = 1;
		}
		
		if(colorStatus.equalsIgnoreCase("redZX")){ //红色正位(下)
			mainLine.setForegroundColor(ColorConstants.blue);
			initLineS.setForegroundColor(ColorConstants.blue);
			changeLineS.setForegroundColor(ColorConstants.white);										
			trackLineS.setForegroundColor(ColorConstants.blue);
			initLineX.setForegroundColor(ColorConstants.blue);
			changeLineX.setForegroundColor(ColorConstants.white);
			trackLineX.setForegroundColor(ColorConstants.red);
			this.turnoutStatus = 1;
		}
		
		if(colorStatus.equalsIgnoreCase("redF")){ //红色反位
			mainLine.setForegroundColor(ColorConstants.red);
			initLineS.setForegroundColor(ColorConstants.white);
			changeLineS.setForegroundColor(ColorConstants.red);										
			trackLineS.setForegroundColor(ColorConstants.white);
			initLineX.setForegroundColor(ColorConstants.white);
			changeLineX.setForegroundColor(ColorConstants.red);
			trackLineX.setForegroundColor(ColorConstants.white);
			this.turnoutStatus = 0;
		}
		
		if(colorStatus.equalsIgnoreCase("blackZ")){ //黑色正位
			mainLine.setForegroundColor(ColorConstants.black);
			initLineS.setForegroundColor(ColorConstants.black);
			changeLineS.setForegroundColor(ColorConstants.white);										
			trackLineS.setForegroundColor(ColorConstants.black);
			initLineX.setForegroundColor(ColorConstants.black);
			changeLineX.setForegroundColor(ColorConstants.white);
			trackLineX.setForegroundColor(ColorConstants.black);
			this.turnoutStatus = 1;
		}
		
		if(colorStatus.equalsIgnoreCase("blackF")){ //黑色反位
			mainLine.setForegroundColor(ColorConstants.black);
			initLineS.setForegroundColor(ColorConstants.white);
			changeLineS.setForegroundColor(ColorConstants.black);										
			trackLineS.setForegroundColor(ColorConstants.white);
			initLineX.setForegroundColor(ColorConstants.white);
			changeLineX.setForegroundColor(ColorConstants.black);
			trackLineX.setForegroundColor(ColorConstants.white);
			this.turnoutStatus = 0;
		}
	}

}

