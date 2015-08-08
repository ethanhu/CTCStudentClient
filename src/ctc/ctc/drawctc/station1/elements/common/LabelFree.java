package ctc.ctc.drawctc.station1.elements.common;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Point;

import ctc.ctc.drawctc.station1.data.BaseParam;

public class LabelFree  extends Label {

	BaseParam baseDataForStation = BaseParam.getInstance();

	Label lab = new Label(); // lab
	public String name; // lable的名称

	public String getLableName() {
		return name;
	}
	public void setLableName(String lableName) {
		this.name = lableName;
	}
	public Label getLab() {
		return lab;
	}
	public void setLab(Label lab) {
		this.lab = lab;
	}

	/**
	 * 新建一个Label
	 */
	public LabelFree(String text, int initX, int initY, int width, int height) {
		this.name = text;
		this.lab.setText(text);
		lab.setSize(width, height);
		lab.setLocation(new Point(initX, initY));
		baseDataForStation.getPanel().add(lab);
	}

	/**
	 * 改变颜色
	 * 
	 * @param color
	 */
	public void changeColor(String color) {

		if (color.equalsIgnoreCase("red")) {
			lab.setForegroundColor(ColorConstants.red);
		}

		if (color.equalsIgnoreCase("blue")) {
			lab.setForegroundColor(ColorConstants.blue);
		}
	}

}