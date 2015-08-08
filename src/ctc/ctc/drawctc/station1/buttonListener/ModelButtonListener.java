package ctc.ctc.drawctc.station1.buttonListener;

import org.eclipse.draw2d.ActionEvent;
import org.eclipse.draw2d.ActionListener;

import ctc.ctc.drawctc.station1.data.*;
import ctc.ctc.drawctc.station1.drawstation.ErrorDialog;
import ctc.ctc.drawctc.station1.layout.StationModel;
import ctc.ctc.drawctc.station1.elements.common.*;


public class ModelButtonListener implements ActionListener {

	BaseParam baseData = BaseParam.getInstance();
	StationModel stationModel = StationModel.getInstance();

	public ModelButtonListener() {
	}

	public void actionPerformed(ActionEvent event) {

		if (ParamFlag.sys_auto == false) { // 非自律模式

			TurnoutButton button = (TurnoutButton) event.getSource();
			String buttonName = button.getName();
			baseData.getMsgLabel().setText(buttonName);

			if (buttonName.equalsIgnoreCase("XDCZDW")) { // X道岔总定位

				System.out.println("X道岔总定位");

				stationModel.turnoutZDW("X");

			} else if (buttonName.equalsIgnoreCase("XDCZFW")) { // X道岔总反位

				System.out.println("X道岔总f位");

				stationModel.turnoutZFW("X");

			} else if (buttonName.equalsIgnoreCase("SDCZDW")) { // S道岔总定位

				System.out.println("S道岔总定位");

				stationModel.turnoutZDW("S");

			} else if (buttonName.equalsIgnoreCase("SDCZFW")) { // S道岔总反位

				System.out.println("S道岔总F位");

				stationModel.turnoutZFW("S");

			} else if (buttonName.equalsIgnoreCase("XPLJL")) { //X排列进路
				
				System.out.println("X排列进路");
				baseData.setB_xpljl(1);

			} else if (buttonName.equalsIgnoreCase("SPLJL")) { //S排列进路

				System.out.println("S排列进路");
				baseData.setB_spljl(1);
				
			} else if (buttonName.equalsIgnoreCase("XZRGJS")) { //X总人工解锁

			} else if (buttonName.equalsIgnoreCase("SZRGJS")) { //S总人工解锁

			} else if (buttonName.equalsIgnoreCase("XZQX")) { //X总取消

				System.out.println("X总取消");
				baseData.setB_xzqx(1);
				
			} else if (buttonName.equalsIgnoreCase("SZQX")) { //S总取消

				System.out.println("S总取消");
				baseData.setB_szqx(1);
				
			} else if (buttonName.equalsIgnoreCase("SZGZ")) { //设置故障
				
				ErrorDialog errorDlg = new ErrorDialog(baseData.getShell().getShell());
				errorDlg.open();	

			} else if (buttonName.equalsIgnoreCase("")) { //

			} else if (buttonName.equalsIgnoreCase("")) { //

			} else if (buttonName.equalsIgnoreCase("")) { //

			} else if (buttonName.equalsIgnoreCase("")) { //

			} else if (buttonName.equalsIgnoreCase("")) { //

			}
		} else {
			System.out.println("目前为自律模式！");
		}

	}

}
