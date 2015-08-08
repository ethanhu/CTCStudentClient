package ctc.sics.station1.elements.common;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;

import ctc.sics.station1.data.BaseParam;

/**
 * 车站Label
 * 
 * @author 胡恩召
 * 
 */
public class TurnoutNameLabel extends Label {

	BaseParam baseData = BaseParam.getInstance();
	public Figure panel = baseData.getPanel();
	public int labHeight = baseData.getLabelHeight();
	public Color bgColor = baseData.getBackgroundColor();
	public Font font1 = baseData.getFont1();
	public int dis = baseData.getDistanceLen();	
	
	public String name; // lable的名称
	public Label nameLabel = new Label(); // lab
	public Color fontColor; // 字体颜色

	public TurnoutNameLabel(String text, int initX, int initY) {
		
		if (bgColor.equals(ColorConstants.white)) {
			fontColor = ColorConstants.black;
		} else {
			fontColor = ColorConstants.white;
		}
		
		name = text;
		nameLabel.setText(text);
		nameLabel.setFont(font1);
		nameLabel.setSize(text.length()*6, 8);
		nameLabel.setLocation(new Point(initX, initY));
		nameLabel.setForegroundColor(fontColor);
		panel.add(nameLabel);
		nameLabel.setVisible(false);
		baseData.getTurnoutNameList().add(nameLabel);
	}

	


}
