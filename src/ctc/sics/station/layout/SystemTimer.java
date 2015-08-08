package ctc.sics.station.layout;

import java.util.Timer;
import org.eclipse.swt.widgets.Display;

import ctc.sics.util.DateUtil;

import ctc.sics.station.data.*;


public class SystemTimer {

	BaseParam baseDataForStation = BaseParam.getInstance();	
	
	final Timer timer = new Timer();
	
	int timestamp = 1000;	
	
	//先删除原来的红线,然后再在新的位置绘制
	public void SystemRun(int initVTime,int step) {

		timer.schedule(new java.util.TimerTask() {
			public void run() {
				
				Display.getDefault().asyncExec(
						new Runnable() {
							public void run() {
								reLocation();
							}
						});				
			}//run
			}, 0, timestamp);			
	}
	
	
	private void reLocation()	{
		
		String curTimeStr = DateUtil.getNowTime();
		baseDataForStation.getTimeLabel().setText(curTimeStr);		
		//System.out.println("时间：" + curTimeStr );
		
	}
	
}
