package ctc.sics.stationLayout;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Point;

import ctc.sics.figure.*;
import ctc.sics.road.*;
import ctc.sics.stationLayout.buttonListener.ButtonS1LAListener;
import ctc.sics.stationLayout.buttonListener.*;

public class DrawStationGraph {


	public static int trackLength = 50;
	public static int centerY = (DrawStation.shellHeight - 300) / 2 + 150;
	public static int centerX = DrawStation.shellWidth / 2;
	public static int ylength = 5;
	public static int xlength = 30;
	
	
	/**
	 * 绘制站型图
	 */
	public static void drawStationGraph() {
		
		
		// -----------股道3----------------//
		// 股道3
		DrawStation.stationLabel = new StationLabel("3", DrawStation.labelWidth, DrawStation.labelHeight,
				centerX - 8, centerY - 2 * DrawStation.rowSpacing - DrawStation.labelHeight - 5, DrawStation.panel);

		// 股道3-2
		DrawStation.trackLine = new TrackLine(trackLength, DrawStation.lineWidth, DrawStation.trackInitColor, centerX
				- 2 * trackLength, centerY - 2 * DrawStation.rowSpacing, DrawStation.panel);
		DrawStation.trackLine.setLineName("3-2");
		DrawStation.trackLineList.add(DrawStation.trackLine);
		
		DrawStation.roadBasicInfo = new RoadBasicInfo();
		DrawStation.roadBasicInfo.setRoadType(DrawStation.roadBasicInfoTypeOfTrackLine);
		DrawStation.roadBasicInfo.setRoadName("3-2");
		
		DrawStation.road_xla_x3la.add(DrawStation.roadBasicInfo);
		DrawStation.road_x3la_xlfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_sla_s3la.add(DrawStation.roadBasicInfo);
		DrawStation.road_s3la_slfa.add(DrawStation.roadBasicInfo);
		
		// 信号灯S3
		DrawStation.semaphoreSimpleL = new SemaphoreSimpleL("S3", DrawStation.semaphoreLightDiameter, centerX - trackLength, centerY - 2 * DrawStation.rowSpacing + ylength, DrawStation.panel);
		
		DrawStation.semaphoreSimpleLList.add(DrawStation.semaphoreSimpleL);
		DrawStation.semphoreBasicInfo = new SemphoreBasicInfo();
		DrawStation.semphoreBasicInfo.setSemphoreName("S3");
		DrawStation.semphoreBasicInfo.setSemphoreType("SemaphoreSimpleL");
		DrawStation.semphoreBasicInfoList.add(DrawStation.semphoreBasicInfo);
		
		DrawStation.stationLabel = new StationLabel("S3", DrawStation.labelWidth - 5, DrawStation.labelHeight,
				centerX - trackLength - DrawStation.labelWidth/2 - 3, centerY - 2*DrawStation.rowSpacing - DrawStation.semaphoreLightDiameter - ylength + 8, DrawStation.panel);

		// 按钮S3LA
		DrawStation.button_s3la = new StationButton(DrawStation.buttonLength, centerX - 5*trackLength/3
				- DrawStation.buttonLength/2, centerY - 2 * DrawStation.rowSpacing - 3*ylength/2,
				DrawStation.buttonInitColor).getButton();
		/*
		DrawStation.button_s3la.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//3道下行接车或3道上行发车
				StationModel.Button_s3laAction();					
			}
		});
		*/
		DrawStation.button_s3la.addActionListener(new ButtonS3LAListener());
		
		
		DrawStation.panel.add(DrawStation.button_s3la);
		DrawStation.stationLabel = new StationLabel("S3LA", DrawStation.labelWidth + 5, DrawStation.labelHeight,
				centerX - 2*trackLength + 5, centerY - 2 * DrawStation.rowSpacing
						+ ylength + 8, DrawStation.panel);

		// 道岔9
		DrawStation.turnoutLS = new TurnoutLS(DrawStation.rowSpacing, centerX
				- 2 * trackLength, centerY - 2 * DrawStation.rowSpacing, DrawStation.panel);
		DrawStation.turnoutLS.setTurnoutName("9");
		DrawStation.turnoutLSList.add(DrawStation.turnoutLS);
		DrawStation.roadBasicInfo = new RoadBasicInfo();
		DrawStation.roadBasicInfo.setRoadType(DrawStation.roadBasicInfoTypeOfTurnoutLS);
		DrawStation.roadBasicInfo.setRoadName("9");		
		//下行
		DrawStation.road_xla_x1la.add(DrawStation.roadBasicInfo);
		DrawStation.road_xla_x3la.add(DrawStation.roadBasicInfo);
		DrawStation.road_xta_xlfa.add(DrawStation.roadBasicInfo);
		//上行
		DrawStation.road_s1la_slfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_s3la_slfa.add(DrawStation.roadBasicInfo);
				
		DrawStation.stationLabel = new StationLabel("9", DrawStation.labelWidth / 2, DrawStation.labelHeight,
				centerX - 3 * trackLength - 8, centerY - DrawStation.rowSpacing
						- DrawStation.labelHeight - 5, DrawStation.panel);

		// 股道3-1
		DrawStation.trackLine = new TrackLine(trackLength, DrawStation.lineWidth, DrawStation.trackInitColor, centerX
				- trackLength, centerY - 2 * DrawStation.rowSpacing, DrawStation.panel);
		DrawStation.trackLine.setLineName("3-1");
		DrawStation.trackLineList.add(DrawStation.trackLine);
		DrawStation.roadBasicInfo = new RoadBasicInfo();
		DrawStation.roadBasicInfo.setRoadType(DrawStation.roadBasicInfoTypeOfTrackLine);
		DrawStation.roadBasicInfo.setRoadName("3-1");
		//下行
		DrawStation.road_xla_x3la.add(DrawStation.roadBasicInfo);
		DrawStation.road_x3la_xlfa.add(DrawStation.roadBasicInfo);
		//上行
		DrawStation.road_sla_s3la.add(DrawStation.roadBasicInfo);
		DrawStation.road_s3la_slfa.add(DrawStation.roadBasicInfo);
						
		// 股道3+1
		DrawStation.trackLine = new TrackLine(trackLength, DrawStation.lineWidth, DrawStation.trackInitColor, centerX,
				centerY - 2 * DrawStation.rowSpacing, DrawStation.panel);
		DrawStation.trackLine.setLineName("3+1");
		DrawStation.trackLineList.add(DrawStation.trackLine);
		DrawStation.roadBasicInfo = new RoadBasicInfo();
		DrawStation.roadBasicInfo.setRoadType(DrawStation.roadBasicInfoTypeOfTrackLine);
		DrawStation.roadBasicInfo.setRoadName("3+1");
		//下行
		DrawStation.road_xla_x3la.add(DrawStation.roadBasicInfo);
		DrawStation.road_x3la_xlfa.add(DrawStation.roadBasicInfo);
		//上行
		DrawStation.road_sla_s3la.add(DrawStation.roadBasicInfo);
		DrawStation.road_s3la_slfa.add(DrawStation.roadBasicInfo);
		
		// 股道3+2
		DrawStation.trackLine = new TrackLine(trackLength, DrawStation.lineWidth, DrawStation.trackInitColor, centerX
				+ trackLength, centerY - 2 * DrawStation.rowSpacing, DrawStation.panel);
		DrawStation.trackLine.setLineName("3+2");
		DrawStation.trackLineList.add(DrawStation.trackLine);
		DrawStation.roadBasicInfo = new RoadBasicInfo();
		DrawStation.roadBasicInfo.setRoadType(DrawStation.roadBasicInfoTypeOfTrackLine);
		DrawStation.roadBasicInfo.setRoadName("3+2");
		//下行
		DrawStation.road_xla_x3la.add(DrawStation.roadBasicInfo);
		DrawStation.road_x3la_xlfa.add(DrawStation.roadBasicInfo);
		//上行
		DrawStation.road_sla_s3la.add(DrawStation.roadBasicInfo);
		DrawStation.road_s3la_slfa.add(DrawStation.roadBasicInfo);
				
		// 信号灯X3
		DrawStation.semaphoreSimpleR = new SemaphoreSimpleR("X3", DrawStation.semaphoreLightDiameter, centerX
				+ trackLength, centerY - 2 * DrawStation.rowSpacing
				- DrawStation.semaphoreLightDiameter - ylength, DrawStation.panel);
		DrawStation.semaphoreSimpleRList.add(DrawStation.semaphoreSimpleR);
		DrawStation.semphoreBasicInfo = new SemphoreBasicInfo();
		DrawStation.semphoreBasicInfo.setSemphoreName("X3");
		DrawStation.semphoreBasicInfo.setSemphoreType("SemaphoreSimpleR");
		DrawStation.semphoreBasicInfoList.add(DrawStation.semphoreBasicInfo);
		
		DrawStation.stationLabel = new StationLabel(
				"X3",
				DrawStation.labelWidth,
				DrawStation.labelHeight,
				centerX + trackLength,
				centerY - 2 * DrawStation.rowSpacing + ylength,
				DrawStation.panel);

		// 按钮X3LA
		DrawStation.button_x3la = new StationButton(DrawStation.buttonLength, centerX + 3*trackLength/2, centerY - 2 * DrawStation.rowSpacing- 3*ylength/2,
				DrawStation.buttonInitColor).getButton();
		/*		
		DrawStation.button_x3la.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//3道上行接车或者3道下行发车
				StationModel.Button_x3laAction();
				
			}
		});
		*/
		DrawStation.button_x3la.addActionListener(new ButtonX3LAListener());
		
		DrawStation.panel.add(DrawStation.button_x3la);
		DrawStation.stationLabel = new StationLabel("X3LA", DrawStation.labelWidth + 5, DrawStation.labelHeight,
				centerX + 3*trackLength/2, 
				centerY - 2*DrawStation.rowSpacing -4*ylength, 
				DrawStation.panel);

		// 道岔10
		DrawStation.turnoutRS = new TurnoutRS(DrawStation.rowSpacing, centerX + 2 * trackLength, centerY - 2 * DrawStation.rowSpacing, DrawStation.panel);
		DrawStation.turnoutRS.setTurnoutName("10");
		DrawStation.turnoutRSList.add(DrawStation.turnoutRS);
		DrawStation.roadBasicInfo = new RoadBasicInfo();
		DrawStation.roadBasicInfo.setRoadType(DrawStation.roadBasicInfoTypeOfTurnoutRS);
		DrawStation.roadBasicInfo.setRoadName("10");		
		//下行
		DrawStation.road_x1la_xlfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_x3la_xlfa.add(DrawStation.roadBasicInfo);		
		DrawStation.road_xta_xlfa.add(DrawStation.roadBasicInfo);
		//上行
		DrawStation.road_sla_s1la.add(DrawStation.roadBasicInfo);
		DrawStation.road_sla_s3la.add(DrawStation.roadBasicInfo);
		
		DrawStation.stationLabel = new StationLabel("10", DrawStation.labelWidth, DrawStation.labelHeight, centerX
				+ 3 * trackLength, centerY - DrawStation.rowSpacing - DrawStation.labelHeight - 5,
				DrawStation.panel);


		//System.out.println("3 DrawStation.trackLineList: " + DrawStation.trackLineList.size());
		
				
		// -----------股道 I-------------//
		// 股道I
		DrawStation.stationLabel = new StationLabel("I", DrawStation.labelWidth, DrawStation.labelHeight,
				centerX - 8, centerY - DrawStation.rowSpacing - DrawStation.labelHeight - 5, DrawStation.panel);

		// 股道I-10
		DrawStation.trackLineXLA1 = new TrackLine(trackLength, DrawStation.lineWidth, DrawStation.trackInitColor, centerX
				- 10 * trackLength, centerY - DrawStation.rowSpacing, DrawStation.panel);
		DrawStation.trackLineXLA1.setLineName("I-10");
		DrawStation.trackLineList.add(DrawStation.trackLineXLA1);
		DrawStation.stationLabel = new StationLabel("第一接近", DrawStation.labelWidth + 30, DrawStation.labelHeight + 5,
				centerX	- 10 * trackLength, centerY - DrawStation.rowSpacing - 15, DrawStation.panel);
		
		// 股道I-9
		DrawStation.trackLineXLA2 = new TrackLine(trackLength, DrawStation.lineWidth, DrawStation.trackInitColor, centerX
				- 9 * trackLength, centerY - DrawStation.rowSpacing, DrawStation.panel);
		DrawStation.trackLineXLA2.setLineName("I-9");
		DrawStation.trackLineList.add(DrawStation.trackLineXLA2);
		DrawStation.stationLabel = new StationLabel("第二接近", DrawStation.labelWidth + 30, DrawStation.labelHeight + 5,
				centerX	- 9 * trackLength, centerY - DrawStation.rowSpacing - 15, DrawStation.panel);
		
		// 股道I-8
		DrawStation.trackLine = new TrackLine(trackLength, DrawStation.lineWidth, DrawStation.trackInitColor, centerX
				- 8 * trackLength, centerY - DrawStation.rowSpacing, DrawStation.panel);
		DrawStation.trackLine.setLineName("I-8");
		DrawStation.trackLineList.add(DrawStation.trackLine);
		DrawStation.roadBasicInfo = new RoadBasicInfo();
		DrawStation.roadBasicInfo.setRoadType(DrawStation.roadBasicInfoTypeOfTrackLine);
		DrawStation.roadBasicInfo.setRoadName("I-8");
		//下行
		DrawStation.road_xla_x1la.add(DrawStation.roadBasicInfo);
		DrawStation.road_xla_x2la.add(DrawStation.roadBasicInfo);
		DrawStation.road_xla_x3la.add(DrawStation.roadBasicInfo);
		DrawStation.road_xla_x4la.add(DrawStation.roadBasicInfo);
		DrawStation.road_xta_xlfa.add(DrawStation.roadBasicInfo);
		// 按钮XTA
		DrawStation.button_xta = new StationButton(DrawStation.buttonLength, 
				centerX - 7	* trackLength, 
				centerY - DrawStation.rowSpacing - DrawStation.buttonLength - ylength,
				DrawStation.buttonInitColor).getButton();
		/*
		DrawStation.button_xta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//下行通过按钮
				StationModel.Button_xtaAction();
			}
		});
		*/
		DrawStation.button_xta.addActionListener(new ButtonXTAListener());
		
		DrawStation.panel.add(DrawStation.button_xta);
		DrawStation.stationLabel = new StationLabel("XTA", DrawStation.labelWidth, DrawStation.labelHeight, 
				centerX	- 7 * trackLength - 3, 
				centerY - DrawStation.rowSpacing - DrawStation.buttonLength	- ylength - DrawStation.labelHeight - 5, 
				DrawStation.panel);

		// 按钮XLA
		DrawStation.button_xla = new StationButton(DrawStation.buttonLength, 
				centerX - 7*trackLength + DrawStation.buttonLength + ylength, 
				centerY - DrawStation.rowSpacing - DrawStation.buttonLength - ylength, DrawStation.buttonInitColor).getButton();
		/*
		DrawStation.button_xla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//下行接车按钮
				StationModel.Button_xlaAction();
			}
		});
		*/
		DrawStation.button_xla.addActionListener(new ButtonXLAListener());
		
		DrawStation.panel.add(DrawStation.button_xla);
		DrawStation.stationLabel = new StationLabel("XLA", DrawStation.labelWidth, DrawStation.labelHeight, 
				centerX	- 7 * trackLength + DrawStation.buttonLength + ylength, 
				centerY	- DrawStation.rowSpacing - DrawStation.buttonLength - ylength - DrawStation.labelHeight - 5, 
				DrawStation.panel);

		// 股道I-7
		DrawStation.trackLine = new TrackLine(trackLength, DrawStation.lineWidth, DrawStation.trackInitColor, centerX
				- 7 * trackLength, centerY - DrawStation.rowSpacing, DrawStation.panel);
		DrawStation.trackLine.setLineName("I-7");
		DrawStation.trackLineList.add(DrawStation.trackLine);
		DrawStation.roadBasicInfo = new RoadBasicInfo();
		DrawStation.roadBasicInfo.setRoadType(DrawStation.roadBasicInfoTypeOfTrackLine);
		DrawStation.roadBasicInfo.setRoadName("I-7");
		//下行
		DrawStation.road_xla_x1la.add(DrawStation.roadBasicInfo);
		DrawStation.road_xla_x2la.add(DrawStation.roadBasicInfo);
		DrawStation.road_xla_x3la.add(DrawStation.roadBasicInfo);
		DrawStation.road_xla_x4la.add(DrawStation.roadBasicInfo);
		DrawStation.road_xta_xlfa.add(DrawStation.roadBasicInfo);
		
		// 信号机XLX
		DrawStation.semaphoreDoubleR = new SemaphoreDoubleR("XLX", DrawStation.semaphoreLightDiameter, 
				centerX	- 8 * trackLength, 
				centerY - DrawStation.rowSpacing - DrawStation.semaphoreLightDiameter - ylength, 
				DrawStation.panel);		
		DrawStation.semaphoreDoubleRList.add(DrawStation.semaphoreDoubleR);
		DrawStation.semphoreBasicInfo = new SemphoreBasicInfo();
		DrawStation.semphoreBasicInfo.setSemphoreName("XLX");
		DrawStation.semphoreBasicInfo.setSemphoreType("SemaphoreDoubleR");
		DrawStation.semphoreBasicInfoList.add(DrawStation.semphoreBasicInfo);
		
		
		DrawStation.stationLabel = new StationLabel("XLX", DrawStation.labelWidth, DrawStation.labelHeight, 
				centerX	- 8 * trackLength + 10, 
				centerY - DrawStation.rowSpacing - DrawStation.buttonLength	- ylength - DrawStation.labelHeight - 5, 
				DrawStation.panel);

		// 股道I-6
		DrawStation.trackLine = new TrackLine(trackLength, DrawStation.lineWidth, DrawStation.trackInitColor, centerX
				- 6 * trackLength, centerY - DrawStation.rowSpacing, DrawStation.panel);
		DrawStation.trackLine.setLineName("I-6");
		DrawStation.trackLineList.add(DrawStation.trackLine);
		DrawStation.roadBasicInfo = new RoadBasicInfo();
		DrawStation.roadBasicInfo.setRoadType(DrawStation.roadBasicInfoTypeOfTrackLine);
		DrawStation.roadBasicInfo.setRoadName("I-6");
		//下行
		DrawStation.road_xla_x1la.add(DrawStation.roadBasicInfo);
		DrawStation.road_xla_x2la.add(DrawStation.roadBasicInfo);
		DrawStation.road_xla_x3la.add(DrawStation.roadBasicInfo);
		DrawStation.road_xla_x4la.add(DrawStation.roadBasicInfo);
		DrawStation.road_xta_xlfa.add(DrawStation.roadBasicInfo);
		
		/*
		// 股道I-5
		DrawStation.trackLine = new TrackLine(trackLength - 2 * DrawStation.turnoutLength, DrawStation.lineWidth,
				DrawStation.trackInitColor, centerX - 5 * trackLength + DrawStation.turnoutLength, centerY
						- DrawStation.rowSpacing, DrawStation.panel);
		DrawStation.trackLine.setLineName("I-5");
		DrawStation.trackLineList.add(DrawStation.trackLine);
		DrawStation.roadBasicInfo = new RoadBasicInfo();
		DrawStation.roadBasicInfo.setRoadType(DrawStation.roadBasicInfoTypeOfTrackLine);
		DrawStation.roadBasicInfo.setRoadName("I-5");
		//下行
		DrawStation.road_xla_x1la.add(DrawStation.roadBasicInfo);
		DrawStation.road_xla_x3la.add(DrawStation.roadBasicInfo);
		DrawStation.road_xta_xlfa.add(DrawStation.roadBasicInfo);
		*/
		
		// 股道I-4
		DrawStation.trackLine = new TrackLine(trackLength, DrawStation.lineWidth, DrawStation.trackInitColor, centerX
				- 4 * trackLength, centerY - DrawStation.rowSpacing, DrawStation.panel);
		DrawStation.trackLine.setLineName("I-4");
		DrawStation.trackLineList.add(DrawStation.trackLine);
		DrawStation.roadBasicInfo = new RoadBasicInfo();
		DrawStation.roadBasicInfo.setRoadType(DrawStation.roadBasicInfoTypeOfTrackLine);
		DrawStation.roadBasicInfo.setRoadName("I-4");
		//下行
		DrawStation.road_xla_x1la.add(DrawStation.roadBasicInfo);
		DrawStation.road_xla_x3la.add(DrawStation.roadBasicInfo);
		DrawStation.road_xta_xlfa.add(DrawStation.roadBasicInfo);
		//上行
		DrawStation.road_s1la_slfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_s3la_slfa.add(DrawStation.roadBasicInfo);		
		
		/*
		// 股道I-3
		DrawStation.trackLine = new TrackLine(trackLength - DrawStation.turnoutLength, DrawStation.lineWidth,
				DrawStation.trackInitColor, centerX - 3 * trackLength + DrawStation.turnoutLength, centerY
						- DrawStation.rowSpacing, DrawStation.panel);
		DrawStation.trackLine.setLineName("I-3");
		DrawStation.trackLineList.add(DrawStation.trackLine);
		
		DrawStation.roadBasicInfo = new RoadBasicInfo();
		DrawStation.roadBasicInfo.setRoadType(DrawStation.roadBasicInfoTypeOfTrackLine);
		DrawStation.roadBasicInfo.setRoadName("I-3");		
		
		//下行
		DrawStation.road_xla_x1la.add(DrawStation.roadBasicInfo);
		DrawStation.road_xta_xlfa.add(DrawStation.roadBasicInfo);
		//上行
		DrawStation.road_s1la_slfa.add(DrawStation.roadBasicInfo);
		*/
		
		// 股道I-2
		DrawStation.trackLine = new TrackLine(trackLength, DrawStation.lineWidth, DrawStation.trackInitColor, centerX
				- 2 * trackLength, centerY - DrawStation.rowSpacing, DrawStation.panel);
		DrawStation.trackLine.setLineName("I-2");
		DrawStation.trackLineList.add(DrawStation.trackLine);
		DrawStation.roadBasicInfo = new RoadBasicInfo();
		DrawStation.roadBasicInfo.setRoadType(DrawStation.roadBasicInfoTypeOfTrackLine);
		DrawStation.roadBasicInfo.setRoadName("I-2");
		//下行
		DrawStation.road_xla_x1la.add(DrawStation.roadBasicInfo);
		DrawStation.road_x1la_xlfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_xta_xlfa.add(DrawStation.roadBasicInfo);
		//上行
		DrawStation.road_sla_s1la.add(DrawStation.roadBasicInfo);
		DrawStation.road_s1la_slfa.add(DrawStation.roadBasicInfo);		
					
		// 信号机SI
		DrawStation.semaphoreSimpleL = new SemaphoreSimpleL("S1", DrawStation.semaphoreLightDiameter, centerX - trackLength, centerY - DrawStation.rowSpacing + ylength, DrawStation.panel);
		
		DrawStation.semaphoreSimpleLList.add(DrawStation.semaphoreSimpleL);
		
		DrawStation.semphoreBasicInfo = new SemphoreBasicInfo();
		DrawStation.semphoreBasicInfo.setSemphoreName("S1");
		DrawStation.semphoreBasicInfo.setSemphoreType("SemaphoreSimpleL");
		DrawStation.semphoreBasicInfoList.add(DrawStation.semphoreBasicInfo);
		
		
		DrawStation.stationLabel = new StationLabel("SI", DrawStation.labelWidth - 5, DrawStation.labelHeight,
				centerX - trackLength - DrawStation.labelWidth/2 - 3, centerY - DrawStation.rowSpacing - DrawStation.semaphoreLightDiameter - ylength + 8, DrawStation.panel);

		// 按钮SILA
		DrawStation.button_s1la = new StationButton(DrawStation.buttonLength, centerX - 5*trackLength/3
				- DrawStation.buttonLength/2, centerY - DrawStation.rowSpacing - 3*ylength/2,
				DrawStation.buttonInitColor).getButton();
		
		/*
		DrawStation.button_s1la.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//1道下行接车或者上行发车
				StationModel.Button_s1laAction();  
			}
		});
		*/
		DrawStation.button_s1la.addActionListener(new ButtonS1LAListener());
		
		DrawStation.panel.add(DrawStation.button_s1la);
		DrawStation.stationLabel = new StationLabel("SILA", DrawStation.labelWidth + 5, DrawStation.labelHeight,
				centerX - 2*trackLength + 4, centerY - DrawStation.rowSpacing
						+ ylength + 8, DrawStation.panel);

		// 股道I-1
		DrawStation.trackLine = new TrackLine(trackLength, DrawStation.lineWidth, DrawStation.trackInitColor, centerX
				- trackLength, centerY - DrawStation.rowSpacing, DrawStation.panel);
		DrawStation.trackLine.setLineName("I-1");
		DrawStation.trackLineList.add(DrawStation.trackLine);
		DrawStation.roadBasicInfo = new RoadBasicInfo();
		DrawStation.roadBasicInfo.setRoadType(DrawStation.roadBasicInfoTypeOfTrackLine);
		DrawStation.roadBasicInfo.setRoadName("I-1");
		//下行
		DrawStation.road_xla_x1la.add(DrawStation.roadBasicInfo);
		DrawStation.road_x1la_xlfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_xta_xlfa.add(DrawStation.roadBasicInfo);
		//上行
		DrawStation.road_sla_s1la.add(DrawStation.roadBasicInfo);
		DrawStation.road_s1la_slfa.add(DrawStation.roadBasicInfo);		
							
		// 股道I+1
		DrawStation.trackLine = new TrackLine(trackLength, DrawStation.lineWidth, DrawStation.trackInitColor, centerX,
				centerY - DrawStation.rowSpacing, DrawStation.panel);
		DrawStation.trackLine.setLineName("I+1");
		DrawStation.trackLineList.add(DrawStation.trackLine);
		DrawStation.roadBasicInfo = new RoadBasicInfo();
		DrawStation.roadBasicInfo.setRoadType(DrawStation.roadBasicInfoTypeOfTrackLine);
		DrawStation.roadBasicInfo.setRoadName("I+1");
		//下行
		DrawStation.road_xla_x1la.add(DrawStation.roadBasicInfo);
		DrawStation.road_x1la_xlfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_xta_xlfa.add(DrawStation.roadBasicInfo);
		//上行
		DrawStation.road_sla_s1la.add(DrawStation.roadBasicInfo);
		DrawStation.road_s1la_slfa.add(DrawStation.roadBasicInfo);		
						
		// 股道I+2
		DrawStation.trackLine = new TrackLine(trackLength, DrawStation.lineWidth, DrawStation.trackInitColor, centerX
				+ trackLength, centerY - DrawStation.rowSpacing, DrawStation.panel);
		DrawStation.trackLine.setLineName("I+2");
		DrawStation.trackLineList.add(DrawStation.trackLine);
		DrawStation.roadBasicInfo = new RoadBasicInfo();
		DrawStation.roadBasicInfo.setRoadType(DrawStation.roadBasicInfoTypeOfTrackLine);
		DrawStation.roadBasicInfo.setRoadName("I+2");
		//下行
		DrawStation.road_xla_x1la.add(DrawStation.roadBasicInfo);
		DrawStation.road_x1la_xlfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_xta_xlfa.add(DrawStation.roadBasicInfo);
		//上行
		DrawStation.road_sla_s1la.add(DrawStation.roadBasicInfo);
		DrawStation.road_s1la_slfa.add(DrawStation.roadBasicInfo);
						
		// 信号机XI
		DrawStation.semaphoreSimpleR = new SemaphoreSimpleR("X1", DrawStation.semaphoreLightDiameter, centerX
				+ trackLength, centerY - DrawStation.rowSpacing
				- DrawStation.semaphoreLightDiameter - ylength, DrawStation.panel);
		DrawStation.semaphoreSimpleRList.add(DrawStation.semaphoreSimpleR);
		
		DrawStation.semphoreBasicInfo = new SemphoreBasicInfo();
		DrawStation.semphoreBasicInfo.setSemphoreName("X1");
		DrawStation.semphoreBasicInfo.setSemphoreType("SemaphoreSimpleR");
		DrawStation.semphoreBasicInfoList.add(DrawStation.semphoreBasicInfo);
		
		DrawStation.stationLabel = new StationLabel("XI", DrawStation.labelWidth, DrawStation.labelHeight, centerX + trackLength,
				centerY - DrawStation.rowSpacing + ylength,	DrawStation.panel);

		// 按钮XILA
		DrawStation.button_x1la = new StationButton(DrawStation.buttonLength, centerX + 3*trackLength/2, centerY - DrawStation.rowSpacing - 3*ylength/2,
				DrawStation.buttonInitColor).getButton();
		
		/*
		DrawStation.button_x1la.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//1股道上行接车或者下行发车
				StationModel.Button_x1laAction();

			}
		});
		*/
		DrawStation.button_x1la.addActionListener(new ButtonX1LAListener());
		
		
		DrawStation.panel.add(DrawStation.button_x1la);
		DrawStation.stationLabel = new StationLabel("XILA", DrawStation.labelWidth + 5, DrawStation.labelHeight,
				centerX + 3*trackLength/2, 
				centerY - DrawStation.rowSpacing -4*ylength, 
				DrawStation.panel);

		/*
		// 股道I+3
		DrawStation.trackLine = new TrackLine(trackLength - DrawStation.turnoutLength, DrawStation.lineWidth,
				DrawStation.trackInitColor, centerX + 2 * trackLength, centerY - DrawStation.rowSpacing,
				DrawStation.panel);
		DrawStation.trackLine.setLineName("I+3");
		DrawStation.trackLineList.add(DrawStation.trackLine);
		DrawStation.roadBasicInfo = new RoadBasicInfo();
		DrawStation.roadBasicInfo.setRoadType(DrawStation.roadBasicInfoTypeOfTrackLine);
		DrawStation.roadBasicInfo.setRoadName("I+3");
		//下行
		DrawStation.road_x1la_xlfa.add(DrawStation.roadBasicInfo);			
		DrawStation.road_xta_xlfa.add(DrawStation.roadBasicInfo);
		//上行
		DrawStation.road_sla_s1la.add(DrawStation.roadBasicInfo);
		*/
		
		// 股道I+4
		DrawStation.trackLine = new TrackLine(trackLength, DrawStation.lineWidth, DrawStation.trackInitColor, centerX
				+ 3 * trackLength, centerY - DrawStation.rowSpacing, DrawStation.panel);
		DrawStation.trackLine.setLineName("I+4");
		DrawStation.trackLineList.add(DrawStation.trackLine);
		DrawStation.roadBasicInfo = new RoadBasicInfo();
		DrawStation.roadBasicInfo.setRoadType(DrawStation.roadBasicInfoTypeOfTrackLine);
		DrawStation.roadBasicInfo.setRoadName("I+4");
		//下行
		DrawStation.road_x1la_xlfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_x3la_xlfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_xta_xlfa.add(DrawStation.roadBasicInfo);
		//上行
		DrawStation.road_sla_s1la.add(DrawStation.roadBasicInfo);
		DrawStation.road_sla_s3la.add(DrawStation.roadBasicInfo);
		
		/*
		// 股道I+5
		DrawStation.trackLine = new TrackLine(trackLength - 2 * DrawStation.turnoutLength, DrawStation.lineWidth,
				DrawStation.trackInitColor, centerX + 4 * trackLength + DrawStation.turnoutLength, centerY
						- DrawStation.rowSpacing, DrawStation.panel);
		DrawStation.trackLine.setLineName("I+5");
		DrawStation.trackLineList.add(DrawStation.trackLine);
		DrawStation.roadBasicInfo = new RoadBasicInfo();
		DrawStation.roadBasicInfo.setRoadType(DrawStation.roadBasicInfoTypeOfTrackLine);
		DrawStation.roadBasicInfo.setRoadName("I+5");
		//下行
		DrawStation.road_x1la_xlfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_x3la_xlfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_xta_xlfa.add(DrawStation.roadBasicInfo);
		*/
		
		// 股道I+6
		DrawStation.trackLine = new TrackLine(trackLength, DrawStation.lineWidth, DrawStation.trackInitColor, centerX
				+ 5 * trackLength, centerY - DrawStation.rowSpacing, DrawStation.panel);
		DrawStation.trackLine.setLineName("I+6");
		DrawStation.trackLineList.add(DrawStation.trackLine);
		DrawStation.roadBasicInfo = new RoadBasicInfo();
		DrawStation.roadBasicInfo.setRoadType(DrawStation.roadBasicInfoTypeOfTrackLine);
		DrawStation.roadBasicInfo.setRoadName("I+6");
		//下行
		DrawStation.road_x1la_xlfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_x2la_xlfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_x3la_xlfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_x4la_xlfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_xta_xlfa.add(DrawStation.roadBasicInfo);
		// 股道I+7
		DrawStation.trackLine = new TrackLine(trackLength, DrawStation.lineWidth, DrawStation.trackInitColor, centerX
				+ 6 * trackLength, centerY - DrawStation.rowSpacing, DrawStation.panel);
		DrawStation.trackLine.setLineName("I+7");
		DrawStation.trackLineList.add(DrawStation.trackLine);
		DrawStation.roadBasicInfo = new RoadBasicInfo();
		DrawStation.roadBasicInfo.setRoadType(DrawStation.roadBasicInfoTypeOfTrackLine);
		DrawStation.roadBasicInfo.setRoadName("I+7");
		//下行
		DrawStation.road_x1la_xlfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_x2la_xlfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_x3la_xlfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_x4la_xlfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_xta_xlfa.add(DrawStation.roadBasicInfo);
		
		// 股道I+8
		DrawStation.trackLine = new TrackLine(trackLength, DrawStation.lineWidth, DrawStation.trackInitColor, centerX
				+ 7 * trackLength, centerY - DrawStation.rowSpacing, DrawStation.panel);
		DrawStation.trackLine.setLineName("I+8");
		DrawStation.trackLineList.add(DrawStation.trackLine);
		DrawStation.roadBasicInfo = new RoadBasicInfo();
		DrawStation.roadBasicInfo.setRoadType(DrawStation.roadBasicInfoTypeOfTrackLine);
		DrawStation.roadBasicInfo.setRoadName("I+8");
		//下行
		DrawStation.road_x1la_xlfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_x2la_xlfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_x3la_xlfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_x4la_xlfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_xta_xlfa.add(DrawStation.roadBasicInfo);
		
		// 按钮XLFA
		DrawStation.button_xlfa = new StationButton(DrawStation.buttonLength, 
				centerX + 8	* trackLength - DrawStation.buttonLength, 
				centerY - DrawStation.rowSpacing - DrawStation.buttonLength - ylength,
				DrawStation.buttonInitColor).getButton();
		/*
		DrawStation.button_xlfa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//下行发车按钮
				StationModel.Button_xlfaAction();
				
			}
		});
		*/
		DrawStation.button_xlfa.addActionListener(new ButtonXLFAListener());
		
		DrawStation.panel.add(DrawStation.button_xlfa);
		DrawStation.stationLabel = new StationLabel("XLFA", DrawStation.labelWidth + 5, DrawStation.labelHeight,
				centerX + 8 * trackLength - DrawStation.labelWidth, 
				centerY - DrawStation.rowSpacing - DrawStation.buttonLength	- ylength - DrawStation.labelHeight - 5, 
				DrawStation.panel);

		// 股道I+9
		DrawStation.trackLineXLFA1 = new TrackLine(trackLength, DrawStation.lineWidth, DrawStation.trackInitColor, centerX
				+ 8 * trackLength, centerY - DrawStation.rowSpacing, DrawStation.panel);		
		DrawStation.trackLineXLFA1.setLineName("I+9");
		DrawStation.trackLineList.add(DrawStation.trackLineXLFA1);
		DrawStation.stationLabel = new StationLabel("第一离去", DrawStation.labelWidth + 30, DrawStation.labelHeight + 5,
				centerX	+ 8 * trackLength, centerY - DrawStation.rowSpacing - 15, DrawStation.panel);
		
		// 股道I+10
		DrawStation.trackLineXLFA2 = new TrackLine(trackLength, DrawStation.lineWidth, DrawStation.trackInitColor, centerX
				+ 9 * trackLength, centerY - DrawStation.rowSpacing, DrawStation.panel);
		DrawStation.trackLineXLFA2.setLineName("I+10");
		DrawStation.trackLineList.add(DrawStation.trackLineXLFA2);
		DrawStation.stationLabel = new StationLabel("第二离去", DrawStation.labelWidth + 30, DrawStation.labelHeight + 5,
				centerX	+ 9 * trackLength, centerY - DrawStation.rowSpacing - 15, DrawStation.panel);
		
		//System.out.println("3 + 1 DrawStation.trackLineList: " + DrawStation.trackLineList.size());
		
		
		
		
		
		//----------------双开道岔--------------//

		int x1 = centerX - 4 * trackLength - 25;
		int x2 = centerX + 4 * trackLength + 25;
		int y1 = centerY - 25;
		int y2 = centerY - 25;
		
		//双开道岔1/3和5/7
		DrawStation.turnoutDouble = new TurnoutDouble(DrawStation.rowSpacing, x1, y1, DrawStation.panel);
		DrawStation.turnoutDouble.setTurnoutName("1/3_5/7");
		DrawStation.turnoutDoubleList.add(DrawStation.turnoutDouble);
		DrawStation.roadBasicInfo = new RoadBasicInfo();
		DrawStation.roadBasicInfo.setRoadType(DrawStation.roadBasicInfoTypeOfTurnoutDouble);
		DrawStation.roadBasicInfo.setRoadName("1/3_5/7");
		
		//下行
		DrawStation.road_xla_x1la.add(DrawStation.roadBasicInfo);
		DrawStation.road_xla_x2la.add(DrawStation.roadBasicInfo);
		DrawStation.road_xla_x3la.add(DrawStation.roadBasicInfo);
		DrawStation.road_xla_x4la.add(DrawStation.roadBasicInfo);
		DrawStation.road_xta_xlfa.add(DrawStation.roadBasicInfo);
		//上行		
		DrawStation.road_s1la_slfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_s2la_slfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_s3la_slfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_s4la_slfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_sta_slfa.add(DrawStation.roadBasicInfo);	
		
		//label-5/7
		DrawStation.stationLabel = new StationLabel("7", DrawStation.labelWidth - 10, DrawStation.labelHeight,
				centerX - 4 * trackLength, centerY - DrawStation.rowSpacing + 5, DrawStation.panel);
		DrawStation.stationLabel = new StationLabel("5", DrawStation.labelWidth - 10, DrawStation.labelHeight,
				centerX - 5 * trackLength - 10, centerY - 15, DrawStation.panel);
		//label-1/3
		DrawStation.stationLabel = new StationLabel("1", DrawStation.labelWidth - 10, DrawStation.labelHeight,
				centerX - 5 * trackLength - 10, centerY - DrawStation.rowSpacing + 5, DrawStation.panel);
		DrawStation.stationLabel = new StationLabel("3", DrawStation.labelWidth - 10, DrawStation.labelHeight,
				centerX - 4 * trackLength, centerY - 15, DrawStation.panel);
		
		/*
		// 道岔5/7
		DrawStation.turnoutDoubleR = new TurnoutDoubleR(DrawStation.turnoutLength, DrawStation.lineWidth,
				DrawStation.rowSpacing, x1, y1, DrawStation.panel); // 绘制右倾斜道岔
		DrawStation.turnoutDoubleR.setTurnoutName("5/7");
		DrawStation.turnoutDoubleRList.add(DrawStation.turnoutDoubleR);
		DrawStation.roadBasicInfo = new RoadBasicInfo();
		DrawStation.roadBasicInfo.setRoadType(DrawStation.roadBasicInfoTypeOfTurnoutDoubleR);
		DrawStation.roadBasicInfo.setRoadName("5/7");
		//下行
		DrawStation.road_xla_x1la.add(DrawStation.roadBasicInfo);
		DrawStation.road_xla_x3la.add(DrawStation.roadBasicInfo);
		DrawStation.road_xta_xlfa.add(DrawStation.roadBasicInfo);
		//上行		
		DrawStation.road_s1la_slfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_s2la_slfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_s3la_slfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_s4la_slfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_sta_slfa.add(DrawStation.roadBasicInfo);		
		
		DrawStation.stationLabel = new StationLabel("7", DrawStation.labelWidth - 10, DrawStation.labelHeight,
				centerX - 4 * trackLength, centerY - DrawStation.rowSpacing + 5, DrawStation.panel);
		DrawStation.stationLabel = new StationLabel("5", DrawStation.labelWidth - 10, DrawStation.labelHeight,
				centerX - 5 * trackLength - 10, centerY - 15, DrawStation.panel);

		// 道岔1/3
		DrawStation.turnoutDoubleL = new TurnoutDoubleL(DrawStation.turnoutLength, DrawStation.lineWidth,
				DrawStation.rowSpacing, x1, y1, DrawStation.panel); // 绘制左倾斜道岔
		DrawStation.turnoutDoubleL.setTurnoutName("1/3");
		DrawStation.turnoutDoubleLList.add(DrawStation.turnoutDoubleL);
		DrawStation.roadBasicInfo = new RoadBasicInfo();
		DrawStation.roadBasicInfo.setRoadType(DrawStation.roadBasicInfoTypeOfTurnoutDoubleL);
		DrawStation.roadBasicInfo.setRoadName("1/3");
		//下行
		DrawStation.road_xla_x1la.add(DrawStation.roadBasicInfo);
		DrawStation.road_xla_x2la.add(DrawStation.roadBasicInfo);
		DrawStation.road_xla_x3la.add(DrawStation.roadBasicInfo);
		DrawStation.road_xla_x4la.add(DrawStation.roadBasicInfo);
		DrawStation.road_xta_xlfa.add(DrawStation.roadBasicInfo);
		//上行
		DrawStation.road_s2la_slfa.add(DrawStation.roadBasicInfo);		
		DrawStation.road_s4la_slfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_sta_slfa.add(DrawStation.roadBasicInfo);
		
		DrawStation.stationLabel = new StationLabel("1", DrawStation.labelWidth - 10, DrawStation.labelHeight,
				centerX - 5 * trackLength - 10, centerY - DrawStation.rowSpacing + 5, DrawStation.panel);
		DrawStation.stationLabel = new StationLabel("3", DrawStation.labelWidth - 10, DrawStation.labelHeight,
				centerX - 4 * trackLength, centerY - 15, DrawStation.panel);

		*/

		
		//双开道岔2/4和6/8
		DrawStation.turnoutDouble = new TurnoutDouble(DrawStation.rowSpacing, x2, y2, DrawStation.panel);
		DrawStation.turnoutDouble.setTurnoutName("2/4_6/8");
		DrawStation.turnoutDoubleList.add(DrawStation.turnoutDouble);
		DrawStation.roadBasicInfo = new RoadBasicInfo();
		DrawStation.roadBasicInfo.setRoadType(DrawStation.roadBasicInfoTypeOfTurnoutDouble);
		DrawStation.roadBasicInfo.setRoadName("2/4_6/8");
		
		//下行
		DrawStation.road_sla_s1la.add(DrawStation.roadBasicInfo);
		DrawStation.road_sla_s2la.add(DrawStation.roadBasicInfo);
		DrawStation.road_sla_s3la.add(DrawStation.roadBasicInfo);
		DrawStation.road_sla_s4la.add(DrawStation.roadBasicInfo);
		DrawStation.road_sta_slfa.add(DrawStation.roadBasicInfo);
		//上行		
		DrawStation.road_x1la_xlfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_x2la_xlfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_x3la_xlfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_x4la_xlfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_xta_xlfa.add(DrawStation.roadBasicInfo);	
		
		//label-6/8
		DrawStation.stationLabel = new StationLabel("6", DrawStation.labelWidth - 10, DrawStation.labelHeight,
				centerX + 5 * trackLength, centerY - DrawStation.rowSpacing + 5, DrawStation.panel);
		DrawStation.stationLabel = new StationLabel("8", DrawStation.labelWidth - 10, DrawStation.labelHeight,
				centerX + 4 * trackLength - 10, centerY - 15, DrawStation.panel);
		//label-2/4
		DrawStation.stationLabel = new StationLabel("4", DrawStation.labelWidth - 10, DrawStation.labelHeight,
				centerX + 4 * trackLength - 10, centerY - DrawStation.rowSpacing + 5, DrawStation.panel);
		DrawStation.stationLabel = new StationLabel("2", DrawStation.labelWidth - 10, DrawStation.labelHeight,
				centerX + 5 * trackLength, centerY - 15, DrawStation.panel);
		
		
		/*
		// 道岔6/8
		DrawStation.turnoutDoubleR = new TurnoutDoubleR(DrawStation.turnoutLength, DrawStation.lineWidth,
				DrawStation.rowSpacing, x2, y2, DrawStation.panel); // 绘制右倾斜道岔
		DrawStation.turnoutDoubleR.setTurnoutName("6/8");
		DrawStation.turnoutDoubleRList.add(DrawStation.turnoutDoubleR);
		DrawStation.roadBasicInfo = new RoadBasicInfo();
		DrawStation.roadBasicInfo.setRoadType(DrawStation.roadBasicInfoTypeOfTurnoutDoubleR);
		DrawStation.roadBasicInfo.setRoadName("6/8");
		//下行
		DrawStation.road_x1la_xlfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_x2la_xlfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_x3la_xlfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_x4la_xlfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_xta_xlfa.add(DrawStation.roadBasicInfo);
		//上行		
		DrawStation.road_sla_s2la.add(DrawStation.roadBasicInfo);
		DrawStation.road_sla_s4la.add(DrawStation.roadBasicInfo);
		DrawStation.road_sta_slfa.add(DrawStation.roadBasicInfo);			
		
		DrawStation.stationLabel = new StationLabel("6", DrawStation.labelWidth - 10, DrawStation.labelHeight,
				centerX + 5 * trackLength, centerY - DrawStation.rowSpacing + 5, DrawStation.panel);
		DrawStation.stationLabel = new StationLabel("8", DrawStation.labelWidth - 10, DrawStation.labelHeight,
				centerX + 4 * trackLength - 10, centerY - 15, DrawStation.panel);

		// 道岔2/4
		DrawStation.turnoutDoubleL = new TurnoutDoubleL(DrawStation.turnoutLength, DrawStation.lineWidth,
				DrawStation.rowSpacing, x2, y2, DrawStation.panel); // 绘制左倾斜道岔
		DrawStation.turnoutDoubleL.setTurnoutName("2/4");
		DrawStation.turnoutDoubleLList.add(DrawStation.turnoutDoubleL);
		DrawStation.roadBasicInfo = new RoadBasicInfo();
		DrawStation.roadBasicInfo.setRoadType(DrawStation.roadBasicInfoTypeOfTurnoutDoubleL);
		DrawStation.roadBasicInfo.setRoadName("2/4");
		//下行
		DrawStation.road_x1la_xlfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_x3la_xlfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_xta_xlfa.add(DrawStation.roadBasicInfo);
		//上行
		DrawStation.road_sla_s1la.add(DrawStation.roadBasicInfo);		
		DrawStation.road_sla_s2la.add(DrawStation.roadBasicInfo);
		DrawStation.road_sla_s3la.add(DrawStation.roadBasicInfo);
		DrawStation.road_sla_s4la.add(DrawStation.roadBasicInfo);
		DrawStation.road_sta_slfa.add(DrawStation.roadBasicInfo);
		
		DrawStation.stationLabel = new StationLabel("4", DrawStation.labelWidth - 10, DrawStation.labelHeight,
				centerX + 4 * trackLength - 10, centerY - DrawStation.rowSpacing + 5, DrawStation.panel);
		DrawStation.stationLabel = new StationLabel("2", DrawStation.labelWidth - 10, DrawStation.labelHeight,
				centerX + 5 * trackLength, centerY - 15, DrawStation.panel);
		*/
		
		
		// -----------------股道 II--------------------//
		// 股道II
		DrawStation.stationLabel = new StationLabel("II", DrawStation.labelWidth, DrawStation.labelHeight,
				centerX - 8, centerY + 5, DrawStation.panel);
		
		// 股道II-10
		DrawStation.trackLineSLFA2 = new TrackLine(trackLength, DrawStation.lineWidth, DrawStation.trackInitColor, centerX
				- 10 * trackLength, centerY, DrawStation.panel);		
		DrawStation.trackLineSLFA2.setLineName("II-10");
		DrawStation.trackLineList.add(DrawStation.trackLineSLFA2);
		DrawStation.stationLabel = new StationLabel("第二离去", DrawStation.labelWidth + 30, DrawStation.labelHeight + 5,
				centerX	- 10 * trackLength, centerY + 5, DrawStation.panel);
		
		// 股道II-9
		DrawStation.trackLineSLFA1 = new TrackLine(trackLength, DrawStation.lineWidth, DrawStation.trackInitColor, centerX
				- 9 * trackLength, centerY, DrawStation.panel);		
		DrawStation.trackLineSLFA1.setLineName("II-9");
		DrawStation.trackLineList.add(DrawStation.trackLineSLFA1);
		DrawStation.stationLabel = new StationLabel("第一离去", DrawStation.labelWidth + 30, DrawStation.labelHeight + 5,
				centerX	- 9 * trackLength, centerY + 5, DrawStation.panel);
		
		// 股道II-8
		DrawStation.trackLine = new TrackLine(trackLength, DrawStation.lineWidth, DrawStation.trackInitColor, centerX
				- 8 * trackLength, centerY, DrawStation.panel);
		DrawStation.trackLine.setLineName("II-8");
		DrawStation.trackLineList.add(DrawStation.trackLine);
		DrawStation.roadBasicInfo = new RoadBasicInfo();
		DrawStation.roadBasicInfo.setRoadType(DrawStation.roadBasicInfoTypeOfTrackLine);
		DrawStation.roadBasicInfo.setRoadName("II-8");
		//上行
		DrawStation.road_s1la_slfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_s2la_slfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_s3la_slfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_s4la_slfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_sta_slfa.add(DrawStation.roadBasicInfo);
		
		// 按钮SLFA
		DrawStation.button_slfa = new StationButton(DrawStation.buttonLength, centerX - 8
				* trackLength, centerY + ylength, DrawStation.buttonInitColor).getButton();
		/*
		DrawStation.button_slfa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//上行发车按钮
				StationModel.Button_slfaAction();
				
			}
		});
		*/
		DrawStation.button_slfa.addActionListener(new ButtonSLFAListener());
		
		DrawStation.panel.add(DrawStation.button_slfa);
		DrawStation.stationLabel = new StationLabel("SLFA", DrawStation.labelWidth + 5, DrawStation.labelHeight,
				centerX - 8 * trackLength - 6, centerY + DrawStation.labelWidth + 5, DrawStation.panel);

		// 股道II-7
		DrawStation.trackLine = new TrackLine(trackLength, DrawStation.lineWidth, DrawStation.trackInitColor, centerX
				- 7 * trackLength, centerY, DrawStation.panel);
		DrawStation.trackLine.setLineName("II-7");
		DrawStation.trackLineList.add(DrawStation.trackLine);
		DrawStation.roadBasicInfo = new RoadBasicInfo();
		DrawStation.roadBasicInfo.setRoadType(DrawStation.roadBasicInfoTypeOfTrackLine);
		DrawStation.roadBasicInfo.setRoadName("II-7");
		//上行
		DrawStation.road_s1la_slfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_s2la_slfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_s3la_slfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_s4la_slfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_sta_slfa.add(DrawStation.roadBasicInfo);
		
		// 股道II-6
		DrawStation.trackLine = new TrackLine(trackLength, DrawStation.lineWidth, DrawStation.trackInitColor, centerX
				- 6 * trackLength, centerY, DrawStation.panel);
		DrawStation.trackLine.setLineName("II-6");
		DrawStation.trackLineList.add(DrawStation.trackLine);
		DrawStation.roadBasicInfo = new RoadBasicInfo();
		DrawStation.roadBasicInfo.setRoadType(DrawStation.roadBasicInfoTypeOfTrackLine);
		DrawStation.roadBasicInfo.setRoadName("II-6");
		//上行
		DrawStation.road_s1la_slfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_s2la_slfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_s3la_slfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_s4la_slfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_sta_slfa.add(DrawStation.roadBasicInfo);
		
		/*
		// 股道II-5
		DrawStation.trackLine = new TrackLine(trackLength - 2 * DrawStation.turnoutLength, DrawStation.lineWidth,
				DrawStation.trackInitColor, centerX - 5 * trackLength + DrawStation.turnoutLength, centerY,
				DrawStation.panel);
		DrawStation.trackLine.setLineName("II-5");
		DrawStation.trackLineList.add(DrawStation.trackLine);
		DrawStation.roadBasicInfo = new RoadBasicInfo();
		DrawStation.roadBasicInfo.setRoadType(DrawStation.roadBasicInfoTypeOfTrackLine);
		DrawStation.roadBasicInfo.setRoadName("II-5");
		//上行
		DrawStation.road_s2la_slfa.add(DrawStation.roadBasicInfo);		
		DrawStation.road_s4la_slfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_sta_slfa.add(DrawStation.roadBasicInfo);
		*/
		
		
		// 股道II-4
		DrawStation.trackLine = new TrackLine(trackLength, DrawStation.lineWidth, DrawStation.trackInitColor, centerX
				- 4 * trackLength, centerY, DrawStation.panel);
		DrawStation.trackLine.setLineName("II-4");
		DrawStation.trackLineList.add(DrawStation.trackLine);
		DrawStation.roadBasicInfo = new RoadBasicInfo();
		DrawStation.roadBasicInfo.setRoadType(DrawStation.roadBasicInfoTypeOfTrackLine);
		DrawStation.roadBasicInfo.setRoadName("II-4");
		//下行
		DrawStation.road_xla_x2la.add(DrawStation.roadBasicInfo);		
		DrawStation.road_xla_x4la.add(DrawStation.roadBasicInfo);
		//上行
		DrawStation.road_s2la_slfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_s4la_slfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_sta_slfa.add(DrawStation.roadBasicInfo);
		
		/*
		// 股道II-3
		DrawStation.trackLine = new TrackLine(trackLength - DrawStation.turnoutLength, DrawStation.lineWidth,
				DrawStation.trackInitColor, centerX - 3 * trackLength + DrawStation.turnoutLength, centerY,
				DrawStation.panel);
		DrawStation.trackLine.setLineName("II-3");
		DrawStation.trackLineList.add(DrawStation.trackLine);
		DrawStation.roadBasicInfo = new RoadBasicInfo();
		DrawStation.roadBasicInfo.setRoadType(DrawStation.roadBasicInfoTypeOfTrackLine);
		DrawStation.roadBasicInfo.setRoadName("II-3");
		//下行
		DrawStation.road_xla_x2la.add(DrawStation.roadBasicInfo);
		//上行
		DrawStation.road_s2la_slfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_sta_slfa.add(DrawStation.roadBasicInfo);
		*/
		
		// 股道II-2
		DrawStation.trackLine = new TrackLine(trackLength, DrawStation.lineWidth, DrawStation.trackInitColor, centerX
				- 2 * trackLength, centerY, DrawStation.panel);
		DrawStation.trackLine.setLineName("II-2");
		DrawStation.trackLineList.add(DrawStation.trackLine);
		DrawStation.roadBasicInfo = new RoadBasicInfo();
		DrawStation.roadBasicInfo.setRoadType(DrawStation.roadBasicInfoTypeOfTrackLine);
		DrawStation.roadBasicInfo.setRoadName("II-2");
		//下行
		DrawStation.road_xla_x2la.add(DrawStation.roadBasicInfo);
		DrawStation.road_x2la_xlfa.add(DrawStation.roadBasicInfo);
		//上行
		DrawStation.road_sla_s2la.add(DrawStation.roadBasicInfo);
		DrawStation.road_s2la_slfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_sta_slfa.add(DrawStation.roadBasicInfo);
		
		// 信号灯SII
		DrawStation.semaphoreSimpleL = new SemaphoreSimpleL("S2", DrawStation.semaphoreLightDiameter, centerX - trackLength, centerY + ylength, DrawStation.panel);
		DrawStation.semaphoreSimpleLList.add(DrawStation.semaphoreSimpleL);

		DrawStation.semphoreBasicInfo = new SemphoreBasicInfo();
		DrawStation.semphoreBasicInfo.setSemphoreName("S2");
		DrawStation.semphoreBasicInfo.setSemphoreType("SemaphoreSimpleL");
		DrawStation.semphoreBasicInfoList.add(DrawStation.semphoreBasicInfo);
		
		DrawStation.stationLabel = new StationLabel("SII", DrawStation.labelWidth, DrawStation.labelHeight, centerX - trackLength - DrawStation.labelWidth/2 - 3, centerY - DrawStation.semaphoreLightDiameter - ylength + 8, DrawStation.panel);

		// 按钮SIILA
		DrawStation.button_s2la = new StationButton(DrawStation.buttonLength, centerX - 5*trackLength/3	- DrawStation.buttonLength/2, centerY - 3*ylength/2,
				DrawStation.buttonInitColor).getButton();
		/*
		DrawStation.button_s2la.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//2股道下行接车，或者2股道上行发车
				StationModel.Button_s2laAction();
				
			}
		});
		*/
		DrawStation.button_s2la.addActionListener(new ButtonS2LAListener());
		
		DrawStation.panel.add(DrawStation.button_s2la);
		DrawStation.stationLabel = new StationLabel("SIILA", DrawStation.labelWidth + 15, DrawStation.labelHeight,
				centerX - 2*trackLength, centerY + ylength + 8,
				DrawStation.panel);

		// 道岔11
		DrawStation.turnoutLX = new TurnoutLX(DrawStation.rowSpacing, centerX - 3 * trackLength, centerY, DrawStation.panel);
		DrawStation.turnoutLX.setTurnoutName("11");
		DrawStation.turnoutLXList.add(DrawStation.turnoutLX);
		DrawStation.roadBasicInfo = new RoadBasicInfo();
		DrawStation.roadBasicInfo.setRoadType(DrawStation.roadBasicInfoTypeOfTurnoutLX);
		DrawStation.roadBasicInfo.setRoadName("11");
		//下行
		DrawStation.road_xla_x2la.add(DrawStation.roadBasicInfo);
		DrawStation.road_xla_x4la.add(DrawStation.roadBasicInfo);
		//上行
		DrawStation.road_s2la_slfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_s4la_slfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_sta_slfa.add(DrawStation.roadBasicInfo);
		
		DrawStation.stationLabel = new StationLabel("11", DrawStation.labelWidth, DrawStation.labelHeight, centerX
				- 3 * trackLength - 15, centerY + 5, DrawStation.panel);

		// 股道II-1
		DrawStation.trackLine = new TrackLine(trackLength, DrawStation.lineWidth, DrawStation.trackInitColor, centerX
				- trackLength, centerY, DrawStation.panel);
		DrawStation.trackLine.setLineName("II-1");
		DrawStation.trackLineList.add(DrawStation.trackLine);
		DrawStation.roadBasicInfo = new RoadBasicInfo();
		DrawStation.roadBasicInfo.setRoadType(DrawStation.roadBasicInfoTypeOfTrackLine);
		DrawStation.roadBasicInfo.setRoadName("II-1");
		//下行
		DrawStation.road_xla_x2la.add(DrawStation.roadBasicInfo);
		DrawStation.road_x2la_xlfa.add(DrawStation.roadBasicInfo);
		//上行
		DrawStation.road_sla_s2la.add(DrawStation.roadBasicInfo);
		DrawStation.road_s2la_slfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_sta_slfa.add(DrawStation.roadBasicInfo);
		
		// 股道II+1
		DrawStation.trackLine = new TrackLine(trackLength, DrawStation.lineWidth, DrawStation.trackInitColor, centerX,
				centerY, DrawStation.panel);
		DrawStation.trackLine.setLineName("II+1");
		DrawStation.trackLineList.add(DrawStation.trackLine);
		DrawStation.roadBasicInfo = new RoadBasicInfo();
		DrawStation.roadBasicInfo.setRoadType(DrawStation.roadBasicInfoTypeOfTrackLine);
		DrawStation.roadBasicInfo.setRoadName("II+1");
		//下行
		DrawStation.road_xla_x2la.add(DrawStation.roadBasicInfo);
		DrawStation.road_x2la_xlfa.add(DrawStation.roadBasicInfo);
		//上行
		DrawStation.road_sla_s2la.add(DrawStation.roadBasicInfo);
		DrawStation.road_s2la_slfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_sta_slfa.add(DrawStation.roadBasicInfo);
		
		// 股道II+2
		DrawStation.trackLine = new TrackLine(trackLength, DrawStation.lineWidth, DrawStation.trackInitColor, centerX
				+ trackLength, centerY, DrawStation.panel);
		DrawStation.trackLine.setLineName("II+2");
		DrawStation.trackLineList.add(DrawStation.trackLine);
		DrawStation.roadBasicInfo = new RoadBasicInfo();
		DrawStation.roadBasicInfo.setRoadType(DrawStation.roadBasicInfoTypeOfTrackLine);
		DrawStation.roadBasicInfo.setRoadName("II+2");
		//下行
		DrawStation.road_xla_x2la.add(DrawStation.roadBasicInfo);
		DrawStation.road_x2la_xlfa.add(DrawStation.roadBasicInfo);
		//上行
		DrawStation.road_sla_s2la.add(DrawStation.roadBasicInfo);
		DrawStation.road_s2la_slfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_sta_slfa.add(DrawStation.roadBasicInfo);
		
		// 信号机XII
		DrawStation.semaphoreSimpleR = new SemaphoreSimpleR("X2", DrawStation.semaphoreLightDiameter, centerX
				+ trackLength, centerY - DrawStation.semaphoreLightDiameter
				- ylength, DrawStation.panel);
		DrawStation.semaphoreSimpleRList.add(DrawStation.semaphoreSimpleR);

		DrawStation.semphoreBasicInfo = new SemphoreBasicInfo();
		DrawStation.semphoreBasicInfo.setSemphoreName("X2");
		DrawStation.semphoreBasicInfo.setSemphoreType("SemaphoreSimpleR");
		DrawStation.semphoreBasicInfoList.add(DrawStation.semphoreBasicInfo);
		
		
		DrawStation.stationLabel = new StationLabel("XII", DrawStation.labelWidth + 5, DrawStation.labelHeight,
				centerX + trackLength,
				centerY + ylength,
				DrawStation.panel);

		// 按钮XIILA
		DrawStation.button_x2la = new StationButton(DrawStation.buttonLength, centerX + 3*trackLength/2, centerY - 3*ylength/2,
				DrawStation.buttonInitColor).getButton();
		/*
		DrawStation.button_x2la.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//2股道上行接车，或者2股道下行发车
				StationModel.Button_x2laAction();		
				
			}
		});
		*/
		DrawStation.button_x2la.addActionListener(new ButtonX2LAListener());
		
		DrawStation.panel.add(DrawStation.button_x2la);
		DrawStation.stationLabel = new StationLabel("XIILA", DrawStation.labelWidth + 12, DrawStation.labelHeight,
				centerX + 3*trackLength/2, 
				centerY -4*ylength, 
				DrawStation.panel);

		// 道岔12
		DrawStation.turnoutRX = new TurnoutRX(DrawStation.rowSpacing, centerX + 3 * trackLength, centerY, DrawStation.panel); // 绘制右下道岔
		DrawStation.turnoutRX.setTurnoutName("12");
		DrawStation.turnoutRXList.add(DrawStation.turnoutRX);
		DrawStation.roadBasicInfo = new RoadBasicInfo();
		DrawStation.roadBasicInfo.setRoadType(DrawStation.roadBasicInfoTypeOfTurnoutRX);
		DrawStation.roadBasicInfo.setRoadName("12");
		//下行
		DrawStation.road_x2la_xlfa.add(DrawStation.roadBasicInfo);
		DrawStation.road_x4la_xlfa.add(DrawStation.roadBasicInfo);
		//上行
		DrawStation.road_sla_s2la.add(DrawStation.roadBasicInfo);
		DrawStation.road_sla_s4la.add(DrawStation.roadBasicInfo);
		DrawStation.road_sta_slfa.add(DrawStation.roadBasicInfo);
		
		DrawStation.stationLabel = new StationLabel("12", DrawStation.labelWidth, DrawStation.labelHeight, centerX
				+ 3 * trackLength, centerY + 5, DrawStation.panel);

		/*
		// 股道II+3
		DrawStation.trackLine = new TrackLine(trackLength - DrawStation.turnoutLength, DrawStation.lineWidth,
				DrawStation.trackInitColor, centerX + 2 * trackLength, centerY, DrawStation.panel);
		DrawStation.trackLine.setLineName("II+3");
		DrawStation.trackLineList.add(DrawStation.trackLine);
		DrawStation.roadBasicInfo = new RoadBasicInfo();
		DrawStation.roadBasicInfo.setRoadType(DrawStation.roadBasicInfoTypeOfTrackLine);
		DrawStation.roadBasicInfo.setRoadName("II+3");
		//下行
		DrawStation.road_sla_s2la.add(DrawStation.roadBasicInfo);		
		DrawStation.road_sta_slfa.add(DrawStation.roadBasicInfo);
		//上行
		DrawStation.road_x2la_xlfa.add(DrawStation.roadBasicInfo);
		*/
		
		// 股道II+4
		DrawStation.trackLine = new TrackLine(trackLength, DrawStation.lineWidth, DrawStation.trackInitColor, centerX
				+ 3 * trackLength, centerY, DrawStation.panel);
		DrawStation.trackLine.setLineName("II+4");
		DrawStation.trackLineList.add(DrawStation.trackLine);
		DrawStation.roadBasicInfo = new RoadBasicInfo();
		DrawStation.roadBasicInfo.setRoadType(DrawStation.roadBasicInfoTypeOfTrackLine);
		DrawStation.roadBasicInfo.setRoadName("II+4");
		//下行		
		DrawStation.road_x2la_xlfa.add(DrawStation.roadBasicInfo);		
		DrawStation.road_x4la_xlfa.add(DrawStation.roadBasicInfo);
		//上行
		DrawStation.road_sla_s2la.add(DrawStation.roadBasicInfo);
		DrawStation.road_sla_s4la.add(DrawStation.roadBasicInfo);
		DrawStation.road_sta_slfa.add(DrawStation.roadBasicInfo);
		
		/*
		// 股道II+5
		DrawStation.trackLine = new TrackLine(trackLength - 2 * DrawStation.turnoutLength, DrawStation.lineWidth,
				DrawStation.trackInitColor, centerX + 4 * trackLength + DrawStation.turnoutLength, centerY,
				DrawStation.panel);
		DrawStation.trackLine.setLineName("II+5");
		DrawStation.trackLineList.add(DrawStation.trackLine);
		DrawStation.roadBasicInfo = new RoadBasicInfo();
		DrawStation.roadBasicInfo.setRoadType(DrawStation.roadBasicInfoTypeOfTrackLine);
		DrawStation.roadBasicInfo.setRoadName("II+5");
		//上行
		DrawStation.road_sla_s2la.add(DrawStation.roadBasicInfo);		
		DrawStation.road_sla_s4la.add(DrawStation.roadBasicInfo);		
		DrawStation.road_sta_slfa.add(DrawStation.roadBasicInfo);
		*/
		
		
		// 股道II+6
		DrawStation.trackLine = new TrackLine(trackLength, DrawStation.lineWidth, DrawStation.trackInitColor, centerX
				+ 5 * trackLength, centerY, DrawStation.panel);
		DrawStation.trackLine.setLineName("II+6");
		DrawStation.trackLineList.add(DrawStation.trackLine);
		DrawStation.roadBasicInfo = new RoadBasicInfo();
		DrawStation.roadBasicInfo.setRoadType(DrawStation.roadBasicInfoTypeOfTrackLine);
		DrawStation.roadBasicInfo.setRoadName("II+6");
		//上行
		DrawStation.road_sla_s1la.add(DrawStation.roadBasicInfo);
		DrawStation.road_sla_s2la.add(DrawStation.roadBasicInfo);
		DrawStation.road_sla_s3la.add(DrawStation.roadBasicInfo);
		DrawStation.road_sla_s4la.add(DrawStation.roadBasicInfo);
		DrawStation.road_sta_slfa.add(DrawStation.roadBasicInfo);
		
		// 股道II+7
		DrawStation.trackLine = new TrackLine(trackLength, DrawStation.lineWidth, DrawStation.trackInitColor, centerX
				+ 6 * trackLength, centerY, DrawStation.panel);
		DrawStation.trackLine.setLineName("II+7");
		DrawStation.trackLineList.add(DrawStation.trackLine);
		DrawStation.roadBasicInfo = new RoadBasicInfo();
		DrawStation.roadBasicInfo.setRoadType(DrawStation.roadBasicInfoTypeOfTrackLine);
		DrawStation.roadBasicInfo.setRoadName("II+7");
		//上行
		DrawStation.road_sla_s1la.add(DrawStation.roadBasicInfo);
		DrawStation.road_sla_s2la.add(DrawStation.roadBasicInfo);
		DrawStation.road_sla_s3la.add(DrawStation.roadBasicInfo);
		DrawStation.road_sla_s4la.add(DrawStation.roadBasicInfo);
		DrawStation.road_sta_slfa.add(DrawStation.roadBasicInfo);
		
		// 信号机SLX
		DrawStation.semaphoreDoubleL = new SemaphoreDoubleL("SLX", DrawStation.semaphoreLightDiameter, 
				centerX	+ 8 * trackLength, 
				centerY + ylength, 
				DrawStation.panel);
		DrawStation.semaphoreDoubleLList.add(DrawStation.semaphoreDoubleL);		

		DrawStation.semphoreBasicInfo = new SemphoreBasicInfo();
		DrawStation.semphoreBasicInfo.setSemphoreName("SLX");
		DrawStation.semphoreBasicInfo.setSemphoreType("SemaphoreDoubleL");
		DrawStation.semphoreBasicInfoList.add(DrawStation.semphoreBasicInfo);
		
		
		DrawStation.stationLabel = new StationLabel("SLX", DrawStation.labelWidth, DrawStation.labelHeight, 
				centerX	+ 8 * trackLength - DrawStation.labelWidth - 5, 
				centerY + DrawStation.labelWidth + 5, 
				DrawStation.panel);

		// 股道II+8
		DrawStation.trackLine = new TrackLine(trackLength, DrawStation.lineWidth, DrawStation.trackInitColor, centerX
				+ 7 * trackLength, centerY, DrawStation.panel);
		DrawStation.trackLine.setLineName("II+8");
		DrawStation.trackLineList.add(DrawStation.trackLine);
		DrawStation.roadBasicInfo = new RoadBasicInfo();
		DrawStation.roadBasicInfo.setRoadType(DrawStation.roadBasicInfoTypeOfTrackLine);
		DrawStation.roadBasicInfo.setRoadName("II+8");
		//上行
		DrawStation.road_sla_s1la.add(DrawStation.roadBasicInfo);
		DrawStation.road_sla_s2la.add(DrawStation.roadBasicInfo);
		DrawStation.road_sla_s3la.add(DrawStation.roadBasicInfo);
		DrawStation.road_sla_s4la.add(DrawStation.roadBasicInfo);
		DrawStation.road_sta_slfa.add(DrawStation.roadBasicInfo);
		
		// 按钮SLA
		DrawStation.button_sla = new StationButton(DrawStation.buttonLength, 
				centerX + 6	* trackLength, 
				centerY + ylength, 
				DrawStation.buttonInitColor).getButton();
		/*
		DrawStation.button_sla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//上行接车按钮
				StationModel.Button_slaAction();
				
			}
		});
		*/
		DrawStation.button_sla.addActionListener(new ButtonSLAListener());
		
		DrawStation.panel.add(DrawStation.button_sla);
		DrawStation.stationLabel = new StationLabel("SLA", DrawStation.labelWidth, DrawStation.labelHeight, 
				centerX	+ 6 * trackLength - 2, 
				centerY + DrawStation.labelWidth + 5, 
				DrawStation.panel);

		// 按钮STA
		DrawStation.button_sta = new StationButton(DrawStation.buttonLength, 
				centerX + 6	* trackLength + DrawStation.buttonLength + 2*ylength, 
				centerY + ylength, 
				DrawStation.buttonInitColor).getButton();
		/*
		DrawStation.button_sta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//上行通过按钮
				StationModel.Button_staAction();
				
			}
		});
		*/
		DrawStation.button_sta.addActionListener(new ButtonSTAListener());
		
		DrawStation.panel.add(DrawStation.button_sta);
		DrawStation.stationLabel = new StationLabel("STA", DrawStation.labelWidth, DrawStation.labelHeight, 
				centerX	+ 6 * trackLength + DrawStation.buttonLength + 2*ylength, 
				centerY	+ DrawStation.labelWidth + 5, 
				DrawStation.panel);

		// 股道II+9
		DrawStation.trackLineSLA2 = new TrackLine(trackLength, DrawStation.lineWidth, DrawStation.trackInitColor, centerX
				+ 8 * trackLength, centerY, DrawStation.panel);		
		DrawStation.trackLineSLA2.setLineName("II+9");
		DrawStation.trackLineList.add(DrawStation.trackLineSLA2);
		DrawStation.stationLabel = new StationLabel("第二接近", DrawStation.labelWidth + 30, DrawStation.labelHeight + 5,
				centerX	+ 8 * trackLength, centerY + 5, DrawStation.panel);
		
		// 股道II+10
		DrawStation.trackLineSLA1 = new TrackLine(trackLength, DrawStation.lineWidth, DrawStation.trackInitColor, centerX
				+ 9 * trackLength, centerY, DrawStation.panel);		
		DrawStation.trackLineSLA1.setLineName("II+10");
		DrawStation.trackLineList.add(DrawStation.trackLineSLA1);
		DrawStation.stationLabel = new StationLabel("第一接近", DrawStation.labelWidth + 30, DrawStation.labelHeight + 5,
				centerX	+ 9 * trackLength, centerY + 5, DrawStation.panel);
		
		//System.out.println("3 + 1 + 2 DrawStation.trackLineList: " + DrawStation.trackLineList.size());
		
		// -----------------股道 4------------------//
		// 股道4
		DrawStation.stationLabel = new StationLabel("4", DrawStation.labelWidth, DrawStation.labelHeight,
				centerX - 8, centerY + DrawStation.rowSpacing + 5, DrawStation.panel);

		// 股道4-2
		DrawStation.trackLine = new TrackLine(trackLength, DrawStation.lineWidth, DrawStation.trackInitColor, centerX
				- 2 * trackLength, centerY + DrawStation.rowSpacing, DrawStation.panel);
		DrawStation.trackLine.setLineName("4-2");
		DrawStation.trackLineList.add(DrawStation.trackLine);
		DrawStation.roadBasicInfo = new RoadBasicInfo();
		DrawStation.roadBasicInfo.setRoadType(DrawStation.roadBasicInfoTypeOfTrackLine);
		DrawStation.roadBasicInfo.setRoadName("4-2");
		//下行
		DrawStation.road_xla_x4la.add(DrawStation.roadBasicInfo);
		DrawStation.road_x4la_xlfa.add(DrawStation.roadBasicInfo);
		//上行
		DrawStation.road_sla_s4la.add(DrawStation.roadBasicInfo);
		DrawStation.road_s4la_slfa.add(DrawStation.roadBasicInfo);
		
		// 信号机S4
		DrawStation.semaphoreSimpleL = new SemaphoreSimpleL("S4",DrawStation.semaphoreLightDiameter, centerX - trackLength, centerY + DrawStation.rowSpacing + ylength, DrawStation.panel);
		DrawStation.semaphoreSimpleLList.add(DrawStation.semaphoreSimpleL);	

		DrawStation.semphoreBasicInfo = new SemphoreBasicInfo();
		DrawStation.semphoreBasicInfo.setSemphoreName("S4");
		DrawStation.semphoreBasicInfo.setSemphoreType("SemaphoreSimpleL");
		DrawStation.semphoreBasicInfoList.add(DrawStation.semphoreBasicInfo);
		
		
		DrawStation.stationLabel = new StationLabel("S4", DrawStation.labelWidth - 5, DrawStation.labelHeight,
				centerX - trackLength - DrawStation.labelWidth/2 - 3, centerY + DrawStation.rowSpacing - DrawStation.semaphoreLightDiameter - ylength + 8, DrawStation.panel);

		// 按钮S4LA
		DrawStation.button_s4la = new StationButton(DrawStation.buttonLength, centerX - 5*trackLength/3
				- DrawStation.buttonLength/2, centerY + DrawStation.rowSpacing - 3*ylength/2,
				DrawStation.buttonInitColor).getButton();
		/*
		DrawStation.button_s4la.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//4股道下行通过，或者4股道上行发车
				StationModel.Button_s4laAction();
				
			}
		});
		*/
		DrawStation.button_s4la.addActionListener(new ButtonS4LAListener());;
		
		DrawStation.panel.add(DrawStation.button_s4la);
		DrawStation.stationLabel = new StationLabel("S4LA", DrawStation.labelWidth + 5, DrawStation.labelHeight,
				centerX - 2*trackLength + 4, centerY + DrawStation.rowSpacing
						+ ylength + 8, DrawStation.panel);

		// 股道4-1
		DrawStation.trackLine = new TrackLine(trackLength, DrawStation.lineWidth, DrawStation.trackInitColor, centerX
				- trackLength, centerY + DrawStation.rowSpacing, DrawStation.panel);
		DrawStation.trackLine.setLineName("4-1");
		DrawStation.trackLineList.add(DrawStation.trackLine);
		DrawStation.roadBasicInfo = new RoadBasicInfo();
		DrawStation.roadBasicInfo.setRoadType(DrawStation.roadBasicInfoTypeOfTrackLine);
		DrawStation.roadBasicInfo.setRoadName("4-1");
		//下行
		DrawStation.road_xla_x4la.add(DrawStation.roadBasicInfo);
		DrawStation.road_x4la_xlfa.add(DrawStation.roadBasicInfo);
		//上行
		DrawStation.road_sla_s4la.add(DrawStation.roadBasicInfo);
		DrawStation.road_s4la_slfa.add(DrawStation.roadBasicInfo);
		
		// 股道4+1
		DrawStation.trackLine = new TrackLine(trackLength, DrawStation.lineWidth, DrawStation.trackInitColor, centerX,
				centerY + DrawStation.rowSpacing, DrawStation.panel);
		DrawStation.trackLine.setLineName("4+1");
		DrawStation.trackLineList.add(DrawStation.trackLine);
		DrawStation.roadBasicInfo = new RoadBasicInfo();
		DrawStation.roadBasicInfo.setRoadType(DrawStation.roadBasicInfoTypeOfTrackLine);
		DrawStation.roadBasicInfo.setRoadName("4+1");
		//下行
		DrawStation.road_xla_x4la.add(DrawStation.roadBasicInfo);
		DrawStation.road_x4la_xlfa.add(DrawStation.roadBasicInfo);
		//上行
		DrawStation.road_sla_s4la.add(DrawStation.roadBasicInfo);
		DrawStation.road_s4la_slfa.add(DrawStation.roadBasicInfo);
		
		// 股道4+2
		DrawStation.trackLine = new TrackLine(trackLength, DrawStation.lineWidth, DrawStation.trackInitColor, centerX
				+ trackLength, centerY + DrawStation.rowSpacing, DrawStation.panel);
		DrawStation.trackLine.setLineName("4+2");
		DrawStation.trackLineList.add(DrawStation.trackLine);
		DrawStation.roadBasicInfo = new RoadBasicInfo();
		DrawStation.roadBasicInfo.setRoadType(DrawStation.roadBasicInfoTypeOfTrackLine);
		DrawStation.roadBasicInfo.setRoadName("4+2");
		//下行
		DrawStation.road_xla_x4la.add(DrawStation.roadBasicInfo);
		DrawStation.road_x4la_xlfa.add(DrawStation.roadBasicInfo);
		//上行
		DrawStation.road_sla_s4la.add(DrawStation.roadBasicInfo);
		DrawStation.road_s4la_slfa.add(DrawStation.roadBasicInfo);
		
		// 信号机X4
		DrawStation.semaphoreSimpleR = new SemaphoreSimpleR("X4", DrawStation.semaphoreLightDiameter, centerX
				+ trackLength, centerY + DrawStation.rowSpacing
				- DrawStation.semaphoreLightDiameter - ylength, DrawStation.panel);
		DrawStation.semaphoreSimpleRList.add(DrawStation.semaphoreSimpleR);	

		DrawStation.semphoreBasicInfo = new SemphoreBasicInfo();
		DrawStation.semphoreBasicInfo.setSemphoreName("X4");
		DrawStation.semphoreBasicInfo.setSemphoreType("SemaphoreSimpleR");
		DrawStation.semphoreBasicInfoList.add(DrawStation.semphoreBasicInfo);
		
		DrawStation.stationLabel = new StationLabel("X4", DrawStation.labelWidth, DrawStation.labelHeight, centerX + trackLength,
				centerY + DrawStation.rowSpacing + ylength,
				DrawStation.panel);

		// 按钮X4LA
		DrawStation.button_x4la = new StationButton(DrawStation.buttonLength, centerX + 3*trackLength/2, centerY + DrawStation.rowSpacing - 3*ylength/2,
				DrawStation.buttonInitColor).getButton();
		/*
		DrawStation.button_x4la.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//4股道上行接车，或者4股道下行发车
				StationModel.Button_x4laAction();				
			}
		});
		*/
		DrawStation.button_x4la.addActionListener(new ButtonX4LAListener());
		
		DrawStation.panel.add(DrawStation.button_x4la);
		DrawStation.stationLabel = new StationLabel("X4LA", DrawStation.labelWidth + 5, DrawStation.labelHeight,
				centerX + 3*trackLength/2, 
				centerY + DrawStation.rowSpacing -4*ylength, 
				DrawStation.panel);
				
	}
	
	/**
	 * 绘制图形的网格
	 */
	public static void drawGrid(){
		
		PolylineConnection line;
		line = new PolylineConnection();
		line.setStart(new Point(centerX, centerY - 200));
		line.setEnd(new Point(centerX, centerY + 200));
		line.setLineWidth(1);
		line.setForegroundColor(ColorConstants.red);
		DrawStation.panel.add(line);
		
		line = new PolylineConnection();
		line.setStart(new Point(centerX - trackLength, centerY - 200));
		line.setEnd(new Point(centerX - trackLength, centerY + 200));
		line.setLineWidth(1);
		line.setForegroundColor(ColorConstants.blue);
		DrawStation.panel.add(line);
		
		line = new PolylineConnection();
		line.setStart(new Point(centerX - 2*trackLength, centerY - 200));
		line.setEnd(new Point(centerX - 2*trackLength, centerY + 200));
		line.setLineWidth(1);
		line.setForegroundColor(ColorConstants.blue);
		DrawStation.panel.add(line);
		
		line = new PolylineConnection();
		line.setStart(new Point(centerX - 3*trackLength, centerY - 200));
		line.setEnd(new Point(centerX - 3*trackLength, centerY + 200));
		line.setLineWidth(1);
		line.setForegroundColor(ColorConstants.blue);
		DrawStation.panel.add(line);
		
		line = new PolylineConnection();
		line.setStart(new Point(centerX - 4*trackLength, centerY - 200));
		line.setEnd(new Point(centerX - 4*trackLength, centerY + 200));
		line.setLineWidth(1);
		line.setForegroundColor(ColorConstants.blue);
		DrawStation.panel.add(line);
		
		line = new PolylineConnection();
		line.setStart(new Point(centerX - 5*trackLength, centerY - 200));
		line.setEnd(new Point(centerX - 5*trackLength, centerY + 200));
		line.setLineWidth(1);
		line.setForegroundColor(ColorConstants.blue);
		DrawStation.panel.add(line);
		
		line = new PolylineConnection();
		line.setStart(new Point(centerX - 6*trackLength, centerY - 200));
		line.setEnd(new Point(centerX - 6*trackLength, centerY + 200));
		line.setLineWidth(1);
		line.setForegroundColor(ColorConstants.blue);
		DrawStation.panel.add(line);
		
		line = new PolylineConnection();
		line.setStart(new Point(centerX - 7*trackLength, centerY - 200));
		line.setEnd(new Point(centerX - 7*trackLength, centerY + 200));
		line.setLineWidth(1);
		line.setForegroundColor(ColorConstants.blue);
		DrawStation.panel.add(line);
		
		line = new PolylineConnection();
		line.setStart(new Point(centerX - 8*trackLength, centerY - 200));
		line.setEnd(new Point(centerX - 8*trackLength, centerY + 200));
		line.setLineWidth(1);
		line.setForegroundColor(ColorConstants.blue);
		DrawStation.panel.add(line);
		
		line = new PolylineConnection();
		line.setStart(new Point(centerX - 9*trackLength, centerY - 200));
		line.setEnd(new Point(centerX - 9*trackLength, centerY + 200));
		line.setLineWidth(1);
		line.setForegroundColor(ColorConstants.blue);
		DrawStation.panel.add(line);
		
		//--------------
		line = new PolylineConnection();
		line.setStart(new Point(centerX + trackLength, centerY - 200));
		line.setEnd(new Point(centerX + trackLength, centerY + 200));
		line.setLineWidth(1);
		line.setForegroundColor(ColorConstants.blue);
		DrawStation.panel.add(line);
		
		line = new PolylineConnection();
		line.setStart(new Point(centerX + 2*trackLength, centerY - 200));
		line.setEnd(new Point(centerX + 2*trackLength, centerY + 200));
		line.setLineWidth(1);
		line.setForegroundColor(ColorConstants.blue);
		DrawStation.panel.add(line);
		
		line = new PolylineConnection();
		line.setStart(new Point(centerX + 3*trackLength, centerY - 200));
		line.setEnd(new Point(centerX + 3*trackLength, centerY + 200));
		line.setLineWidth(1);
		line.setForegroundColor(ColorConstants.blue);
		DrawStation.panel.add(line);
		
		line = new PolylineConnection();
		line.setStart(new Point(centerX + 4*trackLength, centerY - 200));
		line.setEnd(new Point(centerX + 4*trackLength, centerY + 200));
		line.setLineWidth(1);
		line.setForegroundColor(ColorConstants.blue);
		DrawStation.panel.add(line);
		
		line = new PolylineConnection();
		line.setStart(new Point(centerX + 5*trackLength, centerY - 200));
		line.setEnd(new Point(centerX + 5*trackLength, centerY + 200));
		line.setLineWidth(1);
		line.setForegroundColor(ColorConstants.blue);
		DrawStation.panel.add(line);
		
		line = new PolylineConnection();
		line.setStart(new Point(centerX + 6*trackLength, centerY - 200));
		line.setEnd(new Point(centerX + 6*trackLength, centerY + 200));
		line.setLineWidth(1);
		line.setForegroundColor(ColorConstants.blue);
		DrawStation.panel.add(line);

		line = new PolylineConnection();
		line.setStart(new Point(centerX + 7*trackLength, centerY - 200));
		line.setEnd(new Point(centerX + 7*trackLength, centerY + 200));
		line.setLineWidth(1);
		line.setForegroundColor(ColorConstants.blue);
		DrawStation.panel.add(line);
		
		line = new PolylineConnection();
		line.setStart(new Point(centerX + 8*trackLength, centerY - 200));
		line.setEnd(new Point(centerX + 8*trackLength, centerY + 200));
		line.setLineWidth(1);
		line.setForegroundColor(ColorConstants.blue);
		DrawStation.panel.add(line);
		
		line = new PolylineConnection();
		line.setStart(new Point(centerX + 9*trackLength, centerY - 200));
		line.setEnd(new Point(centerX + 9*trackLength, centerY + 200));
		line.setLineWidth(1);
		line.setForegroundColor(ColorConstants.blue);
		DrawStation.panel.add(line);
		
	}
	
	
}
