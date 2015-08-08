package ctc.sics.station3.elements.common;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;

import ctc.sics.station3.data.BaseParam;

public class LineNameLabel extends Label {

	BaseParam baseData = BaseParam.getInstance();
	public Figure panel = baseData.getPanel();
	public int labHeight = baseData.getLabelHeight();
	public Color bgColor = baseData.getBackgroundColor();
	public Font font1 = baseData.getFont1();
	public int dis = baseData.getDistanceLen();

	public String name; // lable的名称
	public Label nameLabel = new Label(); // lab
	public Color fontColor; // 字体颜色

	public LineNameLabel(String lineName, int X, int Y, int length, String type) {

		this.name = lineName;
		if (type.equalsIgnoreCase("S")) { // 显示在上面
			Y = Y - labHeight - dis;
		} else { // 显示在下面
			Y = Y + dis;
		}

		if (bgColor.equals(ColorConstants.white)) {
			fontColor = ColorConstants.black;
		} else {
			fontColor = ColorConstants.white;
		}

		// 股道线名称
		this.nameLabel.setVisible(false);
		this.nameLabel.setText(lineName);
		this.nameLabel.setFont(font1);
		this.nameLabel.setForegroundColor(fontColor);
		this.nameLabel.setSize(lineName.length() * 6, labHeight);
		this.nameLabel.setLocation(new Point(X + length / 2 - lineName.length() * 3, Y));
		panel.add(this.nameLabel);
		baseData.getTracklineNameList().add(nameLabel);
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
