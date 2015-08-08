package ctc.data;

/**
 * This class contains the data for a single entry
 */
public class LoginEntry {
  private String userName;
  private String password;
  private String ctcServerIP;
  private String ctcServerPort;
  
  
  public String getCtcServerIP() {
	return ctcServerIP;
}

public void setCtcServerIP(String ctcServerIP) {
	this.ctcServerIP = ctcServerIP;
}

public String getCtcServerPort() {
	return ctcServerPort;
}

public void setCtcServerPort(String ctcServerPort) {
	this.ctcServerPort = ctcServerPort;
}

public String getUserName() {
	return userName;
}

public void setUserName(String userName) {
	this.userName = userName;
}


public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

  /**
   * Constructs a LoginEntry
   */
  public LoginEntry() {
    this(null, null, null, null);
  }

  /**
   * Constructs a Entry
   */
  public LoginEntry(String newUserName,String newPassword,String newCtcServerIP,String newCtcServerPort) {
    setUserName(newUserName);
    setPassword(newPassword);
    setCtcServerIP(newCtcServerIP);
    setCtcServerPort(newCtcServerPort);
  }

 

  /**
   * Clones an entry
   * 
   * @param entry
   */
  public void clone(LoginEntry entry) {
    setUserName(entry.getUserName());
    setPassword(entry.getPassword());
    setCtcServerIP(entry.getCtcServerIP());
    setCtcServerPort(entry.getCtcServerPort());

  }
}
