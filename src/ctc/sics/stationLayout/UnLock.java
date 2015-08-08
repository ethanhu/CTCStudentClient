package ctc.sics.stationLayout;

import java.util.ArrayList;
import java.util.Timer;
import org.eclipse.draw2d.Button;
import org.eclipse.swt.widgets.Display;

import ctc.sics.SicsMain;

import ctc.sics.figure.*;
import ctc.sics.road.*;

public class UnLock {
	
	public static ArrayList<RoadBasicInfo> roadXLA = new ArrayList<RoadBasicInfo>(); //下行进站道路
	public static ArrayList<RoadBasicInfo> roadSLA =  new ArrayList<RoadBasicInfo>(); //上行进站道路
		
	public static int second = 1000;
	
	public static ArrayList<RoadBasicInfo> roadListLA;
	public static int lenRoadListLA;
	public static int indexLA;
	public static boolean flagLA = true;
	
	//动态解锁(下行进站)
	public static void unLockLA(ArrayList<RoadBasicInfo> roadList, int type1, Button button1, int b1, Button button2, int b2, String sephoreName1, String sephoreName2){
			
		//下行接车设计到的各个操作按钮
		DrawStation.button_xla.setBackgroundColor(DrawStation.buttonInitColor);
		DrawStation.b_xla = 0;
		
		//下行接车涉及到的各个股道按钮		
		DrawStation.button_s1la.setBackgroundColor(DrawStation.buttonInitColor);
		DrawStation.b_s1la = 0;
		DrawStation.button_s2la.setBackgroundColor(DrawStation.buttonInitColor);
		DrawStation.b_s2la = 0;
		DrawStation.button_s3la.setBackgroundColor(DrawStation.buttonInitColor);
		DrawStation.b_s3la = 0;
		DrawStation.button_s4la.setBackgroundColor(DrawStation.buttonInitColor);
		DrawStation.b_s4la = 0;

		ButtonSemphoreModel.semphoreColorModel("XLX", "red");
		
		roadListLA = roadList;
		
		if(roadListLA != null){
			
			lenRoadListLA = roadListLA.size();
			
			if( lenRoadListLA >= 5){
				
				flagLA = true;
				
				final Timer timerLA = new Timer();
				int timestamp = 3000;	
									
				timerLA.schedule(new java.util.TimerTask() {
				
					public void run() {
						if(flagLA){
						for (int i = 0; i < lenRoadListLA - 3; i++) {
							
							RoadBasicInfo rBasicInfo = roadListLA.get(i);
							String roadTypeLA = rBasicInfo.getRoadType();
							String roadNameLA = rBasicInfo.getRoadName();
							
							indexLA = UnLock.getIndexID(roadTypeLA, roadNameLA);
							
							System.out.println("i=" + i + "  indexLA = " + indexLA);
							
							if (indexLA != -1) {

								System.out.println( i + " :" + " 类型：" + roadTypeLA + " 名称：" + roadNameLA);

								if (roadTypeLA.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTrackLine)) {

									Display.getDefault().syncExec( //先变红
											new Runnable() {
												public void run() {
													DrawStation.trackLineList.get(indexLA).setTrackLineStatus("red");														
													//DrawStation.trackLineList.get(indexLA).setStatus(1);
													//timer.cancel();
												}
											});
									try {
										Thread.sleep(second);
										//System.out.println("Thread:");
									} catch (InterruptedException e) {
										// break;
									}
									
									Display.getDefault().syncExec( //再变蓝
											new Runnable() {
												public void run() {
													DrawStation.trackLineList.get(indexLA).setTrackLineStatus("blue");
													//DrawStation.trackLineList.get(indexLA).setStatus(0);
													//timer.cancel();
												}
											});

								} else if (roadTypeLA
										.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutDouble)) {
									
									Display.getDefault().syncExec( //先变红色
											new Runnable() {
												public void run() {
													int status = DrawStation.turnoutDoubleList.get(indexLA).getTurnoutStatus();
													if(status == 1){														
														DrawStation.turnoutDoubleList.get(indexLA).setColorStatus("redZS");
														//timer.cancel();
													}else {
														DrawStation.turnoutDoubleList.get(indexLA).setColorStatus("redFL");														
														//timer.cancel();
													}
													//DrawStation.turnoutDoubleList.get(indexLA).setStatus(1);
												}
											});
									try {
										Thread.sleep(second);
									} catch (InterruptedException e) {
										// break;
									}
									
									Display.getDefault().syncExec( //再变蓝
											new Runnable() {
												public void run() {
													
													int status = DrawStation.turnoutDoubleList.get(indexLA).getTurnoutStatus();
													if(status == 1){														
														DrawStation.turnoutDoubleList.get(indexLA).setColorStatus("blueZS");
													}else {
														DrawStation.turnoutDoubleList.get(indexLA).setColorStatus("blueFL");														
													}
													//DrawStation.turnoutDoubleList.get(indexLA).setStatus(0);
												}
											});

								} else if (roadTypeLA
										.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLS)) {

									Display.getDefault().syncExec( //先变红色
											new Runnable() {
												public void run() {
																								
													int status = DrawStation.turnoutLSList.get(indexLA).getTurnoutStatus();
													if(status == 1){
														DrawStation.turnoutLSList.get(indexLA).setColorStatus("redZ");
													}else {
														DrawStation.turnoutLSList.get(indexLA).setColorStatus("redF");
													}																										
													//DrawStation.turnoutLSList.get(indexLA).setStatus(1);
												}
											});
									try {
										Thread.sleep(second);
									} catch (InterruptedException e) {
										// break;
									}

									Display.getDefault().syncExec( //再变蓝
											new Runnable() {
												public void run() {
													
													int status = DrawStation.turnoutLSList.get(indexLA).getTurnoutStatus();
													if(status == 1){
														DrawStation.turnoutLSList.get(indexLA).setColorStatus("blueZ");
													}else {
														DrawStation.turnoutLSList.get(indexLA).setColorStatus("blueF");
													}																										
													//DrawStation.turnoutLSList.get(indexLA).setStatus(0);
													//timer.cancel();
												}
											});
									
								} else if (roadTypeLA
										.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLX)) {

									Display.getDefault().syncExec( //先变红色
											new Runnable() {
												public void run() {
													int status = DrawStation.turnoutLXList.get(indexLA).getTurnoutStatus();
													if(status == 1){
														DrawStation.turnoutLXList.get(indexLA).setColorStatus("redZ");
													}else {
														DrawStation.turnoutLXList.get(indexLA).setColorStatus("redF");
													}													
													//DrawStation.turnoutLXList.get(indexLA).setStatus(1);
													//timer.cancel();
												}
											});
									try {
										Thread.sleep(second);
									} catch (InterruptedException e) {
										// break;
									}

									Display.getDefault().syncExec( //再变蓝
											new Runnable() {
												public void run() {
													int status = DrawStation.turnoutLXList.get(indexLA).getTurnoutStatus();
													if(status == 1){
														DrawStation.turnoutLXList.get(indexLA).setColorStatus("blueZ");
													}else {
														DrawStation.turnoutLXList.get(indexLA).setColorStatus("blueF");
													}													
													//DrawStation.turnoutLXList.get(indexLA).setStatus(0);
													//timer.cancel();
												}
											});
									
								} else if (roadTypeLA
										.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRS)) {

									Display.getDefault().syncExec( //先变红色
											new Runnable() {
												public void run() {
													int status = DrawStation.turnoutRSList.get(indexLA).getTurnoutStatus();
													if(status == 1){
														DrawStation.turnoutRSList.get(indexLA).setColorStatus("redZ");
													}else {
														DrawStation.turnoutRSList.get(indexLA).setColorStatus("redF");
													}														
													//DrawStation.turnoutRSList.get(indexLA).setStatus(1);
													//timer.cancel();
												}
											});
									try {
										Thread.sleep(second);
									} catch (InterruptedException e) {
										// break;
									}

									Display.getDefault().syncExec( //再变蓝
											new Runnable() {
												public void run() {
													int status = DrawStation.turnoutRSList.get(indexLA).getTurnoutStatus();
													if(status == 1){
														DrawStation.turnoutRSList.get(indexLA).setColorStatus("blueZ");
													}else {
														DrawStation.turnoutRSList.get(indexLA).setColorStatus("blueF");
													}														
													//DrawStation.turnoutRSList.get(indexLA).setStatus(0);
													//timer.cancel();
												}
											});
									
								} else if (roadTypeLA
										.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRX)) {

									Display.getDefault().syncExec( //先变红色
											new Runnable() {
												public void run() {
													int status = DrawStation.turnoutRXList.get(indexLA).getTurnoutStatus();
													if(status == 1){
														DrawStation.turnoutRXList.get(indexLA).setColorStatus("redZ");
													}else {
														DrawStation.turnoutRXList.get(indexLA).setColorStatus("redF");
													}															
													//DrawStation.turnoutRXList.get(indexLA).setStatus(1);
													//timer.cancel();
												}
											});
									try {
										Thread.sleep(second);
									} catch (InterruptedException e) {
										// break;
									}

									Display.getDefault().syncExec( //再变蓝
											new Runnable() {
												public void run() {
													int status = DrawStation.turnoutRXList.get(indexLA).getTurnoutStatus();
													if(status == 1){
														DrawStation.turnoutRXList.get(indexLA).setColorStatus("blueZ");
													}else {
														DrawStation.turnoutRXList.get(indexLA).setColorStatus("blueF");
													}															
													//DrawStation.turnoutRXList.get(indexLA).setStatus(0);
													//timer.cancel();
												}
											});										
								}

							} else {
								System.out.println("根据相应的类型和名称找不到相应的indexID");
							}
						}//end of for1

						for (int i =  lenRoadListLA - 3; i < lenRoadListLA -1; i++) { //绘制最后3站
							
							System.out.println("绘制最后3站 i = " + i);
							
							RoadBasicInfo rBasicInfo = roadListLA.get(i);
							String roadTypeLA = rBasicInfo.getRoadType();
							String roadNameLA = rBasicInfo.getRoadName();
							
							System.out.println( i + " :" + " 类型：" + roadTypeLA + " 名称：" + roadNameLA);
							
							indexLA = UnLock.getIndexID(roadTypeLA, roadNameLA);
							
							System.out.println( i + " ---1" );
							
							if (indexLA != -1) {							

								if (roadTypeLA
										.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTrackLine)) {

									Display.getDefault().syncExec(
											new Runnable() {
												public void run() {
													DrawStation.trackLineList.get(indexLA).setTrackLineStatus("red");														
													//DrawStation.trackLineList.get(indexLA).setStatus(1);
													//timer.cancel();
												}
											});
									try {
										Thread.sleep(second);
									} catch (InterruptedException e) {
										// break;
									}

								} else {
									System.out.println("根据相应的类型和名称找不到相应的indexID");
								}
							}// end of if1

						}//end of for2				
						
						flagLA = false;
						roadListLA = null;
						lenRoadListLA = 0;
						indexLA = -1;
						timerLA.cancel();
						}else {
							System.out.println("--flagLA = false_解锁失败--");
						}						
					}// run
					
				}, 0, timestamp);	
				
			}else{
				System.out.println("进路解锁路径的长度不符合要求，lenRoadListLA = " + lenRoadListLA);
			}			
		}else{
			System.out.println("进路解锁路径为空！");
		}
				
	}
	
	public static ArrayList<RoadBasicInfo> roadListLAS;
	public static int lenRoadListLAS;
	public static int indexLAS;
	public static boolean flagLAS = true;
	
	//动态解锁(上行进站)
	public static void unLockLAS(ArrayList<RoadBasicInfo> roadList, int type1, Button button1, int b1, Button button2, int b2, String sephoreName1, String sephoreName2){
		
		//上行接车涉及到的各个操作按钮
		DrawStation.button_sla.setBackgroundColor(DrawStation.buttonInitColor);
		DrawStation.b_sla = 0;
	
		//上行接车涉及到的各个股道按钮
		DrawStation.button_x1la.setBackgroundColor(DrawStation.buttonInitColor);
		DrawStation.b_x1la = 0;
		DrawStation.button_x2la.setBackgroundColor(DrawStation.buttonInitColor);
		DrawStation.b_x2la = 0;
		DrawStation.button_x3la.setBackgroundColor(DrawStation.buttonInitColor);
		DrawStation.b_x3la = 0;
		DrawStation.button_x4la.setBackgroundColor(DrawStation.buttonInitColor);
		DrawStation.b_x4la = 0;
		
		//ButtonSemphoreModel.allSemphoreColorModel("red");
		ButtonSemphoreModel.semphoreColorModel("SLX", "red");
		
		roadListLAS = roadList;
		
		if(roadListLAS != null){
			
			lenRoadListLAS = roadListLAS.size();
			
			if( lenRoadListLAS >= 5){
				
				flagLAS = true;
				
				final Timer timerLAS = new Timer();
				int timestamp = 3000;	
									
				timerLAS.schedule(new java.util.TimerTask() {
				
					public void run() {
						if(flagLAS){
						for (int i = 0; i < lenRoadListLAS - 3; i++) {
							
							RoadBasicInfo rBasicInfo = roadListLAS.get(i);
							String roadTypeLA = rBasicInfo.getRoadType();
							String roadNameLA = rBasicInfo.getRoadName();
							
							indexLAS = UnLock.getIndexID(roadTypeLA, roadNameLA);
							
							System.out.println("i=" + i + "  indexLA = " + indexLAS);
							
							if (indexLAS != -1) {

								System.out.println( i + " :" + " 类型：" + roadTypeLA + " 名称：" + roadNameLA);

								if (roadTypeLA.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTrackLine)) {

									Display.getDefault().syncExec( //先变红
											new Runnable() {
												public void run() {
													DrawStation.trackLineList.get(indexLAS).setTrackLineStatus("red");														
													//DrawStation.trackLineList.get(indexLAS).setStatus(1);
													//timer.cancel();
												}
											});
									try {
										Thread.sleep(second);
										//System.out.println("Thread:");
									} catch (InterruptedException e) {
										// break;
									}
									
									Display.getDefault().syncExec( //再变蓝
											new Runnable() {
												public void run() {
													DrawStation.trackLineList.get(indexLAS).setTrackLineStatus("blue");
													//DrawStation.trackLineList.get(indexLAS).setStatus(0);
													//timer.cancel();
												}
											});

								} else if (roadTypeLA
										.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutDouble)) {

									Display.getDefault().syncExec( //先变红色
											new Runnable() {
												public void run() {
													int status = DrawStation.turnoutDoubleList.get(indexLAS).getTurnoutStatus();
													if(status == 1){
														DrawStation.turnoutDoubleList.get(indexLAS).setColorStatus("redZX");
													}else {
														DrawStation.turnoutDoubleList.get(indexLAS).setColorStatus("redFL");
													}
													//DrawStation.turnoutDoubleList.get(indexLAS).setStatus(1);
													//timer.cancel();
												}
											});
									try {
										Thread.sleep(second);
									} catch (InterruptedException e) {
										// break;
									}
									
									Display.getDefault().syncExec( //再变蓝
											new Runnable() {
												public void run() {
													int status = DrawStation.turnoutDoubleList.get(indexLAS).getTurnoutStatus();
													if(status == 1){
														DrawStation.turnoutDoubleList.get(indexLAS).setColorStatus("blueZX");
													}else {
														DrawStation.turnoutDoubleList.get(indexLAS).setColorStatus("blueFL");
													}
													//DrawStation.turnoutDoubleList.get(indexLAS).setStatus(0);
													//timer.cancel();
												}
											});

								} else if (roadTypeLA
										.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLS)) {

									Display.getDefault().syncExec( //先变红色
											new Runnable() {
												public void run() {
													int status = DrawStation.turnoutLSList.get(indexLAS).getTurnoutStatus();
													if(status == 1){
														DrawStation.turnoutLSList.get(indexLAS).setColorStatus("redZ");
													}else {
														DrawStation.turnoutLSList.get(indexLAS).setColorStatus("redF");
													}																										
													//DrawStation.turnoutLSList.get(indexLAS).setStatus(1);
													//timer.cancel();
												}
											});
									try {
										Thread.sleep(second);
									} catch (InterruptedException e) {
										// break;
									}

									Display.getDefault().syncExec( //再变蓝
											new Runnable() {
												public void run() {
													int status = DrawStation.turnoutLSList.get(indexLAS).getTurnoutStatus();
													if(status == 1){
														DrawStation.turnoutLSList.get(indexLAS).setColorStatus("blueZ");
													}else {
														DrawStation.turnoutLSList.get(indexLAS).setColorStatus("blueF");
													}																																								
													//DrawStation.turnoutLSList.get(indexLAS).setStatus(0);
													//timer.cancel();
												}
											});
									
								} else if (roadTypeLA
										.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLX)) {

									Display.getDefault().syncExec( //先变红色
											new Runnable() {
												public void run() {
													int status = DrawStation.turnoutLXList.get(indexLAS).getTurnoutStatus();
													if(status == 1){
														DrawStation.turnoutLXList.get(indexLAS).setColorStatus("redZ");
													}else {
														DrawStation.turnoutLXList.get(indexLAS).setColorStatus("redF");
													}													
													//DrawStation.turnoutLXList.get(indexLAS).setStatus(1);
													//timer.cancel();
												}
											});
									try {
										Thread.sleep(second);
									} catch (InterruptedException e) {
										// break;
									}

									Display.getDefault().syncExec( //再变蓝
											new Runnable() {
												public void run() {
													int status = DrawStation.turnoutLXList.get(indexLAS).getTurnoutStatus();
													if(status == 1){
														DrawStation.turnoutLXList.get(indexLAS).setColorStatus("blueZ");
													}else {
														DrawStation.turnoutLXList.get(indexLAS).setColorStatus("blueF");
													}															
													//DrawStation.turnoutLXList.get(indexLAS).setStatus(0);
													//timer.cancel();
												}
											});
									
								} else if (roadTypeLA
										.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRS)) {

									Display.getDefault().syncExec( //先变红色
											new Runnable() {
												public void run() {
													int status = DrawStation.turnoutRSList.get(indexLAS).getTurnoutStatus();
													if(status == 1){
														DrawStation.turnoutRSList.get(indexLAS).setColorStatus("redZ");
													}else {
														DrawStation.turnoutRSList.get(indexLAS).setColorStatus("redF");
													}														
													//DrawStation.turnoutRSList.get(indexLAS).setStatus(1);
													//timer.cancel();
												}
											});
									try {
										Thread.sleep(second);
									} catch (InterruptedException e) {
										// break;
									}

									Display.getDefault().syncExec( //再变蓝
											new Runnable() {
												public void run() {
													int status = DrawStation.turnoutRSList.get(indexLAS).getTurnoutStatus();
													if(status == 1){
														DrawStation.turnoutRSList.get(indexLAS).setColorStatus("blueZ");
													}else {
														DrawStation.turnoutRSList.get(indexLAS).setColorStatus("blueF");
													}													
													//DrawStation.turnoutRSList.get(indexLAS).setStatus(0);
													//timer.cancel();
												}
											});
									
								} else if (roadTypeLA
										.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRX)) {

									Display.getDefault().syncExec( //先变红色
											new Runnable() {
												public void run() {
													int status = DrawStation.turnoutRXList.get(indexLAS).getTurnoutStatus();
													if(status == 1){
														DrawStation.turnoutRXList.get(indexLAS).setColorStatus("redZ");
													}else {
														DrawStation.turnoutRXList.get(indexLAS).setColorStatus("redF");
													}															
													//DrawStation.turnoutRXList.get(indexLAS).setStatus(1);
													//timer.cancel();
												}
											});
									try {
										Thread.sleep(second);
									} catch (InterruptedException e) {
										// break;
									}

									Display.getDefault().syncExec( //再变蓝
											new Runnable() {
												public void run() {
													int status = DrawStation.turnoutRXList.get(indexLAS).getTurnoutStatus();
													if(status == 1){
														DrawStation.turnoutRXList.get(indexLAS).setColorStatus("blueZ");
													}else {
														DrawStation.turnoutRXList.get(indexLAS).setColorStatus("blueF");
													}															
													//DrawStation.turnoutRXList.get(indexLAS).setStatus(0);
													//timer.cancel();
												}
											});										
								}

							} else {
								System.out.println("根据相应的类型和名称找不到相应的indexID");
							}
						}//end of for1

						for (int i =  lenRoadListLAS - 3; i < lenRoadListLAS -1; i++) { //绘制最后3站
							
							System.out.println("绘制最后3站 i = " + i);
							
							RoadBasicInfo rBasicInfo = roadListLAS.get(i);
							String roadTypeLA = rBasicInfo.getRoadType();
							String roadNameLA = rBasicInfo.getRoadName();
							
							System.out.println( i + " :" + " 类型：" + roadTypeLA + " 名称：" + roadNameLA);
							
							indexLAS = UnLock.getIndexID(roadTypeLA, roadNameLA);
							
							System.out.println( i + " ---1" );
							
							if (indexLAS != -1) {							

								if (roadTypeLA
										.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTrackLine)) {

									Display.getDefault().syncExec(
											new Runnable() {
												public void run() {
													DrawStation.trackLineList.get(indexLAS).setTrackLineStatus("red");														
													//DrawStation.trackLineList.get(indexLAS).setStatus(1);
													//timer.cancel();
												}
											});
									try {
										Thread.sleep(second);
									} catch (InterruptedException e) {
										// break;
									}

								} else {
									System.out.println("根据相应的类型和名称找不到相应的indexID");
								}
							}// end of if1

						}//end of for2						
						
						flagLAS = false;
						roadListLAS = null;
						lenRoadListLAS = 0;
						indexLAS = -1;
						timerLAS.cancel();
						}else {
							System.out.println("--flagLAS = false_解锁失败--");
						}
					}// run
				}, 0, timestamp);
				
				//timer.cancel();
				
			}else{
				System.out.println("进路解锁路径的长度不符合要求，lenRoadListLAS = " + lenRoadListLAS);
			}			
		}else{
			System.out.println("进路解锁路径为空！");
		}
				
	}
		
	public static ArrayList<RoadBasicInfo> roadListFA;
	public static int lenRoadListFA;
	public static int indexFA;
	public static boolean flagFA = true;
	
	//动态解锁(下行发车)	
	public static void unLockFA(ArrayList<RoadBasicInfo> roadList, int type1, Button button1, int b1, Button button2, int b2, String sephoreName1, String sephoreName2){
				
		//下行发车涉及到的各个操作按钮
		DrawStation.button_xta.setBackgroundColor(DrawStation.buttonInitColor);
		DrawStation.b_xta = 0;
		DrawStation.button_xlfa.setBackgroundColor(DrawStation.buttonInitColor);
		DrawStation.b_xlfa = 0;
		//下行发车涉及到的各个股道按钮
		DrawStation.button_x1la.setBackgroundColor(DrawStation.buttonInitColor);
		DrawStation.b_x1la = 0;
		DrawStation.button_x2la.setBackgroundColor(DrawStation.buttonInitColor);
		DrawStation.b_x2la = 0;
		DrawStation.button_x3la.setBackgroundColor(DrawStation.buttonInitColor);
		DrawStation.b_x3la = 0;
		DrawStation.button_x4la.setBackgroundColor(DrawStation.buttonInitColor);
		DrawStation.b_x4la = 0;
		
		ButtonSemphoreModel.allSemphoreColorModel("red");
		
		roadListFA = roadList;
		
		if(roadListFA != null){
			
			lenRoadListFA = roadListFA.size();
			
			if( lenRoadListFA >= 5){
				
				flagFA = true;
				
				final Timer timerFA = new Timer();
				int timestamp = 3000;	
									
				timerFA.schedule(new java.util.TimerTask() {
				
					public void run() {
					if(flagFA){
						for (int i = 1; i < lenRoadListFA; i++) {
							
							RoadBasicInfo rBasicInfo = roadListFA.get(i);
							String roadTypeLA = rBasicInfo.getRoadType();
							String roadNameLA = rBasicInfo.getRoadName();
							
							indexFA = UnLock.getIndexID(roadTypeLA, roadNameLA);
							
							System.out.println("i=" + i + "  indexLA = " + indexFA);
							
							if (indexFA != -1) {

//								System.out.println( i + " :" + " 类型：" + roadTypeLA + " 名称：" + roadNameLA);

								if (roadTypeLA.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTrackLine)) {
									
									Display.getDefault().syncExec( //先变红
											new Runnable() {
												public void run() {
													DrawStation.trackLineList.get(indexFA).setTrackLineStatus("red");														
													//DrawStation.trackLineList.get(indexFA).setStatus(1);
													//timer.cancel();
												}
											});
									try {
										Thread.sleep(second);
										//System.out.println("Thread:");
									} catch (InterruptedException e) {
										// break;
									}
									
									Display.getDefault().syncExec( //再变蓝
											new Runnable() {
												public void run() {
													DrawStation.trackLineList.get(indexFA).setTrackLineStatus("blue");
													//DrawStation.trackLineList.get(indexFA).setStatus(0);
													//timer.cancel();
												}
											});

								} else if (roadTypeLA
										.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutDouble)) {

									Display.getDefault().syncExec( //先变红色
											new Runnable() {
												public void run() {
													int status = DrawStation.turnoutDoubleList.get(indexFA).getTurnoutStatus();
													if(status == 1){
														DrawStation.turnoutDoubleList.get(indexFA).setColorStatus("redZS");
													}else {
														DrawStation.turnoutDoubleList.get(indexFA).setColorStatus("redFR");
													}
													//DrawStation.turnoutDoubleList.get(indexFA).setStatus(1);
													//timer.cancel();
												}
											});
									try {
										Thread.sleep(second);
									} catch (InterruptedException e) {
										// break;
									}
									
									Display.getDefault().syncExec( //再变蓝
											new Runnable() {
												public void run() {
													int status = DrawStation.turnoutDoubleList.get(indexFA).getTurnoutStatus();
													if(status == 1){
														DrawStation.turnoutDoubleList.get(indexFA).setColorStatus("blueZS");
													}else {
														DrawStation.turnoutDoubleList.get(indexFA).setColorStatus("blueFR");
													}
													//DrawStation.turnoutDoubleList.get(indexFA).setStatus(0);
													//timer.cancel();
												}
											});

								} else if (roadTypeLA
										.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLS)) {

									Display.getDefault().syncExec( //先变红色
											new Runnable() {
												public void run() {
													int status = DrawStation.turnoutLSList.get(indexFA).getTurnoutStatus();
													if(status == 1){
														DrawStation.turnoutLSList.get(indexFA).setColorStatus("redZ");
													}else {
														DrawStation.turnoutLSList.get(indexFA).setColorStatus("redF");
													}																										
													//DrawStation.turnoutLSList.get(indexFA).setStatus(1);
													//timer.cancel();
												}
											});
									try {
										Thread.sleep(second);
									} catch (InterruptedException e) {
										// break;
									}

									Display.getDefault().syncExec( //再变蓝
											new Runnable() {
												public void run() {
													int status = DrawStation.turnoutLSList.get(indexFA).getTurnoutStatus();
													if(status == 1){
														DrawStation.turnoutLSList.get(indexFA).setColorStatus("blueZ");
													}else {
														DrawStation.turnoutLSList.get(indexFA).setColorStatus("blueF");
													}																																									
													//DrawStation.turnoutLSList.get(indexFA).setStatus(0);
													//timer.cancel();
												}
											});
									
								} else if (roadTypeLA
										.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLX)) {

									Display.getDefault().syncExec( //先变红色
											new Runnable() {
												public void run() {
													int status = DrawStation.turnoutLXList.get(indexFA).getTurnoutStatus();
													if(status == 1){
														DrawStation.turnoutLXList.get(indexFA).setColorStatus("redZ");
													}else {
														DrawStation.turnoutLXList.get(indexFA).setColorStatus("redF");
													}													
													//DrawStation.turnoutLXList.get(indexFA).setStatus(1);
													//timer.cancel();
												}
											});
									try {
										Thread.sleep(second);
									} catch (InterruptedException e) {
										// break;
									}

									Display.getDefault().syncExec( //再变蓝
											new Runnable() {
												public void run() {
													int status = DrawStation.turnoutLXList.get(indexFA).getTurnoutStatus();
													if(status == 1){
														DrawStation.turnoutLXList.get(indexFA).setColorStatus("blueZ");
													}else {
														DrawStation.turnoutLXList.get(indexFA).setColorStatus("blueF");
													}															
													//DrawStation.turnoutLXList.get(indexFA).setStatus(0);
													//timer.cancel();
												}
											});
									
								} else if (roadTypeLA
										.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRS)) {

									Display.getDefault().syncExec( //先变红色
											new Runnable() {
												public void run() {
													int status = DrawStation.turnoutRSList.get(indexFA).getTurnoutStatus();
													if(status == 1){
														DrawStation.turnoutRSList.get(indexFA).setColorStatus("redZ");
													}else {
														DrawStation.turnoutRSList.get(indexFA).setColorStatus("redF");
													}														
													//DrawStation.turnoutRSList.get(indexFA).setStatus(1);
													//timer.cancel();
												}
											});
									try {
										Thread.sleep(second);
									} catch (InterruptedException e) {
										// break;
									}

									Display.getDefault().syncExec( //再变蓝
											new Runnable() {
												public void run() {
													int status = DrawStation.turnoutRSList.get(indexFA).getTurnoutStatus();
													if(status == 1){
														DrawStation.turnoutRSList.get(indexFA).setColorStatus("blueZ");
													}else {
														DrawStation.turnoutRSList.get(indexFA).setColorStatus("blueF");
													}														
													//DrawStation.turnoutRSList.get(indexFA).setStatus(0);
													//timer.cancel();
												}
											});
									
								} else if (roadTypeLA
										.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRX)) {

									Display.getDefault().syncExec( //先变红色
											new Runnable() {
												public void run() {
													int status = DrawStation.turnoutRXList.get(indexFA).getTurnoutStatus();
													if(status == 1){
														DrawStation.turnoutRXList.get(indexFA).setColorStatus("redZ");
													}else {
														DrawStation.turnoutRXList.get(indexFA).setColorStatus("redF");
													}															
													//DrawStation.turnoutRXList.get(indexFA).setStatus(1);
													//timer.cancel();
												}
											});
									try {
										Thread.sleep(second);
									} catch (InterruptedException e) {
										// break;
									}

									Display.getDefault().syncExec( //再变蓝
											new Runnable() {
												public void run() {
													int status = DrawStation.turnoutRXList.get(indexFA).getTurnoutStatus();
													if(status == 1){
														DrawStation.turnoutRXList.get(indexFA).setColorStatus("blueZ");
													}else {
														DrawStation.turnoutRXList.get(indexFA).setColorStatus("blueF");
													}															
													//DrawStation.turnoutRXList.get(indexFA).setStatus(0);
													//timer.cancel();
												}
											});										
								}

							}else {
								System.out.println("根据相应的类型和名称找不到相应的indexID");
							}
						}//end of for1
						
						//下行第一离去
						Display.getDefault().syncExec( //先变红色
								new Runnable() {
									public void run() {
										DrawStation.trackLineXLFA1.setTrackLineStatus("red");														
										//DrawStation.trackLineXLFA1.setStatus(1);
									}
								});
						try {
							Thread.sleep(second);
						} catch (InterruptedException e) {
							// break;
						}

						Display.getDefault().syncExec( //再变蓝
								new Runnable() {
									public void run() {
										DrawStation.trackLineXLFA1.setTrackLineStatus("blue");														
										//DrawStation.trackLineXLFA1.setStatus(0);
									}
								});										
					
						
						//下行第二离去
						Display.getDefault().syncExec( //先变红色
								new Runnable() {
									public void run() {
										DrawStation.trackLineXLFA2.setTrackLineStatus("red");														
										//DrawStation.trackLineXLFA2.setStatus(1);
									}
								});
						try {
							Thread.sleep(second);
						} catch (InterruptedException e) {
							// break;
						}

						Display.getDefault().syncExec( //再变蓝
								new Runnable() {
									public void run() {
										DrawStation.trackLineXLFA2.setTrackLineStatus("blue");														
										//DrawStation.trackLineXLFA2.setStatus(0);
									}
								});		
						
						
						
						flagFA = false;
						roadListFA = null;
						lenRoadListFA = -1;
						indexFA = -1;
						timerFA.cancel();
					}else {
						System.out.println("--flagFA = false_解锁失败--");
					}
					}// run
				}, 0, timestamp);
				
				//timer.cancel();
				
			}else{
				System.out.println("进路解锁路径的长度不符合要求，lenRoadListLA = " + lenRoadListFA);
			}			
		}else{
			System.out.println("进路解锁路径为空！");
		}
		
		
	}
		
	
	public static ArrayList<RoadBasicInfo> roadListFAS;
	public static int lenRoadListFAS;
	public static int indexFAS;
	public static boolean flagFAS = true;	
	//动态解锁(上行出站)
	public static void unLockFAS(ArrayList<RoadBasicInfo> roadList, int type1, Button button1, int b1, Button button2, int b2, String sephoreName1, String sephoreName2){
		
		//上行发车涉及到的各个操作按钮
		DrawStation.button_sta.setBackgroundColor(DrawStation.buttonInitColor);
		DrawStation.b_sta = 0;
		DrawStation.button_slfa.setBackgroundColor(DrawStation.buttonInitColor);
		DrawStation.b_slfa = 0;
		//上行发车涉及到的各个股道按钮
		DrawStation.button_s1la.setBackgroundColor(DrawStation.buttonInitColor);
		DrawStation.b_s1la = 0;
		DrawStation.button_s2la.setBackgroundColor(DrawStation.buttonInitColor);
		DrawStation.b_s2la = 0;
		DrawStation.button_s3la.setBackgroundColor(DrawStation.buttonInitColor);
		DrawStation.b_s3la = 0;
		DrawStation.button_s4la.setBackgroundColor(DrawStation.buttonInitColor);
		DrawStation.b_s4la = 0;

		ButtonSemphoreModel.allSemphoreColorModel("red");
		
		
		roadListFAS = roadList;
		
		if(roadListFAS != null){
			
			lenRoadListFAS = roadListFAS.size();
			
			if( lenRoadListFAS >= 5){
				
				flagFAS = true;
				
				final Timer timerFAS = new Timer();
				int timestamp = 3000;	
									
				timerFAS.schedule(new java.util.TimerTask() {
				
					public void run() {
						if(flagFAS){
						for (int i = 1; i < lenRoadListFAS; i++) {
							
							RoadBasicInfo rBasicInfo = roadListFAS.get(i);
							String roadTypeLA = rBasicInfo.getRoadType();
							String roadNameLA = rBasicInfo.getRoadName();
							
							indexFAS = UnLock.getIndexID(roadTypeLA, roadNameLA);
							
							System.out.println("i=" + i + "  indexLA = " + indexFAS);
							
							if (indexFAS != -1) {

								System.out.println( i + " :" + " 类型：" + roadTypeLA + " 名称：" + roadNameLA);

								if (roadTypeLA.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTrackLine)) {

									Display.getDefault().syncExec( //先变红
											new Runnable() {
												public void run() {
													DrawStation.trackLineList.get(indexFAS).setTrackLineStatus("red");														
													//DrawStation.trackLineList.get(indexFAS).setStatus(1);
													//timer.cancel();
												}
											});
									try {
										Thread.sleep(second);
										//System.out.println("Thread:");
									} catch (InterruptedException e) {
										// break;
									}
									
									Display.getDefault().syncExec( //再变蓝
											new Runnable() {
												public void run() {
													DrawStation.trackLineList.get(indexFAS).setTrackLineStatus("blue");
													//DrawStation.trackLineList.get(indexFAS).setStatus(0);
													//timer.cancel();
												}
											});

								} else if (roadTypeLA
										.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutDouble)) {

									Display.getDefault().syncExec( //先变红色
											new Runnable() {
												public void run() {
													int status = DrawStation.turnoutDoubleList.get(indexFAS).getTurnoutStatus();
													if(status == 1){
														DrawStation.turnoutDoubleList.get(indexFAS).setColorStatus("redZX");
													}else {
														DrawStation.turnoutDoubleList.get(indexFAS).setColorStatus("redFR");
													}
													//DrawStation.turnoutDoubleList.get(indexFAS).setStatus(1);
													//timer.cancel();
												}
											});
									try {
										Thread.sleep(second);
									} catch (InterruptedException e) {
										// break;
									}
									
									Display.getDefault().syncExec( //再变蓝
											new Runnable() {
												public void run() {
													int status = DrawStation.turnoutDoubleList.get(indexFAS).getTurnoutStatus();
													if(status == 1){
														DrawStation.turnoutDoubleList.get(indexFAS).setColorStatus("blueZX");
													}else {
														DrawStation.turnoutDoubleList.get(indexFAS).setColorStatus("blueFR");
													}
													//DrawStation.turnoutDoubleList.get(indexFAS).setStatus(0);
													//timer.cancel();
												}
											});

								} else if (roadTypeLA
										.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLS)) {

									Display.getDefault().syncExec( //先变红色
											new Runnable() {
												public void run() {
													int status = DrawStation.turnoutLSList.get(indexFAS).getTurnoutStatus();
													if(status == 1){
														DrawStation.turnoutLSList.get(indexFAS).setColorStatus("redZ");
													}else {
														DrawStation.turnoutLSList.get(indexFAS).setColorStatus("redF");
													}																										
													//DrawStation.turnoutLSList.get(indexFAS).setStatus(1);
													//timer.cancel();
												}
											});
									try {
										Thread.sleep(second);
									} catch (InterruptedException e) {
										// break;
									}

									Display.getDefault().syncExec( //再变蓝
											new Runnable() {
												public void run() {
													int status = DrawStation.turnoutLSList.get(indexFAS).getTurnoutStatus();
													if(status == 1){
														DrawStation.turnoutLSList.get(indexFAS).setColorStatus("blueZ");
													}else {
														DrawStation.turnoutLSList.get(indexFAS).setColorStatus("blueF");
													}																																							
													//DrawStation.turnoutLSList.get(indexFAS).setStatus(0);
													//timer.cancel();
												}
											});
									
								} else if (roadTypeLA
										.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLX)) {

									Display.getDefault().syncExec( //先变红色
											new Runnable() {
												public void run() {
													int status = DrawStation.turnoutLXList.get(indexFAS).getTurnoutStatus();
													if(status == 1){
														DrawStation.turnoutLXList.get(indexFAS).setColorStatus("redZ");
													}else {
														DrawStation.turnoutLXList.get(indexFAS).setColorStatus("redF");
													}													
													//DrawStation.turnoutLXList.get(indexFAS).setStatus(1);
													//timer.cancel();
												}
											});
									try {
										Thread.sleep(second);
									} catch (InterruptedException e) {
										// break;
									}

									Display.getDefault().syncExec( //再变蓝
											new Runnable() {
												public void run() {
													int status = DrawStation.turnoutLXList.get(indexFAS).getTurnoutStatus();
													if(status == 1){
														DrawStation.turnoutLXList.get(indexFAS).setColorStatus("blueZ");
													}else {
														DrawStation.turnoutLXList.get(indexFAS).setColorStatus("blueF");
													}														
													//DrawStation.turnoutLXList.get(indexFAS).setStatus(0);
													//timer.cancel();
												}
											});
									
								} else if (roadTypeLA
										.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRS)) {

									Display.getDefault().syncExec( //先变红色
											new Runnable() {
												public void run() {
													int status = DrawStation.turnoutRSList.get(indexFAS).getTurnoutStatus();
													if(status == 1){
														DrawStation.turnoutRSList.get(indexFAS).setColorStatus("redZ");
													}else {
														DrawStation.turnoutRSList.get(indexFAS).setColorStatus("redF");
													}														
													//DrawStation.turnoutRSList.get(indexFAS).setStatus(1);
													//timer.cancel();
												}
											});
									try {
										Thread.sleep(second);
									} catch (InterruptedException e) {
										// break;
									}

									Display.getDefault().syncExec( //再变蓝
											new Runnable() {
												public void run() {
													int status = DrawStation.turnoutRSList.get(indexFAS).getTurnoutStatus();
													if(status == 1){
														DrawStation.turnoutRSList.get(indexFAS).setColorStatus("blueZ");
													}else {
														DrawStation.turnoutRSList.get(indexFAS).setColorStatus("blueF");
													}														
													//DrawStation.turnoutRSList.get(indexFAS).setStatus(0);
													//timer.cancel();
												}
											});
									
								} else if (roadTypeLA
										.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRX)) {

									Display.getDefault().syncExec( //先变红色
											new Runnable() {
												public void run() {
													int status = DrawStation.turnoutRXList.get(indexFAS).getTurnoutStatus();
													if(status == 1){
														DrawStation.turnoutRXList.get(indexFAS).setColorStatus("redZ");
													}else {
														DrawStation.turnoutRXList.get(indexFAS).setColorStatus("redF");
													}															
													//DrawStation.turnoutRXList.get(indexFAS).setStatus(1);
													//timer.cancel();
												}
											});
									try {
										Thread.sleep(second);
									} catch (InterruptedException e) {
										// break;
									}

									Display.getDefault().syncExec( //再变蓝
											new Runnable() {
												public void run() {
													int status = DrawStation.turnoutRXList.get(indexFAS).getTurnoutStatus();
													if(status == 1){
														DrawStation.turnoutRXList.get(indexFAS).setColorStatus("blueZ");
													}else {
														DrawStation.turnoutRXList.get(indexFAS).setColorStatus("blueF");
													}													
													//DrawStation.turnoutRXList.get(indexFAS).setStatus(0);
													//timer.cancel();
												}
											});										
								}

							} else {
								System.out.println("根据相应的类型和名称找不到相应的indexID");
							}
						}//end of for1
						
						//上行第一离去
						Display.getDefault().syncExec( //先变红色
								new Runnable() {
									public void run() {
										DrawStation.trackLineSLFA1.setTrackLineStatus("red");														
										//DrawStation.trackLineSLFA1.setStatus(1);
									}
								});
						try {
							Thread.sleep(second);
						} catch (InterruptedException e) {
							// break;
						}

						Display.getDefault().syncExec( //再变蓝
								new Runnable() {
									public void run() {
										DrawStation.trackLineSLFA1.setTrackLineStatus("blue");														
										//DrawStation.trackLineSLFA1.setStatus(0);
									}
								});										
					
						
						//上行第二离去
						Display.getDefault().syncExec( //先变红色
								new Runnable() {
									public void run() {
										DrawStation.trackLineSLFA2.setTrackLineStatus("red");														
										//DrawStation.trackLineSLFA2.setStatus(1);
									}
								});
						try {
							Thread.sleep(second);
						} catch (InterruptedException e) {
							// break;
						}

						Display.getDefault().syncExec( //再变蓝
								new Runnable() {
									public void run() {
										DrawStation.trackLineSLFA2.setTrackLineStatus("blue");														
										//DrawStation.trackLineSLFA2.setStatus(0);
									}
								});		
						
						
						
						flagFAS = false;
						roadListFAS = null;
						lenRoadListFAS = 0;
						indexFAS = -1;
						timerFAS.cancel();
					}else {
						System.out.println("--flagFAS = false_解锁失败--");
					}
					}// run
				}, 0, timestamp);
				
				//timer.cancel();
				
			}else{
				System.out.println("进路解锁路径的长度不符合要求，lenRoadListLA = " + lenRoadListFAS);
			}			
		}else{
			System.out.println("进路解锁路径为空！");
		}
		
		
	}
	

	// 根据类型和名称获得所在索引
	public static int getIndexID(String roadType, String roadName) {
		
		int index = 0;
		int len;

		if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTrackLine)) {

			len = DrawStation.trackLineList.size();
			if (len >= 1) {
				for (int in = 0; in < len; in++) {
					if (roadName.equalsIgnoreCase(DrawStation.trackLineList
							.get(in).getLineName())) {
						index = in;
						return index;
					}
				}
			}

		} else if (roadType
				.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutDouble)) {

			len = DrawStation.turnoutDoubleList.size();
			if (len >= 1) {
				for (int in = 0; in < len; in++) {
					if (roadName
							.equalsIgnoreCase(DrawStation.turnoutDoubleList
									.get(in).getTurnoutName())) {
						index = in;
						return index;
					}
				}
			}

		} else if (roadType
				.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLS)) {

			len = DrawStation.turnoutLSList.size();
			if (len >= 1) {
				for (int in = 0; in < len; in++) {
					if (roadName.equalsIgnoreCase(DrawStation.turnoutLSList
							.get(in).getTurnoutName())) {
						index = in;
						return index;
					}
				}
			}

		} else if (roadType
				.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLX)) {

			len = DrawStation.turnoutLXList.size();
			if (len >= 1) {
				for (int in = 0; in < len; in++) {
					if (roadName.equalsIgnoreCase(DrawStation.turnoutLXList
							.get(in).getTurnoutName())) {
						index = in;
						return index;
					}
				}
			}

		} else if (roadType
				.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRS)) {

			len = DrawStation.turnoutRSList.size();
			if (len >= 1) {
				for (int in = 0; in < len; in++) {
					if (roadName.equalsIgnoreCase(DrawStation.turnoutRSList
							.get(in).getTurnoutName())) {
						index = in;
						return index;
					}
				}
			}

		} else if (roadType
				.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRX)) {

			len = DrawStation.turnoutRXList.size();
			if (len >= 1) {
				for (int in = 0; in < len; in++) {
					if (roadName.equalsIgnoreCase(DrawStation.turnoutRXList
							.get(in).getTurnoutName())) {
						index = in;
						return index;
					}
				}
			}

		}

		System.out.println("找不到符合条件的indexID");
		return -1;

	}

	/**
	 * 手动解锁
	 * 
	 * @param roadList
	 * @param type
	 *            进站1，出站0
	 */

	public static void unLockTrainSD(ArrayList<RoadBasicInfo> roadList1,
			int type1, String sephoreName1, String sephoreName2) {

		DrawStation.button_xta.setBackgroundColor(DrawStation.buttonInitColor);
		DrawStation.b_xta = 0;
		DrawStation.button_xla.setBackgroundColor(DrawStation.buttonInitColor);
		DrawStation.b_xla = 0;
		DrawStation.button_xlfa.setBackgroundColor(DrawStation.buttonInitColor);
		DrawStation.b_xlfa = 0;
		DrawStation.button_sta.setBackgroundColor(DrawStation.buttonInitColor);
		DrawStation.b_sta = 0;
		DrawStation.button_sla.setBackgroundColor(DrawStation.buttonInitColor);
		DrawStation.b_sla = 0;
		DrawStation.button_slfa.setBackgroundColor(DrawStation.buttonInitColor);
		DrawStation.b_slfa = 0;
		// 各个股道按钮
		DrawStation.button_x1la.setBackgroundColor(DrawStation.buttonInitColor);
		DrawStation.b_x1la = 0;
		DrawStation.button_x2la.setBackgroundColor(DrawStation.buttonInitColor);
		DrawStation.b_x2la = 0;
		DrawStation.button_x3la.setBackgroundColor(DrawStation.buttonInitColor);
		DrawStation.b_x3la = 0;
		DrawStation.button_x4la.setBackgroundColor(DrawStation.buttonInitColor);
		DrawStation.b_x4la = 0;
		DrawStation.button_s1la.setBackgroundColor(DrawStation.buttonInitColor);
		DrawStation.b_s1la = 0;
		DrawStation.button_s2la.setBackgroundColor(DrawStation.buttonInitColor);
		DrawStation.b_s2la = 0;
		DrawStation.button_s3la.setBackgroundColor(DrawStation.buttonInitColor);
		DrawStation.b_s3la = 0;
		DrawStation.button_s4la.setBackgroundColor(DrawStation.buttonInitColor);
		DrawStation.b_s4la = 0;

		ButtonSemphoreModel.allSemphoreColorModel("red");

		int len = roadList1.size();

		if (type1 == 1) { // 进站解锁

			for (int i = 0; i < len - 4; i++) {

				RoadBasicInfo rBasicInfo = roadList1.get(i);
				String roadType = rBasicInfo.getRoadType();
				String roadName = rBasicInfo.getRoadName();

				// 当类型为TrackLine时
				if (roadType
						.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTrackLine)) {

					int length = DrawStation.trackLineList.size();
					for (int j = 0; j < length; j++) {
						if (roadName.equalsIgnoreCase(DrawStation.trackLineList
								.get(j).getLineName())) {

							// DrawStation.trackLineList.get(j).
							// setTrackLineStatus("red");
							DrawStation.trackLineList.get(j)
									.setTrackLineStatus("blue");
							DrawStation.trackLineList.get(j).setStatus(0);

						}
					}
				}
				// 当类型为TurnoutDouble时
				if (roadType
						.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutDouble)) {

					int length = DrawStation.turnoutDoubleList.size();
					System.out.println(length);

					for (int j = 0; j < length; j++) {
						if (roadName
								.equalsIgnoreCase(DrawStation.turnoutDoubleList
										.get(j).getTurnoutName())) {

							DrawStation.turnoutDoubleList.get(j)
									.setColorStatus("blue");
							DrawStation.turnoutDoubleList.get(j).setStatus(0);
						}
					}
				}
				
				// 当类型为TurnoutLS时
				if (roadType
						.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLS)) {

					int length = DrawStation.turnoutLSList.size();
					for (int j = 0; j < length; j++) {
						if (roadName.equalsIgnoreCase(DrawStation.turnoutLSList
								.get(j).getTurnoutName())) {

							DrawStation.turnoutLSList.get(j)
									.setColorStatus("blue");
							DrawStation.turnoutLSList.get(j).setStatus(0);
						}
					}
				}
				// 当类型为TurnoutLX时
				if (roadType
						.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLX)) {

					int length = DrawStation.turnoutLXList.size();
					for (int j = 0; j < length; j++) {
						if (roadName.equalsIgnoreCase(DrawStation.turnoutLXList
								.get(j).getTurnoutName())) {

							DrawStation.turnoutLXList.get(j)
									.setColorStatus("blue");
							DrawStation.turnoutLXList.get(j).setStatus(0);
						}
					}
				}
				// 当类型为TurnoutRS时
				if (roadType
						.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRS)) {

					int length = DrawStation.turnoutRSList.size();
					for (int j = 0; j < length; j++) {
						if (roadName.equalsIgnoreCase(DrawStation.turnoutRSList
								.get(j).getTurnoutName())) {

							DrawStation.turnoutRSList.get(j)
									.setColorStatus("blue");
							DrawStation.turnoutRSList.get(j).setStatus(0);
						}
					}
				}
				// 当类型为TurnoutRX时
				if (roadType
						.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRX)) {

					int length = DrawStation.turnoutRXList.size();
					for (int j = 0; j < length; j++) {
						if (roadName.equalsIgnoreCase(DrawStation.turnoutRXList
								.get(j).getTurnoutName())) {

							DrawStation.turnoutRXList.get(j)
									.setColorStatus("blue");
							DrawStation.turnoutRXList.get(j).setStatus(0);
						}
					}
				}
			}

			// 最后4段
			for (int i = len - 4; i < len; i++) {

				RoadBasicInfo rBasicInfo = roadList1.get(i);
				String roadType = rBasicInfo.getRoadType();
				String roadName = rBasicInfo.getRoadName();

				// 当类型为TrackLine时
				if (roadType
						.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTrackLine)) {

					int length = DrawStation.trackLineList.size();
					for (int j = 0; j < length; j++) {
						if (roadName.equalsIgnoreCase(DrawStation.trackLineList
								.get(j).getLineName())) {

							DrawStation.trackLineList.get(j)
									.setTrackLineStatus("red");
							DrawStation.trackLineList.get(j).setStatus(1);

						}
					}
				}
			}

		} else { // 出站解锁

			for (int i = 0; i < len; i++) {

				RoadBasicInfo rBasicInfo = roadList1.get(i);
				String roadType = rBasicInfo.getRoadType();
				String roadName = rBasicInfo.getRoadName();

				// 当类型为TrackLine时
				if (roadType
						.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTrackLine)) {

					int length = DrawStation.trackLineList.size();
					for (int j = 0; j < length; j++) {
						if (roadName.equalsIgnoreCase(DrawStation.trackLineList
								.get(j).getLineName())) {

							// DrawStation.trackLineList.get(j).
							// setTrackLineStatus("red");
							DrawStation.trackLineList.get(j)
									.setTrackLineStatus("blue");
							DrawStation.trackLineList.get(j).setStatus(0);

						}
					}
				}
				// 当类型为TurnoutDouble时
				if (roadType
						.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutDouble)) {

					int length = DrawStation.turnoutDoubleList.size();
					System.out.println(length);

					for (int j = 0; j < length; j++) {
						if (roadName
								.equalsIgnoreCase(DrawStation.turnoutDoubleList
										.get(j).getTurnoutName())) {

							DrawStation.turnoutDoubleList.get(j)
									.setColorStatus("blue");
							DrawStation.turnoutDoubleList.get(j).setStatus(0);
						}
					}
				}
				
				// 当类型为TurnoutLS时
				if (roadType
						.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLS)) {

					int length = DrawStation.turnoutLSList.size();
					for (int j = 0; j < length; j++) {
						if (roadName.equalsIgnoreCase(DrawStation.turnoutLSList
								.get(j).getTurnoutName())) {

							DrawStation.turnoutLSList.get(j)
									.setColorStatus("blue");
							DrawStation.turnoutLSList.get(j).setStatus(0);
						}
					}
				}
				// 当类型为TurnoutLX时
				if (roadType
						.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLX)) {

					int length = DrawStation.turnoutLXList.size();
					for (int j = 0; j < length; j++) {
						if (roadName.equalsIgnoreCase(DrawStation.turnoutLXList
								.get(j).getTurnoutName())) {

							DrawStation.turnoutLXList.get(j)
									.setColorStatus("blue");
							DrawStation.turnoutLXList.get(j).setStatus(0);
						}
					}
				}
				// 当类型为TurnoutRS时
				if (roadType
						.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRS)) {

					int length = DrawStation.turnoutRSList.size();
					for (int j = 0; j < length; j++) {
						if (roadName.equalsIgnoreCase(DrawStation.turnoutRSList
								.get(j).getTurnoutName())) {

							DrawStation.turnoutRSList.get(j)
									.setColorStatus("blue");
							DrawStation.turnoutRSList.get(j).setStatus(0);
						}
					}
				}
				// 当类型为TurnoutRX时
				if (roadType
						.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRX)) {

					int length = DrawStation.turnoutRXList.size();
					for (int j = 0; j < length; j++) {
						if (roadName.equalsIgnoreCase(DrawStation.turnoutRXList
								.get(j).getTurnoutName())) {

							DrawStation.turnoutRXList.get(j)
									.setColorStatus("blue");
							DrawStation.turnoutRXList.get(j).setStatus(0);
						}
					}
				}
			}

		}

		/*
		 * if(i < len2) { timer.schedule(new java.util.TimerTask() { public void
		 * run() { RoadBasicInfo rBasicInfo = roadList.get(i); String roadType =
		 * rBasicInfo.getRoadType(); String roadName = rBasicInfo.getRoadName();
		 * 
		 * i++;
		 * 
		 * // 当类型为TrackLine时 if (roadType
		 * .equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTrackLine)) {
		 * 
		 * int length = DrawStation.trackLineList.size(); for (int j = 0; j <
		 * length; j++) { if (roadName
		 * .equalsIgnoreCase(DrawStation.trackLineList .get(j).getLineName())) {
		 * 
		 * len--;
		 * 
		 * // DrawStation.trackLineList.get(j). // setTrackLineStatus("red");
		 * DrawStation.trackLineList.get(j) .setTrackLineStatus("blue");
		 * DrawStation.trackLineList.get(j).setStatus(0);
		 * 
		 * } } } // 当类型为TurnoutDoubleL时 if (roadType
		 * .equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutDoubleL)) {
		 * 
		 * int length = DrawStation.turnoutDoubleLList.size();
		 * System.out.println(length);
		 * 
		 * for (int j = 0; j < length; j++) { if (roadName
		 * .equalsIgnoreCase(DrawStation.turnoutDoubleLList
		 * .get(j).getTurnoutName())) {
		 * 
		 * len--;
		 * 
		 * DrawStation.turnoutDoubleLList.get(j)
		 * .setTurnoutDoubleLStatus("blue");
		 * DrawStation.turnoutDoubleLList.get(j) .setStatus(0); } } } //
		 * 当类型为TurnoutDoubleR时 if (roadType
		 * .equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutDoubleR)) {
		 * 
		 * int length = DrawStation.turnoutDoubleRList.size(); for (int j = 0; j
		 * < length; j++) { if (roadName
		 * .equalsIgnoreCase(DrawStation.turnoutDoubleRList
		 * .get(j).getTurnoutName())) {
		 * 
		 * len--;
		 * 
		 * DrawStation.turnoutDoubleRList.get(j)
		 * .setTurnoutDoubleRStatus("blue");
		 * DrawStation.turnoutDoubleRList.get(j) .setStatus(0); } }
		 * 
		 * }
		 * 
		 * // 当类型为TurnoutLS时 if (roadType
		 * .equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLS)) {
		 * 
		 * int length = DrawStation.turnoutLSList.size(); for (int j = 0; j <
		 * length; j++) { if (roadName
		 * .equalsIgnoreCase(DrawStation.turnoutLSList
		 * .get(j).getTurnoutName())) {
		 * 
		 * len--;
		 * 
		 * DrawStation.turnoutLSList.get(j) .setColorStatus("blue");
		 * DrawStation.turnoutLSList.get(j).setStatus(0); } } } //
		 * 当类型为TurnoutLX时 if (roadType
		 * .equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLX)) {
		 * 
		 * int length = DrawStation.turnoutLXList.size(); for (int j = 0; j <
		 * length; j++) { if (roadName
		 * .equalsIgnoreCase(DrawStation.turnoutLXList
		 * .get(j).getTurnoutName())) {
		 * 
		 * len--;
		 * 
		 * DrawStation.turnoutLXList.get(j) .setColorStatus("blue");
		 * DrawStation.turnoutLXList.get(j).setStatus(0); } } } //
		 * 当类型为TurnoutRS时 if (roadType
		 * .equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRS)) {
		 * 
		 * int length = DrawStation.turnoutRSList.size(); for (int j = 0; j <
		 * length; j++) { if (roadName
		 * .equalsIgnoreCase(DrawStation.turnoutRSList
		 * .get(j).getTurnoutName())) {
		 * 
		 * len--;
		 * 
		 * DrawStation.turnoutRSList.get(j) .setColorStatus("blue");
		 * DrawStation.turnoutRSList.get(j).setStatus(0); } } } //
		 * 当类型为TurnoutRX时 if (roadType
		 * .equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRX)) {
		 * 
		 * int length = DrawStation.turnoutRXList.size(); for (int j = 0; j <
		 * length; j++) { if (roadName
		 * .equalsIgnoreCase(DrawStation.turnoutRXList
		 * .get(j).getTurnoutName())) {
		 * 
		 * len--;
		 * 
		 * DrawStation.turnoutRXList.get(j) .setColorStatus("blue");
		 * DrawStation.turnoutRXList.get(j).setStatus(0); } } }
		 * 
		 * if (i >= len2) { //timer.cancel(); } }
		 * 
		 * }, 0, timestamp);
		 */
	}

}
