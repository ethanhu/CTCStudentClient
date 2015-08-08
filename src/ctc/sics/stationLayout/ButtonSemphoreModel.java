package ctc.sics.stationLayout;

import ctc.sics.road.*;

public class ButtonSemphoreModel {

	public static SemphoreBasicInfo semphoreBasicInfo;
	public static String semphoreType;
	
	//按钮与信号及变化
	public static void semphoreColorModel(String semphoreName, String color){
		
		//System.out.println("名称为：" + semphoreName + "  颜色为：" + color);
		
		if((DrawStation.semphoreBasicInfoList == null)||(DrawStation.semphoreBasicInfoList.size() == 0)){
			return;
		}		
		
		int len1 = DrawStation.semphoreBasicInfoList.size();
		if(len1 >= 1){
			for(int i = 0; i < len1; i++){
				if(DrawStation.semphoreBasicInfoList.get(i).getSemphoreName().equalsIgnoreCase(semphoreName)){
					
					//System.out.println("找到：" + i);
					
					semphoreBasicInfo = DrawStation.semphoreBasicInfoList.get(i);
					semphoreType = DrawStation.semphoreBasicInfoList.get(i).getSemphoreType();
					
					//System.out.println("类型为：" + semphoreType);
					
					
					ButtonSemphoreModel.changeSemphoreColor(semphoreType, semphoreName, color);
				}				
			}
		}
	}	
	
	//按钮与信号及变化
	public static void allSemphoreColorModel(String color){

		if((DrawStation.semphoreBasicInfoList == null)||(DrawStation.semphoreBasicInfoList.size() == 0)){
			return;
		}		
		
		int len1 = DrawStation.semphoreBasicInfoList.size();
		if(len1 >= 1){
			for(int i = 0; i < len1; i++){
				semphoreBasicInfo = DrawStation.semphoreBasicInfoList.get(i);
				String sempName = DrawStation.semphoreBasicInfoList.get(i).getSemphoreName();
				semphoreType = DrawStation.semphoreBasicInfoList.get(i).getSemphoreType();
				ButtonSemphoreModel.changeSemphoreColor(semphoreType, sempName, color);
								
			}
		}
	}	
	
	/**
	 * 信号及颜色变化
	 * @param semphoreType
	 * @param semphoreName
	 * @param color
	 */
	public static void changeSemphoreColor(String semphoreType, String semphoreName, String color){
		
		if(semphoreType.equalsIgnoreCase("SemaphoreDoubleL")){ // 开口向左的信号机(双灯)
			
			//System.out.println("类型为：" + semphoreType);
			

			if((DrawStation.semaphoreDoubleLList == null)||(DrawStation.semaphoreDoubleLList.size() == 0)){
				return;
			}		
						
			int len = DrawStation.semaphoreDoubleLList.size();
			if(len >= 1){
				for(int i = 0; i < len; i++){
					if(DrawStation.semaphoreDoubleLList.get(i).getSemaphoreName().equalsIgnoreCase(semphoreName)){
						DrawStation.semaphoreDoubleLList.get(i).semaphoreColor(color);
					}
				}
			}			
		}
		
		if(semphoreType.equalsIgnoreCase("semaphoreDoubleR")){ // 开口向右的信号机(双灯)

			if((DrawStation.semaphoreDoubleRList == null)||(DrawStation.semaphoreDoubleRList.size() == 0)){
				return;
			}		
				
			int len = DrawStation.semaphoreDoubleRList.size();
			if(len >= 1){
				for(int i = 0; i < len; i++){
					if(DrawStation.semaphoreDoubleRList.get(i).getSemaphoreName().equalsIgnoreCase(semphoreName)){
						DrawStation.semaphoreDoubleRList.get(i).semaphoreColor(color);
					}
				}
			}			
		}
		
		if(semphoreType.equalsIgnoreCase("semaphoreSimpleL")){ // 开口向左的信号机(单灯)
			
			if((DrawStation.semaphoreSimpleLList == null)||(DrawStation.semaphoreSimpleLList.size() == 0)){
				return;
			}		
				
			int len = DrawStation.semaphoreSimpleLList.size();
			if(len >= 1){
				for(int i = 0; i < len; i++){
					if(DrawStation.semaphoreSimpleLList.get(i).getSemaphoreName().equalsIgnoreCase(semphoreName)){
						DrawStation.semaphoreSimpleLList.get(i).semaphoreColor(color);
					}
				}
			}			
		}
		
		if(semphoreType.equalsIgnoreCase("semaphoreSimpleR")){ // 开口向右的信号机(单灯)
			
			if((DrawStation.semaphoreSimpleRList == null)||(DrawStation.semaphoreSimpleRList.size() == 0)){
				return;
			}		
							
			int len = DrawStation.semaphoreSimpleRList.size();
			if(len >= 1){
				for(int i = 0; i < len; i++){
					if(DrawStation.semaphoreSimpleRList.get(i).getSemaphoreName().equalsIgnoreCase(semphoreName)){
						DrawStation.semaphoreSimpleRList.get(i).semaphoreColor(color);
					}
				}
			}			
		}
				
	}
}
