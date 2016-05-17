package BlueTurtle.settings;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

/**
 * Test for the CheckStyleSettings class.
 * 
 * @author BlueTurtle.
 *
 */
public class CheckStyleSettingsTest {

	private CheckStyleSettings settings;
	
	/**
	 * Instantiate the settings object.
	 */
	@Before
	public void setUp() {
		settings = CheckStyleSettings.getInstance();
	}
	
	/**
	 * Test that getInstance does not return null.
	 */
	@Test
	public void testGetInstanceDoesNotReturnNull() {
		assertNotNull(settings);
	}

	/**
	 * Test that string of config file is not empty.
	 */
	@Test
	public void testConfigFileIsNotEmpty() {
		String configfile = settings.getConfigFile();
		assertFalse(configfile.isEmpty());
	}

	/**
	 * Test that the source file are the same i.e. to check that they point to
	 * the same file.
	 * 
	 * @throws IOException
	 *             throws an exception if path to file cannot be found.
	 */
	@Test
	public void testSourceFileIsTheSame() throws IOException {
		File a = settings.getSourceFile();
		File e = Paths.get("resources", "asatSettings", "CheckStyle_Settings.xml").toFile();
		String actual = a.getCanonicalPath();
		String expected = e.getCanonicalPath();
		assertEquals(expected, actual);
	}
	
	/**
	 * Test that the default output path is the same.
	 */
	@Test
	public void testDefaultOutputPathIsTheSame() {
		String actual = settings.getDefaultOutputFilePath();
		String expected = Paths.get("Runnables", "Testcode", "checkstyle.xml").toString();
		assertEquals(expected, actual);
	}

}
