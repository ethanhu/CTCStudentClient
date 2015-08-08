package ctc.sics.stationLayout;

import java.util.Timer;

import org.eclipse.draw2d.ActionEvent;
import org.eclipse.draw2d.ActionListener;
import org.eclipse.draw2d.Button;
import org.eclipse.swt.widgets.Display;

import ctc.sics.figure.*;

public class DrawTrackButtonX {

	//S单操按钮
	public static Button button_sdc;
	public static int b_sdc = 0;
	public static Button button_szqx;
	public static int b_szqx = 0;
	public static Button button_szrgjs;
	public static int b_szrgjs = 0;
	public static Button button_sydzjs;
	public static int b_sydzjs = 0;
	
	/**
	 * 绘制股道操作按钮(下行)
	 */
	public static void drawTrackButtonX() {

		int initX = DrawStation.shellWidth - 100;
		int initY = DrawStation.shellHeight - 100;
		int xlength = 100;
		int ylength = 15;

		
		// X道岔单操
		DrawStation.stationLabel = new StationLabel("S道岔单操", DrawStation.labelWidth + 50,
				DrawStation.labelHeight + 5, initX - 25 - 3 * xlength, initY, DrawStation.panel);
		button_sdc = new StationButton(DrawStation.buttonLength, initX - 3 * xlength,
				initY + ylength, DrawStation.buttonInitColor).getButton();
		button_sdc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				b_sdc = 1;				
				
				int timestamp = 10;		
				final Timer timersdc = new Timer();					
				timersdc.schedule(new java.util.TimerTask() {					
						public void run() {
							
							Display.getDefault().syncExec(new Runnable() {
								public void run() {
									DrawTrackButtonX.button_sdc.setBackgroundColor(DrawStation.buttonClickColor);
								}
							});

							try {
								Thread.sleep(5000);
							} catch (InterruptedException e) {
								// break;
							}
							
							Display.getDefault().syncExec(new Runnable() {
								public void run() {
									DrawTrackButtonX.button_sdc.setBackgroundColor(DrawStation.buttonInitColor);
									timersdc.cancel();
								}
							});

						}// run
					//}
				}, 0, timestamp);	
				
			}
		});
		DrawStation.panel.add(button_sdc);
		
		// S总取消
		DrawStation.stationLabel = new StationLabel("S总取消", DrawStation.labelWidth + 50,
				DrawStation.labelHeight + 5, initX - 25 - DrawStation.labelWidth - 2 * xlength, initY,
				DrawStation.panel);
		button_szqx = new StationButton(DrawStation.buttonLength, initX - DrawStation.labelWidth - 2
				* xlength, initY + ylength, DrawStation.buttonInitColor).getButton();
		button_szqx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				b_szqx = 1;
				
				int timestamp = 10;		
				final Timer timerszqx = new Timer();					
				timerszqx.schedule(new java.util.TimerTask() {					
						public void run() {
							
							Display.getDefault().syncExec(new Runnable() {
								public void run() {
									DrawTrackButtonX.button_szqx.setBackgroundColor(DrawStation.buttonClickColor);
								}
							});

							try {
								Thread.sleep(5000);
							} catch (InterruptedException e) {
								// break;
							}
							
							Display.getDefault().syncExec(new Runnable() {
								public void run() {
									DrawTrackButtonX.button_szqx.setBackgroundColor(DrawStation.buttonInitColor);
									timerszqx.cancel();
								}
							});

						}// run
					//}
				}, 0, timestamp);	
				
			}
		});
		DrawStation.panel.add(button_szqx);
		
		// S总人工解锁
		DrawStation.stationLabel = new StationLabel("S总人工解锁", DrawStation.labelWidth + 50,
				DrawStation.labelHeight + 5, initX - 25 - DrawStation.labelWidth - xlength, initY,
				DrawStation.panel);
		button_szrgjs = new StationButton(DrawStation.buttonLength, initX - DrawStation.labelWidth
				- xlength, initY + ylength, DrawStation.buttonInitColor).getButton();
		button_szrgjs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				b_szrgjs = 1;
				
				int timestamp = 10;		
				final Timer timerszrgjs = new Timer();					
				timerszrgjs.schedule(new java.util.TimerTask() {					
						public void run() {
							
							Display.getDefault().syncExec(new Runnable() {
								public void run() {
									DrawTrackButtonX.button_szrgjs.setBackgroundColor(DrawStation.buttonClickColor);
								}
							});

							try {
								Thread.sleep(5000);
							} catch (InterruptedException e) {
								// break;
							}
							
							Display.getDefault().syncExec(new Runnable() {
								public void run() {
									DrawTrackButtonX.button_szrgjs.setBackgroundColor(DrawStation.buttonInitColor);
									timerszrgjs.cancel();
								}
							});

						}// run
					//}
				}, 0, timestamp);	
				
			}
		});
		DrawStation.panel.add(button_szrgjs);

		/*
		// S引导总解锁
		DrawStation.stationLabel = new StationLabel("S引导总解锁", DrawStation.labelWidth + 50,
				DrawStation.labelHeight + 5, initX - 25 - DrawStation.labelWidth, initY, DrawStation.panel);
		button_sydzjs = new StationButton(DrawStation.buttonLength, initX - DrawStation.labelWidth,
				initY + ylength, DrawStation.buttonInitColor).getButton();
		button_sydzjs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.out.println("\n ---S引导总解锁 button_sydzjs  is clicked --");
			}
		});
		DrawStation.panel.add(button_sydzjs);
		*/

	}
	
	
}
