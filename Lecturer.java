package SEF;

import java.util.ArrayList;

public class Lecturer extends User {

	private ArrayList<String> _courseOffering;

	public Lecturer(String userID, String name, String password, int role) {
		super(userID, name, password, role);
	}

	public ArrayList<String> getCourseOffering() {
		return _courseOffering;
	}

	public void setCourseOffering(ArrayList<String> courseOffer) {
		this._courseOffering = courseOffer;
	}
}
