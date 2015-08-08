package ctc.ctc.drawctc.ctcmain.drawctcmain;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

/**
 * 火车任务的Item
 * 
 * @author 胡恩召
 * 
 */
public class TrainItem {

	public String stationName; // 站名
	public String trainName; // 车次名
	public String trackLine; // 股道
	public String direction; // 方向
	public TableItem item; // item

	public TrainItem(String trainName, Table parent) {
		item = new TableItem(parent, SWT.NONE);
		this.trainName = trainName;
	}

	public String getTrainName() {
		return trainName;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	public TableItem getItem() {
		return item;
	}

	public void setItem(TableItem item) {
		this.item = item;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getTrackLine() {
		return trackLine;
	}

	public void setTrackLine(String trackLine) {
		this.trackLine = trackLine;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

}
