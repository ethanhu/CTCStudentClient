package ctc.sics.station5.elements.common;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;

import ctc.sics.station5.data.BaseParam;

public class LabelFree  extends Label {

	BaseParam baseData = BaseParam.getInstance();
	public Figure panel = baseData.getPanel();
	public int labHeight = baseData.getLabelHeight();
	public Color bgColor = baseData.getBackgroundColor();
	public Font font1 = baseData.getFont4();
	public int dis = baseData.getDistanceLen();
	public int bLen = baseData.getButtonLength();
	
	public String name; // lable的名称
	public Label nameLabel = new Label(); // lab
	public Color fontColor; // 字体颜色

	public LabelFree(String lineName, int X, int Y) {

		this.name = lineName;
				
		if (bgColor.equals(ColorConstants.white)) {
			fontColor = ColorConstants.black;
		} else {
			fontColor = ColorConstants.white;
		}

		// 股道线名称
		this.nameLabel.setVisible(true);
		this.nameLabel.setText(lineName);
		this.nameLabel.setFont(font1);
		this.nameLabel.setForegroundColor(fontColor);
		this.nameLabel.setSize(lineName.length() * 12, labHeight + 2);
		this.nameLabel.setLocation(new Point(X + bLen / 2 - lineName.length() * 6, Y - bLen - dis));
		panel.add(this.nameLabel);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Label getNameLabel() {
		return nameLabel;
	}

	public void setNameLabel(Label nameLabel) {
		this.nameLabel = nameLabel;
	}


}