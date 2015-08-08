package ctc.sics.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Point;

/**
 * 车站Label
 * @author 胡恩召
 *
 */
public class StationLabel extends Label {
	
	public String lableName; //lable的名称
	Label lab = new Label(); //lab
	
	public String getLableName() {
		return lableName;
	}

	public void setLableName(String lableName) {
		this.lableName = lableName;
	}
		
	public Label getLab() {
		return lab;
	}

	public void setLab(Label lab) {
		this.lab = lab;
	}

	/**
	 * 新建一个Label
	 * @param text lable内容
	 * @param width label的宽度
	 * @param height label的高度，初始为8
	 * @param initX label的X坐标
	 * @param initY label的Y坐标
	 * @param panel label的父容器
	 */
	public StationLabel(String text, int width, int height, int initX, int initY, Figure panel){

		lab.setText(text);
		lab.setSize(width, height);
		lab.setLocation(new Point(initX, initY));
		panel.add(lab);
	}
	
	public StationLabel(){
		
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

}
