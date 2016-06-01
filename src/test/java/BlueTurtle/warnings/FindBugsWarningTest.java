package BlueTurtle.warnings;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Test;


/**
 * Test for FindBugsWarning class.
 * @author BlueTurtle.
 *
 */
public class FindBugsWarningTest {
	
	private static String filePath = "\\src\\test\\TestPMD.java";
	private static String fileName = "FindBugs.java";
	private static String message = "BlueTurtle.warnings.PMDWarning defines equals and uses Object.hashCode()";
	private static String ruleName = "HE_EQUALS_USE_HASHCODE";
	private static String category = "BAD_PRACTICE";
	private static String priority = "High";
	private static String classification = "Interface";

	/**
	 * Test equal method where both objects represent the same warning.
	 */
	@Test
	public void testEqualsTrue() {
		FindBugsWarning expected = new FindBugsWarning(filePath, fileName, 1, message, category, priority, ruleName, classification);
		FindBugsWarning actual = new FindBugsWarning(filePath, fileName, 1, message, category, priority, ruleName, classification);
		assertEquals(expected, actual);
	}
	
	/**
	 * Test objects that are equals should return same HashCode.
	 */
	@Test
	public void testSameHashCode() {
		FindBugsWarning expected = new FindBugsWarning(filePath, fileName, 1, message, category, priority, ruleName, classification);
		FindBugsWarning actual = new FindBugsWarning(filePath, fileName, 1, message, category, priority, ruleName, classification);
		assertEquals(expected.hashCode(), actual.hashCode());
	}

	/**
	 * Test equal method where objects have different file paths.
	 */
	@Test
	public void testEqualsFalseWithDifferentPath() {
		FindBugsWarning expected = new FindBugsWarning(filePath, fileName, 1, message, category, priority, ruleName, classification);
		expected.setFilePath("\\src\\temp\\");
		FindBugsWarning actual = new FindBugsWarning(filePath, fileName, 1, message, category, priority, ruleName, classification);
		assertNotEquals(expected, actual);
	}
	
	/**
	 * Test equal method where objects have different file names.
	 */
	@Test
	public void testEqualsFalseWithDifferentFileName() {
		FindBugsWarning expected = new FindBugsWarning(filePath, fileName, 1, message, category, priority, ruleName, classification);
		expected.setFileName("okay.java");
		FindBugsWarning actual = new FindBugsWarning(filePath, fileName, 1, message, category, priority, ruleName, classification);
		assertNotEquals(expected, actual);
	}	

	/**
	 * Test equal method where objects have different line numbers.
	 */
	@Test
	public void testEqualsFalseWithDifferentLines() {
		FindBugsWarning expected = new FindBugsWarning(filePath, fileName, 1, message, category, priority, ruleName, classification);
		FindBugsWarning actual = new FindBugsWarning(filePath, fileName, 2, message, category, priority, ruleName, classification);
		assertNotEquals(expected, actual);
	}	
	
	/**
	 * Test equal method where objects have different rules.
	 */
	@Test
	public void testEqualsFalseWithDifferentRules() {
		FindBugsWarning expected = new FindBugsWarning(filePath, fileName, 1, message, category, priority, ruleName, classification);
		expected.setClassification("UUF_UNUSED_FIELD");
		FindBugsWarning actual = new FindBugsWarning(filePath, fileName, 1, message, category, priority, ruleName, classification);
		assertNotEquals(expected, actual);
	}
	
	/**
	 * Test equal method where objects have different categories.
	 */
	@Test
	public void testEqualsFalseWithDifferentCategories() {
		FindBugsWarning expected = new FindBugsWarning(filePath, fileName, 1, message, category, priority, ruleName, classification);
		expected.setCategory("STYLE");
		FindBugsWarning actual = new FindBugsWarning(filePath, fileName, 1, message, category, priority, ruleName, classification);
		assertNotEquals(expected, actual);
	}
	
	/**
	 * Test equal method where objects have different priorities.
	 */
	@Test
	public void testEqualsFalseWithDifferentPriority() {
		FindBugsWarning expected = new FindBugsWarning(filePath, fileName, 1, message, category, priority, ruleName, classification);
		expected.setPriority("Normal");
		FindBugsWarning actual = new FindBugsWarning(filePath, fileName, 1, message, category, priority, ruleName, classification);
		assertNotEquals(expected, actual);
	}		
	
	
	/**
	 * Test equal method where one object has FindBugsWarning, the other one has CheckStyleWarning.
	 */
	@Test
	public void testEqualsFalsePMDandCheckStyle() {
		FindBugsWarning expected = new FindBugsWarning(filePath, fileName, 1, message, category, priority, ruleName, classification);
		CheckStyleWarning actual = new CheckStyleWarning(filePath, fileName, 1, "lalala", ruleName, classification);
		assertNotEquals(expected, actual);
	}
	
	/**
	 * Test the change of the line of a FindBugs warning.
	 */
	@Test
	public void testChangeOfLine() {
		int expected = 5;
		FindBugsWarning fb = new FindBugsWarning(filePath, fileName, 1, message, category, priority, ruleName, classification);
		int actual = fb.getLineNumber();
		fb.setLineNumber(expected);
		assertNotSame(expected, actual);
	}
	
	/**
	 * Test the change of the message of a FingBugs warning.
	 */
	@Test
	public void testChangeOfMessage() {
		String expected = "cool";
		FindBugsWarning fb = new FindBugsWarning(filePath, fileName, 1, message, category, priority, ruleName, classification);
		String actual = fb.getMessage();
		fb.setMessage(expected);
		assertNotEquals(expected, actual);
	}	

	/**
	 * Test the change of the category of a FingBugs warning.
	 */
	@Test
	public void testChangeOfCategory() {
		String expected = "I18N";
		FindBugsWarning fb = new FindBugsWarning(filePath, fileName, 1, message, category, priority, ruleName, classification);
		String actual = fb.getCategory();
		fb.setMessage(expected);
		assertNotEquals(expected, actual);
	}	
	
	/**
	 * Test the change of the priority of a FingBugs warning.
	 */
	@Test
	public void testChangeOfPriority() {
		String expected = "Low";
		FindBugsWarning fb = new FindBugsWarning(filePath, fileName, 1, message, category, priority, ruleName, classification);
		String actual = fb.getPriority();
		fb.setMessage(expected);
		assertNotEquals(expected, actual);
	}	
	
	/**
	 * Test the change of the rule name of a FingBugs warning.
	 */
	@Test
	public void testChangeOfRuleName() {
		String expected = "DM_DEFAULT_ENCODING";
		FindBugsWarning fb = new FindBugsWarning(filePath, fileName, 1, message, category, priority, ruleName, classification);
		String actual = fb.getClassification();
		fb.setMessage(expected);
		assertNotEquals(expected, actual);
	}		

	/**
	 * Test the change of the file name of a FingBugs warning.
	 */
	@Test
	public void testChangeOfFileName() {
		String expected = "this.java";
		FindBugsWarning fb = new FindBugsWarning(filePath, fileName, 1, message, category, priority, ruleName, classification);
		String actual = fb.getFileName();
		fb.setMessage(expected);
		assertNotEquals(expected, actual);
	}

	/**
	 * Test the change of the type of a FingBugs warning.
	 */
	@Test
	public void testChangeOfType() {
		String expected = "PMD";
		FindBugsWarning fb = new FindBugsWarning(filePath, fileName, 1, message, category, priority, ruleName, classification);
		String actual = fb.getType();
		fb.setMessage(expected);
		assertNotEquals(expected, actual);
	}		

	/**
	 * Test the change of the file path of a FingBugs warning.
	 */
	@Test
	public void testChangeOfFilePath() {
		String expected = "\\Documents\\GitHub\\Contextproject-TSE\\src\\main\\java";
		FindBugsWarning fb = new FindBugsWarning(filePath, fileName, 1, message, category, priority, ruleName, classification);
		String actual = fb.getFilePath();
		fb.setMessage(expected);
		assertNotEquals(expected, actual);
	}
	
}
