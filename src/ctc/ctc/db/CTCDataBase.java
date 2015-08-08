package ctc.ctc.db;

//用于同服务器进行同步或异步通信
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Display;

import ctc.constant.Constants;
import ctc.pojobean.Plan;
import ctc.pojobean.TaskPlan;
import ctc.pojobean.TaskPlanList;
import ctc.ctc.ctc2sics.CTCToSICS;
import ctc.ctc.drawctc.ctcmain.data.BaseParam;
import ctc.ctc.drawctc.ctcmain.drawctcmain.DrawCTCGraph;
import ctc.ctc.stationLayout.StationLayoutInit;
import ctc.transport.*;
import ctc.transport.AsynClientHandler.*;
import ctc.transport.message.CTCToSICSRequestMessage;
import ctc.transport.message.CommonMessage;
import ctc.transport.message.ErrorMessage;
import ctc.transport.message.P2PCommandMessage;
import ctc.transport.message.P2PCommandResponseMessage;
import ctc.transport.message.SICSToCTCRequestMessage;
import ctc.transport.message.ScheduleErrorMessage;
import ctc.transport.message.StationControlMessage;
import ctc.transport.message.TaskMessage;
import ctc.transport.message.TaskResponseMessage;
import ctc.transport.message.TeamTdcsRsbMessage;
import ctc.transport.message.TrainArriveMessage;
import ctc.util.ErrorLog;
import ctc.util.JsonUtil;

//Callback接口的定义在src\ctc\transport下的AsynClientHandler类中
public class CTCDataBase implements CTCCallback {

	//这里写的都是static变量，是否必要？？？？？？？
	public static SynClientSupport synClientSupport = new SynClientSupport();  //同步通信句柄
	public static AsynClientSupport asynClientSupport = new AsynClientSupport(); //异步通信句柄
	private static MinaClient minaClient = new MinaClient();
	
	private static String userName; //学员账号
	private static int runMode; //系统运行方式
	private static String districtName; //车站区段ID
	private static int teamID; //组ID
	
	static StationLayoutInit stationLayoutInit;

	//经过本车站的所有车次的原计划信息   并已经按照: 首站 ->终点站的顺序排好
	private List<Plan> planList = new ArrayList<Plan>(); 

	public CTCDataBase(){
		stationLayoutInit = StationLayoutInit.getInstance(); 
	}
	
	
	//------------hu-2010-7-15--------start---------//
	//CTC收到CommonMessage消息
	public void ctcReceiveCommonMessage(CommonMessage rMsg) {
			
		ErrorLog.log("\nCTC：CTCDataBase收到CommonMessage消息" + "  messageName = " +  rMsg.getMeseageName());
		
		String messageName = rMsg.getMeseageName();
		String message = rMsg.getMessage();
		
		if(messageName.equalsIgnoreCase("P2PCommandMessage")){
			P2PCommandMessage msg = (P2PCommandMessage) JsonUtil.getObject4JsonString(message, P2PCommandMessage.class);			
			ErrorLog.log("CTC：CTCDataBase收到CommonMessage消息 trainName = " + msg.getTrainName());
			ErrorLog.log("CTC：CTCDataBase收到CommonMessage消息 district = " + msg.getDistrict());
			ErrorLog.log("CTC：CTCDataBase收到CommonMessage消息 diection = " + msg.getDirection());
			CTCToSICS.receiceP2PCommandMessage(msg);
			
		}else if(messageName.equalsIgnoreCase("ScheduleErrorMessage")){
			
			ScheduleErrorMessage msg = (ScheduleErrorMessage)JsonUtil.getObject4JsonString(message, ScheduleErrorMessage.class);
			
			ErrorLog.log("客户端CTC：CTCDataBase收到CommonMessage消息 Content = " + msg.getContent());
		
		}else{
			ErrorLog.log("CTC：CTCDataBase收到CommonMessage消息 messageName != P2PCommandMessage");
		}
		
		
	}
	//------------hu-2010-7-15--------end---------//
	
	
	//服务器分配给本学员的车站的stationID。 目前的区段为districtID ， 运行模式为runMode。区段districtID内所有车次信息trainPlan
    public void setParams(MinaClient minaClient,String userName,int runMode, String districtName, int teamID){
    	this.minaClient = minaClient;
		synClientSupport = minaClient.getSynClientSupport();
		asynClientSupport  = minaClient.getAsynClientSupport();
		
    	this.userName = userName;
    	this.runMode = runMode;
    	this.districtName = districtName;
    	this.teamID = teamID;
    	
    	   	
    }
    
    
	@Override
	//收到服务器发来的首站信息rMsg
	public void receicedFirstStation(P2PCommandResponseMessage rMsg) {
		CTCToSICS.receicedFirstStation(rMsg);
	}
	//接收到自己发向其他站的,由其他站发送的应答消息
	@Override
	public void receicedTrainFromDown(P2PCommandMessage rMsg) {
		CTCToSICS.receicedTrainFromDown(rMsg);
	}
    //接收普通车站SICS发来的接车信息
	@Override
	public void receicedTrainFromUp(P2PCommandMessage rMsg) {
		CTCToSICS.receicedTrainFromUp(rMsg);
	}

    //SICS发送到CTC的消息 
	@Override
	public void SICSToCTC(SICSToCTCRequestMessage rMsg) {
		CTCToSICS.SICSToCTCMessage(rMsg);
	}

	//通信示例
	@Override
	public void receiveStationControlMessageASYN(StationControlMessage msg) {
		CTCToSICS.receiveStationControlMessageASYN(msg);
	}

	//通信示例 hu
	@Override
	public void ctcReceiveTeamTdcsRsbMessageASYN(TeamTdcsRsbMessage rMsg) {
		
		System.out.println("\nCTCDataBase.java: CTC DB 收到 TeamTdcsRsbMessage");
				
		List<TaskPlan> taskList = JsonUtil.getList4Json(rMsg.getTrainPlan(), TaskPlan.class);
		List<TaskPlan> taskList1 = JsonUtil.getList4Json(rMsg.getTrainPlan1(), TaskPlan.class);
		List<TaskPlan> taskList2 = JsonUtil.getList4Json(rMsg.getTrainPlan2(), TaskPlan.class);
		List<TaskPlan> taskList3 = JsonUtil.getList4Json(rMsg.getTrainPlan3(), TaskPlan.class);
		List<TaskPlan> taskList4 = JsonUtil.getList4Json(rMsg.getTrainPlan4(), TaskPlan.class);
		List<TaskPlan> taskList5 = JsonUtil.getList4Json(rMsg.getTrainPlan5(), TaskPlan.class);
		
		BaseParam.getTaskList().clear();
		BaseParam.setTaskList(taskList);
		BaseParam.getTaskList1().clear();
		BaseParam.setTaskList1(taskList1);
		BaseParam.getTaskList2().clear();
		BaseParam.setTaskList2(taskList2);
		BaseParam.getTaskList3().clear();
		BaseParam.setTaskList3(taskList3);
		BaseParam.getTaskList4().clear();
		BaseParam.setTaskList4(taskList4);
		BaseParam.getTaskList5().clear();
		BaseParam.setTaskList5(taskList5);
				
		System.out.println("\nCTCDataBase.java: CTC DB 收到 TeamTdcsRsbMessage -- updateTable --");
		
		Display.getDefault().syncExec(
				new Runnable() {
					public void run() {
						
						DrawCTCGraph.getInstance().updateTable();
					}
				});			
		

/*
		if(taskList != null && taskList.size() != 0){						
			int len = taskList.size();
			ErrorLog.log("CTC 总task = " + len);
		}
					
		ErrorLog.log("\n --------车站一---------- ");
		if(taskList1 != null && taskList1.size() != 0){						
			int len1 = taskList1.size();
			for(int n = 0; n < len1; n++){
				TaskPlan task = taskList1.get(n);
				ErrorLog.log("CTC 车次=" + task.getTrainName() + "   方向=" + task.getTrainDirection() + "   类型=" + task.getTaskType() + "   计划时间=" + task.getTime() +  "   股道="  + task.getTrackLine() + "   起始按钮=" + task.getStartButton() + "   终端按钮=" + task.getEndButton());
			}						
		}	
		
		ErrorLog.log("\n --------车站二---------- ");
		if(taskList2 != null && taskList2.size() != 0){						
			int len2 = taskList2.size();
			for(int n = 0; n < len2; n++){
				TaskPlan task = taskList2.get(n);
				ErrorLog.log("CTC 车次=" + task.getTrainName() + "   方向=" + task.getTrainDirection() + "   类型=" + task.getTaskType() + "   计划时间=" + task.getTime() +  "   股道="  + task.getTrackLine() + "   起始按钮=" + task.getStartButton() + "   终端按钮=" + task.getEndButton());
			}						
		}	
		
		ErrorLog.log("\n --------车站三---------- ");
		if(taskList3 != null && taskList3.size() != 0){						
			int len3 = taskList3.size();
			for(int n = 0; n < len3; n++){
				TaskPlan task = taskList3.get(n);
				ErrorLog.log("CTC 车次=" + task.getTrainName() + "   方向=" + task.getTrainDirection() + "   类型=" + task.getTaskType() + "   计划时间=" + task.getTime() +  "   股道="  + task.getTrackLine() + "   起始按钮=" + task.getStartButton() + "   终端按钮=" + task.getEndButton());
			}						
		}	
		
		ErrorLog.log("\n --------车站四---------- ");
		if(taskList4 != null && taskList4.size() != 0){						
			int len4 = taskList4.size();
			for(int n = 0; n < len4; n++){
				TaskPlan task = taskList4.get(n);
				ErrorLog.log("CTC 车次=" + task.getTrainName() + "   方向=" + task.getTrainDirection() + "   类型=" + task.getTaskType() + "   计划时间=" + task.getTime() +  "   股道="  + task.getTrackLine() + "   起始按钮=" + task.getStartButton() + "   终端按钮=" + task.getEndButton());
			}						
		}					

		ErrorLog.log("\n --------车站五---------- ");
		if(taskList5 != null && taskList5.size() != 0){						
			int len5 = taskList5.size();
			for(int n = 0; n < len5; n++){
				TaskPlan task = taskList5.get(n);
				ErrorLog.log("CTC 车次=" + task.getTrainName() + "   方向=" + task.getTrainDirection() + "   类型=" + task.getTaskType() + "   计划时间=" + task.getTime() +  "   股道="  + task.getTrackLine() + "   起始按钮=" + task.getStartButton() + "   终端按钮=" + task.getEndButton());
			}						
		}	
*/		

	}
	
	//通信示例 hu
	@Override
	public void ctcReceiveTaskMessageASYN(TaskMessage msg) {
		CTCToSICS.ctcReceiveTaskMessageASYN(msg);
	}
	
	//通信示例 hu
	@Override
	public void ctcReceiveTaskResponseMessageASYN(TaskResponseMessage msg) {
		CTCToSICS.ctcReceiveTaskResponseMessageASYN(msg);
	}
	
	//通信示例 hu
	@Override
	public void ctcReceiveTrainArriveMessageASYN(TrainArriveMessage msg) {
		CTCToSICS.ctcReceiveTrainArriveMessageASYN(msg);
	}
	
	//通信示例 hu
	@Override
	public void ctcReceiveErrorMessageASYN(ErrorMessage msg) {
		CTCToSICS.ctcReceiveErrorMessageASYN(msg);
	}
	
	
	public static int getTeamID() {
		return teamID;
	}

	public static void setTeamID(int teamID) {
		CTCDataBase.teamID = teamID;
	}

	public static String getUserName() {
		return userName;
	}

	public static void setUserName(String userName) {
		CTCDataBase.userName = userName;
	}

	public static int getRunMode() {
		return runMode;
	}

	public static void setRunMode(int runMode) {
		CTCDataBase.runMode = runMode;
	}

	
	
    public static MinaClient getMinaClient() {
		return minaClient;
	}

	public static void setMinaClient(MinaClient minaClient) {
		CTCDataBase.minaClient = minaClient;
	}

	public static String getDistrictName() {
		return districtName;
	}

	public static void setDistrictName(String districtName) {
		CTCDataBase.districtName = districtName;
	}

	public static StationLayoutInit getStationLayoutInit() {
		return stationLayoutInit;
	}

	public static void setStationLayoutInit(StationLayoutInit stationLayoutInit) {
		CTCDataBase.stationLayoutInit = stationLayoutInit;
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
		CTCDataBase.synClientSupport = synClientSupport;
	}

	public static AsynClientSupport getAsynClientSupport() {
		return asynClientSupport;
	}

	public  void setAsynClientSupport(AsynClientSupport asynClientSupport) {
		CTCDataBase.asynClientSupport = asynClientSupport;
	}

	
	
	/////////////////////////////////////////////////////////////////原来的代码
	//以下方法都是对Callback接口内的方法提供空实现，以便使database的子类只需要实现部分需要的方法即可    
    //处理异步消息
	
	/*private String vrTime; //虚拟时间    private int runMode; //系统运行方式	private int districtID; //车站区段ID 	private int teamID; //组号
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
	}*/
	
	
	
	
}
