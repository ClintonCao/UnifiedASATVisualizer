package BlueTurtle.finders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.File;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

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
		exampleFilePath = Paths.get("Runnables", "TestCode", "AllClosestPoints.java").toAbsolutePath().toString();
		System.out.println("Path = " + exampleFilePath);
		String testPath = System.getProperty("user.dir") + "/Runnables/TestCode/";
		System.out.println(testPath);
		pif.findFiles(new File(testPath));
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
		assertEquals(expected, actual);
	}

}
