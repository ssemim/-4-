package 메소드모음;

import java.util.List;

import 객체모음.School;
import 객체모음.Student;

public interface GameInterface {
	// 회원가입
	int insert(Student student);

	// 로그인
	Student login(String id, String password);

	// 학교별 랭킹
	// 학교 - 이름, 누적점수
	List<School> schoolRangking();

	// 교내 랭킹
	// 학생 회원정보
	List<Student> classRangking(Student student);

	// 학생랭킹
	// 학생
	List<Student> studentRangking(int gameNo);

}
