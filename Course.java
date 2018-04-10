package SEF;

import java.util.ArrayList;
import java.util.HashMap;

public class Course {

	private String _semester; // e.g. 1810, 1820
	private String _courseCode; // e.g. SYS1234
	private String _cTitle;
	private String _cDesc;
	private int _week;

	private ArrayList<Enrolment> _enrolledStudents = new ArrayList<>();;
	private ProgramCoordinator _programCoordinator;
	private ArrayList<Lecturer> _lecturer = new ArrayList<>();
	private int _totalStudents;
	private Boolean _active;
	private ArrayList<String> _preRequisite = new ArrayList<>();
	private HashMap<String, String> _waivers = new HashMap<>();

	public Course() {
	}

	public Course(String courseCode, String semester, String title) {
		_courseCode = courseCode;
		_semester = semester;
		_cTitle = title;
		_week = 0;
	}

	public Course(String courseCode, String semester, String title, String desc) {
		_courseCode = courseCode;
		_semester = semester;
		_cTitle = title;
		_cDesc = desc;
		_week = 0;
	}

	public Boolean checkCourseExists(String courseCode, String semester) {
		return ((_courseCode.equals(courseCode) & _semester.equals(semester)) ? true : false);
	}

	public String getCourseCode() {
		return _courseCode;
	}

	public String getSemester() {
		return _semester;
	}

	public String getCourseTitle() {
		return _cTitle;
	}

	public String getCourseTitle(String courseCode, String semester) {
		return ((_courseCode.equals(courseCode) & _semester.equals(semester)) ? _cTitle : "");
	}

	public String getCourseDesc(String courseCode, String semester) {
		return ((_courseCode.equals(courseCode) & _semester.equals(semester)) ? _cDesc : "");
	}

}
