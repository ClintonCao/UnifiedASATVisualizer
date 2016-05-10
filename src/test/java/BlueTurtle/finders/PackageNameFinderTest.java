package BlueTurtle.finders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

/**
 * Test for PackageNameFinder class.
 * @author BlueTurtle.
 *
 */
public class PackageNameFinderTest {

	/**
	 * Test that the right package name is found from the file.
	 */
	@Test
	public void testRightPackageNameFound() {
		String packageName  = PackageNameFinder.findPackageName("./src/test/resources/ExampleClass.txt");
		assertEquals("SomePackage.subpackage", packageName);
	}
	
	/**
	 * Test finding the package name in another file.
	 */
	@Test
	public void testFindingPackageNameOnDifferentFile() {
		String packageName  = PackageNameFinder.findPackageName("./src/test/resources/ExampleTestClass.txt");
		assertNotEquals("SomePackage.subpackage", packageName);
	}
	
	/**
	 * Testing class that is in the default package.
	 */
	@Test
	public void testClassInDefaultPackage() {
		String packageName  = PackageNameFinder.findPackageName("./src/test/resources/DefaultClass.txt");
		assertEquals("default", packageName);
	}
	

}
