
public class Student {
	String id;
	String password;
	String school;
	int point;

	public Student(String id, String password, String school, int point) {
		this.id = id;
		this.password = password;
		this.school = school;
		this.point = point;
	}

	public Student(String id, String password) {
		this.id = id;
		this.password = password;
	}

	public Student(String id, String school, int point) {
		this.id = id;
		this.school = school;
		this.point = point;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", password=" + password + ", school=" + school + ", point=" + point + "]";
	}

}
