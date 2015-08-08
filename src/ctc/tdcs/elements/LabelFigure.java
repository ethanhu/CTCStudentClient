package ctc.tdcs.elements;


import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.geometry.Point;

import ctc.tdcs.data.BaseParam;


public class LabelFigure extends Label{
	
  public LabelFigure(boolean boardFlag,String name,int width,int height,int x,int y) {
	
	//设置布局方式
    setLayoutManager(BaseParam.getLayout());
    
    //边框颜色及线宽
    if(boardFlag)
    	setBorder(new LineBorder(BaseParam.getBoardColor(),BaseParam.getLineWidth()));
    
  //组件显示位置
	setLocation(new Point(x,y));
	
    //组件大小
	setSize(width,height);
	
	//设置label中文字的对齐方式
	setTextAlignment(BaseParam.getTextAlignment());
	//设置label的显示文字
	setText(name);
	
    //组件背景默认颜色
	//setBackgroundColor(TdcsDataset.getBackgroundColor());
	setOpaque(true);
  }
  
}