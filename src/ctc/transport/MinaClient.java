package ctc.transport;

import java.net.InetSocketAddress;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.MdcInjectionFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import ctc.constant.Constants;
import ctc.transport.AsynClientHandler.*;
import ctc.transport.message.*;
import ctc.tdcs.tdcsdbserver.CallbackServer;

//异步客户端    //xbm2010-4-24添加,TdcsCallback //hu 2010-4-25添加RSBCallback
public class MinaClient implements CTCCallback, Callback, QuitCallback, ZNTdcsCallback, RSBCallback{

	//设置Session空闲通知的时间间隔
	private static final Long CONNECT_TIMEOUT = 30*1000L; // 30 seconds

	private static int port = 8080;
	private static String IPAddress;
	
	private static NioSocketConnector connector;
	private static AsynClientHandler asynHandler;
	private static AsynClientSupport asynClientSupport;
	private static SynClientSupport synClientSupport;
  
	//需要接收服务器异步发来的消息的程序引用
	private static Callback callback;
	private static CTCCallback ctcCallback;
	private static RSBCallback rsbCallback; //hu 2010-4-25
	private static ZNTdcsCallback tdcsCallback; //hu 2010-7-15
	
	//hu 2010-4-25
	public MinaClient(RSBCallback rsbCallback){
		this.rsbCallback = rsbCallback;
	}
	
	//hu 2010-7-15
	public MinaClient(ZNTdcsCallback tdcsCallback){
		this.tdcsCallback = tdcsCallback;
	}
	
	public MinaClient(){}
	
	/**启动TDCS xbm2010-4-24(2)添加为实验代码*/
	private static CallbackServer callbackServerForTDCS;
	public MinaClient(CallbackServer callbackServerForTDCS){
		this.callbackServerForTDCS = callbackServerForTDCS;
	}
	
	public MinaClient(CTCCallback ctcCallback){
		this.ctcCallback = ctcCallback;
	}
	public MinaClient(Callback callback){
		this.callback = callback;
	}
	
	public static SynClientSupport getSynClientSupport(){
		return synClientSupport;
	}
	
	public static AsynClientSupport getAsynClientSupport(){
		return asynClientSupport;
	}


	public boolean connect(String hostAddress ,String ipport, String username, String password, int userrole){//建立连接
		IPAddress = hostAddress;	
		port = Integer.parseInt(ipport);

		connector = new NioSocketConnector();//创建客户端连接器

		DefaultIoFilterChainBuilder chain = connector.getFilterChain();//创建接收数据的过滤器

		//Configure the service.
		connector.setConnectTimeoutMillis(CONNECT_TIMEOUT);
		//connector.setConnectTimeoutCheckInterval(CONNECT_TIMEOUT); 
		//10秒内没有读写就设置为空闲通道
		//connector.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
		//设置缓冲区的大小
		//connector.getSessionConfig().setReadBufferSize(2048);

		//inject some key IoSession properties 如 remoteAddress  localAddress
		MdcInjectionFilter mdcInjectionFilter = new MdcInjectionFilter();
		chain.addLast("mdc", mdcInjectionFilter);

		//设置编码过滤器
		chain.addLast("codec", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));//系统定义包解析
		
		//chain.addLast("logger", new LoggingFilter());
		
		//绑定异步消息处理类
		asynHandler = new AsynClientHandler(MinaClient.this,MinaClient.this,MinaClient.this,MinaClient.this,MinaClient.this);//接收异步消息用		
		asynClientSupport = new AsynClientSupport(username,userrole,asynHandler);//发送异步消息用
		boolean asynClientFlag = asynClientSupport.connect(connector, new InetSocketAddress(IPAddress,port));
		//System.out.println("asyn:" + asynClientFlag);
		
		//绑定同步消息处理类
		synClientSupport = new SynClientSupport(username,password,userrole);//收发同步消息用
		boolean synClientFlag = synClientSupport.connect(connector, new InetSocketAddress(IPAddress,this.port));
		//System.out.println("syn:" + synClientFlag);
		
		// 绑定address
		if ((! synClientFlag) ||(! synClientFlag))
		{//连接服务器失败
			closeConnection(Constants.CLIENT_CLOSE_NORMAL);
			return false;
		}
		return true;
	}
	
   //供其他类调用. 客户机退出时的处理
	public void closeConnection(int quitFlag){//连接断开后释放资源
		if (asynClientSupport != null)
			asynClientSupport.quit(quitFlag);
		if (synClientSupport != null)
			synClientSupport.quit(quitFlag);
		if (connector != null)
			connector.dispose();
	}
	
	//接收到服务器发送主动退出消息的处理
	public void serverCloseConnection(){
		if (asynClientSupport != null)
			asynClientSupport.serverQuit();
		if (synClientSupport != null)
			synClientSupport.serverQuit();
		if (connector != null)
			connector.dispose();
	}

	public LoginResponseMessage login(){//同步  用户登录
		
		if (synClientSupport != null)
			return synClientSupport.loginMsgSend();
		
		return null;
	}
	
     //////////////////////////////////////////////
	@Override
	public void loggedOut() {
		serverCloseConnection();//系统退出
	}


	//异步通知的回调方法
	@Override
	//接车消息
	public void receiveTrainFromUp(P2PCommandMessage rMsg){
		callback.receiveTrainFromUp(rMsg);
	}
	//答复消息
	public void receiveTrainFromDown(P2PCommandMessage rMsg){
		callback.receiveTrainFromDown(rMsg);
	}
	
	@Override
	public void startRun(P2PCommandResponseMessage rMsg) {
		callback.startRun(rMsg);
	}
	@Override
	public void CTCToSICS(CTCToSICSRequestMessage rMsg) {
		callback.CTCToSICS(rMsg);
	}
	//通信示例
	@Override
	public void receiveStationControlMessageASYN(StationControlResponseMessage rMsg) {
		callback.receiveStationControlMessageASYN(rMsg);
	}
	
	
	//通信示例 hu
	@Override
	public void receiveTaskMessageASYN(TaskMessage rMsg) {
		callback.receiveTaskMessageASYN(rMsg);
	}
	
	//通信示例 hu
	@Override
	public void receiveTaskResponseMessageASYN(TaskResponseMessage rMsg) {
		callback.receiveTaskResponseMessageASYN(rMsg);
	}
	
	//通信示例 hu
	@Override
	public void receiveErrorMessageASYN(ErrorMessage rMsg) {
		callback.receiveErrorMessageASYN(rMsg);
	}
	
	//通信示例 hu
	@Override
	public void receiveTrainArriveMessageASYN(TrainArriveMessage rMsg) {
		callback.receiveTrainArriveMessageASYN(rMsg);
	}
	
	///以下为CTC模块的异步通知的回调方法
	@Override
	public void receicedFirstStation(P2PCommandResponseMessage msg) {
		ctcCallback.receicedFirstStation(msg);
	}

	@Override
	public void receicedTrainFromDown(P2PCommandMessage msg) {
		ctcCallback.receicedTrainFromDown(msg);
		
	}

	@Override
	public void receicedTrainFromUp(P2PCommandMessage msg) {
		ctcCallback.receicedTrainFromUp(msg);
		
	}

	

	@Override
	public void SICSToCTC(SICSToCTCRequestMessage rMsg) {
		ctcCallback.SICSToCTC(rMsg);
	}

	//通信示例
	@Override
	public void receiveStationControlMessageASYN(StationControlMessage msg) {
		ctcCallback.receiveStationControlMessageASYN(msg);
		
	}

	//通信示例 hu
	@Override
	public void ctcReceiveTaskMessageASYN(TaskMessage rMsg) {
		ctcCallback.ctcReceiveTaskMessageASYN(rMsg);
	}	
	//通信示例 hu
	@Override
	public void ctcReceiveTaskResponseMessageASYN(TaskResponseMessage rMsg) {
		ctcCallback.ctcReceiveTaskResponseMessageASYN(rMsg);
	}	
	//通信示例 hu
	@Override
	public void ctcReceiveErrorMessageASYN(ErrorMessage rMsg) {
		ctcCallback.ctcReceiveErrorMessageASYN(rMsg);
	}	
	//通信示例 hu
	@Override
	public void ctcReceiveTrainArriveMessageASYN(TrainArriveMessage rMsg) {
		ctcCallback.ctcReceiveTrainArriveMessageASYN(rMsg);
	}

	// hu 2010-4-25 RSB收到组内TDCS变动信息 
	@Override
	public void rsbReceiveTeamTdcsRsbMessageASYN(TeamTdcsRsbMessage rMsg) {
		rsbCallback.rsbReceiveTeamTdcsRsbMessageASYN(rMsg);
	}
	@Override // hu 2010-4-25 CTC收到组内TDCS变动信息 
	public void ctcReceiveTeamTdcsRsbMessageASYN(TeamTdcsRsbMessage rMsg) {
		ctcCallback.ctcReceiveTeamTdcsRsbMessageASYN(rMsg);
	}
	@Override // hu 2010-4-25 SICS收到组内TDCS变动信息 
	public void receiveTeamTdcsRsbMessageASYN(TeamTdcsRsbMessage rMsg) {
		callback.receiveTeamTdcsRsbMessageASYN(rMsg);
	}
	
	//xbm2010-4-24添加
	//异步通知的回调方法
	@Override
	public void receivedSQLMessage(String result) {
		callbackServerForTDCS.receivedSQLMessage(result);
	}
	public void receivedTDCSCommandMessage(TDCSCommandMessage rMsg){
		callbackServerForTDCS.receivedTDCSCommandMessage(rMsg);
	}
	public void receivedTeamTdcsRsbMessage(TeamTdcsRsbMessage rMsg){
		callbackServerForTDCS.receivedTeamTdcsRsbMessage(rMsg);
	}
	public void receivedTrainLineAnchorMessage(TrainLineAnchorMessage rMsg){
		callbackServerForTDCS.receivedTrainLineAnchorMessage(rMsg);
	}


	
	
	//-------hu 2010-7-15-----start------//
	//CTC收到CommonMessage消息
	public void ctcReceiveCommonMessage(CommonMessage rMsg) {
		ctcCallback.ctcReceiveCommonMessage(rMsg);
		
	}

	//SICS收到CommonMessage消息
	public void sicsReceiveCommonMessage(CommonMessage rMsg) {
		callback.sicsReceiveCommonMessage(rMsg);
		
	}

	//TDCS收到CommonMessage消息
	public void tdcsReceivedCommonMessage(CommonMessage rMsg) {
		tdcsCallback.tdcsReceivedCommonMessage(rMsg);
		
	}

	//RSB收到CommonMessage消息
	public void rsbReceiveCommonMessage(CommonMessage rMsg) {
		rsbCallback.rsbReceiveCommonMessage(rMsg);
		
	}
	//-------hu 2010-7-15------end-----//

}
