package BlueTurtle.uav;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import BlueTurtle.finders.ProjectInfoFinder;
import BlueTurtle.gui.GUIController;
import BlueTurtle.gui.GUIController.ASAT;

/**
 * Test for the JavaController class.
 * 
 * @author BlueTurtle.
 *
 */
public class JavaControllerTest {

	private String userDir = System.getProperty("user.dir");
	private ArrayList<String> checkstyleList = new ArrayList<String>();
	private ArrayList<String> pmdList = new ArrayList<String>();
	private ArrayList<String> findBugsList = new ArrayList<String>();
	private String checkStyleOutputFilePath = userDir + "/src/test/resources/exampleCheckstyle1.xml";
	private String pmdOutputFilePath = userDir + "/src/test/resources/examplePmd1.xml";
	private String findBugsOutputFilePath = userDir + "/src/test/resources/exampleFindbugs1.xml";

	/**
	 * Clear the attributes of JavaController.
	 * 
	 * @throws IOException
	 *             throws an exception if there was a problem encounterd while
	 *             reading the files.
	 */
	@Before
	public void setUp() throws IOException {
		GUIController.setProjectPath("test");
		checkstyleList.add(checkStyleOutputFilePath);
		pmdList.add(pmdOutputFilePath);
		findBugsList.add(findBugsOutputFilePath);
		new ProjectInfoFinder().findFiles(new File(userDir));
		JavaController.setCheckStyleOutputFiles(null);
		JavaController.setPmdOutputFiles(null);
		JavaController.setFindBugsOutputFiles(null);
	}

	/**
	 * Delete the created files.
	 */
	@After
	public void cleanUp() {
		File f = new File(userDir + "/src/main/resources/SummarizedOuput.js");
		GUIController.setProjectPath(null);
		if (f.exists()) {
			f.delete();
		}
		ProjectInfoFinder.getClassLocs().clear();
		ProjectInfoFinder.getPackages().clear();
		ProjectInfoFinder.getClassPaths().clear();
		ProjectInfoFinder.getClassPackage().clear();
	}

	/**
	 * Test that the user direction path is the same.
	 */
	@Test
	public void testUserDir() {
		String expected = userDir;
		String actual = JavaController.getUserDir();
		assertEquals(expected, actual);
	}

	/**
	 * Test that all files are null at first.
	 */
	@Test
	public void allFilesStringsAreNull() {
		assertTrue(JavaController.getCheckStyleOutputFiles() == null && JavaController.getPmdOutputFiles() == null
				&& JavaController.getFindBugsOutputFiles() == null);
	}

	/**
	 * Test changing CheckStyle list of output file.
	 */
	@Test
	public void testChangingCheckStyleOutputFile() {
		ArrayList<String> list = JavaController.getCheckStyleOutputFiles();
		ArrayList<String> newList = new ArrayList<String>();
		newList.add("test path for checkstyle");
		JavaController.setASATOutputFiles(ASAT.CheckStyle, newList);
		assertNotEquals(list, JavaController.getCheckStyleOutputFiles());
	}

	/**
	 * Test changing PMD list of output file.
	 */
	@Test
	public void testChangingPMDOutputFile() {
		ArrayList<String> list = JavaController.getPmdOutputFiles();
		ArrayList<String> newList = new ArrayList<String>();
		newList.add("test path for pmd");
		JavaController.setASATOutputFiles(ASAT.PMD, newList);
		assertNotEquals(list, JavaController.getPmdOutputFiles());
	}

	/**
	 * Test changing FindBugs list of output files.
	 */
	@Test
	public void testChangingFindBugsOutputFile() {
		ArrayList<String> list = JavaController.getFindBugsOutputFiles();
		ArrayList<String> newList = new ArrayList<String>();
		newList.add("test path for findbugs");
		JavaController.setASATOutputFiles(ASAT.FindBugs, newList);
		assertNotEquals(list, JavaController.getFindBugsOutputFiles());
	}

	/**
	 * Test SetASATOutputFiles with a null.
	 */
	@Test
	public void testSetOutputWithNull() {
		ArrayList<String> fileList = JavaController.getCheckStyleOutputFiles();
		JavaController.setASATOutputFiles(ASAT.CheckStyle, null);
		assertEquals(fileList, JavaController.getCheckStyleOutputFiles());
	}

	/**
	 * Test execute should produce an output file.
	 * 
	 * @throws IOException
	 *             throws an exception if a problem is encountered while reading
	 *             the file.
	 */
	@Test
	public void testExecute() throws IOException {
		JavaController.setASATOutputFiles(ASAT.CheckStyle, new ArrayList<String>());
		JavaController.setASATOutputFiles(ASAT.PMD, new ArrayList<String>());
		JavaController.setASATOutputFiles(ASAT.FindBugs, new ArrayList<String>());
		JavaController jc = new JavaController();
		jc.execute();
		assertTrue(new File(userDir + "/visualization/JSON/outputWarningsJSON.js").exists());
	}

	/**
	 * Tests if the JSONFormatter generates an empty list of warnings when it
	 * finds no output files.
	 * 
	 * @throws IOException
	 *             throws an exception if a problem is encountered while reading
	 *             the file.
	 */
	@Test
	public void testNoOutputFile() throws IOException {
		JavaController.setASATOutputFiles(ASAT.CheckStyle, new ArrayList<String>());
		JavaController.setASATOutputFiles(ASAT.PMD, new ArrayList<String>());
		JavaController.setASATOutputFiles(ASAT.FindBugs, new ArrayList<String>());
		JSONFormatter jsonFormatter = new JSONFormatter();
		jsonFormatter.format();
		assertSame(jsonFormatter.getTotalWarnings().size(), 0);
	}

	/**
	 * Tests if the JSONFormatter generates a list of warnings when the list of
	 * output files are not empty There is just one output file for each ASAT.
	 * The output files are modified (smaller output files) for the test.
	 * 
	 * @throws IOException
	 *             throws an exception if a problem is encountered while reading
	 *             the file.
	 */
	@Test
	public void testListOfOneOutputFile() throws IOException {
		JavaController.setASATOutputFiles(ASAT.CheckStyle, checkstyleList);
		JavaController.setASATOutputFiles(ASAT.PMD, pmdList);
		JavaController.setASATOutputFiles(ASAT.FindBugs, findBugsList);
		JSONFormatter jsonFormatter = new JSONFormatter();
		jsonFormatter.format();
		assertSame(jsonFormatter.getTotalWarnings().size(), 8);
	}
	
	/**
	 * Tests if the JSONFormatter generates a list of warnings when the list of
	 * output files are not empty. There are two output files for each ASAT.
	 * The output files are modified (smaller output files) for the test.
	 * 
	 * @throws IOException
	 *             throws an exception if a problem is encountered while reading
	 *             the file.
	 */
	@Test
	public void testListOfTwoOutputFile() throws IOException {
		checkstyleList.add(checkStyleOutputFilePath);
		pmdList.add(pmdOutputFilePath);
		findBugsList.add(findBugsOutputFilePath);
		JavaController.setASATOutputFiles(ASAT.CheckStyle, checkstyleList);
		JavaController.setASATOutputFiles(ASAT.PMD, pmdList);
		JavaController.setASATOutputFiles(ASAT.FindBugs, findBugsList);
		JSONFormatter jsonFormatter = new JSONFormatter();
		jsonFormatter.format();
		assertSame(jsonFormatter.getTotalWarnings().size(), 16);
	}

}
