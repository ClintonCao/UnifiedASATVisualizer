package BlueTurtle.finders;

import static org.junit.Assert.assertSame;

import org.junit.Test;

/**
 * Test for LOCFinder class.
 * 
 * @author BlueTurtle.
 *
 */
public class LOCFinderTest {

	/**
	 * Test that the right right number of lines is found from the file.
	 * 
	 */
	@Test
	public void testRightLOCFound() {
		int numLines = LOCFinder.findLOC("./src/test/resources/DefaultClass.txt");
		assertSame(5, numLines);
	}

	/**
	 * Test finding the right number of lines is found in another file.
	 */
	@Test
	public void testFindingLOCOnDifferentFile() {
		int numLines = LOCFinder.findLOC("./src/test/resources/ExampleTestClass.txt");
		assertSame(6, numLines);
	}

	/**
	 * Test finding LOC in a non existing file should return zero.
	 */
	@Test
	public void testFindingLOCInNonExistingFile() {
		int zero = LOCFinder.findLOC("./NonExistingClass.java");
		assertSame(0, zero);
	}
}
