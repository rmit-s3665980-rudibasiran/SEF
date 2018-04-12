package SEF;

import java.util.Arrays;

public class Enrolment {

	private Student _student;
	private CourseOffering _courseOffering;
	private String _grade;

	public Enrolment() {
	}

	public Enrolment(Student s, CourseOffering co) {
		_student = s;
		_courseOffering = co;
	}

	public Enrolment(Student s, CourseOffering co, String grade) {
		_student = s;
		_courseOffering = co;
		_grade = grade;
	}

	public Boolean isStudentEnrolled(Student s, CourseOffering co) {
		return (_student.getID().equals(s.getID()) & _student.getName().equals(s.getName())
				& _courseOffering.getSemester().equals(co.getSemester())
				& _courseOffering.getCourseCode().equals(co.getCourseCode()) ? true : false);
	}

	public String getCourseCode() {
		return _courseOffering.getCourseCode();
	}

	public String getSemester() {
		return _courseOffering.getSemester();
	}

	public Boolean hasPassed(Student s, CourseOffering co) {
		Boolean passed = false;
		if ((_student.getID().equals(s.getID()) & _student.getName().equals(s.getName())
				& _courseOffering.getSemester().equals(co.getSemester())
				& _courseOffering.getCourseCode().equals(co.getCourseCode()))
				& (Arrays.asList(GlobalClass.passGrades).contains(_grade)))
			passed = true;

		return passed;
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
