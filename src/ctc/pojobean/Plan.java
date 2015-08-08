package ctc.pojobean;

/**
 * 列车原始计划表
 */
public class Plan {

	public String District_name;//区段名称
	public String Plan_arrivestationtime;//到站时间
	public String Plan_leavestationtime;//离站时间	
	public String Prestation_name;//前站名称
	public String Station_name;//本站名称
	public String Train_name;//列车车次
	
	public Plan(){}
	
	public Plan(String station_name,String train_name) {
		Station_name = station_name;
		Train_name = train_name;
	}
	
	public Plan(String plan_arrivestationtime, String plan_leavestationtime,
			String district_name, String prestation_name, String station_name,
			String train_name) {
		Plan_arrivestationtime = plan_arrivestationtime;
		Plan_leavestationtime = plan_leavestationtime;
		District_name = district_name;
		Prestation_name = prestation_name;
		Station_name = station_name;
		Train_name = train_name;
	}
	
	public String getPlan_arrivestationtime() {
		return Plan_arrivestationtime;
	}
	public void setPlan_arrivestationtime(String plan_arrivestationtime) {
		Plan_arrivestationtime = plan_arrivestationtime;
	}
	public String getPlan_leavestationtime() {
		return Plan_leavestationtime;
	}
	public void setPlan_leavestationtime(String plan_leavestationtime) {
		Plan_leavestationtime = plan_leavestationtime;
	}
	public String getDistrict_name() {
		return District_name;
	}
	public void setDistrict_name(String district_name) {
		District_name = district_name;
	}
	public String getPrestation_name() {
		return Prestation_name;
	}
	public void setPrestation_name(String prestation_name) {
		Prestation_name = prestation_name;
	}
	public String getStation_name() {
		return Station_name;
	}
	public void setStation_name(String station_name) {
		Station_name = station_name;
	}
	public String getTrain_name() {
		return Train_name;
	}
	public void setTrain_name(String train_name) {
		Train_name = train_name;
	}

	

}