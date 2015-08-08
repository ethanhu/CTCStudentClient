package ctc.ctc.drawctc.ctcmain.data;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

import ctc.pojobean.Plan;
import ctc.pojobean.TaskPlan;
import ctc.util.JsonUtil;

public class BaseParam {

	private static BaseParam baseData = null;

	public static BaseParam getInstance() {
		if (baseData == null) {
			baseData = new BaseParam();
		}
		return baseData;
	}

	private static Shell mainShell;
	public static Composite ctcCom; // ctc的主容器
	public static Composite ctcOperateCom; // ctc的操作部分容器
	public static Composite ctcTaskCom; // ctc的任务部分容器

	public static Composite ctcTaskCom1; // ctc的任务部分容器1
	public static Composite ctcTaskCom2; // ctc的任务部分容器2
	public static Composite ctcTaskCom3; // ctc的任务部分容器3
	public static Composite ctcTaskCom4; // ctc的任务部分容器4
	public static Composite ctcTaskCom5; // ctc的任务部分容器5
	public static Composite ctcTaskCom6; // ctc的任务部分容器6
	
	private static Figure msgTextPanel = new Figure(); // 消息容器
	private static Text msgLabel; // 操作记录Label
	private static org.eclipse.swt.widgets.Label timeLabel; // 时间label
	
	public static ArrayList<StationPlan> stationPlanList = new ArrayList<StationPlan>();
	public static List<TaskPlan> taskList = new ArrayList<TaskPlan>();
	public static List<TaskPlan> taskList1 = new ArrayList<TaskPlan>();
	public static List<TaskPlan> taskList2 = new ArrayList<TaskPlan>();
	public static List<TaskPlan> taskList3 = new ArrayList<TaskPlan>();
	public static List<TaskPlan> taskList4 = new ArrayList<TaskPlan>();
	public static List<TaskPlan> taskList5 = new ArrayList<TaskPlan>();	
	
	public static Composite station1Com; // 车站1容器
	public static Composite station2Com; // 车站2容器
	public static Composite station3Com; // 车站3容器

	public static String distictName = "呼和浩特-大同";
	public static String stationName1 = "呼和浩特";
	public static String stationName2 = "集宁";
	public static String stationName3 = "大同";

	public static int labWidth = 14;
	public static int labHeight = 8;

	private static Font font1 = new Font(Display.getDefault(), "Times New Roman", 7, SWT.NORMAL);
	private static Font font2 = new Font(Display.getDefault(), "Times New Roman", 15, SWT.NORMAL);
	private static Font font3 = new Font(Display.getDefault(), "Arabic Transparent", 10, SWT.NORMAL);
	private static Font font4 = new Font(Display.getDefault(), "Times New Roman", 8, SWT.NORMAL);

	public static List<TaskPlan> getTaskList() {
		return taskList;
	}

	public static void setTaskList(List<TaskPlan> taskList) {
		BaseParam.taskList = taskList;
	}

	public static List<TaskPlan> getTaskList1() {
		return taskList1;
	}

	public static void setTaskList1(List<TaskPlan> taskList1) {
		BaseParam.taskList1 = taskList1;
	}

	public static List<TaskPlan> getTaskList2() {
		return taskList2;
	}

	public static void setTaskList2(List<TaskPlan> taskList2) {
		BaseParam.taskList2 = taskList2;
	}

	public static List<TaskPlan> getTaskList3() {
		return taskList3;
	}

	public static void setTaskList3(List<TaskPlan> taskList3) {
		BaseParam.taskList3 = taskList3;
	}

	public static List<TaskPlan> getTaskList4() {
		return taskList4;
	}

	public static void setTaskList4(List<TaskPlan> taskList4) {
		BaseParam.taskList4 = taskList4;
	}

	public static List<TaskPlan> getTaskList5() {
		return taskList5;
	}

	public static void setTaskList5(List<TaskPlan> taskList5) {
		BaseParam.taskList5 = taskList5;
	}

	public static Figure getMsgTextPanel() {
		return msgTextPanel;
	}

	public static void setMsgTextPanel(Figure msgTextPanel) {
		BaseParam.msgTextPanel = msgTextPanel;
	}

	public static Text getMsgLabel() {
		return msgLabel;
	}

	public static void setMsgLabel(Text msgLabel) {
		BaseParam.msgLabel = msgLabel;
	}

	public static org.eclipse.swt.widgets.Label getTimeLabel() {
		return timeLabel;
	}

	public static void setTimeLabel(org.eclipse.swt.widgets.Label timeLabel) {
		BaseParam.timeLabel = timeLabel;
	}

	public static ArrayList<StationPlan> getStationPlanList() {
		return stationPlanList;
	}

	public static void setStationPlanList(ArrayList<StationPlan> stationPlanList) {
		BaseParam.stationPlanList = stationPlanList;
	}

	public static Composite getCtcTaskCom6() {
		return ctcTaskCom6;
	}

	public static void setCtcTaskCom6(Composite ctcTaskCom6) {
		BaseParam.ctcTaskCom6 = ctcTaskCom6;
	}

	public static Composite getCtcTaskCom1() {
		return ctcTaskCom1;
	}

	public static void setCtcTaskCom1(Composite ctcTaskCom1) {
		BaseParam.ctcTaskCom1 = ctcTaskCom1;
	}

	public static Composite getCtcTaskCom2() {
		return ctcTaskCom2;
	}

	public static void setCtcTaskCom2(Composite ctcTaskCom2) {
		BaseParam.ctcTaskCom2 = ctcTaskCom2;
	}

	public static Composite getCtcTaskCom3() {
		return ctcTaskCom3;
	}

	public static void setCtcTaskCom3(Composite ctcTaskCom3) {
		BaseParam.ctcTaskCom3 = ctcTaskCom3;
	}

	public static Composite getCtcTaskCom4() {
		return ctcTaskCom4;
	}

	public static void setCtcTaskCom4(Composite ctcTaskCom4) {
		BaseParam.ctcTaskCom4 = ctcTaskCom4;
	}

	public static Composite getCtcTaskCom5() {
		return ctcTaskCom5;
	}

	public static void setCtcTaskCom5(Composite ctcTaskCom5) {
		BaseParam.ctcTaskCom5 = ctcTaskCom5;
	}

	public static int getLabWidth() {
		return labWidth;
	}

	public static void setLabWidth(int labWidth) {
		BaseParam.labWidth = labWidth;
	}

	public static int getLabHeight() {
		return labHeight;
	}

	public static void setLabHeight(int labHeight) {
		BaseParam.labHeight = labHeight;
	}

	public static Font getFont1() {
		return font1;
	}

	public static void setFont1(Font font1) {
		BaseParam.font1 = font1;
	}

	public static Font getFont2() {
		return font2;
	}

	public static void setFont2(Font font2) {
		BaseParam.font2 = font2;
	}

	public static Font getFont3() {
		return font3;
	}

	public static void setFont3(Font font3) {
		BaseParam.font3 = font3;
	}

	public static Font getFont4() {
		return font4;
	}

	public static void setFont4(Font font4) {
		BaseParam.font4 = font4;
	}

	public static Composite getCtcOperateCom() {
		return ctcOperateCom;
	}

	public static void setCtcOperateCom(Composite ctcOperateCom) {
		BaseParam.ctcOperateCom = ctcOperateCom;
	}

	public static Composite getCtcTaskCom() {
		return ctcTaskCom;
	}

	public static void setCtcTaskCom(Composite ctcTaskCom) {
		BaseParam.ctcTaskCom = ctcTaskCom;
	}

	public static Shell getMainShell() {
		return mainShell;
	}

	public static void setMainShell(Shell mainShell) {
		BaseParam.mainShell = mainShell;
	}

	public static Composite getCtcCom() {
		return ctcCom;
	}

	public static void setCtcCom(Composite ctcCom) {
		BaseParam.ctcCom = ctcCom;
	}

	public static Composite getStation1Com() {
		return station1Com;
	}

	public static void setStation1Com(Composite station1Com) {
		BaseParam.station1Com = station1Com;
	}

	public static Composite getStation2Com() {
		return station2Com;
	}

	public static void setStation2Com(Composite station2Com) {
		BaseParam.station2Com = station2Com;
	}

	public static Composite getStation3Com() {
		return station3Com;
	}

	public static void setStation3Com(Composite station3Com) {
		BaseParam.station3Com = station3Com;
	}

	public static String getDistictName() {
		return distictName;
	}

	public static void setDistictName(String distictName) {
		BaseParam.distictName = distictName;
	}

	public static String getStationName1() {
		return stationName1;
	}

	public static void setStationName1(String stationName1) {
		BaseParam.stationName1 = stationName1;
	}

	public static String getStationName2() {
		return stationName2;
	}

	public static void setStationName2(String stationName2) {
		BaseParam.stationName2 = stationName2;
	}

	public static String getStationName3() {
		return stationName3;
	}

	public static void setStationName3(String stationName3) {
		BaseParam.stationName3 = stationName3;
	}

}
