package ctc.sics.station1.buttonListener;

import org.eclipse.draw2d.ActionEvent;
import org.eclipse.draw2d.ActionListener;

import ctc.sics.station1.data.*;
import ctc.sics.station1.layout.StationModel;
import ctc.sics.station1.elements.common.*;


public class TurnoutButtonListener implements ActionListener {

	BaseParam baseDataForStation = BaseParam.getInstance();
	StationModel stationModel = StationModel.getInstance();

	public TurnoutButtonListener() {
	}

	public void actionPerformed(ActionEvent event) {
		
		if (ParamFlag.sys_auto == false) { // 非自律模式
		
			TurnoutButton button = (TurnoutButton) event.getSource();
			String buttonName = button.getName();
			baseDataForStation.getMsgLabel().setText(buttonName);

			stationModel.processTurnout(buttonName);
			
		} else {
			System.out.println("目前为自律模式！");
		}
	}
}
