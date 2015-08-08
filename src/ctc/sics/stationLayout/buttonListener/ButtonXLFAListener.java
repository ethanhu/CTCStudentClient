package ctc.sics.stationLayout.buttonListener;

import java.util.Timer;

import org.eclipse.draw2d.ActionEvent;
import org.eclipse.draw2d.ActionListener;
import org.eclipse.swt.widgets.Display;

import ctc.sics.stationLayout.*;

public class ButtonXLFAListener  implements ActionListener {

	public ButtonXLFAListener() {
	}

	int second = 1000;
	int timestamp = 10;

	public void actionPerformed(ActionEvent event) {

		final Timer timerXLFA = new Timer();

		if (DrawStation.b_x1la == 1) {

			timerXLFA.schedule(new java.util.TimerTask() {

				public void run() {

					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							DrawStation.button_xlfa
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
							StationModel.Button_xlfaAction();
						}
					});

					try {
						Thread.sleep(second);
					} catch (InterruptedException e) {
						// break;
					}

					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							if(DrawStation.b_x1la == 1){
								ButtonSemphoreModel.semphoreColorModel("X1","green");
							}
							// SILA灭
							DrawStation.button_xlfa.setBackgroundColor(DrawStation.buttonInitColor);
							timerXLFA.cancel();
						}
					});
				}// run
			}, 0, timestamp);

		} else
		if (DrawStation.b_x2la == 1) {

			timerXLFA.schedule(new java.util.TimerTask() {

				public void run() {

					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							DrawStation.button_xlfa
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
							StationModel.Button_xlfaAction();
						}
					});

					try {
						Thread.sleep(second);
					} catch (InterruptedException e) {
						// break;
					}

					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							if(DrawStation.b_x2la == 1){
								ButtonSemphoreModel.semphoreColorModel("X2","green");
							}
							// SILA灭
							DrawStation.button_xlfa.setBackgroundColor(DrawStation.buttonInitColor);
							timerXLFA.cancel();
						}
					});
				}// run
			}, 0, timestamp);

		} else
		if (DrawStation.b_x3la == 1) {

			timerXLFA.schedule(new java.util.TimerTask() {

				public void run() {

					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							DrawStation.button_xlfa
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
							StationModel.Button_xlfaAction();
						}
					});

					try {
						Thread.sleep(second);
					} catch (InterruptedException e) {
						// break;
					}

					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							if(DrawStation.b_x3la == 1){
								ButtonSemphoreModel.semphoreColorModel("X3","green");
							}
							// SILA灭
							DrawStation.button_xlfa.setBackgroundColor(DrawStation.buttonInitColor);
							timerXLFA.cancel();
						}
					});
				}// run
			}, 0, timestamp);

		} else
		if (DrawStation.b_x4la == 1) {

			timerXLFA.schedule(new java.util.TimerTask() {

				public void run() {

					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							DrawStation.button_xlfa
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
							StationModel.Button_xlfaAction();
						}
					});

					try {
						Thread.sleep(second);
					} catch (InterruptedException e) {
						// break;
					}

					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							if(DrawStation.b_x4la == 1){
								ButtonSemphoreModel.semphoreColorModel("X4","green");
							}
							// SILA灭
							DrawStation.button_xlfa.setBackgroundColor(DrawStation.buttonInitColor);
							timerXLFA.cancel();
						}
					});
				}// run
			}, 0, timestamp);

		} else
		if (DrawStation.b_xta == 1) {

			timerXLFA.schedule(new java.util.TimerTask() {

				public void run() {

					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							DrawStation.button_xlfa
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
							StationModel.Button_xlfaAction();
						}
					});

					try {
						Thread.sleep(second);
					} catch (InterruptedException e) {
						// break;
					}

					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							if(DrawStation.b_xta == 1){
								ButtonSemphoreModel.semphoreColorModel("XLX","green");
								ButtonSemphoreModel.semphoreColorModel("X1","green");
							}							
							// SILA灭
							DrawStation.button_xlfa.setBackgroundColor(DrawStation.buttonInitColor);
							timerXLFA.cancel();
						}
					});
				}// run
			}, 0, timestamp);
			
		}else {
			
			timerXLFA.schedule(new java.util.TimerTask() {

				public void run() {

					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							DrawStation.button_xlfa
									.setBackgroundColor(DrawStation.buttonClickColor);
						}
					});

					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							StationModel.Button_xlfaAction();
						}
					});

					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// break;
					}

					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							DrawStation.button_xlfa.setBackgroundColor(DrawStation.buttonInitColor);
							timerXLFA.cancel();
						}
					});
				}// run
			}, 0, timestamp);
		}		
	}

}
