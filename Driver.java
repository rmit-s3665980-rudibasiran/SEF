package SEF;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class Driver {

	TreeMap<String, User> _users = new TreeMap<String, User>();
	TreeMap<String, Course> _courses = new TreeMap<String, Course>();
	TreeMap<String, CourseOffering> _courseOffering = new TreeMap<String, CourseOffering>();
	ArrayList<Enrolment> _enrolment = new ArrayList<>();

	public Driver() {
	}

	public void loadData() {

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
					_users.put(userID, new Student(userID, name, password, GlobalClass.Student));

				} else if (role.equals(GlobalClass.roleDesc[GlobalClass.Lecturer])) {
					String userID = input.next();
					String name = input.next();
					String password = input.next();
					_users.put(userID, new Lecturer(userID, name, password, GlobalClass.Lecturer));

				} else if (role.equals(GlobalClass.roleDesc[GlobalClass.ProgramCoordinator])) {
					String userID = input.next();
					String name = input.next();
					String password = input.next();
					_users.put(userID, new ProgramCoordinator(userID, name, password, GlobalClass.ProgramCoordinator));

				} else if (role.equals(GlobalClass.roleDesc[GlobalClass.Admin])) {
					String userID = input.next();
					String name = input.next();
					String password = input.next();
					_users.put(userID, new Admin(userID, name, password, GlobalClass.Admin));

				} else if (role.equals("Course")) {
					String courseCode = input.next();
					String title = input.next();
					String desc = input.next();
					_courses.put(courseCode, new Course(courseCode, title, desc));

				} else if (role.equals("CourseOffering")) {
					String courseCode = input.next();
					String semester = input.next();
					String key = courseCode + semester;
					Course c = _courses.get(courseCode);
					_courseOffering.put(key, new CourseOffering(c, semester));

				} else if (role.equals("Enrolment")) {

					String userID = input.next();
					String courseCode = input.next();
					String semester = input.next();
					String grade = input.next();

					User u = _users.get(userID);
					Student s = (Student) u;
					Enrolment e;
					Course c = _courses.get(courseCode);
					CourseOffering co = new CourseOffering(c, semester);
					if (grade != "")
						e = new Enrolment(s, co, grade);
					else
						e = new Enrolment(s, co);

					_enrolment.add(e);

				} else if (role.equals("Waiver")) {
					String userID = input.next();
					String courseCode = input.next();
					String semester = input.next();
					String reason = input.next();

					User u = _users.get(userID);
					Student s = (Student) u;
					_courseOffering.get(courseCode + semester).addWaiver(s, reason);
				}
			}

		}

		catch (Exception e) {
			RMITExceptions.handleExceptions(e);
		}
	}

	public void menuAction(int menuItem, User currentUser) {

		if (menuItem == GlobalClass.viewCourse) {
			System.out.println("View Course");
		} else if (menuItem == GlobalClass.addCourse) {
			System.out.println("Add Course");
		} else if (menuItem == GlobalClass.viewPerformance) {
			System.out.println("View Performance");
		} else if (menuItem == GlobalClass.addWaiver) {
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

	public Boolean addCourse(String courseCode, String cTitle, String preReq, String cDesc) {
		Boolean status = true;

		return status;
	}

	public ArrayList<Enrolment> viewPerformance(Student s) {
		ArrayList<Enrolment> performance = new ArrayList<>();
		return performance;
	}

	public Boolean addWaiver(String studentID, String courseCode, String semester, Boolean flag) {
		Boolean status = true;
		return status;
	}

	public Boolean addWaiver(Student s, CourseOffering co, Boolean flag) {
		Boolean status = true;
		return status;
	}

	public Boolean changeLoad(String studentID, String semester, int newLoad) {
		Boolean status = false;
		if (newLoad <= GlobalClass.maxLoad) {
			// setMaxLoad
			status = true;
		}

		return status;
	}

	public Boolean assignGrade(String studentID, String courseCode, String semester, String grade) {
		return true;
	}

	public Boolean assignGrade(Student s, CourseOffering co, String grade) {
		return true;
	}

	public Boolean addOffering(String courseCode, String semester, Boolean active) {
		Boolean status = true;
		return status;
	}

	public Boolean addOffering(CourseOffering co, Boolean active) {
		Boolean status = true;
		return status;
	}

	public Boolean addOffering(String courseCode, String semester, int ttlStudent, Boolean active) {
		Boolean status = true;
		return status;
	}

	public Boolean addOffering(CourseOffering co, int ttlStudent, Boolean active) {
		Boolean status = true;
		return status;
	}

	public Boolean addLecturer(CourseOffering co, Lecturer lecturer) {
		Boolean status = true;
		return status;
	}

	public Boolean addLecturer(String courseCode, String semester, Lecturer lecturer) {
		Boolean status = true;
		return status;
	}

	public Boolean advanceWeek(CourseOffering co) {
		Boolean status = true;
		return status;
	}

	public Boolean advanceWeek(CourseOffering co, int numWeeks) {
		Boolean status = true;

		if (numWeeks > GlobalClass.maxWeek)
			status = false;
		else {
			// if (CourseOffering._week + numWeeks) <= GlobalClass.maxWeek
			// CourseOffering._week += numWeeks
		}
		return status;
	}

	public Boolean enrolCourse(Student s, CourseOffering co) {
		Boolean status = true;
		return status;
	}

	public Boolean dropCourse(Student s, CourseOffering co) {
		Boolean status = true;
		return status;
	}

	public void applyWaivers(Student s, CourseOffering co, String reason) {

	}

	public int getIndexOfEnrolment(Student s, CourseOffering co) {
		int result = -1;
		for (int i = 0; i < _enrolment.size(); i++) {
			if (_enrolment.get(i).getStudent().getID().equals(s.getID())
					& _enrolment.get(i).getCourseCode().equals(co.getCourseCode())
					& _enrolment.get(i).getSemester().equals(co.getSemester())) {
				result = i;
				break;
			}
		}
		return result;
	}

	public int getIndexOfLecturer(ArrayList<Lecturer> lecturer, Lecturer l) {
		int result = -1;
		for (int i = 0; i < lecturer.size(); i++) {
			if (lecturer.get(i).getID().equals(l.getID())) {
				result = i;
				break;
			}
		}
		return result;
	}

}
