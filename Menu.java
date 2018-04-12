package SEF;

import java.util.Arrays;

public class Menu {

	private int _choice;
	private Boolean _quit;

	private String[] _menuItems = new String[GlobalClass.menuSize];
	private int[] _menuControl = new int[GlobalClass.menuSize];

	public Menu() {
		_choice = 1;
		_quit = false;
		_menuItems[GlobalClass.quitMenu] = "Quit";
		_menuItems[GlobalClass.viewCourse] = "View Course";
		_menuItems[GlobalClass.addCourse] = "Add Course";
		_menuItems[GlobalClass.viewPerformance] = "View Performance";
		_menuItems[GlobalClass.addWaiver] = "Add Exemption";
		_menuItems[GlobalClass.changeLoad] = "Change Load";
		_menuItems[GlobalClass.assignGrade] = "Assign Grade";
		_menuItems[GlobalClass.addOffering] = "Add Offering";
		_menuItems[GlobalClass.addLecturer] = "Add Lecturer";
		_menuItems[GlobalClass.advanceWeek] = "Advance Week";
		_menuItems[GlobalClass.enrolCourse] = "Enrol Course";
		_menuItems[GlobalClass.dropCourse] = "Drop Course";
		_menuItems[GlobalClass.applyWaivers] = "Apply Waiver";

		for (int i = 0; i < _menuItems.length; i++)
			_menuControl[i] = i;
	}

	public int getOption() {
		return _choice;
	}

	public Boolean displayMenu(User currentUser) {

		Helper.drawLine();

		Boolean validChoice = true;
		System.out.println("Course Management System");

		Helper.drawLine();

		int roles[] = GlobalClass.allRoles[currentUser.getRole()].clone();

		for (int i = 1; i < _menuItems.length; i++) {
			if (Arrays.binarySearch(roles, i) >= 0)
				System.out.println((i) + ": " + _menuItems[i]);
			else
				System.out.println((i) + ": " + _menuItems[i] + " [Not Available]");

		}

		System.out.println((0) + ": " + _menuItems[0]);

		_choice = Helper.getIntegerInput("Enter Option: ");

		if (_choice == GlobalClass.quitMenu)
			_quit = true;
		else if (_choice < GlobalClass.quitMenu || _choice > _menuItems.length)
			validChoice = false;
		else {
			if (Arrays.binarySearch(roles, _choice) < 0)
				validChoice = false;
		}
		if (!validChoice)
			System.out.println("Invalid Option");

		Helper.drawLine();
		return validChoice;

	}

	public Boolean exitMenu() {
		return _quit;
	}

}
