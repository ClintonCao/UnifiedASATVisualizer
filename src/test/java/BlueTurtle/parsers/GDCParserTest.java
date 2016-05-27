package BlueTurtle.parsers;

import java.util.HashMap;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test class for GDCParser.
 * 
 * @author BlueTurtle.
 *
 */
public class GDCParserTest {

	private static String testSet = "./resources/htmlExample.html";
	private static String testSet2 = "./resources/asat-gdc-mapping.html";


	/**
	 * Test that the parser can parse a valid GDC html file.
	 */
	@Test
	public void testParseCorrectBehaviour() {
		GDCParser parser = new GDCParser();

		HashMap<String, String> categoryInfo = parser.parseFile(testSet);

		assertTrue(categoryInfo.containsValue("Naming Conventions"));
	}
	
	/**
	 * Test that the parser created the right amount of warnings.
	 */
	@Test
	public void testCreateRightAmountOfWarnings() {
		GDCParser parser = new GDCParser();

		HashMap<String, String> categoryInfo = parser.parseFile(testSet);

		assertSame(2, categoryInfo.size());
	}	

	/**
	 * Test that the parser parse the wrong file.
	 */
	@Test
	public void testParseTheWrongFile() {
		GDCParser parser = new GDCParser();
		
		String testSet3 = "./resources/ex.xml";

		HashMap<String, String> warnings = parser.parseFile(testSet3);
		
		assertNotSame(6, warnings.size());
	}

}
