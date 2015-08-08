package ctc.ctc.drawctc.station1.elements.common;

import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Point;

import ctc.ctc.drawctc.station1.data.BaseParam;



public class EndLine extends PolylineConnection {

	BaseParam baseData = BaseParam.getInstance();
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
			line1.setLineWidth(baseData.getLineWidth());
			line1.setForegroundColor(baseData.getTrackInitColor());
			baseData.getPanel().add(line1);

			line2.setStart(new Point(initX, initY - l));
			line2.setEnd(new Point(initX + l, initY - l));
			line2.setLineWidth(baseData.getLineWidth());
			line2.setForegroundColor(baseData.getTrackInitColor());
			baseData.getPanel().add(line2);

			line3.setStart(new Point(initX, initY + l));
			line3.setEnd(new Point(initX + l, initY + l));
			line3.setLineWidth(baseData.getLineWidth());
			line3.setForegroundColor(baseData.getTrackInitColor());
			baseData.getPanel().add(line3);

		} else {

			line1.setStart(new Point(initX, initY - l));
			line1.setEnd(new Point(initX, initY + l));
			line1.setLineWidth(baseData.getLineWidth());
			line1.setForegroundColor(baseData.getTrackInitColor());
			baseData.getPanel().add(line1);

			line2.setStart(new Point(initX, initY - l));
			line2.setEnd(new Point(initX - l, initY - l));
			line2.setLineWidth(baseData.getLineWidth());
			line2.setForegroundColor(baseData.getTrackInitColor());
			baseData.getPanel().add(line2);

			line3.setStart(new Point(initX, initY + l));
			line3.setEnd(new Point(initX - l, initY + l));
			line3.setLineWidth(baseData.getLineWidth());
			line3.setForegroundColor(baseData.getTrackInitColor());
			baseData.getPanel().add(line3);
			
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
