package SEF;

import static org.junit.Assert.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

class JUnitA {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Access User class");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Leave User class");
	}

	@Before
	public void setUp() throws Exception {
		User user = new User("User", "1234");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Leave test method");
	}

	@Test
	public void incorrectUserName() {
		User user = new User("User", "1234");
		assertNotSame("Ahdeiah Gurgani", user.getName());
	}

	@Test
	public void correctUserName() {
		User user = new User("User", "1234");
		assertEquals("User", user.getID());
	}

	@Test
	public void correctUserPassword() {
		User user = new User("User", "1234");
		assertEquals("1234", user.getPassword());
	}

	@Test
	public void incorrectUserPassword() {
		User user = new User("User", "1234");
		assertNotSame("5678", user.getPassword());
	}

	@Test
	public void testAdminPassword() {
		Admin admin = new Admin("Admin", "Ahdeiah Gurgani", "1234", GlobalClass.Admin);
		admin.resetPassword("12345");
		assertEquals("12345", admin.getPassword());
	}

	@Test
	public void testAdminUserName() {
		Admin admin = new Admin("Admin", "Ahdeiah Gurgani", "1234", GlobalClass.Admin);
		assertEquals("Ahdeiah Gurgani", admin.getName());
	}

	@Test
	public void testAdminUserID() {
		Admin admin = new Admin("Admin", "Ahdeiah Gurgani", "1234", GlobalClass.Admin);
		assertEquals("Admin", admin.getID());
	}

	@Test
	public void testAdminRole() {
		Admin admin = new Admin("Admin", "Ahdeiah Gurgani", "1234", GlobalClass.Admin);
		assertEquals(GlobalClass.Admin, admin.getRole());
	}

}
