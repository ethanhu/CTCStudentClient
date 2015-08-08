package ctc.tdcs.data;

//记录的是到站或离站信息
public class PlanForStatics {

	private String trainName;//车次
	private String districtName;//区段名称
	
	private String arriveTime;//到站时间
	private String leaveTime;//离站时间
	
	private String prestationName;//前站名称
	private String stationName;//本站名称
	private int trainDirection;//车次方向  上行0和下行1
	
	//为方便TdcsStaticsDialog类进行有关判断而设置的标记
	private int arriveTrainType;//对到站车次进行的操作类别
	private int leaveTrainType;//对离站车次进行的操作类别
	
	//为除TdcsStaticsDialog类外其他类所用
	private int twoInOneTrainType;//仅仅是为了方便对新加的车次在进行操作前进行判断。此字段表示的是两个站之间的一条车次线的状态
	
	public int getTwoInOneTrainType() {
		return twoInOneTrainType;
	}

	public void setTwoInOneTrainType(int twoInOneTrainType) {
		this.twoInOneTrainType = twoInOneTrainType;
	}

	private String operator;//操作者姓名
	
	public PlanForStatics(){}
	
	public PlanForStatics(String stationName,String trainName, int trainDirection) {
		this.stationName = stationName;
		this.trainName = trainName;
		this.trainDirection = trainDirection;
	}
	
	public PlanForStatics(String arriveTime, String leaveTime,
			String districtName, String prestationName, String stationName,
			String trainName, int trainDirection) {
		this.arriveTime = arriveTime;
		this.leaveTime = leaveTime;
		this.districtName = districtName;
		this.prestationName = prestationName;
		this.stationName = stationName;
		this.trainName = trainName;
		this.trainDirection = trainDirection;
	}

	public String getArriveTime() {
		return arriveTime;
	}

	public void setArriveTime(String arriveTime) {
		this.arriveTime = arriveTime;
	}

	public String getLeaveTime() {
		return leaveTime;
	}

	public void setLeaveTime(String leaveTime) {
		this.leaveTime = leaveTime;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getPrestationName() {
		return prestationName;
	}

	public void setPrestationName(String prestationName) {
		this.prestationName = prestationName;
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

	public int getTrainDirection() {
		return trainDirection;
	}

	public void setTrainDirection(int trainDirection) {
		this.trainDirection = trainDirection;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public int getArriveTrainType() {
		return arriveTrainType;
	}

	public void setArriveTrainType(int arriveTrainType) {
		this.arriveTrainType = arriveTrainType;
	}

	public int getLeaveTrainType() {
		return leaveTrainType;
	}

	public void setLeaveTrainType(int leaveTrainType) {
		this.leaveTrainType = leaveTrainType;
	}

	
}