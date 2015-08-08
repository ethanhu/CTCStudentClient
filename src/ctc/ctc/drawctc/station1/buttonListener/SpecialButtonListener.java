package ctc.ctc.drawctc.station1.buttonListener;

import org.eclipse.draw2d.ActionEvent;
import org.eclipse.draw2d.ActionListener;

import ctc.ctc.drawctc.station1.data.*;
import ctc.ctc.drawctc.station1.drawstation.TaskDialog;
import ctc.ctc.drawctc.station1.layout.StationModel;
import ctc.ctc.drawctc.station1.elements.common.*;


/**
 * 特殊按钮事件，不受自律与非自律的影响
 * @author 胡恩召
 *
 */
public class SpecialButtonListener  implements ActionListener {

	BaseParam baseData = BaseParam.getInstance();
	StationModel stationModel = StationModel.getInstance();
	TaskDialog taskDialog = TaskDialog.getInstance(baseData.getMainShell());
	
	public SpecialButtonListener() {
	}
	
	public void actionPerformed(ActionEvent event) {

		TurnoutButton button =  (TurnoutButton)event.getSource();
		String buttonName = button.getName();
		
		System.out.println("按钮:" + buttonName);
				
		if (buttonName.equalsIgnoreCase("SQZK")) { // 申请站控
			//SICSToCTC.sendStationControlMessageASYN("SQZK"); //发送站控请求
		} else if (buttonName.equalsIgnoreCase("FCZK")) { //非常站控
			//SICSToCTC.sendStationControlMessageASYN("FCZK"); //发送非常站控请求

		}else if (buttonName.equalsIgnoreCase("XSWZ")) { // 显示文字

			if (baseData.getNameLabelList() != null && baseData.getNameLabelList().size() != 0) {
				int len = baseData.getNameLabelList().size();
				for (int i = 0; i < len; i++) {

					baseData.getNameLabelList().get(i).setVisible(true);
				}
			} else {
				return;
			}

		} else if (buttonName.equalsIgnoreCase("QCPM")) { // 清除屏幕

			if (baseData.getNameLabelList() != null && baseData.getNameLabelList().size() != 0) {
				int len = baseData.getNameLabelList().size();
				for (int i = 0; i < len; i++) {
					baseData.getNameLabelList().get(i).setVisible(false);
				}
			} else {
				return;
			}

		} else if (buttonName.equalsIgnoreCase("XSRW")) { //非常站控
			taskDialog.open();
			System.out.println("--XSRW--");
		}
		
	}

	
}
