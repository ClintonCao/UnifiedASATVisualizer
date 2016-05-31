package BlueTurtle.warnings;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import BlueTurtle.parsers.GDCParser;

/**
 * Test for CheckStyleWarning class.
 * 
 * @author BlueTurtle.
 *
 */
public class ChecktStyleWarningTest {

	private static String filePath = "\\Dummy\\Cool.java";
	private static String testSet3 = "./src/test/resources/asat-gdc-mapping.html";

	private static String fileName = "Cool.java";
	private static String message = "Unused @param tag for 'filePath'.";
	private static String ruleName = "JavadocMethod";
	private static String classification = " Documentation Conventions";
	private static HashMap<String, String> categoryInfo = new HashMap<String, String>();


	/**
	 * Set up the GDP parser, parse the category information.
	 */
	@Before
	public void setUp() {
		GDCParser gP = new GDCParser();
		categoryInfo = gP.parseFile(testSet3);
	}
	
	/**
	 * Test equal method where both objects represent the same warning.
	 */
	@Test
	public void testEqualsTrue() {
		CheckStyleWarning expected = new CheckStyleWarning(filePath, fileName, 1, message, ruleName, classification);
		CheckStyleWarning actual = new CheckStyleWarning(filePath, fileName, 1, message, ruleName, classification);
		assertEquals(expected, actual);
	}
	
	/**
	 * Test objects that are equal should return same HashCode.
	 */
	@Test
	public void testSameHashCode() {
		CheckStyleWarning expected = new CheckStyleWarning(filePath, fileName, 1, message, ruleName, classification);
		CheckStyleWarning actual = new CheckStyleWarning(filePath, fileName, 1, message, ruleName, classification);
		assertEquals(expected.hashCode(), actual.hashCode());
	}

	/**
	 * Test equal method where both objects represent the same warning.
	 */
	@Test
	public void testEqualsTrueWithSet() {
		CheckStyleWarning expected = new CheckStyleWarning(filePath, fileName, 1, message, ruleName, classification);
		expected.setFileName("");
		expected.setFileName(fileName);
		CheckStyleWarning actual = new CheckStyleWarning(filePath, fileName, 1, message, ruleName, classification);
		assertEquals(expected, actual);
	}

	/**
	 * Test equal method where both objects does represent the same warning.
	 */
	@Test
	public void testEqualsFalse() {
		CheckStyleWarning expected = new CheckStyleWarning(filePath, "Hot.java", 1, message, ruleName, classification);
		CheckStyleWarning actual = new CheckStyleWarning(filePath, fileName, 1, message, ruleName, classification);
		assertNotEquals(expected, actual);
	}

	/**
	 * Test equal method where they have different file path.
	 */
	@Test
	public void testEqualsFalseWithDifferentPath() {
		CheckStyleWarning expected = new CheckStyleWarning("\\Another\\Awesome.java", fileName, 1, message, ruleName, classification);
		CheckStyleWarning actual = new CheckStyleWarning(filePath, fileName, 1, message, ruleName, classification);
		assertNotEquals(expected, actual);
	}

	/**
	 * Test equal method where they have different message.
	 */
	@Test
	public void testEqualsFalseWithDifferentMessage() {
		CheckStyleWarning expected = new CheckStyleWarning("\\Another\\Awesome.java", fileName, 1, message, ruleName, classification);
		expected.setMessage("This is not cool");
		CheckStyleWarning actual = new CheckStyleWarning(filePath, fileName, 1, message, ruleName, classification);
		assertNotEquals(expected, actual);
	}

	/**
	 * Test equal method where they have a different rule name.
	 */
	@Test
	public void testEqualsFalseWithDifferentRule() {
		CheckStyleWarning expected = new CheckStyleWarning("\\Another\\Awesome.java", fileName, 1, message, ruleName, classification);
		expected.setClassification("This is not a rule");
		CheckStyleWarning actual = new CheckStyleWarning(filePath, fileName, 1, message, ruleName, classification);
		assertNotEquals(expected, actual);
	}

	/**
	 * Test equal method where one object has PMDWarning, the other one has
	 * CheckStyleWarning.
	 */
	@Test
	public void testEqualsFalsePMDandCheckStyle() {
		PMDWarning expected = new PMDWarning(filePath, fileName, 1, "", "", "", ruleName, classification);
		CheckStyleWarning actual = new CheckStyleWarning(filePath, fileName, 1, "lalala", ruleName, classification);
		assertNotEquals(expected, actual);
	}

	/**
	 * Test the change of the message of a CheckStyle warning.
	 */
	@Test
	public void testChangeOfMessage() {
		String expected = "cool";
		CheckStyleWarning cw = new CheckStyleWarning(filePath, fileName, 1, "test", ruleName, classification);
		String actual = cw.getMessage();
		cw.setMessage(expected);
		assertNotEquals(expected, actual);
	}

	/**
	 * Test the change of the line of a CheckStyle warning.
	 */
	@Test
	public void testChangeOfLine() {
		int expected = 5;
		CheckStyleWarning cw = new CheckStyleWarning(filePath, fileName, 1, message, ruleName, classification);
		int actual = cw.getLine();
		cw.setLine(expected);
		assertNotSame(expected, actual);
	}

	/**
	 * Test equals between an Integer object and a CheckStyleWarning.
	 */
	@Test
	public void testEqualsFalseWithIntegerObject() {
		CheckStyleWarning cw = new CheckStyleWarning(filePath, fileName, 1, message, ruleName, classification);
		boolean actual = cw.equals(Integer.valueOf(1));
		assertSame(false, actual);
	}

	/**
	 * Test equals between two CheckStyleWarning with different rule and
	 * message.
	 */
	@Test
	public void testEqualsFalseWithDifferentRuleAndMessage() {
		CheckStyleWarning cw = new CheckStyleWarning(filePath, fileName, 1, "random", ruleName, classification);
		CheckStyleWarning diff = new CheckStyleWarning(filePath, fileName, 1, message, "unknown rule", classification);
		assertNotEquals(cw, diff);
	}
	
	/**
	 * Test equals between two CheckStyleWarning with different rule and
	 * and line.
	 */
	@Test
	public void testEqualsFalseWithDifferentRuleAndLine() {
		CheckStyleWarning cw = new CheckStyleWarning(filePath, fileName, 1, message, ruleName, classification);
		CheckStyleWarning diff = new CheckStyleWarning(filePath, fileName, 5, message, "test", classification);
		assertNotEquals(cw, diff);
	}
	
	/**
	 * Test equals between two CheckStyleWarning with different rule and
	 * and line.
	 */
	@Test
	public void testEqualsFalseWithDifferentMessageAndRule() {
		CheckStyleWarning cw = new CheckStyleWarning(filePath, fileName, 1, message, "rule", classification);
		CheckStyleWarning diff = new CheckStyleWarning(filePath, fileName, 1, "hey", ruleName, classification);
		assertNotEquals(cw, diff);
	}

}
