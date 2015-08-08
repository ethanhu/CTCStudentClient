package ctc.sics.station5.layout;

import java.util.ArrayList;

import org.eclipse.draw2d.Figure;

import ctc.ctc.ctc2sics.CTCToSICS;
import ctc.sics.SicsMain;
import ctc.sics.sics2ctc.SICSToCTC;

import ctc.sics.station5.data.*;
import ctc.sics.station5.elements.common.*;
import ctc.sics.station5.elements.semaphore.*;
import ctc.sics.station5.elements.turnout.*;

import ctc.sics.station.sound.SoundPlayer;


public class StationModel {

	BaseParam baseDataForStation = BaseParam.getInstance();
	SoundPlayer SP = SoundPlayer.getInstance(); //播放器
		
	private static StationModel stationModel = null;

	public static StationModel getInstance() {
		if (stationModel == null) {
			stationModel = new StationModel();
		}
		return stationModel;
	}

	// 根据名称获得对象
	public Figure getFigureObjectByName(String name) {

		// System.out.println("寻找：" + name);

		Figure fig = null;

		if (baseDataForStation.getAllRoadFigureList() != null && baseDataForStation.getAllRoadFigureList().size() != 0) {

			int len = baseDataForStation.getAllRoadFigureList().size();

			for (int i = 0; i < len; i++) {

				fig = baseDataForStation.getAllRoadFigureList().get(i);

				if (fig instanceof TrackLine) {
					if (((TrackLine) fig).getName().equalsIgnoreCase(name)) {
						return fig;
					}
				} else if (fig instanceof TurnoutLS) {
					if (((TurnoutLS) fig).getName().equalsIgnoreCase(name)) {
						return fig;
					}
				} else if (fig instanceof TurnoutLX) {
					if (((TurnoutLX) fig).getName().equalsIgnoreCase(name)) {
						return fig;
					}
				} else if (fig instanceof TurnoutRS) {
					if (((TurnoutRS) fig).getName().equalsIgnoreCase(name)) {
						return fig;
					}
				} else if (fig instanceof TurnoutRX) {
					if (((TurnoutRX) fig).getName().equalsIgnoreCase(name)) {
						return fig;
					}
				} else if (fig instanceof TurnoutDouble) {
					if (((TurnoutDouble) fig).getName().equalsIgnoreCase(name)) {
						return fig;
					}
				} else if (fig instanceof TurnoutDoubleR) {
					if (((TurnoutDoubleR) fig).getName().equalsIgnoreCase(name)) {
						return fig;
					}
				} else if (fig instanceof TurnoutDoubleL) {
					if (((TurnoutDoubleL) fig).getName().equalsIgnoreCase(name)) {
						return fig;
					}
				} else if (fig instanceof SemaphoreDoubleL) {
					if (((SemaphoreDoubleL) fig).getName().equalsIgnoreCase(name)) {
						return fig;
					}
				} else if (fig instanceof SemaphoreDoubleR) {
					if (((SemaphoreDoubleR) fig).getName().equalsIgnoreCase(name)) {
						return fig;
					}
				} else if (fig instanceof SemaphoreSimpleL) {
					if (((SemaphoreSimpleL) fig).getName().equalsIgnoreCase(name)) {
						return fig;
					}
				} else if (fig instanceof SemaphoreSimpleR) {
					if (((SemaphoreSimpleR) fig).getName().equalsIgnoreCase(name)) {
						return fig;
					}
				}
			}

			System.out.println("没有找到：" + name);

			return fig;

		} else {
			return fig;
		}
	}

	//引导总锁闭
	public void turnoutYZS(String type, int colorFlag) {

		System.out.println("---引导总锁闭");
		
		Figure fig = null;

		if (baseDataForStation.getAllTurnoutFigureList() != null && baseDataForStation.getAllTurnoutFigureList().size() != 0) {
			int len = baseDataForStation.getAllTurnoutFigureList().size();
			for (int i = 0; i < len; i++) {
				fig = baseDataForStation.getAllTurnoutFigureList().get(i);
				
				if (fig instanceof TurnoutLS) {
					if (((TurnoutLS) fig).getType().equalsIgnoreCase(type)) {
						((TurnoutLS) fig).setColorStatus(colorFlag);
						System.out.println("--TurnoutLS--");
					}
				} else if (fig instanceof TurnoutLX) {
					if (((TurnoutLX) fig).getType().equalsIgnoreCase(type)) {
						((TurnoutLX) fig).setColorStatus(colorFlag);
						System.out.println("--TurnoutLX--");
					}
				} else if (fig instanceof TurnoutRS) {
					if (((TurnoutRS) fig).getType().equalsIgnoreCase(type)) {
						((TurnoutRS) fig).setColorStatus(colorFlag);
						System.out.println("--TurnoutRS--");
					}
				} else if (fig instanceof TurnoutRX) {
					if (((TurnoutRX) fig).getType().equalsIgnoreCase(type)) {
						((TurnoutRX) fig).setColorStatus(colorFlag);
						System.out.println("--TurnoutRX--");
					}
				} else if (fig instanceof TurnoutDouble) {
					if (((TurnoutDouble) fig).getType().equalsIgnoreCase(type)) {
						((TurnoutDouble) fig).setColorStatus(colorFlag);
						System.out.println("--TurnoutDouble--");
					}
				} else if (fig instanceof TurnoutDoubleR) {
					if (((TurnoutDoubleR) fig).getType().equalsIgnoreCase(type)) {
						((TurnoutDoubleR) fig).setColorStatus(colorFlag);
						System.out.println("--TurnoutDoubleR--");
					}
				} else if (fig instanceof TurnoutDoubleL) {
					if (((TurnoutDoubleL) fig).getType().equalsIgnoreCase(type)) {
						((TurnoutDoubleL) fig).setColorStatus(colorFlag);
						System.out.println("--TurnoutDoubleL--");
					}
				}
			}
		} else {
			SP.soundCZCW();
			System.out.println("道岔数 = 0");
			return;
		}
	}
	
	
	// 道岔总定位
	public void turnoutZDW(String type) {

		Figure fig = null;

		if (baseDataForStation.getAllTurnoutFigureList() != null && baseDataForStation.getAllTurnoutFigureList().size() != 0) {

			int len = baseDataForStation.getAllTurnoutFigureList().size();

			for (int i = 0; i < len; i++) {
				fig = baseDataForStation.getAllTurnoutFigureList().get(i);
				if (fig instanceof TurnoutLS) {
					if (((TurnoutLS) fig).getType().equalsIgnoreCase(type) && ((TurnoutLS) fig).getStatus() == 0) {
						((TurnoutLS) fig).setColorStatus(ParamFlag.turnout_dw_bule);
					}
				} else if (fig instanceof TurnoutLX) {
					if (((TurnoutLX) fig).getType().equalsIgnoreCase(type) && ((TurnoutLX) fig).getStatus() == 0) {
						((TurnoutLX) fig).setColorStatus(ParamFlag.turnout_dw_bule);
					}
				} else if (fig instanceof TurnoutRS) {
					if (((TurnoutRS) fig).getType().equalsIgnoreCase(type) && ((TurnoutRS) fig).getStatus() == 0) {
						((TurnoutRS) fig).setColorStatus(ParamFlag.turnout_dw_bule);
					}
				} else if (fig instanceof TurnoutRX) {
					if (((TurnoutRX) fig).getType().equalsIgnoreCase(type) && ((TurnoutRX) fig).getStatus() == 0) {
						((TurnoutRX) fig).setColorStatus(ParamFlag.turnout_dw_bule);
					}
				} else if (fig instanceof TurnoutDouble) {
					if (((TurnoutDouble) fig).getType().equalsIgnoreCase(type) && ((TurnoutDouble) fig).getStatus() == 0) {
						((TurnoutDouble) fig).setColorStatus(ParamFlag.turnout_dw_bule);
					}
				} else if (fig instanceof TurnoutDoubleR) {
					if (((TurnoutDoubleR) fig).getType().equalsIgnoreCase(type) && ((TurnoutDoubleR) fig).getStatus() == 0) {
						((TurnoutDoubleR) fig).setColorStatus(ParamFlag.turnout_dw_bule);
					}
				} else if (fig instanceof TurnoutDoubleL) {
					if (((TurnoutDoubleL) fig).getType().equalsIgnoreCase(type) && ((TurnoutDoubleL) fig).getStatus() == 0) {
						((TurnoutDoubleL) fig).setColorStatus(ParamFlag.turnout_dw_bule);
					}
				}
			}
		} else {
			SP.soundCZCW();
			System.out.println("道岔数 = 0");
			return;
		}
	}

	// 道岔总反位
	public void turnoutZFW(String type) {

		Figure fig = null;

		if (baseDataForStation.getAllTurnoutFigureList() != null && baseDataForStation.getAllTurnoutFigureList().size() != 0) {

			int len = baseDataForStation.getAllTurnoutFigureList().size();
			for (int i = 0; i < len; i++) {
				fig = baseDataForStation.getAllTurnoutFigureList().get(i);
				if (fig instanceof TurnoutLS) {
					if (((TurnoutLS) fig).getType().equalsIgnoreCase(type) && ((TurnoutLS) fig).getStatus() == 0) {
						((TurnoutLS) fig).setColorStatus(ParamFlag.turnout_fw_bule);
					}
				} else if (fig instanceof TurnoutLX) {
					if (((TurnoutLX) fig).getType().equalsIgnoreCase(type) && ((TurnoutLX) fig).getStatus() == 0) {
						((TurnoutLX) fig).setColorStatus(ParamFlag.turnout_fw_bule);
					}
				} else if (fig instanceof TurnoutRS) {
					if (((TurnoutRS) fig).getType().equalsIgnoreCase(type) && ((TurnoutRS) fig).getStatus() == 0) {
						((TurnoutRS) fig).setColorStatus(ParamFlag.turnout_fw_bule);
					}
				} else if (fig instanceof TurnoutRX) {
					if (((TurnoutRX) fig).getType().equalsIgnoreCase(type) && ((TurnoutRX) fig).getStatus() == 0) {
						((TurnoutRX) fig).setColorStatus(ParamFlag.turnout_fw_bule);
					}
				} else if (fig instanceof TurnoutDouble) {
					if (((TurnoutDouble) fig).getType().equalsIgnoreCase(type) && ((TurnoutDouble) fig).getStatus() == 0) {
						((TurnoutDouble) fig).setColorStatus(ParamFlag.turnout_fw_bule);
					}
				} else if (fig instanceof TurnoutDoubleR) {
					if (((TurnoutDoubleR) fig).getType().equalsIgnoreCase(type) && ((TurnoutDoubleR) fig).getStatus() == 0) {
						((TurnoutDoubleR) fig).setColorStatus(ParamFlag.turnout_fw_bule);
					}
				} else if (fig instanceof TurnoutDoubleL) {
					if (((TurnoutDoubleL) fig).getType().equalsIgnoreCase(type) && ((TurnoutDoubleL) fig).getStatus() == 0) {
						((TurnoutDoubleL) fig).setColorStatus(ParamFlag.turnout_fw_bule);
					}
				}
			}
		} else {
			SP.soundCZCW();
			return;
		}
	}

	// 处理道路相关的内容
	public void processRoad(String startName, String midName, String endName, int road_color) {

		//将变化传递给CTC
		CTCToSICS.sendTaskMessageASYN("车站五", startName, endName, road_color);
		
		Boolean findRoad = false;
		
		RoadList road = new RoadList(); // 路径
		RoadBasicInfo roadBasicInfo = new RoadBasicInfo(); // 股道段基本信息
		ArrayList<RoadBasicInfo> roadInfoList = new ArrayList<RoadBasicInfo>(); // 股道段基本信息list

		if (baseDataForStation.getAllRoadList() != null && baseDataForStation.getAllRoadList().size() != 0) {

			int len = baseDataForStation.getAllRoadList().size();
			for (int i = 0; i < len; i++) {
				
				int sepColor = ParamFlag.sep_green;
				if(startName.substring(0, 1).equalsIgnoreCase("D")||startName.substring(2, 3).equalsIgnoreCase("D")){
					sepColor = ParamFlag.sep_white;
				}
				
				road = baseDataForStation.getAllRoadList().get(i); // 取出1条路径
				if(midName.equalsIgnoreCase("BA")){
					if (road.getMidName().equalsIgnoreCase("BA") && road.getStartName().equalsIgnoreCase(startName) && road.getEndName().equalsIgnoreCase(endName)) {
						findRoad = true;
					}else{
						continue;
					}
				} else {
					if (road.getStartName().equalsIgnoreCase(startName) && road.getEndName().equalsIgnoreCase(endName)) {
						findRoad = true;	
					}else{
						continue;
					}
				}
					
				if (findRoad = true) {	
					
					int result = judgeRoadStatus(road);

					if (result == 0 && road_color == ParamFlag.road_green) { // 接发车的通路链接

						System.out.println("--接发车--");
						
						
						

						roadInfoList = road.getRoadInfoList(); // 获得路径的list
						if (roadInfoList != null && roadInfoList.size() >= 2) {

							int s = 0; //从第一个通路开始排通，包括信号机						
							
							int roadLen = roadInfoList.size();
							for (int j = s; j < roadLen; j++) {

								roadBasicInfo = roadInfoList.get(j); // 或者路径中的某1段
								int color = roadBasicInfo.getGreenType();

								Figure fig = roadBasicInfo.getRoadName();

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
									((SemaphoreDoubleL) fig).setColorStatus(sepColor);
								} else if (fig instanceof SemaphoreDoubleR) {
									((SemaphoreDoubleR) fig).setColorStatus(sepColor);
								} else if (fig instanceof SemaphoreSimpleL) {
									((SemaphoreSimpleL) fig).setColorStatus(sepColor);
								} else if (fig instanceof SemaphoreSimpleR) {
									((SemaphoreSimpleR) fig).setColorStatus(sepColor);
								}else {
									System.out.println("出现错误，未找到符合fig的类型！");
								}
							}
							road.setFlag(ParamFlag.road_green);
						}					

					} else if (result == 1 && road_color == ParamFlag.road_blue) { // 取消

						System.out.println("--取消--");

						roadInfoList = road.getRoadInfoList(); // 获得路径的list
						if (roadInfoList != null && roadInfoList.size() >= 2) {

							int s = 0;	//从第1个路径元素开始取消，包括信号机					
							
							int roadLen = roadInfoList.size();
							for (int j = s; j < roadLen; j++) {

								roadBasicInfo = roadInfoList.get(j); // 或者路径中的某1段
								int color = roadBasicInfo.getBlueType();

								Figure fig = roadBasicInfo.getRoadName();

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
									((SemaphoreSimpleL) fig).setColorStatus(ParamFlag.sep_blue);
								} else if (fig instanceof SemaphoreSimpleR) {
									((SemaphoreSimpleR) fig).setColorStatus(ParamFlag.sep_blue);
								} else {
									System.out.println("出现错误，未找到符合fig的类型！");
								}
							}// end for

							road.setFlag(ParamFlag.road_blue);

						} // end if
					} else if (result == 2) {
						SP.soundJLXBC();
						System.out.println("--无法操作--");
						return;
					}
				}// end if
				break;
			}// end for		
			
			if(findRoad == false){
				SP.soundCZCW();
			}
			
		}// end if
	}


	// 处理道路相关的内容
	public void processRoadCancel(String buttonName, String startName) {

		//将变化传递给CTC
		if(SicsMain.db != null){
			//SICSToCTC.sendTaskMessageASYN(startName, endName, road_color);
		}
		
		Boolean findRoad = false;
		
		RoadList road = new RoadList(); // 路径
		RoadBasicInfo roadBasicInfo = new RoadBasicInfo(); // 股道段基本信息
		ArrayList<RoadBasicInfo> roadInfoList = new ArrayList<RoadBasicInfo>(); // 股道段基本信息list

		if (baseDataForStation.getAllRoadList() != null && baseDataForStation.getAllRoadList().size() != 0) {

			int len = baseDataForStation.getAllRoadList().size();
			for (int i = 0; i < len; i++) {
				
				road = baseDataForStation.getAllRoadList().get(i); // 取出1条路径
			
				int road_color = 0;
				
				if (findRoad = true) {	
					
					int result = judgeRoadStatus(road);

					if (result == 0 && road_color == ParamFlag.road_green) { // 接发车的通路链接

						System.out.println("--接发车--");

						roadInfoList = road.getRoadInfoList(); // 获得路径的list
						if (roadInfoList != null && roadInfoList.size() >= 2) {

							int s = 0; //从第一个通路开始排通，包括信号机						
							
							int roadLen = roadInfoList.size();
							for (int j = s; j < roadLen; j++) {

								roadBasicInfo = roadInfoList.get(j); // 或者路径中的某1段
								int color = roadBasicInfo.getGreenType();

								Figure fig = roadBasicInfo.getRoadName();

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
									((SemaphoreDoubleL) fig).setColorStatus(ParamFlag.sep_green);
								} else if (fig instanceof SemaphoreDoubleR) {
									((SemaphoreDoubleR) fig).setColorStatus(ParamFlag.sep_green);
								} else if (fig instanceof SemaphoreSimpleL) {
									((SemaphoreSimpleL) fig).setColorStatus(ParamFlag.sep_green);
								} else if (fig instanceof SemaphoreSimpleR) {
									((SemaphoreSimpleR) fig).setColorStatus(ParamFlag.sep_green);
								}else {
									System.out.println("出现错误，未找到符合fig的类型！");
								}
							}
							road.setFlag(ParamFlag.road_green);
						}					

					} else if (result == 1 && road_color == ParamFlag.road_blue) { // 取消

						System.out.println("--取消--");

						roadInfoList = road.getRoadInfoList(); // 获得路径的list
						if (roadInfoList != null && roadInfoList.size() >= 2) {

							int s = 0;	//从第1个路径元素开始取消，包括信号机					
							
							int roadLen = roadInfoList.size();
							for (int j = s; j < roadLen; j++) {

								roadBasicInfo = roadInfoList.get(j); // 或者路径中的某1段
								int color = roadBasicInfo.getBlueType();

								Figure fig = roadBasicInfo.getRoadName();

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
									System.out.println("出现错误，未找到符合fig的类型！");
								}
							}// end for

							road.setFlag(ParamFlag.road_blue);

						} // end if
					} else if (result == 2) {
						SP.soundJLXBC();
						System.out.println("--无法操作--");
						return;
					}
				}// end if
				break;
			}// end for		
			
			if(findRoad == false){
				SP.soundCZCW();
			}
			
		}// end if
	}
	
	
	// 处理道路相关的内容
	public RoadList getRoadByName(String startName, String endName) {

		RoadList road = new RoadList(); // 路径

		if (baseDataForStation.getAllRoadList() != null && baseDataForStation.getAllRoadList().size() != 0) {
			int len = baseDataForStation.getAllRoadList().size();
			for (int i = 0; i < len; i++) {
				road = baseDataForStation.getAllRoadList().get(i); // 取出1条路径
				if (road.getStartName().equalsIgnoreCase(startName) && road.getEndName().equalsIgnoreCase(endName)) {
					return road;
				}
			}
			System.out.println(" getRoadByName 找不到符合条件的路径！");
			return null;
		} else {
			System.out.println("baseDataForStation.getAllRoadList()==null && baseDataForStation.getAllRoadList().size()==0");
			return null;
		}
	}

	// 出来道岔单操
	public void processTurnout(String name) {

		if (name.equalsIgnoreCase("1/3") || name.equalsIgnoreCase("5/7")) {
			name = "1/3_5/7";
		} else if (name.equalsIgnoreCase("2/4") || name.equalsIgnoreCase("6/8")) {
			name = "2/4_6/8";
		}

		baseDataForStation.getAllTurnoutFigureList();

		Figure fig = null;

		if (baseDataForStation.getAllTurnoutFigureList() != null && baseDataForStation.getAllTurnoutFigureList().size() != 0) {

			int len = baseDataForStation.getAllTurnoutFigureList().size();
			for (int i = 0; i < len; i++) {
				fig = baseDataForStation.getAllTurnoutFigureList().get(i);
				if (fig instanceof TurnoutLS) {
					if (((TurnoutLS) fig).getName().equalsIgnoreCase(name) && ((TurnoutLS) fig).getStatus() == 0) {
						if (((TurnoutLS) fig).getTurnoutStatus() == 1) {
							((TurnoutLS) fig).setColorStatus(ParamFlag.turnout_fw_bule);
						} else {
							((TurnoutLS) fig).setColorStatus(ParamFlag.turnout_dw_bule);
						}
					}
				} else if (fig instanceof TurnoutLX) {
					if (((TurnoutLX) fig).getName().equalsIgnoreCase(name) && ((TurnoutLX) fig).getStatus() == 0) {
						if (((TurnoutLX) fig).getTurnoutStatus() == 1) {
							((TurnoutLX) fig).setColorStatus(ParamFlag.turnout_fw_bule);
						} else {
							((TurnoutLX) fig).setColorStatus(ParamFlag.turnout_dw_bule);
						}
					}
				} else if (fig instanceof TurnoutRS) {
					if (((TurnoutRS) fig).getName().equalsIgnoreCase(name) && ((TurnoutRS) fig).getStatus() == 0) {
						if (((TurnoutRS) fig).getTurnoutStatus() == 1) {
							((TurnoutRS) fig).setColorStatus(ParamFlag.turnout_fw_bule);
						} else {
							((TurnoutRS) fig).setColorStatus(ParamFlag.turnout_dw_bule);
						}
					}
				} else if (fig instanceof TurnoutRX) {
					if (((TurnoutRX) fig).getName().equalsIgnoreCase(name) && ((TurnoutRX) fig).getStatus() == 0) {
						if (((TurnoutRX) fig).getTurnoutStatus() == 1) {
							((TurnoutRX) fig).setColorStatus(ParamFlag.turnout_fw_bule);
						} else {
							((TurnoutRX) fig).setColorStatus(ParamFlag.turnout_dw_bule);
						}
					}
				} else if (fig instanceof TurnoutDouble) {
					if (((TurnoutDouble) fig).getName().equalsIgnoreCase(name) && ((TurnoutDouble) fig).getStatus() == 0) {
						if (((TurnoutDouble) fig).getTurnoutStatus() == 1) {
							((TurnoutDouble) fig).setColorStatus(ParamFlag.turnout_fw_bule);
						} else {
							((TurnoutDouble) fig).setColorStatus(ParamFlag.turnout_dw_bule);
						}
					}
				} else if (fig instanceof TurnoutDoubleR) {
					if (((TurnoutDoubleR) fig).getName().equalsIgnoreCase(name) && ((TurnoutDoubleR) fig).getStatus() == 0) {
						if (((TurnoutDoubleR) fig).getTurnoutStatus() == 1) {
							((TurnoutDoubleR) fig).setColorStatus(ParamFlag.turnout_fw_bule);
						} else {
							((TurnoutDoubleR) fig).setColorStatus(ParamFlag.turnout_dw_bule);
						}
					}
				} else if (fig instanceof TurnoutDoubleL) {
					if (((TurnoutDoubleL) fig).getName().equalsIgnoreCase(name) && ((TurnoutDoubleL) fig).getStatus() == 0) {
						if (((TurnoutDoubleL) fig).getTurnoutStatus() == 1) {
							((TurnoutDoubleL) fig).setColorStatus(ParamFlag.turnout_fw_bule);
						} else {
							((TurnoutDoubleL) fig).setColorStatus(ParamFlag.turnout_dw_bule);
						}
					}
				} else {
					System.out.println("没有找到名称为 " + name + " 的Turnout！");
				}
			}
		} else {
			System.out.println("TurnoutList为空！");
		}
	}

	// 判断道路的状态
	public int judgeRoadStatus(RoadList road) {

		System.out.println("----judgeRoadStatus--start----");

		int sumLen = 0;
		int roadLen = 0;
		int s = 0;
		RoadBasicInfo roadBasicInfo = new RoadBasicInfo(); // 股道段基本信息
		ArrayList<RoadBasicInfo> roadInfoList = new ArrayList<RoadBasicInfo>(); // 股道段基本信息list

		if (road != null) {
			String startName = road.getStartName();
			roadInfoList = road.getRoadInfoList(); // 获得路径的list
			if (roadInfoList != null && roadInfoList.size() >= 2) {
				roadLen = roadInfoList.size();
				
				s = 1; //都从第2个元素开始判断状态，为的是避免信号机

				for (int j = s; j < roadLen; j++) {
					roadBasicInfo = roadInfoList.get(j); // 或者路径中的某1段
					Figure fig = roadBasicInfo.getRoadName();

					if (fig instanceof TrackLine) {
						if (((TrackLine) fig).getStatus() == 2) { //被车占用
							return 2;
						} else if (((TrackLine) fig).getStatus() == 0) {
							sumLen = sumLen + 1;
						}
					} else if (fig instanceof TurnoutLS) {
						if (((TurnoutLS) fig).getStatus() == 2) {
							return 2;
						} else if (((TurnoutLS) fig).getStatus() == 0) {
							sumLen = sumLen + 1;
						}
					} else if (fig instanceof TurnoutLX) {
						if (((TurnoutLX) fig).getStatus() == 2) {
							return 2;
						} else if (((TurnoutLX) fig).getStatus() == 0) {
							sumLen = sumLen + 1;
						}
					} else if (fig instanceof TurnoutRS) {
						if (((TurnoutRS) fig).getStatus() == 2) {
							return 2;
						} else if (((TurnoutRS) fig).getStatus() == 0) {
							sumLen = sumLen + 1;
						}
					} else if (fig instanceof TurnoutRX) {
						if (((TurnoutRX) fig).getStatus() == 2) {
							return 2;
						} else if (((TurnoutRX) fig).getStatus() == 0) {
							sumLen = sumLen + 1;
						}
					} else if (fig instanceof TurnoutDouble) {
						if (((TurnoutDouble) fig).getStatus() == 2) {
							return 2;
						} else if (((TurnoutDouble) fig).getStatus() == 0) {
							sumLen = sumLen + 1;
						}
					} else if (fig instanceof TurnoutDoubleR) {
						if (((TurnoutDoubleR) fig).getStatus() == 2) {
							return 2;
						} else if (((TurnoutDoubleR) fig).getStatus() == 0) {
							sumLen = sumLen + 1;
						}
					} else if (fig instanceof TurnoutDoubleL) {
						if (((TurnoutDoubleL) fig).getStatus() == 2) {
							return 2;
						} else if (((TurnoutDoubleL) fig).getStatus() == 0) {
							sumLen = sumLen + 1;
						}
					} else if (fig instanceof SemaphoreDoubleL) {
						if(((SemaphoreDoubleL) fig).getStatus() == 2){
							return 2;
						}else if(((SemaphoreDoubleL) fig).getStatus() == 0){
							sumLen = sumLen + 1;
						}
					} else if (fig instanceof SemaphoreDoubleR) {
						if(((SemaphoreDoubleR) fig).getStatus() == 2){
							return 2;
						}else if(((SemaphoreDoubleR) fig).getStatus() == 0){
							sumLen = sumLen + 1;
						}
					} else if (fig instanceof SemaphoreSimpleL) {
						if(((SemaphoreSimpleL) fig).getStatus() == 2){
							return 2;
						}else if(((SemaphoreSimpleL) fig).getStatus() == 0){
							sumLen = sumLen + 1;
						}
					} else if (fig instanceof SemaphoreSimpleR) {
						if(((SemaphoreSimpleR) fig).getStatus() == 2){
							return 2;
						}else if(((SemaphoreSimpleR) fig).getStatus() == 0){
							sumLen = sumLen + 1;
						}
					} else {
						System.out.println("出现错误，未找到符合fig的类型！");
					}
				}// end for
			}
		} else {
			System.out.println("road ==null");
		}

		if (sumLen == roadLen - s) {
			return 0; //整个路径全为蓝色
		} else {
			return 1; //说明为绿色，是取消
		}
	}

}
