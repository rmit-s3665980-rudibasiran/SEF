package SEF;

import java.util.ArrayList;

public class Lecturer extends User {

	private ArrayList<String> _myCourses;

	public Lecturer(String userID, String name, String password, int role) {
		super(userID, name, password, role);
	}

}
