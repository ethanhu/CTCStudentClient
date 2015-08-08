package ctc.sics.util;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Clock {

	public String updateTime(){
		
		Calendar now = new GregorianCalendar(); // 实例化日历对象

		String timeInfo = ""; // 输出信息

		int hour = now.get(Calendar.HOUR_OF_DAY); // 得到小时数

		int minute = now.get(Calendar.MINUTE); // 得到分数

		int second = now.get(Calendar.SECOND); // 得到秒数

		if (hour <= 9)// if判断小时 小于等于9时前加0输出 大于则正常输出

			timeInfo += "0" + hour + ":"; // 格式化输出

		else

			timeInfo += hour + ":";

		if (minute <= 9)// if判断分钟 小于等于9时前加0输出 大于则正常输出

			timeInfo += "0" + minute + ":";

		else

			timeInfo += minute + ":";

		if (second <= 9)// if判断秒 小于等于9时前加0输出 大于则正常输出

			timeInfo += "0" + second;

		else

			timeInfo += second;

		return timeInfo;
		
	}
	
	
}
