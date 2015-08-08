package ctc.sics.db.service;

import ctc.sics.SicsMain;
import ctc.sics.db.DataBase;
import ctc.transport.message.P2PCommandMessage;
import ctc.transport.message.P2PCommandResponseMessage;
import ctc.util.ErrorLog;
import ctc.constant.Constants;


public class StationService {

	public static String vrTime;
	public static String timeStep;
	
	private static StationService stationService = null; 
	public static StationService getInstance(){
		if (stationService == null){
			stationService = new StationService();
		}
		return stationService;
	}

	public StationService(){}
	
	
	//异步方式   向下一站发送运行命令
	public void asynP2PMessageToDown(String trainName){

		P2PCommandMessage sMsg = new P2PCommandMessage();
		sMsg.setTerType(Constants.TERMINAL_TYPE_SICS);//普通站终端;
		
		sMsg.setCommandMode(Constants.MODE_CS_ASYN_CLIENT);//异步
		sMsg.setCommandType(Constants.TYPE_CLIENT_P2P_ASYN_DOWN);//发向下一站
		sMsg.setUserRole(Constants.USER_ROLE_STUDENT);//目前没有处理
		sMsg.setResult(Constants.CLIENT_RESULT_OK);//本学员操作的结果   操作正确
		
		//从服务器传送过来的参数值
		sMsg.setUserName(SicsMain.db.getUserName());
		sMsg.setRunMode(SicsMain.db.getRunMode());
		sMsg.setDistrictName(SicsMain.db.getDistrictName());//车站区段ID
		sMsg.setTeamID(SicsMain.db.getTeamID());//本站所在组
		
		//System.out.println("db.getStationID002:" + db.getStationID());
		sMsg.setStationName(SicsMain.db.getStationName());//本站的车站ID
		
		sMsg.setVrTime(vrTime);
		sMsg.setTimeStep(timeStep);
		
		sMsg.setTrainName(trainName); //车次ID
		
		SicsMain.db.getAsynClientSupport().p2pCommandMessageSend(sMsg);//异步通信

	}
		
	//异步方式   向上一站发送应答消息
	public static void asynP2PMessageToUp(P2PCommandResponseMessage rMsg){

		P2PCommandMessage sMsg = new P2PCommandMessage();

		sMsg.setCommandMode(Constants.MODE_CS_ASYN_CLIENT);//异步
		sMsg.setCommandType(Constants.TYPE_CLIENT_P2P_ASYN_UP);//发向上一站  Reply确认消息
		sMsg.setUserRole(Constants.USER_ROLE_STUDENT);//目前没有处理
		sMsg.setResult(Constants.CLIENT_RESULT_OK);//本学员操作的结果   操作正确

		//从服务器传送过来的参数值
		sMsg.setUserName(SicsMain.db.getUserName());//学员账号
		sMsg.setRunMode(rMsg.getRunMode());//系统运行方式
		sMsg.setDistrictName(rMsg.getDistrictName());//车站区段ID
		sMsg.setTeamID(rMsg.getTeamID());//本站所在组
		sMsg.setStationName(rMsg.getStationName());//本站的车站ID
		sMsg.setVrTime(vrTime);
		sMsg.setTimeStep(timeStep);
		
		sMsg.setTrainName(rMsg.getTrainName());//车次ID
		ErrorLog.log("发送到UP::"+ SicsMain.db.getUserName());
		
		SicsMain.db.getAsynClientSupport().p2pCommandMessageSend(sMsg);//异步通信
	}

	//异步方法    接收上站发来的接车消息     然后发送收到的应答消息
	public void acceptedTrainFromUp(P2PCommandResponseMessage rMsg) {

		String trainName = rMsg.getTrainName();
		
		//本地进行接车的具体处理
		asynP2PMessageToUp(rMsg);
		
	}

	//接收下站发来的应答消息
	public void acceptedReplyFromDown(P2PCommandResponseMessage rMsg){

		ErrorLog.log("接收FromDown::"+ SicsMain.db.getUserName());
	}


	//接收服务器发来的启动消息  只有车次的首站才能接收到此消息
	public void startRun(P2PCommandResponseMessage rMsg) {
		//这里的代码都是实验性代码
		
		vrTime = rMsg.getVrTime();
		timeStep = rMsg.getTimeStep();
		ErrorLog.log("首站用户名::"+ rMsg.getUserName()); //由服务器传来
		SicsMain.db.setStationName(rMsg.getStationName());
		SicsMain.db.setTeamID(rMsg.getTeamID());
		SicsMain.db.setDistrictName(rMsg.getDistrictName());
		
		asynP2PMessageToDown("集宁");
		
		//db.synClientSupport.
		
		/* System.out.println("db:"+db);
		 if(db.getPlanInfoList() != null){
			 System.out.println("size:"+ db.getPlanInfoList().size());
			 //System.out.println("train:"+ db.getPlanInfoList());
		 }
		 */
		// System.out.println(db.rMsg.getTeamID()+"::"+rMsg.getStationID()+"::"+ rMsg.getTrainPlan());
		//asynP2PMessage(db.);
	}

	
	
}
