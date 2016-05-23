package BlueTurtle.TSE;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

/**
 * Test for the JavaController class.
 * 
 * @author BlueTurtle.
 *
 */
public class JavaControllerTest {

	/**
	 * Test that the user direction path is the same.
	 */
	@Test
	public void testUserDir() {
		String expected = System.getProperty("user.dir");
		String actual = JavaController.getUserDir();
		assertEquals(expected, actual);
	}

	/**
	 * Test that the analyser is null before execute is called.
	 */
	@Test
	public void testAnalyserIsNullBeforeExecute() {
		JavaController jc = new JavaController();
	}


}
