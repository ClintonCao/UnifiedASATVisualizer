package BlueTurtle.commandbuilders;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import BlueTurtle.settings.CheckStyleSettings;

/**
 * Test class for CheckStyleCommandBuilder.
 * 
 * @author BlueTurtle.
 *
 */
public class CheckStyleCommandBuilderTest {

	private CheckStyleSettings settings;
	private CheckStyleCommandBuilder ccb;

	/**
	 * Instantiate the objects for the tests.
	 */
	@Before
	public void setUp() {
		settings = CheckStyleSettings.getInstance();
		ccb = new CheckStyleCommandBuilder(settings);
	}


	/**
	 * Test that the build command returns the right string array.
	 */
	@Test
	public void testBuildCommand() {
		String[] expected = { "java", "-jar", System.getProperty("user.dir") + "/Runnables/checkstyle-6.18-all.jar",
				"-c", settings.getConfigFile(), "-f", "xml", System.getProperty("user.dir") + "/Runnables/Testcode/" };

		String[] actual = ccb.buildCommand();
		boolean result = Arrays.deepEquals(expected, actual);
		assertTrue(result);
	}

}
