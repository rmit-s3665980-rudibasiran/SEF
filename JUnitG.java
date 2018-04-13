package SEF;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

class JUnitG {
	String separator = " | ";

	@Test
	public void addEnrolAlreadyPassed() {
		Driver driver = new Driver();
		try {
			driver.loadData();
			User u = driver._users.get("s3706098");
			Student s = (Student) u;
			String courseCode = "COSC1295";
			String semester = "1810";
			Course c = driver._courses.get(courseCode);
			CourseOffering co = new CourseOffering(c, semester);
			Enrolment e = new Enrolment(s, co);

			if (driver._courseOffering.get(courseCode + semester).courseOffered(courseCode, semester))
				System.out.println("Course Offered: " + co.getCourseCode() + separator + co.getSemester());
			else
				System.out.println(
						"Course Not Offered: " + separator + co.getCourseCode() + separator + co.getSemester());

			if (driver.getIndexOfEnrolment(s, co) < 0)
				System.out.println("Enrolment Not Found: " + e.getStudent().getID() + separator + e.getCourseCode()
						+ separator + e.getSemester());
			else
				System.out.println("Enrolment Found: " + e.getStudent().getID() + separator + e.getCourseCode()
						+ separator + e.getSemester());

			driver._enrolment.add(e);
			System.out.println("Added Enrolment : " + e.getStudent().getID() + separator + e.getCourseCode() + separator
					+ e.getSemester());
			if (driver.getIndexOfEnrolment(s, co) < 0)
				System.out.println("Enrolment Not Found: " + e.getStudent().getID() + separator + e.getCourseCode()
						+ separator + e.getSemester());
			else
				System.out.println("Enrolment Found: " + e.getStudent().getID() + separator + e.getCourseCode()
						+ separator + e.getSemester());
			c = driver._courses.get(courseCode);
			CourseOffering coPast = new CourseOffering(c, "1720");
			Enrolment ePast = new Enrolment(s, coPast);
			ePast.setGrade("HD");
			driver._enrolment.add(ePast);

			for (int i = 0; i < driver._enrolment.size(); i++) {
				Enrolment eTemp = driver._enrolment.get(i);
				c = driver._courses.get(eTemp.getCourseCode());
				CourseOffering coTemp = new CourseOffering(c, eTemp.getSemester());

				if (eTemp.hasPassed(s, coTemp) & eTemp.getStudent().getID().equals(s.getID()))
					System.out.println("Student Already Passed, cannot add for " + e.getCourseCode() + separator
							+ e.getSemester() + ": " + e.getStudent().getID() + separator + eTemp.getCourseCode()
							+ separator + eTemp.getSemester() + separator + eTemp.getGrade());
			}

		} catch (Exception e) {
			RMITExceptions.handleExceptions(e);
		}
	}

	@Test
	public void addEnrolPreviouslyFailed() {
	}

	@Test
	public void addEnrolPassPreReq() {
	}

	@Test
	public void addEnrolFailPreReq() {
	}

	@Test
	public void addEnrolHasWaiver() {
	}

	@Test
	public void addEnrolExceedLoad() {
	}

	@Test
	// test template
	// do not delete as eclipse will remove the import headers automatically if the
	// other junit tests do not make use of them
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
		} finally {
		}
	};

}
