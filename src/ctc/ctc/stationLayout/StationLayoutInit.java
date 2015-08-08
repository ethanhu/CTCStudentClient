package ctc.ctc.stationLayout;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.*;

import ctc.constant.Constants;
import ctc.pojobean.Plan;
import ctc.ctc.CTCMain;
import ctc.transport.message.P2PCommandMessage;
import ctc.transport.message.P2PCommandResponseMessage;
import ctc.util.ErrorLog;
import ctc.util.JsonUtil;


//继承DataBase类的目的是实现Callback接口内所定义的方法（在DataBase中对所有接口已提供空实现），完成与服务器之间的异步通信
public class StationLayoutInit {

	public static String vrTime;
	public static String timeStep;

	private static StationLayoutInit stationLayoutInit = null;

	public static StationLayoutInit getInstance() {
		if (stationLayoutInit == null) {
			stationLayoutInit = new StationLayoutInit();
		}
		return stationLayoutInit;
	}

	public StationLayoutInit() {

	}

	// 异步方式 向下一站发送运行命令
	public void asynP2PMessageToDown(String trainName) {

		final P2PCommandMessage sMsg = new P2PCommandMessage();
		sMsg.setTerType(Constants.TERMINAL_TYPE_CTC);//CTC中心

		sMsg.setCommandMode(Constants.MODE_CS_ASYN_CLIENT);// 异步
		sMsg.setCommandType(Constants.TYPE_CLIENT_P2P_ASYN_DOWN);// 发向下一站
		sMsg.setUserRole(Constants.USER_ROLE_STUDENT);// 目前没有处理
		sMsg.setResult(Constants.CLIENT_RESULT_OK);// 本学员操作的结果 操作正确

		// 从服务器传送过来的参数值
		
		Display.getDefault().syncExec(new Runnable() {
			public void run() {
				sMsg.setUserName(CTCMain.db.getUserName());
				sMsg.setRunMode(CTCMain.db.getRunMode());
				sMsg.setDistrictName(CTCMain.db.getDistrictName());// 车站区段ID
				sMsg.setTeamID(CTCMain.db.getTeamID());// 本站所在组
			}
	    }); 

		//sMsg.setStationName(CTCMain.db.getStationName());// 本站的车站ID

		sMsg.setVrTime(vrTime);
		sMsg.setTimeStep(timeStep);

		sMsg.setTrainName(trainName); // 车次ID

		CTCMain.db.getAsynClientSupport().p2pCommandMessageSend(sMsg);// 异步通信

	}

	// 异步方式 向上一站发送应答消息
	public void asynP2PMessageToUp(P2PCommandResponseMessage rMsg) {

		P2PCommandMessage sMsg = new P2PCommandMessage();

		sMsg.setCommandMode(Constants.MODE_CS_ASYN_CLIENT);// 异步
		sMsg.setCommandType(Constants.TYPE_CLIENT_P2P_ASYN_UP);// 发向上一站
																// Reply确认消息
		sMsg.setUserRole(Constants.USER_ROLE_STUDENT);// 目前没有处理
		sMsg.setResult(Constants.CLIENT_RESULT_OK);// 本学员操作的结果 操作正确

		// 从服务器传送过来的参数值
		sMsg.setUserName(CTCMain.db.getUserName());// 学员账号
		sMsg.setRunMode(rMsg.getRunMode());// 系统运行方式
		sMsg.setDistrictName(rMsg.getDistrictName());// 车站区段ID
		sMsg.setTeamID(rMsg.getTeamID());// 本站所在组
		sMsg.setStationName(rMsg.getStationName());// 本站的车站ID
		sMsg.setVrTime(vrTime);
		sMsg.setTimeStep(timeStep);

		sMsg.setTrainName(rMsg.getTrainName());// 车次ID
		ErrorLog.log("发送到UP::" + CTCMain.db.getUserName());

		CTCMain.db.getAsynClientSupport().p2pCommandMessageSend(sMsg);// 异步通信
	}

	// 异步方法 接收上站发来的接车消息 然后发送收到的应答消息
	public void acceptedTrainFromUp(P2PCommandResponseMessage rMsg) {

		System.out.println("普通站，执行acceptedTrainFromUp");

		String trainName = rMsg.getTrainName();
		if (trainName == null) {
			trainName = "T11";
		}

		Display.getDefault().syncExec(new Runnable() {
			public void run() {
				//DrawTrainList.changeTrainLabel("T11");
				
			}
	    }); 

	}

	// 接收下站发来的应答消息
	public void acceptedReplyFromDown(P2PCommandResponseMessage rMsg) {

		ErrorLog.log("接收FromDown::" + CTCMain.db.getUserName());
	}

	// 接收服务器发来的启动消息 只有车次的首站才能接收到此消息
	public static void startRun(P2PCommandResponseMessage rMsg) {

		System.out.println("首站，执行startRun");
		System.out.println("startRun-开始 DB = " + CTCMain.db);		
		
		vrTime = rMsg.getVrTime();
		timeStep = rMsg.getTimeStep();
		
		List<Plan> planList = new ArrayList<Plan>(); 
		
		if(rMsg.getTrainPlan() == null){
    		planList = null;		
		}else{
			planList = JsonUtil.getList4Json(rMsg.getTrainPlan(), Plan.class);
		}
		
		int len = planList.size();
		for (int i = 0; i < len; i++) {
			String trainName = planList.get(i).getTrain_name();
			if (trainName == null) {
				trainName = "T11";
			} 
			
			//DrawCTCTrainPlanStation1.changeTrainLabel(trainName);
			
			//ctc.ctc.stationLayout1.DrawTrainList1.changeTrainLabel(trainName);
			
			//初始车次信息,将T11停在首站I股道上
			//TrainRunBetweenStation.initBetweenStationLine();
			//DrawOperate.operateTask("呼和浩特", "接车", "下行", "1");
			
			/*
			else {
				     DrawTrainList.changeTrainLabel(trainName);
				  	 Display.getDefault().syncExec(new Runnable() {
						public void run() {
							DrawTrainList.changeTrainLabel("T11");
														
						}
				  }); 			
			}
		*/
		}
	}
}
