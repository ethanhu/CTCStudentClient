package ctc.rsb.drawrsb.data;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.widgets.Display;

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
	private static int rowSpacing = 60; // 股道间距
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

	private static TextStyle style1;
	private static TextStyle style2 = new TextStyle(font2, ColorConstants.green, null);
	private static TextStyle style3;

	public static BaseParam getBaseData() {
		return baseData;
	}
	public static void setBaseData(BaseParam baseData) {
		BaseParam.baseData = baseData;
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
