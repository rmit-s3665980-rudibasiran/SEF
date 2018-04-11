package SEF;

public class Enrolment {

	private Student _student;
	private String _courseCode;
	private String _semester;
	private String _grade;

	public Enrolment() {

	}

	public Enrolment(Student s, String courseCode, String semester) {
		_student = s;
		_courseCode = courseCode;
		_semester = semester;

	}

	public Enrolment(Student s, String courseCode, String semester, String grade) {
		_student = s;
		_courseCode = courseCode;
		_semester = semester;
		_grade = grade;
	}

	public Boolean isStudentEnrolled(Student s, String courseCode, String semester) {
		return (_student.getID().equals(s.getID()) & _student.getName().equals(s.getName())
				& _courseCode.equals(courseCode) & _semester.equals(semester) ? true : false);
	}

	public String getCourseCode() {
		return _courseCode;
	}

	public String getSemester() {
		return _semester;
	}

	public String getGrade() {
		return _grade;
	}

	public void setGrade(String grade) {
		_grade = grade;
	}

	public Student getStudent() {
		return _student;
	}
}
