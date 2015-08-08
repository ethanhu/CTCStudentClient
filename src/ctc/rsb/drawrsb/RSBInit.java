package ctc.rsb.drawrsb;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import ctc.rsb.drawrsb.data.BaseParam;
import ctc.sics.station.drawstation.DrawStationGraph;
import ctc.sics.station.drawstation.SetRoadList;


public class RSBInit {

	BaseParam baseData = BaseParam.getInstance();

	public Shell mainShell;
	public Composite shell;
	public Composite textShell;
	public static Figure panel = new Figure();
	
	public RSBInit(Shell mainshell, Composite newShell) {

		this.mainShell = mainshell;
		this.shell = new Composite(mainshell, SWT.BORDER);
		this.shell.setBounds(mainshell.getBounds().x, mainShell.getBounds().y, mainShell.getBounds().width, mainShell.getBounds().height);
		
		this.textShell = new Composite(mainshell, SWT.BORDER);
		this.textShell.setBounds(mainshell.getBounds().x, mainShell.getBounds().y, mainShell.getBounds().width - 150, 150);
	
		createContents();

	}

	protected void createContents() {

		// 全图的容器canvas
		FigureCanvas canvas = new FigureCanvas(shell);
		canvas.setHorizontalScrollBarVisibility(FigureCanvas.ALWAYS); //滚动条
		canvas.setVerticalScrollBarVisibility(FigureCanvas.ALWAYS); //滚动条
		canvas.setBackground(BaseParam.getBackgroundColor());
		canvas.setSize(shell.getBounds().width, shell.getBounds().height);

		panel.setSize(1320 * 5 + 20, 600);
		canvas.setContents(panel); // 设置canvas的内容
	
		//public int x0 = 10;
		//public int xstep = 1320;
		//public int X = x0 + xstep;
		//public int Y = 300;
		
		drawStation1(670,300);
		
	}
	
	//绘制车站1
	public void drawStation1(int X, int Y){
		
		Figure fig = new Figure();
		panel.add(fig);
		ctc.sics.station1.data.BaseParam.setCenterX(X);
		ctc.sics.station1.data.BaseParam.setCenterY(Y);
		ctc.sics.station1.data.BaseParam.setMainShell(mainShell);
		ctc.sics.station1.data.BaseParam.setPanel(fig);
		ctc.sics.station1.data.BaseParam.getPanel().setSize(1320, 600);
	
		ctc.sics.station1.drawstation.DrawStationGraph draw = new ctc.sics.station1.drawstation.DrawStationGraph();
		draw.drawStation();
		draw.drawStationName();
		draw.drawTrackLine();
		draw.drawStationButton();
		draw.drawSemaphore();
		draw.drawTurnoutButton();
		draw.drawModelButton();
		
		
		//SetRoadList srl = new SetRoadList();
		//srl.setStationRoad();
		
	}
	
}
