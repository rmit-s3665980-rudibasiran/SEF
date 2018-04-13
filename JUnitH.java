package SEF;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class JUnitH {

	@Test
	void GradeTest() {
		Driver driver = new Driver();
		driver.loadData();

		User u = driver._users.get("s3685849");
		Student s = (Student) u;
		String courseCode = "COSC1295";
		String semester = "1810";
		String grade = "HD";

		Course c = driver._courses.get(courseCode);
		CourseOffering co = new CourseOffering(c, semester);
		Enrolment e = new Enrolment(s, co, grade);

		driver._enrolment.add(e);

		if (driver._enrolment.get(driver.getIndexOfEnrolment(s, co)).isStudentEnrolled(s, co)) {
			driver._enrolment.get(driver.getIndexOfEnrolment(s, co)).setGrade(grade);
		} else {
			System.out.println("fail");
		}
		String output = driver._enrolment.get(driver.getIndexOfEnrolment(s, co)).getGrade();
		assertEquals(grade, output);

	}

	@Test
	void ViewPerformance() {

		Driver driver = new Driver();
		driver.loadData();

		User u = driver._users.get("s3685849");
		Student s = (Student) u;
		for (int i = 0; i < driver._enrolment.size(); i++)
			if (driver._enrolment.get(i).getStudent().getID().equals(s.getID()))
				System.out.println("Enrolment: " + driver._enrolment.get(i).getStudent().getID() + " | "
						+ driver._enrolment.get(i).getStudent().getName() + " | "
						+ driver._enrolment.get(i).getSemester() + " | " + driver._enrolment.get(i).getCourseCode()
						+ " | " + driver._courses.get(driver._enrolment.get(i).getCourseCode()).getCourseTitle() + " | "
						+ (driver._enrolment.get(i).getGrade().equals("") ? "-" : driver._enrolment.get(i).getGrade()));

		// should show waivers too & their status - whether added to Enrollment or still
		// only in CourseOffering
	}
}
