package ctc.pojobean;

public class TeacherInfo {

	public String name;//用户名具有唯一性
	public String password;//密码
	
	public TeacherInfo(String name, String password) {
		this.name = name;
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	
	

}