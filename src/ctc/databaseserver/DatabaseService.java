package ctc.databaseserver;

import java.util.List;

import ctc.constant.Constants;
import ctc.transport.AsynClientSupport;
import ctc.transport.SynClientSupport;
import ctc.transport.message.SQLRequestMessage;
import ctc.util.JsonUtil;

public class DatabaseService {

	private SynClientSupport synClientSupport; // 同步
	private AsynClientSupport asynClientSupport; //异步
	
	private static DatabaseService thisData = null;
	public static DatabaseService getInstance(){
		if (thisData == null){
			thisData = new DatabaseService();
		}
		return thisData;
	}
	
	public DatabaseService()
	{
		MinaCommunicationHandler  minaCommunicationHandler = new MinaCommunicationHandler();
		synClientSupport = minaCommunicationHandler.getSynClientSupport();
		asynClientSupport = minaCommunicationHandler.getAsynClientSupport();
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
