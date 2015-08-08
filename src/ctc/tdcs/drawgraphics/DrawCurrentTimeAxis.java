package ctc.tdcs.drawgraphics;

import java.util.List;
import java.util.Timer;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.widgets.Display;

import ctc.constant.Constants;
import ctc.tdcs.Layout.TdcsLayout;
import ctc.tdcs.Util.*;
import ctc.tdcs.data.BaseParam;
import ctc.tdcs.elements.TimeAxisXFigure;
import ctc.tdcs.ui.TdcsToolbarFactory;
import ctc.util.DateUtil;

public class DrawCurrentTimeAxis {
	
	private static int minuteStep = 0;
	private int initY = 100; //确定线的长度
	private int sumStationDistance; //区间之间的距离
	
	private static int timeX; //时间轴的起始位置  用于定位 currentTimeX
	private static int currentTimeX = 1; //显示当前时间的红线的位置 currentTime*minuteStep
	
	private static TimeAxisXFigure timeAxisXFigure = new TimeAxisXFigure();
	
	private static int backStep = minuteStep;
	
	final int timeStamp = Constants.TIMESTAMP;//延时x秒 
	Timer timer = new Timer();
	private static int redHotLineX = 0;
	private static int timeStep = 1;
		
	////////////////
	private TdcsToolbarFactory tdcsToolbarFactory = new TdcsToolbarFactory().getInstance();
	private TdcsLayout tdcsLayoutGraph = TdcsLayout.getInstance();
	
	
	public DrawCurrentTimeAxis(){ 
		minuteStep = BaseParam.getMinuteStep();
		initY = UtilForTimeRectangle.getInitY();
		sumStationDistance = BaseParam.getSumStationDistance(); 
		timeX = BaseParam.getTimeXOffset();
	}

	//先删除原来的红线,然后再在新的位置绘制
	public void drawTimeAxis(int initVTime,int step) {
		
		timeStep = step;
		
		removeOldTimeAxisX();
		
		currentTimeX = initVTime * minuteStep;
		timeAxisXFigure = new TimeAxisXFigure();
		
		redHotLineX = timeX + currentTimeX + backStep;
		timeAxisXFigure.setEndpoints(new Point(redHotLineX, initY - 80), new Point(redHotLineX, initY + sumStationDistance + 20));
		//backStep += minuteStep;
		
		tdcsLayoutGraph.setHorizontalBar(currentTimeX,backStep);
	    
		TdcsLayout.panelRight.add(timeAxisXFigure);
		
		//java.util.TimerTask() { public void run() { //server.checkNewMail(); 要操作的方法 } }, 0, 5*60*1000); 
		//第一个参数是要操作的方法，第二个参数是要设定延迟的时间，第三个参数是周期的设定，每隔多长时间执行该操作。
		timer.schedule(new java.util.TimerTask() {
			public void run() {
				//reLocation();
				Display.getDefault().asyncExec(
						new Runnable() {
							public void run() {
								if (BaseParam.isDrawCurrentTimeXFlag())
									reLocation();
							}
						});
				
			}// run
			}, 0, timeStamp);
		
			
		/*
		new Thread(new Runnable() {
		   public void run() {
		     while (true) {
		       try { Thread.sleep(timeStamp); } catch (Exception e) { }
				Display.getDefault().asyncExec(
						new Runnable() {
							public void run() {
								timeStep++;
					            reLocation();
					            }
						});
		       
		     }
		   }
		 }).start();*/
		
	}
	private void reLocation()
	{
		tdcsToolbarFactory.updateTime(timeStep);
		
		String vtimeStr = tdcsToolbarFactory.getVRTime();
		
		backStep += minuteStep;
		
		currentTimeX = DateUtil.StrToTime(vtimeStr)* minuteStep;

		tdcsLayoutGraph.setHorizontalBar(currentTimeX,backStep);
		
		//syncExec ,asyncExec 以及timerExec执行的线程可以做的事只能改变UI部件的属性
		//removeOldTimeAxisX();
		
		//timeAxisXFigure = new TimeAxisXFigure();
	
		timeAxisXFigure.setEndpoints(new Point(redHotLineX + backStep, initY - 80), new Point(redHotLineX +backStep, initY + sumStationDistance + 20));
	    
		//TdcsLayout.panelRight.add(timeAxisXFigure);
	}

	public void removeOldTimeAxisX(){
		int listLen = 0;
		synchronized (tdcsLayoutGraph.panelRight){
		List list = tdcsLayoutGraph.panelRight.getChildren(); //获取页面所有的元素
		if (list != null){
			listLen = list.size();
			for (int i = 0; i < listLen; i++) {
				if ( (list.get(i)) instanceof TimeAxisXFigure) {
					TimeAxisXFigure figure = (TimeAxisXFigure)list.get(i);
					TdcsLayout.panelRight.remove(figure);
				}
			}//for
		} 
	}
	}
}
/*
 		
	private int getCurrentTime(){
		return UtilForTime2X.timeToX(DateUtil.getCurrentTimeString());	
	}
	
	
 		//设置显示当前时间的红线的位置
	//	currentTimeX = (int)( getCurrentTime()*minuteStep);// timeX + screenWidth/2+
    	//BaseParam.setCurrentTimeX(currentTimeX);//?????????????????????????????????????????????????????????????????????????????????????????????????????
		
    	//设置当前时间
    	//timeText = DateUtil.getCurrentTimeString();
    	//tdcsToolbarFactory.setCurrentTime(timeText);////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    	
    	//关闭定时器
		Listener exitListener = new Listener() {
		  public void handleEvent(Event e) {
			// timer.cancel();
			 //System.exit(0);
		  }};
		parentShell.addListener(SWT.Close, exitListener);
 */

/*
*/	 

