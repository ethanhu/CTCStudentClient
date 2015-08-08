package ctc.sics.station1.elements.common;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;

import ctc.sics.station1.data.BaseParam;

public class EndLine extends PolylineConnection {

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
	
	private String name;
	public PolylineConnection line1 = new PolylineConnection();
	public PolylineConnection line2 = new PolylineConnection();
	public PolylineConnection line3 = new PolylineConnection();
	
	// 构造函数
	public EndLine(String flag, String lineName, int initX, int initY) {

		int l = 8;
		this.name = lineName;
		
		if (flag.equalsIgnoreCase("Right")) {
			line1.setStart(new Point(initX, initY - l));
			line1.setEnd(new Point(initX, initY + l));
			line1.setLineWidth(lineWidth);
			line1.setForegroundColor(lineColor);
			panel.add(line1);

			line2.setStart(new Point(initX, initY - l));
			line2.setEnd(new Point(initX + l, initY - l));
			line2.setLineWidth(lineWidth);
			line2.setForegroundColor(lineColor);
			panel.add(line2);

			line3.setStart(new Point(initX, initY + l));
			line3.setEnd(new Point(initX + l, initY + l));
			line3.setLineWidth(lineWidth);
			line3.setForegroundColor(lineColor);
			panel.add(line3);

		} else {

			line1.setStart(new Point(initX, initY - l));
			line1.setEnd(new Point(initX, initY + l));
			line1.setLineWidth(lineWidth);
			line1.setForegroundColor(lineColor);
			panel.add(line1);

			line2.setStart(new Point(initX, initY - l));
			line2.setEnd(new Point(initX - l, initY - l));
			line2.setLineWidth(lineWidth);
			line2.setForegroundColor(lineColor);
			panel.add(line2);

			line3.setStart(new Point(initX, initY + l));
			line3.setEnd(new Point(initX - l, initY + l));
			line3.setLineWidth(lineWidth);
			line3.setForegroundColor(lineColor);
			panel.add(line3);
			
		}

	}
	
	
	public PolylineConnection getLine1() {
		return line1;
	}
	public void setLine1(PolylineConnection line1) {
		this.line1 = line1;
	}
	public PolylineConnection getLine2() {
		return line2;
	}
	public void setLine2(PolylineConnection line2) {
		this.line2 = line2;
	}
	public PolylineConnection getLine3() {
		return line3;
	}
	public void setLine3(PolylineConnection line3) {
		this.line3 = line3;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
