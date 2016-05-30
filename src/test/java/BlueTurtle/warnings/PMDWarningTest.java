package BlueTurtle.warnings;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import BlueTurtle.parsers.GDCParser;

/**
 * Test for PMDWarning class.
 * @author BlueTurtle.
 *
 */
public class PMDWarningTest {
	
	private static String filePath = "\\src\\test\\TestPMD.java";
	private static String testSet3 = "./src/test/resources/asat-gdc-mapping.html";

	private static String fileName = "PMD.java";
	private static String packageName = "test";
	private static String ruleName = "UnnecessaryBooleanAssertion";
	private static String ruleSet = "JUnit";
	private static String method = "equals";
	private static String classification = "Refactorings - Redundancies";

	private static HashMap<String, String> categoryInfo = new HashMap<String, String>();

	/**
	 * Set up the GDP parser, parse the category information.
	 */
	@Before
	public void setUp() {
		GDCParser gP = GDCParser.getInstance();
		categoryInfo = gP.parseFile(testSet3);
	}

	/**
	 * Test equal method where both objects represent the same warning.
	 */
	@Test
	public void testEqualsTrue() {
		PMDWarning expected = new PMDWarning(filePath, fileName, 1, packageName, ruleSet, method, ruleName, classification);
		PMDWarning actual = new PMDWarning(filePath, fileName, 1, packageName, ruleSet, method, ruleName, classification);
		assertEquals(expected, actual);
	}
	
	/**
	 * Test objects that are equal should return same HashCode.
	 */
	@Test
	public void testSameHashCode() {
		PMDWarning expected = new PMDWarning(filePath, fileName, 1, packageName, ruleSet, method, ruleName, classification);
		PMDWarning actual = new PMDWarning(filePath, fileName, 1, packageName, ruleSet, method, ruleName, classification);
		assertEquals(expected.hashCode(), actual.hashCode());
	}

	/**
	 * Test equal method where objects have different file paths.
	 */
	@Test
	public void testEqualsFalseWithDifferentPath() {
		PMDWarning expected = new PMDWarning(filePath, fileName, 1, packageName, ruleSet, method, ruleName, classification);
		expected.setFilePath("\\src\\temp\\");
		PMDWarning actual = new PMDWarning(filePath, fileName, 1, packageName, ruleSet, method, ruleName, classification);
		assertNotEquals(expected, actual);
	}
	
	/**
	 * Test equal method where objects have different file names.
	 */
	@Test
	public void testEqualsFalseWithDifferentFileName() {
		PMDWarning expected = new PMDWarning(filePath, fileName, 1, packageName, ruleSet, method, ruleName, classification);
		expected.setFileName("okay.java");
		PMDWarning actual = new PMDWarning(filePath, fileName, 1, packageName, ruleSet, method, ruleName, classification);
		assertNotEquals(expected, actual);
	}	

	/**
	 * Test equal method where objects have different line numbers.
	 */
	@Test
	public void testEqualsFalseWithDifferentLines() {
		PMDWarning expected = new PMDWarning(filePath, fileName, 2, packageName, ruleSet, method, ruleName, classification);
		PMDWarning actual = new PMDWarning(filePath, fileName, 1, packageName, ruleSet, method, ruleName, classification);
		assertNotEquals(expected, actual);
	}	
	
	/**
	 * Test equal method where objects have different rules.
	 */
	@Test
	public void testEqualsFalseWithDifferentRules() {
		PMDWarning expected = new PMDWarning(filePath, fileName, 2, packageName, ruleSet, method, ruleName, classification);
		expected.setClassification("OverrideBothEqualsAndHashcode");
		PMDWarning actual = new PMDWarning(filePath, fileName, 1, packageName, ruleSet, method, ruleName, classification);
		assertNotEquals(expected, actual);
	}
	
	/**
	 * Test equal method where objects have different packages.
	 */
	@Test
	public void testEqualsFalseWithDifferentPackages() {
		PMDWarning expected = new PMDWarning(filePath, fileName, 2, packageName, ruleSet, method, ruleName, classification);
		expected.setPackageName("BlueTurtle.warnings");
		PMDWarning actual = new PMDWarning(filePath, fileName, 1, packageName, ruleSet, method, ruleName, classification);
		assertNotEquals(expected, actual);
	}
	
	/**
	 * Test equal method where objects have different types.
	 */
	@Test
	public void testEqualsFalseWithDifferentTypes() {
		PMDWarning expected = new PMDWarning(filePath, fileName, 2, packageName, ruleSet, method, ruleName, classification);
		expected.setType("warning1");
		PMDWarning actual = new PMDWarning(filePath, fileName, 1, packageName, ruleSet, method, ruleName, classification);
		actual.setType("warning2");
		assertNotEquals(expected, actual);
	}		
	
	/**
	 * Test equal method where objects have different ruleSets.
	 */
	@Test
	public void testEqualsFalseWithDifferentRuleSets() {
		PMDWarning expected = new PMDWarning(filePath, fileName, 2, packageName, ruleSet, method, ruleName, classification);
		expected.setRuleSet("Basic");
		PMDWarning actual = new PMDWarning(filePath, fileName, 1, packageName, ruleSet, method, ruleName, classification);
		assertNotEquals(expected, actual);
	}		

	/**
	 * Test equal method where objects have different methods.
	 */
	@Test
	public void testEqualsFalseWithDifferentMethods() {
		PMDWarning expected = new PMDWarning(filePath, fileName, 2, packageName, ruleSet, method, ruleName, classification);
		expected.setMethod("foo");
		PMDWarning actual = new PMDWarning(filePath, fileName, 1, packageName, ruleSet, method, ruleName, classification);
		assertNotEquals(expected, actual);
	}
	
	/**
	 * Test equal method where one object has PMDWarning, the other one has CheckStyleWarning.
	 */
	@Test
	public void testEqualsFalsePMDandCheckStyle() {
		PMDWarning expected = new PMDWarning(filePath, fileName, 1, packageName, ruleSet, method, ruleName, classification);
		CheckStyleWarning actual = new CheckStyleWarning(filePath, fileName, 1, "lalala", ruleName, classification);
		assertNotEquals(expected, actual);
	}	
	
	/**
	 * Test the change of the line of a PMD warning.
	 */
	@Test
	public void testChangeOfLine() {
		int expected = 5;
		PMDWarning pmd = new PMDWarning(filePath, fileName, 1, packageName, ruleSet, method, ruleName, classification);
		int actual = pmd.getLine();
		pmd.setLine(expected);
		assertNotSame(expected, actual);
	}	
	
	/**
	 * Test the change of the package name of a FingBugs warning.
	 */
	@Test
	public void testChangeOfPackageName() {
		String expected = "BlueTurtle.parsers";
		PMDWarning pmd = new PMDWarning(filePath, fileName, 1, packageName, ruleSet, method, ruleName, classification);
		String actual = pmd.getPackageName();
		pmd.setPackageName(expected);
		assertNotEquals(expected, actual);
	}		
	
	/**
	 * Test the change of the rule set of a FingBugs warning.
	 */
	@Test
	public void testChangeOfRuleSet() {
		String expected = "Basic1";
		PMDWarning pmd = new PMDWarning(filePath, fileName, 1, packageName, ruleSet, method, ruleName, classification);
		String actual = pmd.getRuleSet();
		pmd.setRuleSet(expected);
		assertNotEquals(expected, actual);
	}
	
	/**
	 * Test the change of the method of a FingBugs warning.
	 */
	@Test
	public void testChangeOfMethod() {
		String expected = "func";
		PMDWarning pmd = new PMDWarning(filePath, fileName, 1, packageName, ruleSet, method, ruleName, classification);
		String actual = pmd.getMethod();
		pmd.setMethod(expected);
		assertNotEquals(expected, actual);
	}		
}
