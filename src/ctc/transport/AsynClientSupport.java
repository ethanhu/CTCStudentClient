package ctc.transport;

import java.net.SocketAddress;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import ctc.constant.Constants;
import ctc.transport.message.*;
import ctc.util.ErrorLog;


public class AsynClientSupport {
	
	private static IoHandler handler;
	private static IoSession session;
	
	private static String username;
	private static int userole;

	public AsynClientSupport(){}
	
	public AsynClientSupport(String username,int userole,IoHandler handler) {
		this.userole = userole;
		this.username = username;
		this.handler = handler;
	}

	public boolean connect(NioSocketConnector connector, SocketAddress address) {
		if (session != null && session.isConnected()) {
			throw new IllegalStateException("Already connected. Disconnect first.");
		}
		
		connector.setHandler(handler);//设置事件处理器 ,即业务逻辑的处理.异步通信用

		ConnectFuture future = connector.connect(address);//建立连接
		future.awaitUninterruptibly();//等待连接创建完成  awaitUninterruptibly(CONNECT_TIMEOUT);
		if (!future.isConnected()) {
			return false;
		}
		session = future.getSession();//获取一个会话

		if (session == null)
			return false;

		return true;
	}
	///////////////下面的方法用于向服务器发送消息, 实现异步通信////////////////////
	/**xbm2010-4-24（2）添加*/
	//发送SQL操作消息 。 测试代码
	public void sqlMessageSend(SQLRequestMessage msg){
		session.write(msg);//发送消息
	}
	public void TDCSForTrainMessageAsynSend(TeamTdcsRsbMessage msg){
		
		System.out.println("TDCS: AsynClientSupport: TDCSForTrainMessageAsynSend()---1-- ");
		if(session == null){
			ErrorLog.log("客户端：TDCS：AsynClientSupport.java: TDCSForTrainMessageAsynSend： session == null");
			return;
		}
		session.write(msg);
		
		System.out.println("TDCS: AsynClientSupport: TDCSForTrainMessageAsynSend()---2-- ");
	}
	public void TDCSMessageAsynSend(TDCSCommandMessage msg){
		session.write(msg);
	}/**xbm2010-4-24（2）添加*/
	
	
	//异步  发送SQL操作消息 
	public void p2pCommandMessageSend(P2PCommandMessage sMsg){
		
		ErrorLog.log("-AsynClientSupport.java: --start-p2pCommandMessageSend发送P2PCommandMessage消息--");
		session.write(sMsg);//发送消息
		ErrorLog.log("-AsynClientSupport.java: --end-p2pCommandMessageSend发送P2PCommandMessage消息--");
	}
	
	//通信示例  异步发送
	public void stationControlMessage(StationControlMessage sMsg){
		session.write(sMsg);
	}
	//通信示例  异步发送
	public void stationControlResponseMessage(StationControlResponseMessage sMsg){
		session.write(sMsg);
	}
	
	//通信示例  异步发送 hu
	public void taskMessage(TaskMessage sMsg){
		session.write(sMsg);
	}
	//通信示例  异步发送 hu
	public void taskResponseMessage(TaskResponseMessage sMsg){
		session.write(sMsg);
	}	
	//通信示例  异步发送 hu
	public void errorMessage(ErrorMessage sMsg){
		session.write(sMsg);
	}
	//通信示例  异步发送 hu
	public void trainArriveMessage(TrainArriveMessage sMsg){
		session.write(sMsg);
	}
	
	
	
	
	//hu 2010-7-15
	public void sendCommonMessage(CommonMessage sMsg){
		session.write(sMsg);
	}
	
	
	
	public void quit(int quitFlag) {
		if (session != null) {
			if (session.isConnected()) {

				LogoutMessage logOutMsg = new LogoutMessage();
				logOutMsg.setCommandMode(Constants.MODE_CS_ASYN_CLIENT);   
				logOutMsg.setCommandType(Constants.TYPE_CLIENT_LOGOUT);
				logOutMsg.setUserRole(userole);
				logOutMsg.setUsername(username);
				logOutMsg.setQuitFlag(quitFlag);
				
				session.write(logOutMsg);

				// Wait until the chat ends.
				session.getCloseFuture().awaitUninterruptibly();//等待连接断开
			}
			//true to close this session immediately 
			session.close(true);//.awaitUninterruptibly(1000);加上后面的就会出现死锁现象,原因不明
			session = null;
		}       
	}
	
	public void serverQuit() {
		if (session != null) {
			
			if (session.isConnected()) {
				session.close(true);
				session = null;	
			}
		}
		System.exit(1);
	}

}
