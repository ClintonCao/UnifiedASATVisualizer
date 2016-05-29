package BlueTurtle.finders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.IOException;

import org.junit.Test;

/**
 * Test for PackageNameFinder class.
 * 
 * @author BlueTurtle.
 *
 */
public class PackageNameFinderTest {

	/**
	 * Test that the right package name is found from the file.
	 * 
	 * @throws IOException
	 *             throws an exception if problem is encountered while reading
	 *             the file.
	 */
	@Test
	public void testRightPackageNameFound() throws IOException {
		String packageName = PackageNameFinder.findPackageName("./src/test/resources/ExampleClass.java");
		assertEquals("SomePackage.subpackage", packageName);
	}

	/**
	 * Test finding the package name in another file.
	 * 
	 * @throws IOException
	 *             throws an exception if problem is encountered while reading
	 *             the file.
	 */
	@Test
	public void testFindingPackageNameOnDifferentFile() throws IOException {
		String packageName = PackageNameFinder.findPackageName("./src/test/resources/ExampleTestClass.java");
		assertNotEquals("SomePackage.subpackage", packageName);
	}

	/**
	 * Testing class that is in the default package.
	 * 
	 * @throws IOException
	 *             throws an exception if problem is encountered while reading
	 *             the file.
	 */
	@Test
	public void testClassInDefaultPackage() throws IOException {
		String packageName = PackageNameFinder.findPackageName("./src/test/resources/DefaultClass.java");
		assertEquals("default", packageName);
	}

	/**
	 * Test finding package name in a non-existing file.
	 * 
	 * @throws IOException
	 *             throws an exception if problem is encountered while reading
	 *             the file.
	 */
	@Test(expected = IOException.class)
	public void testFindingNameInNonExistingFile() throws IOException {
		String packageName = PackageNameFinder.findPackageName("./NonExstingFile.java");
	}

}
