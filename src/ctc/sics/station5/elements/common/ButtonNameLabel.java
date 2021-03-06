package ctc.sics.station5.elements.common;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;

import ctc.sics.station5.data.BaseParam;

public class ButtonNameLabel extends Label {

	BaseParam baseData = BaseParam.getInstance();
	public Figure panel = baseData.getPanel();
	public int labHeight = baseData.getLabelHeight();
	public Color bgColor = baseData.getBackgroundColor();
	public Font font1 = baseData.getFont1();
	public int dis = baseData.getDistanceLen();
	public int bLen = baseData.getButtonLength();
	
	public String name; // lable的名称
	public Label nameLabel = new Label(); // lab
	public Color fontColor; // 字体颜色

	public ButtonNameLabel(String buttonName, int X, int Y, String type) {

		this.name = buttonName;
		if (type.equalsIgnoreCase("S")) { // 显示在上面
			Y = Y - labHeight - 1;
		} else if(type.equalsIgnoreCase("X")){ // 显示在下面
			Y = Y + bLen;
		}else if(type.equalsIgnoreCase("Z")){ //左边
			X = X - buttonName.length() * 6;
		}else{ //右边
			X = X;
		}

		if (bgColor.equals(ColorConstants.white)) {
			fontColor = ColorConstants.black;
		} else {
			fontColor = ColorConstants.white;
		}

		// 股道线名称
		this.nameLabel.setVisible(false);
		this.nameLabel.setText(buttonName);
		this.nameLabel.setFont(font1);
		this.nameLabel.setForegroundColor(fontColor);
		this.nameLabel.setSize(buttonName.length() * 7, labHeight);
		this.nameLabel.setLocation(new Point(X + bLen / 2 - buttonName.length() * 3, Y));
		panel.add(this.nameLabel);
		baseData.getButtonNameList().add(nameLabel);
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
