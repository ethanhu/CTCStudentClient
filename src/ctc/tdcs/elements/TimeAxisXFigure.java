package ctc.tdcs.elements;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.PolylineConnection;

import ctc.tdcs.data.BaseParam;


public class TimeAxisXFigure extends PolylineConnection {
	
		public TimeAxisXFigure(){
	
		//绘制线宽
		setLineWidth(BaseParam.getHoursWidth());
       
		//设置线的颜色 
		setForegroundColor(ColorConstants.red);
	}
	
	

}
