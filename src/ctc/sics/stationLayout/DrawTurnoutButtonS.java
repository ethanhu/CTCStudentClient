package ctc.sics.stationLayout;

import java.util.Timer;

import org.eclipse.draw2d.ActionEvent;
import org.eclipse.draw2d.ActionListener;
import org.eclipse.draw2d.Button;
import org.eclipse.swt.widgets.Display;

import ctc.sics.figure.*;


public class DrawTurnoutButtonS {

	public static Button button_sdczdw;
	public static int b_sdczdw = 0;
	public static Button button_sdczfw;
	public static int b_sdczfw = 0;
	public static Button button_zk;
	public static int b_zk = 0;
	public static Button button_spljl;
	public static int b_spljl = 0;
	
	
	/**
	 * 绘制道岔操作按钮(上行)
	 */
	public static void drawTurnoutButtonS() {
		
		int initX = DrawStation.shellWidth - 50;
		int initY = 100;
		int xlength = 100;
		int ylength = 15;

		// S道岔总定位
		DrawStation.stationLabel = new StationLabel("S道岔总定位", DrawStation.labelWidth + 50,
				DrawStation.labelHeight + 5, initX - 25 - DrawStation.labelWidth - 3 * xlength, initY,
				DrawStation.panel);
		button_sdczdw = new StationButton(DrawStation.buttonLength, initX - DrawStation.labelWidth - 3
				* xlength, initY + ylength, DrawStation.buttonInitColor).getButton();
		button_sdczdw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				DrawTurnoutButtonS.b_sdczdw = 1;
				ButtonModel.Button_sdczdwAction();
				
				int timestamp = 10;		
				final Timer timer_sdczdw = new Timer();					
				timer_sdczdw.schedule(new java.util.TimerTask() {					
						public void run() {
							
							Display.getDefault().syncExec(new Runnable() {
								public void run() {
									DrawTurnoutButtonS.button_sdczdw.setBackgroundColor(DrawStation.buttonClickColor);
								}
							});

							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// break;
							}
							
							Display.getDefault().syncExec(new Runnable() {
								public void run() {
									DrawTurnoutButtonS.button_sdczdw.setBackgroundColor(DrawStation.buttonInitColor);
									timer_sdczdw.cancel();
								}
							});

						}// run
					//}
				}, 0, timestamp);						
				
				
			}
		});
		DrawStation.panel.add(button_sdczdw);
		// S道岔总反位
		DrawStation.stationLabel = new StationLabel("S道岔总反位", DrawStation.labelWidth + 50,
				DrawStation.labelHeight + 5, initX - 25 - DrawStation.labelWidth - 2 * xlength, initY,
				DrawStation.panel);
		button_sdczfw = new StationButton(DrawStation.buttonLength, initX - DrawStation.labelWidth - 2
				* xlength, initY + ylength, DrawStation.buttonInitColor).getButton();
		button_sdczfw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				DrawTurnoutButtonS.b_sdczfw = 1;
				ButtonModel.Button_sdczfwAction();
				

				int timestamp = 10;		
				final Timer timer_sdczfw = new Timer();					
				timer_sdczfw.schedule(new java.util.TimerTask() {					
						public void run() {
							
							Display.getDefault().syncExec(new Runnable() {
								public void run() {
									DrawTurnoutButtonS.button_sdczfw.setBackgroundColor(DrawStation.buttonClickColor);
								}
							});

							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// break;
							}
							
							Display.getDefault().syncExec(new Runnable() {
								public void run() {
									DrawTurnoutButtonS.button_sdczfw.setBackgroundColor(DrawStation.buttonInitColor);
									timer_sdczfw.cancel();
								}
							});

						}// run
					//}
				}, 0, timestamp);						
				
				
			}
		});
		DrawStation.panel.add(button_sdczfw);
		
		/*
		// 站 控
		DrawStation.stationLabel = new StationLabel("站    控", DrawStation.labelWidth + 50,
				DrawStation.labelHeight + 5, initX - 25 - DrawStation.labelWidth - xlength, initY,
				DrawStation.panel);
		button_zk = new StationButton(DrawStation.buttonLength, initX - DrawStation.labelWidth
				- xlength, initY + ylength, DrawStation.buttonInitColor).getButton();
		button_zk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.out.println("\n ---站控 button_zk is clicked --");
			}
		});
		DrawStation.panel.add(button_zk);
		// S排列进路
		DrawStation.stationLabel = new StationLabel("S排列进路", DrawStation.labelWidth + 50,
				DrawStation.labelHeight + 5, initX - 25 - DrawStation.labelWidth, initY, DrawStation.panel);
		button_spljl = new StationButton(DrawStation.buttonLength, initX - DrawStation.labelWidth,
				initY + ylength, DrawStation.buttonInitColor).getButton();
		button_spljl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.out.println("\n ---S排列进路 button_spljl is clicked --");
			}
		});
		DrawStation.panel.add(button_spljl);
		 */

	}
	
}
