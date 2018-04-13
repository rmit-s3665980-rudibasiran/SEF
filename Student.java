package SEF;

import java.util.Map.Entry;
import java.util.TreeMap;

public class Student extends User {

	private int _maxLoad;
	private int _maxElectives;

	public Student() {
		super();
	}

	public Student(String userID, String name, String password, int role) {
		super(userID, name, password, role);
		_maxLoad = 1;
	}

	public void setMaxLoad(int i) {
		_maxLoad = i;

	}

	public int getMaxLoad() {
		return _maxLoad;
	}

	public int countEnrolment(TreeMap<String, Enrolment> e, Student s, String semester) {
		int count = 0;

		for (Entry<String, Enrolment> entry : e.entrySet()) {
			Enrolment ei = entry.getValue();
			if (ei.getStudent().getID().equals(s.getID()) & ei.getSemester().equals(semester))
				count++;
		}

		return count;
	}

}
