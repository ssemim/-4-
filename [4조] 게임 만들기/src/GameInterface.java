import java.util.List;

public interface GameInterface {
	// 회원가입
	int insert(Student student);
	
	// 로그인
	int login(Student student);
	
	// 뽑기
	int 뽑기();

	// 학교별 랭킹
	// 학교 - 이름, 누적점수
	List<School> schoolRangking();
	
	// 교내 랭킹
	// 학생 회원정보
	List<Student> classRangking(); 
	
	// 학생랭킹
	// 학생
	List<Student> studentRangking();
	
	
	
	
}
