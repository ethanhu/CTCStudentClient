package ctc.sics.stationLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import org.eclipse.swt.widgets.*;

import ctc.constant.Constants;
import ctc.pojobean.Plan;
import ctc.transport.message.*;
import ctc.util.*;

import ctc.sics.SicsMain;

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
		sMsg.setTerType(Constants.TERMINAL_TYPE_SICS);// 普通站终端;

		sMsg.setCommandMode(Constants.MODE_CS_ASYN_CLIENT);// 异步
		sMsg.setCommandType(Constants.TYPE_CLIENT_P2P_ASYN_DOWN);// 发向下一站
		sMsg.setUserRole(Constants.USER_ROLE_STUDENT);// 目前没有处理
		sMsg.setResult(Constants.CLIENT_RESULT_OK);// 本学员操作的结果 操作正确

		// 从服务器传送过来的参数值

		Display.getDefault().syncExec(new Runnable() {
			public void run() {
				sMsg.setUserName(SicsMain.db.getUserName());
				sMsg.setRunMode(SicsMain.db.getRunMode());
				sMsg.setDistrictName(SicsMain.db.getDistrictName());// 车站区段ID
				sMsg.setTeamID(SicsMain.db.getTeamID());// 本站所在组
			}
		});

		sMsg.setStationName(SicsMain.db.getStationName());// 本站的车站ID

		sMsg.setVrTime(vrTime);
		sMsg.setTimeStep(timeStep);

		sMsg.setTrainName(trainName); // 车次ID

		SicsMain.db.getAsynClientSupport().p2pCommandMessageSend(sMsg);// 异步通信

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
		sMsg.setUserName(SicsMain.db.getUserName());// 学员账号
		sMsg.setRunMode(rMsg.getRunMode());// 系统运行方式
		sMsg.setDistrictName(rMsg.getDistrictName());// 车站区段ID
		sMsg.setTeamID(rMsg.getTeamID());// 本站所在组
		sMsg.setStationName(rMsg.getStationName());// 本站的车站ID
		sMsg.setVrTime(vrTime);
		sMsg.setTimeStep(timeStep);

		sMsg.setTrainName(rMsg.getTrainName());// 车次ID
		ErrorLog.log("发送到UP::" + SicsMain.db.getUserName());

		SicsMain.db.getAsynClientSupport().p2pCommandMessageSend(sMsg);// 异步通信
	}

	public static boolean flag = true;
	public static boolean flagRun = true;

	// 异步方法 接收上站发来的接车消息 然后发送收到的应答消息
	public void acceptedTrainFromUp(P2PCommandResponseMessage rMsg) {

		System.out.println("普通站，执行acceptedTrainFromUp");

		String trainName = rMsg.getTrainName();
		if (trainName == null) {
			trainName = "T11";
		}

		final int second = 2000;
		int timestamp = 2000;
		final Timer timerLA = new Timer();
		timerLA.schedule(new java.util.TimerTask() {

			public void run() {
				if (flag) {
					if (flagRun) {
						Display.getDefault().syncExec(new Runnable() {
							public void run() {
								DrawTrainList.changeTrainLabel("T11");
							}
						});

						try {
							Thread.sleep(second);
						} catch (InterruptedException e) {
							// break;
						}

						Display.getDefault().syncExec(new Runnable() {
							public void run() {
								DrawStation.trackLineXLA1.setTrackLineStatus("red");
							}
						});

						try {
							Thread.sleep(second);
						} catch (InterruptedException e) {
							// break;
						}

						Display.getDefault().syncExec(new Runnable() {
							public void run() {
								DrawStation.trackLineXLA1.setTrackLineStatus("blue");
								DrawStation.trackLineXLA2.setTrackLineStatus("red");
								flagRun = false;
							}
						});
					}
					
					if ((UnLock.roadXLA != null) && (UnLock.roadXLA.size() >= 1)) {

						try {
							Thread.sleep(second);
						} catch (InterruptedException e) {
							// break;
						}

						Display.getDefault().syncExec(new Runnable() {
							public void run() {
								UnLock.unLockLA(UnLock.roadXLA, 1, DrawStation.button_xla, 1, DrawStation.button_xla, 1, "", "");
								DrawStation.trackLineXLA2.setTrackLineStatus("blue");
								flag = false;
								timerLA.cancel();
							}
						});

					} else {
						System.out.println("--------没有通路");
					}
				}

			}// run
		}, 0, timestamp);

	}

	// 接收下站发来的应答消息
	public void acceptedReplyFromDown(P2PCommandResponseMessage rMsg) {

		ErrorLog.log("接收FromDown::" + SicsMain.db.getUserName());
	}

	// 接收服务器发来的启动消息 只有车次的首站才能接收到此消息
	public void startRun(P2PCommandResponseMessage rMsg) {

		System.out.println("首站，执行startRun");
		System.out.println("startRun-开始 DB = " + SicsMain.db);

		vrTime = rMsg.getVrTime();
		timeStep = rMsg.getTimeStep();

		List<Plan> planList = new ArrayList<Plan>();

		if (rMsg.getTrainPlan() == null) {
			planList = null;
		} else {
			planList = JsonUtil.getList4Json(rMsg.getTrainPlan(), Plan.class);
		}

		int len = planList.size();
		for (int i = 0; i < len; i++) {
			final String trainName = planList.get(i).getTrain_name();
			if (trainName != null) {
				Display.getDefault().syncExec(new Runnable() {
					public void run() {
						DrawTrainList.changeTrainLabel(trainName);
					}
				});

			}
		}
	}
}
