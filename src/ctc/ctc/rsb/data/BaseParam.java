package ctc.ctc.rsb.data;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import ctc.pojobean.TaskPlan;
import ctc.sics.station.elements.common.TrackLine;
import ctc.sics.station.elements.semaphore.*;

public class BaseParam {

	private static BaseParam baseData = null;
	public static BaseParam getInstance() {
		if (baseData == null) {
			baseData = new BaseParam();
		}
		return baseData;
	}

	// ------主窗体容器----------//
	private static Shell mainShell; // 程序主shell
	private static Composite rsbShell; // 区间shell
	private static Figure panel = new Figure();; // 绘图容器

	public static List<TaskPlan> taskList = new ArrayList<TaskPlan>(); // 任务列表list

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
	private static Color backgroundColor = ColorConstants.white; // 股道线初始颜色

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
	private static Font font5 = new Font(Display.getDefault(), "Times New Roman", 10, SWT.NORMAL);
	
	private static TextStyle style1;
	private static TextStyle style2 = new TextStyle(font2, ColorConstants.green, null);
	private static TextStyle style3;

	//道路的list
	public static List<TrackLine> disListS1 = new ArrayList<TrackLine>();
	public static List<TrackLine> disListX1 = new ArrayList<TrackLine>();
	
	public static List<TrackLine> disListS2 = new ArrayList<TrackLine>();
	public static List<TrackLine> disListX2 = new ArrayList<TrackLine>();
	
	public static List<TrackLine> disListS3 = new ArrayList<TrackLine>();
	public static List<TrackLine> disListX3 = new ArrayList<TrackLine>();
	
	public static List<TrackLine> disListS4 = new ArrayList<TrackLine>();
	public static List<TrackLine> disListX4 = new ArrayList<TrackLine>();
	
	//信号机list
	public static List<SemaphoreDoubleL> sepListS1 = new ArrayList<SemaphoreDoubleL>();
	public static List<SemaphoreDoubleR> sepListX1 = new ArrayList<SemaphoreDoubleR>();
	
	public static List<SemaphoreDoubleL> sepListS2 = new ArrayList<SemaphoreDoubleL>();
	public static List<SemaphoreDoubleR> sepListX2 = new ArrayList<SemaphoreDoubleR>();
	
	public static List<SemaphoreDoubleL> sepListS3 = new ArrayList<SemaphoreDoubleL>();
	public static List<SemaphoreDoubleR> sepListX3 = new ArrayList<SemaphoreDoubleR>();
	
	public static List<SemaphoreDoubleL> sepListS4 = new ArrayList<SemaphoreDoubleL>();
	public static List<SemaphoreDoubleR> sepListX4 = new ArrayList<SemaphoreDoubleR>();
	
	public static List<SemaphoreDoubleL> getSepListS1() {
		return sepListS1;
	}

	public static void setSepListS1(List<SemaphoreDoubleL> sepListS1) {
		BaseParam.sepListS1 = sepListS1;
	}

	public static List<SemaphoreDoubleR> getSepListX1() {
		return sepListX1;
	}

	public static void setSepListX1(List<SemaphoreDoubleR> sepListX1) {
		BaseParam.sepListX1 = sepListX1;
	}

	public static List<SemaphoreDoubleL> getSepListS2() {
		return sepListS2;
	}

	public static void setSepListS2(List<SemaphoreDoubleL> sepListS2) {
		BaseParam.sepListS2 = sepListS2;
	}

	public static List<SemaphoreDoubleR> getSepListX2() {
		return sepListX2;
	}

	public static void setSepListX2(List<SemaphoreDoubleR> sepListX2) {
		BaseParam.sepListX2 = sepListX2;
	}

	public static List<SemaphoreDoubleL> getSepListS3() {
		return sepListS3;
	}

	public static void setSepListS3(List<SemaphoreDoubleL> sepListS3) {
		BaseParam.sepListS3 = sepListS3;
	}

	public static List<SemaphoreDoubleR> getSepListX3() {
		return sepListX3;
	}

	public static void setSepListX3(List<SemaphoreDoubleR> sepListX3) {
		BaseParam.sepListX3 = sepListX3;
	}

	public static List<SemaphoreDoubleL> getSepListS4() {
		return sepListS4;
	}

	public static void setSepListS4(List<SemaphoreDoubleL> sepListS4) {
		BaseParam.sepListS4 = sepListS4;
	}

	public static List<SemaphoreDoubleR> getSepListX4() {
		return sepListX4;
	}

	public static void setSepListX4(List<SemaphoreDoubleR> sepListX4) {
		BaseParam.sepListX4 = sepListX4;
	}

	public static List<TrackLine> getDisListS1() {
		return disListS1;
	}

	public static void setDisListS1(List<TrackLine> disListS1) {
		BaseParam.disListS1 = disListS1;
	}

	public static List<TrackLine> getDisListX1() {
		return disListX1;
	}

	public static void setDisListX1(List<TrackLine> disListX1) {
		BaseParam.disListX1 = disListX1;
	}

	public static List<TrackLine> getDisListS2() {
		return disListS2;
	}

	public static void setDisListS2(List<TrackLine> disListS2) {
		BaseParam.disListS2 = disListS2;
	}

	public static List<TrackLine> getDisListX2() {
		return disListX2;
	}

	public static void setDisListX2(List<TrackLine> disListX2) {
		BaseParam.disListX2 = disListX2;
	}

	public static List<TrackLine> getDisListS3() {
		return disListS3;
	}

	public static void setDisListS3(List<TrackLine> disListS3) {
		BaseParam.disListS3 = disListS3;
	}

	public static List<TrackLine> getDisListX3() {
		return disListX3;
	}

	public static void setDisListX3(List<TrackLine> disListX3) {
		BaseParam.disListX3 = disListX3;
	}

	public static List<TrackLine> getDisListS4() {
		return disListS4;
	}

	public static void setDisListS4(List<TrackLine> disListS4) {
		BaseParam.disListS4 = disListS4;
	}

	public static List<TrackLine> getDisListX4() {
		return disListX4;
	}

	public static void setDisListX4(List<TrackLine> disListX4) {
		BaseParam.disListX4 = disListX4;
	}

	public static Font getFont5() {
		return font5;
	}

	public static void setFont5(Font font5) {
		BaseParam.font5 = font5;
	}

	public static Figure getPanel() {
		return panel;
	}

	public static void setPanel(Figure panel) {
		BaseParam.panel = panel;
	}

	public static Shell getMainShell() {
		return mainShell;
	}

	public static void setMainShell(Shell mainShell) {
		BaseParam.mainShell = mainShell;
	}

	public static Composite getRsbShell() {
		return rsbShell;
	}

	public static void setRsbShell(Composite rsbShell) {
		BaseParam.rsbShell = rsbShell;
	}

	public static List<TaskPlan> getTaskList() {
		return taskList;
	}

	public static void setTaskList(List<TaskPlan> taskList) {
		BaseParam.taskList = taskList;
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

	public static void setTrackLength(int trackLength) {
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

	public static int getDistanceLen() {
		return distanceLen;
	}

	public static void setDistanceLen(int distanceLen) {
		BaseParam.distanceLen = distanceLen;
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

	public static int getSlineLength() {
		return slineLength;
	}

	public static void setSlineLength(int slineLength) {
		BaseParam.slineLength = slineLength;
	}

	public static Color getTrackInitColor() {
		return trackInitColor;
	}

	public static void setTrackInitColor(Color trackInitColor) {
		BaseParam.trackInitColor = trackInitColor;
	}

	public static Color getBackgroundColor() {
		return backgroundColor;
	}

	public static void setBackgroundColor(Color backgroundColor) {
		BaseParam.backgroundColor = backgroundColor;
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

	public static Color getD_buttonInitColor() {
		return d_buttonInitColor;
	}

	public static void setD_buttonInitColor(Color initColor) {
		d_buttonInitColor = initColor;
	}

	public static Color getD_buttonClickColor() {
		return d_buttonClickColor;
	}

	public static void setD_buttonClickColor(Color clickColor) {
		d_buttonClickColor = clickColor;
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

	public static Font getFont4() {
		return font4;
	}

	public static void setFont4(Font font4) {
		BaseParam.font4 = font4;
	}

	public static TextStyle getStyle1() {
		return style1;
	}

	public static void setStyle1(TextStyle style1) {
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

}
