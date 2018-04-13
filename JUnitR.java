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
		try {
			Driver driver = new Driver();
			driver.loadData();

			for (Entry<String, User> entry : driver._users.entrySet()) {
				User u = entry.getValue();
				System.out.println(
						"User: " + u.getName() + separator + u.getID() + separator + GlobalClass.roleDesc[u.getRole()]);
			}

			Helper.drawLine();

			for (Entry<String, Course> entry : driver._courses.entrySet()) {
				Course c = entry.getValue();
				System.out.println("Course: " + c.getCourseCode() + separator + c.getCourseTitle());
			}

			for (Entry<String, CourseOffering> entry : driver._courseOffering.entrySet()) {
				CourseOffering co = entry.getValue();
				System.out.println("Course Offering: " + co.getSemester() + separator + co.getCourseCode() + separator
						+ driver._courses.get(co.getCourseCode()).getCourseTitle());
			}

			Helper.drawLine();

			Helper.drawLine();
			for (int i = 0; i < driver._enrolment.size(); i++)
				System.out.println("Enrolment: " + driver._enrolment.get(i).getStudent().getID() + separator
						+ driver._enrolment.get(i).getCourseCode() + separator + driver._enrolment.get(i).getSemester()
						+ separator + driver._enrolment.get(i).getGrade());

			Helper.drawLine();

		} catch (Exception e) {
			RMITExceptions.handleExceptions(e);
		} finally {
		}
	};

	@Test
	// add new waiver and add existing waiver
	public void addWaiver() {
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
		} finally {
		}
	};

	// change student's max load per term
	@Test
	public void changeLoadCorrectValue() {
		try {
			Driver driver = new Driver();
			driver.loadData();

			int newLoad = 2;
			String userID = driver._users.get("s3665980").getID();
			assertTrue(newLoad <= GlobalClass.maxLoad & driver.changeLoad(userID, "1810", newLoad));

		} catch (Exception e) {
			RMITExceptions.handleExceptions(e);
		} finally {
		}
	};

	@Test
	public void changeLoadIncorrectValue() {
		try {
			Driver driver = new Driver();
			driver.loadData();

			int newLoad = 12;
			String userID = driver._users.get("s3665980").getID();
			assertFalse(newLoad <= GlobalClass.maxLoad & driver.changeLoad(userID, "1810", newLoad));

		} catch (Exception e) {
			RMITExceptions.handleExceptions(e);
		}
	};

	// what if student is doing 2 modules for the term and maxLoad changed to 1
	@Test
	public void changeLoadHitMaxLoad() {
		try {
			Driver driver = new Driver();
			driver.loadData();

			int newLoad = 1;
			User u = driver._users.get("s3665980");
			Student s = (Student) u;
			String courseCode = "COSC2531";
			String semester = "1810";
			CourseOffering co = new CourseOffering(courseCode, semester);
			Enrolment e = new Enrolment(s, co);
			System.out.println("Enrolment = " + s.countEnrolment(driver._enrolment, s.getID(), semester)
					+ " | MaxLoad = " + s.getMaxLoad());
			Helper.drawLine();
			driver._enrolment.add(e);
			System.out.println("Enrolment = " + s.countEnrolment(driver._enrolment, s.getID(), semester)
					+ " | MaxLoad = " + s.getMaxLoad());
			Helper.drawLine();
			assertFalse(newLoad >= s.countEnrolment(driver._enrolment, s.getID(), semester)
					& driver.changeLoad(s.getID(), semester, newLoad));

		} catch (Exception e) {
			RMITExceptions.handleExceptions(e);
		} finally {
		}
	};

	// add course offerings - new and existing
	@Test
	public void addOffering() {

		try {
			Driver driver = new Driver();
			driver.loadData();

			String courseCode = "COSC2615";
			String semester = "1810";

			// did not add and therefore, false
			assertTrue(driver._courseOffering.get(courseCode + semester).getCourseCode().equals(""));
			System.out.println("Cannot get CourseOffering: " + courseCode + separator + semester);
			Helper.drawLine();

			// added offering
			driver._courseOffering.put(courseCode + semester, new CourseOffering(courseCode, semester));

			// have added and therefore, true
			assertTrue(driver._courseOffering.get(courseCode + semester).courseOffered(courseCode, semester));

		} catch (Exception e) {
			RMITExceptions.handleExceptions(e);
		} finally {
		}

	};

	// add lecturer to course offering - new and existing
	@Test
	public void addLecturer() {
		try {
			Driver driver = new Driver();
			driver.loadData();

			User u = driver._users.get("e12345");
			Lecturer l = (Lecturer) u;
			String key = "ISYS1117" + "1810";

			int il = driver.getIndexOfLecturer(driver._courseOffering.get(key).getLecturer(), l);

			assertFalse(il >= 0);
			System.out.println("Cannot get CourseOffering Lecturers: " + il);
			Helper.drawLine();

			driver._courseOffering.get(key).addLecturer(l);
			il = driver.getIndexOfLecturer(driver._courseOffering.get(key).getLecturer(), l);
			ArrayList<Lecturer> courseLecturer = driver._courseOffering.get(key).getLecturer();
			assertEquals(courseLecturer.get(il).getID(), l.getID());

		} catch (Exception e) {
			RMITExceptions.handleExceptions(e);
		} finally {
		}
	};

	// add course - new and existing
	@Test
	public void addCourse() {

		try {
			Driver driver = new Driver();
			driver.loadData();

			String courseCode = "MATH101";
			String title = "Maths for Beginners";
			assertTrue(driver._courses.get(courseCode).equals(""));
			System.out.println("JUnit.addCourse | Cannot find Course: " + courseCode);
			Helper.drawLine();
			driver._courses.put(courseCode, new Course(courseCode, title));
			assertTrue(driver._courses.get(courseCode).getCourseTitle().equals(title));

		} catch (Exception e) {
			RMITExceptions.handleExceptions(e);
		} finally {
		}
	};

	// add enrolment - new and existing
	@Test
	public void addEnrolment() {
		try {
			Driver driver = new Driver();
			driver.loadData();
			User u = driver._users.get("s3706098");
			Student s = (Student) u;
			String courseCode = "COSC1295";
			String semester = "1810";

			CourseOffering co = new CourseOffering(courseCode, semester);
			Enrolment e = new Enrolment(s, co);

			if (driver._courseOffering.get(courseCode + semester).courseOffered(courseCode, semester))
				System.out.println("Course Offered: " + " | " + co.getCourseCode() + separator + co.getSemester());
			else
				System.out.println("Course Not Offered: " + " | " + co.getCourseCode() + separator + co.getSemester());

			if (driver.getIndexOfEnrolment(s, co) < 0)
				System.out.println("Enrolment Not Found: " + e.getStudent().getID() + separator + e.getCourseCode()
						+ separator + e.getSemester());
			else
				System.out.println("Enrolment Found: " + e.getStudent().getID() + separator + e.getCourseCode()
						+ separator + e.getSemester());

			driver._enrolment.add(e);

			assertTrue(driver._enrolment.get(driver.getIndexOfEnrolment(s, co)).isStudentEnrolled(s, co));
			assertFalse(driver._enrolment.get(driver.getIndexOfEnrolment(s, co)).hasPassed(s, co));

		} catch (Exception e) {
			RMITExceptions.handleExceptions(e);
		}
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

		} catch (Exception e) {
			RMITExceptions.handleExceptions(e);
		} finally {
		}
	};

}
