package SEF;

import java.util.ArrayList;

public class Student extends User {

	private int _maxLoad;

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

	public int countEnrolment(ArrayList<Enrolment> e, String userID, String semester) {
		int count = 0;
		for (int i = 0; i < e.size(); i++) {
			if (e.get(i).getStudent().getID().equals(userID) & e.get(i).getSemester().equals(semester))
				count++;
		}
		return count;
	}

}
