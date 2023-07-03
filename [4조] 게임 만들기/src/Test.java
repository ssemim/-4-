import 객체모음.RspData;
import 객체모음.Student;
import 메소드모음.RspGG;

public class Test {
	public static void main(String[] args) {
		RspGG r = new RspGG();
		r.insert(new RspData(1, 1, new Student("dd", 0), "가위", "바위"));
	}
}
