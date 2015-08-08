package ctc.tdcs.elements;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.MidpointLocator;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.swt.graphics.Color;

import ctc.constant.Constants;
import ctc.tdcs.data.BaseParam;

public class LineFigure extends PolylineConnection {
	
	//保存绘制运行车次线时的车次名称
	private String trainName; //车次名
	private String PrestationName;//前站站名
	private String StationName;//本站站名
	private int trainDirection; //车次的方向（上行0，下次1）
	
	//将来可以考虑添加一个后续车站名
	
	private LineAnchorFigure source;//前一站节点信息
	private LineAnchorFigure target; //后一站节点信息
	
	//此标记位用于判断用户绘制新车次时，是画线的起点或终点
	private int drawPositionFlag = Constants.TDCS_TRAINLINE_NONE;
	
	public LineFigure(){
		//设置绘制直线的默认前景颜色
		setForegroundColor(BaseParam.getForegroundColor());
		//设置绘制直线的默认线宽
		setLineWidth(BaseParam.getLineWidth());
		
		setBackGroundColor(BaseParam.getBackgroundColor());
	}
	
	
	public int getDrawPositionFlag() {
		return drawPositionFlag;
	}

	public LineAnchorFigure getSource() {
		return source;
	}
	public void setSource(LineAnchorFigure source) {
		this.source = source;
	}
	public LineAnchorFigure getTarget() {
		return target;
	}
	public void setTarget(LineAnchorFigure target) {
		this.target = target;
	}

	public void setDrawPositionFlag(int drawPositionFlag) {
		this.drawPositionFlag = drawPositionFlag;
	}
	private Color backGroundColor = ColorConstants.white;
	
	public String getPrestationName() {
		return PrestationName;
	}
	public void setPrestationName(String prestationName) {
		PrestationName = prestationName;
	}
	public String getStationName() {
		return StationName;
	}
	public void setStationName(String stationName) {
		StationName = stationName;
	}
	public Color getBackGroundColor() {
		return backGroundColor;
	}
	public void setBackGroundColor(Color backGroundColor) {
		this.backGroundColor = backGroundColor;
	}
	public String getTrainName() {
		return trainName;
	}
	public int getTrainDirection() {
		return trainDirection;
	}

	public void setTrainDirection(int trainDirection) {
		this.trainDirection = trainDirection;
	}	
		
	public void setTrainName(String trainName) {
		this.trainName = trainName;
		Label label = new Label(trainName);
		 //设置图形是否透明  
		label.setOpaque(false);
		
		label.setBackgroundColor(backGroundColor);
		label.setBorder(new LineBorder());
		add(label, new MidpointLocator(this, 0));
	}

}
