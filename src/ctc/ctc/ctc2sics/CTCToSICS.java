package ctc.ctc.ctc2sics;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;

import ctc.constant.Constants;
import ctc.ctc.CTCMain;
import ctc.ctc.rsb.data.TrainDistrict;
import ctc.ctc.rsb.model.RSBModel;
import ctc.ctc.stationLayout.StationLayoutInit;
import ctc.sics.SicsMain;
import ctc.sics.ui.SicsMainWindow;
import ctc.transport.SynClientSupport;
import ctc.transport.message.*;
import ctc.util.ErrorLog;
import ctc.ctc.drawctc.ctcmain.data.BaseParam;
import ctc.ctc.drawctc.ctcmain.drawctcmain.StationControlDialog;

public class CTCToSICS {

	final static BaseParam baseData = BaseParam.getInstance();
	static ProSICSMessage proModel = ProSICSMessage.getInstance();
	
	public static void showMsg(String str){
		MessageBox mb = new MessageBox(baseData.getMainShell(), SWT.ABORT | SWT.ICON_INFORMATION);
		mb.setText("提示信息");//消息框的标题
		mb.setMessage(str);//消息框的提示文字
		mb.open();
	}
	
	
	//------------hu-2010-7-15--------start---------//
	//CTC发送CommonMessage消息到服务器
	public static void sendCommonMessage(CommonMessage sMsg) {
		
		sMsg.setCommandMode(Constants.MODE_CS_ASYN_CLIENT);// 通信模式 异步
		sMsg.setCommandType(Constants.TYPE_CTC_TO_SICS_ASYN);// 通信类别 CTC异步发向SICS
		sMsg.setUserRole(Constants.USER_ROLE_CTC);// 用户角色 CTC用户
		sMsg.setTerType(Constants.TERMINAL_TYPE_CTC);// CTC中心	
		
		if (CTCMain.db != null) {
			sMsg.setTeamID(CTCMain.db.getTeamID()); // 本站的组
		}else{
			ErrorLog.log("CTC错误：sendCommonMessage(): CTCMain.db == null");
			return;
		}
		
		// 异步发送到服务器
		if (CTCMain.db != null) {
			if(CTCMain.db.getAsynClientSupport() != null){
				ErrorLog.log("CTC：发送CommonMessage信息到:" + sMsg.getStationName());
				CTCMain.db.getAsynClientSupport().sendCommonMessage(sMsg);// 异步通信
			}else{
				ErrorLog.log("CTC错误：sendCommonMessage():CTCMain.db.getAsynClientSupport() == null");
				return;
			}			
		}else{
			ErrorLog.log("CTC错误：sendCommonMessage(): CTCMain.db == null");
		}
	}
	
	
	
	// 接收到P2PCommandMessage
	public static void receiceP2PCommandMessage(P2PCommandMessage rMsg) {

		ErrorLog.log("CTC：CTCToSICS: receiceP2PCommandMessage()");
		
		TrainDistrict trainDis;
		List<TrainDistrict> trainDisList = new ArrayList<TrainDistrict>();
		RSBModel rsbModel = RSBModel.getInstance();
		trainDisList = rsbModel.getTrainDisList();
		
		trainDis = new TrainDistrict();
		trainDis.setIndex(0);
		trainDis.setTrainName(rMsg.getTrainName());		
		trainDis.setDirection(rMsg.getDirection());
		trainDis.setDistrict(rMsg.getDistrict());
		trainDisList.add(trainDis);		
		
	}
	
	
	//------------hu-2010-7-15--------end---------//
	
	
	
	
	
	// 通信示例 hu
	public static void sendErrorMessageASYN(String stationName, String figName, boolean type) {

		// 组装发送报文
		ErrorMessage sMsg = new ErrorMessage();
		sMsg.setCommandMode(Constants.MODE_CS_ASYN_CLIENT);// 通信模式 异步
		sMsg.setCommandType(Constants.TYPE_CTC_TO_SICS_SYN);// 通信类别 CTC异步发向SICS
		sMsg.setUserRole(Constants.USER_ROLE_CTC);// 用户角色 CTC用户
		sMsg.setTerType(Constants.TERMINAL_TYPE_CTC);// CTC中心		
		
		if (CTCMain.getDb() != null) {
			sMsg.setTeamID(CTCMain.getDb().getTeamID()); // 本站的组
		}

		sMsg.setStationName(stationName);
		sMsg.setFigName(figName);
		sMsg.setType(type);
		
		// 异步发送到服务器
		if (CTCMain.db != null) {
			if(CTCMain.db.getAsynClientSupport() != null){
				ErrorLog.log("CTC发送ErrorMessage信息到:" + stationName);
				CTCMain.db.getAsynClientSupport().errorMessage(sMsg);// 异步通信
			}			
		}
	}

	// 通信示例 hu
	public static void ctcReceiveErrorMessageASYN(ErrorMessage rMsg) {
		
		ErrorLog.log("CTCToSICS.java: CTC收到ErrorMessage信息:" + rMsg.getStationName());

		final ErrorMessage msg = rMsg;

		Display.getDefault().syncExec(new Runnable() {
			public void run() {				
				proModel.proErrorMessage(msg);
				//sendTaskResponseMessageASYN(true);
			}
		});

	}
	
	// 通信示例 hu
	// 异步发送到服务器
	public static void sendTrainArriveMessageASYN(String stationName, String trackLineName) {

		// 组装发送报文
		TrainArriveMessage sMsg = new TrainArriveMessage();
		sMsg.setCommandMode(Constants.MODE_CS_ASYN_CLIENT);// 通信模式 异步
		sMsg.setCommandType(Constants.TYPE_CTC_TO_SICS_SYN);// 通信类别 CTC异步发向SICS
		sMsg.setUserRole(Constants.USER_ROLE_CTC);// 用户角色 CTC用户
		sMsg.setTerType(Constants.TERMINAL_TYPE_CTC);// CTC中心		
		
		if (CTCMain.getDb() != null) {
			sMsg.setTeamID(CTCMain.getDb().getTeamID()); // 本站的组
		}

		sMsg.setStationName(stationName);
		sMsg.setTrackLineName(trackLineName);		
		
		// 异步发送到服务器
		if (CTCMain.db != null) {
			if(CTCMain.db.getAsynClientSupport() != null){
				CTCMain.db.getAsynClientSupport().trainArriveMessage(sMsg);// 异步通信
			}			
		}
	}

	// 通信示例 hu
	public static void ctcReceiveTrainArriveMessageASYN(TrainArriveMessage rMsg) {
		
		ErrorLog.log("CTC收到TrainArriveMessage信息:" + rMsg.getStationName());

		System.out.println("CTC收到TaskMessage信息 " + rMsg.getStationName());
		
		final TrainArriveMessage msg = rMsg;

		Display.getDefault().syncExec(new Runnable() {
			public void run() {
				//proModel.proTaskMessage(msg);
			}
		});

	}
	
	// 通信示例 hu
	// 异步发送到服务器
	public static void sendTaskMessageASYN(String stationName, String startName, String endName, int color) {

		// 组装发送报文
		TaskMessage sMsg = new TaskMessage();
		sMsg.setCommandMode(Constants.MODE_CS_ASYN_CLIENT);// 通信模式 异步
		sMsg.setCommandType(Constants.TYPE_CTC_TO_SICS_ASYN);// 通信类别 CTC异步发向SICS
		sMsg.setUserRole(Constants.USER_ROLE_CTC);// 用户角色 CTC用户
		sMsg.setTerType(Constants.TERMINAL_TYPE_CTC);// CTC中心		
		
		if (CTCMain.getDb() == null) {
			System.out.println("CTCToSICS:sendTaskMessageASYN():CTCMain.getDb() == null");
			return;
		}
		sMsg.setTeamID(CTCMain.getDb().getTeamID()); // 本站的组
		sMsg.setStationName(stationName);
		sMsg.setStartName(startName);
		sMsg.setEndName(endName);
		sMsg.setColor(color);
		
		// 异步发送到服务器
		if (CTCMain.db != null) {
			if(CTCMain.db.getAsynClientSupport() != null){
				ErrorLog.log("CTC发送TaskMessage信息到SICS" + stationName);
				CTCMain.db.getAsynClientSupport().taskMessage(sMsg);// 异步通信
			}			
		}
	}

	// 通信示例 hu
	public static void ctcReceiveTaskMessageASYN(TaskMessage rMsg) {
		
		ErrorLog.log("CTCToSICS.java 收到TaskMessage信息:车站=" + rMsg.getStationName());

		final TaskMessage msg = rMsg;

		Display.getDefault().syncExec(new Runnable() {
			public void run() {
				proModel.proTaskMessage(msg);
			}
		});

	}
	
	// 通信示例 hu
	// 异步发送到服务器
	public static void sendTaskResponseMessageASYN(String stationName, boolean result) {

		// 组装发送报文
		TaskResponseMessage sMsg = new TaskResponseMessage();
		sMsg.setCommandMode(Constants.MODE_CS_ASYN_CLIENT);// 通信模式 异步
		sMsg.setCommandType(Constants.TYPE_CTC_TO_SICS_SYN);// 通信类别 CTC异步发向SICS
		sMsg.setUserRole(Constants.USER_ROLE_CTC);// 用户角色 CTC用户
		sMsg.setTerType(Constants.TERMINAL_TYPE_CTC);// CTC中心
	
		if (CTCMain.getDb() != null) {
			sMsg.setTeamID(CTCMain.getDb().getTeamID()); // 本站的组
		}
		
		sMsg.setStationName(stationName);
		sMsg.setProcResult(result);
		
		// 异步发送到服务器
		if (CTCMain.db != null) {
			if(CTCMain.db.getAsynClientSupport() != null){
				CTCMain.db.getAsynClientSupport().taskResponseMessage(sMsg);// 异步通信
			}			
		}
	}

	// 通信示例 hu
	public static void ctcReceiveTaskResponseMessageASYN(TaskResponseMessage rMsg) {
		
		ErrorLog.log("CTC收到TaskResponseMessage信息:" + rMsg.getStationName());
		
		final TaskResponseMessage msg = rMsg;
		
		Display.getDefault().syncExec(new Runnable() {
			public void run() {
				if(msg.isProcResult() == true){
					showMsg("任务执行成功！");
				}else{
					showMsg("任务执行失败！");
				}
			}
		});

	}

	
	
	
	
	// 通信示例
	// 异步发送到服务器
	public static void sendStationControlResponseMessageASYN(String stationName, boolean procResult, String appType) {

		// 组装发送报文
		StationControlResponseMessage sMsg = new StationControlResponseMessage();
		sMsg.setCommandMode(Constants.MODE_CS_ASYN_CLIENT);// 通信模式 异步
		sMsg.setCommandType(Constants.TYPE_CTC_TO_SICS_SYN);// 通信类别 CTC异步发向SICS
		sMsg.setUserRole(Constants.USER_ROLE_CTC);// 用户角色 CTC用户

		sMsg.setTerType(Constants.TERMINAL_TYPE_CTC);// CTC中心
		sMsg.setAppType(appType); // 设置申请类别: 站控和非常站控
		sMsg.setStationName(stationName);
		sMsg.setProcResult(procResult);
		if (CTCMain.getDb() != null) {
			sMsg.setTeamID(CTCMain.getDb().getTeamID()); // 本站的组
		}

		// 异步发送到服务器
		if (CTCMain.db != null) {
			CTCMain.db.getAsynClientSupport().stationControlResponseMessage(sMsg);// 异步通信
		}

	}

	// 通信示例
	public static void receiveStationControlMessageASYN(StationControlMessage rMsg) {
		ErrorLog.log("CTC收到StationControlMessage信息:" + rMsg.getStationName());

		final StationControlMessage msg = rMsg;

		Display.getDefault().syncExec(new Runnable() {
			public void run() {

				StationControlDialog stationControlDialog = new StationControlDialog(baseData.getMainShell());
				if (msg.getAppType().equalsIgnoreCase("SQZK")) {
					stationControlDialog.openSQZK(msg.getStationName()); // 打开申请站控窗口
				} else {
					stationControlDialog.openFCZK(msg.getStationName()); // 打开非常站控窗口
				}
			}
		});

	}

	// 当教师选取开始实验时， 服务器发来的首站信息rMsg OK
	public static void receicedFirstStation(final P2PCommandResponseMessage rMsg) {
		ErrorLog.log("CTC收到首站信息:" + rMsg.getStationName());
		// StationLayoutInit stationLayoutInit =
		// StationLayoutInit.getInstance();
		Display.getDefault().syncExec(new Runnable() {
			public void run() {
				StationLayoutInit.startRun(rMsg);
			}
		});
	}

	// 接收普通车站SICS发来的有关下一站的信息 OK
	public static void receicedTrainFromUp(P2PCommandMessage rMsg) {
		
		ErrorLog.log("CTCToSICS: 收到上一站的P2PCommandMessage信息:" + rMsg.getStationName());
		
		TrainDistrict trainDis = new TrainDistrict();
		trainDis.setTrainName(rMsg.getTrainName());
		trainDis.setIndex(0);
		trainDis.setDistrict(rMsg.getDistrict());
		trainDis.setDirection(rMsg.getDirection());
		RSBModel.getInstance().getTrainDisList().add(trainDis);

	}

	// 发送消息sMsg到下一站 No
	public static void asynP2PMessageDispatch(P2PCommandMessage sMsg) {
		sMsg.setCommandMode(Constants.MODE_CS_ASYN_CLIENT);// 异步
		sMsg.setCommandType(Constants.TYPE_CLIENT_P2P_ASYN_DOWN);// 发向下一站
		sMsg.setTerType(Constants.TERMINAL_TYPE_CTC_SWITCH);// CTC中心
		CTCMain.db.getAsynClientSupport().p2pCommandMessageSend(sMsg);// 异步通信
	}

	// 接收到自己发向其他站的,由其他站发送的应答消息rMsg No
	public static void receicedTrainFromDown(P2PCommandMessage rMsg) {

	}

	// 发送消息sMsg到下一站
	public static void asynP2PMessageToDown(P2PCommandMessage sMsg) {

		sMsg.setCommandMode(Constants.MODE_CS_ASYN_CLIENT);// 异步
		sMsg.setCommandType(Constants.TYPE_CLIENT_P2P_ASYN_DOWN);// 发向下一站
		sMsg.setTerType(Constants.TERMINAL_TYPE_CTC);// CTC中心
		CTCMain.db.getAsynClientSupport().p2pCommandMessageSend(sMsg);// 异步通信
	}

	// 组装CTC发向SICS的有关车站状态变化的消息 OK
	public static void CTCToSICSChangeStationStatus(String trainName, String stationName, String taskName, String directionName, String trackName) {
		CTCToSICSRequestMessage msg = new CTCToSICSRequestMessage();
		msg.setMethodName("operateTask");// 设置方法名
		msg.setTeam_id(CTCMain.db.getTeamID()); // 组信息
		msg.setTrainName(trainName);// 车次信息
		msg.setStationName(stationName);// 车站名称
		msg.setTaskName(taskName); // 任务名称
		msg.setDirectionName(directionName);// 方向
		msg.setTrackName(trackName);// 路名
		synCTCToSICSMessage(msg);
	}

	// CTC同步发送消息到服务器 OK
	private static boolean synCTCToSICSMessage(CTCToSICSRequestMessage msg) {
		SynClientSupport syn = CTCMain.db.getSynClientSupport();
		msg.setCommandMode(Constants.MODE_CS_SYN_CLIENT);// 同步通信模式
		msg.setCommandType(Constants.TYPE_CTC_TO_SICS_SYN);// 通信类别
		return syn.CTCToSICSMessageSend(msg);
	}

	// SICS发送到CTC的消息 SICSToCTCRequestMessage ok
	public static void SICSToCTCMessage(final SICSToCTCRequestMessage msg) {
		/*
		 * Display.getDefault().syncExec( new Runnable() { public void run() {
		 * //DrawOperate.operateTask(msg.getStationName(), msg.getTaskName(),
		 * msg.getDirectionName(), msg.getTrackName());
		 * DrawOperateForChange.operateTaskBaseSICSMsg(msg.getStationName(),
		 * msg.getTaskName(), msg.getDirectionName(), msg.getTrackName()); } });
		 */
		ErrorLog.log("CTC收到SICS发来的有关车站状态变化的消息:" + msg.getStationName());
	}

	// 可以写测试代码
	public static void main(String[] args) {
	}

}
