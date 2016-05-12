package BlueTurtle.parsers;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

import BlueTurtle.warnings.FindBugsWarning;
import BlueTurtle.warnings.Warning;

/**
 * Test class for FindBugsXMLParser.
 * 
 * @author BlueTurtle.
 *
 */
public class FindBugsXMLParserTest {

	private static String testSet = "./src/test/resources/exampleFindbugs2.xml";
	private static String testSet2 = "./src/test/resources/exampleFindbugs1.xml";
	private static String testSet3 = "./src/test/resources/asat-gdc-mapping.html";

	private static String testSet2FileName = "FindBugsWarning.java";
	private static String testSet2RuleName = "HE_EQUALS_USE_HASHCODE";
	private static String testSet2Message = "BlueTurtle.warnings.FindBugsWarning defines equals and uses Object.hashCode()";
	private static String testSet2Category = "BAD_PRACTICE";
	private static String testSet2Priority = "High";
	private static String testSet2Classification = "Interface";
	private static String testSet3FilePath = System.getProperty("user.dir") + "\\BlueTurtle\\warnings\\FindBugsWarning.java";

	private static HashMap<String, String> categoryInfo = new HashMap<String,String>();



	@Before
	public void setUp() {
		GDCParser gP = new GDCParser();
		categoryInfo = gP.parseFile(testSet3);
	}
	
	/**
	 * Test that the parser can parse a valid FindBugs output file.
	 */
	@Test
	public void testParseCorrectBehaviour() {
		FindBugsXMLParser parser = new FindBugsXMLParser();

		List<Warning> warnings = parser.parseFile(testSet2, categoryInfo);

		assertSame(2, warnings.size());
	}

	/**
	 * Test whether the parser creates the right object.
	 */
	@Test
	public void testParsingOneWarning() {
		FindBugsXMLParser parser = new FindBugsXMLParser();

		FindBugsWarning expected = new FindBugsWarning(testSet3FilePath, testSet2FileName, 47,
				testSet2Message, testSet2Category, testSet2Priority, testSet2RuleName, testSet2Classification);

		FindBugsWarning actual = (FindBugsWarning) parser.parseFile(testSet2,categoryInfo).get(0);
		
		System.out.println(expected.getFileName() + " |  | " + actual.getFileName());
		System.out.println(expected.getCategory() + " |  | " + actual.getCategory());
		System.out.println(expected.getFilePath() + " |  | " + actual.getFilePath());
		System.out.println(expected.getLine() + " |  | " + actual.getLine());
		System.out.println(expected.getMessage() + " |  | " + actual.getMessage());
		System.out.println(expected.getPriority() + " |  | " + actual.getPriority());
		System.out.println(expected.getRuleName() + " |  | " + actual.getRuleName());
		System.out.println(expected.getType() + " |  | " + actual.getType());
		System.out.println(expected.getClassification() + " |  | " + actual.getClassification());
		System.out.println(""+ expected.equals(actual));

		assertEquals(expected, actual);
	}

	/**
	 * Test that the parser created the right amount of warnings.
	 */
	@Test
	public void testCreateRightAmountOfWarnings() {
		FindBugsXMLParser parser = new FindBugsXMLParser();

		List<Warning> warnings = parser.parseFile(testSet, categoryInfo);

		assertNotSame(6, warnings.size());
	}

	/**
	 * Test that the parser parse the wrong file.
	 */
	@Test
	public void testParseTheWrongFile() {
		FindBugsXMLParser parser = new FindBugsXMLParser();
		
		String testSet3 = "/ex.xml";

		List<Warning> warnings = parser.parseFile(testSet3, categoryInfo);
		
		assertSame(0, warnings.size());
	}

}
