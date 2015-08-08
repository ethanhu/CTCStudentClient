package ctc.sics.station.autosystem;

//获得staitonName
public class StationNameService {

	public static StationNameService stationNameService = null;
	public static StationNameService getInstance(){
		if(stationNameService == null){
			stationNameService = new StationNameService();
		}
		return stationNameService;
	}
	
	//获得前站站名
	public String getPreStationName(String stationName){
		
		String preStationName = null;
		
		if(stationName.equalsIgnoreCase("车站一")){
			preStationName = null;
		}else if(stationName.equalsIgnoreCase("车站二")){
			preStationName = "车站一";
		}else if(stationName.equalsIgnoreCase("车站三")){
			preStationName = "车站二";
		}else if(stationName.equalsIgnoreCase("车站四")){
			preStationName = "车站三";
		}else if(stationName.equalsIgnoreCase("车站五")){
			preStationName = "车站四";
		}
		
		return preStationName;
	}
	
	//获得后站站名
	public String getSucStationName(String stationName){
		
		String sucStationName = null;
		
		if(stationName.equalsIgnoreCase("车站一")){
			sucStationName = "车站二";
		}else if(stationName.equalsIgnoreCase("车站二")){
			sucStationName = "车站三";
		}else if(stationName.equalsIgnoreCase("车站三")){
			sucStationName = "车站四";
		}else if(stationName.equalsIgnoreCase("车站四")){
			sucStationName = "车站五";
		}else if(stationName.equalsIgnoreCase("车站五")){
			sucStationName = null;
		}
		
		return sucStationName;
	}
	
	
}
