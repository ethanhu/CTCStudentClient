package ctc.sics.station4.autosystem;

import java.util.ArrayList;
import java.util.Timer;

import org.eclipse.draw2d.Figure;
import org.eclipse.swt.widgets.Display;

import ctc.sics.station4.elements.common.*;
import ctc.sics.station4.elements.semaphore.*;
import ctc.sics.station4.elements.turnout.*;

import ctc.sics.station4.data.*;
import ctc.sics.station4.layout.StationModel;
import ctc.sics.station4.sound.SoundPlayer;

/**
 * 自动检测股道是够有车，如果有且有通路，则自动解锁
 * @author 胡恩召
 *
 */
public class SelfDisciplineSystem {

	private static SelfDisciplineSystem selfDiscipline = null;
	public String midName = "NO";
	
	
	public static SelfDisciplineSystem getInstance() {
		if (selfDiscipline == null) {
			selfDiscipline = new SelfDisciplineSystem();
		}
		return selfDiscipline;
	}

	BaseParam baseData = BaseParam.getInstance(); //基本参数类
	StationModel stationModel = StationModel.getInstance(); // 业务处理类
	SoundPlayer SP = SoundPlayer.getInstance(); //播放器

	//int second = 2000;
	int timestamp = 2000;
	public static boolean runFlag = true;
	public TrainStopRoad trainStopRoad = new TrainStopRoad();
	public ArrayList<RoadList> stopLineRoadList = new ArrayList<RoadList>(); // 路径list

	public void TrainSelfDisciplineSystem() {

		final Timer timerTrain = new Timer();

		timerTrain.schedule(new java.util.TimerTask() {

			public void run() {

				Display.getDefault().syncExec(new Runnable() {

					public void run() {

						//System.out.println("调度！");

						if (baseData.getAllStopRoadList() != null && baseData.getAllStopRoadList().size() != 0) {
							
							int len = baseData.getAllStopRoadList().size();
							for (int i = 0; i < len; i++) {
								trainStopRoad = baseData.getAllStopRoadList().get(i);

								if (trainStopRoad.getStopLine().getStatus() == 2) { // 红色
									
									//准备接车提醒
									
									if(trainStopRoad.getStopLine().getName().equalsIgnoreCase("XD2JJ") || trainStopRoad.getStopLine().getName().equalsIgnoreCase("SD2JJ")){
										SP.soundZBJC();									
									}
									
									
									System.out.println("股道： " + i + " 有车");
									
									stopLineRoadList = trainStopRoad.getStopLineRoadList();
									if (stopLineRoadList != null && stopLineRoadList.size() != 0) {
										int len2 = stopLineRoadList.size();
										for (int j = 0; j < len2; j++) {
											if (stopLineRoadList.get(j).getFlag() == ParamFlag.road_green) {

												String startName = stopLineRoadList.get(j).getStartName();
												String endName = stopLineRoadList.get(j).getEndName();
												System.out.println("--找到路径 " + startName + " - " + endName + " 有空闲---！");

												//processTrainRoad(startName, endName, ParamFlag.road_red);
												processTrainRoad(stopLineRoadList.get(j));
												stopLineRoadList.get(j).setFlag(ParamFlag.road_red);
												trainStopRoad.getStopLine().setColorStatus(ParamFlag.trackline_bule);											

											}// end if

										}// end for
									}
								} else {								
									
									stopLineRoadList = trainStopRoad.getStopLineRoadList();
									if (stopLineRoadList != null && stopLineRoadList.size() != 0) {
										int len2 = stopLineRoadList.size();
										for (int j = 0; j < len2; j++) {
											if (stopLineRoadList.get(j).getFlag() == ParamFlag.road_red && stopLineRoadList.get(j).getIndex() >= 1) {
												
												String startName = stopLineRoadList.get(j).getStartName();
												String endName = stopLineRoadList.get(j).getEndName();
												System.out.println("--路径被激活-- " + startName + " - " + endName + " 有空闲！");

												//processTrainRoad(startName, endName, ParamFlag.road_red);
												processTrainRoad(stopLineRoadList.get(j));
												//stopLineRoadList.get(j).setFlag(ParamFlag.road_red);

											}// end if

										}// end for
									}// end if
								}// end if
							}// end else
						}// end for
					}// end run
				}); // end syscExec
			}// run
		}, 10, timestamp);
	}

	
	// 处理道路相关的内容
	public void processTrainRoad(RoadList road) {

		if(road == null){
			return;
		}
		
		RoadBasicInfo roadBasicInfo = new RoadBasicInfo(); // 股道段基本信息
		ArrayList<RoadBasicInfo> roadInfoList = new ArrayList<RoadBasicInfo>(); // 股道段基本信息list
		Figure fig;
		int color;
		
		roadInfoList = road.getRoadInfoList(); // 获得路径的list
		
		if (roadInfoList != null && roadInfoList.size() != 0) {
			
			int roadLen = roadInfoList.size();
			int index = road.getIndex();									

			if (index < roadLen + 2) {

				if(index == 0){
								
					System.out.println("--0--信号机变红--");
								
					//先使信号机变红
					roadBasicInfo = roadInfoList.get(index); // 或者路径中的某1段
							
					fig = roadBasicInfo.getRoadName();
					if (fig instanceof SemaphoreDoubleL) {
						((SemaphoreDoubleL) fig).setColorStatus(ParamFlag.sep_red);
					} else if (fig instanceof SemaphoreDoubleR) {
						((SemaphoreDoubleR) fig).setColorStatus(ParamFlag.sep_red);
					} else if (fig instanceof SemaphoreSimpleL) {
						((SemaphoreSimpleL) fig).setColorStatus(ParamFlag.sep_red);
					} else if (fig instanceof SemaphoreSimpleR) {
						((SemaphoreSimpleR) fig).setColorStatus(ParamFlag.sep_red);
					} else {
						System.out.println("--processTrainRoad--出现错误，第一个fig不是信号机类型！");
					}
					index = index + 1;
					road.setIndex(index);
					
					System.out.println("888--index = " + road.getIndex());
					
				} else if(index >= 1 && index <= 2){
					
					System.out.println("---1---");		
					
					String startName = road.getStartName();
					if(startName.equalsIgnoreCase("XLA") || startName.equalsIgnoreCase("XTA") || startName.equalsIgnoreCase("SLA") ||startName.equalsIgnoreCase("STA")){
						
						// 再使index段变红
						roadBasicInfo = roadInfoList.get(index); // 或者路径中的某1段
						color = roadBasicInfo.getRedType();

						fig = roadBasicInfo.getRoadName();
						if (fig instanceof TrackLine) {
							((TrackLine) fig).setColorStatus(color);
						} else if (fig instanceof TurnoutLS) {
							((TurnoutLS) fig).setColorStatus(color);
						} else if (fig instanceof TurnoutLX) {
							((TurnoutLX) fig).setColorStatus(color);
						} else if (fig instanceof TurnoutRS) {
							((TurnoutRS) fig).setColorStatus(color);
						} else if (fig instanceof TurnoutRX) {
							((TurnoutRX) fig).setColorStatus(color);
						} else if (fig instanceof TurnoutDouble) {
							((TurnoutDouble) fig).setColorStatus(color);
						} else if (fig instanceof TurnoutDoubleR) {
							((TurnoutDoubleR) fig).setColorStatus(color);
						} else if (fig instanceof TurnoutDoubleL) {
							((TurnoutDoubleL) fig).setColorStatus(color);
						} else if (fig instanceof SemaphoreDoubleL) {
							((SemaphoreDoubleL) fig).setColorStatus(ParamFlag.sep_red);
						} else if (fig instanceof SemaphoreDoubleR) {
							((SemaphoreDoubleR) fig).setColorStatus(ParamFlag.sep_red);
						} else if (fig instanceof SemaphoreSimpleL) {
							((SemaphoreSimpleL) fig).setColorStatus(ParamFlag.sep_red);
						} else if (fig instanceof SemaphoreSimpleR) {
							((SemaphoreSimpleR) fig).setColorStatus(ParamFlag.sep_red);
						} else {
							System.out.println("--processTrainRoad--出现错误，未找到符合fig的类型！");
						}
						
					}else{						
						
						index = index + 1;
						// 再使index段变红
						roadBasicInfo = roadInfoList.get(index); // 或者路径中的某1段
						color = roadBasicInfo.getRedType();

						fig = roadBasicInfo.getRoadName();
						if (fig instanceof TrackLine) {
							((TrackLine) fig).setColorStatus(color);
						} else if (fig instanceof TurnoutLS) {
							((TurnoutLS) fig).setColorStatus(color);
						} else if (fig instanceof TurnoutLX) {
							((TurnoutLX) fig).setColorStatus(color);
						} else if (fig instanceof TurnoutRS) {
							((TurnoutRS) fig).setColorStatus(color);
						} else if (fig instanceof TurnoutRX) {
							((TurnoutRX) fig).setColorStatus(color);
						} else if (fig instanceof TurnoutDouble) {
							((TurnoutDouble) fig).setColorStatus(color);
						} else if (fig instanceof TurnoutDoubleR) {
							((TurnoutDoubleR) fig).setColorStatus(color);
						} else if (fig instanceof TurnoutDoubleL) {
							((TurnoutDoubleL) fig).setColorStatus(color);
						} else if (fig instanceof SemaphoreDoubleL) {
							((SemaphoreDoubleL) fig).setColorStatus(ParamFlag.sep_red);
						} else if (fig instanceof SemaphoreDoubleR) {
							((SemaphoreDoubleR) fig).setColorStatus(ParamFlag.sep_red);
						} else if (fig instanceof SemaphoreSimpleL) {
							((SemaphoreSimpleL) fig).setColorStatus(ParamFlag.sep_red);
						} else if (fig instanceof SemaphoreSimpleR) {
							((SemaphoreSimpleR) fig).setColorStatus(ParamFlag.sep_red);
						} else {
							System.out.println("--processTrainRoad--出现错误，未找到符合fig的类型！");
						}
						
					}							
					
					index = index + 1;
					road.setIndex(index);	
					
				} else if (index >= 3 && index < roadLen) {

					System.out.println("---2---");
								
					// 先使index-2变蓝
					roadBasicInfo = roadInfoList.get(index - 2);
					color = roadBasicInfo.getBlueType();
					fig = roadBasicInfo.getRoadName();
					
					if (fig instanceof TrackLine) {
						((TrackLine) fig).setColorStatus(color);
					} else if (fig instanceof TurnoutLS) {
						((TurnoutLS) fig).setColorStatus(color);
					} else if (fig instanceof TurnoutLX) {
						((TurnoutLX) fig).setColorStatus(color);
					} else if (fig instanceof TurnoutRS) {
						((TurnoutRS) fig).setColorStatus(color);
					} else if (fig instanceof TurnoutRX) {
						((TurnoutRX) fig).setColorStatus(color);
					} else if (fig instanceof TurnoutDouble) {
						((TurnoutDouble) fig).setColorStatus(color);
					} else if (fig instanceof TurnoutDoubleR) {
						((TurnoutDoubleR) fig).setColorStatus(color);
					} else if (fig instanceof TurnoutDoubleL) {
						((TurnoutDoubleL) fig).setColorStatus(color);
					} else {
						System.out.println("--processTrainRoad--出现错误，未找到符合fig的类型！");
					}

					// 再使index段变红
					roadBasicInfo = roadInfoList.get(index); 
					color = roadBasicInfo.getRedType();

					fig = roadBasicInfo.getRoadName();
					if (fig instanceof TrackLine) {
						((TrackLine) fig).setColorStatus(color);
					} else if (fig instanceof TurnoutLS) {
						((TurnoutLS) fig).setColorStatus(color);
					} else if (fig instanceof TurnoutLX) {
						((TurnoutLX) fig).setColorStatus(color);
					} else if (fig instanceof TurnoutRS) {
						((TurnoutRS) fig).setColorStatus(color);
					} else if (fig instanceof TurnoutRX) {
						((TurnoutRX) fig).setColorStatus(color);
					} else if (fig instanceof TurnoutDouble) {
						((TurnoutDouble) fig).setColorStatus(color);
					} else if (fig instanceof TurnoutDoubleR) {
						((TurnoutDoubleR) fig).setColorStatus(color);
					} else if (fig instanceof TurnoutDoubleL) {
						((TurnoutDoubleL) fig).setColorStatus(color);
					} else {
						System.out.println("--processTrainRoad--出现错误，未找到符合fig的类型！");
					}
					
					road.setIndex(index + 1);

				} else if (index == roadLen) {

					System.out.println("---3---");
								
					// 先使index-2变蓝
					roadBasicInfo = roadInfoList.get(index - 2);
					color = roadBasicInfo.getBlueType();
					fig = roadBasicInfo.getRoadName();
					if (fig instanceof TrackLine) {
						((TrackLine) fig).setColorStatus(color);
					} else if (fig instanceof TurnoutLS) {
						((TurnoutLS) fig).setColorStatus(color);
					} else if (fig instanceof TurnoutLX) {
						((TurnoutLX) fig).setColorStatus(color);
					} else if (fig instanceof TurnoutRS) {
						((TurnoutRS) fig).setColorStatus(color);
					} else if (fig instanceof TurnoutRX) {
						((TurnoutRX) fig).setColorStatus(color);
					} else if (fig instanceof TurnoutDouble) {
						((TurnoutDouble) fig).setColorStatus(color);
					} else if (fig instanceof TurnoutDoubleR) {
						((TurnoutDoubleR) fig).setColorStatus(color);
					} else if (fig instanceof TurnoutDoubleL) {
						((TurnoutDoubleL) fig).setColorStatus(color);
					} else {
						System.out.println("--processTrainRoad--出现错误，未找到符合fig的类型！");
					}
					road.setIndex(index + 1);
					
				}else if(index == roadLen + 1){
					
					System.out.println("---4---");
					
					String endName = road.getEndName();
					
					if (endName.equalsIgnoreCase("SLFA") || endName.equalsIgnoreCase("XLFA")) {

						roadBasicInfo = roadInfoList.get(index - 2);
						color = roadBasicInfo.getBlueType();
						fig = roadBasicInfo.getRoadName();
						if (fig instanceof TrackLine) {
							((TrackLine) fig).setColorStatus(color);
						} else if (fig instanceof TurnoutLS) {
							((TurnoutLS) fig).setColorStatus(color);
						} else if (fig instanceof TurnoutLX) {
							((TurnoutLX) fig).setColorStatus(color);
						} else if (fig instanceof TurnoutRS) {
							((TurnoutRS) fig).setColorStatus(color);
						} else if (fig instanceof TurnoutRX) {
							((TurnoutRX) fig).setColorStatus(color);
						} else if (fig instanceof TurnoutDouble) {
							((TurnoutDouble) fig).setColorStatus(color);
						} else if (fig instanceof TurnoutDoubleR) {
							((TurnoutDoubleR) fig).setColorStatus(color);
						} else if (fig instanceof TurnoutDoubleL) {
							((TurnoutDoubleL) fig).setColorStatus(color);
						} else {
							System.out.println("--processTrainRoad--出现错误，未找到符合fig的类型！");
						}						
					}else{
						System.out.println("--index = roadLen + 1，此时为接车进站");
					}
					
					road.setIndex(0);
					road.setFlag(ParamFlag.road_blue);
					
				}	

			}
		} else {
			System.out.println("--processTrainRoad--baseDataForStation.getAllRoadList()==null && baseDataForStation.getAllRoadList().size()==0");
		}

	}

	
	
	
	// 处理道路相关的内容
	public void processTrainRoad2(String startName, String endName, int road_color) {

		RoadList road = new RoadList(); // 路径
		RoadBasicInfo roadBasicInfo = new RoadBasicInfo(); // 股道段基本信息
		ArrayList<RoadBasicInfo> roadInfoList = new ArrayList<RoadBasicInfo>(); // 股道段基本信息list
		Figure fig;
		int color;
		if (baseData.getAllRoadList() != null && baseData.getAllRoadList().size() != 0) {

			int len = baseData.getAllRoadList().size();
			for (int i = 0; i < len; i++) {

				road = baseData.getAllRoadList().get(i); // 取出1条路径

				if (road.getStartName().equalsIgnoreCase(startName) && road.getEndName().equalsIgnoreCase(endName)) {

					roadInfoList = road.getRoadInfoList(); // 获得路径的list
					if (roadInfoList != null && roadInfoList.size() != 0) {
						int roadLen = roadInfoList.size();
						int index = road.getIndex();									

						if (index < roadLen) {

							if(index == 0){
								
								System.out.println("--信号机变红--");
								
								//先使信号机变红
								// 再使index段变红
								roadBasicInfo = roadInfoList.get(index); // 或者路径中的某1段
							
								fig = roadBasicInfo.getRoadName();
								if (fig instanceof SemaphoreDoubleL) {
									((SemaphoreDoubleL) fig).setColorStatus(ParamFlag.sep_red);
								} else if (fig instanceof SemaphoreDoubleR) {
									((SemaphoreDoubleR) fig).setColorStatus(ParamFlag.sep_red);
								} else if (fig instanceof SemaphoreSimpleL) {
									((SemaphoreSimpleL) fig).setColorStatus(ParamFlag.sep_red);
								} else if (fig instanceof SemaphoreSimpleR) {
									((SemaphoreSimpleR) fig).setColorStatus(ParamFlag.sep_red);
								} else {
									System.out.println("--processTrainRoad--出现错误，第一个fig不是信号机类型！");
								}
								index = index + 1;
								road.setIndex(index);
								
							}else if (index >= 3 && index < roadLen - 1) {

								System.out.println("---2---");
								
								// 先使index-2变蓝
								roadBasicInfo = roadInfoList.get(index - 2);
								color = roadBasicInfo.getBlueType();
								fig = roadBasicInfo.getRoadName();
								if (fig instanceof TrackLine) {
									((TrackLine) fig).setColorStatus(color);
								} else if (fig instanceof TurnoutLS) {
									((TurnoutLS) fig).setColorStatus(color);
								} else if (fig instanceof TurnoutLX) {
									((TurnoutLX) fig).setColorStatus(color);
								} else if (fig instanceof TurnoutRS) {
									((TurnoutRS) fig).setColorStatus(color);
								} else if (fig instanceof TurnoutRX) {
									((TurnoutRX) fig).setColorStatus(color);
								} else if (fig instanceof TurnoutDouble) {
									((TurnoutDouble) fig).setColorStatus(color);
								} else if (fig instanceof TurnoutDoubleR) {
									((TurnoutDoubleR) fig).setColorStatus(color);
								} else if (fig instanceof TurnoutDoubleL) {
									((TurnoutDoubleL) fig).setColorStatus(color);
								} else {
									System.out.println("--processTrainRoad--出现错误，未找到符合fig的类型！");
								}

								// 再使index段变红
								roadBasicInfo = roadInfoList.get(index); 
								color = roadBasicInfo.getRedType();

								fig = roadBasicInfo.getRoadName();
								if (fig instanceof TrackLine) {
									((TrackLine) fig).setColorStatus(color);
								} else if (fig instanceof TurnoutLS) {
									((TurnoutLS) fig).setColorStatus(color);
								} else if (fig instanceof TurnoutLX) {
									((TurnoutLX) fig).setColorStatus(color);
								} else if (fig instanceof TurnoutRS) {
									((TurnoutRS) fig).setColorStatus(color);
								} else if (fig instanceof TurnoutRX) {
									((TurnoutRX) fig).setColorStatus(color);
								} else if (fig instanceof TurnoutDouble) {
									((TurnoutDouble) fig).setColorStatus(color);
								} else if (fig instanceof TurnoutDoubleR) {
									((TurnoutDoubleR) fig).setColorStatus(color);
								} else if (fig instanceof TurnoutDoubleL) {
									((TurnoutDoubleL) fig).setColorStatus(color);
								} else {
									System.out.println("--processTrainRoad--出现错误，未找到符合fig的类型！");
								}
								road.setIndex(index + 1);

							} else if (index == roadLen - 1) {

								System.out.println("---3---");
								
								// 先使index-2变蓝
								roadBasicInfo = roadInfoList.get(index - 2);
								color = roadBasicInfo.getBlueType();
								fig = roadBasicInfo.getRoadName();
								if (fig instanceof TrackLine) {
									((TrackLine) fig).setColorStatus(color);
								} else if (fig instanceof TurnoutLS) {
									((TurnoutLS) fig).setColorStatus(color);
								} else if (fig instanceof TurnoutLX) {
									((TurnoutLX) fig).setColorStatus(color);
								} else if (fig instanceof TurnoutRS) {
									((TurnoutRS) fig).setColorStatus(color);
								} else if (fig instanceof TurnoutRX) {
									((TurnoutRX) fig).setColorStatus(color);
								} else if (fig instanceof TurnoutDouble) {
									((TurnoutDouble) fig).setColorStatus(color);
								} else if (fig instanceof TurnoutDoubleR) {
									((TurnoutDoubleR) fig).setColorStatus(color);
								} else if (fig instanceof TurnoutDoubleL) {
									((TurnoutDoubleL) fig).setColorStatus(color);
								} else {
									System.out.println("--processTrainRoad--出现错误，未找到符合fig的类型！");
								}
							
								// 再使index段变红
								roadBasicInfo = roadInfoList.get(index); // 或者路径中的某1段
								color = roadBasicInfo.getRedType();

								fig = roadBasicInfo.getRoadName();
								if (fig instanceof TrackLine) {
									((TrackLine) fig).setColorStatus(color);
								} else if (fig instanceof TurnoutLS) {
									((TurnoutLS) fig).setColorStatus(color);
								} else if (fig instanceof TurnoutLX) {
									((TurnoutLX) fig).setColorStatus(color);
								} else if (fig instanceof TurnoutRS) {
									((TurnoutRS) fig).setColorStatus(color);
								} else if (fig instanceof TurnoutRX) {
									((TurnoutRX) fig).setColorStatus(color);
								} else if (fig instanceof TurnoutDouble) {
									((TurnoutDouble) fig).setColorStatus(color);
								} else if (fig instanceof TurnoutDoubleR) {
									((TurnoutDoubleR) fig).setColorStatus(color);
								} else if (fig instanceof TurnoutDoubleL) {
									((TurnoutDoubleL) fig).setColorStatus(color);
								} else {
									System.out.println("--processTrainRoad--出现错误，未找到符合fig的类型！");
								}
								
								/*
								try {
									Thread.sleep(timestamp);
								} catch (InterruptedException e) {
									
								}
								*/
								
								// 先使index-1变蓝
								roadBasicInfo = roadInfoList.get(index - 1);
								color = roadBasicInfo.getBlueType();
								fig = roadBasicInfo.getRoadName();
								if (fig instanceof TrackLine) {
									((TrackLine) fig).setColorStatus(color);
								} else if (fig instanceof TurnoutLS) {
									((TurnoutLS) fig).setColorStatus(color);
								} else if (fig instanceof TurnoutLX) {
									((TurnoutLX) fig).setColorStatus(color);
								} else if (fig instanceof TurnoutRS) {
									((TurnoutRS) fig).setColorStatus(color);
								} else if (fig instanceof TurnoutRX) {
									((TurnoutRX) fig).setColorStatus(color);
								} else if (fig instanceof TurnoutDouble) {
									((TurnoutDouble) fig).setColorStatus(color);
								} else if (fig instanceof TurnoutDoubleR) {
									((TurnoutDoubleR) fig).setColorStatus(color);
								} else if (fig instanceof TurnoutDoubleL) {
									((TurnoutDoubleL) fig).setColorStatus(color);
								} else {
									System.out.println("--processTrainRoad--出现错误，未找到符合fig的类型！");
								}

								if (endName.equalsIgnoreCase("SLFA") || endName.equalsIgnoreCase("XLFA")) {

									roadBasicInfo = roadInfoList.get(index);
									color = roadBasicInfo.getBlueType();
									fig = roadBasicInfo.getRoadName();
									if (fig instanceof TrackLine) {
										((TrackLine) fig).setColorStatus(color);
									} else if (fig instanceof TurnoutLS) {
										((TurnoutLS) fig).setColorStatus(color);
									} else if (fig instanceof TurnoutLX) {
										((TurnoutLX) fig).setColorStatus(color);
									} else if (fig instanceof TurnoutRS) {
										((TurnoutRS) fig).setColorStatus(color);
									} else if (fig instanceof TurnoutRX) {
										((TurnoutRX) fig).setColorStatus(color);
									} else if (fig instanceof TurnoutDouble) {
										((TurnoutDouble) fig).setColorStatus(color);
									} else if (fig instanceof TurnoutDoubleR) {
										((TurnoutDoubleR) fig).setColorStatus(color);
									} else if (fig instanceof TurnoutDoubleL) {
										((TurnoutDoubleL) fig).setColorStatus(color);
									} else {
										System.out.println("--processTrainRoad--出现错误，未找到符合fig的类型！");
									}

								}
								
								road.setIndex(0);
								road.setFlag(ParamFlag.road_blue);
								

							} else if(index >= 1 && index <= 2){

								System.out.println("---1---");
								
								// 再使index段变红
								roadBasicInfo = roadInfoList.get(index); // 或者路径中的某1段
								color = roadBasicInfo.getRedType();

								fig = roadBasicInfo.getRoadName();
								if (fig instanceof TrackLine) {
									((TrackLine) fig).setColorStatus(color);
								} else if (fig instanceof TurnoutLS) {
									((TurnoutLS) fig).setColorStatus(color);
								} else if (fig instanceof TurnoutLX) {
									((TurnoutLX) fig).setColorStatus(color);
								} else if (fig instanceof TurnoutRS) {
									((TurnoutRS) fig).setColorStatus(color);
								} else if (fig instanceof TurnoutRX) {
									((TurnoutRX) fig).setColorStatus(color);
								} else if (fig instanceof TurnoutDouble) {
									((TurnoutDouble) fig).setColorStatus(color);
								} else if (fig instanceof TurnoutDoubleR) {
									((TurnoutDoubleR) fig).setColorStatus(color);
								} else if (fig instanceof TurnoutDoubleL) {
									((TurnoutDoubleL) fig).setColorStatus(color);
								} else if (fig instanceof SemaphoreDoubleL) {
									((SemaphoreDoubleL) fig).setColorStatus(ParamFlag.sep_red);
								} else if (fig instanceof SemaphoreDoubleR) {
									((SemaphoreDoubleR) fig).setColorStatus(ParamFlag.sep_red);
								} else if (fig instanceof SemaphoreSimpleL) {
									((SemaphoreSimpleL) fig).setColorStatus(ParamFlag.sep_red);
								} else if (fig instanceof SemaphoreSimpleR) {
									((SemaphoreSimpleR) fig).setColorStatus(ParamFlag.sep_red);
								} else {
									System.out.println("--processTrainRoad--出现错误，未找到符合fig的类型！");
								}
								index = index + 1;
								road.setIndex(index);								
							}
						}

					}

				}

			}
		} else {
			System.out.println("--processTrainRoad--baseDataForStation.getAllRoadList()==null && baseDataForStation.getAllRoadList().size()==0");
		}

	}

}
