package ctc.sics.station.autosystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import org.eclipse.draw2d.Figure;
import org.eclipse.swt.widgets.Display;

import ctc.ctc.rsb.data.TrainDistrict;
import ctc.sics.SicsMain;
import ctc.sics.station.elements.common.*;
import ctc.sics.station.elements.semaphore.*;
import ctc.sics.station.elements.turnout.*;

import ctc.sics.station.data.*;
import ctc.sics.station.layout.StationModel;
import ctc.sics.station.sound.SoundPlayer;
import ctc.util.ErrorLog;

/**
 * 自动检测股道是够有车，如果有且有通路，则自动解锁
 * @author 胡恩召
 *
 */
public class SelfDisciplineSystem {

	BaseParam baseData = BaseParam.getInstance(); //基本参数类
	
	private static SelfDisciplineSystem selfDiscipline = null;
	public String midName = "NO";
	
	// 区段任务list
	public static List<TrainDistrict> trainDisList = new ArrayList<TrainDistrict>();
	public TrainDistrict trainDis;
	
	
	public static SelfDisciplineSystem getInstance() {
		if (selfDiscipline == null) {
			selfDiscipline = new SelfDisciplineSystem();
		}
		return selfDiscipline;
	}

	
	StationModel stationModel = StationModel.getInstance(); // 业务处理类
	SoundPlayer SP = SoundPlayer.getInstance(); //播放器

	//int second = 2000;
	int timestamp = 5000;
	public static boolean runFlag = true;
	public TrainStopRoad trainStopRoad = new TrainStopRoad();
	public ArrayList<RoadList> stopLineRoadList = new ArrayList<RoadList>(); // 路径list

	//下行离去
	public List<TrackLine> trackListXF = new ArrayList<TrackLine>();	
	//上行离去
	public List<TrackLine> trackListSF = new ArrayList<TrackLine>();
	//自动闭塞分区	
	public List<SemaphoreDoubleL> sepListSFA = baseData.getSepListSFA();		
	public List<SemaphoreDoubleR> sepListXFA = baseData.getSepListXFA();
	
	
	
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
									
									
									//System.out.println("股道： " + i + " 有车");
									
									stopLineRoadList = trainStopRoad.getStopLineRoadList(); //获得有车股道对于的路径list
									
									if (stopLineRoadList != null && stopLineRoadList.size() != 0) {
										int len2 = stopLineRoadList.size();
										for (int j = 0; j < len2; j++) {
											if (stopLineRoadList.get(j).getFlag() == ParamFlag.road_green) {

												String startName = stopLineRoadList.get(j).getStartName();
												String endName = stopLineRoadList.get(j).getEndName();
												System.out.println("--找到路径 " + startName + " - " + endName + " 有空闲---！");

												//processTrainRoad(startName, endName, ParamFlag.road_red);
												
												String trainName = trainStopRoad.getStopLine().getTrainLabel().getText();
												stopLineRoadList.get(j).setTrainName(trainName);
												stopLineRoadList.get(j).setFlag(ParamFlag.road_red);
												
												trainStopRoad.getStopLine().setColorStatus(ParamFlag.trackline_bule);											
												trainStopRoad.getStopLine().getTrainLabel().setText("");
												trainStopRoad.getStopLine().getTrainLabel().setVisible(false);
												
												processTrainRoad(stopLineRoadList.get(j));
												
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

		String trainName = road.getTrainName();
		
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
								
					//System.out.println("--0--信号机变红--");
								
					//先使信号机变红
					roadBasicInfo = roadInfoList.get(index); // 或者路径中的某1段
							
					fig = roadBasicInfo.getRoadName();
					if (fig instanceof SemaphoreDoubleL) {
						((SemaphoreDoubleL) fig).setColorStatus(ParamFlag.sep_red);
						((SemaphoreDoubleL) fig).setTrainName(trainName);
					} else if (fig instanceof SemaphoreDoubleR) {
						((SemaphoreDoubleR) fig).setColorStatus(ParamFlag.sep_red);
						((SemaphoreDoubleR) fig).setTrainName(trainName);
					} else if (fig instanceof SemaphoreSimpleL) {
						((SemaphoreSimpleL) fig).setColorStatus(ParamFlag.sep_red);
						((SemaphoreSimpleL) fig).setTrainName(trainName);
					} else if (fig instanceof SemaphoreSimpleR) {
						((SemaphoreSimpleR) fig).setColorStatus(ParamFlag.sep_red);
						((SemaphoreSimpleR) fig).setTrainName(trainName);
					} else {
						System.out.println("--processTrainRoad--出现错误，第一个fig不是信号机类型！");
					}
					index = index + 1;
					road.setIndex(index);
					
					//System.out.println("888--index = " + road.getIndex());
					
				} else if(index >= 1 && index <= 2){
					
					//System.out.println("---1---");		
					setTrainName(roadInfoList.get(index - 1)); // 将过去1段trainName设置为空
										
					String startName = road.getStartName();
					//if(startName.equalsIgnoreCase("XLA") || startName.equalsIgnoreCase("XTA") || startName.equalsIgnoreCase("SLA") ||startName.equalsIgnoreCase("STA")){
						
						// 再使index段变红
						roadBasicInfo = roadInfoList.get(index); // 或者路径中的某1段
						color = roadBasicInfo.getRedType();
						fig = roadBasicInfo.getRoadName();
						if (fig instanceof TrackLine) {
							((TrackLine) fig).setColorStatus(color);
							
							((TrackLine) fig).setTrainName(trainName);
							((TrackLine) fig).setTrainName(trainName);
							((TrackLine) fig).getTrainLabel().setText(trainName);
							((TrackLine) fig).getTrainLabel().setVisible(true);
							
						} else if (fig instanceof TurnoutLS) {
							((TurnoutLS) fig).setColorStatus(color);
							((TurnoutLS) fig).setTrainName(trainName);
						} else if (fig instanceof TurnoutLX) {
							((TurnoutLX) fig).setColorStatus(color);
							((TurnoutLX) fig).setTrainName(trainName);
						} else if (fig instanceof TurnoutRS) {
							((TurnoutRS) fig).setColorStatus(color);
							((TurnoutRS) fig).setTrainName(trainName);
						} else if (fig instanceof TurnoutRX) {
							((TurnoutRX) fig).setColorStatus(color);
							((TurnoutRX) fig).setTrainName(trainName);
						} else if (fig instanceof TurnoutDouble) {
							((TurnoutDouble) fig).setColorStatus(color);
							((TurnoutDouble) fig).setTrainName(trainName);
						} else if (fig instanceof TurnoutDoubleR) {
							((TurnoutDoubleR) fig).setColorStatus(color);
							((TurnoutDoubleR) fig).setTrainName(trainName);
						} else if (fig instanceof TurnoutDoubleL) {
							((TurnoutDoubleL) fig).setColorStatus(color);
							((TurnoutDoubleL) fig).setTrainName(trainName);
						} else if (fig instanceof SemaphoreDoubleL) {
							((SemaphoreDoubleL) fig).setColorStatus(ParamFlag.sep_red);
							((SemaphoreDoubleL) fig).setTrainName(trainName);
						} else if (fig instanceof SemaphoreDoubleR) {
							((SemaphoreDoubleR) fig).setColorStatus(ParamFlag.sep_red);
							((SemaphoreDoubleR) fig).setTrainName(trainName);
						} else if (fig instanceof SemaphoreSimpleL) {
							((SemaphoreSimpleL) fig).setColorStatus(ParamFlag.sep_red);
							((SemaphoreSimpleL) fig).setTrainName(trainName);
						} else if (fig instanceof SemaphoreSimpleR) {
							((SemaphoreSimpleR) fig).setColorStatus(ParamFlag.sep_red);
							((SemaphoreSimpleR) fig).setTrainName(trainName);
						} else {
							System.out.println("--processTrainRoad--出现错误，未找到符合fig的类型！");
						}
				/*		
					}else{						
						
						setTrainName(roadInfoList.get(index)); // 将过去1段trainName设置为空
						
						index = index + 1;
						// 再使index段变红
						roadBasicInfo = roadInfoList.get(index); // 或者路径中的某1段
						color = roadBasicInfo.getRedType();

						fig = roadBasicInfo.getRoadName();
						if (fig instanceof TrackLine) {
							((TrackLine) fig).setColorStatus(color);
							
							((TrackLine) fig).setTrainName(trainName);
							((TrackLine) fig).setTrainName(trainName);
							((TrackLine) fig).getTrainLabel().setText(trainName);
							((TrackLine) fig).getTrainLabel().setVisible(true);
							
						} else if (fig instanceof TurnoutLS) {
							((TurnoutLS) fig).setColorStatus(color);
							((TurnoutLS) fig).setTrainName(trainName);
						} else if (fig instanceof TurnoutLX) {
							((TurnoutLX) fig).setColorStatus(color);
							((TurnoutLX) fig).setTrainName(trainName);
						} else if (fig instanceof TurnoutRS) {
							((TurnoutRS) fig).setColorStatus(color);
							((TurnoutRS) fig).setTrainName(trainName);
						} else if (fig instanceof TurnoutRX) {
							((TurnoutRX) fig).setColorStatus(color);
							((TurnoutRX) fig).setTrainName(trainName);
						} else if (fig instanceof TurnoutDouble) {
							((TurnoutDouble) fig).setColorStatus(color);
							((TurnoutDouble) fig).setTrainName(trainName);
						} else if (fig instanceof TurnoutDoubleR) {
							((TurnoutDoubleR) fig).setColorStatus(color);
							((TurnoutDoubleR) fig).setTrainName(trainName);
						} else if (fig instanceof TurnoutDoubleL) {
							((TurnoutDoubleL) fig).setColorStatus(color);
							((TurnoutDoubleL) fig).setTrainName(trainName);
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
					*/
						
					index = index + 1;
					road.setIndex(index);	
					
				} else if (index >= 3 && index < roadLen) {

					
					setTrainName(roadInfoList.get(index - 1)); // 将过去1段trainName设置为空
					
					//System.out.println("---2---");
								
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
						
						((TrackLine) fig).setTrainName(trainName);
						((TrackLine) fig).setTrainName(trainName);
						((TrackLine) fig).getTrainLabel().setText(trainName);
						((TrackLine) fig).getTrainLabel().setVisible(true);
						
					} else if (fig instanceof TurnoutLS) {
						((TurnoutLS) fig).setColorStatus(color);
						((TurnoutLS) fig).setTrainName(trainName);
					} else if (fig instanceof TurnoutLX) {
						((TurnoutLX) fig).setColorStatus(color);
						((TurnoutLX) fig).setTrainName(trainName);
					} else if (fig instanceof TurnoutRS) {
						((TurnoutRS) fig).setColorStatus(color);
						((TurnoutRS) fig).setTrainName(trainName);
					} else if (fig instanceof TurnoutRX) {
						((TurnoutRX) fig).setColorStatus(color);
						((TurnoutRX) fig).setTrainName(trainName);
					} else if (fig instanceof TurnoutDouble) {
						((TurnoutDouble) fig).setColorStatus(color);
						((TurnoutDouble) fig).setTrainName(trainName);
					} else if (fig instanceof TurnoutDoubleR) {
						((TurnoutDoubleR) fig).setColorStatus(color);
						((TurnoutDoubleR) fig).setTrainName(trainName);
					} else if (fig instanceof TurnoutDoubleL) {
						((TurnoutDoubleL) fig).setColorStatus(color);
						((TurnoutDoubleL) fig).setTrainName(trainName);
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
					
					road.setIndex(index + 1);

				} else if (index == roadLen) { //最后1节

					//System.out.println("---3---");
								
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
										
					//System.out.println("---4---");
					
					String endName = road.getEndName();
					
					if (endName.equalsIgnoreCase("SLFA") || endName.equalsIgnoreCase("XLFA")) {
						
						setTrainName(roadInfoList.get(index - 2)); // 将发车的最后1段trainName设置为空

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
						
						road.setIndex(0);
						road.setFlag(ParamFlag.road_blue);
						
						//hu 2010-7-12
						//发车最后一段要跳转
						
						//System.out.println("------发车最后一段要跳转---111----");
						
						trainDis = new TrainDistrict();
						trainDis.setTrainName(trainName);
						trainDis.setIndex(0);
						if(endName.equalsIgnoreCase("SLFA")){
							trainDis.setDirection(1);
						}else  if(endName.equalsIgnoreCase("XLFA")){
							trainDis.setDirection(0);
						}
						
						if(SicsMain.db == null){
							System.out.println("---SelfDisciplineSystem---发车最后一段要跳转---111---00----SicsMain.db == null");
							return;
						}
					
						String stationName = SicsMain.db.getStationName();
						trainDis.setStartStation(stationName);
												
						//String stationName = "车站二";
						
						if(stationName.equalsIgnoreCase("车站一")){
							if(endName.equalsIgnoreCase("SLFA")){
								trainDis.setDistrict(1);
							}
						}else if(stationName.equalsIgnoreCase("车站二")){
							if(endName.equalsIgnoreCase("SLFA")){
								trainDis.setDistrict(2);
							}else if(endName.equalsIgnoreCase("XLFA")){
								trainDis.setDistrict(1);
							}
						}else if(stationName.equalsIgnoreCase("车站三")){
							if(endName.equalsIgnoreCase("SLFA")){
								trainDis.setDistrict(3);
							}else if(endName.equalsIgnoreCase("XLFA")){
								trainDis.setDistrict(2);
							}
						}else if(stationName.equalsIgnoreCase("车站四")){
							if(endName.equalsIgnoreCase("SLFA")){
								trainDis.setDistrict(4);
							}else if(endName.equalsIgnoreCase("XLFA")){
								trainDis.setDistrict(3);
							}
						}else if(stationName.equalsIgnoreCase("车站五")){
							if(endName.equalsIgnoreCase("XLFA")){
								trainDis.setDistrict(4);
							}
						}
						trainDisList.add(trainDis);
												
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

	//获得trainName
	public String getTrainName(RoadBasicInfo roadBasicInfo){
		
		if(roadBasicInfo == null){
			return null;
		}
		Figure fig = roadBasicInfo.getRoadName();
		if (fig instanceof TrackLine) {
			return ((TrackLine) fig).getTrainName();
		} else if (fig instanceof TurnoutLS) {
			return ((TurnoutLS) fig).getTrainName();
		} else if (fig instanceof TurnoutLX) {
			return ((TurnoutLX) fig).getTrainName();
		} else if (fig instanceof TurnoutRS) {
			return ((TurnoutRS) fig).getTrainName();
		} else if (fig instanceof TurnoutRX) {
			return ((TurnoutRX) fig).getTrainName();
		} else if (fig instanceof TurnoutDouble) {
			return ((TurnoutDouble) fig).getTrainName();
		} else if (fig instanceof TurnoutDoubleR) {
			return ((TurnoutDoubleR) fig).getTrainName();
		} else if (fig instanceof TurnoutDoubleL) {
			return ((TurnoutDoubleL) fig).getTrainName();
		} else if (fig instanceof SemaphoreDoubleL) {
			return ((SemaphoreDoubleL) fig).getTrainName();
		} else if (fig instanceof SemaphoreDoubleR) {
			return ((SemaphoreDoubleR) fig).getTrainName();
		} else if (fig instanceof SemaphoreSimpleL) {
			return ((SemaphoreSimpleL) fig).getTrainName();
		} else if (fig instanceof SemaphoreSimpleR) {
			return ((SemaphoreSimpleR) fig).getTrainName();
		} else {
			System.out.println("--processTrainRoad--出现错误，未找到符合fig的类型！");
			return null;
		}
	}
	
	//设置trainName
	public void setTrainName(RoadBasicInfo roadBasicInfo){
		
		if(roadBasicInfo == null){
			System.out.println("自律机出现错误：setTrainName(): roadBasicInfo == null");
		}
		Figure fig = roadBasicInfo.getRoadName();
		if (fig instanceof TrackLine) {
			((TrackLine) fig).setTrainName("");
			((TrackLine) fig).getTrainLabel().setText("");
			((TrackLine) fig).getTrainLabel().setVisible(false);
		} else if (fig instanceof TurnoutLS) {
			((TurnoutLS) fig).setTrainName("");
		} else if (fig instanceof TurnoutLX) {
			((TurnoutLX) fig).setTrainName("");
		} else if (fig instanceof TurnoutRS) {
			((TurnoutRS) fig).setTrainName("");
		} else if (fig instanceof TurnoutRX) {
			((TurnoutRX) fig).setTrainName("");
		} else if (fig instanceof TurnoutDouble) {
			((TurnoutDouble) fig).setTrainName("");
		} else if (fig instanceof TurnoutDoubleR) {
			((TurnoutDoubleR) fig).setTrainName("");
		} else if (fig instanceof TurnoutDoubleL) {
			((TurnoutDoubleL) fig).setTrainName("");
		} else if (fig instanceof SemaphoreDoubleL) {
			((SemaphoreDoubleL) fig).setTrainName("");
		} else if (fig instanceof SemaphoreDoubleR) {
			((SemaphoreDoubleR) fig).setTrainName("");
		} else if (fig instanceof SemaphoreSimpleL) {
			((SemaphoreSimpleL) fig).setTrainName("");
		} else if (fig instanceof SemaphoreSimpleR) {
			((SemaphoreSimpleR) fig).setTrainName("");
		} else {
			System.out.println("--processTrainRoad--出现错误，未找到符合fig的类型！");
		}
	}
	

/*
	//处理发车交接
	public void processFA(){
		
		trackListXF.add(baseData.getX1lq());
		trackListXF.add(baseData.getX2lq());
		trackListXF.add(baseData.getX3lq());
		trackListXF.add(baseData.getX4lq());
		
		trackListSF.add(baseData.getS1lq());
		trackListSF.add(baseData.getS2lq());
		trackListSF.add(baseData.getS3lq());
		trackListSF.add(baseData.getS4lq());
		
		
		final Timer timerFA = new Timer();
		
		timerFA.schedule(new java.util.TimerTask() {
			public void run() {

				Display.getDefault().asyncExec(new Runnable() {
					public void run() {
						
						runTrain();
						
					}
				});
			}// run
		}, 0, timestamp);
	}
	
	public void runTrain(){
		
		if (this.trainDisList == null || this.trainDisList.size() == 0) {
			System.out.println("trainDisList == null || trainDisList.size() == 0");
			return;
		}

		int len = this.trainDisList.size();
		int direction;
		int district;
		for (int i = 0; i < len; i++) {
			trainDis = trainDisList.get(i);
			direction = trainDis.getDirection();
			district = trainDis.getDistrict();

			if (direction == 1) { // 下行
				if (district == 1) { // 区段1
					processTrainAndSephore(trainDis, trackListXF, null, sepListXFA);
				} else if (district == 2) { // 区段2
					processTrainAndSephore(trainDis, trackListXF, null, sepListXFA);
				} else if (district == 3) { // 区段3
					processTrainAndSephore(trainDis, trackListXF, null, sepListXFA);
				} else if (district == 4) { // 区段4
					processTrainAndSephore(trainDis, trackListXF, null, sepListXFA);
				} else { // 区段不存在
					System.out.println("出现错误！district!=1-4");
				}
			} else if (direction == 0) { // 上行
				if (district == 1) { // 区段1
					processTrainAndSephore(trainDis, trackListSF, sepListSFA, null);
				} else if (district == 2) { // 区段2
					processTrainAndSephore(trainDis, trackListSF, sepListSFA, null);
				} else if (district == 3) { // 区段3
					processTrainAndSephore(trainDis, trackListSF, sepListSFA, null);
				} else if (district == 4) { // 区段4
					processTrainAndSephore(trainDis, trackListSF, sepListSFA, null);
				} else { // 区段不存在
					System.out.println("出现错误！district!=1-4");
				}
			} else {
				System.out.println("出现错误！direction!=0 && direction !=1");
			}

		}
		
		
	}
	
	public void processTrainAndSephore(TrainDistrict train, List<TrackLine> disList, List<SemaphoreDoubleL> sepListL, List<SemaphoreDoubleR> sepListR) {

		if (disList == null || disList.size() == 0) {
			System.out.println("processTrainAndSephore : disList == null || disList.size() == 0");
			return;
		}

		int index = train.getIndex(); // 车次所处的位置
		String trainName = train.getTrainName();
		
		if(index == 0){
			
			if (disList.get(index).getStatus() == 0) {
				disList.get(index).setColorStatus(ParamFlag.trackline_red);
				disList.get(index).getTrainLabel().setText(trainName);
				disList.get(index).getTrainLabel().setVisible(true);
			} else {
				return;
			}

			if (sepListL != null) {
				if (sepListL.get(index).getColor() != ParamFlag.sep_red) {
					sepListL.get(index).setColorStatus(ParamFlag.sep_red);
				} else {
					return;
				}
			} else if (sepListR != null) {
				if (sepListR.get(index).getColor() != ParamFlag.sep_red) {
					sepListR.get(index).setColorStatus(ParamFlag.sep_red);
				} else {
					return;
				}
			} else {
				System.out.println("出现错误！sepListL==null && sepListR ==null");
			}
			
			train.setIndex(index+1);	
			
		}else if(index == 1){
			
			if (disList.get(index).getStatus() == 0) {
				disList.get(index).setColorStatus(ParamFlag.trackline_red);
				disList.get(index).getTrainLabel().setText(trainName);
				disList.get(index).getTrainLabel().setVisible(true);
				//过去1个变蓝
				disList.get(index - 1).setColorStatus(ParamFlag.trackline_bule);
				disList.get(index - 1).getTrainLabel().setText("");
				disList.get(index - 1).getTrainLabel().setVisible(false);
			} else {
				return;
			}

			if (sepListL != null) {
				if (sepListL.get(index).getColor() != ParamFlag.sep_red) {
					sepListL.get(index).setColorStatus(ParamFlag.sep_red);
					//过去1个变黄
					sepListL.get(index - 1).setColorStatus(ParamFlag.sep_yellow);
				} else {
					return;
				}
			} else if (sepListR != null) {
				if (sepListR.get(index).getColor() != ParamFlag.sep_red) {
					sepListR.get(index).setColorStatus(ParamFlag.sep_red);
					//过去1个变黄
					sepListR.get(index - 1).setColorStatus(ParamFlag.sep_yellow);
				} else {
					return;
				}
			} else {
				System.out.println("出现错误！sepListL==null && sepListR ==null");
			}
			
			train.setIndex(index+1);
			
		}else if(index == 2){
			
			if (disList.get(index).getStatus() == 0) {
				disList.get(index).setColorStatus(ParamFlag.trackline_red);
				disList.get(index).getTrainLabel().setText(trainName);
				disList.get(index).getTrainLabel().setVisible(true);
				//过去1个变蓝
				disList.get(index - 1).setColorStatus(ParamFlag.trackline_bule);
				disList.get(index - 1).getTrainLabel().setText("");
				disList.get(index - 1).getTrainLabel().setVisible(false);
			} else {
				return;
			}

			if (sepListL != null) {
				if (sepListL.get(index).getColor() != ParamFlag.sep_red) {
					sepListL.get(index).setColorStatus(ParamFlag.sep_red);
					//过去1个变黄
					sepListL.get(index - 1).setColorStatus(ParamFlag.sep_yellow);
					//过去2个变为绿黄
					if(sepListL.get(index - 2).getColor() == ParamFlag.sep_yellow){
						sepListL.get(index - 2).setColorStatus(ParamFlag.sep_green_yellow);
					}
				} else {
					return;
				}
			} else if (sepListR != null) {
				if (sepListR.get(index).getColor() != ParamFlag.sep_red) {
					sepListR.get(index).setColorStatus(ParamFlag.sep_red);
					//过去1个变黄
					sepListR.get(index - 1).setColorStatus(ParamFlag.sep_yellow);
					//过去2个变为绿黄
					if(sepListR.get(index - 2).getColor() == ParamFlag.sep_yellow){
						sepListR.get(index - 2).setColorStatus(ParamFlag.sep_green_yellow);
					}
				} else {
					return;
				}
			} else {
				System.out.println("出现错误！sepListL==null && sepListR ==null");
			}
			
			train.setIndex(index+1);			
			
		}else if(index >= 3){
			
			if (disList.get(index).getStatus() == 0) {
				disList.get(index).setColorStatus(ParamFlag.trackline_red);
				disList.get(index).getTrainLabel().setText(trainName);
				disList.get(index).getTrainLabel().setVisible(true);
				//过去1个变蓝
				disList.get(index - 1).setColorStatus(ParamFlag.trackline_bule);
				disList.get(index - 1).getTrainLabel().setText("");
				disList.get(index - 1).getTrainLabel().setVisible(false);
			} else {
				return;
			}

			if (sepListL != null) {
				if (sepListL.get(index).getColor() != ParamFlag.sep_red) {
					sepListL.get(index).setColorStatus(ParamFlag.sep_red);
					//过去1个变黄
					sepListL.get(index - 1).setColorStatus(ParamFlag.sep_yellow);
					//过去2个变为绿黄
					if(sepListL.get(index - 2).getColor() == ParamFlag.sep_yellow){
						sepListL.get(index - 2).setColorStatus(ParamFlag.sep_green_yellow);
						//过去3个变为绿
						if(sepListL.get(index - 3).getColor() == ParamFlag.sep_green_yellow){
							sepListL.get(index - 3).setColorStatus(ParamFlag.sep_green);
						}
					}
				} else {
					return;
				}
			} else if (sepListR != null) {
				if (sepListR.get(index).getColor() != ParamFlag.sep_red) {
					sepListR.get(index).setColorStatus(ParamFlag.sep_red);
					//过去1个变黄
					sepListR.get(index - 1).setColorStatus(ParamFlag.sep_yellow);
					//过去2个变为绿黄
					if(sepListR.get(index - 2).getColor() == ParamFlag.sep_yellow){
						sepListR.get(index - 2).setColorStatus(ParamFlag.sep_green_yellow);
						//过去3个变为绿
						if(sepListR.get(index - 3).getColor() == ParamFlag.sep_green_yellow){
							sepListR.get(index - 3).setColorStatus(ParamFlag.sep_green);
						}
					}
				} else {
					return;
				}
			} else {
				System.out.println("出现错误！sepListL==null && sepListR ==null");
			}
			if(index == 19){
				
			}else{
				train.setIndex(index+1);	
			}			
		}
	}
*/

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


	public static List<TrainDistrict> getTrainDisList() {
		return trainDisList;
	}
	public static void setTrainDisList(List<TrainDistrict> trainDisList) {
		SelfDisciplineSystem.trainDisList = trainDisList;
	}

	

}
