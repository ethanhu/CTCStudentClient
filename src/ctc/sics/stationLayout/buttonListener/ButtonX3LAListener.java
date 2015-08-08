package ctc.sics.stationLayout.buttonListener;

import java.util.Timer;

import org.eclipse.draw2d.ActionEvent;
import org.eclipse.draw2d.ActionListener;
import org.eclipse.swt.widgets.Display;

import ctc.sics.stationLayout.*;

public class ButtonX3LAListener implements ActionListener {

	public ButtonX3LAListener() {
	}

	int second = 1000;
	int timestamp = 10;

	public void actionPerformed(ActionEvent event) {

		final Timer timerX3LA = new Timer();
		if (DrawStation.b_sla == 1) {

			timerX3LA.schedule(new java.util.TimerTask() {

				public void run() {
					
					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							DrawStation.button_x3la
									.setBackgroundColor(DrawStation.buttonClickColor);
						}
					});

					try {
						Thread.sleep(second);
					} catch (InterruptedException e) {
						// break;
					}

					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							StationModel.Button_x3laAction();
						}
					});

					try {
						Thread.sleep(second);
					} catch (InterruptedException e) {
						// break;
					}

					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							if(DrawStation.b_sla == 1){
								ButtonSemphoreModel.semphoreColorModel("SLX", "green");
							}
							// S3LA灭
							DrawStation.button_x3la
									.setBackgroundColor(DrawStation.buttonInitColor);
							timerX3LA.cancel();
						}
					});
				}// run
			}, 0, timestamp);

		} else {

			timerX3LA.schedule(new java.util.TimerTask() {

				public void run() {

					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							DrawStation.button_x3la
									.setBackgroundColor(DrawStation.buttonClickColor);
						}
					});

					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							StationModel.Button_x3laAction();
						}
					});

					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// break;
					}

					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							// X3LA灭
							DrawStation.button_x3la
									.setBackgroundColor(DrawStation.buttonInitColor);
							timerX3LA.cancel();
						}
					});
				}// run
			}, 0, timestamp);
		}
	}

}
