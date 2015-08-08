package ctc.ctc.drawctc.ctcmain.autosystem;

import java.util.Timer;

import org.eclipse.swt.widgets.Display;

import ctc.ctc.drawctc.ctcmain.data.BaseParam;
import ctc.sics.util.DateUtil;

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
