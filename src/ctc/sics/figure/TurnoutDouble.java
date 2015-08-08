package ctc.sics.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Point;

public class TurnoutDouble extends PolylineConnection {

	private int status = 0; // 道岔的状态：可使用0, 锁定中1, 坏了:-1
	private int statusL = 0; // 上半部看的左边道岔的状态：可使用0, 锁定中1, 坏了:-1
	private int statucR = 0; // 上半部看的右边道岔的状态：可使用0, 锁定中1, 坏了:-1
	public int turnoutStatus = 1; // 道岔状态：定位1, 反位0

	private String turnoutName; // 道岔名称
	private String turnoutNameL; // 上半部看的左边道岔名称
	private String turnoutNameR; // 上半部看的右边道岔名称

	private PolylineConnection mainLineL = new PolylineConnection(); //主道岔(左倾斜);
	private PolylineConnection mainLineR = new PolylineConnection(); //主道岔(左倾斜);

	private PolylineConnection trackLineS = new PolylineConnection(); // 股道(上)
	private PolylineConnection trackLineX = new PolylineConnection(); // 股道(下)

	private PolylineConnection trackLineSL = new PolylineConnection(); // 股道(上左)
	private PolylineConnection trackLineSR = new PolylineConnection(); //股道(上右);
	private PolylineConnection trackLineXL = new PolylineConnection(); //股道(下右);
	private PolylineConnection trackLineXR = new PolylineConnection(); //股道(下右);

	private PolylineConnection initLineSL = new PolylineConnection(); //初始小段(上左)
	private PolylineConnection initLineSR = new PolylineConnection(); //初始小段(上右)
	private PolylineConnection initLineXL = new PolylineConnection(); //初始小段(下左)
	private PolylineConnection initLineXR = new PolylineConnection(); //初始小段(下右)

	private PolylineConnection changeLineSL = new PolylineConnection(); //变化小段(上左
																		// )
	private PolylineConnection changeLineSR = new PolylineConnection(); //变化小段(上右
																		// )
	private PolylineConnection changeLineXL = new PolylineConnection(); //变化小段(下左
																		// )
	private PolylineConnection changeLineXR = new PolylineConnection(); //变化小段(下右
																		// )
	/**
	 * 新建双开联动道岔
	 */
	public TurnoutDouble(int h, int initX, int initY, Figure panel) {
		
		//主道岔(左倾斜)
		mainLineL.setForegroundColor(ColorConstants.blue);
		mainLineL.setStart(new Point(initX - h/3, initY - h/3));
		mainLineL.setEnd(new Point(initX + h/3, initY + h/3));
		mainLineL.setLineWidth(2);
		panel.add(mainLineL);
		//主道岔(左倾斜)
		mainLineR.setForegroundColor(ColorConstants.blue);
		mainLineR.setStart(new Point(initX + h/3, initY - h/3));
		mainLineR.setEnd(new Point(initX - h/3, initY + h/3));
		mainLineR.setLineWidth(2);
		panel.add(mainLineR);
		// 股道(上)
		trackLineS.setForegroundColor(ColorConstants.blue);
		trackLineS.setStart(new Point(initX - h/3, initY - h/2));
		trackLineS.setEnd(new Point(initX + h/3, initY - h/2));
		trackLineS.setLineWidth(2);
		panel.add(trackLineS);
		// 股道(下)
		trackLineX.setForegroundColor(ColorConstants.blue);
		trackLineX.setStart(new Point(initX - h/3, initY + h/2));
		trackLineX.setEnd(new Point(initX + h/3, initY + h/2));
		trackLineX.setLineWidth(2);
		panel.add(trackLineX); 
		// 股道(上左)
		trackLineSL.setForegroundColor(ColorConstants.blue);
		trackLineSL.setStart(new Point(initX - h/2, initY - h/2));
		trackLineSL.setEnd(new Point(initX - h/3, initY - h/2));
		trackLineSL.setLineWidth(2);
		panel.add(trackLineSL);  
		//股道(上右)
		trackLineSR.setForegroundColor(ColorConstants.blue);
		trackLineSR.setStart(new Point(initX + h/2, initY - h/2));
		trackLineSR.setEnd(new Point(initX + h/3, initY - h/2));
		trackLineSR.setLineWidth(2);
		panel.add(trackLineSR);  
		//股道(下右)
		trackLineXL.setForegroundColor(ColorConstants.blue);
		trackLineXL.setStart(new Point(initX - h/2, initY + h/2));
		trackLineXL.setEnd(new Point(initX - h/3, initY + h/2));
		trackLineXL.setLineWidth(2);
		panel.add(trackLineXL);   
		//股道(下右)
		trackLineXR.setForegroundColor(ColorConstants.blue);
		trackLineXR.setStart(new Point(initX + h/2, initY + h/2));
		trackLineXR.setEnd(new Point(initX + h/3, initY + h/2));
		trackLineXR.setLineWidth(2);
		panel.add(trackLineXR);  
		//初始小段(上左)
		initLineSL.setForegroundColor(ColorConstants.blue);
		initLineSL.setStart(new Point(initX - h/3, initY - h/3));
		initLineSL.setEnd(new Point(initX - h/2, initY - h/3));
		initLineSL.setLineWidth(2);
		panel.add(initLineSL); 
		//初始小段(上右)
		initLineSR.setForegroundColor(ColorConstants.blue);
		initLineSR.setStart(new Point(initX + h/3, initY - h/3));
		initLineSR.setEnd(new Point(initX + h/2, initY - h/3));
		initLineSR.setLineWidth(2);
		panel.add(initLineSR);  
		//初始小段(下左)
		initLineXL.setForegroundColor(ColorConstants.blue);
		initLineXL.setStart(new Point(initX - h/3, initY + h/3));
		initLineXL.setEnd(new Point(initX - h/2, initY + h/3));
		initLineXL.setLineWidth(2);
		panel.add(initLineXL); 
		//初始小段(下右)
		initLineXR.setForegroundColor(ColorConstants.blue);
		initLineXR.setStart(new Point(initX + h/3, initY + h/3));
		initLineXR.setEnd(new Point(initX + h/2, initY + h/3));
		initLineXR.setLineWidth(2);
		panel.add(initLineXR);  
		//变化小段(上左)
		changeLineSL.setForegroundColor(ColorConstants.white);
		changeLineSL.setStart(new Point(initX - h/3, initY - h/3));
		changeLineSL.setEnd(new Point(initX - h/2, initY - h/2));
		changeLineSL.setLineWidth(2);
		panel.add(changeLineSL);  
		//变化小段(上右)																	
		changeLineSR.setForegroundColor(ColorConstants.white);
		changeLineSR.setStart(new Point(initX + h/3, initY - h/3));
		changeLineSR.setEnd(new Point(initX + h/2, initY - h/2));
		changeLineSR.setLineWidth(2);
		panel.add(changeLineSR);
		//变化小段(下左)																	
		changeLineXL.setForegroundColor(ColorConstants.white);
		changeLineXL.setStart(new Point(initX - h/3, initY + h/3));
		changeLineXL.setEnd(new Point(initX - h/2, initY + h/2));
		changeLineXL.setLineWidth(2);
		panel.add(changeLineXL);  
		//变化小段(下右)																	
		changeLineXR.setForegroundColor(ColorConstants.white);
		changeLineXR.setStart(new Point(initX + h/3, initY + h/3));
		changeLineXR.setEnd(new Point(initX + h/2, initY + h/2));
		changeLineXR.setLineWidth(2);
		panel.add(changeLineXR); 

	}
	
	
	/**
	 * 改变道岔状态
	 */
	public void setColorStatus(String color) {			
		
		//正位蓝色
		if(color.equalsIgnoreCase("blueZ")){
			
			//设置道岔定/反位
			turnoutStatus = 1;
			//设置道岔状态
			status = 0;
			
			//主道岔(左倾斜)
			mainLineL.setForegroundColor(ColorConstants.blue);		
			//主道岔(左倾斜)
			mainLineR.setForegroundColor(ColorConstants.blue);		
			// 股道(上)
			trackLineS.setForegroundColor(ColorConstants.blue);		
			// 股道(下)
			trackLineX.setForegroundColor(ColorConstants.blue);		
			// 股道(上左)
			trackLineSL.setForegroundColor(ColorConstants.blue);		
			//股道(上右)
			trackLineSR.setForegroundColor(ColorConstants.blue);		
			//股道(下右)
			trackLineXL.setForegroundColor(ColorConstants.blue);		
			//股道(下右)
			trackLineXR.setForegroundColor(ColorConstants.blue);		
			//初始小段(上左)
			initLineSL.setForegroundColor(ColorConstants.blue);		
			//初始小段(上右)
			initLineSR.setForegroundColor(ColorConstants.blue);		
			//初始小段(下左)
			initLineXL.setForegroundColor(ColorConstants.blue);		
			//初始小段(下右)
			initLineXR.setForegroundColor(ColorConstants.blue);		 
			//变化小段(上左)
			changeLineSL.setForegroundColor(ColorConstants.white);		
			//变化小段(上右)																	
			changeLineSR.setForegroundColor(ColorConstants.white);	
			//变化小段(下左)																	
			changeLineXL.setForegroundColor(ColorConstants.white);		  
			//变化小段(下右)																	
			changeLineXR.setForegroundColor(ColorConstants.white);	
			
		}else
		//正位蓝色
		if(color.equalsIgnoreCase("blueZS")){
			
			//设置道岔定/反位
			turnoutStatus = 1;
			//设置道岔状态
			status = 0;
			
			//主道岔(左倾斜)
			mainLineL.setForegroundColor(ColorConstants.blue);		
			//主道岔(左倾斜)
			mainLineR.setForegroundColor(ColorConstants.blue);		
			// 股道(上)
			trackLineS.setForegroundColor(ColorConstants.blue);		
			// 股道(下)
			//trackLineX.setForegroundColor(ColorConstants.blue);		
			// 股道(上左)
			trackLineSL.setForegroundColor(ColorConstants.blue);		
			//股道(上右)
			trackLineSR.setForegroundColor(ColorConstants.blue);		
			//股道(下右)
			//trackLineXL.setForegroundColor(ColorConstants.blue);		
			//股道(下右)
			//trackLineXR.setForegroundColor(ColorConstants.blue);		
			//初始小段(上左)
			initLineSL.setForegroundColor(ColorConstants.blue);		
			//初始小段(上右)
			initLineSR.setForegroundColor(ColorConstants.blue);		
			//初始小段(下左)
			initLineXL.setForegroundColor(ColorConstants.blue);		
			//初始小段(下右)
			initLineXR.setForegroundColor(ColorConstants.blue);		 
			//变化小段(上左)
			changeLineSL.setForegroundColor(ColorConstants.white);		
			//变化小段(上右)																	
			changeLineSR.setForegroundColor(ColorConstants.white);	
			//变化小段(下左)																	
			changeLineXL.setForegroundColor(ColorConstants.white);		  
			//变化小段(下右)																	
			changeLineXR.setForegroundColor(ColorConstants.white);	
			
		}else
		//正位蓝色
		if(color.equalsIgnoreCase("blueZX")){
			
			//设置道岔定/反位
			turnoutStatus = 1;
			//设置道岔状态
			status = 0;
			
			//主道岔(左倾斜)
			mainLineL.setForegroundColor(ColorConstants.blue);		
			//主道岔(左倾斜)
			mainLineR.setForegroundColor(ColorConstants.blue);		
			// 股道(上)
			//trackLineS.setForegroundColor(ColorConstants.blue);		
			// 股道(下)
			trackLineX.setForegroundColor(ColorConstants.blue);		
			// 股道(上左)
			//trackLineSL.setForegroundColor(ColorConstants.blue);		
			//股道(上右)
			//trackLineSR.setForegroundColor(ColorConstants.blue);		
			//股道(下右)
			trackLineXL.setForegroundColor(ColorConstants.blue);		
			//股道(下右)
			trackLineXR.setForegroundColor(ColorConstants.blue);		
			//初始小段(上左)
			initLineSL.setForegroundColor(ColorConstants.blue);		
			//初始小段(上右)
			initLineSR.setForegroundColor(ColorConstants.blue);		
			//初始小段(下左)
			initLineXL.setForegroundColor(ColorConstants.blue);		
			//初始小段(下右)
			initLineXR.setForegroundColor(ColorConstants.blue);		 
			//变化小段(上左)
			changeLineSL.setForegroundColor(ColorConstants.white);		
			//变化小段(上右)																	
			changeLineSR.setForegroundColor(ColorConstants.white);	
			//变化小段(下左)																	
			changeLineXL.setForegroundColor(ColorConstants.white);		  
			//变化小段(下右)																	
			changeLineXR.setForegroundColor(ColorConstants.white);	
			
		}else
		//正位绿色(上)
		if(color.equalsIgnoreCase("greenZS")){

			//设置道岔定/反位
			turnoutStatus = 1;
			//设置道岔状态
			status = 0;
			
			//主道岔(左倾斜)
			mainLineL.setForegroundColor(ColorConstants.blue);		
			//主道岔(左倾斜)
			mainLineR.setForegroundColor(ColorConstants.blue);		
			// 股道(上)
			trackLineS.setForegroundColor(ColorConstants.green);		
			// 股道(下)
			//trackLineX.setForegroundColor(ColorConstants.blue);		
			// 股道(上左)
			trackLineSL.setForegroundColor(ColorConstants.green);		
			//股道(上右)
			trackLineSR.setForegroundColor(ColorConstants.green);		
			//股道(下右)
			//trackLineXL.setForegroundColor(ColorConstants.blue);		
			//股道(下右)
			//trackLineXR.setForegroundColor(ColorConstants.blue);		
			//初始小段(上左)
			initLineSL.setForegroundColor(ColorConstants.blue);		
			//初始小段(上右)
			initLineSR.setForegroundColor(ColorConstants.blue);		
			//初始小段(下左)
			initLineXL.setForegroundColor(ColorConstants.blue);		
			//初始小段(下右)
			initLineXR.setForegroundColor(ColorConstants.blue);		 
			//变化小段(上左)
			changeLineSL.setForegroundColor(ColorConstants.white);		
			//变化小段(上右)																	
			changeLineSR.setForegroundColor(ColorConstants.white);	
			//变化小段(下左)																	
			changeLineXL.setForegroundColor(ColorConstants.white);		  
			//变化小段(下右)																	
			changeLineXR.setForegroundColor(ColorConstants.white);	
			
		}else
		//正位绿色(下)
		if(color.equalsIgnoreCase("greenZX")){

			//设置道岔定/反位
			turnoutStatus = 1;
			//设置道岔状态
			status = 0;
			
			//主道岔(左倾斜)
			mainLineL.setForegroundColor(ColorConstants.blue);		
			//主道岔(左倾斜)
			mainLineR.setForegroundColor(ColorConstants.blue);		
			// 股道(上)
			//trackLineS.setForegroundColor(ColorConstants.blue);		
			// 股道(下)
			trackLineX.setForegroundColor(ColorConstants.green);		
			// 股道(上左)
			//trackLineSL.setForegroundColor(ColorConstants.blue);		
			//股道(上右)
			//trackLineSR.setForegroundColor(ColorConstants.blue);		
			//股道(下左)
			trackLineXL.setForegroundColor(ColorConstants.green);		
			//股道(下右)
			trackLineXR.setForegroundColor(ColorConstants.green);		
			//初始小段(上左)
			initLineSL.setForegroundColor(ColorConstants.blue);		
			//初始小段(上右)
			initLineSR.setForegroundColor(ColorConstants.blue);		
			//初始小段(下左)
			initLineXL.setForegroundColor(ColorConstants.blue);		
			//初始小段(下右)
			initLineXR.setForegroundColor(ColorConstants.blue);		 
			//变化小段(上左)
			changeLineSL.setForegroundColor(ColorConstants.white);		
			//变化小段(上右)																	
			changeLineSR.setForegroundColor(ColorConstants.white);	
			//变化小段(下左)																	
			changeLineXL.setForegroundColor(ColorConstants.white);		  
			//变化小段(下右)																	
			changeLineXR.setForegroundColor(ColorConstants.white);	
			
		}else
		//正位红色(上)
		if(color.equalsIgnoreCase("redZS")){

			//设置道岔定/反位
			turnoutStatus = 1;
			//设置道岔状态
			status = 0;
			
			//主道岔(左倾斜)
			mainLineL.setForegroundColor(ColorConstants.blue);		
			//主道岔(左倾斜)
			mainLineR.setForegroundColor(ColorConstants.blue);		
			// 股道(上)
			trackLineS.setForegroundColor(ColorConstants.red);		
			// 股道(下)
			//trackLineX.setForegroundColor(ColorConstants.blue);		
			// 股道(上左)
			trackLineSL.setForegroundColor(ColorConstants.red);		
			//股道(上右)
			trackLineSR.setForegroundColor(ColorConstants.red);		
			//股道(下右)
			//trackLineXL.setForegroundColor(ColorConstants.blue);		
			//股道(下右)
			//trackLineXR.setForegroundColor(ColorConstants.blue);		
			//初始小段(上左)
			initLineSL.setForegroundColor(ColorConstants.blue);		
			//初始小段(上右)
			initLineSR.setForegroundColor(ColorConstants.blue);		
			//初始小段(下左)
			initLineXL.setForegroundColor(ColorConstants.blue);		
			//初始小段(下右)
			initLineXR.setForegroundColor(ColorConstants.blue);		 
			//变化小段(上左)
			changeLineSL.setForegroundColor(ColorConstants.white);		
			//变化小段(上右)																	
			changeLineSR.setForegroundColor(ColorConstants.white);	
			//变化小段(下左)																	
			changeLineXL.setForegroundColor(ColorConstants.white);		  
			//变化小段(下右)																	
			changeLineXR.setForegroundColor(ColorConstants.white);	
			
		}else
		//正位红色(下)
		if(color.equalsIgnoreCase("redZX")){

			//设置道岔定/反位
			turnoutStatus = 1;
			//设置道岔状态
			status = 0;
			
			//主道岔(左倾斜)
			mainLineL.setForegroundColor(ColorConstants.blue);		
			//主道岔(左倾斜)
			mainLineR.setForegroundColor(ColorConstants.blue);		
			// 股道(上)
			//trackLineS.setForegroundColor(ColorConstants.blue);		
			// 股道(下)
			trackLineX.setForegroundColor(ColorConstants.red);		
			// 股道(上左)
			//trackLineSL.setForegroundColor(ColorConstants.blue);		
			//股道(上右)
			//trackLineSR.setForegroundColor(ColorConstants.blue);		
			//股道(下左)
			trackLineXL.setForegroundColor(ColorConstants.red);		
			//股道(下右)
			trackLineXR.setForegroundColor(ColorConstants.red);		
			//初始小段(上左)
			initLineSL.setForegroundColor(ColorConstants.blue);		
			//初始小段(上右)
			initLineSR.setForegroundColor(ColorConstants.blue);		
			//初始小段(下左)
			initLineXL.setForegroundColor(ColorConstants.blue);		
			//初始小段(下右)
			initLineXR.setForegroundColor(ColorConstants.blue);		 
			//变化小段(上左)
			changeLineSL.setForegroundColor(ColorConstants.white);		
			//变化小段(上右)																	
			changeLineSR.setForegroundColor(ColorConstants.white);	
			//变化小段(下左)																	
			changeLineXL.setForegroundColor(ColorConstants.white);		  
			//变化小段(下右)																	
			changeLineXR.setForegroundColor(ColorConstants.white);	
			
		}else
		//正位白色(上)
		if(color.equalsIgnoreCase("whiteZS")){

			//设置道岔定/反位
			turnoutStatus = 1;
			//设置道岔状态
			status = 0;
			
			//主道岔(左倾斜)
			mainLineL.setForegroundColor(ColorConstants.blue);		
			//主道岔(左倾斜)
			mainLineR.setForegroundColor(ColorConstants.blue);		
			// 股道(上)
			trackLineS.setForegroundColor(ColorConstants.white);		
			// 股道(下)
			//trackLineX.setForegroundColor(ColorConstants.blue);		
			// 股道(上左)
			trackLineSL.setForegroundColor(ColorConstants.white);		
			//股道(上右)
			trackLineSR.setForegroundColor(ColorConstants.white);		
			//股道(下右)
			//trackLineXL.setForegroundColor(ColorConstants.blue);		
			//股道(下右)
			//trackLineXR.setForegroundColor(ColorConstants.blue);		
			//初始小段(上左)
			initLineSL.setForegroundColor(ColorConstants.blue);		
			//初始小段(上右)
			initLineSR.setForegroundColor(ColorConstants.blue);		
			//初始小段(下左)
			initLineXL.setForegroundColor(ColorConstants.blue);		
			//初始小段(下右)
			initLineXR.setForegroundColor(ColorConstants.blue);		 
			//变化小段(上左)
			changeLineSL.setForegroundColor(ColorConstants.white);		
			//变化小段(上右)																	
			changeLineSR.setForegroundColor(ColorConstants.white);	
			//变化小段(下左)																	
			changeLineXL.setForegroundColor(ColorConstants.white);		  
			//变化小段(下右)																	
			changeLineXR.setForegroundColor(ColorConstants.white);	
			
		}else
		//正位白色(下)
		if(color.equalsIgnoreCase("whiteZX")){

			//设置道岔定/反位
			turnoutStatus = 1;
			//设置道岔状态
			status = 0;
			
			//主道岔(左倾斜)
			mainLineL.setForegroundColor(ColorConstants.blue);		
			//主道岔(左倾斜)
			mainLineR.setForegroundColor(ColorConstants.blue);		
			// 股道(上)
			//trackLineS.setForegroundColor(ColorConstants.blue);		
			// 股道(下)
			trackLineX.setForegroundColor(ColorConstants.white);		
			// 股道(上左)
			//trackLineSL.setForegroundColor(ColorConstants.blue);		
			//股道(上右)
			//trackLineSR.setForegroundColor(ColorConstants.blue);		
			//股道(下左)
			trackLineXL.setForegroundColor(ColorConstants.white);		
			//股道(下右)
			trackLineXR.setForegroundColor(ColorConstants.white);		
			//初始小段(上左)
			initLineSL.setForegroundColor(ColorConstants.blue);		
			//初始小段(上右)
			initLineSR.setForegroundColor(ColorConstants.blue);		
			//初始小段(下左)
			initLineXL.setForegroundColor(ColorConstants.blue);		
			//初始小段(下右)
			initLineXR.setForegroundColor(ColorConstants.blue);		 
			//变化小段(上左)
			changeLineSL.setForegroundColor(ColorConstants.white);		
			//变化小段(上右)																	
			changeLineSR.setForegroundColor(ColorConstants.white);	
			//变化小段(下左)																	
			changeLineXL.setForegroundColor(ColorConstants.white);		  
			//变化小段(下右)																	
			changeLineXR.setForegroundColor(ColorConstants.white);	
			
		}else
		//蓝色反位
		if(color.equalsIgnoreCase("blueF")){

			//设置道岔定/反位
			turnoutStatus = 0;
			//设置道岔状态
			status = 0;
			
			//主道岔(左倾斜)
			mainLineL.setForegroundColor(ColorConstants.blue);		
			//主道岔(左倾斜)
			mainLineR.setForegroundColor(ColorConstants.blue);		
			// 股道(上)
			trackLineS.setForegroundColor(ColorConstants.blue);		
			// 股道(下)
			trackLineX.setForegroundColor(ColorConstants.blue);		
			// 股道(上左)
			trackLineSL.setForegroundColor(ColorConstants.white);		
			//股道(上右)
			trackLineSR.setForegroundColor(ColorConstants.white);		
			//股道(下右)
			trackLineXL.setForegroundColor(ColorConstants.white);		
			//股道(下右)
			trackLineXR.setForegroundColor(ColorConstants.white);		
			//初始小段(上左)
			initLineSL.setForegroundColor(ColorConstants.white);		
			//初始小段(上右)
			initLineSR.setForegroundColor(ColorConstants.white);		
			//初始小段(下左)
			initLineXL.setForegroundColor(ColorConstants.white);		
			//初始小段(下右)
			initLineXR.setForegroundColor(ColorConstants.white);		 
			//变化小段(上左)
			changeLineSL.setForegroundColor(ColorConstants.blue);		
			//变化小段(上右)																	
			changeLineSR.setForegroundColor(ColorConstants.blue);	
			//变化小段(下左)																	
			changeLineXL.setForegroundColor(ColorConstants.blue);		  
			//变化小段(下右)																	
			changeLineXR.setForegroundColor(ColorConstants.blue);	
			
		}else
		//蓝色反位
		if(color.equalsIgnoreCase("blueFL")){

			//设置道岔定/反位
			turnoutStatus = 0;
			//设置道岔状态
			status = 0;
			
			//主道岔(左倾斜)
			mainLineL.setForegroundColor(ColorConstants.blue);		
			//主道岔(左倾斜)
			mainLineR.setForegroundColor(ColorConstants.blue);		
			// 股道(上)
			trackLineS.setForegroundColor(ColorConstants.blue);		
			// 股道(下)
			trackLineX.setForegroundColor(ColorConstants.blue);		
			// 股道(上左)
			trackLineSL.setForegroundColor(ColorConstants.white);		
			//股道(上右)
			trackLineSR.setForegroundColor(ColorConstants.white);		
			//股道(下右)
			trackLineXL.setForegroundColor(ColorConstants.white);		
			//股道(下右)
			trackLineXR.setForegroundColor(ColorConstants.white);		
			//初始小段(上左)
			initLineSL.setForegroundColor(ColorConstants.white);		
			//初始小段(上右)
			initLineSR.setForegroundColor(ColorConstants.white);		
			//初始小段(下左)
			initLineXL.setForegroundColor(ColorConstants.white);		
			//初始小段(下右)
			initLineXR.setForegroundColor(ColorConstants.white);		 
			//变化小段(上左)
			changeLineSL.setForegroundColor(ColorConstants.blue);		
			//变化小段(上右)																	
			changeLineSR.setForegroundColor(ColorConstants.blue);	
			//变化小段(下左)																	
			changeLineXL.setForegroundColor(ColorConstants.blue);		  
			//变化小段(下右)																	
			changeLineXR.setForegroundColor(ColorConstants.blue);	
			
		}else
		//蓝色反位
		if(color.equalsIgnoreCase("blueFR")){

			//设置道岔定/反位
			turnoutStatus = 0;
			//设置道岔状态
			status = 0;
			
			//主道岔(左倾斜)
			mainLineL.setForegroundColor(ColorConstants.blue);		
			//主道岔(左倾斜)
			mainLineR.setForegroundColor(ColorConstants.blue);		
			// 股道(上)
			trackLineS.setForegroundColor(ColorConstants.blue);		
			// 股道(下)
			trackLineX.setForegroundColor(ColorConstants.blue);		
			// 股道(上左)
			trackLineSL.setForegroundColor(ColorConstants.white);		
			//股道(上右)
			trackLineSR.setForegroundColor(ColorConstants.white);		
			//股道(下右)
			trackLineXL.setForegroundColor(ColorConstants.white);		
			//股道(下右)
			trackLineXR.setForegroundColor(ColorConstants.white);		
			//初始小段(上左)
			initLineSL.setForegroundColor(ColorConstants.white);		
			//初始小段(上右)
			initLineSR.setForegroundColor(ColorConstants.white);		
			//初始小段(下左)
			initLineXL.setForegroundColor(ColorConstants.white);		
			//初始小段(下右)
			initLineXR.setForegroundColor(ColorConstants.white);		 
			//变化小段(上左)
			changeLineSL.setForegroundColor(ColorConstants.blue);		
			//变化小段(上右)																	
			changeLineSR.setForegroundColor(ColorConstants.blue);	
			//变化小段(下左)																	
			changeLineXL.setForegroundColor(ColorConstants.blue);		  
			//变化小段(下右)																	
			changeLineXR.setForegroundColor(ColorConstants.blue);	
			
		}else
		//绿色反位(左)
		if(color.equalsIgnoreCase("greenFL")){

			//设置道岔定/反位
			turnoutStatus = 0;
			//设置道岔状态
			status = 1;
			
			//主道岔(左倾斜)
			mainLineL.setForegroundColor(ColorConstants.green);		
			//主道岔(左倾斜)
			mainLineR.setForegroundColor(ColorConstants.blue);		
			// 股道(上)
			trackLineS.setForegroundColor(ColorConstants.blue);		
			// 股道(下)
			trackLineX.setForegroundColor(ColorConstants.blue);		
			// 股道(上左)
			trackLineSL.setForegroundColor(ColorConstants.white);		
			//股道(上右)
			trackLineSR.setForegroundColor(ColorConstants.white);		
			//股道(下右)
			trackLineXL.setForegroundColor(ColorConstants.white);		
			//股道(下右)
			trackLineXR.setForegroundColor(ColorConstants.white);		
			//初始小段(上左)
			initLineSL.setForegroundColor(ColorConstants.white);		
			//初始小段(上右)
			initLineSR.setForegroundColor(ColorConstants.white);		
			//初始小段(下左)
			initLineXL.setForegroundColor(ColorConstants.white);		
			//初始小段(下右)
			initLineXR.setForegroundColor(ColorConstants.white);		 
			//变化小段(上左)
			changeLineSL.setForegroundColor(ColorConstants.green);		
			//变化小段(上右)																	
			changeLineSR.setForegroundColor(ColorConstants.blue);	
			//变化小段(下左)																	
			changeLineXL.setForegroundColor(ColorConstants.blue);		  
			//变化小段(下右)																	
			changeLineXR.setForegroundColor(ColorConstants.green);	
			
		}else 
		//绿色反位(右)
		if(color.equalsIgnoreCase("greenFR")){

			//设置道岔定/反位
			turnoutStatus = 0;
			//设置道岔状态
			status = 1;
			
			//主道岔(左倾斜)
			mainLineL.setForegroundColor(ColorConstants.blue);		
			//主道岔(左倾斜)
			mainLineR.setForegroundColor(ColorConstants.green);		
			// 股道(上)
			trackLineS.setForegroundColor(ColorConstants.blue);		
			// 股道(下)
			trackLineX.setForegroundColor(ColorConstants.blue);		
			// 股道(上左)
			trackLineSL.setForegroundColor(ColorConstants.white);		
			//股道(上右)
			trackLineSR.setForegroundColor(ColorConstants.white);		
			//股道(下右)
			trackLineXL.setForegroundColor(ColorConstants.white);		
			//股道(下右)
			trackLineXR.setForegroundColor(ColorConstants.white);		
			//初始小段(上左)
			initLineSL.setForegroundColor(ColorConstants.white);		
			//初始小段(上右)
			initLineSR.setForegroundColor(ColorConstants.white);		
			//初始小段(下左)
			initLineXL.setForegroundColor(ColorConstants.white);		
			//初始小段(下右)
			initLineXR.setForegroundColor(ColorConstants.white);		 
			//变化小段(上左)
			changeLineSL.setForegroundColor(ColorConstants.blue);		
			//变化小段(上右)																	
			changeLineSR.setForegroundColor(ColorConstants.green);	
			//变化小段(下左)																	
			changeLineXL.setForegroundColor(ColorConstants.green);		  
			//变化小段(下右)																	
			changeLineXR.setForegroundColor(ColorConstants.blue);	
		}else 
		//红色反位(左)
		if(color.equalsIgnoreCase("redFL")){

			//设置道岔定/反位
			turnoutStatus = 0;
			//设置道岔状态
			status = 1;
			
			//主道岔(左倾斜)
			mainLineL.setForegroundColor(ColorConstants.red);		
			//主道岔(左倾斜)
			mainLineR.setForegroundColor(ColorConstants.blue);		
			// 股道(上)
			trackLineS.setForegroundColor(ColorConstants.blue);		
			// 股道(下)
			trackLineX.setForegroundColor(ColorConstants.blue);		
			// 股道(上左)
			trackLineSL.setForegroundColor(ColorConstants.white);		
			//股道(上右)
			trackLineSR.setForegroundColor(ColorConstants.white);		
			//股道(下右)
			trackLineXL.setForegroundColor(ColorConstants.white);		
			//股道(下右)
			trackLineXR.setForegroundColor(ColorConstants.white);		
			//初始小段(上左)
			initLineSL.setForegroundColor(ColorConstants.white);		
			//初始小段(上右)
			initLineSR.setForegroundColor(ColorConstants.white);		
			//初始小段(下左)
			initLineXL.setForegroundColor(ColorConstants.white);		
			//初始小段(下右)
			initLineXR.setForegroundColor(ColorConstants.white);		 
			//变化小段(上左)
			changeLineSL.setForegroundColor(ColorConstants.red);		
			//变化小段(上右)																	
			changeLineSR.setForegroundColor(ColorConstants.blue);	
			//变化小段(下左)																	
			changeLineXL.setForegroundColor(ColorConstants.blue);		  
			//变化小段(下右)																	
			changeLineXR.setForegroundColor(ColorConstants.red);	
			
		}else 
		//红色反位(右)
		if(color.equalsIgnoreCase("redFR")){

			//设置道岔定/反位
			turnoutStatus = 0;
			//设置道岔状态
			status = 1;
			
			//主道岔(左倾斜)
			mainLineL.setForegroundColor(ColorConstants.blue);		
			//主道岔(左倾斜)
			mainLineR.setForegroundColor(ColorConstants.red);		
			// 股道(上)
			trackLineS.setForegroundColor(ColorConstants.blue);		
			// 股道(下)
			trackLineX.setForegroundColor(ColorConstants.blue);		
			// 股道(上左)
			trackLineSL.setForegroundColor(ColorConstants.white);		
			//股道(上右)
			trackLineSR.setForegroundColor(ColorConstants.white);		
			//股道(下右)
			trackLineXL.setForegroundColor(ColorConstants.white);		
			//股道(下右)
			trackLineXR.setForegroundColor(ColorConstants.white);		
			//初始小段(上左)
			initLineSL.setForegroundColor(ColorConstants.white);		
			//初始小段(上右)
			initLineSR.setForegroundColor(ColorConstants.white);		
			//初始小段(下左)
			initLineXL.setForegroundColor(ColorConstants.white);		
			//初始小段(下右)
			initLineXR.setForegroundColor(ColorConstants.white);		 
			//变化小段(上左)
			changeLineSL.setForegroundColor(ColorConstants.blue);		
			//变化小段(上右)																	
			changeLineSR.setForegroundColor(ColorConstants.red);	
			//变化小段(下左)																	
			changeLineXL.setForegroundColor(ColorConstants.red);		  
			//变化小段(下右)																	
			changeLineXR.setForegroundColor(ColorConstants.blue);	
		}else
		//白色反位(左)
		if(color.equalsIgnoreCase("whiteFL")){

			//设置道岔定/反位
			turnoutStatus = 0;
			//设置道岔状态
			status = 1;
			
			//主道岔(左倾斜)
			mainLineL.setForegroundColor(ColorConstants.white);		
			//主道岔(左倾斜)
			mainLineR.setForegroundColor(ColorConstants.blue);		
			// 股道(上)
			trackLineS.setForegroundColor(ColorConstants.blue);		
			// 股道(下)
			trackLineX.setForegroundColor(ColorConstants.blue);		
			// 股道(上左)
			trackLineSL.setForegroundColor(ColorConstants.white);		
			//股道(上右)
			trackLineSR.setForegroundColor(ColorConstants.white);		
			//股道(下右)
			trackLineXL.setForegroundColor(ColorConstants.white);		
			//股道(下右)
			trackLineXR.setForegroundColor(ColorConstants.white);		
			//初始小段(上左)
			initLineSL.setForegroundColor(ColorConstants.white);		
			//初始小段(上右)
			initLineSR.setForegroundColor(ColorConstants.white);		
			//初始小段(下左)
			initLineXL.setForegroundColor(ColorConstants.white);		
			//初始小段(下右)
			initLineXR.setForegroundColor(ColorConstants.white);		 
			//变化小段(上左)
			changeLineSL.setForegroundColor(ColorConstants.white);		
			//变化小段(上右)																	
			changeLineSR.setForegroundColor(ColorConstants.blue);	
			//变化小段(下左)																	
			changeLineXL.setForegroundColor(ColorConstants.blue);		  
			//变化小段(下右)																	
			changeLineXR.setForegroundColor(ColorConstants.white);	
			
		}else 
		//白色反位(右)
		if(color.equalsIgnoreCase("whiteFR")){

			//设置道岔定/反位
			turnoutStatus = 0;
			//设置道岔状态
			status = 1;
			
			//主道岔(左倾斜)
			mainLineL.setForegroundColor(ColorConstants.blue);		
			//主道岔(左倾斜)
			mainLineR.setForegroundColor(ColorConstants.white);		
			// 股道(上)
			trackLineS.setForegroundColor(ColorConstants.blue);		
			// 股道(下)
			trackLineX.setForegroundColor(ColorConstants.blue);		
			// 股道(上左)
			trackLineSL.setForegroundColor(ColorConstants.white);		
			//股道(上右)
			trackLineSR.setForegroundColor(ColorConstants.white);		
			//股道(下右)
			trackLineXL.setForegroundColor(ColorConstants.white);		
			//股道(下右)
			trackLineXR.setForegroundColor(ColorConstants.white);		
			//初始小段(上左)
			initLineSL.setForegroundColor(ColorConstants.white);		
			//初始小段(上右)
			initLineSR.setForegroundColor(ColorConstants.white);		
			//初始小段(下左)
			initLineXL.setForegroundColor(ColorConstants.white);		
			//初始小段(下右)
			initLineXR.setForegroundColor(ColorConstants.white);		 
			//变化小段(上左)
			changeLineSL.setForegroundColor(ColorConstants.blue);		
			//变化小段(上右)																	
			changeLineSR.setForegroundColor(ColorConstants.white);	
			//变化小段(下左)																	
			changeLineXL.setForegroundColor(ColorConstants.white);		  
			//变化小段(下右)																	
			changeLineXR.setForegroundColor(ColorConstants.blue);	
		}				

	}
	

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getStatusL() {
		return statusL;
	}

	public void setStatusL(int statusL) {
		this.statusL = statusL;
	}

	public int getStatucR() {
		return statucR;
	}

	public void setStatucR(int statucR) {
		this.statucR = statucR;
	}

	public int getTurnoutStatus() {
		return turnoutStatus;
	}

	public void setTurnoutStatus(int turnoutStatus) {
		this.turnoutStatus = turnoutStatus;
	}

	public String getTurnoutName() {
		return turnoutName;
	}

	public void setTurnoutName(String turnoutName) {
		this.turnoutName = turnoutName;
	}

	public String getTurnoutNameL() {
		return turnoutNameL;
	}

	public void setTurnoutNameL(String turnoutNameL) {
		this.turnoutNameL = turnoutNameL;
	}

	public String getTurnoutNameR() {
		return turnoutNameR;
	}

	public void setTurnoutNameR(String turnoutNameR) {
		this.turnoutNameR = turnoutNameR;
	}

	public PolylineConnection getMainLineL() {
		return mainLineL;
	}

	public void setMainLineL(PolylineConnection mainLineL) {
		this.mainLineL = mainLineL;
	}

	public PolylineConnection getMainLineR() {
		return mainLineR;
	}

	public void setMainLineR(PolylineConnection mainLineR) {
		this.mainLineR = mainLineR;
	}

	public PolylineConnection getTrackLineS() {
		return trackLineS;
	}

	public void setTrackLineS(PolylineConnection trackLineS) {
		this.trackLineS = trackLineS;
	}

	public PolylineConnection getTrackLineX() {
		return trackLineX;
	}

	public void setTrackLineX(PolylineConnection trackLineX) {
		this.trackLineX = trackLineX;
	}

	public PolylineConnection getTrackLineSL() {
		return trackLineSL;
	}

	public void setTrackLineSL(PolylineConnection trackLineSL) {
		this.trackLineSL = trackLineSL;
	}

	public PolylineConnection getTrackLineSR() {
		return trackLineSR;
	}

	public void setTrackLineSR(PolylineConnection trackLineSR) {
		this.trackLineSR = trackLineSR;
	}

	public PolylineConnection getTrackLineXL() {
		return trackLineXL;
	}

	public void setTrackLineXL(PolylineConnection trackLineXL) {
		this.trackLineXL = trackLineXL;
	}

	public PolylineConnection getTrackLineXR() {
		return trackLineXR;
	}

	public void setTrackLineXR(PolylineConnection trackLineXR) {
		this.trackLineXR = trackLineXR;
	}

	public PolylineConnection getInitLineSL() {
		return initLineSL;
	}

	public void setInitLineSL(PolylineConnection initLineSL) {
		this.initLineSL = initLineSL;
	}

	public PolylineConnection getInitLineSR() {
		return initLineSR;
	}

	public void setInitLineSR(PolylineConnection initLineSR) {
		this.initLineSR = initLineSR;
	}

	public PolylineConnection getInitLineXL() {
		return initLineXL;
	}

	public void setInitLineXL(PolylineConnection initLineXL) {
		this.initLineXL = initLineXL;
	}

	public PolylineConnection getInitLineXR() {
		return initLineXR;
	}

	public void setInitLineXR(PolylineConnection initLineXR) {
		this.initLineXR = initLineXR;
	}

	public PolylineConnection getChangeLineSL() {
		return changeLineSL;
	}

	public void setChangeLineSL(PolylineConnection changeLineSL) {
		this.changeLineSL = changeLineSL;
	}

	public PolylineConnection getChangeLineSR() {
		return changeLineSR;
	}

	public void setChangeLineSR(PolylineConnection changeLineSR) {
		this.changeLineSR = changeLineSR;
	}

	public PolylineConnection getChangeLineXL() {
		return changeLineXL;
	}

	public void setChangeLineXL(PolylineConnection changeLineXL) {
		this.changeLineXL = changeLineXL;
	}

	public PolylineConnection getChangeLineXR() {
		return changeLineXR;
	}

	public void setChangeLineXR(PolylineConnection changeLineXR) {
		this.changeLineXR = changeLineXR;
	}

}
