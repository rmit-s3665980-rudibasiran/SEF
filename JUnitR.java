package SEF;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class JUnitR {

	@Test
	public void setup() {
		try {
			Driver driver = new Driver();
			driver.loadData();

			for (int i = 0; i < driver._users.size(); i++)
				System.out.println("User: " + driver._users.get(i).getName() + " | " + driver._users.get(i).getID()
						+ " | " + GlobalClass.roleDesc[driver._users.get(i).getRole()]);

			Helper.drawLine();

			for (int i = 0; i < driver._courses.size(); i++)
				System.out.println("Course: " + driver._courses.get(i).getCourseCode() + " | "
						+ driver._courses.get(i).getCourseTitle());

			Helper.drawLine();

			for (int i = 0; i < driver._courseOffering.size(); i++) {
				System.out.println("Course Offering: " + driver._courseOffering.get(i).getSemester() + " | "
						+ driver._courseOffering.get(i).getCourseCode() + " | "
						+ driver._courses.get(driver.getIndexOfCourse(driver._courseOffering.get(i).getCourseCode()))
								.getCourseTitle());
			}

			Helper.drawLine();
			for (int i = 0; i < driver._enrolment.size(); i++)
				System.out.println("Enrolment: " + driver._enrolment.get(i).getStudent().getID() + " | "
						+ driver._enrolment.get(i).getCourseCode() + " | " + driver._enrolment.get(i).getSemester()
						+ " | " + driver._enrolment.get(i).getGrade());

			Helper.drawLine();

		} catch (Exception e) {
			RMITExceptions.handleExceptions(e);
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
			User u = driver._users.get(driver.getIndexOfUser("s3665980"));
			Student s = (Student) u;
			String courseCode = "COSC2531";
			String semester = "1810";
			String reason = "Prior experience";

			// assertFalse to check that student does not have this particular waiver
			assertFalse(driver._courseOffering.get(driver.getIndexOfOffering(courseCode, semester)).hasWaiver(s));

			// add waiver to courseOffering waivers
			driver._courseOffering.get(driver.getIndexOfOffering(courseCode, semester)).addWaiver(s, reason);

			// assertTrue to check that student does indeed have waiver
			assertTrue(driver._courseOffering.get(driver.getIndexOfOffering(courseCode, semester)).hasWaiver(s));

		} catch (Exception e) {
			RMITExceptions.handleExceptions(e);
		}
	};

	// change student's max load per term
	@Test
	public void changeLoadCorrectValue() {
		try {
			Driver driver = new Driver();
			driver.loadData();

			int newLoad = 2;
			String userID = driver._users.get(driver.getIndexOfUser("s3665980")).getID();
			assertTrue(newLoad <= GlobalClass.maxLoad & driver.changeLoad(userID, "1810", newLoad));

		} catch (Exception e) {
			RMITExceptions.handleExceptions(e);
		}
	};

	@Test
	public void changeLoadIncorrectValue() {
		try {
			Driver driver = new Driver();
			driver.loadData();

			int newLoad = 12;
			String userID = driver._users.get(driver.getIndexOfUser("s3665980")).getID();
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
			User u = driver._users.get(driver.getIndexOfUser("s3665980"));
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

			int i = driver.getIndexOfOffering(courseCode, semester);
			// did not add and therefore, false
			assertFalse(i >= 0);
			System.out.println("Cannot get CourseOffering: " + i);
			Helper.drawLine();

			// added offering
			driver._courseOffering.add(new CourseOffering(courseCode, semester));

			// have added and therefore, true
			assertTrue(driver._courseOffering.get(driver.getIndexOfOffering(courseCode, semester))
					.courseOffered(courseCode, semester));

		} catch (Exception e) {
			RMITExceptions.handleExceptions(e);
		}

	};

	// add lecturer to course offering - new and existing
	@Test
	public void addLecturer() {
		try {
			Driver driver = new Driver();
			driver.loadData();

			int x = driver.getIndexOfUser("e12345");
			User u = driver._users.get(x);
			Lecturer l = (Lecturer) u;
			int y = driver.getIndexOfOffering("ISYS1117", "1810");

			int il = driver.getIndexOfLecturer(driver._courseOffering.get(y).getLecturer(), l);

			assertFalse(il >= 0);
			System.out.println("Cannot get CourseOffering Lecturers: " + il);
			Helper.drawLine();

			driver._courseOffering.get(y).addLecturer(l);
			il = driver.getIndexOfLecturer(driver._courseOffering.get(y).getLecturer(), l);
			ArrayList<Lecturer> courseLecturer = driver._courseOffering.get(y).getLecturer();
			assertEquals(courseLecturer.get(il).getID(), l.getID());

		} catch (Exception e) {
			RMITExceptions.handleExceptions(e);
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
			assertFalse(driver.getIndexOfCourse(courseCode) >= 0);
			System.out.println("JUnit.addCourse | Cannot find Course: " + driver.getIndexOfCourse(courseCode));
			Helper.drawLine();
			driver._courses.add(new Course(courseCode, title));
			assertTrue(driver._courses.get(driver.getIndexOfCourse(courseCode)).getCourseTitle().equals(title));

		} catch (Exception e) {
			RMITExceptions.handleExceptions(e);
		}

	};

	// add enrolment - new and existing
	@Test
	public void addEnrolment() {
		try {
			Driver driver = new Driver();
			driver.loadData();

			User u = driver._users.get(driver.getIndexOfUser("s3665980"));
			Student s = (Student) u;
			String courseCode = "COSC1295";
			String semester = "1810";
			String grade = "HD";

			CourseOffering co = new CourseOffering(courseCode, semester);
			Enrolment e = new Enrolment(s, co, grade);

			assertTrue(driver._courseOffering.get(driver.getIndexOfOffering(courseCode, semester))
					.courseOffered(courseCode, semester));

			assertFalse(driver.getIndexOfEnrolment(s, co) >= 0);
			System.out.println("JUnit addEnrolment | Cannot find enrolment: " + driver.getIndexOfEnrolment(s, co));
			driver._enrolment.add(e);
			assertTrue(driver._enrolment.get(driver.getIndexOfEnrolment(s, co)).isStudentEnrolled(s, co));
			assertTrue(driver._enrolment.get(driver.getIndexOfEnrolment(s, co)).hasPassed(s, co));
			e.setGrade("F");
			assertFalse(driver._enrolment.get(driver.getIndexOfEnrolment(s, co)).hasPassed(s, co));

		} catch (Exception e) {
			RMITExceptions.handleExceptions(e);
		}
	};

	// test template
	// do not delete as eclipse will remove the import headers automatically
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
