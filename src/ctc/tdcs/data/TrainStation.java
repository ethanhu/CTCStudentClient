package ctc.tdcs.data;

public class TrainStation {

	private String stationName;//本站名称
	private String trainName;//车次名称

	public TrainStation(){}
	
	public TrainStation(String stationName, String trainName) {
		this.stationName = stationName;
		this.trainName = trainName;
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


	//下面两个方法是为了支持containsKey(arg0)（进行内容比较）覆盖object中相应方法
	public int hashCode(){
		return (stationName + trainName).hashCode();//return 0; 
	}
	public boolean equals(Object o){
	    return (o instanceof TrainStation)&&
	           (stationName.equalsIgnoreCase(((TrainStation)o).stationName))&&
	           (trainName.equalsIgnoreCase(((TrainStation)o).trainName));
	}
	
}

