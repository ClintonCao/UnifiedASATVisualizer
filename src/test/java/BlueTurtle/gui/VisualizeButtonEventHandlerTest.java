package BlueTurtle.gui;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import BlueTurtle.TSE.JavaController;

/**
 * Test class for VisualizeButtonEventHandlerTest.
 * 
 * @author BlueTurtle.
 *
 */
public class VisualizeButtonEventHandlerTest {

	/**
	 * Test setOutputFiles of VisualizeButtonEventHandler class.
	 */
	@Test
	public void testSetOutputFiles() {
		GUIController.setProjectPath(System.getProperty("user.dir"));
		VisualizeButtonEventHandler eventHandler = new VisualizeButtonEventHandler();
		eventHandler.setOutputFiles();
		String sourcePath = GUIController.getProjectPath();
		assertTrue(JavaController.getCheckStyleOutputFile()
				.equals(sourcePath + File.separator + "target" + File.separator + "checkstyle-result.xml")
				&& JavaController.getPmdOutputFile()
						.equals(sourcePath + File.separator + "target" + File.separator + "pmd.xml")
				&& JavaController.getFindBugsOutputFile()
						.equals(sourcePath + File.separator + "target" + File.separator + "findbugs.xml"));
	}

}
