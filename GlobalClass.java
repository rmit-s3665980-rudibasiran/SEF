package SEF;

/*
Title: SEF Assignment 
Developer(s): 
Tiaotiao Li: s3587842
Gustav Johansson: s3706098
Huani Neupane: s3685849
Ahdeiah Gurgani: s3705295
Rudi Basiran: s3665980

Date Created: 3 April 2018 
Description: GlobalClass
Notes: Global variables and re-usable functions so that they need not be declared/created anew each time
 */

public class GlobalClass {

	public static int menuSize = 13;
	public static int quitMenu = 0;
	public static int viewCourse = 1;
	public static int addCourse = 2;
	public static int viewPerformance = 3;
	public static int addExemption = 4;
	public static int changeLoad = 5;
	public static int assignGrade = 6;
	public static int addOffering = 7;
	public static int addLecturer = 8;
	public static int advanceWeek = 9;
	public static int enrolCourse = 10;
	public static int dropCourse = 11;
	public static int applyWaivers = 12;

	public static int Admin = 0;
	public static int ProgramCoordinator = 1;
	public static int Lecturer = 2;
	public static int Student = 3;

	public static String roleDesc[] = { "Admin", "ProgramCoordinator", "Lecturer", "Student" };

	public static int allRoles[][] = new int[][] { { quitMenu, viewCourse, viewPerformance, advanceWeek },
			{ quitMenu, viewCourse, addCourse, viewPerformance, addExemption, changeLoad, addOffering, addLecturer },
			{ quitMenu, viewCourse, addCourse, viewPerformance, addExemption, assignGrade, addOffering },
			{ quitMenu, viewCourse, enrolCourse, dropCourse, applyWaivers } };

	public static String waiverGrade = "WAIVED";
}