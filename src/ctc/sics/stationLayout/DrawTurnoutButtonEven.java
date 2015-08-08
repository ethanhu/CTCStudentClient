package ctc.sics.stationLayout;

import java.util.Timer;

import org.eclipse.draw2d.ActionEvent;
import org.eclipse.draw2d.ActionListener;
import org.eclipse.draw2d.Button;
import org.eclipse.swt.widgets.Display;

import ctc.sics.figure.*;


public class DrawTurnoutButtonEven {

	public static Button button_2_4;
	public static int b_2_4 = 0;
	public static Button button_6_8;
	public static int b_6_8 = 0;
	public static Button button_10;
	public static int b_10 = 0;
	public static Button button_12;
	public static int b_12 = 0;
	
	/**
	 * 绘制道岔单操按钮(偶数)
	 */
	public static void drawTurnoutButtonEven() {

		int initX = DrawStation.shellWidth - 60;
		int initY = 20;
		int xlength = 30;
		int ylength = 10;

		// 绘制2/4
		DrawStation.stationLabel = new StationLabel("2/4", DrawStation.labelWidth, DrawStation.labelHeight, initX
				- DrawStation.labelWidth - 3 * xlength, initY, DrawStation.panel);
		button_2_4 = new StationButton(DrawStation.buttonLength, initX - DrawStation.labelWidth - 3
				* xlength, initY + ylength, DrawStation.buttonInitColor).getButton();
		button_2_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				b_2_4 = 1;
				//button_2_4.setBackgroundColor(DrawStation.buttonClickColor);
				ButtonModel.Button_2_4Action();
				System.out.println("\n ---button_2_4 is clicked --");
				
				
				int timestamp = 10;		
				final Timer timer_2_4 = new Timer();					
				timer_2_4.schedule(new java.util.TimerTask() {					
						public void run() {
							
							Display.getDefault().syncExec(new Runnable() {
								public void run() {
									DrawTurnoutButtonEven.button_2_4.setBackgroundColor(DrawStation.buttonClickColor);
								}
							});

							try {
								Thread.sleep(3000);
							} catch (InterruptedException e) {
								// break;
							}
							
							Display.getDefault().syncExec(new Runnable() {
								public void run() {
									DrawTurnoutButtonEven.button_2_4.setBackgroundColor(DrawStation.buttonInitColor);
									timer_2_4.cancel();
								}
							});

						}// run
					//}
				}, 0, timestamp);	
				
			}
		});
		DrawStation.panel.add(button_2_4);
		
		// 绘制6/8
		DrawStation.stationLabel = new StationLabel("6/8", DrawStation.labelWidth, DrawStation.labelHeight, initX
				- DrawStation.labelWidth - 2 * xlength, initY, DrawStation.panel);
		button_6_8 = new StationButton(DrawStation.buttonLength, initX - DrawStation.labelWidth - 2
				* xlength, initY + ylength, DrawStation.buttonInitColor).getButton();
		button_6_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.out.println("\n ---button_6_8 is clicked --\n");
				b_6_8 = 1;
				//button_6_8.setBackgroundColor(DrawStation.buttonClickColor);
				ButtonModel.Button_6_8Action();
				
				int timestamp = 10;		
				final Timer timer_6_8 = new Timer();					
				timer_6_8.schedule(new java.util.TimerTask() {					
						public void run() {
							
							Display.getDefault().syncExec(new Runnable() {
								public void run() {
									DrawTurnoutButtonEven.button_6_8.setBackgroundColor(DrawStation.buttonClickColor);
								}
							});

							try {
								Thread.sleep(3000);
							} catch (InterruptedException e) {
								// break;
							}
							
							Display.getDefault().syncExec(new Runnable() {
								public void run() {
									DrawTurnoutButtonEven.button_6_8.setBackgroundColor(DrawStation.buttonInitColor);
									timer_6_8.cancel();
								}
							});

						}// run
					//}
				}, 0, timestamp);	
				
			}
		});
		DrawStation.panel.add(button_6_8);
		
		// 绘制10
		DrawStation.stationLabel = new StationLabel("10", DrawStation.labelWidth, DrawStation.labelHeight, initX
				- DrawStation.labelWidth - xlength, initY, DrawStation.panel);
		button_10 = new StationButton(DrawStation.buttonLength, initX - DrawStation.labelWidth
				- xlength, initY + ylength, DrawStation.buttonInitColor).getButton();
		button_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.out.println("\n ---button_10 is clicked --");
				b_10 = 1;
				//button_10.setBackgroundColor(DrawStation.buttonClickColor);
				ButtonModel.Button_10Action();
				
				int timestamp = 10;		
				final Timer timer_10 = new Timer();					
				timer_10.schedule(new java.util.TimerTask() {					
						public void run() {
							
							Display.getDefault().syncExec(new Runnable() {
								public void run() {
									DrawTurnoutButtonEven.button_10.setBackgroundColor(DrawStation.buttonClickColor);
								}
							});

							try {
								Thread.sleep(3000);
							} catch (InterruptedException e) {
								// break;
							}
							
							Display.getDefault().syncExec(new Runnable() {
								public void run() {
									DrawTurnoutButtonEven.button_10.setBackgroundColor(DrawStation.buttonInitColor);
									timer_10.cancel();
								}
							});

						}// run
					//}
				}, 0, timestamp);	
			}
		});
		DrawStation.panel.add(button_10);
		
		// 绘制12
		DrawStation.stationLabel = new StationLabel("12", DrawStation.labelWidth, DrawStation.labelHeight, initX
				- DrawStation.labelWidth, initY, DrawStation.panel);
		button_12 = new StationButton(DrawStation.buttonLength, initX - DrawStation.labelWidth,
				initY + ylength, DrawStation.buttonInitColor).getButton();
		button_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.out.println("\n ---button_12 is clicked --");
				b_12 = 1;
				//button_12.setBackgroundColor(DrawStation.buttonClickColor);
				ButtonModel.Button_12Action();
				
				int timestamp = 10;		
				final Timer timer_12 = new Timer();					
				timer_12.schedule(new java.util.TimerTask() {					
						public void run() {
							
							Display.getDefault().syncExec(new Runnable() {
								public void run() {
									DrawTurnoutButtonEven.button_12.setBackgroundColor(DrawStation.buttonClickColor);
								}
							});

							try {
								Thread.sleep(3000);
							} catch (InterruptedException e) {
								// break;
							}
							
							Display.getDefault().syncExec(new Runnable() {
								public void run() {
									DrawTurnoutButtonEven.button_12.setBackgroundColor(DrawStation.buttonInitColor);
									timer_12.cancel();
								}
							});

						}// run
					//}
				}, 0, timestamp);	
				
			}			
		});
		DrawStation.panel.add(button_12);
		
	}
	
}
