package ctc.ctc.ctc2sics;

import ctc.transport.message.ErrorMessage;
import ctc.transport.message.TaskMessage;
import ctc.util.ErrorLog;

/**
 * 
 * @author ThinkPad
 *
 */
public class ProSICSMessage {

	private static ProSICSMessage proModel = null;
	ProSICSMessageStation1 proModelStation1 = ProSICSMessageStation1.getInstance();
	ProSICSMessageStation2 proModelStation2 = ProSICSMessageStation2.getInstance();
	ProSICSMessageStation3 proModelStation3 = ProSICSMessageStation3.getInstance();
	ProSICSMessageStation4 proModelStation4 = ProSICSMessageStation4.getInstance();
	ProSICSMessageStation5 proModelStation5 = ProSICSMessageStation5.getInstance();
	
	public static ProSICSMessage getInstance() {
		if (proModel == null) {
			proModel = new ProSICSMessage();
		}
		return proModel;		
	}
	
	//处理TaskMessage消息
	public void proTaskMessage(TaskMessage msg){
		
		String stationName = msg.getStationName();
		String startName = msg.getStartName();
		String endName = msg.getEndName();
		int color = msg.getColor();
		
		if(stationName.equalsIgnoreCase("车站一")){
			proModelStation1.processTask(startName, endName, color);
		}else if(stationName.equalsIgnoreCase("车站二")){
			proModelStation2.processTask(startName, endName, color);
		}else if(stationName.equalsIgnoreCase("车站三")){
			proModelStation3.processTask(startName, endName, color);
		}else if(stationName.equalsIgnoreCase("车站四")){
			proModelStation4.processTask(startName, endName, color);
		}else if(stationName.equalsIgnoreCase("车站五")){ //标准站五
			proModelStation5.processTask(startName, endName, color);
		}else{
			ErrorLog.log("CTC收到TaskMessage信息:车站无法找到！");
		}
		
	}
	
	/**
	 * @param stationName
	 * @param startName
	 * @param endName
	 * @param color
	 */
	//处理CTC消息
	public void proCTCTaskMessage(String stationName, String startName, String endName, int color){
		
		if(stationName.equalsIgnoreCase("车站一")){
			proModelStation1.processTask(startName, endName, color);
		}else if(stationName.equalsIgnoreCase("车站二")){
			proModelStation2.processTask(startName, endName, color);
		}else if(stationName.equalsIgnoreCase("车站三")){
			proModelStation3.processTask(startName, endName, color);
		}else if(stationName.equalsIgnoreCase("车站四")){
			proModelStation4.processTask(startName, endName, color);
		}else if(stationName.equalsIgnoreCase("车站五")){
			proModelStation5.processTask(startName, endName, color);
		}else{
			System.out.println("CTC:ProSICSMessage:proCTCTaskMessage():找不到车站!");
		}
		
	}
	
	//处理ErrorMessage消息
	public void proErrorMessage(ErrorMessage msg){
		
		String stationName = msg.getStationName();
		boolean type = msg.isType();
		String figName = msg.getFigName();
		
		if(stationName.equalsIgnoreCase("车站一")){
			if(type == true){
				proModelStation1.HFGZ(figName);
			}else{
				proModelStation1.SZGZ(figName);
			}			
		}else if(stationName.equalsIgnoreCase("车站二")){
			if(type == true){
				proModelStation2.HFGZ(figName);
			}else{
				proModelStation2.SZGZ(figName);
			}				
		}else if(stationName.equalsIgnoreCase("车站三")){
			if(type == true){
				proModelStation3.HFGZ(figName);
			}else{
				proModelStation3.SZGZ(figName);
			}				
		}else if(stationName.equalsIgnoreCase("车站四")){
			if(type == true){
				proModelStation4.HFGZ(figName);
			}else{
				proModelStation4.SZGZ(figName);
			}				
		}else{ //标准站五
			if(type == true){
				proModelStation5.HFGZ(figName);
			}else{
				proModelStation5.SZGZ(figName);
			}				
		}		
	}	
}
