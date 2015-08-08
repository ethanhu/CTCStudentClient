package ctc.tdcs.data;

import ctc.constant.Constants;

public class TrainStationTime {

	private String stationName;//本站名称
	private String trainName;//车次名称
	private int typeTime = Constants.TDCS_TIME_TYPE_NONE;//车次的时间类别: 到站或离站  TDCS_TIME_TYPE_ARRIVEAL 6291457 TDCS_TIME_TYPE_LEAVE
	
	public TrainStationTime(){}
	
	public TrainStationTime(String stationName, String trainName, int typeTime) {
		this.stationName = stationName;
		this.trainName = trainName;
		this.typeTime = typeTime;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getTrainName() {
		return trainName;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	public int getTypeTime() {
		return typeTime;
	}

	public void setTypeTime(int typeTime) {
		this.typeTime = typeTime;
	}

	//下面两个方法是为了支持containsKey(arg0)（进行内容比较）覆盖object中相应方法
	public int hashCode(){
		return (stationName + trainName + typeTime).hashCode();//return 0; 
	}
	public boolean equals(Object o){
	    return (o instanceof TrainStationTime)&&
	           (stationName.equalsIgnoreCase(((TrainStationTime)o).stationName))&&
	           (trainName.equalsIgnoreCase(((TrainStationTime)o).trainName))&&
	    	   (typeTime == ((TrainStationTime)o).typeTime);
	}
	
}

