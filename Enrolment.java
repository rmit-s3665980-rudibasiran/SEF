package SEF;

public class Enrolment {

	private String _studentID;
	private String _courseCode;
	private String _semester;
	private String _grade;
	private Boolean _waiver;

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
		if (grade.equals(GlobalClass.waiverGrade))
			_waiver = true;
		else
			_grade = grade;
	}

	public Enrolment(String userID, String courseCode, String semester, String grade, Boolean waived) {
		_studentID = userID;
		_courseCode = courseCode;
		_semester = semester;
		if (grade.equals(GlobalClass.waiverGrade) | (waived))
			_waiver = waived;
		else
			_grade = grade;
	}

	public Enrolment(String userID, String courseCode, String semester, Boolean waived) {
		_studentID = userID;
		_courseCode = courseCode;
		_semester = semester;
		_waiver = waived;
	}

	public Boolean isStudentEnrolled(String userID, String courseCode, String semester) {
		return (_studentID.equals(userID) & _courseCode.equals(courseCode) & _semester.equals(semester) ? true : false);

	}

	public Boolean hasWaiver() {
		return _waiver;
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
		if (grade.equals(GlobalClass.waiverGrade))
			_waiver = true;
		else
			_grade = grade;
	}
}
