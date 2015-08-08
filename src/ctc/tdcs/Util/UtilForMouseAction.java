package ctc.tdcs.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import org.eclipse.draw2d.geometry.Point;

import ctc.constant.Constants;
import ctc.tdcs.data.BaseParam;
import ctc.tdcs.data.SortedTrain;
import ctc.tdcs.elements.LineAnchorFigure;
import ctc.tdcs.elements.LineFigure;
import ctc.tdcs.ui.TdcsInputTrainDialog;
import ctc.tdcs.ui.TdcsToolbarFactory;
import ctc.util.DateUtil;

//主要处理ScheduleDataset_x数据和判断用户的操作是否合理
public class UtilForMouseAction {
	
	//保存已经按照到站时间排好序的所有车次信息     Key为车站名 
	private static Map<String,TreeSet<SortedTrain>> stationTrainDownMap = new HashMap<String,TreeSet<SortedTrain>>();//下行
	private static Map<String,TreeSet<SortedTrain>> stationTrainUpMap = new HashMap<String,TreeSet<SortedTrain>>();//上行
	
	//保存排好序的的车站名称
	private static List<String> sortedStationNameList = new ArrayList<String>();
	
	private static  int hours,locationX,minuteStep;
	
	private static int constMaxX = 0;
	private static  String preStationName = "";
	private static String firstStation,lastStation;
	private UtilForStatics utilForStatics = new UtilForStatics();
	/////////////////////////////////////////////////////////////////////////////
	private static TdcsToolbarFactory tdcsToolbarFactory = TdcsToolbarFactory.getInstance();
	
	//获取当前时间  false不能进行操作
	public boolean isDrawFlag(int minuteX){
		
		//还没有选取开始按钮
		if (! tdcsToolbarFactory.isStartFlag())
			return true;
		
		boolean drawFlag = false;
		
		//获取当前虚拟时间
		int vrTimeI = DateUtil.StrToTime(tdcsToolbarFactory.getVRTime());
		
		int minutes = UtilForTimeRectangle.xToTime(minuteX);//将屏幕时间转换为实际时间如屏幕值为：117 ,转换后为：20分钟
		if(minutes > vrTimeI)
			drawFlag = true;
		
		return drawFlag;
	}
	
	
	////////////////////////////////////////////////////////////////
	public void init(){
		
		hours = BaseParam.getHours();
		sortedStationNameList = BaseParam.getSortedStationNameList();
		locationX = UtilForTimeRectangle.getTimeRectangleLocationX();
		minuteStep = UtilForTimeRectangle.getMinuteStep();
		int stationLength = sortedStationNameList.size();
		firstStation = sortedStationNameList.get(0);
		lastStation = sortedStationNameList.get(stationLength - 1);	
	}
	
	public UtilForMouseAction(){	}

	public static void setStationTrainDownMap(Map<String, TreeSet<SortedTrain>> stationTrainDownMap) {
		UtilForMouseAction.stationTrainDownMap = stationTrainDownMap;
	}
	public static void setStationTrainUpMap(Map<String, TreeSet<SortedTrain>> stationTrainUpMap) {
		UtilForMouseAction.stationTrainUpMap = stationTrainUpMap;
	}
	//判断某一车次是否存在（屏幕上）,当不存在时，才可以使用户重新定义此车次
	private void trainExist(String flag, String trainName){
		Map<String,TreeSet<SortedTrain>> map = new HashMap<String,TreeSet<SortedTrain>>();
		if ( flag.equalsIgnoreCase("down"))
			map = stationTrainDownMap;
		else
			map = stationTrainUpMap;

		boolean resultFlag = false;
		TreeSet<SortedTrain> treeSet = new TreeSet<SortedTrain>();
		for(TreeSet value : map.values()){
			Iterator it = value.iterator();
			while(it.hasNext()){//检查序列中是否还有元素
				SortedTrain data = (SortedTrain) it.next();//获得序列中的下一个元素
				if (data.getTrainName().equalsIgnoreCase(trainName))
				{
					resultFlag = true;
					break;
				}
			}		
		}
	    if(!resultFlag)
	    	TdcsInputTrainDialog.deleteTrain(trainName);
	}
		 
	//删除保存在stationTrainDownMap或stationTrainUp中的内容
	public boolean deleteStationTrainMap(LineFigure newTrainLine){
		boolean resultFlag = false;
	
		if (newTrainLine == null)//一般 不会出现
			return resultFlag;
		
		TreeSet<SortedTrain> preTreeSet = new TreeSet<SortedTrain>();
		TreeSet<SortedTrain> treeSet = new TreeSet<SortedTrain>();
		LineAnchorFigure sourceAnchor = new LineAnchorFigure();
		LineAnchorFigure targetAnchor = new LineAnchorFigure();
		
		String prestationName = newTrainLine.getPrestationName();//前站站名
		sourceAnchor = newTrainLine.getSource();
		
		String stationName = newTrainLine.getStationName();//本站
		targetAnchor = newTrainLine.getTarget();
		
		int direction = newTrainLine.getTrainDirection();//上行0，下行1
		
		int time = 0;
		SortedTrain sourceSortedPlan = null,targetSortedPlan = null;
		
		if( sourceAnchor != null)
		{
			//System.out.println("删除时间01:"+sourceAnchor.getCurrentTime());
			time = UtilForTimeRectangle.xToTime(sourceAnchor.getCurrentTime());
			sourceSortedPlan = new SortedTrain(sourceAnchor.getTrainName(),direction,sourceAnchor.getTimeType(),time);//time是离站时间
		}
		
		if( targetAnchor != null)
		{
			time = UtilForTimeRectangle.xToTime(targetAnchor.getCurrentTime());
			targetSortedPlan = new SortedTrain(targetAnchor.getTrainName(),direction,targetAnchor.getTimeType(),time);//time是离站时间
		}
		
		switch(direction){
		case 1://下行1
			
			preTreeSet = stationTrainDownMap.get(prestationName);//获取指定车站的所有车次的信息
			treeSet = stationTrainDownMap.get(stationName);//获取指定车站的所有车次的信息
			if (preTreeSet != null)
			{
				//System.out.println("删除时间02:"+sourceSortedPlan.getTime());
				//System.out.println("preTreeSet_001::"+preTreeSet.size());
				preTreeSet.remove(sourceSortedPlan);
				
				resultFlag = true;
				//System.out.println("preTreeSet_003::"+preTreeSet.size());
				if (preTreeSet.isEmpty())//空
				{
					stationTrainDownMap.remove(prestationName);
				}
				else
				{
					stationTrainDownMap.put(prestationName,preTreeSet);
				}
				if( sourceSortedPlan != null)
					trainExist("down",sourceSortedPlan.getTrainName());
			}
			if (treeSet != null )
			{
				treeSet.remove(targetSortedPlan);
				resultFlag = true;
				
				if (treeSet.isEmpty())//空
				{
					stationTrainDownMap.remove(stationName);
				}
				else
				{
					stationTrainDownMap.put(stationName,treeSet);
				}
				
				if( targetSortedPlan != null)
					trainExist("down",targetSortedPlan.getTrainName());
			}
			break;
		case 0:
			preTreeSet = stationTrainUpMap.get(prestationName);
			treeSet = stationTrainUpMap.get(stationName);
			if (preTreeSet != null)
			{
				preTreeSet.remove(sourceSortedPlan);
				resultFlag = true;
				
				if (preTreeSet.isEmpty())//空
				{
					stationTrainUpMap.remove(prestationName);
				}
				else
				{
					stationTrainUpMap.put(prestationName,preTreeSet);
				}
				if( sourceSortedPlan != null)
					trainExist("up",sourceSortedPlan.getTrainName());
			}
			if (treeSet != null )
			{
				treeSet.remove(targetSortedPlan);
				resultFlag = true;
				
				if (treeSet.isEmpty())//空
				{
					stationTrainUpMap.remove(stationName);
				}
				else
				{
					stationTrainUpMap.put(stationName,treeSet);
				}
				if( targetSortedPlan != null)
					trainExist("up",targetSortedPlan.getTrainName());
			}
			break;
		}
		
		return resultFlag;
	}

	//处理用户新添加的车次信息
	public boolean appendStationTrainMap(LineFigure newTrainLine){
		boolean resultFlag = false;
		
		if (newTrainLine == null)//一般 不会出现
			return resultFlag;
		
		TreeSet<SortedTrain> newTreeSet = new TreeSet<SortedTrain>();
		String stationName = "";
		int time = 0;
		SortedTrain tempSortedPlan = null;
		int direction = newTrainLine.getTrainDirection();
		String trainName = newTrainLine.getTrainName();
		int timeType = Constants.TDCS_TIME_TYPE_NONE;
		int typeFlag = Constants.TDCS_TRAIN_TYPE_NEW;//新添加的车次信息
		LineAnchorFigure anchor = new LineAnchorFigure();
		
		switch(newTrainLine.getDrawPositionFlag()){
		
		case Constants.TDCS_TRAINLINE_START://开始
			stationName = newTrainLine.getPrestationName();
			if (direction == 1)//下行1
			{
				if (stationName.equalsIgnoreCase(lastStation)) 
				   return resultFlag;
			}
			else//上行
			{
				if (stationName.equalsIgnoreCase(firstStation)) 
				   return resultFlag;
			}
			
			preStationName = stationName;
			
			anchor = newTrainLine.getSource();
			if( anchor != null)//一般情况下，不会出现
			{
				time = UtilForTimeRectangle.xToTime(anchor.getCurrentTime());//将屏幕时间转换为实际时间
			}
			//String trainName, int trainDirection,int timeType,String time
			timeType = Constants.TDCS_TIME_TYPE_LEAVE;
		
			tempSortedPlan = new SortedTrain(typeFlag,trainName,direction,timeType,time);//time是离站时间
			break;
		case Constants.TDCS_TRAINLINE_END://结束
			stationName = newTrainLine.getStationName();
			if (direction == 1)//下行1
			{
				if (stationName.equalsIgnoreCase(firstStation)) 
				   return resultFlag;
			}
			else//上行
			{
				if (stationName.equalsIgnoreCase(lastStation)) 
				   return resultFlag;
			}
			
			//保证绘制车次的方向是从上一站到下一站
			int pIndex = sortedStationNameList.indexOf(preStationName);
			int cIndex = sortedStationNameList.indexOf(stationName);
			
			if (direction == 1)//下行1
			{
			  if (cIndex <= pIndex)
				return resultFlag;
			}
			else//上行
			{
				if (cIndex >= pIndex)
					return resultFlag;
			}
			
			anchor = newTrainLine.getTarget();
			if( anchor != null)//一般情况下，不会出现
				time = UtilForTimeRectangle.xToTime(anchor.getCurrentTime());//将屏幕时间转换为实际时间
			
			timeType = Constants.TDCS_TIME_TYPE_ARRIVEAL;
			tempSortedPlan = new SortedTrain(typeFlag,trainName,direction,timeType,time);//time是离站时间
			break;
		}//switch
		
		/*System.out.println("添加:"+tempSortedPlan.getTime() +":车次:"+ tempSortedPlan.getTrainName()+
				":方向:"+tempSortedPlan.getTrainDirection()+":时间类别:"+tempSortedPlan.getTimeType());*/
		newTreeSet = new TreeSet<SortedTrain>();
		
		//上行0，下行1
		if (direction == 1)
		{
			if (stationTrainDownMap.get(stationName) != null)//防止对treeSet赋null
				newTreeSet = stationTrainDownMap.get(stationName);//获取指定车站的所有车次的信息
		}
		else
		if (direction == 0)
		{
			if (stationTrainUpMap.get(stationName) != null)//防止对treeSet赋null
				newTreeSet = stationTrainUpMap.get(stationName);//获取指定车站的所有车次的信息
		}

		Iterator it = newTreeSet.iterator();
		while(it.hasNext()){//检查序列中是否还有元素
			SortedTrain data = (SortedTrain) it.next();//获得序列中的下一个元素
			if ( (data.getTrainName().equalsIgnoreCase(trainName))&&  //车次一致
				 (data.getTimeType() == tempSortedPlan.getTimeType()) )//已经存在该车次的信息
			{
				resultFlag = true;
				break;
			}
		}
        //进行添加操作     
		if(! resultFlag){//不存在
			newTreeSet.add(tempSortedPlan);
			switch(direction){
			case 1://下行1
				stationTrainDownMap.put(stationName,newTreeSet);
				break;
			case 0://上行
				stationTrainUpMap.put(stationName,newTreeSet);
				break;
			}
		}
		resultFlag = ! resultFlag;
		return resultFlag;
	}

	private int caclNewX(int minuteNo){
		int minuteX = minuteNo;
		if ( minuteNo <= 0 )
			minuteX = 0;
		else
		if( minuteNo >= constMaxX )
			minuteX = constMaxX;
		
	
		return minuteX;
	}
	
	//判断调整后的位置是否合理
	public Point calcTimeRectangleLocation(LineAnchorFigure timeRectangleFigure){
		
		//Point newPoint = null;//记录要调整到的新位置 或null(不调整)
	
		String stationName = timeRectangleFigure.getStationName();
		//String trainName = timeRectangleFigure.getTrainName();
		int oldX = timeRectangleFigure.getOldTime();
		int curY = timeRectangleFigure.getOffsetY();
		int curX = timeRectangleFigure.getCurrentTime();
		
		//TrainStation_x newKey = new TrainStation_x(stationName,trainName);
		
		//判断map中是否存在某个key时，用方法map.containsKey(key);返回true/false;
		//正常情况下,不会出现 
//		if(! scheduleTrainMap.containsKey(newKey)) //不存在,返回
	//		return newPoint;

		boolean resultFlag = false;
		//判断同一车站的其他车次同调整后车次的时间之间是否存在冲突
		if(timeRectangleFigure.getTrainDirection() == 1)//车次方向  下行1
		{
			if ( (stationTrainDownMap != null) &&
				 (! stationTrainDownMap.isEmpty())&&
				 (stationTrainDownMap.containsKey(stationName)) )
			{
				resultFlag = leftAndRightForTrain("Down",stationName,timeRectangleFigure);
			}
		}
		else//上行
		{
			if ( (stationTrainUpMap != null) &&
				 (! stationTrainUpMap.isEmpty()) &&
				 (stationTrainUpMap.containsKey(stationName)) )
			{
				resultFlag = leftAndRightForTrain("Up",stationName,timeRectangleFigure);//没有调试
			}
		}
		
		//判断同一车次调整后的时间同其他车站上的此车次的时间之间是否存在冲突  有无必要？？？？？
	/*	if (resultFlag)
			resultFlag = upAndDownForStation(stationName,timeRectangleFigure);
		*/
		
		if (! resultFlag){//调整后的位置不合适
			return new Point(oldX,curY);//定位到调整前的位置
		}
		else//调整后的位置合适 进行有关更新
		{
			//获取原来的值   暂时不用
			/*TrainStation_x trainStation = new TrainStation_x(stationName,trainName);
			TempTrain_x value = ScheduleDataset_x.getScheduleTrainMap(trainStation);
			value.trainType = Constants.TDCS_MENU_TOOL_RECTANGLE_ADJUST;
			int timeType = timeRectangleFigure.getTimeType();
			String timeX = TimeX_x.XToTime(curX);
			if (timeType == Constants.TDCS_TIME_TYPE_ARRIVEAL)//到站
			{
				value.Dispatch_arrivestationtime = timeX;
			}
			else
			{
				value.Dispatch_leavestationtime = timeX;
			}
			ScheduleDataset_x.putScheduleTrainMap(newKey,value);
			*/
		}
		//可以进行调整操作
		utilForStatics.adjustScheduleTrainMap(timeRectangleFigure,curX);
		
		return new Point(caclNewX(curX),curY);
	}

	//与同一车站的指定车次的前和后车次进行比较 下行1
	private boolean leftAndRightForTrain(String downUpFlag,String stationName,LineAnchorFigure timeRectangle){
		boolean resultFlag = false;
		
		if( (sortedStationNameList == null) || 
			(sortedStationNameList.size() == 0) )
			return resultFlag;

	//	int stationLength = sortedStationNameList.size();
		
		TreeSet<SortedTrain> treeSet = new TreeSet<SortedTrain>();
		
		//获取指定车站的所有车次的信息
		if ( downUpFlag.equalsIgnoreCase("down"))
			treeSet = stationTrainDownMap.get(stationName);
		else
			treeSet = stationTrainUpMap.get(stationName);
		
		if( (treeSet == null)||(treeSet.size() == 0) )
			return resultFlag;
	
		//treeset保证插入元素唯一和有序，转换为list是为了处理上方便
		List<SortedTrain> listSet = new ArrayList<SortedTrain>();
		Iterator it = treeSet.iterator();
		//int ii = 0;
		while(it.hasNext()){//检查序列中是否还有元素
			SortedTrain data = (SortedTrain) it.next();//获得序列中的下一个元素
			listSet.add(data );
			//System.out.println("顺序"+(ii++)+":车次:"+data.getTrainName()+":时间:"+data.getTime()+"("+data.getTimeType()+")");
		}
		
		SortedTrain curData = new SortedTrain();
		SortedTrain preData  = new SortedTrain(); 
		SortedTrain nextData = new SortedTrain();
		
		int len = listSet.size();
		int preX = 0;
		
		int maxX = locationX + hours *60*minuteStep - minuteStep/2;//最大值：locationX + 1440
		constMaxX = maxX; 
		
		//对给定车站stationName的所有车次信息进行逐一检查
		for(int i = 0; i < len; i++ )
		{
			boolean adjustFlag = false;
			
			curData = (SortedTrain)listSet.get(i);
			String curTrainName = curData.getTrainName();//车次信息
			int curTimeType = curData.getTimeType();
			int curTypeFlag = curData.getTypeFlag();
			int direction = curData.getTrainDirection();
			
			//System.out.println("direction:"+direction);//1下行
			
            //将屏幕时间转换为实际时间	
			int curX = UtilForTimeRectangle.xToTime(timeRectangle.getCurrentTime());
			
			
			//如果车次相等,需要进一步进行处理
			if(  curTrainName.equalsIgnoreCase(timeRectangle.getTrainName())//车次编号一致
				//)&& (curTimeType == timeRectangle.getTimeType())
			 )
			{
				int j = 0;
				
				//如果要调整的车次是到站车次 ,需要与离站时间进行对比 (同一车次)
				if (timeRectangle.getTimeType() == Constants.TDCS_TIME_TYPE_ARRIVEAL)//到站
				{
					for(j = 0; j < len; j++)
					{
						nextData = (SortedTrain)listSet.get(j);
						if ( (nextData.getTrainName().equalsIgnoreCase(timeRectangle.getTrainName())) && //车次编号一致 
							 (nextData.getTimeType() == Constants.TDCS_TIME_TYPE_LEAVE) //离站   
						   ) 
						{//最多只有一项满足要求
							maxX = nextData.getTime();
						    if(curX < maxX){
						    	adjustFlag = true;
						    }
						    break;
						}
						
					}//for
					if( j == len){//没有找到离站时间
					   	adjustFlag = true;
					}
				}else
				{//要调整的车次是到离站车次 ,需要与到站时间进行对比 (同一车次)
					for(j = 0; j < len; j++)
					{
						preData = (SortedTrain)listSet.get(j);
						if ( (preData.getTrainName().equalsIgnoreCase(timeRectangle.getTrainName())) && //车次编号一致 
							 (preData.getTimeType() == Constants.TDCS_TIME_TYPE_ARRIVEAL) //到站   
						   ) 
						{
							preX = preData.getTime();
						
							if(curX > preX){
							  	adjustFlag = true;
							}
						    break;
						}
					}//for
					if( j == len){//没有找到到站时间
					   	adjustFlag = true;
					}
				}
				
				//可以进行调整
				if (adjustFlag){
					/*
					System.out.println("////////////////////////////////////");
					Iterator it1 = treeSet.iterator();
					while(it1.hasNext()){//检查序列中是否还有元素
						SortedPlan_x data = (SortedPlan_x) it1.next();//获得序列中的下一个元素
						System.out.print("原来:"+data.getTrainName()+":"+data.getTime()+"("+data.getTimeType()+"):");
					}
					System.out.println();
					*/
					treeSet.remove(curData);
					/*
					it1 = treeSet.iterator();
					while(it1.hasNext()){//检查序列中是否还有元素
						SortedPlan_x data = (SortedPlan_x) it1.next();//获得序列中的下一个元素
						System.out.print("删除:"+data.getTrainName()+":"+data.getTime()+"("+data.getTimeType()+"):");
					}
					System.out.println();
					*/
					if (curTypeFlag == Constants.TDCS_TRAIN_TYPE_NO)
					{
						curData.setTypeFlag(Constants.TDCS_TRAIN_TYPE_ADJUST);//对原计划车次进行调整
					}
					
					//System.out.println("xbm_003:"+curX);
					
					curData.setTime(curX);
					treeSet.add(curData);
					/*
					it1 = treeSet.iterator();
					while(it1.hasNext()){//检查序列中是否还有元素
						SortedPlan_x data = (SortedPlan_x) it1.next();//获得序列中的下一个元素
						System.out.print("添加:"+data.getTrainName()+":"+ data.getTime()+"("+data.getTimeType()+"):");
					}
					System.out.println();
					*/
					if ( downUpFlag.equalsIgnoreCase("down"))
						stationTrainDownMap.put(stationName,treeSet);
					else
						stationTrainUpMap.put(stationName,treeSet);
					
					resultFlag = true;
					break;
				}
				
			}//if
		}//for
		
		return resultFlag;
	}
	
	
	//用于与同一车次的指定车站的的上一站和下一站进行比较
	private boolean upAndDownForStation(String stationName,LineAnchorFigure timeRectangle){
		boolean resultFlag = false;
		
		if( (sortedStationNameList == null) || 
			(sortedStationNameList.size() == 0) )
			return resultFlag;

		int stationLength = sortedStationNameList.size();
		
		String curStationName,preStationName="", nextStationName=""; 
		for(int i = 0; i < stationLength; i++ )
		{
			curStationName = sortedStationNameList.get(i);
			if(  (curStationName.equalsIgnoreCase(stationName))	 )
			{
				if (i > 0){
					preStationName = sortedStationNameList.get(i - 1);//前站
				}
				if (i < stationLength - 1){
					nextStationName = sortedStationNameList.get(i + 1);//后站
				}
				System.out.println("preStationName:"+preStationName);
				System.out.println("nextStationName:"+nextStationName);
				
			}//if
		
		}//for
		return resultFlag;
	}
	
	public void updateScheduleTrainMap(){
		//获取原来的值
	/*	TrainStation_x trainStation = new TrainStation_x(stationName,trainName);
		TempTrain_x value = ScheduleDataset_x.getScheduleTrainMap(trainStation);
		value.trainType = Constants.TDCS_MENU_TOOL_RECTANGLE_ADJUST;
		int timeType = timeRectangleFigure.getTimeType();
		String timeX = TimeX_x.XToTime(curX);
		if (timeType == Constants.TDCS_TIME_TYPE_ARRIVEAL)//到站
		{
			value.Dispatch_arrivestationtime = timeX;
		}
		else
		{
			value.Dispatch_leavestationtime = timeX;
		}
		//System.out.println("oldX:"+oldX);
		//System.out.println("DateUtil.timeToStr(oldX):"+timeX);
		ScheduleDataset_x.putScheduleTrainMap(newKey,value);			
	*/
	}
	
	
	
	
	
}
