package ctc.ctc.drawctc.ctcmain.model;

import ctc.ctc.ctc2sics.CTCToSICS;
import ctc.ctc.ctc2sics.ProSICSMessage;
import ctc.sics.station.data.ParamFlag;

/**
 * 处理CTC业务
 * 
 * @author 胡恩召
 * 
 */
public class CTCModel {

	public static CTCModel ctcModel = null;

	public static CTCModel getInstance() {
		if (ctcModel == null) {
			ctcModel = new CTCModel();
		}
		return ctcModel;
	}

	// 处理CTC的调度任务信息
	public void proCTCTask(String stationName, String taskName, String directionName, String trackName, String taskType) {

		String startName;
		String endName;
		int color;
		
		if(taskType.equalsIgnoreCase("执行")){
			color = ParamFlag.road_green;
		}else{ //取消
			color = ParamFlag.road_blue;
		}
		
		if (taskName.equalsIgnoreCase("接车")) { // 接车任务

			if (directionName.equalsIgnoreCase("下行")) {
				startName = "XLA";
				if(trackName.equalsIgnoreCase("1G")){
					endName = "S1LA";
				}else if(trackName.equalsIgnoreCase("2G")){
					endName = "S2LA";
				}else if(trackName.equalsIgnoreCase("3G")){
					endName = "S3LA";
				}else if(trackName.equalsIgnoreCase("4G")){
					endName = "S4LA";
				}else { //6G
					endName = "S6LA";
				}				
			} else { // 上行
				startName = "SLA";
				if(trackName.equalsIgnoreCase("1G")){
					endName = "X1LA";
				}else if(trackName.equalsIgnoreCase("2G")){
					endName = "X2LA";
				}else if(trackName.equalsIgnoreCase("3G")){
					endName = "X3LA";
				}else if(trackName.equalsIgnoreCase("4G")){
					endName = "X4LA";
				}else { //6G
					endName = "X6LA";
				}		
			}

		} else if (taskName.equalsIgnoreCase("发车")) { // 发车任务

			if (directionName.equalsIgnoreCase("下行")) {
				if(trackName.equalsIgnoreCase("1G")){
					startName = "X1LA";
				}else if(trackName.equalsIgnoreCase("2G")){
					startName = "X2LA";
				}else if(trackName.equalsIgnoreCase("3G")){
					startName = "X3LA";
				}else if(trackName.equalsIgnoreCase("4G")){
					startName = "X4LA";
				}else { //6G
					startName = "X6LA";
				}			
				endName = "XLFA";				
			} else { // 上行
				if(trackName.equalsIgnoreCase("1G")){
					startName = "S1LA";
				}else if(trackName.equalsIgnoreCase("2G")){
					startName = "S2LA";
				}else if(trackName.equalsIgnoreCase("3G")){
					startName = "S3LA";
				}else if(trackName.equalsIgnoreCase("4G")){
					startName = "S4LA";
				}else { //6G
					startName = "S6LA";
				}	
				endName = "SLFA";				
			}
			
			
		} else { // 直接通过

			if (directionName.equalsIgnoreCase("下行")) {
				startName = "XTA";
				endName = "XLFA";
			} else { // 上行
				startName = "STA";
				endName = "SLFA";
			}
		}
				
		System.out.println("车站：" + stationName);
		System.out.println("起始按钮：" + startName);
		System.out.println("终点按钮：" + endName + "\n");

		CTCToSICS.sendTaskMessageASYN(stationName, startName, endName, color);
		ProSICSMessage.getInstance().proCTCTaskMessage(stationName, startName, endName, color);
		
	}

}
