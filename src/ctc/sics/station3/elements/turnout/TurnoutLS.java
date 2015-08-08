package ctc.sics.station3.elements.turnout;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;

import ctc.sics.station3.elements.common.*;
import ctc.sics.station3.data.*;

/**
 * 左上道岔
 * 
 * @author 胡恩召
 * 
 */
public class TurnoutLS extends PolylineConnection {

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
	public int h = baseData.getRowSpacing();

	private String name;

	public String type;	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int status = 0; // 使用状态：可使用0, 锁定中1, 坏了:-1
	public int turnoutStatus = 1; // 道岔状态：正位1, 反位0

	public PolylineConnection mainLine = new PolylineConnection(); // 主道岔	
	public PolylineConnection changeLine = new PolylineConnection(); // 道岔定位后显示的小线段
	public PolylineConnection trackLineChange = new PolylineConnection(); // 道岔反位后隐藏的小线段
	public PolylineConnection trackLineMain = new PolylineConnection(); // 主道岔部分

	public TurnoutNameLabel nameLabel;

	/**
	 * 新建一个道岔
	 */
	public TurnoutLS(String name, int initX, int initY, String type) {

		this.type = type;
		this.name = name;
		nameLabel = new TurnoutNameLabel(name, initX - h - name.length()*3 + 3, initY + 3 * h / 4 - 6);

		mainLine.setForegroundColor(lineColor);
		mainLine.setStart(new Point(initX, initY));
		mainLine.setEnd(new Point(initX - 5 * h / 6, initY + 5 * h / 6));
		mainLine.setLineWidth(lineWidth);
		panel.add(mainLine);

		changeLine.setForegroundColor(backgroundColor);
		changeLine.setStart(new Point(initX - 5 * h / 6, initY + 5 * h / 6));
		changeLine.setEnd(new Point(initX - h, initY + h));
		changeLine.setLineWidth(lineWidth);
		panel.add(changeLine);

		trackLineChange.setForegroundColor(lineColor);
		trackLineChange.setStart(new Point(initX - h, initY + h));
		trackLineChange.setEnd(new Point(initX - 5 * h / 6, initY + h));
		trackLineChange.setLineWidth(lineWidth);
		panel.add(trackLineChange);

		trackLineMain.setForegroundColor(lineColor);
		trackLineMain.setStart(new Point(initX, initY + h));
		trackLineMain.setEnd(new Point(initX - 5 * h / 6, initY + h));
		trackLineMain.setLineWidth(lineWidth);
		panel.add(trackLineMain);

	}

	/**
	 * 设置道岔的正位、反位
	 */
	public void setColorStatus(int color) {

		switch(color){

		case ParamFlag.turnout_red: //锁闭
			this.status = 1;			
			this.mainLine.setForegroundColor(ColorConstants.red); //主道岔(左倾斜);		
			break;		
		case ParamFlag.turnout_blue: //解锁
			this.status = 0;			
			this.mainLine.setForegroundColor(lineColor); //主道岔(左倾斜);	
			break;			
			
		case ParamFlag.turnout_dw_bule: //定位蓝色
			this.status = 0;
			this.turnoutStatus = 1;
			this.mainLine.setForegroundColor(lineColor); // 主道岔
			this.changeLine.setForegroundColor(backgroundColor); // 道岔定位后隐藏的小线段
			this.trackLineChange.setForegroundColor(lineColor); // 道岔反位后隐藏的小线段
			this.trackLineMain.setForegroundColor(lineColor); // 主道岔部分
			break;
		case ParamFlag.turnout_dw_green: //定位绿色
			this.status = 1;
			this.turnoutStatus = 1;
			this.mainLine.setForegroundColor(lineColor); // 主道岔
			this.changeLine.setForegroundColor(backgroundColor); // 道岔定位后隐藏的小线段
			this.trackLineChange.setForegroundColor(ColorConstants.green); // 主道岔隐藏的小线段
			this.trackLineMain.setForegroundColor(ColorConstants.green); // 主道岔部分
			break;
		case ParamFlag.turnout_dw_red: //定位红色
			this.status = 2;
			this.turnoutStatus = 1;
			this.mainLine.setForegroundColor(lineColor); // 主道岔
			this.changeLine.setForegroundColor(backgroundColor); // 道岔定位后隐藏的小线段
			this.trackLineChange.setForegroundColor(ColorConstants.red); // 道岔反位后隐藏的小线段
			this.trackLineMain.setForegroundColor(ColorConstants.red); // 主道岔部分
			break;			
		case ParamFlag.turnout_dw_black: //定位黑色
			this.status = -1;
			this.turnoutStatus = 1;
			this.mainLine.setForegroundColor(lineColor); // 主道岔
			this.changeLine.setForegroundColor(backgroundColor); // 道岔定位后隐藏的小线段
			this.trackLineChange.setForegroundColor(ColorConstants.black); // 道岔反位后隐藏的小线段
			this.trackLineMain.setForegroundColor(ColorConstants.black); // 主道岔部分
			break;
		case ParamFlag.turnout_fw_bule: //反位蓝色
			this.status = 0;
			this.turnoutStatus = 0;
			this.mainLine.setForegroundColor(lineColor); // 主道岔
			this.changeLine.setForegroundColor(lineColor); // 道岔定位后隐藏的小线段
			this.trackLineChange.setForegroundColor(backgroundColor); // 道岔反位后隐藏的小线段
			this.trackLineMain.setForegroundColor(lineColor); // 主道岔部分
			break;
		case ParamFlag.turnout_fw_green: //反位绿色
			this.status = 1;
			this.turnoutStatus = 0;
			this.mainLine.setForegroundColor(ColorConstants.green); // 主道岔
			this.changeLine.setForegroundColor(ColorConstants.green); // 道岔定位后隐藏的小线段
			this.trackLineChange.setForegroundColor(backgroundColor); // 主道岔隐藏的小线段
			this.trackLineMain.setForegroundColor(lineColor); // 主道岔部分
			break;
		case ParamFlag.turnout_fw_red: //反位红色
			this.status = 2;
			this.turnoutStatus = 0;
			this.mainLine.setForegroundColor(ColorConstants.red); // 主道岔
			this.changeLine.setForegroundColor(ColorConstants.red); // 道岔定位后隐藏的小线段
			this.trackLineChange.setForegroundColor(backgroundColor); // 道岔反位后隐藏的小线段
			this.trackLineMain.setForegroundColor(lineColor); // 主道岔部分
			break;			
		case ParamFlag.turnout_fw_black: //反位黑色
			this.status = -1;
			this.turnoutStatus = 0;
			this.mainLine.setForegroundColor(ColorConstants.black); // 主道岔
			this.changeLine.setForegroundColor(ColorConstants.black); // 道岔定位后隐藏的小线段
			this.trackLineChange.setForegroundColor(backgroundColor); // 道岔反位后隐藏的小线段
			this.trackLineMain.setForegroundColor(lineColor); // 主道岔部分
			break;
			
		}

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Figure getPanel() {
		return panel;
	}

	public void setPanel(Figure panel) {
		this.panel = panel;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public TurnoutNameLabel getNameLabel() {
		return nameLabel;
	}

	public void setNameLabel(TurnoutNameLabel nameLabel) {
		this.nameLabel = nameLabel;
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
