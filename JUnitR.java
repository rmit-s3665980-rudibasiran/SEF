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
				System.out.println("Course: " + driver._courses.get(i).getCourseCode() + " | "
						+ driver._courses.get(i).getSemester() + " | " + driver._courses.get(i).getCourseTitle());

			for (int i = 0; i < driver._enrolment.size(); i++)
				System.out.println("Enrolment: " + driver._enrolment.get(i).getStudentID() + " | "
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
			Enrolment e = new Enrolment("s3665980", "COSC2531", "1720", GlobalClass.isWaived);
			int i = driver.getIndexOfEnrolment(driver._enrolment, e.getStudentID(), e.getCourseCode(), e.getSemester());
			assertFalse(driver._enrolment.get(i).hasWaiver());
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
			Enrolment e = new Enrolment("s3665980", "ISYS1055", "1720", GlobalClass.isWaived);
			int i = driver.getIndexOfEnrolment(driver._enrolment, e.getStudentID(), e.getCourseCode(), e.getSemester());
			assertTrue(driver._enrolment.get(i).hasWaiver());

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
		fail("");
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
