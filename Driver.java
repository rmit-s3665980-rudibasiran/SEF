package SEF;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {

	ArrayList<Course> _courses = new ArrayList<>();
	ArrayList<User> _users = new ArrayList<>();
	ArrayList<Enrolment> _enrolment = new ArrayList<>();

	public Driver() {
	}

	public void loadData() throws FileNotFoundException {

		try {
			// amend to your own path
			String path = "/Users/rudibasiran/Google Drive/RMIT/Java/oxygen/exercise/src/SEF/";
			Scanner input = new Scanner(new File(path + "data.txt"));
			input.useDelimiter(";|\n");

			while (input.hasNext()) {
				String role = input.next();
				if (role.equals(GlobalClass.roleDesc[GlobalClass.Student])) {
					String userID = input.next();
					String name = input.next();
					String password = input.next();
					_users.add(new Student(userID, name, password, GlobalClass.Student));

				} else if (role.equals(GlobalClass.roleDesc[GlobalClass.Lecturer])) {
					String userID = input.next();
					String name = input.next();
					String password = input.next();
					_users.add(new Lecturer(userID, name, password, GlobalClass.Lecturer));

				} else if (role.equals(GlobalClass.roleDesc[GlobalClass.ProgramCoordinator])) {
					String userID = input.next();
					String name = input.next();
					String password = input.next();
					_users.add(new ProgramCoordinator(userID, name, password, GlobalClass.ProgramCoordinator));

				} else if (role.equals(GlobalClass.roleDesc[GlobalClass.Admin])) {
					String userID = input.next();
					String name = input.next();
					String password = input.next();
					_users.add(new Admin(userID, name, password, GlobalClass.Admin));

				} else if (role.equals("Course")) {
					String courseCode = input.next();
					String semester = input.next();
					String title = input.next();
					String desc = input.next();
					_courses.add(new Course(courseCode, semester, title, desc));

				} else if (role.equals("Enrolment")) {
					String userID = input.next();
					String courseCode = input.next();
					String semester = input.next();
					Enrolment e = new Enrolment(userID, courseCode, semester);
					_enrolment.add(e);

				} else if (role.equals("Waiver")) {
					String userID = input.next();
					String courseCode = input.next();
					String semester = input.next();
					Enrolment e = new Enrolment(userID, courseCode, semester, GlobalClass.isWaived);
					_enrolment.add(e);
				}
			}
		}

		catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void menuAction(int menuItem, User currentUser) {

		if (menuItem == GlobalClass.viewCourse) {
			System.out.println("View Course");
		} else if (menuItem == GlobalClass.addCourse) {
			System.out.println("Add Course");
		} else if (menuItem == GlobalClass.viewPerformance) {
			System.out.println("View Performance");
		} else if (menuItem == GlobalClass.addExemption) {
			System.out.println("Add Exemption");
		} else if (menuItem == GlobalClass.changeLoad) {
			System.out.println("Change Load");
		} else if (menuItem == GlobalClass.assignGrade) {
			System.out.println("Assign Grade");
		} else if (menuItem == GlobalClass.addOffering) {
			System.out.println("Add Offering");
		} else if (menuItem == GlobalClass.addLecturer) {
			System.out.println("Add Lecture");
		} else if (menuItem == GlobalClass.advanceWeek) {
			System.out.println("Advance Week ");
		} else if (menuItem == GlobalClass.enrolCourse) {
			System.out.println("Enrol Course");
		} else if (menuItem == GlobalClass.dropCourse) {
			System.out.println("Drop Course");
		} else if (menuItem == GlobalClass.applyWaivers) {
			System.out.println("Apply Waiver");
		}

		if (menuItem != GlobalClass.quitMenu) {
			Helper.drawLine();
			Helper.getAnyKey("Press <Enter> to continue ");
		}
	}

	public void viewCourse(String courseCode) {

	}

	public Boolean addCourse(String courseCode, String semester, String preReq, String cDesc) {
		Boolean status = true;

		return status;
	}

	public ArrayList<String> viewPerformance(String studentID) {
		ArrayList<String> performance = new ArrayList<>();
		return performance;
	}

	public Boolean addExemption(String studentID, String courseCode, String semester, Boolean flag) {
		Boolean status = true;
		return status;
	}

	public Boolean changeLoad(String studentID, String semester) {
		Boolean status = true;
		return status;
	}

	public Boolean assignGrade(String studentID, String courseCode, String semester, String grade) {
		Boolean status = true;
		return status;
	}

	public Boolean addOffering(String courseCode, String semester, Boolean active) {
		Boolean status = true;
		return status;
	}

	public Boolean addOffering(String courseCode, String semester, int ttlStudent, Boolean active) {
		Boolean status = true;
		return status;
	}

	public Boolean addLecturer(String courseCode, String semester, Lecturer lecturer) {
		Boolean status = true;
		return status;
	}

	public Boolean advanceWeek(String courseCode, String semester) {
		Boolean status = true;
		return status;
	}

	public Boolean advanceWeek(String courseCode, String semester, int numWeeks) {
		Boolean status = true;
		return status;
	}

	public Boolean enrolCourse(String studentID, String courseCode, String semester) {
		Boolean status = true;
		return status;
	}

	public Boolean dropCourse(String studentID, String courseCode, String semester) {
		Boolean status = true;
		return status;
	}

	public void applyWaivers(String studentID, String courseCode, String semester, String reason) {

	}

	public int getIndexOfCourse(String courseCode, String semester) {
		int result = -1;
		for (int i = 0; i < _courses.size(); i++) {
			if (_courses.get(i).checkCourseExists(courseCode, semester)) {
				result = i;
				break;
			}
		}
		return result;
	}

}
