package ctc.sics.stationLayout;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class StationInit {

	/**
	 * 构造函数
	 * @param mainshell 程序主shell
	 */
	public StationInit(Shell mainshell, Composite newShell){
		
		DrawStation.shell = newShell;
		DrawStation.mainShell = mainshell;
		
		DrawStation.font1 = new Font(Display.getDefault(), "Tahoma", 14, SWT.COLOR_RED);
		DrawStation.font2 = new Font(Display.getDefault(), "MS Mincho", 20, SWT.ITALIC); //斜体
		DrawStation.font3 = new Font(Display.getDefault(), "Arabic Transparent", 20, SWT.NORMAL); //
		
		createContents();
		
	}
	
	protected void createContents() {
					
		DrawStation.shell.setLayout(new FillLayout());
		
		// 全图的容器1
		FigureCanvas canvas = new FigureCanvas(DrawStation.shell);
		canvas.setHorizontalScrollBarVisibility(FigureCanvas.ALWAYS);
		canvas.setVerticalScrollBarVisibility(FigureCanvas.ALWAYS);
		canvas.setBackground(ColorConstants.white);
		canvas.setSize(DrawStation.shell.getBounds().width, DrawStation.shell.getBounds().height);		
		// 容器1的内容fig
		DrawStation.panel = new Figure();
		DrawStation.panel.setLayoutManager(new XYLayout());		
		DrawStation.panel.setSize(DrawStation.shell.getBounds().width, DrawStation.shell.getBounds().height);
		canvas.setContents(DrawStation.panel); // 设置canvas的内容

		DrawStation.shellWidth = DrawStation.shell.getBounds().width;
		DrawStation.shellHeight = DrawStation.shell.getBounds().height;
	
		DrawStationName.drawStationName(); // 绘制站名
		DrawTurnoutButtonOdd.drawTurnoutButtonOdd(); // 绘制道岔单操按钮(奇数)
		DrawTurnoutButtonEven.drawTurnoutButtonEven(); // 绘制道岔单操按钮(偶数)
		DrawTurnoutButtonS.drawTurnoutButtonS(); // 绘制道岔操作按钮(上行)
		DrawTurnoutButtonX.drawTurnoutButtonX(); // 绘制道岔操作按钮(下行)
		DrawStationGraph.drawStationGraph(); // 绘制站型图
		SetRoadList.setRoadList(); //设置roadList内容
		SortRoadInfoList.sortRoadInfoList(); //设置车站内部的路径信息
		DrawTrackButtonS.drawTrackButtonS(); // 绘制股道操作按钮(上行)
		DrawTrackButtonX.drawTrackButtonX(); // 绘制股道操作按钮(下行)
		
		DrawTrainList.drawTrainList();
		DrawStationGraph.drawGrid();
		//DrawTrainList.changeTrainLabel("T11");
	}
	
}
