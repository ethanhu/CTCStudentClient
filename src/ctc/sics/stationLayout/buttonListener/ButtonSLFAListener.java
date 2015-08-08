package ctc.sics.stationLayout.buttonListener;

import java.util.Timer;

import org.eclipse.draw2d.ActionEvent;
import org.eclipse.draw2d.ActionListener;
import org.eclipse.swt.widgets.Display;

import ctc.sics.stationLayout.*;

public class ButtonSLFAListener  implements ActionListener {

	public ButtonSLFAListener() {
	}

	int second = 1000;
	int timestamp = 10;

	public void actionPerformed(ActionEvent event) {

		final Timer timerSLFA = new Timer();

		if (DrawStation.b_s1la == 1) {

			timerSLFA.schedule(new java.util.TimerTask() {

				public void run() {

					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							DrawStation.button_slfa.setBackgroundColor(DrawStation.buttonClickColor);
						}
					});

					try {
						Thread.sleep(second);
					} catch (InterruptedException e) {
						// break;
					}
					
					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							StationModel.Button_slfaAction();
						}
					});

					try {
						Thread.sleep(second);
					} catch (InterruptedException e) {
						// break;
					}

					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							if(DrawStation.b_s1la == 1){
								ButtonSemphoreModel.semphoreColorModel("S1","green");
							}
							
							// SLfA灭
							DrawStation.button_slfa.setBackgroundColor(DrawStation.buttonInitColor);
							timerSLFA.cancel();
						}
					});
				}// run
			}, 0, timestamp);

		} else
		if (DrawStation.b_s2la == 1) {

			timerSLFA.schedule(new java.util.TimerTask() {

				public void run() {

					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							DrawStation.button_slfa.setBackgroundColor(DrawStation.buttonClickColor);
						}
					});

					try {
						Thread.sleep(second);
					} catch (InterruptedException e) {
						// break;
					}
					
					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							StationModel.Button_slfaAction();
						}
					});

					try {
						Thread.sleep(second);
					} catch (InterruptedException e) {
						// break;
					}

					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							if(DrawStation.b_s2la == 1){
								ButtonSemphoreModel.semphoreColorModel("S2","green");
							}
							// SILA灭
							DrawStation.button_slfa.setBackgroundColor(DrawStation.buttonInitColor);
							timerSLFA.cancel();
						}
					});
				}// run
			}, 0, timestamp);

		} else
		if (DrawStation.b_s3la == 1) {

			timerSLFA.schedule(new java.util.TimerTask() {

				public void run() {

					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							DrawStation.button_slfa.setBackgroundColor(DrawStation.buttonClickColor);
						}
					});

					try {
						Thread.sleep(second);
					} catch (InterruptedException e) {
						// break;
					}
					
					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							StationModel.Button_slfaAction();
						}
					});

					try {
						Thread.sleep(second);
					} catch (InterruptedException e) {
						// break;
					}

					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							if(DrawStation.b_s3la == 1){
								ButtonSemphoreModel.semphoreColorModel("S3","green");
							}
							// SILA灭
							DrawStation.button_slfa.setBackgroundColor(DrawStation.buttonInitColor);
							timerSLFA.cancel();
						}
					});
				}// run
			}, 0, timestamp);

		} else
		if (DrawStation.b_s4la == 1) {

			timerSLFA.schedule(new java.util.TimerTask() {

				public void run() {

					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							DrawStation.button_slfa.setBackgroundColor(DrawStation.buttonClickColor);
						}
					});

					try {
						Thread.sleep(second);
					} catch (InterruptedException e) {
						// break;
					}
					
					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							StationModel.Button_slfaAction();
						}
					});

					try {
						Thread.sleep(second);
					} catch (InterruptedException e) {
						// break;
					}

					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							if(DrawStation.b_s4la == 1){
								ButtonSemphoreModel.semphoreColorModel("S4","green");
							}
							// SLfA灭
							DrawStation.button_slfa.setBackgroundColor(DrawStation.buttonInitColor);
							timerSLFA.cancel();
						}
					});
				}// run
			}, 0, timestamp);

		} else
		if (DrawStation.b_sta == 1) {

			timerSLFA.schedule(new java.util.TimerTask() {

				public void run() {

					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							DrawStation.button_slfa.setBackgroundColor(DrawStation.buttonClickColor);
						}
					});

					try {
						Thread.sleep(second);
					} catch (InterruptedException e) {
						// break;
					}
					
					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							StationModel.Button_slfaAction();
						}
					});

					try {
						Thread.sleep(second);
					} catch (InterruptedException e) {
						// break;
					}

					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							if(DrawStation.b_sta == 1){
								ButtonSemphoreModel.semphoreColorModel("SLX","green");
								ButtonSemphoreModel.semphoreColorModel("S2","green");
							}
							
							// SLFA灭
							DrawStation.button_xlfa.setBackgroundColor(DrawStation.buttonInitColor);
							timerSLFA.cancel();
						}
					});
				}// run
			}, 0, timestamp);
			
		}else {
			
			timerSLFA.schedule(new java.util.TimerTask() {

				public void run() {

					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							DrawStation.button_slfa.setBackgroundColor(DrawStation.buttonClickColor);
						}
					});
					
					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							StationModel.Button_slfaAction();
						}
					});

					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// break;
					}

					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							DrawStation.button_slfa.setBackgroundColor(DrawStation.buttonInitColor);
							timerSLFA.cancel();
						}
					});
				}// run
			}, 0, timestamp);
			
		}		
	}

}
