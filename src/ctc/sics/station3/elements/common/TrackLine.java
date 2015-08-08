package ctc.sics.station3.elements.common;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;

import ctc.sics.station.data.ParamFlag;
import ctc.sics.station3.data.*;

/**
 * 站的股道线
 * 
 * @author 胡恩召
 * 
 */
public class TrackLine extends PolylineConnection {

	BaseParam baseData = BaseParam.getInstance();
	public Figure panel = baseData.getPanel();
	public int lineWidth = baseData.getLineWidth();
	public Color lineColor = baseData.getTrackInitColor();
	public Color bgColor = baseData.getBackgroundColor();
	public int labHeight = baseData.getLabelHeight();
	public int dis = baseData.getDistanceLen();
	public Font font1 = baseData.getFont1();
	public Font font2 = baseData.getFont4();
	public int slen = baseData.getSlineLength();

	private String name; // 线的名称
	public int status = 0; // 使用状态：可使用0, 锁定中1, 车占用2, 坏了:-1
	public Label trainLabel = new Label(); // 火车名称
	public Label nameLabel = new Label(); // 线名称Label
	public PolylineConnection line = new PolylineConnection();
	public PolylineConnection sline = new PolylineConnection();
	public Color fontColor; // 字体颜色
	
	// 股道线
	public TrackLine(String lineName, int X, int Y, int length, String type) {
		
		if (bgColor.equals(ColorConstants.white)) {
			fontColor = ColorConstants.black;
		} else {
			fontColor = ColorConstants.white;
		}
		
		
		//this.nameLabel.setText(lineName);
		//this.trainLabel.setToolTip(this.nameLabel);
		//this.trainLabel.setBackgroundColor(fontColor);
		// 车次名称		
		this.trainLabel.setText(lineName);
		this.trainLabel.setVisible(false);
		this.trainLabel.setOpaque(true);		
		this.trainLabel.setBorder(new LineBorder());
		this.trainLabel.setFont(font2);
		this.trainLabel.setForegroundColor(fontColor);				
		this.trainLabel.setSize(lineName.length() * 8 + 6, labHeight * 3 / 2);
		this.trainLabel.setLocation(new Point(X + length / 2 - lineName.length() * 4 - 3, Y - labHeight * 3 / 2));
		panel.add(this.trainLabel);

		// 股道线
		this.name = lineName;
		// this.line.setToolTip(this.nameLabel);
		this.line.setStart(new Point(X, Y));
		this.line.setEnd(new Point(X + length, Y));
		this.line.setLineWidth(lineWidth);
		this.line.setForegroundColor(lineColor);
		panel.add(this.line);

		// 绝缘线
		this.sline.setStart(new Point(X, Y - slen / 2));
		this.sline.setEnd(new Point(X, Y + slen / 2));
		this.sline.setLineWidth(lineWidth - 1);
		this.sline.setForegroundColor(lineColor);
		panel.add(this.sline);

		//股道名称
		new LineNameLabel(lineName, X, Y, length, type);
	}

	/**
	 * 设置线的状态
	 */
	public void setColorStatus(int color) {

		switch (color) {

		case ParamFlag.trackline_bule: // 蓝色
			this.status = 0;
			this.line.setForegroundColor(ColorConstants.blue); // 主道岔
			break;
		case ParamFlag.trackline_green: // 绿色
			this.status = 1;
			this.line.setForegroundColor(ColorConstants.green); // 主道岔
			break;
		case ParamFlag.trackline_red: // 红色
			this.status = 2;
			this.line.setForegroundColor(ColorConstants.red); // 主道岔
			break;
		case ParamFlag.trackline_black: // 黑色
			this.status = -1;
			this.line.setForegroundColor(ColorConstants.black); // 主道岔
			break;
		case ParamFlag.trackline_white: // 白色
			this.status = -1;
			this.line.setForegroundColor(ColorConstants.white); // 主道岔
			break;
		}
	}

	public Label getTrainLabel() {
		return trainLabel;
	}

	public void setTrainLabel(Label trainLabel) {
		this.trainLabel = trainLabel;
	}

	public PolylineConnection getSline() {
		return sline;
	}

	public void setSline(PolylineConnection sline) {
		this.sline = sline;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public PolylineConnection getLine() {
		return line;
	}

	public void setLine(PolylineConnection line) {
		this.line = line;
	}
}
