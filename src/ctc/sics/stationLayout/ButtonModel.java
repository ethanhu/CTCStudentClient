package ctc.sics.stationLayout;


public class ButtonModel {

	//道岔2/4单操
	public static void Button_2_4Action(){
			
		if(DrawTrackButtonX.b_sdc == 1){
			
			if((DrawStation.turnoutDoubleList == null)||(DrawStation.turnoutDoubleList.size() == 0)){
				return;
			}
			
			int len = DrawStation.turnoutDoubleList.size();
			if(len >= 1){
				for(int i = 0; i <len; i++){
					if(DrawStation.turnoutDoubleList.get(i).getTurnoutName().equalsIgnoreCase("2/4_6/8")){
						if(DrawStation.turnoutDoubleList.get(i).getStatus() == 0){
							if(DrawStation.turnoutDoubleList.get(i).getTurnoutStatus() == 1){
								DrawStation.turnoutDoubleList.get(i).setColorStatus("blueF");
							}else{
								DrawStation.turnoutDoubleList.get(i).setColorStatus("blueZ");
							}
						}
						DrawTrackButtonX.b_sdc = 0;
					}
				} 
			}
		}
		
	}

	//道岔6/8单操
	public static void Button_6_8Action(){
		if(DrawTrackButtonX.b_sdc == 1){
			
			if((DrawStation.turnoutDoubleList == null)||(DrawStation.turnoutDoubleList.size() == 0)){
				return;
			}
			
			int len = DrawStation.turnoutDoubleList.size();
			if(len >= 1){
				for(int i = 0; i <len; i++){
					if(DrawStation.turnoutDoubleList.get(i).getTurnoutName().equalsIgnoreCase("2/4_6/8")){
						if(DrawStation.turnoutDoubleList.get(i).getStatus() == 0){
							if(DrawStation.turnoutDoubleList.get(i).getTurnoutStatus() == 1){
								DrawStation.turnoutDoubleList.get(i).setColorStatus("blueF");
							}else{
								DrawStation.turnoutDoubleList.get(i).setColorStatus("blueZ");
							}
						}
					}
					DrawTrackButtonX.b_sdc = 0;
				} 
			}
		}
	}

	//道岔10单操
	public static void Button_10Action(){
		
		if(DrawTrackButtonX.b_sdc == 1){
			
			if((DrawStation.turnoutRSList == null)||(DrawStation.turnoutRSList.size() == 0)){
				return;
			}
						
			int len = DrawStation.turnoutRSList.size();
			if(len >= 1){
				for(int i = 0; i <len; i++){
					if(DrawStation.turnoutRSList.get(i).getTurnoutName().equalsIgnoreCase("10")){
						if(DrawStation.turnoutRSList.get(i).getStatus() == 0){
							if(DrawStation.turnoutRSList.get(i).getTurnoutStatus() == 1){
								DrawStation.turnoutRSList.get(i).setColorStatus("blueF");
							}else{
								DrawStation.turnoutRSList.get(i).setColorStatus("blueZ");
							}
						}
						DrawTrackButtonX.b_sdc = 0;
					}
				} 
			}
		}		
		
	}

	//道岔12单操
	public static void Button_12Action(){
		
		if(DrawTrackButtonX.b_sdc == 1){

			if((DrawStation.turnoutRXList == null)||(DrawStation.turnoutRXList.size() == 0)){
				return;
			}
				
			int len = DrawStation.turnoutRXList.size();
			if(len >= 1){
				for(int i = 0; i <len; i++){
					if(DrawStation.turnoutRXList.get(i).getTurnoutName().equalsIgnoreCase("12")){
						if(DrawStation.turnoutRXList.get(i).getStatus() == 0){
							if(DrawStation.turnoutRXList.get(i).getTurnoutStatus() == 1){
								DrawStation.turnoutRXList.get(i).setColorStatus("blueF");
							}else{
								DrawStation.turnoutRXList.get(i).setColorStatus("blueZ");
							}
						}
						DrawTrackButtonX.b_sdc = 0;
					}
				} 
			}
		}
	}
	
	//道岔1/3单操
	public static void Button_1_3Action(){
				
		if(DrawTrackButtonS.b_xdc == 1){

			if((DrawStation.turnoutDoubleList == null)||(DrawStation.turnoutDoubleList.size() == 0)){
				return;
			}
				
			int len = DrawStation.turnoutDoubleList.size();
			if(len >= 1){
				for(int i = 0; i <len; i++){
					if(DrawStation.turnoutDoubleList.get(i).getTurnoutName().equalsIgnoreCase("1/3_5/7")){
						if(DrawStation.turnoutDoubleList.get(i).getStatus() == 0){
							if(DrawStation.turnoutDoubleList.get(i).getTurnoutStatus() == 1){
								DrawStation.turnoutDoubleList.get(i).setColorStatus("blueF");
							}else{
								DrawStation.turnoutDoubleList.get(i).setColorStatus("blueZ");
							}							
						}
						DrawTrackButtonS.b_xdc = 0;
					}
				} 
			}
		}
		
	}

	//道岔5/7单操
	public static void Button_5_7Action(){
		
		if(DrawTrackButtonS.b_xdc == 1){

			if((DrawStation.turnoutDoubleList == null)||(DrawStation.turnoutDoubleList.size() == 0)){
				return;
			}
				
			int len = DrawStation.turnoutDoubleList.size();
			if(len >= 1){
				for(int i = 0; i <len; i++){
					if(DrawStation.turnoutDoubleList.get(i).getTurnoutName().equalsIgnoreCase("1/3_5/7")){
						if(DrawStation.turnoutDoubleList.get(i).getStatus() == 0){
							if(DrawStation.turnoutDoubleList.get(i).getTurnoutStatus() == 1){
								DrawStation.turnoutDoubleList.get(i).setColorStatus("blueF");
							}else{
								DrawStation.turnoutDoubleList.get(i).setColorStatus("blueZ");
							}							
						}
						DrawTrackButtonS.b_xdc = 0;
					}
				} 
			}
		}
	}

	//道岔9单操
	public static void Button_9Action(){

		if(DrawTrackButtonS.b_xdc == 1){

			if((DrawStation.turnoutLSList == null)||(DrawStation.turnoutLSList.size() == 0)){
				return;
			}
				
			int len = DrawStation.turnoutLSList.size();
			if(len >= 1){
				for(int i = 0; i <len; i++){
					if(DrawStation.turnoutLSList.get(i).getTurnoutName().equalsIgnoreCase("9")){
						if(DrawStation.turnoutLSList.get(i).getStatus() == 0){
							if(DrawStation.turnoutLSList.get(i).getTurnoutStatus() == 1){
								DrawStation.turnoutLSList.get(i).setColorStatus("blueF");
							}else{
								DrawStation.turnoutLSList.get(i).setColorStatus("blueZ");
							}
						}
					}
					DrawTrackButtonS.b_xdc = 0;
				} 
			}
		}
		
	}

	//道岔11单操
	public static void Button_11Action(){
		
		if(DrawTrackButtonS.b_xdc == 1){

			if((DrawStation.turnoutLXList == null)||(DrawStation.turnoutLXList.size() == 0)){
				return;
			}
				
			int len = DrawStation.turnoutLXList.size();
			if(len >= 1){
				for(int i = 0; i <len; i++){
					if(DrawStation.turnoutLXList.get(i).getTurnoutName().equalsIgnoreCase("11")){
						if(DrawStation.turnoutLXList.get(i).getStatus() == 0){
							if(DrawStation.turnoutLXList.get(i).getTurnoutStatus() == 1){
								DrawStation.turnoutLXList.get(i).setColorStatus("blueF");
							}else{
								DrawStation.turnoutLXList.get(i).setColorStatus("blueZ");
							}
						}
						DrawTrackButtonS.b_xdc = 0;
					}
				} 
			}
		}
	}
		
	//X道岔总定位
	public static void Button_xdczdwAction(){
		
		if(DrawTurnoutButtonX.b_xdczdw == 1){
			
			//1/3/5/7
			if((DrawStation.turnoutDoubleList == null)&&(DrawStation.turnoutDoubleList.size() == 0)){
				return;
			}else{
				
				int len1 = DrawStation.turnoutDoubleList.size();
				for(int i = 0; i <len1; i++){
					if(DrawStation.turnoutDoubleList.get(i).getTurnoutName().equalsIgnoreCase("1/3_5/7")){
						if(DrawStation.turnoutDoubleList.get(i).getStatus() == 0){
							DrawStation.turnoutDoubleList.get(i).setColorStatus("blueZ");
						}
					}
				} 
			
			}
			
			//9
			if((DrawStation.turnoutLSList == null)&&(DrawStation.turnoutLSList.size() == 0)){
				return;
			}else{
				
				int len2 = DrawStation.turnoutLSList.size();
				
				for(int i = 0; i <len2; i++){
					if(DrawStation.turnoutLSList.get(i).getTurnoutName().equalsIgnoreCase("9")){
						if(DrawStation.turnoutLSList.get(i).getStatus() == 0){
							DrawStation.turnoutLSList.get(i).setColorStatus("blueZ");							
						}
					}
				} 				
			}
			
			//11
			if((DrawStation.turnoutLXList == null)&&(DrawStation.turnoutLXList.size() == 0)){
				return;
			}else{
				
				int len3 = DrawStation.turnoutLXList.size();
				
				for(int i = 0; i <len3; i++){
					if(DrawStation.turnoutLXList.get(i).getTurnoutName().equalsIgnoreCase("11")){
						if(DrawStation.turnoutLXList.get(i).getStatus() == 0){
							DrawStation.turnoutLXList.get(i).setColorStatus("blueZ");
						}
					}
				} 				
			}				
		}
		
	} 
	
	//X道岔总反位
	public static void Button_xdczfwAction(){
		
		if(DrawTurnoutButtonX.b_xdczfw == 1){
			
			//1/3/5/7
			if((DrawStation.turnoutDoubleList == null)&&(DrawStation.turnoutDoubleList.size() == 0)){
				return;
			}else{
				
				int len1 = DrawStation.turnoutDoubleList.size();
				for(int i = 0; i <len1; i++){
					if(DrawStation.turnoutDoubleList.get(i).getTurnoutName().equalsIgnoreCase("1/3_5/7")){
						if(DrawStation.turnoutDoubleList.get(i).getStatus() == 0){
							DrawStation.turnoutDoubleList.get(i).setColorStatus("blueF");
						}
					}
				} 
			
			}
			
			//9
			if((DrawStation.turnoutLSList == null)&&(DrawStation.turnoutLSList.size() == 0)){
				return;
			}else{
				
				int len2 = DrawStation.turnoutLSList.size();
				
				for(int i = 0; i <len2; i++){
					if(DrawStation.turnoutLSList.get(i).getTurnoutName().equalsIgnoreCase("9")){
						if(DrawStation.turnoutLSList.get(i).getStatus() == 0){
							DrawStation.turnoutLSList.get(i).setColorStatus("blueF");							
						}
					}
				} 				
			}
			
			//11
			if((DrawStation.turnoutLXList == null)&&(DrawStation.turnoutLXList.size() == 0)){
				return;
			}else{
				
				int len3 = DrawStation.turnoutLXList.size();
				
				for(int i = 0; i <len3; i++){
					if(DrawStation.turnoutLXList.get(i).getTurnoutName().equalsIgnoreCase("11")){
						if(DrawStation.turnoutLXList.get(i).getStatus() == 0){
							DrawStation.turnoutLXList.get(i).setColorStatus("blueF");
						}
					}
				} 				
			}				
		}
		
	} 
	
	//S道岔总反位
	public static void Button_sdczdwAction(){
		
		if(DrawTurnoutButtonS.b_sdczdw == 1){
			
			//2/4/6/8
			if((DrawStation.turnoutDoubleList == null)&&(DrawStation.turnoutDoubleList.size() == 0)){
				return;
			}else{
				
				int len1 = DrawStation.turnoutDoubleList.size();
				for(int i = 0; i <len1; i++){
					if(DrawStation.turnoutDoubleList.get(i).getTurnoutName().equalsIgnoreCase("2/4_6/8")){
						if(DrawStation.turnoutDoubleList.get(i).getStatus() == 0){
							DrawStation.turnoutDoubleList.get(i).setColorStatus("blueZ");
						}
					}
				} 
			
			}
			
			//10
			if((DrawStation.turnoutRSList == null)&&(DrawStation.turnoutRSList.size() == 0)){
				return;
			}else{
				
				int len2 = DrawStation.turnoutRSList.size();
				
				for(int i = 0; i <len2; i++){
					if(DrawStation.turnoutRSList.get(i).getTurnoutName().equalsIgnoreCase("10")){
						if(DrawStation.turnoutRSList.get(i).getStatus() == 0){
							DrawStation.turnoutRSList.get(i).setColorStatus("blueZ");							
						}
					}
				} 				
			}
			
			//12
			if((DrawStation.turnoutRXList == null)&&(DrawStation.turnoutRXList.size() == 0)){
				return;
			}else{
				
				int len3 = DrawStation.turnoutRXList.size();
				
				for(int i = 0; i <len3; i++){
					if(DrawStation.turnoutRXList.get(i).getTurnoutName().equalsIgnoreCase("12")){
						if(DrawStation.turnoutRXList.get(i).getStatus() == 0){
							DrawStation.turnoutRXList.get(i).setColorStatus("blueZ");
						}
					}
				} 				
			}		
			
		}
	} 
	
	//S道岔总反位
	public static void Button_sdczfwAction(){
		
		if(DrawTurnoutButtonS.b_sdczfw == 1){
			
			//2/4/6/8
			if((DrawStation.turnoutDoubleList == null)&&(DrawStation.turnoutDoubleList.size() == 0)){
				return;
			}else{
				
				int len1 = DrawStation.turnoutDoubleList.size();
				for(int i = 0; i <len1; i++){
					if(DrawStation.turnoutDoubleList.get(i).getTurnoutName().equalsIgnoreCase("2/4_6/8")){
						if(DrawStation.turnoutDoubleList.get(i).getStatus() == 0){
							DrawStation.turnoutDoubleList.get(i).setColorStatus("blueF");
						}
					}
				} 
			
			}
			
			//10
			if((DrawStation.turnoutRSList == null)&&(DrawStation.turnoutRSList.size() == 0)){
				return;
			}else{
				
				int len2 = DrawStation.turnoutRSList.size();
				
				for(int i = 0; i <len2; i++){
					if(DrawStation.turnoutRSList.get(i).getTurnoutName().equalsIgnoreCase("10")){
						if(DrawStation.turnoutRSList.get(i).getStatus() == 0){
							DrawStation.turnoutRSList.get(i).setColorStatus("blueF");							
						}
					}
				} 				
			}
			
			//12
			if((DrawStation.turnoutRXList == null)&&(DrawStation.turnoutRXList.size() == 0)){
				return;
			}else{
				
				int len3 = DrawStation.turnoutRXList.size();
				
				for(int i = 0; i <len3; i++){
					if(DrawStation.turnoutRXList.get(i).getTurnoutName().equalsIgnoreCase("12")){
						if(DrawStation.turnoutRXList.get(i).getStatus() == 0){
							DrawStation.turnoutRXList.get(i).setColorStatus("blueF");
						}
					}
				} 				
			}		
			
		}
	} 
	
	
}
