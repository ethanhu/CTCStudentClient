package ctc.tdcs.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.geometry.Point;

import ctc.constant.Constants;
import ctc.pojobean.TaskPlan;
import ctc.pojobean.TaskPlanList;
import ctc.tdcs.Util.UtilForStatics;
import ctc.util.JsonUtil;

/**
 * 将plan信息转换为任务信息
 * 
 * @author Happy
 * @date 2010-7-10
 */
public class TrainPlanToTaskPlan {

	private static TrainPlanToTaskPlan trainPlanToTaskPlan = null;

	public static TrainPlanToTaskPlan getInstance() {
		if (trainPlanToTaskPlan == null) {
			trainPlanToTaskPlan = new TrainPlanToTaskPlan();
		}
		return trainPlanToTaskPlan;
	}

	public TaskPlan taskPlanArrive; // 基本任务信息
	public TaskPlan taskPlanLeave; // 基本任务信息
	public List<TaskPlan> planList = new ArrayList<TaskPlan>(); // 存储所有车站所有车次的list
	public List<TaskPlan> planList1 = new ArrayList<TaskPlan>(); // 存储车站1所有车次的list
	public List<TaskPlan> planList2 = new ArrayList<TaskPlan>(); // 存储车站2所有车次的list
	public List<TaskPlan> planList3 = new ArrayList<TaskPlan>(); // 存储车站3所有车次的list
	public List<TaskPlan> planList4 = new ArrayList<TaskPlan>(); // 存储车站4所有车次的list
	public List<TaskPlan> planList5 = new ArrayList<TaskPlan>(); // 存储车站5所有车次的list
	public List<List<TaskPlan>> allTaskPlan = new ArrayList<List<TaskPlan>>();
	
	public TaskPlan taskPlanArriveAndLeave; // 基本任务信息
	public TaskPlanList taskPlanList; // 每个车站的任务信息
	public List<TaskPlanList> allTaskPlanList = new ArrayList<TaskPlanList>(); // 所有车站的信息

	public String[] track = new String[] { "1G", "2G", "3G", "4G", "6G" };
	public String[] taskType = new String[] { "接车", "发车", "通过" };
	public String[] xla = new String[] { "S1LA", "S2LA", "S3LA", "S4LA", "S6LA" }; // 下行接车
	public String[] xlfa = new String[] { "X1LA", "X2LA", "X3LA", "X4LA", "X6LA" }; // 下行发车
	public String[] sla = new String[] { "X1LA", "X2LA", "X3LA", "X4LA", "X6LA" }; // 上行接车
	public String[] slfa = new String[] { "S1LA", "S2LA", "S3LA", "S4LA", "S6LA" }; // 上行发车

	String arriveTime;
	String leaveTime;
	int trainDirection;
	String direction;
	String trainTask;
	String trackNum;
	int indexOfStringArray = 0;

	// 获得所有车次的信息
	public List<List<TaskPlan>> getAllTrainPlanTask() {
	
		planList.clear(); //每次先清空list	
		planList1.clear(); //每次先清空list	
		planList2.clear(); //每次先清空list	
		planList3.clear(); //每次先清空list	
		planList4.clear(); //每次先清空list	
		planList5.clear(); //每次先清空list		
		
		allTaskPlan.clear();
		allTaskPlan.add(planList1);
		allTaskPlan.add(planList2);
		allTaskPlan.add(planList3);
		allTaskPlan.add(planList4);
		allTaskPlan.add(planList5);
		allTaskPlan.add(planList);
		
		UtilForStatics utilForStatics = new UtilForStatics();
		Map<TrainStation, PlanForStatics> stationTrainsMap = new HashMap<TrainStation, PlanForStatics>();
		String station = null; // 所有车站
		stationTrainsMap = utilForStatics.getStationTrainsMapByStationName(station);
		int stationNum = 0;

		List<String> sortedStationNameList = new ArrayList<String>();
		sortedStationNameList = BaseParam.getSortedStationNameList();

		if (station == null) {
			stationNum = sortedStationNameList.size();
		}

		for (int index = 0; index < stationNum; index++) {
			String stationName = station;

			if (station == null)
				stationName = sortedStationNameList.get(index);

			indexOfStringArray = 0;

			// 显示具体车次内容
			if ((stationTrainsMap != null) && (!stationTrainsMap.isEmpty())) { // if

				PlanForStatics value;

				for (TrainStation key : stationTrainsMap.keySet()) {// for

					if ((key.getStationName()).equalsIgnoreCase(stationName)) {
						value = stationTrainsMap.get(key);

						// 不显示已经删除的车次信息
						if (((value.getArriveTrainType() == Constants.TDCS_TRAIN_TYPE_DELETE) && (value.getLeaveTrainType() == Constants.TDCS_TRAIN_TYPE_DELETE))
								|| ((stationName.equalsIgnoreCase(UtilForStatics.getFirstStation())) && (value.getLeaveTrainType() == Constants.TDCS_TRAIN_TYPE_DELETE))
								|| ((stationName.equalsIgnoreCase(UtilForStatics.getLastStation())) && (value.getArriveTrainType() == Constants.TDCS_TRAIN_TYPE_DELETE))) {

							continue;
						}

						arriveTime = value.getArriveTime();
						leaveTime = value.getLeaveTime();
						trainDirection = value.getTrainDirection();

						if (leaveTime != null && arriveTime != null) {

							if (trainDirection == 0) {

								direction = "上行";

								if (arriveTime == leaveTime) { // 通过

									taskPlanArriveAndLeave = new TaskPlan();
									taskPlanArriveAndLeave.setStationName(stationName); // 站名
									taskPlanArriveAndLeave.setTrainName(value.getTrainName()); // 车次
									taskPlanArriveAndLeave.setTrainDirection(direction); // 方向
									taskPlanArriveAndLeave.setTaskType(taskType[2]);
									taskPlanArriveAndLeave.setTime(arriveTime); // 时间
									taskPlanArriveAndLeave.setTrackLine(track[indexOfStringArray % 5]); // 股道
									taskPlanArriveAndLeave.setStartButton("SLA"); // 起始按钮
									taskPlanArriveAndLeave.setEndButton("XLFA"); // 终端按钮
									taskPlanArriveAndLeave.setType("middle"); // 中间
																		
									if(allTaskPlan != null && allTaskPlan.size() == 6){
										allTaskPlan.get(index).add(taskPlanArriveAndLeave);
									}else{
										System.out.print("TDCS:TrainPlanToTaskPlan:getAllTrainPlanTask():  allTaskPlan != null && allTaskPlan.size() == 5");
									}

								} else {

									// 接车
									taskPlanArrive = new TaskPlan();
									taskPlanArrive.setStationName(stationName); // 站名
									taskPlanArrive.setTrainName(value.getTrainName()); // 车次
									taskPlanArrive.setTrainDirection(direction); // 方向
									taskPlanArrive.setTaskType(taskType[0]);
									taskPlanArrive.setTime(arriveTime); // 时间
									taskPlanArrive.setTrackLine(track[indexOfStringArray % 5]); // 股道
									taskPlanArrive.setStartButton("SLA"); // 起始按钮
									taskPlanArrive.setEndButton(sla[indexOfStringArray % 5]); // 终端按钮
									taskPlanArrive.setType("middle"); // 中间
																		
									if(allTaskPlan != null && allTaskPlan.size() == 6){
										allTaskPlan.get(5).add(taskPlanArrive);
										allTaskPlan.get(index).add(taskPlanArrive);
									}else{
										System.out.print("TDCS:TrainPlanToTaskPlan:getAllTrainPlanTask():  allTaskPlan != null && allTaskPlan.size() == 5");
									}

									// 发车
									taskPlanLeave = new TaskPlan();
									taskPlanLeave.setStationName(stationName); // 站名
									taskPlanLeave.setTrainName(value.getTrainName()); // 车次
									taskPlanLeave.setTrainDirection(direction); // 方向
									taskPlanLeave.setTaskType(taskType[1]);
									taskPlanLeave.setTime(leaveTime); // 时间
									taskPlanLeave.setTrackLine(track[indexOfStringArray % 5]); // 股道
									taskPlanLeave.setStartButton(slfa[indexOfStringArray % 5]); // 起始按钮
									taskPlanLeave.setEndButton("XLFA"); // 终端按钮
									taskPlanLeave.setType("middle"); // 中间
									
									if(allTaskPlan != null && allTaskPlan.size() == 6){
										allTaskPlan.get(5).add(taskPlanLeave);
										allTaskPlan.get(index).add(taskPlanLeave);
									}else{
										System.out.print("TDCS:TrainPlanToTaskPlan:getAllTrainPlanTask():  allTaskPlan != null && allTaskPlan.size() == 5");
									}

								}

							} else if (trainDirection == 1) {

								direction = "下行";

								if (arriveTime == leaveTime) { // 通过

									taskPlanArriveAndLeave = new TaskPlan();
									taskPlanArriveAndLeave.setStationName(stationName); // 站名
									taskPlanArriveAndLeave.setTrainName(value.getTrainName()); // 车次
									taskPlanArriveAndLeave.setTrainDirection(direction); // 方向
									taskPlanArriveAndLeave.setTaskType(taskType[2]);
									taskPlanArriveAndLeave.setTime(arriveTime); // 时间
									taskPlanArriveAndLeave.setTrackLine(track[indexOfStringArray % 5]); // 股道
									taskPlanArriveAndLeave.setStartButton("XLA"); // 起始按钮
									taskPlanArriveAndLeave.setEndButton("SLFA"); // 终端按钮
									taskPlanArriveAndLeave.setType("middle"); // 中间
									
									if(allTaskPlan != null && allTaskPlan.size() == 6){
										allTaskPlan.get(5).add(taskPlanArriveAndLeave);
										allTaskPlan.get(index).add(taskPlanArriveAndLeave);
									}else{
										System.out.print("TDCS:TrainPlanToTaskPlan:getAllTrainPlanTask():  allTaskPlan != null && allTaskPlan.size() == 5");
									}

								} else {

									// 接车
									taskPlanArrive = new TaskPlan();
									taskPlanArrive.setStationName(stationName); // 站名
									taskPlanArrive.setTrainName(value.getTrainName()); // 车次
									taskPlanArrive.setTrainDirection(direction); // 方向
									taskPlanArrive.setTaskType(taskType[0]);
									taskPlanArrive.setTime(arriveTime); // 时间
									taskPlanArrive.setTrackLine(track[indexOfStringArray % 5]); // 股道
									taskPlanArrive.setStartButton("XLA"); // 起始按钮
									taskPlanArrive.setEndButton(xla[indexOfStringArray % 5]); // 终端按钮
									taskPlanArrive.setType("middle"); // 中间
									
									if(allTaskPlan != null && allTaskPlan.size() == 6){
										allTaskPlan.get(5).add(taskPlanArrive);
										allTaskPlan.get(index).add(taskPlanArrive);
									}else{
										System.out.print("TDCS:TrainPlanToTaskPlan:getAllTrainPlanTask():  allTaskPlan != null && allTaskPlan.size() == 5");
									}
									// 发车
									taskPlanLeave = new TaskPlan();
									taskPlanLeave.setStationName(stationName); // 站名
									taskPlanLeave.setTrainName(value.getTrainName()); // 车次
									taskPlanLeave.setTrainDirection(direction); // 方向
									taskPlanLeave.setTaskType(taskType[1]);
									taskPlanLeave.setTime(leaveTime); // 时间
									taskPlanLeave.setTrackLine(track[indexOfStringArray % 5]); // 股道
									taskPlanLeave.setStartButton(xlfa[indexOfStringArray % 5]); // 起始按钮
									taskPlanLeave.setEndButton("SLFA"); // 终端按钮
									taskPlanLeave.setType("middle"); // 中间
									
									if(allTaskPlan != null && allTaskPlan.size() == 6){
										allTaskPlan.get(5).add(taskPlanLeave);
										allTaskPlan.get(index).add(taskPlanLeave);
									}else{
										System.out.print("TDCS:TrainPlanToTaskPlan:getAllTrainPlanTask():  allTaskPlan != null && allTaskPlan.size() == 5");
									}
								}
							}

						} else if (arriveTime == null && leaveTime != null) { // 首站
							
							// 发车
							if (trainDirection == 0) {

								direction = "上行";

								// 发车
								taskPlanLeave = new TaskPlan();
								taskPlanLeave.setStationName(stationName); // 站名
								taskPlanLeave.setTrainName(value.getTrainName()); // 车次
								taskPlanLeave.setTrainDirection(direction); // 方向
								taskPlanLeave.setTaskType(taskType[1]);
								taskPlanLeave.setTime(leaveTime); // 时间
								taskPlanLeave.setTrackLine(track[indexOfStringArray % 5]); // 股道
								taskPlanLeave.setStartButton(slfa[indexOfStringArray % 5]); // 起始按钮
								taskPlanLeave.setEndButton("XLFA"); // 终端按钮
								taskPlanLeave.setType("start"); //起始站
								
								if(allTaskPlan != null && allTaskPlan.size() == 6){
									allTaskPlan.get(5).add(taskPlanLeave);
									allTaskPlan.get(index).add(taskPlanLeave);
								}else{
									System.out.print("TDCS:TrainPlanToTaskPlan:getAllTrainPlanTask():  allTaskPlan != null && allTaskPlan.size() == 5");
								}

							} else if (trainDirection == 1) {

								direction = "下行";

								// 发车
								taskPlanLeave = new TaskPlan();
								taskPlanLeave.setStationName(stationName); // 站名
								taskPlanLeave.setTrainName(value.getTrainName()); // 车次
								taskPlanLeave.setTrainDirection(direction); // 方向
								taskPlanLeave.setTaskType(taskType[1]);
								taskPlanLeave.setTime(leaveTime); // 时间
								taskPlanLeave.setTrackLine(track[indexOfStringArray % 5]); // 股道
								taskPlanLeave.setStartButton(xlfa[indexOfStringArray % 5]); // 起始按钮
								taskPlanLeave.setEndButton("SLFA"); // 终端按钮
								taskPlanLeave.setType("start"); //起始站
								
								if(allTaskPlan != null && allTaskPlan.size() == 6){
									allTaskPlan.get(5).add(taskPlanLeave);
									allTaskPlan.get(index).add(taskPlanLeave);
								}else{
									System.out.print("TDCS:TrainPlanToTaskPlan:getAllTrainPlanTask():  allTaskPlan != null && allTaskPlan.size() == 5");
								}

							}

						} else if (leaveTime == null && arriveTime != null) { // 末站接车

							if (trainDirection == 0) {

								direction = "上行";

								// 接车
								taskPlanArrive = new TaskPlan();
								taskPlanArrive.setStationName(stationName); // 站名
								taskPlanArrive.setTrainName(value.getTrainName()); // 车次
								taskPlanArrive.setTrainDirection(direction); // 方向
								taskPlanArrive.setTaskType(taskType[0]);
								taskPlanArrive.setTime(arriveTime); // 时间
								taskPlanArrive.setTrackLine(track[indexOfStringArray % 5]); // 股道
								taskPlanArrive.setStartButton("SLA"); // 起始按钮
								taskPlanArrive.setEndButton(sla[indexOfStringArray % 5]); // 终端按钮
								taskPlanArrive.setType("end"); //末站
								
								if(allTaskPlan != null && allTaskPlan.size() == 6){
									allTaskPlan.get(5).add(taskPlanArrive);
									allTaskPlan.get(index).add(taskPlanArrive);
								}else{
									System.out.print("TDCS:TrainPlanToTaskPlan:getAllTrainPlanTask():  allTaskPlan != null && allTaskPlan.size() == 5");
								}

							} else if (trainDirection == 1) {

								direction = "下行";

								// 接车
								taskPlanArrive = new TaskPlan();
								taskPlanArrive.setStationName(stationName); // 站名
								taskPlanArrive.setTrainName(value.getTrainName()); // 车次
								taskPlanArrive.setTrainDirection(direction); // 方向
								taskPlanArrive.setTaskType(taskType[0]);
								taskPlanArrive.setTime(arriveTime); // 时间
								taskPlanArrive.setTrackLine(track[indexOfStringArray % 5]); // 股道
								taskPlanArrive.setStartButton("XLA"); // 起始按钮
								taskPlanArrive.setEndButton(xla[indexOfStringArray % 5]); // 终端按钮
								taskPlanArrive.setType("end"); //末站
								
								if(allTaskPlan != null && allTaskPlan.size() == 6){
									allTaskPlan.get(5).add(taskPlanArrive);
									allTaskPlan.get(index).add(taskPlanArrive);
								}else{
									System.out.print("TDCS:TrainPlanToTaskPlan:getAllTrainPlanTask():  allTaskPlan != null && allTaskPlan.size() == 5");
								}
							}

						}
						indexOfStringArray++;
					}
				}// for
			}// if			
		}// for
		
		return allTaskPlan;
		
	}
}
