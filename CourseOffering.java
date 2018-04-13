package SEF;

import java.util.ArrayList;
import java.util.HashMap;

public class CourseOffering {

	private String _courseCode;
	private String _semester;
	private int _week;
	private int _maxStudents;
	private ArrayList<Lecturer> _lecturer = new ArrayList<>();
	private HashMap _waivers = new HashMap();
	private Boolean _active;

	public CourseOffering() {

	}

	public CourseOffering(String courseCode, String semester) {
		_courseCode = courseCode;
		_semester = semester;
		_week = 0;
	}

	public Boolean courseOffered(CourseOffering co) {
		return ((_courseCode.equals(co.getCourseCode()) & _semester.equals(co.getSemester())) ? true : false);
	}

	public Boolean courseOffered(String courseCode, String semester) {
		return ((_courseCode.equals(courseCode) & _semester.equals(semester)) ? true : false);
	}

	public String getCourseCode() {
		return _courseCode;
	}

	public String getSemester() {
		return _semester;
	}

	public int getWeek() {
		return _week;
	}

	public void setWeek(int i) {
		_week = i;
	}

	public void addWaiver(Student s, String reason) {
		_waivers.put(s.getID(), reason);
	}

	public Boolean hasWaiver(Student s) {
		Boolean found = false;
		if (_waivers.containsKey(s.getID()))
			found = true;

		return found;
	}

	public void addLecturer(Lecturer l) {
		_lecturer.add(l);
	}

	public ArrayList getLecturer() {
		return _lecturer;
	}

}
