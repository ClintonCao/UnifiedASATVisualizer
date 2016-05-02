package BlueTurtle.warnings;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;

/**
 * Test for CheckStyleWarning class.
 * @author BlueTurtle.
 *
 */
public class ChecktStyleWarningTest {

	private static String filePath = "\\Dummy\\Cool.java";
	private static String fileName = "Cool.java";
	private static String message = "Unused @param tag for 'filePath'.";
	private static String ruleName = "JavadocMethod";

	/**
	 * Test equal method where both objects represent the same warning.
	 */
	@Test
	public void testEqualsTrue() {
		CheckStyleWarning expected = new CheckStyleWarning(filePath, fileName, 1, message, ruleName);
		CheckStyleWarning actual = new CheckStyleWarning(filePath, fileName, 1, message, ruleName);
		assertEquals(expected, actual);
	}

	/**
	 * Test equal method where both objects represent the same warning.
	 */
	@Test
	public void testEqualsTrueWithSet() {
		CheckStyleWarning expected = new CheckStyleWarning(filePath, fileName, 1, message, ruleName);
		expected.setFileName("");
		expected.setFileName(fileName);
		CheckStyleWarning actual = new CheckStyleWarning(filePath, fileName, 1, message, ruleName);
		assertEquals(expected, actual);
	}

	/**
	 * Test equal method where both objects does represent the same warning.
	 */
	@Test
	public void testEqualsFalse() {
		CheckStyleWarning expected = new CheckStyleWarning(filePath, "Hot.java", 1, message, ruleName);
		CheckStyleWarning actual = new CheckStyleWarning(filePath, fileName, 1, message, ruleName);
		assertNotEquals(expected, actual);
	}

	/**
	 * Test equal method where they have different file path.
	 */
	@Test
	public void testEqualsFalseWithDifferentPath() {
		CheckStyleWarning expected = new CheckStyleWarning("\\Another\\Awesome.java", fileName, 1, message, ruleName);
		CheckStyleWarning actual = new CheckStyleWarning(filePath, fileName, 1, message, ruleName);
		assertNotEquals(expected, actual);
	}

	/**
	 * Test equal method where they have different message.
	 */
	@Test
	public void testEqualsFalseWithDifferentMessage() {
		CheckStyleWarning expected = new CheckStyleWarning("\\Another\\Awesome.java", fileName, 1, message, ruleName);
		expected.setMessage("This is not cool");
		CheckStyleWarning actual = new CheckStyleWarning(filePath, fileName, 1, message, ruleName);
		assertNotEquals(expected, actual);
	}

	/**
	 * Test equal method where they have rule name.
	 */
	@Test
	public void testEqualsFalseWithDifferentRule() {
		CheckStyleWarning expected = new CheckStyleWarning("\\Another\\Awesome.java", fileName, 1, message, ruleName);
		expected.setRuleName("This is not a rule");
		CheckStyleWarning actual = new CheckStyleWarning(filePath, fileName, 1, message, ruleName);
		assertNotEquals(expected, actual);
	}

}
