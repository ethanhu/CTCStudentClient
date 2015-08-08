package ctc.sics.station1.data;

import java.util.ArrayList;

import ctc.sics.station1.elements.common.*;

public class TrainStopRoad {

	public TrackLine stopLine;
	public ArrayList<RoadList> stopLineRoadList = new ArrayList<RoadList>(); // 路径list
	
	public TrackLine getStopLine() {
		return stopLine;
	}
	public void setStopLine(TrackLine stopLine) {
		this.stopLine = stopLine;
	}
	public ArrayList<RoadList> getStopLineRoadList() {
		return stopLineRoadList;
	}
	public void setStopLineRoadList(ArrayList<RoadList> stopLineRoadList) {
		this.stopLineRoadList = stopLineRoadList;
	}
		
}
