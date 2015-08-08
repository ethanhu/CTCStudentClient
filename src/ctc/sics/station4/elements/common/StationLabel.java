package ctc.sics.station4.elements.common;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Point;

import ctc.sics.station4.data.BaseParam;

/**
 * 车站Label
 * @author 胡恩召
 *
 */
public class StationLabel extends Label {
	
	BaseParam baseData = BaseParam.getInstance();
	
	public String name; //lable的名称
	Label lab = new Label(); //lab

	/**
	 * 新建一个Label
	 */
	public StationLabel(String text, int initX, int initY){
		this.name = text;
		lab.setText(text);
		lab.setFont(baseData.getFont1());
		lab.setSize(baseData.getLabelWidth(), baseData.getLabelHeight());
		lab.setLocation(new Point(initX, initY));
		baseData.getPanel().add(lab);	
		lab.setVisible(false);
		baseData.getTracklineNameList().add(lab);
	}
	
	/**
	 * 改变颜色
	 * @param color
	 */
	public void changeColor(String color){
		
		if(color.equalsIgnoreCase("red")){
			lab.setForegroundColor(ColorConstants.red);
		}
		
		if(color.equalsIgnoreCase("blue")){
			lab.setForegroundColor(ColorConstants.blue);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Label getLab() {
		return lab;
	}

	public void setLab(Label lab) {
		this.lab = lab;
	}
	
}
