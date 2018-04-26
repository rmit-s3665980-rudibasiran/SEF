package SEF;

import java.util.Arrays;

public class Enrolment {

	// unique key(s)
	private Student _student;
	private CourseOffering _courseOffering;
	Student std = new Student();
	CourseOffering cf = new CourseOffering();

	// about
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

	public String getCourseCode() {
		return _courseOffering.getCourseCode();
	}

	public String getSemester() {
		return _courseOffering.getSemester();
	}

	public Boolean hasPassed(Student s, CourseOffering co) {
		Boolean passed = false;
		if ((_student.getID().equals(s.getID()) & _courseOffering.getSemester().equals(co.getSemester())
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

	public CourseOffering getCourseOffering() {
		return _courseOffering;
	}

	public String getKey() {
		String key = Helper.createEnrolmentKey(_student, _courseOffering);
		return key;
	}
}
