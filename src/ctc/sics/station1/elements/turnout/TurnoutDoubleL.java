package ctc.sics.station1.elements.turnout;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;

import ctc.sics.station1.elements.common.*;
import ctc.sics.station1.data.*;

/**
 * 左倾斜双开道岔
 * 
 * @author 胡恩召
 * 
 */
public class TurnoutDoubleL extends PolylineConnection {

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

	private String name;
	private String turnoutNameL; // 上半部看的左边道岔名称
	private String turnoutNameR; // 上半部看的右边道岔名称

	public String type;	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	private PolylineConnection mainLineL = new PolylineConnection(); //主道岔(左倾斜);
	
	private PolylineConnection trackLineS = new PolylineConnection(); // 股道(上)
	private PolylineConnection trackLineX = new PolylineConnection(); // 股道(下)

	private PolylineConnection trackLineSL = new PolylineConnection(); // 股道(上左)
	private PolylineConnection trackLineSR = new PolylineConnection(); //股道(上右);
	private PolylineConnection trackLineXL = new PolylineConnection(); //股道(下右);
	private PolylineConnection trackLineXR = new PolylineConnection(); //股道(下右);

	private PolylineConnection changeLineSL = new PolylineConnection(); //变化小段(上左
	private PolylineConnection changeLineXR = new PolylineConnection(); //变化小段(下右

	public TurnoutNameLabel LSnameLabel;
	public TurnoutNameLabel RSnameLabel;
	public TurnoutNameLabel LXnameLabel;
	public TurnoutNameLabel RXnameLabel;

	/**
	 * 新建双开联动道岔
	 */
	public TurnoutDoubleL(String name, String nameLS, String nameRX, int initX, int initY, String type) {

		this.type = type;		
		this.name = name;

		LSnameLabel = new TurnoutNameLabel(nameLS, initX - h / 2 - nameLS.length()*3 + 3, initY - h / 3 + 3);
		RXnameLabel = new TurnoutNameLabel(nameRX, initX + h / 2 - 10, initY + h / 3 - 8);

		// 主道岔(左倾斜)
		mainLineL.setForegroundColor(lineColor);
		mainLineL.setStart(new Point(initX - h / 3, initY - h / 3));
		mainLineL.setEnd(new Point(initX + h / 3, initY + h / 3));
		mainLineL.setLineWidth(lineWidth);
		panel.add(mainLineL);

		// 股道(上)
		trackLineS.setForegroundColor(lineColor);
		trackLineS.setStart(new Point(initX - h / 3, initY - h / 2));
		trackLineS.setEnd(new Point(initX + h / 2, initY - h / 2));
		trackLineS.setLineWidth(lineWidth);
		panel.add(trackLineS);
		// 股道(上左)
		trackLineSL.setForegroundColor(lineColor);
		trackLineSL.setStart(new Point(initX - h / 2, initY - h / 2));
		trackLineSL.setEnd(new Point(initX - h / 3, initY - h / 2));
		trackLineSL.setLineWidth(lineWidth);
		panel.add(trackLineSL);
		// 股道(下)
		trackLineX.setForegroundColor(lineColor);
		trackLineX.setStart(new Point(initX - h / 2, initY + h / 2));
		trackLineX.setEnd(new Point(initX + h / 3, initY + h / 2));
		trackLineX.setLineWidth(lineWidth);
		panel.add(trackLineX);
		// 股道(下右)
		trackLineXR.setForegroundColor(lineColor);
		trackLineXR.setStart(new Point(initX + h / 2, initY + h / 2));
		trackLineXR.setEnd(new Point(initX + h / 3, initY + h / 2));
		trackLineXR.setLineWidth(lineWidth);
		panel.add(trackLineXR);
		
		// 变化小段(上左)
		changeLineSL.setForegroundColor(backgroundColor);
		changeLineSL.setStart(new Point(initX - h / 3, initY - h / 3));
		changeLineSL.setEnd(new Point(initX - h / 2, initY - h / 2));
		changeLineSL.setLineWidth(lineWidth);
		panel.add(changeLineSL);
		// 变化小段(下右)
		changeLineXR.setForegroundColor(backgroundColor);
		changeLineXR.setStart(new Point(initX + h / 3, initY + h / 3));
		changeLineXR.setEnd(new Point(initX + h / 2, initY + h / 2));
		changeLineXR.setLineWidth(lineWidth);
		panel.add(changeLineXR);

	}

	/**
	 * 改变道岔状态
	 */
	public void setColorStatus(int color) {

		switch(color){
		
		case ParamFlag.turnout_red: //锁闭
			this.status = 1;			
			this.mainLineL.setForegroundColor(ColorConstants.red); //主道岔(左倾斜);		
			break;
		case ParamFlag.turnout_blue: //解锁
			this.status = 0;			
			this.mainLineL.setForegroundColor(lineColor); //主道岔(左倾斜);	
			break;			
			
		case ParamFlag.turnout_dw_bule: //定位蓝色
			this.status = 0;
			this.turnoutStatus = 1;
			this.mainLineL.setForegroundColor(lineColor); //主道岔(左倾斜);
			
			this.trackLineS.setForegroundColor(lineColor); // 股道(上)
			this.trackLineSL.setForegroundColor(lineColor); //股道(上左)
			this.trackLineSR.setForegroundColor(lineColor); //股道(上右);
			
			this.trackLineX.setForegroundColor(lineColor); // 股道(下)			
			this.trackLineXL.setForegroundColor(lineColor); //股道(下右);
			this.trackLineXR.setForegroundColor(lineColor); //股道(下右);

			this.changeLineSL.setForegroundColor(backgroundColor); //变化小段(上左			
			this.changeLineXR.setForegroundColor(backgroundColor); //变化小段(下右
			break;
		case ParamFlag.turnout_dw_bule_s: //定位蓝色
			this.status = 0;
			this.turnoutStatus = 1;
			this.mainLineL.setForegroundColor(lineColor); //主道岔(左倾斜);
			
			this.trackLineS.setForegroundColor(lineColor); // 股道(上)
			this.trackLineSL.setForegroundColor(lineColor); //股道(上左)
			this.trackLineSR.setForegroundColor(lineColor); //股道(上右);
/*			
			this.trackLineX.setForegroundColor(lineColor); // 股道(下)			
			this.trackLineXL.setForegroundColor(lineColor); //股道(下右);
			this.trackLineXR.setForegroundColor(lineColor); //股道(下右);
*/
			this.changeLineSL.setForegroundColor(backgroundColor); //变化小段(上左			
			this.changeLineXR.setForegroundColor(backgroundColor); //变化小段(下右
			break;
		case ParamFlag.turnout_dw_bule_x: //定位蓝色
			this.status = 0;
			this.turnoutStatus = 1;
			this.mainLineL.setForegroundColor(lineColor); //主道岔(左倾斜);
/*			
			this.trackLineS.setForegroundColor(lineColor); // 股道(上)
			this.trackLineSL.setForegroundColor(lineColor); //股道(上左)
			this.trackLineSR.setForegroundColor(lineColor); //股道(上右);
*/		
			this.trackLineX.setForegroundColor(lineColor); // 股道(下)			
			this.trackLineXL.setForegroundColor(lineColor); //股道(下右);
			this.trackLineXR.setForegroundColor(lineColor); //股道(下右);

			this.changeLineSL.setForegroundColor(backgroundColor); //变化小段(上左			
			this.changeLineXR.setForegroundColor(backgroundColor); //变化小段(下右
			break;
		case ParamFlag.turnout_dw_black: //定位黑色
			this.status = -1;
			this.turnoutStatus = 1;
			this.mainLineL.setForegroundColor(ColorConstants.black); //主道岔(左倾斜);			

			this.trackLineS.setForegroundColor(lineColor); // 股道(上)
			this.trackLineSL.setForegroundColor(lineColor); //股道(上左)
			this.trackLineSR.setForegroundColor(lineColor); //股道(上右);
			
			this.trackLineX.setForegroundColor(lineColor); // 股道(下)			
			this.trackLineXL.setForegroundColor(lineColor); //股道(下右);
			this.trackLineXR.setForegroundColor(lineColor); //股道(下右);

			this.changeLineSL.setForegroundColor(backgroundColor); //变化小段(上左			
			this.changeLineXR.setForegroundColor(backgroundColor); //变化小段(下右
			break;
		case ParamFlag.turnout_dw_green_s: //定位绿色(上)
			this.status = 0;
			this.turnoutStatus = 1;
			this.mainLineL.setForegroundColor(lineColor); //主道岔(左倾斜);
			
			this.trackLineS.setForegroundColor(ColorConstants.green); // 股道(上)
			this.trackLineSL.setForegroundColor(ColorConstants.green); //股道(上左)
			this.trackLineSR.setForegroundColor(ColorConstants.green); //股道(上右);
/*			
			this.trackLineX.setForegroundColor(lineColor); // 股道(下)			
			this.trackLineXL.setForegroundColor(lineColor); //股道(下右);
			this.trackLineXR.setForegroundColor(lineColor); //股道(下右);
*/
			this.changeLineSL.setForegroundColor(backgroundColor); //变化小段(上左		
			this.changeLineXR.setForegroundColor(backgroundColor); //变化小段(下右
			break;
		case ParamFlag.turnout_dw_green_x: //定位绿色(下)
			this.status = 0;
			this.turnoutStatus = 1;
			this.mainLineL.setForegroundColor(lineColor); //主道岔(左倾斜);			
/*
			this.trackLineS.setForegroundColor(lineColor); // 股道(上)
			this.trackLineSL.setForegroundColor(lineColor); //股道(上左)
			this.trackLineSR.setForegroundColor(lineColor); //股道(上右);
*/		
			this.trackLineX.setForegroundColor(ColorConstants.green); // 股道(下)			
			this.trackLineXL.setForegroundColor(ColorConstants.green); //股道(下右);
			this.trackLineXR.setForegroundColor(ColorConstants.green); //股道(下右);

			this.changeLineSL.setForegroundColor(backgroundColor); //变化小段(上左			
			this.changeLineXR.setForegroundColor(backgroundColor); //变化小段(下右
			break;
		case ParamFlag.turnout_dw_red_s: //定位红色(上)
			this.status = 0;
			this.turnoutStatus = 1;
			this.mainLineL.setForegroundColor(lineColor); //主道岔(左倾斜);
			
			this.trackLineS.setForegroundColor(ColorConstants.red); // 股道(上)
			this.trackLineSL.setForegroundColor(ColorConstants.red); //股道(上左)
			this.trackLineSR.setForegroundColor(ColorConstants.red); //股道(上右);
/*			
			this.trackLineX.setForegroundColor(lineColor); // 股道(下)			
			this.trackLineXL.setForegroundColor(lineColor); //股道(下右);
			this.trackLineXR.setForegroundColor(lineColor); //股道(下右);
*/
			this.changeLineSL.setForegroundColor(backgroundColor); //变化小段(上左
			this.changeLineXR.setForegroundColor(backgroundColor); //变化小段(下右
			break;
		case ParamFlag.turnout_dw_red_x: //定位红色(下)
			this.status = 0;
			this.turnoutStatus = 1;
			this.mainLineL.setForegroundColor(lineColor); //主道岔(左倾斜);		
/*
			this.trackLineS.setForegroundColor(lineColor); // 股道(上)
			this.trackLineSL.setForegroundColor(lineColor); //股道(上左)
			this.trackLineSR.setForegroundColor(lineColor); //股道(上右);
*/		
			this.trackLineX.setForegroundColor(ColorConstants.red); // 股道(下)			
			this.trackLineXL.setForegroundColor(ColorConstants.red); //股道(下右);
			this.trackLineXR.setForegroundColor(ColorConstants.red); //股道(下右);

			this.changeLineSL.setForegroundColor(backgroundColor); //变化小段(上左		
			this.changeLineXR.setForegroundColor(backgroundColor); //变化小段(下右
			break;
			
		
		case ParamFlag.turnout_fw_bule: //反位蓝色
			this.status = 0;
			this.turnoutStatus = 0;
			this.mainLineL.setForegroundColor(lineColor); //主道岔(左倾斜);		

			this.trackLineS.setForegroundColor(lineColor); // 股道(上)
			this.trackLineSL.setForegroundColor(backgroundColor); //股道(上左)
			this.trackLineSR.setForegroundColor(backgroundColor); //股道(上右);
			
			this.trackLineX.setForegroundColor(lineColor); // 股道(下)			
			this.trackLineXL.setForegroundColor(backgroundColor); //股道(下右);
			this.trackLineXR.setForegroundColor(backgroundColor); //股道(下右);

			this.changeLineSL.setForegroundColor(lineColor); //变化小段(上左			
			this.changeLineXR.setForegroundColor(lineColor); //变化小段(下右
			break;
		case ParamFlag.turnout_fw_green: //反位绿色(左)
			this.status = 1;
			this.turnoutStatus = 0;
			this.mainLineL.setForegroundColor(ColorConstants.green); //主道岔(左倾斜);			

			this.trackLineS.setForegroundColor(lineColor); // 股道(上)
			this.trackLineSL.setForegroundColor(backgroundColor); //股道(上左)
			this.trackLineSR.setForegroundColor(backgroundColor); //股道(上右);
			
			this.trackLineX.setForegroundColor(lineColor); // 股道(下)			
			this.trackLineXL.setForegroundColor(backgroundColor); //股道(下右);
			this.trackLineXR.setForegroundColor(backgroundColor); //股道(下右);

			this.changeLineSL.setForegroundColor(ColorConstants.green); //变化小段(上左		
			this.changeLineXR.setForegroundColor(ColorConstants.green); //变化小段(下右
			break;
		case ParamFlag.turnout_fw_red: //反位红色(左)
			this.status = 2;
			this.turnoutStatus = 0;
			this.mainLineL.setForegroundColor(ColorConstants.red); //主道岔(左倾斜);			

			this.trackLineS.setForegroundColor(lineColor); // 股道(上)
			this.trackLineSL.setForegroundColor(backgroundColor); //股道(上左)
			this.trackLineSR.setForegroundColor(backgroundColor); //股道(上右);
			
			this.trackLineX.setForegroundColor(lineColor); // 股道(下)			
			this.trackLineXL.setForegroundColor(backgroundColor); //股道(下右);
			this.trackLineXR.setForegroundColor(backgroundColor); //股道(下右);

			this.changeLineSL.setForegroundColor(ColorConstants.red); //变化小段(上左			
			this.changeLineXR.setForegroundColor(ColorConstants.red); //变化小段(下右
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

	public PolylineConnection getMainLineL() {
		return mainLineL;
	}

	public void setMainLineL(PolylineConnection mainLineL) {
		this.mainLineL = mainLineL;
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

	public PolylineConnection getChangeLineSL() {
		return changeLineSL;
	}

	public void setChangeLineSL(PolylineConnection changeLineSL) {
		this.changeLineSL = changeLineSL;
	}

	public PolylineConnection getChangeLineXR() {
		return changeLineXR;
	}

	public void setChangeLineXR(PolylineConnection changeLineXR) {
		this.changeLineXR = changeLineXR;
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

}
