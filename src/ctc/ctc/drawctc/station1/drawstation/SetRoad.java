package ctc.ctc.drawctc.station1.drawstation;

import java.util.ArrayList;

import org.eclipse.draw2d.Figure;


import ctc.ctc.drawctc.station1.data.*;
import ctc.ctc.drawctc.station1.layout.StationModel;

public class SetRoad {

	StationModel stationModel = StationModel.getInstance();
	BaseParam baseDataForStation = BaseParam.getInstance();

	public RoadBasicInfo roadBasicInfo; // 股道段基本信息
	public ArrayList<RoadBasicInfo> roadInfoList; // 股道段基本信息list
	public RoadList road; // 路径
	public ArrayList<RoadList> roadList = baseDataForStation.getAllRoadList(); // 路径list

	public void setR_XLA_S1LA() {

		road = new RoadList();
		road.setStartName("XLA");
		road.setEndName("S1LA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R = new String[] {"XLX", "I-3", "I-2", "1/3_5/7", "9", "I-1", "I" }; // 6
		int[] R_blue = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.turnout_dw_bule_s, ParamFlag.turnout_dw_bule,
				ParamFlag.trackline_bule, ParamFlag.trackline_bule };
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green_s, ParamFlag.turnout_dw_green,
				ParamFlag.trackline_green, ParamFlag.trackline_green };
		int[] R_red = new int[] {ParamFlag.sep_red, ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.turnout_dw_red_s, ParamFlag.turnout_dw_red, ParamFlag.trackline_red,
				ParamFlag.trackline_red };

		int len = R.length;
		for (int i = 0; i < len; i++) {
			roadBasicInfo = new RoadBasicInfo();
			Figure fig = stationModel.getFigureObjectByName(R[i]);
			if (fig == null) {
				System.out.println("出现错误: 找不到名称为" + R[i] + " 的Figure!");
			} else {
				roadBasicInfo.setRoadName(fig);
				roadBasicInfo.setBlueType(R_blue[i]);
				roadBasicInfo.setGreenType(R_green[i]);
				roadBasicInfo.setRedType(R_red[i]);
				roadInfoList.add(roadBasicInfo);
			}
		}
		road.setRoadInfoList(roadInfoList);
		roadList.add(road);

		//System.out.println(baseDataForStation.getAllRoadList().size());
	}

	public void setR_XLA_S2LA() {

		road = new RoadList();
		road.setStartName("XLA");
		road.setEndName("S2LA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R_2 = new String[] {"XLX", "I-3", "I-2", "1/3_5/7", "11", "II-1", "II" }; // 6
		int[] R_blue_2 = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.turnout_fw_bule, ParamFlag.turnout_dw_bule,
				ParamFlag.trackline_bule, ParamFlag.trackline_bule };
		int[] R_green_2 = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_fw_green_l, ParamFlag.turnout_dw_green,
				ParamFlag.trackline_green, ParamFlag.trackline_green };
		int[] R_red_2 = new int[] {ParamFlag.sep_red, ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.turnout_fw_red_l, ParamFlag.turnout_dw_red, ParamFlag.trackline_red,
				ParamFlag.trackline_red };

		int len2 = R_2.length;
		for (int i = 0; i < len2; i++) {
			roadBasicInfo = new RoadBasicInfo();
			Figure fig = stationModel.getFigureObjectByName(R_2[i]);
			if (fig == null) {
				System.out.println("出现错误: 找不到名称为" + R_2[i] + " 的Figure!");
			} else {
				roadBasicInfo.setRoadName(fig);
				roadBasicInfo.setBlueType(R_blue_2[i]);
				roadBasicInfo.setGreenType(R_green_2[i]);
				roadBasicInfo.setRedType(R_red_2[i]);
				roadInfoList.add(roadBasicInfo);
			}
		}
		road.setRoadInfoList(roadInfoList);
		roadList.add(road);

		//System.out.println(baseDataForStation.getAllRoadList().size());
	}

	public void setR_XLA_S3LA() {

		road = new RoadList();
		road.setStartName("XLA");
		road.setEndName("S3LA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R_3 = new String[] {"XLX", "I-3", "I-2", "1/3_5/7", "9", "3-1", "3" }; // 6
		int[] R_blue_3 = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.turnout_dw_bule_s, ParamFlag.turnout_fw_bule,
				ParamFlag.trackline_bule, ParamFlag.trackline_bule };
		int[] R_green_3 = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green_s, ParamFlag.turnout_fw_green,
				ParamFlag.trackline_green, ParamFlag.trackline_green };
		int[] R_red_3 = new int[] {ParamFlag.sep_red, ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.turnout_dw_red_s, ParamFlag.turnout_fw_red, ParamFlag.trackline_red,
				ParamFlag.trackline_red };

		int len3 = R_3.length;
		for (int i = 0; i < len3; i++) {
			roadBasicInfo = new RoadBasicInfo();
			Figure fig = stationModel.getFigureObjectByName(R_3[i]);
			if (fig == null) {
				System.out.println("出现错误: 找不到名称为" + R_3[i] + " 的Figure!");
			} else {
				roadBasicInfo.setRoadName(fig);

				roadBasicInfo.setBlueType(R_blue_3[i]);
				roadBasicInfo.setGreenType(R_green_3[i]);
				roadBasicInfo.setRedType(R_red_3[i]);

				roadInfoList.add(roadBasicInfo);
			}
		}
		road.setRoadInfoList(roadInfoList);
		roadList.add(road);

		//System.out.println(baseDataForStation.getAllRoadList().size());
	}

	public void setR_XLA_S4LA() {

		road = new RoadList();
		road.setStartName("XLA");
		road.setEndName("S4LA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R_4 = new String[] {"XLX", "I-3", "I-2", "1/3_5/7", "11", "13", "4-1", "4" }; // 7
		int[] R_blue_4 = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.turnout_fw_bule, ParamFlag.turnout_fw_bule,
				ParamFlag.turnout_dw_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule };
		int[] R_green_4 = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_fw_green_l, ParamFlag.turnout_fw_green,
				ParamFlag.turnout_dw_green, ParamFlag.trackline_green, ParamFlag.trackline_green };
		int[] R_red_4 = new int[] {ParamFlag.sep_red, ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.turnout_fw_red_l, ParamFlag.turnout_fw_red,
				ParamFlag.turnout_dw_red, ParamFlag.trackline_red, ParamFlag.trackline_red };

		int len4 = R_4.length;
		for (int i = 0; i < len4; i++) {

			roadBasicInfo = new RoadBasicInfo();
			Figure fig = stationModel.getFigureObjectByName(R_4[i]);
			if (fig == null) {
				System.out.println("出现错误: 找不到名称为" + R_4[i] + " 的Figure!");
			} else {
				roadBasicInfo.setRoadName(fig);
				roadBasicInfo.setBlueType(R_blue_4[i]);
				roadBasicInfo.setGreenType(R_green_4[i]);
				roadBasicInfo.setRedType(R_red_4[i]);
				roadInfoList.add(roadBasicInfo);
			}
		}
		road.setRoadInfoList(roadInfoList);
		roadList.add(road);

		//System.out.println(baseDataForStation.getAllRoadList().size());
	}

	public void setR_XLA_S6LA() {

		road = new RoadList();
		road.setStartName("XLA");
		road.setEndName("S6LA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R_6 = new String[] {"XLX", "I-3", "I-2", "1/3_5/7", "11", "13", "6-1", "6" }; // 7
		int[] R_blue_6 = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.turnout_fw_bule, ParamFlag.turnout_fw_bule,
				ParamFlag.turnout_fw_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule };
		int[] R_green_6 = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_fw_green_l, ParamFlag.turnout_fw_green,
				ParamFlag.turnout_fw_green, ParamFlag.trackline_green, ParamFlag.trackline_green };
		int[] R_red_6 = new int[] {ParamFlag.sep_red, ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.turnout_fw_red_l, ParamFlag.turnout_fw_red,
				ParamFlag.turnout_fw_red, ParamFlag.trackline_red, ParamFlag.trackline_red };

		int len6 = R_6.length;
		for (int i = 0; i < len6; i++) {

			roadBasicInfo = new RoadBasicInfo();
			Figure fig = stationModel.getFigureObjectByName(R_6[i]);
			if (fig == null) {
				System.out.println("出现错误: 找不到名称为" + R_6[i] + " 的Figure!");
			} else {
				roadBasicInfo.setRoadName(fig);
				roadBasicInfo.setBlueType(R_blue_6[i]);
				roadBasicInfo.setGreenType(R_green_6[i]);
				roadBasicInfo.setRedType(R_red_6[i]);
				roadInfoList.add(roadBasicInfo);
			}
		}
		road.setRoadInfoList(roadInfoList);
		roadList.add(road);

		//System.out.println(baseDataForStation.getAllRoadList().size());
	}

	public void setR_X1LA_XLFA() {

		road = new RoadList();
		road.setStartName("X1LA");
		road.setEndName("XLFA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R_7 = new String[] {"X1", "I", "I+1", "10", "14/16", "I+2", "18/20", "22/24", "I+3", "I+4", "XD1LQ", "XD2LQ" }; // 9
		int[] R_blue_7 = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.turnout_dw_bule, ParamFlag.turnout_dw_bule_s,
				ParamFlag.trackline_bule, ParamFlag.turnout_dw_bule_s, ParamFlag.turnout_dw_bule_x, ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule };
		int[] R_green_7 = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green, ParamFlag.turnout_dw_green_s,
				ParamFlag.trackline_green, ParamFlag.turnout_dw_green_s, ParamFlag.turnout_dw_green_x, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green };
		int[] R_red_7 = new int[] {ParamFlag.sep_white, ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.turnout_dw_red, ParamFlag.turnout_dw_red_s, ParamFlag.trackline_red,
				ParamFlag.turnout_dw_red_s, ParamFlag.turnout_dw_red_x, ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.trackline_red  };

		int len7 = R_7.length;
		for (int i = 0; i < len7; i++) {

			roadBasicInfo = new RoadBasicInfo();
			Figure fig = stationModel.getFigureObjectByName(R_7[i]);
			if (fig == null) {
				System.out.println("出现错误: 找不到名称为" + R_7[i] + " 的Figure!");
			} else {
				roadBasicInfo.setRoadName(fig);
				roadBasicInfo.setBlueType(R_blue_7[i]);
				roadBasicInfo.setGreenType(R_green_7[i]);
				roadBasicInfo.setRedType(R_red_7[i]);
				roadInfoList.add(roadBasicInfo);
			}
		}
		road.setRoadInfoList(roadInfoList);
		roadList.add(road);

		//System.out.println(baseDataForStation.getAllRoadList().size());
	}

	public void setR_X2LA_XLFA() {

		road = new RoadList();
		road.setStartName("X2LA");
		road.setEndName("XLFA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R_8 = new String[] {"X2", "II", "II+1", "II+2", "14/16", "2/4_6/8", "18/20", "22/24", "I+3", "I+4", "XD1LQ", "XD2LQ"  }; // 8
		int[] R_blue_8 = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.turnout_dw_bule_x,
				ParamFlag.turnout_dw_bule_s, ParamFlag.turnout_fw_bule, ParamFlag.turnout_dw_bule_x, ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule };
		int[] R_green_8 = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green_x,
				ParamFlag.turnout_dw_green_s, ParamFlag.turnout_fw_green, ParamFlag.turnout_dw_green_x, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green };
		int[] R_red_8 = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.turnout_dw_red_x,
				ParamFlag.turnout_dw_red_s, ParamFlag.turnout_fw_red, ParamFlag.turnout_dw_red_x, ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.trackline_red  };

		int len8 = R_8.length;
		for (int i = 0; i < len8; i++) {

			roadBasicInfo = new RoadBasicInfo();
			Figure fig = stationModel.getFigureObjectByName(R_8[i]);
			if (fig == null) {
				System.out.println("出现错误: 找不到名称为" + R_8[i] + " 的Figure!");
			} else {
				roadBasicInfo.setRoadName(fig);
				roadBasicInfo.setBlueType(R_blue_8[i]);
				roadBasicInfo.setGreenType(R_green_8[i]);
				roadBasicInfo.setRedType(R_red_8[i]);
				roadInfoList.add(roadBasicInfo);
			}
		}
		road.setRoadInfoList(roadInfoList);
		roadList.add(road);

		//System.out.println(baseDataForStation.getAllRoadList().size());
	}

	public void setR_X3LA_XLFA() {

		road = new RoadList();
		road.setStartName("X3LA");
		road.setEndName("XLFA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R_9 = new String[] {"X3", "3", "3+1", "10", "14/16", "I+2", "18/20", "22/24", "I+3", "I+4", "XD1LQ", "XD2LQ"  }; // 9
		int[] R_blue_9 = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.turnout_fw_bule, ParamFlag.turnout_dw_bule_s,
				ParamFlag.trackline_bule, ParamFlag.turnout_dw_bule_s, ParamFlag.turnout_dw_bule_x, ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule };
		int[] R_green_9 = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_fw_green, ParamFlag.turnout_dw_green_s,
				ParamFlag.trackline_green, ParamFlag.turnout_dw_green_s, ParamFlag.turnout_dw_green_x, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green };
		int[] R_red_9 = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.turnout_fw_red, ParamFlag.turnout_dw_red_s, ParamFlag.trackline_red,
				ParamFlag.turnout_dw_red_s, ParamFlag.turnout_dw_red_x, ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.trackline_red  };

		int len9 = R_9.length;
		for (int i = 0; i < len9; i++) {

			roadBasicInfo = new RoadBasicInfo();
			Figure fig = stationModel.getFigureObjectByName(R_9[i]);
			if (fig == null) {
				System.out.println("出现错误: 找不到名称为" + R_9[i] + " 的Figure!");
			} else {
				roadBasicInfo.setRoadName(fig);
				roadBasicInfo.setBlueType(R_blue_9[i]);
				roadBasicInfo.setGreenType(R_green_9[i]);
				roadBasicInfo.setRedType(R_red_9[i]);
				roadInfoList.add(roadBasicInfo);
			}
		}
		road.setRoadInfoList(roadInfoList);
		roadList.add(road);

		//System.out.println(baseDataForStation.getAllRoadList().size());

	}

	public void setR_X4LA_XLFA() {

		road = new RoadList();
		road.setStartName("X4LA");
		road.setEndName("XLFA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R = new String[] {"X4", "4", "4+1", "12", "2/4_6/8", "18/20", "22/24", "I+3", "I+4", "XD1LQ", "XD2LQ"  }; // 8
		int[] R_blue = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.turnout_dw_bule, ParamFlag.turnout_fw_bule_r,
				ParamFlag.turnout_fw_bule, ParamFlag.turnout_dw_bule_x, ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule };
		int[] R_green = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green, ParamFlag.turnout_fw_green_r,
				ParamFlag.turnout_fw_green, ParamFlag.turnout_dw_green_x, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green };
		int[] R_red = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.turnout_dw_red, ParamFlag.turnout_fw_red_r,
				ParamFlag.turnout_fw_red, ParamFlag.turnout_dw_red_x, ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.trackline_red  };

		int len = R.length;
		for (int i = 0; i < len; i++) {
			roadBasicInfo = new RoadBasicInfo();
			Figure fig = stationModel.getFigureObjectByName(R[i]);
			if (fig == null) {
				System.out.println("出现错误: 找不到名称为" + R[i] + " 的Figure!");
			} else {
				roadBasicInfo.setRoadName(fig);
				roadBasicInfo.setBlueType(R_blue[i]);
				roadBasicInfo.setGreenType(R_green[i]);
				roadBasicInfo.setRedType(R_red[i]);
				roadInfoList.add(roadBasicInfo);
			}
		}
		road.setRoadInfoList(roadInfoList);
		roadList.add(road);

		//System.out.println(baseDataForStation.getAllRoadList().size());
	}

	public void setR_X6LA_XLFA() {

		road = new RoadList();
		road.setStartName("X6LA");
		road.setEndName("XLFA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R = new String[] {"X6", "6", "6+1", "12", "2/4_6/8", "18/20", "22/24", "I+3", "I+4", "XD1LQ", "XD2LQ"  }; // 8
		int[] R_blue = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.turnout_fw_bule, ParamFlag.turnout_fw_bule_r,
				ParamFlag.turnout_fw_bule, ParamFlag.turnout_dw_bule_x, ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule };
		int[] R_green = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_fw_green, ParamFlag.turnout_fw_green_r,
				ParamFlag.turnout_fw_green, ParamFlag.turnout_dw_green_x, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green };
		int[] R_red = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.turnout_fw_red, ParamFlag.turnout_fw_red_r,
				ParamFlag.turnout_fw_red, ParamFlag.turnout_dw_red_x, ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.trackline_red };

		int len = R.length;
		for (int i = 0; i < len; i++) {
			roadBasicInfo = new RoadBasicInfo();
			Figure fig = stationModel.getFigureObjectByName(R[i]);
			if (fig == null) {
				System.out.println("出现错误: 找不到名称为" + R[i] + " 的Figure!");
			} else {
				roadBasicInfo.setRoadName(fig);
				roadBasicInfo.setBlueType(R_blue[i]);
				roadBasicInfo.setGreenType(R_green[i]);
				roadBasicInfo.setRedType(R_red[i]);
				roadInfoList.add(roadBasicInfo);
			}
		}
		road.setRoadInfoList(roadInfoList);
		roadList.add(road);

		//System.out.println(baseDataForStation.getAllRoadList().size());
	}

	public void setR_XTA_XLFA() {

		road = new RoadList();
		road.setStartName("XTA");
		road.setEndName("XLFA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R = new String[] {"XLX", "X1", "I-3", "I-2", "1/3_5/7", "9", "I-1", "I", "I+1", "10", "14/16", "I+2", "18/20", "22/24", "I+3", "I+4", "XD1LQ", "XD2LQ"  }; // 9
		int[] R_blue = new int[] {ParamFlag.sep_white, ParamFlag.sep_white,  ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.turnout_dw_bule_s, ParamFlag.turnout_dw_bule,
				ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.turnout_dw_bule, ParamFlag.turnout_dw_bule_s,
				ParamFlag.trackline_bule, ParamFlag.turnout_dw_bule_s, ParamFlag.turnout_dw_bule_x, ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule };
		int[] R_green = new int[] {ParamFlag.sep_white, ParamFlag.sep_white,  ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green_s, ParamFlag.turnout_dw_green,
				ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green, ParamFlag.turnout_dw_green_s,
				ParamFlag.trackline_green, ParamFlag.turnout_dw_green_s, ParamFlag.turnout_dw_green_x, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green };
		int[] R_red = new int[] {ParamFlag.sep_white, ParamFlag.sep_white,  ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.turnout_dw_red_s, ParamFlag.turnout_dw_red, ParamFlag.trackline_red,
				ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.turnout_dw_red, ParamFlag.turnout_dw_red_s, ParamFlag.trackline_red,
				ParamFlag.turnout_dw_red_s, ParamFlag.turnout_dw_red_x, ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.trackline_red  };

		int len = R.length;
		for (int i = 0; i < len; i++) {
			roadBasicInfo = new RoadBasicInfo();
			Figure fig = stationModel.getFigureObjectByName(R[i]);
			if (fig == null) {
				System.out.println("出现错误: 找不到名称为" + R[i] + " 的Figure!");
			} else {
				roadBasicInfo.setRoadName(fig);
				roadBasicInfo.setBlueType(R_blue[i]);
				roadBasicInfo.setGreenType(R_green[i]);
				roadBasicInfo.setRedType(R_red[i]);
				roadInfoList.add(roadBasicInfo);
			}
		}
		road.setRoadInfoList(roadInfoList);
		roadList.add(road);

		//System.out.println(baseDataForStation.getAllRoadList().size());
	}

	public void setR_SLA_X1LA() {

		road = new RoadList();
		road.setStartName("SLA");
		road.setEndName("X1LA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R = new String[] {"SLX", "II+5", "II+4", "II+3", "18/20", "2/4_6/8", "14/16", "10", "I+1", "I"}; // 9
		int[] R_blue = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule, 
				ParamFlag.turnout_dw_bule_x, ParamFlag.turnout_dw_bule_s, ParamFlag.turnout_fw_bule, ParamFlag.turnout_dw_bule, 
				ParamFlag.trackline_bule, ParamFlag.trackline_bule };
		int[] R_green = new int[] {ParamFlag.sep_white, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green, 
				ParamFlag.turnout_dw_green_x, ParamFlag.turnout_dw_green_s, ParamFlag.turnout_fw_green, 
				ParamFlag.turnout_dw_green, ParamFlag.trackline_green, ParamFlag.trackline_green };
		int[] R_red = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.trackline_red, 
				ParamFlag.turnout_dw_red_x, ParamFlag.turnout_dw_red_s, ParamFlag.turnout_fw_red, 
				ParamFlag.turnout_dw_red, ParamFlag.trackline_red, ParamFlag.trackline_red };

		int len = R.length;
		for (int i = 0; i < len; i++) {
			roadBasicInfo = new RoadBasicInfo();
			Figure fig = stationModel.getFigureObjectByName(R[i]);
			if (fig == null) {
				System.out.println("出现错误: 找不到名称为" + R[i] + " 的Figure!");
			} else {
				roadBasicInfo.setRoadName(fig);
				roadBasicInfo.setBlueType(R_blue[i]);
				roadBasicInfo.setGreenType(R_green[i]);
				roadBasicInfo.setRedType(R_red[i]);
				roadInfoList.add(roadBasicInfo);
			}
		}
		road.setRoadInfoList(roadInfoList);
		roadList.add(road);

		//System.out.println(baseDataForStation.getAllRoadList().size());
	}

	public void setR_SLA_X2LA() {
		
		road = new RoadList();
		road.setStartName("SLA");
		road.setEndName("X2LA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R = new String[] {"SLX", "II+5", "II+4", "II+3", "18/20", "2/4_6/8", "14/16", "II+2", "II+1", "II"}; // 9
		int[] R_blue = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule, 
				ParamFlag.turnout_dw_bule_x, ParamFlag.turnout_dw_bule_s, ParamFlag.turnout_dw_bule_x,  
				ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule };
		int[] R_green = new int[] {ParamFlag.sep_white, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green, 
				ParamFlag.turnout_dw_green_x, ParamFlag.turnout_dw_green_s, ParamFlag.turnout_dw_green_x, 
				ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green };
		int[] R_red = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.trackline_red, 
				ParamFlag.turnout_dw_red_x, ParamFlag.turnout_dw_red_s, ParamFlag.turnout_dw_red_x, 
				ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.trackline_red };

		int len = R.length;
		for (int i = 0; i < len; i++) {
			roadBasicInfo = new RoadBasicInfo();
			Figure fig = stationModel.getFigureObjectByName(R[i]);
			if (fig == null) {
				System.out.println("出现错误: 找不到名称为" + R[i] + " 的Figure!");
			} else {
				roadBasicInfo.setRoadName(fig);
				roadBasicInfo.setBlueType(R_blue[i]);
				roadBasicInfo.setGreenType(R_green[i]);
				roadBasicInfo.setRedType(R_red[i]);
				roadInfoList.add(roadBasicInfo);
			}
		}
		road.setRoadInfoList(roadInfoList);
		roadList.add(road);

		//System.out.println(baseDataForStation.getAllRoadList().size());
	}

	public void setR_SLA_X3LA() {
		
		road = new RoadList();
		road.setStartName("SLA");
		road.setEndName("X3LA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R = new String[] {"SLX", "II+5", "II+4", "II+3", "18/20", "2/4_6/8", "14/16", "10", "3+1", "3"}; // 9
		int[] R_blue = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule, 
				ParamFlag.turnout_dw_bule_x, ParamFlag.turnout_dw_bule_s, ParamFlag.turnout_fw_bule, ParamFlag.turnout_fw_bule, 
				ParamFlag.trackline_bule, ParamFlag.trackline_bule };
		int[] R_green = new int[] {ParamFlag.sep_white, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green, 
				ParamFlag.turnout_dw_green_x, ParamFlag.turnout_dw_green_s, ParamFlag.turnout_fw_green, 
				ParamFlag.turnout_fw_green, ParamFlag.trackline_green, ParamFlag.trackline_green };
		int[] R_red = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.trackline_red, 
				ParamFlag.turnout_dw_red_x, ParamFlag.turnout_dw_red_s, ParamFlag.turnout_fw_red, 
				ParamFlag.turnout_fw_red, ParamFlag.trackline_red, ParamFlag.trackline_red };

		int len = R.length;
		for (int i = 0; i < len; i++) {
			roadBasicInfo = new RoadBasicInfo();
			Figure fig = stationModel.getFigureObjectByName(R[i]);
			if (fig == null) {
				System.out.println("出现错误: 找不到名称为" + R[i] + " 的Figure!");
			} else {
				roadBasicInfo.setRoadName(fig);
				roadBasicInfo.setBlueType(R_blue[i]);
				roadBasicInfo.setGreenType(R_green[i]);
				roadBasicInfo.setRedType(R_red[i]);
				roadInfoList.add(roadBasicInfo);
			}
		}
		road.setRoadInfoList(roadInfoList);
		roadList.add(road);

		//System.out.println(baseDataForStation.getAllRoadList().size());
	}

	public void setR_SLA_X4LA() {
		
		road = new RoadList();
		road.setStartName("SLA");
		road.setEndName("X4LA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R = new String[] {"SLX", "II+5", "II+4", "II+3", "18/20", "2/4_6/8", "12", "4+1", "4"}; // 9
		int[] R_blue = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule, 
				ParamFlag.turnout_dw_bule_x, ParamFlag.turnout_fw_bule_r, ParamFlag.turnout_dw_bule,  
				ParamFlag.trackline_bule, ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_white, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green, 
				ParamFlag.turnout_dw_green_x, ParamFlag.turnout_fw_green_r, ParamFlag.turnout_dw_green, 
				ParamFlag.trackline_green, ParamFlag.trackline_green};
		int[] R_red = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.trackline_red, 
				ParamFlag.turnout_dw_red_x, ParamFlag.turnout_fw_red_r, ParamFlag.turnout_dw_red, 
				ParamFlag.trackline_red, ParamFlag.trackline_red};

		int len = R.length;
		for (int i = 0; i < len; i++) {
			roadBasicInfo = new RoadBasicInfo();
			Figure fig = stationModel.getFigureObjectByName(R[i]);
			if (fig == null) {
				System.out.println("出现错误: 找不到名称为" + R[i] + " 的Figure!");
			} else {
				roadBasicInfo.setRoadName(fig);
				roadBasicInfo.setBlueType(R_blue[i]);
				roadBasicInfo.setGreenType(R_green[i]);
				roadBasicInfo.setRedType(R_red[i]);
				roadInfoList.add(roadBasicInfo);
			}
		}
		road.setRoadInfoList(roadInfoList);
		roadList.add(road);

		//System.out.println(baseDataForStation.getAllRoadList().size());
	}

	public void setR_SLA_X6LA() {

		road = new RoadList();
		road.setStartName("SLA");
		road.setEndName("X6LA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R = new String[] {"SLX", "II+5", "II+4", "II+3", "18/20", "2/4_6/8", "12", "6+1", "6"}; // 9
		int[] R_blue = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule, 
				ParamFlag.turnout_dw_bule_x, ParamFlag.turnout_fw_bule_r, ParamFlag.turnout_fw_bule,  
				ParamFlag.trackline_bule, ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_white, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green, 
				ParamFlag.turnout_dw_green_x, ParamFlag.turnout_fw_green_r, ParamFlag.turnout_fw_green, 
				ParamFlag.trackline_green, ParamFlag.trackline_green};
		int[] R_red = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.trackline_red, 
				ParamFlag.turnout_dw_red_x, ParamFlag.turnout_fw_red_r, ParamFlag.turnout_fw_red, 
				ParamFlag.trackline_red, ParamFlag.trackline_red};

		int len = R.length;
		for (int i = 0; i < len; i++) {
			roadBasicInfo = new RoadBasicInfo();
			Figure fig = stationModel.getFigureObjectByName(R[i]);
			if (fig == null) {
				System.out.println("出现错误: 找不到名称为" + R[i] + " 的Figure!");
			} else {
				roadBasicInfo.setRoadName(fig);
				roadBasicInfo.setBlueType(R_blue[i]);
				roadBasicInfo.setGreenType(R_green[i]);
				roadBasicInfo.setRedType(R_red[i]);
				roadInfoList.add(roadBasicInfo);
			}
		}
		road.setRoadInfoList(roadInfoList);
		roadList.add(road);

		//System.out.println(baseDataForStation.getAllRoadList().size());
		
	}

	public void setR_S1LA_SLFA() {

		road = new RoadList();
		road.setStartName("S1LA");
		road.setEndName("SLFA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R = new String[] {"S1", "I", "I-1", "9", "1/3_5/7", "II-2", "II-3", "SD1LQ", "SD2LQ"}; // 8
		int[] R_blue = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.turnout_dw_bule, 
				ParamFlag.turnout_fw_bule_r, ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule };
		int[] R_green = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green,
				ParamFlag.turnout_fw_green_r, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green };
		int[] R_red = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.turnout_dw_red,
				ParamFlag.turnout_fw_red_r, ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.trackline_red  };

		int len = R.length;
		for (int i = 0; i < len; i++) {
			roadBasicInfo = new RoadBasicInfo();
			Figure fig = stationModel.getFigureObjectByName(R[i]);
			if (fig == null) {
				System.out.println("出现错误: 找不到名称为" + R[i] + " 的Figure!");
			} else {
				roadBasicInfo.setRoadName(fig);
				roadBasicInfo.setBlueType(R_blue[i]);
				roadBasicInfo.setGreenType(R_green[i]);
				roadBasicInfo.setRedType(R_red[i]);
				roadInfoList.add(roadBasicInfo);
			}
		}
		road.setRoadInfoList(roadInfoList);
		roadList.add(road);

		//System.out.println(baseDataForStation.getAllRoadList().size());
		
	}

	public void setR_S2LA_SLFA() {
		
		road = new RoadList();
		road.setStartName("S2LA");
		road.setEndName("SLFA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R = new String[] {"S2", "II", "II-1", "11", "1/3_5/7", "II-2", "II-3", "SD1LQ", "SD2LQ" }; // 8
		int[] R_blue = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.turnout_dw_bule, 
				ParamFlag.turnout_dw_bule_x, ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule };
		int[] R_green = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green,
				ParamFlag.turnout_dw_green_x, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green };
		int[] R_red = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.turnout_dw_red,
				ParamFlag.turnout_dw_red_x, ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.trackline_red  };

		int len = R.length;
		for (int i = 0; i < len; i++) {
			roadBasicInfo = new RoadBasicInfo();
			Figure fig = stationModel.getFigureObjectByName(R[i]);
			if (fig == null) {
				System.out.println("出现错误: 找不到名称为" + R[i] + " 的Figure!");
			} else {
				roadBasicInfo.setRoadName(fig);
				roadBasicInfo.setBlueType(R_blue[i]);
				roadBasicInfo.setGreenType(R_green[i]);
				roadBasicInfo.setRedType(R_red[i]);
				roadInfoList.add(roadBasicInfo);
			}
		}
		road.setRoadInfoList(roadInfoList);
		roadList.add(road);

		//System.out.println(baseDataForStation.getAllRoadList().size());
		
	}

	public void setR_S3LA_SLFA() {
		
		road = new RoadList();
		road.setStartName("S3LA");
		road.setEndName("SLFA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R = new String[] {"S3", "3", "3-1", "9", "1/3_5/7", "II-2", "II-3", "SD1LQ", "SD2LQ" }; // 8
		int[] R_blue = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.turnout_fw_bule, 
				ParamFlag.turnout_fw_bule_r, ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule };
		int[] R_green = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_fw_green,
				ParamFlag.turnout_fw_green_r, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green };
		int[] R_red = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.turnout_fw_red,
				ParamFlag.turnout_fw_red_r, ParamFlag.trackline_red, ParamFlag.trackline_red , ParamFlag.trackline_red, ParamFlag.trackline_red };

		int len = R.length;
		for (int i = 0; i < len; i++) {
			roadBasicInfo = new RoadBasicInfo();
			Figure fig = stationModel.getFigureObjectByName(R[i]);
			if (fig == null) {
				System.out.println("出现错误: 找不到名称为" + R[i] + " 的Figure!");
			} else {
				roadBasicInfo.setRoadName(fig);
				roadBasicInfo.setBlueType(R_blue[i]);
				roadBasicInfo.setGreenType(R_green[i]);
				roadBasicInfo.setRedType(R_red[i]);
				roadInfoList.add(roadBasicInfo);
			}
		}
		road.setRoadInfoList(roadInfoList);
		roadList.add(road);

		//System.out.println(baseDataForStation.getAllRoadList().size());
		
	}

	public void setR_S4LA_SLFA() {
		
		road = new RoadList();
		road.setStartName("S4LA");
		road.setEndName("SLFA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R = new String[] {"S4", "4", "4-1", "13", "11", "1/3_5/7", "II-2", "II-3", "SD1LQ", "SD2LQ" }; // 8
		int[] R_blue = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.turnout_dw_bule, 
				ParamFlag.turnout_fw_bule, ParamFlag.turnout_dw_bule_x, ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule };
		int[] R_green = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green,
				ParamFlag.turnout_fw_green, ParamFlag.turnout_dw_green_x, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green };
		int[] R_red = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.turnout_dw_red,
				ParamFlag.turnout_fw_red, ParamFlag.turnout_dw_red_x, ParamFlag.trackline_red, ParamFlag.trackline_red , ParamFlag.trackline_red, ParamFlag.trackline_red };

		int len = R.length;
		for (int i = 0; i < len; i++) {
			roadBasicInfo = new RoadBasicInfo();
			Figure fig = stationModel.getFigureObjectByName(R[i]);
			if (fig == null) {
				System.out.println("出现错误: 找不到名称为" + R[i] + " 的Figure!");
			} else {
				roadBasicInfo.setRoadName(fig);
				roadBasicInfo.setBlueType(R_blue[i]);
				roadBasicInfo.setGreenType(R_green[i]);
				roadBasicInfo.setRedType(R_red[i]);
				roadInfoList.add(roadBasicInfo);
			}
		}
		road.setRoadInfoList(roadInfoList);
		roadList.add(road);

		//System.out.println(baseDataForStation.getAllRoadList().size());
		
	}

	public void setR_S6LA_SLFA() {
		
		road = new RoadList();
		road.setStartName("S6LA");
		road.setEndName("SLFA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R = new String[] {"S6", "6", "6-1", "13", "11", "1/3_5/7", "II-2", "II-3", "SD1LQ", "SD2LQ" }; // 8
		int[] R_blue = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.turnout_fw_bule, 
				ParamFlag.turnout_fw_bule, ParamFlag.turnout_dw_bule_x, ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule };
		int[] R_green = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_fw_green,
				ParamFlag.turnout_fw_green, ParamFlag.turnout_dw_green_x, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green };
		int[] R_red = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.turnout_fw_red,
				ParamFlag.turnout_fw_red, ParamFlag.turnout_dw_red_x, ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.trackline_red  };

		int len = R.length;
		for (int i = 0; i < len; i++) {
			roadBasicInfo = new RoadBasicInfo();
			Figure fig = stationModel.getFigureObjectByName(R[i]);
			if (fig == null) {
				System.out.println("出现错误: 找不到名称为" + R[i] + " 的Figure!");
			} else {
				roadBasicInfo.setRoadName(fig);
				roadBasicInfo.setBlueType(R_blue[i]);
				roadBasicInfo.setGreenType(R_green[i]);
				roadBasicInfo.setRedType(R_red[i]);
				roadInfoList.add(roadBasicInfo);
			}
		}
		road.setRoadInfoList(roadInfoList);
		roadList.add(road);

		//System.out.println(baseDataForStation.getAllRoadList().size());
		
	}

	public void setR_STA_SLFA() {

		road = new RoadList();
		road.setStartName("STA");
		road.setEndName("SLFA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R = new String[] {"SLX", "S2", "II+5", "II+4", "II+3", "18/20", "2/4_6/8", "14/16", "II+2", "II+1", "II", "II-1", "11", "1/3_5/7", "II-2", "II-3", "SD1LQ", "SD2LQ"}; // 9
		int[] R_blue = new int[] {ParamFlag.sep_white, ParamFlag.sep_white,  ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule, 
				ParamFlag.turnout_dw_bule_x, ParamFlag.turnout_dw_bule_s, ParamFlag.turnout_dw_bule_x,  
				ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.turnout_dw_bule, 
				ParamFlag.turnout_dw_bule_x, ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_white, ParamFlag.sep_white, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green, 
				ParamFlag.turnout_dw_green_x, ParamFlag.turnout_dw_green_s, ParamFlag.turnout_dw_green_x, 
				ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green,
				ParamFlag.turnout_dw_green_x, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green };
		int[] R_red = new int[] {ParamFlag.sep_white, ParamFlag.sep_white, ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.trackline_red, 
				ParamFlag.turnout_dw_red_x, ParamFlag.turnout_dw_red_s, ParamFlag.turnout_dw_red_x, 
				ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.turnout_dw_red,
				ParamFlag.turnout_dw_red_x, ParamFlag.trackline_red, ParamFlag.trackline_red , ParamFlag.trackline_red, ParamFlag.trackline_red };
		
		int len = R.length;
		for (int i = 0; i < len; i++) {
			roadBasicInfo = new RoadBasicInfo();
			Figure fig = stationModel.getFigureObjectByName(R[i]);
			if (fig == null) {
				System.out.println("出现错误: 找不到名称为" + R[i] + " 的Figure!");
			} else {
				roadBasicInfo.setRoadName(fig);
				roadBasicInfo.setBlueType(R_blue[i]);
				roadBasicInfo.setGreenType(R_green[i]);
				roadBasicInfo.setRedType(R_red[i]);
				roadInfoList.add(roadBasicInfo);
			}
		}
		road.setRoadInfoList(roadInfoList);
		roadList.add(road);

		//System.out.println(baseDataForStation.getAllRoadList().size());
		
	}

	public void setR_DX1LA_DX3LB() {

		road = new RoadList();
		road.setStartName("DX1LA");
		road.setEndName("DX3LB");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R = new String[] {"X1", "I", "I+1", "10", "14/16", "I+2", "18/20", "22/24", "3+5", "3+6" }; // 9
		int[] R_blue = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.turnout_dw_bule, 
				ParamFlag.turnout_dw_bule_s, ParamFlag.trackline_bule, ParamFlag.turnout_dw_bule_s, 
				ParamFlag.turnout_fw_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule };
		int[] R_green = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green, 
				ParamFlag.turnout_dw_green_s, ParamFlag.trackline_green, ParamFlag.turnout_dw_green_s, 
				ParamFlag.turnout_fw_green, ParamFlag.trackline_green, ParamFlag.trackline_green };
		int[] R_red = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.turnout_dw_red, 
				ParamFlag.turnout_dw_red_s, ParamFlag.trackline_red, ParamFlag.turnout_dw_red_s, 
				ParamFlag.turnout_fw_red, ParamFlag.trackline_red, ParamFlag.trackline_red };

		int len = R.length;
		for (int i = 0; i < len; i++) {

			roadBasicInfo = new RoadBasicInfo();
			Figure fig = stationModel.getFigureObjectByName(R[i]);
			if (fig == null) {
				System.out.println("出现错误: 找不到名称为" + R[i] + " 的Figure!");
			} else {
				roadBasicInfo.setRoadName(fig);
				roadBasicInfo.setBlueType(R_blue[i]);
				roadBasicInfo.setGreenType(R_green[i]);
				roadBasicInfo.setRedType(R_red[i]);
				roadInfoList.add(roadBasicInfo);
			}
		}
		road.setRoadInfoList(roadInfoList);
		roadList.add(road);

		//System.out.println(baseDataForStation.getAllRoadList().size());
		
	}

	public void setR_DX1LA_DX4LB() {

		road = new RoadList();
		road.setStartName("DX1LA");
		road.setEndName("DX4LB");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R = new String[] {"X1", "I", "I+1", "10", "14/16", "2/4_6/8", "4+2", "4+3", "4+4", "4+5" }; // 9
		int[] R_blue = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.turnout_dw_bule, 
				ParamFlag.turnout_fw_bule, ParamFlag.turnout_fw_bule_l, ParamFlag.trackline_bule, 
				ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule };
		int[] R_green = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green, 
				ParamFlag.turnout_fw_green, ParamFlag.turnout_fw_green_l, ParamFlag.trackline_green, 
				ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green };
		int[] R_red = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.turnout_dw_red, 
				ParamFlag.turnout_fw_red, ParamFlag.turnout_fw_red_l, ParamFlag.trackline_red, 
				ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.trackline_red };

		int len = R.length;
		for (int i = 0; i < len; i++) {

			roadBasicInfo = new RoadBasicInfo();
			Figure fig = stationModel.getFigureObjectByName(R[i]);
			if (fig == null) {
				System.out.println("出现错误: 找不到名称为" + R[i] + " 的Figure!");
			} else {
				roadBasicInfo.setRoadName(fig);
				roadBasicInfo.setBlueType(R_blue[i]);
				roadBasicInfo.setGreenType(R_green[i]);
				roadBasicInfo.setRedType(R_red[i]);
				roadInfoList.add(roadBasicInfo);
			}
		}
		road.setRoadInfoList(roadInfoList);
		roadList.add(road);

		//System.out.println(baseDataForStation.getAllRoadList().size());
		
	}

	public void setR_DX2LA_DX3LB() {

		road = new RoadList();
		road.setStartName("DX2LA");
		road.setEndName("DX3LB");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R = new String[] {"X2", "II", "II+1", "II+2", "14/16", "2/4_6/8", "18/20", "22/24", "3+5", "3+6" }; // 9
		int[] R_blue = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule, 
				ParamFlag.turnout_dw_bule_x, ParamFlag.turnout_dw_bule_s, ParamFlag.turnout_fw_bule, 
				ParamFlag.turnout_fw_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule };
		int[] R_green = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green, 
				ParamFlag.turnout_dw_green_x, ParamFlag.turnout_dw_green_s, ParamFlag.turnout_fw_green, 
				ParamFlag.turnout_fw_green, ParamFlag.trackline_green, ParamFlag.trackline_green };
		int[] R_red = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.trackline_red, 
				ParamFlag.turnout_dw_red_x, ParamFlag.turnout_dw_red_s, ParamFlag.turnout_fw_red, 
				ParamFlag.turnout_fw_red, ParamFlag.trackline_red, ParamFlag.trackline_red };

		int len = R.length;
		for (int i = 0; i < len; i++) {

			roadBasicInfo = new RoadBasicInfo();
			Figure fig = stationModel.getFigureObjectByName(R[i]);
			if (fig == null) {
				System.out.println("出现错误: 找不到名称为" + R[i] + " 的Figure!");
			} else {
				roadBasicInfo.setRoadName(fig);
				roadBasicInfo.setBlueType(R_blue[i]);
				roadBasicInfo.setGreenType(R_green[i]);
				roadBasicInfo.setRedType(R_red[i]);
				roadInfoList.add(roadBasicInfo);
			}
		}
		road.setRoadInfoList(roadInfoList);
		roadList.add(road);

		//System.out.println(baseDataForStation.getAllRoadList().size());		
		
	}

	public void setR_DX2LA_DX4LB() {

		road = new RoadList();
		road.setStartName("DX2LA");
		road.setEndName("DX4LB");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R = new String[] {"X2", "II", "II+1", "II+2", "14/16", "2/4_6/8", "4+2", "4+3", "4+4", "4+5"}; // 9
		int[] R_blue = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule, 
				ParamFlag.turnout_dw_bule_x, ParamFlag.turnout_fw_bule_l, ParamFlag.trackline_bule, 
				ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule };
		int[] R_green = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green, 
				ParamFlag.turnout_dw_green_x, ParamFlag.turnout_fw_green_l, ParamFlag.trackline_green, 
				ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green };
		int[] R_red = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.trackline_red, 
				ParamFlag.turnout_dw_red_x, ParamFlag.turnout_fw_red_l, ParamFlag.trackline_red, 
				ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.trackline_red };

		int len = R.length;
		for (int i = 0; i < len; i++) {

			roadBasicInfo = new RoadBasicInfo();
			Figure fig = stationModel.getFigureObjectByName(R[i]);
			if (fig == null) {
				System.out.println("出现错误: 找不到名称为" + R[i] + " 的Figure!");
			} else {
				roadBasicInfo.setRoadName(fig);
				roadBasicInfo.setBlueType(R_blue[i]);
				roadBasicInfo.setGreenType(R_green[i]);
				roadBasicInfo.setRedType(R_red[i]);
				roadInfoList.add(roadBasicInfo);
			}
		}
		road.setRoadInfoList(roadInfoList);
		roadList.add(road);

		//System.out.println(baseDataForStation.getAllRoadList().size());
		
	}

	public void setR_DX3LA_DX3LB() {

		road = new RoadList();
		road.setStartName("DX3LA");
		road.setEndName("DX3LB");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R = new String[] {"X3", "3", "3+1", "3+2", "3+3", "26", "3+4", "22/24", "3+5", "3+6" }; // 9
		int[] R_blue = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule, 
				ParamFlag.trackline_bule, ParamFlag.turnout_dw_bule, ParamFlag.trackline_bule, 
				ParamFlag.turnout_dw_bule_s, ParamFlag.trackline_bule, ParamFlag.trackline_bule };
		int[] R_green = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green, 
				ParamFlag.trackline_green, ParamFlag.turnout_dw_green, ParamFlag.trackline_green, 
				ParamFlag.turnout_dw_green_s, ParamFlag.trackline_green, ParamFlag.trackline_green };
		int[] R_red = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.trackline_red, 
				ParamFlag.trackline_red, ParamFlag.turnout_dw_red, ParamFlag.trackline_red, 
				ParamFlag.turnout_dw_red_s, ParamFlag.trackline_red, ParamFlag.trackline_red };

		int len = R.length;
		for (int i = 0; i < len; i++) {

			roadBasicInfo = new RoadBasicInfo();
			Figure fig = stationModel.getFigureObjectByName(R[i]);
			if (fig == null) {
				System.out.println("出现错误: 找不到名称为" + R[i] + " 的Figure!");
			} else {
				roadBasicInfo.setRoadName(fig);
				roadBasicInfo.setBlueType(R_blue[i]);
				roadBasicInfo.setGreenType(R_green[i]);
				roadBasicInfo.setRedType(R_red[i]);
				roadInfoList.add(roadBasicInfo);
			}
		}
		road.setRoadInfoList(roadInfoList);
		roadList.add(road);

		//System.out.println(baseDataForStation.getAllRoadList().size());
		
	}

	public void setR_DX3LA_DX4LB() {

		road = new RoadList();
		road.setStartName("DX3LA");
		road.setEndName("DX4LB");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R = new String[] {"X3", "3", "3+1", "10", "14/16", "2/4_6/8", "4+2", "4+3", "4+4", "4+5" }; // 9
		int[] R_blue = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.turnout_fw_bule, 
				ParamFlag.turnout_fw_bule, ParamFlag.turnout_fw_bule_l, ParamFlag.trackline_bule, 
				ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule };
		int[] R_green = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_fw_green, 
				ParamFlag.turnout_fw_green, ParamFlag.turnout_fw_green_l, ParamFlag.trackline_green, 
				ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green };
		int[] R_red = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.turnout_fw_red, 
				ParamFlag.turnout_fw_red, ParamFlag.turnout_fw_red_l, ParamFlag.trackline_red, 
				ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.trackline_red };

		int len = R.length;
		for (int i = 0; i < len; i++) {

			roadBasicInfo = new RoadBasicInfo();
			Figure fig = stationModel.getFigureObjectByName(R[i]);
			if (fig == null) {
				System.out.println("出现错误: 找不到名称为" + R[i] + " 的Figure!");
			} else {
				roadBasicInfo.setRoadName(fig);
				roadBasicInfo.setBlueType(R_blue[i]);
				roadBasicInfo.setGreenType(R_green[i]);
				roadBasicInfo.setRedType(R_red[i]);
				roadInfoList.add(roadBasicInfo);
			}
		}
		road.setRoadInfoList(roadInfoList);
		roadList.add(road);

		//System.out.println(baseDataForStation.getAllRoadList().size());
		
	}

	public void setR_DX4LA_DX3LB() {

		road = new RoadList();
		road.setStartName("DX4LA");
		road.setEndName("DX3LB");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R = new String[] {"X4", "4", "4+1", "12", "2/4_6/8", "18/20", "22/24", "3+5", "3+6" }; // 9
		int[] R_blue = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.turnout_dw_bule, 
				ParamFlag.turnout_fw_bule_r, ParamFlag.turnout_fw_bule, ParamFlag.turnout_fw_bule, 
				ParamFlag.trackline_bule, ParamFlag.trackline_bule };
		int[] R_green = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green, 
				ParamFlag.turnout_fw_green_r, ParamFlag.turnout_fw_green, ParamFlag.turnout_fw_green, 
				ParamFlag.trackline_green, ParamFlag.trackline_green };
		int[] R_red = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.turnout_dw_red, 
				ParamFlag.turnout_fw_red_r, ParamFlag.turnout_fw_red, ParamFlag.turnout_fw_red, 
				ParamFlag.trackline_red, ParamFlag.trackline_red };

		int len = R.length;
		for (int i = 0; i < len; i++) {

			roadBasicInfo = new RoadBasicInfo();
			Figure fig = stationModel.getFigureObjectByName(R[i]);
			if (fig == null) {
				System.out.println("出现错误: 找不到名称为" + R[i] + " 的Figure!");
			} else {
				roadBasicInfo.setRoadName(fig);
				roadBasicInfo.setBlueType(R_blue[i]);
				roadBasicInfo.setGreenType(R_green[i]);
				roadBasicInfo.setRedType(R_red[i]);
				roadInfoList.add(roadBasicInfo);
			}
		}
		road.setRoadInfoList(roadInfoList);
		roadList.add(road);

		//System.out.println(baseDataForStation.getAllRoadList().size());
		
	}

	public void setR_DX4LA_DX4LB() {

		road = new RoadList();
		road.setStartName("DX4LA");
		road.setEndName("DX4LB");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R = new String[] {"X4", "4", "4+1", "12", "2/4_6/8", "4+2", "4+3", "4+4", "4+5" }; // 9
		int[] R_blue = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.turnout_dw_bule, 
				ParamFlag.turnout_dw_bule_x, ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule };
		int[] R_green = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green, 
				ParamFlag.turnout_dw_green_x, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green };
		int[] R_red = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.turnout_dw_red, 
				ParamFlag.turnout_dw_red_x, ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.trackline_red };

		int len = R.length;
		for (int i = 0; i < len; i++) {

			roadBasicInfo = new RoadBasicInfo();
			Figure fig = stationModel.getFigureObjectByName(R[i]);
			if (fig == null) {
				System.out.println("出现错误: 找不到名称为" + R[i] + " 的Figure!");
			} else {
				roadBasicInfo.setRoadName(fig);
				roadBasicInfo.setBlueType(R_blue[i]);
				roadBasicInfo.setGreenType(R_green[i]);
				roadBasicInfo.setRedType(R_red[i]);
				roadInfoList.add(roadBasicInfo);
			}
		}
		road.setRoadInfoList(roadInfoList);
		roadList.add(road);

		//System.out.println(baseDataForStation.getAllRoadList().size());
		
	}

	public void setR_DX5LA_DX3LB() {

		road = new RoadList();
		road.setStartName("DX5LA");
		road.setEndName("DX3LB");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R = new String[] {"X5", "5", "5+1", "5+2", "5+3", "26", "3+4", "22/24", "3+5", "3+6" }; // 9
		int[] R_blue = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule, 
				ParamFlag.trackline_bule, ParamFlag.turnout_fw_bule, ParamFlag.trackline_bule, 
				ParamFlag.turnout_dw_bule_s, ParamFlag.trackline_bule, ParamFlag.trackline_bule };
		int[] R_green = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green, 
				ParamFlag.trackline_green, ParamFlag.turnout_fw_green, ParamFlag.trackline_green, 
				ParamFlag.turnout_dw_green_s, ParamFlag.trackline_green, ParamFlag.trackline_green };
		int[] R_red = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.trackline_red, 
				ParamFlag.trackline_red, ParamFlag.turnout_fw_red, ParamFlag.trackline_red, 
				ParamFlag.turnout_dw_red_s, ParamFlag.trackline_red, ParamFlag.trackline_red };

		int len = R.length;
		for (int i = 0; i < len; i++) {

			roadBasicInfo = new RoadBasicInfo();
			Figure fig = stationModel.getFigureObjectByName(R[i]);
			if (fig == null) {
				System.out.println("出现错误: 找不到名称为" + R[i] + " 的Figure!");
			} else {
				roadBasicInfo.setRoadName(fig);
				roadBasicInfo.setBlueType(R_blue[i]);
				roadBasicInfo.setGreenType(R_green[i]);
				roadBasicInfo.setRedType(R_red[i]);
				roadInfoList.add(roadBasicInfo);
			}
		}
		road.setRoadInfoList(roadInfoList);
		roadList.add(road);

		//System.out.println(baseDataForStation.getAllRoadList().size());
		
	}

	public void setR_DX6LA_DX3LB() {

		road = new RoadList();
		road.setStartName("DX6LA");
		road.setEndName("DX3LB");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R = new String[] {"X6", "6", "6+1", "12", "2/4_6/8", "18/20", "22/24", "3+5", "3+6" }; // 9
		int[] R_blue = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.turnout_fw_bule, 
				ParamFlag.turnout_fw_bule_r, ParamFlag.turnout_fw_bule, ParamFlag.turnout_fw_bule, 
				ParamFlag.trackline_bule, ParamFlag.trackline_bule };
		int[] R_green = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_fw_green, 
				ParamFlag.turnout_fw_green_r, ParamFlag.turnout_fw_green, ParamFlag.turnout_fw_green, 
				ParamFlag.trackline_green, ParamFlag.trackline_green };
		int[] R_red = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.turnout_fw_red, 
				ParamFlag.turnout_fw_red_r, ParamFlag.turnout_fw_red, ParamFlag.turnout_fw_red, 
				ParamFlag.trackline_red, ParamFlag.trackline_red };

		int len = R.length;
		for (int i = 0; i < len; i++) {

			roadBasicInfo = new RoadBasicInfo();
			Figure fig = stationModel.getFigureObjectByName(R[i]);
			if (fig == null) {
				System.out.println("出现错误: 找不到名称为" + R[i] + " 的Figure!");
			} else {
				roadBasicInfo.setRoadName(fig);
				roadBasicInfo.setBlueType(R_blue[i]);
				roadBasicInfo.setGreenType(R_green[i]);
				roadBasicInfo.setRedType(R_red[i]);
				roadInfoList.add(roadBasicInfo);
			}
		}
		road.setRoadInfoList(roadInfoList);
		roadList.add(road);

		//System.out.println(baseDataForStation.getAllRoadList().size());
		
	}

	public void setR_DX6LA_DX4LB() {

		road = new RoadList();
		road.setStartName("DX6LA");
		road.setEndName("DX4LB");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R = new String[] {"X6", "6", "6+1", "12", "2/4_6/8", "4+2", "4+3", "4+4", "4+5" }; // 9
		int[] R_blue = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.turnout_fw_bule, 
				ParamFlag.turnout_dw_bule_x, ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule };
		int[] R_green = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_fw_green, 
				ParamFlag.turnout_dw_green_x, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green };
		int[] R_red = new int[] {ParamFlag.sep_white,  ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.turnout_fw_red, 
				ParamFlag.turnout_dw_red_x, ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.trackline_red };

		int len = R.length;
		for (int i = 0; i < len; i++) {

			roadBasicInfo = new RoadBasicInfo();
			Figure fig = stationModel.getFigureObjectByName(R[i]);
			if (fig == null) {
				System.out.println("出现错误: 找不到名称为" + R[i] + " 的Figure!");
			} else {
				roadBasicInfo.setRoadName(fig);
				roadBasicInfo.setBlueType(R_blue[i]);
				roadBasicInfo.setGreenType(R_green[i]);
				roadBasicInfo.setRedType(R_red[i]);
				roadInfoList.add(roadBasicInfo);
			}
		}
		road.setRoadInfoList(roadInfoList);
		roadList.add(road);

		//System.out.println(baseDataForStation.getAllRoadList().size());
		
	}

	public void setR_DX3LB_DX1LA() {

		road = new RoadList();
		road.setStartName("DX3LB");
		road.setEndName("DX1LA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R = new String[] { "I", "I+1", "10", "14/16", "I+2", "18/20", "22/24", "3+5", "3+6", "D3"}; // 9
		int[] R_blue = new int[] { ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.turnout_dw_bule, 
				ParamFlag.turnout_dw_bule_s, ParamFlag.trackline_bule, ParamFlag.turnout_dw_bule_s, 
				ParamFlag.turnout_fw_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.sep_white };
		int[] R_green = new int[] { ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green, 
				ParamFlag.turnout_dw_green_s, ParamFlag.trackline_green, ParamFlag.turnout_dw_green_s, 
				ParamFlag.turnout_fw_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.sep_white };
		int[] R_red = new int[] { ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.turnout_dw_red, 
				ParamFlag.turnout_dw_red_s, ParamFlag.trackline_red, ParamFlag.turnout_dw_red_s, 
				ParamFlag.turnout_fw_red, ParamFlag.trackline_red, ParamFlag.trackline_red , ParamFlag.sep_white};

		int len = R.length;
		for (int i = len - 1; i >= 0; i--) {

			roadBasicInfo = new RoadBasicInfo();
			Figure fig = stationModel.getFigureObjectByName(R[i]);
			if (fig == null) {
				System.out.println("出现错误: 找不到名称为" + R[i] + " 的Figure!");
			} else {
				roadBasicInfo.setRoadName(fig);
				roadBasicInfo.setBlueType(R_blue[i]);
				roadBasicInfo.setGreenType(R_green[i]);
				roadBasicInfo.setRedType(R_red[i]);
				roadInfoList.add(roadBasicInfo);
			}
		}
		road.setRoadInfoList(roadInfoList);
		roadList.add(road);

		//System.out.println(baseDataForStation.getAllRoadList().size());
		
	}

	public void setR_DX3LB_DX2LA() {
		
		road = new RoadList();
		road.setStartName("DX3LB");
		road.setEndName("DX2LA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R = new String[] { "II", "II+1", "II+2", "14/16", "2/4_6/8", "18/20", "22/24", "3+5", "3+6" , "D3"}; // 9
		int[] R_blue = new int[] { ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule, 
				ParamFlag.turnout_dw_bule_x, ParamFlag.turnout_dw_bule_s, ParamFlag.turnout_fw_bule, 
				ParamFlag.turnout_fw_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.sep_white };
		int[] R_green = new int[] { ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green, 
				ParamFlag.turnout_dw_green_x, ParamFlag.turnout_dw_green_s, ParamFlag.turnout_fw_green, 
				ParamFlag.turnout_fw_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.sep_white };
		int[] R_red = new int[] { ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.trackline_red, 
				ParamFlag.turnout_dw_red_x, ParamFlag.turnout_dw_red_s, ParamFlag.turnout_fw_red, 
				ParamFlag.turnout_fw_red, ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.sep_white };

		int len = R.length;
		for (int i = len-1; i >=0; i--) {

			roadBasicInfo = new RoadBasicInfo();
			Figure fig = stationModel.getFigureObjectByName(R[i]);
			if (fig == null) {
				System.out.println("出现错误: 找不到名称为" + R[i] + " 的Figure!");
			} else {
				roadBasicInfo.setRoadName(fig);
				roadBasicInfo.setBlueType(R_blue[i]);
				roadBasicInfo.setGreenType(R_green[i]);
				roadBasicInfo.setRedType(R_red[i]);
				roadInfoList.add(roadBasicInfo);
			}
		}
		road.setRoadInfoList(roadInfoList);
		roadList.add(road);

		//System.out.println(baseDataForStation.getAllRoadList().size());		
		
	}

	public void setR_DX3LB_DX3LA() {

		road = new RoadList();
		road.setStartName("DX3LB");
		road.setEndName("DX3LA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R = new String[] { "3", "3+1", "3+2", "3+3", "26", "3+4", "22/24", "3+5", "3+6" , "D3"}; // 9
		int[] R_blue = new int[] { ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule, 
				ParamFlag.trackline_bule, ParamFlag.turnout_dw_bule, ParamFlag.trackline_bule, 
				ParamFlag.turnout_dw_bule_s, ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.sep_white };
		int[] R_green = new int[] { ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green, 
				ParamFlag.trackline_green, ParamFlag.turnout_dw_green, ParamFlag.trackline_green, 
				ParamFlag.turnout_dw_green_s, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.sep_white };
		int[] R_red = new int[] { ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.trackline_red, 
				ParamFlag.trackline_red, ParamFlag.turnout_dw_red, ParamFlag.trackline_red, 
				ParamFlag.turnout_dw_red_s, ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.sep_white };

		int len = R.length;
		for (int i = len-1; i >= 0; i--) {

			roadBasicInfo = new RoadBasicInfo();
			Figure fig = stationModel.getFigureObjectByName(R[i]);
			if (fig == null) {
				System.out.println("出现错误: 找不到名称为" + R[i] + " 的Figure!");
			} else {
				roadBasicInfo.setRoadName(fig);
				roadBasicInfo.setBlueType(R_blue[i]);
				roadBasicInfo.setGreenType(R_green[i]);
				roadBasicInfo.setRedType(R_red[i]);
				roadInfoList.add(roadBasicInfo);
			}
		}
		road.setRoadInfoList(roadInfoList);
		roadList.add(road);

		//System.out.println(baseDataForStation.getAllRoadList().size());
		
	}

	public void setR_DX3LB_DX4LA() {

		road = new RoadList();
		road.setStartName("DX3LB");
		road.setEndName("DX4LA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R = new String[] { "4", "4+1", "12", "2/4_6/8", "18/20", "22/24", "3+5", "3+6" , "D3"}; // 9
		int[] R_blue = new int[] { ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.turnout_dw_bule, 
				ParamFlag.turnout_fw_bule_r, ParamFlag.turnout_fw_bule, ParamFlag.turnout_fw_bule, 
				ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.sep_white };
		int[] R_green = new int[] { ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green, 
				ParamFlag.turnout_fw_green_r, ParamFlag.turnout_fw_green, ParamFlag.turnout_fw_green, 
				ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.sep_white };
		int[] R_red = new int[] { ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.turnout_dw_red, 
				ParamFlag.turnout_fw_red_r, ParamFlag.turnout_fw_red, ParamFlag.turnout_fw_red, 
				ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.sep_white };

		int len = R.length;
		for (int i = len-1; i >=0; i--) {

			roadBasicInfo = new RoadBasicInfo();
			Figure fig = stationModel.getFigureObjectByName(R[i]);
			if (fig == null) {
				System.out.println("出现错误: 找不到名称为" + R[i] + " 的Figure!");
			} else {
				roadBasicInfo.setRoadName(fig);
				roadBasicInfo.setBlueType(R_blue[i]);
				roadBasicInfo.setGreenType(R_green[i]);
				roadBasicInfo.setRedType(R_red[i]);
				roadInfoList.add(roadBasicInfo);
			}
		}
		road.setRoadInfoList(roadInfoList);
		roadList.add(road);

		//System.out.println(baseDataForStation.getAllRoadList().size());
		
	}

	public void setR_DX3LB_DX5LA() {

		road = new RoadList();
		road.setStartName("DX3LB");
		road.setEndName("DX5LA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R = new String[] { "5", "5+1", "5+2", "5+3", "26", "3+4", "22/24", "3+5", "3+6" , "D3"}; // 9
		int[] R_blue = new int[] { ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule, 
				ParamFlag.trackline_bule, ParamFlag.turnout_fw_bule, ParamFlag.trackline_bule, 
				ParamFlag.turnout_dw_bule_s, ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.sep_white };
		int[] R_green = new int[] { ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green, 
				ParamFlag.trackline_green, ParamFlag.turnout_fw_green, ParamFlag.trackline_green, 
				ParamFlag.turnout_dw_green_s, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.sep_white };
		int[] R_red = new int[] { ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.trackline_red, 
				ParamFlag.trackline_red, ParamFlag.turnout_fw_red, ParamFlag.trackline_red, 
				ParamFlag.turnout_dw_red_s, ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.sep_white };

		int len = R.length;
		for (int i = len-1; i >=0; i--) {

			roadBasicInfo = new RoadBasicInfo();
			Figure fig = stationModel.getFigureObjectByName(R[i]);
			if (fig == null) {
				System.out.println("出现错误: 找不到名称为" + R[i] + " 的Figure!");
			} else {
				roadBasicInfo.setRoadName(fig);
				roadBasicInfo.setBlueType(R_blue[i]);
				roadBasicInfo.setGreenType(R_green[i]);
				roadBasicInfo.setRedType(R_red[i]);
				roadInfoList.add(roadBasicInfo);
			}
		}
		road.setRoadInfoList(roadInfoList);
		roadList.add(road);

		//System.out.println(baseDataForStation.getAllRoadList().size());
		
	}

	public void setR_DX3LB_DX6LA() {

		road = new RoadList();
		road.setStartName("DX3LB");
		road.setEndName("DX6LA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R = new String[] { "6", "6+1", "12", "2/4_6/8", "18/20", "22/24", "3+5", "3+6" , "D3"}; // 9
		int[] R_blue = new int[] { ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.turnout_fw_bule, 
				ParamFlag.turnout_fw_bule_r, ParamFlag.turnout_fw_bule, ParamFlag.turnout_fw_bule, 
				ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.sep_white };
		int[] R_green = new int[] { ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_fw_green, 
				ParamFlag.turnout_fw_green_r, ParamFlag.turnout_fw_green, ParamFlag.turnout_fw_green, 
				ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.sep_white };
		int[] R_red = new int[] { ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.turnout_fw_red, 
				ParamFlag.turnout_fw_red_r, ParamFlag.turnout_fw_red, ParamFlag.turnout_fw_red, 
				ParamFlag.trackline_red, ParamFlag.trackline_red , ParamFlag.sep_white};

		int len = R.length;
		for (int i = len-1; i >=0; i--) {

			roadBasicInfo = new RoadBasicInfo();
			Figure fig = stationModel.getFigureObjectByName(R[i]);
			if (fig == null) {
				System.out.println("出现错误: 找不到名称为" + R[i] + " 的Figure!");
			} else {
				roadBasicInfo.setRoadName(fig);
				roadBasicInfo.setBlueType(R_blue[i]);
				roadBasicInfo.setGreenType(R_green[i]);
				roadBasicInfo.setRedType(R_red[i]);
				roadInfoList.add(roadBasicInfo);
			}
		}
		road.setRoadInfoList(roadInfoList);
		roadList.add(road);

		//System.out.println(baseDataForStation.getAllRoadList().size());
		
	}

	public void setR_DX4LB_DX1LA() {

		road = new RoadList();
		road.setStartName("DX4LB");
		road.setEndName("DX1LA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R = new String[] { "I", "I+1", "10", "14/16", "2/4_6/8", "4+2", "4+3", "4+4", "4+5" , "D4"}; // 9
		int[] R_blue = new int[] { ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.turnout_dw_bule, 
				ParamFlag.turnout_fw_bule, ParamFlag.turnout_fw_bule_l, ParamFlag.trackline_bule, 
				ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.sep_white };
		int[] R_green = new int[] { ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green, 
				ParamFlag.turnout_fw_green, ParamFlag.turnout_fw_green_l, ParamFlag.trackline_green, 
				ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.sep_white };
		int[] R_red = new int[] { ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.turnout_dw_red, 
				ParamFlag.turnout_fw_red, ParamFlag.turnout_fw_red_l, ParamFlag.trackline_red, 
				ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.sep_white };

		int len = R.length;
		for (int i = len-1; i >=0; i--) {

			roadBasicInfo = new RoadBasicInfo();
			Figure fig = stationModel.getFigureObjectByName(R[i]);
			if (fig == null) {
				System.out.println("出现错误: 找不到名称为" + R[i] + " 的Figure!");
			} else {
				roadBasicInfo.setRoadName(fig);
				roadBasicInfo.setBlueType(R_blue[i]);
				roadBasicInfo.setGreenType(R_green[i]);
				roadBasicInfo.setRedType(R_red[i]);
				roadInfoList.add(roadBasicInfo);
			}
		}
		road.setRoadInfoList(roadInfoList);
		roadList.add(road);

		//System.out.println(baseDataForStation.getAllRoadList().size());
		
	}

	public void setR_DX4LB_DX2LA() {

		road = new RoadList();
		road.setStartName("DX4LB");
		road.setEndName("DX2LA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R = new String[] { "II", "II+1", "II+2", "14/16", "2/4_6/8", "4+2", "4+3", "4+4", "4+5", "D4"}; // 9
		int[] R_blue = new int[] { ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule, 
				ParamFlag.turnout_dw_bule_x, ParamFlag.turnout_fw_bule_l, ParamFlag.trackline_bule, 
				ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.sep_white };
		int[] R_green = new int[] { ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green, 
				ParamFlag.turnout_dw_green_x, ParamFlag.turnout_fw_green_l, ParamFlag.trackline_green, 
				ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.sep_white };
		int[] R_red = new int[] { ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.trackline_red, 
				ParamFlag.turnout_dw_red_x, ParamFlag.turnout_fw_red_l, ParamFlag.trackline_red, 
				ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.sep_white };

		int len = R.length;
		for (int i = len-1; i >=0; i--) {

			roadBasicInfo = new RoadBasicInfo();
			Figure fig = stationModel.getFigureObjectByName(R[i]);
			if (fig == null) {
				System.out.println("出现错误: 找不到名称为" + R[i] + " 的Figure!");
			} else {
				roadBasicInfo.setRoadName(fig);
				roadBasicInfo.setBlueType(R_blue[i]);
				roadBasicInfo.setGreenType(R_green[i]);
				roadBasicInfo.setRedType(R_red[i]);
				roadInfoList.add(roadBasicInfo);
			}
		}
		road.setRoadInfoList(roadInfoList);
		roadList.add(road);

		//System.out.println(baseDataForStation.getAllRoadList().size());
		
	}

	public void setR_DX4LB_DX3LA() {

		road = new RoadList();
		road.setStartName("DX4LB");
		road.setEndName("DX3LA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R = new String[] { "3", "3+1", "10", "14/16", "2/4_6/8", "4+2", "4+3", "4+4", "4+5" , "D4"}; // 9
		int[] R_blue = new int[] { ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.turnout_fw_bule, 
				ParamFlag.turnout_fw_bule, ParamFlag.turnout_fw_bule_l, ParamFlag.trackline_bule, 
				ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.sep_white };
		int[] R_green = new int[] { ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_fw_green, 
				ParamFlag.turnout_fw_green, ParamFlag.turnout_fw_green_l, ParamFlag.trackline_green, 
				ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.sep_white };
		int[] R_red = new int[] { ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.turnout_fw_red, 
				ParamFlag.turnout_fw_red, ParamFlag.turnout_fw_red_l, ParamFlag.trackline_red, 
				ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.sep_white };

		int len = R.length;
		for (int i = len-1; i >=0; i--) {

			roadBasicInfo = new RoadBasicInfo();
			Figure fig = stationModel.getFigureObjectByName(R[i]);
			if (fig == null) {
				System.out.println("出现错误: 找不到名称为" + R[i] + " 的Figure!");
			} else {
				roadBasicInfo.setRoadName(fig);
				roadBasicInfo.setBlueType(R_blue[i]);
				roadBasicInfo.setGreenType(R_green[i]);
				roadBasicInfo.setRedType(R_red[i]);
				roadInfoList.add(roadBasicInfo);
			}
		}
		road.setRoadInfoList(roadInfoList);
		roadList.add(road);

		//System.out.println(baseDataForStation.getAllRoadList().size());
		
	}

	public void setR_DX4LB_DX4LA() {

		road = new RoadList();
		road.setStartName("DX4LB");
		road.setEndName("DX4LA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R = new String[] { "4", "4+1", "12", "2/4_6/8", "4+2", "4+3", "4+4", "4+5" , "D4"}; // 9
		int[] R_blue = new int[] { ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.turnout_dw_bule, 
				ParamFlag.turnout_dw_bule_x, ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.sep_white };
		int[] R_green = new int[] { ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green, 
				ParamFlag.turnout_dw_green_x, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.sep_white };
		int[] R_red = new int[] { ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.turnout_dw_red, 
				ParamFlag.turnout_dw_red_x, ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.sep_white };

		int len = R.length;
		for (int i = len-1; i >=0; i--) {

			roadBasicInfo = new RoadBasicInfo();
			Figure fig = stationModel.getFigureObjectByName(R[i]);
			if (fig == null) {
				System.out.println("出现错误: 找不到名称为" + R[i] + " 的Figure!");
			} else {
				roadBasicInfo.setRoadName(fig);
				roadBasicInfo.setBlueType(R_blue[i]);
				roadBasicInfo.setGreenType(R_green[i]);
				roadBasicInfo.setRedType(R_red[i]);
				roadInfoList.add(roadBasicInfo);
			}
		}
		road.setRoadInfoList(roadInfoList);
		roadList.add(road);

		//System.out.println(baseDataForStation.getAllRoadList().size());
		
	}

	public void setR_DX4LB_DX6LA() {

		road = new RoadList();
		road.setStartName("DX4LB");
		road.setEndName("DX6LA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R = new String[] { "6", "6+1", "12", "2/4_6/8", "4+2", "4+3", "4+4", "4+5" , "D4"}; // 9
		int[] R_blue = new int[] { ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.turnout_fw_bule, 
				ParamFlag.turnout_dw_bule_x, ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.trackline_bule, ParamFlag.sep_white };
		int[] R_green = new int[] { ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_fw_green, 
				ParamFlag.turnout_dw_green_x, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.sep_white };
		int[] R_red = new int[] { ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.turnout_fw_red, 
				ParamFlag.turnout_dw_red_x, ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.trackline_red, ParamFlag.sep_white};

		int len = R.length;
		for (int i = len-1; i >=0; i--) {

			roadBasicInfo = new RoadBasicInfo();
			Figure fig = stationModel.getFigureObjectByName(R[i]);
			if (fig == null) {
				System.out.println("出现错误: 找不到名称为" + R[i] + " 的Figure!");
			} else {
				roadBasicInfo.setRoadName(fig);
				roadBasicInfo.setBlueType(R_blue[i]);
				roadBasicInfo.setGreenType(R_green[i]);
				roadBasicInfo.setRedType(R_red[i]);
				roadInfoList.add(roadBasicInfo);
			}
		}
		road.setRoadInfoList(roadInfoList);
		roadList.add(road);

		//System.out.println(baseDataForStation.getAllRoadList().size());
		
	}

}
