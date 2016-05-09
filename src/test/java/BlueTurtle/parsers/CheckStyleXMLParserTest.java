package BlueTurtle.parsers;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import BlueTurtle.warnings.CheckStyleWarning;
import BlueTurtle.warnings.Warning;

/**
 * Test class for CheckStyleXMLParser.
 * 
 * @author BlueTurtle.
 *
 */
public class CheckStyleXMLParserTest {

	private static String testSet = "./src/test/resources/exampleCheckstyle1.xml";
	private static String testSet2 = "./src/test/resources/exampleCheckstyle2.xml";
	private static String testSet2FilePath = "C:\\Users\\Clinton\\Documents\\GitHub\\ContextProject-TSE\\src\\main\\java\\BlueTurtle\\warnings\\Warning.java";
	private static String testSet2FileName = "Warning.java";
	private static String testSet2Message = "Unused @param tag for 'filePath'.";
	private static String testSet2RuleName = "JavadocMethod";

	/**
	 * Test that the parser can parse a valid CheckStyle output file.
	 */
	@Test
	public void testParseCorrectBehaviour() {
		XMLParser parser = new CheckStyleXMLParser();

		List<Warning> warnings = parser.parseFile(testSet);

		assertSame(5, warnings.size());
	}

	/**
	 * Test whether the getRuleName method works as it should.
	 */
	@Test
	public void testGetRuleNameCorrectBehaviour() {
		String expected = "PackageName";
		String actual = CheckStyleXMLParser
				.getRuleName("com.puppycrawl.tools.checkstyle.checks.naming.PackageNameCheck");

		assertEquals(expected, actual);
	}

	/**
	 * Test whether the parser creates the right object.
	 */
	@Test
	public void testParsingOneWarning() {
		XMLParser parser = new CheckStyleXMLParser();

		CheckStyleWarning expected = new CheckStyleWarning(testSet2FilePath, testSet2FileName, 20,
				testSet2Message, testSet2RuleName);

		CheckStyleWarning actual = (CheckStyleWarning) parser.parseFile(testSet2).get(0);

		assertEquals(expected, actual);
	}

	/**
	 * Test that the parser created the right amount of warnings.
	 */
	@Test
	public void testCreateRightAmountOfWarnings() {
		XMLParser parser = new CheckStyleXMLParser();

		List<Warning> warnings = parser.parseFile(testSet);

		assertNotSame(6, warnings.size());
	}

	/**
	 * Test whether the method work for another rule name.
	 */
	@Test
	public void testGetRuleNameCorrectBehaviour2() {
		String expected = "PackageName";
		String actual = CheckStyleXMLParser
				.getRuleName("com.puppycrawl.tools.checkstyle.checks.naming.JavadocMethodCheck");

		assertNotEquals(expected, actual);
	}

	/**
	 * Test whether the parser creates the right object.
	 */
	@Test
	public void testParsingOneWarning2() {
		XMLParser parser = new CheckStyleXMLParser();

		CheckStyleWarning expected = new CheckStyleWarning(testSet2FilePath, testSet2FileName, 21, testSet2Message,
				testSet2RuleName);

		CheckStyleWarning actual = (CheckStyleWarning) parser.parseFile(testSet2).get(0);

		assertNotEquals(expected, actual);
	}
	
	/**
	 * Test that the parser parse the wrong file.
	 */
	@Test
	public void testParseTheWrongFile() {
		XMLParser parser = new CheckStyleXMLParser();
		
		String testSet3 = "/ex.xml";

		List<Warning> warnings = parser.parseFile(testSet3);
		
		assertNotSame(6, warnings.toString());
	}
	

}
