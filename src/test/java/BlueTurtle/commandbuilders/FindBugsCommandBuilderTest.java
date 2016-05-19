package BlueTurtle.commandbuilders;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import BlueTurtle.settings.FindBugsSettings;

/**
 * Test for the FindBugsCommandBuilder class.
 * 
 * @author BlueTurtle.
 *
 */
public class FindBugsCommandBuilderTest {

	private FindBugsSettings settings;
	private FindBugsCommandBuilder fcb;
	
	/**
	 * Instantiate the objects for the tests.
	 */
	@Before
	public void setUp() {
		settings = FindBugsSettings.getInstance();
		fcb = new FindBugsCommandBuilder(settings);
	}

	/**
	 * Test that the build command returns the right string array.
	 */
	@Test
	public void testBuildCommand() {
		String[] expected = { System.getProperty("user.dir") + "/Runnables/findbugs-3.0.1/bin/findbugs.bat", "-textui",
				"-maxHeap", "256", "-nested:false", "-output",
				System.getProperty("user.dir") + "/Runnables/Testcode/findbugs.xml", "-effort:min", "-low",
				"-sortByClass", "-xml:withMessages",
				System.getProperty("user.dir") + "/Runnables/context_findbugs.jar" };
		
		String[] actual = fcb.buildCommand();
		boolean result = Arrays.deepEquals(expected, actual);
		assertTrue(result);
	}

}
