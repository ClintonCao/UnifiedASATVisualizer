package BlueTurtle.settings;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

/**
 * Test for the CoberturaSettings class.
 * 
 * @author BlueTurtle.
 *
 */
public class CoberturaSettingsTest {

	private CoberturaSettings settings = new CoberturaSettings();
	
	/**
	 * Test changing the output path.
	 */
	@Test
	public void testChangingOutputPath() {
		String oldPath = settings.getDefaultOutputFilePath();
		settings.setDefaultOutputFilePath("New Path");
		String newPath = settings.getDefaultOutputFilePath();
		assertNotEquals(newPath, oldPath);
	}

}
