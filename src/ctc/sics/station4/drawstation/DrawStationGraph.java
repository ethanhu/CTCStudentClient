package ctc.sics.station4.drawstation;

import java.util.ArrayList;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;

import ctc.sics.SicsMain;
import ctc.sics.ui.SicsMainWindow;

import ctc.sics.station4.elements.common.*;
import ctc.sics.station4.elements.semaphore.*;
import ctc.sics.station4.elements.turnout.*;

import ctc.sics.station4.data.*;
import ctc.sics.station4.layout.*;
import ctc.sics.station4.autosystem.*;
import ctc.sics.station4.buttonListener.*;




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

	BaseParam baseData = BaseParam.getInstance();
	
	//--------基本参数----------//	
	public int X = baseData.getCenterX() - 60; //中心X
	public int Y = baseData.getCenterY() - 80; //中心Y
	public int len = baseData.getRowSpacing(); //股道单位长度
	public int dis = baseData.getDistanceLen(); //label与物体的距离
	public int bLen = baseData.getButtonLength();
	public Color bColor = baseData.getButtonInitColor();
	public Color dColor = baseData.getD_buttonInitColor();
	public Color yColor = baseData.getY_buttonInitColor();
	
	public int ylength = 5;
	public int xlength = 30;
	public int lineLength;

	public TrackLine trackLine; // 股道线
	public CutLine cutLine; //绝缘线
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

	public Figure panel = baseData.getPanel();
	
	// 画车站
	public void drawStation() {

		allRoadFigureList = baseData.getAllRoadFigureList();
		allTrackLineFigureList = baseData.getAllTrackLineFigureList();
		allTurnoutFigureList = baseData.getAllTurnoutFigureList();
		allButtonFigureList = baseData.getAllButtonFigureList();
		allTurnoutButtonFigureList = baseData.getAllTurnoutButtonFigureList();

	}
	
	//显示消息
	public static void showStationControlMsg(Boolean result){
		MessageBox mb = new MessageBox(SicsMainWindow.getShell(), SWT.ABORT | SWT.ICON_INFORMATION);
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
		Color fontColor;
		if (baseData.getBackgroundColor().equals(ColorConstants.white)) {
			fontColor = ColorConstants.black;
		} else {
			fontColor = ColorConstants.white;
		}	
		String stationName = "车站四";
		Label stationNameLabel = new Label(stationName);
		stationNameLabel.setSize(stationName.length()*25, 30);	
		stationNameLabel.setFont(baseData.getFont2());
		stationNameLabel.setForegroundColor(fontColor);
		stationNameLabel.setLocation(new Point(X - stationNameLabel.getSize().width/2, 30));
		stationNameLabel.setBorder(new LineBorder());
		panel.add(stationNameLabel);
		
	}

	// 绘制股道线
	public void drawTrackLine() {
		
		drawTrackLine1();
		drawTrackLine2();
		drawTrackLine3();
		drawTrackLine4();
		drawTrackLine5();
		drawTrackLine6();
		

	}

	
	//绘制股道线1
	public void drawTrackLine1(){
				
		// ------股道1-------//
		// 股道X1JJ
		baseData.setX1jj(new TrackLine("X1JJ", X - 10*len, Y, len, "X")); // 股道线
		allRoadFigureList.add(baseData.getX1jj());
		
		// 股道X2JJ
		baseData.setX2jj(new TrackLine("X2JJ", X - 9*len, Y, len, "X")); // 股道线
		allRoadFigureList.add(baseData.getX2jj());
		
		// 股道X3JJ
		baseData.setX3jj(new TrackLine("X3JJ", X - 8*len, Y, len, "X")); // 股道线
		allRoadFigureList.add(baseData.getX3jj());

		// 股道X4JJ
		baseData.setX4jj(new TrackLine("X4JJ", X - 7*len, Y, len, "X")); // 股道线
		allRoadFigureList.add(baseData.getX4jj());

		//设置为停车股道
		baseData.setTrackline_xxjz(baseData.getX4jj()); 
		
		//baseData.getX4jj().setColorStatus(ParamFlag.trackline_red);
		//baseData.getX4jj().getTrainLabel().setText("K2");
		//baseData.getX4jj().getTrainLabel().setVisible(true);
		
		// 股道I-2
		trackLine = new TrackLine("I-2", X - 6 * len, Y, len, "X"); // 股道线
		allRoadFigureList.add(trackLine);
		
		//设置为停车股道
		baseData.setTrackline_1_z_2(trackLine);
		
		//绝缘线
		cutLine = new CutLine(X - 5 * len, Y);
		
		// 道岔15/17
		turnoutDoubleL = new TurnoutDoubleL("15/17", "15", "17", X - 5 * len + len / 2, Y + len / 2, "X");
		allRoadFigureList.add(turnoutDoubleL);
		allTurnoutFigureList.add(turnoutDoubleL);

		//绝缘线
		cutLine = new CutLine(X - 4 * len, Y);
		
		// 道岔1/3/5/7
		turnoutDouble = new TurnoutDouble("1/3_5/7", "1", "3", "5", "7", X - 4 * len + len / 2, Y + len / 2, "X");
		allRoadFigureList.add(turnoutDouble);
		allTurnoutFigureList.add(turnoutDouble);

		//绝缘线
		cutLine = new CutLine(X - 3 * len, Y);
		
		// 道岔9
		turnoutLS = new TurnoutLS("9", X - 2 * len, Y - len, "X");
		allRoadFigureList.add(turnoutLS);
		allTurnoutFigureList.add(turnoutLS);

		// 股道I-1
		trackLine = new TrackLine("I-1", X - 2 * len, Y, len, "S"); // 股道线
		allRoadFigureList.add(trackLine);

		// 股道I
		trackLine = new TrackLine("I", X - len, Y, 2 * len, "X"); // 股道线
		allRoadFigureList.add(trackLine);
		

		//设置为停车股道
		baseData.setTrackline_1(trackLine);

		// 股道I+1
		trackLine = new TrackLine("I+1", X + len, Y, len, "X"); // 股道线
		allRoadFigureList.add(trackLine);
		

		//绝缘线
		cutLine = new CutLine(X + 2 * len, Y);
		
		// 道岔10
		turnoutRS = new TurnoutRS("10", X + 2 * len, Y - len, "S");
		allRoadFigureList.add(turnoutRS);
		allTurnoutFigureList.add(turnoutRS);

		//绝缘线
		cutLine = new CutLine(X + 3 * len, Y);
		
		// 道岔14/16
		turnoutDoubleL = new TurnoutDoubleL("14/16", "14", "16", X + 3 * len + len / 2, Y + len / 2, "S");
		allRoadFigureList.add(turnoutDoubleL);
		allTurnoutFigureList.add(turnoutDoubleL);

		// 股道I+2
		trackLine = new TrackLine("I+2", X + 4 * len, Y, len, "X"); // 股道线
		allRoadFigureList.add(trackLine);

		//设置为停车股道
		baseData.setTrackline_1_y_2(trackLine);
		
		//绝缘线
		cutLine = new CutLine(X + 5 * len, Y);
		
		// 道岔18/20
		turnoutDoubleR = new TurnoutDoubleR("18/20", "18", "20", X + 5 * len + len / 2, Y + len / 2, "S");
		allRoadFigureList.add(turnoutDoubleR);
		allTurnoutFigureList.add(turnoutDoubleR);

		// 股道I+3
		trackLine = new TrackLine("I+3", X + 6 * len, Y, len, "X"); // 股道线
		allRoadFigureList.add(trackLine);

		// 股道I+4
		trackLine = new TrackLine("I+4", X + 7 * len, Y, len, "X"); // 股道线
		allRoadFigureList.add(trackLine);


		//设置为停车股道
		baseData.setTrackline_1_y_4(trackLine);
		
		
		// 股道X1LQ
		baseData.setX1lq(new TrackLine("X1LQ", X + 8 * len, Y, len, "X")); // 股道线
		allRoadFigureList.add(baseData.getX1lq());

		// 股道X2LQ
		baseData.setX2lq(new TrackLine("X2LQ", X + 9 * len, Y, len, "X")); // 股道线
		allRoadFigureList.add(baseData.getX2lq());
		
		// 股道X3LQ
		baseData.setX3lq(new TrackLine("X3LQ", X + 10 * len, Y, len, "X")); // 股道线
		allRoadFigureList.add(BaseParam.getX3lq());

		
		// 股道X4LQ
		baseData.setX4lq(new TrackLine("X4LQ", X + 11 * len, Y, len, "X")); // 股道线
		allRoadFigureList.add(baseData.getX4lq());

		//绝缘线
		cutLine = new CutLine(X + 12 * len, Y);
		

		
	}
	
	//绘制股道线2
	public void drawTrackLine2(){
		
		// -----------股道II--------------//
		// 股道S4LQ
		baseData.setS4lq(new TrackLine("S4LQ", X - 10 * len, Y + len, len, "S")); // 股道线
		allRoadFigureList.add(baseData.getS4lq());
		
		// 股道S3LQ
		baseData.setS3lq(new TrackLine("S3LQ", X - 9 * len, Y + len, len, "S")); // 股道线
		allRoadFigureList.add(baseData.getS3lq());
		
		// 股道S2LQ
		baseData.setS2lq(new TrackLine("S2LQ", X - 8 * len, Y + len, len, "S")); // 股道线
		allRoadFigureList.add(baseData.getS2lq());

		// 股道S1LQ
		baseData.setS1lq(new TrackLine("S1LQ", X - 7 * len, Y + len, len, "S")); // 股道线
		allRoadFigureList.add(baseData.getS1lq());

		// 股道II-2
		trackLine = new TrackLine("II-2", X - 6 * len, Y + len, len, "S"); // 股道线
		allRoadFigureList.add(trackLine);

		//设置为停车股道
		baseData.setTrackline_2_z_2(trackLine);
		
		//绝缘线
		cutLine = new CutLine(X - 5 * len, Y + len);

		//绝缘线
		cutLine = new CutLine(X - 4 * len, Y + len);

		//绝缘线
		cutLine = new CutLine(X - 3 * len, Y + len);
		
		// 道岔11
		turnoutLX = new TurnoutLX("11", X - 3 * len, Y + len, "X");
		allRoadFigureList.add(turnoutLX);
		allTurnoutFigureList.add(turnoutLX);

		//绝缘线
		cutLine = new CutLine(X - 2 * len, Y + len);
		
		// 股道II-1
		trackLine = new TrackLine("II-1", X - 2 * len, Y + len, len, "S"); // 股道线
		allRoadFigureList.add(trackLine);

		// 股道II
		trackLine = new TrackLine("II", X - len, Y + len, 2 * len, "X"); // 股道线
		allRoadFigureList.add(trackLine);

		baseData.setTrackline_2(trackLine);

		// 股道II+1
		trackLine = new TrackLine("II+1", X + len, Y + len, len, "X"); // 股道线
		allRoadFigureList.add(trackLine);

		// 股道II+2
		trackLine = new TrackLine("II+2", X + 2 * len, Y + len, len, "X"); // 股道线
		allRoadFigureList.add(trackLine);

		//绝缘线
		cutLine = new CutLine(X + 3 * len, Y + len);
		
		// 道岔2/4/6/8
		turnoutDouble = new TurnoutDouble("2/4_6/8", "2", "4", "6", "8", X + 4 * len + len / 2, Y + 3 * len / 2, "S");
		allRoadFigureList.add(turnoutDouble);
		allTurnoutFigureList.add(turnoutDouble);


		//绝缘线
		cutLine = new CutLine(X + 4 * len, Y + len);
		
		//绝缘线
		cutLine = new CutLine(X + 5 * len, Y + len);
		
		// 股道II+3
		trackLine = new TrackLine("II+3", X + 6 * len, Y + len, len, "X"); // 股道线
		allRoadFigureList.add(trackLine);

		// 股道II+4
		trackLine = new TrackLine("II+4", X + 7 * len, Y + len, len, "S"); // 股道线
		allRoadFigureList.add(trackLine);

		//设置为停车股道
		baseData.setTrackline_2_y_4(trackLine);
		
		// 股道S4JJ
		baseData.setS4jj(new TrackLine("S4JJ", X + 8 * len, Y + len, len, "S")); // 股道线
		allRoadFigureList.add(baseData.getS4jj());

		baseData.setTrackline_sxjz(baseData.getS4jj());
		//baseData.getSd2jj().setColorStatus(ParamFlag.trackline_red);

		// 股道S3JJ
		baseData.setS3jj(new TrackLine("S3JJ", X + 9 * len, Y + len, len, "S")); // 股道线
		allRoadFigureList.add(baseData.getS3jj());

		// 股道S2JJ
		baseData.setS2jj(new TrackLine("S2JJ", X + 10 * len, Y + len, len, "S")); // 股道线
		allRoadFigureList.add(baseData.getS2jj());

		baseData.setTrackline_sxjz(baseData.getS2jj());
		//baseData.getSd2jj().setColorStatus(ParamFlag.trackline_red);

		// 股道S1JJ
		baseData.setS1jj(new TrackLine("S1JJ", X + 11 * len, Y + len, len, "S")); // 股道线
		allRoadFigureList.add(baseData.getS1jj());

		//绝缘线
		cutLine = new CutLine(X + 12 * len, Y + len);
		
		
	}
	
	//绘制股道线3
	public void drawTrackLine3(){
		
		// ----------股道3------------//
		// 股道3-1
		trackLine = new TrackLine("3-1", X - 2 * len, Y - len, len, "S"); // 股道线
		trackLine.getSline().setVisible(false);
		allRoadFigureList.add(trackLine);

		// 股道3
		trackLine = new TrackLine("3", X - len, Y - len, 2 * len, "X"); // 股道线
		allRoadFigureList.add(trackLine);

		baseData.setTrackline_3(trackLine);

		// 股道3+1
		trackLine = new TrackLine("3+1", X + len, Y - len, len, "X"); // 股道线
		allRoadFigureList.add(trackLine);


		//绝缘线
		cutLine = new CutLine(X + 2 * len, Y - len);
		
		
		// 股道3+2
		trackLine = new TrackLine("3+2", X + 3 * len, Y - len, len, "S"); // 股道线
		allRoadFigureList.add(trackLine);

		//设置为停车股道
		baseData.setTrackline_3_y_2(trackLine);
		
		// 绝缘线
		endLine = new EndLine("Right", "绝缘线", X + 4 * len, Y - len);
		// allRoadFigureList.add(trackLine);
		
	}
	
	//绘制股道线4
	public void drawTrackLine4(){
		
		// --------------股道4----------------//
		// 道岔13
		turnoutLX = new TurnoutLX("13", X - 2 * len, Y + 2 * len, "X");
		allRoadFigureList.add(turnoutLX);
		allTurnoutFigureList.add(turnoutLX);

		// 股道4-1
		trackLine = new TrackLine("4-1", X - len, Y + 2 * len, len, "S"); // 股道线
		allRoadFigureList.add(trackLine);

		// 股道4
		trackLine = new TrackLine("4", X, Y + 2 * len, 2 * len, "X"); // 股道线
		allRoadFigureList.add(trackLine);

		baseData.setTrackline_4(trackLine);

		// 股道4+1
		trackLine = new TrackLine("4+1", X + 2 * len, Y + 2 * len, len, "X"); // 股道线
		allRoadFigureList.add(trackLine);

		//绝缘线
		cutLine = new CutLine(X + 3 * len, Y + 2*len);
		
		// 道岔12
		turnoutRX = new TurnoutRX("12", X + 4 * len, Y + 2 * len, "S");
		allRoadFigureList.add(turnoutRX);
		allTurnoutFigureList.add(turnoutRX);

		//绝缘线
		cutLine = new CutLine(X + 4 * len, Y + 2*len);
		
		// 股道4+2
		trackLine = new TrackLine("4+2", X + 5 * len, Y + 2 * len, len, "X"); // 股道线
		allRoadFigureList.add(trackLine);

		// 股道4+3
		trackLine = new TrackLine("4+3", X + 6 * len, Y + 2 * len, len, "X"); // 股道线
		allRoadFigureList.add(trackLine);
		
		//设置为停车股道
		baseData.setTrackline_4_y_3(trackLine);
		
		// 股道4+4
		trackLine = new TrackLine("4+4", X + 7 * len, Y + 2 * len, len, "X"); // 股道线
		allRoadFigureList.add(trackLine);

		//设置为停车股道
		baseData.setTrackline_4_y_4(trackLine);
		
		// 股道4+5
		trackLine = new TrackLine("4+5", X + 8 * len, Y + 2 * len, len, "X"); // 股道线
		allRoadFigureList.add(trackLine);

		//设置为停车股道
		baseData.setTrackline_4_y_5(trackLine);
		
		// 绝缘线
		endLine = new EndLine("Right", "绝缘线", X + 9 * len, Y + 2 * len);
		// allRoadFigureList.add(trackLine);
		
	}
	
	//绘制股道线5
	public void drawTrackLine5(){
		
		// ----------股道5------------//
		endLine = new EndLine("Left", "绝缘线", X, Y - 2 * len);
		// allRoadFigureList.add(endLine);

		// 股道5
		trackLine = new TrackLine("5", X, Y - 2 * len, len, "X"); // 股道线
		allRoadFigureList.add(trackLine);

		baseData.setTrackline_5(trackLine);

		// 股道5+1
		trackLine = new TrackLine("5+1", X + len, Y - 2 * len, len, "X"); // 股道线
		allRoadFigureList.add(trackLine);

		// 道岔26
		turnoutRS = new TurnoutRS("26", X + 2 * len, Y - 2 * len, "S");
		allRoadFigureList.add(turnoutRS);
		allTurnoutFigureList.add(turnoutRS);
		
	}
	
	//绘制股道线6
	public void drawTrackLine6(){
		
		// -------------股道6-------------------//

		// 股道6-1
		trackLine = new TrackLine("6-1", X - len, Y + 3 * len, len, "S"); // 股道线
		trackLine.getSline().setVisible(false);
		allRoadFigureList.add(trackLine);

		// 股道6
		trackLine = new TrackLine("6", X, Y + 3 * len, 2 * len, "X"); // 股道线
		allRoadFigureList.add(trackLine);

		baseData.setTrackline_6(trackLine);

		// 股道6+1
		trackLine = new TrackLine("6+1", X + 2 * len, Y + 3 * len, len, "X"); // 股道线
		allRoadFigureList.add(trackLine);

		// baseData.setAllTurnoutFigureList(allTurnoutFigureList);
		
	}
	
	
	// 绘制信号机器
	public void drawSemaphore() {

		int d = baseData.getSemaphoreLightDiameter();

		// 信号机X
		semaphoreDoubleR = new SemaphoreDoubleR("X", X - 6 * len, Y - d - dis, "S");
		allRoadFigureList.add(semaphoreDoubleR);
		// 信号机S
		semaphoreDoubleL = new SemaphoreDoubleL("S", X + 8 * len, Y + len + dis, "X");
		allRoadFigureList.add(semaphoreDoubleL);

		// 信号机S1
		semaphoreDoubleL = new SemaphoreDoubleL("S1", X - len, Y + dis, "X");
		allRoadFigureList.add(semaphoreDoubleL);		
		// 信号机X1
		semaphoreDoubleR = new SemaphoreDoubleR("X1",  X + len, Y - d - dis, "S");
		allRoadFigureList.add(semaphoreDoubleR);
		
		// 信号及S2
		semaphoreDoubleL = new SemaphoreDoubleL("S2", X - len, Y + len + dis, "X");
		allRoadFigureList.add(semaphoreDoubleL);		
		// 信号及X2
		semaphoreDoubleR = new SemaphoreDoubleR("X2", X + len, Y +len - d - dis, "S");
		allRoadFigureList.add(semaphoreDoubleR);
		
		// 信号及S3
		semaphoreDoubleL = new SemaphoreDoubleL("S3", X - len, Y -len + dis, "X");
		allRoadFigureList.add(semaphoreDoubleL);		
		// 信号机X3
		semaphoreDoubleR = new SemaphoreDoubleR("X3", X + len, Y - len - d - dis, "S");
		allRoadFigureList.add(semaphoreDoubleR);
		
		// 信号及S4
		semaphoreDoubleL = new SemaphoreDoubleL("S4", X, Y + 2*len + dis, "X");
		allRoadFigureList.add(semaphoreDoubleL);
		
		// 信号及X4
		semaphoreDoubleR = new SemaphoreDoubleR("X4", X + 2*len, Y + 2*len - d - dis, "S");
		allRoadFigureList.add(semaphoreDoubleR);
				
		// 信号及S6
		semaphoreDoubleL = new SemaphoreDoubleL("S6", X, Y + 3*len + dis, "X");
		allRoadFigureList.add(semaphoreDoubleL);
		
		// 信号及X6
		semaphoreDoubleR = new SemaphoreDoubleR("X6", X + 2*len, Y + 3*len - d - dis, "S");
		allRoadFigureList.add(semaphoreDoubleR);
		
		
		/////////////////////////////////////////////
		// 信号机D11
		semaphoreSimpleR = new SemaphoreSimpleR("D11", X - 5*len, Y - d - dis, "S");
		allRoadFigureList.add(semaphoreSimpleR);
		
		// 信号机D12
		semaphoreSimpleR = new SemaphoreSimpleR("D12", X + 5*len, Y - d - dis, "S");
		allRoadFigureList.add(semaphoreSimpleR);
		
		// 信号机D13
		semaphoreSimpleL = new SemaphoreSimpleL("D13", X + 4*len, Y + dis, "X");
		allRoadFigureList.add(semaphoreSimpleL);
		
		// 信号机D14
		semaphoreSimpleL = new SemaphoreSimpleL("D14", X + 7*len, Y + dis, "X");
		allRoadFigureList.add(semaphoreSimpleL);
				
		// 信号机D21
		semaphoreSimpleR = new SemaphoreSimpleR("D21", X - 5*len, Y - d - dis + len, "S");
		allRoadFigureList.add(semaphoreSimpleR);
		
		// 信号机D22
		semaphoreSimpleL = new SemaphoreSimpleL("D22", X + 7 * len, Y + len + dis, "X");
		allRoadFigureList.add(semaphoreSimpleL);
		
		// 信号机D31
		semaphoreSimpleL = new SemaphoreSimpleL("D31", X + 3 * len, Y - len + dis, "X");
		allRoadFigureList.add(semaphoreSimpleL);
		
		// 信号机D41
		semaphoreSimpleL = new SemaphoreSimpleL("D41", X + 6 * len, Y + 2 * len + dis, "X");
		allRoadFigureList.add(semaphoreSimpleL);
		
		// 信号机D42
		semaphoreSimpleL = new SemaphoreSimpleL("D42", X + 7 * len, Y + 2 * len + dis, "X");
		allRoadFigureList.add(semaphoreSimpleL);
		
		// 信号机D43
		semaphoreSimpleL = new SemaphoreSimpleL("D43", X + 8 * len, Y + 2 * len + dis, "X");
		allRoadFigureList.add(semaphoreSimpleL);

		// 信号机D44
		semaphoreSimpleR = new SemaphoreSimpleR("D44", X + 7*len, Y + 2 * len - d - dis, "S");
		allRoadFigureList.add(semaphoreSimpleR);
		
		// 信号机D51
		semaphoreSimpleR = new SemaphoreSimpleR("D51", X + len, Y - 2 * len - d - dis, "S");
		allRoadFigureList.add(semaphoreSimpleR);
		
		
		
		///////////////////////////////////////////////////////////////
		//信号机X1JJ
		semaphoreDoubleR = new SemaphoreDoubleR("X1JJ", X - 7 * len, Y - d - dis, "S");
		semaphoreDoubleR.setColorStatus(ParamFlag.sep_green);
		allRoadFigureList.add(semaphoreDoubleR);
		//信号机X2JJ
		semaphoreDoubleR = new SemaphoreDoubleR("X2JJ", X - 8 * len, Y - d - dis, "S");
		semaphoreDoubleR.setColorStatus(ParamFlag.sep_green);
		allRoadFigureList.add(semaphoreDoubleR);
		//信号机X3JJ
		semaphoreDoubleR = new SemaphoreDoubleR("X3JJ", X - 9 * len, Y - d - dis, "S");
		semaphoreDoubleR.setColorStatus(ParamFlag.sep_green);
		allRoadFigureList.add(semaphoreDoubleR);
		//信号机X4JJ
		semaphoreDoubleR = new SemaphoreDoubleR("X4JJ", X - 10 * len, Y - d - dis, "S");
		semaphoreDoubleR.setColorStatus(ParamFlag.sep_green);
		allRoadFigureList.add(semaphoreDoubleR);
		
		
		// 信号机XN
		semaphoreDoubleR = new SemaphoreDoubleR("XN", X - 6 * len, Y + len + dis, "X");
		allRoadFigureList.add(semaphoreDoubleR);		
		// 信号机S2LQ
		semaphoreDoubleL = new SemaphoreDoubleL("S2LQ", X - 7 * len, Y + len + dis, "X");
		semaphoreDoubleL.setColorStatus(ParamFlag.sep_green);
		allRoadFigureList.add(semaphoreDoubleL);		
		// 信号机S3LQ
		semaphoreDoubleL = new SemaphoreDoubleL("S3LQ", X - 8 * len, Y + len + dis, "X");
		semaphoreDoubleL.setColorStatus(ParamFlag.sep_green);
		allRoadFigureList.add(semaphoreDoubleL);		
		// 信号机S4LQ
		semaphoreDoubleL = new SemaphoreDoubleL("S4LQ", X - 9 * len, Y + len + dis, "X");
		semaphoreDoubleL.setColorStatus(ParamFlag.sep_green);
		allRoadFigureList.add(semaphoreDoubleL);
		
				
		// 信号机SN
		semaphoreDoubleL = new SemaphoreDoubleL("SN", X + 8 * len, Y - bLen - dis, "S");
		allRoadFigureList.add(semaphoreDoubleL);
		//信号机X2LQ
		semaphoreDoubleR = new SemaphoreDoubleR("X2LQ", X + 9 * len, Y - bLen - dis, "S");
		semaphoreDoubleR.setColorStatus(ParamFlag.sep_green);
		allRoadFigureList.add(semaphoreDoubleR);
		//信号机X3LQ
		semaphoreDoubleR = new SemaphoreDoubleR("X3LQ", X + 10 * len, Y - bLen - dis, "S");
		semaphoreDoubleR.setColorStatus(ParamFlag.sep_green);
		allRoadFigureList.add(semaphoreDoubleR);
		//信号机X4LQ
		semaphoreDoubleR = new SemaphoreDoubleR("X4LQ", X + 11 * len, Y - bLen - dis, "S");
		semaphoreDoubleR.setColorStatus(ParamFlag.sep_green);
		allRoadFigureList.add(semaphoreDoubleR);
		
		// 信号机S4JJ
		semaphoreDoubleL = new SemaphoreDoubleL("S4JJ", X + 9 * len, Y + len + dis, "X");
		semaphoreDoubleL.setColorStatus(ParamFlag.sep_green);
		allRoadFigureList.add(semaphoreDoubleL);		
		// 信号机S3JJ
		semaphoreDoubleL = new SemaphoreDoubleL("S3JJ", X + 10 * len, Y + len + dis, "X");
		semaphoreDoubleL.setColorStatus(ParamFlag.sep_green);
		allRoadFigureList.add(semaphoreDoubleL);		
		// 信号机S2JJ
		semaphoreDoubleL = new SemaphoreDoubleL("S2JJ", X + 11 * len, Y + len + dis, "X");
		semaphoreDoubleL.setColorStatus(ParamFlag.sep_green);
		allRoadFigureList.add(semaphoreDoubleL);		
		// 信号机S1JJ
		semaphoreDoubleL = new SemaphoreDoubleL("S1JJ", X + 12 * len, Y + len + dis, "X");
		semaphoreDoubleL.setColorStatus(ParamFlag.sep_green);
		allRoadFigureList.add(semaphoreDoubleL);
		
		
	}

	// 绘制业务控制按钮
	public void drawModelButton() {

		int initX = 80;
		int initY = 100;
		int xlength = 80;
		int ylength = 15;

		// X道岔总定位
		label = new LabelFree("X总定位", initX, initY);
		turnoutButton = new TurnoutButton("XDCZDW", "X");
		turnoutButton.setSize(bLen, bLen);
		turnoutButton.setLocation(new Point(initX, initY));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new ModelButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);

		// X道岔总反位
		label = new LabelFree("X总反位", initX + xlength, initY);
		turnoutButton = new TurnoutButton("XDCZFW", "X");
		turnoutButton.setSize(bLen, bLen);
		turnoutButton.setLocation(new Point(initX + xlength, initY));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new ModelButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);

		// 申请站控
		label = new LabelFree("申请站控", initX + 2 * xlength, initY);
		turnoutButton = new TurnoutButton("SQZK", "X");
		turnoutButton.setSize(bLen, bLen);
		turnoutButton.setLocation(new Point(initX + 2 * xlength, initY));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new SpecialButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);

		// S道岔总定位
		label = new LabelFree("S总定位", panelWidth - 3 * labelWidth, initY);
		turnoutButton = new TurnoutButton("SDCZDW", "S");
		turnoutButton.setSize(bLen, bLen);
		turnoutButton.setLocation(new Point(panelWidth - 3 * labelWidth, initY));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new ModelButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);

		// S道岔总反位
		label = new LabelFree("S总反位", panelWidth - 3 * labelWidth - xlength, initY);
		turnoutButton = new TurnoutButton("SDCZFW", "S");
		turnoutButton.setSize(bLen, bLen);
		turnoutButton.setLocation(new Point(panelWidth - 3 * labelWidth - xlength, initY));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new ModelButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);

		// 非常站控
		label = new LabelFree("非常站控", panelWidth - 3 * labelWidth - 2 * xlength, initY);
		turnoutButton = new TurnoutButton("FCZK", "S");
		turnoutButton.setSize(bLen, bLen);
		turnoutButton.setLocation(new Point(panelWidth - 3 * labelWidth - 2 * xlength, initY));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new SpecialButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);

		// -----------------------界面下半部分按钮绘制----------------------------//

		int initY2 = panelHeight - 60;

		// X排列进路
		label = new LabelFree("X引总锁", initX, initY2);
		turnoutButton = new TurnoutButton("XYZS", "X");
		turnoutButton.setSize(bLen, bLen);
		turnoutButton.setLocation(new Point(initX, initY2));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new ModelButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);

		// X总人工解锁
		label = new LabelFree("X总人解", initX + xlength, initY2);
		turnoutButton = new TurnoutButton("XZRGJS", "X");
		turnoutButton.setSize(bLen, bLen);
		turnoutButton.setLocation(new Point(initX + xlength, initY2));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new ModelButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);

		// X取消
		label = new LabelFree("X总取消", initX + 2 * xlength, initY2);
		turnoutButton = new TurnoutButton("XZQX", "X");
		turnoutButton.setSize(bLen, bLen);
		turnoutButton.setLocation(new Point(initX + 2 * xlength, initY2));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new ModelButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);
		
		// X反向操作
		label = new LabelFree("X反向操作", initX + 3 * xlength, initY2);
		turnoutButton = new TurnoutButton("XF", "X");
		turnoutButton.setSize(bLen, bLen);
		turnoutButton.setLocation(new Point(initX + 3 * xlength, initY2));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new ModelButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);

		
		// S引总锁
		label = new LabelFree("S引总锁", panelWidth - initX - bLen, initY2);
		turnoutButton = new TurnoutButton("SYZS", "S");
		turnoutButton.setSize(bLen, bLen);
		turnoutButton.setLocation(new Point(panelWidth - initX - bLen, initY2));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new ModelButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);

		// S总人解
		label = new LabelFree("S总人解", panelWidth - initX - bLen - xlength, initY2);
		turnoutButton = new TurnoutButton("SZRGJS", "S");
		turnoutButton.setSize(bLen, bLen);
		turnoutButton.setLocation(new Point(panelWidth - initX - bLen - xlength, initY2));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new ModelButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);

		// S总取消
		label = new LabelFree("S总取消", panelWidth - initX - bLen - 2*xlength, initY2);
		turnoutButton = new TurnoutButton("SZQX", "S");
		turnoutButton.setSize(bLen, bLen);
		turnoutButton.setLocation(new Point(panelWidth - initX - bLen - 2*xlength, initY2));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new ModelButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);
		
		// 区故解
		label = new LabelFree("区故解", panelWidth - initX - bLen - 3*xlength, initY2);
		turnoutButton = new TurnoutButton("QGJ", "S");
		turnoutButton.setSize(bLen, bLen);
		turnoutButton.setLocation(new Point(panelWidth - initX - bLen - 3*xlength, initY2));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new ModelButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);
		
		// S反向操作
		label = new LabelFree("S反向操作", panelWidth - initX - bLen - 4*xlength, initY2);
		turnoutButton = new TurnoutButton("SF", "S");
		turnoutButton.setSize(bLen, bLen);
		turnoutButton.setLocation(new Point(panelWidth - initX - bLen - 4*xlength, initY2));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new ModelButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);
		
		
/*
		// 显示任务按钮
		label = new LabelFree("设置故障", panelWidth / 2 - 3 * labelWidth / 2, initY2);
		turnoutButton = new TurnoutButton("SZGZ", "X");
		turnoutButton.setSize(2 * bLen, bLen);
		turnoutButton.setLocation(new Point(panelWidth / 2 - bLen, initY2 + ylength));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new ModelButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);
*/
		
		

		// 显示文字
		label = new LabelFree("股道名称", X - 3*xlength/2, initY2);
		turnoutButton = new TurnoutButton("XSGD", "X");
		turnoutButton.setSize(bLen, bLen);
		turnoutButton.setLocation(new Point(X - 3*xlength/2, initY2));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new SpecialButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);

		
		// 显示文字
		label = new LabelFree("道岔名称",  X - xlength/2, initY2);
		turnoutButton = new TurnoutButton("XSDC", "X");
		turnoutButton.setSize(bLen, bLen);
		turnoutButton.setLocation(new Point( X - xlength/2, initY2));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new SpecialButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);
		
		// 显示文字
		label = new LabelFree("按钮名称", X + xlength/2, initY2);
		turnoutButton = new TurnoutButton("XSAN", "X");
		turnoutButton.setSize(bLen, bLen);
		turnoutButton.setLocation(new Point(X + xlength/2, initY2));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new SpecialButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);
		
		// 显示文字
		label = new LabelFree("信号名称", X + 3*xlength/2, initY2);
		turnoutButton = new TurnoutButton("XSXH", "X");
		turnoutButton.setSize(bLen, bLen);
		turnoutButton.setLocation(new Point(X + 3*xlength/2, initY2));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new SpecialButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);
		
		
		
		
	}

	// 绘制道岔控制按钮
	public void drawTurnoutButton() {

		int initX = 50;
		int initY = 50;
		int xlength = 40;
		int ylength = 12;

		// 绘制1/3
		label = new LabelFree("1/3", initX, initY);
		turnoutButton = new TurnoutButton("1/3", "X");
		turnoutButton.setSize(bLen, bLen);
		turnoutButton.setLocation(new Point(initX, initY));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new TurnoutButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);

		// 绘制5/7
		label = new LabelFree("5/7", initX + xlength, initY);
		turnoutButton = new TurnoutButton("5/7", "X");
		turnoutButton.setSize(bLen, bLen);
		turnoutButton.setLocation(new Point(initX + xlength, initY));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new TurnoutButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);

		// 绘制9
		label = new LabelFree("9", initX + 2 * xlength, initY);
		turnoutButton = new TurnoutButton("9", "X");
		turnoutButton.setSize(bLen, bLen);
		turnoutButton.setLocation(new Point(initX + 2 * xlength, initY));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new TurnoutButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);

		// 绘制11
		label = new LabelFree("11", initX + 3 * xlength, initY);
		turnoutButton = new TurnoutButton("11", "X");
		turnoutButton.setSize(bLen, bLen);
		turnoutButton.setLocation(new Point(initX + 3 * xlength, initY));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new TurnoutButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);

		// 绘制13
		label = new LabelFree("13", initX + 4 * xlength, initY);
		turnoutButton = new TurnoutButton("13", "X");
		turnoutButton.setSize(bLen, bLen);
		turnoutButton.setLocation(new Point(initX + 4 * xlength, initY));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new TurnoutButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);
		
		// 绘制15/17
		label = new LabelFree("15/17", initX + 5 * xlength, initY);
		turnoutButton = new TurnoutButton("15/17", "X");
		turnoutButton.setSize(bLen, bLen);
		turnoutButton.setLocation(new Point(initX + 5 * xlength, initY));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new TurnoutButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);
		

		// 绘制2/4
		label = new LabelFree("2/4", panelWidth - initX - labelWidth, initY);
		turnoutButton = new TurnoutButton("2/4", "S");
		turnoutButton.setSize(bLen, bLen);
		turnoutButton.setLocation(new Point(panelWidth - initX - labelWidth, initY));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new TurnoutButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);

		// 绘制6/8
		label = new LabelFree("6/8", panelWidth - initX - labelWidth - xlength, initY);
		turnoutButton = new TurnoutButton("6/8", "S");
		turnoutButton.setSize(bLen, bLen);
		turnoutButton.setLocation(new Point(panelWidth - initX - labelWidth - xlength, initY));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new TurnoutButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);

		// 绘制10
		label = new LabelFree("10", panelWidth - initX - labelWidth - 2 * xlength, initY);
		turnoutButton = new TurnoutButton("10", "S");
		turnoutButton.setSize(bLen, bLen);
		turnoutButton.setLocation(new Point(panelWidth - initX - labelWidth - 2 * xlength, initY));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new TurnoutButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);

		// 绘制12
		label = new LabelFree("12", panelWidth - initX - labelWidth - 3 * xlength, initY);
		turnoutButton = new TurnoutButton("12", "S");
		turnoutButton.setSize(bLen, bLen);
		turnoutButton.setLocation(new Point(panelWidth - initX - labelWidth - 3 * xlength, initY));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new TurnoutButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);

		// 绘制14/16
		label = new LabelFree("14/16", panelWidth - initX - labelWidth - 4 * xlength, initY);
		turnoutButton = new TurnoutButton("14/16", "S");
		turnoutButton.setSize(bLen, bLen);
		turnoutButton.setLocation(new Point(panelWidth - initX - labelWidth - 4 * xlength, initY));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new TurnoutButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);

		// 绘制18/20
		label = new LabelFree("18/20", panelWidth - initX - labelWidth - 5 * xlength, initY);
		turnoutButton = new TurnoutButton("18/20", "S");
		turnoutButton.setSize(bLen, bLen);
		turnoutButton.setLocation(new Point(panelWidth - initX - labelWidth - 5 * xlength, initY));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new TurnoutButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);

		// 绘制26
		label = new LabelFree("26", panelWidth - initX - labelWidth - 6 * xlength, initY);
		turnoutButton = new TurnoutButton("26", "S");
		turnoutButton.setSize(bLen, bLen);
		turnoutButton.setLocation(new Point(panelWidth - initX - labelWidth - 6 * xlength, initY));
		turnoutButton.setBackgroundColor(buttonInitColor);
		panel.add(turnoutButton);
		turnoutButton.addActionListener(new TurnoutButtonListener());
		allTurnoutButtonFigureList.add(turnoutButton);


	}

	// 绘制股道控制按钮
	public void drawStationButton() {

		// ----------------进出站按钮------------------//
		// 按钮XLA
		stationButton = new StationButton("XLA", "L");
		stationButton.setSize(bLen, bLen);
		stationButton.setLocation(new Point(X - 6 * len - bLen - dis, Y - bLen - dis));
		stationButton.setBackgroundColor(bColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);

		// 按钮XTA
		stationButton = new StationButton("XTA", "L", X - 6 * len - bLen - dis, Y - 2*bLen - dis - 1, "S");
		stationButton.setSize(bLen, bLen);
		stationButton.setLocation(new Point(X - 6 * len - bLen - dis, Y - 2*bLen - dis - 1));
		stationButton.setBackgroundColor(bColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);
		
		// 按钮XYA
		stationButton = new StationButton("XYA", "Y", X - 6 * len - bLen - dis, Y + dis, "X");
		stationButton.setSize(bLen, bLen);
		stationButton.setLocation(new Point(X - 6 * len - bLen - dis, Y + dis));
		stationButton.setBackgroundColor(yColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);
		
		// 按钮BA
		stationButton = new StationButton("BA", "B", X - 5 * len + len/2, Y - bLen - dis, "S");
		stationButton.setSize(bLen, bLen);
		stationButton.setLocation(new Point(X - 5 * len + len/2, Y - bLen - dis));
		stationButton.setBackgroundColor(bColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);
		
		// 按钮XNLA
		stationButton = new StationButton("XNLA", "L", X - 6 * len - bLen - dis, Y - bLen - dis + len, "S");
		stationButton.setSize(bLen, bLen);
		stationButton.setLocation(new Point(X - 6 * len - bLen - dis, Y - bLen - dis + len));
		stationButton.setBackgroundColor(bColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);
		
		// 按钮XLFA
		stationButton = new StationButton("XLFA", "L");
		stationButton.setSize(bLen, bLen);
		stationButton.setLocation(new Point(X - 6 * len - bLen - dis, Y + dis + len));
		stationButton.setBackgroundColor(bColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);
		
		// 按钮XYFA
		stationButton = new StationButton("XYFA", "Y", X - 6 * len - bLen - dis, Y + bLen + dis + 1 + len, "X");
		stationButton.setSize(bLen, bLen);
		stationButton.setLocation(new Point(X - 6 * len - bLen - dis, Y + bLen + dis + 1 + len));
		stationButton.setBackgroundColor(yColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);
		
		
		
		// 按钮SLFA
		stationButton = new StationButton("SLFA", "B");
		stationButton.setSize(bLen, bLen);
		stationButton.setLocation(new Point(X + 8 * len + 2, Y - bLen - dis));
		stationButton.setBackgroundColor(bColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);
		
		// 按钮SYFA
		stationButton = new StationButton("SYFA", "Y", X + 8 * len + 2, Y - 2*bLen - dis - 1, "S");
		stationButton.setSize(bLen, bLen);
		stationButton.setLocation(new Point(X + 8 * len + 2, Y - 2*bLen - dis - 1));
		stationButton.setBackgroundColor(yColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);
		
		// 按钮SNLA
		stationButton = new StationButton("SNLA", "L", X + 8 * len + 2, Y + dis, "X");
		stationButton.setSize(bLen, bLen);
		stationButton.setLocation(new Point(X + 8 * len + 2, Y + dis));
		stationButton.setBackgroundColor(bColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);
		
		// 按钮SYLA
		stationButton = new StationButton("SYA", "L", X + 8 * len + 2, Y - bLen - dis + len, "S");
		stationButton.setSize(bLen, bLen);
		stationButton.setLocation(new Point(X + 8 * len + 2, Y - bLen - dis + len));
		stationButton.setBackgroundColor(yColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);

		// 按钮SLA
		stationButton = new StationButton("SLA", "L");
		stationButton.setSize(bLen, bLen);
		stationButton.setLocation(new Point(X + 8 * len + 2, Y + dis + len));
		stationButton.setBackgroundColor(bColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);
		
		// 按钮STA
		stationButton = new StationButton("STA", "L", X + 8 * len + 2, Y + bLen + dis + 1 + len, "X");
		stationButton.setSize(bLen, bLen);
		stationButton.setLocation(new Point(X + 8 * len + 2, Y + bLen + dis + 1 + len));
		stationButton.setBackgroundColor(bColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);
		
		
		// -------------股道上按钮（接发车和调车）----------------//
		// 按钮S1DA
		stationButton = new StationButton("S1DA", "D");
		stationButton.setSize(bLen, bLen);
		stationButton.setLocation(new Point(X - len + 2, Y + dis));
		stationButton.setBackgroundColor(dColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);
		
		// 按钮S1LA
		stationButton = new StationButton("S1LA", "L");
		stationButton.setSize(bLen, bLen);
		stationButton.setLocation(new Point(X - len + 4 + bLen, Y + dis));
		stationButton.setBackgroundColor(bColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);
		
		// 按钮S2DA
		stationButton = new StationButton("S2DA", "D");
		stationButton.setSize(bLen, bLen);
		stationButton.setLocation(new Point(X - len + 2, Y + len + dis));
		stationButton.setBackgroundColor(dColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);
		
		// 按钮S2LA
		stationButton = new StationButton("S2LA", "L");
		stationButton.setSize(bLen, bLen);
		stationButton.setLocation(new Point(X - len + 4 + bLen, Y + len + dis));
		stationButton.setBackgroundColor(bColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);
		
		// 按钮S3DA
		stationButton = new StationButton("S3DA", "D");
		stationButton.setSize(bLen, bLen);
		stationButton.setLocation(new Point(X - len + 2, Y - len + dis));
		stationButton.setBackgroundColor(dColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);
		
		// 按钮S3LA
		stationButton = new StationButton("S3LA", "L");
		stationButton.setSize(bLen, bLen);
		stationButton.setLocation(new Point(X - len + 4 + bLen, Y - len + dis));
		stationButton.setBackgroundColor(bColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);
		
		// 按钮S4DA
		stationButton = new StationButton("S4DA", "D");
		stationButton.setSize(bLen, bLen);
		stationButton.setLocation(new Point(X + 2, Y + 2*len + dis));
		stationButton.setBackgroundColor(dColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);
		
		// 按钮S4LA
		stationButton = new StationButton("S4LA", "L");
		stationButton.setSize(bLen, bLen);
		stationButton.setLocation(new Point(X + 4 + bLen, Y + 2*len + dis));
		stationButton.setBackgroundColor(bColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);
		
		// 按钮S6DA
		stationButton = new StationButton("S6DA", "D");
		stationButton.setSize(bLen, bLen);
		stationButton.setLocation(new Point(X + 2, Y + 3*len + dis));
		stationButton.setBackgroundColor(dColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);
		
		// 按钮S6LA
		stationButton = new StationButton("S6LA", "L");
		stationButton.setSize(bLen, bLen);
		stationButton.setLocation(new Point(X + 4 + bLen, Y + 3*len + dis));
		stationButton.setBackgroundColor(bColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);
		
		
		// 按钮X1DA
		stationButton = new StationButton("X1DA", "D");
		stationButton.setSize(bLen, bLen);
		stationButton.setLocation(new Point(X + len - bLen - 2, Y - bLen - dis));
		stationButton.setBackgroundColor(dColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);
		
		// 按钮X1LA
		stationButton = new StationButton("X1LA", "L");
		stationButton.setSize(bLen, bLen);
		stationButton.setLocation(new Point(X + len - 2*bLen - 3, Y - bLen - dis));
		stationButton.setBackgroundColor(bColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);
		
		// 按钮X2DA
		stationButton = new StationButton("X2DA", "D");
		stationButton.setSize(bLen, bLen);
		stationButton.setLocation(new Point(X + len - bLen - 2, Y - bLen - dis + len));
		stationButton.setBackgroundColor(dColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);
		
		// 按钮X2LA
		stationButton = new StationButton("X2LA", "L");
		stationButton.setSize(bLen, bLen);
		stationButton.setLocation(new Point(X + len - 2*bLen - 3, Y - bLen - dis + len));
		stationButton.setBackgroundColor(bColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);
		
		// 按钮X3DA
		stationButton = new StationButton("X3DA", "D");
		stationButton.setSize(bLen, bLen);
		stationButton.setLocation(new Point(X + len - bLen - 2, Y - bLen - dis - len));
		stationButton.setBackgroundColor(dColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);
		
		// 按钮X3LA
		stationButton = new StationButton("X3LA", "L");
		stationButton.setSize(bLen, bLen);
		stationButton.setLocation(new Point(X + len - 2*bLen - 3, Y - bLen - dis - len));
		stationButton.setBackgroundColor(bColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);
		
		// 按钮X4DA
		stationButton = new StationButton("X4DA", "D");
		stationButton.setSize(bLen, bLen);
		stationButton.setLocation(new Point(X + 2*len - bLen - 2, Y - bLen - dis + 2*len));
		stationButton.setBackgroundColor(dColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);
		
		// 按钮X4LA
		stationButton = new StationButton("X4LA", "L");
		stationButton.setSize(bLen, bLen);
		stationButton.setLocation(new Point(X + 2*len - 2*bLen - 3, Y - bLen - dis + 2*len));
		stationButton.setBackgroundColor(bColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);
		
		// 按钮X6DA
		stationButton = new StationButton("X6DA", "D");
		stationButton.setSize(bLen, bLen);
		stationButton.setLocation(new Point(X + 2*len - bLen - 2, Y - bLen - dis + 3*len));
		stationButton.setBackgroundColor(dColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);
		
		// 按钮X6LA
		stationButton = new StationButton("X6LA", "L");
		stationButton.setSize(bLen, bLen);
		stationButton.setLocation(new Point(X + 2*len - 2*bLen - 3, Y - bLen - dis + 3*len));
		stationButton.setBackgroundColor(bColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);

		
		
		
		//---绘制调车按钮----
		// 按钮D11A
		stationButton = new StationButton("D11A", "D");
		stationButton.setSize(bLen, bLen);
		stationButton.setLocation(new Point(X - 5*len - bLen - 2, Y - bLen - dis ));
		stationButton.setBackgroundColor(dColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);
		
		// 按钮D12A
		stationButton = new StationButton("D12A", "D");
		stationButton.setSize(bLen, bLen);
		stationButton.setLocation(new Point(X + 5*len - bLen - 2, Y - bLen - dis ));
		stationButton.setBackgroundColor(dColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);
				
		// 按钮D13A
		stationButton = new StationButton("D13A", "D");
		stationButton.setSize(bLen, bLen);
		stationButton.setLocation(new Point(X + 2 + 4*len, Y + dis));
		stationButton.setBackgroundColor(dColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);
		
		// 按钮D14A
		stationButton = new StationButton("D14A", "D");
		stationButton.setSize(bLen, bLen);
		stationButton.setLocation(new Point(X + 2 + 7*len, Y + dis));
		stationButton.setBackgroundColor(dColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);
		
		// 按钮D21A
		stationButton = new StationButton("D21A", "D");
		stationButton.setSize(bLen, bLen);
		stationButton.setLocation(new Point(X - 5*len - bLen - 2, Y - bLen - dis + len));
		stationButton.setBackgroundColor(dColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);
		
		// 按钮D22A
		stationButton = new StationButton("D22A", "D");
		stationButton.setSize(bLen, bLen);
		stationButton.setLocation(new Point(X + 2 + 7*len, Y + dis + len));
		stationButton.setBackgroundColor(dColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);
		
		// 按钮D31A
		stationButton = new StationButton("D31A", "D");
		stationButton.setSize(bLen, bLen);
		stationButton.setLocation(new Point(X + 2 + 3*len, Y - len + dis));
		stationButton.setBackgroundColor(dColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);
				
		// 按钮D41A
		stationButton = new StationButton("D41A", "D");
		stationButton.setSize(bLen, bLen);
		stationButton.setLocation(new Point(X + 2 + 6*len, Y + dis + 2*len));
		stationButton.setBackgroundColor(dColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);
		
		// 按钮D42A
		stationButton = new StationButton("D42A", "D");
		stationButton.setSize(bLen, bLen);
		stationButton.setLocation(new Point(X + 2 + 7*len, Y + dis + 2*len));
		stationButton.setBackgroundColor(dColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);
		
		// 按钮D43A
		stationButton = new StationButton("D43A", "D");
		stationButton.setSize(bLen, bLen);
		stationButton.setLocation(new Point(X + 2 + 8*len, Y + dis + 2*len));
		stationButton.setBackgroundColor(dColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);
		
		// 按钮D44A
		stationButton = new StationButton("D44A", "D");
		stationButton.setSize(bLen, bLen);
		stationButton.setLocation(new Point(X + 7*len - bLen - 2, Y - bLen - dis + 2*len));
		stationButton.setBackgroundColor(dColor);
		panel.add(stationButton);
		new MouseAction(stationButton);
		stationButton.addActionListener(new ButtonListener());
		allButtonFigureList.add(stationButton);
		
		// 按钮D51A
		stationButton = new StationButton("D51A", "D");
		stationButton.setSize(bLen, bLen);
		stationButton.setLocation(new Point(X + len - bLen - 2, Y - bLen - dis - 2*len));
		stationButton.setBackgroundColor(dColor);
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
		//taskTable.setTableContents();

	}

	/**
	 * 绘制图形的网格
	 */
	public void drawGrid() {

		PolylineConnection line;
		line = new PolylineConnection();
		line.setStart(new Point(X, Y - 200));
		line.setEnd(new Point(X, Y + 200));
		line.setLineWidth(1);
		line.setForegroundColor(ColorConstants.red);
		baseData.getPanel().add(line);

		line = new PolylineConnection();
		line.setStart(new Point(X - len, Y - 200));
		line.setEnd(new Point(X - len, Y + 200));
		line.setLineWidth(1);
		line.setForegroundColor(ColorConstants.blue);
		baseData.getPanel().add(line);

		line = new PolylineConnection();
		line.setStart(new Point(X - 2 * len, Y - 200));
		line.setEnd(new Point(X - 2 * len, Y + 200));
		line.setLineWidth(1);
		line.setForegroundColor(ColorConstants.blue);
		baseData.getPanel().add(line);

		line = new PolylineConnection();
		line.setStart(new Point(X - 3 * len, Y - 200));
		line.setEnd(new Point(X - 3 * len, Y + 200));
		line.setLineWidth(1);
		line.setForegroundColor(ColorConstants.blue);
		baseData.getPanel().add(line);

		line = new PolylineConnection();
		line.setStart(new Point(X - 4 * len, Y - 200));
		line.setEnd(new Point(X - 4 * len, Y + 200));
		line.setLineWidth(1);
		line.setForegroundColor(ColorConstants.blue);
		baseData.getPanel().add(line);

		line = new PolylineConnection();
		line.setStart(new Point(X - 5 * len, Y - 200));
		line.setEnd(new Point(X - 5 * len, Y + 200));
		line.setLineWidth(1);
		line.setForegroundColor(ColorConstants.blue);
		baseData.getPanel().add(line);

		line = new PolylineConnection();
		line.setStart(new Point(X - 6 * len, Y - 200));
		line.setEnd(new Point(X - 6 * len, Y + 200));
		line.setLineWidth(1);
		line.setForegroundColor(ColorConstants.blue);
		baseData.getPanel().add(line);

		line = new PolylineConnection();
		line.setStart(new Point(X - 7 * len, Y - 200));
		line.setEnd(new Point(X - 7 * len, Y + 200));
		line.setLineWidth(1);
		line.setForegroundColor(ColorConstants.blue);
		baseData.getPanel().add(line);

		line = new PolylineConnection();
		line.setStart(new Point(X - 8 * len, Y - 200));
		line.setEnd(new Point(X - 8 * len, Y + 200));
		line.setLineWidth(1);
		line.setForegroundColor(ColorConstants.blue);
		baseData.getPanel().add(line);

		line = new PolylineConnection();
		line.setStart(new Point(X - 9 * len, Y - 200));
		line.setEnd(new Point(X - 9 * len, Y + 200));
		line.setLineWidth(1);
		line.setForegroundColor(ColorConstants.blue);
		baseData.getPanel().add(line);

		// --------------
		line = new PolylineConnection();
		line.setStart(new Point(X + len, Y - 200));
		line.setEnd(new Point(X + len, Y + 200));
		line.setLineWidth(1);
		line.setForegroundColor(ColorConstants.blue);
		baseData.getPanel().add(line);

		line = new PolylineConnection();
		line.setStart(new Point(X + 2 * len, Y - 200));
		line.setEnd(new Point(X + 2 * len, Y + 200));
		line.setLineWidth(1);
		line.setForegroundColor(ColorConstants.blue);
		baseData.getPanel().add(line);

		line = new PolylineConnection();
		line.setStart(new Point(X + 3 * len, Y - 200));
		line.setEnd(new Point(X + 3 * len, Y + 200));
		line.setLineWidth(1);
		line.setForegroundColor(ColorConstants.blue);
		baseData.getPanel().add(line);

		line = new PolylineConnection();
		line.setStart(new Point(X + 4 * len, Y - 200));
		line.setEnd(new Point(X + 4 * len, Y + 200));
		line.setLineWidth(1);
		line.setForegroundColor(ColorConstants.blue);
		baseData.getPanel().add(line);

		line = new PolylineConnection();
		line.setStart(new Point(X + 5 * len, Y - 200));
		line.setEnd(new Point(X + 5 * len, Y + 200));
		line.setLineWidth(1);
		line.setForegroundColor(ColorConstants.blue);
		baseData.getPanel().add(line);

		line = new PolylineConnection();
		line.setStart(new Point(X + 6 * len, Y - 200));
		line.setEnd(new Point(X + 6 * len, Y + 200));
		line.setLineWidth(1);
		line.setForegroundColor(ColorConstants.blue);
		baseData.getPanel().add(line);

		line = new PolylineConnection();
		line.setStart(new Point(X + 7 * len, Y - 200));
		line.setEnd(new Point(X + 7 * len, Y + 200));
		line.setLineWidth(1);
		line.setForegroundColor(ColorConstants.blue);
		baseData.getPanel().add(line);

		line = new PolylineConnection();
		line.setStart(new Point(X + 8 * len, Y - 200));
		line.setEnd(new Point(X + 8 * len, Y + 200));
		line.setLineWidth(1);
		line.setForegroundColor(ColorConstants.blue);
		baseData.getPanel().add(line);

		line = new PolylineConnection();
		line.setStart(new Point(X + 9 * len, Y - 200));
		line.setEnd(new Point(X + 9 * len, Y + 200));
		line.setLineWidth(1);
		line.setForegroundColor(ColorConstants.blue);
		baseData.getPanel().add(line);

	}

}
