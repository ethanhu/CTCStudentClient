package ctc.tdcs.tdcsdbserver;

import ctc.tdcs.data.BaseParam;
import ctc.transport.AsynClientSupport;
import ctc.transport.MinaClient;
import ctc.transport.SynClientSupport;
import ctc.transport.AsynClientHandler.*;
import ctc.transport.message.CommonMessage;
import ctc.transport.message.TDCSCommandMessage;
import ctc.transport.message.TeamTdcsRsbMessage;
import ctc.transport.message.TrainLineAnchorMessage;
import ctc.util.ErrorLog;

public class CallbackServer implements ZNTdcsCallback {

	BaseParam baseData = BaseParam.getInstance();

	// 这里写的都是static变量，是否必要？？？？？？？
	private static SynClientSupport synClientSupport = new SynClientSupport(); // 同步通信句柄
	private static AsynClientSupport asynClientSupport = new AsynClientSupport(); // 异步通信句柄
	private static MinaClient minaClient = new MinaClient();

	private static String userName; // 学员账号
	private static int runMode; // 系统运行方式
	private static String districtName; // 车站区段ID
	private static int teamID; // 组ID

	private CallbackServer thisData = null;

	public CallbackServer getInstance() {
		if (thisData == null) {
			thisData = new CallbackServer();
		}
		return thisData;
	}
	
	
	
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

	////////////////////////////////////////////////////////////////////////////
	// //////

	@Override
	public void receivedTDCSCommandMessage(TDCSCommandMessage rMsg) {
		// 目前没有用 将来用做接受
		ErrorLog.log("客户端TDCS：CallbackServer收到CommonMessage: 函数 = receivedTDCSCommandMessage");
	}

	@Override
	public void receivedSQLMessage(String result) {
		// 目前没有用
		ErrorLog.log("客户端TDCS：CallbackServer收到CommonMessage: 函数 = receivedSQLMessage");
	}

	/** 处理服务器端转发来的TeamTdcsRsbMessage */
	@Override
	public void receivedTeamTdcsRsbMessage(TeamTdcsRsbMessage rMsg) {
		ErrorLog.log("客户端TDCS：CallbackServer收到CommonMessage: 函数 = receivedTeamTdcsRsbMessage");
	}

	/** 接受服务器转发来普通站机发送给TDCS的TrainLineAnchorMessage消息 */
	@Override
	public void receivedTrainLineAnchorMessage(TrainLineAnchorMessage rMsg) {
		// hu 2010-11-2
		// new RelocationPlanTrain().drawTrainLine(rMsg);
		ErrorLog.log("客户端TDCS：CallbackServer收到CommonMessage: 函数 = receivedTrainLineAnchorMessage");
	}
		
	//hu 2010-11-3
	@Override
	public void tdcsReceivedCommonMessage(CommonMessage rMsg) {
		ErrorLog.log("客户端TDCS：CallbackServer收到CommonMessage: 函数 = tdcsReceivedCommonMessage --0--");
	}

	// ------------hu-2010-7-15--------start---------//
	// TDCS收到CommonMessage消息
	public void tdcsReceiveCommonMessage(CommonMessage rMsg) {

		ErrorLog.log("客户端TDCS：CallbackServer收到CommonMessage: 函数 = tdcsReceiveCommonMessage");
/*
		String messageName = rMsg.getMeseageName();
		String message = rMsg.getMessage();

		if (messageName.equalsIgnoreCase("TrainLineAnchorMessage")) {
			final TrainLineAnchorMessage msg = (TrainLineAnchorMessage) JsonUtil.getObject4JsonString(message, TrainLineAnchorMessage.class);
			ErrorLog.log("TDCS：CallbackServer收到CommonMessage消息 trainName = " + msg.getTrainName());
			ErrorLog.log("TDCS：CallbackServer收到CommonMessage消息 realTime = " + msg.getRTime());
			ErrorLog.log("TDCS：CallbackServer收到CommonMessage消息 diection = " + msg.getTrainDirection());

			Display.getDefault().syncExec(new Runnable() {
				public void run() {
					// new RelocationPlanTrain().drawTrainLine(msg);
				}
			});
		} else if (messageName.equalsIgnoreCase("ScheduleErrorMessage")) {
			ScheduleErrorMessage msg = (ScheduleErrorMessage) JsonUtil.getObject4JsonString(message, ScheduleErrorMessage.class);
			ErrorLog.log("客户端TDCS：CallBackServer收到CommonMessage消息 Content = " + msg.getContent());

		} else {
			ErrorLog.log("TDCS：CallbackServer收到CommonMessage消息 messageName != TrainLineAnchorMessage");
		}
*/
		
	}
 


}
