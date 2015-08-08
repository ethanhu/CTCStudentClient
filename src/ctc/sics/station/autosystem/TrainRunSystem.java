package ctc.sics.station.autosystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import org.eclipse.swt.widgets.Display;

import ctc.constant.Constants;
import ctc.ctc.rsb.data.TrainDistrict;
import ctc.sics.SicsMain;
import ctc.sics.sics2ctc.SICSToCTC;
import ctc.sics.station.data.BaseParam;
import ctc.sics.station.data.ParamFlag;
import ctc.sics.station.elements.common.TrackLine;
import ctc.sics.station.elements.semaphore.SemaphoreDoubleL;
import ctc.sics.station.elements.semaphore.SemaphoreDoubleR;
import ctc.sics.util.DateUtil;
import ctc.transport.message.CommonMessage;
import ctc.transport.message.P2PCommandMessage;
import ctc.transport.message.TrainLineAnchorMessage;
import ctc.util.ErrorLog;
import ctc.util.JsonUtil;

public class TrainRunSystem {

	public static TrainRunSystem trainRunSystem = null;
	public static TrainRunSystem getInstance(){
		if(trainRunSystem == null){
			trainRunSystem = new TrainRunSystem();
		}
		return trainRunSystem;
	}
	
	BaseParam baseData = BaseParam.getInstance();
	SelfDisciplineSystem disSystem = SelfDisciplineSystem.getInstance();
	StationNameService stationNameService = StationNameService.getInstance();
		 
	public List<TrackLine> trackListXFA = new ArrayList<TrackLine>();	//下行离去	
	public List<TrackLine> trackListXLA = new ArrayList<TrackLine>();	//下行离去	
	public List<TrackLine> trackListSLA = new ArrayList<TrackLine>();    //上行离去
	public List<TrackLine> trackListSFA = new ArrayList<TrackLine>();    //上行离去
	
	//自动闭塞分区	
	public List<SemaphoreDoubleL> sepListSLA = baseData.getSepListSLA();
	public List<SemaphoreDoubleL> sepListSFA = baseData.getSepListSFA();
	public List<SemaphoreDoubleR> sepListXLA = baseData.getSepListXLA();
	public List<SemaphoreDoubleR> sepListXFA = baseData.getSepListXFA();
	
	int timestamp = 3000;
	
	//处理发车交接
	public void processFA(){
		
		trackListXLA.add(baseData.getX1jj());
		trackListXLA.add(baseData.getX2jj());
		trackListXLA.add(baseData.getX3jj());
		trackListXLA.add(baseData.getX4jj());
		
		trackListXFA.add(baseData.getX1lq());
		trackListXFA.add(baseData.getX2lq());
		trackListXFA.add(baseData.getX3lq());
		trackListXFA.add(baseData.getX4lq());
		
		trackListSLA.add(baseData.getS1jj());
		trackListSLA.add(baseData.getS2jj());
		trackListSLA.add(baseData.getS3jj());
		trackListSLA.add(baseData.getS4jj());
		
		trackListSFA.add(baseData.getS1lq());
		trackListSFA.add(baseData.getS2lq());
		trackListSFA.add(baseData.getS3lq());
		trackListSFA.add(baseData.getS4lq());
		
		
		final Timer timerFA = new Timer();
		
		timerFA.schedule(new java.util.TimerTask() {
			public void run() {

				Display.getDefault().asyncExec(new Runnable() {
					public void run() {
						
						runTrain();
						
					}
				});
			}// run
		}, 0, timestamp);
	}
	
	public void runTrain(){
		
		if (disSystem.getTrainDisList() == null || disSystem.getTrainDisList().size() == 0) {
			//System.out.println("-111-TrainRunSystem--trainDisList == null || trainDisList.size() == 0");
			return;
		}

		int len = disSystem.getTrainDisList().size();
		String stationName;
		int direction;
		int district;
		for (int i = 0; i < len; i++) {
			TrainDistrict trainDis = disSystem.getTrainDisList().get(i);
			direction = trainDis.getDirection();
			district = trainDis.getDistrict();
			
			if(SicsMain.db == null){
				System.out.println("TrainRunSystem错误: SicsMain.db == null");
				continue;
			}
			stationName = SicsMain.db.getStationName();
			
			if (direction == 1) { // 下行
				if (district == 1) { // 区段1
					if(stationName.equalsIgnoreCase("车站一")){
						processTrainAndSephoreFA(trainDis, trackListXFA, null, sepListXFA);
					}else if(stationName.equalsIgnoreCase("车站二")){
						processTrainAndSephoreLA(trainDis, trackListXLA, null, sepListXLA);
					}
				} else if (district == 2) { // 区段2
					if(stationName.equalsIgnoreCase("车站二")){
						processTrainAndSephoreFA(trainDis, trackListXFA, null, sepListXFA);
					}else if(stationName.equalsIgnoreCase("车站三")){
						processTrainAndSephoreLA(trainDis, trackListXLA, null, sepListXLA);
					}
				} else if (district == 3) { // 区段3
					if(stationName.equalsIgnoreCase("车站三")){
						processTrainAndSephoreFA(trainDis, trackListXFA, null, sepListXFA);
					}else if(stationName.equalsIgnoreCase("车站四")){
						processTrainAndSephoreLA(trainDis, trackListXLA, null, sepListXLA);
					}
				} else if (district == 4) { // 区段4
					if(stationName.equalsIgnoreCase("车站四")){
						processTrainAndSephoreFA(trainDis, trackListXFA, null, sepListXFA);
					}else if(stationName.equalsIgnoreCase("车站五")){
						processTrainAndSephoreLA(trainDis, trackListXLA, null, sepListXLA);
					}
				} else { // 区段不存在
					System.out.println("出现错误！district!=1-4");
				}
			} else if (direction == 0) { // 上行
				if (district == 1) { // 区段1
					if(stationName.equalsIgnoreCase("车站一")){
						processTrainAndSephoreLA(trainDis, trackListSLA, sepListSLA, null);
					}else if(stationName.equalsIgnoreCase("车站二")){
						processTrainAndSephoreFA(trainDis, trackListSFA, sepListSFA, null);
					}
				} else if (district == 2) { // 区段2
					if(stationName.equalsIgnoreCase("车站二")){
						processTrainAndSephoreLA(trainDis, trackListSLA, sepListSLA, null);
					}else if(stationName.equalsIgnoreCase("车站三")){
						processTrainAndSephoreFA(trainDis, trackListSFA, sepListSFA, null);
					}
				} else if (district == 3) { // 区段3
					if(stationName.equalsIgnoreCase("车站三")){
						processTrainAndSephoreLA(trainDis, trackListSLA, sepListSLA, null);
					}else if(stationName.equalsIgnoreCase("车站四")){
						processTrainAndSephoreFA(trainDis, trackListSFA, sepListSFA, null);
					}
				} else if (district == 4) { // 区段4
					if(stationName.equalsIgnoreCase("车站四")){
						processTrainAndSephoreLA(trainDis, trackListSLA, sepListSLA, null);
					}else if(stationName.equalsIgnoreCase("车站五")){
						processTrainAndSephoreFA(trainDis, trackListSFA, sepListSFA, null);
					}
				} else { // 区段不存在
					System.out.println("出现错误！district!=1-4");
				}				
			} else {
				//System.out.println("出现错误！direction!=0 && direction !=1");
			}		
			
			//已经完成任务
			if(disSystem.getTrainDisList().get(i).getIndex() == 7){
				
				ErrorLog.log("-SICS: start-TrainRunSystem--disSystem.getTrainDisList().remove(i)-");				
				disSystem.getTrainDisList().remove(i);
				ErrorLog.log("-SICS: end-TrainRunSystem--disSystem.getTrainDisList().remove(i)-");
			}
			
			//System.out.println("-111-TrainRunSystem--trainDisLis.size()=" + len);
			
		}
		
		
	}
	
	
	//发车区段的处理
	public void processTrainAndSephoreFA(TrainDistrict train, List<TrackLine> disList, List<SemaphoreDoubleL> sepListL, List<SemaphoreDoubleR> sepListR) {

		if (disList == null || disList.size() == 0) {
			//System.out.println("-222-processTrainAndSephore : disList == null || disList.size() == 0");
			return;
		}

		int index = train.getIndex(); // 车次所处的位置
		String trainName = train.getTrainName();
		
		if(index == 0){
			
			//ErrorLog.log("\n-SICS: --TrainRunSystem--准备发送CommonMessage消息--start--");
			ErrorLog.log("-SICS-: --TrainRunSystem--准备发送CommonMessage消息--trainName = " + train.getTrainName());
			
			P2PCommandMessage msg = new P2PCommandMessage();
			msg.setTrainName(train.getTrainName());
			msg.setDistrict(train.getDistrict());
			msg.setDirection(train.getDirection());			
			
			if(SicsMain.db == null){
				ErrorLog.log("-SICS错误: --TrainRunSystem--SicsMain.db == null--");
				return;
			}
			
			CommonMessage sMsg = new CommonMessage();
			sMsg.setStationName(SicsMain.db.getStationName());
			sMsg.setMeseageName("P2PCommandMessage");
			sMsg.setMessage(JsonUtil.bean2json(msg));
			SICSToCTC.sendCommonMessageToCTC(sMsg);
			
			//ErrorLog.log("-SICS: --TrainRunSystem--准备发送CommonMessage消息--end--");
			
			//--hu 2010-7-18----start----//发送实际 发车 时间到TDCS
			TrainLineAnchorMessage msg2 = new TrainLineAnchorMessage();		
			String stationName = SicsMain.db.getStationName();
			String sucStationName = stationNameService.getSucStationName(stationName);
			String curTimeStr = DateUtil.getNowTime();
			int direction = train.getDirection();
			msg2.setRTime(curTimeStr);
			msg2.setTimeType(Constants.TDCS_TIME_TYPE_LEAVE);
			msg2.setStationName(stationName);
			msg2.setPrestationName(sucStationName);
			msg2.setTrainDirection(direction);
			msg2.setTrainName(trainName);
			
			CommonMessage sMsg2 = new CommonMessage();
			sMsg2.setStationName(SicsMain.db.getStationName());
			sMsg2.setMeseageName("TrainLineAnchorMessage");
			sMsg2.setMessage(JsonUtil.bean2json(msg2));
			SICSToCTC.sendCommonMessageToZNTDCS(sMsg2);
			
			
			
			
			//--hu 2010-7-18----end----//
			
			
			
						
			if (disList.get(index).getStatus() == 0) {
				disList.get(index).setColorStatus(ParamFlag.trackline_red);
				disList.get(index).getTrainLabel().setText(trainName);
				disList.get(index).getTrainLabel().setVisible(true);
			} else {
				return;
			}

			if (sepListL != null) {
				if (sepListL.get(index).getColor() != ParamFlag.sep_red) {
					sepListL.get(index).setColorStatus(ParamFlag.sep_red);
				} else {
					return;
				}
			} else if (sepListR != null) {
				if (sepListR.get(index).getColor() != ParamFlag.sep_red) {
					sepListR.get(index).setColorStatus(ParamFlag.sep_red);
				} else {
					return;
				}
			} else {
				System.out.println("出现错误！sepListL==null && sepListR ==null");
			}
			
			train.setIndex(index+1);	
			
		}else if(index == 1){
			
			if (disList.get(index).getStatus() == 0) {
				disList.get(index).setColorStatus(ParamFlag.trackline_red);
				disList.get(index).getTrainLabel().setText(trainName);
				disList.get(index).getTrainLabel().setVisible(true);
				//过去1个变蓝
				disList.get(index - 1).setColorStatus(ParamFlag.trackline_bule);
				disList.get(index - 1).getTrainLabel().setText("");
				disList.get(index - 1).getTrainLabel().setVisible(false);
			} else {
				return;
			}

			if (sepListL != null) {
				if (sepListL.get(index).getColor() != ParamFlag.sep_red) {
					sepListL.get(index).setColorStatus(ParamFlag.sep_red);
					//过去1个变黄
					sepListL.get(index - 1).setColorStatus(ParamFlag.sep_yellow);
				} else {
					return;
				}
			} else if (sepListR != null) {
				if (sepListR.get(index).getColor() != ParamFlag.sep_red) {
					sepListR.get(index).setColorStatus(ParamFlag.sep_red);
					//过去1个变黄
					sepListR.get(index - 1).setColorStatus(ParamFlag.sep_yellow);
				} else {
					return;
				}
			} else {
				System.out.println("出现错误！sepListL==null && sepListR ==null");
			}
			
			train.setIndex(index+1);
			
		}else if(index == 2){
			
			if (disList.get(index).getStatus() == 0) {
				disList.get(index).setColorStatus(ParamFlag.trackline_red);
				disList.get(index).getTrainLabel().setText(trainName);
				disList.get(index).getTrainLabel().setVisible(true);
				//过去1个变蓝
				disList.get(index - 1).setColorStatus(ParamFlag.trackline_bule);
				disList.get(index - 1).getTrainLabel().setText("");
				disList.get(index - 1).getTrainLabel().setVisible(false);
			} else {
				return;
			}

			if (sepListL != null) {
				if (sepListL.get(index).getColor() != ParamFlag.sep_red) {
					sepListL.get(index).setColorStatus(ParamFlag.sep_red);
					//过去1个变黄
					sepListL.get(index - 1).setColorStatus(ParamFlag.sep_yellow);
					//过去2个变为绿黄
					if(sepListL.get(index - 2).getColor() == ParamFlag.sep_yellow){
						sepListL.get(index - 2).setColorStatus(ParamFlag.sep_green_yellow);
					}
				} else {
					return;
				}
			} else if (sepListR != null) {
				if (sepListR.get(index).getColor() != ParamFlag.sep_red) {
					sepListR.get(index).setColorStatus(ParamFlag.sep_red);
					//过去1个变黄
					sepListR.get(index - 1).setColorStatus(ParamFlag.sep_yellow);
					//过去2个变为绿黄
					if(sepListR.get(index - 2).getColor() == ParamFlag.sep_yellow){
						sepListR.get(index - 2).setColorStatus(ParamFlag.sep_green_yellow);
					}
				} else {
					return;
				}
			} else {
				System.out.println("出现错误！sepListL==null && sepListR ==null");
			}
			
			train.setIndex(index+1);			
			
		}else if(index == 3){
			
			if (disList.get(index).getStatus() == 0) {
				disList.get(index).setColorStatus(ParamFlag.trackline_red);
				disList.get(index).getTrainLabel().setText(trainName);
				disList.get(index).getTrainLabel().setVisible(true);
				//过去1个变蓝
				disList.get(index - 1).setColorStatus(ParamFlag.trackline_bule);
				disList.get(index - 1).getTrainLabel().setText("");
				disList.get(index - 1).getTrainLabel().setVisible(false);
			} else {
				return;
			}

			if (sepListL != null) {
				if (sepListL.get(index).getColor() != ParamFlag.sep_red) {
					sepListL.get(index).setColorStatus(ParamFlag.sep_red);
					//过去1个变黄
					sepListL.get(index - 1).setColorStatus(ParamFlag.sep_yellow);
					//过去2个变为绿黄
					if(sepListL.get(index - 2).getColor() == ParamFlag.sep_yellow){
						sepListL.get(index - 2).setColorStatus(ParamFlag.sep_green_yellow);
						//过去3个变为绿
						if(sepListL.get(index - 3).getColor() == ParamFlag.sep_green_yellow){
							sepListL.get(index - 3).setColorStatus(ParamFlag.sep_green);
						}
					}
				} else {
					return;
				}
			} else if (sepListR != null) {
				if (sepListR.get(index).getColor() != ParamFlag.sep_red) {
					sepListR.get(index).setColorStatus(ParamFlag.sep_red);
					//过去1个变黄
					sepListR.get(index - 1).setColorStatus(ParamFlag.sep_yellow);
					//过去2个变为绿黄
					if(sepListR.get(index - 2).getColor() == ParamFlag.sep_yellow){
						sepListR.get(index - 2).setColorStatus(ParamFlag.sep_green_yellow);
						//过去3个变为绿
						if(sepListR.get(index - 3).getColor() == ParamFlag.sep_green_yellow){
							sepListR.get(index - 3).setColorStatus(ParamFlag.sep_green);
						}
					}
				} else {
					return;
				}
			} else {
				System.out.println("出现错误！sepListL==null && sepListR ==null");
			}
			
			train.setIndex(index+1);
			
		}else if(index == 4){
			//过去1个变蓝
			disList.get(index - 1).setColorStatus(ParamFlag.trackline_bule);
			disList.get(index - 1).getTrainLabel().setText("");
			disList.get(index - 1).getTrainLabel().setVisible(false);
			
			if (sepListL != null) {
				if (sepListL.get(index - 1).getColor() == ParamFlag.sep_red) {
					//过去1个变黄
					sepListL.get(index - 1).setColorStatus(ParamFlag.sep_yellow);
					//过去2个变为绿黄
					if(sepListL.get(index - 2).getColor() == ParamFlag.sep_yellow){
						sepListL.get(index - 2).setColorStatus(ParamFlag.sep_green_yellow);
						//过去3个变为绿
						if(sepListL.get(index - 3).getColor() == ParamFlag.sep_green_yellow){
							sepListL.get(index - 3).setColorStatus(ParamFlag.sep_green);
						}
					}
				} else {
					return;
				}
			} else if (sepListR != null) {
				if (sepListR.get(index - 1).getColor() == ParamFlag.sep_red) {
					//过去1个变黄
					sepListR.get(index - 1).setColorStatus(ParamFlag.sep_yellow);
					//过去2个变为绿黄
					if(sepListR.get(index - 2).getColor() == ParamFlag.sep_yellow){
						sepListR.get(index - 2).setColorStatus(ParamFlag.sep_green_yellow);
						//过去3个变为绿
						if(sepListR.get(index - 3).getColor() == ParamFlag.sep_green_yellow){
							sepListR.get(index - 3).setColorStatus(ParamFlag.sep_green);
						}
					}
				}
			} else {
				System.out.println("出现错误！sepListL==null && sepListR ==null");
			}		
			
			train.setIndex(index+1);
			
		}else if(index == 5){
						
			if (sepListL != null) {
				if (sepListL.get(index - 2).getColor() == ParamFlag.sep_yellow) {
					//过去1个变黄
					sepListL.get(index - 2).setColorStatus(ParamFlag.sep_green_yellow);
					//过去2个变为绿黄
					if(sepListL.get(index - 3).getColor() == ParamFlag.sep_green_yellow){
						sepListL.get(index - 3).setColorStatus(ParamFlag.sep_green);						
					}
				}
			} else if (sepListR != null) {
				if (sepListR.get(index - 2).getColor() == ParamFlag.sep_yellow) {
					//过去1个变黄
					sepListR.get(index - 2).setColorStatus(ParamFlag.sep_green_yellow);
					//过去2个变为绿黄
					if(sepListR.get(index - 3).getColor() == ParamFlag.sep_green_yellow){
						sepListR.get(index - 3).setColorStatus(ParamFlag.sep_green);
					}
				}
			} else {
				System.out.println("出现错误！sepListL==null && sepListR ==null");
			}		
			
			train.setIndex(index+1);
			
		}else if(index == 6){						
			if (sepListL != null) {				
				
				if (sepListL.get(index - 3).getColor() == ParamFlag.sep_green_yellow) {
					//过去1个变黄
					sepListL.get(index - 3).setColorStatus(ParamFlag.sep_green);
				}
			} else if (sepListR != null) {
				if (sepListR.get(index - 3).getColor() == ParamFlag.sep_green_yellow) {					
					//过去1个变黄
					sepListR.get(index - 3).setColorStatus(ParamFlag.sep_green);
				}
			} else {
				System.out.println("出现错误！sepListL==null && sepListR ==null");
			}
			train.setIndex(index+1);
		}else{
			
			ErrorLog.log("--SICS: TrainRunSystem.java 出现错误 index == 7 ---");
			
		}		
	}
	
	
	//接车区段的处理
	public void processTrainAndSephoreLA(TrainDistrict train, List<TrackLine> disList, List<SemaphoreDoubleL> sepListL, List<SemaphoreDoubleR> sepListR) {

		if (disList == null || disList.size() == 0) {
			//System.out.println("-222-processTrainAndSephore : disList == null || disList.size() == 0");
			return;
		}

		int index = train.getIndex(); // 车次所处的位置
		String trainName = train.getTrainName();
		
		if(index == 0){
							
			if (disList.get(index).getStatus() == 0) {
				disList.get(index).setColorStatus(ParamFlag.trackline_red);
				disList.get(index).getTrainLabel().setText(trainName);
				disList.get(index).getTrainLabel().setVisible(true);
			} else {
				return;
			}

			if (sepListL != null) {
				if (sepListL.get(index).getColor() != ParamFlag.sep_red) {
					sepListL.get(index).setColorStatus(ParamFlag.sep_red);
				} else {
					return;
				}
			} else if (sepListR != null) {
				if (sepListR.get(index).getColor() != ParamFlag.sep_red) {
					sepListR.get(index).setColorStatus(ParamFlag.sep_red);
				} else {
					return;
				}
			} else {
				System.out.println("出现错误！sepListL==null && sepListR ==null");
			}
			
			train.setIndex(index+1);	
			
		}else if(index == 1){
			
			if (disList.get(index).getStatus() == 0) {
				disList.get(index).setColorStatus(ParamFlag.trackline_red);
				disList.get(index).getTrainLabel().setText(trainName);
				disList.get(index).getTrainLabel().setVisible(true);
				//过去1个变蓝
				disList.get(index - 1).setColorStatus(ParamFlag.trackline_bule);
				disList.get(index - 1).getTrainLabel().setText("");
				disList.get(index - 1).getTrainLabel().setVisible(false);
			} else {
				return;
			}

			if (sepListL != null) {
				if (sepListL.get(index).getColor() != ParamFlag.sep_red) {
					sepListL.get(index).setColorStatus(ParamFlag.sep_red);
					//过去1个变黄
					sepListL.get(index - 1).setColorStatus(ParamFlag.sep_yellow);
				} else {
					return;
				}
			} else if (sepListR != null) {
				if (sepListR.get(index).getColor() != ParamFlag.sep_red) {
					sepListR.get(index).setColorStatus(ParamFlag.sep_red);
					//过去1个变黄
					sepListR.get(index - 1).setColorStatus(ParamFlag.sep_yellow);
				} else {
					return;
				}
			} else {
				System.out.println("出现错误！sepListL==null && sepListR ==null");
			}
			
			train.setIndex(index+1);
			
		}else if(index == 2){
			
			if (disList.get(index).getStatus() == 0) {
				disList.get(index).setColorStatus(ParamFlag.trackline_red);
				disList.get(index).getTrainLabel().setText(trainName);
				disList.get(index).getTrainLabel().setVisible(true);
				//过去1个变蓝
				disList.get(index - 1).setColorStatus(ParamFlag.trackline_bule);
				disList.get(index - 1).getTrainLabel().setText("");
				disList.get(index - 1).getTrainLabel().setVisible(false);
			} else {
				return;
			}

			if (sepListL != null) {
				if (sepListL.get(index).getColor() != ParamFlag.sep_red) {
					sepListL.get(index).setColorStatus(ParamFlag.sep_red);
					//过去1个变黄
					sepListL.get(index - 1).setColorStatus(ParamFlag.sep_yellow);
					//过去2个变为绿黄
					if(sepListL.get(index - 2).getColor() == ParamFlag.sep_yellow){
						sepListL.get(index - 2).setColorStatus(ParamFlag.sep_green_yellow);
					}
				} else {
					return;
				}
			} else if (sepListR != null) {
				if (sepListR.get(index).getColor() != ParamFlag.sep_red) {
					sepListR.get(index).setColorStatus(ParamFlag.sep_red);
					//过去1个变黄
					sepListR.get(index - 1).setColorStatus(ParamFlag.sep_yellow);
					//过去2个变为绿黄
					if(sepListR.get(index - 2).getColor() == ParamFlag.sep_yellow){
						sepListR.get(index - 2).setColorStatus(ParamFlag.sep_green_yellow);
					}
				} else {
					return;
				}
			} else {
				System.out.println("出现错误！sepListL==null && sepListR ==null");
			}
			
			train.setIndex(index+1);			
			
		}else if(index == 3){
			
			if (disList.get(index).getStatus() == 0) {
				disList.get(index).setColorStatus(ParamFlag.trackline_red);
				disList.get(index).getTrainLabel().setText(trainName);
				disList.get(index).getTrainLabel().setVisible(true);
				
				
				//--hu 2010-7-18----start----//发送实际 到站 时间到TDCS
				TrainLineAnchorMessage msg = new TrainLineAnchorMessage();		
				if(SicsMain.db == null){
					ErrorLog.log("-SICS: TrainRunSystem.java 出现错误: SicsMain.db == null");
					return;
				}
				String stationName = SicsMain.db.getStationName();
				String prestationName = stationNameService.getPreStationName(stationName);
				String curTimeStr = DateUtil.getNowTime();
				int direction = train.getDirection();
				msg.setRTime(curTimeStr);
				msg.setTimeType(Constants.TDCS_TIME_TYPE_ARRIVEAL);
				msg.setStationName(stationName);
				msg.setPrestationName(prestationName);
				msg.setTrainDirection(direction);
				msg.setTrainName(trainName);
				
				CommonMessage sMsg = new CommonMessage();
				sMsg.setStationName(SicsMain.db.getStationName());
				sMsg.setMeseageName("TrainLineAnchorMessage");
				sMsg.setMessage(JsonUtil.bean2json(msg));
				SICSToCTC.sendCommonMessageToZNTDCS(sMsg);
								
				
				//--hu 2010-7-18----end----//
				
				
				
				
				//过去1个变蓝
				disList.get(index - 1).setColorStatus(ParamFlag.trackline_bule);
				disList.get(index - 1).getTrainLabel().setText("");
				disList.get(index - 1).getTrainLabel().setVisible(false);
			} else {
				return;
			}

			if (sepListL != null) {
				if (sepListL.get(index).getColor() != ParamFlag.sep_red) {
					sepListL.get(index).setColorStatus(ParamFlag.sep_red);
					//过去1个变黄
					sepListL.get(index - 1).setColorStatus(ParamFlag.sep_yellow);
					//过去2个变为绿黄
					if(sepListL.get(index - 2).getColor() == ParamFlag.sep_yellow){
						sepListL.get(index - 2).setColorStatus(ParamFlag.sep_green_yellow);
						//过去3个变为绿
						if(sepListL.get(index - 3).getColor() == ParamFlag.sep_green_yellow){
							sepListL.get(index - 3).setColorStatus(ParamFlag.sep_green);
						}
					}
				} else {
					return;
				}
			} else if (sepListR != null) {
				if (sepListR.get(index).getColor() != ParamFlag.sep_red) {
					sepListR.get(index).setColorStatus(ParamFlag.sep_red);
					//过去1个变黄
					sepListR.get(index - 1).setColorStatus(ParamFlag.sep_yellow);
					//过去2个变为绿黄
					if(sepListR.get(index - 2).getColor() == ParamFlag.sep_yellow){
						sepListR.get(index - 2).setColorStatus(ParamFlag.sep_green_yellow);
						//过去3个变为绿
						if(sepListR.get(index - 3).getColor() == ParamFlag.sep_green_yellow){
							sepListR.get(index - 3).setColorStatus(ParamFlag.sep_green);
						}
					}
				} else {
					return;
				}
			} else {
				System.out.println("出现错误！sepListL==null && sepListR ==null");
			}
			
			train.setIndex(index+1);
			
		}else if(index == 4){
			
			//过去1个变蓝
			//disList.get(index - 1).setColorStatus(ParamFlag.trackline_bule);
			//disList.get(index - 1).getTrainLabel().setText("");
			//disList.get(index - 1).getTrainLabel().setVisible(false);
			
			if (sepListL != null) {
				if (sepListL.get(index - 1).getColor() == ParamFlag.sep_red) {
					//过去1个变黄
					sepListL.get(index - 1).setColorStatus(ParamFlag.sep_yellow);
					//过去2个变为绿黄
					if(sepListL.get(index - 2).getColor() == ParamFlag.sep_yellow){
						sepListL.get(index - 2).setColorStatus(ParamFlag.sep_green_yellow);
						//过去3个变为绿
						if(sepListL.get(index - 3).getColor() == ParamFlag.sep_green_yellow){
							sepListL.get(index - 3).setColorStatus(ParamFlag.sep_green);
						}
					}
				} else {
					return;
				}
			} else if (sepListR != null) {
				if (sepListR.get(index - 1).getColor() == ParamFlag.sep_red) {
					//过去1个变黄
					sepListR.get(index - 1).setColorStatus(ParamFlag.sep_yellow);
					//过去2个变为绿黄
					if(sepListR.get(index - 2).getColor() == ParamFlag.sep_yellow){
						sepListR.get(index - 2).setColorStatus(ParamFlag.sep_green_yellow);
						//过去3个变为绿
						if(sepListR.get(index - 3).getColor() == ParamFlag.sep_green_yellow){
							sepListR.get(index - 3).setColorStatus(ParamFlag.sep_green);
						}
					}
				}
			} else {
				System.out.println("出现错误！sepListL==null && sepListR ==null");
			}		
			
			train.setIndex(index+1);
			
		}else if(index == 5){
						
			if (sepListL != null) {
				if (sepListL.get(index - 2).getColor() == ParamFlag.sep_yellow) {
					//过去1个变黄
					sepListL.get(index - 2).setColorStatus(ParamFlag.sep_green_yellow);
					//过去2个变为绿黄
					if(sepListL.get(index - 3).getColor() == ParamFlag.sep_green_yellow){
						sepListL.get(index - 3).setColorStatus(ParamFlag.sep_green);						
					}
				}
			} else if (sepListR != null) {
				if (sepListR.get(index - 2).getColor() == ParamFlag.sep_yellow) {
					//过去1个变黄
					sepListR.get(index - 2).setColorStatus(ParamFlag.sep_green_yellow);
					//过去2个变为绿黄
					if(sepListR.get(index - 3).getColor() == ParamFlag.sep_green_yellow){
						sepListR.get(index - 3).setColorStatus(ParamFlag.sep_green);
					}
				}
			} else {
				System.out.println("出现错误！sepListL==null && sepListR ==null");
			}		
			
			train.setIndex(index+1);
			
		}else if(index == 6){						
			if (sepListL != null) {				
				
				if (sepListL.get(index - 3).getColor() == ParamFlag.sep_green_yellow) {
					//过去1个变黄
					sepListL.get(index - 3).setColorStatus(ParamFlag.sep_green);
				}
			} else if (sepListR != null) {
				if (sepListR.get(index - 3).getColor() == ParamFlag.sep_green_yellow) {					
					//过去1个变黄
					sepListR.get(index - 3).setColorStatus(ParamFlag.sep_green);
				}
			} else {
				System.out.println("出现错误！sepListL==null && sepListR ==null");
			}
			train.setIndex(index+1);
		}else{
			
			ErrorLog.log("--SICS: TrainRunSystem.java 出现错误 index == 7 ---");
			
		}		
	}
	
	
}
