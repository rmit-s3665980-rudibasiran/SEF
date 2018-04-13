package SEF;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.Map.Entry;

import org.junit.jupiter.api.Test;

class JUnitG {
	String separator = " | ";

	@Test
	public void addEnrolAlreadyPassed() {
		System.out.println("Test addEnrolAlreadyPassed: Begin");
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
			String eKey = Helper.createEnrolmentKey(s, co);

			if (driver._courseOffering.get(courseCode + semester).courseOffered(co))
				System.out.println("Course Offered: " + co.getCourseCode() + separator + co.getSemester());
			else
				System.out.println(
						"Course Not Offered: " + separator + co.getCourseCode() + separator + co.getSemester());

			if (driver._enrolment.containsKey(eKey))
				System.out.println("Enrolment Not Found: " + e.getStudent().getID() + separator + e.getCourseCode()
						+ separator + e.getSemester());
			else
				System.out.println("Enrolment Found: " + e.getStudent().getID() + separator + e.getCourseCode()
						+ separator + e.getSemester());

			driver._enrolment.put(s.getID() + co.getCourseCode() + co.getSemester(), e);
			System.out.println("Added Enrolment : " + e.getStudent().getID() + separator + e.getCourseCode() + separator
					+ e.getSemester());

			eKey = Helper.createEnrolmentKey(s, co);
			if (driver._enrolment.get(eKey).getKey().equals(e.getKey()))
				System.out.println("Enrolment Not Found: " + e.getStudent().getID() + separator + e.getCourseCode()
						+ separator + e.getSemester());
			else
				System.out.println("Enrolment Found: " + e.getStudent().getID() + separator + e.getCourseCode()
						+ separator + e.getSemester());
			c = driver._courses.get(courseCode);
			CourseOffering coPast = new CourseOffering(c, "1720");
			Enrolment ePast = new Enrolment(s, coPast);
			ePast.setGrade("HD");
			driver._enrolment.put(s.getID() + coPast.getCourseCode() + coPast.getSemester(), ePast);

			for (Entry<String, Enrolment> entry : driver._enrolment.entrySet()) {
				Enrolment ei = entry.getValue();
				Student si = ei.getStudent();
				CourseOffering coi = ei.getCourseOffering();
				Course ci = driver._courses.get(ei.getCourseCode());

				if (ei.hasPassed(s, coi) & ei.getStudent().getID().equals(s.getID())) {
					System.out.println("Student Already Passed, cannot add for " + e.getCourseCode() + separator
							+ e.getSemester() + ": " + e.getStudent().getID() + separator + ei.getCourseCode()
							+ separator + ei.getSemester() + separator + ei.getGrade());
				}
			}

		} catch (Exception e) {
			RMITExceptions.handleExceptions(e);
		}
		System.out.println("Test addEnrolAlreadyPassed: End");
		Helper.drawLine();
	}

	@Test
	public void addEnrolPreviouslyFailed() {
		System.out.println("Test addEnrolPreviouslyFailed: Begin");
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
			String eKey = Helper.createEnrolmentKey(s, co);
			if (driver._courseOffering.get(courseCode + semester).courseOffered(co))
				System.out.println("Course Offered: " + co.getCourseCode() + separator + co.getSemester());
			else
				System.out.println(
						"Course Not Offered: " + separator + co.getCourseCode() + separator + co.getSemester());

			if (driver._enrolment.containsKey(eKey))
				System.out.println("Enrolment Not Found: " + e.getStudent().getID() + separator + e.getCourseCode()
						+ separator + e.getSemester());
			else
				System.out.println("Enrolment Found: " + e.getStudent().getID() + separator + e.getCourseCode()
						+ separator + e.getSemester());

			driver._enrolment.put(s.getID() + co.getCourseCode() + co.getSemester(), e);
			System.out.println("Added Enrolment : " + e.getStudent().getID() + separator + e.getCourseCode() + separator
					+ e.getSemester());
			if (driver._enrolment.get(eKey).isEnrolled(s, co))
				System.out.println("Enrolment Not Found: " + e.getStudent().getID() + separator + e.getCourseCode()
						+ separator + e.getSemester());
			else
				System.out.println("Enrolment Found: " + e.getStudent().getID() + separator + e.getCourseCode()
						+ separator + e.getSemester());
			c = driver._courses.get(courseCode);
			CourseOffering coPast = new CourseOffering(c, "1720");
			Enrolment ePast = new Enrolment(s, coPast);
			ePast.setGrade("F");
			driver._enrolment.put(s.getID() + coPast.getCourseCode() + coPast.getSemester(), ePast);

			for (Entry<String, Enrolment> entry : driver._enrolment.entrySet()) {
				Enrolment ei = entry.getValue();
				Student si = ei.getStudent();
				CourseOffering coi = ei.getCourseOffering();
				Course ci = driver._courses.get(ei.getCourseCode());

				if (ei.hasPassed(s, coi) & ei.getStudent().getID().equals(s.getID()))
					System.out.println("Student Already Passed, cannot add for " + e.getCourseCode() + separator
							+ e.getSemester() + ": " + e.getStudent().getID() + separator + ei.getCourseCode()
							+ separator + ei.getSemester() + separator + ei.getGrade());
			}

		} catch (Exception e) {
			RMITExceptions.handleExceptions(e);
		}
		System.out.println("Test addEnrolPreviouslyFailed: End");
		Helper.drawLine();
	}

	@Test
	public void addEnrolPassPreReq() {
		System.out.println("Test addEnrolPassPreReq: Begin");

		try {
			Driver driver = new Driver();
			driver.loadData();

			User u = driver._users.get("s3706098");
			Student s = (Student) u;
			String courseCode = "COSC1295";
			String semester = "1810";
			Course c = driver._courses.get(courseCode);
			CourseOffering co = new CourseOffering(c, semester);
			Enrolment e = new Enrolment(s, co);

			assertTrue(driver.checkPreReq(s, e));

		} catch (Exception e) {
			RMITExceptions.handleExceptions(e);
		}

		System.out.println("Test addEnrolPassPreReq: End");
		Helper.drawLine();

	}

	@Test
	public void addEnrolFailPreReq() {
		System.out.println("Test addEnrolFailPreReq: Begin");
		try {
			Driver driver = new Driver();
			driver.loadData();

			User u = driver._users.get("s3706098");
			Student s = (Student) u;
			String courseCode = "COSC1295";
			String semester = "1810";
			Course c = driver._courses.get(courseCode);
			CourseOffering co = new CourseOffering(c, semester);
			Enrolment e = new Enrolment(s, co);

			assertTrue(driver.checkPreReq(s, e));

		} catch (Exception e) {
			RMITExceptions.handleExceptions(e);
		}
		System.out.println("Test addEnrolFailPreReq: End");
		Helper.drawLine();

	}

	@Test
	public void addEnrolHasWaiver() {
		System.out.println("Test addEnrolHasWaiver: Begin");

		try {
			Driver driver = new Driver();
			driver.loadData();

			User u = driver._users.get("s3706098");
			Student s = (Student) u;
			String courseCode = "COSC1295";
			String semester = "1810";
			Course c = driver._courses.get(courseCode);
			CourseOffering co = new CourseOffering(c, semester);
			Enrolment e = new Enrolment(s, co);

			assertFalse(driver._courseOffering.get(courseCode + semester).hasWaiver(s));

		} catch (Exception e) {
			RMITExceptions.handleExceptions(e);
		}

		System.out.println("Test addEnrolHasWaiver: End");
		Helper.drawLine();
	}

	@Test
	public void addEnrolExceedLoad() {
		System.out.println("Test addEnrolExceedLoad: Begin");

		try {
			Driver driver = new Driver();
			driver.loadData();
			User u = driver._users.get("s3706098");
			Student s = (Student) u;
			String courseCode = "COSC1295";
			String semester = "1810";
			Course c = driver._courses.get(courseCode);
			CourseOffering co = new CourseOffering(c, semester);
			Enrolment e = new Enrolment(s, co);

			System.out.println("Enrolment = " + s.countEnrolment(driver._enrolment, s, semester) + " | MaxLoad = "
					+ s.getMaxLoad());

		} catch (Exception e) {
			RMITExceptions.handleExceptions(e);
		}

		System.out.println("Test addEnrolExceedLoad: End");
		Helper.drawLine();
	}

	@Test
	public void applyWaiver() {
		System.out.println("Test applyWaiver: Begin");
		try {
			Driver driver = new Driver();
			driver.loadData();

			// create new waiver but don't add to _courseOffering waivers, thus should
			// not exists
			User u = driver._users.get("s3706098");
			Student s = (Student) u;
			String courseCode = "COSC2531";
			String semester = "1810";
			String reason = "Prior experience";

			// assertFalse to check that student does not have this particular waiver
			assertFalse(driver._courseOffering.get(courseCode + semester).hasWaiver(s));

			// add waiver to courseOffering waivers
			driver._courseOffering.get(courseCode + semester).addWaiver(s, reason);

			// assertTrue to check that student does indeed have waiver
			assertTrue(driver._courseOffering.get(courseCode + semester).hasWaiver(s));

		} catch (Exception e) {
			RMITExceptions.handleExceptions(e);
		}
		System.out.println("Test applyWaiver: End");
		Helper.drawLine();
	}

	@Test
	public void dropEnrol() {
		System.out.println("Test dropEnrol: Begin");

		try {
			Driver driver = new Driver();
			driver.loadData();

			User u = driver._users.get("s3706098");
			Student s = (Student) u;
			String courseCode = "COSC1295";
			String semester = "1810";
			Course c = driver._courses.get(courseCode);
			CourseOffering co = new CourseOffering(c, semester);
			Enrolment e = new Enrolment(s, co);

			driver._enrolment.put(s.getID() + co.getCourseCode() + co.getSemester(), e);
			e.setGrade("HD");

			for (Entry<String, Enrolment> entry : driver._enrolment.entrySet()) {
				Enrolment ei = entry.getValue();
				Student si = ei.getStudent();
				CourseOffering coi = ei.getCourseOffering();
				Course ci = driver._courses.get(ei.getCourseCode());

				if (ei.hasPassed(s, coi) & ei.getStudent().getID().equals(s.getID())) {
					System.out.println("Student already has grade, cannot drop : " + e.getStudent().getID() + separator
							+ ei.getCourseCode() + separator + ei.getSemester() + separator + ei.getGrade());
				}
			}

			e.setGrade("");

			for (Entry<String, Enrolment> entry : driver._enrolment.entrySet()) {
				Enrolment ei = entry.getValue();
				Student si = ei.getStudent();
				CourseOffering coi = ei.getCourseOffering();
				Course ci = driver._courses.get(ei.getCourseCode());

				if (ei.hasPassed(s, coi) & ei.getStudent().getID().equals(s.getID())) {
					System.out.println("Student already has grade, cannot drop : " + e.getStudent().getID() + separator
							+ ei.getCourseCode() + separator + ei.getSemester() + separator + ei.getGrade());
				}
			}

			String eKey = Helper.createEnrolmentKey(s, co);
			driver._enrolment.remove(eKey);

		} catch (Exception e) {
			RMITExceptions.handleExceptions(e);
		}

		System.out.println("Test dropEnrol: End");
		Helper.drawLine();
	}

	@Test
	// test template
	// do not delete as eclipse will remove the import headers automatically if the
	// other junit tests do not make use of them
	public void testTemplate() {
		try {
			Driver driver = new Driver();
			driver.loadData();

			User u = driver._users.get("s3706098");
			Student s = (Student) u;
			String courseCode = "COSC1295";
			String semester = "1810";
			Course c = driver._courses.get(courseCode);
			CourseOffering co = new CourseOffering(c, semester);
			Enrolment e = new Enrolment(s, co);

			// test something
			assertTrue(true);
			assertFalse(false);
			assertEquals(1, 1);
			assertNotSame(2, 1);
			// fail("");
			// import static org.junit.jupiter.api.Assertions.fail;

		} catch (Exception e) {
			RMITExceptions.handleExceptions(e);
		}
	};

}
