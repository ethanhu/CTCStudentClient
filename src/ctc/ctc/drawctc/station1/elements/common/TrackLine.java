package ctc.ctc.drawctc.station1.elements.common;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Point;

import ctc.ctc.drawctc.station1.data.*;

/**
 * 站的股道线
 * 
 * @author 胡恩召
 * 
 */
public class TrackLine extends PolylineConnection {

	BaseParam baseData = BaseParam.getInstance();

	public int status = 0; // 使用状态：可使用0, 锁定中1, 车占用2, 坏了:-1
	public static StationLabel stationLabel; // 文字label
	public PolylineConnection line = new PolylineConnection();

	private String name;

	/**
	 * 新建一条直线
	 * 
	 */
	public TrackLine(String lineName, int initX, int initY, int length) {

		this.name = lineName;
		line.setStart(new Point(initX, initY));
		line.setEnd(new Point(initX + length, initY));
		line.setLineWidth(baseData.getLineWidth());
		line.setForegroundColor(baseData.getTrackInitColor());
		baseData.getPanel().add(line);

		stationLabel = new StationLabel(lineName, initX + length / 2 - baseData.getLabelWidth() / 2, initY + 2);
				
	}

	/**
	 * 设置线的状态
	 */
	public void setColorStatus(int color) {

		switch(color){
		
		case ParamFlag.trackline_bule: //蓝色
			this.status = 0;
			this.line.setForegroundColor(ColorConstants.blue); // 主道岔
			break;
		case ParamFlag.trackline_green: //绿色
			this.status = 1;
			this.line.setForegroundColor(ColorConstants.green); // 主道岔
			break;
		case ParamFlag.trackline_red: //红色
			this.status = 2;
			this.line.setForegroundColor(ColorConstants.red); // 主道岔
			break;
		case ParamFlag.trackline_black: //黑色
			this.status = -1;
			this.line.setForegroundColor(ColorConstants.black); // 主道岔
			break;
		}
	}

	/**
	 * 设置label的显示与隐藏
	 */
	public void setLabelStatus(boolean flag) {
		stationLabel.getLab().setVisible(flag);
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

	public StationLabel getStationLabel() {
		return stationLabel;
	}

	public void setStationLabel(StationLabel stationLabel) {
		this.stationLabel = stationLabel;
	}

}
