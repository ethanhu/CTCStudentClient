package ctc.ctc.drawctc.ctcmain.autosystem;

import java.util.Date;
import java.util.Timer;

import org.eclipse.swt.widgets.Display;

import ctc.ctc.ctc2sics.CTCToSICS;
import ctc.ctc.drawctc.ctcmain.data.BaseParam;
import ctc.ctc.drawctc.ctcmain.drawctcmain.TrainItem;
import ctc.ctc.drawctc.util.DateUtil;


/**
 * 每隔一段时间初始化一下首站的车次
 * @author 胡恩召
 *
 */
public class InitTrainSystem {

	BaseParam baseData = BaseParam.getInstance();	
	AutoSystem autoSystem = AutoSystem.getInstance();
	
	final Timer timer = new Timer();
	
	int timestamp = 10*60*1000; //10分钟初始化一次	
	
	//先删除原来的红线,然后再在新的位置绘制
	public void SystemRun(int initVTime,int step) {

		timer.schedule(new java.util.TimerTask() {
			public void run() {
				
				Display.getDefault().asyncExec(
						new Runnable() {
							public void run() {
								
								System.out.println("--1231232-");
								
								@SuppressWarnings("deprecation")
								Date now = new Date();
								int hour = now.getHours();
								int minute = now.getMinutes();
								int sumMinute = 60*hour + minute;
								int minuteStep = 10;
								
								initTrainOnTrackLine(sumMinute,minuteStep);
							}
						});				
			}//run
			}, 0, timestamp);			
	}
	
	//初始化车次
	private void initTrainOnTrackLine(int startMinute, int step)	{
		
		if(autoSystem != null){
			if(autoSystem.getTrainList1() != null && autoSystem.getTrainList1().size() != 0){
				
				int len = autoSystem.getTrainList1().size();
				for(int i = 0; i < len; i++){
					TrainItem plan = autoSystem.getTrainList1().get(i);
					int min = DateUtil.transferToMinute(plan.getItem().getText(4));
					if(min > startMinute && min <= startMinute + step){		
						String stationName = "标准站一";
						String trackName;
						String name = plan.getItem().getText(6);
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
		
		/*
		if(CTCMain.db != null){
			if(CTCMain.db.getPlanInfoList() != null && CTCMain.db.getPlanInfoList().size() != 0){
				
				int len = CTCMain.db.getPlanInfoList().size();
				for(int i = 0; i < len; i++){
					Plan plan = CTCMain.db.getPlanInfoList().get(i);
					if(plan.getPlan_arrivestationtime().equalsIgnoreCase("00:00:00")){
						int min = DateUtil.transferToMinute(plan.getPlan_leavestationtime());
						if(min > startMinute && min <= startMinute + step){
							
							//CTCToSICS.sendTrainArriveMessageASYN(plan.getStation_name(), plan.get)
							
						}
					}
				}				
			}
		}
		*/
		
	}
	
}
