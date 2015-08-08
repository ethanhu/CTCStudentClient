package ctc.tdcs.drawgraphics;

import java.util.ArrayList;
import java.util.List;
import ctc.constant.Constants;
import ctc.tdcs.Layout.TdcsLayout;
import ctc.tdcs.data.BaseParam;
import ctc.tdcs.data.FromatedTrain;
import ctc.tdcs.elements.LineAnchorFigure;
import ctc.tdcs.elements.LineFigure;
import ctc.transport.message.TrainLineAnchorMessage;
import ctc.util.ErrorLog;

/**此类目前的处理方法:先删除原来的线,再添加新线.
  *考虑:可否用新的颜色绘制用户实际执行的车次????????
  * 此方法可能会存在较多问题. 目前还没有任何调试操作
 */
public class RelocationPlanTrain {

	private TdcsLayout tdcsLayoutGraph = TdcsLayout.getInstance();

	//车次信息list(绘图的格式)
	private List<FromatedTrain> trainPlanFromatList = new ArrayList<FromatedTrain>();
	
	private static int minuteStep = 0;
	
	public RelocationPlanTrain(){
		trainPlanFromatList = BaseParam.getTrainPlanFromatList();
		minuteStep = BaseParam.getMinuteStep();
	}

	private boolean deleteFlag = false;//true表示进行了删除操作
	private String trainName;//服务器发来的车次名
	private int timeType;//服务器发来车次的时间类别
	private String rTime;//接车时间
	private String sTime;//发车时间
	private int targetTime;//分钟序号方法表示的时间
	private int sourceTime;//分钟序号方法表示的时间

	//获取当前站stationName的下一站的站名
	private String getNextStationName(String station){
		String nextStationName = "";//下一站的站名
		
		if (trainPlanFromatList.isEmpty())
			return nextStationName;
		
		int lineNum = trainPlanFromatList.size();
		if (lineNum > 0) {
			for (int i = 0; i < lineNum; i++) { 
				FromatedTrain list = trainPlanFromatList.get(i);
				String trainNameT = list.getTrainName();//车次名称
				String stationNameT = list.getStationName();//本站的站名
				if( (trainNameT == trainName) &&
					(stationNameT.equals(station)))
				{
					nextStationName = list.getNextStationName();//下一站的站名
					break;
				}
			}
		}
		return nextStationName;
	}
	
	//绘制实际运行线
	public void drawTrainLine(TrainLineAnchorMessage rMsg) {
		
		ErrorLog.log("TDCS：RelocationPlanTrain.java  drawTrainLine---start---");
		
		
		deleteFlag = false;
		if(rMsg == null)
			return;
		
		String stationName;//服务器发来的本站站名
		String preStationName;//服务器发来的前站站名
		String sucstationName;//后站站名 
		
		stationName = rMsg.getStationName();
		trainName = rMsg.getTrainName();
		
		if((stationName == null || stationName.equals("null") )||
		   (trainName == null || trainName.equals("null")) )
			return;
		
		preStationName = rMsg.getPrestationName();

		rTime = rMsg.getRTime();//接车时间
		sTime = rMsg.getSTime();//发车时间
		int trainDirection = rMsg.getTrainDirection();//车次方向
		
		
		timeType = rMsg.getTimeType();		
		switch(timeType){
		case Constants.TDCS_TIME_TYPE_ARRIVEAL://普通站机进行接车操作后发消息到TDCS/到站
			
			ErrorLog.log("TDCS：RelocationPlanTrain.java  drawTrainLine---到达---");
			
			removeOldTrainLine(preStationName,stationName);

			//如果进行了删除操作,需要进行重绘操作
			if(deleteFlag){
				/**要绘制直线的上端点车站名称stationName,下端点车站名称nextStationName 车次trainName
				 * 上端点发车时间leaveTime,leaveTimeFlag=true表示leaveTime已经是用分钟序号方法表示的时间
				 * 下端点接车时间arriveTime,arriveTimeFlag=true表示arriveTime已经是用分钟序号方法表示的时间
				 * drawPlanTrain(String trainName, String stationName,String nextStationName,
			                  String leaveTime,boolean leaveTimeFlag,
			                  String arriveTime,boolean arriveTimeFlag) 
				 */ 
				new RedrawPlanTrain().drawPlanTrain(trainName,trainDirection,preStationName,stationName,Integer.toString(sourceTime),true,rTime,false);
			}
			
			break;
		case Constants.TDCS_TIME_TYPE_LEAVE://普通站机进行发车操作后发消息到TDCS/离站
			
			ErrorLog.log("TDCS：RelocationPlanTrain.java  drawTrainLine---离站---");
			
			sucstationName = getNextStationName(stationName);	
			removeOldTrainLine(stationName,sucstationName);
			
			//如果进行了删除操作,需要进行重绘操作
			if(deleteFlag){
				new RedrawPlanTrain().drawPlanTrain(trainName,trainDirection,stationName,sucstationName,sTime,false,Integer.toString(targetTime),true);
			}
			
			break;
		case Constants.TDCS_TIME_TYPE_DOUBLE://普通站机进行收发车操作后发消息到TDCS
			removeOldTrainLine(preStationName,stationName);
			if(deleteFlag){
				new RedrawPlanTrain().drawPlanTrain(trainName,trainDirection,preStationName,stationName,Integer.toString(sourceTime),true,rTime,false);
			}
			
			sucstationName = getNextStationName(stationName);	
			removeOldTrainLine(stationName,sucstationName);
			
			if(deleteFlag){
				new RedrawPlanTrain().drawPlanTrain(trainName,trainDirection,stationName,sucstationName,sTime,false,Integer.toString(targetTime),true);
			}
			break;
		}
		
	
	}
	
	//删除原来的车次
	private void removeOldTrainLine(String preStationName,String localName){
		int listLen = 0;
		synchronized (tdcsLayoutGraph.panelRight){
			List list = tdcsLayoutGraph.panelRight.getChildren(); //获取页面所有的元素
			if (list != null){
				listLen = list.size();
				for (int i = 0; i < listLen; i++) {
					if ( (list.get(i)) instanceof LineFigure) {
						LineFigure figure = (LineFigure)list.get(i);
						if ( (figure.getTrainName() ==  trainName)&& //找到相应的车次
								((figure.getStationName()).equals(localName)) && //本站
								((figure.getPrestationName()).equals(preStationName)) )//前站
						{
							TdcsLayout.panelRight.remove(figure);//删除直线

							//获取车次的起点和终点  删除直线的两个端点
							LineAnchorFigure source = figure.getSource();
							sourceTime = source.getMinuteNo();//分钟序号方法表示的时间
							sourceTime = sourceTime* minuteStep;//屏幕上的时间X表示方法
							
							LineAnchorFigure target = figure.getTarget();
							targetTime = target.getMinuteNo();//分钟序号方法表示的时间
							targetTime = sourceTime* minuteStep;
							
							removeTrainLineAnchor(source,Constants.TDCS_TIME_TYPE_LEAVE,localName);//离站
							removeTrainLineAnchor(target,Constants.TDCS_TIME_TYPE_ARRIVEAL,localName);//到站
							
							deleteFlag = true;
						}
					}
				}//for
			} 
		}
	}
	//接车消息的处理
	private void removeTrainLineAnchor(LineAnchorFigure anchor,int timeType, String localName){
		int listLen = 0;
		synchronized (tdcsLayoutGraph.panelRight){
			List list = tdcsLayoutGraph.panelRight.getChildren(); //获取页面所有的元素
			if (list != null){
				listLen = list.size();
				for (int i = 0; i < listLen; i++) {
					if ( (list.get(i)) instanceof LineAnchorFigure) {
						LineAnchorFigure figure = (LineAnchorFigure)list.get(i);
						if ( (anchor.getTrainName() ==  trainName)&& //找到相应的车次
							 ((anchor.getStationName()).equals(localName))&& //站名相同
							 (anchor.getTimeType() == timeType) )//时间类别
						{
							TdcsLayout.panelRight.remove(figure);	
						}
					}
				}//for
			} 
		}
	}

}


