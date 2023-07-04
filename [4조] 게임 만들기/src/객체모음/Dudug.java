package 객체모음;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GGG
 *
 */
public class Dudug {
	private int play;
	private String studentid;
	private int success;
	private int failure;
	private int ttoe;
	private int stof;
	private int ftoo;

	public Dudug(int play, String studentid, int success, int failure, int ttoe, int stof, int ftoo) {
		super();
		this.play = play;
		this.studentid = studentid;
		this.success = success;
		this.failure = failure;
		this.ttoe = ttoe;
		this.stof = stof;
		this.ftoo = ftoo;
	}

	public int getPlay() {
		return play;
	}

	public void setPlay(int play) {
		this.play = play;
	}

	public String getStudentid() {
		return studentid;
	}

	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}

	public int getSuccess() {
		return success;
	}

	public void setSuccess(int success) {
		this.success = success;
	}

	public int getFailure() {
		return failure;
	}

	public void setFailure(int failure) {
		this.failure = failure;
	}

	public int getTtoe() {
		return ttoe;
	}

	public void setTtoe(int ttoe) {
		this.ttoe = ttoe;
	}

	public int getStof() {
		return stof;
	}

	public void setStof(int stof) {
		this.stof = stof;
	}

	public int getFtoo() {
		return ftoo;
	}

	public void setFtoo(int ftoo) {
		this.ftoo = ftoo;
	}

	@Override
	public String toString() {
		return "Dudug [play=" + play + ", studentid=" + studentid + ", success=" + success + ", failure=" + failure
				+ ", ttoe=" + ttoe + ", stof=" + stof + ", ftoo=" + ftoo + "]";
	}
}
