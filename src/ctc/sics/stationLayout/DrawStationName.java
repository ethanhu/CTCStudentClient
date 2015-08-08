package ctc.sics.stationLayout;

import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Point;

import ctc.sics.SicsMain;

public class DrawStationName {

	/**
	 * 绘制站名
	 */
	public static void drawStationName() {

		Label stationName = new Label();
		stationName.setSize(200, 30);
		
		if((SicsMain.db == null)||(SicsMain.db.getStationName() == null)){
			System.out.println("出现错误：SicsMain.db == null");
			return;
		}
		stationName.setText(SicsMain.db.getStationName());
		stationName.setFont(DrawStation.font3);		
		stationName.setLocation(new Point(DrawStation.shellWidth / 2 - 50, 20));
		DrawStation.panel.add(stationName);

	}
	
}
