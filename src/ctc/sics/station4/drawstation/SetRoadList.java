package ctc.sics.station4.drawstation;

public class SetRoadList {

	public void setStationRoad(){
		
		SetRoad sr = new SetRoad();		
		
		// 下行接车
		sr.setR_XLA_S1LA();
		sr.setR_XLA_S2LA();
		sr.setR_XLA_BA_S2LA();
		sr.setR_XLA_S3LA();
		sr.setR_XLA_S4LA();
		sr.setR_XLA_BA_S4LA();		
		sr.setR_XLA_S6LA();
		sr.setR_XLA_BA_S6LA();
		// 下行发车
		sr.setR_X1LA_SLFA();
		sr.setR_X2LA_SLFA();
		sr.setR_X3LA_SLFA();
		sr.setR_X4LA_SLFA();
		sr.setR_X6LA_SLFA();
		// 下行通过
		sr.setR_XTA_SLFA();

		// 上行接车
		sr.setR_SLA_X1LA();
		sr.setR_SLA_X2LA();
		sr.setR_SLA_X3LA();
		sr.setR_SLA_X4LA();
		sr.setR_SLA_X6LA();
		// 上行发车
		sr.setR_S1LA_XLFA();
		sr.setR_S2LA_XLFA();
		sr.setR_S3LA_XLFA();
		sr.setR_S4LA_XLFA();
		sr.setR_S6LA_XLFA();
		// 上行通过
		sr.setR_STA_XLFA();


		// 调车
		sr.setR_D11A_S1DA();
		sr.setR_D11A_S2DA();
		sr.setR_D11A_BA_S2DA();
		sr.setR_D11A_S3DA();
		sr.setR_D11A_S4DA();
		sr.setR_D11A_BA_S4DA();
		sr.setR_D11A_S6DA();		
		sr.setR_D11A_BA_S6DA();
		
		sr.setR_D21A_S1DA();
		sr.setR_D21A_S2DA();
		sr.setR_D21A_S3DA();
		sr.setR_D21A_S4DA();
		sr.setR_D21A_S6DA();
		
		sr.setR_S1DA_D11A();
		sr.setR_S1DA_D21A();
		
		sr.setR_S2DA_D11A();
		sr.setR_S2DA_BA_D11A();
		sr.setR_S2DA_D21A();
		
		sr.setR_S3DA_D11A();
		sr.setR_S3DA_D21A();
		
		sr.setR_S4DA_D11A();
		sr.setR_S4DA_BA_D11A();
		sr.setR_S4DA_D21A();
		
		sr.setR_S6DA_D11A();
		sr.setR_S6DA_BA_D11A();
		sr.setR_S6DA_D21A();
		
		sr.setR_D51A_D31A();

		
		//53
		sr.setR_X3DA_D31A();
		sr.setR_X3DA_D13A();
		sr.setR_X3DA_D14A();
		sr.setR_X3DA_D22A();
		sr.setR_X3DA_D41A();
		sr.setR_X3DA_D42A();
		sr.setR_X3DA_D43A();
		
		sr.setR_X1DA_D13A();
		sr.setR_X1DA_D14A();
		sr.setR_X1DA_D22A();
		sr.setR_X1DA_D41A();
		sr.setR_X1DA_D42A();
		sr.setR_X1DA_D43A();
		
		sr.setR_X2DA_D14A();
		sr.setR_X2DA_D22A();
		sr.setR_X2DA_D41A();
		sr.setR_X2DA_D42A();
		sr.setR_X2DA_D43A();

		//72
		sr.setR_X4DA_D14A();
		sr.setR_X4DA_D22A();
		sr.setR_X4DA_D41A();
		sr.setR_X4DA_D42A();
		sr.setR_X4DA_D43A();
		
		sr.setR_X6DA_D14A();
		sr.setR_X6DA_D22A();
		sr.setR_X6DA_D41A();
		sr.setR_X6DA_D42A();
		sr.setR_X6DA_D43A();
		
		sr.setR_D31A_X3DA();
		sr.setR_D31A_D51A();
		
		sr.setR_D12A_D14A();
		
		sr.setR_D13A_X1DA();
		sr.setR_D13A_X3DA();
		
		sr.setR_D14A_D11A();
		sr.setR_D14A_X1DA();
		sr.setR_D14A_X3DA();
		
		sr.setR_D22A_X1DA();
		sr.setR_D22A_X2DA();
		sr.setR_D22A_X3DA();
		sr.setR_D22A_X4DA();
		sr.setR_D22A_X6DA();
		
		sr.setR_D41A_X1DA();
		sr.setR_D41A_X2DA();
		sr.setR_D41A_X3DA();
		sr.setR_D41A_X4DA();
		sr.setR_D41A_X6DA();
		
		sr.setR_D42A_X1DA();
		sr.setR_D42A_X2DA();
		sr.setR_D42A_X3DA();
		sr.setR_D42A_X4DA();
		sr.setR_D42A_X6DA();
		
		sr.setR_D43A_X1DA();
		sr.setR_D43A_X2DA();
		sr.setR_D43A_X3DA();
		sr.setR_D43A_X4DA();
		sr.setR_D43A_X6DA();
		sr.setR_D43A_D44A();
		
		sr.setR_D44A_D43A();

        //111
		sr.setR_XNLA_S1LA();
		sr.setR_XNLA_S2LA();
		sr.setR_XNLA_S3LA();
		sr.setR_XNLA_S4LA();
		sr.setR_XNLA_S6LA();
		sr.setR_S1LA_XLA();
		sr.setR_S2LA_XLA();
		sr.setR_S2LA_BA_XLA();
		sr.setR_S3LA_XLA();
		sr.setR_S4LA_XLA();
		sr.setR_S4LA_BA_XLA();
		sr.setR_S6LA_XLA();		
		sr.setR_S6LA_BA_XLA();
		
		
		sr.setR_SNLA_X1LA();
		sr.setR_SNLA_X2LA();
		sr.setR_SNLA_X3LA();
		sr.setR_SNLA_X4LA();
		sr.setR_SNLA_X6LA();
		sr.setR_X1LA_SLA();
		sr.setR_X2LA_SLA();
		sr.setR_X3LA_SLA();
		sr.setR_X4LA_SLA();
		sr.setR_X6LA_SLA();
			
		
	}
	
}
