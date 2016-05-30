package BlueTurtle.gui;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.After;
import org.junit.Test;

import BlueTurtle.TSE.JavaController;
import BlueTurtle.gui.GUIController.ASAT;

/**
 * Test class for VisualizeButtonEventHandlerTest.
 * 
 * @author BlueTurtle.
 *
 */
public class VisualizeButtonEventHandlerTest {

	
	/**
	 * clean up the attributes of the JavaController.
	 */
	@After
	public void cleaUp() {
		JavaController.setASATOutput(ASAT.CheckStyle, null);
		JavaController.setASATOutput(ASAT.PMD, null);
		JavaController.setASATOutput(ASAT.FindBugs, null);
	}
	
	/**
	 * Test setOutputFiles of VisualizeButtonEventHandler class.
	 */
	@Test
	public void testSetOutputFiles() {
		GUIController.setSourcePath(System.getProperty("user.dir"));
		VisualizeButtonEventHandler eventHandler = new VisualizeButtonEventHandler();
		eventHandler.setOutputFiles();
		String sourcePath = GUIController.getSourcePath();
		assertTrue(JavaController.getCheckStyleOutputFile()
				.equals(sourcePath + File.separator + "target" + File.separator + "checkstyle-result.xml")
				&& JavaController.getPmdOutputFile()
						.equals(sourcePath + File.separator + "target" + File.separator + "pmd.xml")
				&& JavaController.getFindBugsOutputFile()
						.equals(sourcePath + File.separator + "target" + File.separator + "findbugs.xml"));
	}

}
