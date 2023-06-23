package 객체모음;

public class School {
	String name;
	int pointAll;

	public School(String name, int pointAll) {
		this.name = name;
		this.pointAll = pointAll;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPointAll() {
		return pointAll;
	}

	public void setPointAll(int pointAll) {
		this.pointAll = pointAll;
	}

	@Override
	public String toString() {
		return "School [name=" + name + ", pointAll=" + pointAll + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + pointAll;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof School))
			return false;
		School other = (School) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pointAll != other.pointAll)
			return false;
		return true;
	}

}
