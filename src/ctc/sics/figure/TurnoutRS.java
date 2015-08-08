package ctc.sics.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Point;

/**
 * 右上道岔
 * @author 胡恩召
 *
 */
public class TurnoutRS extends PolylineConnection {
	
	public String turnoutName; // 道岔名称
	public int status = 0; //使用状态：可使用0, 锁定中1, 坏了:-1	
	public int turnoutStatus = 1; //道岔状态：正位1, 反位0
	
	public PolylineConnection mainLine = new PolylineConnection(); // 主道岔
	public PolylineConnection initLine = new PolylineConnection(); // 初始道岔显示小线段
	public PolylineConnection changeLine = new PolylineConnection(); // 道岔定位后显示的小线段
	public PolylineConnection trackLineChange = new PolylineConnection(); // 道岔反位后隐藏的小线段
	public PolylineConnection trackLineMain = new PolylineConnection(); // 主道岔部分

	

	/**
	 * 新建一个道岔
	 * @param h 股道线之间的行距
	 * @param initX 道岔的X坐标
	 * @param initY 道岔的Y坐标
	 * @param panel 道岔的父容器
	 */
	public TurnoutRS(int h, int initX, int initY, Figure panel) {

		mainLine.setForegroundColor(ColorConstants.blue);
		mainLine.setStart(new Point(initX, initY));
		mainLine.setEnd(new Point(initX + 5*h/6, initY + 5*h/6));
		mainLine.setLineWidth(2);
		panel.add(mainLine);

		initLine.setForegroundColor(ColorConstants.blue);
		initLine.setStart(new Point(initX + 5*h/6, initY + 5*h/6));
		initLine.setEnd(new Point(initX + h, initY + 5*h/6));
		initLine.setLineWidth(2);
		panel.add(initLine);

		changeLine.setForegroundColor(ColorConstants.white);
		changeLine.setStart(new Point(initX + 5*h/6, initY + 5*h/6));
		changeLine.setEnd(new Point(initX + h, initY + h));
		changeLine.setLineWidth(2);
		panel.add(changeLine);
		
		trackLineChange.setForegroundColor(ColorConstants.blue);
		trackLineChange.setStart(new Point(initX + h, initY + h));
		trackLineChange.setEnd(new Point(initX + 5*h/6, initY + h));
		trackLineChange.setLineWidth(2);
		panel.add(trackLineChange);
		
		trackLineMain.setForegroundColor(ColorConstants.blue);
		trackLineMain.setStart(new Point(initX, initY + h));
		trackLineMain.setEnd(new Point(initX + 5*h/6, initY + h));
		trackLineMain.setLineWidth(2);
		panel.add(trackLineMain);

	}
	
	/**
	 * 设置道岔的正位、反位
	 * @param colorStatus 颜色状态
	 */ 
	public void setColorStatus(String color){
		
		//正位蓝色
		if(color.equalsIgnoreCase("blueZ")){ 			

			//设置道岔定/反位
			turnoutStatus = 1;
			//设置道岔状态
			status = 0;			
			
			//道岔主线
			mainLine.setForegroundColor(ColorConstants.blue);			
			//初始显示部分
			initLine.setForegroundColor(ColorConstants.blue);
			//反位时显示部分
			changeLine.setForegroundColor(ColorConstants.white);
			//反位时股道隐藏部分			
			trackLineChange.setForegroundColor(ColorConstants.blue);
			//股道主干道			
			trackLineMain.setForegroundColor(ColorConstants.blue);
			
		}else
		//正位红色
		if(color.equalsIgnoreCase("redZ")){ 

			//设置道岔定/反位
			turnoutStatus = 1;
			//设置道岔状态
			status = 1;			
			
			//道岔主线
			mainLine.setForegroundColor(ColorConstants.blue);			
			//初始显示部分
			initLine.setForegroundColor(ColorConstants.blue);
			//反位时显示部分
			changeLine.setForegroundColor(ColorConstants.white);
			//反位时股道隐藏部分			
			trackLineChange.setForegroundColor(ColorConstants.red);
			//股道主干道			
			trackLineMain.setForegroundColor(ColorConstants.red);
			
		}else
		//正位绿色
		if(color.equalsIgnoreCase("greenZ")){  

			//设置道岔定/反位
			turnoutStatus = 1;
			//设置道岔状态
			status = 1;			
			
			//道岔主线
			mainLine.setForegroundColor(ColorConstants.blue);			
			//初始显示部分
			initLine.setForegroundColor(ColorConstants.blue);
			//反位时显示部分
			changeLine.setForegroundColor(ColorConstants.white);
			//反位时股道隐藏部分			
			trackLineChange.setForegroundColor(ColorConstants.green);
			//股道主干道			
			trackLineMain.setForegroundColor(ColorConstants.green);
			
		}else
		//正位白色
		if(color.equalsIgnoreCase("whiteZ")){  

			//设置道岔定/反位
			turnoutStatus = 1;
			//设置道岔状态
			status = 1;			
			
			//道岔主线
			mainLine.setForegroundColor(ColorConstants.blue);			
			//初始显示部分
			initLine.setForegroundColor(ColorConstants.blue);
			//反位时显示部分
			changeLine.setForegroundColor(ColorConstants.white);
			//反位时股道隐藏部分			
			trackLineChange.setForegroundColor(ColorConstants.white);
			//股道主干道			
			trackLineMain.setForegroundColor(ColorConstants.white);
			
		}else
		//反位蓝色
		if(color.equalsIgnoreCase("blueF")){  

			//设置道岔定/反位
			turnoutStatus = 0;
			//设置道岔状态
			status = 0;			
			
			//道岔主线
			mainLine.setForegroundColor(ColorConstants.blue);			
			//初始显示部分
			initLine.setForegroundColor(ColorConstants.white);
			//反位时显示部分
			changeLine.setForegroundColor(ColorConstants.blue);
			//反位时股道隐藏部分			
			trackLineChange.setForegroundColor(ColorConstants.white);
			//股道主干道			
			trackLineMain.setForegroundColor(ColorConstants.blue);
			
		}else 
		//反位绿色
		if(color.equalsIgnoreCase("greenF")){  

			//设置道岔定/反位
			turnoutStatus = 0;
			//设置道岔状态
			status = 1;			
			
			//道岔主线
			mainLine.setForegroundColor(ColorConstants.green);			
			//初始显示部分
			initLine.setForegroundColor(ColorConstants.white);
			//反位时显示部分
			changeLine.setForegroundColor(ColorConstants.green);
			//反位时股道隐藏部分			
			trackLineChange.setForegroundColor(ColorConstants.white);
			//股道主干道			
			trackLineMain.setForegroundColor(ColorConstants.blue);
			
		}else
		//反位红色
		if(color.equalsIgnoreCase("redF")){   

			//设置道岔定/反位
			turnoutStatus = 0;
			//设置道岔状态
			status = 1;			
			
			//道岔主线
			mainLine.setForegroundColor(ColorConstants.red);			
			//初始显示部分
			initLine.setForegroundColor(ColorConstants.white);
			//反位时显示部分
			changeLine.setForegroundColor(ColorConstants.red);
			//反位时股道隐藏部分			
			trackLineChange.setForegroundColor(ColorConstants.white);
			//股道主干道			
			trackLineMain.setForegroundColor(ColorConstants.blue);
			
		}else
		//反位白色
		if(color.equalsIgnoreCase("whiteF")){   

			//设置道岔定/反位
			turnoutStatus = 0;
			//设置道岔状态
			status = 1;			
			
			//道岔主线
			mainLine.setForegroundColor(ColorConstants.white);			
			//初始显示部分
			initLine.setForegroundColor(ColorConstants.white);
			//反位时显示部分
			changeLine.setForegroundColor(ColorConstants.white);
			//反位时股道隐藏部分			
			trackLineChange.setForegroundColor(ColorConstants.white);
			//股道主干道			
			trackLineMain.setForegroundColor(ColorConstants.blue);
			
		}		
		
	}
	

	public String getTurnoutName() {
		return turnoutName;
	}

	public void setTurnoutName(String turnoutName) {
		this.turnoutName = turnoutName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getTurnoutStatus() {
		return turnoutStatus;
	}

	public void setTurnoutStatus(int turnoutStatus) {
		this.turnoutStatus = turnoutStatus;
	}

	public PolylineConnection getMainLine() {
		return mainLine;
	}

	public void setMainLine(PolylineConnection mainLine) {
		this.mainLine = mainLine;
	}

	public PolylineConnection getInitLine() {
		return initLine;
	}

	public void setInitLine(PolylineConnection initLine) {
		this.initLine = initLine;
	}

	public PolylineConnection getChangeLine() {
		return changeLine;
	}

	public void setChangeLine(PolylineConnection changeLine) {
		this.changeLine = changeLine;
	}

	public PolylineConnection getTrackLineChange() {
		return trackLineChange;
	}

	public void setTrackLineChange(PolylineConnection trackLineChange) {
		this.trackLineChange = trackLineChange;
	}

	public PolylineConnection getTrackLineMain() {
		return trackLineMain;
	}

	public void setTrackLineMain(PolylineConnection trackLineMain) {
		this.trackLineMain = trackLineMain;
	}
	

}
