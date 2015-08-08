package ctc.ctc.drawctc.station1.elements.common;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Point;

import ctc.ctc.drawctc.station1.data.BaseParam;

/**
 * 车站Label
 * 
 * @author 胡恩召
 * 
 */
public class NameLabel extends Label {

	BaseParam baseDataForStation = BaseParam.getInstance();

	public String name; // lable的名称
	Label lab = new Label(); // lab

	public NameLabel(String text, int initX, int initY) {
		name = text;
		lab.setText(text);
		lab.setFont(baseDataForStation.getFont1());
		lab.setSize(15, baseDataForStation.getLabelHeight());
		lab.setLocation(new Point(initX, initY));
		baseDataForStation.getPanel().add(lab);
		lab.setVisible(false);
		baseDataForStation.getNameLabelList().add(lab);
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


}
