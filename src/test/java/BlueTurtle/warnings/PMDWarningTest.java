package BlueTurtle.warnings;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
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


}
