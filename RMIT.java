package SEF;

import java.io.FileNotFoundException;

/*
Title: SEF Assignment 
Developer(s): 
1. Tiaotiao Li: s3587842
2. Gustav Johansson: s3706098
3. Huani Neupane: s3685849
4. Rudi Basiran: s3665980
5. Ahdeiah Gurgani: s3705295


Date Created: 3 April 2018  
Description: RMIT CMS
Notes: Main class, initially created as CMS.java, renamed RMIT.java on 12 Apr 2018
 */

public class RMIT {

	public static void main(String[] args) throws FileNotFoundException {

		Menu menu = new Menu();
		Driver driver = new Driver();
		driver.loadData();

		System.out.println("Welcome to the RMIT Course Management System");
		System.out.println("Enter your username and password to login to your account.");

		String userID = Helper.getStringInput("Enter User ID: ");
		String password = Helper.getStringInput("Password (case sensitive): ");

		User currentUser = new User(userID, password);

		if (currentUser.isAuthenticated(driver)) {
			System.out.println("Welcome " + currentUser.getName() + "! [Role: "
					+ GlobalClass.roleDesc[currentUser.getRole()] + "]");
			while (!menu.exitMenu()) {
				if (menu.displayMenu(currentUser))
					driver.menuAction(menu.getOption(), currentUser);
				else
					Helper.getAnyKey("Press <Enter> to continue ");
			}
		} else
			System.out.println(
					"The UserID and password you entered are incorrect. Please contact the CMS Administrator.");
	}

}
