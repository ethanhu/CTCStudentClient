package ctc.sics.station.drawstation;

import java.util.ArrayList;

import org.eclipse.draw2d.Figure;

import ctc.sics.station.data.*;
import ctc.sics.station.layout.StationModel;

public class SetRoad {

	StationModel stationModel = StationModel.getInstance();
	BaseParam baseDataForStation = BaseParam.getInstance();

	public RoadBasicInfo roadBasicInfo; // 股道段基本信息
	public ArrayList<RoadBasicInfo> roadInfoList; // 股道段基本信息list
	public RoadList road; // 路径
	public ArrayList<RoadList> roadList = baseDataForStation.getAllRoadList(); // 路径list


//编号 1
	public void setR_XLA_S1LA() {

		road = new RoadList();
		road.setStartName("XLA");
		road.setEndName("S1LA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R    = new String[] {    "X",                 "I-2",                    "15/17",                     "1/3_5/7",                       "9",                       "I-1",                      "I"               };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule_s,  ParamFlag.turnout_dw_bule_s,  ParamFlag.turnout_dw_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule  };
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green_s, ParamFlag.turnout_dw_green_s, ParamFlag.turnout_dw_green, ParamFlag.trackline_green, ParamFlag.trackline_green };
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red_s,   ParamFlag.turnout_dw_red_s,   ParamFlag.turnout_dw_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red   };

		setArrayR(R, R_blue, R_green, R_red);	
	}
//编号 2
	public void setR_XLA_S2LA() {

		road = new RoadList();
		road.setStartName("XLA");
		road.setEndName("S2LA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R    = new String[] {     "X",                "I-2",                    "15/17",                     "1/3_5/7",                      "11",                      "II-1",                       "II" };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_dw_bule_x,  ParamFlag.turnout_dw_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule}  ;
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_fw_green, ParamFlag.turnout_dw_green_x, ParamFlag.turnout_dw_green, ParamFlag.trackline_green, ParamFlag.trackline_green} ;
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_dw_red_x,   ParamFlag.turnout_dw_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red}   ;

		setArrayR(R, R_blue, R_green, R_red);	
	}
//编号 3
	public void setR_XLA_BA_S2LA() {

		road = new RoadList();
		road.setStartName("XLA");
		road.setMidName("BA");
		road.setEndName("S2LA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R    = new String[] {     "X",                "I-2",                    "15/17",                     "1/3_5/7",                      "11",                      "II-1",                       "II" };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule_s,  ParamFlag.turnout_fw_bule_l,  ParamFlag.turnout_dw_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule  };
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green_s, ParamFlag.turnout_fw_green_l, ParamFlag.turnout_dw_green, ParamFlag.trackline_green, ParamFlag.trackline_green };
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red_s,   ParamFlag.turnout_fw_red_l,   ParamFlag.turnout_dw_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red   };

		setArrayR(R, R_blue, R_green, R_red);	
	}
//编号  4	
	public void setR_XLA_S3LA() {

		road = new RoadList();
		road.setStartName("XLA");
		road.setEndName("S3LA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R    = new String[] {    "X",                 "I-2",                    "15/17",                     "1/3_5/7",                       "9",                       "3-1",                      "3" };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule_s,  ParamFlag.turnout_dw_bule_s,  ParamFlag.turnout_fw_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule}  ;
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green_s, ParamFlag.turnout_dw_green_s, ParamFlag.turnout_fw_green, ParamFlag.trackline_green, ParamFlag.trackline_green} ;
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red_s,   ParamFlag.turnout_dw_red_s,   ParamFlag.turnout_fw_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red}   ;

		setArrayR(R, R_blue, R_green, R_red);	
	}
//编号 5 
	public void setR_XLA_S4LA() {

		road = new RoadList();
		road.setStartName("XLA");
		road.setEndName("S4LA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R    = new String[] {     "X",                "I-2",                    "15/17",                     "1/3_5/7",                      "11",                      "13",                     "4-1",                       "4" };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_dw_bule_x,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_dw_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule  };
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_fw_green, ParamFlag.turnout_dw_green_x, ParamFlag.turnout_fw_green, ParamFlag.turnout_dw_green, ParamFlag.trackline_green, ParamFlag.trackline_green };
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_dw_red_x,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_dw_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red   };

		setArrayR(R, R_blue, R_green, R_red);	
	}
//编号 6 
	public void setR_XLA_BA_S4LA() {

		road = new RoadList();
		road.setStartName("XLA");
		road.setMidName("BA");
		road.setEndName("S4LA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R    = new String[] {     "X",                "I-2",                    "15/17",                     "1/3_5/7",                      "11",                      "13",                     "4-1",                       "4" };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule_s,  ParamFlag.turnout_fw_bule_l,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_dw_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule  };
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green_s, ParamFlag.turnout_fw_green_l, ParamFlag.turnout_fw_green, ParamFlag.turnout_dw_green, ParamFlag.trackline_green, ParamFlag.trackline_green };
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red_s,   ParamFlag.turnout_fw_red_l,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_dw_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red   };

		setArrayR(R, R_blue, R_green, R_red);	
	}
//编号 7	
	public void setR_XLA_S6LA() {

		road = new RoadList();
		road.setStartName("XLA");
		road.setEndName("S6LA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R    = new String[] {     "X",                "I-2",                    "15/17",                     "1/3_5/7",                      "11",                      "13",                     "6-1",                       "6" };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_dw_bule_x,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_fw_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule  };
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_fw_green, ParamFlag.turnout_dw_green_x, ParamFlag.turnout_fw_green, ParamFlag.turnout_fw_green, ParamFlag.trackline_green, ParamFlag.trackline_green };
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_dw_red_x,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_fw_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red   };

		setArrayR(R, R_blue, R_green, R_red);	
	}
//编号 8 
	public void setR_XLA_BA_S6LA() {

		road = new RoadList();
		road.setStartName("XLA");
		road.setMidName("BA");
		road.setEndName("S6LA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R    = new String[] {     "X",                "I-2",                    "15/17",                     "1/3_5/7",                      "11",                      "13",                     "6-1",                       "6" };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule_s,  ParamFlag.turnout_fw_bule_l,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_fw_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule  };
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green_s, ParamFlag.turnout_fw_green_l, ParamFlag.turnout_fw_green, ParamFlag.turnout_fw_green, ParamFlag.trackline_green, ParamFlag.trackline_green };
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red_s,   ParamFlag.turnout_fw_red_l,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_fw_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red   };

		setArrayR(R, R_blue, R_green, R_red);	
	}
//编号 9	
	public void setR_X1LA_SLFA() {

		road = new RoadList();
		road.setStartName("X1LA");
		road.setEndName("SLFA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R    = new String[] {     "X1",                "I+1",                    "10",                        "14/16",                    "I+2",                       "18/20",                      "I+3",                       "I+4" };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule,   ParamFlag.turnout_dw_bule_s,  ParamFlag.trackline_bule,   ParamFlag.turnout_dw_bule_s,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule  };
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green,  ParamFlag.turnout_dw_green_s, ParamFlag.trackline_green,  ParamFlag.turnout_dw_green_s, ParamFlag.trackline_green, ParamFlag.trackline_green };
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red,    ParamFlag.turnout_dw_red_s,   ParamFlag.trackline_red,    ParamFlag.turnout_dw_red_s,   ParamFlag.trackline_red,   ParamFlag.trackline_red   };

		setArrayR(R, R_blue, R_green, R_red);	
	}
//编号 10 
	public void setR_X2LA_SLFA() {

		road = new RoadList();
		road.setStartName("X2LA");
		road.setEndName("SLFA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R    = new String[] {     "X2",                "II+1",                    "II+2",                    "14/16",                    "2/4_6/8",                       "18/20",                      "I+3",                       "I+4" };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule_x,  ParamFlag.turnout_dw_bule_s,  ParamFlag.turnout_fw_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule  };
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green_x, ParamFlag.turnout_dw_green_s, ParamFlag.turnout_fw_green, ParamFlag.trackline_green, ParamFlag.trackline_green };
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red_x,   ParamFlag.turnout_dw_red_s,   ParamFlag.turnout_fw_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red   };

		setArrayR(R, R_blue, R_green, R_red);	
	}
//编号 11 
	public void setR_X3LA_SLFA() {

		road = new RoadList();
		road.setStartName("X3LA");
		road.setEndName("SLFA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R    = new String[] {     "X3",                "3+1",                    "10",                        "14/16",                    "I+2",                       "18/20",                      "I+3",                       "I+4" };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_fw_bule,   ParamFlag.turnout_dw_bule_s,  ParamFlag.trackline_bule,   ParamFlag.turnout_dw_bule_s,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule  };
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_fw_green,  ParamFlag.turnout_dw_green_s, ParamFlag.trackline_green,  ParamFlag.turnout_dw_green_s, ParamFlag.trackline_green, ParamFlag.trackline_green };
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_fw_red,    ParamFlag.turnout_dw_red_s,   ParamFlag.trackline_red,    ParamFlag.turnout_dw_red_s,   ParamFlag.trackline_red,   ParamFlag.trackline_red   };

		setArrayR(R, R_blue, R_green, R_red);	
	}
//编号 12 
	public void setR_X4LA_SLFA() {

		road = new RoadList();
		road.setStartName("X4LA");
		road.setEndName("SLFA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R    = new String[] {     "X4",                "4+1",                    "12",                     "2/4_6/8",                       "18/20",                      "I+3",                       "I+4" };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule,  ParamFlag.turnout_fw_bule_r,  ParamFlag.turnout_fw_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule  };
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green, ParamFlag.turnout_fw_green_r, ParamFlag.turnout_fw_green, ParamFlag.trackline_green, ParamFlag.trackline_green };
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red,   ParamFlag.turnout_fw_red_r,   ParamFlag.turnout_fw_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red   };

		setArrayR(R, R_blue, R_green, R_red);	
	}
//编号 13 
	public void setR_X6LA_SLFA() {

		road = new RoadList();
		road.setStartName("X6LA");
		road.setEndName("SLFA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R    = new String[] {     "X6",                "6+1",                    "12",                     "2/4_6/8",                       "18/20",                      "I+3",                       "I+4" };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_fw_bule_r,  ParamFlag.turnout_fw_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule  };
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_fw_green, ParamFlag.turnout_fw_green_r, ParamFlag.turnout_fw_green, ParamFlag.trackline_green, ParamFlag.trackline_green };
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_fw_red_r,   ParamFlag.turnout_fw_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red   };

		setArrayR(R, R_blue, R_green, R_red);	
	}
//编号 14 
	public void setR_XTA_SLFA() {

		road = new RoadList();
		road.setStartName("XTA");
		road.setEndName("SLFA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R    = new String[] {    "X",                 "X1",                      "I-2",                    "15/17",                     "1/3_5/7",                       "9",                       "I-1",                      "I",                       "I+1",                    "10",                        "14/16",                    "I+2",                       "18/20",                      "I+3",                       "I+4"  };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule_s,  ParamFlag.turnout_dw_bule_s,  ParamFlag.turnout_dw_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule,   ParamFlag.turnout_dw_bule_s,  ParamFlag.trackline_bule,   ParamFlag.turnout_dw_bule_s,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule  };
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green_s, ParamFlag.turnout_dw_green_s, ParamFlag.turnout_dw_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green,  ParamFlag.turnout_dw_green_s, ParamFlag.trackline_green,  ParamFlag.turnout_dw_green_s, ParamFlag.trackline_green, ParamFlag.trackline_green };
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red_s,   ParamFlag.turnout_dw_red_s,   ParamFlag.turnout_dw_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red,    ParamFlag.turnout_dw_red_s,   ParamFlag.trackline_red,    ParamFlag.turnout_dw_red_s,   ParamFlag.trackline_red,   ParamFlag.trackline_red   };

		setArrayR(R, R_blue, R_green, R_red);	
	}
//编号 15
	public void setR_SLA_X1LA() {

		road = new RoadList();
		road.setStartName("SLA");
		road.setEndName("X1LA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R    = new String[] {     "S",                "II+4",                    "II+3",                    "18/20",                     "2/4_6/8",                       "14/16",                     "10",                      "I+1",                       "I" };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule_x,  ParamFlag.turnout_dw_bule_s,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_dw_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule  };
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green_x, ParamFlag.turnout_dw_green_s, ParamFlag.turnout_fw_green, ParamFlag.turnout_dw_green, ParamFlag.trackline_green, ParamFlag.trackline_green };
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red_x,   ParamFlag.turnout_dw_red_s,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_dw_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red   };

		setArrayR(R, R_blue, R_green, R_red);	
	}
//编号 16 
	public void setR_SLA_X2LA() {
		
		road = new RoadList();
		road.setStartName("SLA");
		road.setEndName("X2LA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R    = new String[] {     "S",                "II+4",                    "II+3",                    "18/20",                     "2/4_6/8",                       "14/16",                     "II+2",                      "II+1",                       "II" };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule_x,  ParamFlag.turnout_dw_bule_s,  ParamFlag.turnout_dw_bule_x,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule  };
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green_x, ParamFlag.turnout_dw_green_s, ParamFlag.turnout_dw_green_x, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green };
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red_x,   ParamFlag.turnout_dw_red_s,   ParamFlag.turnout_dw_red_x,   ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red   };

		setArrayR(R, R_blue, R_green, R_red);	
	}
//编号 17 
	public void setR_SLA_X3LA() {
		
		road = new RoadList();
		road.setStartName("SLA");
		road.setEndName("X3LA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R    = new String[] {     "S",                "II+4",                    "II+3",                    "18/20",                     "2/4_6/8",                       "14/16",                     "10",                      "3+1",                       "3" };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule_x,  ParamFlag.turnout_dw_bule_s,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_fw_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule  };
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green_x, ParamFlag.turnout_dw_green_s, ParamFlag.turnout_fw_green, ParamFlag.turnout_fw_green, ParamFlag.trackline_green, ParamFlag.trackline_green };
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red_x,   ParamFlag.turnout_dw_red_s,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_fw_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red   };

		setArrayR(R, R_blue, R_green, R_red);	
	}
//编号 18 
	public void setR_SLA_X4LA() {
		
		road = new RoadList();
		road.setStartName("SLA");
		road.setEndName("X4LA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R    = new String[] {     "S",                "II+4",                    "II+3",                    "18/20",                     "2/4_6/8",                       "12",                      "4+1",                      "4"              };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule_x,  ParamFlag.turnout_fw_bule_r,  ParamFlag.turnout_dw_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule  };
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green_x, ParamFlag.turnout_fw_green_r, ParamFlag.turnout_dw_green, ParamFlag.trackline_green, ParamFlag.trackline_green };
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red_x,   ParamFlag.turnout_fw_red_r,   ParamFlag.turnout_dw_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red,  };

		setArrayR(R, R_blue, R_green, R_red);	
	}
//编号 19 
	public void setR_SLA_X6LA() {

		road = new RoadList();
		road.setStartName("SLA");
		road.setEndName("X6LA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R    = new String[] {     "S",                "II+4",                    "II+3",                    "18/20",                     "2/4_6/8",                       "12",                      "6+1",                      "6"              };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule_x,  ParamFlag.turnout_fw_bule_r,  ParamFlag.turnout_fw_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule  };
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green_x, ParamFlag.turnout_fw_green_r, ParamFlag.turnout_fw_green, ParamFlag.trackline_green, ParamFlag.trackline_green };
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red_x,   ParamFlag.turnout_fw_red_r,   ParamFlag.turnout_fw_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red,  };

		setArrayR(R, R_blue, R_green, R_red);	
	}
//编号 20 
	public void setR_S1LA_XLFA() {

		road = new RoadList();
		road.setStartName("S1LA");
		road.setEndName("XLFA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R    = new String[] {    "S1",                 "I-1",                    "9",                     "1/3_5/7",                       "15/17",                       "II-2"            };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule,  ParamFlag.turnout_fw_bule_r,  ParamFlag.turnout_dw_bule_x,  ParamFlag.trackline_bule  };
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green, ParamFlag.turnout_fw_green_r, ParamFlag.turnout_dw_green_x, ParamFlag.trackline_green };
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red,   ParamFlag.turnout_fw_red_r,   ParamFlag.turnout_dw_red_x,   ParamFlag.trackline_red   };

		setArrayR(R, R_blue, R_green, R_red);	
	}
//编号 21 
	public void setR_S2LA_XLFA() {
		
		road = new RoadList();
		road.setStartName("S2LA");
		road.setEndName("XLFA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R    = new String[] {    "S2",                 "II-1",                    "11",                     "1/3_5/7",                       "15/17",                       "II-2"          };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule,  ParamFlag.turnout_dw_bule_x,  ParamFlag.turnout_dw_bule_x,  ParamFlag.trackline_bule  };
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green, ParamFlag.turnout_dw_green_x, ParamFlag.turnout_dw_green_x, ParamFlag.trackline_green };
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red,   ParamFlag.turnout_dw_red_x,   ParamFlag.turnout_dw_red_x,   ParamFlag.trackline_red   };

		setArrayR(R, R_blue, R_green, R_red);	
	}
//编号 22 
	public void setR_S3LA_XLFA() {
		
		road = new RoadList();
		road.setStartName("S3LA");
		road.setEndName("XLFA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R    = new String[] {    "S3",                 "3-1",                    "9",                     "1/3_5/7",                       "15/17",                       "II-2"            };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_fw_bule_r,  ParamFlag.turnout_dw_bule_x,  ParamFlag.trackline_bule  };
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_fw_green, ParamFlag.turnout_fw_green_r, ParamFlag.turnout_dw_green_x, ParamFlag.trackline_green };
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_fw_red_r,   ParamFlag.turnout_dw_red_x,   ParamFlag.trackline_red   };

		setArrayR(R, R_blue, R_green, R_red);	
	}
//编号 23 
	public void setR_S4LA_XLFA() {
		
		road = new RoadList();
		road.setStartName("S4LA");
		road.setEndName("XLFA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R    = new String[] {    "S4",                 "4-1",                    "13",                         "11",                     "1/3_5/7",                       "15/17",                       "II-2"          };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_dw_bule_x,  ParamFlag.turnout_dw_bule_x,  ParamFlag.trackline_bule  };
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green, ParamFlag.turnout_fw_green, ParamFlag.turnout_dw_green_x, ParamFlag.turnout_dw_green_x, ParamFlag.trackline_green };
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_dw_red_x,   ParamFlag.turnout_dw_red_x,   ParamFlag.trackline_red   };

		setArrayR(R, R_blue, R_green, R_red);	
	}
//编号 24 
	public void setR_S6LA_XLFA() {
		
		road = new RoadList();
		road.setStartName("S6LA");
		road.setEndName("XLFA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R    = new String[] {    "S6",                 "6-1",                    "13",                         "11",                     "1/3_5/7",                       "15/17",                       "II-2"          };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_dw_bule_x,  ParamFlag.turnout_dw_bule_x,  ParamFlag.trackline_bule  };
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_fw_green, ParamFlag.turnout_fw_green, ParamFlag.turnout_dw_green_x, ParamFlag.turnout_dw_green_x, ParamFlag.trackline_green };
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_dw_red_x,   ParamFlag.turnout_dw_red_x,   ParamFlag.trackline_red   };

		setArrayR(R, R_blue, R_green, R_red);	
	}
//编号 25 
	public void setR_STA_XLFA() {

		road = new RoadList();
		road.setStartName("STA");
		road.setEndName("XLFA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R    = new String[] {     "S",                 "S1",                 "II+4",                    "II+3",                    "18/20",                     "2/4_6/8",                       "14/16",                     "II+2",                      "II+1",               "II",                    "II-1",                       "11",                     "1/3_5/7",                    "15/17",                     "II-2"              };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule_x,  ParamFlag.turnout_dw_bule_s,  ParamFlag.turnout_dw_bule_x,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule,  ParamFlag.turnout_dw_bule_x,  ParamFlag.turnout_dw_bule_x,  ParamFlag.trackline_bule  };
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green_x, ParamFlag.turnout_dw_green_s, ParamFlag.turnout_dw_green_x, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green, ParamFlag.turnout_dw_green_x, ParamFlag.turnout_dw_green_x, ParamFlag.trackline_green };
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red_x,   ParamFlag.turnout_dw_red_s,   ParamFlag.turnout_dw_red_x,   ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red,   ParamFlag.turnout_dw_red_x,   ParamFlag.turnout_dw_red_x,   ParamFlag.trackline_red   };

		setArrayR(R, R_blue, R_green, R_red);		
	}
//编号 26
	public void setR_D11A_S1DA() {

		road = new RoadList();
		road.setStartName("D11A");
		road.setEndName("S1DA");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "D11",                 "15/17",                     "1/3_5/7",                       "9",                       "I-1",                      "I"               };
		int[] R_blue  = new int[] {ParamFlag.sep_blue, ParamFlag.turnout_dw_bule_s,  ParamFlag.turnout_dw_bule_s,  ParamFlag.turnout_dw_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule  };
		int[] R_green = new int[] {ParamFlag.sep_white, ParamFlag.turnout_dw_green_s, ParamFlag.turnout_dw_green_s, ParamFlag.turnout_dw_green, ParamFlag.trackline_green, ParamFlag.trackline_green };
		int[] R_red   = new int[] {ParamFlag.sep_blue,   ParamFlag.turnout_dw_red_s,   ParamFlag.turnout_dw_red_s,   ParamFlag.turnout_dw_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red   };

		setArrayR(R, R_blue, R_green, R_red);
	}	
//编号 27
	public void setR_D11A_S2DA() {

		road = new RoadList();
		road.setStartName("D11A");
		road.setEndName("S2DA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		
		String[] R    = new String[] {     "D11",               "15/17",                     "1/3_5/7",                      "11",                      "II-1",                       "II" };
		int[] R_blue  = new int[] {ParamFlag.sep_blue, ParamFlag.turnout_fw_bule,  ParamFlag.turnout_dw_bule_x,  ParamFlag.turnout_dw_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule}  ;
		int[] R_green = new int[] {ParamFlag.sep_white, ParamFlag.turnout_fw_green, ParamFlag.turnout_dw_green_x, ParamFlag.turnout_dw_green, ParamFlag.trackline_green, ParamFlag.trackline_green} ;
		int[] R_red   = new int[] {ParamFlag.sep_blue,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_dw_red_x,   ParamFlag.turnout_dw_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red}   ;

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 28
	public void setR_D11A_BA_S2DA() {

		road = new RoadList();
		road.setStartName("D11A");
		road.setMidName("BA");
		road.setEndName("S2DA");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {     "D11",                 "15/17",                     "1/3_5/7",                      "11",                      "II-1",                       "II" };
		int[] R_blue  = new int[] {ParamFlag.sep_green, ParamFlag.turnout_dw_bule_s,  ParamFlag.turnout_fw_bule_l,  ParamFlag.turnout_dw_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule  };
		int[] R_green = new int[] {ParamFlag.sep_white, ParamFlag.turnout_dw_green_s, ParamFlag.turnout_fw_green_l, ParamFlag.turnout_dw_green, ParamFlag.trackline_green, ParamFlag.trackline_green };
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.turnout_dw_red_s,   ParamFlag.turnout_fw_red_l,   ParamFlag.turnout_dw_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red   };

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 29
	public void setR_D11A_S3DA() {

		road = new RoadList();
		road.setStartName("D11A");
		road.setEndName("S3DA");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "D11",                "15/17",                     "1/3_5/7",                       "9",                       "3-1",                      "3" };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.turnout_dw_bule_s,  ParamFlag.turnout_dw_bule_s,  ParamFlag.turnout_fw_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule}  ;
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.turnout_dw_green_s, ParamFlag.turnout_dw_green_s, ParamFlag.turnout_fw_green, ParamFlag.trackline_green, ParamFlag.trackline_green} ;
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.turnout_dw_red_s,   ParamFlag.turnout_dw_red_s,   ParamFlag.turnout_fw_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red}   ;

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 30
	public void setR_D11A_S4DA() {

		road = new RoadList();
		road.setStartName("D11A");
		road.setEndName("S4DA");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {     "D11",               "15/17",                     "1/3_5/7",                      "11",                      "13",                     "4-1",                       "4" };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.turnout_fw_bule,  ParamFlag.turnout_dw_bule_x,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_dw_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule  };
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.turnout_fw_green, ParamFlag.turnout_dw_green_x, ParamFlag.turnout_fw_green, ParamFlag.turnout_dw_green, ParamFlag.trackline_green, ParamFlag.trackline_green };
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_dw_red_x,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_dw_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red   };

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 31
	public void setR_D11A_BA_S4DA() {

		road = new RoadList();
		road.setStartName("D11A");
		road.setMidName("BA");
		road.setEndName("S4DA");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {     "D11",                "15/17",                     "1/3_5/7",                      "11",                      "13",                     "4-1",                       "4" };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.turnout_dw_bule_s,  ParamFlag.turnout_fw_bule_l,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_dw_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule  };
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.turnout_dw_green_s, ParamFlag.turnout_fw_green_l, ParamFlag.turnout_fw_green, ParamFlag.turnout_dw_green, ParamFlag.trackline_green, ParamFlag.trackline_green };
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.turnout_dw_red_s,   ParamFlag.turnout_fw_red_l,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_dw_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red   };

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 32
	public void setR_D11A_S6DA() {

		road = new RoadList();
		road.setStartName("D11A");
		road.setEndName("S6DA");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {     "D11",                "15/17",                     "1/3_5/7",                      "11",                      "13",                     "6-1",                       "6" };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.turnout_fw_bule,  ParamFlag.turnout_dw_bule_x,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_fw_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule  };
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.turnout_fw_green, ParamFlag.turnout_dw_green_x, ParamFlag.turnout_fw_green, ParamFlag.turnout_fw_green, ParamFlag.trackline_green, ParamFlag.trackline_green };
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_dw_red_x,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_fw_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red   };

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 33
	public void setR_D11A_BA_S6DA() {

		road = new RoadList();
		road.setStartName("D11A");
		road.setMidName("BA");
		road.setEndName("S6DA");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {     "D11",                "15/17",                     "1/3_5/7",                      "11",                      "13",                     "6-1",                       "6" };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.turnout_dw_bule_s,  ParamFlag.turnout_fw_bule_l,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_fw_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule  };
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.turnout_dw_green_s, ParamFlag.turnout_fw_green_l, ParamFlag.turnout_fw_green, ParamFlag.turnout_fw_green, ParamFlag.trackline_green, ParamFlag.trackline_green };
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.turnout_dw_red_s,   ParamFlag.turnout_fw_red_l,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_fw_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red   };

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 34
	public void setR_D21A_S1DA() {

		road = new RoadList();
		road.setStartName("D21A");
		road.setEndName("S1DA");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "D21",                 "15/17",                    "1/3_5/7",                       "9",                       "I-1"            ,            "I"           };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.turnout_dw_bule_x,  ParamFlag.turnout_fw_bule_r,  ParamFlag.turnout_dw_bule,  ParamFlag.trackline_bule  ,  ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.turnout_dw_green_x, ParamFlag.turnout_fw_green_r, ParamFlag.turnout_dw_green, ParamFlag.trackline_green ,  ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.turnout_dw_red_x,   ParamFlag.turnout_fw_red_r,   ParamFlag.turnout_dw_red,   ParamFlag.trackline_red   ,  ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 35
	public void setR_D21A_S2DA() {

		road = new RoadList();
		road.setStartName("D21A");
		road.setEndName("S2DA");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "D21",                 "15/17",                    "1/3_5/7",                       "11",                       "II-1"            ,          "II"          };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.turnout_dw_bule_x,  ParamFlag.turnout_dw_bule_x,  ParamFlag.turnout_dw_bule,  ParamFlag.trackline_bule  ,  ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.turnout_dw_green_x, ParamFlag.turnout_dw_green_x, ParamFlag.turnout_dw_green, ParamFlag.trackline_green ,  ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.turnout_dw_red_x,   ParamFlag.turnout_dw_red_x,   ParamFlag.turnout_dw_red,   ParamFlag.trackline_red   ,  ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 36
	public void setR_D21A_S3DA() {

		road = new RoadList();
		road.setStartName("D21A");
		road.setEndName("S3DA");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "D21",                 "15/17",                    "1/3_5/7",                       "9",                       "3-1"            ,          "3"          };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.turnout_dw_bule_x,  ParamFlag.turnout_fw_bule_r,  ParamFlag.turnout_fw_bule,  ParamFlag.trackline_bule  ,  ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.turnout_dw_green_x, ParamFlag.turnout_fw_green_r, ParamFlag.turnout_fw_green, ParamFlag.trackline_green ,  ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.turnout_dw_red_x,   ParamFlag.turnout_fw_red_r,   ParamFlag.turnout_fw_red,   ParamFlag.trackline_red   ,  ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}
	//编号 37
	public void setR_D21A_S4DA() {

		road = new RoadList();
		road.setStartName("D21A");
		road.setEndName("S4DA");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "D21",                 "15/17",                    "1/3_5/7",                       "11",                      "13",                        "4-1"            ,             "4"          };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.turnout_dw_bule_x,  ParamFlag.turnout_dw_bule_x,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_dw_bule,   ParamFlag.trackline_bule  ,  ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.turnout_dw_green_x, ParamFlag.turnout_dw_green_x, ParamFlag.turnout_fw_green, ParamFlag.turnout_dw_green,  ParamFlag.trackline_green ,  ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.turnout_dw_red_x,   ParamFlag.turnout_dw_red_x,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_dw_red,    ParamFlag.trackline_red   ,  ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 38
	public void setR_D21A_S6DA() {

		road = new RoadList();
		road.setStartName("D21A");
		road.setEndName("S6DA");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "D21",                 "15/17",                    "1/3_5/7",                       "11",                      "13",                        "6-1",                    "6"          };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.turnout_dw_bule_x,  ParamFlag.turnout_dw_bule_x,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_fw_bule,   ParamFlag.trackline_bule,  ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.turnout_dw_green_x, ParamFlag.turnout_dw_green_x, ParamFlag.turnout_fw_green, ParamFlag.turnout_fw_green,  ParamFlag.trackline_green, ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.turnout_dw_red_x,   ParamFlag.turnout_dw_red_x,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_fw_red,    ParamFlag.trackline_red,   ParamFlag.trackline_red};
		
		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 39
	public void setR_S1DA_D11A() {

		road = new RoadList();
		road.setStartName("S1DA");
		road.setEndName("D11A");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "S1",                 "I-1",                       "9",                         "1/3_5/7",                      "15/17",                        "I-2"            };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule,   ParamFlag.turnout_dw_bule_s,   ParamFlag.turnout_dw_bule_s,   ParamFlag.trackline_bule  };
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green,  ParamFlag.turnout_dw_green_s,  ParamFlag.turnout_dw_green_s,  ParamFlag.trackline_green };
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red,    ParamFlag.turnout_dw_red_s,    ParamFlag.turnout_dw_red_s,    ParamFlag.trackline_red   };
		
		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 40
	public void setR_S1DA_D21A() {

		road = new RoadList();
		road.setStartName("S1DA");
		road.setEndName("D21A");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "S1",                 "I-1",                       "9",                         "1/3_5/7",                      "15/17",                        "II-2"            };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule,   ParamFlag.turnout_fw_bule_r,   ParamFlag.turnout_dw_bule_x,   ParamFlag.trackline_bule  };
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green,  ParamFlag.turnout_fw_green_r,  ParamFlag.turnout_dw_green_x,  ParamFlag.trackline_green };
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red,    ParamFlag.turnout_fw_red_r,    ParamFlag.turnout_dw_red_x,    ParamFlag.trackline_red   };
		
		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 41
	public void setR_S2DA_D11A() {

		road = new RoadList();
		road.setStartName("S2DA");
		road.setEndName("D11A");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "S2",                 "II-1",                       "11",                         "1/3_5/7",                      "15/17",                        "I-2"            };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule,   ParamFlag.turnout_fw_bule_l,   ParamFlag.turnout_dw_bule_s,   ParamFlag.trackline_bule  };
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green,  ParamFlag.turnout_fw_green_l,  ParamFlag.turnout_dw_green_s,  ParamFlag.trackline_green };
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red,    ParamFlag.turnout_fw_red_l,    ParamFlag.turnout_dw_red_s,    ParamFlag.trackline_red   };
		
		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 42
	public void setR_S2DA_BA_D11A() {

		road = new RoadList();
		road.setStartName("S2DA");
		road.setMidName("BA");
		road.setEndName("D11A");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "S2",                 "II-1",                       "11",                     "1/3_5/7",                      "15/17",                      "I-2"            };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule,   ParamFlag.turnout_dw_bule_x,   ParamFlag.turnout_fw_bule,     ParamFlag.trackline_bule  };
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green,  ParamFlag.turnout_dw_green_x,  ParamFlag.turnout_fw_green,    ParamFlag.trackline_green };
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red,    ParamFlag.turnout_dw_red_x,    ParamFlag.turnout_fw_red,      ParamFlag.trackline_red   };
		
		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 43
	public void setR_S2DA_D21A() {

		road = new RoadList();
		road.setStartName("S2DA");
		road.setEndName("D21A");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "S2",                 "II-1",                       "11",                     "1/3_5/7",                      "15/17",                      "II-2"            };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule,   ParamFlag.turnout_dw_bule_x,   ParamFlag.turnout_dw_bule_x,     ParamFlag.trackline_bule  };
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green,  ParamFlag.turnout_dw_green_x,  ParamFlag.turnout_dw_green_x,    ParamFlag.trackline_green };
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red,    ParamFlag.turnout_dw_red_x,    ParamFlag.turnout_dw_red_x,      ParamFlag.trackline_red   };
		
		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 44
	public void setR_S3DA_D11A() {

		road = new RoadList();
		road.setStartName("S3DA");
		road.setEndName("D11A");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "S3",                 "3-1",                       "9",                     "1/3_5/7",                      "15/17",                      "I-2"            };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_fw_bule,   ParamFlag.turnout_dw_bule_s,   ParamFlag.turnout_dw_bule_s,     ParamFlag.trackline_bule  };
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_fw_green,  ParamFlag.turnout_dw_green_s,  ParamFlag.turnout_dw_green_s,    ParamFlag.trackline_green };
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_fw_red,    ParamFlag.turnout_dw_red_s,    ParamFlag.turnout_dw_red_s,      ParamFlag.trackline_red   };
		
		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 45
	public void setR_S3DA_D21A() {

		road = new RoadList();
		road.setStartName("S3DA");
		road.setEndName("D21A");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "S3",                 "3-1",                       "9",                     "1/3_5/7",                      "15/17",                      "II-2"            };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_fw_bule,   ParamFlag.turnout_fw_bule_r,   ParamFlag.turnout_dw_bule_x,     ParamFlag.trackline_bule  };
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_fw_green,  ParamFlag.turnout_fw_green_r,  ParamFlag.turnout_dw_green_x,    ParamFlag.trackline_green };
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_fw_red,    ParamFlag.turnout_fw_red_r,    ParamFlag.turnout_dw_red_x,      ParamFlag.trackline_red   };
		
		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 46
	public void setR_S4DA_D11A() {

		road = new RoadList();
		road.setStartName("S4DA");
		road.setEndName("D11A");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "S4",                 "4-1",                       "13",                         "11",                     "1/3_5/7",                      "15/17",                      "I-2"            };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule,  ParamFlag.turnout_fw_bule,   ParamFlag.turnout_dw_bule_x,   ParamFlag.turnout_fw_bule,     ParamFlag.trackline_bule  };
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green, ParamFlag.turnout_fw_green,  ParamFlag.turnout_dw_green_x,  ParamFlag.turnout_fw_green,    ParamFlag.trackline_green };
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red,   ParamFlag.turnout_fw_red,    ParamFlag.turnout_dw_red_x,    ParamFlag.turnout_fw_red,      ParamFlag.trackline_red   };
		
		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 47
	public void setR_S4DA_BA_D11A() {

		road = new RoadList();
		road.setStartName("S4DA");
		road.setMidName("BA");
		road.setEndName("D11A");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "S4",                 "4-1",                       "13",                         "11",                     "1/3_5/7",                      "15/17",                      "I-2"            };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule,  ParamFlag.turnout_fw_bule,   ParamFlag.turnout_fw_bule_l,   ParamFlag.turnout_dw_bule_s,     ParamFlag.trackline_bule  };
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green, ParamFlag.turnout_fw_green,  ParamFlag.turnout_fw_green_l,  ParamFlag.turnout_dw_green_s,    ParamFlag.trackline_green };
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red,   ParamFlag.turnout_fw_red,    ParamFlag.turnout_fw_red_l,    ParamFlag.turnout_dw_red_s,      ParamFlag.trackline_red   };
		
		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 48
	public void setR_S4DA_D21A() {

		road = new RoadList();
		road.setStartName("S4DA");
		road.setEndName("D21A");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "S4",                 "4-1",                       "13",                         "11",                     "1/3_5/7",                      "15/17",                      "II-2"            };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule,  ParamFlag.turnout_fw_bule,   ParamFlag.turnout_dw_bule_x,   ParamFlag.turnout_dw_bule_x,     ParamFlag.trackline_bule  };
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green, ParamFlag.turnout_fw_green,  ParamFlag.turnout_dw_green_x,  ParamFlag.turnout_dw_green_x,    ParamFlag.trackline_green };
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red,   ParamFlag.turnout_fw_red,    ParamFlag.turnout_dw_red_x,    ParamFlag.turnout_dw_red_x,      ParamFlag.trackline_red   };
		
		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 49
	public void setR_S6DA_D11A() {

		road = new RoadList();
		road.setStartName("S6DA");
		road.setEndName("D11A");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "S6",                 "6-1",                       "13",                         "11",                     "1/3_5/7",                      "15/17",                      "I-2"            };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_fw_bule,   ParamFlag.turnout_dw_bule_x,   ParamFlag.turnout_fw_bule,     ParamFlag.trackline_bule  };
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_fw_green, ParamFlag.turnout_fw_green,  ParamFlag.turnout_dw_green_x,  ParamFlag.turnout_fw_green,    ParamFlag.trackline_green };
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_fw_red,    ParamFlag.turnout_dw_red_x,    ParamFlag.turnout_fw_red,      ParamFlag.trackline_red   };
		
		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 50
	public void setR_S6DA_BA_D11A() {

		road = new RoadList();
		road.setStartName("S6DA");
		road.setMidName("BA");
		road.setEndName("D11A");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "S6",                 "6-1",                       "13",                         "11",                     "1/3_5/7",                      "15/17",                      "I-2"            };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_fw_bule,   ParamFlag.turnout_fw_bule_l,   ParamFlag.turnout_dw_bule_s,     ParamFlag.trackline_bule  };
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_fw_green, ParamFlag.turnout_fw_green,  ParamFlag.turnout_fw_green_l,  ParamFlag.turnout_dw_green_s,    ParamFlag.trackline_green };
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_fw_red,    ParamFlag.turnout_fw_red_l,    ParamFlag.turnout_dw_red_s,      ParamFlag.trackline_red   };
		
		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 51
	public void setR_S6DA_D21A() {

		road = new RoadList();
		road.setStartName("S6DA");
		road.setEndName("D21A");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "S6",                 "6-1",                       "13",                         "11",                     "1/3_5/7",                      "15/17",                      "II-2"            };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_fw_bule,   ParamFlag.turnout_dw_bule_x,   ParamFlag.turnout_dw_bule_x,     ParamFlag.trackline_bule  };
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_fw_green, ParamFlag.turnout_fw_green,  ParamFlag.turnout_dw_green_x,  ParamFlag.turnout_dw_green_x,    ParamFlag.trackline_green };
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_fw_red,    ParamFlag.turnout_dw_red_x,    ParamFlag.turnout_dw_red_x,      ParamFlag.trackline_red   };
		
		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 52
	public void setR_D51A_D31A() {

		road = new RoadList();
		road.setStartName("D51A");
		road.setEndName("D31A");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "D51",                 "5+1",                       "26",                         "3+2",      };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_fw_bule,  ParamFlag.trackline_bule };
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_fw_green, ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_fw_red,   ParamFlag.trackline_red  };
		
		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 53
	public void setR_X3DA_D31A() {

		road = new RoadList();
		road.setStartName("X3DA");
		road.setEndName("D31A");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "X3",                 "3+1",                       "26",                         "3+2",      };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule,  ParamFlag.trackline_bule };
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green, ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red,   ParamFlag.trackline_red  };

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 54
	public void setR_X3DA_D13A() {

		road = new RoadList();
		road.setStartName("X3DA");
		road.setEndName("D13A");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "X3",                 "3+1",                       "10",                     "14/16",                         "I+2",      };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_dw_bule_s,  ParamFlag.trackline_bule };
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_fw_green, ParamFlag.turnout_dw_green_s, ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_dw_red_s,   ParamFlag.trackline_red  };

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 55
	public void setR_X3DA_D14A() {

		road = new RoadList();
		road.setStartName("X3DA");
		road.setEndName("D14A");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "X3",                 "3+1",                       "10",                     "14/16",                         "I+2",                   "18/20",                         "I+3" ,                "I+4" };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_dw_bule_s,  ParamFlag.trackline_bule ,  ParamFlag.turnout_dw_bule_s,   ParamFlag.trackline_bule,   ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_fw_green, ParamFlag.turnout_dw_green_s, ParamFlag.trackline_green,  ParamFlag.turnout_dw_green_s,  ParamFlag.trackline_green,  ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_dw_red_s,   ParamFlag.trackline_red  ,  ParamFlag.turnout_dw_red_s,    ParamFlag.trackline_red,    ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 56
	public void setR_X3DA_D22A() {

		road = new RoadList();
		road.setStartName("X3DA");
		road.setEndName("D22A");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "X3",                 "3+1",                       "10",                     "14/16",                         "2/4_6/8",                   "18/20",                     "II+3" ,                "II+4" };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_dw_bule_s ,  ParamFlag.turnout_dw_bule_x,   ParamFlag.trackline_bule,   ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_fw_green, ParamFlag.turnout_fw_green, ParamFlag.turnout_dw_green_s,  ParamFlag.turnout_dw_green_x,  ParamFlag.trackline_green,  ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_dw_red_s  ,  ParamFlag.turnout_dw_red_x,    ParamFlag.trackline_red,    ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 57
	public void setR_X3DA_D41A() {

		road = new RoadList();
		road.setStartName("X3DA");
		road.setEndName("D41A");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "X3",                 "3+1",                       "10",                     "14/16",                         "2/4_6/8",                 "4+2" ,                 "4+3" };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_fw_bule_l ,  ParamFlag.trackline_bule,   ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_fw_green, ParamFlag.turnout_fw_green, ParamFlag.turnout_fw_green_l,  ParamFlag.trackline_green,  ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_fw_red_l  ,  ParamFlag.trackline_red,    ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 58
	public void setR_X3DA_D42A() {

		road = new RoadList();
		road.setStartName("X3DA");
		road.setEndName("D42A");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "X3",                 "3+1",                       "10",                     "14/16",                         "2/4_6/8",                 "4+2" ,                 "4+3" ,                         "4+4"         };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_fw_bule_l ,  ParamFlag.trackline_bule,   ParamFlag.trackline_bule,   ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_fw_green, ParamFlag.turnout_fw_green, ParamFlag.turnout_fw_green_l,  ParamFlag.trackline_green,  ParamFlag.trackline_green,  ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_fw_red_l  ,  ParamFlag.trackline_red,    ParamFlag.trackline_red,    ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 59
	public void setR_X3DA_D43A() {

		road = new RoadList();
		road.setStartName("X3DA");
		road.setEndName("D43A");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "X3",                 "3+1",                       "10",                     "14/16",                         "2/4_6/8",                 "4+2" ,                 "4+3" ,                         "4+4" ,                     "4+5"          };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_fw_bule_l ,  ParamFlag.trackline_bule,   ParamFlag.trackline_bule,   ParamFlag.trackline_bule,   ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_fw_green, ParamFlag.turnout_fw_green, ParamFlag.turnout_fw_green_l,  ParamFlag.trackline_green,  ParamFlag.trackline_green,  ParamFlag.trackline_green,  ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_fw_red_l  ,  ParamFlag.trackline_red,    ParamFlag.trackline_red,    ParamFlag.trackline_red,    ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 60
	public void setR_X1DA_D13A() {

		road = new RoadList();
		road.setStartName("X1DA");
		road.setEndName("D13A");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "X1",                 "I+1",                       "10",                     "14/16",                         "I+2",      };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule,  ParamFlag.turnout_dw_bule_s,  ParamFlag.trackline_bule };
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green, ParamFlag.turnout_dw_green_s, ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red,   ParamFlag.turnout_dw_red_s,   ParamFlag.trackline_red  };

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 61
	public void setR_X1DA_D14A() {

		road = new RoadList();
		road.setStartName("X1DA");
		road.setEndName("D14A");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "X1",                 "I+1",                       "10",                     "14/16",                         "I+2",                   "18/20",                         "I+3" ,                "I+4" };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule,  ParamFlag.turnout_dw_bule_s,  ParamFlag.trackline_bule ,  ParamFlag.turnout_dw_bule_s,   ParamFlag.trackline_bule,   ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green, ParamFlag.turnout_dw_green_s, ParamFlag.trackline_green,  ParamFlag.turnout_dw_green_s,  ParamFlag.trackline_green,  ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red,   ParamFlag.turnout_dw_red_s,   ParamFlag.trackline_red  ,  ParamFlag.turnout_dw_red_s,    ParamFlag.trackline_red,    ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 62
	public void setR_X1DA_D22A() {

		road = new RoadList();
		road.setStartName("X1DA");
		road.setEndName("D22A");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "X1",                 "I+1",                       "10",                     "14/16",                         "2/4_6/8",                   "18/20",                     "II+3" ,                "II+4" };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_dw_bule_s ,  ParamFlag.turnout_dw_bule_x,   ParamFlag.trackline_bule,   ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green, ParamFlag.turnout_fw_green, ParamFlag.turnout_dw_green_s,  ParamFlag.turnout_dw_green_x,  ParamFlag.trackline_green,  ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_dw_red_s  ,  ParamFlag.turnout_dw_red_x,    ParamFlag.trackline_red,    ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 63
	public void setR_X1DA_D41A() {

		road = new RoadList();
		road.setStartName("X1DA");
		road.setEndName("D41A");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "X1",                 "I+1",                       "10",                     "14/16",                         "2/4_6/8",                 "4+2" ,                 "4+3" };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_fw_bule_l ,  ParamFlag.trackline_bule,   ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green, ParamFlag.turnout_fw_green, ParamFlag.turnout_fw_green_l,  ParamFlag.trackline_green,  ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_fw_red_l  ,  ParamFlag.trackline_red,    ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 64
	public void setR_X1DA_D42A() {

		road = new RoadList();
		road.setStartName("X1DA");
		road.setEndName("D42A");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "X1",                 "I+1",                       "10",                     "14/16",                         "2/4_6/8",                 "4+2" ,                 "4+3" ,                         "4+4"         };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_fw_bule_l ,  ParamFlag.trackline_bule,   ParamFlag.trackline_bule,   ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green, ParamFlag.turnout_fw_green, ParamFlag.turnout_fw_green_l,  ParamFlag.trackline_green,  ParamFlag.trackline_green,  ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_fw_red_l  ,  ParamFlag.trackline_red,    ParamFlag.trackline_red,    ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 65
	public void setR_X1DA_D43A() {

		road = new RoadList();
		road.setStartName("X1DA");
		road.setEndName("D43A");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "X1",                 "I+1",                       "10",                     "14/16",                         "2/4_6/8",                 "4+2" ,                 "4+3" ,                         "4+4" ,                     "4+5"          };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_fw_bule_l ,  ParamFlag.trackline_bule,   ParamFlag.trackline_bule,   ParamFlag.trackline_bule,   ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green, ParamFlag.turnout_fw_green, ParamFlag.turnout_fw_green_l,  ParamFlag.trackline_green,  ParamFlag.trackline_green,  ParamFlag.trackline_green,  ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_fw_red_l  ,  ParamFlag.trackline_red,    ParamFlag.trackline_red,    ParamFlag.trackline_red,    ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 66
	public void setR_X2DA_D14A() {

		road = new RoadList();
		road.setStartName("X2DA");
		road.setEndName("D14A");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "X2",                 "II+1",                    "II+2",                     "14/16",                     "2/4_6/8",                       "18/20",                         "I+3" ,                "I+4" };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule_x,  ParamFlag.turnout_dw_bule_s ,  ParamFlag.turnout_fw_bule,   ParamFlag.trackline_bule,   ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green_x, ParamFlag.turnout_dw_green_s,  ParamFlag.turnout_fw_green,  ParamFlag.trackline_green,  ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red_x,   ParamFlag.turnout_dw_red_s  ,  ParamFlag.turnout_fw_red,    ParamFlag.trackline_red,    ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 67
	public void setR_X2DA_D22A() {

		road = new RoadList();
		road.setStartName("X2DA");
		road.setEndName("D22A");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "X2",                 "II+1",                    "II+2",                     "14/16",                     "2/4_6/8",                       "18/20",                         "II+3" ,                "II+4" };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule_x,  ParamFlag.turnout_dw_bule_s ,  ParamFlag.turnout_dw_bule_x,   ParamFlag.trackline_bule,   ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green_x, ParamFlag.turnout_dw_green_s,  ParamFlag.turnout_dw_green_x,  ParamFlag.trackline_green,  ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red_x,   ParamFlag.turnout_dw_red_s  ,  ParamFlag.turnout_dw_red_x,    ParamFlag.trackline_red,    ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 68
	public void setR_X2DA_D41A() {

		road = new RoadList();
		road.setStartName("X2DA");
		road.setEndName("D41A");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "X2",                 "II+1",                    "II+2",                     "14/16",                     "2/4_6/8",                        "4+2" ,                  "4+3" };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule_x,  ParamFlag.turnout_fw_bule_l ,  ParamFlag.trackline_bule,   ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green_x, ParamFlag.turnout_fw_green_l,  ParamFlag.trackline_green,  ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red_x,   ParamFlag.turnout_fw_red_l  ,  ParamFlag.trackline_red,    ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 69
	public void setR_X2DA_D42A() {

		road = new RoadList();
		road.setStartName("X2DA");
		road.setEndName("D42A");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "X2",                 "II+1",                    "II+2",                     "14/16",                     "2/4_6/8",                        "4+2" ,                  "4+3",                         "4+4" };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule_x,  ParamFlag.turnout_fw_bule_l ,  ParamFlag.trackline_bule,   ParamFlag.trackline_bule,   ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green_x, ParamFlag.turnout_fw_green_l,  ParamFlag.trackline_green,  ParamFlag.trackline_green,  ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red_x,   ParamFlag.turnout_fw_red_l  ,  ParamFlag.trackline_red,    ParamFlag.trackline_red,    ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 70
	public void setR_X2DA_D43A() {

		road = new RoadList();
		road.setStartName("X2DA");
		road.setEndName("D43A");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "X2",                 "II+1",                    "II+2",                     "14/16",                     "2/4_6/8",                        "4+2" ,                  "4+3",                         "4+4",                     "4+5"      };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule_x,  ParamFlag.turnout_fw_bule_l ,  ParamFlag.trackline_bule,   ParamFlag.trackline_bule,   ParamFlag.trackline_bule,   ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green_x, ParamFlag.turnout_fw_green_l,  ParamFlag.trackline_green,  ParamFlag.trackline_green,  ParamFlag.trackline_green,  ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red_x,   ParamFlag.turnout_fw_red_l  ,  ParamFlag.trackline_red,    ParamFlag.trackline_red,    ParamFlag.trackline_red,    ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}	
//编号 71
	public void setR_X4DA_D14A() {

		road = new RoadList();
		road.setStartName("X4DA");
		road.setEndName("D14A");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "X4",                 "4+1",                      "12",                     "2/4_6/8",                       "18/20",                         "I+3" ,                "I+4" };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule,  ParamFlag.turnout_fw_bule_r ,  ParamFlag.turnout_fw_bule,   ParamFlag.trackline_bule,   ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green, ParamFlag.turnout_fw_green_r,  ParamFlag.turnout_fw_green,  ParamFlag.trackline_green,  ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red,   ParamFlag.turnout_fw_red_r  ,  ParamFlag.turnout_fw_red,    ParamFlag.trackline_red,    ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 72
	public void setR_X4DA_D22A() {

		road = new RoadList();
		road.setStartName("X4DA");
		road.setEndName("D22A");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "X4",                 "4+1",                    "12",                     "2/4_6/8",                       "18/20",                         "II+3" ,                "II+4" };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule ,  ParamFlag.turnout_fw_bule_r,   ParamFlag.turnout_dw_bule_x,   ParamFlag.trackline_bule,   ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green,  ParamFlag.turnout_fw_green_r,  ParamFlag.turnout_dw_green_x,  ParamFlag.trackline_green,  ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red,    ParamFlag.turnout_fw_red_r,    ParamFlag.turnout_dw_red_x,    ParamFlag.trackline_red,    ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 73
	public void setR_X4DA_D41A() {

		road = new RoadList();
		road.setStartName("X4DA");
		road.setEndName("D41A");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "X4",                 "4+1",                    "12",                      "2/4_6/8",                        "4+2" ,                  "4+3" };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule,  ParamFlag.turnout_dw_bule_x ,  ParamFlag.trackline_bule,   ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green, ParamFlag.turnout_dw_green_x,  ParamFlag.trackline_green,  ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red,   ParamFlag.turnout_dw_red_x  ,  ParamFlag.trackline_red,    ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 74
	public void setR_X4DA_D42A() {

		road = new RoadList();
		road.setStartName("X4DA");
		road.setEndName("D42A");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "X4",                 "4+1",                    "12",                      "2/4_6/8",                        "4+2" ,                  "4+3" ,                         "4+4"};
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule,  ParamFlag.turnout_dw_bule_x ,  ParamFlag.trackline_bule,   ParamFlag.trackline_bule,   ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green, ParamFlag.turnout_dw_green_x,  ParamFlag.trackline_green,  ParamFlag.trackline_green,  ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red,   ParamFlag.turnout_dw_red_x  ,  ParamFlag.trackline_red,    ParamFlag.trackline_red,    ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 75
	public void setR_X4DA_D43A() {

		road = new RoadList();
		road.setStartName("X4DA");
		road.setEndName("D43A");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "X4",                 "4+1",                    "12",                      "2/4_6/8",                        "4+2" ,                  "4+3" ,                         "4+4" ,                   "4+5"          };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule,  ParamFlag.turnout_dw_bule_x ,  ParamFlag.trackline_bule,   ParamFlag.trackline_bule,   ParamFlag.trackline_bule,   ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green, ParamFlag.turnout_dw_green_x,  ParamFlag.trackline_green,  ParamFlag.trackline_green,  ParamFlag.trackline_green,  ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red,   ParamFlag.turnout_dw_red_x  ,  ParamFlag.trackline_red,    ParamFlag.trackline_red,    ParamFlag.trackline_red,    ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 76
	public void setR_X6DA_D14A() {

		road = new RoadList();
		road.setStartName("X6DA");
		road.setEndName("D14A");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "X6",                 "6+1",                      "12",                     "2/4_6/8",                       "18/20",                         "I+3" ,                "I+4" };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_fw_bule_r ,  ParamFlag.turnout_fw_bule,   ParamFlag.trackline_bule,   ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_fw_green, ParamFlag.turnout_fw_green_r,  ParamFlag.turnout_fw_green,  ParamFlag.trackline_green,  ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_fw_red_r  ,  ParamFlag.turnout_fw_red,    ParamFlag.trackline_red,    ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 77
	public void setR_X6DA_D22A() {

		road = new RoadList();
		road.setStartName("X6DA");
		road.setEndName("D22A");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "X6",                 "6+1",                    "12",                     "2/4_6/8",                       "18/20",                         "II+3" ,                "II+4" };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_fw_bule ,  ParamFlag.turnout_fw_bule_r,   ParamFlag.turnout_dw_bule_x,   ParamFlag.trackline_bule,   ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_fw_green,  ParamFlag.turnout_fw_green_r,  ParamFlag.turnout_dw_green_x,  ParamFlag.trackline_green,  ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_fw_red,    ParamFlag.turnout_fw_red_r,    ParamFlag.turnout_dw_red_x,    ParamFlag.trackline_red,    ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 78
	public void setR_X6DA_D41A() {

		road = new RoadList();
		road.setStartName("X6DA");
		road.setEndName("D41A");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "X6",                 "6+1",                    "12",                      "2/4_6/8",                        "4+2" ,                  "4+3" };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_dw_bule_x ,  ParamFlag.trackline_bule,   ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_fw_green, ParamFlag.turnout_dw_green_x,  ParamFlag.trackline_green,  ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_dw_red_x  ,  ParamFlag.trackline_red,    ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 79
	public void setR_X6DA_D42A() {

		road = new RoadList();
		road.setStartName("X6DA");
		road.setEndName("D42A");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "X6",                 "6+1",                    "12",                      "2/4_6/8",                        "4+2" ,                  "4+3" ,                         "4+4"};
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_dw_bule_x ,  ParamFlag.trackline_bule,   ParamFlag.trackline_bule,   ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_fw_green, ParamFlag.turnout_dw_green_x,  ParamFlag.trackline_green,  ParamFlag.trackline_green,  ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_dw_red_x  ,  ParamFlag.trackline_red,    ParamFlag.trackline_red,    ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 80
	public void setR_X6DA_D43A() {

		road = new RoadList();
		road.setStartName("X6DA");
		road.setEndName("D43A");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "X6",                 "6+1",                    "12",                      "2/4_6/8",                        "4+2" ,                  "4+3" ,                         "4+4" ,                   "4+5"          };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_dw_bule_x ,  ParamFlag.trackline_bule,   ParamFlag.trackline_bule,   ParamFlag.trackline_bule,   ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_fw_green, ParamFlag.turnout_dw_green_x,  ParamFlag.trackline_green,  ParamFlag.trackline_green,  ParamFlag.trackline_green,  ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_dw_red_x  ,  ParamFlag.trackline_red,    ParamFlag.trackline_red,    ParamFlag.trackline_red,    ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 81
	public void setR_D31A_X3DA() {

		road = new RoadList();
		road.setStartName("D31A");
		road.setEndName("X3DA");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "D31",                 "26",                      "3+1",                      "3"    };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.turnout_dw_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.turnout_dw_green, ParamFlag.trackline_green, ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.turnout_dw_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 82
	public void setR_D31A_D51A() {

		road = new RoadList();
		road.setStartName("D31A");
		road.setEndName("D51A");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "D31",               "26",                      "5+1",                      "5"    };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.turnout_fw_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.turnout_fw_green, ParamFlag.trackline_green, ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.turnout_fw_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 83
	public void setR_D12A_D14A() {

		road = new RoadList();
		road.setStartName("D12A");
		road.setEndName("D14A");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "D12",               "18/20",                      "I+3",                      "I+4"    };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.turnout_dw_bule_s,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.turnout_dw_green_s, ParamFlag.trackline_green, ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.turnout_dw_red_s,   ParamFlag.trackline_red,   ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 84
	public void setR_D13A_X1DA() {

		road = new RoadList();
		road.setStartName("D13A");
		road.setEndName("X1DA");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "D13",               "14/16",                       "10",                       "I+1",                      "I"    };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.turnout_dw_bule_s,  ParamFlag.turnout_dw_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.turnout_dw_green_s, ParamFlag.turnout_dw_green, ParamFlag.trackline_green, ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.turnout_dw_red_s,   ParamFlag.turnout_dw_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 85
	public void setR_D13A_X3DA() {

		road = new RoadList();
		road.setStartName("D13A");
		road.setEndName("X3DA");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "D13",               "14/16",                       "10",                       "3+1",                      "3"    };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.turnout_dw_bule_s,  ParamFlag.turnout_fw_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.turnout_dw_green_s, ParamFlag.turnout_fw_green, ParamFlag.trackline_green, ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.turnout_dw_red_s,   ParamFlag.turnout_fw_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 86
	public void setR_D14A_D11A() {

		road = new RoadList();
		road.setStartName("D14A");
		road.setEndName("D11A");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "D14",                  "I+3",                    "18/20",                      "I+2",                    "14/16",                       "10",                       "I+1",                      "I",                        "I-1",                    "9",                       "1/3_5/7",                   "15/17",                          "I-2"    };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule_s,  ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule_s,  ParamFlag.turnout_dw_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule,  ParamFlag.turnout_dw_bule_s,  ParamFlag.turnout_dw_bule_s,   ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green_s, ParamFlag.trackline_green, ParamFlag.turnout_dw_green_s, ParamFlag.turnout_dw_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green, ParamFlag.turnout_dw_green_s, ParamFlag.turnout_dw_green_s,  ParamFlag.turnout_dw_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red_s,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red_s,   ParamFlag.turnout_dw_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red,   ParamFlag.turnout_dw_red_s,   ParamFlag.turnout_dw_red_s,    ParamFlag.turnout_dw_red};

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 87
	public void setR_D14A_X1DA() {

		road = new RoadList();
		road.setStartName("D14A");
		road.setEndName("X1DA");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "D14",                  "I+3",                    "18/20",                      "I+2",                    "14/16",                       "10",                       "I+1",                      "I"             };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule_s,  ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule_s,  ParamFlag.turnout_dw_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green_s, ParamFlag.trackline_green, ParamFlag.turnout_dw_green_s, ParamFlag.turnout_dw_green, ParamFlag.trackline_green, ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red_s,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red_s,   ParamFlag.turnout_dw_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 88
	public void setR_D14A_X3DA() {

		road = new RoadList();
		road.setStartName("D14A");
		road.setEndName("X3DA");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "D14",                  "I+3",                    "18/20",                      "I+2",                    "14/16",                       "10",                       "3+1",                      "3"    };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule_s,  ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule_s,  ParamFlag.turnout_fw_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green_s, ParamFlag.trackline_green, ParamFlag.turnout_dw_green_s, ParamFlag.turnout_fw_green, ParamFlag.trackline_green, ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red_s,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red_s,   ParamFlag.turnout_fw_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 89
	public void setR_D22A_X1DA() {

		road = new RoadList();
		road.setStartName("D22A");
		road.setEndName("X1DA");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "D22",                  "II+3",                    "18/20",                    "2/4_6/8",                    "14/16",                       "10",                       "I+1",                      "I"             };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule_x,  ParamFlag.turnout_dw_bule_s,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_dw_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green_x, ParamFlag.turnout_dw_green_s, ParamFlag.turnout_fw_green, ParamFlag.turnout_dw_green, ParamFlag.trackline_green, ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red_x,   ParamFlag.turnout_dw_red_s,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_dw_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 90
	public void setR_D22A_X2DA() {

		road = new RoadList();
		road.setStartName("D22A");
		road.setEndName("X2DA");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "D22",                  "II+3",                    "18/20",                    "2/4_6/8",                    "14/16",                       "II+2" ,                      "II+1",                      "II"             };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule_x,  ParamFlag.turnout_dw_bule_s,  ParamFlag.turnout_dw_bule_x,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green_x, ParamFlag.turnout_dw_green_s, ParamFlag.turnout_dw_green_x, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red_x,   ParamFlag.turnout_dw_red_s,   ParamFlag.turnout_dw_red_x,   ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 91
	public void setR_D22A_X3DA() {

		road = new RoadList();
		road.setStartName("D22A");
		road.setEndName("X3DA");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "D22",                  "II+3",                    "18/20",                    "2/4_6/8",                    "14/16",                         "10",                   "3+1" ,                      "3"};
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule_x,  ParamFlag.turnout_dw_bule_s,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_fw_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green_x, ParamFlag.turnout_dw_green_s, ParamFlag.turnout_fw_green, ParamFlag.turnout_fw_green, ParamFlag.trackline_green, ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red_x,   ParamFlag.turnout_dw_red_s,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_fw_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 92
	public void setR_D22A_X4DA() {

		road = new RoadList();
		road.setStartName("D22A");
		road.setEndName("X4DA");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "D22",                  "II+3",                    "18/20",                    "2/4_6/8",                     "12",                   "4+1" ,                      "4"};
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule_x,  ParamFlag.turnout_fw_bule_r,  ParamFlag.turnout_dw_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green_x, ParamFlag.turnout_fw_green_r, ParamFlag.turnout_dw_green, ParamFlag.trackline_green, ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red_x,   ParamFlag.turnout_fw_red_r,   ParamFlag.turnout_dw_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 93
	public void setR_D22A_X6DA() {

		road = new RoadList();
		road.setStartName("D22A");
		road.setEndName("X6DA");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "D22",                  "II+3",                    "18/20",                    "2/4_6/8",                     "12",                   "6+1" ,                      "6"};
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule_x,  ParamFlag.turnout_fw_bule_r,  ParamFlag.turnout_fw_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green_x, ParamFlag.turnout_fw_green_r, ParamFlag.turnout_fw_green, ParamFlag.trackline_green, ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red_x,   ParamFlag.turnout_fw_red_r,   ParamFlag.turnout_fw_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 94
	public void setR_D41A_X1DA() {

		road = new RoadList();
		road.setStartName("D41A");
		road.setEndName("X1DA");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "D41",                  "4+2",                  "2/4_6/8",                    "14/16",                       "10",                       "I+1",                      "I"             };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_fw_bule_l,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_dw_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_fw_green_l, ParamFlag.turnout_fw_green, ParamFlag.turnout_dw_green, ParamFlag.trackline_green, ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_fw_red_l,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_dw_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 95
	public void setR_D41A_X2DA() {

		road = new RoadList();
		road.setStartName("D41A");
		road.setEndName("X2DA");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "D41",                  "4+2",                     "2/4_6/8",                    "14/16",                       "II+2" ,                      "II+1",                      "II"             };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_fw_bule_l,  ParamFlag.turnout_dw_bule_x,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_fw_green_l, ParamFlag.turnout_dw_green_x, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_fw_red_l,   ParamFlag.turnout_dw_red_x,   ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}	
//编号 96
	public void setR_D41A_X3DA() {

		road = new RoadList();
		road.setStartName("D41A");
		road.setEndName("X3DA");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "D41",                  "4+2",                       "2/4_6/8",                    "14/16",                         "10",                   "3+1" ,                      "3"};
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_fw_bule_l,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_fw_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_fw_green_l, ParamFlag.turnout_fw_green, ParamFlag.turnout_fw_green, ParamFlag.trackline_green, ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_fw_red_l,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_fw_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 97
	public void setR_D41A_X4DA() {

		road = new RoadList();
		road.setStartName("D41A");
		road.setEndName("X4DA");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "D41",                  "4+2",                   "2/4_6/8",                     "12",                   "4+1" ,                      "4"};
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule_x,  ParamFlag.turnout_dw_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green_x, ParamFlag.turnout_dw_green, ParamFlag.trackline_green, ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red_x,   ParamFlag.turnout_dw_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 98
	public void setR_D41A_X6DA() {

		road = new RoadList();
		road.setStartName("D41A");
		road.setEndName("X6DA");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "D41",                  "4+2",                     "2/4_6/8",                     "12",                   "6+1" ,                      "6"};
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule_x,  ParamFlag.turnout_fw_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green_x, ParamFlag.turnout_fw_green, ParamFlag.trackline_green, ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red_x,   ParamFlag.turnout_fw_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 99
	public void setR_D42A_X1DA() {

		road = new RoadList();
		road.setStartName("D42A");
		road.setEndName("X1DA");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "D42",                  "4+3",                    "4+2",                   "2/4_6/8",                    "14/16",                       "10",                       "I+1",                      "I"             };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.turnout_fw_bule_l,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_dw_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_fw_green_l, ParamFlag.turnout_fw_green, ParamFlag.turnout_dw_green, ParamFlag.trackline_green, ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.turnout_fw_red_l,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_dw_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 100
	public void setR_D42A_X2DA() {

		road = new RoadList();
		road.setStartName("D42A");
		road.setEndName("X2DA");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "D42",                  "4+3",                    "4+2",                 "2/4_6/8",                    "14/16",                       "II+2" ,                      "II+1",                      "II"             };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.turnout_fw_bule_l,  ParamFlag.turnout_dw_bule_x,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_fw_green_l, ParamFlag.turnout_dw_green_x, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.turnout_fw_red_l,   ParamFlag.turnout_dw_red_x,   ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 101
	public void setR_D42A_X3DA() {

		road = new RoadList();
		road.setStartName("D42A");
		road.setEndName("X3DA");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "D42",                  "4+3",                    "4+2",                 "2/4_6/8",                    "14/16",                         "10",                   "3+1" ,                      "3"};
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.turnout_fw_bule_l,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_fw_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_fw_green_l, ParamFlag.turnout_fw_green, ParamFlag.turnout_fw_green, ParamFlag.trackline_green, ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.turnout_fw_red_l,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_fw_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 102
	public void setR_D42A_X4DA() {

		road = new RoadList();
		road.setStartName("D42A");
		road.setEndName("X4DA");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "D42",                  "4+3",                  "4+2",                    "2/4_6/8",                     "12",                   "4+1" ,                      "4"};
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule_x,  ParamFlag.turnout_dw_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green_x, ParamFlag.turnout_dw_green, ParamFlag.trackline_green, ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red_x,   ParamFlag.turnout_dw_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 103
	public void setR_D42A_X6DA() {

		road = new RoadList();
		road.setStartName("D42A");
		road.setEndName("X6DA");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "D42",                  "4+3",                    "4+2",                   "2/4_6/8",                     "12",                   "6+1" ,                      "6"};
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule_x,  ParamFlag.turnout_fw_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green_x, ParamFlag.turnout_fw_green, ParamFlag.trackline_green, ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red_x,   ParamFlag.turnout_fw_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 104
	public void setR_D43A_X1DA() {

		road = new RoadList();
		road.setStartName("D43A");
		road.setEndName("X1DA");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "D43",                  "4+4",                       "4+3",                    "4+2",                   "2/4_6/8",                    "14/16",                       "10",                       "I+1",                      "I"             };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.turnout_fw_bule_l,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_dw_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_fw_green_l, ParamFlag.turnout_fw_green, ParamFlag.turnout_dw_green, ParamFlag.trackline_green, ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.turnout_fw_red_l,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_dw_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 105
	public void setR_D43A_X2DA() {

		road = new RoadList();
		road.setStartName("D43A");
		road.setEndName("X2DA");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "D43",                  "4+4",                       "4+3",                    "4+2",                 "2/4_6/8",                    "14/16",                       "II+2" ,                      "II+1",                      "II"             };
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.turnout_fw_bule_l,  ParamFlag.turnout_dw_bule_x,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_fw_green_l, ParamFlag.turnout_dw_green_x, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.turnout_fw_red_l,   ParamFlag.turnout_dw_red_x,   ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 106
	public void setR_D43A_X3DA() {

		road = new RoadList();
		road.setStartName("D43A");
		road.setEndName("X3DA");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "D43",                  "4+4",                       "4+3",                    "4+2",                 "2/4_6/8",                    "14/16",                         "10",                   "3+1" ,                      "3"};
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.turnout_fw_bule_l,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_fw_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_fw_green_l, ParamFlag.turnout_fw_green, ParamFlag.turnout_fw_green, ParamFlag.trackline_green, ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.turnout_fw_red_l,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_fw_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 107
	public void setR_D43A_X4DA() {

		road = new RoadList();
		road.setStartName("D43A");
		road.setEndName("X4DA");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "D43",                  "4+4",                       "4+3",                  "4+2",                    "2/4_6/8",                     "12",                   "4+1" ,                      "4"};
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule_x,  ParamFlag.turnout_dw_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green_x, ParamFlag.turnout_dw_green, ParamFlag.trackline_green, ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red_x,   ParamFlag.turnout_dw_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 108
	public void setR_D43A_X6DA() {

		road = new RoadList();
		road.setStartName("D43A");
		road.setEndName("X6DA");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "D43",                  "4+4",                       "4+3",                    "4+2",                   "2/4_6/8",                     "12",                   "6+1" ,                      "6"};
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule_x,  ParamFlag.turnout_fw_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green_x, ParamFlag.turnout_fw_green, ParamFlag.trackline_green, ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red_x,   ParamFlag.turnout_fw_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 109
	public void setR_D43A_D44A() {

		road = new RoadList();
		road.setStartName("D43A");
		road.setEndName("D44A");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "D43",                  "4+4",                       "4+3"};
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}
//编号 110
	public void setR_D44A_D43A() {

		road = new RoadList();
		road.setStartName("D44A");
		road.setEndName("D43A");
		roadInfoList = new ArrayList<RoadBasicInfo>();
		
		String[] R    = new String[] {    "D43",                  "4+4",                       "4+5"};
		int[] R_blue  = new int[] {ParamFlag.sep_white, ParamFlag.trackline_bule,  ParamFlag.trackline_bule};
		int[] R_green = new int[] {ParamFlag.sep_green, ParamFlag.trackline_green, ParamFlag.trackline_green};
		int[] R_red   = new int[] {ParamFlag.sep_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red};

		setArrayR(R, R_blue, R_green, R_red);
	}

//编号 111 
	public void setR_XNLA_S1LA() {

		road = new RoadList();
		road.setStartName("XNLA");
		road.setEndName("S1LA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R    = new String[] {    "I",                 "I-1",                    "9",                     "1/3_5/7",                       "15/17",                       "II-2",                        "XN"           };
		int[] R_blue  = new int[] {ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule,  ParamFlag.turnout_fw_bule_r,  ParamFlag.turnout_dw_bule_x,  ParamFlag.trackline_bule,  ParamFlag.sep_white };
		int[] R_green = new int[] {ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green, ParamFlag.turnout_fw_green_r, ParamFlag.turnout_dw_green_x, ParamFlag.trackline_green, ParamFlag.sep_green };
		int[] R_red   = new int[] {ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red,   ParamFlag.turnout_fw_red_r,   ParamFlag.turnout_dw_red_x,   ParamFlag.trackline_red,   ParamFlag.sep_red   };

		setArrayR(R, R_blue, R_green, R_red);	
	}
//编号 112 
	public void setR_XNLA_S2LA() {
		
		road = new RoadList();
		road.setStartName("XNLA");
		road.setEndName("S2LA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R    = new String[] {    "II",                 "II-1",                    "11",                     "1/3_5/7",                       "15/17",                       "II-2",                "XN"           };
		int[] R_blue  = new int[] {ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule,  ParamFlag.turnout_dw_bule_x,  ParamFlag.turnout_dw_bule_x,  ParamFlag.trackline_bule ,  ParamFlag.sep_white };
		int[] R_green = new int[] {ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green, ParamFlag.turnout_dw_green_x, ParamFlag.turnout_dw_green_x, ParamFlag.trackline_green,  ParamFlag.sep_green };
		int[] R_red   = new int[] {ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red,   ParamFlag.turnout_dw_red_x,   ParamFlag.turnout_dw_red_x,   ParamFlag.trackline_red,    ParamFlag.sep_red   };

		setArrayR(R, R_blue, R_green, R_red);	
	}
//编号 113 
	public void setR_XNLA_S3LA() {
		
		road = new RoadList();
		road.setStartName("XNLA");
		road.setEndName("S3LA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R    = new String[] {    "3",                 "3-1",                    "9",                     "1/3_5/7",                       "15/17",                       "II-2",                "XN"           };
		int[] R_blue  = new int[] {ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_fw_bule_r,  ParamFlag.turnout_dw_bule_x,  ParamFlag.trackline_bule,  ParamFlag.sep_white };
		int[] R_green = new int[] {ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_fw_green, ParamFlag.turnout_fw_green_r, ParamFlag.turnout_dw_green_x, ParamFlag.trackline_green, ParamFlag.sep_green };
		int[] R_red   = new int[] {ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_fw_red_r,   ParamFlag.turnout_dw_red_x,   ParamFlag.trackline_red,   ParamFlag.sep_red   };

		setArrayR(R, R_blue, R_green, R_red);	
	}
//编号 114
	public void setR_XNLA_S4LA() {
		
		road = new RoadList();
		road.setStartName("XNLA");
		road.setEndName("S4LA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R    = new String[] {    "4",                 "4-1",                    "13",                         "11",                     "1/3_5/7",                       "15/17",                       "II-2",                "XN"           };
		int[] R_blue  = new int[] {ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_dw_bule_x,  ParamFlag.turnout_dw_bule_x,  ParamFlag.trackline_bule , ParamFlag.sep_white };
		int[] R_green = new int[] {ParamFlag.trackline_green,  ParamFlag.trackline_green, ParamFlag.turnout_dw_green, ParamFlag.turnout_fw_green, ParamFlag.turnout_dw_green_x, ParamFlag.turnout_dw_green_x, ParamFlag.trackline_green, ParamFlag.sep_green };
		int[] R_red   = new int[] {ParamFlag.trackline_red,    ParamFlag.trackline_red,   ParamFlag.turnout_dw_red,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_dw_red_x,   ParamFlag.turnout_dw_red_x,   ParamFlag.trackline_red,   ParamFlag.sep_red   };

		setArrayR(R, R_blue, R_green, R_red);	
	}
//编号 115 
	public void setR_XNLA_S6LA() {
		
		road = new RoadList();
		road.setStartName("XNLA");
		road.setEndName("S6LA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R    = new String[] {    "6",                 "6-1",                    "13",                         "11",                     "1/3_5/7",                       "15/17",                       "II-2",                "XN"           };
		int[] R_blue  = new int[] {ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_dw_bule_x,  ParamFlag.turnout_dw_bule_x,  ParamFlag.trackline_bule ,  ParamFlag.sep_white };
		int[] R_green = new int[] {ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_fw_green, ParamFlag.turnout_fw_green, ParamFlag.turnout_dw_green_x, ParamFlag.turnout_dw_green_x, ParamFlag.trackline_green , ParamFlag.sep_green };
		int[] R_red   = new int[] {ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_dw_red_x,   ParamFlag.turnout_dw_red_x,   ParamFlag.trackline_red,    ParamFlag.sep_red   };

		setArrayR(R, R_blue, R_green, R_red);	
	}
//编号 116
	public void setR_S1LA_XLA() {

		road = new RoadList();
		road.setStartName("S1LA");
		road.setEndName("XLA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R    = new String[] {     "I-2",                    "15/17",                     "1/3_5/7",                       "9",                       "I-1",                      "S1",       };
		int[] R_blue  = new int[] {ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule_s,  ParamFlag.turnout_dw_bule_s,  ParamFlag.turnout_dw_bule,  ParamFlag.trackline_bule,  ParamFlag.sep_white  };
		int[] R_green = new int[] {ParamFlag.trackline_green, ParamFlag.turnout_dw_green_s, ParamFlag.turnout_dw_green_s, ParamFlag.turnout_dw_green, ParamFlag.trackline_green, ParamFlag.sep_green  };
		int[] R_red   = new int[] {ParamFlag.trackline_red,   ParamFlag.turnout_dw_red_s,   ParamFlag.turnout_dw_red_s,   ParamFlag.turnout_dw_red,   ParamFlag.trackline_red,   ParamFlag.sep_red  };

		setArrayRN(R, R_blue, R_green, R_red);	
	}
//编号 117
	public void setR_S2LA_XLA() {

		road = new RoadList();
		road.setStartName("S2LA");
		road.setEndName("XLA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R    = new String[] {      "I-2",                    "15/17",                     "1/3_5/7",                      "11",                      "II-1",                 "S2"    };
		int[] R_blue  = new int[] {ParamFlag.trackline_bule,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_dw_bule_x,  ParamFlag.turnout_dw_bule,  ParamFlag.trackline_bule,  ParamFlag.sep_white }  ;
		int[] R_green = new int[] {ParamFlag.trackline_green, ParamFlag.turnout_fw_green, ParamFlag.turnout_dw_green_x, ParamFlag.turnout_dw_green, ParamFlag.trackline_green, ParamFlag.sep_green };
		int[] R_red   = new int[] {ParamFlag.trackline_red,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_dw_red_x,   ParamFlag.turnout_dw_red,   ParamFlag.trackline_red,   ParamFlag.sep_red   }   ;

		setArrayRN(R, R_blue, R_green, R_red);	
	}
//编号 118
	public void setR_S2LA_BA_XLA() {

		road = new RoadList();
		road.setStartName("S2LA");
		road.setMidName("BA");
		road.setEndName("XLA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R    = new String[] {        "I-2",                    "15/17",                     "1/3_5/7",                      "11",                      "II-1",                       "S3" };
		int[] R_blue  = new int[] {ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule_s,  ParamFlag.turnout_fw_bule_l,  ParamFlag.turnout_dw_bule,  ParamFlag.trackline_bule,  ParamFlag.sep_white  };
		int[] R_green = new int[] {ParamFlag.trackline_green, ParamFlag.turnout_dw_green_s, ParamFlag.turnout_fw_green_l, ParamFlag.turnout_dw_green, ParamFlag.trackline_green, ParamFlag.sep_green };
		int[] R_red   = new int[] {ParamFlag.trackline_red,   ParamFlag.turnout_dw_red_s,   ParamFlag.turnout_fw_red_l,   ParamFlag.turnout_dw_red,   ParamFlag.trackline_red,   ParamFlag.sep_red   };

		setArrayRN(R, R_blue, R_green, R_red);	
	}
//编号  119
	public void setR_S3LA_XLA() {

		road = new RoadList();
		road.setStartName("S3LA");
		road.setEndName("XLA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R    = new String[] {          "I-2",                    "15/17",                     "1/3_5/7",                       "9",                       "3-1",                      "S3" };
		int[] R_blue  = new int[] {ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule_s,  ParamFlag.turnout_dw_bule_s,  ParamFlag.turnout_fw_bule,  ParamFlag.trackline_bule,  ParamFlag.sep_white}  ;
		int[] R_green = new int[] {ParamFlag.trackline_green, ParamFlag.turnout_dw_green_s, ParamFlag.turnout_dw_green_s, ParamFlag.turnout_fw_green, ParamFlag.trackline_green, ParamFlag.sep_green} ;
		int[] R_red   = new int[] {ParamFlag.trackline_red,   ParamFlag.turnout_dw_red_s,   ParamFlag.turnout_dw_red_s,   ParamFlag.turnout_fw_red,   ParamFlag.trackline_red,   ParamFlag.sep_red}   ;

		setArrayRN(R, R_blue, R_green, R_red);	
	}
//编号 120
	public void setR_S4LA_XLA() {

		road = new RoadList();
		road.setStartName("S4LA");
		road.setEndName("XLA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R    = new String[] {        "I-2",                    "15/17",                     "1/3_5/7",                      "11",                      "13",                     "4-1",                       "S4" };
		int[] R_blue  = new int[] {ParamFlag.trackline_bule,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_dw_bule_x,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_dw_bule,  ParamFlag.trackline_bule,  ParamFlag.sep_white  };
		int[] R_green = new int[] {ParamFlag.trackline_green, ParamFlag.turnout_fw_green, ParamFlag.turnout_dw_green_x, ParamFlag.turnout_fw_green, ParamFlag.turnout_dw_green, ParamFlag.trackline_green, ParamFlag.sep_green };
		int[] R_red   = new int[] {ParamFlag.trackline_red,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_dw_red_x,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_dw_red,   ParamFlag.trackline_red,   ParamFlag.sep_red   };

		setArrayRN(R, R_blue, R_green, R_red);	
	}
//编号 121 
	public void setR_S4LA_BA_XLA() {

		road = new RoadList();
		road.setStartName("S4LA");
		road.setMidName("BA");
		road.setEndName("XLA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R    = new String[] {          "I-2",                    "15/17",                     "1/3_5/7",                      "11",                      "13",                     "4-1",                       "S4" };
		int[] R_blue  = new int[] {ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule_s,  ParamFlag.turnout_fw_bule_l,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_dw_bule,  ParamFlag.trackline_bule,  ParamFlag.sep_white  };
		int[] R_green = new int[] {ParamFlag.trackline_green, ParamFlag.turnout_dw_green_s, ParamFlag.turnout_fw_green_l, ParamFlag.turnout_fw_green, ParamFlag.turnout_dw_green, ParamFlag.trackline_green, ParamFlag.sep_green };
		int[] R_red   = new int[] {ParamFlag.trackline_red,   ParamFlag.turnout_dw_red_s,   ParamFlag.turnout_fw_red_l,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_dw_red,   ParamFlag.trackline_red,   ParamFlag.sep_red   };

		setArrayRN(R, R_blue, R_green, R_red);	
	}
//编号 122
	public void setR_S6LA_XLA() {

		road = new RoadList();
		road.setStartName("S6LA");
		road.setEndName("XLA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R    = new String[] {         "I-2",                    "15/17",                     "1/3_5/7",                      "11",                      "13",                     "6-1",                       "S6" };
		int[] R_blue  = new int[] {ParamFlag.trackline_bule,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_dw_bule_x,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_fw_bule,  ParamFlag.trackline_bule,  ParamFlag.sep_white  };
		int[] R_green = new int[] {ParamFlag.trackline_green, ParamFlag.turnout_fw_green, ParamFlag.turnout_dw_green_x, ParamFlag.turnout_fw_green, ParamFlag.turnout_fw_green, ParamFlag.trackline_green, ParamFlag.sep_green };
		int[] R_red   = new int[] {ParamFlag.trackline_red,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_dw_red_x,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_fw_red,   ParamFlag.trackline_red,   ParamFlag.sep_red   };

		setArrayRN(R, R_blue, R_green, R_red);	
	}
//编号 123 
	public void setR_S6LA_BA_XLA() {

		road = new RoadList();
		road.setStartName("S6LA");
		road.setMidName("BA");
		road.setEndName("XLA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R    = new String[] {       "I-2",                    "15/17",                     "1/3_5/7",                      "11",                      "13",                     "6-1",                       "S6"        };
		int[] R_blue  = new int[] {ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule_s,  ParamFlag.turnout_fw_bule_l,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_fw_bule,  ParamFlag.trackline_bule,  ParamFlag.sep_white  };
		int[] R_green = new int[] {ParamFlag.trackline_green, ParamFlag.turnout_dw_green_s, ParamFlag.turnout_fw_green_l, ParamFlag.turnout_fw_green, ParamFlag.turnout_fw_green, ParamFlag.trackline_green, ParamFlag.sep_green  };
		int[] R_red   = new int[] {ParamFlag.trackline_red,   ParamFlag.turnout_dw_red_s,   ParamFlag.turnout_fw_red_l,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_fw_red,   ParamFlag.trackline_red,   ParamFlag.sep_red    };

		setArrayRN(R, R_blue, R_green, R_red);	
	}	
//编号 124	
	public void setR_SNLA_X1LA() {

		road = new RoadList();
		road.setStartName("SNLA");
		road.setEndName("X1LA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R    = new String[] {          "I",                "I+1",                    "10",                        "14/16",                    "I+2",                       "18/20",                      "I+3",                       "I+4",                       "SN"        };
		int[] R_blue  = new int[] {ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule,   ParamFlag.turnout_dw_bule_s,  ParamFlag.trackline_bule,   ParamFlag.turnout_dw_bule_s,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.sep_white  };
		int[] R_green = new int[] {ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green,  ParamFlag.turnout_dw_green_s, ParamFlag.trackline_green,  ParamFlag.turnout_dw_green_s, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.sep_green  };
		int[] R_red   = new int[] {ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red,    ParamFlag.turnout_dw_red_s,   ParamFlag.trackline_red,    ParamFlag.turnout_dw_red_s,   ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.sep_red    };

		setArrayRN(R, R_blue, R_green, R_red);	
	}
//编号 125 
	public void setR_SNLA_X2LA() {

		road = new RoadList();
		road.setStartName("SNLA");
		road.setEndName("X2LA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R    = new String[] {     "II",                "II+1",                    "II+2",                    "14/16",                    "2/4_6/8",                       "18/20",                      "I+3",                       "I+4",                       "SN"        };
		int[] R_blue  = new int[] {ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule_x,  ParamFlag.turnout_dw_bule_s,  ParamFlag.turnout_fw_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.sep_white  };
		int[] R_green = new int[] {ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green_x, ParamFlag.turnout_dw_green_s, ParamFlag.turnout_fw_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.sep_green  };
		int[] R_red   = new int[] {ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red_x,   ParamFlag.turnout_dw_red_s,   ParamFlag.turnout_fw_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.sep_red    };

		setArrayRN(R, R_blue, R_green, R_red);	
	}
//编号 126 
	public void setR_SNLA_X3LA() {

		road = new RoadList();
		road.setStartName("SNLA");
		road.setEndName("X3LA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R    = new String[] {     "3",                "3+1",                    "10",                        "14/16",                    "I+2",                       "18/20",                      "I+3",                       "I+4",                       "SN"        };
		int[] R_blue  = new int[] {ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.turnout_fw_bule,   ParamFlag.turnout_dw_bule_s,  ParamFlag.trackline_bule,   ParamFlag.turnout_dw_bule_s,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.sep_white  };
		int[] R_green = new int[] {ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_fw_green,  ParamFlag.turnout_dw_green_s, ParamFlag.trackline_green,  ParamFlag.turnout_dw_green_s, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.sep_green  };
		int[] R_red   = new int[] {ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.turnout_fw_red,    ParamFlag.turnout_dw_red_s,   ParamFlag.trackline_red,    ParamFlag.turnout_dw_red_s,   ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.sep_red    };

		setArrayRN(R, R_blue, R_green, R_red);	
	}
//编号 127 
	public void setR_SNLA_X4LA() {

		road = new RoadList();
		road.setStartName("X4LA");
		road.setEndName("X4LA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R    = new String[] {     "4",                "4+1",                    "12",                     "2/4_6/8",                       "18/20",                      "I+3",                       "I+4",                 "SN"        };
		int[] R_blue  = new int[] {ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule,  ParamFlag.turnout_fw_bule_r,  ParamFlag.turnout_fw_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.sep_white  };
		int[] R_green = new int[] {ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green, ParamFlag.turnout_fw_green_r, ParamFlag.turnout_fw_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.sep_green  };
		int[] R_red   = new int[] {ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red,   ParamFlag.turnout_fw_red_r,   ParamFlag.turnout_fw_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.sep_red    };

		setArrayRN(R, R_blue, R_green, R_red);	
	}
//编号 128
	public void setR_SNLA_X6LA() {

		road = new RoadList();
		road.setStartName("SNLA");
		road.setEndName("X6LA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R    = new String[] {     "6",                "6+1",                    "12",                     "2/4_6/8",                       "18/20",                      "I+3",                       "I+4",                  "SN"        };
		int[] R_blue  = new int[] {ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_fw_bule_r,  ParamFlag.turnout_fw_bule,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.sep_white  };
		int[] R_green = new int[] {ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_fw_green, ParamFlag.turnout_fw_green_r, ParamFlag.turnout_fw_green, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.sep_green  };
		int[] R_red   = new int[] {ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_fw_red_r,   ParamFlag.turnout_fw_red,   ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.sep_red    };

		setArrayRN(R, R_blue, R_green, R_red);	
	}	
//编号 129
	public void setR_X1LA_SLA() {

		road = new RoadList();
		road.setStartName("X1LA");
		road.setEndName("SLA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R    = new String[] {            "II+4",                    "II+3",                    "18/20",                     "2/4_6/8",                       "14/16",                     "10",                      "I+1",                       "X1" };
		int[] R_blue  = new int[] {ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule_x,  ParamFlag.turnout_dw_bule_s,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_dw_bule,  ParamFlag.trackline_bule,  ParamFlag.sep_white  };
		int[] R_green = new int[] {ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green_x, ParamFlag.turnout_dw_green_s, ParamFlag.turnout_fw_green, ParamFlag.turnout_dw_green, ParamFlag.trackline_green, ParamFlag.sep_green };
		int[] R_red   = new int[] {ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red_x,   ParamFlag.turnout_dw_red_s,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_dw_red,   ParamFlag.trackline_red,   ParamFlag.sep_red   };

		setArrayRN(R, R_blue, R_green, R_red);	
	}
//编号 130 
	public void setR_X2LA_SLA() {
		
		road = new RoadList();
		road.setStartName("X2LA");
		road.setEndName("SLA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R    = new String[] {     "II+4",                    "II+3",                    "18/20",                     "2/4_6/8",                       "14/16",                     "II+2",                      "II+1",                       "X2" };
		int[] R_blue  = new int[] {ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule_x,  ParamFlag.turnout_dw_bule_s,  ParamFlag.turnout_dw_bule_x,  ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.sep_white  };
		int[] R_green = new int[] {ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green_x, ParamFlag.turnout_dw_green_s, ParamFlag.turnout_dw_green_x, ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.sep_green };
		int[] R_red   = new int[] {ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red_x,   ParamFlag.turnout_dw_red_s,   ParamFlag.turnout_dw_red_x,   ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.sep_red   };

		setArrayRN(R, R_blue, R_green, R_red);	
	}
//编号 131 
	public void setR_X3LA_SLA() {
		
		road = new RoadList();
		road.setStartName("X3LA");
		road.setEndName("SLA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R    = new String[] {     "II+4",                    "II+3",                    "18/20",                     "2/4_6/8",                       "14/16",                     "10",                      "3+1",                       "X3" };
		int[] R_blue  = new int[] {ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule_x,  ParamFlag.turnout_dw_bule_s,  ParamFlag.turnout_fw_bule,  ParamFlag.turnout_fw_bule,  ParamFlag.trackline_bule,  ParamFlag.sep_white  };
		int[] R_green = new int[] {ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green_x, ParamFlag.turnout_dw_green_s, ParamFlag.turnout_fw_green, ParamFlag.turnout_fw_green, ParamFlag.trackline_green, ParamFlag.sep_green };
		int[] R_red   = new int[] {ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red_x,   ParamFlag.turnout_dw_red_s,   ParamFlag.turnout_fw_red,   ParamFlag.turnout_fw_red,   ParamFlag.trackline_red,   ParamFlag.sep_red   };

		setArrayRN(R, R_blue, R_green, R_red);	
	}
//编号 132 
	public void setR_X4LA_SLA() {
		
		road = new RoadList();
		road.setStartName("X4LA");
		road.setEndName("SLA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R    = new String[] {         "II+4",                    "II+3",                    "18/20",                     "2/4_6/8",                       "12",                      "4+1",                      "X4"              };
		int[] R_blue  = new int[] {ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule_x,  ParamFlag.turnout_fw_bule_r,  ParamFlag.turnout_dw_bule,  ParamFlag.trackline_bule,  ParamFlag.sep_white  };
		int[] R_green = new int[] {ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green_x, ParamFlag.turnout_fw_green_r, ParamFlag.turnout_dw_green, ParamFlag.trackline_green, ParamFlag.sep_green };
		int[] R_red   = new int[] {ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red_x,   ParamFlag.turnout_fw_red_r,   ParamFlag.turnout_dw_red,   ParamFlag.trackline_red,   ParamFlag.sep_red,  };

		setArrayRN(R, R_blue, R_green, R_red);	
	}
//编号 133 
	public void setR_X6LA_SLA() {

		road = new RoadList();
		road.setStartName("X6LA");
		road.setEndName("SLA");
		roadInfoList = new ArrayList<RoadBasicInfo>();

		String[] R    = new String[] {       "II+4",                    "II+3",                    "18/20",                     "2/4_6/8",                       "12",                      "6+1",                      "X6"              };
		int[] R_blue  = new int[] {ParamFlag.trackline_bule,  ParamFlag.trackline_bule,  ParamFlag.turnout_dw_bule_x,  ParamFlag.turnout_fw_bule_r,  ParamFlag.turnout_fw_bule,  ParamFlag.trackline_bule,  ParamFlag.sep_white  };
		int[] R_green = new int[] {ParamFlag.trackline_green, ParamFlag.trackline_green, ParamFlag.turnout_dw_green_x, ParamFlag.turnout_fw_green_r, ParamFlag.turnout_fw_green, ParamFlag.trackline_green, ParamFlag.sep_green };
		int[] R_red   = new int[] {ParamFlag.trackline_red,   ParamFlag.trackline_red,   ParamFlag.turnout_dw_red_x,   ParamFlag.turnout_fw_red_r,   ParamFlag.turnout_fw_red,   ParamFlag.trackline_red,   ParamFlag.sep_red,  };

		setArrayRN(R, R_blue, R_green, R_red);	
	}
	
	
	//顺序
	public void setArrayR(String R[], int R_blue[], int R_green[], int R_red[]) {

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
	}
	
	//逆序
	public void setArrayRN(String R[], int R_blue[], int R_green[], int R_red[]) {

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
	}
	

}
