package ctc.ctc.drawctc.ctcmain.data;

import java.util.ArrayList;

import ctc.pojobean.Plan;

public class StationPlan {

	public String stationName;
	public ArrayList<Plan> taskList = new ArrayList<Plan>();

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public ArrayList<Plan> getTaskList() {
		return taskList;
	}

	public void setTaskList(ArrayList<Plan> taskList) {
		this.taskList = taskList;
	}

}
