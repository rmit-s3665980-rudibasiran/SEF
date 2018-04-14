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
				User ui = entry.getValue();
				System.out.println("User: " + ui.getID() + separator + ui.getName() + separator
						+ GlobalClass.roleDesc[ui.getRole()]);
			}

			for (Entry<String, Course> entry : driver._courses.entrySet()) {
				Course ci = entry.getValue();
				System.out.println("Course: " + ci.getCourseCode() + separator + ci.getCourseTitle());
			}

			for (Entry<String, CourseOffering> entry : driver._courseOffering.entrySet()) {
				CourseOffering coi = entry.getValue();
				System.out.println("Course Offering: " + coi.getCourseCode() + separator + coi.getSemester() + separator
						+ driver._courses.get(coi.getCourseCode()).getCourseTitle());
			}

			for (Entry<String, Enrolment> entry : driver._enrolment.entrySet()) {
				Enrolment ei = entry.getValue();
				Student si = ei.getStudent();
				CourseOffering coi = ei.getCourseOffering();
				Course ci = driver._courses.get(ei.getCourseCode());
				System.out.println("Enrolment: " + si.getID() + separator + si.getName() + separator
						+ ei.getCourseCode() + separator + ci.getCourseTitle() + separator + ei.getSemester()
						+ separator + ei.getGrade());

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
			Course c = driver._courses.get(courseCode);
			CourseOffering co = new CourseOffering(c, semester);
			String coKey = Helper.createCourseOfferingKey(co);
			System.out.println("Requesting waiver for: " + s.getID() + separator + co.getCourseCode() + separator
					+ co.getSemester());
			// assertFalse to check that student does not have this particular waiver
			if (driver._courseOffering.get(coKey).hasWaiver(s))
				System.out.println(
						"Waiver Exists: " + s.getID() + separator + co.getCourseCode() + separator + co.getSemester());
			else
				System.out.println("Waiver does not Exist: " + s.getID() + separator + co.getCourseCode() + separator
						+ co.getSemester());

			// add waiver to courseOffering waivers
			driver._courseOffering.get(coKey).addWaiver(s, reason);
			System.out.println("Added waiver for: " + co.getCourseCode() + separator + co.getSemester());
			if (driver._courseOffering.get(coKey).hasWaiver(s))
				System.out.println(
						"Waiver Exists: " + s.getID() + separator + co.getCourseCode() + separator + co.getSemester());
			else
				System.out.println("Waiver does not Exist: " + s.getID() + separator + co.getCourseCode() + separator
						+ co.getSemester());

		} catch (Exception e) {
			RMITExceptions.handleExceptions(e);
		}
		System.out.println("Test addWaiver: End");
		Helper.drawLine();
	};

	// change student's max load per term
	@Test
	public void changeLoad() {

		System.out.println("Test changeLoad: Begin");
		try {
			Driver driver = new Driver();
			driver.loadData();

			int newLoad = 2;
			String userID = driver._users.get("s3665980").getID();
			System.out.println("Increasing Load to " + newLoad + " Successful");
			assertTrue(newLoad <= GlobalClass.maxLoad & driver.changeLoad(userID, "1810", newLoad));

			newLoad = 12;

			assertFalse(newLoad <= GlobalClass.maxLoad & driver.changeLoad(userID, "1810", newLoad));
			System.out.println("Increasing Load to " + newLoad + " Unsuccessful");
		} catch (Exception e) {
			RMITExceptions.handleExceptions(e);
		}
		System.out.println("Test changeLoad: End");
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
			Student s = (Student) driver._users.get("s3665980");
			String courseCode = "COSC2531";
			String semester = "1810";
			Course c = driver._courses.get(courseCode);
			CourseOffering co = new CourseOffering(c, semester);
			Enrolment e = new Enrolment(s, co);
			String eKey = Helper.createEnrolmentKey(s, co);

			System.out.println("Checking Current Load = " + s.countEnrolment(driver._enrolment, s, semester)
					+ " | MaxLoad = " + s.getMaxLoad());

			driver._enrolment.put(eKey, e);

			assertFalse(newLoad >= s.countEnrolment(driver._enrolment, s, semester)
					& driver.changeLoad(s.getID(), semester, newLoad));

			System.out.println(
					"Adding Enrolment :" + s.getID() + separator + e.getCourseCode() + separator + e.getSemester());
			System.out.println("Checking New Load: " + s.countEnrolment(driver._enrolment, s, semester)
					+ " | MaxLoad = " + s.getMaxLoad());

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
			if (driver._courseOffering.containsKey(coKey))
				System.out.println("CourseOffering Exists: " + co.getCourseCode() + separator + co.getSemester());
			else
				System.out
						.println("CourseOffering does not Exist: " + co.getCourseCode() + separator + co.getSemester());

			System.out.println("No. of CourseOffering before Addition: " + driver._courseOffering.size());
			// added offering. since treemap does not allow duplicates, it doesn't matter
			// how
			// many time we add
			driver._courseOffering.put(coKey, new CourseOffering(c, semester));
			driver._courseOffering.put(coKey, new CourseOffering(c, semester));
			driver._courseOffering.put(coKey, new CourseOffering(c, semester));

			System.out.println("No. of CourseOffering after Addition: " + driver._courseOffering.size());

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

			ArrayList<Lecturer> courseLecturer = driver._courseOffering.get(coKey).getLecturer();
			if (!courseLecturer.isEmpty())
				for (int i = 0; i <= courseLecturer.size(); i++)
					System.out.println("Lecturers for " + co.getCourseCode() + separator + co.getSemester() + separator
							+ courseLecturer.get(i).getID() + separator + courseLecturer.get(i).getName());

			courseLecturer.add(l);
			courseLecturer.add(new Lecturer("t1234", "Guest Lecturer 1", "t1234", GlobalClass.Lecturer));
			courseLecturer.add(new Lecturer("t5678", "Guest Lecturer 2", "t1234", GlobalClass.Lecturer));

			if (!courseLecturer.isEmpty())
				for (int i = 0; i < courseLecturer.size(); i++) {
					System.out.println("Lecturers for " + co.getCourseCode() + separator + c.getCourseTitle()
							+ separator + co.getSemester() + separator + courseLecturer.get(i).getName() + separator
							+ courseLecturer.get(i).getName());
				}
			int il = driver.getIndexOfLecturer(courseLecturer, l);
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
			Course c = new Course(courseCode, title);
			if (driver._courses.containsKey(c.getCourseCode()))
				System.out.println("Course Exists: " + c.getCourseCode() + separator + c.getCourseTitle());
			else
				System.out.println("Course does not Exist: " + c.getCourseCode() + separator + c.getCourseTitle());
			System.out.println("Number of Courses before Addition: " + driver._courses.size());
			driver._courses.put(courseCode, c);
			driver._courses.put(courseCode, c);
			assertTrue(driver._courses.get(courseCode).getCourseTitle().equals(title));
			System.out.println("Number of Courses after Addition: " + driver._courses.size());

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
			String eKey = Helper.createEnrolmentKey(s, co);

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

			assertTrue(driver._enrolment.containsKey(eKey));
			assertFalse(driver._enrolment.get(eKey).hasPassed(s, co));

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
