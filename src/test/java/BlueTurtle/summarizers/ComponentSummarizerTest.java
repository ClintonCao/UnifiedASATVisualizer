package BlueTurtle.summarizers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import BlueTurtle.warnings.CheckStyleWarning;
import BlueTurtle.warnings.Warning;

/**
 * Test for the ComponentSummarizer class.
 * 
 * @author BlueTurtle.
 *
 */
public class ComponentSummarizerTest {

	private String filePath;
	private String fileName;
	private String packageName;
	private List<Warning> warningList;
	private Warning w;
	private List<Warning> warningList2;
	private Warning w2;

	/**
	 * Initialize necessary objects.
	 */
	@Before
	public void initialize() {
		filePath = "./src/test/resources/ExampleClass.txt";
		fileName = "ExampleClass.java";
		packageName = "SomePackage.subpackage";
		w = new CheckStyleWarning(filePath, fileName, 3, "Test", "TestRule");
		w2 = new CheckStyleWarning("./src/test/resources/ExampleTestClass.txt", fileName, 3, "Test", "TestRule");
		warningList = new ArrayList<Warning>();
		warningList2 = new ArrayList<Warning>();
		warningList2.add(w2);
		warningList.add(w);
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
	 * Test simple path change.
	 */
	@Test
	public void testPathChange() {
		ComponentSummarizer cs = new ComponentSummarizer(fileName, filePath, packageName);
		cs.setFilePath("New path");
		assertEquals("New path", cs.getFilePath());
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
		ComponentSummarizer cs2 = new ComponentSummarizer("Not same name", "Not same path", packageName);
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
		ComponentSummarizer cs2 = new ComponentSummarizer(fileName, "Not same path", packageName);
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
	 * Test equal with differnt object.
	 */
	@Test
	public void testEqualsFalseWithDiffernetObject() {
		ComponentSummarizer cs = new ComponentSummarizer(fileName, filePath, packageName);
		assertNotEquals(cs, Integer.valueOf(1));
	}

}
