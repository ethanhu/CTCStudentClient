package ctc.ctc.drawctc.station1.autosystem;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import ctc.ctc.drawctc.station1.data.*;
import ctc.ctc.drawctc.station1.elements.common.*;
import ctc.databaseserver.DatabaseService;
import ctc.pojobean.Plan;


public class TaskTable {

	BaseParam baseData = BaseParam.getInstance();	
	DatabaseService dataBase = DatabaseService.getInstance();
	AutoSystem autoSystem = AutoSystem.getInstance();

	public TrainItem trainItem;
	public Task task;

	private static TaskTable taskTable = null;

	public static TaskTable getInstance() {
		if (taskTable == null) {
			taskTable = new TaskTable();
		}
		return taskTable;
	}

	public void setTableContents() {

		Table table = new Table(baseData.getTextShell(), SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		int width = baseData.getTextShell().getBounds().width;
		int height = baseData.getTextShell().getBounds().height;
		table.setSize(table.computeSize(600, height - 40));
		table.setLocation(baseData.getTextShell().getBounds().width - 650, 0);

		String[] titles = { "车次", "状态", "方向", "类型", "计划时间", "实际时间", "股道", "起始按钮", "终端按钮" };
		for (int i = 0; i < titles.length; i++) {
			TableColumn column = new TableColumn(table, SWT.NONE);
			column.setText(titles[i]);
		}

		List<Plan> trainPlanList = getPlanList();
		if (trainPlanList == null || trainPlanList.size() == 0) {
			return;
		}

		int trainNum = trainPlanList.size();

		String[] track = new String[] { "1G", "2G", "3G", "4G", "6G" };
		String[] xla = new String[] { "S1LA", "S2LA", "S3LA", "S4LA", "S6LA" }; // 下行接车
		String[] xlfa = new String[] { "X1LA", "X2LA", "X3LA", "X4LA", "X6LA" }; // 下行发车
		String[] sfa = new String[] { "X1LA", "X2LA", "X3LA", "X4LA", "X6LA" }; // 上行接车
		String[] slfa = new String[] { "S1LA", "S2LA", "S3LA", "S4LA", "S6LA" }; // 上行发车

		String startName;
		String endName;

		for (int i = 0; i < trainNum; i++) {
			Plan plan = trainPlanList.get(i);

			// 下行发车
			if (plan.getPlan_arrivestationtime().equalsIgnoreCase("00:00:00")) {

				startName = xlfa[i % 5];
				endName = "XLFA";

				trainItem = new TrainItem(plan.getTrain_name(), table);
				trainItem.getItem().setText(0, plan.getTrain_name());
				trainItem.getItem().setText(1, "等待执行");
				trainItem.getItem().setText(2, "下行");
				trainItem.getItem().setText(3, "发车");
				trainItem.getItem().setText(4, plan.getPlan_leavestationtime());
				trainItem.getItem().setText(5, "");
				trainItem.getItem().setText(6, track[i % 5]);
				trainItem.getItem().setText(7, startName);
				trainItem.getItem().setText(8, endName);
				autoSystem.getTrainList().add(trainItem);

				// 任务信息
				task = new Task();
				task.setTime(plan.getPlan_leavestationtime());
				task.setTrainName(plan.getTrain_name());
				task.setStartName(startName);
				task.setEndName(endName);
				autoSystem.getTaskList().add(task);

			} else if (plan.getPlan_leavestationtime().equalsIgnoreCase("00:00:00")) {

				startName = "XLA";
				endName = xla[i % 5];

				// 接车
				trainItem = new TrainItem(plan.getTrain_name(), table);
				trainItem.getItem().setText(0, plan.getTrain_name());
				trainItem.getItem().setText(1, "等待执行");
				trainItem.getItem().setText(2, "下行");
				trainItem.getItem().setText(3, "接车");
				trainItem.getItem().setText(4, plan.getPlan_arrivestationtime());
				trainItem.getItem().setText(5, "");
				trainItem.getItem().setText(6, track[i % 5]);
				trainItem.getItem().setText(7, startName);
				trainItem.getItem().setText(8, endName);
				autoSystem.getTrainList().add(trainItem);

				// 任务信息
				task = new Task();
				task.setTime(plan.getPlan_arrivestationtime());
				task.setTrainName(plan.getTrain_name());
				task.setStartName(startName);
				task.setEndName(endName);
				autoSystem.getTaskList().add(task);

			} else {

				startName = "XLA";
				endName = xla[i % 5];

				// 接车
				trainItem = new TrainItem(plan.getTrain_name(), table);
				trainItem.getItem().setText(0, plan.getTrain_name());
				trainItem.getItem().setText(1, "等待执行");
				trainItem.getItem().setText(2, "下行");
				trainItem.getItem().setText(3, "接车");
				trainItem.getItem().setText(4, plan.getPlan_arrivestationtime());
				trainItem.getItem().setText(5, "");
				trainItem.getItem().setText(6, track[i % 5]);
				trainItem.getItem().setText(7, "XLA");
				trainItem.getItem().setText(8, xla[i % 5]);
				autoSystem.getTrainList().add(trainItem);

				// 任务信息
				task = new Task();
				task.setTime(plan.getPlan_arrivestationtime());
				task.setTrainName(plan.getTrain_name());
				task.setStartName(startName);
				task.setEndName(endName);
				autoSystem.getTaskList().add(task);

				startName = xlfa[i % 5];
				endName = "XLFA";

				// 发车
				trainItem = new TrainItem(plan.getTrain_name(), table);
				trainItem.getItem().setText(0, plan.getTrain_name());
				trainItem.getItem().setText(1, "等待执行");
				trainItem.getItem().setText(2, "下行");
				trainItem.getItem().setText(3, "发车");
				trainItem.getItem().setText(4, plan.getPlan_leavestationtime());
				trainItem.getItem().setText(5, "");
				trainItem.getItem().setText(6, track[i % 5]);
				trainItem.getItem().setText(7, startName);
				trainItem.getItem().setText(8, endName);
				autoSystem.getTrainList().add(trainItem);

				// 任务信息
				task = new Task();
				task.setTime(plan.getPlan_leavestationtime());
				task.setTrainName(plan.getTrain_name());
				task.setStartName(startName);
				task.setEndName(endName);
				autoSystem.getTaskList().add(task);

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

		List<Plan> trainPlanList = null;
		if(ctc.ctc.drawctc.ctcmain.data.BaseParam.getInstance().getStationPlanList() != null && ctc.ctc.drawctc.ctcmain.data.BaseParam.getInstance().getStationPlanList().size() != 0){
			
			trainPlanList = ctc.ctc.drawctc.ctcmain.data.BaseParam.getInstance().getStationPlanList().get(0).getTaskList();			
		}
		
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

}
