package BlueTurtle.commandbuilders;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import BlueTurtle.settings.PMDSettings;

/**
 * Test for the PMDCommandBuilder class.
 * 
 * @author BlueTurtle.
 *
 */
public class PMDCommandBuilderTest {

	private PMDSettings settings;
	private PMDCommandBuilder pcb;
	
	/**
	 * Instantiate the objects for the tests.
	 */
	@Before
	public void setUp() {
		settings = PMDSettings.getInstance();
		pcb = new PMDCommandBuilder(settings);
	}

	/**
	 * Test that the build command returns the right string array.
	 */
	@Test
	public void testBuildCommand() {
		String[] expected = { "java", "-jar",
				System.getProperty("user.dir") + "/Runnables/pmd-bin-4.2.6/lib/pmd-4.2.6.jar",
				System.getProperty("user.dir") + "/Runnables/Testcode/", "xml", "basic" };

		String[] actual = pcb.buildCommand();
		boolean result = Arrays.deepEquals(expected, actual);
		assertTrue(result);
	}

}
