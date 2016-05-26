package BlueTurtle.TSE;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

/**
 * Test for the AnalyserCommand class.
 * 
 * @author BlueTurtle.
 *
 */
public class AnalyserCommandTest {

	private AnalyserCommand ac;

	/**
	 * Instantiate object that is needed for the test.
	 */
	@Before
	public void setUp() {
		ac = new AnalyserCommand("Test", new String[] { "Args" });
	}

	/**
	 * Test changing the default output path.
	 */
	@Test
	public void testChangingDefaultOutputPath() {
		String oldPath = ac.getDefaultOutputFilePath();
		ac.setDefaultOutputFilePath("New Path");
		String newPath = ac.getDefaultOutputFilePath();
		assertNotEquals(oldPath, newPath);
	}

	/**
	 * Test changing the arguments.
	 */
	@Test
	public void testChangingArguments() {
		String[] oldArgs = ac.getArgs();
		ac.setArgs(new String[] { "NewArgs" });
		String[] newArgs = ac.getArgs();
		boolean result = Arrays.deepEquals(oldArgs, newArgs);
		assertFalse(result);
	}

}
