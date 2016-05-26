package BlueTurtle.groupers;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

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

	private Warning w;
	private Warning w2;
	private String filePath;
	private String filePath2;
	private HashMap<String, String> componentsInfo;
	private Set<String> packagesNames;
	private ComponentSummarizer cs;
	private ComponentSummarizer cs2;
	private PackageSummarizer ps;
	private PackageSummarizer ps2;
	private List<Warning> warnings;

	/**
	 * Initialize necessary objects.
	 */
	@Before
	public void initialize() {
		filePath = "./src/test/resources/ExampleClass.txt";
		filePath2 = "./src/test/resources/ExampleTestClass.txt";
		componentsInfo = new HashMap<String, String>();
		componentsInfo.put("ExampleClass.java", filePath);
		componentsInfo.put("ExampleTestClass.java", filePath2);
		packagesNames = new HashSet<String>();
		packagesNames.add("SomePackage.subpackage");
		packagesNames.add("SomePackage.different");
		w = new CheckStyleWarning(filePath, "ExampleClass.java", 3, "Test", "TestRule", "Class");
		w2 = new CheckStyleWarning(filePath2, "ExampleTestClass.java", 3, "Test", "TestRule", "Class");
		cs = new ComponentSummarizer("ExampleClass.java", filePath, "SomePackage.subpackage");
		cs2 = new ComponentSummarizer("ExampleTestClass.java", filePath2, "SomePackage.different");
		ps = new PackageSummarizer("SomePackage.subpackage");
		ps2 = new PackageSummarizer("SomePackage.different");
		warnings = new ArrayList<Warning>();
		warnings.add(w);
		warnings.add(w2);
	}

	/**
	 * Test group by default case.
	 */
	@Test
	public void testGroupByDefaultCase() {
		List<Warning> warnings = new ArrayList<Warning>();
		warnings.add(w);
		warnings.add(w2);
		WarningGrouper wg = new WarningGrouper(componentsInfo, packagesNames, warnings);
		assertNull(wg.groupBy(null));
	}

	/**
	 * Test group by components.
	 */
	@Test
	public void testGroupByComponents() {
		cs.summarise(warnings);
		cs2.summarise(warnings);
		List<Summarizer> expected = new ArrayList<Summarizer>();
		expected.add(cs);
		expected.add(cs2);
		WarningGrouper wg = new WarningGrouper(componentsInfo, packagesNames, warnings);
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
		WarningGrouper wg = new WarningGrouper(componentsInfo, packagesNames, warnings);
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
		WarningGrouper wg = new WarningGrouper(componentsInfo, packagesNames, warnings);
		WarningGrouper wg2 = new WarningGrouper(componentsInfo, packagesNames, warnings);
		assertEquals(wg, wg2);
	}

	/**
	 * Test equals where the warnings are different.
	 */
	@Test
	public void testEqualsFalseDifferentWarningList() {
		WarningGrouper wg = new WarningGrouper(componentsInfo, packagesNames, new ArrayList<Warning>());
		WarningGrouper wg2 = new WarningGrouper(componentsInfo, packagesNames, warnings);
		assertNotEquals(wg, wg2);
	}

	/**
	 * Test equals where the name of the packages are different.
	 */
	@Test
	public void testEqualsFalseDifferentPackagesNames() {
		WarningGrouper wg = new WarningGrouper(componentsInfo, packagesNames, warnings);
		WarningGrouper wg2 = new WarningGrouper(componentsInfo, new HashSet<String>(), warnings);
		assertNotEquals(wg, wg2);
	}

	/**
	 * Test equals where the components information are different.
	 */
	@Test
	public void testEqualsFalseDifferentComponentsInfo() {
		WarningGrouper wg = new WarningGrouper(componentsInfo, packagesNames, warnings);
		WarningGrouper wg2 = new WarningGrouper(new HashMap<String, String>(), packagesNames, warnings);
		assertNotEquals(wg, wg2);
	}
	
	/**
	 * Test equals where the components information and warnings are different.
	 */
	@Test
	public void testEqualsFalseDifferentComponentsInfoAndWarnings() {
		WarningGrouper wg = new WarningGrouper(componentsInfo, packagesNames, warnings);
		WarningGrouper wg2 = new WarningGrouper(new HashMap<String, String>(), packagesNames, new ArrayList<Warning>());
		assertNotEquals(wg, wg2);
	}
	
	/**
	 * Test equals where the components information and warnings are different.
	 */
	@Test
	public void testEqualsFalseDifferentComponentsInfoAndNames() {
		WarningGrouper wg = new WarningGrouper(componentsInfo, packagesNames, warnings);
		WarningGrouper wg2 = new WarningGrouper(new HashMap<String, String>(), packagesNames, warnings);
		assertNotEquals(wg, wg2);
	}
	
	/**
	 * Test equals where the warnings and packages names are different.
	 */
	@Test
	public void testEqualsFalseDifferentWarningsAndNames() {
		WarningGrouper wg = new WarningGrouper(componentsInfo, packagesNames, warnings);
		WarningGrouper wg2 = new WarningGrouper(componentsInfo, new HashSet<String>(), new ArrayList<Warning>());
		assertNotEquals(wg, wg2);
	}
	
	/**
	 * Test equals with different object (that is not a grouper).
	 */
	@Test
	public void testEqualsFalseDifferentObject() {
		WarningGrouper wg = new WarningGrouper(componentsInfo, packagesNames, warnings);
		boolean answer = wg.equals(Integer.valueOf(6));
		assertFalse(answer);
	}
	
	/**
	 * Test summarizedWarnings.
	 */
	@Test
	public void testSummarizedWarnings() {
		WarningGrouper wg = new WarningGrouper(componentsInfo, packagesNames, warnings);
		EnumMap<Criteria, List<Summarizer>> empty = new EnumMap<Criteria, List<Summarizer>>(Criteria.class);
		EnumMap<Criteria, List<Summarizer>> actual = wg.getSummarizedWarnings();
		assertNotEquals(empty, actual);
	}

}
