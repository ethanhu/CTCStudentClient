package ctc.sics.stationLayout;

import java.util.ArrayList;

import ctc.sics.figure.*;
import ctc.sics.road.*;

public class SortRoadInfoList {

	
	
	/**
	 * 对每一个路径list进行排序
	 */
	public static void sortRoadInfoList(){
		
		SortRoadInfoList.sortList(); //将sortList中的roadBasicInfo对象按照路的顺序，转存到roadSortInfoList中
		
/*		
		int roadListLength = DrawStation.roadList.size();
		if( roadListLength >= 1 ){
			for(int i = 0; i < roadListLength; i++){
				ArrayList<RoadBasicInfo> roadInfo = DrawStation.roadList.get(i);
				int len = roadInfo.size();
				if( len >= 1){
					System.out.println("\n");
					for(int j = 0; j < len; j++){
						RoadBasicInfo basicInfo = roadInfo.get(j);						
						System.out.println(j + "  Type: " + basicInfo.getRoadType() + " + " + "Name：" + basicInfo.getRoadName());
					}
				}else{
					System.out.println("路径存在空路径！" + DrawStation.roadList.get(i));
				}
			}
		}else{
			System.out.println("roadList为空！");
		}	
*/

	}
	
	/**
	 * 对路径list的元素进行排序
	 * @param sortList
	 */
	public static void sortList(){
		
		//数组中存储的是roadList中各个道路的对应顺序ID
		int r_xla_x1laArray[] = {1,2,3,9,4,0,5,6,7,8};
		DrawStation.roadIDList.add(r_xla_x1laArray);
		int r_x1la_xlfaArray[] = {1,2,3,4,0,5,9,6,7,8};
		DrawStation.roadIDList.add(r_x1la_xlfaArray);
		int r_xla_x2laArray[] = {0,1,2,3,4,6,5,7,8,9};
		DrawStation.roadIDList.add(r_xla_x2laArray);
		int r_x2la_xlfaArray[] = {4,5,6,7,8,9,3,0,1,2};
		DrawStation.roadIDList.add(r_x2la_xlfaArray);
		
		int r_xla_x3laArray[] = {5,6,7,9,8,1,0,2,3,4};
		DrawStation.roadIDList.add(r_xla_x3laArray);
		int r_x3la_xlfaArray[] = {0,1,2,3,4,5,9,6,7,8};
		DrawStation.roadIDList.add(r_x3la_xlfaArray);
		int r_xla_x4laArray[] = {0,1,2,3,4,5,6,7,8,9};
		DrawStation.roadIDList.add(r_xla_x4laArray);
		int r_x4la_xlfaArray[] = {6,7,8,9,4,5,3,0,1,2};
		DrawStation.roadIDList.add(r_x4la_xlfaArray);
		
		int r_sla_s1laArray[] = {9,8,7,6,5,0,4,3,2,1};
		DrawStation.roadIDList.add(r_sla_s1laArray);
		int r_s1la_slfaArray[] = {5,4,3,2,0,1,6,9,8,7};
		DrawStation.roadIDList.add(r_s1la_slfaArray);
		int r_sla_s2laArray[] = {9,8,7,0,6,5,4,3,2,1};
		DrawStation.roadIDList.add(r_sla_s2laArray);
		int r_s2la_slfaArray[] = {9,8,7,5,6,4,0,3,2,1};
		DrawStation.roadIDList.add(r_s2la_slfaArray);
		
		int r_sla_s3laArray[] = {9,8,7,6,5,4,3,2,1,0};
		DrawStation.roadIDList.add(r_sla_s3laArray);
		int r_s3la_slfaArray[] = {4,3,2,0,1,5,6,9,8,7};
		DrawStation.roadIDList.add(r_s3la_slfaArray);
		int r_sla_s4laArray[] = {5,4,3,0,2,1,9,8,7,6};
		DrawStation.roadIDList.add(r_sla_s4laArray);
		int r_s4la_slfaArray[] = {9,8,7,6,5,4,0,3,2,1};
		DrawStation.roadIDList.add(r_s4la_slfaArray);
		
		int r_xta_xlfaArray[] = {2,3,4,14,5,0,6,7,8,9,1,10,15,11,12,13};
		DrawStation.roadIDList.add(r_xta_xlfaArray);
		int r_sta_slfaArray[] = {15,14,13,1,12,11,10,9,8,6,7,5,0,4,3,2};
		DrawStation.roadIDList.add(r_sta_slfaArray);
		
		//将sortList中的roadBasicInfo对象按照路的顺序，转存到roadSortInfoList中
		int roadIDListLength = DrawStation.roadIDList.size();
		if( roadIDListLength >= 1 ){
			for(int i = 0; i < roadIDListLength; i++){
				int roadInfoArray[] = DrawStation.roadIDList.get(i);
				ArrayList<RoadBasicInfo> roadInfo = DrawStation.roadList.get(i);
				ArrayList<RoadBasicInfo> roadSortInfo = DrawStation.roadSortList.get(i);
				int len = roadInfoArray.length;
				if( len >= 1){
					for(int j = 0; j < len; j++){
						roadSortInfo.add(roadInfo.get(roadInfoArray[j]));
					}
				}else{
					System.out.println("路径存在空路径！" + DrawStation.roadList.get(i));
				}
			}
		}else{
			System.out.println("roadSortList为空！");
		}		
		
/*		
		System.out.println("排序后：");
		int roadSortListLength = DrawStation.roadSortList.size();
		if( roadSortListLength >= 1 ){
			for(int i = 0; i < roadSortListLength; i++){
				
				System.out.println("顺序：" + i);
				
				ArrayList<RoadBasicInfo> roadSortInfo = DrawStation.roadSortList.get(i);
				int len = roadSortInfo.size();
				if( len >= 1){
					System.out.println("\n");
					for(int j = 0; j < len; j++){
						RoadBasicInfo basicInfo = roadSortInfo.get(j);						
						System.out.println(j + "  Type: " + basicInfo.getRoadType() + " + " + "Name：" + basicInfo.getRoadName());
					}
				}else{
					System.out.println("路径存在空路径！" + DrawStation.roadList.get(i));
				}
			}
		}else{
			System.out.println("roadSortList为空！");
		}
*/		

		
	}
	
	
	
}
