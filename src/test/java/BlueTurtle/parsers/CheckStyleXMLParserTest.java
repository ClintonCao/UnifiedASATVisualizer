package BlueTurtle.parsers;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

import org.junit.Before;
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
	private static String testSet2Classification = "Documentation Conventions";
	private static String testSet3FilePath = System.getProperty("user.dir") + File.separatorChar + "src" + File.separatorChar + "main" + File.separatorChar + "java" + File.separatorChar + "BlueTurtle" + File.separatorChar + "warnings"+ File.separatorChar + "Warning.java";

	private static HashMap<String, String> categoryInfo = new HashMap<String, String>();

	/**
	 * Set up the GDP parser, parse the category information.
	 */
	@Before
	public void setUp() {
		GDCParser gP = GDCParser.getInstance();
		categoryInfo = gP.getCategoryInfo();
	}
	
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
				testSet2Message, testSet2RuleName, testSet2Classification);

		CheckStyleWarning actual = (CheckStyleWarning) parser.parseFile(testSet2).get(0);
		String catagory = actual.getClassification();
		System.out.println(catagory);

		assertEquals(expected.getFilePath(), actual.getFilePath());
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
				testSet2RuleName, testSet2Classification);

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
		
		assertSame(0, warnings.size());
	}
	

}
