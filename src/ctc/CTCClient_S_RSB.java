package ctc;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ctc.constant.*;
import ctc.ctc.CTCMain;
import ctc.ctc.db.CTCDataBase;
import ctc.data.*;
import ctc.sics.SicsMain;
import ctc.sics.db.DataBase;
import ctc.swing.StandardParametersPanel;
import ctc.util.ErrorLog;
import ctc.transport.*;
import ctc.transport.message.LoginResponseMessage;

public class CTCClient_S_RSB extends JFrame implements ActionListener {

	public final static Logger LOGGER = LoggerFactory.getLogger("CTCStudent");

	private static final long serialVersionUID = 1L;
	private JMenuBar accessDialogMenuBar;
	private JButton loginButton, quitButton;

	private ConfigureFile cf = new ConfigureFile();
	private StandardParametersPanel standardParametersPanel;

	private String host = "127.0.0.1";
	private String port = "9999";
	private String userName = "test";
	String passwordString = "";

	private MinaClient minaClient = new MinaClient();

	protected void center() {
		// 并没有真正用
		PropertyConfigurator.configure(Constants.PATH_LOG4J);
		// LOGGER.info("服务器连接出错");

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension us = getSize();
		int x = (screen.width - us.width) / 2;
		int y = (screen.height - us.height) / 2;
		setLocation(x, y);
	}

	public static void main(String[] args) {
		CTCClient_S_RSB loginAccess = new CTCClient_S_RSB();
		loginAccess.setSize(335, 320);
		loginAccess.setResizable(false);
		loginAccess.center();
		loginAccess.setVisible(true);
	}

	protected CTCClient_S_RSB() {
		setTitle("登录界面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		String fileSeparator = System.getProperty("file.separator");
		if (fileSeparator == null || fileSeparator.equals(""))
			fileSeparator = "/";
		String IMAGE_PATH = System.getProperty("user.dir") + fileSeparator + "resources" + fileSeparator + "images" + fileSeparator;

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.err.println("Can't set look and feel.");
		}

		accessDialogMenuBar = new JMenuBar();
		accessDialogMenuBar.setBorder(BorderFactory.createEtchedBorder());
		accessDialogMenuBar.setMargin(new Insets(0, 0, 0, 0));

		quitButton = new JButton(new ImageIcon(IMAGE_PATH + "quit.png"));
		quitButton.setFocusable(false);
		quitButton.setMargin(new Insets(0, 0, 0, 0));
		quitButton.setToolTipText("退出登录");
		quitButton.addActionListener(this);
		accessDialogMenuBar.add(quitButton);

		setJMenuBar(accessDialogMenuBar);

		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBorder(BorderFactory.createEtchedBorder());

		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(null);

		// MyJSQLView Icon Logo & Components
		JPanel iconPanel = new JPanel();
		iconPanel.add(new JLabel(new ImageIcon(IMAGE_PATH + "login_logo.png"), JLabel.LEFT));
		iconPanel.setBounds(0, 0, 180, 210);// int x, int y, int width, int
		// height
		centerPanel.add(iconPanel);

		cf.init();

		// Standard Parameters Panel & Components
		standardParametersPanel = new StandardParametersPanel(cf.getCtcServerIP(), cf.getCtcServerPort(), cf.getUserName());
		standardParametersPanel.setBounds(190, 0, 130, 200);
		centerPanel.add(standardParametersPanel);

		mainPanel.add(centerPanel, BorderLayout.CENTER);

		// Action Button Panel
		JPanel actionPanel = new JPanel();
		actionPanel.setBorder(BorderFactory.createRaisedBevelBorder());

		loginButton = new JButton("登录");		
		loginButton.addActionListener(this);
		actionPanel.add(loginButton);

		mainPanel.add(actionPanel, BorderLayout.SOUTH);
		getContentPane().add(mainPanel);
		(this.getRootPane()).setDefaultButton(loginButton);

	}

	protected static void displayErrors(String error) {
		JOptionPane.showMessageDialog(null, error, "提示信息", JOptionPane.ERROR_MESSAGE);
	}
	
	protected void accessCheck() {
		// Check for some kind of valid input.
		host = standardParametersPanel.getHost();
		port = standardParametersPanel.getPort();
		userName = standardParametersPanel.getUserName();
		char[] passwordCharacters = standardParametersPanel.getPassword();

		if (host.equals("") || port.equals("") || userName.equals("") || passwordCharacters.length == 0) {
			displayErrors("请输入完整的登录信息！");
			return;
		}
		passwordString = "";
		// Obtaining the password & clearing.
		for (int i = 0; i < passwordCharacters.length; i++) {
			passwordString += passwordCharacters[i];
			passwordCharacters[i] = '0';
		}
		
		if (!minaClient.connect(host, port, userName, passwordString, Constants.USER_ROLE_STUDENT)) {

			displayErrors("服务器连接出错！");
			if (minaClient != null)
				minaClient.closeConnection(Constants.CLIENT_CLOSE_NORMAL);
		} else {// 成功连接
			LoginResponseMessage rMsg = minaClient.login();// 验证用户身份

			if (rMsg == null || rMsg.getResult() == Constants.SERVER_RESULT_ERROR) {
				displayErrors("用户姓名或密码有错！！");
				if (minaClient != null)
					minaClient.closeConnection(Constants.CLIENT_CLOSE_NORMAL);
				return;
			} else if (rMsg.getResult() == Constants.SERVER_ALLOCATE_ERROR) {
				displayErrors("所有车站已经分配完毕！");// 此错误是由于无车站可分配产生的
				if (minaClient != null)
					minaClient.closeConnection(Constants.CLIENT_CLOSE_NORMAL);
				return;
			} else if (rMsg.getResult() == Constants.SERVER_ALLOCATE_NOSTART)// 实验还没有开始
			{
				displayErrors("实验还没有开始！");
				if (minaClient != null)
					minaClient.closeConnection(Constants.CLIENT_CLOSE_NORMAL);
				return;
			} else if (rMsg.getResult() == Constants.SERVER_RESULT_RLOGIN) {
				displayErrors("你已经登录到系统！");
				if (minaClient != null)
					minaClient.closeConnection(Constants.CLIENT_CLOSE_RLOGIN);
				return;
			}

			LoginEntry entry = new LoginEntry(userName, "", host, port);
			cf.save(entry);
			setVisible(false);

			if (rMsg.getUserRole() == Constants.USER_ROLE_STUDENT) {// 启动学员界面

				int runMode = rMsg.getRunMode();   // 获取系统运行模式
				int teamID = rMsg.getTeamID();     //小组号				
				int id = rMsg.getExperimentMode(); // 获取实验主题
				String districtName = rMsg.getDistrictName();
				
				if (id == Constants.EXPERIMENT_MODE_TDSI)// 综合实验					
				{
					int terType = rMsg.getTerType();
					switch (terType) {
					case Constants.TERMINAL_TYPE_CTC:
						
						System.out.println("启动CTC模式");
						CTCDataBase ctcDB = new CTCDataBase();
						MinaClient ctcMinaClient = new MinaClient(ctcDB);
						ctcDB.setParams(ctcMinaClient, userName, runMode, districtName, teamID);
						String ctcTitle = " 组号:" + rMsg.getTeamID() + "  学员:" + userName +  "  区段:" + rMsg.getDistrictName();
						new CTCMain(ctcMinaClient, ctcDB).start(ctcTitle);// 启动CTC界面
						break;
						
					case Constants.TERMINAL_TYPE_RSB:
						
						System.out.println("启动RSB电务维修机模式");
						
						CTCDataBase ctcDB2 = new CTCDataBase();
						MinaClient ctcMinaClient2 = new MinaClient(ctcDB2);
						ctcDB2.setParams(ctcMinaClient2, userName, runMode, districtName, teamID);
						String ctcTitle2 = " 组号:" + rMsg.getTeamID() + "  学员:" + userName +  "  区段:" + rMsg.getDistrictName();
						new CTCMain(ctcMinaClient2, ctcDB2).start(ctcTitle2);// 启动CTC界面
						break;
						
					case Constants.TERMINAL_TYPE_ZNTDCS:
						
						System.out.println("启动TDCS模式");
						
						/**启动TDCS xbm2010-4-24(2)添加为实验代码*/
						//注册回调方法，供异步通信用，相关方法在CallbackServer中进行实现
												
						ctc.tdcs.tdcsdbserver.CallbackServer callbackServer = new ctc.tdcs.tdcsdbserver.CallbackServer();						
						MinaClient minaClientForTdcs = new MinaClient(callbackServer);
						callbackServer.setParams(minaClientForTdcs, userName, runMode, districtName, teamID);
						
						boolean roleFlag = false;//表示启动组内TDCS
						new ctc.tdcs.TdcsEnvInit(minaClientForTdcs, userName, rMsg, roleFlag).start(); 
			
						break;
					default:// 启动车站联锁界面
						
						System.out.println("启动SICS模式");	
						DataBase sicsDB = new DataBase();
						MinaClient sicsMinaClient = new MinaClient(sicsDB);
						sicsDB.setParams(sicsMinaClient, userName, runMode, districtName, teamID, rMsg.getStationName());
						String sicsTitle = " 组号:" + rMsg.getTeamID() + "  学员:" + userName +  "  区段:" + rMsg.getDistrictName() + "  车站:" + rMsg.getStationName();
						new SicsMain(sicsMinaClient, sicsDB).start(sicsTitle);
						break;
					}
				}else
				if (id == Constants.EXPERIMENT_MODE_SICS) // 车站联锁
				{
					//new SicsMain(minaClient, sicsDB).start(title);// 启动界面
				} else if (id == Constants.EXPERIMENT_MODE_TDCS)// 行车调度
				{
					//String tdcsTitle = " 学员:" + userName + " 组号:" + rMsg.getTeamID() + " 区段：" + rMsg.getDistrictName();
					//BaseService tdcsDB = new BaseService(minaClient, rMsg.getDistrictName(), userName, rMsg.getRunMode(), rMsg.getTeamID());
					//new TdcsMain(tdcsDB, tdcsTitle).start(); // 启动界面

				} else // CTC客户机处理
				// CTC客户机处理
				{// 没有设置实验模式 EXPERIMENT_MODE_NONE
					displayErrors("实验还没有开始！\n\r待会再登录!!!");
					if (minaClient != null)
						minaClient.closeConnection(Constants.CLIENT_CLOSE_NORMAL);

					System.exit(1);
				}
			}// 启动学员界面
		}
	}

	public void actionPerformed(ActionEvent evt) {
		Object panelSource = evt.getSource();
		// Button Actions
		if (panelSource instanceof JButton) {
			JButton actionButton = (JButton) panelSource;
			// Login Attempt
			if (actionButton == loginButton) {
				accessCheck();// 连接服务器
			} else if (actionButton == quitButton) {
				// 同步通信处理
				if (minaClient != null)
					minaClient.closeConnection(Constants.CLIENT_CLOSE_NORMAL);
				System.exit(1);
			}
		}
	}

}
