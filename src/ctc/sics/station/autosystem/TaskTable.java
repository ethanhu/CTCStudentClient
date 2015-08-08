package ctc.sics.station.autosystem;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import ctc.pojobean.Plan;
import ctc.pojobean.TaskPlan;

import ctc.sics.SicsMain;
import ctc.sics.db.DataBase;
import ctc.sics.station.elements.common.*;
import ctc.databaseserver.DatabaseService;

import ctc.sics.station.data.BaseParam;

public class TaskTable {

	BaseParam baseData = BaseParam.getInstance();
	DatabaseService dataBase = DatabaseService.getInstance();
	
	public TrainItem trainItem;
	public Task task;
	
	public static Table table;
	public String[] titles = { "车次", "状态", "方向", "类型", "计划时间", "实际时间", "股道", "起始按钮", "终端按钮" };
	List<TaskPlan> taskList = new ArrayList<TaskPlan>();
	
	private static TaskTable taskTable = null;

	public static TaskTable getInstance() {
		if (taskTable == null) {
			taskTable = new TaskTable();
		}
		return taskTable;
	}

	public void setTableContents() {

		table = new Table(baseData.getTextShell(), SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		int width = baseData.getTextShell().getBounds().width;
		int height = baseData.getTextShell().getBounds().height;
		table.setSize(table.computeSize(600, height - 40));
		table.setLocation(baseData.getTextShell().getBounds().width - 650, 0);
		
		for (int i = 0; i < titles.length; i++) {
			TableColumn column = new TableColumn(table, SWT.NONE);
			column.setText(titles[i]);
		}	
		
		for (int i = 0; i < titles.length; i++) {
			table.getColumn(i).pack();
		}		
	}

	//刷新代码（根据内容动态显示表格内容） 
	public void updateTable(){
				
		if(baseData.getTaskList() == null || baseData.getTaskList().size() < 1){
			System.out.println("baseData.getTaskList() == null || baseData.getTaskList().size() < 1");
			table.clearAll();
			return;
		}
			
		int len = baseData.getTaskList().size();	
		int tabLen = table.getItems().length;
		
		if(len >= tabLen){			
			for(int i = 0; i < tabLen; i++){ //重写原有tableItem的值				
				TaskPlan taskPlan = baseData.getTaskList().get(i);
				table.getItem(i).setText(0, taskPlan.getTrainName());
				//table.getItem(i).setText(1, ""); //任务状态,保持原有不变		
				table.getItem(i).setText(2, taskPlan.getTrainDirection());
				table.getItem(i).setText(3, taskPlan.getTaskType());
				table.getItem(i).setText(4, taskPlan.getTime());
				//table.getItem(i).setText(5, ""); //实际执行时间,保持原有不变
				table.getItem(i).setText(6, taskPlan.getTrackLine());
				table.getItem(i).setText(7, taskPlan.getStartButton());
				table.getItem(i).setText(8, taskPlan.getEndButton());				
			}
			
			for(int j = 0; j < len - tabLen; j++){ //新建item
				TaskPlan taskPlan = baseData.getTaskList().get(tabLen+j);
				TableItem tableItem = new TableItem(table, SWT.NONE);	
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
				TaskPlan taskPlan = baseData.getTaskList().get(i);
				table.getItem(i).setText(0, taskPlan.getTrainName());
				//table.getItem(i).setText(1, ""); //任务状态,保持原有不变		
				table.getItem(i).setText(2, taskPlan.getTrainDirection());
				table.getItem(i).setText(3, taskPlan.getTaskType());
				table.getItem(i).setText(4, taskPlan.getTime());
				//table.getItem(i).setText(5, ""); //实际执行时间,保持原有不变
				table.getItem(i).setText(6, taskPlan.getTrackLine());
				table.getItem(i).setText(7, taskPlan.getStartButton());
				table.getItem(i).setText(8, taskPlan.getEndButton());				
			}
			
			for(int j = 0; j < tabLen - len; j++){ //清除多余item
				table.clear(len+j);	
			}
			
		}
		
		for (int i = 0; i < titles.length; i++) {
			table.getColumn(i).pack();
		}		
	}
	
	
	
	/**
	 * 获得车次信息
	 */
	public List<Plan> getPlanList() {

		DataBase data = SicsMain.getDb();
		if (data == null) {
			return null;
		}

		List<Plan> trainPlanList = data.getPlanInfoList();
		if (trainPlanList == null || trainPlanList.size() == 0) {
			return null;
		}

		return trainPlanList;

	}

	public DatabaseService getDataBase() {
		return dataBase;
	}

	public void setDataBase(DatabaseService dataBase) {
		this.dataBase = dataBase;
	}

	public TrainItem getTrainItem() {
		return trainItem;
	}

	public void setTrainItem(TrainItem trainItem) {
		this.trainItem = trainItem;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public static Table getTable() {
		return table;
	}

	public static void setTable(Table table) {
		TaskTable.table = table;
	}

}
