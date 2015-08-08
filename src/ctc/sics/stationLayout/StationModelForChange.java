package ctc.sics.stationLayout;

import java.util.ArrayList;

import ctc.sics.SicsMain;

import ctc.sics.road.*;
import ctc.sics.sics2ctc.SICSToCTC;

/**
 * 车站连锁业务
 * 
 * @author 胡恩召
 * 
 */
public class StationModelForChange {

	public static String stationName = SicsMain.db.getStationName();

	/**
	 * 判断道路是否可以通行
	 * 
	 * @param roadList
	 *            道路list
	 * @return
	 */
	public static boolean JudgeRoad(ArrayList<RoadBasicInfo> roadList, int type) {

		boolean can = false;
		int len = roadList.size();

		if (type == 1) { // 进站判断
			for (int i = 0; i < len; i++) {

				RoadBasicInfo rBasicInfo = roadList.get(i);
				String roadType = rBasicInfo.getRoadType();
				String roadName = rBasicInfo.getRoadName();

				// 当类型为TrackLine时
				if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTrackLine)) {

					if ((DrawStation.trackLineList != null) && (DrawStation.trackLineList.size() != 0)) {

						int length = DrawStation.trackLineList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.trackLineList.get(j).getLineName())) {
								if (!(DrawStation.trackLineList.get(j).getStatus() == 0)) { // 如果不为初始状态
									can = false;
									return can;
								}
							}
						}
					}
				}

				// 当类型为TurnoutDouble时
				if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutDouble)) {

					if ((DrawStation.turnoutDoubleList != null) && (DrawStation.turnoutDoubleList.size() != 0)) {
						int length = DrawStation.turnoutDoubleList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutDoubleList.get(j).getTurnoutName())) {
								if (!(DrawStation.turnoutDoubleList.get(j).getStatus() == 0)) { // 如果不为初始状态
									can = false;
									return can;
								}
							}
						}
					}
				}

				// 当类型为TurnoutLS时
				if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLS)) {

					int length = DrawStation.turnoutLSList.size();
					for (int j = 0; j < length; j++) {
						if (roadName.equalsIgnoreCase(DrawStation.turnoutLSList.get(j).getTurnoutName())) {
							if (!(DrawStation.turnoutLSList.get(j).getStatus() == 0)) { // 如果不为初始状态
								can = false;
								return can;
							}
						}
					}
				}
				// 当类型为TurnoutLX时
				if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLX)) {

					int length = DrawStation.turnoutLXList.size();
					for (int j = 0; j < length; j++) {
						if (roadName.equalsIgnoreCase(DrawStation.turnoutLXList.get(j).getTurnoutName())) {
							if (!(DrawStation.turnoutLXList.get(j).getStatus() == 0)) { // 如果不为初始状态
								can = false;
								return can;
							}
						}
					}
				}
				// 当类型为TurnoutRS时
				if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRS)) {

					int length = DrawStation.turnoutRSList.size();
					for (int j = 0; j < length; j++) {
						if (roadName.equalsIgnoreCase(DrawStation.turnoutRSList.get(j).getTurnoutName())) {
							if (!(DrawStation.turnoutRSList.get(j).getStatus() == 0)) { // 如果不为初始状态
								can = false;
								return can;
							}
						}
					}
				}
				// 当类型为TurnoutRX时
				if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRX)) {

					int length = DrawStation.turnoutRXList.size();
					for (int j = 0; j < length; j++) {
						if (roadName.equalsIgnoreCase(DrawStation.turnoutRXList.get(j).getTurnoutName())) {
							if (!(DrawStation.turnoutRXList.get(j).getStatus() == 0)) { // 如果不为初始状态
								can = false;
								return can;
							}
						}
					}
				}
			}
		} else { // 出站判断

			for (int i = 4; i < len; i++) {

				RoadBasicInfo rBasicInfo = roadList.get(i);
				String roadType = rBasicInfo.getRoadType();
				String roadName = rBasicInfo.getRoadName();

				// 当类型为TrackLine时
				if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTrackLine)) {
					int length = DrawStation.trackLineList.size();
					for (int j = 0; j < length; j++) {
						if (roadName.equalsIgnoreCase(DrawStation.trackLineList.get(j).getLineName())) {
							if (!(DrawStation.trackLineList.get(j).getStatus() == 0)) { // 如果不为初始状态
								can = false;
								return can;
							}
						}
					}
				}
				// 当类型为TurnoutDouble时
				if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutDouble)) {
					int length = DrawStation.turnoutDoubleList.size();
					// System.out.println(length);

					for (int j = 0; j < length; j++) {
						if (roadName.equalsIgnoreCase(DrawStation.turnoutDoubleList.get(j).getTurnoutName())) {
							if (!(DrawStation.turnoutDoubleList.get(j).getStatus() == 0)) { // 如果不为初始状态
								can = false;
								return can;
							}
						}
					}
				}
				// 当类型为TurnoutDoubleR时
				if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutDouble)) {

					int length = DrawStation.turnoutDoubleList.size();
					for (int j = 0; j < length; j++) {
						if (roadName.equalsIgnoreCase(DrawStation.turnoutDoubleList.get(j).getTurnoutName())) {
							if (!(DrawStation.turnoutDoubleList.get(j).getStatus() == 0)) { // 如果不为初始状态
								can = false;
								return can;
							}
						}
					}

				}

				// 当类型为TurnoutLS时
				if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLS)) {

					int length = DrawStation.turnoutLSList.size();
					for (int j = 0; j < length; j++) {
						if (roadName.equalsIgnoreCase(DrawStation.turnoutLSList.get(j).getTurnoutName())) {
							if (!(DrawStation.turnoutLSList.get(j).getStatus() == 0)) { // 如果不为初始状态
								can = false;
								return can;
							}
						}
					}
				}
				// 当类型为TurnoutLX时
				if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLX)) {

					int length = DrawStation.turnoutLXList.size();
					for (int j = 0; j < length; j++) {
						if (roadName.equalsIgnoreCase(DrawStation.turnoutLXList.get(j).getTurnoutName())) {
							if (!(DrawStation.turnoutLXList.get(j).getStatus() == 0)) { // 如果不为初始状态
								can = false;
								return can;
							}
						}
					}
				}
				// 当类型为TurnoutRS时
				if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRS)) {

					int length = DrawStation.turnoutRSList.size();
					for (int j = 0; j < length; j++) {
						if (roadName.equalsIgnoreCase(DrawStation.turnoutRSList.get(j).getTurnoutName())) {
							if (!(DrawStation.turnoutRSList.get(j).getStatus() == 0)) { // 如果不为初始状态
								can = false;
								return can;
							}
						}
					}
				}
				// 当类型为TurnoutRX时
				if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRX)) {

					int length = DrawStation.turnoutRXList.size();
					for (int j = 0; j < length; j++) {
						if (roadName.equalsIgnoreCase(DrawStation.turnoutRXList.get(j).getTurnoutName())) {
							if (!(DrawStation.turnoutRXList.get(j).getStatus() == 0)) { // 如果不为初始状态
								can = false;
								return can;
							}
						}
					}
				}
			}
		}
		can = true;
		return can;

	}

	/**
	 * 判断道路是否有车（可发）
	 * 
	 * @param roadList
	 *            道路list
	 * @return
	 */
	public static boolean JudgeRoadTrain(ArrayList<RoadBasicInfo> roadList, int type) {

		boolean trainExist = false;
		int len = roadList.size();

		if (type == 1) { // 进站判断
			if (len >= 5) {
				for (int i = len - 3; i < len - 1; i++) {

					RoadBasicInfo rBasicInfo = roadList.get(i);
					String roadType = rBasicInfo.getRoadType();
					String roadName = rBasicInfo.getRoadName();

					// 当类型为TrackLine时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTrackLine)) {
						int length = DrawStation.trackLineList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.trackLineList.get(j).getLineName())) {
								if (DrawStation.trackLineList.get(j).getStatus() == 2) { // 如果不为初始状态
									trainExist = true;
									return trainExist;
								}
							}
						}
					}
				}

			} else {
				System.out.println("路径出现错误！");
				return false;
			}
		} else { // 出站判断

			if (len >= 3) {
				for (int i = 1; i < 3; i++) {

					RoadBasicInfo rBasicInfo = roadList.get(i);
					String roadType = rBasicInfo.getRoadType();
					String roadName = rBasicInfo.getRoadName();

					// 当类型为TrackLine时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTrackLine)) {
						int length = DrawStation.trackLineList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.trackLineList.get(j).getLineName())) {
								if (DrawStation.trackLineList.get(j).getStatus() == 2) { // 如果不为初始状态
									trainExist = true;
									return trainExist;
								}
							}
						}
					}
				}
			} else {
				System.out.println("路径出现错误！");
				return false;
			}
		}

		return trainExist;
	}

	/**
	 * 通过（上行）
	 */
	public static void Button_xtaAction() {

		DrawStation.b_sta = 1;
		DrawStation.button_sta.setBackgroundColor(DrawStation.buttonClickColor);

	}

	/**
	 * 进站按钮（上行）
	 */
	public static void Button_xlaAction() {

		DrawStation.b_sla = 1;
		DrawStation.button_sla.setBackgroundColor(DrawStation.buttonClickColor);
	}

	/**
	 * 发车按钮（下行）
	 */
	public static void Button_xlfaAction() {

		DrawStation.b_xlfa = 1;

		// 1股道发车(下行)
		if (DrawStation.b_x1la == 1) {

			DrawTrackButtonModel.roadXZQX = DrawStation.r_x1la_xlfa;
			DrawTrackButtonModel.semphoreNameX = "X1";

			// 发车
			if (StationModel.JudgeRoad(DrawStation.r_x1la_xlfa, 0) == true) {

				// 发向CTC
				// SICSToCTC.SICSToCTCChangeStationStatus("T11", stationName,
				// "发车", "下行", "1");

				DrawStation.r_preRoad = DrawStation.r_x1la_xlfa;
				DrawStation.r_preRoadType = 0;

				int len = DrawStation.r_x1la_xlfa.size();

				for (int i = 3; i < len; i++) {

					RoadBasicInfo rBasicInfo = DrawStation.r_x1la_xlfa.get(i);
					String roadType = rBasicInfo.getRoadType();
					String roadName = rBasicInfo.getRoadName();

					// 当类型为TrackLine时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTrackLine)) {
						int length = DrawStation.trackLineList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.trackLineList.get(j).getLineName())) {
								DrawStation.trackLineList.get(j).setTrackLineStatus("green"); // 绿色
								break;
							}
						}
					}
					// 当类型为TurnoutDouble时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutDouble)) {
						int length = DrawStation.turnoutDoubleList.size();

						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutDoubleList.get(j).getTurnoutName())) {
								DrawStation.turnoutDoubleList.get(j).setColorStatus("greenZS"); // 绿色正位
								break;
							}
						}
					}

					// 当类型为TurnoutLS时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLS)) {

						int length = DrawStation.turnoutLSList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutLSList.get(j).getTurnoutName())) {
								DrawStation.turnoutLSList.get(j).setColorStatus("greenZ"); // 绿色正位
								break;
							}
						}
					}
					// 当类型为TurnoutLX时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLX)) {

						int length = DrawStation.turnoutLXList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutLXList.get(j).getTurnoutName())) {
								DrawStation.turnoutLXList.get(j).setColorStatus("greenZ"); // 绿色正位
								break;
							}
						}
					}
					// 当类型为TurnoutRS时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRS)) {

						int length = DrawStation.turnoutRSList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutRSList.get(j).getTurnoutName())) {
								DrawStation.turnoutRSList.get(j).setColorStatus("greenZ"); // 绿色正位

								break;
							}
						}
					}
					// 当类型为TurnoutRX时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRX)) {

						int length = DrawStation.turnoutRXList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutRXList.get(j).getTurnoutName())) {
								DrawStation.turnoutRXList.get(j).setColorStatus("greenZ"); // 绿色正位

								break;
							}
						}
					}
				}

				if (StationModel.JudgeRoadTrain(DrawStation.r_x1la_xlfa, 0) == true) {
					UnLock.unLockFA(DrawStation.r_x1la_xlfa, 0, DrawStation.button_x1la, DrawStation.b_x1la, DrawStation.button_xlfa,
							DrawStation.b_xlfa, "", "");
				}

			} else {
				System.out.println("1股道有车，1股道下行发车失败！");
				DrawStation.button_x1la.setBackgroundColor(DrawStation.buttonInitColor);
				DrawStation.b_x1la = 0;
				DrawStation.button_xlfa.setBackgroundColor(DrawStation.buttonInitColor);
				DrawStation.b_xlfa = 0;

				DrawTrackButtonModel.Button_xzqxAction();
			}

		} else

		// 2股道发车(下行)
		if (DrawStation.b_x2la == 1) {

			DrawTrackButtonModel.roadXZQX = DrawStation.r_x2la_xlfa;
			DrawTrackButtonModel.semphoreNameX = "X2";

			// 发车
			if (StationModel.JudgeRoad(DrawStation.r_x2la_xlfa, 0) == true) {

				// 发向CTC
				//SICSToCTC.SICSToCTCChangeStationStatus("T11",stationName,"发车",
				// "下行","2");

				DrawStation.r_preRoad = DrawStation.r_x2la_xlfa;
				DrawStation.r_preRoadType = 0;

				int len = DrawStation.r_x2la_xlfa.size();

				for (int i = 3; i < len; i++) {

					RoadBasicInfo rBasicInfo = DrawStation.r_x2la_xlfa.get(i);
					String roadType = rBasicInfo.getRoadType();
					String roadName = rBasicInfo.getRoadName();

					// 当类型为TrackLine时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTrackLine)) {
						int length = DrawStation.trackLineList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.trackLineList.get(j).getLineName())) {
								DrawStation.trackLineList.get(j).setTrackLineStatus("green"); // 绿色

								break;
							}
						}
					}
					// 当类型为TurnoutDouble时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutDouble)) {
						int length = DrawStation.turnoutDoubleList.size();
						// System.out.println(length);

						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutDoubleList.get(j).getTurnoutName())) {
								DrawStation.turnoutDoubleList.get(j).setColorStatus("greenFR"); // 绿色反位

								break;
							}
						}
					}

					// 当类型为TurnoutLS时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLS)) {

						int length = DrawStation.turnoutLSList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutLSList.get(j).getTurnoutName())) {
								DrawStation.turnoutLSList.get(j).setColorStatus("greenZ"); // 绿色正位

								break;
							}
						}
					}
					// 当类型为TurnoutLX时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLX)) {

						int length = DrawStation.turnoutLXList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutLXList.get(j).getTurnoutName())) {
								DrawStation.turnoutLXList.get(j).setColorStatus("greenZ"); // 绿色正位

								break;
							}
						}
					}
					// 当类型为TurnoutRS时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRS)) {

						int length = DrawStation.turnoutRSList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutRSList.get(j).getTurnoutName())) {
								DrawStation.turnoutRSList.get(j).setColorStatus("greenZ"); // 绿色正位

								break;
							}
						}
					}
					// 当类型为TurnoutRX时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRX)) {

						int length = DrawStation.turnoutRXList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutRXList.get(j).getTurnoutName())) {
								DrawStation.turnoutRXList.get(j).setColorStatus("greenZ"); // 绿色正位

								break;
							}
						}
					}
				}

				if (StationModel.JudgeRoadTrain(DrawStation.r_x2la_xlfa, 0) == true) {
					UnLock.unLockFA(DrawStation.r_x2la_xlfa, 0, DrawStation.button_x2la, DrawStation.b_x2la, DrawStation.button_xlfa,
							DrawStation.b_xlfa, "", "");
				}

			} else {
				System.out.println("2股道有车，2股道下行发车失败！");
				DrawStation.button_x2la.setBackgroundColor(DrawStation.buttonInitColor);
				DrawStation.b_x2la = 0;
				DrawStation.button_xlfa.setBackgroundColor(DrawStation.buttonInitColor);
				DrawStation.b_xlfa = 0;

				DrawTrackButtonModel.Button_xzqxAction();
			}

		} else

		// 3股道发车(下行)
		if (DrawStation.b_x3la == 1) {

			DrawTrackButtonModel.roadXZQX = DrawStation.r_x3la_xlfa;
			DrawTrackButtonModel.semphoreNameX = "X3";

			// 发车
			if (StationModel.JudgeRoad(DrawStation.r_x3la_xlfa, 0) == true) {

				// 发向CTC
				//SICSToCTC.SICSToCTCChangeStationStatus("T11",stationName,"发车",
				// "下行","3");

				DrawStation.r_preRoad = DrawStation.r_x3la_xlfa;
				DrawStation.r_preRoadType = 0;

				int len = DrawStation.r_x3la_xlfa.size();

				for (int i = 3; i < len; i++) {

					RoadBasicInfo rBasicInfo = DrawStation.r_x3la_xlfa.get(i);
					String roadType = rBasicInfo.getRoadType();
					String roadName = rBasicInfo.getRoadName();

					// 当类型为TrackLine时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTrackLine)) {
						int length = DrawStation.trackLineList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.trackLineList.get(j).getLineName())) {
								DrawStation.trackLineList.get(j).setTrackLineStatus("green"); // 绿色

								break;
							}
						}
					}
					// 当类型为TurnoutDouble时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutDouble)) {
						int length = DrawStation.turnoutDoubleList.size();
						// System.out.println(length);

						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutDoubleList.get(j).getTurnoutName())) {
								DrawStation.turnoutDoubleList.get(j).setColorStatus("greenZS"); // 绿色正位

								break;
							}
						}
					}

					// 当类型为TurnoutLS时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLS)) {

						int length = DrawStation.turnoutLSList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutLSList.get(j).getTurnoutName())) {
								DrawStation.turnoutLSList.get(j).setColorStatus("greenF"); // 绿色反位

								break;
							}
						}
					}
					// 当类型为TurnoutLX时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLX)) {

						int length = DrawStation.turnoutLXList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutLXList.get(j).getTurnoutName())) {
								DrawStation.turnoutLXList.get(j).setColorStatus("greenF"); // 绿色反位

								break;
							}
						}
					}
					// 当类型为TurnoutRS时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRS)) {

						int length = DrawStation.turnoutRSList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutRSList.get(j).getTurnoutName())) {
								DrawStation.turnoutRSList.get(j).setColorStatus("greenF"); // 绿色反位

								break;
							}
						}
					}
					// 当类型为TurnoutRX时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRX)) {

						int length = DrawStation.turnoutRXList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutRXList.get(j).getTurnoutName())) {
								DrawStation.turnoutRXList.get(j).setColorStatus("greenF"); // 绿色反位

								break;
							}
						}
					}
				}

				if (StationModel.JudgeRoadTrain(DrawStation.r_x3la_xlfa, 0) == true) {
					UnLock.unLockFA(DrawStation.r_x3la_xlfa, 0, DrawStation.button_x3la, DrawStation.b_x3la, DrawStation.button_xlfa,
							DrawStation.b_xlfa, "", "");
				}

			} else {
				System.out.println("3股道有车，3股道下行发车失败！");
				DrawStation.button_x3la.setBackgroundColor(DrawStation.buttonInitColor);
				DrawStation.b_x3la = 0;
				DrawStation.button_xlfa.setBackgroundColor(DrawStation.buttonInitColor);
				DrawStation.b_xlfa = 0;

				DrawTrackButtonModel.Button_xzqxAction();
			}

		} else

		// 4股道发车(下行)
		if (DrawStation.b_x4la == 1) {

			DrawTrackButtonModel.roadXZQX = DrawStation.r_x4la_xlfa;
			DrawTrackButtonModel.semphoreNameX = "X4";

			// 发车
			if (StationModel.JudgeRoad(DrawStation.r_x4la_xlfa, 0) == true) {

				// 发向CTC
				//SICSToCTC.SICSToCTCChangeStationStatus("T11",stationName,"发车",
				// "下行","4");

				DrawStation.r_preRoad = DrawStation.r_x4la_xlfa;
				DrawStation.r_preRoadType = 0;

				int len = DrawStation.r_x4la_xlfa.size();

				for (int i = 3; i < len; i++) {

					RoadBasicInfo rBasicInfo = DrawStation.r_x4la_xlfa.get(i);
					String roadType = rBasicInfo.getRoadType();
					String roadName = rBasicInfo.getRoadName();

					// 当类型为TrackLine时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTrackLine)) {
						int length = DrawStation.trackLineList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.trackLineList.get(j).getLineName())) {
								DrawStation.trackLineList.get(j).setTrackLineStatus("green"); // 绿色

								break;
							}
						}
					}
					// 当类型为TurnoutDouble时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutDouble)) {
						int length = DrawStation.turnoutDoubleList.size();
						// System.out.println(length);

						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutDoubleList.get(j).getTurnoutName())) {
								DrawStation.turnoutDoubleList.get(j).setColorStatus("greenFR"); // 绿色反位

								break;
							}
						}
					}

					// 当类型为TurnoutLS时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLS)) {

						int length = DrawStation.turnoutLSList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutLSList.get(j).getTurnoutName())) {
								DrawStation.turnoutLSList.get(j).setColorStatus("greenF"); // 绿色反位

								break;
							}
						}
					}
					// 当类型为TurnoutLX时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLX)) {

						int length = DrawStation.turnoutLXList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutLXList.get(j).getTurnoutName())) {
								DrawStation.turnoutLXList.get(j).setColorStatus("greenF"); // 绿色反位

								break;
							}
						}
					}
					// 当类型为TurnoutRS时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRS)) {

						int length = DrawStation.turnoutRSList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutRSList.get(j).getTurnoutName())) {
								DrawStation.turnoutRSList.get(j).setColorStatus("greenF"); // 绿色反位

								break;
							}
						}
					}
					// 当类型为TurnoutRX时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRX)) {

						int length = DrawStation.turnoutRXList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutRXList.get(j).getTurnoutName())) {
								DrawStation.turnoutRXList.get(j).setColorStatus("greenF"); // 绿色反位

								break;
							}
						}
					}
				}

				if (StationModel.JudgeRoadTrain(DrawStation.r_x4la_xlfa, 0) == true) {
					UnLock.unLockFA(DrawStation.r_x4la_xlfa, 0, DrawStation.button_x4la, DrawStation.b_x4la, DrawStation.button_xlfa,
							DrawStation.b_xlfa, "", "");
				}

			} else {
				System.out.println("4股道有车，4股道下行发车失败！");
				DrawStation.button_x4la.setBackgroundColor(DrawStation.buttonInitColor);
				DrawStation.b_x4la = 0;
				DrawStation.button_xlfa.setBackgroundColor(DrawStation.buttonInitColor);
				DrawStation.b_xlfa = 0;

				DrawTrackButtonModel.Button_xzqxAction();
			}

		} else

		// 下行通过
		if (DrawStation.b_xta == 1) {

			DrawTrackButtonModel.roadXZQX = DrawStation.r_xta_xlfa;
			DrawTrackButtonModel.semphoreNameX = "XLX+X1";

			if (StationModel.JudgeRoad(DrawStation.r_xta_xlfa, 1) == true) {

				// 发向CTC
				//SICSToCTC.SICSToCTCChangeStationStatus("T11",stationName,"发车",
				// "下行","1");

				DrawStation.r_preRoad = DrawStation.r_xta_xlfa;
				DrawStation.r_preRoadType = 0;

				int len = DrawStation.r_xta_xlfa.size();

				for (int i = 0; i < len; i++) {

					RoadBasicInfo rBasicInfo = DrawStation.r_xta_xlfa.get(i);
					String roadType = rBasicInfo.getRoadType();
					String roadName = rBasicInfo.getRoadName();

					// 当类型为TrackLine时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTrackLine)) {
						int length = DrawStation.trackLineList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.trackLineList.get(j).getLineName())) {
								DrawStation.trackLineList.get(j).setTrackLineStatus("green"); // 绿色

								break;
							}
						}
					}
					// 当类型为TurnoutDouble时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutDouble)) {
						int length = DrawStation.turnoutDoubleList.size();
						// System.out.println(length);

						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutDoubleList.get(j).getTurnoutName())) {
								DrawStation.turnoutDoubleList.get(j).setColorStatus("greenZS"); // 正位绿色

								break;
							}
						}
					}

					// 当类型为TurnoutLS时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLS)) {

						int length = DrawStation.turnoutLSList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutLSList.get(j).getTurnoutName())) {
								DrawStation.turnoutLSList.get(j).setColorStatus("greenZ");// 反位绿色

								break;
							}
						}
					}
					// 当类型为TurnoutLX时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLX)) {

						int length = DrawStation.turnoutLXList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutLXList.get(j).getTurnoutName())) {
								DrawStation.turnoutLXList.get(j).setColorStatus("greenZ");// 反位绿色

								break;
							}
						}
					}
					// 当类型为TurnoutRS时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRS)) {

						int length = DrawStation.turnoutRSList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutRSList.get(j).getTurnoutName())) {
								DrawStation.turnoutRSList.get(j).setColorStatus("greenZ");// 反位绿色

								break;
							}
						}
					}
					// 当类型为TurnoutRX时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRX)) {

						int length = DrawStation.turnoutRXList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutRXList.get(j).getTurnoutName())) {
								DrawStation.turnoutRXList.get(j).setColorStatus("greenZ");// 反位绿色

								break;
							}
						}
					}
				}// 通路选定完毕

				if (DrawStation.trackLineXLA2.getStatus() == 2) {
					UnLock.unLockFAS(DrawStation.r_xta_xlfa, 0, DrawStation.button_xta, DrawStation.b_xta, DrawStation.button_xlfa,
							DrawStation.b_xlfa, "", "");
				}

			} else {
				System.out.println("道路有车，下行直接通过失败！");
				DrawStation.button_xlfa.setBackgroundColor(DrawStation.buttonInitColor);
				DrawStation.b_xlfa = 0;
				DrawStation.button_xta.setBackgroundColor(DrawStation.buttonInitColor);
				DrawStation.b_xta = 0;

				DrawTrackButtonModel.Button_xzqxAction();
			}
		} else {
			System.out.println("第一次点击XLFA按钮，无效操作！");
			DrawStation.b_xlfa = 0;
		}
	}

	/**
	 * 通过（上行）
	 */
	public static void Button_staAction() {

		DrawStation.b_sta = 1;
		DrawStation.button_sta.setBackgroundColor(DrawStation.buttonClickColor);

	}

	/**
	 * 进站按钮（上行）
	 */
	public static void Button_slaAction() {

		DrawStation.b_sla = 1;
		DrawStation.button_sla.setBackgroundColor(DrawStation.buttonClickColor);
	}

	/**
	 * 发车按钮（上行）
	 */
	public static void Button_slfaAction() {

		DrawStation.b_slfa = 1;

		// 1股道发车(上行)
		if (DrawStation.b_s1la == 1) {

			DrawTrackButtonModel.roadSZQX = DrawStation.r_s1la_slfa;
			DrawTrackButtonModel.semphoreNameS = "S1";

			// 发车
			if (StationModel.JudgeRoad(DrawStation.r_s1la_slfa, 0) == true) {

				// 发向CTC
				//SICSToCTC.SICSToCTCChangeStationStatus("T11",stationName,"发车",
				// "上行","1");

				DrawStation.r_preRoad = DrawStation.r_s1la_slfa;
				DrawStation.r_preRoadType = 0;

				int len = DrawStation.r_s1la_slfa.size();

				for (int i = 3; i < len; i++) {

					RoadBasicInfo rBasicInfo = DrawStation.r_s1la_slfa.get(i);
					String roadType = rBasicInfo.getRoadType();
					String roadName = rBasicInfo.getRoadName();

					// 当类型为TrackLine时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTrackLine)) {
						int length = DrawStation.trackLineList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.trackLineList.get(j).getLineName())) {
								DrawStation.trackLineList.get(j).setTrackLineStatus("green"); // 绿色

								break;
							}
						}
					}
					// 当类型为TurnoutDouble时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutDouble)) {
						int length = DrawStation.turnoutDoubleList.size();
						// System.out.println(length);

						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutDoubleList.get(j).getTurnoutName())) {
								DrawStation.turnoutDoubleList.get(j).setColorStatus("greenFR"); // 绿色反位

								break;
							}
						}
					}

					// 当类型为TurnoutLS时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLS)) {

						int length = DrawStation.turnoutLSList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutLSList.get(j).getTurnoutName())) {
								DrawStation.turnoutLSList.get(j).setColorStatus("greenZ"); // 绿色正位

								break;
							}
						}
					}
					// 当类型为TurnoutLX时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLX)) {

						int length = DrawStation.turnoutLXList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutLXList.get(j).getTurnoutName())) {
								DrawStation.turnoutLXList.get(j).setColorStatus("greenZ"); // 绿色正位

								break;
							}
						}
					}
					// 当类型为TurnoutRS时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRS)) {

						int length = DrawStation.turnoutRSList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutRSList.get(j).getTurnoutName())) {
								DrawStation.turnoutRSList.get(j).setColorStatus("greenZ"); // 绿色正位

								break;
							}
						}
					}
					// 当类型为TurnoutRX时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRX)) {

						int length = DrawStation.turnoutRXList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutRXList.get(j).getTurnoutName())) {
								DrawStation.turnoutRXList.get(j).setColorStatus("greenZ"); // 绿色正位

								break;
							}
						}
					}
				}

				if (StationModel.JudgeRoadTrain(DrawStation.r_s1la_slfa, 0)) {
					UnLock.unLockFAS(DrawStation.r_s1la_slfa, 0, DrawStation.button_s1la, DrawStation.b_s1la, DrawStation.button_slfa,
							DrawStation.b_slfa, "", "");
				}

			} else {
				System.out.println("1股道有车，1股道上行发车失败！");
				DrawStation.button_s1la.setBackgroundColor(DrawStation.buttonInitColor);
				DrawStation.b_s1la = 0;
				DrawStation.button_slfa.setBackgroundColor(DrawStation.buttonInitColor);
				DrawStation.b_slfa = 0;

				DrawTrackButtonModel.Button_szqxAction();
			}

		} else

		// 2股道发车(上行)
		if (DrawStation.b_s2la == 1) {

			DrawTrackButtonModel.roadSZQX = DrawStation.r_s2la_slfa;
			DrawTrackButtonModel.semphoreNameS = "S2";

			// 发车
			if (StationModel.JudgeRoad(DrawStation.r_s2la_slfa, 0) == true) {

				// 发向CTC
				//SICSToCTC.SICSToCTCChangeStationStatus("T11",stationName,"发车",
				// "上行","2");

				DrawStation.r_preRoad = DrawStation.r_s2la_slfa;
				DrawStation.r_preRoadType = 0;

				int len = DrawStation.r_s2la_slfa.size();

				for (int i = 3; i < len; i++) {

					RoadBasicInfo rBasicInfo = DrawStation.r_s2la_slfa.get(i);
					String roadType = rBasicInfo.getRoadType();
					String roadName = rBasicInfo.getRoadName();

					// 当类型为TrackLine时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTrackLine)) {
						int length = DrawStation.trackLineList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.trackLineList.get(j).getLineName())) {
								DrawStation.trackLineList.get(j).setTrackLineStatus("green"); // 绿色

								break;
							}
						}
					}
					// 当类型为TurnoutDouble时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutDouble)) {
						int length = DrawStation.turnoutDoubleList.size();
						// System.out.println(length);

						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutDoubleList.get(j).getTurnoutName())) {
								DrawStation.turnoutDoubleList.get(j).setColorStatus("greenZX"); // 正位绿色

								break;
							}
						}
					}

					// 当类型为TurnoutLS时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLS)) {

						int length = DrawStation.turnoutLSList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutLSList.get(j).getTurnoutName())) {
								DrawStation.turnoutLSList.get(j).setColorStatus("greenZ"); // 正位绿色

								break;
							}
						}
					}
					// 当类型为TurnoutLX时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLX)) {

						int length = DrawStation.turnoutLXList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutLXList.get(j).getTurnoutName())) {
								DrawStation.turnoutLXList.get(j).setColorStatus("greenZ"); // 正位绿色

								break;
							}
						}
					}
					// 当类型为TurnoutRS时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRS)) {

						int length = DrawStation.turnoutRSList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutRSList.get(j).getTurnoutName())) {
								DrawStation.turnoutRSList.get(j).setColorStatus("greenZ"); // 正位绿色

								break;
							}
						}
					}
					// 当类型为TurnoutRX时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRX)) {

						int length = DrawStation.turnoutRXList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutRXList.get(j).getTurnoutName())) {
								DrawStation.turnoutRXList.get(j).setColorStatus("greenZ"); // 正位绿色

								break;
							}
						}
					}
				}

				if (StationModel.JudgeRoadTrain(DrawStation.r_s2la_slfa, 0)) {
					UnLock.unLockFAS(DrawStation.r_s2la_slfa, 0, DrawStation.button_s2la, DrawStation.b_s2la, DrawStation.button_slfa,
							DrawStation.b_slfa, "", "");
				}

			} else {
				System.out.println("2股道有车，2股道上行发车失败！");
				DrawStation.button_s2la.setBackgroundColor(DrawStation.buttonInitColor);
				DrawStation.b_s2la = 0;
				DrawStation.button_slfa.setBackgroundColor(DrawStation.buttonInitColor);
				DrawStation.b_slfa = 0;

				DrawTrackButtonModel.Button_szqxAction();
			}

		} else

		// 3股道发车(上行)
		if (DrawStation.b_s3la == 1) {

			DrawTrackButtonModel.roadSZQX = DrawStation.r_s3la_slfa;
			DrawTrackButtonModel.semphoreNameS = "S3";

			// 发车
			if (StationModel.JudgeRoad(DrawStation.r_s3la_slfa, 0) == true) {

				// 发向CTC
				//SICSToCTC.SICSToCTCChangeStationStatus("T11",stationName,"发车",
				// "上行","3");

				DrawStation.r_preRoad = DrawStation.r_s3la_slfa;
				DrawStation.r_preRoadType = 0;

				// ButtonSemphoreModel.semphoreColorModel("S3", "green");

				int len = DrawStation.r_s3la_slfa.size();

				for (int i = 3; i < len; i++) {

					RoadBasicInfo rBasicInfo = DrawStation.r_s3la_slfa.get(i);
					String roadType = rBasicInfo.getRoadType();
					String roadName = rBasicInfo.getRoadName();

					// 当类型为TrackLine时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTrackLine)) {
						int length = DrawStation.trackLineList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.trackLineList.get(j).getLineName())) {
								DrawStation.trackLineList.get(j).setTrackLineStatus("green"); // 绿色

								break;
							}
						}
					}
					// 当类型为TurnoutDouble时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutDouble)) {
						int length = DrawStation.turnoutDoubleList.size();
						// System.out.println(length);

						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutDoubleList.get(j).getTurnoutName())) {
								DrawStation.turnoutDoubleList.get(j).setColorStatus("greenFR"); // 绿色反位

								break;
							}
						}
					}

					// 当类型为TurnoutLS时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLS)) {

						int length = DrawStation.turnoutLSList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutLSList.get(j).getTurnoutName())) {
								DrawStation.turnoutLSList.get(j).setColorStatus("greenF"); // 绿色反位

								break;
							}
						}
					}
					// 当类型为TurnoutLX时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLX)) {

						int length = DrawStation.turnoutLXList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutLXList.get(j).getTurnoutName())) {
								DrawStation.turnoutLXList.get(j).setColorStatus("greenF"); // 绿色反位

								break;
							}
						}
					}
					// 当类型为TurnoutRS时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRS)) {

						int length = DrawStation.turnoutRSList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutRSList.get(j).getTurnoutName())) {
								DrawStation.turnoutRSList.get(j).setColorStatus("greenF"); // 绿色反位

								break;
							}
						}
					}
					// 当类型为TurnoutRX时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRX)) {

						int length = DrawStation.turnoutRXList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutRXList.get(j).getTurnoutName())) {
								DrawStation.turnoutRXList.get(j).setColorStatus("greenF"); // 绿色反位

								break;
							}
						}
					}
				}

				if (StationModel.JudgeRoadTrain(DrawStation.r_s3la_slfa, 0)) {
					UnLock.unLockFAS(DrawStation.r_s3la_slfa, 0, DrawStation.button_s3la, DrawStation.b_s3la, DrawStation.button_slfa,
							DrawStation.b_slfa, "", "");
				}

			} else {
				System.out.println("3股道有车，3股道上行发车失败！");
				DrawStation.button_s3la.setBackgroundColor(DrawStation.buttonInitColor);
				DrawStation.b_s3la = 0;
				DrawStation.button_slfa.setBackgroundColor(DrawStation.buttonInitColor);
				DrawStation.b_slfa = 0;

				DrawTrackButtonModel.Button_szqxAction();
			}

		} else

		// 4股道发车(上行)
		if (DrawStation.b_s4la == 1) {

			DrawTrackButtonModel.roadSZQX = DrawStation.r_s4la_slfa;
			DrawTrackButtonModel.semphoreNameS = "S4";

			// 发车
			if (StationModel.JudgeRoad(DrawStation.r_s4la_slfa, 0) == true) {

				// DrawStation.b_s4la = 0;

				// 发向CTC
				//SICSToCTC.SICSToCTCChangeStationStatus("T11",stationName,"发车",
				// "上行","4");

				DrawStation.r_preRoad = DrawStation.r_s4la_slfa;
				DrawStation.r_preRoadType = 0;

				// ButtonSemphoreModel.semphoreColorModel("S4", "green");

				int len = DrawStation.r_s4la_slfa.size();

				for (int i = 3; i < len; i++) {

					RoadBasicInfo rBasicInfo = DrawStation.r_s4la_slfa.get(i);
					String roadType = rBasicInfo.getRoadType();
					String roadName = rBasicInfo.getRoadName();

					// 当类型为TrackLine时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTrackLine)) {
						int length = DrawStation.trackLineList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.trackLineList.get(j).getLineName())) {
								DrawStation.trackLineList.get(j).setTrackLineStatus("green"); // 绿色

								break;
							}
						}
					}
					// 当类型为TurnoutDouble时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutDouble)) {
						int length = DrawStation.turnoutDoubleList.size();
						// System.out.println(length);

						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutDoubleList.get(j).getTurnoutName())) {
								DrawStation.turnoutDoubleList.get(j).setColorStatus("greenZX"); // 绿色正位

								break;
							}
						}
					}

					// 当类型为TurnoutLS时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLS)) {

						int length = DrawStation.turnoutLSList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutLSList.get(j).getTurnoutName())) {
								DrawStation.turnoutLSList.get(j).setColorStatus("greenF"); // 绿色反位

								break;
							}
						}
					}
					// 当类型为TurnoutLX时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLX)) {

						int length = DrawStation.turnoutLXList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutLXList.get(j).getTurnoutName())) {
								DrawStation.turnoutLXList.get(j).setColorStatus("greenF"); // 绿色反位

								break;
							}
						}
					}
					// 当类型为TurnoutRS时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRS)) {

						int length = DrawStation.turnoutRSList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutRSList.get(j).getTurnoutName())) {
								DrawStation.turnoutRSList.get(j).setColorStatus("greenF"); // 绿色反位

								break;
							}
						}
					}
					// 当类型为TurnoutRX时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRX)) {

						int length = DrawStation.turnoutRXList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutRXList.get(j).getTurnoutName())) {
								DrawStation.turnoutRXList.get(j).setColorStatus("greenF"); // 绿色反位

								break;
							}
						}
					}
				}

				if (StationModel.JudgeRoadTrain(DrawStation.r_s4la_slfa, 0)) {
					UnLock.unLockFAS(DrawStation.r_s4la_slfa, 0, DrawStation.button_s4la, DrawStation.b_s4la, DrawStation.button_slfa,
							DrawStation.b_slfa, "", "");
				}

			} else {
				System.out.println("4股道有车，4股道上行发车失败！");
				DrawStation.button_s4la.setBackgroundColor(DrawStation.buttonInitColor);
				DrawStation.b_s4la = 0;
				DrawStation.button_slfa.setBackgroundColor(DrawStation.buttonInitColor);
				DrawStation.b_slfa = 0;

				DrawTrackButtonModel.Button_szqxAction();
			}

		} else

		// 直接通过
		if (DrawStation.b_sta == 1) {

			DrawTrackButtonModel.roadSZQX = DrawStation.r_sta_slfa;
			DrawTrackButtonModel.semphoreNameS = "SLX+S2";

			if (StationModel.JudgeRoad(DrawStation.r_sta_slfa, 1) == true) {

				// 发向CTC
				//SICSToCTC.SICSToCTCChangeStationStatus("T11",stationName,"发车",
				// "上行","1");

				DrawStation.r_preRoad = DrawStation.r_sta_slfa;
				DrawStation.r_preRoadType = 0;

				// ButtonSemphoreModel.semphoreColorModel("S2", "green");

				int len = DrawStation.r_sta_slfa.size();

				for (int i = 0; i < len; i++) {

					RoadBasicInfo rBasicInfo = DrawStation.r_sta_slfa.get(i);
					String roadType = rBasicInfo.getRoadType();
					String roadName = rBasicInfo.getRoadName();

					// 当类型为TrackLine时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTrackLine)) {
						int length = DrawStation.trackLineList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.trackLineList.get(j).getLineName())) {
								DrawStation.trackLineList.get(j).setTrackLineStatus("green"); // 设置为通路

								break;
							}
						}
					}
					// 当类型为TurnoutDouble时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutDouble)) {
						int length = DrawStation.turnoutDoubleList.size();
						// System.out.println(length);

						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutDoubleList.get(j).getTurnoutName())) {
								DrawStation.turnoutDoubleList.get(j).setColorStatus("greenZX"); // 正位绿色
								// (
								// 下
								// )
								DrawStation.turnoutDoubleList.get(j).setStatus(1); // 设置状态为锁定
								break;
							}
						}
					}

					// 当类型为TurnoutLS时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLS)) {

						int length = DrawStation.turnoutLSList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutLSList.get(j).getTurnoutName())) {
								DrawStation.turnoutLSList.get(j).setColorStatus("greenZ"); // 绿色正位

								break;
							}
						}
					}
					// 当类型为TurnoutLX时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLX)) {

						int length = DrawStation.turnoutLXList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutLXList.get(j).getTurnoutName())) {
								DrawStation.turnoutLXList.get(j).setColorStatus("greenZ");// 绿色正位

								break;
							}
						}
					}
					// 当类型为TurnoutRS时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRS)) {

						int length = DrawStation.turnoutRSList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutRSList.get(j).getTurnoutName())) {
								DrawStation.turnoutRSList.get(j).setColorStatus("greenZ");// 绿色正位

								break;
							}
						}
					}
					// 当类型为TurnoutRX时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRX)) {

						int length = DrawStation.turnoutRXList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutRXList.get(j).getTurnoutName())) {
								DrawStation.turnoutRXList.get(j).setColorStatus("greenZ");// 绿色正位

								break;
							}
						}
					}
				}

				if (DrawStation.trackLineSLA2.getStatus() == 2) {
					UnLock.unLockFAS(DrawStation.r_sta_slfa, 0, DrawStation.button_sta, DrawStation.b_sta, DrawStation.button_slfa,
							DrawStation.b_slfa, "", "");
				}

			} else {
				System.out.println("股道有车，上行直接通过失败！");
				DrawStation.button_sta.setBackgroundColor(DrawStation.buttonInitColor);
				DrawStation.b_sta = 0;
				DrawStation.button_slfa.setBackgroundColor(DrawStation.buttonInitColor);
				DrawStation.b_slfa = 0;

				DrawTrackButtonModel.Button_szqxAction();
			}

		} else {
			System.out.println("SLFA按钮第一次被按，无效操作！");
			DrawStation.b_slfa = 0;

		}

	}

	/**
	 * 1股道接车（下行）
	 */
	public static void Button_s1laAction() {

		DrawStation.b_s1la = 1;

		// 接车
		if (DrawStation.b_xla == 1) {

			DrawTrackButtonModel.roadXZQX = DrawStation.r_xla_x1la;
			DrawTrackButtonModel.semphoreNameX = "XLX";

			if (StationModel.JudgeRoad(DrawStation.r_xla_x1la, 1) == true) {

				UnLock.roadXLA = DrawStation.r_xla_x1la;

				// 发向CTC
				// SICSToCTC.SICSToCTCChangeStationStatus("T11", stationName,
				// "接车", "下行", "1");

				DrawStation.r_preRoad = DrawStation.r_xla_x1la;
				DrawStation.r_preRoadType = 1;

				int len = DrawStation.r_xla_x1la.size();

				for (int i = 0; i < len - 1; i++) {

					RoadBasicInfo rBasicInfo = DrawStation.r_xla_x1la.get(i);
					String roadType = rBasicInfo.getRoadType();
					String roadName = rBasicInfo.getRoadName();

					// 当类型为TrackLine时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTrackLine)) {
						int length = DrawStation.trackLineList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.trackLineList.get(j).getLineName())) {
								DrawStation.trackLineList.get(j).setTrackLineStatus("green"); // 绿色

								break;
							}
						}
					}
					// 当类型为TurnoutDouble时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutDouble)) {
						int length = DrawStation.turnoutDoubleList.size();
						// System.out.println(length);

						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutDoubleList.get(j).getTurnoutName())) {
								DrawStation.turnoutDoubleList.get(j).setColorStatus("greenZS"); // 绿色正位

								break;
							}
						}
					}

					// 当类型为TurnoutLS时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLS)) {

						int length = DrawStation.turnoutLSList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutLSList.get(j).getTurnoutName())) {
								DrawStation.turnoutLSList.get(j).setColorStatus("greenZ"); // 绿色正位

								break;
							}
						}
					}
					// 当类型为TurnoutLX时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLX)) {

						int length = DrawStation.turnoutLXList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutLXList.get(j).getTurnoutName())) {
								DrawStation.turnoutLXList.get(j).setColorStatus("greenZ"); // 绿色正位

								break;
							}
						}
					}
					// 当类型为TurnoutRS时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRS)) {

						int length = DrawStation.turnoutRSList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutRSList.get(j).getTurnoutName())) {
								DrawStation.turnoutRSList.get(j).setColorStatus("greenZ"); // 绿色正位

								break;
							}
						}
					}
					// 当类型为TurnoutRX时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRX)) {

						int length = DrawStation.turnoutRXList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutRXList.get(j).getTurnoutName())) {
								DrawStation.turnoutRXList.get(j).setColorStatus("greenZ"); // 绿色正位

								break;
							}
						}
					}
				}
				
				UnLock.unLockLA(DrawStation.r_xla_x1la, 1, DrawStation.button_xla, DrawStation.b_xla,DrawStation.button_s1la,DrawStation.b_s1la,"","");

			} else {
				System.out.println("1股道有车，1股道下行接车失败！");
				DrawStation.button_s1la.setBackgroundColor(DrawStation.buttonInitColor);
				DrawStation.b_s1la = 0;
				DrawStation.button_xla.setBackgroundColor(DrawStation.buttonInitColor);
				DrawStation.b_xla = 0;

				DrawTrackButtonModel.Button_xzqxAction();
			}
		} else {

			System.out.println("XLA按钮未按，S1LA第一次被按，可能为1股道上行发车！");
		}
	}

	/**
	 * 2股道接车（下行）
	 */
	public static void Button_s2laAction() {

		DrawStation.b_s2la = 1;

		// 接车
		if (DrawStation.b_xla == 1) {

			DrawTrackButtonModel.roadXZQX = DrawStation.r_xla_x2la;
			DrawTrackButtonModel.semphoreNameX = "XLX";

			if (StationModel.JudgeRoad(DrawStation.r_xla_x2la, 1) == true) {

				UnLock.roadXLA = DrawStation.r_xla_x2la;

				// 发向CTC
				// SICSToCTC.SICSToCTCChangeStationStatus("T11", stationName,
				// "接车", "下行", "2");

				DrawStation.r_preRoad = DrawStation.r_xla_x2la;
				DrawStation.r_preRoadType = 1;

				int len = DrawStation.r_xla_x2la.size();

				for (int i = 0; i < len - 1; i++) {

					RoadBasicInfo rBasicInfo = DrawStation.r_xla_x2la.get(i);
					String roadType = rBasicInfo.getRoadType();
					String roadName = rBasicInfo.getRoadName();

					// 当类型为TrackLine时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTrackLine)) {
						int length = DrawStation.trackLineList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.trackLineList.get(j).getLineName())) {
								DrawStation.trackLineList.get(j).setTrackLineStatus("green"); // 绿色

								break;
							}
						}
					}
					// 当类型为TurnoutDouble时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutDouble)) {
						int length = DrawStation.turnoutDoubleList.size();
						// System.out.println(length);

						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutDoubleList.get(j).getTurnoutName())) {
								DrawStation.turnoutDoubleList.get(j).setColorStatus("greenFL"); // 绿色反位

								break;
							}
						}
					}

					// 当类型为TurnoutLS时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLS)) {

						int length = DrawStation.turnoutLSList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutLSList.get(j).getTurnoutName())) {
								DrawStation.turnoutLSList.get(j).setColorStatus("greenZ"); // 绿色正位

								break;
							}
						}
					}
					// 当类型为TurnoutLX时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLX)) {

						int length = DrawStation.turnoutLXList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutLXList.get(j).getTurnoutName())) {
								DrawStation.turnoutLXList.get(j).setColorStatus("greenZ"); // 绿色正位

								break;
							}
						}
					}
					// 当类型为TurnoutRS时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRS)) {

						int length = DrawStation.turnoutRSList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutRSList.get(j).getTurnoutName())) {
								DrawStation.turnoutRSList.get(j).setColorStatus("greenZ"); // 绿色正位

								break;
							}
						}
					}
					// 当类型为TurnoutRX时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRX)) {

						int length = DrawStation.turnoutRXList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutRXList.get(j).getTurnoutName())) {
								DrawStation.turnoutRXList.get(j).setColorStatus("greenZ"); // 绿色正位

								break;
							}
						}
					}
				}

			} else {
				System.out.println("2股道有车，2股道下行接车失败！");
				DrawStation.button_s2la.setBackgroundColor(DrawStation.buttonInitColor);
				DrawStation.b_s2la = 0;
				DrawStation.button_xla.setBackgroundColor(DrawStation.buttonInitColor);
				DrawStation.b_xla = 0;

				DrawTrackButtonModel.Button_xzqxAction();
			}
		} else {

			System.out.println("XLA按钮未按，S2LA按钮第一次被按，可能为2股道上行发车！");
		}
	}

	/**
	 * 3股道接车（下行）
	 */
	public static void Button_s3laAction() {
		DrawStation.b_s3la = 1;

		// 接车
		if (DrawStation.b_xla == 1) {

			DrawTrackButtonModel.roadXZQX = DrawStation.r_xla_x3la;
			DrawTrackButtonModel.semphoreNameX = "XLX";

			if (StationModel.JudgeRoad(DrawStation.r_xla_x3la, 1) == true) {

				UnLock.roadXLA = DrawStation.r_xla_x3la;

				// 发向CTC
				// SICSToCTC.SICSToCTCChangeStationStatus("T11", stationName,
				// "接车", "下行", "3");

				DrawStation.r_preRoad = DrawStation.r_xla_x3la;
				DrawStation.r_preRoadType = 1;

				int len = DrawStation.r_xla_x3la.size();

				for (int i = 0; i < len - 1; i++) {

					RoadBasicInfo rBasicInfo = DrawStation.r_xla_x3la.get(i);
					String roadType = rBasicInfo.getRoadType();
					String roadName = rBasicInfo.getRoadName();

					// 当类型为TrackLine时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTrackLine)) {
						int length = DrawStation.trackLineList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.trackLineList.get(j).getLineName())) {
								DrawStation.trackLineList.get(j).setTrackLineStatus("green"); // 绿色

								break;
							}
						}
					}
					// 当类型为TurnoutDouble时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutDouble)) {
						int length = DrawStation.turnoutDoubleList.size();
						// System.out.println(length);

						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutDoubleList.get(j).getTurnoutName())) {
								DrawStation.turnoutDoubleList.get(j).setColorStatus("greenZS"); // 绿色正位

								break;
							}
						}
					}

					// 当类型为TurnoutLS时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLS)) {

						int length = DrawStation.turnoutLSList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutLSList.get(j).getTurnoutName())) {
								DrawStation.turnoutLSList.get(j).setColorStatus("greenF"); // 绿色反位

								break;
							}
						}
					}
					// 当类型为TurnoutLX时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLX)) {

						int length = DrawStation.turnoutLXList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutLXList.get(j).getTurnoutName())) {
								DrawStation.turnoutLXList.get(j).setColorStatus("greenF"); // 绿色反位

								break;
							}
						}
					}
					// 当类型为TurnoutRS时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRS)) {

						int length = DrawStation.turnoutRSList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutRSList.get(j).getTurnoutName())) {
								DrawStation.turnoutRSList.get(j).setColorStatus("greenF"); // 绿色反位

								break;
							}
						}
					}
					// 当类型为TurnoutRX时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRX)) {

						int length = DrawStation.turnoutRXList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutRXList.get(j).getTurnoutName())) {
								DrawStation.turnoutRXList.get(j).setColorStatus("greenF"); // 绿色反位

								break;
							}
						}
					}
				}

			} else {
				System.out.println("3股道有车，3道下行接车失败!");
				DrawStation.button_s3la.setBackgroundColor(DrawStation.buttonInitColor);
				DrawStation.b_s3la = 0;
				DrawStation.button_xla.setBackgroundColor(DrawStation.buttonInitColor);
				DrawStation.b_xla = 0;

				DrawTrackButtonModel.Button_xzqxAction();
			}
		} else {

			System.out.println("XLA按钮未按，S3LA第一次被按，可能为3股道上行发车！");
		}
	}

	/**
	 * 4股道接车（下行）
	 */
	public static void Button_s4laAction() {
		DrawStation.b_s4la = 1;

		// 接车
		if (DrawStation.b_xla == 1) {

			DrawTrackButtonModel.roadXZQX = DrawStation.r_xla_x4la;
			DrawTrackButtonModel.semphoreNameX = "XLX";

			if (StationModel.JudgeRoad(DrawStation.r_xla_x4la, 1) == true) {

				UnLock.roadXLA = DrawStation.r_xla_x4la;

				// 发向CTC
				SICSToCTC.SICSToCTCChangeStationStatus("T11", stationName, "接车", "下行", "4");

				DrawStation.r_preRoad = DrawStation.r_xla_x4la;
				DrawStation.r_preRoadType = 1;

				int len = DrawStation.r_xla_x4la.size();

				for (int i = 0; i < len - 1; i++) {

					RoadBasicInfo rBasicInfo = DrawStation.r_xla_x4la.get(i);
					String roadType = rBasicInfo.getRoadType();
					String roadName = rBasicInfo.getRoadName();

					// 当类型为TrackLine时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTrackLine)) {
						int length = DrawStation.trackLineList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.trackLineList.get(j).getLineName())) {
								DrawStation.trackLineList.get(j).setTrackLineStatus("green"); // 绿色

								break;
							}
						}
					}
					// 当类型为TurnoutDouble时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutDouble)) {
						int length = DrawStation.turnoutDoubleList.size();
						// System.out.println(length);

						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutDoubleList.get(j).getTurnoutName())) {
								DrawStation.turnoutDoubleList.get(j).setColorStatus("greenFL"); // 绿色反位

								break;
							}
						}
					}

					// 当类型为TurnoutLS时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLS)) {

						int length = DrawStation.turnoutLSList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutLSList.get(j).getTurnoutName())) {
								DrawStation.turnoutLSList.get(j).setColorStatus("greenF"); // 绿色反位

								break;
							}
						}
					}
					// 当类型为TurnoutLX时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLX)) {

						int length = DrawStation.turnoutLXList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutLXList.get(j).getTurnoutName())) {
								DrawStation.turnoutLXList.get(j).setColorStatus("greenF"); // 绿色反位

								break;
							}
						}
					}
					// 当类型为TurnoutRS时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRS)) {

						int length = DrawStation.turnoutRSList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutRSList.get(j).getTurnoutName())) {
								DrawStation.turnoutRSList.get(j).setColorStatus("greenF"); // 绿色反位

								break;
							}
						}
					}
					// 当类型为TurnoutRX时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRX)) {

						int length = DrawStation.turnoutRXList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutRXList.get(j).getTurnoutName())) {
								DrawStation.turnoutRXList.get(j).setColorStatus("greenF"); // 绿色反位

								break;
							}
						}
					}
				}

			} else {
				System.out.println("4股道有车，4股道下行接车失败！");
				DrawStation.button_s4la.setBackgroundColor(DrawStation.buttonInitColor);
				DrawStation.b_s4la = 0;
				DrawStation.button_xla.setBackgroundColor(DrawStation.buttonInitColor);
				DrawStation.b_xla = 0;

				DrawTrackButtonModel.Button_xzqxAction();
			}
		} else {

			System.out.println("XLA按钮未按，S4LA按钮第一次被按，可能为4股道上行发车！");
		}
	}

	/**
	 * 1股道接车(上行)
	 */
	public static void Button_x1laAction() {

		DrawStation.b_x1la = 1;

		// 接车
		if (DrawStation.b_sla == 1) {

			DrawTrackButtonModel.roadSZQX = DrawStation.r_sla_s1la;
			DrawTrackButtonModel.semphoreNameS = "SLX";

			if (StationModel.JudgeRoad(DrawStation.r_sla_s1la, 1) == true) {

				UnLock.roadSLA = DrawStation.r_sla_s1la;

				// 发向CTC
				// SICSToCTC.SICSToCTCChangeStationStatus("T11", stationName,
				// "接车", "上行", "1");

				DrawStation.r_preRoad = DrawStation.r_sla_s1la;
				DrawStation.r_preRoadType = 1;

				int len = DrawStation.r_sla_s1la.size();

				for (int i = 0; i < len - 1; i++) {

					RoadBasicInfo rBasicInfo = DrawStation.r_sla_s1la.get(i);
					String roadType = rBasicInfo.getRoadType();
					String roadName = rBasicInfo.getRoadName();

					// 当类型为TrackLine时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTrackLine)) {
						int length = DrawStation.trackLineList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.trackLineList.get(j).getLineName())) {
								DrawStation.trackLineList.get(j).setTrackLineStatus("green"); // 绿色

								break;
							}
						}
					}
					// 当类型为TurnoutDouble时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutDouble)) {
						int length = DrawStation.turnoutDoubleList.size();
						// System.out.println(length);

						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutDoubleList.get(j).getTurnoutName())) {
								DrawStation.turnoutDoubleList.get(j).setColorStatus("greenFL"); // 反位绿色

								break;
							}
						}
					}

					// 当类型为TurnoutLS时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLS)) {

						int length = DrawStation.turnoutLSList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutLSList.get(j).getTurnoutName())) {
								DrawStation.turnoutLSList.get(j).setColorStatus("greenZ"); // 正位绿色

								break;
							}
						}
					}
					// 当类型为TurnoutLX时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLX)) {

						int length = DrawStation.turnoutLXList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutLXList.get(j).getTurnoutName())) {
								DrawStation.turnoutLXList.get(j).setColorStatus("greenZ"); // 正位绿色

								break;
							}
						}
					}
					// 当类型为TurnoutRS时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRS)) {

						int length = DrawStation.turnoutRSList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutRSList.get(j).getTurnoutName())) {
								DrawStation.turnoutRSList.get(j).setColorStatus("greenZ"); // 正位绿色

								break;
							}
						}
					}
					// 当类型为TurnoutRX时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRX)) {

						int length = DrawStation.turnoutRXList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutRXList.get(j).getTurnoutName())) {
								DrawStation.turnoutRXList.get(j).setColorStatus("greenZ"); // 正位绿色

								break;
							}
						}
					}
				}

				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// break;
				}

			} else {
				System.out.println("1股道有车，1股道上行接车失败！");
				DrawStation.button_x1la.setBackgroundColor(DrawStation.buttonInitColor);
				DrawStation.b_x1la = 0;
				DrawStation.button_sla.setBackgroundColor(DrawStation.buttonInitColor);
				DrawStation.b_sla = 0;

				DrawTrackButtonModel.Button_szqxAction();
			}
		} else {

			System.out.println("SLA没有被点击，X1LA第一次被点击，可能为1股道下行发车！");
		}
	}

	/**
	 * 2股道接车（上行）
	 */
	public static void Button_x2laAction() {

		DrawStation.b_x2la = 1;

		// 接车
		if (DrawStation.b_sla == 1) {

			DrawTrackButtonModel.roadSZQX = DrawStation.r_sla_s2la;
			DrawTrackButtonModel.semphoreNameS = "SLX";

			if (StationModel.JudgeRoad(DrawStation.r_sla_s2la, 1) == true) {

				UnLock.roadSLA = DrawStation.r_sla_s2la;

				// 发向CTC
				// SICSToCTC.SICSToCTCChangeStationStatus("T11", stationName,
				// "接车", "上行", "2");

				DrawStation.r_preRoad = DrawStation.r_sla_s2la;
				DrawStation.r_preRoadType = 1;

				int len = DrawStation.r_sla_s2la.size();

				for (int i = 0; i < len - 1; i++) {

					RoadBasicInfo rBasicInfo = DrawStation.r_sla_s2la.get(i);
					String roadType = rBasicInfo.getRoadType();
					String roadName = rBasicInfo.getRoadName();

					// 当类型为TrackLine时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTrackLine)) {
						int length = DrawStation.trackLineList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.trackLineList.get(j).getLineName())) {
								DrawStation.trackLineList.get(j).setTrackLineStatus("green"); // 绿色

								break;
							}
						}
					}
					// 当类型为TurnoutDouble时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutDouble)) {
						int length = DrawStation.turnoutDoubleList.size();

						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutDoubleList.get(j).getTurnoutName())) {
								DrawStation.turnoutDoubleList.get(j).setColorStatus("greenZX"); // 正位绿色

								break;
							}
						}
					}

					// 当类型为TurnoutLS时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLS)) {

						int length = DrawStation.turnoutLSList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutLSList.get(j).getTurnoutName())) {
								DrawStation.turnoutLSList.get(j).setColorStatus("greenZ"); // 绿色正位

								break;
							}
						}
					}
					// 当类型为TurnoutLX时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLX)) {

						int length = DrawStation.turnoutLXList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutLXList.get(j).getTurnoutName())) {
								DrawStation.turnoutLXList.get(j).setColorStatus("greenZ"); // 绿色正位

								break;
							}
						}
					}
					// 当类型为TurnoutRS时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRS)) {

						int length = DrawStation.turnoutRSList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutRSList.get(j).getTurnoutName())) {
								DrawStation.turnoutRSList.get(j).setColorStatus("greenZ"); // 绿色正位

								break;
							}
						}
					}
					// 当类型为TurnoutRX时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRX)) {

						int length = DrawStation.turnoutRXList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutRXList.get(j).getTurnoutName())) {
								DrawStation.turnoutRXList.get(j).setColorStatus("greenZ"); // 绿色正位

								break;
							}
						}
					}
				}

			} else {
				System.out.println("2股道有车，2股道上行接车失败！");
				DrawStation.button_x2la.setBackgroundColor(DrawStation.buttonInitColor);
				DrawStation.b_x2la = 0;
				DrawStation.button_sla.setBackgroundColor(DrawStation.buttonInitColor);
				DrawStation.b_sla = 0;

				DrawTrackButtonModel.Button_szqxAction();
			}
		} else {

			System.out.println("SLA未被点击，X2LA按钮第一次被按，可能为2股道下行发车！");
		}

	}

	/**
	 * 3股道接车（上行）
	 */
	public static void Button_x3laAction() {

		DrawStation.b_x3la = 1;

		// 接车
		if (DrawStation.b_sla == 1) {

			DrawTrackButtonModel.roadSZQX = DrawStation.r_sla_s3la;
			DrawTrackButtonModel.semphoreNameS = "SLX";

			if (StationModel.JudgeRoad(DrawStation.r_sla_s3la, 1) == true) {

				UnLock.roadSLA = DrawStation.r_sla_s3la;

				// 发向CTC
				// SICSToCTC.SICSToCTCChangeStationStatus("T11", stationName,
				// "接车", "上行", "3");

				DrawStation.r_preRoad = DrawStation.r_sla_s3la;
				DrawStation.r_preRoadType = 1;

				// 信号机

				int len = DrawStation.r_sla_s3la.size();

				for (int i = 0; i < len - 1; i++) {

					RoadBasicInfo rBasicInfo = DrawStation.r_sla_s3la.get(i);
					String roadType = rBasicInfo.getRoadType();
					String roadName = rBasicInfo.getRoadName();

					// 当类型为TrackLine时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTrackLine)) {
						int length = DrawStation.trackLineList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.trackLineList.get(j).getLineName())) {
								DrawStation.trackLineList.get(j).setTrackLineStatus("green");// 绿色

								break;
							}
						}
					}
					// 当类型为TurnoutDouble时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutDouble)) {
						int length = DrawStation.turnoutDoubleList.size();

						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutDoubleList.get(j).getTurnoutName())) {
								DrawStation.turnoutDoubleList.get(j).setColorStatus("greenFL");// 绿色反位

								break;
							}
						}
					}

					// 当类型为TurnoutLS时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLS)) {

						int length = DrawStation.turnoutLSList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutLSList.get(j).getTurnoutName())) {
								DrawStation.turnoutLSList.get(j).setColorStatus("greenF");// 绿色反位

								break;
							}
						}
					}
					// 当类型为TurnoutLX时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLX)) {

						int length = DrawStation.turnoutLXList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutLXList.get(j).getTurnoutName())) {
								DrawStation.turnoutLXList.get(j).setColorStatus("greenF");// 绿色反位

								break;
							}
						}
					}
					// 当类型为TurnoutRS时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRS)) {

						int length = DrawStation.turnoutRSList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutRSList.get(j).getTurnoutName())) {
								DrawStation.turnoutRSList.get(j).setColorStatus("greenF");// 绿色反位

								break;
							}
						}
					}
					// 当类型为TurnoutRX时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRX)) {

						int length = DrawStation.turnoutRXList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutRXList.get(j).getTurnoutName())) {
								DrawStation.turnoutRXList.get(j).setColorStatus("greenF");// 绿色反位

								break;
							}
						}
					}
				}

			} else {
				System.out.println("3股道有车，3道上行接车失败！");
				DrawStation.button_x3la.setBackgroundColor(DrawStation.buttonInitColor);
				DrawStation.b_x3la = 0;
				DrawStation.button_sla.setBackgroundColor(DrawStation.buttonInitColor);
				DrawStation.b_sla = 0;

				DrawTrackButtonModel.Button_szqxAction();
			}
		} else {

			System.out.println("SLA按钮未被按，X3LA第一次被点击，可能为发车！");
		}
	}

	/**
	 * 4股道接车（上行）
	 */
	public static void Button_x4laAction() {

		DrawStation.b_x4la = 1;

		// 接车
		if (DrawStation.b_sla == 1) {

			DrawTrackButtonModel.roadSZQX = DrawStation.r_sla_s4la;
			DrawTrackButtonModel.semphoreNameS = "SLX";

			if (StationModel.JudgeRoad(DrawStation.r_sla_s4la, 1) == true) {

				UnLock.roadSLA = DrawStation.r_sla_s4la;

				// 发向CTC
				// SICSToCTC.SICSToCTCChangeStationStatus("T11", stationName,
				// "接车", "上行", "4");

				DrawStation.r_preRoad = DrawStation.r_sla_s4la;
				DrawStation.r_preRoadType = 1;

				int len = DrawStation.r_sla_s4la.size();

				for (int i = 0; i < len - 1; i++) {

					RoadBasicInfo rBasicInfo = DrawStation.r_sla_s4la.get(i);
					String roadType = rBasicInfo.getRoadType();
					String roadName = rBasicInfo.getRoadName();

					// 当类型为TrackLine时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTrackLine)) {
						int length = DrawStation.trackLineList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.trackLineList.get(j).getLineName())) {
								DrawStation.trackLineList.get(j).setTrackLineStatus("green"); // 绿色

								break;
							}
						}
					}
					// 当类型为TurnoutDouble时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutDouble)) {
						int length = DrawStation.turnoutDoubleList.size();

						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutDoubleList.get(j).getTurnoutName())) {
								DrawStation.turnoutDoubleList.get(j).setColorStatus("greenZX"); // 绿色正位

								break;
							}
						}
					}

					// 当类型为TurnoutLS时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLS)) {

						int length = DrawStation.turnoutLSList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutLSList.get(j).getTurnoutName())) {
								DrawStation.turnoutLSList.get(j).setColorStatus("greenF"); // 绿色反位

								break;
							}
						}
					}
					// 当类型为TurnoutLX时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutLX)) {

						int length = DrawStation.turnoutLXList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutLXList.get(j).getTurnoutName())) {
								DrawStation.turnoutLXList.get(j).setColorStatus("greenF"); // 绿色反位

								break;
							}
						}
					}
					// 当类型为TurnoutRS时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRS)) {

						int length = DrawStation.turnoutRSList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutRSList.get(j).getTurnoutName())) {
								DrawStation.turnoutRSList.get(j).setColorStatus("greenF"); // 绿色反位

								break;
							}
						}
					}
					// 当类型为TurnoutRX时
					if (roadType.equalsIgnoreCase(DrawStation.roadBasicInfoTypeOfTurnoutRX)) {

						int length = DrawStation.turnoutRXList.size();
						for (int j = 0; j < length; j++) {
							if (roadName.equalsIgnoreCase(DrawStation.turnoutRXList.get(j).getTurnoutName())) {
								DrawStation.turnoutRXList.get(j).setColorStatus("greenF"); // 绿色反位

								break;
							}
						}
					}
				}

			} else {
				System.out.println("4股道有车，4股道上行接车失败！");
				DrawStation.button_x4la.setBackgroundColor(DrawStation.buttonInitColor);
				DrawStation.b_x4la = 0;
				DrawStation.button_sla.setBackgroundColor(DrawStation.buttonInitColor);
				DrawStation.b_sla = 0;

				DrawTrackButtonModel.Button_szqxAction();
			}
		} else {
			System.out.println("SLA未被点击，X4LA按钮第一次被按，可能为4股道下行发车！");
		}
	}

}
