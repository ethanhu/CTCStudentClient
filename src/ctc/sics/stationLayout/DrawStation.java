package ctc.sics.stationLayout;

import java.util.ArrayList;

import org.eclipse.draw2d.Button;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import ctc.sics.figure.*;
import ctc.sics.road.*;

public class DrawStation {

	public static Shell mainShell;
	public static Composite shell;
	public static Font font1;
	public static Font font2;
	public static Font font3;

	public static Figure panel; // 绘图容器
	public static int shellHeight; // shell的高度
	public static int shellWidth; // shell的宽度

	public static int rowSpacing = 50; // 股道间距
	public static int turnoutLength = 10; // 道岔拐角处的伸出长度
	public static int lineWidth = 2; // 股道线宽度
	public static int semaphoreLightDiameter = 16; // 信号机灯的直径
	public static int buttonLength = 15; // 按钮的边长
	public static int labelWidth = 20; // label的宽度
	public static int labelHeight = 8; // label的高度

	public static Color trackInitColor = ColorConstants.blue; // 股道线初始颜色
	public static Color buttonInitColor = ColorConstants.gray; //按钮初始颜色
	public static Color buttonClickColor = ColorConstants.green; //按钮初始颜色
		
	public static StationButton stationButton; // 操作按钮
	public static ArrayList<StationButton> stationButtonList = new ArrayList<StationButton>(
			100);

	public static StationLabel stationLabel; // 文字label
	public static ArrayList<StationLabel> stationLabelList = new ArrayList<StationLabel>(
			100);

	//----------------信号机------------------//
	public static SemaphoreDoubleL semaphoreDoubleL; // 开口向左的信号机(双灯)
	public static ArrayList<SemaphoreDoubleL> semaphoreDoubleLList = new ArrayList<SemaphoreDoubleL>();
	public static SemaphoreDoubleR semaphoreDoubleR; // 开口向左的信号机(双灯)
	public static ArrayList<SemaphoreDoubleR> semaphoreDoubleRList = new ArrayList<SemaphoreDoubleR>();
	public static SemaphoreSimpleL semaphoreSimpleL; // 开口向左的信号机(单灯)
	public static ArrayList<SemaphoreSimpleL> semaphoreSimpleLList = new ArrayList<SemaphoreSimpleL>();
	public static SemaphoreSimpleR semaphoreSimpleR; // 开口向右的信号机(单灯)
	public static ArrayList<SemaphoreSimpleR> semaphoreSimpleRList = new ArrayList<SemaphoreSimpleR>();
	public static SemphoreBasicInfo semphoreBasicInfo; //信号及基本信息
	public static ArrayList<SemphoreBasicInfo> semphoreBasicInfoList = new ArrayList<SemphoreBasicInfo>();
	
	//----------------------------------//

	public static TrackLine trackLineSLA1; //上行第一接近
	public static TrackLine trackLineSLA2; //上行第二接近
	public static TrackLine trackLineSLFA1; //上行第一离去
	public static TrackLine trackLineSLFA2; //上行第二离去
	public static TrackLine trackLineXLA1; //下行第一接近 
	public static TrackLine trackLineXLA2; //下行第二接近
	public static TrackLine trackLineXLFA1; //下行第一接近离去 
	public static TrackLine trackLineXLFA2; //下行第二接近离去
	
	public static TrackLine trackLine; // 股道线
	public static ArrayList<TrackLine> trackLineList = new ArrayList<TrackLine>();
	public static TurnoutLS turnoutLS; // 左上道岔
	public static ArrayList<TurnoutLS> turnoutLSList = new ArrayList<TurnoutLS>();
	public static TurnoutLX turnoutLX; // 左下道岔
	public static ArrayList<TurnoutLX> turnoutLXList = new ArrayList<TurnoutLX>();	
	public static TurnoutRS turnoutRS; // 右上道岔
	public static ArrayList<TurnoutRS> turnoutRSList = new ArrayList<TurnoutRS>();
	public static TurnoutRX turnoutRX; // 右下道岔
	public static ArrayList<TurnoutRX> turnoutRXList = new ArrayList<TurnoutRX>();
	//public static TurnoutDoubleR turnoutDoubleR; // 右倾斜道岔
	//public static ArrayList<TurnoutDoubleR> turnoutDoubleRList = new ArrayList<TurnoutDoubleR>();
	//public static TurnoutDoubleL turnoutDoubleL; // 左倾斜道岔
	//public static ArrayList<TurnoutDoubleL> turnoutDoubleLList = new ArrayList<TurnoutDoubleL>();
	public static TurnoutDouble turnoutDouble; // 双开道岔
	public static ArrayList<TurnoutDouble> turnoutDoubleList = new ArrayList<TurnoutDouble>();

	public static RoadBasicInfo roadBasicInfo = new RoadBasicInfo(); //存储路段基本信息，包括路段信息类型和名称
	public static String roadBasicInfoTypeOfTrackLine = "TRACKLINE";
	public static String roadBasicInfoTypeOfTurnoutLS = "TURNOUTLS";
	public static String roadBasicInfoTypeOfTurnoutLX = "TURNOUTLX";
	public static String roadBasicInfoTypeOfTurnoutRS = "TURNOUTRS";
	public static String roadBasicInfoTypeOfTurnoutRX = "TURNOUTRX";
	//public static String roadBasicInfoTypeOfTurnoutDoubleR = "TURNOUTDOUBLER";
	//public static String roadBasicInfoTypeOfTurnoutDoubleL = "TURNOUTDOUBLEL";
	public static String roadBasicInfoTypeOfTurnoutDouble = "TURNOUTDOUBLE";
	
	//-------- 车站连锁-路径（排序前）------------//
	public static ArrayList<RoadBasicInfo> road_xla_x1la = new ArrayList<RoadBasicInfo>(); // 1道的路径(下行进站)
	public static ArrayList<RoadBasicInfo> road_x1la_xlfa = new ArrayList<RoadBasicInfo>(); // 1道的路径(下行出站)	
	public static ArrayList<RoadBasicInfo> road_xla_x2la = new ArrayList<RoadBasicInfo>(); // 2道的路径(下行进站)
	public static ArrayList<RoadBasicInfo> road_x2la_xlfa = new ArrayList<RoadBasicInfo>(); // 2道的路径(下行出站)	
	public static ArrayList<RoadBasicInfo> road_xla_x3la = new ArrayList<RoadBasicInfo>(); // 3道的路径(下行进站)
	public static ArrayList<RoadBasicInfo> road_x3la_xlfa = new ArrayList<RoadBasicInfo>(); // 3道的路径(下行出站)	
	public static ArrayList<RoadBasicInfo> road_xla_x4la = new ArrayList<RoadBasicInfo>(); // 4道的路径(下行进站)
	public static ArrayList<RoadBasicInfo> road_x4la_xlfa = new ArrayList<RoadBasicInfo>(); // 4道的路径(下行出站)	
	public static ArrayList<RoadBasicInfo> road_sla_s1la = new ArrayList<RoadBasicInfo>(); // 1道的路径(上行进站)
	public static ArrayList<RoadBasicInfo> road_s1la_slfa = new ArrayList<RoadBasicInfo>(); // 1道的路径(上行出站)	
	public static ArrayList<RoadBasicInfo> road_sla_s2la = new ArrayList<RoadBasicInfo>(); // 2道的路径(上行进站)
	public static ArrayList<RoadBasicInfo> road_s2la_slfa = new ArrayList<RoadBasicInfo>(); // 2道的路径(上行出站)	
	public static ArrayList<RoadBasicInfo> road_sla_s3la = new ArrayList<RoadBasicInfo>(); // 3道的路径(上行进站)
	public static ArrayList<RoadBasicInfo> road_s3la_slfa = new ArrayList<RoadBasicInfo>(); // 3道的路径(上行出站)	
	public static ArrayList<RoadBasicInfo> road_sla_s4la = new ArrayList<RoadBasicInfo>(); // 4道的路径(上行进站)
	public static ArrayList<RoadBasicInfo> road_s4la_slfa = new ArrayList<RoadBasicInfo>(); // 4道的路径(上行出站)	
	public static ArrayList<RoadBasicInfo> road_xta_xlfa = new ArrayList<RoadBasicInfo>(); // 下行通过路径
	public static ArrayList<RoadBasicInfo> road_sta_slfa = new ArrayList<RoadBasicInfo>(); // 上行通过路径	
	//-------- 车站连锁-路径（排序后）------------//
	public static ArrayList<RoadBasicInfo> r_xla_x1la = new ArrayList<RoadBasicInfo>(); // 1道的路径(下行进站)
	public static ArrayList<RoadBasicInfo> r_x1la_xlfa = new ArrayList<RoadBasicInfo>(); // 1道的路径(下行出站)	
	public static ArrayList<RoadBasicInfo> r_xla_x2la = new ArrayList<RoadBasicInfo>(); // 2道的路径(下行进站)
	public static ArrayList<RoadBasicInfo> r_x2la_xlfa = new ArrayList<RoadBasicInfo>(); // 2道的路径(下行出站)	
	public static ArrayList<RoadBasicInfo> r_xla_x3la = new ArrayList<RoadBasicInfo>(); // 3道的路径(下行进站)
	public static ArrayList<RoadBasicInfo> r_x3la_xlfa = new ArrayList<RoadBasicInfo>(); // 3道的路径(下行出站)	
	public static ArrayList<RoadBasicInfo> r_xla_x4la = new ArrayList<RoadBasicInfo>(); // 4道的路径(下行进站)
	public static ArrayList<RoadBasicInfo> r_x4la_xlfa = new ArrayList<RoadBasicInfo>(); // 4道的路径(下行出站)	
	public static ArrayList<RoadBasicInfo> r_sla_s1la = new ArrayList<RoadBasicInfo>(); // 1道的路径(上行进站)
	public static ArrayList<RoadBasicInfo> r_s1la_slfa = new ArrayList<RoadBasicInfo>(); // 1道的路径(上行出站)	
	public static ArrayList<RoadBasicInfo> r_sla_s2la = new ArrayList<RoadBasicInfo>(); // 2道的路径(上行进站)
	public static ArrayList<RoadBasicInfo> r_s2la_slfa = new ArrayList<RoadBasicInfo>(); // 2道的路径(上行出站)	
	public static ArrayList<RoadBasicInfo> r_sla_s3la = new ArrayList<RoadBasicInfo>(); // 3道的路径(上行进站)
	public static ArrayList<RoadBasicInfo> r_s3la_slfa = new ArrayList<RoadBasicInfo>(); // 3道的路径(上行出站)	
	public static ArrayList<RoadBasicInfo> r_sla_s4la = new ArrayList<RoadBasicInfo>(); // 4道的路径(上行进站)
	public static ArrayList<RoadBasicInfo> r_s4la_slfa = new ArrayList<RoadBasicInfo>(); // 4道的路径(上行出站)	
	public static ArrayList<RoadBasicInfo> r_xta_xlfa = new ArrayList<RoadBasicInfo>(); // 下行通过路径
	public static ArrayList<RoadBasicInfo> r_sta_slfa = new ArrayList<RoadBasicInfo>(); // 上行通过路径	
	
	public static int r_preRoadType; //类型
	public static ArrayList<RoadBasicInfo> r_preRoad; //上一步的语句	
	
	public static ArrayList<ArrayList<RoadBasicInfo>> roadList = new ArrayList<ArrayList<RoadBasicInfo>>();
	public static ArrayList<ArrayList<RoadBasicInfo>> roadSortList = new ArrayList<ArrayList<RoadBasicInfo>>();
	public static ArrayList<int[]> roadIDList = new ArrayList<int[]>();
	//-------buton的状态-------//
	//上下行接发车按钮
	public static Button button_xta;
	public static int b_xta = 0;
	public static Button button_xla;
	public static int b_xla = 0;
	public static Button button_xlfa;
	public static int b_xlfa = 0;
	public static Button button_sta;
	public static int b_sta = 0;
	public static Button button_sla;
	public static int b_sla = 0;
	public static Button button_slfa;
	public static int b_slfa = 0;
	//各个股道按钮
	public static Button button_x1la;
	public static int b_x1la = 0;
	public static Button button_x2la;
	public static int b_x2la = 0;
	public static Button button_x3la;
	public static int b_x3la = 0;
	public static Button button_x4la;
	public static int b_x4la = 0;
	public static Button button_s1la;
	public static int b_s1la = 0;
	public static Button button_s2la;
	public static int b_s2la = 0;
	public static Button button_s3la;
	public static int b_s3la = 0;
	public static Button button_s4la;
	public static int b_s4la = 0;

}
