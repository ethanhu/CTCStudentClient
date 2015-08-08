package ctc.tdcs.drawgraphics;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Color;

import ctc.constant.Constants;
import ctc.tdcs.Layout.MouseAction;
import ctc.tdcs.Layout.TdcsLayout;
import ctc.tdcs.data.BaseParam;
import ctc.tdcs.data.FromatedTrain;
import ctc.tdcs.elements.LineAnchorFigure;
import ctc.tdcs.elements.LineFigure;
import ctc.tdcs.elements.TimeRectangleFigure;

/**
 * 绘制计划的车次 
 */
//Draw2d 创建一个连接，第一步要做的是需要两个端点。这些端点称作源锚点和目的锚点
public class DrawPlanTrain {

	private List<FromatedTrain> trainPlanFromatList = new ArrayList<FromatedTrain>(); 
	private List<TimeRectangleFigure> timeRectangleList = new ArrayList<TimeRectangleFigure>();
	private Color planTrainDownColor = ColorConstants.blue;
	private Color planTrainUpColor = ColorConstants.red;
	
	TdcsLayout tdcsLayoutGraph = TdcsLayout.getInstance();
	BaseParam tdcsDataset = BaseParam.getInstance();

	private List<LineAnchorFigure> lineAnchorList = new ArrayList<LineAnchorFigure>();
	
	public DrawPlanTrain(){
		trainPlanFromatList = BaseParam.getTrainPlanFromatList();
		lineAnchorList = BaseParam.getLineAnchorList();
		timeRectangleList = BaseParam.getTimeRectangleList();
		planTrainDownColor = BaseParam.getPlanTrainDownColor();
		planTrainUpColor = BaseParam.getPlanTrainUpColor();
	}
	
	public void drawPlanTrain() {
		if(trainPlanFromatList == null || trainPlanFromatList.size() == 0 )
			return;

		int lineNum = trainPlanFromatList.size();
		if (lineNum > 0) {
			for (int i = 0; i < lineNum; i++) {
				FromatedTrain list = trainPlanFromatList.get(i);
				String trainName = list.getTrainName();//车次名称
				String stationName = list.getStationName(); //本站的站名
				int stationTimeX = list.getStationTimeX();//本站的离开时间X坐标
				String nextStationName = list.getNextStationName();//下一站的站名
				int nextStationTimeX = list.getNextStationTimeX();//下一站的到达时间X坐标
				int trainDirection = tdcsDataset.getTrainDirectionByTrainName(trainName);

				//对于数据库中时间是"00:00:00"的不在屏幕上进行显示
				if(! ((stationTimeX == 0 )&& (nextStationTimeX == 0)) ) 
				{
					LineAnchorFigure sourceAnchor = getTimeRectangle(stationName, stationTimeX);
					LineAnchorFigure targetAnchor = getTimeRectangle(nextStationName, nextStationTimeX);
					
					if (sourceAnchor != null && targetAnchor != null) {

						//sourceAnchor.setSize(sourceAnchor.getSize().width + 5, sourceAnchor.getSize().height + 5);
						//sourceAnchor.setVisible(true);//屏幕显示

						//鼠标移动到Ifigure上的时候在鼠标下面显示一个label用来显示相关信息
						//Label label1 = new Label(DateUtil.timeToStr(sourceAnchor.getMinuteNo()));
						//sourceAnchor.setToolTip(label1);
						//String timeStr = DateUtil.timeToStr(sourceAnchor.getMinuteNo());
						sourceAnchor.setCurrentTime(sourceAnchor.getLocation().x);
						sourceAnchor.setOldTime(sourceAnchor.getLocation().x);//sourceAnchor.getMinuteNo()
						sourceAnchor.setToolTip(sourceAnchor.getTimeLabel());
						sourceAnchor.setTrainName(trainName);
						//设置值的地方是: DrawTimeRectangle中的: rectangle.setLocation(new Point(initX + x*minuteStep - minuteStep, initY - minuteStep));
						sourceAnchor.setOffsetY(sourceAnchor.getLocation().y);
						sourceAnchor.setTrainDirection(trainDirection);
						sourceAnchor.setTimeType(Constants.TDCS_TIME_TYPE_LEAVE);
						sourceAnchor.setTrainType(Constants.TDCS_TRAIN_TYPE_NO);

						//System.out.println("原来setCurrentTime:"+sourceAnchor.getLocation().x);

						targetAnchor.setCurrentTime(targetAnchor.getLocation().x);
						targetAnchor.setOldTime(targetAnchor.getLocation().x);//targetAnchor.getMinuteNo()
						targetAnchor.setToolTip(targetAnchor.getTimeLabel());
						targetAnchor.setTrainName(trainName);
						targetAnchor.setOffsetY(targetAnchor.getLocation().y);
						targetAnchor.setTrainDirection(trainDirection);
						targetAnchor.setTimeType(Constants.TDCS_TIME_TYPE_ARRIVEAL);
						targetAnchor.setTrainType(Constants.TDCS_TRAIN_TYPE_NO);

						LineFigure trainLine = new LineFigure();

						trainLine.setTrainName(trainName); //设置Line的名称
						trainLine.setPrestationName(stationName);//连线上端的车站名
						trainLine.setStationName(nextStationName);//连线下端的车站名
						trainLine.setTrainDirection(trainDirection);

						if(trainDirection == 1){
							trainLine.setForegroundColor(planTrainDownColor); //设置下行线的颜色
						}else{
							trainLine.setForegroundColor(planTrainUpColor); //设置上行线的颜色
						}

						//设置连线起点的锚点
						///*		
						trainLine.setSourceAnchor(new ChopboxAnchor(sourceAnchor)); //设置矩形为ChopboxAnchor源锚点
						//trainLine.setStart(new Point(sourceAnchor.getLocation().x,sourceAnchor.getLocation().y)); //设置trainLine的起点

						//设置连线目标的锚点
						trainLine.setTargetAnchor(new ChopboxAnchor(targetAnchor)); //设置矩形为ChopboxAnchor目标锚点
						//	trainLine.setEnd(new Point(targetAnchor.getLocation().x,targetAnchor.getLocation().y )); //设置trainLine的终点
						//*/
						/*
					trainLine.setSourceAnchor(new ChopboxAnchor(sourceAnchor)); // 设置矩形为ChopboxAnchor源锚点
					trainLine.setStart(new Point(sourceAnchor.getLocation().x + sourceAnchor.getBounds().width / 2,
							sourceAnchor.getLocation().y + sourceAnchor.getBounds().height / 2)); // 设置trainLine的起点

					trainLine.setTargetAnchor(new ChopboxAnchor(targetAnchor)); // 设置矩形为ChopboxAnchor目标锚点
					trainLine.setEnd(new Point(targetAnchor.getLocation().x + targetAnchor.getBounds().width / 2,
							targetAnchor.getLocation().y + targetAnchor.getBounds().height / 2)); // 设置trainLine的终点
						 */				
						trainLine.setSource(sourceAnchor);
						trainLine.setTarget(targetAnchor);

						new MouseAction(trainLine);//支持平移和删除
						new MouseAction(targetAnchor);//支持拖动
						new MouseAction(sourceAnchor);//支持拖动

						//System.out.println("Line:" + trainLine.getEnd().x+"::"+trainLine.getEnd().y);
						tdcsLayoutGraph.panelRight.add(sourceAnchor);
						tdcsLayoutGraph.panelRight.add(targetAnchor);
						tdcsLayoutGraph.panelRight.add(trainLine);
					}
				}
			}
		}
	}

	/**
	 * 根据车站名和时间坐标，获得相应的矩形
	 * @param stationName
	 * @param timeX 分钟数  LineAnchorFigure_x rectangle2 = new LineAnchorFigure_x();
	 * @return timeRectangle
	 */
	private LineAnchorFigure getTimeRectangle(String stationName, int timeX) {
		if(timeRectangleList == null || timeRectangleList.size() == 0 )
			return null;

		int len = lineAnchorList.size();
		LineAnchorFigure lineAnchor = new LineAnchorFigure(); 
		//遍历所有车站的时间矩形
		for (int i = 0; i < len; i++) {
			lineAnchor = lineAnchorList.get(i);
			if ( (lineAnchor.getStationName().equalsIgnoreCase(stationName)) &&
				 (lineAnchor.getMinuteNo() == timeX) )
			 {
				Point point = lineAnchorList.get(i).getLocation();
				lineAnchor.setLocation(point);
				break;
			}
			//timeRectangle = null;
		}
		return lineAnchor;
	}	
	
}
