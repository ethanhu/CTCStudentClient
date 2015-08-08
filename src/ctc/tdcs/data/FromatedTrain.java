package ctc.tdcs.data;

/**
 * 界面绘制中所采用的表示车次信息的格式
 * @author 胡恩召
 * 
 */
public class FromatedTrain {

	private String trainName; //车次名称
	
	private String stationName; //本站的站名
	private int stationTimeX; //本站的离开时间X坐标
	
	private String nextStationName; //下一站的站名
	private int nextStationTimeX; //下一站的到达时间X坐标

	
	public String getTrainName() {
		return trainName;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public int getStationTimeX() {
		return stationTimeX;
	}

	public void setStationTimeX(int stationTimeX) {
		this.stationTimeX = stationTimeX;
	}

	public String getNextStationName() {
		return nextStationName;
	}

	public void setNextStationName(String nextStationName) {
		this.nextStationName = nextStationName;
	}

	public int getNextStationTimeX() {
		return nextStationTimeX;
	}

	public void setNextStationTimeX(int nextStationTimeX) {
		this.nextStationTimeX = nextStationTimeX;
	}

}
