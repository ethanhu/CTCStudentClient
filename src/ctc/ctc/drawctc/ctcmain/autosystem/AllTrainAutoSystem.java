package ctc.ctc.drawctc.ctcmain.autosystem;

import java.util.Date;
import java.util.Timer;

import org.eclipse.swt.widgets.Display;

import ctc.ctc.ctc2sics.CTCToSICS;
import ctc.ctc.drawctc.ctcmain.data.BaseParam;
import ctc.ctc.drawctc.ctcmain.data.StationPlan;
import ctc.ctc.drawctc.ctcmain.drawctcmain.TrainItem;
import ctc.ctc.drawctc.util.DateUtil;
import ctc.pojobean.Plan;

/**
 * 监控所有车辆的情况
 * @author 胡恩召
 *
 */
public class AllTrainAutoSystem {

	AutoSystem autoSystem = AutoSystem.getInstance();
	BaseParam baseData = BaseParam.getInstance();	
	
	public static AllTrainAutoSystem allTrainAutoSystem = null;

	public static AllTrainAutoSystem getInstance() {

		if (allTrainAutoSystem == null) {
			allTrainAutoSystem = new AllTrainAutoSystem();
		}
		return allTrainAutoSystem;
	}
	
	final Timer timer = new Timer();
	int timestamp = 60*1000;
	
	//负责监控车站到达问题
	public void autoSystemRun(){

		timer.schedule(new java.util.TimerTask() {
			public void run() {

				Display.getDefault().asyncExec(new Runnable() {
					public void run() {
						proTrainArrive(); // 处理车辆到达
					}
				});
			}// run
		}, 0, timestamp);
	}
		
	// 处理车辆到达问题
	public void proTrainArrive(){
		
		if(baseData.getStationPlanList() != null && baseData.getStationPlanList().size() != 0){
			
			int stationNum = baseData.getStationPlanList().size();
			for(int i = 0; i < stationNum; i++){
				StationPlan stationPlan = baseData.getStationPlanList().get(i);
				String stationName = stationPlan.getStationName();
				
				if(stationPlan.getTaskList() != null && stationPlan.getTaskList().size() != 0){
					int trainNum = stationPlan.getTaskList().size();
					for(int j = 0; j < trainNum; j++){
						Plan plan = stationPlan.getTaskList().get(j);
						
						if(plan.getPlan_arrivestationtime().equalsIgnoreCase("00:00:00")){ //出现始发站
							
							String trainName = plan.getTrain_name();
							
							int nowMin = DateUtil.getMinuteOfDate(new Date());
							int planMinArrive = DateUtil.transferToMinute(plan.getPlan_arrivestationtime());
							int planMinLieve = DateUtil.transferToMinute(plan.getPlan_leavestationtime());
							if(planMinLieve == nowMin + 1){ //提前一分钟初始化列车到相应的股道
								String name = getTrackLineOfTrain(stationName, trainName);
								String trackName;
								if(name.equalsIgnoreCase("1G")){
									trackName = "I";
								}else if(name.equalsIgnoreCase("2G")){
									trackName = "II";
								}else if(name.equalsIgnoreCase("3G")){
									trackName = "3";
								}else if(name.equalsIgnoreCase("4G")){
									trackName = "4";
								}else {
									trackName = "6";
								}
								CTCToSICS.sendTrainArriveMessageASYN(stationName, trackName);									
							}													
						}else{
							
							String trainName = plan.getTrain_name();
							
							int nowMin = DateUtil.getMinuteOfDate(new Date());
							int planMinArrive = DateUtil.transferToMinute(plan.getPlan_arrivestationtime());
							int planMinLieve = DateUtil.transferToMinute(plan.getPlan_leavestationtime());
							if(planMinArrive == nowMin + 1){ //提前一分钟初始化列车到相应的股道
								
								if(planMinArrive <= planMinLieve){ //下行
									
									
									
								}else { //上行
									
									
								}
								
								String name = getTrackLineOfTrain(stationName, trainName);
								String trackName;
								if(name.equalsIgnoreCase("1G")){
									trackName = "I";
								}else if(name.equalsIgnoreCase("2G")){
									trackName = "II";
								}else if(name.equalsIgnoreCase("3G")){
									trackName = "3";
								}else if(name.equalsIgnoreCase("4G")){
									trackName = "4";
								}else {
									trackName = "6";
								}
								CTCToSICS.sendTrainArriveMessageASYN(stationName, trackName);									
							}													
							
						}
					}
				}			
			}		
		}
		
		
		if(autoSystem != null){
			if(autoSystem.getTrainList() != null && autoSystem.getTrainList().size() != 0){
				
				int len = autoSystem.getTrainList().size();
				for(int i = 0; i < len; i++){
					
					TrainItem plan = autoSystem.getTrainList().get(i);
					String direction = plan.getItem().getText(3);
					String trainTime = plan.getItem().getText(4);
					
					
					
					int min = DateUtil.transferToMinute(plan.getItem().getText(4));
					
					
					int nowMinutes = DateUtil.getMinuteOfDate(new Date());
					
					
					
					if(min > 1 && min <= 1 + 1){		
										
					}
				}				
			}
		}
		
	}
	
	//获得车次所在的股道
	public String getTrackLineOfTrain(String stationName, String trainName){
		
		String trackLine = "1G";
		
		if(autoSystem != null){
			if(autoSystem.getTrainList() != null && autoSystem.getTrainList().size() != 0){
				
				int len = autoSystem.getTrainList().size();
				for(int i = 0; i < len; i++){		
					TrainItem trainItem = autoSystem.getTrainList().get(i);
					if(trainItem.getStationName().equalsIgnoreCase(stationName) && trainItem.getTrainName().equalsIgnoreCase(trainName)){						
						trackLine =  trainItem.getTrackLine();
						break;
					}
				}
			}
		}
		return trackLine;
	}
		
	
	
	
}
