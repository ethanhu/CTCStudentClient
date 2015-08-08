package ctc.ctc.drawctc.station1.buttonListener;

import org.eclipse.draw2d.ActionEvent;
import org.eclipse.draw2d.ActionListener;

import ctc.ctc.drawctc.station1.data.*;
import ctc.ctc.drawctc.station1.layout.StationModel;
import ctc.ctc.drawctc.station1.elements.common.*;


public class ButtonListener implements ActionListener {

	BaseParam baseData = BaseParam.getInstance();
	StationModel stationModel = StationModel.getInstance();

	public static int num = 0;
	public static String startName;
	public static String endName;

	public ButtonListener() {
	}

	int second = 1000;
	int timestamp = 10;

	public void actionPerformed(ActionEvent event) {

		if (ParamFlag.sys_auto == false) { //非自律模式

			StationButton button = (StationButton) event.getSource();
			String buttonName = button.getName();

			if (num == 0) {
				startName = buttonName;
				num++;
			} else if (num == 1) {
				endName = buttonName;
				num++;
				baseData.getMsgLabel().setText(startName + "+" + endName);
				
				int color = ParamFlag.road_green;
				
				if(baseData.getB_xzqx() == 1){ //X总取消
					System.out.println("X总取消");
					color = ParamFlag.road_blue;
					baseData.setB_xzqx(0);
					
				}else if(baseData.getB_szqx() == 1){ //S总取消
					System.out.println("S总取消");
					color = ParamFlag.road_blue;
					baseData.setB_szqx(0);
					
				}else if(baseData.getB_xzrgjs() == 1){ //X总人工解锁
					System.out.println("X总人工解锁");
					color = ParamFlag.road_blue;
					baseData.setB_xzrgjs(0);
					
				}else if(baseData.getB_szrgjs() == 1){ //S总人工解锁
					System.out.println("S总人工解锁");
					color = ParamFlag.road_blue;
					baseData.setB_szrgjs(0);
					
				}
				System.out.println("排列进路");
				stationModel.processRoad(startName, endName, color);
				
			} else if (num == 2) {
				startName = buttonName;
				num = 1;
			}

		}else{
			System.out.println("目前为自律模式！");
		}

	}

	public static int getNum() {
		return num;
	}

	public static void setNum(int num) {
		ButtonListener.num = num;
	}

}