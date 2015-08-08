package ctc.sics.station.data;

import org.eclipse.draw2d.Figure;

public class RoadBasicInfo {
	
	public Figure roadName; //名称
	public int blueType; //蓝色类型
	public int greenType; //绿色类型
	public int redType; //红色类型
		
	public Figure getRoadName() {
		return roadName;
	}
	public void setRoadName(Figure roadName) {
		this.roadName = roadName;
	}
	public int getBlueType() {
		return blueType;
	}
	public void setBlueType(int blueType) {
		this.blueType = blueType;
	}
	public int getGreenType() {
		return greenType;
	}
	public void setGreenType(int greenType) {
		this.greenType = greenType;
	}
	public int getRedType() {
		return redType;
	}
	public void setRedType(int redType) {
		this.redType = redType;
	}
	
}
