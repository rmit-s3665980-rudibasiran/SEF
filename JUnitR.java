package SEF;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Map.Entry;

import org.junit.jupiter.api.Test;

class JUnitR {
	String separator = " | ";

	@Test
	public void setUp() {
		System.out.println("Test setUp: Begin");
		try {
			Driver driver = new Driver();
			driver.loadData();

			for (Entry<String, User> entry : driver._users.entrySet()) {
				User u = entry.getValue();
				System.out.println(
						"User: " + u.getName() + separator + u.getID() + separator + GlobalClass.roleDesc[u.getRole()]);
			}

			for (Entry<String, Course> entry : driver._courses.entrySet()) {
				Course c = entry.getValue();
				System.out.println("Course: " + c.getCourseCode() + separator + c.getCourseTitle());
			}

			for (Entry<String, CourseOffering> entry : driver._courseOffering.entrySet()) {
				CourseOffering co = entry.getValue();
				System.out.println("Course Offering: " + co.getSemester() + separator + co.getCourseCode() + separator
						+ driver._courses.get(co.getCourseCode()).getCourseTitle());
			}

			for (Entry<String, Enrolment> entry : driver._enrolment.entrySet()) {
				Enrolment ei = entry.getValue();
				Student si = ei.getStudent();
				CourseOffering coi = ei.getCourseOffering();
				Course ci = driver._courses.get(ei.getCourseCode());
				System.out.println("Enrolment: " + si.getID() + separator + si.getName() + separator
						+ ei.getCourseCode() + separator + ci.getCourseTitle() + separator + separator
						+ ei.getSemester() + separator + ei.getGrade());

			}

		} catch (Exception e) {
			RMITExceptions.handleExceptions(e);
		}
		System.out.println("Test setUp: End");
		Helper.drawLine();

	};

	@Test
	// add new waiver and add existing waiver
	public void addWaiver() {
		System.out.println("Test addWaiver: Begin");
		try {
			Driver driver = new Driver();
			driver.loadData();

			// create new waiver but don't add to _courseOffering waivers, thus should
			// not exists
			User u = driver._users.get("s3665980");
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
		System.out.println("Test addWaiver: End");
		Helper.drawLine();
	};

	// change student's max load per term
	@Test
	public void changeLoadCorrectValue() {

		System.out.println("Test changeLoadCorrectValue: Begin");
		try {
			Driver driver = new Driver();
			driver.loadData();

			int newLoad = 2;
			String userID = driver._users.get("s3665980").getID();
			assertTrue(newLoad <= GlobalClass.maxLoad & driver.changeLoad(userID, "1810", newLoad));

		} catch (Exception e) {
			RMITExceptions.handleExceptions(e);
		}
		System.out.println("Test changeLoadCorrectValue: End");
		Helper.drawLine();
	};

	@Test
	public void changeLoadIncorrectValue() {
		System.out.println("Test changeLoadIncorrectValue: Begin");
		try {
			Driver driver = new Driver();
			driver.loadData();

			int newLoad = 12;
			String userID = driver._users.get("s3665980").getID();
			assertFalse(newLoad <= GlobalClass.maxLoad & driver.changeLoad(userID, "1810", newLoad));

		} catch (Exception e) {
			RMITExceptions.handleExceptions(e);
		}
		System.out.println("Test changeLoadIncorrectValue: End");
		Helper.drawLine();
	};

	// what if student is doing 2 modules for the term and maxLoad changed to 1
	@Test
	public void changeLoadHitMaxLoad() {
		System.out.println("Test changeLoadHitMaxLoad: Begin");
		try {
			Driver driver = new Driver();
			driver.loadData();

			int newLoad = 1;
			User u = driver._users.get("s3665980");
			Student s = (Student) u;
			String courseCode = "COSC2531";
			String semester = "1810";
			Course c = driver._courses.get(courseCode);
			CourseOffering co = new CourseOffering(c, semester);
			Enrolment e = new Enrolment(s, co);
			System.out.println("Enrolment = " + s.countEnrolment(driver._enrolment, s, semester) + " | MaxLoad = "
					+ s.getMaxLoad());

			driver._enrolment.put(s.getID() + co.getCourseCode() + co.getSemester(), e);
			System.out.println("Enrolment = " + s.countEnrolment(driver._enrolment, s, semester) + " | MaxLoad = "
					+ s.getMaxLoad());

			assertFalse(newLoad >= s.countEnrolment(driver._enrolment, s, semester)
					& driver.changeLoad(s.getID(), semester, newLoad));

		} catch (Exception e) {
			RMITExceptions.handleExceptions(e);
		}
		System.out.println("Test changeLoadHitMaxLoad: End");
		Helper.drawLine();
	};

	// add course offerings - new and existing
	@Test
	public void addOffering() {
		System.out.println("Test addOffering: Begin");

		try {
			Driver driver = new Driver();
			driver.loadData();

			String courseCode = "COSC2615";
			String semester = "1810";
			Course c = driver._courses.get(courseCode);
			CourseOffering co = new CourseOffering(c, semester);
			String coKey = Helper.createCourseOfferingKey(co);

			// did not add and therefore, false
			assertFalse(driver._courseOffering.containsKey(co));

			System.out.println("Cannot get CourseOffering: " + co.getCourseCode() + separator + co.getSemester());

			// added offering
			driver._courseOffering.put(coKey, new CourseOffering(c, semester));

			// have added and therefore, true
			assertTrue(co.courseOffered(co));

		} catch (Exception e) {
			RMITExceptions.handleExceptions(e);
		}

		System.out.println("Test addOffering: End");
		Helper.drawLine();

	};

	// add lecturer to course offering - new and existing
	@Test
	public void addLecturer() {
		System.out.println("Test addLecturer: Begin");
		try {
			Driver driver = new Driver();
			driver.loadData();

			User u = driver._users.get("e12345");
			Lecturer l = (Lecturer) u;
			String courseCode = "ISYS1117";
			String semester = "1810";
			Course c = driver._courses.get(courseCode);
			CourseOffering co = new CourseOffering(c, semester);
			String coKey = Helper.createCourseOfferingKey(co);

			int il = driver.getIndexOfLecturer(driver._courseOffering.get(coKey).getLecturer(), l);

			assertFalse(il >= 0);
			System.out.println("Cannot get CourseOffering Lecturers: " + il);

			driver._courseOffering.get(coKey).addLecturer(l);
			il = driver.getIndexOfLecturer(driver._courseOffering.get(coKey).getLecturer(), l);
			ArrayList<Lecturer> courseLecturer = driver._courseOffering.get(coKey).getLecturer();
			assertEquals(courseLecturer.get(il).getID(), l.getID());

		} catch (Exception e) {
			RMITExceptions.handleExceptions(e);
		}
		System.out.println("Test addLecturer: End");
		Helper.drawLine();
	};

	// add course - new and existing
	@Test
	public void addCourse() {
		System.out.println("Test addCourse: Begin");
		try {
			Driver driver = new Driver();
			driver.loadData();

			String courseCode = "MATH101";
			String title = "Maths for Beginners";
			assertFalse(driver._courses.containsKey(courseCode));
			System.out.println("JUnit.addCourse | Cannot find Course: " + courseCode);

			driver._courses.put(courseCode, new Course(courseCode, title));
			assertTrue(driver._courses.get(courseCode).getCourseTitle().equals(title));

		} catch (Exception e) {
			RMITExceptions.handleExceptions(e);
		}
		System.out.println("Test addCourse: End");
		Helper.drawLine();
	};

	// add enrolment - new and existing
	@Test
	public void addEnrolment() {
		System.out.println("Test addEnrolment: Begin");
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

			if (driver._courseOffering.get(courseCode + semester).courseOffered(co))
				System.out.println("Course Offered: " + " | " + co.getCourseCode() + separator + co.getSemester());
			else
				System.out.println("Course Not Offered: " + " | " + co.getCourseCode() + separator + co.getSemester());

			if (driver._enrolment.containsKey(s.getID() + co.getCourseCode() + co.getSemester()))
				System.out.println("Enrolment Not Found: " + e.getStudent().getID() + separator + e.getCourseCode()
						+ separator + e.getSemester());
			else
				System.out.println("Enrolment Found: " + e.getStudent().getID() + separator + e.getCourseCode()
						+ separator + e.getSemester());

			driver._enrolment.put(s.getID() + co.getCourseCode() + co.getSemester(), e);

			assertTrue(driver._enrolment.get(s.getID() + co.getCourseCode() + co.getSemester()).isEnrolled(s, co));
			assertFalse(driver._enrolment.get(s.getID() + co.getCourseCode() + co.getSemester()).hasPassed(s, co));

		} catch (Exception e) {
			RMITExceptions.handleExceptions(e);
		}
		System.out.println("Test addEnrolment: End");
		Helper.drawLine();
	};

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
			// fail("");
			// import static org.junit.jupiter.api.Assertions.fail;
			// import static org.junit.Assert.assertEquals;
			// import static org.junit.Assert.assertFalse;
			// import static org.junit.Assert.assertNotSame;
			// import static org.junit.Assert.assertTrue;

		} catch (Exception e) {
			RMITExceptions.handleExceptions(e);
		} finally {
		}
	};

}
