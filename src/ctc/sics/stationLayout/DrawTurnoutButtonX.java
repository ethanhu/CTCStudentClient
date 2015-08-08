package ctc.sics.stationLayout;

import java.util.Timer;

import org.eclipse.draw2d.ActionEvent;
import org.eclipse.draw2d.ActionListener;
import org.eclipse.draw2d.Button;
import org.eclipse.swt.widgets.Display;

import ctc.sics.figure.*;

public class DrawTurnoutButtonX {
	
	public static Button button_xdczdw;
	public static int b_xdczdw = 0;
	public static Button button_xdczfw;
	public static int b_xdczfw = 0;
	public static Button button_xpljl;
	public static int b_xpljl = 0;
	public static Button button_fczk;
	public static int b_fczk = 0;

	/**
	 * 绘制道岔操作按钮(下行)
	 */
	public static void drawTurnoutButtonX() {

		int initX = 50;
		int initY = 100;
		int xlength = 100;
		int ylength = 15;

		// X道岔总定位
		DrawStation.stationLabel = new StationLabel("X道岔总定位", DrawStation.labelWidth + 50,
				DrawStation.labelHeight + 5, initX - 25 + 2 * xlength, initY, DrawStation.panel);
		button_xdczdw = new StationButton(DrawStation.buttonLength, initX + 2 * xlength, initY + ylength,
				DrawStation.buttonInitColor).getButton();
		button_xdczdw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				DrawTurnoutButtonX.b_xdczdw = 1;
				ButtonModel.Button_xdczdwAction();
				

				int timestamp = 10;		
				final Timer timer_xdczdw = new Timer();					
				timer_xdczdw.schedule(new java.util.TimerTask() {					
						public void run() {
							
							Display.getDefault().syncExec(new Runnable() {
								public void run() {
									DrawTurnoutButtonX.button_xdczdw.setBackgroundColor(DrawStation.buttonClickColor);
								}
							});

							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// break;
							}
							
							Display.getDefault().syncExec(new Runnable() {
								public void run() {
									DrawTurnoutButtonX.button_xdczdw.setBackgroundColor(DrawStation.buttonInitColor);
									timer_xdczdw.cancel();
								}
							});

						}// run
					//}
				}, 0, timestamp);	
				
			}
		});
		DrawStation.panel.add(button_xdczdw);
		
		// X道岔总反位
		DrawStation.stationLabel = new StationLabel("X道岔总反位", DrawStation.labelWidth + 50,
				DrawStation.labelHeight + 5, initX - 25 + 3*xlength, initY, DrawStation.panel);
		button_xdczfw = new StationButton(DrawStation.buttonLength, initX + 3*xlength, initY
				+ ylength, DrawStation.buttonInitColor).getButton();
		button_xdczfw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				DrawTurnoutButtonX.b_xdczfw = 1;
				ButtonModel.Button_xdczfwAction();
				

				int timestamp = 10;		
				final Timer timer_xdczfw = new Timer();					
				timer_xdczfw.schedule(new java.util.TimerTask() {					
						public void run() {
							
							Display.getDefault().syncExec(new Runnable() {
								public void run() {
									DrawTurnoutButtonX.button_xdczfw.setBackgroundColor(DrawStation.buttonClickColor);
								}
							});

							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// break;
							}
							
							Display.getDefault().syncExec(new Runnable() {
								public void run() {
									DrawTurnoutButtonX.button_xdczfw.setBackgroundColor(DrawStation.buttonInitColor);
									timer_xdczfw.cancel();
								}
							});

						}// run
					//}
				}, 0, timestamp);	
				
				
			}
		});
		DrawStation.panel.add(button_xdczfw);
		
		
		/*
		// X道岔总定位
		DrawStation.stationLabel = new StationLabel("X道岔总定位", DrawStation.labelWidth + 50,
				DrawStation.labelHeight + 5, initX - 25, initY, DrawStation.panel);
		button_xdczdw = new StationButton(DrawStation.buttonLength, initX, initY + ylength,
				DrawStation.buttonInitColor).getButton();
		button_xdczdw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				DrawTurnoutButtonX.b_xdczdw = 1;
				ButtonModel.Button_xdczdwAction();
				System.out.println("\n ---X道岔总定位 button_xdczdw is clicked --");
			}
		});
		DrawStation.panel.add(button_xdczdw);
		
		// X道岔总反位
		DrawStation.stationLabel = new StationLabel("X道岔总反位", DrawStation.labelWidth + 50,
				DrawStation.labelHeight + 5, initX - 25 + xlength, initY, DrawStation.panel);
		button_xdczfw = new StationButton(DrawStation.buttonLength, initX + xlength, initY
				+ ylength, DrawStation.buttonInitColor).getButton();
		button_xdczfw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				DrawTurnoutButtonX.b_xdczfw = 1;
				ButtonModel.Button_xdczfwAction();
				System.out.println("\n ---X道岔总反位 button_xdczfw is clicked --");
			}
		});
		DrawStation.panel.add(button_xdczfw);
		
		
		// X排列进路
		DrawStation.stationLabel = new StationLabel("X排列进路", DrawStation.labelWidth + 50,
				DrawStation.labelHeight + 5, initX - 25 + 2 * xlength, initY, DrawStation.panel);
		button_xpljl = new StationButton(DrawStation.buttonLength, initX + 2 * xlength,
				initY + ylength, DrawStation.buttonInitColor).getButton();
		button_xpljl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.out.println("\n ---X排列进路 button_xpljl is clicked --");
			}
		});
		DrawStation.panel.add(button_xpljl);
		// 非常站控
		DrawStation.stationLabel = new StationLabel("非常站控", DrawStation.labelWidth + 50,
				DrawStation.labelHeight + 5, initX - 25 + 3 * xlength, initY, DrawStation.panel);
		button_fczk = new StationButton(DrawStation.buttonLength, initX + 3 * xlength,
				initY + ylength, DrawStation.buttonInitColor).getButton();
		button_fczk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.out.println("\n ---非常站控 button_fczk is clicked --");
			}
		});
		DrawStation.panel.add(button_fczk);
		*/

	}
	
}
