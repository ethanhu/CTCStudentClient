package ctc.sics.station.elements.turnout;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;

import ctc.sics.station.elements.common.*;
import ctc.sics.station.data.*;

/**
 * 右倾斜双开道岔
 * 
 * @author 胡恩召
 * 
 */
public class TurnoutDoubleR extends PolylineConnection {

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

	private int status = 0; // 道岔的状态：可使用0, 锁定中1, 坏了:-1
	private int statusL = 0; // 上半部看的左边道岔的状态：可使用0, 锁定中1, 坏了:-1
	private int statucR = 0; // 上半部看的右边道岔的状态：可使用0, 锁定中1, 坏了:-1
	public int turnoutStatus = 1; // 道岔状态：定位1, 反位0
	public String trainName; //车次名
	public String getTrainName() {
		return trainName;
	}
	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}
	private String name;

	public String type;	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	private String turnoutNameL; // 上半部看的左边道岔名称
	private String turnoutNameR; // 上半部看的右边道岔名称

	private PolylineConnection mainLineR = new PolylineConnection(); //主道岔(左倾斜);

	private PolylineConnection trackLineS = new PolylineConnection(); // 股道(上)
	private PolylineConnection trackLineX = new PolylineConnection(); // 股道(下)

	private PolylineConnection trackLineSL = new PolylineConnection(); // 股道(上左)
	private PolylineConnection trackLineSR = new PolylineConnection(); //股道(上右);
	private PolylineConnection trackLineXL = new PolylineConnection(); //股道(下右);
	private PolylineConnection trackLineXR = new PolylineConnection(); //股道(下右);
	
	private PolylineConnection changeLineSR = new PolylineConnection(); //变化小段(上右
	private PolylineConnection changeLineXL = new PolylineConnection(); //变化小段(下左
	
	public TurnoutNameLabel LSnameLabel;
	public TurnoutNameLabel RSnameLabel;
	public TurnoutNameLabel LXnameLabel;
	public TurnoutNameLabel RXnameLabel;

	/**
	 * 新建双开联动道岔
	 */
	public TurnoutDoubleR(String name, String nameRS, String nameLX, int initX, int initY, String type) {
		
		this.type = type;
		this.name = name;

		RSnameLabel = new TurnoutNameLabel(nameRS, initX + h / 2 - 8, initY - h / 3 + 3);
		LXnameLabel = new TurnoutNameLabel(nameLX, initX - h / 2 - nameLX.length()*3 + 3, initY + h / 3 - 9);

		// 主道岔(右倾斜)
		mainLineR.setForegroundColor(lineColor);
		mainLineR.setStart(new Point(initX + h / 3, initY - h / 3));
		mainLineR.setEnd(new Point(initX - h / 3, initY + h / 3));
		mainLineR.setLineWidth(lineWidth);
		panel.add(mainLineR);
		// 股道(上)
		trackLineS.setForegroundColor(lineColor);
		trackLineS.setStart(new Point(initX - h / 2, initY - h / 2));
		trackLineS.setEnd(new Point(initX + h / 3, initY - h / 2));
		trackLineS.setLineWidth(lineWidth);
		panel.add(trackLineS);
		// 股道(上右)
		trackLineSR.setForegroundColor(lineColor);
		trackLineSR.setStart(new Point(initX + h / 2, initY - h / 2));
		trackLineSR.setEnd(new Point(initX + h / 3, initY - h / 2));
		trackLineSR.setLineWidth(lineWidth);
		panel.add(trackLineSR);
		// 股道(下)
		trackLineX.setForegroundColor(lineColor);
		trackLineX.setStart(new Point(initX - h / 3, initY + h / 2));
		trackLineX.setEnd(new Point(initX + h / 2, initY + h / 2));
		trackLineX.setLineWidth(lineWidth);
		panel.add(trackLineX);
		// 股道(下左)
		trackLineXL.setForegroundColor(lineColor);
		trackLineXL.setStart(new Point(initX - h / 2, initY + h / 2));
		trackLineXL.setEnd(new Point(initX - h / 3, initY + h / 2));
		trackLineXL.setLineWidth(lineWidth);
		panel.add(trackLineXL);

		// 变化小段(上右)
		changeLineSR.setForegroundColor(backgroundColor);
		changeLineSR.setStart(new Point(initX + h / 3, initY - h / 3));
		changeLineSR.setEnd(new Point(initX + h / 2, initY - h / 2));
		changeLineSR.setLineWidth(lineWidth);
		panel.add(changeLineSR);
		// 变化小段(下左)
		changeLineXL.setForegroundColor(backgroundColor);
		changeLineXL.setStart(new Point(initX - h / 3, initY + h / 3));
		changeLineXL.setEnd(new Point(initX - h / 2, initY + h / 2));
		changeLineXL.setLineWidth(lineWidth);
		panel.add(changeLineXL);

	}

	/**
	 * 改变道岔状态
	 */
	public void setColorStatus(int color) {

		switch(color){

		case ParamFlag.turnout_red: //锁闭
			this.status = 1;			
			this.mainLineR.setForegroundColor(ColorConstants.red); //主道岔(左倾斜);		
			break;		
		case ParamFlag.turnout_blue: //解锁
			this.status = 0;			
			this.mainLineR.setForegroundColor(lineColor); //主道岔(左倾斜);		
			break;		
			
		case ParamFlag.turnout_dw_bule: //定位蓝色
			this.status = 0;		
			this.turnoutStatus = 1;
			this.mainLineR.setForegroundColor(lineColor); //主道岔(左倾斜);

			this.trackLineS.setForegroundColor(lineColor); // 股道(上)
			this.trackLineSL.setForegroundColor(lineColor); //股道(上左)
			this.trackLineSR.setForegroundColor(lineColor); //股道(上右);
			
			this.trackLineX.setForegroundColor(lineColor); // 股道(下)			
			this.trackLineXL.setForegroundColor(lineColor); //股道(下右);
			this.trackLineXR.setForegroundColor(lineColor); //股道(下右);
			
			this.changeLineSR.setForegroundColor(backgroundColor); //变化小段(上右
			this.changeLineXL.setForegroundColor(backgroundColor); //变化小段(下左			
			break;
		case ParamFlag.turnout_dw_bule_s: //定位蓝色
			this.status = 0;		
			this.turnoutStatus = 1;
			this.mainLineR.setForegroundColor(lineColor); //主道岔(左倾斜);

			this.trackLineS.setForegroundColor(lineColor); // 股道(上)
			this.trackLineSL.setForegroundColor(lineColor); //股道(上左)
			this.trackLineSR.setForegroundColor(lineColor); //股道(上右);
/*			
			this.trackLineX.setForegroundColor(lineColor); // 股道(下)			
			this.trackLineXL.setForegroundColor(lineColor); //股道(下右);
			this.trackLineXR.setForegroundColor(lineColor); //股道(下右);
*/		
			this.changeLineSR.setForegroundColor(backgroundColor); //变化小段(上右
			this.changeLineXL.setForegroundColor(backgroundColor); //变化小段(下左			
			break;
		case ParamFlag.turnout_dw_bule_x: //定位蓝色
			this.status = 0;			
			this.turnoutStatus = 1;
			this.mainLineR.setForegroundColor(lineColor); //主道岔(左倾斜);
/*
			this.trackLineS.setForegroundColor(lineColor); // 股道(上)
			this.trackLineSL.setForegroundColor(lineColor); //股道(上左)
			this.trackLineSR.setForegroundColor(lineColor); //股道(上右);
*/		
			this.trackLineX.setForegroundColor(lineColor); // 股道(下)			
			this.trackLineXL.setForegroundColor(lineColor); //股道(下右);
			this.trackLineXR.setForegroundColor(lineColor); //股道(下右);
			
			this.changeLineSR.setForegroundColor(backgroundColor); //变化小段(上右
			this.changeLineXL.setForegroundColor(backgroundColor); //变化小段(下左			
			break;
		case ParamFlag.turnout_dw_white: //定位黑色
			this.status = -1;
			this.turnoutStatus = 1;
			this.mainLineR.setForegroundColor(ColorConstants.white); //主道岔(左倾斜);

			this.trackLineS.setForegroundColor(lineColor); // 股道(上)
			this.trackLineSL.setForegroundColor(lineColor); //股道(上左)
			this.trackLineSR.setForegroundColor(lineColor); //股道(上右);
			
			this.trackLineX.setForegroundColor(lineColor); // 股道(下)			
			this.trackLineXL.setForegroundColor(lineColor); //股道(下右);
			this.trackLineXR.setForegroundColor(lineColor); //股道(下右);
			
			this.changeLineSR.setForegroundColor(backgroundColor); //变化小段(上右
			this.changeLineXL.setForegroundColor(backgroundColor); //变化小段(下左
			break;
		case ParamFlag.turnout_dw_green_s: //定位绿色(上)
			this.status = 0;	
			this.turnoutStatus = 1;
			this.mainLineR.setForegroundColor(lineColor); //主道岔(左倾斜);

			this.trackLineS.setForegroundColor(ColorConstants.green); // 股道(上)
			this.trackLineSL.setForegroundColor(ColorConstants.green); //股道(上左)
			this.trackLineSR.setForegroundColor(ColorConstants.green); //股道(上右);
/*			
			this.trackLineX.setForegroundColor(lineColor); // 股道(下)			
			this.trackLineXL.setForegroundColor(lineColor); //股道(下右);
			this.trackLineXR.setForegroundColor(lineColor); //股道(下右);
*/		
			this.changeLineSR.setForegroundColor(backgroundColor); //变化小段(上右
			this.changeLineXL.setForegroundColor(backgroundColor); //变化小段(下左			
			break;
		case ParamFlag.turnout_dw_green_x: //定位绿色(下)
			this.status = 0;		
			this.turnoutStatus = 1;
			this.mainLineR.setForegroundColor(lineColor); //主道岔(左倾斜);
/*
			this.trackLineS.setForegroundColor(lineColor); // 股道(上)
			this.trackLineSL.setForegroundColor(lineColor); //股道(上左)
			this.trackLineSR.setForegroundColor(lineColor); //股道(上右);
*/		
			this.trackLineX.setForegroundColor(ColorConstants.green); // 股道(下)			
			this.trackLineXL.setForegroundColor(ColorConstants.green); //股道(下右);
			this.trackLineXR.setForegroundColor(ColorConstants.green); //股道(下右);

			this.changeLineSR.setForegroundColor(backgroundColor); //变化小段(上右
			this.changeLineXL.setForegroundColor(backgroundColor); //变化小段(下左			
			break;
		case ParamFlag.turnout_dw_red_s: //定位红色(上)
			this.status = 0;	
			this.turnoutStatus = 1;
			this.mainLineR.setForegroundColor(lineColor); //主道岔(左倾斜);

			this.trackLineS.setForegroundColor(ColorConstants.red); // 股道(上)
			this.trackLineSL.setForegroundColor(ColorConstants.red); //股道(上左)
			this.trackLineSR.setForegroundColor(ColorConstants.red); //股道(上右);
/*			
			this.trackLineX.setForegroundColor(lineColor); // 股道(下)			
			this.trackLineXL.setForegroundColor(lineColor); //股道(下右);
			this.trackLineXR.setForegroundColor(lineColor); //股道(下右);
*/
			this.changeLineSR.setForegroundColor(backgroundColor); //变化小段(上右
			this.changeLineXL.setForegroundColor(backgroundColor); //变化小段(下左			
			break;
		case ParamFlag.turnout_dw_red_x: //定位红色(下)
			this.status = 0;		
			this.turnoutStatus = 1;
			this.mainLineR.setForegroundColor(lineColor); //主道岔(左倾斜);
/*
			this.trackLineS.setForegroundColor(lineColor); // 股道(上)
			this.trackLineSL.setForegroundColor(lineColor); //股道(上左)
			this.trackLineSR.setForegroundColor(lineColor); //股道(上右);
*/		
			this.trackLineX.setForegroundColor(ColorConstants.red); // 股道(下)			
			this.trackLineXL.setForegroundColor(ColorConstants.red); //股道(下右);
			this.trackLineXR.setForegroundColor(ColorConstants.red); //股道(下右);
		
			this.changeLineSR.setForegroundColor(backgroundColor); //变化小段(上右
			this.changeLineXL.setForegroundColor(backgroundColor); //变化小段(下左			
			break;
			
		
		case ParamFlag.turnout_fw_bule: //反位蓝色
			this.status = 0;		
			this.turnoutStatus = 0;
			this.mainLineR.setForegroundColor(lineColor); //主道岔(左倾斜);

			this.trackLineS.setForegroundColor(lineColor); // 股道(上)
			this.trackLineSL.setForegroundColor(backgroundColor); //股道(上左)
			this.trackLineSR.setForegroundColor(backgroundColor); //股道(上右);
			
			this.trackLineX.setForegroundColor(lineColor); // 股道(下)			
			this.trackLineXL.setForegroundColor(backgroundColor); //股道(下右);
			this.trackLineXR.setForegroundColor(backgroundColor); //股道(下右);

			this.changeLineSR.setForegroundColor(lineColor); //变化小段(上右
			this.changeLineXL.setForegroundColor(lineColor); //变化小段(下左			
			break;		
		case ParamFlag.turnout_fw_green: //反位绿色(右)
			this.status = 1;
			this.turnoutStatus = 0;
			this.mainLineR.setForegroundColor(ColorConstants.green); //主道岔(左倾斜);

			this.trackLineS.setForegroundColor(lineColor); // 股道(上)
			this.trackLineSL.setForegroundColor(backgroundColor); //股道(上左)
			this.trackLineSR.setForegroundColor(backgroundColor); //股道(上右);
			
			this.trackLineX.setForegroundColor(lineColor); // 股道(下)			
			this.trackLineXL.setForegroundColor(backgroundColor); //股道(下右);
			this.trackLineXR.setForegroundColor(backgroundColor); //股道(下右);

			this.changeLineSR.setForegroundColor(ColorConstants.green); //变化小段(上右
			this.changeLineXL.setForegroundColor(ColorConstants.green); //变化小段(下左
			break;
		case ParamFlag.turnout_fw_red: //反位红色(右)
			this.status = 2;
			this.turnoutStatus = 0;
			this.mainLineR.setForegroundColor(ColorConstants.red); //主道岔(左倾斜);

			this.trackLineS.setForegroundColor(lineColor); // 股道(上)
			this.trackLineSL.setForegroundColor(backgroundColor); //股道(上左)
			this.trackLineSR.setForegroundColor(backgroundColor); //股道(上右);
			
			this.trackLineX.setForegroundColor(lineColor); // 股道(下)			
			this.trackLineXL.setForegroundColor(backgroundColor); //股道(下右);
			this.trackLineXR.setForegroundColor(backgroundColor); //股道(下右);

			this.changeLineSR.setForegroundColor(ColorConstants.red); //变化小段(上右
			this.changeLineXL.setForegroundColor(ColorConstants.red); //变化小段(下左			
			break;		
			
		} 

	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getStatusL() {
		return statusL;
	}

	public void setStatusL(int statusL) {
		this.statusL = statusL;
	}

	public int getStatucR() {
		return statucR;
	}

	public void setStatucR(int statucR) {
		this.statucR = statucR;
	}

	public int getTurnoutStatus() {
		return turnoutStatus;
	}

	public void setTurnoutStatus(int turnoutStatus) {
		this.turnoutStatus = turnoutStatus;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public Figure getPanel() {
		return panel;
	}

	public void setPanel(Figure panel) {
		this.panel = panel;
	}

	public TurnoutNameLabel getLSnameLabel() {
		return LSnameLabel;
	}

	public void setLSnameLabel(TurnoutNameLabel snameLabel) {
		LSnameLabel = snameLabel;
	}

	public TurnoutNameLabel getRSnameLabel() {
		return RSnameLabel;
	}

	public void setRSnameLabel(TurnoutNameLabel snameLabel) {
		RSnameLabel = snameLabel;
	}

	public TurnoutNameLabel getLXnameLabel() {
		return LXnameLabel;
	}

	public void setLXnameLabel(TurnoutNameLabel xnameLabel) {
		LXnameLabel = xnameLabel;
	}

	public TurnoutNameLabel getRXnameLabel() {
		return RXnameLabel;
	}

	public void setRXnameLabel(TurnoutNameLabel xnameLabel) {
		RXnameLabel = xnameLabel;
	}

	public String getTurnoutNameL() {
		return turnoutNameL;
	}

	public void setTurnoutNameL(String turnoutNameL) {
		this.turnoutNameL = turnoutNameL;
	}

	public String getTurnoutNameR() {
		return turnoutNameR;
	}

	public void setTurnoutNameR(String turnoutNameR) {
		this.turnoutNameR = turnoutNameR;
	}

	public PolylineConnection getMainLineR() {
		return mainLineR;
	}

	public void setMainLineR(PolylineConnection mainLineR) {
		this.mainLineR = mainLineR;
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

	public PolylineConnection getTrackLineSL() {
		return trackLineSL;
	}

	public void setTrackLineSL(PolylineConnection trackLineSL) {
		this.trackLineSL = trackLineSL;
	}

	public PolylineConnection getTrackLineSR() {
		return trackLineSR;
	}

	public void setTrackLineSR(PolylineConnection trackLineSR) {
		this.trackLineSR = trackLineSR;
	}

	public PolylineConnection getTrackLineXL() {
		return trackLineXL;
	}

	public void setTrackLineXL(PolylineConnection trackLineXL) {
		this.trackLineXL = trackLineXL;
	}

	public PolylineConnection getTrackLineXR() {
		return trackLineXR;
	}

	public void setTrackLineXR(PolylineConnection trackLineXR) {
		this.trackLineXR = trackLineXR;
	}

	public PolylineConnection getChangeLineSR() {
		return changeLineSR;
	}

	public void setChangeLineSR(PolylineConnection changeLineSR) {
		this.changeLineSR = changeLineSR;
	}

	public PolylineConnection getChangeLineXL() {
		return changeLineXL;
	}

	public void setChangeLineXL(PolylineConnection changeLineXL) {
		this.changeLineXL = changeLineXL;
	}

}
