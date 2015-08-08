package ctc.ctc.rsb;

import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import ctc.ctc.rsb.data.BaseParam;
import ctc.ctc.rsb.layout.DrawRSB;
import ctc.ctc.rsb.model.RSBModel;


//区间
public class RSBInit {

	BaseParam baseData = BaseParam.getInstance();
	
	public RSBInit(Shell mainshell, Composite rsbShell) {

		baseData.setMainShell(mainshell);
		baseData.setRsbShell(rsbShell);		

		createContents();

	}

	protected void createContents() {

		int width = baseData.getRsbShell().getBounds().width;
		int height = baseData.getRsbShell().getBounds().height;
		baseData.setCenterX(width/2);
		baseData.setCenterY(height/2);
	
		// 全图的容器canvas
		FigureCanvas canvas = new FigureCanvas(baseData.getRsbShell());
		//canvas.setHorizontalScrollBarVisibility(FigureCanvas.ALWAYS); //滚动条
		//canvas.setVerticalScrollBarVisibility(FigureCanvas.ALWAYS); //滚动条
		canvas.setBackground(baseData.getBackgroundColor());
		canvas.setSize(width, height);

		// 容器canvas的内容fig
		baseData.getPanel().setLayoutManager(new XYLayout());
		//baseData.getPanel().setSize(3*canvas.getBounds().width, canvas.getBounds().height);
		baseData.getPanel().setSize(width, height);
		canvas.setContents(baseData.getPanel()); // 设置canvas的内容

		DrawRSB draw = new DrawRSB();
		draw.drawDistrictName();
		draw.drawDistrict1();
		draw.drawDistrict2();
		draw.drawDistrict3();
		draw.drawDistrict4();
		
		//自动闭塞
		RSBModel.getInstance().trainRun();
		
	}
	
	
}
