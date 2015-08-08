package ctc.sics.sics2ctc;

import ctc.sics.station.data.BaseParam;
import ctc.sics.station.data.ParamFlag;
import ctc.sics.station.layout.StationModel;
import ctc.transport.message.CTCToSICSRequestMessage;

public class ProcCTCMessage {

	public static BaseParam baseData = BaseParam.getInstance();
	public static StationModel stationModel = StationModel.getInstance();

	public static String midName = "NO";
	
	public static void processCTCMsg(CTCToSICSRequestMessage msg) {

		if (msg == null) {
			return;
		}

		String startName;
		String endName;
				
		String trainName = msg.getTrainName();
		String taskName = msg.getTaskName();
		String directName = msg.getDirectionName();
		String trackLineName = msg.getTrackName();
		
		if(taskName.equalsIgnoreCase("初始化")){		
			
			baseData.getX2jj().setColorStatus(ParamFlag.trackline_red);
			
		}else if (directName.equalsIgnoreCase("下行")) {
			if (taskName.equalsIgnoreCase("接车")) {

				startName = "XLA";
				if (trackLineName.equalsIgnoreCase("1")) {
					endName = "S1LA";
				} else if (trackLineName.equalsIgnoreCase("2")) {
					endName = "S2LA";
				} else if (trackLineName.equalsIgnoreCase("3")) {
					endName = "S3LA";
				} else if (trackLineName.equalsIgnoreCase("4")) {
					endName = "S4LA";
				} else if (trackLineName.equalsIgnoreCase("6")) {
					endName = "S6LA";
				} else {
					endName = "S1LA";
					System.out.println("--Error--");
				}

				stationModel.processRoad(startName, midName, endName, ParamFlag.road_green);
				
			} else if (taskName.equalsIgnoreCase("发车")) {

				if (trackLineName.equalsIgnoreCase("1")) {
					startName = "X1LA";
				} else if (trackLineName.equalsIgnoreCase("2")) {
					startName = "X2LA";
				} else if (trackLineName.equalsIgnoreCase("3")) {
					startName = "X3LA";
				} else if (trackLineName.equalsIgnoreCase("4")) {
					startName = "X4LA";
				} else if (trackLineName.equalsIgnoreCase("6")) {
					startName = "X6LA";
				} else {
					startName = "X1LA";
					System.out.println("--Error--");
				}

				endName = "XLFA";
				
				stationModel.processRoad(startName, midName, endName, ParamFlag.road_green);

			} else if (taskName.equalsIgnoreCase("调车")) {

			}

		} else if (directName.equalsIgnoreCase("上行")) {
			
			if (taskName.equalsIgnoreCase("接车")) {

				startName = "SLA";
				if (trackLineName.equalsIgnoreCase("1")) {
					endName = "X1LA";
				} else if (trackLineName.equalsIgnoreCase("2")) {
					endName = "X2LA";
				} else if (trackLineName.equalsIgnoreCase("3")) {
					endName = "X3LA";
				} else if (trackLineName.equalsIgnoreCase("4")) {
					endName = "X4LA";
				} else if (trackLineName.equalsIgnoreCase("6")) {
					endName = "X6LA";
				} else {
					endName = "X2LA";
					System.out.println("--Error--");
				}

				stationModel.processRoad(startName, endName, midName, ParamFlag.road_green);
				
			} else if (taskName.equalsIgnoreCase("发车")) {

				if (trackLineName.equalsIgnoreCase("1")) {
					startName = "S1LA";
				} else if (trackLineName.equalsIgnoreCase("2")) {
					startName = "S2LA";
				} else if (trackLineName.equalsIgnoreCase("3")) {
					startName = "S3LA";
				} else if (trackLineName.equalsIgnoreCase("4")) {
					startName = "S4LA";
				} else if (trackLineName.equalsIgnoreCase("6")) {
					startName = "S6LA";
				} else {
					startName = "S2LA";
					System.out.println("--Error--");
				}

				endName = "SLFA";
				
				stationModel.processRoad(startName, midName, endName, ParamFlag.road_green);

			} else if (taskName.equalsIgnoreCase("调车")) {

			}
		}		

	}

}
