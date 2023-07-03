package 객체모음;

import java.util.ArrayList;
import java.util.List;

public class Dudug {
	private String id;
	private int success;
	private int failure;

	public Dudug(String id, int success, int failure) {
		super();
		this.id = id;
		this.success = success;
		this.failure = failure;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "Dudug [id=" + id + ", success=" + success + ", failure=" + failure + "]";
	}
}
