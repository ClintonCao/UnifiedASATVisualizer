package BlueTurtle.summarizers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import BlueTurtle.parsers.GDCParser;
import BlueTurtle.warnings.CheckStyleWarning;
import BlueTurtle.warnings.Warning;

/**
 * Test for the ComponentSummarizer class.
 * 
 * @author BlueTurtle.
 *
 */
public class CategorySummarizerTest {

	private String filePath;
	private String fileName;
	private String packageName;
	private List<Warning> warningList;
	private Warning w;
	private List<Warning> warningList2;
	private Warning w2;
	private String testSet;
	private String testSet3;
	private HashMap<String, String> categoryInfo;
	private HashMap<String, String> categoryInfo2;
	
	/**
	 * set up the necessary objects.
	 */
	@Before
	public void setUp() {
		filePath = "./src/test/resources/ExampleClass.txt";
		fileName = "ExampleClass.java";
		packageName = "SomePackage.subpackage";
		testSet = "./src/test/resources/htmlExample.html";
		testSet3 = "./src/test/resources/asat-gdc-mapping.html";
		w = new CheckStyleWarning(filePath, fileName, 3, "Test", "AbstractClassName", "Naming Conventions");
		w2 = new CheckStyleWarning("./src/test/resources/ExampleTestClass.txt", fileName, 3, "Test", "TestRule", "Class");
		warningList = new ArrayList<Warning>();
		warningList2 = new ArrayList<Warning>();
		warningList2.add(w2);
		warningList.add(w);
		GDCParser parser = new GDCParser();
     	categoryInfo = parser.parseFile(testSet);
     	categoryInfo2 = parser.parseFile(testSet3);
	}

	/**
	 * Test that the number of warnings is zero when the object is just created.
	 */
	@Test
	public void testNumWarningsIsZero() {
		CategorySummarizer cs = new CategorySummarizer("Naming Conventions", packageName, categoryInfo);
		assertSame(0, cs.numberOfWarnings);
	}
	
	/**
	 * Test the warnings list.
	 */
	@Test
	public void testWarningList() {
		CategorySummarizer cs = new CategorySummarizer("Naming Conventions", packageName, categoryInfo);
		cs.summarise(warningList);
		assertEquals(warningList, cs.getWarningList());
	}
	
	/**
	 * Test the category info.
	 */
	@Test
	public void testCategoryInfo() {
		CategorySummarizer cs = new CategorySummarizer("Naming Conventions", packageName, categoryInfo);
		assertEquals(categoryInfo, cs.getCategoryInfo());
	}


	/**
	 * Test that the number of warnings is not zero after summarise has been
	 * called.
	 */
	@Test
	public void testNumWarningsIsNotZeroAfterSummarise() {
		CategorySummarizer cs = new CategorySummarizer("Naming Conventions", packageName, categoryInfo);
		cs.summarise(warningList);
		assertSame(1, cs.numberOfWarnings);
	}

	/**
	 * Test equals between two category summarizer. They have different category.
	 * 
	 */
	@Test
	public void testEqualsWithDifferentCategory() {
		CategorySummarizer cs = new CategorySummarizer("Naming Conventions", packageName, categoryInfo);
		CategorySummarizer cs2 = new CategorySummarizer("Document Conventions", packageName, categoryInfo);
		assertNotEquals(cs, cs2);
	}

	/**
	 * Test equals between two category summarizer. They have different category information.
	 * 
	 */
	@Test
	public void testEqualsWithDifferentCategoryInfo() {
		CategorySummarizer cs = new CategorySummarizer("Naming Conventions", packageName, categoryInfo);
		CategorySummarizer cs2 = new CategorySummarizer("Naming Conventions", packageName, categoryInfo2);
		assertNotEquals(cs, cs2);
	}

	/**
	 * Test equals between two component summarizer. Both do summarizes the
	 * warnings.
	 */
	@Test
	public void testEqualsTrueAfterSummarise() {
		CategorySummarizer cs = new CategorySummarizer("Naming Conventions", packageName, categoryInfo);
		CategorySummarizer cs2 = new CategorySummarizer("Naming Conventions", packageName, categoryInfo);
		cs.summarise(warningList);
		cs2.summarise(warningList);
		assertEquals(cs, cs2);
	}
	
	/**
	 * Test objects that are the same should return same hashCode.
	 */
	@Test
	public void testSameHashCode() {
		CategorySummarizer cs = new CategorySummarizer("Naming Conventions", packageName, categoryInfo);
		CategorySummarizer cs2 = new CategorySummarizer("Naming Conventions", packageName, categoryInfo);
		assertEquals(cs.hashCode(), cs2.hashCode());
	}

	/**
	 * Test equals between two component summarizer. Only one summarizes the
	 * warnings.
	 */
	@Test
	public void testEqualsFalseAfterWithOneSummarise() {
		CategorySummarizer cs = new CategorySummarizer("Naming Conventions", packageName, categoryInfo);
		CategorySummarizer cs2 = new CategorySummarizer("Naming Conventions", packageName, categoryInfo);
		cs.summarise(warningList);
		assertNotEquals(cs, cs2);
	}


	/**
	 * Test equal with different object.
	 */
	@Test
	public void testEqualsFalseWithDiffernetObject() {
		CategorySummarizer cs = new CategorySummarizer("Naming Conventions", packageName, categoryInfo);
		assertNotEquals(cs, Integer.valueOf(1));
	}


}
