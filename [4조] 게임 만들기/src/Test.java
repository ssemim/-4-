import java.util.List;

import 객체모음.Student;
import 메소드모음.Inventory;

public class Test {
	public static void main(String[] args) {
		Student s = new Student("dd", 0);
		List<Boolean> cList = new Inventory(s).ItemAcquisition("캐릭터");
		System.out.println(cList);
	}
}
