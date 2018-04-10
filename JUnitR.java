package SEF;

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
		fail("");
	};

	@Test
	public void addExistingExemption() {
		fail("");
	};

	@Test
	public void changeLoadCorrectValue() {
		fail("");
	};

	@Test
	public void changeLoadIncorrectValue() {
		fail("");
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
