package BlueTurtle.TSE;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import BlueTurtle.gui.GUIController.ASAT;

/**
 * Test for the JavaController class.
 * 
 * @author BlueTurtle.
 *
 */
public class JavaControllerTest {

	private String checkStyleOutputFilePath = "./src/test/resources/exampleCheckStyle1.xml";
	private String pmdOutputFilePath = "./src/test/resources/examplePmd1.xml";
	private String findBugsOutputFilePath = "./src/test/resources/exampleFindbugs1.xml";
	private String outputPath = "./src/test/resources/testOutput.js";

	/**
	 * Test that the user direction path is the same.
	 */
	@Test
	public void testUserDir() {
		String expected = System.getProperty("user.dir");
		String actual = JavaController.getUserDir();
		assertEquals(expected, actual);
	}

	/**
	 * Test that all files are null at first.
	 */
	@Test
	public void allFilesStringsAreNull() {
		assertTrue(JavaController.getCheckStyleOutputFile() == null && JavaController.getPmdOutputFile() == null
				&& JavaController.getFindBugsOutputFile() == null);
	}

	/**
	 * Test changing CheckStyle output file.
	 */
	@Test
	public void testChangingCheckStyleOutputFile() {
		String emptyFile = JavaController.getCheckStyleOutputFile();
		File newFile = new File("newFile");
		JavaController.setASATOutput(ASAT.CheckStyle, newFile);
		String checkStyleFile = JavaController.getCheckStyleOutputFile();
		assertNotEquals(emptyFile, checkStyleFile);
	}

	/**
	 * Test changing PMD output file.
	 */
	@Test
	public void testChangingPMDOutputFile() {
		String emptyFile = JavaController.getPmdOutputFile();
		File newFile = new File("newFile");
		JavaController.setASATOutput(ASAT.PMD, newFile);
		String pmdFile = JavaController.getPmdOutputFile();
		assertNotEquals(emptyFile, pmdFile);
	}

	/**
	 * Test changing FindBugs output file.
	 */
	@Test
	public void testChangingFindBugsOutputFile() {
		String emptyFile = JavaController.getFindBugsOutputFile();
		File newFile = new File("newFile");
		JavaController.setASATOutput(ASAT.FindBugs, newFile);
		String findBugsFile = JavaController.getFindBugsOutputFile();
		assertNotEquals(emptyFile, findBugsFile);
	}

	/**
	 * Test SetASATOutput with a null.
	 */
	@Test
	public void testSetOutputWithNull() {
		String currentFile = JavaController.getCheckStyleOutputFile();
		JavaController.setASATOutput(ASAT.CheckStyle, null);
		String newFile = JavaController.getCheckStyleOutputFile();
		assertEquals(currentFile, newFile);
	}

}
