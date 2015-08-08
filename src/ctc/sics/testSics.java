package ctc.sics;

import ctc.sics.db.DataBase;
import ctc.transport.MinaClient;

public class testSics {

	public static MinaClient minaClient = new MinaClient();
	public static String userName = "12345";
	public static String passwordString = "123456";
	public static DataBase db = new DataBase();
	
	public static void main(String[] args) {
		new SicsMain(minaClient, db).start("北京站");
	}

}
