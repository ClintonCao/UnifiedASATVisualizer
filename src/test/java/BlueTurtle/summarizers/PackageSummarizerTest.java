package BlueTurtle.summarizers;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertFalse;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import BlueTurtle.finders.ProjectInfoFinder;
import BlueTurtle.warnings.CheckStyleWarning;
import BlueTurtle.warnings.Warning;

/**
 * Test for the PackageSummarizer class.
 * 
 * @author BlueTurtle.
 *
 */
public class PackageSummarizerTest {

	private String packageName;
	private List<Warning> warningList;
	private Warning w;
	private List<Warning> warningList2;
	private Warning w2;
	private String filePath;
	private String filePath2;

	/**
	 * Initialize necessary objects.
	 * 
	 * @throws IOException
	 *             throws an exception if problem was encountered while reading
	 *             files.
	 */
	@Before
	public void initialize() throws IOException {
		ProjectInfoFinder pif = new ProjectInfoFinder();
		pif.findFiles(new File(System.getProperty("user.dir") + "/src/test/resources"));
		filePath = ProjectInfoFinder.getClassPaths().stream().filter(path -> path.endsWith(
				"src" + File.separator + "test" + File.separator + "resources" + File.separator + "ExampleClass.java"))
				.findFirst().get();
		filePath2 = ProjectInfoFinder.getClassPaths().stream().filter(path -> path.endsWith("src" + File.separator
				+ "test" + File.separator + "resources" + File.separator + "ExampleTestClass.java")).findFirst().get();
		packageName = "SomePackage.subpackage";
		w = new CheckStyleWarning(filePath, "ExampleClass.java", 3, "Test", "TestRule", "Class");
		w2 = new CheckStyleWarning(filePath2, "ExampleTestClass.java", 3, "Test", "TestRule", "Class");
		warningList = new ArrayList<Warning>();
		warningList2 = new ArrayList<Warning>();
		warningList2.add(w2);
		warningList2.add(w);
		warningList.add(w);
	}

	/**
	 * Clean up the attributes of ProjectInfoFinder.
	 */
	@After
	public void cleanUpAttributes() {
		ProjectInfoFinder.getClassLocs().clear();
		ProjectInfoFinder.getClassPaths().clear();
		ProjectInfoFinder.getClassPackage().clear();
		ProjectInfoFinder.getPackages().clear();
	}

	/**
	 * Test that there is no warning when the object is made.
	 */
	@Test
	public void testNumWarningIsZero() {
		PackageSummarizer ps = new PackageSummarizer(packageName);
		assertSame(0, ps.numberOfWarnings);
	}

	/**
	 * Test that the number of warning is not zero after summarise.
	 */
	@Test
	public void testNotZeroSummarise() {
		PackageSummarizer ps = new PackageSummarizer(packageName);
		ps.summarise(warningList);
		assertNotSame(0, ps.numberOfWarnings);
	}

	/**
	 * Test that the number of CheckStyle warnings is not zero after summarise.
	 */
	@Test
	public void testCheckStyleWarningsIsNotZero() {
		PackageSummarizer ps = new PackageSummarizer(packageName);
		ps.summarise(warningList);
		assertNotSame(0, ps.numberOfCheckStyleWarnings);
	}

	/**
	 * Test that the number of classes is not zero after summarise.
	 */
	@Test
	public void testNumClassesIsNotZero() {
		PackageSummarizer ps = new PackageSummarizer(packageName);
		ps.summarise(warningList);
		assertSame(1, ps.getNumberOfClasses());
	}

	/**
	 * Test that warning types is not empty after summarise.
	 */
	@Test
	public void testWarningTypeIsNotEmptyAfterSummarise() {
		PackageSummarizer ps = new PackageSummarizer(packageName);
		ps.summarise(warningList);
		Set<String> expected = new HashSet<String>();
		expected.add("CheckStyle");
		assertEquals(expected, ps.warningTypes);
	}

	/**
	 * Test that classes is not empty after summarise.
	 */
	@Test
	public void testClassesIsNotEmptyAfterSummarise() {
		PackageSummarizer ps = new PackageSummarizer(packageName);
		ps.summarise(warningList2);
		List<ComponentSummarizer> list = new ArrayList<ComponentSummarizer>();
		ComponentSummarizer cs = new ComponentSummarizer("ExampleClass.java", filePath, packageName);
		cs.summarise(warningList2);
		list.add(cs);
		assertEquals(list, ps.getClasses());
	}

	/**
	 * Test findOwnClasses.
	 */
	@Test
	public void testfindOwnClasses() {
		PackageSummarizer ps = new PackageSummarizer(packageName);
		HashMap<String, String> expected = new HashMap<String, String>();
		expected.put("ExampleClass.java", filePath);

		HashMap<String, String> actual = ps.findOwnClasses(warningList2);

		assertEquals(expected, actual);
	}

	/**
	 * Test equals false with only one PackageSummarizer running summarise.
	 */
	@Test
	public void testEqualsFalseWithOneRunningSummarise() {
		PackageSummarizer ps = new PackageSummarizer(packageName);
		ps.summarise(warningList2);
		PackageSummarizer ps2 = new PackageSummarizer(packageName);
		assertNotEquals(ps2, ps);
	}

	/**
	 * Test equals where both is running summarise.
	 */
	@Test
	public void testEqualsTrue() {
		PackageSummarizer ps = new PackageSummarizer(packageName);
		ps.summarise(warningList2);
		PackageSummarizer ps2 = new PackageSummarizer(packageName);
		ps2.summarise(warningList2);
		assertEquals(ps2, ps);
	}

	/**
	 * Test objects that are the same should return same hashCode.
	 */
	@Test
	public void tesSameHashCode() {
		PackageSummarizer ps = new PackageSummarizer(packageName);
		PackageSummarizer ps2 = new PackageSummarizer(packageName);
		assertEquals(ps2.hashCode(), ps.hashCode());
	}

	/**
	 * Test equals with another object.
	 */
	@Test
	public void testEqualsWithAnotherObject() {
		PackageSummarizer ps = new PackageSummarizer(packageName);
		ps.summarise(warningList2);
		boolean answer = ps.equals(Integer.valueOf(5));
		assertFalse(answer);
	}

}
