import java.util.List;

import 객체모음.Student;
import 메소드모음.GameRepoRanking;

public class Test {
	public static void main(String[] args) {
		GameRepoRanking g = new GameRepoRanking();
		List<Student> list = g.studentRangking(2);
		for (Student student : list) {
			System.out.println(student);
		}
	}
}
