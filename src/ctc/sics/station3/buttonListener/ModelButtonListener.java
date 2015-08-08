package ctc.sics.station3.buttonListener;

import org.eclipse.draw2d.ActionEvent;
import org.eclipse.draw2d.ActionListener;
import org.eclipse.swt.widgets.Text;

import ctc.sics.station.elements.common.*;

import ctc.sics.station3.data.*;
import ctc.sics.station3.drawstation.*;
import ctc.sics.station3.layout.StationModel;

public class ModelButtonListener implements ActionListener {

	BaseParam baseData = BaseParam.getInstance();
	StationModel stationModel = StationModel.getInstance();
	public Text msgLabel = baseData.getMsgLabel();

	public ModelButtonListener() {
	}

	public void actionPerformed(ActionEvent event) {

		if (ParamFlag.sys_auto == false) { // 非自律模式

			TurnoutButton button = (TurnoutButton) event.getSource();
			String buttonName = button.getName();

			if (buttonName.equalsIgnoreCase("XDCZDW")) { // X道岔总定位
				stationModel.turnoutZDW("X");
			} else if (buttonName.equalsIgnoreCase("XDCZFW")) { // X道岔总反位
				stationModel.turnoutZFW("X");
			} else if (buttonName.equalsIgnoreCase("SDCZDW")) { // S道岔总定位
				stationModel.turnoutZDW("S");
			} else if (buttonName.equalsIgnoreCase("SDCZFW")) { // S道岔总反位
				stationModel.turnoutZFW("S");

			} else if (buttonName.equalsIgnoreCase("XYZS")) { // X引导总锁闭
				CheckDialog checkDlg = new CheckDialog(baseData.getShell().getShell());
				checkDlg.open();
				if (checkDlg.getResult() == true) {
					if (baseData.getB_xyzs() == 0) {
						baseData.setB_xyzs(1);
						stationModel.turnoutYZS("X", ParamFlag.turnout_red);
					} else {
						baseData.setB_xyzs(0);
						stationModel.turnoutYZS("X", ParamFlag.turnout_blue);
					}
				}
			} else if (buttonName.equalsIgnoreCase("SYZS")) { // S引导总锁闭
				CheckDialog checkDlg = new CheckDialog(baseData.getShell().getShell());
				checkDlg.open();
				if (checkDlg.getResult() == true) {
					if (baseData.getB_syzs() == 0) {
						baseData.setB_syzs(1);
						stationModel.turnoutYZS("S", ParamFlag.turnout_red);
					} else {
						baseData.setB_syzs(0);
						stationModel.turnoutYZS("S", ParamFlag.turnout_blue);
					}
				}

			} else if (buttonName.equalsIgnoreCase("XZRGJS")) { // X总人工解锁

				CheckDialog checkDlg = new CheckDialog(baseData.getShell().getShell());
				checkDlg.open();
				if (checkDlg.getResult() == true) {
					baseData.setB_xzqx(1);
				}
			} else if (buttonName.equalsIgnoreCase("SZRGJS")) { // S总人工解锁
				CheckDialog checkDlg = new CheckDialog(baseData.getShell().getShell());
				checkDlg.open();
				if (checkDlg.getResult() == true) {
					baseData.setB_xzqx(1);
				}

			} else if (buttonName.equalsIgnoreCase("XZQX")) { // X总取消
				baseData.setB_xzqx(1);
			} else if (buttonName.equalsIgnoreCase("SZQX")) { // S总取消
				baseData.setB_szqx(1);

			} else if (buttonName.equalsIgnoreCase("QGJ")) { // S总取消
				CheckDialog checkDlg = new CheckDialog(baseData.getShell().getShell());
				checkDlg.open();
				if (checkDlg.getResult() == true) {
					baseData.setB_xzqx(1);
				}

			} else if (buttonName.equalsIgnoreCase("XF")) { // X反向操作
				CheckDialog checkDlg = new CheckDialog(baseData.getShell().getShell());
				checkDlg.open();
				if (checkDlg.getResult() == true) {
					if (baseData.getB_syzs() == 0) {
						baseData.setB_syzs(1);
						stationModel.turnoutYZS("S", ParamFlag.turnout_red);
					} else {
						baseData.setB_syzs(0);
						stationModel.turnoutYZS("S", ParamFlag.turnout_blue);
					}
				}
			} else if (buttonName.equalsIgnoreCase("SF")) { // S反向操作
				CheckDialog checkDlg = new CheckDialog(baseData.getShell().getShell());
				checkDlg.open();
				if (checkDlg.getResult() == true) {
					if (baseData.getB_syzs() == 0) {
						baseData.setB_syzs(1);
						stationModel.turnoutYZS("S", ParamFlag.turnout_red);
					} else {
						baseData.setB_syzs(0);
						stationModel.turnoutYZS("S", ParamFlag.turnout_blue);
					}
				} else if (buttonName.equalsIgnoreCase("SZGZ")) { // 设置故障

					ErrorDialog errorDlg = new ErrorDialog(baseData.getShell().getShell());
					errorDlg.open();
				}
			}

		} else {
			msgLabel.setText("目前为自律模式！");
		}

	}

}
