package ctc.transport;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import ctc.constant.*;
import ctc.transport.message.*;
import ctc.util.ErrorLog;

/*
 * client端的业务代码 处理消息接收事件
 * 处理服务端通信的类，用于和服务端建立通信，处理由服务端发送过来的消息。
 * 
 *  异步接受服务器发来的消息
 */
public class AsynClientHandler extends IoHandlerAdapter {

	// 接口定义 其他程序需要接收服务器异步发来的消息
	public interface CTCCallback {
		void receicedFirstStation(P2PCommandResponseMessage rMsg);// CTC接收服务器发来的首站信息
		void receicedTrainFromUp(P2PCommandMessage rMsg);// 接收上站发来的接车消息
		void receicedTrainFromDown(P2PCommandMessage rMsg);
		void SICSToCTC(SICSToCTCRequestMessage rMsg);

		void receiveStationControlMessageASYN(StationControlMessage rMsg);// 通信示例
		void ctcReceiveTaskMessageASYN(TaskMessage rMsg); // 通信示例 hu
		void ctcReceiveTaskResponseMessageASYN(TaskResponseMessage rMsg); // 通信示例

		// hu
		void ctcReceiveTrainArriveMessageASYN(TrainArriveMessage rMsg); // 通信示例
		// hu
		void ctcReceiveErrorMessageASYN(ErrorMessage rMsg); // 通信示例 hu

		// hu 2010-4-25
		void ctcReceiveTeamTdcsRsbMessageASYN(TeamTdcsRsbMessage rMsg); // CTC接收TeamTdcsRsbMessage组内变动信息
		
		
		
		//hu 2010-7-15
		void ctcReceiveCommonMessage(CommonMessage rMsg); //CTC收到CommonMessage
		
		
		
	}

	// SICS接口定义 其他程序需要接收服务器异步发来的消息
	public interface Callback {
		void startRun(P2PCommandResponseMessage rMsg);// SICS接收服务器发来的首站信息
		void receiveTrainFromUp(P2PCommandMessage rMsg);// 接收上站发来的接车消息
		void receiveTrainFromDown(P2PCommandMessage rMsg);
		void CTCToSICS(CTCToSICSRequestMessage rMsg);// CTC发向SICS的有关车站状态变化的消息
		void receiveStationControlMessageASYN(StationControlResponseMessage rMsg); // 通信示例

		void receiveTaskMessageASYN(TaskMessage rMsg); // 通信示例 hu
		void receiveTaskResponseMessageASYN(TaskResponseMessage rMsg); // 通信示例
		// hu
		void receiveTrainArriveMessageASYN(TrainArriveMessage rMsg); // 通信示例 hu
		void receiveErrorMessageASYN(ErrorMessage rMsg); // 通信示例 hu

		// hu 2010-4-25
		void receiveTeamTdcsRsbMessageASYN(TeamTdcsRsbMessage rMsg); // SICS接收TeamTdcsRsbMessage组内变动信息
		//void receiveTeamTdcsPlanMessageASYN(P2PCommandResponseMessage rMsg); // SICS接收TeamTdcsRsbMessage组内变动信息
		
		
		
		//hu 2010-7-15
		void sicsReceiveCommonMessage(CommonMessage rMsg); //CTC收到CommonMessage
		
	}

	// 接口定义 其他程序需要接收服务器异步发来的消息
	public interface RSBCallback {

		// hu 2010-4-25
		void rsbReceiveTeamTdcsRsbMessageASYN(TeamTdcsRsbMessage rMsg); // SICS接收TeamTdcsRsbMessage组内变动信息
		
		
		//hu 2010-7-15
		void rsbReceiveCommonMessage(CommonMessage rMsg); //CTC收到CommonMessage
		
	}

	// xbm2010-4-24添加供TDCS用
	public interface ZNTdcsCallback {
		void receivedSQLMessage(String result);

		void receivedTDCSCommandMessage(TDCSCommandMessage rMsg);

		void receivedTeamTdcsRsbMessage(TeamTdcsRsbMessage rMsg);

		void receivedTrainLineAnchorMessage(TrainLineAnchorMessage rMsg);
				
		//hu 2010-7-15
		//void tdcsReceiveCommonMessage(CommonMessage rMsg); //CTC收到CommonMessage
		void tdcsReceivedCommonMessage(CommonMessage rMsg); //CTC收到CommonMessage
	}

	// 退出处理接口
	public interface QuitCallback {
		void loggedOut();
	}

	// 返回给MinaClient类进行处理
	private ZNTdcsCallback tdcsCallback;// xbm2010-4-24添加供TDCS用
	private Callback callback;
	private CTCCallback ctcCallback;
	private QuitCallback quitCallback;
	private RSBCallback rsbCallback; // hu 2010-4-25

	public AsynClientHandler(CTCCallback ctcCallback, Callback callback, QuitCallback quitCallback, ZNTdcsCallback tdcsCallback, RSBCallback rsbCallback) {
		this.ctcCallback = ctcCallback;
		this.callback = callback;
		this.quitCallback = quitCallback;
		this.tdcsCallback = tdcsCallback;
		this.rsbCallback = rsbCallback; // hu 2010-4-25
	}

	// ///////以下是实现由接口IoHandlerAdapter所定义的方法////////////////////////
	// 下面方法的功能是用来接收服务器发来的消息,并通过调用Callback接口中定义的方法来通知类CTCSynClient进行具体处理
	/*
	 * 处理异常: 一旦收到执行中的异常，为保证后续任务可用，强行关闭本次session连接
	 */
	@Override
	public void exceptionCaught(IoSession session, Throwable cause) {
		// LOGGER.warn("Unexpected exception.", cause);
		// cause.printStackTrace();
		// Close connection when unexpected exception is caught.
		session.close(true);
	}

	// 在sessionCreated调用之后被调用；当会话开始时被触发
	@Override
	public void sessionOpened(IoSession session) throws Exception {
	}

	// 接收服务器发送的消息 调用各任务类的处理函数. 只处理服务器发送的异步消息
	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		
		// hu 2010-4-25
		if (message instanceof CommonMessage)// 处理SQL消息
		{
			ErrorLog.log("\n客户端AsynClientHandler.java ASY 收到CommonMessage消息");
			CommonMessage rMsg = (CommonMessage) message;
			
			int terType = rMsg.getTerType();
						
			switch (terType) {
			case Constants.TERMINAL_TYPE_CTC: //CTC接收
				
				ErrorLog.log("\n客户端AsynClientHandler.java ASY 收到CommonMessage消息  terType = CTC");		
				ctcCallback.ctcReceiveCommonMessage(rMsg);
				break;
			
			case Constants.TERMINAL_TYPE_SICS: //SICS接收
				
				ErrorLog.log("\n客户端AsynClientHandler.java ASY 收到CommonMessage消息  terType = SICS");
				callback.sicsReceiveCommonMessage(rMsg);
				break;
				
			case Constants.TERMINAL_TYPE_TDCS: //TDCS接收
				
				ErrorLog.log("\n客户端AsynClientHandler.java ASY 收到CommonMessage消息  terType = TDCS -开始-");
				tdcsCallback.tdcsReceivedCommonMessage(rMsg);
				ErrorLog.log("\n客户端AsynClientHandler.java ASY 收到CommonMessage消息  terType = TDCS -结束-");
				break;
				
			case Constants.TERMINAL_TYPE_RSB: //RSB接收
				
				ErrorLog.log("\n客户端AsynClientHandler.java ASY 收到CommonMessage消息  terType = RSB");		
				ctcCallback.ctcReceiveCommonMessage(rMsg);
				
				break;
				
			}

		}else
		
		
		
		
		// 处理退出消息 对同步退出消息不处理
		if (message instanceof LogoutResponseMessage) {
			Object obj = message;
			LogoutResponseMessage rMsg;
			if (obj instanceof LogoutResponseMessage)
				rMsg = (LogoutResponseMessage) message;
			else
				return;

			if ((rMsg.getCommandMode() == Constants.MODE_CS_ASYN_SERVER) && (rMsg.getUserRole() == Constants.USER_ROLE_SERVER)) {
				if (rMsg.getResult() == Constants.SERVER_RESULT_OK) {
					quitCallback.loggedOut();
				}
			}
		} else // 通信示例
		// 处理服务器发来的StationControlMessage
		if (message instanceof StationControlMessage) {
			// 保证次分支只处理 StationControlMessage消息
			Object obj = message;
			StationControlMessage rMsg;
			if (obj instanceof StationControlMessage)
				rMsg = (StationControlMessage) message;
			else
				return;
			// 异步消息
			if (rMsg.getCommandMode() == Constants.MODE_CS_ASYN_SERVER) {
				ctcCallback.receiveStationControlMessageASYN(rMsg);
			}
		} else // 通信示例
		// 处理服务器发来的StationControlResponseMessage
		if (message instanceof StationControlResponseMessage) {
			// 保证次分支只处理 StationControlMessage消息
			Object obj = message;
			StationControlResponseMessage rMsg;
			if (obj instanceof StationControlResponseMessage)
				rMsg = (StationControlResponseMessage) message;
			else
				return;
			// 异步消息
			if (rMsg.getCommandMode() == Constants.MODE_CS_ASYN_SERVER) {
				callback.receiveStationControlMessageASYN(rMsg);
			}
		}

		else // 通信示例 hu
		// 处理服务器发来的StationControlResponseMessage
		if (message instanceof TaskMessage) {
			// 保证次分支只处理 StationControlMessage消息
			
			ErrorLog.log("AsynClientHandler:收到TaskMessage消息");
			
			Object obj = message;
			TaskMessage rMsg;
			if (obj instanceof TaskMessage)
				rMsg = (TaskMessage) message;
			else
				return;
			// 异步消息
			if (rMsg.getCommandMode() == Constants.MODE_CS_ASYN_SERVER) {
				if (rMsg.getTerType() == Constants.TERMINAL_TYPE_SICS) {
					ErrorLog.log("AsynClientHandler:收到TaskMessage消息：SICS发给CTC");
					ctcCallback.ctcReceiveTaskMessageASYN(rMsg);					
				} else if (rMsg.getTerType() == Constants.TERMINAL_TYPE_CTC) {
					ErrorLog.log("AsynClientHandler:收到TaskMessage消息：CTC发给SICS");
					callback.receiveTaskMessageASYN(rMsg);					
				} else if (rMsg.getTerType() == Constants.TERMINAL_TYPE_RSB) {
					ErrorLog.log("AsynClientHandler:收到TaskMessage消息：CTC发给SICS");
					callback.receiveTaskMessageASYN(rMsg);					
				}
			}
		} else // 通信示例 hu
		// 处理服务器发来的StationControlResponseMessage
		if (message instanceof TaskResponseMessage) {
			// 保证次分支只处理 StationControlMessage消息
			Object obj = message;
			TaskResponseMessage rMsg;
			if (obj instanceof TaskResponseMessage)
				rMsg = (TaskResponseMessage) message;
			else
				return;
			// 异步消息
			if (rMsg.getCommandMode() == Constants.MODE_CS_ASYN_SERVER) {
				if (rMsg.getTerType() == Constants.TERMINAL_TYPE_SICS) {
					ctcCallback.ctcReceiveTaskResponseMessageASYN(rMsg);
				} else if (rMsg.getTerType() == Constants.TERMINAL_TYPE_CTC) {
					callback.receiveTaskResponseMessageASYN(rMsg);
				}
			}
		}

		else // 通信示例 hu
		// 处理服务器发来的StationControlResponseMessage
		if (message instanceof ErrorMessage) {
			// 保证次分支只处理 StationControlMessage消息
			Object obj = message;
			ErrorMessage rMsg;
			if (obj instanceof ErrorMessage)
				rMsg = (ErrorMessage) message;
			else
				return;
			// 异步消息
			if (rMsg.getCommandMode() == Constants.MODE_CS_ASYN_SERVER) {
				if (rMsg.getTerType() == Constants.TERMINAL_TYPE_SICS) {
					ctcCallback.ctcReceiveErrorMessageASYN(rMsg);
				} else if (rMsg.getTerType() == Constants.TERMINAL_TYPE_CTC) {
					callback.receiveErrorMessageASYN(rMsg);
				}else if (rMsg.getTerType() == Constants.TERMINAL_TYPE_RSB) {
					callback.receiveErrorMessageASYN(rMsg);
				}
			}
		} else // 通信示例 hu
		// 处理服务器发来的StationControlResponseMessage
		if (message instanceof TrainArriveMessage) {
			// 保证次分支只处理 TrainArriveMessage消息
			Object obj = message;
			TrainArriveMessage rMsg;
			if (obj instanceof TrainArriveMessage)
				rMsg = (TrainArriveMessage) message;
			else
				return;
			// 异步消息
			if (rMsg.getCommandMode() == Constants.MODE_CS_ASYN_SERVER) {
				if (rMsg.getTerType() == Constants.TERMINAL_TYPE_SICS) {
					ctcCallback.ctcReceiveTrainArriveMessageASYN(rMsg);
				} else if (rMsg.getTerType() == Constants.TERMINAL_TYPE_CTC) {
					callback.receiveTrainArriveMessageASYN(rMsg);
				}else if (rMsg.getTerType() == Constants.TERMINAL_TYPE_RSB) {
					callback.receiveTrainArriveMessageASYN(rMsg);
				}
			}
		} else
		// SICSToCTCRequestMessage是SICS发向CTC的有关车站状态变化的消息
		if (message instanceof SICSToCTCRequestMessage) {
			Object obj = message;
			SICSToCTCRequestMessage rMsg;
			if (obj instanceof SICSToCTCRequestMessage)
				rMsg = (SICSToCTCRequestMessage) message;
			else
				return;
			// 异步消息
			if (rMsg.getCommandMode() == Constants.MODE_CS_ASYN_SERVER) {
				ctcCallback.SICSToCTC(rMsg);
			}
		} else // 处理服务器发来的CTCToSICSMessage 示例代码
		// CTCToSICSRequestMessage是CTC发向SICS的有关车站状态变化的消息
		if (message instanceof CTCToSICSRequestMessage) {
			Object obj = message;
			CTCToSICSRequestMessage rMsg;
			if (obj instanceof CTCToSICSRequestMessage)
				rMsg = (CTCToSICSRequestMessage) message;
			else
				return;
			// 同步消息
			if (rMsg.getCommandMode() == Constants.MODE_CS_ASYN_SERVER) {
				callback.CTCToSICS(rMsg);
			}
		} else // 处理服务器发送的P2P消息
		if (message instanceof P2PCommandMessage) {
			Object obj = message;
			P2PCommandMessage rMsg;
			if (obj instanceof P2PCommandMessage)
				rMsg = (P2PCommandMessage) message;
			else
				return;// 非P2PCommandMessage

			// 对同步消息不处理 实际上目前服务器只发送异步消息
			if (rMsg.getCommandMode() == Constants.MODE_CS_SYN_SERVER) {
				return;
			}
			int terType = rMsg.getTerType();
			switch (terType) {
			case Constants.TERMINAL_TYPE_CTC:// 发送到CTC的P2P2消息
				// ErrorLog.log("客户_CTC");
				ctcCallbackProcess(rMsg);
				break;
			case Constants.TERMINAL_TYPE_SICS:// 发送到SICS的P2P2消息				
				System.out.println("ASY SICS 收到 P2PCommandMessage");				
				callbackProcess(rMsg);
				break;
			}
		} else // 处理服务器发送的P2P消息

		// hu 2010-4-25
		if (message instanceof TeamTdcsRsbMessage)// 处理SQL消息
		{
			ErrorLog.log("\n AsynClientHandler.java ASY 收到 TeamTdcsRsbMessage消息");
			TeamTdcsRsbMessage rMsg = (TeamTdcsRsbMessage) message;
			
			int terType = rMsg.getTerType();
						
			switch (terType) {
			case Constants.TERMINAL_TYPE_CTC: //CTC接收
				
				ErrorLog.log("\n AsynClientHandler.java ASY 收到 TeamTdcsRsbMessage消息  terType = CTC");		
				ctcCallback.ctcReceiveTeamTdcsRsbMessageASYN(rMsg);
				break;
			case Constants.TERMINAL_TYPE_RSB: //RSB接收
				
				ErrorLog.log("\n AsynClientHandler.java ASY 收到 TeamTdcsRsbMessage消息  terType = RSB");
				ctcCallback.ctcReceiveTeamTdcsRsbMessageASYN(rMsg);
				break;
			case Constants.TERMINAL_TYPE_SICS: //SICS接收
				
				ErrorLog.log("\n AsynClientHandler.java ASY 收到 TeamTdcsRsbMessage消息  terType = SICS");
				callback.receiveTeamTdcsRsbMessageASYN(rMsg);
				break;
			}

		}

		// xbm2010-4-24添加以下代码
		else if (message instanceof SQLResponseMessage)// 处理SQL消息
		{
			SQLResponseMessage rMsg = (SQLResponseMessage) message;
			if (rMsg.getCommandMode() == Constants.MODE_CS_SYN_SERVER)
				return;// 对同步消息不处理

			if (rMsg.getResult() == Constants.SERVER_RESULT_OK) {
				tdcsCallback.receivedSQLMessage(rMsg.getList());
			} else {
				tdcsCallback.receivedSQLMessage(null);
			}
		} else if (message instanceof TDCSCommandMessage)// 接受服务器发来的TDCS消息
		{
			Object obj = message;
			TDCSCommandMessage rMsg;
			if (obj instanceof TDCSCommandMessage)
				rMsg = (TDCSCommandMessage) message;
			else
				return;// 非P2PCommandResponseMessage

			// 对同步消息不处理 实际上目前服务器只发送异步消息
			if (rMsg.getCommandMode() == Constants.MODE_CS_SYN_SERVER) {
				return;
			}
			tdcsCallback.receivedTDCSCommandMessage(rMsg);
		} // 处理服务器发送的消息

		else /** 接受服务器转发来TDCS发送的TeamTdcsRsbMessage消息 */
		if (message instanceof TeamTdcsRsbMessage) {
			Object obj = message;
			TeamTdcsRsbMessage rMsg;
			if (obj instanceof TeamTdcsRsbMessage)
				rMsg = (TeamTdcsRsbMessage) message;
			else
				return;// 非P2PCommandResponseMessage

			// 对同步消息不处理 实际上目前服务器只发送异步消息
			if (rMsg.getCommandMode() == Constants.MODE_CS_SYN_SERVER) {
				return;
			}
			tdcsCallback.receivedTeamTdcsRsbMessage(rMsg);
		} else /** 接受服务器转发来普通站机发送给TDCS的TrainLineAnchorMessage消息 */
		if (message instanceof TrainLineAnchorMessage) {
			Object obj = message;
			TrainLineAnchorMessage rMsg;
			if (obj instanceof TrainLineAnchorMessage)
				rMsg = (TrainLineAnchorMessage) message;
			else
				return;// 非P2PCommandResponseMessage

			// 对同步消息不处理 实际上目前服务器只发送异步消息
			if (rMsg.getCommandMode() == Constants.MODE_CS_SYN_SERVER) {
				return;
			}
			tdcsCallback.receivedTrainLineAnchorMessage(rMsg);
		}

	}

	// 处理服务器发送来的与CTC有关的消息
	private void ctcCallbackProcess(P2PCommandMessage rMsg) {

		// 处理服务器发送的开始实验的消息
		if (rMsg.getCommandType() == Constants.TYPE_SERVER_EXPERIMENT_RUN) {// 开始实验消息
			if (rMsg.getResult() == Constants.SERVER_RESULT_OK){
				//ctcCallback.receicedFirstStation(rMsg);// 处理服务器发来的首站信息
			}else{
				ctcCallback.receicedFirstStation(null);
			}
		} else
		// 接收SICS站发送到下一站的p2p消息，CTC先处理此消息
		if (rMsg.getCommandType() == Constants.TYPE_CLIENT_P2P_ASYN_DOWN) {
			if (rMsg.getResult() != Constants.CLIENT_RESULT_OK) // 上一站 操作不正确
			// 如何处理？
			{
				// 如何进行处理？？？？？
			}
			// 接收上站发来的接车消息
			ctcCallback.receicedTrainFromUp(rMsg);
		} else
		// 接收下站发来的确认已收到该车站所发送的消息
		if (rMsg.getCommandType() == Constants.TYPE_CLIENT_P2P_ASYN_UP) {
			// 接收上站发来的接车消息
			ctcCallback.receicedTrainFromDown(rMsg);
		}
	}

	// 处理服务器发送的P2P消息
	private void callbackProcess(P2PCommandMessage rMsg) {

		// 处理服务器发送的开始实验的消息 只有某一车次的首站才能接收到此消息
		if (rMsg.getCommandType() == Constants.TYPE_SERVER_EXPERIMENT_RUN) {// 开始实验消息
			if (rMsg.getResult() == Constants.SERVER_RESULT_OK) {
				
				System.out.println("ASY SICS收到试验开始");
				
				//callback.startRun(rMsg);// 开始运行
			} else {
				callback.startRun(null);// 不运行
			}
		} else
		// 接收上站所发来的接车消息 由SICS或CTC发送来的
		if (rMsg.getCommandType() == Constants.TYPE_CLIENT_P2P_ASYN_DOWN) {
			if (rMsg.getResult() != Constants.CLIENT_RESULT_OK) // 上一站 操作不正确
			// 如何处理？
			{
				// 如何进行处理？？？？？
			}
			// 接收上站发来的接车消息
			callback.receiveTrainFromUp(rMsg);

		} else
		// 接收下站发来的确认已收到该车站所发送的消息
		if (rMsg.getCommandType() == Constants.TYPE_CLIENT_P2P_ASYN_UP) {
			// 接收上站发来的接车消息
			callback.receiveTrainFromDown(rMsg);
		}
	}

	/*
	 * 响应session关闭事件
	 */
	@Override
	public void sessionClosed(IoSession session) throws Exception {
		// 处理远程主机强迫关闭了一个现有的连接 即当客户机已经连在服务器的情况下,服务器异常退出时,客户端的处理代码
		session.close(true);
		quitCallback.loggedOut();
	}

}
