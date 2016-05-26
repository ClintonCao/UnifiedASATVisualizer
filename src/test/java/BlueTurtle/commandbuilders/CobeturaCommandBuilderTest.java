package BlueTurtle.commandbuilders;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import BlueTurtle.settings.CoberturaSettings;

/**
 * Test for the CoberturaCommandBuilder class.
 * 
 * @author BlueTurtle.
 *
 */
public class CobeturaCommandBuilderTest {

	private CoberturaSettings settings;
	private CoberturaCommandBuilder cobercb;
	
	/**
	 * Instantiate the objects for the tests.
	 */
	@Before
	public void setUp() {
		settings = new CoberturaSettings();
		cobercb = new CoberturaCommandBuilder(settings);
	}

	/**
	 * Test that the build command returns the right string array.
	 */
	@Test
	public void testBuildCommand() {
		String[] expected = { System.getProperty("user.dir") + "/Runnables/cobertura-2.1.1/cobertura-report.bat",
				"--format", "xml", "--destination", System.getProperty("user.dir") + "/Runnables/Testcode/cobertura",
				System.getProperty("user.dir") + "/Runnables/Testcode/" };

		String[] actual = cobercb.buildCommand();
		boolean result = Arrays.deepEquals(expected, actual);
		assertTrue(result);
	}

}
