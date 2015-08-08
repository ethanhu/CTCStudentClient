package ctc.sics.stationLayout;

import java.util.ArrayList;

import ctc.sics.SicsMain;
import ctc.sics.road.*;
import ctc.sics.sics2ctc.SICSToCTC;

/**
 * 道路操作
 * @author 胡恩召
 *
 */
public class DrawTrackButtonModel {

	public static ArrayList<RoadBasicInfo> roadXZQX = new ArrayList<RoadBasicInfo>(); //下行进站道路
	public static String semphoreNameX = "XLX";
	public static ArrayList<RoadBasicInfo> roadSZQX =  new ArrayList<RoadBasicInfo>(); //上行进站道路
	public static String semphoreNameS = "SLX";
	
	public static String trackName = "1";
	
	//下行道路总取消
	public static void Button_xzqxAction() {
	
		if((roadXZQX == null)||(roadXZQX.size() == 0)){
			return;
		}	
		
		if(DrawTrackButtonS.b_xzqx == 1){
			
			// 发向CTC
			SICSToCTC.SICSToCTCChangeStationStatus("T11", SicsMain.db.getStationName(), "取消", "下行", trackName);
			
			if(semphoreNameX.length() <= 3){
				ButtonSemphoreModel.semphoreColorModel(semphoreNameX, "red");	
			}else{
				ButtonSemphoreModel.semphoreColorModel("XLX", "red");	
				ButtonSemphoreModel.semphoreColorModel("X1", "red");
			}
			
			int len = roadXZQX.size();
			for(int i=0; i < len; i++){
				
				RoadBasicInfo rBasicInfo = roadXZQX.get(i);
				String roadType = rBasicInfo.getRoadType();
				String roadName = rBasicInfo.getRoadName();

				// 当类型为TrackLine时
				if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTrackLine)) {
					
					if((DrawStation.trackLineList == null) || (DrawStation.trackLineList.size() == 0)){
						return;
					}
					int length = DrawStation.trackLineList.size();
					for (int j = 0; j < length; j++) {
						if (roadName.equalsIgnoreCase(DrawStation.trackLineList.get(j).getLineName())) {
							DrawStation.trackLineList.get(j).setTrackLineStatus("blue"); // 绿色
							DrawStation.trackLineList.get(j).setStatus(0); // 设置状态为锁定
							break;
						}
					}
				}else
				// 当类型为TurnoutDouble时
				if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutDouble)) {
					
					if((DrawStation.turnoutDoubleList == null) || (DrawStation.turnoutDoubleList.size() == 0)){
						return;
					}
					
					int length = DrawStation.turnoutDoubleList.size();
					
					for (int j = 0; j < length; j++) {
						if (roadName.equalsIgnoreCase(DrawStation.turnoutDoubleList.get(j).getTurnoutName())) {
							
							int status = DrawStation.turnoutDoubleList.get(j).getTurnoutStatus();
							if(status == 1){
								DrawStation.turnoutDoubleList.get(j).setColorStatus("blueZS"); // 绿色正位
							}else{
								DrawStation.turnoutDoubleList.get(j).setColorStatus("blueFL"); // 绿色正位
							}
							DrawStation.turnoutDoubleList.get(j).setStatus(0); // 设置状态为锁定
							break;
						}
					}
				}
				
				// 当类型为TurnoutLS时
				if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLS)) {
					
					if((DrawStation.turnoutLSList == null) || (DrawStation.turnoutLSList.size() == 0)){
						return;
					}
					
					int length = DrawStation.turnoutLSList.size();
					for (int j = 0; j < length; j++) {
						if (roadName.equalsIgnoreCase(DrawStation.turnoutLSList.get(j).getTurnoutName())) {
							
							int status = DrawStation.turnoutLSList.get(j).getTurnoutStatus();
							if(status == 1){
								DrawStation.turnoutLSList.get(j).setColorStatus("blueZ"); // 绿色反位
							}else{
								DrawStation.turnoutLSList.get(j).setColorStatus("blueF"); // 绿色反位
							}
							DrawStation.turnoutLSList.get(j).setStatus(0); // 设置状态为锁定
							break;
						}
					}
				}
				// 当类型为TurnoutLX时
				if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLX)) {
					
					if((DrawStation.turnoutLXList == null) || (DrawStation.turnoutLXList.size() == 0)){
						return;
					}

					int length = DrawStation.turnoutLXList.size();
					for (int j = 0; j < length; j++) {
						if (roadName.equalsIgnoreCase(DrawStation.turnoutLXList.get(j).getTurnoutName())) {
							
							int status = DrawStation.turnoutLXList.get(j).getTurnoutStatus();
							if(status == 1){
								DrawStation.turnoutLXList.get(j).setColorStatus("blueZ"); // 绿色反位
							}else{
								DrawStation.turnoutLXList.get(j).setColorStatus("blueF"); // 绿色反位
							}						
							DrawStation.turnoutLXList.get(j).setStatus(0); // 设置状态为锁定
							break;
						}
					}
				}
				// 当类型为TurnoutRS时
				if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRS)) {
					
					if((DrawStation.turnoutRSList == null) || (DrawStation.turnoutRSList.size() == 0)){
						return;
					}

					int length = DrawStation.turnoutRSList.size();
					for (int j = 0; j < length; j++) {
						if (roadName.equalsIgnoreCase(DrawStation.turnoutRSList.get(j).getTurnoutName())) {
							
							int status = DrawStation.turnoutRSList.get(j).getTurnoutStatus();
							if(status == 1){
								DrawStation.turnoutRSList.get(j).setColorStatus("blueZ"); // 绿色反位
							}else{
								DrawStation.turnoutRSList.get(j).setColorStatus("blueF"); // 绿色反位
							}				
							DrawStation.turnoutRSList.get(j).setStatus(0); // 设置状态为锁定
							break;
						}
					}
				}
				// 当类型为TurnoutRX时
				if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRX)) {
					
					if((DrawStation.turnoutRXList == null) || (DrawStation.turnoutRXList.size() == 0)){
						return;
					}

					int length = DrawStation.turnoutRXList.size();
					for (int j = 0; j < length; j++) {
						if (roadName.equalsIgnoreCase(DrawStation.turnoutRXList.get(j).getTurnoutName())) {
							
							int status = DrawStation.turnoutRXList.get(j).getTurnoutStatus();
							if(status == 1){
								DrawStation.turnoutRXList.get(j).setColorStatus("blueZ"); // 绿色反位
							}else{
								DrawStation.turnoutRXList.get(j).setColorStatus("blueF"); // 绿色反位
							}		
							DrawStation.turnoutRXList.get(j).setStatus(0); // 设置状态为锁定
							break;
						}
					}
				}			
			}
		}
		DrawTrackButtonS.b_xzqx = 0;
	}
	
	
	//上行道路总取消
	public static void Button_szqxAction() {
	
		if((roadSZQX == null)||(roadSZQX.size() == 0)){
			return;
		}
		
		// 发向CTC
		SICSToCTC.SICSToCTCChangeStationStatus("T11", SicsMain.db.getStationName(), "取消", "上行", trackName);
		
		if(DrawTrackButtonX.b_szqx == 1){
		
			if(semphoreNameX.length() <= 3){
				ButtonSemphoreModel.semphoreColorModel(semphoreNameS, "red");
			}else{
				ButtonSemphoreModel.semphoreColorModel("SLX", "red");	
				ButtonSemphoreModel.semphoreColorModel("S2", "red");
			}				
			
			int len = roadSZQX.size();
			for(int i=0; i < len; i++){
				
				RoadBasicInfo rBasicInfo = roadSZQX.get(i);
				String roadType = rBasicInfo.getRoadType();
				String roadName = rBasicInfo.getRoadName();

				// 当类型为TrackLine时
				if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTrackLine)) {
					
					if((DrawStation.trackLineList == null) || (DrawStation.trackLineList.size() == 0)){
						return;
					}
					int length = DrawStation.trackLineList.size();
					for (int j = 0; j < length; j++) {
						if (roadName.equalsIgnoreCase(DrawStation.trackLineList.get(j).getLineName())) {
							DrawStation.trackLineList.get(j).setTrackLineStatus("blue"); // 绿色
							DrawStation.trackLineList.get(j).setStatus(0); // 设置状态为锁定
							break;
						}
					}
				}else
				// 当类型为TurnoutDouble时
				if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutDouble)) {
					
					if((DrawStation.turnoutDoubleList == null) || (DrawStation.turnoutDoubleList.size() == 0)){
						return;
					}
					
					int length = DrawStation.turnoutDoubleList.size();
					
					for (int j = 0; j < length; j++) {
						if (roadName.equalsIgnoreCase(DrawStation.turnoutDoubleList.get(j).getTurnoutName())) {
							
							int status = DrawStation.turnoutDoubleList.get(j).getTurnoutStatus();
							if(status == 1){
								DrawStation.turnoutDoubleList.get(j).setColorStatus("blueZX"); // 绿色正位
							}else{
								DrawStation.turnoutDoubleList.get(j).setColorStatus("blueFL"); // 绿色正位
							}
							DrawStation.turnoutDoubleList.get(j).setStatus(0); // 设置状态为锁定
							break;
						}
					}
				}
				
				// 当类型为TurnoutLS时
				if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLS)) {
					
					if((DrawStation.turnoutLSList == null) || (DrawStation.turnoutLSList.size() == 0)){
						return;
					}
					
					int length = DrawStation.turnoutLSList.size();
					for (int j = 0; j < length; j++) {
						if (roadName.equalsIgnoreCase(DrawStation.turnoutLSList.get(j).getTurnoutName())) {
							
							int status = DrawStation.turnoutLSList.get(j).getTurnoutStatus();
							if(status == 1){
								DrawStation.turnoutLSList.get(j).setColorStatus("blueZ"); // 绿色反位
							}else{
								DrawStation.turnoutLSList.get(j).setColorStatus("blueF"); // 绿色反位
							}
							DrawStation.turnoutLSList.get(j).setStatus(0); // 设置状态为锁定
							break;
						}
					}
				}
				// 当类型为TurnoutLX时
				if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLX)) {
					
					if((DrawStation.turnoutLXList == null) || (DrawStation.turnoutLXList.size() == 0)){
						return;
					}

					int length = DrawStation.turnoutLXList.size();
					for (int j = 0; j < length; j++) {
						if (roadName.equalsIgnoreCase(DrawStation.turnoutLXList.get(j).getTurnoutName())) {
							
							int status = DrawStation.turnoutLXList.get(j).getTurnoutStatus();
							if(status == 1){
								DrawStation.turnoutLXList.get(j).setColorStatus("blueZ"); // 绿色反位
							}else{
								DrawStation.turnoutLXList.get(j).setColorStatus("blueF"); // 绿色反位
							}						
							DrawStation.turnoutLXList.get(j).setStatus(0); // 设置状态为锁定
							break;
						}
					}
				}
				// 当类型为TurnoutRS时
				if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRS)) {
					
					if((DrawStation.turnoutRSList == null) || (DrawStation.turnoutRSList.size() == 0)){
						return;
					}

					int length = DrawStation.turnoutRSList.size();
					for (int j = 0; j < length; j++) {
						if (roadName.equalsIgnoreCase(DrawStation.turnoutRSList.get(j).getTurnoutName())) {
							
							int status = DrawStation.turnoutRSList.get(j).getTurnoutStatus();
							if(status == 1){
								DrawStation.turnoutRSList.get(j).setColorStatus("blueZ"); // 绿色反位
							}else{
								DrawStation.turnoutRSList.get(j).setColorStatus("blueF"); // 绿色反位
							}				
							DrawStation.turnoutRSList.get(j).setStatus(0); // 设置状态为锁定
							break;
						}
					}
				}
				// 当类型为TurnoutRX时
				if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRX)) {
					
					if((DrawStation.turnoutRXList == null) || (DrawStation.turnoutRXList.size() == 0)){
						return;
					}

					int length = DrawStation.turnoutRXList.size();
					for (int j = 0; j < length; j++) {
						if (roadName.equalsIgnoreCase(DrawStation.turnoutRXList.get(j).getTurnoutName())) {
							
							int status = DrawStation.turnoutRXList.get(j).getTurnoutStatus();
							if(status == 1){
								DrawStation.turnoutRXList.get(j).setColorStatus("blueZ"); // 绿色反位
							}else{
								DrawStation.turnoutRXList.get(j).setColorStatus("blueF"); // 绿色反位
							}		
							DrawStation.turnoutRXList.get(j).setStatus(0); // 设置状态为锁定
							break;
						}
					}
				}			
			}
		}
		DrawTrackButtonX.b_szqx = 0;
	}
	
}
