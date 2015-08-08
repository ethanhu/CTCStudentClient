package ctc.sics.station3.layout;

import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import ctc.sics.station3.autosystem.SelfDisciplineSystem;
import ctc.sics.station3.data.BaseParam;
import ctc.sics.station3.drawstation.*;


/**
 * 入口程序
 * @author 胡恩召
 * 
 */
public class StationInit {

	BaseParam baseData = BaseParam.getInstance();

	
	public StationInit(Shell mainshell, Composite newShell) {

		baseData.setMainShell(mainshell);
		baseData.setStationShell(newShell);

		// 车站站型容器
		baseData.setShell(new Composite(baseData.getStationShell(), SWT.BORDER));
		baseData.getShell().setBounds(baseData.getStationShell().getBounds().x, baseData.getStationShell().getBounds().y, baseData.getStationShell().getBounds().width, baseData.getStationShell().getBounds().height - 150);

		// 操作文本显示部分容器
		baseData.setTextShell(new Composite(baseData.getStationShell(), SWT.BORDER));
		baseData.getTextShell().setBounds(baseData.getStationShell().getBounds().x,
				baseData.getStationShell().getBounds().height - 150, baseData.getStationShell().getBounds().width, 150);

		createContents();

	}

	protected void createContents() {

		baseData.setShellWidth(baseData.getShell().getBounds().width);
		baseData.setShellHeight(baseData.getShell().getBounds().height);
		baseData.getShell().setLayout(new FillLayout());

		baseData.setCenterX(baseData.getShellWidth()/2);
		baseData.setCenterY(baseData.getShellHeight()/2);
	
		// 全图的容器canvas
		FigureCanvas canvas = new FigureCanvas(baseData.getShell());
		//canvas.setHorizontalScrollBarVisibility(FigureCanvas.ALWAYS); //滚动条
		//canvas.setVerticalScrollBarVisibility(FigureCanvas.ALWAYS); //滚动条
		canvas.setBackground(baseData.getBackgroundColor());
		canvas.setSize(baseData.getShell().getBounds().width, baseData.getShell().getBounds().height);

		// 容器canvas的内容fig
		baseData.getPanel().setLayoutManager(new XYLayout());
		//baseData.getPanel().setSize(3*canvas.getBounds().width, canvas.getBounds().height);
		baseData.getPanel().setSize(canvas.getBounds().width, canvas.getBounds().height);
		canvas.setContents(baseData.getPanel()); // 设置canvas的内容

		DrawStationGraph draw = new DrawStationGraph();
		draw.drawStation();
		draw.drawStationName();
		draw.drawTrackLine();
		draw.drawStationButton();
		draw.drawSemaphore();
		draw.drawTurnoutButton();
		draw.drawModelButton();
		//draw.drawGrid();
		
		SetRoadList srl = new SetRoadList();
		srl.setStationRoad();
		
		//设置停车路径
		SetStopLineRoad setStop = new SetStopLineRoad();
		setStop.setStopRoadArray();
		
		////////////////////////////////////////////////////////////////////		
		//-------------------消息显示容器canvas----------------------------//			
		draw.drawMessageText();
		SystemTimer sysTimer = new SystemTimer();		//启动系统时间
		sysTimer.SystemRun(1, 1);
		
		
		////////////////////////////////////////////////////////////////////
		//启动站内行车控制自律机
		SelfDisciplineSystem selfDiscipline = SelfDisciplineSystem.getInstance();
		selfDiscipline.TrainSelfDisciplineSystem();
		//启动列车计划信息自律机
		//AutoSystem autoSystem = new AutoSystem();
		//autoSystem.AutoSystemRun(1, 1);
		
	}
}
