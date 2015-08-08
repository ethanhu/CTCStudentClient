package ctc.ctc.drawctc.ctcmain.drawctcmain;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import ctc.ctc.CTCMain;
import ctc.ctc.ctc2sics.CTCToSICS;
import ctc.ctc.db.CTCDataBase;
import ctc.ctc.drawctc.ctcmain.autosystem.AutoSystem;
import ctc.ctc.drawctc.ctcmain.autosystem.InitTrainSystem;
import ctc.ctc.drawctc.ctcmain.autosystem.SystemTimer;
import ctc.ctc.drawctc.ctcmain.autosystem.Task;
import ctc.ctc.drawctc.ctcmain.data.BaseParam;
import ctc.ctc.drawctc.ctcmain.model.CTCModel;
import ctc.pojobean.Plan;
import ctc.pojobean.TaskPlan;
import ctc.transport.message.CommonMessage;
import ctc.util.ErrorLog;

public class DrawCTCGraph {

	BaseParam baseData = BaseParam.getInstance();
	CTCModel ctcModel = CTCModel.getInstance();

	public static Table table1;
	public static Table table2;
	public static Table table3;
	public static Table table4;
	public static Table table5;

	public int width;
	public int height;
	
	public static boolean redrawFlag = false;
	
	private static DrawCTCGraph drawCTC = null;
	public static DrawCTCGraph getInstance() {
		if (drawCTC == null) {
			drawCTC = new DrawCTCGraph();
		}
		return drawCTC;
	}
	
	public DrawCTCGraph(){}
	
	AutoSystem autoSystem = AutoSystem.getInstance();
	public TrainItem trainItem;
	public Task task;
	public TableItem tableItem;
	public TableColumn column;
	
	public int step = 30;
	public int labWidth = 100;
	public int labHeight = 30;
	public String[] titles = { "车次", "状态", "方向", "类型", "计划时间", "实际时间", "股道", "起始按钮", "终端按钮" };
			
	//绘制CTC主控函数
	public DrawCTCGraph(Composite ctcCom) {
		
		//操作部分容器
		baseData.setCtcOperateCom(new Composite(ctcCom, SWT.BORDER));
		baseData.getCtcOperateCom().setBounds(ctcCom.getBounds().x, ctcCom.getBounds().y, ctcCom.getBounds().width, 80);
		drawOperate();
		
		//任务部分容器
		baseData.setCtcTaskCom(new Composite(ctcCom, SWT.BORDER));
		baseData.getCtcTaskCom().setBounds(ctcCom.getBounds().x, ctcCom.getBounds().y + 80, ctcCom.getBounds().width, ctcCom.getBounds().height - 80);
		
		int initX = 10;
		int initY = 0;		
		int stepX = 10;
		int stepY = 10;
		int width1 = (baseData.getCtcTaskCom().getBounds().width - stepX*2 - initX*2)/3;		
		int height1 = (baseData.getCtcTaskCom().getBounds().height - 6*stepY)/2;
			
		baseData.setCtcTaskCom1(new Composite(baseData.getCtcTaskCom(), SWT.BORDER));
		baseData.getCtcTaskCom1().setBounds(initX, initY, width1, height1);
		baseData.setCtcTaskCom2(new Composite(baseData.getCtcTaskCom(), SWT.BORDER));
		baseData.getCtcTaskCom2().setBounds(initX + width1 + stepX, initY, width1, height1);
		baseData.setCtcTaskCom3(new Composite(baseData.getCtcTaskCom(), SWT.BORDER));
		baseData.getCtcTaskCom3().setBounds(initX + 2*width1 + 2*stepX, initY, width1, height1);
		baseData.setCtcTaskCom4(new Composite(baseData.getCtcTaskCom(), SWT.BORDER));
		baseData.getCtcTaskCom4().setBounds(initX, initY + height1 + stepY, width1, height1);
		baseData.setCtcTaskCom5(new Composite(baseData.getCtcTaskCom(), SWT.BORDER));
		baseData.getCtcTaskCom5().setBounds(initX + width1 + stepX, initY + height1 + stepY, width1, height1);
		baseData.setCtcTaskCom6(new Composite(baseData.getCtcTaskCom(), SWT.BORDER));
		baseData.getCtcTaskCom6().setBounds(initX + 2*width1 + 2*stepX, initY + height1 + stepY, width1, height1);
		
		width = baseData.getCtcTaskCom1().getBounds().width;
		height = baseData.getCtcTaskCom1().getBounds().height - labWidth;
		
		drawTable(); //绘制table
		
	}
		

	//绘制操作部分
	public void drawOperate(){
		
		int initX = (baseData.getCtcOperateCom().getBounds().width - 750 - 3*labWidth) / 2;
		int initY = 40;
		int labWidth = 40;
		int labHeight = 20;
		int comWidth = 100;
		int comHeight = 20;
				
		Label districtName = new Label(baseData.getCtcOperateCom(), 0);
		districtName.setText("实验区段");
		districtName.setFont(baseData.getFont2());
		districtName.setSize(200, 30);
		districtName.setLocation((baseData.getCtcOperateCom().getBounds().width - 200) / 2, 5);

		
		Label stationName = new Label(baseData.getCtcOperateCom(), 0);
		stationName.setText("车站");
		stationName.setSize(labWidth, labHeight);
		stationName.setLocation(initX, initY);

		final Combo station = new Combo(baseData.getCtcOperateCom(), SWT.READ_ONLY);
		station.setItems(new String[] { "车站一", "车站二", "车站三", "车站四", "车站五" });
		station.select(0);
		station.setSize(comWidth, comHeight);
		station.setLocation(initX + 40, initY);

		
		Label taskName = new Label(baseData.getCtcOperateCom(), 0);
		taskName.setText("任务");
		taskName.setSize(labWidth, labHeight);
		taskName.setLocation(initX + 150, initY);

		final Combo task = new Combo(baseData.getCtcOperateCom(), SWT.READ_ONLY);
		task.setItems(new String[] { "接车", "发车", "通过"});
		task.select(0);
		task.setSize(comWidth, comHeight);
		task.setLocation(initX + 150 + 40, initY);
		
		Label directionName = new Label(baseData.getCtcOperateCom(), 0);
		directionName.setText("方向");		
		directionName.setSize(labWidth, labHeight);
		directionName.setLocation(initX + 300, initY);

		final Combo direction = new Combo(baseData.getCtcOperateCom(), SWT.READ_ONLY);
		direction.setItems(new String[] { "上行", "下行" });
		direction.select(0);
		direction.setSize(comWidth, comHeight);
		direction.setLocation(initX + 300 + 40, initY);

		
		Label trackName = new Label(baseData.getCtcOperateCom(), 0);
		trackName.setText("股道");		
		trackName.setSize(labWidth, labHeight);
		trackName.setLocation(initX + 450, initY);

		final Combo track = new Combo(baseData.getCtcOperateCom(), SWT.READ_ONLY);
		track.setItems(new String[] { "1G", "2G", "3G", "4G", "6G"});
		track.select(0);
		track.setSize(comWidth, comHeight);
		track.setLocation(initX + 450 + 40, initY);
	
			
		Label typeName = new Label(baseData.getCtcOperateCom(), 0);
		typeName.setText("类型");		
		typeName.setSize(labWidth, labHeight);
		typeName.setLocation(initX + 600, initY);
		
		final Combo type = new Combo(baseData.getCtcOperateCom(), SWT.READ_ONLY);
		type.setItems(new String[] { "执行", "取消"});
		type.select(0);
		type.setSize(comWidth, comHeight);
		type.setLocation(initX + 600 + 40, initY);

		
		Button operateButton = new Button(baseData.getCtcOperateCom(), SWT.PUSH);
		operateButton.setText("发送调度任务");
		operateButton.setSize(3*labWidth, labHeight);
		operateButton.setLocation(initX + 750, initY);

		operateButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {

				ErrorLog.log("\nCTC:发送CommonMessage");
				CommonMessage sMsg = new CommonMessage();
				sMsg.setStationName("车站一");
				sMsg.setMeseageName("BOOK");
				CTCToSICS.sendCommonMessage(sMsg);
				
/*
				String stationName = station.getText();
				String taskName = task.getText();
				String directionName = direction.getText();
				String trackName = track.getText();
				String taskType = type.getText();
				
				//进行任务处理
				ctcModel.proCTCTask(stationName, taskName, directionName, trackName, taskType);
*/			
			}
		});
		
		
		
	}
	
		
	//绘制任务部分
	public void drawTable(){
		
		drawTask1();
		drawTask2();
		drawTask3();
		drawTask4();
		drawTask5();
		drawTask6();
				
	}
	
	//绘制任务部分
	public void updateTable(){
		
		updateTask1();
		updateTask2();
		updateTask3();
		updateTask4();
		updateTask5();
						
	}
	
	//创建1个带标题的table
	public void drawTask1() 
	{	
		String stationName = "车站一";		
		Label stationNameLabel = new Label(baseData.getCtcTaskCom1(), 0);
		stationNameLabel.setText(stationName);
		stationNameLabel.setFont(baseData.getFont2());
		stationNameLabel.setSize(labWidth, labHeight - 5);
		stationNameLabel.setLocation((width - labWidth) / 2, 3);
		
		table1 = new Table(baseData.getCtcTaskCom1(), SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
		table1.setLinesVisible(true);
		table1.setHeaderVisible(true);
		table1.setSize(table1.computeSize(width - step, height));
		table1.setLocation(0, labHeight);
		
		for (int i = 0; i < titles.length; i++) {
			column = new TableColumn(table1, SWT.NONE);
			column.setText(titles[i]);
		}	
		
		for (int i = 0; i < titles.length; i++) {
			table1.getColumn(i).pack();
		}		
	}
	
	//刷新代码（根据内容动态显示表格内容） 
	public void updateTask1(){
		
		if(baseData.getTaskList1() == null || baseData.getTaskList1().size() < 1){
			System.out.println("baseData.getTaskList1() == null || baseData.getTaskList1().size() < 1");
			table1.clearAll();
			return;
		}
			
		int len = baseData.getTaskList1().size();	
		int tabLen = table1.getItems().length;
		
		if(len >= tabLen){			
			for(int i = 0; i < tabLen; i++){ //重写原有tableItem的值				
				TaskPlan taskPlan = baseData.getTaskList1().get(i);
				table1.getItem(i).setText(0, taskPlan.getTrainName());
				//table1.getItem(i).setText(1, ""); //任务状态,保持原有不变		
				table1.getItem(i).setText(2, taskPlan.getTrainDirection());
				table1.getItem(i).setText(3, taskPlan.getTaskType());
				table1.getItem(i).setText(4, taskPlan.getTime());
				//table1.getItem(i).setText(5, ""); //实际执行时间,保持原有不变
				table1.getItem(i).setText(6, taskPlan.getTrackLine());
				table1.getItem(i).setText(7, taskPlan.getStartButton());
				table1.getItem(i).setText(8, taskPlan.getEndButton());				
			}
			
			for(int j = 0; j < len - tabLen; j++){ //新建item
				TaskPlan taskPlan = baseData.getTaskList1().get(tabLen+j);
				tableItem = new TableItem(table1, SWT.NONE);	
				tableItem.setText(0, taskPlan.getTrainName());
				tableItem.setText(1, "等待执行");			
				tableItem.setText(2, taskPlan.getTrainDirection());
				tableItem.setText(3, taskPlan.getTaskType());
				tableItem.setText(4, taskPlan.getTime());
				tableItem.setText(5, ""); //实际执行时间
				tableItem.setText(6, taskPlan.getTrackLine());
				tableItem.setText(7, taskPlan.getStartButton());
				tableItem.setText(8, taskPlan.getEndButton());				
			}
			
		}else{
			
			for(int i = 0; i < len; i++){ //重写原有tableItem的值				
				TaskPlan taskPlan = baseData.getTaskList1().get(i);
				table1.getItem(i).setText(0, taskPlan.getTrainName());
				//table1.getItem(i).setText(1, ""); //任务状态,保持原有不变		
				table1.getItem(i).setText(2, taskPlan.getTrainDirection());
				table1.getItem(i).setText(3, taskPlan.getTaskType());
				table1.getItem(i).setText(4, taskPlan.getTime());
				//table1.getItem(i).setText(5, ""); //实际执行时间,保持原有不变
				table1.getItem(i).setText(6, taskPlan.getTrackLine());
				table1.getItem(i).setText(7, taskPlan.getStartButton());
				table1.getItem(i).setText(8, taskPlan.getEndButton());				
			}
			
			for(int j = 0; j < tabLen - len; j++){ //清除多余item
				table1.clear(len+j);	
			}
			
		}
		
		for (int i = 0; i < titles.length; i++) {
			table1.getColumn(i).pack();
		}		
	}
	
	public void drawTask2(){
	
		String stationName = "车站二";		
		Label stationNameLabel = new Label(baseData.getCtcTaskCom2(), 0);
		stationNameLabel.setText(stationName);
		stationNameLabel.setFont(baseData.getFont2());
		stationNameLabel.setSize(labWidth, labHeight - 5);
		stationNameLabel.setLocation((width - labWidth) / 2, 3);
		
		table2 = new Table(baseData.getCtcTaskCom2(), SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
		table2.setLinesVisible(true);
		table2.setHeaderVisible(true);		
		//table2.setSize(table2.computeSize(width - step, height - step));
		table2.setSize(table2.computeSize(width - step, height));
		table2.setLocation(0, labHeight);
		
		for (int i = 0; i < titles.length; i++) {
			TableColumn column = new TableColumn(table2, SWT.NONE);
			column.setText(titles[i]);
		}

		for (int i = 0; i < titles.length; i++) {
			table2.getColumn(i).pack();
		}
	}
	
	public void updateTask2(){
			
		if(baseData.getTaskList2() == null || baseData.getTaskList2().size() < 1){
			System.out.println("baseData.getTaskList2() == null || baseData.getTaskList2().size() < 1");
			table2.clearAll();
			return;
		}
			
		int len = baseData.getTaskList2().size();	
		int tabLen = table2.getItems().length;
		
		if(len >= tabLen){			
			for(int i = 0; i < tabLen; i++){ //重写原有tableItem的值				
				TaskPlan taskPlan = baseData.getTaskList2().get(i);
				table2.getItem(i).setText(0, taskPlan.getTrainName());
				//table2.getItem(i).setText(1, ""); //任务状态,保持原有不变		
				table2.getItem(i).setText(2, taskPlan.getTrainDirection());
				table2.getItem(i).setText(3, taskPlan.getTaskType());
				table2.getItem(i).setText(4, taskPlan.getTime());
				//table2.getItem(i).setText(5, ""); //实际执行时间,保持原有不变
				table2.getItem(i).setText(6, taskPlan.getTrackLine());
				table2.getItem(i).setText(7, taskPlan.getStartButton());
				table2.getItem(i).setText(8, taskPlan.getEndButton());				
			}
			
			for(int j = 0; j < len - tabLen; j++){ //新建item
				TaskPlan taskPlan = baseData.getTaskList2().get(tabLen+j);
				tableItem = new TableItem(table2, SWT.NONE);	
				tableItem.setText(0, taskPlan.getTrainName());
				tableItem.setText(1, "等待执行");			
				tableItem.setText(2, taskPlan.getTrainDirection());
				tableItem.setText(3, taskPlan.getTaskType());
				tableItem.setText(4, taskPlan.getTime());
				tableItem.setText(5, ""); //实际执行时间
				tableItem.setText(6, taskPlan.getTrackLine());
				tableItem.setText(7, taskPlan.getStartButton());
				tableItem.setText(8, taskPlan.getEndButton());				
			}
			
		}else{
			
			for(int i = 0; i < len; i++){ //重写原有tableItem的值				
				TaskPlan taskPlan = baseData.getTaskList2().get(i);
				table2.getItem(i).setText(0, taskPlan.getTrainName());
				//table2.getItem(i).setText(1, ""); //任务状态,保持原有不变		
				table2.getItem(i).setText(2, taskPlan.getTrainDirection());
				table2.getItem(i).setText(3, taskPlan.getTaskType());
				table2.getItem(i).setText(4, taskPlan.getTime());
				//table2.getItem(i).setText(5, ""); //实际执行时间,保持原有不变
				table2.getItem(i).setText(6, taskPlan.getTrackLine());
				table2.getItem(i).setText(7, taskPlan.getStartButton());
				table2.getItem(i).setText(8, taskPlan.getEndButton());				
			}
			
			for(int j = 0; j < tabLen - len; j++){ //清除多余item
				table2.clear(len+j);	
			}			
		}
		
		for (int i = 0; i < titles.length; i++) {
			table2.getColumn(i).pack();
		}		
	}
	
	public void drawTask3(){
		
		String stationName = "车站三";		
		Label stationNameLabel = new Label(baseData.getCtcTaskCom3(), 0);
		stationNameLabel.setText(stationName);
		stationNameLabel.setFont(baseData.getFont2());
		stationNameLabel.setSize(labWidth, labHeight - 5);
		stationNameLabel.setLocation((width - labWidth) / 2, 3);
		
		table3 = new Table(baseData.getCtcTaskCom3(), SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
		table3.setLinesVisible(true);
		table3.setHeaderVisible(true);		
		table3.setSize(table3.computeSize(width - step, height));
		table3.setLocation(0, labHeight);
		
		for (int i = 0; i < titles.length; i++) {
			TableColumn column = new TableColumn(table3, SWT.NONE);
			column.setText(titles[i]);
		}

		for (int i = 0; i < titles.length; i++) {
			table3.getColumn(i).pack();
		}
	}
	
	public void updateTask3(){
		
		if(baseData.getTaskList3() == null || baseData.getTaskList3().size() < 1){
			System.out.println("baseData.getTaskList3() == null || baseData.getTaskList3().size() < 1");
			table3.clearAll();
			return;
		}
			
		int len = baseData.getTaskList3().size();	
		int tabLen = table3.getItems().length;
		
		if(len >= tabLen){			
			for(int i = 0; i < tabLen; i++){ //重写原有tableItem的值				
				TaskPlan taskPlan = baseData.getTaskList3().get(i);
				table3.getItem(i).setText(0, taskPlan.getTrainName());
				//table3.getItem(i).setText(1, ""); //任务状态,保持原有不变		
				table3.getItem(i).setText(2, taskPlan.getTrainDirection());
				table3.getItem(i).setText(3, taskPlan.getTaskType());
				table3.getItem(i).setText(4, taskPlan.getTime());
				//table3.getItem(i).setText(5, ""); //实际执行时间,保持原有不变
				table3.getItem(i).setText(6, taskPlan.getTrackLine());
				table3.getItem(i).setText(7, taskPlan.getStartButton());
				table3.getItem(i).setText(8, taskPlan.getEndButton());				
			}
			
			for(int j = 0; j < len - tabLen; j++){ //新建item
				TaskPlan taskPlan = baseData.getTaskList3().get(tabLen+j);
				tableItem = new TableItem(table3, SWT.NONE);	
				tableItem.setText(0, taskPlan.getTrainName());
				tableItem.setText(1, "等待执行");			
				tableItem.setText(2, taskPlan.getTrainDirection());
				tableItem.setText(3, taskPlan.getTaskType());
				tableItem.setText(4, taskPlan.getTime());
				tableItem.setText(5, ""); //实际执行时间
				tableItem.setText(6, taskPlan.getTrackLine());
				tableItem.setText(7, taskPlan.getStartButton());
				tableItem.setText(8, taskPlan.getEndButton());				
			}
			
		}else{
			
			for(int i = 0; i < len; i++){ //重写原有tableItem的值				
				TaskPlan taskPlan = baseData.getTaskList3().get(i);
				table3.getItem(i).setText(0, taskPlan.getTrainName());
				//table3.getItem(i).setText(1, ""); //任务状态,保持原有不变		
				table3.getItem(i).setText(2, taskPlan.getTrainDirection());
				table3.getItem(i).setText(3, taskPlan.getTaskType());
				table3.getItem(i).setText(4, taskPlan.getTime());
				//table3.getItem(i).setText(5, ""); //实际执行时间,保持原有不变
				table3.getItem(i).setText(6, taskPlan.getTrackLine());
				table3.getItem(i).setText(7, taskPlan.getStartButton());
				table3.getItem(i).setText(8, taskPlan.getEndButton());				
			}
			
			for(int j = 0; j < tabLen - len; j++){ //清除多余item
				table3.clear(len+j);	
			}			
		}
		
		for (int i = 0; i < titles.length; i++) {
			table3.getColumn(i).pack();
		}		

	}
	
	public void drawTask4(){
	
		String stationName = "车站四";		
		Label stationNameLabel = new Label(baseData.getCtcTaskCom4(), 0);
		stationNameLabel.setText(stationName);
		stationNameLabel.setFont(baseData.getFont2());
		stationNameLabel.setSize(labWidth, labHeight - 5);
		stationNameLabel.setLocation((width - labWidth) / 2, 3);
		
		table4 = new Table(baseData.getCtcTaskCom4(), SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
		table4.setLinesVisible(true);
		table4.setHeaderVisible(true);		
		table4.setSize(table4.computeSize(width - step, height));
		table4.setLocation(0, labHeight);
		
		for (int i = 0; i < titles.length; i++) {
			TableColumn column = new TableColumn(table4, SWT.NONE);
			column.setText(titles[i]);
		}

		for (int i = 0; i < titles.length; i++) {
			table4.getColumn(i).pack();
		}
	}
	
	public void updateTask4(){
		
		if(baseData.getTaskList4() == null || baseData.getTaskList4().size() < 1){
			System.out.println("baseData.getTaskList4() == null || baseData.getTaskList4().size() < 1");
			table4.clearAll();
			return;
		}
			
		int len = baseData.getTaskList4().size();	
		int tabLen = table4.getItems().length;
		
		if(len >= tabLen){			
			for(int i = 0; i < tabLen; i++){ //重写原有tableItem的值				
				TaskPlan taskPlan = baseData.getTaskList4().get(i);
				table4.getItem(i).setText(0, taskPlan.getTrainName());
				//table4.getItem(i).setText(1, ""); //任务状态,保持原有不变		
				table4.getItem(i).setText(2, taskPlan.getTrainDirection());
				table4.getItem(i).setText(3, taskPlan.getTaskType());
				table4.getItem(i).setText(4, taskPlan.getTime());
				//table4.getItem(i).setText(5, ""); //实际执行时间,保持原有不变
				table4.getItem(i).setText(6, taskPlan.getTrackLine());
				table4.getItem(i).setText(7, taskPlan.getStartButton());
				table4.getItem(i).setText(8, taskPlan.getEndButton());				
			}
			
			for(int j = 0; j < len - tabLen; j++){ //新建item
				TaskPlan taskPlan = baseData.getTaskList4().get(tabLen+j);
				tableItem = new TableItem(table4, SWT.NONE);	
				tableItem.setText(0, taskPlan.getTrainName());
				tableItem.setText(1, "等待执行");			
				tableItem.setText(2, taskPlan.getTrainDirection());
				tableItem.setText(3, taskPlan.getTaskType());
				tableItem.setText(4, taskPlan.getTime());
				tableItem.setText(5, ""); //实际执行时间
				tableItem.setText(6, taskPlan.getTrackLine());
				tableItem.setText(7, taskPlan.getStartButton());
				tableItem.setText(8, taskPlan.getEndButton());				
			}
			
		}else{
			
			for(int i = 0; i < len; i++){ //重写原有tableItem的值				
				TaskPlan taskPlan = baseData.getTaskList4().get(i);
				table4.getItem(i).setText(0, taskPlan.getTrainName());
				//table4.getItem(i).setText(1, ""); //任务状态,保持原有不变		
				table4.getItem(i).setText(2, taskPlan.getTrainDirection());
				table4.getItem(i).setText(3, taskPlan.getTaskType());
				table4.getItem(i).setText(4, taskPlan.getTime());
				//table4.getItem(i).setText(5, ""); //实际执行时间,保持原有不变
				table4.getItem(i).setText(6, taskPlan.getTrackLine());
				table4.getItem(i).setText(7, taskPlan.getStartButton());
				table4.getItem(i).setText(8, taskPlan.getEndButton());				
			}
			
			for(int j = 0; j < tabLen - len; j++){ //清除多余item
				table4.clear(len+j);	
			}			
		}
		
		for (int i = 0; i < titles.length; i++) {
			table4.getColumn(i).pack();
		}			
	}
	
	
	public void drawTask5(){
	
		String stationName = "车站五";		
		Label stationNameLabel = new Label(baseData.getCtcTaskCom5(), 0);
		stationNameLabel.setText(stationName);
		stationNameLabel.setFont(baseData.getFont2());
		stationNameLabel.setSize(labWidth, labHeight - 5);
		stationNameLabel.setLocation((width - labWidth) / 2, 3);
		
		table5 = new Table(baseData.getCtcTaskCom5(), SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
		table5.setLinesVisible(true);
		table5.setHeaderVisible(true);		
		table5.setSize(table5.computeSize(width - step, height));
		table5.setLocation(0, labHeight);
		
		for (int i = 0; i < titles.length; i++) {
			TableColumn column = new TableColumn(table5, SWT.NONE);
			column.setText(titles[i]);
		}

		for (int i = 0; i < titles.length; i++) {
			table5.getColumn(i).pack();
		}
	}
	
	public void updateTask5(){
		
		if(baseData.getTaskList5() == null || baseData.getTaskList5().size() < 1){
			System.out.println("baseData.getTaskList5() == null || baseData.getTaskList5().size() < 1");
			table5.clearAll();
			return;
		}
			
		int len = baseData.getTaskList5().size();	
		int tabLen = table5.getItems().length;

		if(len >= tabLen){			
			for(int i = 0; i < tabLen; i++){ //重写原有tableItem的值				
				TaskPlan taskPlan = baseData.getTaskList5().get(i);
				table5.getItem(i).setText(0, taskPlan.getTrainName());
				//table5.getItem(i).setText(1, ""); //任务状态,保持原有不变		
				table5.getItem(i).setText(2, taskPlan.getTrainDirection());
				table5.getItem(i).setText(3, taskPlan.getTaskType());
				table5.getItem(i).setText(4, taskPlan.getTime());
				//table5.getItem(i).setText(5, ""); //实际执行时间,保持原有不变
				table5.getItem(i).setText(6, taskPlan.getTrackLine());
				table5.getItem(i).setText(7, taskPlan.getStartButton());
				table5.getItem(i).setText(8, taskPlan.getEndButton());				
			}
			
			for(int j = 0; j < len - tabLen; j++){ //新建item
				TaskPlan taskPlan = baseData.getTaskList5().get(tabLen+j);
				tableItem = new TableItem(table5, SWT.NONE);	
				tableItem.setText(0, taskPlan.getTrainName());
				tableItem.setText(1, "等待执行");			
				tableItem.setText(2, taskPlan.getTrainDirection());
				tableItem.setText(3, taskPlan.getTaskType());
				tableItem.setText(4, taskPlan.getTime());
				tableItem.setText(5, ""); //实际执行时间
				tableItem.setText(6, taskPlan.getTrackLine());
				tableItem.setText(7, taskPlan.getStartButton());
				tableItem.setText(8, taskPlan.getEndButton());				
			}
			
		}else{
			
			for(int i = 0; i < len; i++){ //重写原有tableItem的值				
				TaskPlan taskPlan = baseData.getTaskList5().get(i);
				table5.getItem(i).setText(0, taskPlan.getTrainName());
				//table5.getItem(i).setText(1, ""); //任务状态,保持原有不变		
				table5.getItem(i).setText(2, taskPlan.getTrainDirection());
				table5.getItem(i).setText(3, taskPlan.getTaskType());
				table5.getItem(i).setText(4, taskPlan.getTime());
				//table5.getItem(i).setText(5, ""); //实际执行时间,保持原有不变
				table5.getItem(i).setText(6, taskPlan.getTrackLine());
				table5.getItem(i).setText(7, taskPlan.getStartButton());
				table5.getItem(i).setText(8, taskPlan.getEndButton());				
			}
			
			for(int j = 0; j < tabLen - len; j++){ //清除多余item
				table5.clear(len+j);	
			}			
		}
		
		for (int i = 0; i < titles.length; i++) {
			table5.getColumn(i).pack();
		}		
	}
	public void drawTask6(){
				
		int initX = 10;
		int initY = 20;

		org.eclipse.swt.widgets.Label operateLabel = new org.eclipse.swt.widgets.Label(baseData.getCtcTaskCom6(), SWT.NONE);
		operateLabel.setText("操作记录：");
		operateLabel.setSize(100, 30);
		operateLabel.setFont(baseData.getFont3());
		operateLabel.setLocation(initX, initY);

		baseData.setMsgLabel(new Text(baseData.getCtcTaskCom6(), SWT.NONE));
		baseData.getMsgLabel().setSize(200, 30);
		baseData.getMsgLabel().setFont(baseData.getFont3());
		baseData.getMsgLabel().setLocation(initX + 110, initY);

		org.eclipse.swt.widgets.Label timeLabel = new org.eclipse.swt.widgets.Label(baseData.getCtcTaskCom6(), SWT.NONE);
		timeLabel.setText("虚拟时间：");
		timeLabel.setSize(100, 30);
		timeLabel.setFont(baseData.getFont3());
		timeLabel.setLocation(initX, initY + 50);

		baseData.setTimeLabel(new org.eclipse.swt.widgets.Label(baseData.getCtcTaskCom6(), SWT.NONE));
		baseData.getTimeLabel().setText("12:10");
		baseData.getTimeLabel().setSize(150, 30);
		baseData.getTimeLabel().setFont(baseData.getFont3());
		baseData.getTimeLabel().setLocation(initX + 110, initY + 50);

/*
		org.eclipse.swt.widgets.Button initButton = new org.eclipse.swt.widgets.Button(baseData.getCtcTaskCom6(), SWT.NONE);
		initButton.setText("初始化列车");
		initButton.setSize(100, 30);
		initButton.setFont(baseData.getFont4());
		initButton.setLocation(initX, initY + 100);
*/	
		SystemTimer sysTimer = new SystemTimer();		//启动系统时间
		sysTimer.SystemRun(1, 1);
		
		InitTrainSystem initSystem = new InitTrainSystem();		//启动系统时间
		initSystem.SystemRun(1, 1);
		
		
		
	}
	
	/**
	 * 获得车次信息
	 */
	public List<Plan> getPlanList() {

		CTCDataBase data = CTCMain.getDb();
		if (data == null) {
			return null;
		}

		List<Plan> trainPlanList = data.getPlanInfoList();
		if (trainPlanList == null || trainPlanList.size() == 0) {
			return null;
		}

		return trainPlanList;

	}
	
	
}

