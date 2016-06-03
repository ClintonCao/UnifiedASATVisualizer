package BlueTurtle.TSE;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import BlueTurtle.finders.ProjectInfoFinder;
import BlueTurtle.gui.GUIController.ASAT;

/**
 * Test for the JavaController class.
 * 
 * @author BlueTurtle.
 *
 */
public class JavaControllerTest {

	private String checkStyleOutputFilePath = System.getProperty("user.dir")
			+ "/src/test/resources/exampleCheckstyle1.xml";
	private String pmdOutputFilePath = System.getProperty("user.dir") + "/src/test/resources/examplePmd1.xml";
	private String findBugsOutputFilePath = System.getProperty("user.dir") + "/src/test/resources/exampleFindbugs1.xml";

//	/**
//	 * Clear the attributes of JavaController.
//	 * 
//	 * @throws IOException
//	 *             throws an exception if there was a problem encounterd while
//	 *             reading the files.
//	 */
//	@Before
//	public void setUp() throws IOException {
//		new ProjectInfoFinder().findFiles(new File(System.getProperty("user.dir")));
//		JavaController.setCheckStyleOutputFile(null);
//		JavaController.setPmdOutputFile(null);
//		JavaController.setFindBugsOutputFile(null);
//	}
//	
//	/**
//	 * Delete the created files.
//	 */
//	@After
//	public void cleanUp() {
//		File f = new File(System.getProperty("user.dir") + "/src/main/resources/SummarizedOuput.js");
//		if (f.exists()) {
//			f.delete();
//		}
//		ProjectInfoFinder.getClassLocs().clear();
//		ProjectInfoFinder.getPackages().clear();
//		ProjectInfoFinder.getClassPaths().clear();
//		ProjectInfoFinder.getClassPackage().clear();
//	}
//
//	/**
//	 * Test that the user direction path is the same.
//	 */
//	@Test
//	public void testUserDir() {
//		String expected = System.getProperty("user.dir");
//		String actual = JavaController.getUserDir();
//		assertEquals(expected, actual);
//	}
//
//	/**
//	 * Test that all files are null at first.
//	 */
//	@Test
//	public void allFilesStringsAreNull() {
//		assertTrue(JavaController.getCheckStyleOutputFile() == null && JavaController.getPmdOutputFile() == null
//				&& JavaController.getFindBugsOutputFile() == null);
//	}
//
//	/**
//	 * Test changing CheckStyle output file.
//	 */
//	@Test
//	public void testChangingCheckStyleOutputFile() {
//		String emptyFile = JavaController.getCheckStyleOutputFile();
//		File newFile = new File("newFile");
//		JavaController.setASATOutput(ASAT.CheckStyle, newFile);
//		String checkStyleFile = JavaController.getCheckStyleOutputFile();
//		assertNotEquals(emptyFile, checkStyleFile);
//	}
//
//	/**
//	 * Test changing PMD output file.
//	 */
//	@Test
//	public void testChangingPMDOutputFile() {
//		String emptyFile = JavaController.getPmdOutputFile();
//		File newFile = new File("newFile");
//		JavaController.setASATOutput(ASAT.PMD, newFile);
//		String pmdFile = JavaController.getPmdOutputFile();
//		assertNotEquals(emptyFile, pmdFile);
//	}
//
//	/**
//	 * Test changing FindBugs output file.
//	 */
//	@Test
//	public void testChangingFindBugsOutputFile() {
//		String emptyFile = JavaController.getFindBugsOutputFile();
//		File newFile = new File("newFile");
//		JavaController.setASATOutput(ASAT.FindBugs, newFile);
//		String findBugsFile = JavaController.getFindBugsOutputFile();
//		assertNotEquals(emptyFile, findBugsFile);
//	}
//
//	/**
//	 * Test SetASATOutput with a null.
//	 */
//	@Test
//	public void testSetOutputWithNull() {
//		String currentFile = JavaController.getCheckStyleOutputFile();
//		JavaController.setASATOutput(ASAT.CheckStyle, null);
//		String newFile = JavaController.getCheckStyleOutputFile();
//		assertEquals(currentFile, newFile);
//	}
//
//	/**
//	 * Test execute should output file.
//	 * 
//	 * @throws IOException
//	 *             throws an exception if a problem is encountered while reading
//	 *             the file.
//	 */
//	@Test
//	public void testExecute() throws IOException {
//		JavaController.setASATOutput(ASAT.CheckStyle, new File(checkStyleOutputFilePath));
//		JavaController.setASATOutput(ASAT.PMD, new File(pmdOutputFilePath));
//		JavaController.setASATOutput(ASAT.FindBugs, new File(findBugsOutputFilePath));
//		JavaController jc = new JavaController();
//		jc.execute();
//		assertTrue(new File(System.getProperty("user.dir") + "/src/main/resources/SummarizedOuput.js").exists());
//	}

}
