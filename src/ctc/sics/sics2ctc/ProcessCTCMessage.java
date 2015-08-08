package ctc.sics.sics2ctc;

import ctc.sics.station.data.BaseParam;
import ctc.sics.station.data.ParamFlag;
import ctc.sics.station.layout.StationModel;
import ctc.sics.stationLayout.*;

/**
 * 处理CTC发过来的消息（主要是接发车时股道的变化及解锁）
 * @author 胡恩召
 *
 */
public class ProcessCTCMessage {

	public static BaseParam baseData = BaseParam.getInstance();
	public StationModel stationModel = StationModel.getInstance();
	
	/**
	 * 根据ctc发来的消息，对sics进行变动保持一致
	 * @param stationName
	 * @param taskName
	 * @param directionName
	 * @param trackName
	 */
	public static void changeSICSBaseCTCMsg(String stationName, String taskName, String directionName, String trackName){
		
		if(stationName.equalsIgnoreCase("呼和浩特")){
			
			if(trackName.equalsIgnoreCase("1")){
				
				if(directionName.equalsIgnoreCase("下行")){
					if(taskName.equalsIgnoreCase("接车")){
						//1道下行接车
						//DrawStation.b_xla = 1;						
						//StationModelForChange.Button_s1laAction();
					}else if(taskName.equalsIgnoreCase("取消")){							
						//1道下行发车
						DrawTrackButtonS.b_xzqx = 1;
						DrawTrackButtonModel.roadXZQX = DrawStation.r_xla_x1la;
						DrawTrackButtonModel.semphoreNameX = "XLX";
						DrawTrackButtonModel.trackName = "1";
						DrawTrackButtonModel.Button_xzqxAction();
					}else if(taskName.equalsIgnoreCase("初始化")){		
						
						baseData.getX2jj().setColorStatus(ParamFlag.trackline_red);
						//初始化相应的股道
						//DrawStation.b_xla = 1;						
						//StationModelForChange.Button_s1laAction();
						
					}else {							
						//1道下行发车
						DrawStation.b_x1la = 1;
						StationModelForChange.Button_xlfaAction();
					}
				}else{
					if(taskName.equalsIgnoreCase("接车")){
						//1道上行接车
						DrawStation.b_sla = 1;
						StationModelForChange.Button_x1laAction();
					}else if(taskName.equalsIgnoreCase("取消")){							
						//1道下行发车
						DrawTrackButtonX.b_szqx = 1;
						DrawTrackButtonModel.Button_szqxAction();
					}else{
						//1道上行发车
						DrawStation.b_s1la = 1;
						StationModelForChange.Button_slfaAction();
					}
				}
			}else if(trackName.equalsIgnoreCase("2")){
				
				if(directionName.equalsIgnoreCase("下行")){
					if(taskName.equalsIgnoreCase("接车")){
						//1道下行接车
						DrawStation.b_xla = 1;
						DrawTrackButtonModel.roadXZQX = DrawStation.r_xla_x2la;
						DrawTrackButtonModel.semphoreNameX = "XLX";
						DrawTrackButtonModel.trackName = "2";
						StationModelForChange.Button_s2laAction();
					}else if(taskName.equalsIgnoreCase("取消")){							
						//1道下行发车
						DrawTrackButtonS.b_xzqx = 1;
						DrawTrackButtonModel.Button_xzqxAction();
					}else{
						//1道下行发车
						DrawStation.b_x2la = 1;
						StationModelForChange.Button_xlfaAction();
					}
				}else{
					if(taskName.equalsIgnoreCase("接车")){
						//1道上行接车
						DrawStation.b_sla = 1;
						StationModelForChange.Button_x2laAction();
					}else if(taskName.equalsIgnoreCase("取消")){							
						//1道下行发车
						DrawTrackButtonX.b_szqx = 1;
						DrawTrackButtonModel.Button_szqxAction();
					}else{
						//1道上行发车
						DrawStation.b_s2la = 1;
						StationModelForChange.Button_slfaAction();
					}
				}
				
			}else if(trackName.equalsIgnoreCase("3")){
				
				if(directionName.equalsIgnoreCase("下行")){
					if(taskName.equalsIgnoreCase("接车")){
						//1道下行接车
						DrawStation.b_xla = 1;
						DrawTrackButtonModel.roadXZQX = DrawStation.r_xla_x3la;
						DrawTrackButtonModel.semphoreNameX = "XLX";
						DrawTrackButtonModel.trackName = "3";
						StationModelForChange.Button_s3laAction();
					}else if(taskName.equalsIgnoreCase("取消")){							
						//1道下行发车
						DrawTrackButtonS.b_xzqx = 1;
						DrawTrackButtonModel.Button_xzqxAction();
					}else{
						//1道下行发车
						DrawStation.b_x3la = 1;
						StationModelForChange.Button_xlfaAction();
					}
				}else{
					if(taskName.equalsIgnoreCase("接车")){
						//1道上行接车
						DrawStation.b_sla = 1;
						StationModelForChange.Button_x3laAction();
					}else if(taskName.equalsIgnoreCase("取消")){							
						//1道下行发车
						DrawTrackButtonX.b_szqx = 1;
						DrawTrackButtonModel.Button_szqxAction();
					}else{
						//1道上行发车
						DrawStation.b_s3la = 1;
						StationModelForChange.Button_slfaAction();
					}
				}
				
			}else if(trackName.equalsIgnoreCase("4")){
				
				if(directionName.equalsIgnoreCase("下行")){
					if(taskName.equalsIgnoreCase("接车")){
						//1道下行接车
						DrawStation.b_xla = 1;
						StationModelForChange.Button_s4laAction();
					}else if(taskName.equalsIgnoreCase("取消")){							
						//1道下行发车
						DrawTrackButtonS.b_xzqx = 1;
						DrawTrackButtonModel.Button_xzqxAction();
					}else{
						//1道下行发车
						DrawStation.b_x4la = 1;
						StationModelForChange.Button_xlfaAction();
					}
				}else{
					if(taskName.equalsIgnoreCase("接车")){
						//1道上行接车
						DrawStation.b_sla = 1;
						StationModelForChange.Button_x4laAction();
					}else if(taskName.equalsIgnoreCase("取消")){							
						//1道下行发车
						DrawTrackButtonX.b_szqx = 1;
						DrawTrackButtonModel.Button_szqxAction();
					}else{
						//1道上行发车
						DrawStation.b_s4la = 1;
						StationModelForChange.Button_slfaAction();
					}
				}
				
			}
		}else if(stationName.equalsIgnoreCase("集宁")){
			

			if(trackName.equalsIgnoreCase("1")){
				
				if(directionName.equalsIgnoreCase("下行")){
					if(taskName.equalsIgnoreCase("接车")){
						//1道下行接车
						DrawStation.b_xla = 1;
						StationModelForChange.Button_s1laAction();
					}else if(taskName.equalsIgnoreCase("取消")){							
						//1道下行发车
						DrawTrackButtonS.b_xzqx = 1;
						DrawTrackButtonModel.Button_xzqxAction();
					}else{							
						//1道下行发车
						DrawStation.b_x1la = 1;
						StationModelForChange.Button_xlfaAction();
					}
				}else{
					if(taskName.equalsIgnoreCase("接车")){
						//1道上行接车
						DrawStation.b_sla = 1;
						StationModelForChange.Button_x1laAction();
					}else if(taskName.equalsIgnoreCase("取消")){							
						//1道下行发车
						DrawTrackButtonX.b_szqx = 1;
						DrawTrackButtonModel.Button_szqxAction();
					}else{
						//1道上行发车
						DrawStation.b_s1la = 1;
						StationModelForChange.Button_slfaAction();
					}
				}
			}else if(trackName.equalsIgnoreCase("2")){
				
				if(directionName.equalsIgnoreCase("下行")){
					if(taskName.equalsIgnoreCase("接车")){
						//1道下行接车
						DrawStation.b_xla = 1;
						StationModelForChange.Button_s2laAction();
					}else if(taskName.equalsIgnoreCase("取消")){							
						//1道下行发车
						DrawTrackButtonS.b_xzqx = 1;
						DrawTrackButtonModel.Button_xzqxAction();
					}else{
						//1道下行发车
						DrawStation.b_x2la = 1;
						StationModelForChange.Button_xlfaAction();
					}
				}else{
					if(taskName.equalsIgnoreCase("接车")){
						//1道上行接车
						DrawStation.b_sla = 1;
						StationModelForChange.Button_x2laAction();
					}else if(taskName.equalsIgnoreCase("取消")){							
						//1道下行发车
						DrawTrackButtonX.b_szqx = 1;
						DrawTrackButtonModel.Button_szqxAction();
					}else{
						//1道上行发车
						DrawStation.b_s2la = 1;
						StationModelForChange.Button_slfaAction();
					}
				}
				
			}else if(trackName.equalsIgnoreCase("3")){
				
				if(directionName.equalsIgnoreCase("下行")){
					if(taskName.equalsIgnoreCase("接车")){
						//1道下行接车
						DrawStation.b_xla = 1;
						StationModelForChange.Button_s3laAction();
					}else if(taskName.equalsIgnoreCase("取消")){							
						//1道下行发车
						DrawTrackButtonS.b_xzqx = 1;
						DrawTrackButtonModel.Button_xzqxAction();
					}else{
						//1道下行发车
						DrawStation.b_x3la = 1;
						StationModelForChange.Button_xlfaAction();
					}
				}else{
					if(taskName.equalsIgnoreCase("接车")){
						//1道上行接车
						DrawStation.b_sla = 1;
						StationModelForChange.Button_x3laAction();
					}else if(taskName.equalsIgnoreCase("取消")){							
						//1道下行发车
						DrawTrackButtonX.b_szqx = 1;
						DrawTrackButtonModel.Button_szqxAction();
					}else{
						//1道上行发车
						DrawStation.b_s3la = 1;
						StationModelForChange.Button_slfaAction();
					}
				}
				
			}else if(trackName.equalsIgnoreCase("4")){
				
				if(directionName.equalsIgnoreCase("下行")){
					if(taskName.equalsIgnoreCase("接车")){
						//1道下行接车
						DrawStation.b_xla = 1;
						StationModelForChange.Button_s4laAction();
					}else if(taskName.equalsIgnoreCase("取消")){							
						//1道下行发车
						DrawTrackButtonS.b_xzqx = 1;
						DrawTrackButtonModel.Button_xzqxAction();
					}else{
						//1道下行发车
						DrawStation.b_x4la = 1;
						StationModelForChange.Button_xlfaAction();
					}
				}else{
					if(taskName.equalsIgnoreCase("接车")){
						//1道上行接车
						DrawStation.b_sla = 1;
						StationModelForChange.Button_x4laAction();
					}else if(taskName.equalsIgnoreCase("取消")){							
						//1道下行发车
						DrawTrackButtonX.b_szqx = 1;
						DrawTrackButtonModel.Button_szqxAction();
					}else{
						//1道上行发车
						DrawStation.b_s4la = 1;
						StationModelForChange.Button_slfaAction();
					}
				}
				
			}
			
		}else if(stationName.equalsIgnoreCase("大同")){
			

			if(trackName.equalsIgnoreCase("1")){
				
				if(directionName.equalsIgnoreCase("下行")){
					if(taskName.equalsIgnoreCase("接车")){
						//1道下行接车
						DrawStation.b_xla = 1;
						StationModelForChange.Button_s1laAction();
					}else if(taskName.equalsIgnoreCase("取消")){							
						//1道下行发车
						DrawTrackButtonS.b_xzqx = 1;
						DrawTrackButtonModel.Button_xzqxAction();
					}else{							
						//1道下行发车
						DrawStation.b_x1la = 1;
						StationModelForChange.Button_xlfaAction();
					}
				}else{
					if(taskName.equalsIgnoreCase("接车")){
						//1道上行接车
						DrawStation.b_sla = 1;
						StationModelForChange.Button_x1laAction();
					}else if(taskName.equalsIgnoreCase("取消")){							
						//1道下行发车
						DrawTrackButtonX.b_szqx = 1;
						DrawTrackButtonModel.Button_szqxAction();
					}else{
						//1道上行发车
						DrawStation.b_s1la = 1;
						StationModelForChange.Button_slfaAction();
					}
				}
			}else if(trackName.equalsIgnoreCase("2")){
				
				if(directionName.equalsIgnoreCase("下行")){
					if(taskName.equalsIgnoreCase("接车")){
						//1道下行接车
						DrawStation.b_xla = 1;
						StationModelForChange.Button_s2laAction();
					}else if(taskName.equalsIgnoreCase("取消")){							
						//1道下行发车
						DrawTrackButtonS.b_xzqx = 1;
						DrawTrackButtonModel.Button_xzqxAction();
					}else{
						//1道下行发车
						DrawStation.b_x2la = 1;
						StationModelForChange.Button_xlfaAction();
					}
				}else{
					if(taskName.equalsIgnoreCase("接车")){
						//1道上行接车
						DrawStation.b_sla = 1;
						StationModelForChange.Button_x2laAction();
					}else if(taskName.equalsIgnoreCase("取消")){							
						//1道下行发车
						DrawTrackButtonX.b_szqx = 1;
						DrawTrackButtonModel.Button_szqxAction();
					}else{
						//1道上行发车
						DrawStation.b_s2la = 1;
						StationModelForChange.Button_slfaAction();
					}
				}
				
			}else if(trackName.equalsIgnoreCase("3")){
				
				if(directionName.equalsIgnoreCase("下行")){
					if(taskName.equalsIgnoreCase("接车")){
						//1道下行接车
						DrawStation.b_xla = 1;
						StationModelForChange.Button_s3laAction();
					}else if(taskName.equalsIgnoreCase("取消")){							
						//1道下行发车
						DrawTrackButtonS.b_xzqx = 1;
						DrawTrackButtonModel.Button_xzqxAction();
					}else{
						//1道下行发车
						DrawStation.b_x3la = 1;
						StationModelForChange.Button_xlfaAction();
					}
				}else{
					if(taskName.equalsIgnoreCase("接车")){
						//1道上行接车
						DrawStation.b_sla = 1;
						StationModelForChange.Button_x3laAction();
					}else if(taskName.equalsIgnoreCase("取消")){							
						//1道下行发车
						DrawTrackButtonX.b_szqx = 1;
						DrawTrackButtonModel.Button_szqxAction();
					}else{
						//1道上行发车
						DrawStation.b_s3la = 1;
						StationModelForChange.Button_slfaAction();
					}
				}
				
			}else if(trackName.equalsIgnoreCase("4")){
				
				if(directionName.equalsIgnoreCase("下行")){
					if(taskName.equalsIgnoreCase("接车")){
						//1道下行接车
						DrawStation.b_xla = 1;
						StationModelForChange.Button_s4laAction();
					}else if(taskName.equalsIgnoreCase("取消")){							
						//1道下行发车
						DrawTrackButtonS.b_xzqx = 1;
						DrawTrackButtonModel.Button_xzqxAction();
					}else{
						//1道下行发车
						DrawStation.b_x4la = 1;
						StationModelForChange.Button_xlfaAction();
					}
				}else{
					if(taskName.equalsIgnoreCase("接车")){
						//1道上行接车
						DrawStation.b_sla = 1;
						StationModelForChange.Button_x4laAction();
					}else if(taskName.equalsIgnoreCase("取消")){							
						//1道下行发车
						DrawTrackButtonX.b_szqx = 1;
						DrawTrackButtonModel.Button_szqxAction();
					}else{
						//1道上行发车
						DrawStation.b_s4la = 1;
						StationModelForChange.Button_slfaAction();
					}
				}
				
			}
			
		}else {
			System.out.println("出现错误：找不到车站!");
		}		
	}
	
	
}
