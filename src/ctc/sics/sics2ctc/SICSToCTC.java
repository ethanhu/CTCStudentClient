package ctc.sics.sics2ctc;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.swt.widgets.Display;

import ctc.constant.*;
import ctc.ctc.rsb.data.TrainDistrict;
import ctc.ctc.rsb.model.RSBModel;
import ctc.sics.SicsMain;
import ctc.sics.station.elements.common.*;
import ctc.sics.station.autosystem.SelfDisciplineSystem;
import ctc.sics.station.data.ParamFlag;
import ctc.sics.station.drawstation.DrawStationGraph;
import ctc.sics.stationLayout.StationLayoutInit;
import ctc.transport.SynClientSupport;
import ctc.transport.message.*;
import ctc.util.ErrorLog;

public class SICSToCTC {

	static StationLayoutInit stationLayoutInit = StationLayoutInit.getInstance(); 
	
	static ProCTCMessage proModel = ProCTCMessage.getInstance();
	
	
	//------------hu-2010-7-15--------start---------//
	//	SICS发送CommonMessage消息到CTC
	public static void sendCommonMessageToCTC(CommonMessage sMsg) {
		
		sMsg.setCommandMode(Constants.MODE_CS_ASYN_CLIENT);// 通信模式 异步
		sMsg.setCommandType(Constants.TYPE_SICS_TO_CTC_ASYN);// 通信类别 CTC异步发向SICS
		sMsg.setUserRole(Constants.USER_ROLE_SICS);// 用户角色 SICS
		sMsg.setTerType(Constants.TERMINAL_TYPE_SICS);// SICS
		
		if (SicsMain.db != null) {
			sMsg.setTeamID(SicsMain.db.getTeamID()); // 本站的组
		}else{
			ErrorLog.log("SICS错误：sendCommonMessageToCTC(): CTCMain.db == null");
		}
		
		// 异步发送到服务器
		if (SicsMain.db != null) {
			if(SicsMain.db.getAsynClientSupport() != null){
				ErrorLog.log("SICS：发送CommonMessage信息到:" + sMsg.getStationName());
				SicsMain.db.getAsynClientSupport().sendCommonMessage(sMsg);// 异步通信
			}else{
				ErrorLog.log("SICS错误：sendCommonMessageToCTC(): SicsMain.db.getAsynClientSupport() == null");
			}			
		}else{
			ErrorLog.log("SICS错误：sendCommonMessageToCTC(): SicsMain.db == null");
		}
	}
	
	//	SICS发送CommonMessage消息到ZNTDCS
	public static void sendCommonMessageToZNTDCS(CommonMessage sMsg) {
		
		sMsg.setCommandMode(Constants.MODE_CS_ASYN_CLIENT);// 通信模式 异步
		sMsg.setCommandType(Constants.TYPE_SICS_TO_ZNTDCS_ASYN);// 通信类别 CTC异步发向SICS
		sMsg.setUserRole(Constants.USER_ROLE_SICS);// 用户角色 SICS
		sMsg.setTerType(Constants.TERMINAL_TYPE_SICS);// SICS
		
		if (SicsMain.db != null) {
			sMsg.setTeamID(SicsMain.db.getTeamID()); // 本站的组
		}else{
			ErrorLog.log("SICS错误：sendCommonMessageToZNTDCS(): CTCMain.db == null");
		}
		
		// 异步发送到服务器
		if (SicsMain.db != null) {
			if(SicsMain.db.getAsynClientSupport() != null){
				ErrorLog.log("SICS：发送CommonMessage信息到:" + sMsg.getStationName());
				SicsMain.db.getAsynClientSupport().sendCommonMessage(sMsg);// 异步通信
			}else{
				ErrorLog.log("SICS错误：sendCommonMessageToZNTDCS(): SicsMain.db.getAsynClientSupport() == null");
			}			
		}else{
			ErrorLog.log("SICS错误：sendCommonMessageToZNTDCS(): SicsMain.db == null");
		}
	}
	

	// 接收到P2PCommandMessage
	public static void receiceP2PCommandMessage(P2PCommandMessage rMsg) {

		ErrorLog.log("SICS：CTCToSICS: receiceP2PCommandMessage()--收到--");
		
		TrainDistrict trainDis;
		List<TrainDistrict> trainDisList = new ArrayList<TrainDistrict>();
		SelfDisciplineSystem disSystem = SelfDisciplineSystem.getInstance();
		trainDisList = disSystem.getTrainDisList();
		
		trainDis = new TrainDistrict();
		trainDis.setIndex(0);
		trainDis.setTrainName(rMsg.getTrainName());		
		trainDis.setDirection(rMsg.getDirection());
		trainDis.setDistrict(rMsg.getDistrict());
		trainDisList.add(trainDis);		
		
	}
	
	
	
	
	
	
	
	//------------hu-2010-7-15--------end---------//
	
	
	
	
	//通信示例 hu
	//异步发送到服务器
	public static void sendErrorMessageASYN(String figName, boolean type){
		//组装发送报文
		ErrorMessage sMsg = new ErrorMessage(); 
		sMsg.setCommandMode(Constants.MODE_CS_ASYN_CLIENT);//通信模式   异步
		sMsg.setCommandType(Constants.TYPE_SICS_TO_CTC_ASYN);//通信类别  SICS异步发向CTC
		sMsg.setUserRole(Constants.USER_ROLE_SICS);//用户角色  SICS用户

		sMsg.setTerType(Constants.TERMINAL_TYPE_SICS);//普通站终端;
		if (SicsMain.getDb() != null){
			sMsg.setTeamID(SicsMain.getDb().getTeamID()); // 本站的组
			sMsg.setStationName(SicsMain.getDb().getStationName());
		}
		sMsg.setFigName(figName);
		sMsg.setType(type);
				
		//异步发送到服务器
		if(SicsMain.db != null){
			if(SicsMain.db.getAsynClientSupport() != null){				
				SicsMain.db.getAsynClientSupport().errorMessage(sMsg);
			}
		}		
	}
	
	//通信示例 hu
	public static void receiveErrorMessageASYN(ErrorMessage rMsg){
		
		ErrorLog.log("--SICS收到ErrorMessage--");
		
		final ErrorMessage msg = rMsg;	
		
		Display.getDefault().syncExec(
				new Runnable() {
					public void run() {
						
						if(msg.isType() == true){ //恢复故障
							proModel.HFGZ(msg.getFigName());
						}else{ //设置故障
							proModel.SZGZ(msg.getFigName());
						}
						//sendTaskResponseMessageASYN(true);
					}
				});				
		
	}	
	
	//通信示例 hu
	//异步发送到服务器
	public static void sendTrainArriveMessageASYN(String trackLineName){
		//组装发送报文
		TrainArriveMessage sMsg = new TrainArriveMessage(); 
		sMsg.setCommandMode(Constants.MODE_CS_ASYN_CLIENT);//通信模式   异步
		sMsg.setCommandType(Constants.TYPE_SICS_TO_CTC_ASYN);//通信类别  SICS异步发向CTC
		sMsg.setUserRole(Constants.USER_ROLE_SICS);//用户角色  SICS用户

		sMsg.setTerType(Constants.TERMINAL_TYPE_SICS);//普通站终端;
		if (SicsMain.getDb() != null){
			sMsg.setTeamID(SicsMain.getDb().getTeamID()); // 本站的组
			sMsg.setStationName(SicsMain.getDb().getStationName());
		}

		sMsg.setTrackLineName(trackLineName);
				
		//异步发送到服务器
		if(SicsMain.db != null){
			if(SicsMain.db.getAsynClientSupport() != null){				
				SicsMain.db.getAsynClientSupport().trainArriveMessage(sMsg);
			}
		}		
	}
	
	//通信示例 hu
	public static void receiveTrainArriveMessageASYN(TrainArriveMessage rMsg){
		
		ErrorLog.log("SICS收到TrainArriveMessage");
		
		final TrainArriveMessage msg = rMsg;	
		
		Display.getDefault().syncExec(
				new Runnable() {
					public void run() {
						
						Figure fig = proModel.getFigureObjectByName(msg.getTrackLineName());
						TrackLine line = (TrackLine)fig;
						
						line.setColorStatus(ParamFlag.trackline_red);
						
						if(line.getStatus() == 0){ //空闲
							sendTaskResponseMessageASYN(true);
						}else{ //占用
							sendTaskResponseMessageASYN(false);
						}
						
					}
				});				
		
	}	
	
	//通信示例 hu
	//异步发送到服务器
	public static void sendTaskMessageASYN(String startName, String endName, int color){
		
		//组装发送报文
		TaskMessage sMsg = new TaskMessage(); 
		sMsg.setCommandMode(Constants.MODE_CS_ASYN_CLIENT);//通信模式   异步
		sMsg.setCommandType(Constants.TYPE_SICS_TO_CTC_ASYN);//通信类别  SICS异步发向CTC
		sMsg.setUserRole(Constants.USER_ROLE_SICS);//用户角色  SICS用户
		sMsg.setTerType(Constants.TERMINAL_TYPE_SICS);//普通站终端;
		
		if (SicsMain.getDb() == null){
			System.out.println("SICSToCTC:sendTaskMessageASYN():SicsMain.getDb() == null");
			return;
		}
		sMsg.setTeamID(SicsMain.getDb().getTeamID()); // 本站的组
		sMsg.setStationName(SicsMain.getDb().getStationName());
		sMsg.setStartName(startName);
		sMsg.setEndName(endName);
		sMsg.setColor(color);
		
		//异步发送到服务器
		if(SicsMain.db != null){
			if(SicsMain.db.getAsynClientSupport() != null){	
				ErrorLog.log("SICSToCTC.java  SICS发送TaskMessage到CTC " + SicsMain.getDb().getStationName());
				if(SicsMain.db.getAsynClientSupport() == null){
					ErrorLog.log("SICSToCTC.java  SICS发送TaskMessage到CTC  SicsMain.db.getAsynClientSupport() == null ");
					return;
				}
				SicsMain.db.getAsynClientSupport().taskMessage(sMsg);
			}
		}		
	}
	
	//通信示例 hu
	public static void receiveTaskMessageASYN(TaskMessage rMsg){
		
		ErrorLog.log("SICS收到CTC发来的TaskMessage");
		
		final TaskMessage msg = rMsg;	
		
		Display.getDefault().syncExec(
				new Runnable() {
					public void run() {
						int result = proModel.processTask(msg.getStartName(), msg.getEndName(), msg.getColor());
						/*
						boolean proResult = false;
						if(result == 0 && msg.getColor() == ParamFlag.road_green){
							proResult = true;
						}else if(result == 1 && msg.getColor() == ParamFlag.road_blue){
							proResult = true;
						}
						sendTaskResponseMessageASYN(proResult);
						*/
					}
				});				
		
	}	
	
	
	//通信示例 hu
	//异步发送到服务器
	public static void sendTaskResponseMessageASYN(boolean result){
		
		//组装发送报文
		TaskResponseMessage sMsg = new TaskResponseMessage(); 
		sMsg.setCommandMode(Constants.MODE_CS_ASYN_CLIENT);//通信模式   异步
		sMsg.setCommandType(Constants.TYPE_SICS_TO_CTC_ASYN);//通信类别  SICS异步发向CTC
		sMsg.setUserRole(Constants.USER_ROLE_SICS);//用户角色  SICS用户
		sMsg.setTerType(Constants.TERMINAL_TYPE_SICS);//普通站终端;
		
		if (SicsMain.getDb() != null){
			sMsg.setTeamID(SicsMain.getDb().getTeamID()); // 本站的组
			sMsg.setStationName(SicsMain.getDb().getStationName());
		}
		
		sMsg.setProcResult(result);
		
		//异步发送到服务器
		if(SicsMain.db != null){
			if(SicsMain.db.getAsynClientSupport() != null){				
				SicsMain.db.getAsynClientSupport().taskResponseMessage(sMsg);
			}
		}		
	}
		
	//通信示例 hu
	public static void receiveTaskResponseMessageASYN(TaskResponseMessage rMsg){
		
		ErrorLog.log("SICS收到TaskResponseMessage");
		
		final TaskResponseMessage msg = rMsg;
					
		Display.getDefault().syncExec(
				new Runnable() {
					public void run() {
						
					}
				});				
		
	}	
	
	
	
	
	//通信示例
	//异步发送到服务器
	public static void sendStationControlMessageASYN(String appType){
		//组装发送报文
		StationControlMessage sMsg = new StationControlMessage(); 
		sMsg.setCommandMode(Constants.MODE_CS_ASYN_CLIENT);//通信模式   异步
		sMsg.setCommandType(Constants.TYPE_SICS_TO_CTC_ASYN);//通信类别  SICS异步发向CTC
		sMsg.setUserRole(Constants.USER_ROLE_SICS);//用户角色  SICS用户

		sMsg.setTerType(Constants.TERMINAL_TYPE_SICS);//普通站终端;
		sMsg.setAppType(appType); //站控类别（站控和非常站控）		
		if (SicsMain.getDb() != null){
			sMsg.setTeamID(SicsMain.getDb().getTeamID()); // 本站的组
			sMsg.setStationName(SicsMain.getDb().getStationName());
		}
		//异步发送到服务器
		SicsMain.db.getAsynClientSupport().stationControlMessage(sMsg);
		
	}
	//通信示例
	public static void receiveStationControlMessageASYN(StationControlResponseMessage rMsg){
		ErrorLog.log("SICS收到StationControlResponseMessage");
		
		final Boolean result = rMsg.isProcResult();
		
		if(result == true){
			ParamFlag.sys_auto = false;
		}else{
			ParamFlag.sys_auto = true;
		}
				
		Display.getDefault().syncExec(
				new Runnable() {
					public void run() {
						DrawStationGraph.showStationControlMsg(result);
					}
				});				
		
	}	
	
	/////////////////////////////////////////////////////////
	//异步方式 向下一站发送消息 P2PCommandMessage
	public static void asynP2PMessageToDown(P2PCommandMessage sMsg) {
		
		ErrorLog.log("-SICSToCTC: --start-asynP2PMessageToDown发送P2PCommandMessage消息--");
		
		sMsg.setTerType(Constants.TERMINAL_TYPE_SICS);//普通站终端;
		sMsg.setCommandMode(Constants.MODE_CS_ASYN_CLIENT);// 异步
		sMsg.setCommandType(Constants.TYPE_CLIENT_P2P_ASYN_DOWN);// 发向下一站
		sMsg.setUserRole(Constants.USER_ROLE_STUDENT);// 目前没有处理
		sMsg.setResult(Constants.CLIENT_RESULT_OK);//本学员操作的结果 操作正确

		if(SicsMain.db == null){
			System.out.println("SICS: SICSToCTC: asynP2PMessageToDown(): SicsMain.db == null");
			return;
		}
		sMsg.setTeamID(SicsMain.db.getTeamID());
		if(SicsMain.db.getAsynClientSupport() != null){
			SicsMain.db.getAsynClientSupport().p2pCommandMessageSend(sMsg);// 异步通信
		}else{
			ErrorLog.log("-SICSToCTC错误: --SicsMain.db.getAsynClientSupport()==null--");
			return;
		}
		
		ErrorLog.log("-SICSToCTC: --end-asynP2PMessageToDown发送P2PCommandMessage消息--");
	}
	
	
	// 异步方式 向上一站发送应答消息
	public void asynP2PMessageToUp(P2PCommandMessage rMsg) {

		P2PCommandMessage sMsg = new P2PCommandMessage();

		sMsg.setCommandMode(Constants.MODE_CS_ASYN_CLIENT);// 异步
		sMsg.setCommandType(Constants.TYPE_CLIENT_P2P_ASYN_UP);// 发向上一站
																// Reply确认消息
		sMsg.setUserRole(Constants.USER_ROLE_STUDENT);// 目前没有处理
		sMsg.setResult(Constants.CLIENT_RESULT_OK);// 本学员操作的结果 操作正确

		// 从服务器传送过来的参数值
		sMsg.setUserName(SicsMain.db.getUserName());// 学员账号
		sMsg.setRunMode(rMsg.getRunMode());// 系统运行方式
		sMsg.setDistrictName(rMsg.getDistrictName());// 车站区段ID
		sMsg.setTeamID(rMsg.getTeamID());// 本站所在组
		sMsg.setStationName(rMsg.getStationName());// 本站的车站ID
		//sMsg.setVrTime(vrTime);
	    //sMsg.setTimeStep(timeStep);

		sMsg.setTrainName(rMsg.getTrainName());// 车次ID
		ErrorLog.log("发送到UP::" + SicsMain.db.getUserName());

		SicsMain.db.getAsynClientSupport().p2pCommandMessageSend(sMsg);// 异步通信
	}
     //接收由SICS或CTC发送的一站信息
	public static void receiveTrainFromUp(P2PCommandMessage rMsg) {
		ErrorLog.log("SICS收到上一站信息:" + rMsg.getStationName());
		//stationLayoutInit.acceptedTrainFromUp(rMsg);
	}
	
	//接收到自己发向其他站的,由其他站发送的应答消息
	public static void receiveTrainFromDown(P2PCommandMessage rMsg){
		//stationLayoutInit.receiveTrainFromDown(rMsg);
	}
	
	//当教师选取开始实验时， 服务器发来的首站信息rMsg  OK
	public static void startRun(P2PCommandResponseMessage rMsg) {
		stationLayoutInit.startRun(rMsg);
	}
	
	//SICS收到CTC发来的有关车站状态变化的消息 OK
	public static void CTCToSICS(final CTCToSICSRequestMessage msg) {
		
		Display.getDefault().syncExec(
				new Runnable() {
					public void run() {
						ProcessCTCMessage.changeSICSBaseCTCMsg(msg.getStationName(), msg.getTaskName(), msg.getDirectionName(), msg.getTrackName());
					}
				});	
		
		ErrorLog.log("SICS收到CTC发来的有关车站状态变化的消息:" + msg.getStationName()+"::"+msg.getTaskName());		
	}
	
	
	//组装SICS发向CTC的有关车站状态变化的消息
	public static void SICSToCTCChangeStationStatus(String trainName,String stationName, String taskName, String directionName, String trackName){
		SICSToCTCRequestMessage msg = new SICSToCTCRequestMessage();
		msg.setMethodName("operateTask");//设置方法名
		msg.setTeam_id(SicsMain.db.getTeamID());//组信息
		msg.setTrainName(trainName);//车次信息
		msg.setStationName(stationName);//车站名称
		msg.setTaskName(taskName); //任务名称
		msg.setDirectionName(directionName);//方向
		msg.setTrackName(trackName);//路名
		synSICSToCTCMessage(msg);
	}
	
	//SICS发送消息到CTC通信 同步
	private static boolean synSICSToCTCMessage(SICSToCTCRequestMessage msg){
		SynClientSupport syn = SicsMain.db.getSynClientSupport();
		msg.setCommandMode(Constants.MODE_CS_SYN_CLIENT);//同步通信模式
		msg.setCommandType(Constants.TYPE_SICS_TO_CTC_SYN);//通信类别
		return syn.SICSToCTCMessageSend(msg);
	}
}
