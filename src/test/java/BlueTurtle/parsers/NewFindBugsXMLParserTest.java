package BlueTurtle.parsers;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

import BlueTurtle.finders.ProjectInfoFinder;
import BlueTurtle.warnings.FindBugsWarning;
import BlueTurtle.warnings.Warning;

/**
 * Test class for NewFindBugsXMLParser.
 * 
 * @author BlueTurtle.
 *
 */
public class NewFindBugsXMLParserTest {

	private static String testSet = "./src/test/resources/exampleFindbugs2.xml";
	private static String testSet2 = "./src/test/resources/exampleFindbugs1.xml";
	private static String testSet3 = "./src/test/resources/asat-gdc-mapping.html";
	private static String srcDir = System.getProperty("user.dir") + "/src";

	private static String testSet2FileName = "FindBugsWarning.java";
	private static String testSet2RuleName = "HE_EQUALS_USE_HASHCODE";
	private static String testSet2Message = "BlueTurtle.warnings.FindBugsWarning defines equals and uses Object.hashCode()";
	private static String testSet2Category = "BAD_PRACTICE";
	private static String testSet2Priority = "High";
	private static String testSet2Classification = "Interface";
	private static String testSet3FilePath = System.getProperty("user.dir") + File.separatorChar + "src" + File.separatorChar + "main" + File.separatorChar + "java" + File.separatorChar + "BlueTurtle" + File.separatorChar + "warnings"+ File.separatorChar + "FindBugsWarning.java";

	private static HashMap<String, String> categoryInfo = new HashMap<String, String>();

	/**
	 * Set up the GDP parser, parse the category information.
	 */
	@Before
	public void setUp() {
		GDCParser gP = new GDCParser();
		categoryInfo = gP.parseFile(testSet3);
		ProjectInfoFinder pif = new ProjectInfoFinder();
		pif.findFiles(new File(srcDir));
	}
	
	/**
	 * Test that the parser can parse a valid FindBugs output file.
	 */
	@Test
	public void testParseCorrectBehaviour() {
		NewFindBugsXMLParser parser = new NewFindBugsXMLParser();

		List<Warning> warnings = parser.parseFile(testSet2, categoryInfo);

		assertSame(2, warnings.size());
	}

	/**
	 * Test whether the parser creates the right object.
	 */
	@Test
	public void testParsingOneWarning() {
		NewFindBugsXMLParser parser = new NewFindBugsXMLParser();

		FindBugsWarning expected = new FindBugsWarning(testSet3FilePath, testSet2FileName, 47,
				testSet2Message, testSet2Category, testSet2Priority, testSet2RuleName, testSet2Classification);

		FindBugsWarning actual = (FindBugsWarning) parser.parseFile(testSet2, categoryInfo).get(0);

		assertEquals(expected.getFilePath(), actual.getFilePath());
	}

	/**
	 * Test that the parser created the right amount of warnings.
	 */
	@Test
	public void testCreateRightAmountOfWarnings() {
		NewFindBugsXMLParser parser = new NewFindBugsXMLParser();

		List<Warning> warnings = parser.parseFile(testSet, categoryInfo);

		assertNotSame(6, warnings.size());
	}

	/**
	 * Test that the parser parse the wrong file.
	 */
	@Test
	public void testParseTheWrongFile() {
		NewFindBugsXMLParser parser = new NewFindBugsXMLParser();
		
		String testSet3 = "/ex.xml";

		List<Warning> warnings = parser.parseFile(testSet3, categoryInfo);
		
		assertSame(0, warnings.size());
	}

}
