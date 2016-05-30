package BlueTurtle.computers;

import static org.junit.Assert.assertSame;

import java.io.IOException;

import org.junit.Test;

/**
 * Test for LOCFinder class.
 * 
 * @author BlueTurtle.
 *
 */
public class LOCComputerTest {

	/**
	 * Test that the right right number of lines is found from the file.
	 * 
	 * @throws IOException
	 *             throws an exception if problem is encountered while reading
	 *             the file.
	 * 
	 */
	@Test
	public void testRightLOCFound() throws IOException {
		int numLines = LOCComputer.getInstance().computeLOC("./src/test/resources/DefaultClass.java");
		assertSame(5, numLines);
	}

	/**
	 * Test finding the right number of lines is found in another file.
	 * 
	 * @throws IOException
	 *             throws an exception if problem is encountered while reading
	 *             the file.
	 */
	@Test
	public void testFindingLOCOnDifferentFile() throws IOException {
		int numLines = LOCComputer.getInstance().computeLOC("./src/test/resources/ExampleTestClass.java");
		assertSame(6, numLines);
	}

	/**
	 * Test finding LOC in a non existing file should return zero.
	 * 
	 * @throws IOException
	 *             throws an exception if problem is encountered while reading
	 *             the file.
	 */
	@Test(expected = IOException.class)
	public void testFindingLOCInNonExistingFile() throws IOException {
		int zero = LOCComputer.getInstance().computeLOC("./NonExistingClass.java");
		assertSame(0, zero);
	}
}
