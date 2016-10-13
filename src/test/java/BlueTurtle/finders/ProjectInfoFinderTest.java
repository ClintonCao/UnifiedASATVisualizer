package BlueTurtle.finders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import BlueTurtle.gui.GUIController.ASAT;

/**
 * Test for the ProjectInforFinder class.
 * 
 * @author BlueTurtle.
 *
 */
public class ProjectInfoFinderTest {

	private ProjectInfoFinder pif;
	private String exampleFilePath;

	/**
	 * Initialize the objects that are needed.
	 * 
	 * @throws Exception
	 *             throws an exception if problem is encountered while
	 *             instantiating the objects.
	 */
	@Before
	public void setUp() throws Exception {
		pif = new ProjectInfoFinder();
		ProjectInfoFinder.getClassLocs().clear();
		ProjectInfoFinder.getClassPackage().clear();
		ProjectInfoFinder.getPackages().clear();
		ProjectInfoFinder.getClassPaths().clear();
		ProjectInfoFinder.getOutputFilesPaths().clear();
		exampleFilePath = Paths.get("src", "test", "resources", "TestCodeFolder", "AllClosestPoints.java")
				.toAbsolutePath().toString();
		pif.findFiles(new File(Paths.get("src", "test", "resources").toAbsolutePath().toString()));
	}

	/**
	 * Cleanup the fields of ProjectInfoFinder used for testing.
	 * 
	 * @throws Exception
	 *             throws an exception if problem is encountered while cleaning
	 *             up the attributes.
	 */
	@After
	public void cleanUp() throws Exception {
		ProjectInfoFinder.getClassLocs().clear();
		ProjectInfoFinder.getClassPackage().clear();
		ProjectInfoFinder.getPackages().clear();
		ProjectInfoFinder.getClassPaths().clear();
		ProjectInfoFinder.getOutputFilesPaths().clear();
	}

	/**
	 * Test finding files does not give an empty list of paths.
	 * 
	 */
	@Test
	public void testFindFilesNonEmptyListOfPaths() {
		assertFalse(ProjectInfoFinder.getClassPaths().isEmpty());
	}

	/**
	 * Test that the right package name is returned given a path to a file.
	 * 
	 */
	@Test
	public void testRightPackageNameForPathIsReturned() {
		String packageName = ProjectInfoFinder.getClassPackage().get(exampleFilePath);
		assertEquals("default", packageName);
	}

	/**
	 * Test that the right loc is returned given path.
	 * 
	 */
	@Test
	public void testRightLOCForPathIsReturned() {
		int actual = ProjectInfoFinder.getClassLocs().get(exampleFilePath);
		assertEquals(272, actual);
	}

	/**
	 * Test that the right set of packages names is returned given path.
	 * 
	 */
	@Test
	public void testRightSetOfPackagesIsReturned() {
		Set<String> actual = ProjectInfoFinder.getPackages();
		Set<String> expected = new HashSet<String>();
		expected.add("default");
		expected.add("SomePackage.different");
		expected.add("SomePackage.subpackage");		
		assertEquals(expected, actual);
	}
	
	/**
	 * Test retrieveCodeFiles method. At least one instance of CodeFile should be added to codeFiles field.
	 * @throws IOException
	 * 				if file is not found, inaccessible, etc.
	 */
	@Test
	public void testRetrieveCodeFiles() throws IOException {
		pif.retrieveCodeFiles();
		assertTrue(pif.getCodeFiles().size() > 0);
	}
	
	
	/**
	 * Test the correct behaviour of checkForOutputFile.
	 */
	@Test
	public void testCheckForOutputFileTrue() {
		assertTrue(new ProjectInfoFinder().checkForASATOutputFile("checkstyle-result.xml"));
	}
	
	/**
	 * Test the correct behaviour of checkForOutputFile.
	 */
	@Test
	public void testCheckForOutputFileFalse() {
		assertFalse(new ProjectInfoFinder().checkForASATOutputFile("Hello I am a test string"));
	}
	
}
