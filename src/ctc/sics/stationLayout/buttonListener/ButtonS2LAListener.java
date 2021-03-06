package ctc.sics.stationLayout.buttonListener;

import java.util.Timer;

import org.eclipse.draw2d.ActionEvent;
import org.eclipse.draw2d.ActionListener;
import org.eclipse.swt.widgets.Display;

import ctc.sics.stationLayout.*;

public class ButtonS2LAListener implements ActionListener {

	public ButtonS2LAListener() {
	}

	int second = 1000;
	int timestamp = 10;

	public void actionPerformed(ActionEvent event) {

		final Timer timerS2LA = new Timer();

		if (DrawStation.b_xla == 1) {
			
			timerS2LA.schedule(new java.util.TimerTask() {

				public void run() {
					
					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							DrawStation.button_s2la.setBackgroundColor(DrawStation.buttonClickColor);
						}
					});

					try {
						Thread.sleep(second);
					} catch (InterruptedException e) {
						// break;
					}
					
					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							StationModel.Button_s2laAction();
						}
					});

					try {
						Thread.sleep(second);
					} catch (InterruptedException e) {
						// break;
					}

					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							if(DrawStation.b_xla == 1){
								ButtonSemphoreModel.semphoreColorModel("XLX", "green");
							}
							// S2LA灭
							DrawStation.button_s2la
									.setBackgroundColor(DrawStation.buttonInitColor);
							timerS2LA.cancel();
						}
					});

				}// run
			}, 0, timestamp);
			
		} else {

			timerS2LA.schedule(new java.util.TimerTask() {

				public void run() {					

					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							DrawStation.button_s2la.setBackgroundColor(DrawStation.buttonClickColor);
						}
					});
					
					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							StationModel.Button_s2laAction();
						}
					});

					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// break;
					}

					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							// S2LA灭
							DrawStation.button_s2la.setBackgroundColor(DrawStation.buttonInitColor);
							timerS2LA.cancel();
						}
					});

				}// run

			}, 0, timestamp);
			
		}
	}

}
