package ctc.tdcs.data;

import ctc.constant.Constants;
import ctc.tdcs.TdcsEnvInit;

public class TrainPlan {

	private String stationName;//本站站名  同库中时间格式一致
	private String trainName;//车次名称	
	private int trainDirection;//车次方向（上行0和下行1）	
	private String prestationName;//前站站名	
	private String time;//到站或离站时间	
	private String districtName = BaseParam.getCurrentDistrictName() ;//区段名称
	private int trainType = Constants.TDCS_TRAIN_TYPE_NO;//对车次进行的操作类别:对原计划车次没有进行任何操作; 对原计划车次进行了变动; 新添加的车次信息;实际运行的车次
	private String operator = TdcsEnvInit.getUserName(); //操作者姓名
	
	private boolean operatorFlag = false;//可操作标记 : false可以操作, true不能再操作: 根据车次的运行时间与当前时间的比对决定
	
	public TrainPlan(){
		
	}

	public TrainPlan(String prestationName, String stationName,String trainName, 
			           String time,int trainDirection, boolean operatorFlag) {
		this.trainName = trainName;
		this.trainDirection = trainDirection;
		this.stationName = stationName;
		this.time = time;
		this.prestationName = prestationName;
		this.operatorFlag = operatorFlag;
	}
	
	public TrainPlan(String prestationName, String stationName,String trainName, 
			           String time,int trainDirection, boolean operatorFlag,
			           int trainType) {
		this.trainName = trainName;
		this.trainDirection = trainDirection;
		this.stationName = stationName;
		this.time = time;
		this.prestationName = prestationName;
		this.operatorFlag = operatorFlag;
		this.trainType = trainType;
	}
	
	public TrainPlan(String prestationName, String stationName,String trainName, 
			           String time,int trainDirection,boolean operatorFlag,
			           int trainType,String districtName, String operator)
	{
		this.trainName = trainName;
		this.trainDirection = trainDirection;
		this.districtName = districtName;
		this.stationName = stationName;
		this.time = time;
		this.prestationName = prestationName;
		this.operator = operator;
		this.operatorFlag = operatorFlag;
		this.trainType = trainType;
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

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPrestationName() {
		return prestationName;
	}

	public void setPrestationName(String prestationName) {
		this.prestationName = prestationName;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public boolean isOperatorFlag() {
		return operatorFlag;
	}

	public void setOperatorFlag(boolean operatorFlag) {
		this.operatorFlag = operatorFlag;
	}

	public int getTrainType() {
		return trainType;
	}

	public void setTrainType(int trainType) {
		this.trainType = trainType;
	}
	
}