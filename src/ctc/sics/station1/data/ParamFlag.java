package ctc.sics.station1.data;

public class ParamFlag {

	//--------------道岔----------------//
	//-----定位----//
	
	public static final int turnout_dw = 0x000001; //道岔定位	

	public static final int turnout_dw_black = 0x000002; //定位黑色
	public static final int turnout_dw_bule = 0x000003; //定位蓝色
	public static final int turnout_dw_green = 0x000004; //定位绿色
	public static final int turnout_dw_red = 0x000005; //定位红色

	public static final int turnout_dw_black_s = 0x000006; //定位黑色(上)
	public static final int turnout_dw_bule_s = 0x000007; //定位蓝色(上)
	public static final int turnout_dw_green_s = 0x000008; //定位绿色(上)
	public static final int turnout_dw_red_s = 0x000009; //定位红色(上)

	public static final int turnout_dw_black_x = 0x00000A; //定位黑色(下)
	public static final int turnout_dw_bule_x = 0x00000B; //定位蓝色(下)
	public static final int turnout_dw_green_x = 0x00000C; //定位绿色(下)
	public static final int turnout_dw_red_x = 0x00000D; //定位红色(下)
	

	public static final int turnout_red = 0x00000E; //锁闭红色	
	public static final int turnout_blue = 0x00000F; //解锁	
	
	
	//-----反位-----//
	public static final int turnout_fw = 0x100001; //道岔反位

	public static final int turnout_fw_black = 0x100002; //反位黑色
	public static final int turnout_fw_bule = 0x100003; //反位蓝色
	public static final int turnout_fw_green = 0x100004; //反位绿色
	public static final int turnout_fw_red = 0x100005; //反位红色
	
	public static final int turnout_fw_black_l = 0x100006; //反位黑色(左倾斜部分)
	public static final int turnout_fw_bule_l = 0x100007; //反位蓝色(左倾斜部分)
	public static final int turnout_fw_green_l = 0x100008; //反位绿色(左倾斜部分)
	public static final int turnout_fw_red_l = 0x100009; //反位红色(左倾斜部分)
	
	public static final int turnout_fw_black_r = 0x10000A; //反位黑色(右倾斜部分)
	public static final int turnout_fw_bule_r = 0x10000B; //反位蓝色(右倾斜部分)
	public static final int turnout_fw_green_r = 0x10000C; //反位绿色(右倾斜部分)
	public static final int turnout_fw_red_r = 0x10000D; //反位红色(右倾斜部分)
		
	//--------------股道--------------------//
	public static final int trackline_black = 0x300001; //定位黑色
	public static final int trackline_bule = 0x300002; //定位蓝色
	public static final int trackline_green = 0x300003; //定位绿色
	public static final int trackline_red = 0x300004; //定位红色
	
	//--------------信号机------------------//
	public static final int sep_white = 0x400001; //白色
	public static final int sep_yellow = 0x400002; //黄色
	public static final int sep_green = 0x400003; //绿色
	public static final int sep_red = 0x400004; //红色	
	public static final int sep_blue = 0x400005; //红色	
	
	//--------------路的操作flag------------------//
	public static final int road_blue = 0x500001; //绿色
	public static final int road_green = 0x500002; //绿色
	public static final int road_red = 0x500003; //红色
	public static final int road_black = 0x500004; //黑色
		
	//--------------路的类型flag----------------//
	public static final int road_type_x = 0x600001; //下行 
	public static final int road_type_s = 0x600002; //上行 
	public static final int road_type_d = 0x600003; //调车路 
	
	
	//-----------按钮---------------//
	public static boolean sys_auto = false; //自律模式
	public static boolean road_cancel = false;
	public static boolean road_unlock = false;
	
		
	
	
	
	
	
	
	
	
	
}
