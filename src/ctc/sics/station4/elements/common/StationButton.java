package ctc.sics.station4.elements.common;

import org.eclipse.draw2d.Button;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.swt.graphics.Color;

import ctc.sics.station4.data.BaseParam;

/**
 * 车站按钮
 * @author 胡恩召
 *
 */
public class StationButton extends Button{

	BaseParam baseData = BaseParam.getInstance();
	public Color bColor = baseData.getButtonInitColor();
	public Color dColor = baseData.getD_buttonInitColor();
	public Color yColor = baseData.getY_buttonInitColor();
	public int bLen = baseData.getButtonLength();
	public Figure panel = baseData.getPanel();
	
	public String name;
	public Button button = new Button();
	public int length;
	public Color color;
	public String bType = "B"; //按钮类型，B分为接发车按钮，D为调车按钮
	public Label nameLabel = new Label();
	
	public StationButton(){	}

	public StationButton(String buttonName, String type, int initX, int initY, String locationType){
		
		this.bType = type;
		this.name = buttonName;		
		this.nameLabel.setText(buttonName);		
	
		new ButtonNameLabel(buttonName, initX, initY, locationType);
		
	}	
	
	public StationButton(String buttonName, String buttonType){
		this.bType = buttonType;
		this.name = buttonName;
		this.nameLabel.setText(buttonName);		
	}
	
	
	/**
	 * 新建一个button
	 * @param length button的边长
	 * @param initX button的X坐标
	 * @param initY button的Y坐标
	 * @param color button的颜色
	 * @param panel button的父容器
	 */
/*
	public StationButton(String buttonName, String type, int initX, int initY, int length, String locationType){
		
		this.bType = type;
		this.name = buttonName;		
		this.nameLabel.setText(buttonName);
		
		if(type.equalsIgnoreCase("B")){
			this.color = bColor;
		}else if(type.equalsIgnoreCase("D")){
			this.color = dColor;
		}else  if(type.equalsIgnoreCase("Y")){
			this.color = yColor;
		}

		this.button = new Button();
		this.button.setSize(bLen,bLen);
		this.button.setLocation(new Point(initX, initY));
		this.button.setBackgroundColor(color);		
		panel.add(button);
	
		new ButtonNameLabel(buttonName, initX, initY, length, locationType);
		
	}
	
*/
	
	public Button getButton() {
		return this.button;
	}
	public void setButton(Button button) {
		this.button = button;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}

	public String getBType() {
		return bType;
	}

	public void setBType(String type) {
		bType = type;
	}

	public Label getNameLabel() {
		return nameLabel;
	}

	public void setNameLabel(Label nameLabel) {
		this.nameLabel = nameLabel;
	}
	
	
}
