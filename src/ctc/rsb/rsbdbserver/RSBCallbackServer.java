package ctc.rsb.rsbdbserver;

import java.util.List;

import ctc.pojobean.Plan;
import ctc.transport.AsynClientHandler.RSBCallback;
import ctc.transport.message.CommonMessage;
import ctc.transport.message.TeamTdcsRsbMessage;
import ctc.util.JsonUtil;

public class RSBCallbackServer implements RSBCallback{

	
	private RSBCallbackServer thisData = null;
	public RSBCallbackServer getInstance(){
		if (thisData == null){
			thisData = new RSBCallbackServer();
		}
		return thisData;
	}
//////////////////////////////////////////////////////////////////////////////////	
	
	@Override // hu 2010-4-25
	public void rsbReceiveTeamTdcsRsbMessageASYN(TeamTdcsRsbMessage rMsg) {
		
		//System.out.println("\n RSB DB 收到 TeamTdcsRsbMessage");
		
		List<Plan> planList = JsonUtil.getList4Json(rMsg.getTrainPlan(), Plan.class);
		if(planList == null){
			System.out.println("planList == null");
			return;
		}
		
		int len = planList.size();
		//System.out.println("planListSize == " + len);
	}

	//RSB收到CommonMessage消息
	public void rsbReceiveCommonMessage(CommonMessage msg) {
		// TODO Auto-generated method stub
		
	}

	

}