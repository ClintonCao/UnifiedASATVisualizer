package BlueTurtle.computers;

import static org.junit.Assert.assertSame;

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
	 */
	@Test
	public void testRightLOCFound() {
		int numLines = LOCComputer.computeLOC("./src/test/resources/DefaultClass.txt");
		assertSame(5, numLines);
	}

	/**
	 * Test finding the right number of lines is found in another file.
	 */
	@Test
	public void testFindingLOCOnDifferentFile() {
		int numLines = LOCComputer.computeLOC("./src/test/resources/ExampleTestClass.txt");
		assertSame(6, numLines);
	}

	/**
	 * Test finding LOC in a non existing file should return zero.
	 */
	@Test
	public void testFindingLOCInNonExistingFile() {
		int zero = LOCComputer.computeLOC("./NonExistingClass.java");
		assertSame(0, zero);
	}
}
