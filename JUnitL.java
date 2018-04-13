package SEF;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

public class JUnitL {

	public JUnitL() {

	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Access Course class");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Leave Course class");
	}

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Leave test method");
	}

	@Test
	public void advanceWeekByOne() {

		Driver driver = new Driver();
		driver.loadData();
		String courseCode = "COSC2531";
		String semester = "1810";
		Course c = driver._courses.get(courseCode);
		CourseOffering co = new CourseOffering(c, semester);
		int advanceCorrectInt = 1;

		assertTrue(driver.advanceWeek(co, advanceCorrectInt));
	}

	@Test
	public void advanceWeekByMany() {
		Driver driver = new Driver();
		driver.loadData();

		String courseCode = "COSC2531";
		String semester = "1810";

		Course c = driver._courses.get(courseCode);
		CourseOffering co = new CourseOffering(c, semester);
		int advanceIncorrectInt = 13;
		assertFalse(driver.advanceWeek(co, advanceIncorrectInt));
	}
}
