package ctc.ctc.drawctc.station1.autosystem;

import java.util.ArrayList;
import java.util.Timer;

import org.eclipse.swt.widgets.Display;

import ctc.ctc.drawctc.station1.data.*;
import ctc.ctc.drawctc.station1.layout.StationModel;
import ctc.ctc.drawctc.station1.elements.common.*;
import ctc.sics.util.DateUtil;

/**
 * 自律模式下自动执行
 * 
 * @author 胡恩召
 * 
 */
public class AutoSystem {

	BaseParam baseData = BaseParam.getInstance();
	StationModel stationModel = StationModel.getInstance();
	
	public static ArrayList<Task> taskList = new ArrayList<Task>();
	public static ArrayList<TrainItem> trainList = new ArrayList<TrainItem>();

	final Timer timer = new Timer();
	int timestamp = 1000;

	public String trainName;
	public TrainItem trainItem;

	public static AutoSystem autoSystem = null;

	public static AutoSystem getInstance() {

		if (autoSystem == null) {
			autoSystem = new AutoSystem();
		}
		return autoSystem;
	}


	public void AutoSystemRun(int initVTime, int step) {

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

		if (taskList == null || taskList.size() == 0) {
			System.out.println("taskList==null || taskList.size()==0");
			return;
		}

		int len = taskList.size();
		for (int i = 0; i < len; i++) {

			Task task = taskList.get(i);
			if (task.flag == 0) {
				if (task.getTime().equalsIgnoreCase(DateUtil.getNowTime())) {
					stationModel.processRoad(task.getStartName(), task.getEndName(), ParamFlag.road_green);
					getItemByName(task.getTrainName(), task.getStartName(), task.getEndName());

				}
			} else {
				System.out.println("任务 " + i + " 已经被执行！");
			}

		}

	}

	// 根据名称找出trainItem
	public void getItemByName(String trainName, String startName, String endName) {

		if (trainList == null || trainList.size() == 0) {
			System.out.println("taskTable.getTrainList() == null || taskTable.getTrainList().size() == 0");
			return;
		}

		int len = trainList.size();
		for (int i = 0; i < len; i++) {
			TrainItem trainItem = trainList.get(i);
			if (trainItem.getTrainName().equalsIgnoreCase(trainName)) {
				if (startName.equalsIgnoreCase("XLA") || startName.equalsIgnoreCase("SLA")) {
					System.out.println("XLA——SLA");
					trainItem.getItem().setText(5, DateUtil.getNowTime());
					trainItem.getItem().setText(1, "已执行");
				} else if (endName.equalsIgnoreCase("XLFA") || endName.equalsIgnoreCase("SLFA")) {
					System.out.println("XLA——SLA");
					trainItem.getItem().setText(5, DateUtil.getNowTime());
					trainItem.getItem().setText(1, "已执行");
				}
			}

		}

	}

	public BaseParam getBaseData() {
		return baseData;
	}

	public void setBaseData(BaseParam baseData) {
		this.baseData = baseData;
	}

	public static ArrayList<Task> getTaskList() {
		return taskList;
	}

	public static void setTaskList(ArrayList<Task> taskList) {
		AutoSystem.taskList = taskList;
	}

	public int getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}

	public Timer getTimer() {
		return timer;
	}

	public static ArrayList<TrainItem> getTrainList() {
		return trainList;
	}

	public static void setTrainList(ArrayList<TrainItem> trainList) {
		AutoSystem.trainList = trainList;
	}

	public String getTrainName() {
		return trainName;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	public TrainItem getTrainItem() {
		return trainItem;
	}

	public void setTrainItem(TrainItem trainItem) {
		this.trainItem = trainItem;
	}

	public static AutoSystem getAutoSystem() {
		return autoSystem;
	}

	public static void setAutoSystem(AutoSystem autoSystem) {
		AutoSystem.autoSystem = autoSystem;
	}

}
