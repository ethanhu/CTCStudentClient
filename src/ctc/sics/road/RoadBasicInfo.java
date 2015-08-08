package ctc.sics.road;

/**
 * 车站内的路径基本信息表
 * @author 胡恩召
 *
 */
public class RoadBasicInfo {
	
	public String roadType; //路段的类型(根据figure中的类而定)
	public String roadName; //路段的名称(标号)
	
	public String getRoadType() {
		return roadType;
	}
	
	public void setRoadType(String roadType) {
		this.roadType = roadType;
	}
	
	public String getRoadName() {
		return roadName;
	}
	
	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}	
}
