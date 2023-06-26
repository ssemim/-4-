
public class School {
	String userId;
	int pointAll;

	public School(String userId, int pointAll) {
		this.userId = userId;
		this.pointAll = pointAll;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getPointAll() {
		return pointAll;
	}

	public void setPointAll(int pointAll) {
		this.pointAll = pointAll;
	}

	@Override
	public String toString() {
		return "School [userId=" + userId + ", pointAll=" + pointAll + "]";
	}
}
