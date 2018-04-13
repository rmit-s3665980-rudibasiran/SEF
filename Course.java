package SEF;

import java.util.ArrayList;

public class Course {

	// unique key(s)
	private String _courseCode;

	// about
	private String _cTitle;
	private String _cDesc;
	private ProgramCoordinator _programCoordinator;
	private Boolean _active;
	private ArrayList<String> _preRequisite = new ArrayList<>();
	private ArrayList<CourseOffering> _courseOffering = new ArrayList<>();

	public Course() {
	}

	public Course(String courseCode, String title) {
		_courseCode = courseCode;
		_cTitle = title;
	}

	public Course(String courseCode, String title, String desc) {
		_courseCode = courseCode;
		_cTitle = title;
		_cDesc = desc;
	}

	public Boolean checkCourseExists(String courseCode) {
		return (_courseCode.equals(courseCode) ? true : false);
	}

	public String getCourseCode() {
		return _courseCode;
	}

	public String getCourseTitle() {
		return _cTitle;
	}

	public String getCourseTitle(String courseCode) {
		return _cTitle;
	}

	public String getCourseDesc(String courseCode, String semester) {
		return _cDesc;
	}

}
