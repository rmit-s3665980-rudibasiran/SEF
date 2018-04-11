package SEF;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileNotFoundException;
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

			for (int i = 0; i < driver._courses.size(); i++)
				System.out.println("Course: " + driver._courses.get(i).getCourseCode() + " | "
						+ driver._courses.get(i).getCourseTitle());

			for (int i = 0; i < driver._courseOffering.size(); i++) {
				System.out.println("Course Offering: " + driver._courseOffering.get(i).getSemester() + " | "
						+ driver._courseOffering.get(i).getCourseCode() + " | "
						+ driver._courses.get(driver.getIndexOfCourse(driver._courseOffering.get(i).getCourseCode()))
								.getCourseTitle());
			}

			for (int i = 0; i < driver._enrolment.size(); i++)
				System.out.println("Enrolment: " + driver._enrolment.get(i).getStudent().getID() + " | "
						+ driver._enrolment.get(i).getCourseCode() + " | " + driver._enrolment.get(i).getSemester()
						+ " | " + driver._enrolment.get(i).getGrade());

		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		} catch (NullPointerException e) {
			System.out.println("NullPointerException");
		} catch (Exception e) {
			e.printStackTrace();
		}
	};

	@Test
	// add new waiver and add existing waiver
	public void addExemption() {
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

		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		} catch (NullPointerException e) {
			System.out.println("NullPointerException");
		} catch (Exception e) {
			e.printStackTrace();
		}
	};

	@Test
	public void changeLoadCorrectValue() {
		try {
			Driver driver = new Driver();
			driver.loadData();

			int newLoad = 2;
			String userID = driver._users.get(driver.getIndexOfUser("s3665980")).getID();
			assertTrue(newLoad <= GlobalClass.maxLoad & driver.changeLoad(userID, "1810", newLoad));

		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		} catch (NullPointerException e) {
			System.out.println("NullPointerException");
		} catch (Exception e) {
			e.printStackTrace();
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

		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		} catch (NullPointerException e) {
			System.out.println("NullPointerException");
		} catch (Exception e) {
			e.printStackTrace();
		}
	};

	@Test
	public void changeLoadHitMaxLoad() {
		try {
			Driver driver = new Driver();
			driver.loadData();

			int newLoad = 1;
			User u = driver._users.get(driver.getIndexOfUser("s3665980"));
			Student s = (Student) u;
			Enrolment e = new Enrolment(s, "COSC2531", "1810");
			System.out.println("Enrolment = " + s.countEnrolment(driver._enrolment, s.getID(), "1810") + " | MaxLoad = "
					+ s.getMaxLoad());

			driver._enrolment.add(e);
			System.out.println("Enrolment = " + s.countEnrolment(driver._enrolment, s.getID(), "1810") + " | MaxLoad = "
					+ s.getMaxLoad());
			assertFalse(newLoad >= s.countEnrolment(driver._enrolment, s.getID(), "1810")
					& driver.changeLoad(s.getID(), "1810", newLoad));

		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		} catch (NullPointerException e) {
			System.out.println("NullPointerException");
		} catch (Exception e) {
			e.printStackTrace();
		}
	};

	@Test
	public void addNewOffering() {

		try {
			Driver driver = new Driver();
			driver.loadData();

			String courseCode = "COSC2615";
			String semester = "1810";

			int i = driver.getIndexOfOffering(courseCode, semester);
			// did not add and therefore, false
			assertFalse(i >= 0);
			System.out.println("Cannot get CourseOffering: " + i);

		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		} catch (NullPointerException e) {
			System.out.println("NullPointerException");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("addNewOffering ArrayIndexOutOfBoundsException");
		} catch (Exception e) {
			e.printStackTrace();
		}

	};

	@Test
	public void addExistingOffering() {

		try {
			Driver driver = new Driver();
			driver.loadData();

			String courseCode = "COSC2615";
			String semester = "1810";

			// added offering
			driver._courseOffering.add(new CourseOffering(courseCode, semester));

			// have added and therefore, true
			assertTrue(driver._courseOffering.get(driver.getIndexOfOffering(courseCode, semester))
					.courseOffered(courseCode, semester));

		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		} catch (NullPointerException e) {
			System.out.println("NullPointerException");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("addExistingOffering ArrayIndexOutOfBoundsException");
		} catch (Exception e) {
			e.printStackTrace();
		}

	};

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

			driver._courseOffering.get(y).addLecturer(l);
			il = driver.getIndexOfLecturer(driver._courseOffering.get(y).getLecturer(), l);
			ArrayList<Lecturer> courseLecturer = driver._courseOffering.get(y).getLecturer();
			assertEquals(courseLecturer.get(il).getID(), l.getID());

		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		} catch (NullPointerException e) {
			System.out.println("NullPointerException");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("addLecturer ArrayIndexOutOfBoundsException");
		} catch (Exception e) {
			e.printStackTrace();
		}
	};

	@Test
	public void addNewCourse() {
		fail("");
	};

	@Test
	public void addExistingCourse() {
		fail("");
	};

}
