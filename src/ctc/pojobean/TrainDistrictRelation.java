package ctc.pojobean;

public class TrainDistrictRelation {

	// 车次区段关系
	public String Train_name;//车次名称
	public String District_name;//区段名称
	
	public TrainDistrictRelation(){}
	
	public TrainDistrictRelation(String district_name, String train_name) {
		Train_name = train_name;
		District_name = district_name;
	}

	public String getTrain_name() {
		return Train_name;
	}
	public void setTrain_name(String train_name) {
		Train_name = train_name;
	}
	public String getDistrict_name() {
		return District_name;
	}
	public void setDistrict_name(String district_name) {
		District_name = district_name;
	}


}
