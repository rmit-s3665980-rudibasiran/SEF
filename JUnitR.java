package SEF;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
			driver._courseOffering.sort(Comparator.comparing(CourseOffering::getCourseCode));

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
	public void addExemption() {
		try {
			Driver driver = new Driver();
			driver.loadData();
			driver._users.sort(Comparator.comparing(User::getName));
			driver._courses.sort(Comparator.comparing(Course::getCourseCode));
			driver._enrolment.sort(Comparator.comparing(Enrolment::getCourseCode));
			driver._courseOffering.sort(Comparator.comparing(CourseOffering::getCourseCode));

			// create new enrollment but don't add to _courseOffering waivers, thus should
			// not exists
			User u = driver._users.get(driver.getIndexOfUser("s3665980"));
			Student s = (Student) u;
			String courseCode = "COSC2531";
			String semester = "1810";
			String reason = "Prior experience";

			// assertFalse to check that student does not have waiver
			assertFalse(driver._courseOffering.get(driver.getIndexOfOffering(courseCode, semester)).getWaiver(s));

			// add waiver to courseOffering waivers
			driver._courseOffering.get(driver.getIndexOfOffering(courseCode, semester)).addWaiver(s, reason);

			// assertTrue to check that student does indeed have waiver
			assertTrue(driver._courseOffering.get(driver.getIndexOfOffering(courseCode, semester)).getWaiver(s));

		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		} catch (NullPointerException e) {
			System.out.println("NullPointerException");
		} catch (Exception e) {
			e.printStackTrace();
		}
	};

}
