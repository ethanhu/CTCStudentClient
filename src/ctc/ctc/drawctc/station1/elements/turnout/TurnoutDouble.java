package ctc.ctc.drawctc.station1.elements.turnout;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Point;

import ctc.ctc.drawctc.station1.data.BaseParam;
import ctc.ctc.drawctc.station1.elements.common.*;
import ctc.ctc.drawctc.station1.data.*;

public class TurnoutDouble extends PolylineConnection {

	BaseParam baseDataForStation = BaseParam.getInstance();

	private int status = 0; // 道岔的状态：可使用0, 锁定中1, 坏了:-1
	private int statusL = 0; // 上半部看的左边道岔的状态：可使用0, 锁定中1, 坏了:-1
	private int statucR = 0; // 上半部看的右边道岔的状态：可使用0, 锁定中1, 坏了:-1
	public int turnoutStatus = 1; // 道岔状态：定位1, 反位0

	public String type;	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public int h;
	public Figure panel;
	public NameLabel LSnameLabel;
	public NameLabel RSnameLabel;
	public NameLabel LXnameLabel;
	public NameLabel RXnameLabel;

	private String name;
	private String turnoutNameL; // 上半部看的左边道岔名称
	private String turnoutNameR; // 上半部看的右边道岔名称

	private PolylineConnection mainLineL = new PolylineConnection(); //主道岔(左倾斜);
	private PolylineConnection mainLineR = new PolylineConnection(); //主道岔(左倾斜);

	private PolylineConnection trackLineS = new PolylineConnection(); // 股道(上)
	private PolylineConnection trackLineX = new PolylineConnection(); // 股道(下)

	private PolylineConnection trackLineSL = new PolylineConnection(); // 股道(上左)
	private PolylineConnection trackLineSR = new PolylineConnection(); //股道(上右);
	private PolylineConnection trackLineXL = new PolylineConnection(); //股道(下右);
	private PolylineConnection trackLineXR = new PolylineConnection(); //股道(下右);

	private PolylineConnection changeLineSL = new PolylineConnection(); //变化小段(上左
	private PolylineConnection changeLineSR = new PolylineConnection(); //变化小段(上右
	private PolylineConnection changeLineXL = new PolylineConnection(); //变化小段(下左
	private PolylineConnection changeLineXR = new PolylineConnection(); //变化小段(下右

	/**
	 * 新建双开联动道岔
	 */
	public TurnoutDouble(String name, String nameLS, String nameRX, String nameLX, String nameRS, int initX, int initY, String type) {

		this.type = type;
		int lineWidth = baseDataForStation.getLineWidth();
		h = baseDataForStation.getRowSpacing();
		panel = baseDataForStation.getPanel();

		this.name = name;		

		LSnameLabel = new NameLabel(nameLS, initX - h / 2 - baseDataForStation.getLabelWidth() / 2, initY - h / 3);
		RSnameLabel = new NameLabel(nameRS, initX + h / 2, initY - h / 3);
		LXnameLabel = new NameLabel(nameLX, initX - h / 2 - baseDataForStation.getLabelWidth() / 2, initY + h / 3 - 3);
		RXnameLabel = new NameLabel(nameRX, initX + h / 2, initY + h / 3 - 3);

		// 主道岔(左倾斜)
		mainLineL.setForegroundColor(ColorConstants.blue);
		mainLineL.setStart(new Point(initX - h / 3, initY - h / 3));
		mainLineL.setEnd(new Point(initX + h / 3, initY + h / 3));
		mainLineL.setLineWidth(lineWidth);
		panel.add(mainLineL);
		// 主道岔(左倾斜)
		mainLineR.setForegroundColor(ColorConstants.blue);
		mainLineR.setStart(new Point(initX + h / 3, initY - h / 3));
		mainLineR.setEnd(new Point(initX - h / 3, initY + h / 3));
		mainLineR.setLineWidth(lineWidth);
		panel.add(mainLineR);
		// 股道(上)
		trackLineS.setForegroundColor(ColorConstants.blue);
		trackLineS.setStart(new Point(initX - h / 3, initY - h / 2));
		trackLineS.setEnd(new Point(initX + h / 3, initY - h / 2));
		trackLineS.setLineWidth(lineWidth);
		panel.add(trackLineS);
		// 股道(下)
		trackLineX.setForegroundColor(ColorConstants.blue);
		trackLineX.setStart(new Point(initX - h / 3, initY + h / 2));
		trackLineX.setEnd(new Point(initX + h / 3, initY + h / 2));
		trackLineX.setLineWidth(lineWidth);
		panel.add(trackLineX);
		// 股道(上左)
		trackLineSL.setForegroundColor(ColorConstants.blue);
		trackLineSL.setStart(new Point(initX - h / 2, initY - h / 2));
		trackLineSL.setEnd(new Point(initX - h / 3, initY - h / 2));
		trackLineSL.setLineWidth(lineWidth);
		panel.add(trackLineSL);
		// 股道(上右)
		trackLineSR.setForegroundColor(ColorConstants.blue);
		trackLineSR.setStart(new Point(initX + h / 2, initY - h / 2));
		trackLineSR.setEnd(new Point(initX + h / 3, initY - h / 2));
		trackLineSR.setLineWidth(lineWidth);
		panel.add(trackLineSR);
		// 股道(下右)
		trackLineXL.setForegroundColor(ColorConstants.blue);
		trackLineXL.setStart(new Point(initX - h / 2, initY + h / 2));
		trackLineXL.setEnd(new Point(initX - h / 3, initY + h / 2));
		trackLineXL.setLineWidth(lineWidth);
		panel.add(trackLineXL);
		// 股道(下右)
		trackLineXR.setForegroundColor(ColorConstants.blue);
		trackLineXR.setStart(new Point(initX + h / 2, initY + h / 2));
		trackLineXR.setEnd(new Point(initX + h / 3, initY + h / 2));
		trackLineXR.setLineWidth(lineWidth);
		panel.add(trackLineXR);

		// 变化小段(上左)
		changeLineSL.setForegroundColor(ColorConstants.white);
		changeLineSL.setStart(new Point(initX - h / 3, initY - h / 3));
		changeLineSL.setEnd(new Point(initX - h / 2, initY - h / 2));
		changeLineSL.setLineWidth(lineWidth);
		panel.add(changeLineSL);
		// 变化小段(上右)
		changeLineSR.setForegroundColor(ColorConstants.white);
		changeLineSR.setStart(new Point(initX + h / 3, initY - h / 3));
		changeLineSR.setEnd(new Point(initX + h / 2, initY - h / 2));
		changeLineSR.setLineWidth(lineWidth);
		panel.add(changeLineSR);
		// 变化小段(下左)
		changeLineXL.setForegroundColor(ColorConstants.white);
		changeLineXL.setStart(new Point(initX - h / 3, initY + h / 3));
		changeLineXL.setEnd(new Point(initX - h / 2, initY + h / 2));
		changeLineXL.setLineWidth(lineWidth);
		panel.add(changeLineXL);
		// 变化小段(下右)
		changeLineXR.setForegroundColor(ColorConstants.white);
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
		
		case ParamFlag.turnout_dw_bule: //定位蓝色
			this.status = 0;
			this.turnoutStatus = 1;
			this.mainLineL.setForegroundColor(ColorConstants.blue); //主道岔(左倾斜);
			this.mainLineR.setForegroundColor(ColorConstants.blue); //主道岔(左倾斜);

			this.trackLineS.setForegroundColor(ColorConstants.blue); // 股道(上)
			this.trackLineSL.setForegroundColor(ColorConstants.blue); //股道(上左)
			this.trackLineSR.setForegroundColor(ColorConstants.blue); //股道(上右);
			
			this.trackLineX.setForegroundColor(ColorConstants.blue); // 股道(下)			
			this.trackLineXL.setForegroundColor(ColorConstants.blue); //股道(下右);
			this.trackLineXR.setForegroundColor(ColorConstants.blue); //股道(下右);

			this.changeLineSL.setForegroundColor(ColorConstants.white); //变化小段(上左
			this.changeLineSR.setForegroundColor(ColorConstants.white); //变化小段(上右
			this.changeLineXL.setForegroundColor(ColorConstants.white); //变化小段(下左
			this.changeLineXR.setForegroundColor(ColorConstants.white); //变化小段(下右
			break;
		case ParamFlag.turnout_dw_bule_s: //定位蓝色
			this.status = 0;
			this.turnoutStatus = 1;
			this.trackLineS.setForegroundColor(ColorConstants.blue); // 股道(上)
			this.trackLineSL.setForegroundColor(ColorConstants.blue); //股道(上左)
			this.trackLineSR.setForegroundColor(ColorConstants.blue); //股道(上右);
/*			
			this.trackLineX.setForegroundColor(ColorConstants.blue); // 股道(下)			
			this.trackLineXL.setForegroundColor(ColorConstants.blue); //股道(下右);
			this.trackLineXR.setForegroundColor(ColorConstants.blue); //股道(下右);
*/
			this.changeLineSL.setForegroundColor(ColorConstants.white); //变化小段(上左
			this.changeLineSR.setForegroundColor(ColorConstants.white); //变化小段(上右
			this.changeLineXL.setForegroundColor(ColorConstants.white); //变化小段(下左
			this.changeLineXR.setForegroundColor(ColorConstants.white); //变化小段(下右
			break;
		case ParamFlag.turnout_dw_bule_x: //定位蓝色
			this.status = 0;
			this.turnoutStatus = 1;
			this.mainLineL.setForegroundColor(ColorConstants.blue); //主道岔(左倾斜);
			this.mainLineR.setForegroundColor(ColorConstants.blue); //主道岔(左倾斜);
/*
			this.trackLineS.setForegroundColor(ColorConstants.blue); // 股道(上)
			this.trackLineSL.setForegroundColor(ColorConstants.blue); //股道(上左)
			this.trackLineSR.setForegroundColor(ColorConstants.blue); //股道(上右);
*/		
			this.trackLineX.setForegroundColor(ColorConstants.blue); // 股道(下)			
			this.trackLineXL.setForegroundColor(ColorConstants.blue); //股道(下右);
			this.trackLineXR.setForegroundColor(ColorConstants.blue); //股道(下右);

			this.changeLineSL.setForegroundColor(ColorConstants.white); //变化小段(上左
			this.changeLineSR.setForegroundColor(ColorConstants.white); //变化小段(上右
			this.changeLineXL.setForegroundColor(ColorConstants.white); //变化小段(下左
			this.changeLineXR.setForegroundColor(ColorConstants.white); //变化小段(下右
			break;
		case ParamFlag.turnout_dw_black: //定位黑色
			this.status = -1;
			this.turnoutStatus = 1;
			this.mainLineL.setForegroundColor(ColorConstants.black); //主道岔(左倾斜);
			this.mainLineR.setForegroundColor(ColorConstants.black); //主道岔(左倾斜);

			this.trackLineS.setForegroundColor(ColorConstants.blue); // 股道(上)
			this.trackLineSL.setForegroundColor(ColorConstants.blue); //股道(上左)
			this.trackLineSR.setForegroundColor(ColorConstants.blue); //股道(上右);
			
			this.trackLineX.setForegroundColor(ColorConstants.blue); // 股道(下)			
			this.trackLineXL.setForegroundColor(ColorConstants.blue); //股道(下右);
			this.trackLineXR.setForegroundColor(ColorConstants.blue); //股道(下右);

			this.changeLineSL.setForegroundColor(ColorConstants.white); //变化小段(上左
			this.changeLineSR.setForegroundColor(ColorConstants.white); //变化小段(上右
			this.changeLineXL.setForegroundColor(ColorConstants.white); //变化小段(下左
			this.changeLineXR.setForegroundColor(ColorConstants.white); //变化小段(下右
			break;
		case ParamFlag.turnout_dw_green_s: //定位绿色(上)
			this.status = 0;
			this.turnoutStatus = 1;
			this.mainLineL.setForegroundColor(ColorConstants.blue); //主道岔(左倾斜);
			this.mainLineR.setForegroundColor(ColorConstants.blue); //主道岔(左倾斜);

			this.trackLineS.setForegroundColor(ColorConstants.green); // 股道(上)
			this.trackLineSL.setForegroundColor(ColorConstants.green); //股道(上左)
			this.trackLineSR.setForegroundColor(ColorConstants.green); //股道(上右);
/*			
			this.trackLineX.setForegroundColor(ColorConstants.blue); // 股道(下)			
			this.trackLineXL.setForegroundColor(ColorConstants.blue); //股道(下右);
			this.trackLineXR.setForegroundColor(ColorConstants.blue); //股道(下右);
*/
			this.changeLineSL.setForegroundColor(ColorConstants.white); //变化小段(上左
			this.changeLineSR.setForegroundColor(ColorConstants.white); //变化小段(上右
			this.changeLineXL.setForegroundColor(ColorConstants.white); //变化小段(下左
			this.changeLineXR.setForegroundColor(ColorConstants.white); //变化小段(下右
			break;
		case ParamFlag.turnout_dw_green_x: //定位绿色(下)
			this.status = 0;
			this.turnoutStatus = 1;
			this.mainLineL.setForegroundColor(ColorConstants.blue); //主道岔(左倾斜);
			this.mainLineR.setForegroundColor(ColorConstants.blue); //主道岔(左倾斜);
/*
			this.trackLineS.setForegroundColor(ColorConstants.blue); // 股道(上)
			this.trackLineSL.setForegroundColor(ColorConstants.blue); //股道(上左)
			this.trackLineSR.setForegroundColor(ColorConstants.blue); //股道(上右);
*/		
			this.trackLineX.setForegroundColor(ColorConstants.green); // 股道(下)			
			this.trackLineXL.setForegroundColor(ColorConstants.green); //股道(下右);
			this.trackLineXR.setForegroundColor(ColorConstants.green); //股道(下右);

			this.changeLineSL.setForegroundColor(ColorConstants.white); //变化小段(上左
			this.changeLineSR.setForegroundColor(ColorConstants.white); //变化小段(上右
			this.changeLineXL.setForegroundColor(ColorConstants.white); //变化小段(下左
			this.changeLineXR.setForegroundColor(ColorConstants.white); //变化小段(下右
			break;
		case ParamFlag.turnout_dw_red_s: //定位红色(上)
			this.status = 0;
			this.turnoutStatus = 1;
			this.mainLineL.setForegroundColor(ColorConstants.blue); //主道岔(左倾斜);
			this.mainLineR.setForegroundColor(ColorConstants.blue); //主道岔(左倾斜);

			this.trackLineS.setForegroundColor(ColorConstants.red); // 股道(上)
			this.trackLineSL.setForegroundColor(ColorConstants.red); //股道(上左)
			this.trackLineSR.setForegroundColor(ColorConstants.red); //股道(上右);
/*			
			this.trackLineX.setForegroundColor(ColorConstants.blue); // 股道(下)			
			this.trackLineXL.setForegroundColor(ColorConstants.blue); //股道(下右);
			this.trackLineXR.setForegroundColor(ColorConstants.blue); //股道(下右);
*/
			this.changeLineSL.setForegroundColor(ColorConstants.white); //变化小段(上左
			this.changeLineSR.setForegroundColor(ColorConstants.white); //变化小段(上右
			this.changeLineXL.setForegroundColor(ColorConstants.white); //变化小段(下左
			this.changeLineXR.setForegroundColor(ColorConstants.white); //变化小段(下右
			break;
		case ParamFlag.turnout_dw_red_x: //定位红色(下)
			this.status = 0;
			this.turnoutStatus = 1;
			this.mainLineL.setForegroundColor(ColorConstants.blue); //主道岔(左倾斜);
			this.mainLineR.setForegroundColor(ColorConstants.blue); //主道岔(左倾斜);
/*
			this.trackLineS.setForegroundColor(ColorConstants.blue); // 股道(上)
			this.trackLineSL.setForegroundColor(ColorConstants.blue); //股道(上左)
			this.trackLineSR.setForegroundColor(ColorConstants.blue); //股道(上右);
*/		
			this.trackLineX.setForegroundColor(ColorConstants.red); // 股道(下)			
			this.trackLineXL.setForegroundColor(ColorConstants.red); //股道(下右);
			this.trackLineXR.setForegroundColor(ColorConstants.red); //股道(下右);

			this.changeLineSL.setForegroundColor(ColorConstants.white); //变化小段(上左
			this.changeLineSR.setForegroundColor(ColorConstants.white); //变化小段(上右
			this.changeLineXL.setForegroundColor(ColorConstants.white); //变化小段(下左
			this.changeLineXR.setForegroundColor(ColorConstants.white); //变化小段(下右
			break;
			
		
		case ParamFlag.turnout_fw_bule: //反位蓝色
			this.status = 0;
			this.turnoutStatus = 0;
			this.mainLineL.setForegroundColor(ColorConstants.blue); //主道岔(左倾斜);
			this.mainLineR.setForegroundColor(ColorConstants.blue); //主道岔(左倾斜);

			this.trackLineS.setForegroundColor(ColorConstants.blue); // 股道(上)
			this.trackLineSL.setForegroundColor(ColorConstants.white); //股道(上左)
			this.trackLineSR.setForegroundColor(ColorConstants.white); //股道(上右);
			
			this.trackLineX.setForegroundColor(ColorConstants.blue); // 股道(下)			
			this.trackLineXL.setForegroundColor(ColorConstants.white); //股道(下右);
			this.trackLineXR.setForegroundColor(ColorConstants.white); //股道(下右);

			this.changeLineSL.setForegroundColor(ColorConstants.blue); //变化小段(上左
			this.changeLineSR.setForegroundColor(ColorConstants.blue); //变化小段(上右
			this.changeLineXL.setForegroundColor(ColorConstants.blue); //变化小段(下左
			this.changeLineXR.setForegroundColor(ColorConstants.blue); //变化小段(下右
			break;
		case ParamFlag.turnout_fw_bule_l: //反位蓝色
			this.status = 0;
			this.turnoutStatus = 0;
			this.mainLineL.setForegroundColor(ColorConstants.blue); //主道岔(左倾斜);
			this.mainLineR.setForegroundColor(ColorConstants.blue); //主道岔(左倾斜);

			this.trackLineS.setForegroundColor(ColorConstants.blue); // 股道(上)
			this.trackLineSL.setForegroundColor(ColorConstants.white); //股道(上左)
			this.trackLineSR.setForegroundColor(ColorConstants.white); //股道(上右);
			
			this.trackLineX.setForegroundColor(ColorConstants.blue); // 股道(下)			
			this.trackLineXL.setForegroundColor(ColorConstants.white); //股道(下右);
			this.trackLineXR.setForegroundColor(ColorConstants.white); //股道(下右);

			this.changeLineSL.setForegroundColor(ColorConstants.blue); //变化小段(上左
			this.changeLineSR.setForegroundColor(ColorConstants.blue); //变化小段(上右
			this.changeLineXL.setForegroundColor(ColorConstants.blue); //变化小段(下左
			this.changeLineXR.setForegroundColor(ColorConstants.blue); //变化小段(下右
			break;
		case ParamFlag.turnout_fw_bule_r: //反位蓝色
			this.status = 0;
			this.turnoutStatus = 0;
			this.mainLineL.setForegroundColor(ColorConstants.blue); //主道岔(左倾斜);
			this.mainLineR.setForegroundColor(ColorConstants.blue); //主道岔(左倾斜);

			this.trackLineS.setForegroundColor(ColorConstants.blue); // 股道(上)
			this.trackLineSL.setForegroundColor(ColorConstants.white); //股道(上左)
			this.trackLineSR.setForegroundColor(ColorConstants.white); //股道(上右);
			
			this.trackLineX.setForegroundColor(ColorConstants.blue); // 股道(下)			
			this.trackLineXL.setForegroundColor(ColorConstants.white); //股道(下右);
			this.trackLineXR.setForegroundColor(ColorConstants.white); //股道(下右);

			this.changeLineSL.setForegroundColor(ColorConstants.blue); //变化小段(上左
			this.changeLineSR.setForegroundColor(ColorConstants.blue); //变化小段(上右
			this.changeLineXL.setForegroundColor(ColorConstants.blue); //变化小段(下左
			this.changeLineXR.setForegroundColor(ColorConstants.blue); //变化小段(下右
			break;
		case ParamFlag.turnout_fw_green_l: //反位绿色(左)
			this.status = 1;
			this.turnoutStatus = 0;
			this.mainLineL.setForegroundColor(ColorConstants.green); //主道岔(左倾斜);
			this.mainLineR.setForegroundColor(ColorConstants.blue); //主道岔(左倾斜);

			this.trackLineS.setForegroundColor(ColorConstants.blue); // 股道(上)
			this.trackLineSL.setForegroundColor(ColorConstants.white); //股道(上左)
			this.trackLineSR.setForegroundColor(ColorConstants.white); //股道(上右);
			
			this.trackLineX.setForegroundColor(ColorConstants.blue); // 股道(下)			
			this.trackLineXL.setForegroundColor(ColorConstants.white); //股道(下右);
			this.trackLineXR.setForegroundColor(ColorConstants.white); //股道(下右);

			this.changeLineSL.setForegroundColor(ColorConstants.green); //变化小段(上左
			this.changeLineSR.setForegroundColor(ColorConstants.blue); //变化小段(上右
			this.changeLineXL.setForegroundColor(ColorConstants.blue); //变化小段(下左
			this.changeLineXR.setForegroundColor(ColorConstants.green); //变化小段(下右
			break;
		case ParamFlag.turnout_fw_red_l: //反位红色(左)
			this.status = 2;
			this.turnoutStatus = 0;
			this.mainLineL.setForegroundColor(ColorConstants.red); //主道岔(左倾斜);
			this.mainLineR.setForegroundColor(ColorConstants.blue); //主道岔(左倾斜);

			this.trackLineS.setForegroundColor(ColorConstants.blue); // 股道(上)
			this.trackLineSL.setForegroundColor(ColorConstants.white); //股道(上左)
			this.trackLineSR.setForegroundColor(ColorConstants.white); //股道(上右);
			
			this.trackLineX.setForegroundColor(ColorConstants.blue); // 股道(下)			
			this.trackLineXL.setForegroundColor(ColorConstants.white); //股道(下右);
			this.trackLineXR.setForegroundColor(ColorConstants.white); //股道(下右);

			this.changeLineSL.setForegroundColor(ColorConstants.red); //变化小段(上左
			this.changeLineSR.setForegroundColor(ColorConstants.blue); //变化小段(上右
			this.changeLineXL.setForegroundColor(ColorConstants.blue); //变化小段(下左
			this.changeLineXR.setForegroundColor(ColorConstants.red); //变化小段(下右
			break;
		case ParamFlag.turnout_fw_green_r: //反位绿色(右)
			this.status = 1;
			this.turnoutStatus = 0;
			this.mainLineL.setForegroundColor(ColorConstants.blue); //主道岔(左倾斜);
			this.mainLineR.setForegroundColor(ColorConstants.green); //主道岔(左倾斜);

			this.trackLineS.setForegroundColor(ColorConstants.blue); // 股道(上)
			this.trackLineSL.setForegroundColor(ColorConstants.white); //股道(上左)
			this.trackLineSR.setForegroundColor(ColorConstants.white); //股道(上右);
			
			this.trackLineX.setForegroundColor(ColorConstants.blue); // 股道(下)			
			this.trackLineXL.setForegroundColor(ColorConstants.white); //股道(下右);
			this.trackLineXR.setForegroundColor(ColorConstants.white); //股道(下右);

			this.changeLineSL.setForegroundColor(ColorConstants.blue); //变化小段(上左
			this.changeLineSR.setForegroundColor(ColorConstants.green); //变化小段(上右
			this.changeLineXL.setForegroundColor(ColorConstants.green); //变化小段(下左
			this.changeLineXR.setForegroundColor(ColorConstants.blue); //变化小段(下右
			break;
		case ParamFlag.turnout_fw_red_r: //反位红色(右)
			this.status = 2;
			this.turnoutStatus = 0;
			this.mainLineL.setForegroundColor(ColorConstants.blue); //主道岔(左倾斜);
			this.mainLineR.setForegroundColor(ColorConstants.red); //主道岔(左倾斜);

			this.trackLineS.setForegroundColor(ColorConstants.blue); // 股道(上)
			this.trackLineSL.setForegroundColor(ColorConstants.white); //股道(上左)
			this.trackLineSR.setForegroundColor(ColorConstants.white); //股道(上右);
			
			this.trackLineX.setForegroundColor(ColorConstants.blue); // 股道(下)			
			this.trackLineXL.setForegroundColor(ColorConstants.white); //股道(下右);
			this.trackLineXR.setForegroundColor(ColorConstants.white); //股道(下右);

			this.changeLineSL.setForegroundColor(ColorConstants.blue); //变化小段(上左
			this.changeLineSR.setForegroundColor(ColorConstants.red); //变化小段(上右
			this.changeLineXL.setForegroundColor(ColorConstants.red); //变化小段(下左
			this.changeLineXR.setForegroundColor(ColorConstants.blue); //变化小段(下右
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

	public PolylineConnection getChangeLineSL() {
		return changeLineSL;
	}

	public void setChangeLineSL(PolylineConnection changeLineSL) {
		this.changeLineSL = changeLineSL;
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

	public NameLabel getLSnameLabel() {
		return LSnameLabel;
	}

	public void setLSnameLabel(NameLabel snameLabel) {
		LSnameLabel = snameLabel;
	}

	public NameLabel getRSnameLabel() {
		return RSnameLabel;
	}

	public void setRSnameLabel(NameLabel snameLabel) {
		RSnameLabel = snameLabel;
	}

	public NameLabel getLXnameLabel() {
		return LXnameLabel;
	}

	public void setLXnameLabel(NameLabel xnameLabel) {
		LXnameLabel = xnameLabel;
	}

	public NameLabel getRXnameLabel() {
		return RXnameLabel;
	}

	public void setRXnameLabel(NameLabel xnameLabel) {
		RXnameLabel = xnameLabel;
	}
	
	

}
