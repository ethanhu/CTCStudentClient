package ctc.ctc.ctc2sics;

import java.util.ArrayList;

import org.eclipse.draw2d.Figure;

import ctc.sics.station4.data.*;
import ctc.sics.station4.elements.common.*;
import ctc.sics.station4.elements.semaphore.*;
import ctc.sics.station4.elements.turnout.*;
import ctc.sics.station4.sound.SoundPlayer;

public class ProSICSMessageStation4 {

	BaseParam baseDataForStation = BaseParam.getInstance();
	SoundPlayer SP = SoundPlayer.getInstance(); //播放器
		
	private static ProSICSMessageStation4 proModelStation4 = null;

	public static ProSICSMessageStation4 getInstance() {
		if (proModelStation4 == null) {
			proModelStation4 = new ProSICSMessageStation4();
		}
		return proModelStation4;
	}

	
	// 处理道路相关的内容
	public int processTask(String startName, String endName, int road_color) {

		Boolean findRoad = false;
		int result = -1;
		
		RoadList road = new RoadList(); // 路径
		RoadBasicInfo roadBasicInfo = new RoadBasicInfo(); // 股道段基本信息
		ArrayList<RoadBasicInfo> roadInfoList = new ArrayList<RoadBasicInfo>(); // 股道段基本信息list

		if (baseDataForStation.getAllRoadList() != null && baseDataForStation.getAllRoadList().size() != 0) {

			int len = baseDataForStation.getAllRoadList().size();
			for (int i = 0; i < len; i++) {
				road = baseDataForStation.getAllRoadList().get(i); // 取出1条路径
				if (road.getStartName().equalsIgnoreCase(startName) && road.getEndName().equalsIgnoreCase(endName)) {

					findRoad = true;
					
					result = judgeRoadStatus(road);

					if (result == 0 && road_color == ParamFlag.road_green) { // 接发车

						roadInfoList = road.getRoadInfoList(); // 获得路径的list
						if (roadInfoList != null && roadInfoList.size() >= 2) {

							int s = 0;
							if (startName.equalsIgnoreCase("XLA") || startName.equalsIgnoreCase("XTA") || startName.equalsIgnoreCase("SLA")
									|| startName.equalsIgnoreCase("STA")) {
								s = 0;
							} else {
								s = 2;
							}

							if(s == 2){
								
								roadBasicInfo = roadInfoList.get(0); // 或者路径中的某1段
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

							int s = 0;
							if (startName.equalsIgnoreCase("XLA") || startName.equalsIgnoreCase("XTA") || startName.equalsIgnoreCase("SLA")
									|| startName.equalsIgnoreCase("STA")) {
								s = 0;
							} else {
								s = 2;
							}

							if(s == 2){
								
								roadBasicInfo = roadInfoList.get(0); // 或者路径中的某1段
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
									((SemaphoreDoubleL) fig).setColorStatus(ParamFlag.sep_red);
								} else if (fig instanceof SemaphoreDoubleR) {
									((SemaphoreDoubleR) fig).setColorStatus(ParamFlag.sep_red);
								} else if (fig instanceof SemaphoreSimpleL) {
									((SemaphoreSimpleL) fig).setColorStatus(ParamFlag.sep_red);
								} else if (fig instanceof SemaphoreSimpleR) {
									((SemaphoreSimpleR) fig).setColorStatus(ParamFlag.sep_red);
								}else {
									System.out.println("出现错误，未找到符合fig的类型！");
								}
								
							}
							
							
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
						return -1;
					}
				}// end if
			}// end for		
			
			if(findRoad == false){
				SP.soundCZCW();
			}
			
		}// end if
		
		return result;
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
				if (startName.equalsIgnoreCase("XLA") || startName.equalsIgnoreCase("XTA") || startName.equalsIgnoreCase("SLA")
						|| startName.equalsIgnoreCase("STA")) {
					s = 0;
				} else {
					s = 2;
				}

				for (int j = s; j < roadLen; j++) {
					roadBasicInfo = roadInfoList.get(j); // 或者路径中的某1段
					Figure fig = roadBasicInfo.getRoadName();

					if (fig instanceof TrackLine) {
						if (((TrackLine) fig).getStatus() == 2) {
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
			return 0;
		} else {
			return 1;
		}
	}
	
	// 根据名称获得对象
	public Figure getFigureObjectByName(String name) {

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
	
	// 设置故障
	public void SZGZ(String name) {

		
		Figure fig = getFigureObjectByName(name);
		
		if (fig == null) {
			return;
		}

		if (fig instanceof TrackLine) {
			if (((TrackLine) fig).getName().equalsIgnoreCase(name)) {
				((TrackLine) fig).setColorStatus(ParamFlag.trackline_black);
			}
		} else if (fig instanceof TurnoutLS) {
			if (((TurnoutLS) fig).getName().equalsIgnoreCase(name)) {
				((TurnoutLS) fig).setColorStatus(ParamFlag.turnout_dw_black);
			}
		} else if (fig instanceof TurnoutLX) {
			if (((TurnoutLX) fig).getName().equalsIgnoreCase(name)) {
				((TurnoutLX) fig).setColorStatus(ParamFlag.turnout_dw_black);
			}
		} else if (fig instanceof TurnoutRS) {
			if (((TurnoutRS) fig).getName().equalsIgnoreCase(name)) {
				((TurnoutRS) fig).setColorStatus(ParamFlag.turnout_dw_black);
			}
		} else if (fig instanceof TurnoutRX) {
			if (((TurnoutRX) fig).getName().equalsIgnoreCase(name)) {
				((TurnoutRX) fig).setColorStatus(ParamFlag.turnout_dw_black);
			}
		} else if (fig instanceof TurnoutDouble) {
			if (((TurnoutDouble) fig).getName().equalsIgnoreCase(name)) {
				((TurnoutDouble) fig).setColorStatus(ParamFlag.turnout_dw_black);
			}
		} else if (fig instanceof TurnoutDoubleR) {
			if (((TurnoutDoubleR) fig).getName().equalsIgnoreCase(name)) {
				((TurnoutDoubleR) fig).setColorStatus(ParamFlag.turnout_dw_black);
			}
		} else if (fig instanceof TurnoutDoubleL) {
			if (((TurnoutDoubleL) fig).getName().equalsIgnoreCase(name)) {
				((TurnoutDoubleL) fig).setColorStatus(ParamFlag.turnout_dw_black);
			}
		} else if (fig instanceof SemaphoreDoubleL) {
			if (((SemaphoreDoubleL) fig).getName().equalsIgnoreCase(name)) {
				((SemaphoreDoubleL) fig).setColorStatus(ParamFlag.sep_white);
			}
		} else if (fig instanceof SemaphoreDoubleR) {
			if (((SemaphoreDoubleR) fig).getName().equalsIgnoreCase(name)) {
				((SemaphoreDoubleR) fig).setColorStatus(ParamFlag.sep_white);
			}
		} else if (fig instanceof SemaphoreSimpleL) {
			if (((SemaphoreSimpleL) fig).getName().equalsIgnoreCase(name)) {
				((SemaphoreSimpleL) fig).setColorStatus(ParamFlag.sep_white);
			}
		} else if (fig instanceof SemaphoreSimpleR) {
			if (((SemaphoreSimpleR) fig).getName().equalsIgnoreCase(name)) {
				((SemaphoreSimpleR) fig).setColorStatus(ParamFlag.sep_white);
			}
		}

	}

	
	// 恢复故障
	public void HFGZ(String name) {
		
		Figure fig = getFigureObjectByName(name);
		
		if (fig == null) {
			return;
		}

		if (fig instanceof TrackLine) {
			if (((TrackLine) fig).getName().equalsIgnoreCase(name)) {
				((TrackLine) fig).setColorStatus(ParamFlag.trackline_bule);
			}
		} else if (fig instanceof TurnoutLS) {
			if (((TurnoutLS) fig).getName().equalsIgnoreCase(name)) {
				((TurnoutLS) fig).setColorStatus(ParamFlag.turnout_dw_bule);
			}
		} else if (fig instanceof TurnoutLX) {
			if (((TurnoutLX) fig).getName().equalsIgnoreCase(name)) {
				((TurnoutLX) fig).setColorStatus(ParamFlag.turnout_dw_bule);
			}
		} else if (fig instanceof TurnoutRS) {
			if (((TurnoutRS) fig).getName().equalsIgnoreCase(name)) {
				((TurnoutRS) fig).setColorStatus(ParamFlag.turnout_dw_bule);
			}
		} else if (fig instanceof TurnoutRX) {
			if (((TurnoutRX) fig).getName().equalsIgnoreCase(name)) {
				((TurnoutRX) fig).setColorStatus(ParamFlag.turnout_dw_bule);
			}
		} else if (fig instanceof TurnoutDouble) {
			if (((TurnoutDouble) fig).getName().equalsIgnoreCase(name)) {
				((TurnoutDouble) fig).setColorStatus(ParamFlag.turnout_dw_bule);
			}
		} else if (fig instanceof TurnoutDoubleR) {
			if (((TurnoutDoubleR) fig).getName().equalsIgnoreCase(name)) {
				((TurnoutDoubleR) fig).setColorStatus(ParamFlag.turnout_dw_bule);
			}
		} else if (fig instanceof TurnoutDoubleL) {
			if (((TurnoutDoubleL) fig).getName().equalsIgnoreCase(name)) {
				((TurnoutDoubleL) fig).setColorStatus(ParamFlag.turnout_dw_bule);
			}
		} else if (fig instanceof SemaphoreDoubleL) {
			if (((SemaphoreDoubleL) fig).getName().equalsIgnoreCase(name)) {
				((SemaphoreDoubleL) fig).setColorStatus(ParamFlag.sep_red);
			}
		} else if (fig instanceof SemaphoreDoubleR) {
			if (((SemaphoreDoubleR) fig).getName().equalsIgnoreCase(name)) {
				((SemaphoreDoubleR) fig).setColorStatus(ParamFlag.sep_red);
			}
		} else if (fig instanceof SemaphoreSimpleL) {
			if (((SemaphoreSimpleL) fig).getName().equalsIgnoreCase(name)) {
				((SemaphoreSimpleL) fig).setColorStatus(ParamFlag.sep_red);
			}
		} else if (fig instanceof SemaphoreSimpleR) {
			if (((SemaphoreSimpleR) fig).getName().equalsIgnoreCase(name)) {
				((SemaphoreSimpleR) fig).setColorStatus(ParamFlag.sep_red);
			}
		}

	}
	
}
