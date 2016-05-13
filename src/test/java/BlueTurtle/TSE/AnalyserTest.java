package BlueTurtle.TSE;

import static org.junit.Assert.assertTrue;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

import BlueTurtle.commandbuilders.CheckStyleCommandBuilder;
import BlueTurtle.commandbuilders.CommandBuilder;
import BlueTurtle.commandbuilders.FindBugsCommandBuilder;
import BlueTurtle.commandbuilders.PMDCommandBuilder;
import BlueTurtle.settings.CheckStyleSettings;
import BlueTurtle.settings.FindBugsSettings;
import BlueTurtle.settings.PMDSettings;

/**
 * Unit test for simple Analyser.
 */
public class AnalyserTest {

	/**
	 * Set up a command to run PMD and run CheckStyle. These commands are handed
	 * to the analyser which runs them.
	 * 
	 * @throws IOException
	 *             throws an exception if problem is encounter while building
	 *             and analysing the commands.
	 */
	@Before
	public void setUp() throws IOException {
		JavaController javaController = new JavaController();
		ArrayList<AnalyserCommand> commands = new ArrayList<AnalyserCommand>();
		CommandBuilder commandBuilder;
		PMDSettings pmdSettings = javaController.getPMDSettings();
		CheckStyleSettings checkStyleSettings = javaController.getCheckStyleSettings();

		FindBugsSettings findBugsSettings = new FindBugsSettings();

		commandBuilder = new PMDCommandBuilder(pmdSettings);
		String[] pmdCommands = commandBuilder.buildCommand();
		AnalyserCommand c1 = new AnalyserCommand(pmdSettings.getDefaultOutputFilePath(), pmdCommands);
		commands.add(c1);

		commandBuilder = new CheckStyleCommandBuilder(checkStyleSettings);
		String[] checkStyleCommands = commandBuilder.buildCommand();
		AnalyserCommand c2 = new AnalyserCommand(checkStyleSettings.getDefaultOutputFilePath(), checkStyleCommands);
		commands.add(c2);

		Analyser analyser = new Analyser(commands);

		analyser.analyse();
	}

	/**
	 * Simple test to check if running the analyser actually produces output for
	 * checkstyle.
	 * 
	 * @throws IOException
	 *             throws an exception if problem is encountered while reading
	 *             the file.
	 */
	@Test
	public void testCheckStyleOutput() throws IOException {
		File file = new File(JavaController.getUserDir() + "/Runnables/Testcode/checkstyle.xml");
		assertTrue(file.length() > 0);
	}

	/**
	 * Simple test to check if running the analyser actually produces output for
	 * PMD.
	 * 
	 * @throws IOException
	 *             throws an exception if problem is encountered while reading
	 *             the file.
	 */
	@Test
	public void testPMDOutput() throws IOException {
		File file = new File(JavaController.getUserDir() + "/Runnables/Testcode/PMD.xml");
		assertTrue(file.length() > 0);
	}
}
