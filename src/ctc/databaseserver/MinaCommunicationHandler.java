package ctc.databaseserver;

import ctc.transport.AsynClientSupport;
import ctc.transport.MinaClient;
import ctc.transport.SynClientSupport;

public class MinaCommunicationHandler {

	public MinaClient minaClient;
	public static AsynClientSupport asynClientSupport; //异步
	public static SynClientSupport synClientSupport; // 同步
	
	public MinaCommunicationHandler(){
	}
	
	public MinaCommunicationHandler(MinaClient newMinaClient){		
		minaClient = newMinaClient;
		asynClientSupport = newMinaClient.getAsynClientSupport();
		synClientSupport = newMinaClient.getSynClientSupport();
	}

	public MinaClient getMinaClient() {
		return minaClient;
	}

	public void setMinaClient(MinaClient minaClient) {
		this.minaClient = minaClient;
	}

	public AsynClientSupport getAsynClientSupport() {
		return asynClientSupport;
	}

	public void setAsynClientSupport(AsynClientSupport asynClientSupport) {
		this.asynClientSupport = asynClientSupport;
	}

	public SynClientSupport getSynClientSupport() {
		return synClientSupport;
	}

	public void setSynClientSupport(SynClientSupport synClientSupport) {
		this.synClientSupport = synClientSupport;
	}
	
}
