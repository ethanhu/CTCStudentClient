package ctc.sics.db.test;

import java.util.List;

import ctc.pojobean.Station;
import ctc.sics.db.minadata.StationService;

public class TestStationService {

	public static StationService stationService;
	public static List<Station> stationList;
	public static Station station;
	
	public static void main(String[] args) {
		
		stationService = new StationService(); 
		//stationService.connectServer(); // 链接服务器
		stationList = stationService.getStationInfo(); // 获得车站信息
		
		int listLen = stationList.size();
		if(listLen >= 1){
			for(int i = 0; i < listLen; i++ ){ // 输出车站信息
				station = (Station)stationList.get(i);
				if(station != null)	{
					System.out.println("------------" + ++i + "------------");
					System.out.println("车站ID：     " + station.getStation_name());
					System.out.println("车站名称： " + station.getStation_name());
					System.out.println("车站上行线数： " + station.getStation_upnumber());
					System.out.println("车站下行线数： " + station.getStation_downnumber());
				}
			}
		}
	}

}
