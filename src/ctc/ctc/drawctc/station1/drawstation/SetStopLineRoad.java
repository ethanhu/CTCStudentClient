package ctc.ctc.drawctc.station1.drawstation;

import java.util.ArrayList;

import ctc.ctc.drawctc.station1.data.*;
import ctc.ctc.drawctc.station1.layout.StationModel;
import ctc.ctc.drawctc.station1.elements.common.*;


public class SetStopLineRoad {
	
	BaseParam baseData = BaseParam.getInstance();
	StationModel stationModel = StationModel.getInstance();
	
	public ArrayList<TrackLine> stopLineList = new ArrayList<TrackLine>();
	
	public TrainStopRoad stopRoad;
	public ArrayList<RoadList> stopLineRoadList;
	public ArrayList<String[][]> roadArray = new ArrayList<String[][]>();
	public String[][] road;
	
	public void setStopRoadArray(){
		
		String[][] road_1 = new String[][]{{"S1LA","SLFA"},{"X1LA","XLFA"},{"DX1LA","DX3LB"},{"DX1LA","DX4LB"}};
		roadArray.add(road_1);
		stopLineList.add(baseData.getTrackline_1());		
		
		String[][] road_2 = new String[][]{{"S2LA","SLFA"},{"X2LA","XLFA"},{"DX2LA","DX3LB"},{"DX2LA","DX4LB"}};
		roadArray.add(road_2);		
		stopLineList.add(baseData.getTrackline_2());		
		
		String[][] road_3 = new String[][]{{"S3LA","SLFA"},{"X3LA","XLFA"},{"DX3LA","DX3LB"},{"DX3LA","DX4LB"}};
		roadArray.add(road_3);		
		stopLineList.add(baseData.getTrackline_3());		
		
		String[][] road_3_6 = new String[][]{{"DX3LB","DX1LA"},{"DX3LB","DX2LA"},{"DX3LB","DX3LA"},{"DX3LB","DX4LA"},{"DX3LB","DX5LA"},{"DX3LB","DX6LA"}};
		roadArray.add(road_3_6);		
		stopLineList.add(baseData.getTrackline_3_6());		
		
		String[][] road_4 = new String[][]{{"S4LA","SLFA"},{"X4LA","XLFA"},{"DX4LA","DX3LB"},{"DX4LA","DX4LB"}};
		roadArray.add(road_4);		
		stopLineList.add(baseData.getTrackline_4());
		
		String[][] road_4_5 = new String[][]{{"DX4LB","DX1LA"},{"DX4LB","DX2LA"},{"DX4LB","DX3LA"},{"DX4LB","DX4LA"},{"DX4LB","DX6LA"}};
		roadArray.add(road_4_5);		
		stopLineList.add(baseData.getTrackline_4_5());
		
		String[][] road_5 = new String[][]{{"DX5LA","DX3LB"}};
		roadArray.add(road_5);		
		stopLineList.add(baseData.getTrackline_5());
		
		String[][] road_6 = new String[][]{{"S6LA","SLFA"},{"X6LA","XLFA"},{"DX6LA","DX3LB"},{"DX6LA","DX4LB"}};
		roadArray.add(road_6);		
		stopLineList.add(baseData.getTrackline_6());
		
		String[][] road_xxjz = new String[][]{{"XLA","S1LA"},{"XLA","S2LA"},{"XLA","S3LA"},{"XLA","S4LA"},{"XLA","S6LA"},{"XTA","XLFA"}};
		roadArray.add(road_xxjz);		
		stopLineList.add(baseData.getTrackline_xxjz());
		
		String[][] road_sxjz = new String[][]{{"SLA","X1LA"},{"SLA","X2LA"},{"SLA","X3LA"},{"SLA","X4LA"},{"SLA","X6LA"},{"STA","SLFA"}};
		roadArray.add(road_sxjz);		
		stopLineList.add(baseData.getTrackline_sxjz());		
		
		if(roadArray != null && roadArray.size() != 0){
			
			int len = roadArray.size();
			for(int i=0;i<len;i++){		
				
				road = roadArray.get(i);
				stopRoad = new TrainStopRoad();
				stopRoad.setStopLine(stopLineList.get(i));
				stopLineRoadList = new ArrayList<RoadList>();
				if(road != null && road.length != 0){
					int lenRoad = road.length;
					for(int j=0;j<lenRoad;j++){						
						//System.out.println("StartName = " + road[j][0] + " EndName = " + road[j][1]);	
						if(stationModel.getRoadByName(road[j][0], road[j][1]) != null){
							stopLineRoadList.add(stationModel.getRoadByName(road[j][0], road[j][1]));
						}else{
							System.out.println("找不到符合条件的路径！");		
						}
					}					
				}
				stopRoad.setStopLineRoadList(stopLineRoadList);
				baseData.getAllStopRoadList().add(stopRoad);				
			}
			
		}
		
		
	}
	
	public static void main(String args[]){
		
		new SetStopLineRoad().setStopRoadArray();
		
		
	}
	
	
	
}
