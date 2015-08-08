package ctc.ctc.rsb.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import org.eclipse.swt.widgets.Display;

import ctc.ctc.CTCMain;
import ctc.ctc.ctc2sics.CTCToSICS;
import ctc.ctc.rsb.data.BaseParam;
import ctc.ctc.rsb.data.TrainDistrict;
import ctc.sics.SicsMain;
import ctc.sics.sics2ctc.SICSToCTC;
import ctc.sics.station.data.ParamFlag;
import ctc.sics.station.elements.common.TrackLine;
import ctc.sics.station.elements.semaphore.SemaphoreDoubleL;
import ctc.sics.station.elements.semaphore.SemaphoreDoubleR;
import ctc.transport.message.CommonMessage;
import ctc.transport.message.P2PCommandMessage;
import ctc.util.ErrorLog;
import ctc.util.JsonUtil;

public class RSBModel {

	private BaseParam baseData = BaseParam.getInstance();
	final Timer timer = new Timer();
	int timestamp = 5000;

	// 区间1
	public List<TrackLine> disListS1 = baseData.getDisListS1();
	public List<TrackLine> disListX1 = baseData.getDisListX1();
	public List<SemaphoreDoubleL> sepListS1 = baseData.getSepListS1();
	public List<SemaphoreDoubleR> sepListX1 = baseData.getSepListX1();
	// 区间2
	public List<TrackLine> disListS2 = baseData.getDisListS2();
	public List<TrackLine> disListX2 = baseData.getDisListX2();
	public List<SemaphoreDoubleL> sepListS2 = baseData.getSepListS2();
	public List<SemaphoreDoubleR> sepListX2 = baseData.getSepListX2();
	// 区间3
	public List<TrackLine> disListS3 = baseData.getDisListS3();
	public List<TrackLine> disListX3 = baseData.getDisListX3();
	public List<SemaphoreDoubleL> sepListS3 = baseData.getSepListS3();
	public List<SemaphoreDoubleR> sepListX3 = baseData.getSepListX3();
	// 区间4
	public List<TrackLine> disListS4 = baseData.getDisListS4();
	public List<TrackLine> disListX4 = baseData.getDisListX4();
	public List<SemaphoreDoubleL> sepListS4 = baseData.getSepListS4();
	public List<SemaphoreDoubleR> sepListX4 = baseData.getSepListX4();

	// 区段任务list
	public List<TrainDistrict> trainDisList = new ArrayList<TrainDistrict>();
	TrainDistrict trainDis;

	private static RSBModel rsbModel = null;
	public static RSBModel getInstance() {
		if (rsbModel == null) {
			rsbModel = new RSBModel();
		}
		return rsbModel;
	}

	// 列车在区段开
	public void trainRun() {
/*
		trainDis = new TrainDistrict();
		trainDis.setTrainName("T1");
		trainDis.setIndex(0);
		trainDis.setDistrict(1);
		trainDis.setDirection(1);
		trainDisList.add(trainDis);
		
		trainDis = new TrainDistrict();
		trainDis.setTrainName("T2");
		trainDis.setIndex(0);
		trainDis.setDistrict(1);
		trainDis.setDirection(0);
		trainDisList.add(trainDis);
*/		
		
		timer.schedule(new java.util.TimerTask() {
			public void run() {

				Display.getDefault().asyncExec(new Runnable() {
					public void run() {

						runProcess(); // 区间自动闭塞

					}
				});
			}// run
		}, 0, timestamp);

	}

	// 处理自动闭塞
	public void runProcess() {

		if (trainDisList == null || trainDisList.size() == 0) {
			//System.out.println("trainDisList == null || trainDisList.size() == 0");
			return;
		}

		int len = trainDisList.size();
		int direction;
		int district;
		for (int i = 0; i < len; i++) {
			trainDis = trainDisList.get(i);
			direction = trainDis.getDirection();
			district = trainDis.getDistrict();

			if (direction == 1) { // 下行
				if (district == 1) { // 区段1
					processTrainAndSephore(trainDis, disListX1, null, sepListX1);
				} else if (district == 2) { // 区段2
					processTrainAndSephore(trainDis, disListX2, null, sepListX2);
				} else if (district == 3) { // 区段3
					processTrainAndSephore(trainDis, disListX3, null, sepListX3);
				} else if (district == 4) { // 区段4
					processTrainAndSephore(trainDis, disListX4, null, sepListX4);
				} else { // 区段不存在
					System.out.println("出现错误！district!=1-4");
				}
			} else if (direction == 0) { // 上行
				if (district == 1) { // 区段1
					processTrainAndSephore(trainDis, disListS1, sepListS1, null);
				} else if (district == 2) { // 区段2
					processTrainAndSephore(trainDis, disListS2, sepListS2, null);
				} else if (district == 3) { // 区段3
					processTrainAndSephore(trainDis, disListS3, sepListS3, null);
				} else if (district == 4) { // 区段4
					processTrainAndSephore(trainDis, disListS4, sepListS4, null);
				} else { // 区段不存在
					System.out.println("出现错误！district!=1-4");
				}
			} else {
				System.out.println("出现错误！direction!=0 && direction !=1");
			}

			//已经完成任务
			if(trainDisList.get(i).getIndex() == 23){
				
				ErrorLog.log("-CTC rsb: start-TrainRunSystem--disSystem.getTrainDisList().remove(i)-");				
				trainDisList.remove(i);
				ErrorLog.log("-CTC rsb: end-TrainRunSystem--disSystem.getTrainDisList().remove(i)-");
			}
			
		}

	}

	public void processTrainAndSephore(TrainDistrict train, List<TrackLine> disList, List<SemaphoreDoubleL> sepListL, List<SemaphoreDoubleR> sepListR) {

		if (disList == null || disList.size() == 0) {
			//System.out.println("processTrainAndSephore : disList == null || disList.size() == 0");
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
			
		}else if(index >= 3 && index <=19){
			
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
			
		}else if(index == 20){
			
			//---发送到站消息---//
			P2PCommandMessage msg = new P2PCommandMessage();
			msg.setTrainName(train.getTrainName());
			msg.setDistrict(train.getDistrict());
			msg.setDirection(train.getDirection());			
			
			if(CTCMain.db == null){
				ErrorLog.log("-CTC RSB错误: --RSBModel--CTCMain.db == null--");
				return;
			}
			
			int district = train.getDistrict();
			int direction = train.getDirection();
			String stationName;
			
			CommonMessage sMsg = new CommonMessage();
			if(district == 1){
				if(direction == 1){ //下行
					stationName = "车站二";
				}else{ //上行
					stationName = "车站一";
				}
			}else if(district == 2){
				if(direction == 1){ //下行
					stationName = "车站三";
				}else{ //上行
					stationName = "车站二";
				}
			}else if(district == 3){
				if(direction == 1){ //下行
					stationName = "车站四";
				}else{ //上行
					stationName = "车站三";
				}
			}else if(district == 4){
				if(direction == 1){ //下行
					stationName = "车站五";
				}else{ //上行
					stationName = "车站四";
				}
			}else{
				ErrorLog.log("-CTC RSB错误: --stationName == null--");
				return;
			}			
			
			sMsg.setStationName(stationName);
			sMsg.setMeseageName("P2PCommandMessage");
			sMsg.setMessage(JsonUtil.bean2json(msg));
			CTCToSICS.sendCommonMessage(sMsg);
			
			
			
			
			
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
			
		}else if(index == 21){
						
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
			
		}else if(index == 22){						
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
			
			ErrorLog.log("--CTC RSB: TrainRunSystem.java 出现错误 index == 7 ---");
			
		}		
		
	}

	public List<TrainDistrict> getTrainDisList() {
		return trainDisList;
	}

	public void setTrainDisList(List<TrainDistrict> trainDisList) {
		this.trainDisList = trainDisList;
	}

}
