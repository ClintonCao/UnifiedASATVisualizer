package BlueTurtle.warnings;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Test;

/**
 * Test for PMDWarning class.
 * @author BlueTurtle.
 *
 */
public class PMDWarningTest {
	
	private static String filePath = "\\src\\test\\TestPMD.java";
	private static String fileName = "PMD.java";
	private static String packageName = "test";
	private static String ruleName = "UnnecessaryBooleanAssertion";
	private static String ruleSet = "JUnit";
	private static String method = "equals";


	/**
	 * Test equal method where both objects represent the same warning.
	 */
	@Test
	public void testEqualsTrue() {
		PMDWarning expected = new PMDWarning(filePath, fileName, 1, packageName, ruleSet, method, ruleName);
		PMDWarning actual = new PMDWarning(filePath, fileName, 1, packageName, ruleSet, method, ruleName);
		assertEquals(expected, actual);
	}

	/**
	 * Test equal method where objects have different file paths.
	 */
	@Test
	public void testEqualsFalseWithDifferentPath() {
		PMDWarning expected = new PMDWarning(filePath, fileName, 1, packageName, ruleSet, method, ruleName);
		expected.setFilePath("\\src\\temp\\");
		PMDWarning actual = new PMDWarning(filePath, fileName, 1, packageName, ruleSet, method, ruleName);
		assertNotEquals(expected, actual);
	}
	
	/**
	 * Test equal method where objects have different file names.
	 */
	@Test
	public void testEqualsFalseWithDifferentFileName() {
		PMDWarning expected = new PMDWarning(filePath, fileName, 1, packageName, ruleSet, method, ruleName);
		expected.setFileName("okay.java");
		PMDWarning actual = new PMDWarning(filePath, fileName, 1, packageName, ruleSet, method, ruleName);
		assertNotEquals(expected, actual);
	}	

	/**
	 * Test equal method where objects have different line numbers.
	 */
	@Test
	public void testEqualsFalseWithDifferentLines() {
		PMDWarning expected = new PMDWarning(filePath, fileName, 2, packageName, ruleSet, method, ruleName);
		PMDWarning actual = new PMDWarning(filePath, fileName, 1, packageName, ruleSet, method, ruleName);
		assertNotEquals(expected, actual);
	}	
	
	/**
	 * Test equal method where objects have different rules.
	 */
	@Test
	public void testEqualsFalseWithDifferentRules() {
		PMDWarning expected = new PMDWarning(filePath, fileName, 2, packageName, ruleSet, method, ruleName);
		expected.setClassification("OverrideBothEqualsAndHashcode");
		PMDWarning actual = new PMDWarning(filePath, fileName, 1, packageName, ruleSet, method, ruleName);
		assertNotEquals(expected, actual);
	}
	
	/**
	 * Test equal method where objects have different packages.
	 */
	@Test
	public void testEqualsFalseWithDifferentPackages() {
		PMDWarning expected = new PMDWarning(filePath, fileName, 2, packageName, ruleSet, method, ruleName);
		expected.setPackageName("BlueTurtle.warnings");
		PMDWarning actual = new PMDWarning(filePath, fileName, 1, packageName, ruleSet, method, ruleName);
		assertNotEquals(expected, actual);
	}
	
	/**
	 * Test equal method where objects have different types.
	 */
	@Test
	public void testEqualsFalseWithDifferentTypes() {
		PMDWarning expected = new PMDWarning(filePath, fileName, 2, packageName, ruleSet, method, ruleName);
		expected.setType("warning1");
		PMDWarning actual = new PMDWarning(filePath, fileName, 1, packageName, ruleSet, method, ruleName);
		actual.setType("warning2");
		assertNotEquals(expected, actual);
	}		
	
	/**
	 * Test equal method where objects have different ruleSets.
	 */
	@Test
	public void testEqualsFalseWithDifferentRuleSets() {
		PMDWarning expected = new PMDWarning(filePath, fileName, 2, packageName, ruleSet, method, ruleName);
		expected.setRuleSet("Basic");
		PMDWarning actual = new PMDWarning(filePath, fileName, 1, packageName, ruleSet, method, ruleName);
		assertNotEquals(expected, actual);
	}		

	/**
	 * Test equal method where objects have different methods.
	 */
	@Test
	public void testEqualsFalseWithDifferentMethods() {
		PMDWarning expected = new PMDWarning(filePath, fileName, 2, packageName, ruleSet, method, ruleName);
		expected.setMethod("foo");
		PMDWarning actual = new PMDWarning(filePath, fileName, 1, packageName, ruleSet, method, ruleName);
		assertNotEquals(expected, actual);
	}
	
	/**
	 * Test equal method where one object has PMDWarning, the other one has CheckStyleWarning.
	 */
	@Test
	public void testEqualsFalsePMDandCheckStyle() {
		PMDWarning expected = new PMDWarning(filePath, fileName, 1, packageName, ruleSet, method, ruleName);
		CheckStyleWarning actual = new CheckStyleWarning(filePath, fileName, 1, "lalala", ruleName);
		assertNotEquals(expected, actual);
	}	
	
	/**
	 * Test the change of the line of a PMD warning.
	 */
	@Test
	public void testChangeOfLine() {
		int expected = 5;
		PMDWarning pmd = new PMDWarning(filePath, fileName, 1, packageName, ruleSet, method, ruleName);
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
		PMDWarning pmd = new PMDWarning(filePath, fileName, 1, packageName, ruleSet, method, ruleName);
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
		PMDWarning pmd = new PMDWarning(filePath, fileName, 1, packageName, ruleSet, method, ruleName);
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
		PMDWarning pmd = new PMDWarning(filePath, fileName, 1, packageName, ruleSet, method, ruleName);
		String actual = pmd.getMethod();
		pmd.setMethod(expected);
		assertNotEquals(expected, actual);
	}		
}
