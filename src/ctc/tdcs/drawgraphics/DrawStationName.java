package ctc.tdcs.drawgraphics;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.PositionConstants;
import ctc.pojobean.StationDistrictRelation;
import ctc.tdcs.Layout.TdcsLayout;
import ctc.tdcs.data.BaseParam;
import ctc.tdcs.elements.LabelFigure;

public class DrawStationName {

	private int initX = 5;
	private int initY = 90;
	private int width = 100;
	private int height = 20;
	
	private TdcsLayout tdcsLayoutGraph = TdcsLayout.getInstance();
	private BaseParam tdcsDataset = BaseParam.getInstance();
	private List<StationDistrictRelation> sortedStationDistrictRelationList = new ArrayList<StationDistrictRelation>();
	
	public DrawStationName(){
		sortedStationDistrictRelationList = tdcsDataset.getSortedStationDistrictRelationList(); 
		initX  = BaseParam.getOriginalX() + BaseParam.getStationNameXOffset();
		initY  = BaseParam.getOriginalY()+ BaseParam.getStationNameYOffset();
		width = BaseParam.getStationNameWidth();
		height = BaseParam.getStationNameHeight();
	}
	
	//绘制区段内的车站名称
	public void startDraw(){

		if(sortedStationDistrictRelationList == null || sortedStationDistrictRelationList.size() == 0 )
			return;
		int stationNum = sortedStationDistrictRelationList.size();
	
		for(int i = 0; i < stationNum; i++){
			StationDistrictRelation sdr = sortedStationDistrictRelationList.get(i); 
			initY = initY + sdr.getPredistance();

			LabelFigure stationName = new LabelFigure(false,sdr.getStation_name(),width,height,initX, initY);
					
			stationName.setTextAlignment(PositionConstants.LEFT);
			//stationName.setBackgroundColor(ColorConstants.cyan);
			tdcsLayoutGraph.panelLeft.add(stationName);
		}
		
		
	}
}
