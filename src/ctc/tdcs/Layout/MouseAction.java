package ctc.tdcs.Layout;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.MessageBox;

import ctc.constant.Constants;
import ctc.tdcs.Util.UtilForMouseAction;
import ctc.tdcs.Util.UtilForStatics;
import ctc.tdcs.data.BaseParam;
import ctc.tdcs.elements.LineAnchorFigure;
import ctc.tdcs.elements.LineFigure;
import ctc.tdcs.elements.TimeRectangleFigure;
import ctc.tdcs.ui.TdcsMainWindow;


//MouseMotionListener.Stub是MouseMotionListener的一个空实现
//鼠标事件处理
public class MouseAction extends MouseMotionListener.Stub implements MouseListener {

	private void showMsg(String str){
		MessageBox mb = new MessageBox(TdcsMainWindow.getShell(), SWT.ABORT | SWT.ICON_INFORMATION);
		mb.setText("提示信息");//消息框的标题
		mb.setMessage(str);//消息框的提示文字
		mb.open();
	}
	
	public MouseAction(IFigure figure){
		this(figure,true); 
	}
	//添加鼠标监听器
	public MouseAction(IFigure figure, boolean flag) {
		if(flag)
			figure.addMouseMotionListener((MouseMotionListener) this);	//鼠标拖动
		figure.addMouseListener(this);//鼠标点击
	}


	private static Color color = BaseParam.getTimeRectangleColor();
	
	private static LineAnchorFigure sourceAnchor = null;
	private static boolean canNew = false; // 能否画线
	private static String trainName = ""; 
	private static int trainDirection = 1; //车次的方向（下行1，上行0）
	private static LineFigure newTrainLine;//列车线


	private static Point lastPoint;//记录上次节点坐标
	private static LineAnchorFigure clickedTimeRectangle = new LineAnchorFigure();//记录被点击过的时间矩阵
	private static LineFigure clickedLine = new LineFigure();//记录被点击过的直线
	private static UtilForMouseAction utilForDraw = new UtilForMouseAction();
	private UtilForStatics utilForStatics = new UtilForStatics();

	
	TdcsLayout tdcsLayoutGraph = TdcsLayout.getInstance();

	public static void setTrainName(String trainName) {
		MouseAction.trainName = trainName;
	}
	public static void setTrainDirection(int trainDirection) {
		MouseAction.trainDirection = trainDirection;
	}

	//鼠标按下事件
	public void mousePressed(MouseEvent event) {
		lastPoint = event.getLocation();
		int buttonCode = event.button;//event.button 1 鼠标左键  3鼠标右键  2鼠标 中间键

		if(buttonCode == 3)//取消上一步的绘制 即Undo 不发送到服务器
		{
			switch(BaseParam.getOperationType()){
			case Constants.TDCS_TRAIN_TYPE_NEW://添加新车次
				if(newTrainLine != null)
				{
					//屏幕上删除
					newTrainLine.setEnd(new Point(lastPoint.x,lastPoint.y));
					LineAnchorFigure anchor = newTrainLine.getSource();
					if ((anchor != null) &&(anchor.getParent() != null))
						anchor.getParent().remove(anchor);
					anchor = newTrainLine.getTarget();
					if ((anchor != null) &&(anchor.getParent() != null))
						anchor.getParent().remove(anchor);
					
					if(newTrainLine.getParent() != null)
						newTrainLine.getParent().remove(newTrainLine);
					
					if(utilForDraw.deleteStationTrainMap(newTrainLine))
						utilForStatics.deleteScheduleTrainMap(newTrainLine);
					
					canNew = false;	
				}
			}
		}
		else
		if (buttonCode == 1) { //如果点击鼠标左键，则画连接线,删除车次,或调整车次	
			
			Figure figure = ((Figure) event.getSource());

			switch(BaseParam.getOperationType()){
			case Constants.TDCS_MENU_TOOL_NO://用户不能进行任何操作
				break;
				
            /**调整车次的发车或到站时间*/
			case Constants.TDCS_MENU_TOOL_RECTANGLE_ADJUST:
				
				if (event.getSource() instanceof LineAnchorFigure){
					clickedTimeRectangle = new LineAnchorFigure();
					if(figure != null){
						clickedTimeRectangle = (LineAnchorFigure)figure;
						
						//验证时间关系,确定能否在此位置添加新线
						if (! utilForDraw.isDrawFlag(clickedTimeRectangle.getCurrentTime()))
						{
							showMsg("你所选取的车次在此站已经交接!");
							return;
						}
					}
				}
				break;
            /**删除车次*/
			case Constants.TDCS_TRAIN_TYPE_DELETE://删除车次
				if (event.getSource() instanceof LineFigure){
					if(figure != null){
						clickedLine = (LineFigure)figure;
						
						LineAnchorFigure anchor = clickedLine.getSource();
						int time1 = 0;
						if(anchor != null)
							time1 = anchor.getCurrentTime();			
						anchor = clickedLine.getTarget();
						int time2 = 0;
						if(anchor != null)
						   time2 = anchor.getCurrentTime();
						
						//验证时间关系,确定能否在此位置添加新线
						if ( (! utilForDraw.isDrawFlag(time1)) ||(! utilForDraw.isDrawFlag(time2)) )
						{
							showMsg("你所选取的车次已经在运行!");
							return;
						}
					}
				}
				break;
				
            /**添加新车次*/
			case Constants.TDCS_TRAIN_TYPE_NEW://添加新车次
				
				if (event.getSource() instanceof TimeRectangleFigure)// 如果点击了矩形内部，则执行画线	
				{
					TimeRectangleFigure timeRectangle = (TimeRectangleFigure) event.getSource(); //获得点击的矩形

					//验证时间关系,确定能否在此位置添加新线
					if (! utilForDraw.isDrawFlag(timeRectangle.getCurrentTime()))
					{
						showMsg("你所选取的时间已经过去了!");
						return;
					}
					
					LineAnchorFigure anchor = new LineAnchorFigure();

					anchor.setLocation(timeRectangle.getLocation());
					anchor.setOffsetY(timeRectangle.getOffsetY());//屏幕上Y轴的值
					anchor.setTrainDirection(trainDirection);
					anchor.setTrainName(trainName); //车次
					anchor.setStationName(timeRectangle.getStationName()); //车站名
					anchor.setMinuteNo(timeRectangle.getMinuteNo());//分钟序号 直接 从数据库得到的的车次到站或离站的时间转换为分钟表示的序号
					anchor.setOldTime(timeRectangle.getLocation().x);
					anchor.setCurrentTime(timeRectangle.getCurrentTime());//移动过程中最新的时间值  分钟*minuteStep
					anchor.setToolTip(anchor.getTimeLabel());
					anchor.setTrainType(Constants.TDCS_TRAIN_TYPE_NEW);
					
					if (!canNew ) //canNew=false，表示在绘制一条新 的车次周期内，第一次点击矩形，即能够进行画线或新车次的起始站
					{
						newTrainLine = new LineFigure();//列车线

						newTrainLine.setTrainName(trainName);//车次
						newTrainLine.setTrainDirection(trainDirection);//方向
						newTrainLine.setPrestationName(timeRectangle.getStationName());
						newTrainLine.setDrawPositionFlag(Constants.TDCS_TRAINLINE_START);

						if(trainDirection == 1){
							newTrainLine.setForegroundColor(ColorConstants.blue); //设置Line的颜色
						}else{
							newTrainLine.setForegroundColor(ColorConstants.red); //设置Line的颜色
						}

						//设置连线起点的锚点
						newTrainLine.setSourceAnchor(new ChopboxAnchor(anchor)); //设置矩形为ChopboxAnchor源锚点
						newTrainLine.setStart(new Point(anchor.getLocation().x,anchor.getLocation().y)); //设置trainLine的起点
						newTrainLine.setSource(anchor);

						//if (trainDirection == 1)//上行0，下行1
						anchor.setTimeType(Constants.TDCS_TIME_TYPE_LEAVE);//离站

						//System.out.println("canNew:05");
						//已经存在
						if (! utilForDraw.appendStationTrainMap(newTrainLine))
							return; 

					//	System.out.println("canNew:06");
						new MouseAction(anchor);
						
						tdcsLayoutGraph.panelRight.add(anchor);

						sourceAnchor = anchor; //设置此矩形为连线的源矩形

						canNew = true; //设置canNew=true，说明此时同1个画线周期还未结束
					}
					else
						if (canNew) { //canNew=true，说明画线周期已经开始，且其中源矩形已经被选中，在结束之前不能画新的连线。绘制一条车次的终到站

							//在同一车站绘制车次 数据无效
							if ( sourceAnchor.getOffsetY() == anchor.getOffsetY())
							{
								return;
							}

							//newTrainLine.setTrainDirection(trainDirection);//方向
							newTrainLine.setTrainName(trainName);//车次
							newTrainLine.setStationName(timeRectangle.getStationName());
							newTrainLine.setDrawPositionFlag(Constants.TDCS_TRAINLINE_END);

							//设置连线目标的锚点
							newTrainLine.setTargetAnchor(new ChopboxAnchor(anchor)); //设置矩形为ChopboxAnchor目标锚点
							newTrainLine.setEnd(new Point(anchor.getLocation().x,anchor.getLocation().y));
							newTrainLine.setTarget(anchor);
							anchor.setTimeType(Constants.TDCS_TIME_TYPE_ARRIVEAL);//到站

							//已经存在
							if (! utilForDraw.appendStationTrainMap(newTrainLine))
								return; 

							new MouseAction(anchor);
							tdcsLayoutGraph.panelRight.add(anchor);
							
							new MouseAction(newTrainLine);//支持平移和删除
							tdcsLayoutGraph.panelRight.add(newTrainLine);
							
							
							//主要供保存和查询用
							utilForStatics.appendScheduleTrainMap(newTrainLine);//新添加车次的一条直线段绘制完毕

							
							canNew = false; //画线周期结束，重新设置canNew = false
						} 
				}
				break;
				//////////////////////////////////////	

				/*case Constants.TDCS_MENU_TOOL_LINE_PARALLEL://用户选取表示车次的直线进行平移操作
			if (event.getSource() instanceof LineFigure_x) {//点击的是直线本身 进行平移
				if(figure != null)
					clickedLine = (LineFigure_x)figure;
				//clickedLine
				clickedLine.setForegroundColor(ColorConstants.red); //点击直线后，改变其颜色	
			}
			break;*/
			}

		}
		event.consume();
	}

	//拖动鼠标  X轴的值：initX + x*minuteStep - minuteStep
	public void mouseDragged(MouseEvent event) {

		if (lastPoint == null)
			return;

		//计算和显示当前时间
		int x = event.getLocation().x;
	//	String timeStr = DateUtil.timeToStr(XYForTimeRectangle.XToStr(x));
		Point newPoint = event.getLocation();
		Dimension delta = newPoint.getDifference(lastPoint);
		
		switch(BaseParam.getOperationType()){
		case Constants.TDCS_MENU_TOOL_RECTANGLE_ADJUST://用户选取表示车次的直线的上端或下端进行时间调整
			if (event.getSource() instanceof LineAnchorFigure){//点击的是直线的上端或下端. 位于时间轴上
				
				
				lastPoint = newPoint;

				Figure figure = (Figure) event.getSource();
				//设置新的提示信息
				clickedTimeRectangle.setCurrentTime(x);
				
				figure.setToolTip(clickedTimeRectangle.getTimeLabel());
				//设置拖动的Figure的位置  利用translate()方法移动
				figure.setBounds(figure.getBounds().getTranslated(delta.width, delta.height));
				
				//figure.validate();
				//figure.getParent().getLayoutManager().setConstraint(figure, figure.getBounds().getTranslated(delta.width, delta.height)); 
			}
			break;
			////////////////////////////////////////////////////////////////////////////////////	
		/*case Constants.TDCS_MENU_TOOL_LINE_PARALLEL://用户选取表示车次的直线进行平移操作
			if (event.getSource() instanceof LineFigure_x) {//点击的是直线本身 进行平移

				//clickedLine.getClientArea(rect)
				ConnectionAnchor s = clickedLine.getSourceAnchor();
				s.getLocation(newPoint);
				TimeRectangleFigure_x source = clickedLine.getSource();

				TimeRectangleFigure_x timeRectangle = new TimeRectangleFigure_x(); 
				timeRectangle.setLocation(new Point(200,230));
				clickedLine.setSourceAnchor(new ChopboxAnchor(timeRectangle));

				timeRectangle.setLocation(new Point(800,330));
				clickedLine.setTargetAnchor(new ChopboxAnchor(timeRectangle));
				Dimension d = newPoint.getDifference(lastPoint);
				Figure f = ((Figure)event.getSource());
				f.setBounds(f.getBounds().getTranslated(d.width, d.height));
			}
			break;
*/
		}
		event.consume();
	}
	

	public void mouseReleased(MouseEvent event) {
		
		Point endPoint = event.getLocation();

		switch(BaseParam.getOperationType()){
		case Constants.TDCS_MENU_TOOL_RECTANGLE_ADJUST://真正调整工作，是在释放鼠标左键时确定

			if (clickedTimeRectangle != null){
				int x = endPoint.x;
				//String timeStr = DateUtil.timeToStr(UtilForTimeRectangle.XToStr(x));
				clickedTimeRectangle.setCurrentTime(x);
			
				//System.out.println("adjust_020:" + x);
				
				//判断用户的调整是否合理
				Point newPoint = utilForDraw.calcTimeRectangleLocation(clickedTimeRectangle);
			
				//System.out.println("adjust_021:" + newPoint);
				if (newPoint != null){
					//System.out.println("adjust_022:" + x);
					clickedTimeRectangle.setCurrentTime(x);
					clickedTimeRectangle.setOldTime(newPoint.x);//更新到最新的值
					clickedTimeRectangle.setLocation(newPoint);
					//clickedTimeRectangle.setLocation(endPoint);
				}
			}
			break;
			
		case Constants.TDCS_TRAIN_TYPE_DELETE://真正删除工作，是在释放鼠标左键时确定
			//System.out.println("delete_LineFigure_00:" + event.getSource());
			if ( (event.getSource() instanceof LineFigure)
				//	||(event.getSource() instanceof ZoomContainer) 
			    )//
				
			{
				
			//	System.out.println("delete_LineFigure_01");
				
				if(clickedLine != null){
					
					//屏幕上进行删除
					LineAnchorFigure anchor = clickedLine.getSource();
					if ((anchor != null) &&(anchor.getParent() != null))
						anchor.getParent().remove(anchor);
					
					anchor = clickedLine.getTarget();
					if ((anchor != null) &&(anchor.getParent() != null))
						anchor.getParent().remove(anchor);
					
					if(clickedLine.getParent()!= null)
						clickedLine.getParent().remove(clickedLine);
					
					//内存中删除
					if (utilForDraw.deleteStationTrainMap(clickedLine))
						utilForStatics.deleteScheduleTrainMap(clickedLine);
				}
			}
			break;
			/////////////////////////////////////
		/*case Constants.TDCS_MENU_TOOL_LINE_PARALLEL://用户选取表示车次的直线进行平移操作
			break;
			*/
		}
		
		
		clickedLine = null;
		clickedTimeRectangle = null;

		event.consume();
	}


	//移动鼠标
	public void mouseMoved(MouseEvent event) {	
		
		switch(BaseParam.getOperationType()){
		case Constants.TDCS_TRAIN_TYPE_NEW://添加新车次
			if (canNew) {
				Point start= null;
				if(newTrainLine != null)
					start = newTrainLine.getStart();
				
				int xlocation = 0, ylocation = 0;
				
				if (start.x > event.x)
					xlocation = event.x + 4;
				else 
					xlocation = event.x - 4;

				if (start.y > event.y)
					ylocation = event.y + 4;
				else 
					ylocation = event.y - 4;
				
				newTrainLine.setEnd(new Point(xlocation, ylocation));//绘制新车次的终点站
				tdcsLayoutGraph.panelRight.add(newTrainLine);
			}
			break;
		}
		event.consume();
	}
	// 判断鼠标是否在界面中
	public void mouseEntered(MouseEvent event) {					
		if (event.getSource() instanceof TimeRectangleFigure) {
			TimeRectangleFigure rec = (TimeRectangleFigure) event.getSource();				
			rec.setBackgroundColor(ColorConstants.red); // 鼠标在矩形中，则使其变红
		}
		event.consume();
	}

	// 判断鼠标是否在界面外
	public void mouseExited(MouseEvent event) {	
		if (event.getSource() instanceof TimeRectangleFigure) {
			TimeRectangleFigure rec = (TimeRectangleFigure) event.getSource();					
			rec.setBackgroundColor(color); // 鼠标在外面，则使其变淡绿
		}
		event.consume();
	}
	
	//双击事件
	public void mouseDoubleClicked(MouseEvent event) {	}



 }
