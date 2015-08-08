package ctc.tdcs.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ctc.constant.Constants;
import ctc.pojobean.*;
import ctc.tdcs.tdcsdbserver.DatabaseServer;
import ctc.tdcs.data.BaseParam;
import ctc.tdcs.data.PlanForStatics;
import ctc.tdcs.data.TrainPlan;
import ctc.tdcs.data.TrainStation;
import ctc.tdcs.data.TrainStationTime;
import ctc.tdcs.elements.LineAnchorFigure;
import ctc.tdcs.elements.LineFigure;
import ctc.tdcs.TdcsEnvInit;

public class UtilForStatics {

	public UtilForStatics(boolean saveFlag) {
		this.saveFlag = saveFlag;
	}

	public UtilForStatics() {
		init();
	}

	private static boolean saveFlag = false;
	private static int stationNum; // 车站数量
	private static String firstStation; // 首站
	private static String lastStation; // 终点站

	// 保存排号序的车站名 同下行车次的首站到终点站的顺序一致
	private static List<String> sortedStationNameList = new ArrayList<String>();

	// 用于记录学员对所有车次的实际操作 供更新数据库用
	private static Map<TrainStationTime, TrainPlan> scheduleTrainMap = new HashMap<TrainStationTime, TrainPlan>();

	public static boolean isSaveFlag() {
		return saveFlag;
	}

	public static void setSaveFlag(boolean saveFlag) {
		UtilForStatics.saveFlag = saveFlag;
	}

	private void init() {
		sortedStationNameList = BaseParam.getSortedStationNameList();
		if (!sortedStationNameList.isEmpty()) {
			stationNum = sortedStationNameList.size();
		}
	}

	public static String getFirstStation() {
		return firstStation;
	}

	public static String getLastStation() {
		return lastStation;
	}

	public static void clearScheduleTrainMap() {
		if (!scheduleTrainMap.isEmpty())// 如果此映射不包含键-值映射关系，则返回 true
			scheduleTrainMap.clear();// 从此映射中移除所有映射关系
		if (!sortedStationNameList.isEmpty())
			sortedStationNameList.clear();
	}

	// 供databaseserver调用,处理对象是直接从数据库获取的内容
	public void putScheduleTrainMap(Plan plan) {

		if (plan == null)
			return;
		if ((sortedStationNameList == null) || (sortedStationNameList.isEmpty())) {
			init();
			if (sortedStationNameList.isEmpty())
				return;
		}

		String stationName = plan.getStation_name();// 当前站
		String trainName = plan.getTrain_name();// 车次信息
		String arriveTime = plan.getPlan_arrivestationtime();// 到站时间
																// 时间格式同数据库中的格式：
																// 00：00：00
		String leaveTime = plan.getPlan_leavestationtime();// 离站时间
		String prestationName = plan.getPrestation_name(); // 前站
		int trainDirection = BaseParam.getTrainDirectionByTrainName(trainName);// 指定车次的方向

		int timeType = Constants.TDCS_TIME_TYPE_ARRIVEAL;// 车次的时间类别
															// 6291457/Constants
															// .
															// TDCS_TIME_TYPE_LEAVE

		// 操作类别设置
		String zeroTimeStr = "00:00:00";
		int arriveTrainType = Constants.TDCS_TRAIN_TYPE_NO;
		int leaveTrainType = Constants.TDCS_TRAIN_TYPE_NO;

		if (arriveTime.equalsIgnoreCase(zeroTimeStr)) {
			arriveTrainType = Constants.TDCS_TRAIN_TYPE_DELETE;// 此信息已经删除过
		}
		if (leaveTime.equalsIgnoreCase(zeroTimeStr)) {
			leaveTrainType = Constants.TDCS_TRAIN_TYPE_DELETE;// 此信息已经删除过
		}

		// 目前暂时设为：false
		// ?????????????????????????????????????????????????????????
		// ?????????????
		// ?????????????????????????????????????????????????????????
		boolean operatorFlag = false;// 可操作标记 : false可以操作, true不能再操作:
										// 根据车次的运行时间与当前时间的比对决定

		if (trainDirection == 1)// 下行
		{
			firstStation = sortedStationNameList.get(0);
			lastStation = sortedStationNameList.get(stationNum - 1);
		} else if (trainDirection == 0)// 上行
		{
			lastStation = sortedStationNameList.get(0);
			firstStation = sortedStationNameList.get(stationNum - 1);
		}
		TrainStationTime key;
		TrainPlan value;

		if (prestationName.equalsIgnoreCase(stationName))// 首站
		{// 对于首站，只处理离开时间
			timeType = Constants.TDCS_TIME_TYPE_LEAVE;// 离开
			key = new TrainStationTime(stationName, trainName, timeType);
			value = new TrainPlan(prestationName, stationName, trainName, leaveTime, trainDirection, operatorFlag, leaveTrainType);
			UtilForStatics.scheduleTrainMap.put(key, value);// 在此映射中关联指定值与指定键
		} else// 非首站
		{
			if (lastStation.equalsIgnoreCase(stationName))// 终点站
			{// 对于终点站，只处理到达时间
				timeType = Constants.TDCS_TIME_TYPE_ARRIVEAL;// 到站
				key = new TrainStationTime(stationName, trainName, timeType);
				value = new TrainPlan(prestationName, stationName, trainName, arriveTime, trainDirection, operatorFlag, arriveTrainType);
				UtilForStatics.scheduleTrainMap.put(key, value);// 在此映射中关联指定值与指定键
			} else// 中间站
			{
				timeType = Constants.TDCS_TIME_TYPE_ARRIVEAL;// 到站
				key = new TrainStationTime(stationName, trainName, timeType);
				value = new TrainPlan(prestationName, stationName, trainName, arriveTime, trainDirection, operatorFlag, arriveTrainType);
				UtilForStatics.scheduleTrainMap.put(key, value);// 在此映射中关联指定值与指定键

				timeType = Constants.TDCS_TIME_TYPE_LEAVE;// 离开
				key = new TrainStationTime(stationName, trainName, timeType);
				value = new TrainPlan(prestationName, stationName, trainName, leaveTime, trainDirection, operatorFlag, leaveTrainType);
				UtilForStatics.scheduleTrainMap.put(key, value);// 在此映射中关联指定值与指定键
			}
		}// 非首站
	}

	////////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////
	/** 供UtilForMouseAction_x调用，当用户添加新车次时，此方法被调用 */
	public void appendScheduleTrainMap(LineFigure newTrainLine) {

		if (newTrainLine == null)// 一般 不会出现
			return;

		boolean operatorFlag = false;// 默认都是可以操作的
		String districtName = BaseParam.getCurrentDistrictName();// 区段名称
		String operator = TdcsEnvInit.getUserName();// 操作者名称

		int trainType = Constants.TDCS_TRAIN_TYPE_NEW;// 新添加的车次信息

		LineAnchorFigure sourceAnchor = new LineAnchorFigure();
		LineAnchorFigure targetAnchor = new LineAnchorFigure();
		sourceAnchor = newTrainLine.getSource();
		targetAnchor = newTrainLine.getTarget();

		String trainName = newTrainLine.getTrainName();// 车次信息
		int trainDirection = newTrainLine.getTrainDirection();// 上行0，下行1
		int timeType = Constants.TDCS_TIME_TYPE_ARRIVEAL;

		String prestationName = "";// 前站站名
		String stationName = "";// 本站
		String time = "";// 时间格式同数据库中的格式： 00：00：00

		TrainStationTime key = null;
		TrainPlan value = null;

		// 对于用户在两个车站之间绘制的一条线,系统在scheduleTrainMap中保存两条信息,一条为起点,一条为终点的信息
		// 车次的起点
		if (sourceAnchor != null) {
			stationName = sourceAnchor.getStationName();
			prestationName = getPreStationName(stationName, trainDirection);
			timeType = sourceAnchor.getTimeType();// 时间类别

			key = new TrainStationTime(stationName, trainName, timeType);

			if (scheduleTrainMap.containsKey(key))// 正常情况下，不会出现
				return;

			time = xyTimeToStr(sourceAnchor.getCurrentTime());

			value = new TrainPlan(prestationName, stationName, trainName, time, trainDirection, operatorFlag, trainType, districtName, operator);

			// System.out.println("K01:"+trainName+"/K02:"+
			// stationName+"/K03:"+timeType);
			// System.out.println("01:"+prestationName+"/02:"+
			// stationName+"/03:"+trainType+"/03:"+time);

			UtilForStatics.scheduleTrainMap.put(key, value);// 在此映射中关联指定值与指定键

			/** 生成新的车次,用于经服务器转发该车次信息到相应的组内所有用户终端 */
			TDCSPlan tdcsplan = new TDCSPlan(time, time, districtName, prestationName, stationName, trainName, timeType);
			BaseParam.appendTdcsTrainList(tdcsplan);

			setSaveFlag(true);
		}
		// 车次的终点
		if (targetAnchor != null) {
			stationName = targetAnchor.getStationName();// 本站
			prestationName = getPreStationName(stationName, trainDirection);// 前站
			timeType = targetAnchor.getTimeType();// 时间类别

			key = new TrainStationTime(stationName, trainName, timeType);

			if (scheduleTrainMap.containsKey(key))// 正常情况下，不会出现
				return;

			time = xyTimeToStr(targetAnchor.getCurrentTime());
			value = new TrainPlan(prestationName, stationName, trainName, time, trainDirection, operatorFlag, trainType, districtName, operator);
			// System.out.println("K001:"+trainName+"/K002:"+
			// stationName+"/K003:"+timeType);
			// System.out.println("001:"+prestationName+"/002:"+
			// stationName+"/003:"+trainType+"/003:"+time);
			// System.out.println("*****************************");

			/** 生成新的车次,用于经服务器转发该车次信息到相应的组内所有用户终端 */
			TDCSPlan tdcsplan = new TDCSPlan(time, time, districtName, prestationName, stationName, trainName, timeType);
			BaseParam.appendTdcsTrainList(tdcsplan);

			UtilForStatics.scheduleTrainMap.put(key, value);// 在此映射中关联指定值与指定键

			setSaveFlag(true);
		}

		// System.out.println("添加:到站:前站:"+prestationName+"/当前站:"+stationName+
		// "/时间类别:"+timeType+"/数量:"+scheduleTrainMap.size());

	}

	// 获取指定站的前站
	private String getPreStationName(String stationName, int trainDirection) {
		String preStationName = stationName;

		if (trainDirection == 1)// 下行
		{
			for (int i = 0; i < stationNum; i++) {
				String name = sortedStationNameList.get(i);
				if ((name.equalsIgnoreCase(stationName)) && (i > 0)) {
					preStationName = sortedStationNameList.get(i - 1);// 前站
					break;
				}
			}
		} else if (trainDirection == 0)// 上行
		{
			for (int i = stationNum - 1; i >= 0; i--) {
				String name = sortedStationNameList.get(i);
				if ((name.equalsIgnoreCase(stationName)) && (i < (stationNum - 1))) {
					preStationName = sortedStationNameList.get(i + 1);// 前站
					break;
				}
			}
		}
		return preStationName;
	}

	// 时间格式转换
	private String xyTimeToStr(int x) {
		String time = "";
		int curX = UtilForTimeRectangle.xToTime(x);// 将屏幕时间转换为实际时间如屏幕值为：117
													// ,转换后为：20分钟
		time = UtilForTime2X.XToTime(curX);// 将分钟表示的时间值如20转换为数据库中的格式：即00:20:00
		return time;
	}

	////////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////
	/** 供UtilForMouseAction_x调用，当用户删除车次时，此方法被调用 */
	public void deleteScheduleTrainMap(LineFigure newTrainLine) {

		if (newTrainLine == null)// 一般 不会出现
			return;

		LineAnchorFigure sourceAnchor = new LineAnchorFigure();
		LineAnchorFigure targetAnchor = new LineAnchorFigure();
		sourceAnchor = newTrainLine.getSource();
		targetAnchor = newTrainLine.getTarget();

		String trainName = newTrainLine.getTrainName();// 车次信息
		int timeType = Constants.TDCS_TIME_TYPE_ARRIVEAL; // 时间类别

		String preStationName = "";// 前站
		String stationName = "";// 本站
		TrainStationTime key = null;

		// 车次的起始点
		if (sourceAnchor != null) {
			preStationName = sourceAnchor.getStationName();// 前站
			timeType = sourceAnchor.getTimeType();// 时间类别
			key = new TrainStationTime(preStationName, trainName, timeType);
			deleteScheduleTrainMap(key);
		}
		// 车次的起始点
		if (targetAnchor != null) {
			stationName = targetAnchor.getStationName();// 本站
			timeType = targetAnchor.getTimeType();// 时间类别
			key = new TrainStationTime(stationName, trainName, timeType);
			deleteScheduleTrainMap(key);
		}

		/** 生成要删除的车次保存,用于经服务器转发该车次信息到相应的组内所有用户终端 */
		TDCSPlan tdcsplan = new TDCSPlan(preStationName, stationName, trainName);
		BaseParam.appendTdcsTrainList(tdcsplan);
	}

	private void deleteScheduleTrainMap(TrainStationTime key) {
		TrainPlan value = null;

		// 是否删除，要判断是用户新加的或是从库中获取的，对于新加的直接删除，否则的话，标注为TDCS_TRAIN_TYPE_DELETE库
		if (scheduleTrainMap.containsKey(key)) {// 含有要删除的记录

			value = scheduleTrainMap.get(key);
			int trainType = value.getTrainType();
			//System.out.println("01:"+trainType+"/"+value.getStationName()+"/"+
			// value.getTrainName()+"/"+timeType);

			switch (trainType) {
			case Constants.TDCS_TRAIN_TYPE_NEW:// 对新添加车次, 没有进行任何操作就进行删除操作
			case Constants.TDCS_TRAIN_TYPE_NEW_ADJUST:// 对新添加的线进行调整,但还没有进行保存,
				scheduleTrainMap.remove(key);
				setSaveFlag(true);
				break;
			case Constants.TDCS_TRAIN_TYPE_NEW_SAVE_ADJUST://对新添加,并保存过的线进行调整后又删除
			case Constants.TDCS_TRAIN_TYPE_NEW_SAVE:// 对新添加车次进行删除前，已进行了保存操作
				value.setTrainType(Constants.TDCS_TRAIN_TYPE_NEW_SAVE_DEELETE);
				scheduleTrainMap.put(key, value);
				setSaveFlag(true);
				break;

			// 对从数据库中读来的,进行标记
			case Constants.TDCS_TRAIN_TYPE_NO:// 对原计划车次没有进行任何操作; 车次是从数据库获取的
			case Constants.TDCS_TRAIN_TYPE_ADJUST:// 对原计划车次进行调整后进行删除
				// case Constants.TDCS_TRAIN_TYPE_DELETE://删除原计划车次
				// 设置删除标记
				value.setTrainType(Constants.TDCS_TRAIN_TYPE_DELETE);
				scheduleTrainMap.put(key, value);

				setSaveFlag(true);
				break;
			}
		}
		//System.out.println("001前站:"+prestationName+"/当前站:"+stationName+"/时间类别:"
		// +timeType+"/数量:"+scheduleTrainMap.size());
	}

	////////////////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////
	// 供UtilForMouseAction_x调用，当用户调整车次时，此方法被调用
	public void adjustScheduleTrainMap(LineAnchorFigure newTrainLine, int newX) {

		// 要判断是用户新加的或是从库中获取的，对于新加的直接调整; 否则的话，标注为TDCS_TRAIN_TYPE_ADJUST
		if (newTrainLine == null)// 一般 不会出现
			return;

		int trainType = newTrainLine.getTrainType();// 车次类别，新加的，从数据库读的等
													// 对原计划车次进行调整 ...
		String trainName = newTrainLine.getTrainName();// 车次信息
		String stationName = newTrainLine.getStationName();// 本站
		int timeType = newTrainLine.getTimeType();// 时间类别 到站/离站等
		String time = xyTimeToStr(newX);// 将时间格式转换为数据库表表示的时间格式
		TrainPlan value = null;

		/** 生成要调整的车次,用于经服务器转发该车次信息到相应的组内所有用户终端 */
		TDCSPlan tdcsplan = new TDCSPlan(stationName, trainName, timeType, time);
		BaseParam.appendTdcsTrainList(tdcsplan);

		TrainStationTime key = new TrainStationTime(stationName, trainName, timeType);
		if (key != null)// 一般不会出现
			value = scheduleTrainMap.get(key);

		switch (trainType) {
		case Constants.TDCS_TRAIN_TYPE_NEW:// 对新添加的线没有进行任何操作前,进行调整
		case Constants.TDCS_TRAIN_TYPE_NEW_ADJUST:// 对新添加的线没有进行保存操作前,进行调整
			value.setTrainType(Constants.TDCS_TRAIN_TYPE_NEW_ADJUST);
			value.setTime(time);
			break;
		case Constants.TDCS_TRAIN_TYPE_NEW_SAVE:// 对新添加已保存过的车次进行调整
			value.setTrainType(Constants.TDCS_TRAIN_TYPE_NEW_SAVE_ADJUST);//对新添加,
																			// 并保存过的线
																			// ,
																			// 进行调整
			value.setTime(time);
			break;
		case Constants.TDCS_TRAIN_TYPE_NO:// 对原计划车次没有进行任何操作
		case Constants.TDCS_TRAIN_TYPE_ADJUST:// 对原计划车次进行调整
			value.setTrainType(Constants.TDCS_TRAIN_TYPE_ADJUST);
			value.setTime(time);
			break;
		}
		if (key != null)// 一般不会出现
			scheduleTrainMap.put(key, value);// 进行更新
	}

	////////////////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////

	public Map<TrainStationTime, TrainPlan> getScheduleTrainMap() {
		return scheduleTrainMap;
	}

	// Map<TrainStation_x,TrainPlan_x> scheduleTrainMap = new
	// HashMap<TrainStation_x,TrainPlan_x>();
	// 获取满足要求的所有车次信息
	public Map<TrainStation, PlanForStatics> getStationTrainsMap(String stationName, String trainName, int directionFlag) {

		// 以<车站,车次>TrainStation为关键字
		Map<TrainStation, PlanForStatics> stationTrainsMap = new HashMap<TrainStation, PlanForStatics>();

		if (scheduleTrainMap == null || scheduleTrainMap.isEmpty())
			return null;

		for (TrainStationTime key : scheduleTrainMap.keySet()) {

			if (((stationName == null) || (key.getStationName()).equalsIgnoreCase(stationName)) && // 指定车站或所有车站null
					((trainName == null) || (key.getTrainName()).equalsIgnoreCase(trainName))// 指定车次或或所有车次null
			) {
				TrainPlan value = scheduleTrainMap.get(key);
				int direction = value.getTrainDirection();

				String station = key.getStationName();

				if ((directionFlag == 2) || (directionFlag == direction))// 上行0，
																			// 下行1
																			// ,
																			// 双向2
				{
					String train = key.getTrainName();// 车次

					int timeType = key.getTypeTime();// 时间类别

					String time = value.getTime();// 时间

					PlanForStatics newValue = new PlanForStatics();

					TrainStation newKey = new TrainStation(station, train);

					if (!stationTrainsMap.isEmpty() && stationTrainsMap.containsKey(newKey))// 不空且含有此关键字
																							// ，
																							// 即已经存在
					{
						newValue = stationTrainsMap.get(newKey);
					} else
						newValue = new PlanForStatics(station, train, direction);

					if (timeType == Constants.TDCS_TIME_TYPE_ARRIVEAL)// 57 到站
					{
						newValue.setArriveTime(time);
						newValue.setArriveTrainType(value.getTrainType());// 对到站车次进行的操作类别
					} else if (timeType == Constants.TDCS_TIME_TYPE_LEAVE) // 58
																			// 离站
					{
						newValue.setLeaveTime(time);
						newValue.setLeaveTrainType(value.getTrainType());// 对离站车次进行的操作类别
					}
					newValue.setDistrictName(value.getDistrictName());

					newValue.setTwoInOneTrainType(value.getTrainType());// 整个车次的操作类别

					String prestationName = getPreStationName(station, direction);
					newValue.setPrestationName(prestationName);

					newValue.setOperator(value.getOperator());

					// System.out.println("数量："+ stationTrainsMap.size());
					stationTrainsMap.put(newKey, newValue);
					// System.out.println("数量22："+ stationTrainsMap.size());
				}
			}
		}
		return stationTrainsMap;
	}

	/**
	 * 根据stationName获得计划信息，如果stationName==null，则是获取所有stationName的计划信息
	 * @author huenzhao 2010-7-8
	 * @param stationName
	 * @return
	 */
	public Map<TrainStation, PlanForStatics> getStationTrainsMapByStationName(String stationName) {

		// 以<车站,车次>TrainStation为关键字
		Map<TrainStation, PlanForStatics> stationTrainsMap = new HashMap<TrainStation, PlanForStatics>();

		if (scheduleTrainMap == null || scheduleTrainMap.isEmpty())
			return null;

		for (TrainStationTime key : scheduleTrainMap.keySet()) {

			if ((stationName == null) || (key.getStationName()).equalsIgnoreCase(stationName)) {
				TrainPlan value = scheduleTrainMap.get(key);
				int direction = value.getTrainDirection();

				String station = key.getStationName();

				String train = key.getTrainName();// 车次

				int timeType = key.getTypeTime();// 时间类别

				String time = value.getTime();// 时间

				PlanForStatics newValue = new PlanForStatics();

				TrainStation newKey = new TrainStation(station, train);

				if (!stationTrainsMap.isEmpty() && stationTrainsMap.containsKey(newKey))// 不空且含有此关键字， 即已经存在
				{
					newValue = stationTrainsMap.get(newKey);
				} else
					newValue = new PlanForStatics(station, train, direction);

				if (timeType == Constants.TDCS_TIME_TYPE_ARRIVEAL)// 57 到站
				{
					newValue.setArriveTime(time);
					newValue.setArriveTrainType(value.getTrainType());// 对到站车次进行的操作类别
				} else if (timeType == Constants.TDCS_TIME_TYPE_LEAVE) // 58 离站
				{
					newValue.setLeaveTime(time);
					newValue.setLeaveTrainType(value.getTrainType());// 对离站车次进行的操作类别
				}
				newValue.setDistrictName(value.getDistrictName());

				newValue.setTwoInOneTrainType(value.getTrainType());// 整个车次的操作类别

				String prestationName = getPreStationName(station, direction);
				newValue.setPrestationName(prestationName);

				newValue.setOperator(value.getOperator());

				// System.out.println("数量："+ stationTrainsMap.size());
				stationTrainsMap.put(newKey, newValue);
				// System.out.println("数量22："+ stationTrainsMap.size());

			}
		}
		return stationTrainsMap;
	}

	// 对已经保存过的新添加车次进行更新，以便能在保存后又删除此车次时，
	private void updatescheduleTrainMap(String trainName, int trainType) {
		if (scheduleTrainMap == null || scheduleTrainMap.isEmpty())
			return;

		for (TrainStationTime key : scheduleTrainMap.keySet()) {
			if (trainName.equalsIgnoreCase(key.getTrainName())) {
				TrainPlan value = scheduleTrainMap.get(key);
				value.setTrainType(trainType);
				scheduleTrainMap.put(key, value);// 进行更新
			}
		}
	}

	// 对从数据库中获取的车次信息进行判断 scheduleTrainMap对于每一车站 到站 和离站的车次状态都进行保存
	// 返回数字0，表示此车次已经全部删除
	private int deleteAllOrNot(String trainName, int trainType) {
		int number = 0;
		if (!scheduleTrainMap.isEmpty()) {
			for (TrainStationTime key : scheduleTrainMap.keySet()) {
				TrainPlan value = scheduleTrainMap.get(key);
				if ((value.getTrainName()).equalsIgnoreCase(trainName) && (value.getTrainType() != trainType)) {
					number++;
				}
			}// for
		}// if
		return number;
	}

	////////////////////////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////
	/*
	 * 1.对于教师的操作：更新Train TrainDistrictRelation Plan， 2. 对于学员的操作：更新Train
	 * TrainDistrictRelation Dispatch 这里的操作 对象是教师，即只进行（1）的处理
	 */
	DatabaseServer databaseServer = new DatabaseServer().getInstance();

	public boolean saveToDB() {
		boolean resultFlag = true;

		if (!isSaveFlag())//
			return resultFlag;

		Map<TrainStation, PlanForStatics> stationTrainsMap = new HashMap<TrainStation, PlanForStatics>();
		stationTrainsMap = getStationTrainsMap(null, null, 2);// 获取指定区域内所有车站的所有车次信息

		if (stationTrainsMap == null || stationTrainsMap.isEmpty())
			return resultFlag;

		// KEY: 车次名称
		// 记录删除的原车次 或已经保存过的并将删除的新车次 对train和TrainDistrictRelation表
		Map<String, PlanForStatics> deleteTrainDistrictMap = new HashMap<String, PlanForStatics>();
		// 记录新加的车次
		Map<String, PlanForStatics> insertTrainDistrictMap = new HashMap<String, PlanForStatics>(); // 对train和TrainDistrictRelation表

		Map<TrainStation, PlanForStatics> insertPlanMap = new HashMap<TrainStation, PlanForStatics>();// 用于更新Plan表
																										// 插入
		Map<TrainStationTime, TrainPlan> updatePlanMap = new HashMap<TrainStationTime, TrainPlan>();// 对Plan表进行删除和更新

		// 用于对plan表进行删除 和更新
		for (TrainStationTime key : scheduleTrainMap.keySet()) {

			if (updatePlanMap.containsKey(key))// 已处理过
				continue;

			String zeroTimeStr = "00:00:00";// 删除标记

			TrainPlan value = scheduleTrainMap.get(key);

			int tranType = value.getTrainType();// 操作类别
			switch (tranType) {
			case Constants.TDCS_TRAIN_TYPE_DELETE:// 对原计划车次进行删除
			case Constants.TDCS_TRAIN_TYPE_NEW_SAVE_DEELETE://对新添加的车次信息进行删除(已保存过
															// )
				value.setTime(zeroTimeStr);// 做删除标记
				updatePlanMap.put(key, value);
				break;

			case Constants.TDCS_TRAIN_TYPE_ADJUST:// 对原计划车次进行调整
			case Constants.TDCS_TRAIN_TYPE_NEW_SAVE_ADJUST:// 对新添加,并保存过的线,进行调整
				updatePlanMap.put(key, value);
				break;
			case Constants.TDCS_TRAIN_TYPE_NEW_ADJUST:// 对新添加的线进行调整,但还没有进行保存,
				// 对本地信息进行更新, 标记为已保存
				String trainName = key.getTrainName();
				updatescheduleTrainMap(trainName, Constants.TDCS_TRAIN_TYPE_NEW_SAVE);

				updatePlanMap.put(key, value);
				break;
			}
		}

		// 对train和TrainDistrictRelation表进行删除和插入
		for (TrainStation key : stationTrainsMap.keySet()) {

			PlanForStatics value = new PlanForStatics();
			String trainName = key.getTrainName();

			if (!(deleteTrainDistrictMap.containsKey(trainName)) && // 此车次还没有处理过
					!(insertPlanMap.containsKey(key))) {
				value = stationTrainsMap.get(key);

				int twoInOneTrainType = value.getTwoInOneTrainType();// 对车次进行操作的类别

				switch (twoInOneTrainType) {
				case Constants.TDCS_TRAIN_TYPE_NEW:// 新添加的车次信息36
					value.setTwoInOneTrainType(Constants.TDCS_TRAIN_TYPE_NEW_SAVE);// 设置已经保存标记

					// 对本地信息进行更新, 标记为已保存
					updatescheduleTrainMap(trainName, Constants.TDCS_TRAIN_TYPE_NEW_SAVE);

					insertTrainDistrictMap.put(trainName, value);// 用于更新train表
					insertPlanMap.put(key, value);// 用于更新Plan表 插入新的
					break;
				case Constants.TDCS_TRAIN_TYPE_NEW_SAVE_DEELETE:// 对已经保存过，
																// 又进行删除的新添加车次
					if (deleteAllOrNot(trainName, Constants.TDCS_TRAIN_TYPE_NEW_SAVE_DEELETE) == 0)// 不存在
																									// ,
																									// 即可以进行删除
					{
						value.setTwoInOneTrainType(Constants.TDCS_TRAIN_TYPE_NEW_SAVE_DEELETE);
						deleteTrainDistrictMap.put(trainName, value);
					}
					break;
				case Constants.TDCS_TRAIN_TYPE_DELETE:// 删除原计划车次35
					// 需要判断是否在所有车站内的该车次都删除了
					if (deleteAllOrNot(trainName, Constants.TDCS_TRAIN_TYPE_DELETE) == 0)// 不存在
																							// ,
																							// 即可以进行删除
					{
						value.setTwoInOneTrainType(Constants.TDCS_TRAIN_TYPE_SAVE);
						deleteTrainDistrictMap.put(trainName, value);
					}
					break;
				}// switch
			}// if
		}// for

		// 都空的话，不进行操作
		if (!(insertTrainDistrictMap.isEmpty() && deleteTrainDistrictMap.isEmpty() && insertPlanMap.isEmpty() && updatePlanMap.isEmpty())) {
			// 写数据到数据库
			resultFlag = databaseServer.batchUpdateFromTDCSForTeacher(insertTrainDistrictMap, deleteTrainDistrictMap, insertPlanMap, updatePlanMap,
					firstStation, lastStation);

			// 批量操作失败 需要对scheduleTrainMap表中部分记录进行恢复
			if (!resultFlag) {
				if (updatePlanMap != null && !updatePlanMap.isEmpty()) {
					for (TrainStationTime key : updatePlanMap.keySet()) {
						TrainPlan info = updatePlanMap.get(key);
						if (info.getTrainType() == Constants.TDCS_TRAIN_TYPE_NEW_SAVE) {
							updatescheduleTrainMap(key.getTrainName(), Constants.TDCS_TRAIN_TYPE_NEW_ADJUST);
						}
					}// for
				}// if

				if (insertTrainDistrictMap != null && !insertTrainDistrictMap.isEmpty()) {
					for (String key : insertTrainDistrictMap.keySet()) {
						PlanForStatics info = insertTrainDistrictMap.get(key);
						if (info.getTwoInOneTrainType() == Constants.TDCS_TRAIN_TYPE_NEW_SAVE) {
							updatescheduleTrainMap(key, Constants.TDCS_TRAIN_TYPE_NEW);
						}
					}// for
				}// if

			}// 批量操作失败

		}// if

		setSaveFlag(false);

		return resultFlag;
	}

}