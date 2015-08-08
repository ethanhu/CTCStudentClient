package ctc.sics.station.autosystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import org.eclipse.draw2d.Figure;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;

import ctc.pojobean.TaskPlan;
import ctc.sics.SicsMain;
import ctc.sics.util.DateUtil;

import ctc.sics.station.data.*;
import ctc.sics.station.elements.common.*;
import ctc.sics.station.layout.StationModel;


/**
 * 自律模式下自动执行，进行办理接发车作业
 * 
 * @author 胡恩召
 * 
 */
public class AutoSystem {

	BaseParam baseData = BaseParam.getInstance();
	StationModel stationModel = StationModel.getInstance();
	Table table = TaskTable.getInstance().getTable();
	
	public static AutoSystem autoSystem = null;
	public static AutoSystem getInstance() {
		if (autoSystem == null) {
			autoSystem = new AutoSystem();
		}
		return autoSystem;
	}

	final Timer initTrainTimer = new Timer();
	final Timer timer = new Timer();
	int timestamp = 1000;
	
	public List<TaskPlan> taskList = new ArrayList<TaskPlan>();

	//自动执行
	public void AutoSystemRun() {
			
		timer.schedule(new java.util.TimerTask() {
			public void run() {

				Display.getDefault().asyncExec(new Runnable() {
					public void run() {

						LA_FA(); // 接发车

					}
				});
			}// run
		}, 0, timestamp);
	}

	/**
	 * 进行自动接发车
	 */
	public void LA_FA() {
		
		taskList = baseData.getTaskList();
		
		if(taskList == null || taskList.size() == 0){
			//System.out.println("--AutoSystem-LA_FA-taskList==null || taskList.size()==0");
			return;		
		}
		
		int len = taskList.size();
		for (int i = 0; i < len; i++) {
			TaskPlan task = taskList.get(i);
			if (task.getTime().equalsIgnoreCase(DateUtil.getNowTime())) {
				
				stationModel.processRoad(task.getStartButton(), "NO", task.getEndButton(), ParamFlag.road_green);
				
				if(table == null || table.getItems().length != len ){
					System.out.println("table == null || table.getItems().length != taskList.size()");
					return;
				}
				table.getItem(i).setText(1, "已经执行");
				table.getItem(i).setText(5, task.getTime());
			}			
		}
	}
	
	
	//自动执行
	public void initTrainRun() {
			
		initTrainTimer.schedule(new java.util.TimerTask() {
			public void run() {

				Display.getDefault().asyncExec(new Runnable() {
					public void run() {

						initTrain(); // 接发车

					}
				});
			}// run
		}, 0, timestamp);
	}
	
	//初始化列车
	public void initTrain(){
		
		taskList = baseData.getTaskList();
		
		if(taskList == null || taskList.size() == 0){
			//System.out.println("taskList==null || taskList.size()==0");
			return;		
		}
		
		int len = taskList.size();
		for (int i = 0; i < len; i++) {
			TaskPlan task = taskList.get(i);
			
			String type = task.getType();
			String trainName = task.getTrainName();
			String trackName = task.getTrackLine();
			
			if(trackName.equalsIgnoreCase("1G")){
				trackName = "I";
			}else if(trackName.equalsIgnoreCase("2G")){
				trackName = "II";
			}else if(trackName.equalsIgnoreCase("3G")){
				trackName = "3";
			}else if(trackName.equalsIgnoreCase("4G")){
				trackName = "4";
			}else if(trackName.equalsIgnoreCase("6G")){
				trackName = "6";
			}else{
				trackName = "";
				System.out.println("--初始化车次到股道 = " + trackName + " 找不到！");
				continue;
			}
			
			if(type.equalsIgnoreCase("start")){
				if (task.getTime().equalsIgnoreCase(DateUtil.getNowTimeBefore())) {
					initTrainByTrackName(trackName, trainName, "init");
				}	
			}else if(type.equalsIgnoreCase("end")){
				if (task.getTime().equalsIgnoreCase(DateUtil.getNowTimeAfter())) {
					initTrainByTrackName(trackName, trainName, "cancel");
				}
			}		
		}
		
	}
	
	//初始化车次到相应的股道
	public void initTrainByTrackName(String trackName, String trainName, String type){
		
		Figure fig = stationModel.getFigureObjectByName(trackName);
		if (fig instanceof TrackLine) {
			if(type.equalsIgnoreCase("init")){
				((TrackLine) fig).setTrainName(trainName);
				((TrackLine) fig).setColorStatus(ParamFlag.trackline_red);
				((TrackLine) fig).getTrainLabel().setText(trainName);
				((TrackLine) fig).getTrainLabel().setVisible(true);
			}else if(type.equalsIgnoreCase("cancel")){
				((TrackLine) fig).setTrainName("");
				((TrackLine) fig).setColorStatus(ParamFlag.trackline_bule);
				((TrackLine) fig).getTrainLabel().setText("");
				((TrackLine) fig).getTrainLabel().setVisible(false);
			}
		}		
		
	}
	
	

}
