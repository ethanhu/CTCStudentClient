package ctc.tdcs.Util;

//目前Mysql 存储的时间格式是：时00：分00：秒00   其中 秒为00
public class UtilForTime2X {
	
	//字符串表示的时间（如从数据库中直接读出的时间格式）转化为整数表示
	public static int timeToX(String timeStr){
		int xCoordinate = 0;
		
		if(timeStr == null || timeStr.length() == 0)
			return xCoordinate;
		
		int index = timeStr.indexOf(":");
		int hours = Integer.valueOf(timeStr.substring(0,index));//时
	
		String subTimeStr = timeStr.substring(index + 1);
		index = subTimeStr.indexOf(":");
		int minutes = Integer.valueOf(subTimeStr.substring(0,index));//分
			
		xCoordinate = hours*60 + minutes;
		return xCoordinate;
	}

	
	//整数表示的时间转化为字符串表示
	public static String XToTime(int x){
		String timeStr = "";
		String minStr = "";
		if( x < 0 )
			return timeStr;
			
		int hours = (int) x/60;
		if (hours <= 9)//保证小时的表示占两位
			timeStr = "0";
		
		int minutes = x % 60;
		if (minutes <= 9)//保证小时的表示占两位
			minStr = "0";
				
		timeStr +=String.valueOf(hours) + ":" + minStr + String.valueOf(minutes) +":00";
		
		return timeStr;
	}
	


}
