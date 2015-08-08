package ctc.sics.figure;



import org.eclipse.draw2d.Button;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Color;

/**
 * 车站按钮
 * @author 胡恩召
 *
 */
public class StationButton extends Button{

	public String buttonName;
	public Button button;
		
	public String getButtonName() {
		return buttonName;
	}

	public void setButtonName(String buttonName) {
		this.buttonName = buttonName;
	}	
		
	public Button getButton() {
		return this.button;
	}

	public void setButton(Button button) {
		this.button = button;
	}

	/**
	 * 新建一个button
	 * @param length button的边长
	 * @param initX button的X坐标
	 * @param initY button的Y坐标
	 * @param color button的颜色
	 * @param panel button的父容器
	 */
	public StationButton(int length, int initX, int initY, Color color){
		
		this.button = new Button();
		this.button.setSize(length,length);
		this.button.setLocation(new Point(initX, initY));
		this.button.setBackgroundColor(color);
		
	}
}
