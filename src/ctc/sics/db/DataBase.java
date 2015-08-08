package ctc.sics.db;

//用于同服务器进行同步或异步通信
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Display;

import ctc.constant.Constants;
import ctc.ctc.ctc2sics.CTCToSICS;
import ctc.pojobean.Plan;
import ctc.pojobean.TaskPlan;
import ctc.sics.SicsMain;
import ctc.sics.sics2ctc.SICSToCTC;
import ctc.sics.station.autosystem.TaskTable;
import ctc.sics.station.data.BaseParam;
import ctc.sics.stationLayout.StationLayoutInit;
import ctc.transport.*;
import ctc.transport.AsynClientHandler.Callback;
import ctc.transport.message.*;
import ctc.util.ErrorLog;
import ctc.util.JsonUtil;

//Callback接口的定义在src\ctc\transport下的AsynClientHandler类中
public class DataBase implements Callback {

	
	BaseParam baseData = BaseParam.getInstance();
	
	public static SynClientSupport synClientSupport = new SynClientSupport();  //同步通信句柄
	public static AsynClientSupport asynClientSupport = new AsynClientSupport(); //异步通信句柄
	private static MinaClient minaClient = new MinaClient();
	
	private static String userName; //学员账号
	private static int runMode; //系统运行方式
	private static String districtName; //车站区段ID
	private static int teamID; //组ID
	private static String stationName = ""; //车站ID
	
	static StationLayoutInit stationLayoutInit;

	//经过本车站的所有车次的原计划信息   并已经按照: 首站 ->终点站的顺序排好
	private List<Plan> planList = new ArrayList<Plan>(); 

	public DataBase(){
		stationLayoutInit = StationLayoutInit.getInstance(); 
	}

	//服务器分配给本学员的车站的stationID。 目前的区段为districtID ， 运行模式为runMode。区段districtID内所有车次信息trainPlan
    public void setParams(MinaClient minaClient, String userName, int runMode, String districtName, int teamID, String stationName){
    	this.minaClient = minaClient;
		synClientSupport = minaClient.getSynClientSupport();
		asynClientSupport  = minaClient.getAsynClientSupport();
		
    	this.userName = userName;
    	this.runMode = runMode;
    	this.districtName = districtName;
    	this.teamID = teamID;
    	this.stationName = stationName;
    	
    }
    
    
    //------------hu-2010-7-15--------start---------//
	//SICS收到CommonMessage消息
	public void sicsReceiveCommonMessage(CommonMessage rMsg) {
		
		ErrorLog.log("SICS：DataBase收到CommonMessage消息");
		String messageName = rMsg.getMeseageName();
		String message = rMsg.getMessage();
		
		if(messageName.equalsIgnoreCase("P2PCommandMessage")){
			P2PCommandMessage msg = (P2PCommandMessage) JsonUtil.getObject4JsonString(message, P2PCommandMessage.class);			
			ErrorLog.log("SICS：DataBase收到CommonMessage消息 trainName = " + msg.getTrainName());
			ErrorLog.log("SICS：DataBase收到CommonMessage消息 district = " + msg.getDistrict());
			ErrorLog.log("SICS：DataBase收到CommonMessage消息 diection = " + msg.getDirection());
			SICSToCTC.receiceP2PCommandMessage(msg);
		}else if(messageName.equalsIgnoreCase("ScheduleErrorMessage")){
			ScheduleErrorMessage msg = (ScheduleErrorMessage)JsonUtil.getObject4JsonString(message, ScheduleErrorMessage.class);
			ErrorLog.log("客户端SICS：DataBase收到CommonMessage消息 Content = " + msg.getContent());
		
		}else if(messageName.equalsIgnoreCase("ErrorMessage")){
			ErrorMessage msg = (ErrorMessage)JsonUtil.getObject4JsonString(message, ErrorMessage.class);
			ErrorLog.log("客户端SICS：DataBase收到CommonMessage消息stationName = " + msg.getStationName() +  "  figName = " + msg.getFigName());
		
			SICSToCTC.receiveErrorMessageASYN(msg);
			
		}else{
			ErrorLog.log("SICS：DataBase收到CommonMessage消息 messageName != P2PCommandMessage");
		}
	}
    
	 //------------hu-2010-7-15--------end---------//
	
	
	
	
	//hu 2010-4-25
	@Override
	public void receiveTeamTdcsRsbMessageASYN(TeamTdcsRsbMessage rMsg) {
		
		System.out.println("\nSICS DataBase.java: 收到 TeamTdcsRsbMessage");
		
		if ((SicsMain.getDb() == null) || (SicsMain.getDb().getStationName() == null)) {
			System.out.println("出现错误：SicsMain.db == null");
			return;
		}
			
		String stationName = SicsMain.getDb().getStationName();	
		if(stationName.equalsIgnoreCase("车站一")){
			baseData.getTaskList().clear();
			baseData.setTaskList(JsonUtil.getList4Json(rMsg.getTrainPlan1(), TaskPlan.class));
		}else if(stationName.equalsIgnoreCase("车站二")){
			baseData.getTaskList().clear();
			baseData.setTaskList(JsonUtil.getList4Json(rMsg.getTrainPlan2(), TaskPlan.class));
		}else if(stationName.equalsIgnoreCase("车站三")){
			baseData.getTaskList().clear();
			baseData.setTaskList(JsonUtil.getList4Json(rMsg.getTrainPlan3(), TaskPlan.class));
		}else if(stationName.equalsIgnoreCase("车站四")){
			baseData.getTaskList().clear();
			baseData.setTaskList(JsonUtil.getList4Json(rMsg.getTrainPlan4(), TaskPlan.class));
		}else if(stationName.equalsIgnoreCase("车站五")){
			baseData.getTaskList().clear();
			baseData.setTaskList(JsonUtil.getList4Json(rMsg.getTrainPlan5(), TaskPlan.class));
		}
				
		System.out.println("\nSICS DataBase.java: 收到 TeamTdcsRsbMessage -- updateTable --");
		
		Display.getDefault().syncExec(
				new Runnable() {
					public void run() {
						
						TaskTable.getInstance().updateTable();
					}
				});			
	}
	
	
	/////////////////////////////////////////////////////////////////
	//以下方法都是对Callback接口内的方法提供空实现，以便使database的子类只需要实现部分需要的方法即可    
    //处理异步消息
	/*@Override
	private String vrTime; //虚拟时间    private int runMode; //系统运行方式	private int districtID; //车站区段ID 	private int teamID; //组号
		private int trainID; //车次ID  private int stationID;*/
	//实际上：vrTime,int runMode,int districtID都不用传输，只是备用
	/*public void acceptedTrainFromUp(P2PCommandResponseMessage rMsg) {
		stationLayoutInit.acceptedTrainFromUp(rMsg);
	}
	
	@Override
	//接收到自己发向其他站的,由其他站发送的应答消息
	public void acceptedReplyFromDown(P2PCommandResponseMessage rMsg){
		stationLayoutInit.acceptedReplyFromDown(rMsg);
	}
	
    //当教师选取开始实验按钮后,服务器发送此消息
	@Override
	public void startRun(P2PCommandResponseMessage rMsg) {
	  stationLayoutInit.startRun(rMsg);
	}
	
	原来的，替换为下面
*/

	@Override
	public void receiveTrainFromUp(P2PCommandMessage msg) {
		// TODO Auto-generated method stub
		SICSToCTC.receiveTrainFromUp(msg);
	}
	
	@Override
	public void receiveTrainFromDown(P2PCommandMessage rMsg) {
		SICSToCTC.receiveTrainFromDown(rMsg);
	}
	//通信示例
	public void receiveStationControlMessageASYN(StationControlResponseMessage rMsg) {
		SICSToCTC.receiveStationControlMessageASYN(rMsg);
	}
	
	
	//通信示例 hu
	public void receiveTaskMessageASYN(TaskMessage rMsg) {
		SICSToCTC.receiveTaskMessageASYN(rMsg);
	}
	
	//通信示例 hu
	public void receiveTaskResponseMessageASYN(TaskResponseMessage rMsg) {
		SICSToCTC.receiveTaskResponseMessageASYN(rMsg);
	}
	
	//通信示例 hu
	public void receiveErrorMessageASYN(ErrorMessage rMsg) {
		SICSToCTC.receiveErrorMessageASYN(rMsg);
	}
	
	//通信示例 hu
	public void receiveTrainArriveMessageASYN(TrainArriveMessage rMsg) {
		SICSToCTC.receiveTrainArriveMessageASYN(rMsg);
	}
	
	//当教师选取开始实验按钮后,服务器发送此消息
	@Override
	public void startRun(P2PCommandResponseMessage rMsg) {
		
		//System.out.println("\n SICS DB 收到 P2PCommandResponseMessage 启动消息");
		
		List<Plan> planList = JsonUtil.getList4Json(rMsg.getTrainPlan(), Plan.class);
		if(planList == null){
			//System.out.println("planList == null");
			return;
		}
		
		int len = planList.size();
		//System.out.println("planListSize == " + len);
		
		//SICSToCTC.startRun(rMsg);
	}

	
   
    
    public static int getTeamID() {
		return teamID;
	}

	public static void setTeamID(int teamID) {
		DataBase.teamID = teamID;
	}

	public static String getUserName() {
		return userName;
	}

	public static void setUserName(String userName) {
		DataBase.userName = userName;
	}

	public static int getRunMode() {
		return runMode;
	}

	public static void setRunMode(int runMode) {
		DataBase.runMode = runMode;
	}

	
	
    public static MinaClient getMinaClient() {
		return minaClient;
	}

	public static void setMinaClient(MinaClient minaClient) {
		DataBase.minaClient = minaClient;
	}

	public static String getDistrictName() {
		return districtName;
	}

	public static void setDistrictName(String districtName) {
		DataBase.districtName = districtName;
	}

	public static String getStationName() {
		return stationName;
	}

	public static void setStationName(String stationName) {
		DataBase.stationName = stationName;
	}

	public static StationLayoutInit getStationLayoutInit() {
		return stationLayoutInit;
	}

	public static void setStationLayoutInit(StationLayoutInit stationLayoutInit) {
		DataBase.stationLayoutInit = stationLayoutInit;
	}

	public List<Plan> getPlanInfoList() {
		return planList;
	}

	public void setPlanInfoList(List<Plan> planList) {
		this.planList = planList;
	}

	public void closeMinaClient(){
    	if (minaClient != null)
    		minaClient.closeConnection(Constants.CLIENT_CLOSE_NORMAL);
    }
	
	public static SynClientSupport getSynClientSupport() {
		return synClientSupport;
	}

	public void setSynClientSupport(SynClientSupport synClientSupport) {
		DataBase.synClientSupport = synClientSupport;
	}

	public static AsynClientSupport getAsynClientSupport() {
		return asynClientSupport;
	}

	public  void setAsynClientSupport(AsynClientSupport asynClientSupport) {
		DataBase.asynClientSupport = asynClientSupport;
	}

	@Override
	public void CTCToSICS(CTCToSICSRequestMessage msg) {
		SICSToCTC.CTCToSICS(msg);
		
	}

	
}
