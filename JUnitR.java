package SEF;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileNotFoundException;
import java.util.Comparator;

import org.junit.jupiter.api.Test;

class JUnitR {

	@Test
	public void setup() {
		try {
			Driver driver = new Driver();
			driver.loadData();
			driver._users.sort(Comparator.comparing(User::getName));
			driver._courses.sort(Comparator.comparing(Course::getCourseCode));
			driver._enrolment.sort(Comparator.comparing(Enrolment::getCourseCode));

			for (int i = 0; i < driver._users.size(); i++)
				System.out.println("User: " + driver._users.get(i).getName() + " | " + driver._users.get(i).getID()
						+ " | " + GlobalClass.roleDesc[driver._users.get(i).getRole()]);

			for (int i = 0; i < driver._courses.size(); i++)
				System.out.println("Course: " + driver._courses.get(i).getCourseCode() + " | " + " | "
						+ driver._courses.get(i).getCourseTitle());

			for (int i = 0; i < driver._enrolment.size(); i++)
				System.out.println("Enrolment: " + driver._enrolment.get(i).getStudent().getID() + " | "
						+ driver._enrolment.get(i).getCourseCode() + " | " + driver._enrolment.get(i).getSemester()
						+ " | " + driver._enrolment.get(i).getGrade());

		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		} catch (Exception e) {
			e.printStackTrace();
		}
	};

	@Test
	public void addNewExemption() {
		try {
			Driver driver = new Driver();
			driver.loadData();
			driver._users.sort(Comparator.comparing(User::getName));
			driver._courses.sort(Comparator.comparing(Course::getCourseCode));
			driver._enrolment.sort(Comparator.comparing(Enrolment::getCourseCode));

			// create new enrollment but don't add to _enrolment, thus should not exists
			User u = driver._users.get(driver.getIndexOfUser("s3665980"));
			Student s = (Student) u;
			Enrolment e = new Enrolment(s, "COSC2531", "1720");

			// false because even though it's created, driver._enrolment.add(e) was not done

		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		} catch (NullPointerException e) {
			System.out.println("NullPointerException");
		} catch (Exception e) {
			e.printStackTrace();
		}
	};

	@Test
	public void addExistingExemption() {
		try {
			Driver driver = new Driver();
			driver.loadData();
			driver._users.sort(Comparator.comparing(User::getName));
			driver._courses.sort(Comparator.comparing(Course::getCourseCode));
			driver._enrolment.sort(Comparator.comparing(Enrolment::getCourseCode));
			User u = driver._users.get(driver.getIndexOfUser("s3665980"));
			Student s = (Student) u;
			Enrolment e = new Enrolment(s, "COSC2531", "1720");

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
			driver._users.sort(Comparator.comparing(User::getName));
			driver._courses.sort(Comparator.comparing(Course::getCourseCode));
			driver._enrolment.sort(Comparator.comparing(Enrolment::getCourseCode));

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
			driver._users.sort(Comparator.comparing(User::getName));
			driver._courses.sort(Comparator.comparing(Course::getCourseCode));
			driver._enrolment.sort(Comparator.comparing(Enrolment::getCourseCode));

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
			driver._users.sort(Comparator.comparing(User::getName));
			driver._courses.sort(Comparator.comparing(Course::getCourseCode));
			driver._enrolment.sort(Comparator.comparing(Enrolment::getCourseCode));

			int newLoad = 1;
			User u = driver._users.get(driver.getIndexOfUser("s3665980"));
			Student s = (Student) u;
			Enrolment e = new Enrolment(s, "COSC2531", "1720");
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
		fail("");
	};

	@Test
	public void addExistingOffering() {
		fail("");
	};

	@Test
	public void addNewLecturer() {
		fail("");
	};

	@Test
	public void addExistingLectuer() {
		fail("");
	};

}
