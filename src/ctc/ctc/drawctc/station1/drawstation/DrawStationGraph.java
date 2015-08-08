package ctc.ctc.drawctc.station1.drawstation;

import java.util.ArrayList;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;

import ctc.ctc.drawctc.station1.autosystem.*;
import ctc.ctc.drawctc.station1.buttonListener.*;
import ctc.ctc.drawctc.station1.data.*;
import ctc.ctc.drawctc.station1.layout.MouseAction;
import ctc.ctc.drawctc.station1.elements.semaphore.*;
import ctc.ctc.drawctc.station1.elements.common.*;
import ctc.ctc.drawctc.station1.elements.turnout.*;


/**
 * 绘制站型图、按钮
 * @author 胡恩召
 *
 */
public class DrawStationGraph {

	public static ArrayList<Figure> allRoadFigureList; // 路径有关的所有（包括股道和道岔）
	public static ArrayList<Figure> allTrackLineFigureList; // 所有股道
	public static ArrayList<Figure> allTurnoutFigureList; // 所有道岔
	public static ArrayList<Figure> allButtonFigureList; // 所有按钮
	public static ArrayList<Figure> allTurnoutButtonFigureList;

	static BaseParam baseData = BaseParam.getInstance();
	public int centerY;
	public int centerX;
	public int ylength = 5;
	public int xlength = 30;
	public int lineLength;

	public TrackLine trackLine; // 股道线
	public StationButton stationButton; // 按钮
	public TurnoutButton turnoutButton; // 道岔按钮
	public static LabelFree label;
	int labelWidth = baseData.getLabelWidth();
	int labelHeight = baseData.getLabelHeight();
	int panelWidth = baseData.getShellWidth();
	int panelHeight = baseData.getShellHeight();
	public static EndLine endLine;

	Color buttonInitColor = baseData.getButtonInitColor();
	Color dbuttonInitColor = baseData.getD_buttonInitColor();

	// ---------道岔--------//
	public TurnoutLS turnoutLS; // 左上道岔
	public TurnoutLX turnoutLX; // 左下道岔
	public TurnoutRS turnoutRS; // 右上道岔
	public TurnoutRX turnoutRX; // 右下道岔
	public TurnoutDouble turnoutDouble; // 双开道岔
	public TurnoutDoubleR turnoutDoubleR; // 右倾斜道岔
	public TurnoutDoubleL turnoutDoubleL; // 左倾斜道岔

	// --------信号机--------//
	public SemaphoreDoubleL semaphoreDoubleL; // 开口向左的信号机(双灯)
	public SemaphoreDoubleR semaphoreDoubleR; // 开口向右的信号机(双灯)
	public SemaphoreSimpleL semaphoreSimpleL; // 开口向左的信号机(单灯)
	public SemaphoreSimpleR semaphoreSimpleR; // 开口向右的信号机(单灯)

	public static Figure panel;
	public static int bLength;

	// 画车站
	public void drawStation() {

		centerX = baseData.getShellWidth() / 2 - 100;
		centerY = baseData.getShellHeight() / 2 - 50;
		lineLength = baseData.getTrackLength();

		panel = baseData.getPanel();
		bLength = baseData.getButtonLength();

		allRoadFigureList = baseData.getAllRoadFigureList();
		allTrackLineFigureList = baseData.getAllTrackLineFigureList();
		allTurnoutFigureList = baseData.getAllTurnoutFigureList();
		allButtonFigureList = baseData.getAllButtonFigureList();
		allTurnoutButtonFigureList = baseData.getAllTurnoutButtonFigureList();

	}
	
	
	public static void showStationControlMsg(Boolean result){
		MessageBox mb = new MessageBox(baseData.getMainShell(), SWT.ABORT | SWT.ICON_INFORMATION);
		mb.setText("站控消息");//消息框的标题
		String str;
		if(result == true){
			str = "申请成功！";			
		}else{
			str = "申请失败！";			
		}
		
		mb.setMessage(str);//消息框的提示文字
		mb.open();
	}	
		
	//绘制站名
	public void drawStationName(){
		
		Label stationName = new Label();
		stationName.setSize(200, 30);

		stationName.setText("标准站一");
		stationName.setFont(baseData.getFont2());
		stationName.setLocation(new Point(500, 30));
		baseData.getPanel().add(stationName);
		
	}

	// 绘制股道线
	public void drawTrackLine() {

		// ----------股道5------------//
		endLine = new EndLine("Left", "绝缘线", centerX - lineLength, centerY - 2 * lineLength);
		// allRoadFigureList.add(endLine);

		// 股道5
		trackLine = new TrackLine("5", centerX - lineLength, centerY - 2 * lineLength, 2 * lineLength); // 股道线
		trackLine.setLabelStatus(true);
		allRoadFigureList.add(trackLine);

		baseData.setTrackline_5(trackLine);

		// 股道5+1
		trackLine = new TrackLine("5+1", centerX + lineLength, centerY - 2 * lineLength, lineLength); // 股道线
		allRoadFigureList.add(trackLine);

		// 股道5+2
		trackLine = new TrackLine("5+2", centerX + 2 * lineLength, centerY - 2 * lineLength, lineLength); // 股道线
		allRoadFigureList.add(trackLine);

		// 股道5+3
		trackLine = new TrackLine("5+3", centerX + 3 * lineLength, centerY - 2 * lineLength, lineLength); // 股道线
		allRoadFigureList.add(trackLine);

		// 道岔26
		turnoutRS = new TurnoutRS("26", centerX + 4 * lineLength, centerY - 2 * lineLength, "S");
		allRoadFigureList.add(turnoutRS);
		allTurnoutFigureList.add(turnoutRS);

		// ----------股道3------------//
		// 股道3-1
		trackLine = new TrackLine("3-1", centerX - 2 * lineLength, centerY - lineLength, lineLength); // 股道线
		allRoadFigureList.add(trackLine);

		// 股道3
		trackLine = new TrackLine("3", centerX - lineLength, centerY - lineLength, 2 * lineLength); // 股道线
		trackLine.setLabelStatus(true);
		allRoadFigureList.add(trackLine);

		baseData.setTrackline_3(trackLine);

		// 股道3+1
		trackLine = new TrackLine("3+1", centerX + lineLength, centerY - lineLength, lineLength); // 股道线
		allRoadFigureList.add(trackLine);

		// 股道3+2
		trackLine = new TrackLine("3+2", centerX + 2 * lineLength, centerY - lineLength, lineLength); // 股道线
		allRoadFigureList.add(trackLine);

		// 股道3+3
		trackLine = new TrackLine("3+3", centerX + 3 * lineLength, centerY - lineLength, lineLength); // 股道线
		allRoadFigureList.add(trackLine);

		// 股道3+4
		trackLine = new TrackLine("3+4", centerX + 5 * lineLength, centerY - lineLength, lineLength); // 股道线
		allRoadFigureList.add(trackLine);

		// 道岔22/24
		turnoutDoubleR = new TurnoutDoubleR("22/24", "22", "24", centerX + 6 * lineLength + lineLength / 2, centerY - lineLength / 2, "S");
		allRoadFigureList.add(turnoutDoubleR);
		allTurnoutFigureList.add(turnoutDoubleR);

		// 股道3+5
		trackLine = new TrackLine("3+5", centerX + 7 * lineLength, centerY - lineLength, lineLength); // 股道线
		allRoadFigureList.add(trackLine);

		// 股道3+6
		trackLine = new TrackLine("3+6", centerX + 8 * lineLength, centerY - lineLength, 2 * lineLength); // 股道线
		allRoadFigureList.add(trackLine);
		baseData.setTrackline_3_6(trackLine);

		// 绝缘线
		endLine = new EndLine("Right", "绝缘线", centerX + 10 * lineLength, centerY - lineLength);
		// allRoadFigureList.add(trackLine);

		// ------股道1-------//
		// 股道XD1JJ
		baseData.setXd1jj(new TrackLine("XD1JJ", centerX - 8 * lineLength, centerY, lineLength)); // 股道线
		allRoadFigureList.add(baseData.getXd1jj());

		// 股道XD2JJ
		baseData.setXd2jj(new TrackLine("XD2JJ", centerX - 7 * lineLength, centerY, lineLength)); // 股道线
		allRoadFigureList.add(baseData.getXd2jj());

		baseData.setTrackline_xxjz(baseData.getXd2jj());
		//baseData.getXd2jj().setColorStatus(ParamFlag.trackline_red);

		// 股道I-3
		trackLine = new TrackLine("I-3", centerX - 6 * lineLength, centerY, lineLength); // 股道线
		allRoadFigureList.add(trackLine);

		// 股道I-2
		trackLine = new TrackLine("I-2", centerX - 5 * lineLength, centerY, lineLength); // 股道线
		allRoadFigureList.add(trackLine);

		// 道岔1/3/5/7
		turnoutDouble = new TurnoutDouble("1/3_5/7", "1", "3", "5", "7", centerX - 4 * lineLength + lineLength / 2, centerY + lineLength / 2, "X");
		allRoadFigureList.add(turnoutDouble);
		allTurnoutFigureList.add(turnoutDouble);

		// 道岔9
		turnoutLS = new TurnoutLS("9", centerX - 2 * lineLength, centerY - lineLength, "X");
		allRoadFigureList.add(turnoutLS);
		allTurnoutFigureList.add(turnoutLS);

		// 股道I-1
		trackLine = new TrackLine("I-1", centerX - 2 * lineLength, centerY, lineLength); // 股道线
		allRoadFigureList.add(trackLine);

		// 股道I
		trackLine = new TrackLine("I", centerX - lineLength, centerY, 2 * lineLength); // 股道线
		trackLine.setLabelStatus(true);
		allRoadFigureList.add(trackLine);

		baseData.setTrackline_1(trackLine);

		// 股道I+1
		trackLine = new TrackLine("I+1", centerX + lineLength, centerY, lineLength); // 股道线
		allRoadFigureList.add(trackLine);

		// 道岔10
		turnoutRS = new TurnoutRS("10", centerX + 2 * lineLength, centerY - lineLength, "S");
		allRoadFigureList.add(turnoutRS);
		allTurnoutFigureList.add(turnoutRS);

		// 道岔14/16
		turnoutDoubleL = new TurnoutDoubleL("14/16", "14", "16", centerX + 3 * lineLength + lineLength / 2, centerY + lineLength / 2, "S");
		allRoadFigureList.add(turnoutDoubleL);
		allTurnoutFigureList.add(turnoutDoubleL);

		// 股道I+2
		trackLine = new TrackLine("I+2", centerX + 4 * lineLength, centerY, lineLength); // 股道线
		allRoadFigureList.add(trackLine);

		// 道岔18/20
		turnoutDoubleR = new TurnoutDoubleR("18/20", "18", "20", centerX + 5 * lineLength + lineLength / 2, centerY + lineLength / 2, "S");
		allRoadFigureList.add(turnoutDoubleR);
		allTurnoutFigureList.add(turnoutDoubleR);

		// 股道I+3
		trackLine = new TrackLine("I+3", centerX + 7 * lineLength, centerY, lineLength); // 股道线
		allRoadFigureList.add(trackLine);

		// 股道I+4
		trackLine = new TrackLine("I+4", centerX + 8 * lineLength, centerY, lineLength); // 股道线
		allRoadFigureList.add(trackLine);

		// 股道XD1LQ
		baseData.setXd1lq(new TrackLine("XD1LQ", centerX + 9 * lineLength, centerY, lineLength)); // 股道线
		allRoadFigureList.add(baseData.getXd1lq());

		// 股道XD2LQ
		baseData.setXd2lq(new TrackLine("XD2LQ", centerX + 10 * lineLength, centerY, lineLength)); // 股道线
		allRoadFigureList.add(baseData.getXd2lq());

		// -----------股道II--------------//

		// 股道SD2LQ
		baseData.setSd2lq(new TrackLine("SD2LQ", centerX - 8 * lineLength, centerY + lineLength, lineLength)); // 股道线
		allRoadFigureList.add(baseData.getSd2lq());

		// 股道SD1LQ
		baseData.setSd1lq(new TrackLine("SD1LQ", centerX - 7 * lineLength, centerY + lineLength, lineLength)); // 股道线
		allRoadFigureList.add(baseData.getSd1lq());

		// 股道II-3
		trackLine = new TrackLine("II-3", centerX - 6 * lineLength, centerY + lineLength, lineLength); // 股道线
		allRoadFigureList.add(trackLine);

		// 股道II-2
		trackLine = new TrackLine("II-2", centerX - 5 * lineLength, centerY + lineLength, lineLength); // 股道线
		allRoadFigureList.add(trackLine);

		// 道岔11
		turnoutLX = new TurnoutLX("11", centerX - 3 * lineLength, centerY + lineLength, "X");
		allRoadFigureList.add(turnoutLX);
		allTurnoutFigureList.add(turnoutLX);

		// 股道II-1
		trackLine = new TrackLine("II-1", centerX - 2 * lineLength, centerY + lineLength, lineLength); // 股道线
		allRoadFigureList.add(trackLine);

		// 股道II
		trackLine = new TrackLine("II", centerX - lineLength, centerY + lineLength, 2 * lineLength); // 股道线
		trackLine.setLabelStatus(true);
		allRoadFigureList.add(trackLine);

		baseData.setTrackline_2(trackLine);

		// 股道II+1
		trackLine = new TrackLine("II+1", centerX + lineLength, centerY + lineLength, lineLength); // 股道线
		allRoadFigureList.add(trackLine);

		// 股道II+2
		trackLine = new TrackLine("II+2", centerX + 2 * lineLength, centerY + lineLength, lineLength); // 股道线
		allRoadFigureList.add(trackLine);

		// 道岔2/4/6/8
		turnoutDouble = new TurnoutDouble("2/4_6/8", "2", "4", "6", "8", centerX + 4 * lineLength + lineLength / 2, centerY + 3 * lineLength / 2, "S");
		allRoadFigureList.add(turnoutDouble);
		allTurnoutFigureList.add(turnoutDouble);

		// 股道II+3
		trackLine = new TrackLine("II+3", centerX + 6 * lineLength, centerY + lineLength, lineLength); // 股道线
		allRoadFigureList.add(trackLine);

		// 股道II+4
		trackLine = new TrackLine("II+4", centerX + 7 * lineLength, centerY + lineLength, lineLength); // 股道线
		allRoadFigureList.add(trackLine);

		// 股道II+5
		trackLine = new TrackLine("II+5", centerX + 8 * lineLength, centerY + lineLength, lineLength); // 股道线
		allRoadFigureList.add(trackLine);

		// 股道SD2JJ
		baseData.setSd2jj(new TrackLine("SD2JJ", centerX + 9 * lineLength, centerY + lineLength, lineLength)); // 股道线
		allRoadFigureList.add(baseData.getSd2jj());

		baseData.setTrackline_sxjz(baseData.getSd2jj());
		//baseData.getSd2jj().setColorStatus(ParamFlag.trackline_red);

		// 股道SD1JJ
		baseData.setSd1jj(new TrackLine("SD1JJ", centerX + 10 * lineLength, centerY + lineLength, lineLength)); // 股道线
		allRoadFigureList.add(baseData.getSd1jj());

		// --------------股道4----------------//
		// 道岔13
		turnoutLX = new TurnoutLX("13", centerX - 2 * lineLength, centerY + 2 * lineLength, "X");
		allRoadFigureList.add(turnoutLX);
		allTurnoutFigureList.add(turnoutLX);

		// 股道4-1
		trackLine = new TrackLine("4-1", centerX - lineLength, centerY + 2 * lineLength, lineLength); // 股道线
		allRoadFigureList.add(trackLine);

		// 股道4
		trackLine = new TrackLine("4", centerX, centerY + 2 * lineLength, 2 * lineLength); // 股道线
		trackLine.setLabelStatus(true);
		allRoadFigureList.add(trackLine);

		baseData.setTrackline_4(trackLine);

		// 股道4+1
		trackLine = new TrackLine("4+1", centerX + 2 * lineLength, centerY + 2 * lineLength, lineLength); // 股道线
		allRoadFigureList.add(trackLine);

		// 道岔12
		turnoutRX = new TurnoutRX("12", centerX + 4 * lineLength, centerY + 2 * lineLength, "S");
		allRoadFigureList.add(turnoutRX);
		allTurnoutFigureList.add(turnoutRX);

		// 股道4+2
		trackLine = new TrackLine("4+2", centerX + 5 * lineLength, centerY + 2 * lineLength, lineLength); // 股道线
		allRoadFigureList.add(trackLine);

		// 股道4+3
		trackLine = new TrackLine("4+3", centerX + 6 * lineLength, centerY + 2 * lineLength, lineLength); // 股道线
		allRoadFigureList.add(trackLine);

		// 股道4+4
		trackLine = new TrackLine("4+4", centerX + 7 * lineLength, centerY + 2 * lineLength, lineLength); // 股道线
		allRoadFigureList.add(trackLine);

		// 股道4+5
		trackLine = new TrackLine("4+5", centerX + 8 * lineLength, centerY + 2 * lineLength, 2 * lineLength); // 股道线
		allRoadFigureList.add(trackLine);

		baseData.setTrackline_4_5(trackLine);

		// 绝缘线
		endLine = new EndLine("Right", "绝缘线", centerX + 10 * lineLength, centerY + 2 * lineLength);
		// allRoadFigureList.add(trackLine);

		// -------------股道6-------------------//

		// 股道6-1
		trackLine = new TrackLine("6-1", centerX - lineLength, centerY + 3 * lineLength, lineLength); // 股道线
		allRoadFigureList.add(trackLine);

		// 股道6
		trackLine = new TrackLine("6", centerX, centerY + 3 * lineLength, 2 * lineLength); // 股道线
		trackLine.setLabelStatus(true);
		allRoadFigureList.add(trackLine);

		baseData.setTrackline_6(trackLine);

		// 股道6+1
		trackLine = new TrackLine("6+1", centerX + 2 * lineLength, centerY + 3 * lineLength, lineLength); // 股道线
		allRoadFigureList.add(trackLine);

		// baseData.setAllTurnoutFigureList(allTurnoutFigureList);

	}

	// 绘制信号机器
	public void drawSemaphore() {

		int d = baseData.getSemaphoreLightDiameter();

		// 信号机XLX
		semaphoreDoubleR = new SemaphoreDoubleR("XLX", centerX - 6 * lineLength, centerY - d - 4);
		allRoadFigureList.add(semaphoreDoubleR);

		// 信号机SLX
		semaphoreDoubleL = new SemaphoreDoubleL("SLX", centerX + 9 * lineLength, centerY + lineLength + 4);
		allRoadFigureList.add(semaphoreDoubleL);

		// 信号机S1
		semaphoreSimpleL = new SemaphoreSimpleL("S1", centerX - lineLength, centerY + 4);
		allRoadFigureList.add(semaphoreSimpleL);
		// 信号机X1
		semaphoreSimpleR = new SemaphoreSimpleR("X1", centerX + lineLength, centerY - d - 4);
		allRoadFigureList.add(semaphoreSimpleR);

		// 信号及S2
		semaphoreSimpleL = new SemaphoreSimpleL("S2", centerX - lineLength, centerY + lineLength + 4);
		allRoadFigureList.add(semaphoreSimpleL);
		// 信号及X2
		semaphoreSimpleR = new SemaphoreSimpleR("X2", centerX + lineLength, centerY + lineLength - d - 4);
		allRoadFigureList.add(semaphoreSimpleR);

		// 信号及S3
		semaphoreSimpleL = new SemaphoreSimpleL("S3", centerX - lineLength, centerY - lineLength + 4);
		allRoadFigureList.add(semaphoreSimpleL);
		// 信号机X3
		semaphoreSimpleR = new SemaphoreSimpleR("X3", centerX + lineLength, centerY - lineLength - d - 4);
		allRoadFigureList.add(semaphoreSimpleR);
		// 信号机D3
		semaphoreSimpleL = new SemaphoreSimpleL("D3", centerX + 8 * lineLength, centerY - lineLength + 4);
		allRoadFigureList.add(semaphoreSimpleL);

		// 信号及S4
		semaphoreSimpleL = new SemaphoreSimpleL("S4", centerX, centerY + 2 * lineLength + 4);
		allRoadFigureList.add(semaphoreSimpleL);
		// 信号及X4
		semaphoreSimpleR = new SemaphoreSimpleR("X4", centerX + 2 * lineLength, centerY + 2 * lineLength - d - 4);
		allRoadFigureList.add(semaphoreSimpleR);
		// 信号机D4
		semaphoreSimpleL = new SemaphoreSimpleL("D4", centerX + 8 * lineLength, centerY + 2 * lineLength + 4);
		allRoadFigureList.add(semaphoreSimpleL);

		// 信号机X5
		semaphoreSimpleR = new SemaphoreSimpleR("X5", centerX + lineLength, centerY - 2 * lineLength - d - 4);
		allRoadFigureList.add(semaphoreSimpleR);

		// 信号及S6
		semaphoreSimpleL = new SemaphoreSimpleL("S6", centerX, centerY + 3 * lineLength + 4);
		allRoadFigureList.add(semaphoreSimpleL);
		// 信号及X6
		semaphoreSimpleR = new SemaphoreSimpleR("X6", centerX + 2 * lineLength, centerY + 3 * lineLength - d - 4);
		allRoadFigureList.add(semaphoreSimpleR);

	}

	// 绘制业务控制按钮
	public void drawModelButton() {

		int initX = 30;
		int initY = 80;
		int xlength = 100;
		int ylength = 15;

		// X道岔总定位
		label = new LabelFree("X道岔总定位", initX, initY, 3 * labelWidth, labelHeight + 2);
		turnoutButton = new TurnoutButton("XDCZDW", "X");
		turnoutButton.setSize(bLength, bLength);
		turnoutButton.setLocation(new Point(initX + 3 * labelWidth / 2 - bLength / 2, initY + ylength));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new ModelButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);

		// X道岔总反位
		label = new LabelFree("X道岔总反位", initX + xlength, initY, 3 * labelWidth, labelHeight + 2);
		turnoutButton = new TurnoutButton("XDCZFW", "X");
		turnoutButton.setSize(bLength, bLength);
		turnoutButton.setLocation(new Point(initX + 3 * labelWidth / 2 - bLength / 2 + xlength, initY + ylength));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new ModelButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);

		// 申请站控
		label = new LabelFree("收回站控", initX + 2 * xlength, initY, 3 * labelWidth, labelHeight + 2);
		turnoutButton = new TurnoutButton("SHZK", "X");
		turnoutButton.setSize(bLength, bLength);
		turnoutButton.setLocation(new Point(initX + 3 * labelWidth / 2 - bLength / 2 + 2 * xlength, initY + ylength));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new SpecialButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);

		// S道岔总定位
		label = new LabelFree("S道岔总定位", panelWidth - initX - 3 * labelWidth, initY, 3 * labelWidth, labelHeight + 2);
		turnoutButton = new TurnoutButton("SDCZDW", "S");
		turnoutButton.setSize(bLength, bLength);
		turnoutButton.setLocation(new Point(panelWidth - initX - 3 * labelWidth / 2 - bLength / 2, initY + ylength));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new ModelButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);

		// S道岔总反位
		label = new LabelFree("S道岔总反位", panelWidth - initX - 3 * labelWidth - xlength, initY, 3 * labelWidth, labelHeight + 2);
		turnoutButton = new TurnoutButton("SDCZFW", "S");
		turnoutButton.setSize(bLength, bLength);
		turnoutButton.setLocation(new Point(panelWidth - initX - 3 * labelWidth / 2 - bLength / 2 - xlength, initY + ylength));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new ModelButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);

/*
		// 非常站控
		label = new LabelFree("非常站控", panelWidth - initX - 3 * labelWidth - 2 * xlength, initY, 3 * labelWidth, labelHeight + 2);
		turnoutButton = new TurnoutButton("FCZK", "S");
		turnoutButton.setSize(bLength, bLength);
		turnoutButton.setLocation(new Point(panelWidth - initX - 3 * labelWidth / 2 - bLength / 2 - 2 * xlength, initY + ylength));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new SpecialButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);
*/

		// -----------------------界面下半部分按钮绘制----------------------------//

		int initY2 = panelHeight - 60;

		// X排列进路
		label = new LabelFree("X排列进路", initX, initY2, 3 * labelWidth, labelHeight + 2);
		turnoutButton = new TurnoutButton("XPLJL", "X");
		turnoutButton.setSize(bLength, bLength);
		turnoutButton.setLocation(new Point(initX + 3 * labelWidth / 2 - bLength / 2, initY2 + ylength));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new ModelButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);

		// X总人工解锁
		label = new LabelFree("X总人工解锁", initX + xlength, initY2, 3 * labelWidth, labelHeight + 2);
		turnoutButton = new TurnoutButton("XZRGJS", "X");
		turnoutButton.setSize(bLength, bLength);
		turnoutButton.setLocation(new Point(initX + 3 * labelWidth / 2 - bLength / 2 + xlength, initY2 + ylength));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new ModelButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);

		// X总人工解锁
		label = new LabelFree("X总取消", initX + 2 * xlength, initY2, 3 * labelWidth, labelHeight + 2);
		turnoutButton = new TurnoutButton("XZQX", "X");
		turnoutButton.setSize(bLength, bLength);
		turnoutButton.setLocation(new Point(initX + 3 * labelWidth / 2 - bLength / 2 + 2 * xlength, initY2 + ylength));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new ModelButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);

		// 显示文字
		label = new LabelFree("显示文字", initX + 3 * xlength, initY2, 3 * labelWidth, labelHeight + 2);
		turnoutButton = new TurnoutButton("XSWZ", "X");
		turnoutButton.setSize(bLength, bLength);
		turnoutButton.setLocation(new Point(initX + 3 * labelWidth / 2 - bLength / 2 + 3 * xlength, initY2 + ylength));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new SpecialButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);

		// S排列进路
		label = new LabelFree("S排列进路", panelWidth - initX - 3 * labelWidth, initY2, 3 * labelWidth, labelHeight + 2);
		turnoutButton = new TurnoutButton("SPLJL", "S");
		turnoutButton.setSize(bLength, bLength);
		turnoutButton.setLocation(new Point(panelWidth - initX - 3 * labelWidth / 2 - bLength / 2, initY2 + ylength));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new ModelButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);

		// S总人工解锁
		label = new LabelFree("S总人工解锁", panelWidth - initX - 3 * labelWidth - xlength, initY2, 3 * labelWidth, labelHeight + 2);
		turnoutButton = new TurnoutButton("SZRGJS", "S");
		turnoutButton.setSize(bLength, bLength);
		turnoutButton.setLocation(new Point(panelWidth - initX - 3 * labelWidth / 2 - bLength / 2 - xlength, initY2 + ylength));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new ModelButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);

		// S总取消
		label = new LabelFree("S总取消", panelWidth - initX - 3 * labelWidth - 2 * xlength, initY2, 3 * labelWidth, labelHeight + 2);
		turnoutButton = new TurnoutButton("SZQX", "S");
		turnoutButton.setSize(bLength, bLength);
		turnoutButton.setLocation(new Point(panelWidth - initX - 3 * labelWidth / 2 - bLength / 2 - 2 * xlength, initY2 + ylength));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new ModelButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);

		// 清除屏幕
		label = new LabelFree("清除屏幕", panelWidth - initX - 3 * labelWidth - 3 * xlength, initY2, 3 * labelWidth, labelHeight + 2);
		turnoutButton = new TurnoutButton("QCPM", "S");
		turnoutButton.setSize(bLength, bLength);
		turnoutButton.setLocation(new Point(panelWidth - initX - 3 * labelWidth / 2 - bLength / 2 - 3 * xlength, initY2 + ylength));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new SpecialButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);

		// 显示任务按钮
		label = new LabelFree("设置故障", panelWidth / 2 - 3 * labelWidth / 2, initY2, 3 * labelWidth, labelHeight + 2);
		turnoutButton = new TurnoutButton("SZGZ", "X");
		turnoutButton.setSize(2 * bLength, bLength);
		turnoutButton.setLocation(new Point(panelWidth / 2 - bLength, initY2 + ylength));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new ModelButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);

	}

	// 绘制道岔控制按钮
	public void drawTurnoutButton() {

		int initX = 50;
		int initY = 20;
		int xlength = 40;
		int ylength = 12;

		// 绘制1/3
		label = new LabelFree("1/3", initX, initY, labelWidth, labelHeight);
		turnoutButton = new TurnoutButton("1/3", "X");
		turnoutButton.setSize(bLength, bLength);
		turnoutButton.setLocation(new Point(initX + labelWidth / 2 - bLength / 2, initY + ylength));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new TurnoutButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);

		// 绘制5/7
		label = new LabelFree("5/7", initX + xlength, initY, labelWidth, labelHeight);
		turnoutButton = new TurnoutButton("5/7", "X");
		turnoutButton.setSize(bLength, bLength);
		turnoutButton.setLocation(new Point(initX + labelWidth / 2 - bLength / 2 + xlength, initY + ylength));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new TurnoutButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);

		// 绘制9
		label = new LabelFree("9", initX + 2 * xlength, initY, labelWidth, labelHeight);
		turnoutButton = new TurnoutButton("9", "X");
		turnoutButton.setSize(bLength, bLength);
		turnoutButton.setLocation(new Point(initX + labelWidth / 2 - bLength / 2 + 2 * xlength, initY + ylength));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new TurnoutButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);

		// 绘制11
		label = new LabelFree("11", initX + 3 * xlength, initY, labelWidth, labelHeight);
		turnoutButton = new TurnoutButton("11", "X");
		turnoutButton.setSize(bLength, bLength);
		turnoutButton.setLocation(new Point(initX + labelWidth / 2 - bLength / 2 + 3 * xlength, initY + ylength));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new TurnoutButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);

		// 绘制13
		label = new LabelFree("13", initX + 4 * xlength, initY, labelWidth, labelHeight);
		turnoutButton = new TurnoutButton("13", "X");
		turnoutButton.setSize(bLength, bLength);
		turnoutButton.setLocation(new Point(initX + labelWidth / 2 - bLength / 2 + 4 * xlength, initY + ylength));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new TurnoutButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);

		// 绘制2/4
		label = new LabelFree("2/4", panelWidth - initX - labelWidth, initY, labelWidth, labelHeight);
		turnoutButton = new TurnoutButton("2/4", "S");
		turnoutButton.setSize(bLength, bLength);
		turnoutButton.setLocation(new Point(panelWidth - initX - labelWidth / 2 - bLength / 2, initY + ylength));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new TurnoutButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);

		// 绘制6/8
		label = new LabelFree("6/8", panelWidth - initX - labelWidth - xlength, initY, labelWidth, labelHeight);
		turnoutButton = new TurnoutButton("6/8", "S");
		turnoutButton.setSize(bLength, bLength);
		turnoutButton.setLocation(new Point(panelWidth - initX - labelWidth / 2 - bLength / 2 - xlength, initY + ylength));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new TurnoutButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);

		// 绘制10
		label = new LabelFree("10", panelWidth - initX - labelWidth - 2 * xlength, initY, labelWidth, labelHeight);
		turnoutButton = new TurnoutButton("10", "S");
		turnoutButton.setSize(bLength, bLength);
		turnoutButton.setLocation(new Point(panelWidth - initX - labelWidth / 2 - bLength / 2 - 2 * xlength, initY + ylength));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new TurnoutButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);

		// 绘制12
		label = new LabelFree("12", panelWidth - initX - labelWidth - 3 * xlength, initY, labelWidth, labelHeight);
		turnoutButton = new TurnoutButton("12", "S");
		turnoutButton.setSize(bLength, bLength);
		turnoutButton.setLocation(new Point(panelWidth - initX - labelWidth / 2 - bLength / 2 - 3 * xlength, initY + ylength));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new TurnoutButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);

		// 绘制14/16
		label = new LabelFree("14/16", panelWidth - initX - labelWidth - 4 * xlength, initY, labelWidth, labelHeight);
		turnoutButton = new TurnoutButton("14/16", "S");
		turnoutButton.setSize(bLength, bLength);
		turnoutButton.setLocation(new Point(panelWidth - initX - labelWidth / 2 - bLength / 2 - 4 * xlength, initY + ylength));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new TurnoutButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);

		// 绘制18/20
		label = new LabelFree("18/20", panelWidth - initX - labelWidth - 5 * xlength, initY, labelWidth, labelHeight);
		turnoutButton = new TurnoutButton("18/20", "S");
		turnoutButton.setSize(bLength, bLength);
		turnoutButton.setLocation(new Point(panelWidth - initX - labelWidth / 2 - bLength / 2 - 5 * xlength, initY + ylength));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new TurnoutButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);

		// 绘制22/24
		label = new LabelFree("22/24", panelWidth - initX - labelWidth - 6 * xlength, initY, labelWidth, labelHeight);
		turnoutButton = new TurnoutButton("22/24", "S");
		turnoutButton.setSize(bLength, bLength);
		turnoutButton.setLocation(new Point(panelWidth - initX - labelWidth / 2 - bLength / 2 - 6 * xlength, initY + ylength));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new TurnoutButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);

		// 绘制26
		label = new LabelFree("26", panelWidth - initX - labelWidth - 7 * xlength, initY, labelWidth, labelHeight);
		turnoutButton = new TurnoutButton("26", "S");
		turnoutButton.setSize(bLength, bLength);
		turnoutButton.setLocation(new Point(panelWidth - initX - labelWidth / 2 - bLength / 2 - 7 * xlength, initY + ylength));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new TurnoutButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);

	}

	// 绘制股道控制按钮
	public void drawStationButton() {

		// ----------------进出站按钮------------------//
		// 按钮XLA
		stationButton = new StationButton("XLA", "B");
		stationButton.setSize(bLength, bLength);
		stationButton.setLocation(new Point(centerX - 5 * lineLength + 1, centerY - bLength - 4));
		stationButton.setBackgroundColor(buttonInitColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);

		// 按钮XTA
		stationButton = new StationButton("XTA", "B");
		stationButton.setSize(bLength, bLength);
		stationButton.setLocation(new Point(centerX - 5 * lineLength + bLength + 2, centerY - bLength - 4));
		stationButton.setBackgroundColor(buttonInitColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);

		// 按钮SLFA
		stationButton = new StationButton("SLFA", "B");
		stationButton.setSize(bLength, bLength);
		stationButton.setLocation(new Point(centerX - 5 * lineLength + 1, centerY + lineLength + 4));
		stationButton.setBackgroundColor(buttonInitColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);

		// 按钮XLFA
		stationButton = new StationButton("XLFA", "B");
		stationButton.setSize(bLength, bLength);
		stationButton.setLocation(new Point(centerX + 8 * lineLength + 2, centerY - bLength - 4));
		stationButton.setBackgroundColor(buttonInitColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);

		// 按钮STA
		stationButton = new StationButton("STA", "B");
		stationButton.setSize(bLength, bLength);
		stationButton.setLocation(new Point(centerX + 7 * lineLength + 5, centerY + lineLength + 4));
		stationButton.setBackgroundColor(buttonInitColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);

		// 按钮SLA
		stationButton = new StationButton("SLA", "B");
		stationButton.setSize(bLength, bLength);
		stationButton.setLocation(new Point(centerX + 7 * lineLength + bLength + 10, centerY + lineLength + 4));
		stationButton.setBackgroundColor(buttonInitColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);

		// -------------股道上按钮（接发车和调车）----------------//
		// 按钮X1LA
		stationButton = new StationButton("X1LA", "B");
		stationButton.setSize(bLength, bLength);
		stationButton.setLocation(new Point(centerX + 3 * lineLength / 2 - 1, centerY - bLength / 2));
		stationButton.setBackgroundColor(buttonInitColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);

		// 按钮S1LA
		stationButton = new StationButton("S1LA", "B");
		stationButton.setSize(bLength, bLength);
		stationButton.setLocation(new Point(centerX - 2 * lineLength + bLength + 1, centerY - bLength / 2));
		stationButton.setBackgroundColor(buttonInitColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);

		/*
		 * //按钮DS1LA stationButton = new StationButton("DS1LA", "D");
		 * stationButton.setSize(bLength,bLength); stationButton.setLocation(new
		 * Point(centerX - 2lineLength, centerY - bLength / 2));
		 * stationButton.setBackgroundColor(dbuttonInitColor);
		 * panel.add(stationButton); new MouseAction(stationButton);
		 * stationButton.addActionListener(new ButtonListener());
		 * allButtonFigureList.add(stationButton);
		 */

		// 按钮DX1LA
		stationButton = new StationButton("DX1LA", "D");
		stationButton.setSize(bLength, bLength);
		stationButton.setLocation(new Point(centerX + 3 * lineLength / 2 + bLength, centerY - bLength / 2));
		stationButton.setBackgroundColor(dbuttonInitColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);

		// 按钮X2LA
		stationButton = new StationButton("X2LA", "B");
		stationButton.setSize(bLength, bLength);
		stationButton.setLocation(new Point(centerX + 3 * lineLength / 2 - 1, centerY + lineLength - bLength / 2));
		stationButton.setBackgroundColor(buttonInitColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);

		// 按钮S2LA
		stationButton = new StationButton("S2LA", "B");
		stationButton.setSize(bLength, bLength);
		stationButton.setLocation(new Point(centerX - 2 * lineLength + bLength + 1, centerY + lineLength - bLength / 2));
		stationButton.setBackgroundColor(buttonInitColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);

		/*
		 * //按钮DS2LA stationButton = new StationButton("DS2LA", "D");
		 * stationButton.setSize(bLength,bLength); stationButton.setLocation(new
		 * Point(centerX - 2lineLength, centerY + lineLength - bLength / 2));
		 * stationButton.setBackgroundColor(dbuttonInitColor);
		 * panel.add(stationButton); new MouseAction(stationButton);
		 * stationButton.addActionListener(new ButtonListener());
		 * allButtonFigureList.add(stationButton);
		 */

		// 按钮DX2LA
		stationButton = new StationButton("DX2LA", "D");
		stationButton.setSize(bLength, bLength);
		stationButton.setLocation(new Point(centerX + 3 * lineLength / 2 + bLength, centerY + lineLength - bLength / 2));
		stationButton.setBackgroundColor(dbuttonInitColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);

		// 按钮X3LA
		stationButton = new StationButton("X3LA", "B");
		stationButton.setSize(bLength, bLength);
		stationButton.setLocation(new Point(centerX + 3 * lineLength / 2 - 1, centerY - lineLength - bLength / 2));
		stationButton.setBackgroundColor(buttonInitColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);

		// 按钮S3LA
		stationButton = new StationButton("S3LA", "B");
		stationButton.setSize(bLength, bLength);
		stationButton.setLocation(new Point(centerX - 2 * lineLength + bLength + 1, centerY - lineLength - bLength / 2));
		stationButton.setBackgroundColor(buttonInitColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);

		/*
		 * //按钮DS3LA stationButton = new StationButton("DS3LA", "D");
		 * stationButton.setSize(bLength,bLength); stationButton.setLocation(new
		 * Point(centerX - 2lineLength, centerY - lineLength - bLength / 2));
		 * stationButton.setBackgroundColor(dbuttonInitColor);
		 * panel.add(stationButton); new MouseAction(stationButton);
		 * stationButton.addActionListener(new ButtonListener());
		 * allButtonFigureList.add(stationButton);
		 */

		// 按钮DX3LA
		stationButton = new StationButton("DX3LA", "D");
		stationButton.setSize(bLength, bLength);
		stationButton.setLocation(new Point(centerX + 3 * lineLength / 2 + bLength, centerY - lineLength - bLength / 2));
		stationButton.setBackgroundColor(dbuttonInitColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);

		// 按钮DX3LB
		stationButton = new StationButton("DX3LB", "D");
		stationButton.setSize(bLength, bLength);
		stationButton.setLocation(new Point(centerX + 7 * lineLength, centerY - lineLength - bLength / 2));
		stationButton.setBackgroundColor(dbuttonInitColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);

		// 按钮X4LA
		stationButton = new StationButton("X4LA", "B");
		stationButton.setSize(bLength, bLength);
		stationButton.setLocation(new Point(centerX + 5 * lineLength / 2 - 1, centerY + 2 * lineLength - bLength / 2));
		stationButton.setBackgroundColor(buttonInitColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);

		// 按钮S4LA
		stationButton = new StationButton("S4LA", "B");
		stationButton.setSize(bLength, bLength);
		stationButton.setLocation(new Point(centerX - lineLength + bLength + 1, centerY + 2 * lineLength - bLength / 2));
		stationButton.setBackgroundColor(buttonInitColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);

		/*
		 * //按钮DS4LA stationButton = new StationButton("DS4LA", "D");
		 * stationButton.setSize(bLength,bLength); stationButton.setLocation(new
		 * Point(centerX - lineLength, centerY + 2lineLength - bLength / 2));
		 * stationButton.setBackgroundColor(dbuttonInitColor);
		 * panel.add(stationButton); new MouseAction(stationButton);
		 * stationButton.addActionListener(new ButtonListener());
		 * allButtonFigureList.add(stationButton);
		 */

		// 按钮DX4LA
		stationButton = new StationButton("DX4LA", "D");
		stationButton.setSize(bLength, bLength);
		stationButton.setLocation(new Point(centerX + 5 * lineLength / 2 + bLength, centerY + 2 * lineLength - bLength / 2));
		stationButton.setBackgroundColor(dbuttonInitColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);

		// 按钮DX4LB
		stationButton = new StationButton("DX4LB", "D");
		stationButton.setSize(bLength, bLength);
		stationButton.setLocation(new Point(centerX + 7 * lineLength, centerY + 2 * lineLength - bLength / 2));
		stationButton.setBackgroundColor(dbuttonInitColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);

		// 按钮DX5LA
		stationButton = new StationButton("DX5LA", "D");
		stationButton.setSize(bLength, bLength);
		stationButton.setLocation(new Point(centerX + 3 * lineLength / 2 + bLength, centerY - 2 * lineLength - bLength / 2));
		stationButton.setBackgroundColor(dbuttonInitColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);

		// 按钮X6LA
		stationButton = new StationButton("X6LA", "B");
		stationButton.setSize(bLength, bLength);
		stationButton.setLocation(new Point(centerX + 5 * lineLength / 2 - 1, centerY + 3 * lineLength - bLength / 2));
		stationButton.setBackgroundColor(buttonInitColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);

		// 按钮S6LA
		stationButton = new StationButton("S6LA", "B");
		stationButton.setSize(bLength, bLength);
		stationButton.setLocation(new Point(centerX - lineLength + bLength + 1, centerY + 3 * lineLength - bLength / 2));
		stationButton.setBackgroundColor(buttonInitColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);

		/*
		 * //按钮DS6LA stationButton = new StationButton("DS6LA", "D");
		 * stationButton.setSize(bLength,bLength); stationButton.setLocation(new
		 * Point( centerX - lineLength, centerY + 3lineLength - bLength / 2));
		 * stationButton.setBackgroundColor(dbuttonInitColor);
		 * panel.add(stationButton); new MouseAction(stationButton);
		 * stationButton.addActionListener(new ButtonListener());
		 * allButtonFigureList.add(stationButton);
		 */

		// 按钮DX6LA
		stationButton = new StationButton("DX6LA", "D");
		stationButton.setSize(bLength, bLength);
		stationButton.setLocation(new Point(centerX + 5 * lineLength / 2 + bLength, centerY + 3 * lineLength - bLength / 2));
		stationButton.setBackgroundColor(dbuttonInitColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);

	}

	// 绘制消息显示部分
	public void drawMessageText() {

		int initX = 10;
		int initY = 20;

		org.eclipse.swt.widgets.Label operateLabel = new org.eclipse.swt.widgets.Label(baseData.getTextShell(), SWT.NONE);
		operateLabel.setText("操作记录：");
		operateLabel.setSize(100, 30);
		operateLabel.setFont(baseData.getFont2());
		operateLabel.setLocation(initX, initY);

		baseData.setMsgLabel(new Text(baseData.getTextShell(), SWT.NONE));
		baseData.getMsgLabel().setSize(200, 30);
		baseData.getMsgLabel().setFont(baseData.getFont2());
		baseData.getMsgLabel().setLocation(initX + 110, initY);

		org.eclipse.swt.widgets.Label timeLabel = new org.eclipse.swt.widgets.Label(baseData.getTextShell(), SWT.NONE);
		timeLabel.setText("虚拟时间：");
		timeLabel.setSize(100, 30);
		timeLabel.setFont(baseData.getFont2());
		timeLabel.setLocation(initX, initY + 50);

		baseData.setTimeLabel(new org.eclipse.swt.widgets.Label(baseData.getTextShell(), SWT.NONE));
		baseData.getTimeLabel().setText("12:10");
		baseData.getTimeLabel().setSize(150, 30);
		baseData.getTimeLabel().setFont(baseData.getFont2());
		baseData.getTimeLabel().setLocation(initX + 110, initY + 50);

		// 设置任务列表
		TaskTable taskTable = TaskTable.getInstance();
		taskTable.setTableContents();

	}

	/**
	 * 绘制图形的网格
	 */
	public void drawGrid() {

		PolylineConnection line;
		line = new PolylineConnection();
		line.setStart(new Point(centerX, centerY - 200));
		line.setEnd(new Point(centerX, centerY + 200));
		line.setLineWidth(1);
		line.setForegroundColor(ColorConstants.red);
		baseData.getPanel().add(line);

		line = new PolylineConnection();
		line.setStart(new Point(centerX - lineLength, centerY - 200));
		line.setEnd(new Point(centerX - lineLength, centerY + 200));
		line.setLineWidth(1);
		line.setForegroundColor(ColorConstants.blue);
		baseData.getPanel().add(line);

		line = new PolylineConnection();
		line.setStart(new Point(centerX - 2 * lineLength, centerY - 200));
		line.setEnd(new Point(centerX - 2 * lineLength, centerY + 200));
		line.setLineWidth(1);
		line.setForegroundColor(ColorConstants.blue);
		baseData.getPanel().add(line);

		line = new PolylineConnection();
		line.setStart(new Point(centerX - 3 * lineLength, centerY - 200));
		line.setEnd(new Point(centerX - 3 * lineLength, centerY + 200));
		line.setLineWidth(1);
		line.setForegroundColor(ColorConstants.blue);
		baseData.getPanel().add(line);

		line = new PolylineConnection();
		line.setStart(new Point(centerX - 4 * lineLength, centerY - 200));
		line.setEnd(new Point(centerX - 4 * lineLength, centerY + 200));
		line.setLineWidth(1);
		line.setForegroundColor(ColorConstants.blue);
		baseData.getPanel().add(line);

		line = new PolylineConnection();
		line.setStart(new Point(centerX - 5 * lineLength, centerY - 200));
		line.setEnd(new Point(centerX - 5 * lineLength, centerY + 200));
		line.setLineWidth(1);
		line.setForegroundColor(ColorConstants.blue);
		baseData.getPanel().add(line);

		line = new PolylineConnection();
		line.setStart(new Point(centerX - 6 * lineLength, centerY - 200));
		line.setEnd(new Point(centerX - 6 * lineLength, centerY + 200));
		line.setLineWidth(1);
		line.setForegroundColor(ColorConstants.blue);
		baseData.getPanel().add(line);

		line = new PolylineConnection();
		line.setStart(new Point(centerX - 7 * lineLength, centerY - 200));
		line.setEnd(new Point(centerX - 7 * lineLength, centerY + 200));
		line.setLineWidth(1);
		line.setForegroundColor(ColorConstants.blue);
		baseData.getPanel().add(line);

		line = new PolylineConnection();
		line.setStart(new Point(centerX - 8 * lineLength, centerY - 200));
		line.setEnd(new Point(centerX - 8 * lineLength, centerY + 200));
		line.setLineWidth(1);
		line.setForegroundColor(ColorConstants.blue);
		baseData.getPanel().add(line);

		line = new PolylineConnection();
		line.setStart(new Point(centerX - 9 * lineLength, centerY - 200));
		line.setEnd(new Point(centerX - 9 * lineLength, centerY + 200));
		line.setLineWidth(1);
		line.setForegroundColor(ColorConstants.blue);
		baseData.getPanel().add(line);

		// --------------
		line = new PolylineConnection();
		line.setStart(new Point(centerX + lineLength, centerY - 200));
		line.setEnd(new Point(centerX + lineLength, centerY + 200));
		line.setLineWidth(1);
		line.setForegroundColor(ColorConstants.blue);
		baseData.getPanel().add(line);

		line = new PolylineConnection();
		line.setStart(new Point(centerX + 2 * lineLength, centerY - 200));
		line.setEnd(new Point(centerX + 2 * lineLength, centerY + 200));
		line.setLineWidth(1);
		line.setForegroundColor(ColorConstants.blue);
		baseData.getPanel().add(line);

		line = new PolylineConnection();
		line.setStart(new Point(centerX + 3 * lineLength, centerY - 200));
		line.setEnd(new Point(centerX + 3 * lineLength, centerY + 200));
		line.setLineWidth(1);
		line.setForegroundColor(ColorConstants.blue);
		baseData.getPanel().add(line);

		line = new PolylineConnection();
		line.setStart(new Point(centerX + 4 * lineLength, centerY - 200));
		line.setEnd(new Point(centerX + 4 * lineLength, centerY + 200));
		line.setLineWidth(1);
		line.setForegroundColor(ColorConstants.blue);
		baseData.getPanel().add(line);

		line = new PolylineConnection();
		line.setStart(new Point(centerX + 5 * lineLength, centerY - 200));
		line.setEnd(new Point(centerX + 5 * lineLength, centerY + 200));
		line.setLineWidth(1);
		line.setForegroundColor(ColorConstants.blue);
		baseData.getPanel().add(line);

		line = new PolylineConnection();
		line.setStart(new Point(centerX + 6 * lineLength, centerY - 200));
		line.setEnd(new Point(centerX + 6 * lineLength, centerY + 200));
		line.setLineWidth(1);
		line.setForegroundColor(ColorConstants.blue);
		baseData.getPanel().add(line);

		line = new PolylineConnection();
		line.setStart(new Point(centerX + 7 * lineLength, centerY - 200));
		line.setEnd(new Point(centerX + 7 * lineLength, centerY + 200));
		line.setLineWidth(1);
		line.setForegroundColor(ColorConstants.blue);
		baseData.getPanel().add(line);

		line = new PolylineConnection();
		line.setStart(new Point(centerX + 8 * lineLength, centerY - 200));
		line.setEnd(new Point(centerX + 8 * lineLength, centerY + 200));
		line.setLineWidth(1);
		line.setForegroundColor(ColorConstants.blue);
		baseData.getPanel().add(line);

		line = new PolylineConnection();
		line.setStart(new Point(centerX + 9 * lineLength, centerY - 200));
		line.setEnd(new Point(centerX + 9 * lineLength, centerY + 200));
		line.setLineWidth(1);
		line.setForegroundColor(ColorConstants.blue);
		baseData.getPanel().add(line);

	}

}
