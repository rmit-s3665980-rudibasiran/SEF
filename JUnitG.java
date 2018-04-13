package SEF;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

class JUnitG {

	@Test
	void test() {
		fail("Not yet implemented");
	}

	// test template
	// do not delete as eclipse will remove the import headers automatically if the
	// other junit tests do not make use of them
	@Test
	public void testTemplate() {
		try {
			Driver driver = new Driver();
			driver.loadData();

			// test something
			assertTrue(true);
			assertFalse(false);
			assertEquals(1, 1);
			assertNotSame(2, 1);

		} catch (Exception e) {
			RMITExceptions.handleExceptions(e);
		} finally {
		}
	};

}
