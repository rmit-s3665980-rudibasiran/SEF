package SEF;

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

}
