package BlueTurtle.uav;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

/**
 * Test CodeFile class.
 * @author michiel
 *
 */
public class CodeFileTest {
	private CodeFile codeFile;
	private File testFile = new File("./src/test/resources/CodeFileTestFile.txt");
	
	/**
	 * Setup method, initialize CodeFile.
	 * @throws IOException
	 * 				if file not found, inaccessible, etc.
	 */
	@Before
	public void setUp() throws IOException {
		codeFile = new CodeFile();
	}
	
	/**
	 * Test if the getCodeFromFile method actually reads and stores code from a file.
	 * @throws IOException
	 * 				if file not found, inaccessible, etc.
	 */
	@Test
	public void testGetCodeFromFile() throws IOException {
		codeFile.getCodeFromFile(testFile);
		assertTrue(codeFile.getCode().length() > 0);
	}
}
