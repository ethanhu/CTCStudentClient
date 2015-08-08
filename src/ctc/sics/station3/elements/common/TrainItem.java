package ctc.sics.station3.elements.common;

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

	public String trainName;
	public TableItem item;

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

}
