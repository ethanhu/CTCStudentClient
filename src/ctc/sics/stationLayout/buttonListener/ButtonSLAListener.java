package ctc.sics.stationLayout.buttonListener;

import java.util.Timer;

import org.eclipse.draw2d.ActionEvent;
import org.eclipse.draw2d.ActionListener;
import org.eclipse.swt.widgets.Display;

import ctc.sics.stationLayout.*;

public class ButtonSLAListener implements ActionListener {

	public ButtonSLAListener() {
	}

	int second = 1000;
	int timestamp = 10;

	public void actionPerformed(ActionEvent event) {

		final Timer timerSLA = new Timer();

		timerSLA.schedule(new java.util.TimerTask() {

			public void run() {
				
				Display.getDefault().syncExec(new Runnable() {
					public void run() {
						StationModel.Button_slaAction();
					}
				});
				
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// break;
				}

				Display.getDefault().syncExec(new Runnable() {
					public void run() {
						// SLA灭
						DrawStation.button_sla
								.setBackgroundColor(DrawStation.buttonInitColor);
						timerSLA.cancel();
					}
				});
			}// run
		}, 0, timestamp);
	}
}