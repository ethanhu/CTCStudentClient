package ctc.tdcs.tdcsdbserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import ctc.tdcs.TdcsEnvInit;
import ctc.constant.Constants;
import ctc.pojobean.*;
import ctc.tdcs.Util.UtilForCurrentTimeAxis;
import ctc.tdcs.Util.UtilForMouseAction;
import ctc.tdcs.Util.UtilForStatics;
import ctc.tdcs.Util.UtilForTime2X;
import ctc.tdcs.data.*;
import ctc.tdcs.elements.LineFigure;
import ctc.transport.message.TDCSCommandMessage;
import ctc.transport.message.TeamTdcsRsbMessage;
import ctc.util.ErrorLog;
import ctc.util.JsonUtil;


public class DatabaseServer {

	private static DatabaseServer thisData = null;
	public static DatabaseServer getInstance(){
		if (thisData == null){
			thisData = new DatabaseServer();
		}
		return thisData;
	}
	public DatabaseServer(){}

///////////////////////////////////////////////////////////////////////////////////////
	private static BaseParam baseParam = BaseParam.getInstance();
	private UtilForCurrentTimeAxis utilForCurrentTimeAxis = new UtilForCurrentTimeAxis().getInstance();
	private DatabaseAccessHandler databaseAccessHandler = new DatabaseAccessHandler().getInstance();

	//********************************************************************************************************//
	//////////////////以下代码是供发有关命令到服务器使用//////////////////////////////
	
	public boolean TDCSCommandForRun(String vrTime,String timeStep){//此方法对应于教师TDCS界面中选取命令的处理

		TDCSCommandMessage sMsg = new TDCSCommandMessage();
		
		//AbstractMessage类中定义的字段 通信类别  此数据报所表示的是客户端和服务器代码进行进一步处理的依据 
		sMsg.setCommandType(Constants.TYPE_CLIENT_EXPERIMENT_RUN);//运行
		//sMsg.setCommandType(Constants.TYPE_CLIENT_EXPERIMENT_CLOSE);关闭实验
		//Constants.TYPE_TEACHER_TDCS_START);//启动
		
		//设置当前区段名称
		sMsg.setDistrictName(baseParam.getCurrentDistrictName());
		
		//下面设置默认的工作模式
		sMsg.setRunMode(Constants.RUN_MODE_AUTO);//自律模式
		sMsg.setSubjectName(Constants.EXPERIMENT_MODE_TDSI);//综合实验
		
        //设置虚拟时间
		if( (vrTime == null) || (vrTime.length() == 0))
			timeStep = "00:00";//默认值
		sMsg.setVrTime(vrTime);

		//设置时间步长
		if( (timeStep == null) || (timeStep.length() == 0))
			timeStep = "2";//默认值
		sMsg.setTimeStep(timeStep);

		//向服务器发送开始实验消息
		TDCSCommandMessage rMsg = databaseAccessHandler.TDCSRunCommandToServer(sMsg);
		
		if(rMsg == null)
			return false;
		else//成功
		{
			utilForCurrentTimeAxis.displayTimeOnToolBar(rMsg.getVrTime(),rMsg.getCurrentTime(),rMsg.getTimeStep());
			return true;
		}
	}
	
	//发送改动后的车次信息到服务器
	public void TDCSCommandForTrain()
	{
		TeamTdcsRsbMessage sMsg = new TeamTdcsRsbMessage();
		
		switch(BaseParam.getOperationType()){
		case Constants.TDCS_TRAIN_TYPE_DELETE://删除车次
			sMsg.setCommandType(Constants.TYPE_CLIENT_ZNTDCS_DELETE);
			break;
		case Constants.TDCS_TRAIN_TYPE_NEW://添加新车次
			sMsg.setCommandType(Constants.TYPE_CLIENT_ZNTDCS_ADD);
			break;
		case Constants.TDCS_MENU_TOOL_RECTANGLE_ADJUST://调整车次的发车或到站时间
			sMsg.setCommandType(Constants.TYPE_CLIENT_ZNTDCS_ADJUST);
			break;
		}
		sMsg.setTeamID(new TdcsEnvInit().getTeamID());//组编号
		
		//车次信息
		//List<TDCSPlan> trainPlan = BaseParam.getTdcsTrainList(); //xbm 2010-3-22	
		List<List<TaskPlan>> trainPlanList = TrainPlanToTaskPlan.getInstance().getAllTrainPlanTask();
		
		if(trainPlanList != null && trainPlanList.size() == 6){			
			sMsg.setTrainPlan(JsonUtil.list2json(trainPlanList.get(5)));
			sMsg.setTrainPlan1(JsonUtil.list2json(trainPlanList.get(0)));
			sMsg.setTrainPlan2(JsonUtil.list2json(trainPlanList.get(1)));
			sMsg.setTrainPlan3(JsonUtil.list2json(trainPlanList.get(2)));
			sMsg.setTrainPlan4(JsonUtil.list2json(trainPlanList.get(3)));
			sMsg.setTrainPlan5(JsonUtil.list2json(trainPlanList.get(4)));	
			
			databaseAccessHandler.TDCSCommandForTrainToServer(sMsg);	
		
		}else{
			System.out.println("TDCS:DatabaseServer:TDCSCommandForTrain(): trainPlanList == null && trainPlanList.size() != 6 ");
		}			
	}
	
	public void TDCSCommandForStop(){//关闭实验

		TDCSCommandMessage sMsg = new TDCSCommandMessage();
		
		//AbstractMessage类中定义的字段 通信类别  此数据报所表示的是客户端和服务器代码进行进一步处理的依据 
		sMsg.setCommandType(Constants.TYPE_CLIENT_EXPERIMENT_CLOSE);//关闭实验
		
		//设置当前区段名称
		sMsg.setDistrictName(baseParam.getCurrentDistrictName());
		
		databaseAccessHandler.TDCSCloseCommandToServer(sMsg);
	}

	//////////////////以上代码是供发有关命令到服务器使用//////////////////////////////
	//********************************************************************************************************//
     //////////////////以下代码是写数据到数据库中//////////////////////////////
	
	/*
	  本方法是针对教师的,如果对学生,只需将plan表更改为dispatch表,当然需要增加一些字段  
	 */
	public boolean batchUpdateFromTDCSForTeacher(
							Map<String,PlanForStatics> insertTrainDistrictMap,
							Map<String,PlanForStatics> deleteTrainDistrictMap,
							Map<TrainStation,PlanForStatics> insertPlanMap, //plan表插入
							Map<TrainStationTime,TrainPlan> updatePlanMap,//plan表更新
							String firstStation,String lastStation)
	{
		int insertLen = insertTrainDistrictMap.size();
		int deleteLen = deleteTrainDistrictMap.size();
		int insertPlanLen = insertPlanMap.size();
		int updatePlanLen = updatePlanMap.size();
		
		String []sqlArray = new String [ (deleteLen * 3) +  (insertLen * 2) + insertPlanLen + updatePlanLen];

		int rowNumber = 0;
		String zeroTimeStr = "00:00:00";
		
		//组装插入记录
		if(insertLen != 0){//存在元素
			for(String key : insertTrainDistrictMap.keySet())
			{
				PlanForStatics info = insertTrainDistrictMap.get(key);

				/*表TrainDistrictRelation  关键字: Train_name  车次名称, District_name 区段名称 */
				String inertStr = "insert into TrainDistrictRelation(District_name,Train_name) Values('" + info.getDistrictName() + "','"+ key + "');";
				sqlArray[rowNumber++] = inertStr;
			
				/*Train表  关键字:Train_name
		      	Train_name 车次名称,Train_direction 车次方向（上行0和下行1）Train_maxspeed 车最大速度'
		      	Train_startstationname始发站名称',Train_endstationname 到达站名称'*/
				inertStr = "insert into Train(Train_name,Train_direction,Train_startstationname,Train_endstationname) Values('"
				                              + key + "',"+ info.getTrainDirection()  + ",'" +  firstStation + "','"+ lastStation + "');";
				sqlArray[rowNumber++] = inertStr;
				
			//	System.out.println(inertStr);
			}
		}
		
	   //对于plan需要单独处理,因为plan表所保存的内容是以车站和车次为关键字,即同一个车次在不同车站的状态都要保存	
		if(insertPlanLen != 0){//存在元素
			for(TrainStation key : insertPlanMap.keySet())
			{
				/*Plan表     关键字:Train_name   Station_name
				Train_name 车次名称, District_name 区段名称' Prestation_name'前站站名',Station_name 本站站名',
	            Plan_arrivestationtime 到站时间', Plan_leavestationtime 离站时间',*/
				
				String trainName = key.getTrainName(); 
				PlanForStatics info = insertPlanMap.get(key);
				
				String arriveTime = info.getArriveTime();
				String leaveTime = info.getLeaveTime();
				if(leaveTime == null)
					leaveTime = zeroTimeStr;
				if(arriveTime == null)
					arriveTime = zeroTimeStr;
				
				String inertStr = "insert into Plan(District_name,Train_name,Prestation_name,Station_name,Plan_arrivestationtime,Plan_leavestationtime) Values('"
				                         	+ info.getDistrictName()+ "','"+ trainName + "','"+info.getPrestationName()+"','" 
					                        + info.getStationName()+"','" + arriveTime + "','"+leaveTime + "')";
				sqlArray[rowNumber++] = inertStr;
				
			//	System.out.println(inertStr);
			}
		}
		
		//对于plan需要单独处理,因为plan表所保存的内容是以车站和车次为关键字,即同一个车次在不同车站的状态都要保存
		if(updatePlanLen != 0){//存在元素
			for(TrainStationTime key : updatePlanMap.keySet())
			{
				int timeType = key.getTypeTime();
			
				TrainPlan value = updatePlanMap.get(key);
				String fixStr = "' where Train_name='" + key.getTrainName() + "' and Station_name='"+ key.getStationName() + "';";
				String updateStr = "";
				
				if (timeType == Constants.TDCS_TIME_TYPE_ARRIVEAL)//到站时间
				{
					updateStr = "update Plan set Plan_arrivestationtime='" + value.getTime() + fixStr;
				}
				else
				if (timeType == Constants.TDCS_TIME_TYPE_LEAVE)//离站时间
				{
					updateStr = "update Plan set Plan_leavestationtime='" + value.getTime() + fixStr;
				}	
				sqlArray[rowNumber++] = updateStr;
				
			//	System.out.println(updateStr);
			}
		}
		
		//组装删除记录
		if(deleteLen != 0){//存在元素
	
			for(String key : deleteTrainDistrictMap.keySet())
			{
				PlanForStatics info = deleteTrainDistrictMap.get(key);
           
				//TrainDistrictRelation表
				String deleteStr = "delete from TrainDistrictRelation where District_name='" + info.getDistrictName() + "' and Train_name='"+ key + "';";
				sqlArray[rowNumber++] = deleteStr;
				
				//Train表
				deleteStr = "delete from Train where Train_name='" + key + "';";
				sqlArray[rowNumber++] = deleteStr;

				
				//Plan表
				deleteStr = "delete from Plan where Train_name='" + key + "';";
				sqlArray[rowNumber++] = deleteStr;
				
				//System.out.println(deleteStr);
			}
		}
		//NODEFINE 表示无须指定表名，实际上此字段可以去掉。
		boolean result = databaseAccessHandler.updateQuery("NODEFINE",sqlArray);
		//System.out.println("result::"+result);
		return result;
	}


	////////////////////以上的代码是写数据到数据库中//////////////////////////////
	//********************************************************************************************************//
	////////////////////以下的代码是从数据库中读取内容//////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	//获取所有区段信息
	public void getAllDistrictInfo() {
		District tableName = new District();
		String sqlStr = "select * from District";
				
		List<District> list = databaseAccessHandler.sqlQuery(tableName,"District",sqlStr);
		
		baseParam.setAllDistrictName(list);
		
		//测试代码
		//getAllPlanTrainInfoByDistrict("北京-呼和浩特");
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//根据区段获得属于区段内的所有车站信息  并排序 
	public void getAllStationInfoByDistrict(String districtName){
		
		StationDistrictRelation tableName = new StationDistrictRelation();//设置要进行操作的数据库表名
		String sqlStr = "select * from stationdistrictrelation where District_name = '" + districtName + "'"; 
		
		//获取区段内所有车站信息
		List<StationDistrictRelation> list = databaseAccessHandler.sqlQuery(tableName,"StationDistrictRelation",sqlStr);
		
		List<StationDistrictRelation> sortedStationDistrictRelationList = new ArrayList<StationDistrictRelation>();
		
		//对区段内所有车站信息进行排序 : 首站->终点站
		sortedStationDistrictRelationList = this.sortStations(list);
		
		baseParam.setSortedStationDistrictRelationList(sortedStationDistrictRelationList);
		if(sortedStationDistrictRelationList == null || sortedStationDistrictRelationList.size() == 0 )
			return;
		
		int len = sortedStationDistrictRelationList.size();
		List<String> stationNameList = new ArrayList<String>();
		//计算区段内站的总距离 和提取车站名
		int sumStationDistance = 0; 
		for(int i = 0; i < len; i++){
			StationDistrictRelation sdr = sortedStationDistrictRelationList.get(i);
			sumStationDistance = sumStationDistance + sdr.getPredistance();
			stationNameList.add(sdr.getStation_name());//提取车站名
		}
		
		BaseParam.setSortedStationNameList(stationNameList);
		
		baseParam.setSumStationDistance(sumStationDistance);
	}	
	
	//对list序列进行排序 首站->终点站
	private List<StationDistrictRelation> sortStations(List<StationDistrictRelation> list){

		List<StationDistrictRelation> sortedList = new ArrayList<StationDistrictRelation>();

		if( (list == null) || (list.size() == 0) ){
			return null;
		}
		StationDistrictRelation sdr = new StationDistrictRelation();
		int listLength = list.size();
		String preStationName = "";

		//查找区段内首站,即第一个车站
		for(int i = 0; i < listLength; i++){			
			sdr = list.get(i);
			String stationName = sdr.getStation_name();
			if(stationName.equalsIgnoreCase(sdr.getPrestation_name())){
				preStationName = stationName;
				sortedList.add(sdr);
				break;
			}			
		}

		//没有找到首站
		if(preStationName.length() == 0){
			return null;
		}
		//依据首站 依次查找区段内其他站
		for(int i = 0; i < listLength; i++){
			sdr = list.get(i);
			if(sdr.getStation_name().equalsIgnoreCase(sdr.getPrestation_name())){//首站
				continue;
			}
			for(int j = 0; j < listLength; j++)
			{
				sdr = list.get(j);
				String stationName = sdr.getStation_name();
				if(stationName.equalsIgnoreCase(sdr.getPrestation_name())){//首站
					continue;
				}
				else//非首站	
				if( preStationName.equalsIgnoreCase(sdr.getPrestation_name())) 
				{
					preStationName = stationName;
					sortedList.add(sdr);
				}
			}
		}
		return sortedList;
	}


///////////////////////////////////////////////////////////////////////////////////////////////////////////
	//根据区段 从TrainDistrictRelation表获得属于区段内的所有计划车次信息 仅含车次信息  目前不用
	private List<TrainDistrictRelation> getAllTrainDistrictRelationByDistrict(String districtName){
		TrainDistrictRelation tableName = new TrainDistrictRelation();//设置要进行操作的数据库表名
		String sqlStr = "select * from Traindistrictrelation where District_name = '" + districtName + "'"; 
		List<TrainDistrictRelation> trainNameList = databaseAccessHandler.sqlQuery(tableName,"TrainDistrictRelation",sqlStr);
		return trainNameList; 
	}
	private List<Train> getAllTrainsByDistrict(String districtName){
		Train tableName = new Train();//设置要进行操作的数据库表名
		String sqlStr = "select * from Train where Train.Train_name in " +
				        "(select Train_name from Traindistrictrelation where District_name='" + districtName + "')";
		List<Train> trainList = databaseAccessHandler.sqlQuery(tableName,"Train",sqlStr);
		return trainList; 
	}	
	
	//返回车次的方向（上行0，下行1）
	private int getTrainDirectionByTrainName(String trainName) {
		if( (trainNameDirectionMap == null) ||
			(trainNameDirectionMap.size() ==0) ||
			(! trainNameDirectionMap.containsKey(trainName)) )
			return -1;
		else{
			return trainNameDirectionMap.get(trainName);
		}
	}
	
	private Map<String,Integer> trainNameDirectionMap = new HashMap<String,Integer>();
	
	//根据区段获得属于区段内的所有计划车次信息
	public void getAllPlanTrainInfoByDistrict(String districtName){
		
		//ORDER BY 记录将按照默认的升序进行排列（即：从1到9，从a到z）使用DESC 数据按照降序排列
		Plan tableName = new Plan();//设置要进行操作的数据库表名
		String sqlStr = "select * from plan where District_name = '" + districtName + "' ORDER BY Plan_arrivestationtime,Plan_leavestationtime"; 

		//从plan表中获取区段内所有车次信息 已经按照到站和离站时间排好序
		List<Plan> planTrainList = databaseAccessHandler.sqlQuery(tableName,"Plan",sqlStr);
		
		//////////////////////////////////////////////////////////////////
		//从TrainDistrictRelation表中获取区段内所有车次信息,仅有车次信息  原来的 
		//List<TrainDistrictRelation> trainNameList = getAllTrainDistrictRelationByDistrict(districtName);
		//从Train表中获取区段内所有车次信息 
		List<Train> trainNameList = getAllTrainsByDistrict(districtName);
		
		if (trainNameList == null || trainNameList.size() == 0)
			return;

		//对trainNameList进行加工,方便getTrainDirectionByTrainName方法的执行,提高效率
		Iterator<Train> it = trainNameList.iterator();
		while (it.hasNext()) {
			Train data = (Train)it.next();
			trainNameDirectionMap.put(data.getTrain_name(), data.getTrain_direction());
		}
		baseParam.setTrainNameDirectionMap(trainNameDirectionMap); 
		
		//对每一个车站,获取经过的所有车次信息,并自动排序
		sortPlanTrainForStation(planTrainList);
		
	    //转换格式	
		int len = trainNameList.size();
		//每次执行,仅对一个车次的信息的进行操作
		for(int i = 0; i < len; i++){
			String trainName = trainNameList.get(i).getTrain_name();
		
			//依据车次trainName按照车站的先后顺序对本车次进行排序  首站->终点站 
			List<Plan> sortPlanTrainList = sortPlanTrainList(planTrainList,trainName);
			
			//依据车次trainName,对排好序的车次详细信息进行加工,使之用于屏幕绘图
			setTrainPlanFromatList(sortPlanTrainList,trainName);
		}
		
		//将车次信息添加到tdcsDataset的TrainList中
		for(int i = 0; i < len; i++){
			String trainName = trainNameList.get(i).getTrain_name();
			trainNameExist(trainName); //判断trainName是否已经存在
		}			
	}
	//对每一个车站,获取经过的所有车次信息,并按到站/离站时间升序排列
	//从数据库读的车次信息 已经按照到站和离站时间排好序
	private void sortPlanTrainForStation(List<Plan> planList){
		//临时保存车站名和经过该车站的所有车次信息(添加过程中,系统按找车次的到达时间自动排序)  
		Map<String,TreeSet<SortedTrain>> stationTrainDownMap = new HashMap<String,TreeSet<SortedTrain>>();//保存下行
		Map<String,TreeSet<SortedTrain>> stationTrainUpMap = new HashMap<String,TreeSet<SortedTrain>>();//保存上行

		//获取已经排好序的车站列表
		List<String> sortedStationNameList = new ArrayList<String>();
		
		sortedStationNameList = BaseParam.getSortedStationNameList();
		
		if( (planList == null) ||(sortedStationNameList == null) || 
			(planList.size() == 0) ||(sortedStationNameList.size() == 0) )
	       return;
		
		Plan plan = new Plan();
		int planLength = planList.size();
		int stationLength = sortedStationNameList.size();
		String firstStation = sortedStationNameList.get(0);
		String lastStation = sortedStationNameList.get(stationLength - 1);
		
		for (int i = 0; i < stationLength;i++){//对所有车站
			TreeSet<SortedTrain> treeSetDown = new TreeSet<SortedTrain>();
			TreeSet<SortedTrain> treeSetUp = new TreeSet<SortedTrain>();
			
			String sortedStationName = sortedStationNameList.get(i);
			//int timeType = Constants.TDCS_TIME_TYPE_ARRIVEAL;
			String trainName = "";
			for(int j = 0; j < planLength; j++){//某一车站的所有车次
			
				plan = planList.get(j);
				if(sortedStationName.equalsIgnoreCase(plan.getStation_name())){
					trainName = plan.getTrain_name();
					/*数据库中时间格式（字符串）：00：00：00 //plan.getPlan_arrivestationtime()
					 转换后时间格式（整数）：000，单位 分钟 //TimeX_x.timeToX
					*/
					int arriveTime = UtilForTime2X.timeToX(plan.getPlan_arrivestationtime());
					int leaveTime = UtilForTime2X.timeToX(plan.getPlan_leavestationtime());
					int trainDirection = getTrainDirectionByTrainName(trainName);
					int typeFlag = Constants.TDCS_TRAIN_TYPE_NO;
					SortedTrain sortedPlan = null;
					if (trainDirection == 1)//车次方向  下行1
					{
						if (! firstStation.equalsIgnoreCase(plan.getStation_name())){//首站 不处理
							sortedPlan = new SortedTrain(typeFlag,trainName, trainDirection,Constants.TDCS_TIME_TYPE_ARRIVEAL,arriveTime);//time是到站时间	
							treeSetDown.add(sortedPlan);
						}
						if (! lastStation.equalsIgnoreCase(plan.getStation_name())){//终点站 不处理
							sortedPlan = new SortedTrain(typeFlag,trainName, trainDirection,Constants.TDCS_TIME_TYPE_LEAVE,leaveTime);//time是离站时间
							treeSetDown.add(sortedPlan);
						}
					}else
					if (trainDirection == 0)//车次方向（上行0）
					{
						if (! lastStation.equalsIgnoreCase(plan.getStation_name())){//首站  不处理
							sortedPlan = new SortedTrain(typeFlag,trainName, trainDirection,Constants.TDCS_TIME_TYPE_ARRIVEAL,arriveTime);//time是到站时间	
							treeSetUp.add(sortedPlan);
						}
						
						if (! firstStation.equalsIgnoreCase(plan.getStation_name())){//终点站 不处理
							sortedPlan = new SortedTrain(typeFlag,trainName, trainDirection,Constants.TDCS_TIME_TYPE_LEAVE,leaveTime);//time是离站时间
							treeSetUp.add(sortedPlan);
						}
					}
				}
				
				
				if(! treeSetDown.isEmpty()){
					stationTrainDownMap.put(sortedStationName,treeSetDown);
				}
				if(! treeSetUp.isEmpty()){
					stationTrainUpMap.put(sortedStationName,treeSetUp);
				}
			}//某一车站的所有车次 for
		}//对所有车站
		UtilForMouseAction.setStationTrainDownMap(stationTrainDownMap);
		UtilForMouseAction.setStationTrainUpMap(stationTrainUpMap);
	}

	 //判断trainName是否存在
	 private void trainNameExist(String trainName){	 

		 boolean existFlag = false;
		 List<String> trainNameList = baseParam.getTrainNameList();

		 if( trainNameList == null || trainNameList.size() ==0)
			 existFlag = false;
		 else
		 {
			 for(int i = 0; i < trainNameList.size(); i++){ 
				 if(trainNameList.get(i).equalsIgnoreCase(trainName)){//如果存在
					 existFlag = true;	
					 break;
				 }
			 }
		 }
		 // 如果不存在
		 if(! existFlag)
		 {	 //车次名称
			 baseParam.appendTrainNameList(trainName);
			 //车次本身信息
			 LineFigure trainList = new LineFigure();
			 trainList.setTrainName(trainName); //设置车次信息List的trainName
			 trainList.setTrainDirection(getTrainDirectionByTrainName(trainName));//设置车次的方向
			 baseParam.appendTrainList(trainList);
		 }		 
	 }

	
	//将排序后的sortPlanTrainList中的plan数据转化为可以直接画图的TrainDrawFromat格式数据
	//执行一次，只能实现对某一车次的运营计划的数据进行格式化
	private void setTrainPlanFromatList(List<Plan> list,String trainName){
		
		if( (list == null) || (list.size() == 0) || (trainName.length() == 0)){
			return;
		}
		Plan plan = new Plan();
		Plan planStation = new Plan();
		Plan planNextStation = new Plan();
		FromatedTrain trainDrawFromat  = new FromatedTrain();
		int len = list.size();		
		for(int i = 0; i < len; i++){//for2		
			plan = list.get(i);
			if(plan.getTrain_name().equalsIgnoreCase(trainName)){//if语句1
				planStation = plan;
				//System.out.println("转换格式函数：i = " + i);
				for(int j = i + 1; j < len; j++){//for 1
					plan = list.get(j);
					if(plan.getTrain_name().equalsIgnoreCase(trainName)){

						planNextStation = plan;

						trainDrawFromat = new FromatedTrain();
						trainDrawFromat.setTrainName(trainName);//设置车次
						trainDrawFromat.setStationName(planStation.getStation_name());//设置本站名称
						trainDrawFromat.setNextStationName(planNextStation.getStation_name());//设置下一站名称
						
						//设置本站出发时间    时：分：秒 转换为分
						String leaveTimeStr = planStation.getPlan_leavestationtime();						
						trainDrawFromat.setStationTimeX(UtilForTime2X.timeToX(leaveTimeStr));

						//设置下站到站时间
						String arriveTimeStr = planNextStation.getPlan_arrivestationtime();						
						trainDrawFromat.setNextStationTimeX(UtilForTime2X.timeToX(arriveTimeStr));

						//System.out.println("车次：" + trainDrawFromat.getTrainName() + "  发站：" +  leaveTimeStr + " 到站：" +  arriveTimeStr  );
                         
						baseParam.appendTrainPlanFromatList(trainDrawFromat);

						planStation = planNextStation;
					}		
				}//for1
				break;//退出循环
			}//if 语句1		
		}//for2
	}
	
	private List<Plan> sortPlanTrainList(List<Plan> list, String trainName){
		List<Plan> sortedList = new ArrayList<Plan>();
		UtilForStatics utilForStatics = new UtilForStatics();
		if( (list == null) || (list.size() == 0) || (trainName.length() ==0)){
			return null;
		}
		Plan plan = new Plan();
		int listLength = list.size();//获取指定区段内所有车次的数量
		String preStationName = "";

		//查找区段内首站,即第一个车站
		for(int i = 0; i < listLength; i++){			
			plan = list.get(i);
			if( (plan.getTrain_name().equalsIgnoreCase(trainName))&&
				(plan.getStation_name().equalsIgnoreCase(plan.getPrestation_name())) )
			{
				preStationName = plan.getStation_name();
				//System.out.println("首站:"+preStationName);
				sortedList.add(plan);
				
				//保存数据到ScheduleDataset类的scheduleTrainMap中,用于记录学员调整运行计划
				utilForStatics.putScheduleTrainMap(plan);
				break;
			}	
		}

		//没有找到首站
		if(preStationName.length() == 0){
			return null;
		}
		
		//依据首站 依次查找区段内其他站
		for(int i = 0; i < listLength; i++){
			plan = list.get(i);
			if(plan.getStation_name().equalsIgnoreCase(plan.getPrestation_name())){//首站
				continue;
			}
			for(int j = 0; j < listLength; j++)
			{
				plan = list.get(j);
				String stationName = plan.getStation_name();
				if( (plan.getTrain_name().equalsIgnoreCase(trainName))&&
				   (plan.getStation_name().equalsIgnoreCase(plan.getPrestation_name()))){//首站
					continue;
				}
				else//非首站	
				if( (plan.getTrain_name().equalsIgnoreCase(trainName))&&
					preStationName.equalsIgnoreCase(plan.getPrestation_name())) 
				{
					//System.out.println("其他站:"+stationName);
					preStationName = stationName;
					sortedList.add(plan);
				
					//保存数据到UtilForStatics_x类的scheduleTrainMap中,用于记录学员调整运行计划
					utilForStatics.putScheduleTrainMap(plan);
					
				}
			}
		}
		return sortedList;
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	 public static void main(String args[]){
		 
		 /*String time = "01:15:00";
		 DateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		//得到现在小时
		public static String getHour() {
		   Date currentTime = new Date();
		   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   String dateString = formatter.format(currentTime);
		   String hour;
		   hour = dateString.substring(11, 13);
		   return hour;
		}
		//得到现在分钟
		  
		public static String getTime() {
		   Date currentTime = new Date();
		   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   String dateString = formatter.format(currentTime);
		   String min;
		   min = dateString.substring(14, 16);
		   return min;
		}
		 
		public static String getTimeShort() {
			   SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
			   Date currentTime = new Date();
			   String dateString = formatter.format(currentTime);
			   return dateString;
			}
		 */
	 }
	
}
