package 객체모음;

public class RspData {
	private int playLog;
	private int playTime;
	private String studentId;
	private String myChoice;
	private String comChoice;
	public RspData(int playLog, int playTime, Student s, String myChoice, String comChoice) {
		super();
		this.playLog = playLog;
		this.playTime = playTime;
		this.studentId = s.getId();
		this.myChoice = myChoice;
		this.comChoice = comChoice;
	}
	public int getPlayLog() {
		return playLog;
	}
	public void setPlayLog(int playLog) {
		this.playLog = playLog;
	}
	public int getPlayTime() {
		return playTime;
	}
	public void setPlayTime(int playTime) {
		this.playTime = playTime;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getMyChoice() {
		return myChoice;
	}
	public void setMyChoice(String myChoice) {
		this.myChoice = myChoice;
	}
	public String getComChoice() {
		return comChoice;
	}
	public void setComChoice(String comChoice) {
		this.comChoice = comChoice;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comChoice == null) ? 0 : comChoice.hashCode());
		result = prime * result + ((myChoice == null) ? 0 : myChoice.hashCode());
		result = prime * result + playLog;
		result = prime * result + playTime;
		result = prime * result + ((studentId == null) ? 0 : studentId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RspData other = (RspData) obj;
		if (comChoice == null) {
			if (other.comChoice != null)
				return false;
		} else if (!comChoice.equals(other.comChoice))
			return false;
		if (myChoice == null) {
			if (other.myChoice != null)
				return false;
		} else if (!myChoice.equals(other.myChoice))
			return false;
		if (playLog != other.playLog)
			return false;
		if (playTime != other.playTime)
			return false;
		if (studentId == null) {
			if (other.studentId != null)
				return false;
		} else if (!studentId.equals(other.studentId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "RspData [playLog=" + playLog + ", playTime=" + playTime + ", studentId=" + studentId + ", myChoice="
				+ myChoice + ", comChoice=" + comChoice + "]";
	}
	
}
