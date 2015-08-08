package ctc.ctc.drawctc.station1.drawstation;

public class SetRoadList {

	public void setStationRoad(){
		
		SetRoad sr = new SetRoad();		
		
		// 下行接车
		sr.setR_XLA_S1LA();
		sr.setR_XLA_S2LA();
		sr.setR_XLA_S3LA();
		sr.setR_XLA_S4LA();
		sr.setR_XLA_S6LA();
		// 下行发车
		sr.setR_X1LA_XLFA();
		sr.setR_X2LA_XLFA();
		sr.setR_X3LA_XLFA();
		sr.setR_X4LA_XLFA();
		sr.setR_X6LA_XLFA();
		// 下行通过
		sr.setR_XTA_XLFA();

		// 上行接车
		sr.setR_SLA_X1LA();
		sr.setR_SLA_X2LA();
		sr.setR_SLA_X3LA();
		sr.setR_SLA_X4LA();
		sr.setR_SLA_X6LA();
		// 上行发车
		sr.setR_S1LA_SLFA();
		sr.setR_S2LA_SLFA();
		sr.setR_S3LA_SLFA();
		sr.setR_S4LA_SLFA();
		sr.setR_S6LA_SLFA();
		// 上行通过
		sr.setR_STA_SLFA();

		// 调车
		sr.setR_DX1LA_DX3LB();
		sr.setR_DX1LA_DX4LB();
		sr.setR_DX2LA_DX3LB();
		sr.setR_DX2LA_DX4LB();
		sr.setR_DX3LA_DX3LB();
		sr.setR_DX3LA_DX4LB();
		sr.setR_DX4LA_DX3LB();
		sr.setR_DX4LA_DX4LB();
		sr.setR_DX5LA_DX3LB();
		sr.setR_DX6LA_DX3LB();
		sr.setR_DX6LA_DX4LB();
		sr.setR_DX3LB_DX1LA();
		sr.setR_DX3LB_DX2LA();
		sr.setR_DX3LB_DX3LA();
		sr.setR_DX3LB_DX4LA();
		sr.setR_DX3LB_DX5LA();
		sr.setR_DX3LB_DX6LA();
		sr.setR_DX4LB_DX1LA();
		sr.setR_DX4LB_DX2LA();
		sr.setR_DX4LB_DX3LA();
		sr.setR_DX4LB_DX4LA();
		sr.setR_DX4LB_DX6LA();
		
		
	}
	
}
