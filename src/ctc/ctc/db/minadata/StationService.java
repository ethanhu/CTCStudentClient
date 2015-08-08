package ctc.ctc.db.minadata;

import java.util.List;

import ctc.constant.Constants;
import ctc.pojobean.Station;
import ctc.transport.MinaClient;
import ctc.transport.SynClientSupport;
import ctc.transport.message.SQLRequestMessage;
import ctc.util.JsonUtil;

public class StationService {

	public static SynClientSupport synClientSupport; // 同步
	public static MinaClient minaClient;

	public static void connectServer(MinaClient newMinaClient) {
		minaClient = newMinaClient; // 同步网络连接对象
		synClientSupport = minaClient.getSynClientSupport();// 同步通信
	}

	// 从服务器获取信息.
	public List<Station> getStationInfo() {

		SQLRequestMessage msg = new SQLRequestMessage();
		msg.setCommandMode(Constants.MODE_CS_SYN_CLIENT);
		msg.setCommandType(Constants.TYPE_CLIENT_SQLQUERY);
		msg.setDataBean("Station"); // 设置要进行操作的数据库表名

		// 不带参数的sql语句的使用方法
		String sqlRequest = "select * from Station where Station_id = 9 "; //设置要获取数据的sql语句
		msg.setSql(sqlRequest);// limit 16
		String paprams = "null";//
		msg.setParams(paprams);// 转换为json字符串进行传递

		String listString = synClientSupport.sqlMessageSend(msg);// 发送sql请求到服务器
		if (listString == null) {// 此情况不会出现
			return null;
		} else {
			List<Station> list = JsonUtil.getList4Json(listString, Station.class);
			if (list.size() <= 0)
				return null;
			else
				return list;
		}
	}

}
