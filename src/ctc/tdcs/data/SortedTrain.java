package ctc.tdcs.data;

import ctc.constant.Constants;

public class SortedTrain implements Comparable{

	private int timeType = Constants.TDCS_TIME_TYPE_ARRIVEAL;//1:表示time是到站时间， 2表示time是离站时间
	/*关于time的值：
	原有车次： baseserver所填写的时间格式为（单位 分钟）：000，由从数据库获取的由字符串表示的时间转换而来：所调用的转化方法：TimeX_x.timeToX
	新添加车次： UtilForDraw类appendStationTrainMap方法所填写的时间格式为（单位 分钟）：000， 由屏幕获取的由整数表示的时间
	转换为由整数表示的实际时间，所调用的转化方法XYForTimeRectangle.xToTime
	屏幕值由drawTimeRectangle和DrawPlanTrain两个类中有关方法决定
	*/
	private int time;//时间  格式：127分钟 同界面上的时间表示方式一致
	private String trainName;//列车名称
	private int trainDirection;//车次方向（上行0和下行1）
	private int typeFlag;//表示该车次类别
	
	public SortedTrain(){}
    //用于删除
	public SortedTrain(String trainName, int trainDirection,int timeType,int time) {
		this.timeType = timeType;
		this.time = time;
		this.trainName = trainName;
		this.trainDirection = trainDirection;
	}
	//用于添加
	public SortedTrain(int typeFlag,String trainName, int trainDirection,int timeType,int time) {
		this.typeFlag = typeFlag;
		this.timeType = timeType;
		this.time = time;
		this.trainName = trainName;
		this.trainDirection = trainDirection;
	}

	
	public int getTypeFlag() {
		return typeFlag;
	}

	public void setTypeFlag(int typeFlag) {
		this.typeFlag = typeFlag;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getTrainDirection() {
		return trainDirection;
	}

	public void setTrainDirection(int trainDirection) {
		this.trainDirection = trainDirection;
	}

	public int getTimeType() {
		return timeType;
	}

	public void setTimeType(int timeType) {
		this.timeType = timeType;
	}

	public String getTrainName() {
		return trainName;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}
	/*Comparable<String> 中的 compareTo 如果参数字符串等于此字符串，则返回 0 值；如果按字典顺序此字符串小于字符串参数，
	则返回一个小于 0 的值；如果按字典顺序此字符串大于字符串参数，则返回一个大于 0 的值。
	if(a.compareTo(b) == -1) => a < b    if(a.compareTo(b) == 0) => a == b    if(a.compareTo(b) == 1) => a > b*/
   @Override
   public int compareTo(Object obj) {//相等返回0，前者小返回-1，前者大返回1
	   int returnValue = 0;
	   Integer time1I = new Integer(time);
	   Integer time2I = new Integer(((SortedTrain) obj).getTime());
	   int strReturnValue = time1I.compareTo(time2I);
	   
       //排序的规则是同一车次排在一块   到站时间类型为：6291457 (前)离站时间类型值：6291458（后）
	   //不同车次，情况下，按

	   if( 0 == trainName.compareTo(((SortedTrain) obj).getTrainName())) //同一车次
	   {
		   time1I = new Integer(timeType);
		   time2I = new Integer(((SortedTrain) obj).getTimeType());
		   strReturnValue = time1I.compareTo(time2I);
	   }
	   else//表示非同一趟车
	   {//
		   strReturnValue = 1;//表示不同车次同时到站或离站，默认先到的排在后到的前面（在TreeSet中）
	   }

	   if (strReturnValue < 0)
		   returnValue = -1;
	   else
	   if (strReturnValue > 0)   
		   returnValue = 1;
	   
	   //System.out.println("returnValue:"+returnValue);
	   
	   return returnValue;			
	}
}
