package ctc.sics.stationLayout.buttonListener;

import java.util.Timer;

import org.eclipse.draw2d.ActionEvent;
import org.eclipse.draw2d.ActionListener;
import org.eclipse.swt.widgets.Display;

import ctc.sics.stationLayout.*;

public class ButtonSTAListener implements ActionListener {

	public ButtonSTAListener() {
	}

	int second = 1000;
	int timestamp = 10;

	public void actionPerformed(ActionEvent event) {

		final Timer timerSTA = new Timer();

		timerSTA.schedule(new java.util.TimerTask() {

			public void run() {

				Display.getDefault().syncExec(new Runnable() {
					public void run() {
						StationModel.Button_staAction();
					}
				});
				
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// break;
				}

				Display.getDefault().syncExec(new Runnable() {
					public void run() {
						// XTA灭
						DrawStation.button_sta
								.setBackgroundColor(DrawStation.buttonInitColor);
						timerSTA.cancel();
					}
				});
			}// run
		}, 0, timestamp);
	}
}