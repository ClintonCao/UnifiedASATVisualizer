package BlueTurtle.parsers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import BlueTurtle.finders.ProjectInfoFinder;
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
	private static String srcDir = System.getProperty("user.dir") + "/src";

	private static String testSetFileName = "CheckStyleWarning.java";
	private static String testSetRuleName = "OverrideBothEqualsAndHashcode";
	private static String testSetPackageName = "BlueTurtle.warnings";
	private static String testSetRuleSet = "Basic";
	private static String testSetMethod = "equals";
	private static String testSetClassification = "Interface";
	private static String testSet3FilePath = System.getProperty("user.dir") + File.separatorChar + "src"
			+ File.separatorChar + "main" + File.separatorChar + "java" + File.separatorChar + "BlueTurtle"
			+ File.separatorChar + "warnings" + File.separatorChar + "CheckStyleWarning.java";

	/**
	 * Set up the GDP parser, parse the category information.
	 * 
	 * @throws IOException
	 *             throws an exception if there was a problem found while
	 *             reading the file.
	 */
	@Before
	public void setUp() throws IOException {
		new ProjectInfoFinder().findFiles(new File(srcDir));
	}

	/**
	 * Clean up the attributes of ProjectInfoFinder.
	 */
	@After
	public void cleanUp() {
		ProjectInfoFinder.getClassLocs().clear();
		ProjectInfoFinder.getPackages().clear();
		ProjectInfoFinder.getClassPaths().clear();
		ProjectInfoFinder.getClassPackage().clear();
	}

	/**
	 * Test that the parser can parse a valid PMD output file.
	 */
	@Test
	public void testParseCorrectBehaviour() {
		XMLParser parser = new PMDXMLParser();

		List<Warning> warnings = parser.parseFile(testSet);

		assertSame(1, warnings.size());
	}

	// The PMD parser need to be fixed, the rule name need to be combined with
	// ruleset and rulename, basic.xml/OverrideBoth.
	/**
	 * Test whether the parser creates the right object.
	 */
	@Test
	public void testParsingOneWarning() {
		XMLParser parser = new PMDXMLParser();

		PMDWarning expected = new PMDWarning(testSet3FilePath, testSetFileName, 43, testSetPackageName, testSetRuleSet,
				testSetMethod, testSetRuleName, testSetClassification);

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

	/**
	 * Test that the parser parse the wrong file.
	 */
	@Test
	public void testParseTheWrongFile() {
		XMLParser parser = new PMDXMLParser();

		String testSet3 = "/ex.xml";

		List<Warning> warnings = parser.parseFile(testSet3);

		assertSame(0, warnings.size());
	}

	/**
	 * Test that the XML parser classify method.
	 */
	@Test
	public void testXMLParserClassify() {
		String classification = XMLParser.classify("AbstractClassName");

		assertEquals("Naming Conventions", classification);
	}

}
