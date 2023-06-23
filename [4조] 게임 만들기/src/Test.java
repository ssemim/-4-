import java.util.List;

public class Test {
	public static void main(String[] args) {
		GameRepoRanking g = new GameRepoRanking();
		List<Student> list = g.studentRangking(2);
		for (Student student : list) {
			System.out.println(student);
		}
	}
}
