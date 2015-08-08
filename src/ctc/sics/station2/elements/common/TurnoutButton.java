package ctc.sics.station2.elements.common;

import org.eclipse.draw2d.Button;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Color;

import ctc.sics.station2.data.BaseParam;

public class TurnoutButton extends Button{

	BaseParam baseDataForStation = BaseParam.getInstance();
	
	public String name;
	public Button button = new Button();
	public int length;
	public Color color;
	public String bType = "S"; //按钮类型，B分为接发车按钮，D为调车按钮
	//public Label nameLabel = new Label();
	
	public TurnoutButton(){	}

	public TurnoutButton(String buttonName, String type){
		this.bType = type;
		this.name = buttonName;		
		//this.nameLabel.setText(buttonName);
	
	}
	
	
	/**
	 * 新建一个button
	 * @param length button的边长
	 * @param initX button的X坐标
	 * @param initY button的Y坐标
	 * @param color button的颜色
	 * @param panel button的父容器
	 */
	public TurnoutButton(String buttonName, String type, int initX, int initY){
		
		this.bType = type;
		this.name = buttonName;		
		//this.nameLabel.setText(buttonName);
		
	
		Figure panel = baseDataForStation.getPanel();
		
		this.length = baseDataForStation.getButtonLength();
		if(type.equalsIgnoreCase("B")){
			this.color = baseDataForStation.getButtonInitColor();
		}else{
			this.color = baseDataForStation.getD_buttonClickColor();
		}
		
		this.button = new Button();
		this.button.setSize(length,length);
		this.button.setLocation(new Point(initX, initY));
		this.button.setBackgroundColor(color);
		
		panel.add(button);
		
	}
	
	
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

		
	
}
