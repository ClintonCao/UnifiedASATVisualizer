package BlueTurtle.parsers;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import BlueTurtle.warnings.PMDWarning;
import BlueTurtle.warnings.Warning;

/**
 * Test class for PPMDXMLParser.
 * 
 * @author BlueTurtle.
 *
 */
public class PMDXMLParserTest {

	private static String testSet = "./resources/examplePmd1.xml";
	private static String testSet2 = "./resources/examplePmd2.xml";
	private static String testSet2FilePath = "C:\\Users\\wangs\\Documents\\GitHub\\Contextproject-TSE\\src\\main\\java\\BlueTurtle\\warnings\\CheckStyleWarning.java";
	private static String testSet2FileName = "CheckStyleWarning.java";
	private static String testSet2RuleName = "OverrideBothEqualsAndHashcode";
	private static String testSet2PackageName = "BlueTurtle.warnings";
	private static String testSet2RuleSet = "Basic";
	private static String testSet2Method = "equals";


	/**
	 * Test that the parser can parse a valid PMD output file.
	 */
	@Test
	public void testParseCorrectBehaviour() {
		XMLParser parser = new PMDXMLParser();

		List<Warning> warnings = parser.parseFile(testSet);

		assertSame(1, warnings.size());
	}

	/**
	 * Test whether the parser creates the right object.
	 */
	@Test
	public void testParsingOneWarning() {
		XMLParser parser = new PMDXMLParser();

		PMDWarning expected = new PMDWarning(testSet2FilePath, testSet2FileName, 43,
				testSet2PackageName, testSet2RuleSet, testSet2Method, testSet2RuleName);

		PMDWarning actual = (PMDWarning) parser.parseFile(testSet2).get(0);

		assertEquals(expected, actual);
	}

	/**
	 * Test that the parser created the right amount of warnings.
	 */
	@Test
	public void testCreateRightAmountOfWarnings() {
		XMLParser parser = new PMDXMLParser();

		List<Warning> warnings = parser.parseFile(testSet);

		assertNotSame(6, warnings.size());
	}



}
