package ctc.swing;

import java.awt.*;
import javax.swing.*;

public class StandardParametersPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Class Instances.
	private TextField host;
	private TextField port;
	private TextField userName;
	private JPasswordField passwordTextField;
	
	//===========================================================
	// StandardParametersPanel Constructor
	//===========================================================
	
	public StandardParametersPanel(String ip, String ipport,String name)
	{	
		// Standard Parameters Panel & Components
		setLayout(new GridLayout(8,1,80,0));
		
		// Host
		JLabel hostLabel = new JLabel("服务器IP", JLabel.LEFT);
		add(hostLabel);
		
		host = new TextField(ip);
		host.setEditable(true);
		host.setBounds(0,0,35,12);
		add(host);
		
		// Database
		JLabel dbLabel = new JLabel("端口");
		add(dbLabel);
		
		port = new TextField(ipport);
		port.setEditable(true);
		port.setBounds(0,0,35,12);
		add(port);
		
		// User
		JLabel userLabel = new JLabel("用户名", JLabel.LEFT);
		add(userLabel);
		
		userName = new TextField(name);
		userName.setEditable(true);
		userName.setBounds(0,0,35,12);
		add(userName);
		
		// Password
		JLabel passwordLabel = new JLabel("密码", JLabel.LEFT);
		add(passwordLabel);
		
		passwordTextField = new JPasswordField();
		add(passwordTextField);
	}

	public String getHost()
	{
		return host.getText();
	}
	
	public String getPort()
	{
		return port.getText();
	}
	
	public String getUserName()
	{
		return userName.getText();
	}

	public char[] getPassword()
	{
		return passwordTextField.getPassword();
	}
	

	
}