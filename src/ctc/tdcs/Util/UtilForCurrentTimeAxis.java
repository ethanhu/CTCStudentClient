package ctc.tdcs.Util;

import ctc.tdcs.data.BaseParam;
import ctc.tdcs.drawgraphics.DrawCurrentTimeAxis;
import ctc.tdcs.ui.TdcsToolbarFactory;
import ctc.util.DateUtil;

public class UtilForCurrentTimeAxis {
	
	private UtilForCurrentTimeAxis thisData = null;
	public UtilForCurrentTimeAxis getInstance(){
		if (thisData == null){
			thisData = new UtilForCurrentTimeAxis();
		}
		return thisData;
	}
	public UtilForCurrentTimeAxis(){}
	
   ///////////////////////////////////////////////////////////////////
	
	TdcsToolbarFactory tdcsToolbarFactory = TdcsToolbarFactory.getInstance();

	/*
	 关于时间的说明:
	 虚拟时间决定屏幕上所显示当前时间轴的位置及什么车次可以进行操作.
	 当前时间是计算机的当前时间,用于确定试验已经进行了多长时间
	 时间步长用于表示车次在车站之间的时间,多少分钟相当于机器时间过去多少秒.目前??????????????????????????????????????????????????????????????????????
	 */
	
	private static String vrTime; //虚拟时间
	private static String currentTime;//当前时间 从服务器获取到的当前时间
	private static int timeSLOffset = 0;//服务器的当前时间与本地机的当前时间之间的误差
	private static int timeStep = 1;//
	private static int vtime = 0;//
	///////////////////////////////
	public void displayTimeOnToolBar(String vrTime,String currentTime,String otimeStep)
	{
		timeStep = Integer.valueOf(otimeStep);
		//设置实验开始标记
		BaseParam.setDrawCurrentTimeXFlag(true);
		
		//将0::00或00:00表示的时间统一为00:00格式 
		UtilForCurrentTimeAxis.vrTime = DateUtil.formatedTimeStr(vrTime);
		
		//将00:00:00表示的时间统一为00:00格式
		UtilForCurrentTimeAxis.currentTime = DateUtil.shortedTimeStr(currentTime);
		
		//将00:00格式的字符串表示的时间转换为用分钟表示的时间
		vtime = DateUtil.StrToTime(vrTime);
		int ctime = DateUtil.StrToTime(UtilForCurrentTimeAxis.currentTime);
		
		//获取本地机器的当前时间
	//	String locatlCurrentTime = DateUtil.getCurrentTimeString();
	//	int lctime = DateUtil.StrToTime(DateUtil.shortedTimeStr(locatlCurrentTime));
		
		//timeSLOffset = ctime - lctime; 
		
	    //更新工具条上所显示的时间 
		updateTimeOnTDCStoolbar(UtilForCurrentTimeAxis.vrTime,UtilForCurrentTimeAxis.currentTime);
	//	tdcsToolbarFactory.setTimeStep(timeStep);
		
		tdcsToolbarFactory.setTimeOffset(vtime - ctime);
		
	}
	
	public void updateTimeOnTDCStoolbar(String vrTime, String currentTime)
	{
		//true说明现在可以开始操作了，
		
		if (BaseParam.isDrawCurrentTimeXFlag())
		{
			tdcsToolbarFactory.setVRTime(vrTime);
			tdcsToolbarFactory.setCurrentTime(currentTime);
			
			//启动定时器
			BaseParam.setTimer();
		
			//timeSLOffset = 0; //目前暂时这样
			//BaseParam.setTimeStep(timeStep + timeSLOffset);
			
			//设置定时更新屏幕上显示的时间的步长
			//BaseParam.setTimeStep(timeStep);
			//绘制代表当前时间的红线  目前 的问题,添加此功能后移线有问题
			new DrawCurrentTimeAxis().drawTimeAxis(vtime,timeStep);
	
		}
	}

	
	
}
