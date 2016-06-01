package BlueTurtle.summarizers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import BlueTurtle.finders.ProjectInfoFinder;
import BlueTurtle.gui.GUIController.ASAT;
import BlueTurtle.warnings.CheckStyleWarning;
import BlueTurtle.warnings.FindBugsWarning;
import BlueTurtle.warnings.PMDWarning;
import BlueTurtle.warnings.Warning;

/**
 * Test for the ComponentSummarizer class.
 * 
 * @author BlueTurtle.
 *
 */
public class ComponentSummarizerTest {

	private String filePath;
	private String filePath2;
	private String fileName;
	private String packageName;
	private List<Warning> warningList;
	private Warning w;
	private List<Warning> warningList2;
	private Warning w2;

	/**
	 * Initialize necessary objects.
	 * 
	 * @throws IOException
	 *             throws an exception if problem is encountered while reading
	 *             the file.
	 */
	@Before
	public void initialize() throws IOException {
		ProjectInfoFinder pif = new ProjectInfoFinder();
		pif.findFiles(new File(System.getProperty("user.dir") + "/src/test/resources"));
		filePath = ProjectInfoFinder.getClassPaths().stream().filter(path -> path.endsWith(
				"src" + File.separator + "test" + File.separator + "resources" + File.separator + "ExampleClass.java"))
				.findFirst().get();
		filePath2 = ProjectInfoFinder.getClassPaths().stream().filter(path -> path.endsWith("src" + File.separator
				+ "test" + File.separator + "resources" + File.separator + "ExampleTestClass.java")).findFirst().get();
		fileName = "ExampleClass.java";
		packageName = "SomePackage.subpackage";
		w = new CheckStyleWarning(filePath, fileName, 3, "Test", "TestRule", "Class");
		w2 = new CheckStyleWarning(filePath2, fileName, 3, "Test", "TestRule", "Class");
		warningList = new ArrayList<Warning>();
		warningList2 = new ArrayList<Warning>();
		warningList2.add(w2);
		warningList.add(w);
	}

	/**
	 * Clean up the attributes of ProjectInfoFinder.
	 */
	@After
	public void clearAttributes() {
		ProjectInfoFinder.getClassLocs().clear();
		ProjectInfoFinder.getClassPaths().clear();
		ProjectInfoFinder.getClassPackage().clear();
		ProjectInfoFinder.getPackages().clear();
	}

	/**
	 * Test that the number of warnings is zero when the object is just created.
	 */
	@Test
	public void testNumWarningsIsZero() {
		ComponentSummarizer cs = new ComponentSummarizer(fileName, filePath, packageName);
		assertSame(0, cs.numberOfWarnings);
	}

	/**
	 * Test the file path of the summarizer.
	 */
	@Test
	public void testFilePath() {
		ComponentSummarizer cs = new ComponentSummarizer(fileName, filePath, packageName);
		assertSame(filePath, cs.getFilePath());
	}

	/**
	 * Test that the number of warnings is not zero after summarise has been
	 * called.
	 */
	@Test
	public void testNumWarningsIsNotZeroAfterSummarise() {
		ComponentSummarizer cs = new ComponentSummarizer(fileName, filePath, packageName);
		cs.summarise(warningList);
		assertSame(cs.getWarningList().size(), cs.numberOfWarnings);
	}

	/**
	 * Test equals between two component summarizer. They have different file
	 * name and file path.
	 */
	@Test
	public void testEqualsWithDifferentNameAndPath() {
		ComponentSummarizer cs = new ComponentSummarizer(fileName, filePath, packageName);
		ComponentSummarizer cs2 = new ComponentSummarizer("ExampleTestClass.java", filePath2, packageName);
		assertNotEquals(cs, cs2);
	}

	/**
	 * Test equals between two component summarizer. They have different file
	 * name.
	 */
	@Test
	public void testEqualsWithDifferentName() {
		ComponentSummarizer cs = new ComponentSummarizer(fileName, filePath, packageName);
		ComponentSummarizer cs2 = new ComponentSummarizer("Not same name", filePath, packageName);
		assertNotEquals(cs, cs2);
	}

	/**
	 * Test equals between two component summarizer. They have different file
	 * file path.
	 */
	@Test
	public void testEqualsWithDifferentPath() {
		ComponentSummarizer cs = new ComponentSummarizer(fileName, filePath, packageName);
		ComponentSummarizer cs2 = new ComponentSummarizer(fileName, filePath2, packageName);
		assertNotEquals(cs, cs2);
	}

	/**
	 * Test equals between two component summarizer. They have different file
	 * package name.
	 */
	@Test
	public void testEqualsWithDifferentPackageName() {
		ComponentSummarizer cs = new ComponentSummarizer(fileName, filePath, packageName);
		ComponentSummarizer cs2 = new ComponentSummarizer(fileName, filePath, "Not same package");
		assertNotEquals(cs, cs2);
	}

	/**
	 * Test equals between two component summarizer. Both do summarizes the
	 * warnings.
	 */
	@Test
	public void testEqualsTrueAfterSummarise() {
		ComponentSummarizer cs = new ComponentSummarizer(fileName, filePath, packageName);
		ComponentSummarizer cs2 = new ComponentSummarizer(fileName, filePath, packageName);
		cs.summarise(warningList);
		cs2.summarise(warningList);
		assertEquals(cs, cs2);
	}

	/**
	 * Test objects that are the same should return same hashCode.
	 */
	@Test
	public void testSameHashCode() {
		ComponentSummarizer cs = new ComponentSummarizer(fileName, filePath, packageName);
		ComponentSummarizer cs2 = new ComponentSummarizer(fileName, filePath, packageName);
		assertEquals(cs.hashCode(), cs2.hashCode());
	}

	/**
	 * Test equals between two component summarizer. Only one summarizes the
	 * warnings.
	 */
	@Test
	public void testEqualsFalseAfterWithOneSummarise() {
		ComponentSummarizer cs = new ComponentSummarizer(fileName, filePath, packageName);
		ComponentSummarizer cs2 = new ComponentSummarizer(fileName, filePath, packageName);
		cs.summarise(warningList);
		assertNotEquals(cs, cs2);
	}

	/**
	 * Test summarizing warnings from components with same name but from
	 * different package.
	 */
	@Test
	public void testSummarizingWarningDiffrentPackage() {
		ComponentSummarizer cs = new ComponentSummarizer(fileName, filePath, packageName);
		cs.summarise(warningList2);
		assertSame(0, cs.getNumberOfWarnings());
	}

	/**
	 * Test equal with different object.
	 */
	@Test
	public void testEqualsFalseWithDiffernetObject() {
		ComponentSummarizer cs = new ComponentSummarizer(fileName, filePath, packageName);
		assertNotEquals(cs, Integer.valueOf(1));
	}

	/**
	 * Test that the number of PMD warnings is not zero after summarise has been
	 * called.
	 */
	@Test
	public void testNumPMDWarningsIsNotZeroAfterSummarise() {
		ComponentSummarizer cs = new ComponentSummarizer(fileName, filePath, packageName);
		warningList.add(new PMDWarning(filePath, fileName, 3, packageName, "test", "test2", "test3", "Class"));
		cs.summarise(warningList);
		assertSame(1, cs.numberOfPMDWarnings);
	}

	/**
	 * Test that the number of FindBugs warnings is not zero after summarise has
	 * been called.
	 */
	@Test
	public void testNumFindBugsWarningsIsNotZeroAfterSummarise() {
		ComponentSummarizer cs = new ComponentSummarizer(fileName, filePath, packageName);
		warningList.add(new FindBugsWarning(filePath, fileName, 3, "testMessage", "test", "test2", "test3", "test4"));
		cs.summarise(warningList);
		assertSame(1, cs.numberOfFindBugsWarnings);
	}

	/**
	 * Test that a IllegalArgumentException is thrown for incrementNumWarnings.
	 * called.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testIllegalArgumentExceptionIncrementNumWarnings() {
		ComponentSummarizer cs = new ComponentSummarizer(fileName, filePath, packageName);
		warningList.add(new FindBugsWarning(filePath, fileName, 3, "testMessage", "test", "test2", "test3", "test4"));
		cs.summarise(warningList);
		cs.incrementNumberOfWarnings(ASAT.valueOf("Not a right type of ASAT"));
	}

	/**
	 * Test that the loc is not zero after summarise has been called.
	 */
	@Test
	public void testLOCIsNotZeroAfterSummarise() {
		ComponentSummarizer cs = new ComponentSummarizer(fileName, filePath, packageName);
		cs.summarise(warningList);
		assertNotSame(0, cs.getLoc());
	}
	
	/**
	 * Test two equal ComponentSummarizer return the same string.
	 */
	@Test
	public void testSameStringShouldBeReturned() {
		ComponentSummarizer cs = new ComponentSummarizer(fileName, filePath, packageName);
		ComponentSummarizer cs2 = new ComponentSummarizer(fileName, filePath, packageName);
		assertEquals(cs.toString(), cs2.toString());
	}

}
