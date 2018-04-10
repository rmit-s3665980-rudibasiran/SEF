package SEF;

public class User {

	private int _role;
	private String _userID;
	private String _password;
	private String _name;

	public User() {

	}

	public User(String userID, String password) {
		_userID = userID;
		_password = password;
	}

	public User(String userID, String name, String password) {
		_userID = userID;
		_name = name;
		_password = password;
	}

	public User(String userID, String name, String password, int role) {
		_userID = userID;
		_name = name;
		_password = password;
		_role = role;
	}

	public String getName() {
		return _name;
	}

	public String getID() {
		return _userID;
	}

	public String getPassword() {
		return _password;
	}

	public int getRole() {
		return _role;
	}

	public void resetPassword(String newPassword) {
		_password = newPassword;
	}

	public boolean isAuthenticated(Driver d) {
		boolean found = false;
		for (int i = 0; i < d._users.size(); i++) {
			if (d._users.get(i).getID().toUpperCase().equals(_userID.toUpperCase())
					& d._users.get(i).getPassword().equals(_password)) {
				found = true;
				_role = d._users.get(i).getRole();
				_name = (d._users.get(i).getName());
			}
		}
		return found;
	}
}
