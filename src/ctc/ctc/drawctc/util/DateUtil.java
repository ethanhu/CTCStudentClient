package ctc.ctc.drawctc.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

	@SuppressWarnings("deprecation")
	public static Date transferToDate(String time) {
		int index1 = time.indexOf(":");
		int index2 = time.lastIndexOf(":");
		int hour = Integer.parseInt(time.substring(0, index1));
		int minute = Integer.parseInt(time.substring(index1 + 1, index2));
		int second = Integer.parseInt(time.substring(index2 + 1));

		Date date = new Date();
		date.setHours(hour);
		date.setMinutes(minute);
		date.setSeconds(second);

		return date;
	}

	@SuppressWarnings("deprecation")
	public static int transferToMinute(String time) {
		Date date = DateUtil.transferToDate(time);

		return date.getHours() * 60 + date.getMinutes();
	}
	
	//根据给定时间返回分钟数
	public static int getMinuteOfDate(Date date){
		if(date != null){
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			return calendar.get(Calendar.HOUR_OF_DAY) * 60 + calendar.get(Calendar.MINUTE);
		}
		return 0;
	}
	
}
