package ctc.tdcs.tdcsdbserver;

import java.util.List;
import ctc.constant.Constants;
import ctc.transport.*;
import ctc.transport.message.SQLRequestMessage;
import ctc.transport.message.TDCSCommandMessage;
import ctc.transport.message.TeamTdcsRsbMessage;
import ctc.tdcs.data.BaseParam;
import ctc.util.JsonUtil;

public class DatabaseAccessHandler {
	
	private SynClientSupport synClientSupport; // 同步
	private AsynClientSupport asynClientSupport; //异步
	
	BaseParam districtData = BaseParam.getInstance();
	
	private DatabaseAccessHandler thisData = null;
	public DatabaseAccessHandler getInstance(){
		if (thisData == null){
			thisData = new DatabaseAccessHandler();
		}
		return thisData;
	}
	
	public DatabaseAccessHandler()
	{
		MinaCommunicationHandler minaCommunicationHandler = new MinaCommunicationHandler();
		synClientSupport = minaCommunicationHandler.getSynClientSupport();
		asynClientSupport = minaCommunicationHandler.getAsynClientSupport();
	}
	
	////////////////////////////////////////////////////////////////////////
	//启动实验
	public TDCSCommandMessage TDCSRunCommandToServer(TDCSCommandMessage sMsg){
		//AbstractMessage类中定义的字段
		sMsg.setCommandMode(Constants.MODE_CS_SYN_CLIENT);//客户端发送到发向服务器的同步消息
		sMsg.setUserRole(Constants.USER_ROLE_TEACHER);//用户角色是教师
	
		return synClientSupport.TDCSMessageSynSend(sMsg);
	}
	
	//发送修改后的车次信息到服务器
	public void TDCSCommandForTrainToServer(TeamTdcsRsbMessage sMsg)
	{
		System.out.println("TDCS: TDCSCommandForTrainToServer()----1---- ");
		
		sMsg.setCommandMode(Constants.MODE_CS_ASYN_CLIENT);//异步
		sMsg.setUserRole(Constants.USER_ROLE_ZNTDCS);//设置用户角色
		
		if(asynClientSupport == null){
			System.out.println("TDCS: TDCSCommandForTrainToServer----asynClientSupport == null---- ");
			return;
		}
		asynClientSupport.TDCSForTrainMessageAsynSend(sMsg);
		
		System.out.println("TDCS: TDCSCommandForTrainToServer()----2---- ");
	}
	
	//关闭实验
	public void TDCSCloseCommandToServer(TDCSCommandMessage sMsg)
	{
		sMsg.setCommandMode(Constants.MODE_CS_ASYN_CLIENT);//异步
		sMsg.setUserRole(Constants.USER_ROLE_TEACHER);//设置用户角色
		
		asynClientSupport.TDCSMessageAsynSend(sMsg);
	}
	
   /////////////////////////////////////////////////////////////////////////////
	//无参数时，参数设为小写的"null";
	public boolean updateQuery(String tableName,String[] sqlArray){
		
		SQLRequestMessage msg = new SQLRequestMessage();
		msg.setCommandMode(Constants.MODE_CS_SYN_CLIENT);
		msg.setCommandType(Constants.TYPE_CLIENT_SQLBATCHINSERTDEELETE);//批量删除和批量插入
		msg.setDataBean(tableName);//要操作的表的名称
		
		//插入sql
		String sql = JsonUtil.array2json(sqlArray);
		msg.setSql(sql);
		String paprams = "null";//表示SQL语句的参数是空
		msg.setParams(paprams);
		
		String result = synClientSupport.sqlMessageSend(msg);//同步通信
		if(result == null){
			return false;		
		}else{
			return true;	 
		}
	}
	
//////////////////////////////////////////////////////////////////////////////
	
	/*功能：依据SQL语句从数据库获取相关信息，不带参数*/
   //定义泛型方法，有一个形式参数用类型参数T来定义
	public <T extends Object> List<T> sqlQuery(T t,String tableName,String sqlStr){
		
		SQLRequestMessage msg = new SQLRequestMessage();
		msg.setCommandMode(Constants.MODE_CS_SYN_CLIENT);
		msg.setCommandType(Constants.TYPE_CLIENT_SQLQUERY); 

		msg.setDataBean(tableName);
		msg.setSql(sqlStr);
		//不带参数的sql语句的使用方法
		String paprams = "null";
		msg.setParams(paprams);//转换为json字符串进行传递

		String resultListStr = synClientSupport.sqlMessageSend(msg);//同步通信
		
		if(resultListStr == null){//此情况一般不会出现
			return null;		
		}else{
			List<T> list = JsonUtil.getList4Json(resultListStr,t.getClass());
			return list;
		}
	}
	//带参数params的sql语句
	public <T extends Object> List<T> sqlQuery(T t,String tableName,String sqlStr,Object[] params){
		
		SQLRequestMessage msg = new SQLRequestMessage();
		msg.setCommandMode(Constants.MODE_CS_SYN_CLIENT);
		msg.setCommandType(Constants.TYPE_CLIENT_SQLQUERY);
		
		msg.setDataBean(tableName);
		msg.setSql(sqlStr);
		String paprams = JsonUtil.array2json(params);
		msg.setParams(paprams);//转换为json字符串进行传递

		String resultListStr = synClientSupport.sqlMessageSend(msg);//同步通信
		if(resultListStr == null){//此情况一般不会出现
			return null;		
		}else{
			List<T> list = JsonUtil.getList4Json(resultListStr,t.getClass());
			return list;
		}
	}

}
