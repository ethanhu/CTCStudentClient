package ctc.sics.station2.buttonListener;

import org.eclipse.draw2d.ActionEvent;
import org.eclipse.draw2d.ActionListener;
import org.eclipse.draw2d.Label;

import ctc.sics.SicsMain;
import ctc.sics.sics2ctc.SICSToCTC;

import ctc.sics.station2.elements.common.*;
import ctc.sics.station2.data.BaseParam;
import ctc.sics.station2.drawstation.TaskDialog;
import ctc.sics.station2.layout.StationModel;


/**
 * 特殊按钮事件，不受自律与非自律的影响
 * @author 胡恩召
 *
 */
public class SpecialButtonListener  implements ActionListener {

	BaseParam baseData = BaseParam.getInstance();
	StationModel stationModel = StationModel.getInstance();
	TaskDialog taskDialog = TaskDialog.getInstance(baseData.getMainShell());
	
	public static boolean gdFlag = false; //股道显示标记
	public static boolean dcFlag = false; //道岔显示标记
	public static boolean anFlag = false; //按钮显示标记
	public static boolean xhFlag = false; //信号机显示标记
	
	
	public SpecialButtonListener() {
	}
	
	public void actionPerformed(ActionEvent event) {

		TurnoutButton button =  (TurnoutButton)event.getSource();
		String buttonName = button.getName();
		
		System.out.println("按钮:" + buttonName);
				
		if (buttonName.equalsIgnoreCase("SQZK")) { // 申请站控

			if (SicsMain.getDb() != null){
				SICSToCTC.sendStationControlMessageASYN("SQZK"); //发送站控请求
			}			

		} else if (buttonName.equalsIgnoreCase("FCZK")) { //非常站控

			if (SicsMain.getDb() != null){
				SICSToCTC.sendStationControlMessageASYN("FCZK"); //发送非常站控请求
			}	

		}else if (buttonName.equalsIgnoreCase("XSGD")) { // 显示股道

			gdFlag = !gdFlag;
			Label nameLabel;
			int len = baseData.getTracklineNameList().size();
			for(int i = 0; i < len; i++){
				nameLabel = baseData.getTracklineNameList().get(i);
				nameLabel.setVisible(gdFlag);
			}
			
		} else if (buttonName.equalsIgnoreCase("XSDC")) { // 显示道岔

			dcFlag = !dcFlag;
			Label nameLabel;
			int len = baseData.getTurnoutNameList().size();
			for(int i = 0; i < len; i++){
				nameLabel = baseData.getTurnoutNameList().get(i);
				nameLabel.setVisible(dcFlag);
			}

		}  else if (buttonName.equalsIgnoreCase("XSAN")) { // 显示按钮

			anFlag = !anFlag;
			Label nameLabel;
			int len = baseData.getButtonNameList().size();
			for(int i = 0; i < len; i++){
				nameLabel = baseData.getButtonNameList().get(i);
				nameLabel.setVisible(anFlag);
			}

		} else if (buttonName.equalsIgnoreCase("XSXH")) { // 显示信号机

			xhFlag = !xhFlag;
			Label nameLabel;
			int len = baseData.getSemaphoreNameList().size();
			for(int i = 0; i < len; i++){
				nameLabel = baseData.getSemaphoreNameList().get(i);
				nameLabel.setVisible(xhFlag);
			}

		}  else if (buttonName.equalsIgnoreCase("XSRW")) { //非常站控
			taskDialog.open();
			System.out.println("--XSRW--");
		}
		
	}

	
}
