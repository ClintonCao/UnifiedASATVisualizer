package BlueTurtle.groupers;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import BlueTurtle.finders.ProjectInfoFinder;
import BlueTurtle.groupers.WarningGrouper.Criteria;
import BlueTurtle.summarizers.ComponentSummarizer;
import BlueTurtle.summarizers.PackageSummarizer;
import BlueTurtle.summarizers.Summarizer;
import BlueTurtle.warnings.CheckStyleWarning;
import BlueTurtle.warnings.Warning;

/**
 * Tests for the WarningGrouper class.
 * 
 * @author BlueTurtle.
 *
 */
public class WarningGrouperTest {

	private ComponentSummarizer cs;
	private ComponentSummarizer cs2;
	private ComponentSummarizer cs3;
	private ComponentSummarizer cs4;
	private PackageSummarizer ps;
	private PackageSummarizer ps2;
	private List<Warning> warnings;
	private Warning w;
	private Warning w2;
	private Warning w3;
	private Warning w4;

	/**
	 * Initialize necessary objects.
	 * 
	 * @throws IOException
	 *             throws an exception if there problem was encountered while
	 *             reading file.
	 */
	@Before
	public void initialize() throws IOException {
		ProjectInfoFinder pif = new ProjectInfoFinder();
		pif.findFiles(new File(System.getProperty("user.dir") + "/src/test/resources"));
		String filePath = ProjectInfoFinder.getClassPaths().stream().filter(path -> path.endsWith(
				"src" + File.separator + "test" + File.separator + "resources" + File.separator + "ExampleClass.java"))
				.findFirst().get();
		String filePath2 = ProjectInfoFinder.getClassPaths().stream().filter(path -> path.endsWith("src"
				+ File.separator + "test" + File.separator + "resources" + File.separator + "ExampleTestClass.java"))
				.findFirst().get();
		String filePath3 = ProjectInfoFinder.getClassPaths().stream()
				.filter(path -> path.endsWith("src" + File.separator + "test" + File.separator + "resources"
						+ File.separator + "TestCodeFolder" + File.separator + "AllClosestPoints.java"))
				.findFirst().get();
		String filePath4 = ProjectInfoFinder.getClassPaths().stream().filter(path -> path.endsWith(
				"src" + File.separator + "test" + File.separator + "resources" + File.separator + "DefaultClass.java"))
				.findFirst().get();
		w = new CheckStyleWarning(filePath, "ExampleClass.java", 3, "Test", "TestRule", "Class");
		w2 = new CheckStyleWarning(filePath2, "ExampleTestClass.java", 3, "Test", "TestRule", "Class");
		w3 = new CheckStyleWarning(filePath3, "AllClosestPoints.java", 3, "Test", "TestRule", "Class");
		w4 = new CheckStyleWarning(filePath4, "DefaultClass.java", 3, "Test", "TestRule", "Class");
		cs = new ComponentSummarizer("ExampleClass.java", filePath, "SomePackage.subpackage");
		cs2 = new ComponentSummarizer("ExampleTestClass.java", filePath2, "SomePackage.different");
		cs3 = new ComponentSummarizer("AllClosestPoints.java", filePath3, "default");
		cs4 = new ComponentSummarizer("DefaultClass.java", filePath4, "default");
		ps = new PackageSummarizer("SomePackage.subpackage");
		ps2 = new PackageSummarizer("SomePackage.different");
		warnings = new ArrayList<Warning>();
		warnings.add(w);
		warnings.add(w2);
		warnings.add(w3);
		warnings.add(w4);
		cs.summarise(warnings);
		cs2.summarise(warnings);
		cs3.summarise(warnings);
		cs4.summarise(warnings);
		
	}

	/**
	 * Clean up the attributes of ProjectInfoFinder.
	 */
	@After
	public void cleanAttributes() {
		ProjectInfoFinder.getClassLocs().clear();
		ProjectInfoFinder.getClassPaths().clear();
		ProjectInfoFinder.getClassPackage().clear();
		ProjectInfoFinder.getPackages().clear();
	}

	/**
	 * Test group by default case.
	 */
	@Test
	public void testGroupByDefaultCase() {
		List<Warning> warnings = new ArrayList<Warning>();
		warnings.add(w);
		warnings.add(w2);
		WarningGrouper wg = new WarningGrouper(warnings);
		assertNull(wg.groupBy(null));
	}

	/**
	 * Test group by components.
	 */
	@Test
	public void testGroupByComponents() {
		List<Summarizer> expected = new ArrayList<Summarizer>();
		expected.add(cs4);
		expected.add(cs);
		expected.add(cs2);
		expected.add(cs3);
		WarningGrouper wg = new WarningGrouper(warnings);
		List<Summarizer> actual = wg.groupBy(Criteria.COMPONENTS);
		assertEquals(expected, actual);
	}

	/**
	 * Test group by packages.
	 */
	@Test
	public void testGroupByPackages() {
		ps.summarise(warnings);
		ps2.summarise(warnings);
		WarningGrouper wg = new WarningGrouper(warnings);
		List<Summarizer> list = wg.groupBy(Criteria.PACKAGES);

		// because the order of the set is not always the same.
		boolean answer = list.contains(ps) && list.contains(ps2);
		assertTrue(answer);
	}

	/**
	 * Test equals where both grouper are the same.
	 */
	@Test
	public void testEqualsTrue() {
		WarningGrouper wg = new WarningGrouper(warnings);
		WarningGrouper wg2 = new WarningGrouper(warnings);
		assertEquals(wg, wg2);
	}

	/**
	 * Test objects that are the same should return same HashCode.
	 */
	@Test
	public void testSameHashCode() {
		WarningGrouper wg = new WarningGrouper(warnings);
		WarningGrouper wg2 = new WarningGrouper(warnings);
		assertEquals(wg.hashCode(), wg2.hashCode());
	}

	/**
	 * Test equals where the warnings are different.
	 */
	@Test
	public void testEqualsFalseDifferentWarningList() {
		WarningGrouper wg = new WarningGrouper(new ArrayList<Warning>());
		WarningGrouper wg2 = new WarningGrouper(warnings);
		assertNotEquals(wg, wg2);
	}

	/**
	 * Test equals with different object (that is not a grouper).
	 */
	@Test
	public void testEqualsFalseDifferentObject() {
		WarningGrouper wg = new WarningGrouper(warnings);
		boolean answer = wg.equals(Integer.valueOf(6));
		assertFalse(answer);
	}

	/**
	 * Test summarizedWarnings.
	 */
	@Test
	public void testSummarizedWarnings() {
		WarningGrouper wg = new WarningGrouper(warnings);
		EnumMap<Criteria, List<Summarizer>> empty = new EnumMap<Criteria, List<Summarizer>>(Criteria.class);
		EnumMap<Criteria, List<Summarizer>> actual = wg.getSummarizedWarnings();
		assertNotEquals(empty, actual);
	}

}
