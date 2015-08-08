package ctc.sics.station.data;

import java.util.ArrayList;

public class RoadList {

	public String startName; // 路的起点名称
	public String midName = "NOBA"; // 路的变更名称
	public String endName; // 路的终点名称
	public int index = 0; // 路的索引
	public int flag = ParamFlag.road_blue; // 路的状态(蓝，绿，红)
	public String trainName = "";
	public ArrayList<RoadBasicInfo> roadInfoList = new ArrayList<RoadBasicInfo>(); // 道路
	
	public String getTrainName() {
		return trainName;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	public String getStartName() {
		return startName;
	}

	public void setStartName(String startName) {
		this.startName = startName;
	}

	public String getEndName() {
		return endName;
	}

	public void setEndName(String endName) {
		this.endName = endName;
	}

	public ArrayList<RoadBasicInfo> getRoadInfoList() {
		return roadInfoList;
	}

	public void setRoadInfoList(ArrayList<RoadBasicInfo> roadInfoList) {
		this.roadInfoList = roadInfoList;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getMidName() {
		return midName;
	}

	public void setMidName(String midName) {
		this.midName = midName;
	}

}
