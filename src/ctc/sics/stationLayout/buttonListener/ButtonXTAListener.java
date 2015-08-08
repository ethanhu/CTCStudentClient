package ctc.sics.stationLayout.buttonListener;

import java.util.Timer;

import org.eclipse.draw2d.ActionEvent;
import org.eclipse.draw2d.ActionListener;
import org.eclipse.swt.widgets.Display;

import ctc.sics.stationLayout.*;

public class ButtonXTAListener implements ActionListener {

	public ButtonXTAListener() {
	}

	int second = 1000;
	int timestamp = 10;

	public void actionPerformed(ActionEvent event) {

		final Timer timerXTA = new Timer();

		timerXTA.schedule(new java.util.TimerTask() {

			public void run() {

				Display.getDefault().syncExec(new Runnable() {
					public void run() {
						StationModel.Button_xtaAction();
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
						DrawStation.button_xta
								.setBackgroundColor(DrawStation.buttonInitColor);
						timerXTA.cancel();
					}
				});
			}// run
		}, 0, timestamp);
	}
}