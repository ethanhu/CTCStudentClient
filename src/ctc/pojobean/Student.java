package ctc.pojobean;

/**
 * 学员表
 */
public class Student {

	public int Student_id;//学员学号ID
	public String Student_name;//用户名具有唯一性
	public String Student_password;//密码
	public String Student_role;//角色

	public Student(){}
	
	public Student(int student_id, String student_name,String student_password, String student_role) {
		Student_id = student_id;
		Student_name = student_name;
		Student_password = student_password;
		Student_role = student_role;
	}

	public int getStudent_id() {
		return Student_id;
	}

	public void setStudent_id(int student_id) {
		Student_id = student_id;
	}

	public String getStudent_password() {
		return Student_password;
	}

	public void setStudent_password(String student_password) {
		Student_password = student_password;
	}

	public String getStudent_name() {
		return Student_name;
	}

	public void setStudent_name(String student_name) {
		Student_name = student_name;
	}

	public String getStudent_role() {
		return Student_role;
	}

	public void setStudent_role(String student_role) {
		Student_role = student_role;
	}

}