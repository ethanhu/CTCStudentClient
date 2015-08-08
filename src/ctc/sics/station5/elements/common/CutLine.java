package ctc.sics.station5.elements.common;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Color;

import ctc.sics.station5.data.BaseParam;

public class CutLine  extends PolylineConnection {

	BaseParam baseData = BaseParam.getInstance();
	public Figure panel = baseData.getPanel();
	public int lineWidth = baseData.getLineWidth();
	public Color lineColor = baseData.getTrackInitColor();	
	public int slen = baseData.getSlineLength();	
	public PolylineConnection sline = new PolylineConnection();
	
	//股道线
	public CutLine(int X, int Y) {		
		
		//绝缘线
		this.sline.setStart(new Point(X, Y - slen/2));
		this.sline.setEnd(new Point(X, Y + slen/2));
		this.sline.setLineWidth(lineWidth - 1);
		this.sline.setForegroundColor(lineColor);
		panel.add(this.sline);

	}
}
