package SEF;

public class Enrolment {

	private Student _student = new Student();
	private String _studentID;
	private String _courseCode;
	private String _semester;
	private String _grade;
	private String _waiver;

	public Enrolment() {

	}

	public Enrolment(String userID, String courseCode, String semester) {
		_studentID = userID;
		_courseCode = courseCode;
		_semester = semester;
	}

	public Enrolment(String userID, String courseCode, String semester, String grade) {
		_studentID = userID;
		_courseCode = courseCode;
		_semester = semester;
		_grade = grade;
	}

	public Boolean isStudentEnrolled() {
		return true;
	}

	public String getStudentID() {
		return _studentID;
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

}
