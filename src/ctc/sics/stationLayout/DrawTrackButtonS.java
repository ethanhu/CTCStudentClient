package ctc.sics.stationLayout;

import java.util.Timer;

import org.eclipse.draw2d.ActionEvent;
import org.eclipse.draw2d.ActionListener;
import org.eclipse.draw2d.Button;
import org.eclipse.swt.widgets.Display;

import ctc.sics.figure.*;

public class DrawTrackButtonS {

	//按钮
	public static Button button_xydzsb;
	public static int b_xydzsb = 0;
	public static Button button_xzrgjs;
	public static int b_xzrgjs = 0;
	public static Button button_xzqx;
	public static int b_xzqx = 0;
	public static Button button_xdc;
	public static int b_xdc = 0;
	
	public static Button button_rgjs;
	public static int b_rgjs = 0;
	/**
	 * 绘制股道操作按钮(上行)
	 */
	public static void drawTrackButtonS() {

		int initX = 100;
		int initY = DrawStation.shellHeight - 100;
		int xlength = 100;
		int ylength = 15;

		/*
		// X引导总锁闭
		DrawStation.stationLabel = new StationLabel("X引导总锁闭", DrawStation.labelWidth + 50,
				DrawStation.labelHeight + 5, initX - 25, initY, DrawStation.panel);
		button_xydzsb = new StationButton(DrawStation.buttonLength, initX, initY + ylength,
				DrawStation.buttonInitColor).getButton();
		button_xydzsb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.out.println("\n ---X引导总锁闭 button_xydzsb is clicked --");
			}
		});
		DrawStation.panel.add(button_xydzsb);
		*/
		
		// X总人工解锁
		DrawStation.stationLabel = new StationLabel("X总人工解锁", DrawStation.labelWidth + 50,
				DrawStation.labelHeight + 5, initX - 25 + xlength, initY, DrawStation.panel);
		button_xzrgjs = new StationButton(DrawStation.buttonLength, initX + xlength, initY
				+ ylength, DrawStation.buttonInitColor).getButton();
		button_xzrgjs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				b_xzrgjs = 1;
				
				int timestamp = 10;		
				final Timer timerxzrgjs = new Timer();					
				timerxzrgjs.schedule(new java.util.TimerTask() {					
						public void run() {
							
							Display.getDefault().syncExec(new Runnable() {
								public void run() {
									DrawTrackButtonS.button_xzrgjs.setBackgroundColor(DrawStation.buttonClickColor);
								}
							});

							try {
								Thread.sleep(5000);
							} catch (InterruptedException e) {
								// break;
							}
							
							Display.getDefault().syncExec(new Runnable() {
								public void run() {
									DrawTrackButtonS.button_xzrgjs.setBackgroundColor(DrawStation.buttonInitColor);
									timerxzrgjs.cancel();
								}
							});

						}// run
					//}
				}, 0, timestamp);	
				
			}
		});
		DrawStation.panel.add(button_xzrgjs);
		
		// X总取消
		DrawStation.stationLabel = new StationLabel("X总取消", DrawStation.labelWidth + 50,
				DrawStation.labelHeight + 5, initX - 25 + 2 * xlength, initY, DrawStation.panel);
		button_xzqx = new StationButton(DrawStation.buttonLength, initX + 2 * xlength,
				initY + ylength, DrawStation.buttonInitColor).getButton();
		button_xzqx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				b_xzqx = 1;				
				
				int timestamp = 10;		
				final Timer timerxzqx = new Timer();					
				timerxzqx.schedule(new java.util.TimerTask() {					
						public void run() {
							
							Display.getDefault().syncExec(new Runnable() {
								public void run() {
									DrawTrackButtonS.button_xzqx.setBackgroundColor(DrawStation.buttonClickColor);
								}
							});

							try {
								Thread.sleep(5000);
							} catch (InterruptedException e) {
								// break;
							}
							
							Display.getDefault().syncExec(new Runnable() {
								public void run() {
									DrawTrackButtonS.button_xzqx.setBackgroundColor(DrawStation.buttonInitColor);
									timerxzqx.cancel();
								}
							});

						}// run
					//}
				}, 0, timestamp);	
				
			}
		});
		DrawStation.panel.add(button_xzqx);
		
		// X道岔单操
		DrawStation.stationLabel = new StationLabel("X道岔单操", DrawStation.labelWidth + 50,
				DrawStation.labelHeight + 5, initX - 25 + 3 * xlength, initY, DrawStation.panel);
		button_xdc = new StationButton(DrawStation.buttonLength, initX + 3 * xlength,
				initY + ylength, DrawStation.buttonInitColor).getButton();
		button_xdc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
								
				b_xdc = 1;					
				
				int timestamp = 10;		
				final Timer timerxdc = new Timer();					
				timerxdc.schedule(new java.util.TimerTask() {					
						public void run() {
							
							Display.getDefault().syncExec(new Runnable() {
								public void run() {
									DrawTrackButtonS.button_xdc.setBackgroundColor(DrawStation.buttonClickColor);
								}
							});

							try {
								Thread.sleep(5000);
							} catch (InterruptedException e) {
								// break;
							}
							
							Display.getDefault().syncExec(new Runnable() {
								public void run() {
									DrawTrackButtonS.button_xdc.setBackgroundColor(DrawStation.buttonInitColor);
									timerxdc.cancel();
								}
							});

						}// run
					//}
				}, 0, timestamp);	
				
			}					
		});	
	
		DrawStation.panel.add(button_xdc);	
/*		
		// 人工解锁
		DrawStation.stationLabel = new StationLabel("人工解锁", DrawStation.labelWidth + 50,
				DrawStation.labelHeight + 5, initX - 25 + 4 * xlength, initY, DrawStation.panel);
		button_rgjs = new StationButton(DrawStation.buttonLength, initX + 4 * xlength,
				initY + ylength, DrawStation.buttonInitColor).getButton();
		button_rgjs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.out.println("\n 人工解锁 button_rgjs is clicked --");
				b_rgjs = 1;
				button_rgjs.setBackgroundColor(DrawStation.buttonClickColor);
				//UnLock.unLockTrain(DrawStation.r_preRoad, DrawStation.r_preRoadType, "", "");
			}
		});
		DrawStation.panel.add(button_rgjs);	
		
		// 测试
		/*DrawStation.stationLabel = new StationLabel("测试", DrawStation.labelWidth + 50,
				DrawStation.labelHeight + 5, initX - 25 + 6 * xlength, initY, DrawStation.panel);*/
/*
		final Label lab = new Label();
		lab.setText("变色测试");
		lab.setSize(60,20);
		lab.setLocation(new Point(initX - 25 + 6 * xlength, initY));
		DrawStation.panel.add(lab);
						
		final Button button_cs = new StationButton(DrawStation.buttonLength, initX + 6 * xlength,
				initY + ylength - 5, DrawStation.buttonInitColor).getButton();
		
		button_cs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				lab.setForegroundColor(ColorConstants.red);
				button_cs.setBackgroundColor(DrawStation.buttonClickColor);
				DrawTrainList.changeTrainLabel("T11");
				button_cs.setBackgroundColor(DrawStation.buttonInitColor);
			}
		});
		DrawStation.panel.add(button_cs);	
*/	

	}

	
}
