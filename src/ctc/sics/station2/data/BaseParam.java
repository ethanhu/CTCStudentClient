package ctc.sics.station2.data;

import java.util.ArrayList;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import ctc.sics.station2.elements.common.*;

public class BaseParam {

	private static BaseParam baseData = null;

	public static BaseParam getInstance() {
		if (baseData == null) {
			baseData = new BaseParam();
		}
		return baseData;
	}

	// ----------站型图基本数据------------------//
	private static int centerX; // 中心X
	private static int centerY; // 中心Y
	private static int rowSpacing = 56; // 股道间距
	private static int turnoutLength = 10; // 道岔拐角处的伸出长度
	private static int lineWidth = 3; // 股道线宽度
	private static int trackLength = rowSpacing; // 股道单位长度
	private static int semaphoreLightDiameter = 14; // 信号机灯的直径
	private static int buttonLength = 13; // 按钮的边长
	private static int distanceLen = 3; // label与物体的距离
	private static int labelWidth = 30; // label的宽度
	private static int labelHeight = 10; // label的高度
	private static int slineLength = 8; // 绝缘线长度

	private static Color trackInitColor = ColorConstants.blue; // 股道线初始颜色
	private static Color backgroundColor = ColorConstants.black; // 股道线初始颜色

	private static Color buttonInitColor = ColorConstants.blue; // 接发车按钮初始颜色
	private static Color buttonClickColor = ColorConstants.green; // 接发车按钮初始颜色
	private static Color d_buttonInitColor = ColorConstants.gray; // 调车按钮初始颜色
	private static Color d_buttonClickColor = ColorConstants.green; // 调车按钮初始颜色
	private static Color y_buttonInitColor = ColorConstants.green; // 引导进路按钮初始颜色
	private static Color y_buttonClickColor = ColorConstants.red; // 引导进路按钮初始颜色

	// ------主窗体字体----------//
	private static Font font1 = new Font(Display.getDefault(), "Times New Roman", 7, SWT.NORMAL);
	private static Font font2 = new Font(Display.getDefault(), "Times New Roman", 15, SWT.NORMAL);
	private static Font font3 = new Font(Display.getDefault(), "Arabic Transparent", 10, SWT.NORMAL);
	private static Font font4 = new Font(Display.getDefault(), "Times New Roman", 8, SWT.NORMAL);

	private static TextStyle style1;
	private static TextStyle style2 = new TextStyle(font2, ColorConstants.green, null);
	private static TextStyle style3;

	public static ArrayList<RoadList> allRoadList = new ArrayList<RoadList>(); // 所有道路的list
	public static ArrayList<Figure> allRoadFigureList = new ArrayList<Figure>(); // 包括股道和道岔
	public static ArrayList<Figure> allTrackLineFigureList = new ArrayList<Figure>(); // 所有股道
	public static ArrayList<Figure> allTurnoutFigureList = new ArrayList<Figure>(); // 所有道岔
	public static ArrayList<Figure> allButtonFigureList = new ArrayList<Figure>(); // 所有按钮
	public static ArrayList<Figure> allTurnoutButtonFigureList = new ArrayList<Figure>(); // 所有按钮

	public static ArrayList<Label> turnoutNameList = new ArrayList<Label>(); // 所有股道显示list
	public static ArrayList<Label> tracklineNameList = new ArrayList<Label>(); // 所有道岔显示list
	public static ArrayList<Label> buttonNameList = new ArrayList<Label>(); // 所有按钮显示list
	public static ArrayList<Label> semaphoreNameList = new ArrayList<Label>(); // 所有信号机显示list

	public static TrackLine trackline_1; // 车道1
	public static TrackLine trackline_2; // 车道2
	public static TrackLine trackline_3; // 车道3
	public static TrackLine trackline_4; // 车道4
	public static TrackLine trackline_5; // 车道5
	public static TrackLine trackline_6; // 车道6
	public static TrackLine trackline_1_z_2;
	public static TrackLine trackline_1_y_2;
	public static TrackLine trackline_1_y_4;
	public static TrackLine trackline_2_z_2;
	public static TrackLine trackline_2_y_4;
	public static TrackLine trackline_3_y_2;
	public static TrackLine trackline_4_y_3;
	public static TrackLine trackline_4_y_4;
	public static TrackLine trackline_4_y_5;
	public static TrackLine trackline_xxjz; // 下行进站
	public static TrackLine trackline_sxjz; // 上行进站
	
	public static ArrayList<TrainStopRoad> allStopRoadList = new ArrayList<TrainStopRoad>(); // 存放可以停车的list

	// -----------------------基本变量参数--------------------------//
	// ------主窗体容器----------//
	private static Shell mainShell; // 程序主shell
	private static Composite stationShell; // 车站shell
	private static Composite shell; // 绘制站型图shell
	private static Composite textShell; // 绘制站型图shell

	
	
	// -----------Figure数据-----------//
	private static Figure panel = new Figure();; // 绘图容器
	private static Figure msgTextPanel = new Figure(); // 消息容器
	private static Text msgLabel; // 操作记录Label
	private static org.eclipse.swt.widgets.Label timeLabel; // 时间label

	private static int shellHeight; // shell的高度
	private static int shellWidth; // shell的宽度

	public static TrackLine x1jj; // X第1接近
	public static TrackLine x2jj; // X第2接近
	public static TrackLine x3jj; // X第3接近
	public static TrackLine x4jj; // X第4接近
	
	public static TrackLine x1lq; // X第1离去
	public static TrackLine x2lq; // X第2离去
	public static TrackLine x3lq; // X第3离去
	public static TrackLine x4lq; // X第4离去
	
	public static TrackLine s1jj; // S第1接近
	public static TrackLine s2jj; // S第2接近
	public static TrackLine s3jj; // S第3接近
	public static TrackLine s4jj; // S第4接近
	
	public static TrackLine s1lq; // S第1离去
	public static TrackLine s2lq; // S第2离去
	public static TrackLine s3lq; // S第3离去
	public static TrackLine s4lq; // S第4离去

	
	
	// ----------业务按钮-----------//
	public static int b_sqzk = 0; // 站控
	public static int b_fczk = 0;// 非常站控

	public static int b_sdczdw = 0;// 上行道岔总定位
	public static int b_sdczfw = 0;// 上行道岔总反位
	public static int b_xdczdw = 0;// 下行道岔总定位
	public static int b_xdczfw = 0;// 下行行道岔总反位
	public static int b_syzs = 0;// 上行排列进路
	public static int b_xyzs = 0; // 下行排列进路
	public static int b_qgj = 0; // 区故解

	public static int b_sdc = 0;// S单操按钮
	public static int b_szqx = 0;// S总取消
	public static int b_szrgjs = 0;// S总人工解锁
	public static int b_sydzjs = 0;// S引导总锁闭
	public static int b_xdc = 0;// X单操按钮
	public static int b_xzqx = 0;// X总取消
	public static int b_xzrgjs = 0;// X总人工解锁
	public static int b_xydzjs = 0;// X引导总锁闭

	public static ArrayList<Label> getButtonNameList() {
		return buttonNameList;
	}

	public static void setButtonNameList(ArrayList<Label> buttonNameList) {
		BaseParam.buttonNameList = buttonNameList;
	}

	public static ArrayList<Label> getSemaphoreNameList() {
		return semaphoreNameList;
	}

	public static void setSemaphoreNameList(ArrayList<Label> semaphoreNameList) {
		BaseParam.semaphoreNameList = semaphoreNameList;
	}

	public static Color getY_buttonInitColor() {
		return y_buttonInitColor;
	}

	public static void setY_buttonInitColor(Color initColor) {
		y_buttonInitColor = initColor;
	}

	public static Color getY_buttonClickColor() {
		return y_buttonClickColor;
	}

	public static void setY_buttonClickColor(Color clickColor) {
		y_buttonClickColor = clickColor;
	}

	public static int getSlineLength() {
		return slineLength;
	}

	public static void setSlineLength(int slineLength) {
		BaseParam.slineLength = slineLength;
	}

	public static Font getFont4() {
		return font4;
	}

	public static void setFont4(Font font4) {
		BaseParam.font4 = font4;
	}

	public static Color getBackgroundColor() {
		return backgroundColor;
	}

	public static void setBackgroundColor(Color backgroundColor) {
		BaseParam.backgroundColor = backgroundColor;
	}

	public static int getDistanceLen() {
		return distanceLen;
	}

	public static void setDistanceLen(int distanceLen) {
		BaseParam.distanceLen = distanceLen;
	}

	public static int getCenterX() {
		return centerX;
	}

	public static void setCenterX(int centerX) {
		BaseParam.centerX = centerX;
	}

	public static int getCenterY() {
		return centerY;
	}

	public static void setCenterY(int centerY) {
		BaseParam.centerY = centerY;
	}

	public static int getB_sqzk() {
		return b_sqzk;
	}

	public static void setB_sqzk(int b_sqzk) {
		BaseParam.b_sqzk = b_sqzk;
	}

	public static int getB_fczk() {
		return b_fczk;
	}

	public static void setB_fczk(int b_fczk) {
		BaseParam.b_fczk = b_fczk;
	}

	public static int getB_sdczdw() {
		return b_sdczdw;
	}

	public static void setB_sdczdw(int b_sdczdw) {
		BaseParam.b_sdczdw = b_sdczdw;
	}

	public static int getB_sdczfw() {
		return b_sdczfw;
	}

	public static void setB_sdczfw(int b_sdczfw) {
		BaseParam.b_sdczfw = b_sdczfw;
	}

	public static int getB_xdczdw() {
		return b_xdczdw;
	}

	public static void setB_xdczdw(int b_xdczdw) {
		BaseParam.b_xdczdw = b_xdczdw;
	}

	public static int getB_xdczfw() {
		return b_xdczfw;
	}

	public static void setB_xdczfw(int b_xdczfw) {
		BaseParam.b_xdczfw = b_xdczfw;
	}

	public static int getB_syzs() {
		return b_syzs;
	}

	public static void setB_syzs(int b_syzs) {
		BaseParam.b_syzs = b_syzs;
	}

	public static int getB_xyzs() {
		return b_xyzs;
	}

	public static void setB_xyzs(int b_xyzs) {
		BaseParam.b_xyzs = b_xyzs;
	}

	public static int getB_qgj() {
		return b_qgj;
	}

	public static void setB_qgj(int b_qgj) {
		BaseParam.b_qgj = b_qgj;
	}

	public static int getB_sdc() {
		return b_sdc;
	}

	public static void setB_sdc(int b_sdc) {
		BaseParam.b_sdc = b_sdc;
	}

	public static int getB_szqx() {
		return b_szqx;
	}

	public static void setB_szqx(int b_szqx) {
		BaseParam.b_szqx = b_szqx;
	}

	public static int getB_szrgjs() {
		return b_szrgjs;
	}

	public static void setB_szrgjs(int b_szrgjs) {
		BaseParam.b_szrgjs = b_szrgjs;
	}

	public static int getB_sydzjs() {
		return b_sydzjs;
	}

	public static void setB_sydzjs(int b_sydzjs) {
		BaseParam.b_sydzjs = b_sydzjs;
	}

	public static int getB_xdc() {
		return b_xdc;
	}

	public static void setB_xdc(int b_xdc) {
		BaseParam.b_xdc = b_xdc;
	}

	public static int getB_xzqx() {
		return b_xzqx;
	}

	public static void setB_xzqx(int b_xzqx) {
		BaseParam.b_xzqx = b_xzqx;
	}

	public static int getB_xzrgjs() {
		return b_xzrgjs;
	}

	public static void setB_xzrgjs(int b_xzrgjs) {
		BaseParam.b_xzrgjs = b_xzrgjs;
	}

	public static int getB_xydzjs() {
		return b_xydzjs;
	}

	public static void setB_xydzjs(int b_xydzjs) {
		BaseParam.b_xydzjs = b_xydzjs;
	}

	public static ArrayList<TrainStopRoad> getAllStopRoadList() {
		return allStopRoadList;
	}

	public static void setAllStopRoadList(ArrayList<TrainStopRoad> allStopRoadList) {
		BaseParam.allStopRoadList = allStopRoadList;
	}

	public static TrackLine getTrackline_1() {
		return trackline_1;
	}

	public static void setTrackline_1(TrackLine trackline_1) {
		BaseParam.trackline_1 = trackline_1;
	}

	public static TrackLine getTrackline_2() {
		return trackline_2;
	}

	public static void setTrackline_2(TrackLine trackline_2) {
		BaseParam.trackline_2 = trackline_2;
	}

	public static TrackLine getTrackline_3() {
		return trackline_3;
	}

	public static void setTrackline_3(TrackLine trackline_3) {
		BaseParam.trackline_3 = trackline_3;
	}

	public static TrackLine getTrackline_4() {
		return trackline_4;
	}

	public static void setTrackline_4(TrackLine trackline_4) {
		BaseParam.trackline_4 = trackline_4;
	}

	public static TrackLine getTrackline_5() {
		return trackline_5;
	}

	public static void setTrackline_5(TrackLine trackline_5) {
		BaseParam.trackline_5 = trackline_5;
	}

	public static TrackLine getTrackline_6() {
		return trackline_6;
	}

	public static void setTrackline_6(TrackLine trackline_6) {
		BaseParam.trackline_6 = trackline_6;
	}
	
	public static TrackLine getTrackline_1_z_2() {
		return trackline_1_z_2;
	}

	public static void setTrackline_1_z_2(TrackLine trackline_1_z_2) {
		BaseParam.trackline_1_z_2 = trackline_1_z_2;
	}

	public static TrackLine getTrackline_1_y_2() {
		return trackline_1_y_2;
	}

	public static void setTrackline_1_y_2(TrackLine trackline_1_y_2) {
		BaseParam.trackline_1_y_2 = trackline_1_y_2;
	}

	public static TrackLine getTrackline_1_y_4() {
		return trackline_1_y_4;
	}

	public static void setTrackline_1_y_4(TrackLine trackline_1_y_4) {
		BaseParam.trackline_1_y_4 = trackline_1_y_4;
	}

	public static TrackLine getTrackline_2_z_2() {
		return trackline_2_z_2;
	}

	public static void setTrackline_2_z_2(TrackLine trackline_2_z_2) {
		BaseParam.trackline_2_z_2 = trackline_2_z_2;
	}

	public static TrackLine getTrackline_2_y_4() {
		return trackline_2_y_4;
	}

	public static void setTrackline_2_y_4(TrackLine trackline_2_y_4) {
		BaseParam.trackline_2_y_4 = trackline_2_y_4;
	}

	public static TrackLine getTrackline_3_y_2() {
		return trackline_3_y_2;
	}

	public static void setTrackline_3_y_2(TrackLine trackline_3_y_2) {
		BaseParam.trackline_3_y_2 = trackline_3_y_2;
	}

	public static TrackLine getTrackline_4_y_3() {
		return trackline_4_y_3;
	}

	public static void setTrackline_4_y_3(TrackLine trackline_4_y_3) {
		BaseParam.trackline_4_y_3 = trackline_4_y_3;
	}

	public static TrackLine getTrackline_4_y_4() {
		return trackline_4_y_4;
	}

	public static void setTrackline_4_y_4(TrackLine trackline_4_y_4) {
		BaseParam.trackline_4_y_4 = trackline_4_y_4;
	}

	public static TrackLine getTrackline_4_y_5() {
		return trackline_4_y_5;
	}

	public static void setTrackline_4_y_5(TrackLine trackline_4_y_5) {
		BaseParam.trackline_4_y_5 = trackline_4_y_5;
	}

	public static TrackLine getTrackline_xxjz() {
		return trackline_xxjz;
	}

	public static void setTrackline_xxjz(TrackLine trackline_xxjz) {
		BaseParam.trackline_xxjz = trackline_xxjz;
	}

	public static TrackLine getTrackline_sxjz() {
		return trackline_sxjz;
	}

	public static void setTrackline_sxjz(TrackLine trackline_sxjz) {
		BaseParam.trackline_sxjz = trackline_sxjz;
	}

	public static ArrayList<Figure> getAllTurnoutButtonFigureList() {
		return allTurnoutButtonFigureList;
	}

	public static void setAllTurnoutButtonFigureList(ArrayList<Figure> allTurnoutButtonFigureList) {
		BaseParam.allTurnoutButtonFigureList = allTurnoutButtonFigureList;
	}

	public static ArrayList<RoadList> getAllRoadList() {
		return allRoadList;
	}

	public static void setAllRoadList(ArrayList<RoadList> allRoadList) {
		BaseParam.allRoadList = allRoadList;
	}

	public static ArrayList<Label> getTurnoutNameList() {
		return turnoutNameList;
	}

	public static void setTurnoutNameList(ArrayList<Label> turnoutNameList) {
		BaseParam.turnoutNameList = turnoutNameList;
	}

	public static ArrayList<Label> getTracklineNameList() {
		return tracklineNameList;
	}

	public static void setTracklineNameList(ArrayList<Label> tracklineNameList) {
		BaseParam.tracklineNameList = tracklineNameList;
	}

	

	public static TrackLine getX1jj() {
		return x1jj;
	}

	public static void setX1jj(TrackLine x1jj) {
		BaseParam.x1jj = x1jj;
	}

	public static TrackLine getX2jj() {
		return x2jj;
	}

	public static void setX2jj(TrackLine x2jj) {
		BaseParam.x2jj = x2jj;
	}

	public static TrackLine getX3jj() {
		return x3jj;
	}

	public static void setX3jj(TrackLine x3jj) {
		BaseParam.x3jj = x3jj;
	}

	public static TrackLine getX4jj() {
		return x4jj;
	}

	public static void setX4jj(TrackLine x4jj) {
		BaseParam.x4jj = x4jj;
	}

	public static TrackLine getX1lq() {
		return x1lq;
	}

	public static void setX1lq(TrackLine x1lq) {
		BaseParam.x1lq = x1lq;
	}

	public static TrackLine getX2lq() {
		return x2lq;
	}

	public static void setX2lq(TrackLine x2lq) {
		BaseParam.x2lq = x2lq;
	}

	public static TrackLine getX3lq() {
		return x3lq;
	}

	public static void setX3lq(TrackLine x3lq) {
		BaseParam.x3lq = x3lq;
	}

	public static TrackLine getX4lq() {
		return x4lq;
	}

	public static void setX4lq(TrackLine x4lq) {
		BaseParam.x4lq = x4lq;
	}

	public static TrackLine getS1jj() {
		return s1jj;
	}

	public static void setS1jj(TrackLine s1jj) {
		BaseParam.s1jj = s1jj;
	}

	public static TrackLine getS2jj() {
		return s2jj;
	}

	public static void setS2jj(TrackLine s2jj) {
		BaseParam.s2jj = s2jj;
	}

	public static TrackLine getS3jj() {
		return s3jj;
	}

	public static void setS3jj(TrackLine s3jj) {
		BaseParam.s3jj = s3jj;
	}

	public static TrackLine getS4jj() {
		return s4jj;
	}

	public static void setS4jj(TrackLine s4jj) {
		BaseParam.s4jj = s4jj;
	}

	public static TrackLine getS1lq() {
		return s1lq;
	}

	public static void setS1lq(TrackLine s1lq) {
		BaseParam.s1lq = s1lq;
	}

	public static TrackLine getS2lq() {
		return s2lq;
	}

	public static void setS2lq(TrackLine s2lq) {
		BaseParam.s2lq = s2lq;
	}

	public static TrackLine getS3lq() {
		return s3lq;
	}

	public static void setS3lq(TrackLine s3lq) {
		BaseParam.s3lq = s3lq;
	}

	public static TrackLine getS4lq() {
		return s4lq;
	}

	public static void setS4lq(TrackLine s4lq) {
		BaseParam.s4lq = s4lq;
	}

	public static Shell getMainShell() {
		return mainShell;
	}

	public static void setMainShell(Shell mainShell) {
		BaseParam.mainShell = mainShell;
	}

	public static Composite getStationShell() {
		return stationShell;
	}

	public static void setStationShell(Composite stationShell) {
		BaseParam.stationShell = stationShell;
	}

	public static Composite getShell() {
		return shell;
	}

	public static void setShell(Composite shell) {
		BaseParam.shell = shell;
	}

	public static Composite getTextShell() {
		return textShell;
	}

	public static void setTextShell(Composite textShell) {
		BaseParam.textShell = textShell;
	}

	public static TextStyle getStyle1() {
		return style1;
	}

	public void setStyle1(TextStyle style1) {
		BaseParam.style1 = style1;
	}

	public static TextStyle getStyle2() {
		return style2;
	}

	public static void setStyle2(TextStyle style2) {
		BaseParam.style2 = style2;
	}

	public static TextStyle getStyle3() {
		return style3;
	}

	public static void setStyle3(TextStyle style3) {
		BaseParam.style3 = style3;
	}

	public static Color getD_buttonInitColor() {
		return d_buttonInitColor;
	}

	public static void setD_buttonInitColor(Color initColor) {
		d_buttonInitColor = initColor;
	}

	public static ArrayList<Figure> getAllRoadFigureList() {
		return allRoadFigureList;
	}

	public static void setAllRoadFigureList(ArrayList<Figure> allRoadFigureList) {
		BaseParam.allRoadFigureList = allRoadFigureList;
	}

	public static ArrayList<Figure> getAllTrackLineFigureList() {
		return allTrackLineFigureList;
	}

	public static void setAllTrackLineFigureList(ArrayList<Figure> allTrackLineFigureList) {
		BaseParam.allTrackLineFigureList = allTrackLineFigureList;
	}

	public static ArrayList<Figure> getAllTurnoutFigureList() {
		return allTurnoutFigureList;
	}

	public static void setAllTurnoutFigureList(ArrayList<Figure> allTurnoutFigureList) {
		BaseParam.allTurnoutFigureList = allTurnoutFigureList;
	}

	public static ArrayList<Figure> getAllButtonFigureList() {
		return allButtonFigureList;
	}

	public static void setAllButtonFigureList(ArrayList<Figure> allButtonFigureList) {
		BaseParam.allButtonFigureList = allButtonFigureList;
	}

	public static Figure getPanel() {
		return panel;
	}

	public static void setPanel(Figure panel) {
		BaseParam.panel = panel;
	}

	public static int getShellHeight() {
		return shellHeight;
	}

	public static void setShellHeight(int shellHeight) {
		BaseParam.shellHeight = shellHeight;
	}

	public static int getShellWidth() {
		return shellWidth;
	}

	public static void setShellWidth(int shellWidth) {
		BaseParam.shellWidth = shellWidth;
	}

	public static Color getD_buttonClickColor() {
		return d_buttonClickColor;
	}

	public static void setD_buttonClickColor(Color clickColor) {
		d_buttonClickColor = clickColor;
	}

	public static int getRowSpacing() {
		return rowSpacing;
	}

	public static void setRowSpacing(int rowSpacing) {
		BaseParam.rowSpacing = rowSpacing;
	}

	public static int getTurnoutLength() {
		return turnoutLength;
	}

	public static void setTurnoutLength(int turnoutLength) {
		BaseParam.turnoutLength = turnoutLength;
	}

	public static int getLineWidth() {
		return lineWidth;
	}

	public static void setLineWidth(int lineWidth) {
		BaseParam.lineWidth = lineWidth;
	}

	public static int getTrackLength() {
		return trackLength;
	}

	public static void settrackLength(int trackLength) {
		BaseParam.trackLength = trackLength;
	}

	public static int getSemaphoreLightDiameter() {
		return semaphoreLightDiameter;
	}

	public static void setSemaphoreLightDiameter(int semaphoreLightDiameter) {
		BaseParam.semaphoreLightDiameter = semaphoreLightDiameter;
	}

	public static int getButtonLength() {
		return buttonLength;
	}

	public static void setButtonLength(int buttonLength) {
		BaseParam.buttonLength = buttonLength;
	}

	public static int getLabelWidth() {
		return labelWidth;
	}

	public static void setLabelWidth(int labelWidth) {
		BaseParam.labelWidth = labelWidth;
	}

	public static int getLabelHeight() {
		return labelHeight;
	}

	public static void setLabelHeight(int labelHeight) {
		BaseParam.labelHeight = labelHeight;
	}

	public static Color getTrackInitColor() {
		return trackInitColor;
	}

	public static void setTrackInitColor(Color trackInitColor) {
		BaseParam.trackInitColor = trackInitColor;
	}

	public static Color getButtonInitColor() {
		return buttonInitColor;
	}

	public static void setButtonInitColor(Color buttonInitColor) {
		BaseParam.buttonInitColor = buttonInitColor;
	}

	public static Color getButtonClickColor() {
		return buttonClickColor;
	}

	public static void setButtonClickColor(Color buttonClickColor) {
		BaseParam.buttonClickColor = buttonClickColor;
	}

	public static Font getFont1() {
		return font1;
	}

	public static void setFont1(Font font1) {
		BaseParam.font1 = font1;
	}

	public static Font getFont2() {
		return font2;
	}

	public static void setFont2(Font font2) {
		BaseParam.font2 = font2;
	}

	public static Font getFont3() {
		return font3;
	}

	public static void setFont3(Font font3) {
		BaseParam.font3 = font3;
	}

	public static BaseParam getBaseData() {
		return baseData;
	}

	public static void setBaseData(BaseParam baseData) {
		BaseParam.baseData = baseData;
	}

	public static void setTrackLength(int trackLength) {
		BaseParam.trackLength = trackLength;
	}

	public static Text getMsgLabel() {
		return msgLabel;
	}

	public static void setMsgLabel(Text msgLabel) {
		BaseParam.msgLabel = msgLabel;
	}

	public static org.eclipse.swt.widgets.Label getTimeLabel() {
		return timeLabel;
	}

	public static void setTimeLabel(org.eclipse.swt.widgets.Label timeLabel) {
		BaseParam.timeLabel = timeLabel;
	}

	public static Figure getMsgTextPanel() {
		return msgTextPanel;
	}

	public static void setMsgTextPanel(Figure msgTextPanel) {
		BaseParam.msgTextPanel = msgTextPanel;
	}

}
