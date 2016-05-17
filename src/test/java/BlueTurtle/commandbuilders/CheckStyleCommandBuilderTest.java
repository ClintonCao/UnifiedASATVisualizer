package BlueTurtle.commandbuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
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
	 * Test that the command attribute is not used. We should probably remove
	 * this attribute.
	 */
	@Test
	public void testListOfCommandsIsNotUsed() {
		assertSame(0, ccb.getCommands().size());
	}
	
	/**
	 * Test that the command attribute is not used. We should probably remove
	 * this attribute.
	 */
	@Test
	public void testSettingListOfCommand() {
		ArrayList<String> newList = new ArrayList<String>();
		newList.add("Test");
		ccb.setCommands(newList);
		assertEquals("Test", ccb.getCommands().get(0));
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
