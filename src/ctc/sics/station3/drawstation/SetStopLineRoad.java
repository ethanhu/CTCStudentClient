package ctc.sics.station3.drawstation;

import java.util.ArrayList;

import ctc.sics.station3.elements.common.*;
import ctc.sics.station3.data.*;
import ctc.sics.station3.layout.StationModel;

public class SetStopLineRoad {
	
	BaseParam baseData = BaseParam.getInstance();
	StationModel stationModel = StationModel.getInstance();
	
	public ArrayList<TrackLine> stopLineList = new ArrayList<TrackLine>();
	
	public TrainStopRoad stopRoad;
	public ArrayList<RoadList> stopLineRoadList;
	public ArrayList<String[][]> roadArray = new ArrayList<String[][]>();
	public String[][] road;
	
	public void setStopRoadArray(){
		
		String[][] road_1 = new String[][]{{"S1LA","XLFA"},{"X1LA","SLFA"}};
		roadArray.add(road_1);
		stopLineList.add(baseData.getTrackline_1());		
		
		String[][] road_2 = new String[][]{{"S2LA","XLFA"},{"X2LA","SLFA"}};
		roadArray.add(road_2);		
		stopLineList.add(baseData.getTrackline_2());		
		
		String[][] road_3 = new String[][]{{"S3LA","XLFA"},{"X3LA","SLFA"}};
		roadArray.add(road_3);		
		stopLineList.add(baseData.getTrackline_3());		
				
		String[][] road_4 = new String[][]{{"S4LA","XLFA"},{"X4LA","SLFA"}};
		roadArray.add(road_4);		
		stopLineList.add(baseData.getTrackline_4());
		
		String[][] road_5 = new String[][]{{"D51A","D31A"}};
		roadArray.add(road_5);		
		stopLineList.add(baseData.getTrackline_5());
		
		String[][] road_6 = new String[][]{{"S6LA","XLFA"},{"X6LA","SLFA"}};
		roadArray.add(road_6);		
		stopLineList.add(baseData.getTrackline_6());
		
		String[][] road_xxjz = new String[][]{{"XLA","S1LA"},{"XLA","S2LA"},{"XLA","S3LA"},{"XLA","S4LA"},{"XLA","S6LA"},{"XTA","SLFA"}};
		roadArray.add(road_xxjz);		
		stopLineList.add(baseData.getTrackline_xxjz());
		
		String[][] road_sxjz = new String[][]{{"SLA","X1LA"},{"SLA","X2LA"},{"SLA","X3LA"},{"SLA","X4LA"},{"SLA","X6LA"},{"STA","XLFA"}};
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
					for(int j=0; j < lenRoad; j++){						
						
						if(stationModel.getRoadByName(road[j][0], road[j][1]) != null){
							stopLineRoadList.add(stationModel.getRoadByName(road[j][0], road[j][1]));
						}else{
							System.out.println("setStopRoadArray 找不到符合条件的路径！" + road[j][0] + " --- " + road[j][1]);		
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
