package BlueTurtle.parsers;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

import BlueTurtle.warnings.PMDWarning;
import BlueTurtle.warnings.Warning;

/**
 * Test class for PMDXMLParser.
 * 
 * @author BlueTurtle.
 *
 */
public class PMDXMLParserTest {

	private static String testSet = "./src/test/resources/examplePmd1.xml";
	private static String testSet2 = "./src/test/resources/examplePmd2.xml";
	private static String testSet3 = "./src/test/resources/asat-gdc-mapping.html";

	private static String testSet2FilePath = "C:\\Users\\wangs\\Documents\\GitHub\\Contextproject-TSE\\src\\main\\java\\BlueTurtle\\warnings\\CheckStyleWarning.java";
	private static String testSet2FileName = "CheckStyleWarning.java";
	private static String testSet2RuleName = "OverrideBothEqualsAndHashcode";
	private static String testSet2PackageName = "BlueTurtle.warnings";
	private static String testSet2RuleSet = "Basic";
	private static String testSet2Method = "equals";
	private static String testSet2Classification = "Interface";


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
	 * Test that the parser can parse a valid PMD output file.
	 */
	@Test
	public void testParseCorrectBehaviour() {
		XMLParser parser = new PMDXMLParser();

		List<Warning> warnings = parser.parseFile(testSet, categoryInfo);

		assertSame(1, warnings.size());
	}

	// The PMD parser need to be fixed, the rule name need to be combined with ruleset and rulename, basic.xml/OverrideBoth.
	/**
	 * Test whether the parser creates the right object.
	 */
	@Test
	public void testParsingOneWarning() {
		XMLParser parser = new PMDXMLParser();

		PMDWarning expected = new PMDWarning(testSet2FilePath, testSet2FileName, 43,
				testSet2PackageName, testSet2RuleSet, testSet2Method, testSet2RuleName, testSet2Classification);

		PMDWarning actual = (PMDWarning) parser.parseFile(testSet2, categoryInfo).get(0);

		assertEquals(expected, actual);
	}

	/**
	 * Test that the parser created the right amount of warnings.
	 */
	@Test
	public void testCreateRightAmountOfWarnings() {
		XMLParser parser = new PMDXMLParser();

		List<Warning> warnings = parser.parseFile(testSet, categoryInfo);

		assertNotSame(6, warnings.size());
	}

	/**
	 * Test that the parser parse the wrong file.
	 */
	@Test
	public void testParseTheWrongFile() {
		XMLParser parser = new PMDXMLParser();
		
		String testSet3 = "/ex.xml";

		List<Warning> warnings = parser.parseFile(testSet3, categoryInfo);
		
		assertSame(0, warnings.size());
	}
	
	/**
	 * Test that the XML parser classify method.
	 */
	@Test
	public void testXMLParserClassify() {				
		String classification = XMLParser.classify("AbstractClassName", categoryInfo);
		
		assertEquals("Naming Conventions", classification);
	}	

}
