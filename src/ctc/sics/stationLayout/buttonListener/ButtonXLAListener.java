package ctc.sics.stationLayout.buttonListener;

import java.util.Timer;

import org.eclipse.draw2d.ActionEvent;
import org.eclipse.draw2d.ActionListener;
import org.eclipse.swt.widgets.Display;

import ctc.sics.stationLayout.*;

public class ButtonXLAListener implements ActionListener {

	public ButtonXLAListener() {
	}

	int second = 1000;
	int timestamp = 10;

	public void actionPerformed(ActionEvent event) {

		final Timer timerXLA = new Timer();

		timerXLA.schedule(new java.util.TimerTask() {

			public void run() {

				Display.getDefault().syncExec(new Runnable() {
					public void run() {
						StationModel.Button_xlaAction();
					}
				});
				
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// break;
				}

				Display.getDefault().syncExec(new Runnable() {
					public void run() {
						// XLA灭
						DrawStation.button_xla
								.setBackgroundColor(DrawStation.buttonInitColor);
						timerXLA.cancel();
					}
				});
			}// run
		}, 0, timestamp);
	}
}