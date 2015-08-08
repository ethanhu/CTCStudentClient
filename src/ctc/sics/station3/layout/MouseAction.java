package ctc.sics.station3.layout;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;

import ctc.sics.ui.SicsMainWindow;

import ctc.sics.station3.elements.common.*;
import ctc.sics.station3.data.BaseParam;


public class MouseAction extends MouseMotionListener.Stub implements MouseListener {
	
	BaseParam baseDataForStation = BaseParam.getInstance();
	
	private void showMsg(String str){
		MessageBox mb = new MessageBox(SicsMainWindow.getShell(), SWT.ABORT | SWT.ICON_INFORMATION);
		mb.setText("提示信息");//消息框的标题
		mb.setMessage(str);//消息框的提示文字
		mb.open();
	}
	
	// 构造方法，添加鼠标监听器
	public MouseAction(IFigure figure) {
		figure.addMouseMotionListener((MouseMotionListener) this);
		figure.addMouseListener(this);
	}

	// 双击事件
	public void mouseDoubleClicked(MouseEvent me) {
	}

	// 鼠标按住事件，开始画线的点
	public void mousePressed(MouseEvent me) {
		
		if(me.button == 1){
			//System.out.println("按钮 ");
			//StationButton button = (StationButton)me.getSource();
			//System.out.println("按钮: " + button.getName());
			
			showMsg("名称");
			
		}else{
			showMsg("名称2");
		}
		me.consume();
		
	}// 方法结束

	// 松开鼠标
	public void mouseReleased(MouseEvent me) {
		
		if(me.button == 1){
			System.out.println("按钮2 ");
			//showMsg("名称" + button.getName());
		}
		me.consume();
		
	}

	// 鼠标是否在移动
	public void mouseMoved(MouseEvent me) {

		
	}

	// 鼠标拖拽
	public void mouseDragged(MouseEvent me) {

		
	}

	// 判断鼠标是否在界面中
	public void mouseEntered(MouseEvent me) {
		
		if (me.getSource() instanceof StationButton) {
			StationButton button = (StationButton)me.getSource();
			//button.setBackgroundColor(ColorConstants.red); // 鼠标在矩形中，则使其变红	
			button.setToolTip(button.getNameLabel());
			button.setCursor(new  Cursor(Display.getDefault(), SWT.CURSOR_HAND)); //实现鼠标手型变换 
			
		}
		me.consume();
	}

	// 判断鼠标是否在界面外
	public void mouseExited(MouseEvent me) {
/*
		if (me.getSource() instanceof StationButton) {
			StationButton button = (StationButton)me.getSource();
			if(button.getBType().equalsIgnoreCase("L")){
				button.setBackgroundColor(ColorConstants.blue); // 鼠标在矩形中，则使其变红	
			}else if(button.getBType().equalsIgnoreCase("B")){
				button.setBackgroundColor(ColorConstants.blue);
			}
			
		}
		
		me.consume();
*/
	}

	

	// 获得不同的点
	public Point getDiffPoint(Point point, Point pos) {

		int xlocation = 0;
		int ylocation = 0;

		if (point.x > pos.x)
			xlocation = pos.x + 4;
		else {
			xlocation = pos.x - 4;
		}
		if (point.y > pos.y)
			ylocation = pos.y + 4;
		else {
			ylocation = pos.y - 4;
		}
		return new Point(xlocation, ylocation);

	}// 方法结束

}// MouseAction类结束
