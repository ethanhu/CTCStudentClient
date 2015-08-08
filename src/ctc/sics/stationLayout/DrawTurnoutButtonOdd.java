package ctc.sics.stationLayout;

import java.util.Timer;

import org.eclipse.draw2d.ActionEvent;
import org.eclipse.draw2d.ActionListener;
import org.eclipse.draw2d.Button;
import org.eclipse.swt.widgets.Display;

import ctc.sics.figure.*;


public class DrawTurnoutButtonOdd {

	public static Button button_1_3;
	public static int b_1_3 = 0;
	public static Button button_5_7;
	public static int b_5_7 = 0;
	public static Button button_9;
	public static int b_9 = 0;
	public static Button button_11;
	public static int b_11 = 0;
	
	
	/**
	 * 绘制道岔单操按钮(奇数)
	 */
	public static void drawTurnoutButtonOdd() {

		int initX = 60;
		int initY = 20;
		int xlength = 30;
		int ylength = 10;

		// 绘制1/3
		DrawStation.stationLabel = new StationLabel("1/3", DrawStation.labelWidth, DrawStation.labelHeight, initX,
				initY, DrawStation.panel);
		button_1_3 = new StationButton(DrawStation.buttonLength, initX, initY + ylength,
				DrawStation.buttonInitColor).getButton();
		button_1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.out.println("\n ---button_1_3 is clicked --");
				//button_1_3.setBackgroundColor(DrawStation.buttonClickColor);
				b_1_3 = 1;
				ButtonModel.Button_1_3Action();		
				
				int timestamp = 10;		
				final Timer timer_1_3 = new Timer();					
				timer_1_3.schedule(new java.util.TimerTask() {					
						public void run() {
							
							Display.getDefault().syncExec(new Runnable() {
								public void run() {
									DrawTurnoutButtonOdd.button_1_3.setBackgroundColor(DrawStation.buttonClickColor);
								}
							});

							try {
								Thread.sleep(3000);
							} catch (InterruptedException e) {
								// break;
							}
							
							Display.getDefault().syncExec(new Runnable() {
								public void run() {
									DrawTurnoutButtonOdd.button_1_3.setBackgroundColor(DrawStation.buttonInitColor);
									timer_1_3.cancel();
								}
							});

						}// run
					//}
				}, 0, timestamp);		
				
			}
		});
		DrawStation.panel.add(button_1_3);
		
		// 绘制5/7
		DrawStation.stationLabel = new StationLabel("5/7", DrawStation.labelWidth, DrawStation.labelHeight, initX
				+ xlength, initY, DrawStation.panel);
		button_5_7 = new StationButton(DrawStation.buttonLength, initX + xlength, initY
				+ ylength, DrawStation.buttonInitColor).getButton();
		button_5_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.out.println("\n ---button_5_7 is clicked --");
				//button_5_7.setBackgroundColor(DrawStation.buttonClickColor);
				b_5_7 = 1;
				ButtonModel.Button_5_7Action();	
				

				int timestamp = 10;		
				final Timer timer_5_7 = new Timer();					
				timer_5_7.schedule(new java.util.TimerTask() {					
						public void run() {
							
							Display.getDefault().syncExec(new Runnable() {
								public void run() {
									DrawTurnoutButtonOdd.button_5_7.setBackgroundColor(DrawStation.buttonClickColor);
								}
							});

							try {
								Thread.sleep(3000);
							} catch (InterruptedException e) {
								// break;
							}
							
							Display.getDefault().syncExec(new Runnable() {
								public void run() {
									DrawTurnoutButtonOdd.button_5_7.setBackgroundColor(DrawStation.buttonInitColor);
									timer_5_7.cancel();
								}
							});

						}// run
					//}
				}, 0, timestamp);		
				
				
			}
		});
		DrawStation.panel.add(button_5_7);
		
		// 绘制9
		DrawStation.stationLabel = new StationLabel("9", DrawStation.labelWidth, DrawStation.labelHeight, initX + 2
				* xlength, initY, DrawStation.panel);
		button_9 = new StationButton(DrawStation.buttonLength, initX + 2 * xlength,
				initY + ylength, DrawStation.buttonInitColor).getButton();
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.out.println("\n ---button_9 is clicked --");
				//button_9.setBackgroundColor(DrawStation.buttonClickColor);
				b_9 = 1;
				ButtonModel.Button_9Action();	
				

				int timestamp = 10;		
				final Timer timer_9 = new Timer();					
				timer_9.schedule(new java.util.TimerTask() {					
						public void run() {
							
							Display.getDefault().syncExec(new Runnable() {
								public void run() {
									DrawTurnoutButtonOdd.button_9.setBackgroundColor(DrawStation.buttonClickColor);
								}
							});

							try {
								Thread.sleep(3000);
							} catch (InterruptedException e) {
								// break;
							}
							
							Display.getDefault().syncExec(new Runnable() {
								public void run() {
									DrawTurnoutButtonOdd.button_9.setBackgroundColor(DrawStation.buttonInitColor);
									timer_9.cancel();
								}
							});

						}// run
					//}
				}, 0, timestamp);		
				
				
			}
		});
		DrawStation.panel.add(button_9);
		
		// 绘制11
		DrawStation.stationLabel = new StationLabel("11", DrawStation.labelWidth, DrawStation.labelHeight, initX
				+ 3 * xlength, initY, DrawStation.panel);
		button_11 = new StationButton(DrawStation.buttonLength, initX + 3 * xlength,
				initY + ylength, DrawStation.buttonInitColor).getButton();
		button_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.out.println("\n ---button_11 is clicked --");
				//button_11.setBackgroundColor(DrawStation.buttonClickColor);
				b_11 = 1;
				ButtonModel.Button_11Action();	
				

				int timestamp = 10;		
				final Timer timer_11 = new Timer();					
				timer_11.schedule(new java.util.TimerTask() {					
						public void run() {
							
							Display.getDefault().syncExec(new Runnable() {
								public void run() {
									DrawTurnoutButtonOdd.button_11.setBackgroundColor(DrawStation.buttonClickColor);
								}
							});

							try {
								Thread.sleep(3000);
							} catch (InterruptedException e) {
								// break;
							}
							
							Display.getDefault().syncExec(new Runnable() {
								public void run() {
									DrawTurnoutButtonOdd.button_11.setBackgroundColor(DrawStation.buttonInitColor);
									timer_11.cancel();
								}
							});

						}// run
					//}
				}, 0, timestamp);		
				
				
			}
		});
		DrawStation.panel.add(button_11);

	}

}
