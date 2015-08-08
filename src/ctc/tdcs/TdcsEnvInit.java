package ctc.tdcs;

//每个SWT应用程序至少需要一个Display和大于等于1个的Shell实例
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;

import ctc.tdcs.TdcsMain;
import ctc.transport.AsynClientSupport;
import ctc.transport.MinaClient;
import ctc.transport.SynClientSupport;
import ctc.transport.message.LoginResponseMessage;



public class TdcsEnvInit {

	private static MinaClient minaClient;//由学员启动界面传来的MinaClient类的实例对象
	public static SynClientSupport synClientSupport = new SynClientSupport();  //同步通信句柄
	public static AsynClientSupport asynClientSupport = new AsynClientSupport(); //异步通信句柄

	private static String userName;//用户名
	private static int teamID;//组号
	private static boolean roleFlag;//启动教师TDCS或组内TDCS（false）
	private static String districtName;//区段名称

	final String IMAGE_PATH = System.getProperty("user.dir")+"/resources/images/tray.png";
	public Rectangle displayBounds; 

	public TdcsEnvInit(){ }

	public TdcsEnvInit(MinaClient minaClient,String userName,LoginResponseMessage rMsg,boolean roleFlag)
	{
		this.minaClient = minaClient;
		synClientSupport = minaClient.getSynClientSupport();
		asynClientSupport  = minaClient.getAsynClientSupport();
		
		setUserName(userName);
		setTeamID(rMsg.getTeamID());
		setDistrictName(rMsg.getDistrictName());
		setRoleFlag(roleFlag);
	}
	
	public static void start(){
		new TdcsMain(minaClient).run();
	}

	public static String getUserName() {
		return userName;
	}

	public static void setUserName(String userName) {
		TdcsEnvInit.userName = userName;
	}

	public static int getTeamID() {
		return teamID;
	}

	public static void setTeamID(int teamID) {
		TdcsEnvInit.teamID = teamID;
	}

	public static boolean isRoleFlag() {
		return roleFlag;
	}

	public static void setRoleFlag(boolean roleFlag) {
		TdcsEnvInit.roleFlag = roleFlag;
	}

	public static String getDistrictName() {
		return districtName;
	}

	public static void setDistrictName(String districtName) {
		TdcsEnvInit.districtName = districtName;
	}
}
