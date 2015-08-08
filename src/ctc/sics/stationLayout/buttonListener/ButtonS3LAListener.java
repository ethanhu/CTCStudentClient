package ctc.sics.stationLayout.buttonListener;

import java.util.Timer;

import org.eclipse.draw2d.ActionEvent;
import org.eclipse.draw2d.ActionListener;
import org.eclipse.swt.widgets.Display;

import ctc.sics.stationLayout.*;

public class ButtonS3LAListener implements ActionListener {

	public ButtonS3LAListener() {
	}

	int second = 1000;
	int timestamp = 10;

	public void actionPerformed(ActionEvent event) {

		final Timer timerS3LA = new Timer();
		
		if (DrawStation.b_xla == 1) {
			
			timerS3LA.schedule(new java.util.TimerTask() {

				public void run() {

					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							DrawStation.button_s3la.setBackgroundColor(DrawStation.buttonClickColor);
						}
					});

					try {
						Thread.sleep(second);
					} catch (InterruptedException e) {
						// break;
					}
					
					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							StationModel.Button_s3laAction();
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
							// SILA灭
							DrawStation.button_s3la
									.setBackgroundColor(DrawStation.buttonInitColor);
							timerS3LA.cancel();
						}
					});

				}// run
			}, 0, timestamp);
			
		} else {

			timerS3LA.schedule(new java.util.TimerTask() {

				public void run() {
					
					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							DrawStation.button_s3la.setBackgroundColor(DrawStation.buttonClickColor);
						}
					});
					
					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							StationModel.Button_s3laAction();
						}
					});
					
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// break;
					}

					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							// S3LA灭
							DrawStation.button_s3la.setBackgroundColor(DrawStation.buttonInitColor);
							timerS3LA.cancel();
						}
					});

				}// run
			}, 0, timestamp);
			
		}
	}

}
