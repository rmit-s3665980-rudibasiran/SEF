package SEF;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

class JUnitH {

	@Test
	void GradeTest() {
		Driver driver = new Driver();
		driver.loadData();

		User u = driver._users.get(driver.getIndexOfUser("s3685849"));
		Student s = (Student) u;
		String courseCode = "COSC1295";
		String semester = "1810";
		String grade = "HD";

		CourseOffering co = new CourseOffering(courseCode, semester);
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

	// test template
	// do not delete as eclipse will remove the import headers automatically if the
	// other junit tests do not make use of them
	@Test
	public void testTemplate() {
		try {
			Driver driver = new Driver();
			driver.loadData();

			// test something
			assertTrue(true);
			assertFalse(false);
			assertEquals(1, 1);
			assertNotSame(2, 1);

		} catch (Exception e) {
			RMITExceptions.handleExceptions(e);
		}
	};
}
