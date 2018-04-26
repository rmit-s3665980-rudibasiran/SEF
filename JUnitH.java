package SEF;

import static org.junit.Assert.assertEquals;

import java.util.Map.Entry;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class JUnitH {
	String separator = " | ";

	@Test
	void GradeTest() {
		Driver driver = new Driver();
		driver.loadData();

		User u = driver._users.get("s3685849");
		Student s = (Student) u;
		String courseCode = "COSC1295";
		String semester = "1810";
		String grade = "HD";

		Course c = driver._courses.get(courseCode);
		CourseOffering co = new CourseOffering(c, semester);
		Enrolment e = new Enrolment(s, co, grade);
		String eKey = Helper.createEnrolmentKey(s, co);

		if (driver._enrolment.containsKey(eKey)) {
			System.out.println("Enrolment exists, setting grade.");
			driver._enrolment.get(eKey).setGrade(grade);
		} else {
			System.out.println("Enrolment does not exist, cannot get grade");
			Assert.fail("enrollment does not exist");
		}

		driver._enrolment.put(eKey, e);

		if (driver._enrolment.containsKey(eKey)) {
			System.out.println("Enrolment exists, setting grade.");
			driver._enrolment.get(eKey).setGrade(grade);
		} else {
			System.out.println("Enrolment does not exist, cannot get grade");
		}
		String output = driver._enrolment.get(eKey).getGrade();
		assertEquals(grade, output);
	}

	@Test
	void ViewPerformance() {

		Driver driver = new Driver();
		driver.loadData();

		User u = driver._users.get("s3685849");
		Student s = (Student) u;
		for (Entry<String, Enrolment> entry : driver._enrolment.entrySet()) {
			Enrolment ei = entry.getValue();
			Student si = ei.getStudent();
			String sID = si.getID();
			CourseOffering coi = ei.getCourseOffering();
			Course ci = driver._courses.get(ei.getCourseCode());
			if (ei.getCourseOffering().equals(coi) & sID.equals(s.getID()))
				System.out.println("Enrolment: " + sID + separator + si.getName() + separator + ei.getCourseCode()
						+ separator + ci.getCourseTitle() + separator + ei.getSemester() + separator
						+ (ei.getGrade().equals("") ? "-" : ei.getGrade()));

		}
		// should show waivers too & their status - whether added to Enrollment or still
		// only in CourseOffering
	}
}
