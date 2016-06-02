package BlueTurtle.parsers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import BlueTurtle.finders.ProjectInfoFinder;
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
	private static String srcDir = System.getProperty("user.dir") + "/src";

	private static String testSet2FileName = "Warning.java";
	private static String testSet2Message = "Unused @param tag for 'filePath'.";
	private static String testSet2RuleName = "JavadocMethod";
	private static String testSet2Classification = "Documentation Conventions";
	private static String testSet3FilePath = System.getProperty("user.dir") + File.separatorChar + "src"
			+ File.separatorChar + "main" + File.separatorChar + "java" + File.separatorChar + "BlueTurtle"
			+ File.separatorChar + "warnings" + File.separatorChar + "Warning.java";

	/**
	 * Set up the GDP parser, parse the category information.
	 * 
	 * @throws IOException
	 *             throws an exception if a problem is ecountered while reading
	 *             the files.
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
		ProjectInfoFinder.getClassPackage().clear();
		ProjectInfoFinder.getClassPaths().clear();
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

		CheckStyleWarning expected = new CheckStyleWarning(testSet3FilePath, testSet2FileName, 20, testSet2Message,
				testSet2RuleName, testSet2Classification);

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

		CheckStyleWarning expected = new CheckStyleWarning(testSet3FilePath, testSet2FileName, 21, testSet2Message,
				testSet2RuleName, testSet2Classification);

		CheckStyleWarning actual = (CheckStyleWarning) parser.parseFile(testSet2).get(0);

		assertNotEquals(expected, actual);
	}

//	/**
//	 * Test that the parser parse the wrong file.
//	 */
//	@Test
//	public void testParseTheWrongFile() {
//		XMLParser parser = new CheckStyleXMLParser();
//
//		String testSet3 = "/ex.xml";
//
//		List<Warning> warnings = parser.parseFile(testSet3);
//
//		assertSame(0, warnings.size());
//	}

}
