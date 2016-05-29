package BlueTurtle.writers;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import BlueTurtle.finders.ProjectInfoFinder;
import BlueTurtle.groupers.WarningGrouper;
import BlueTurtle.groupers.WarningGrouper.Criteria;
import BlueTurtle.summarizers.Summarizer;
import BlueTurtle.warnings.CheckStyleWarning;
import BlueTurtle.warnings.Warning;

/**
 * Tests for the JsonWriter class.
 * 
 * @author BlueTurtle.
 *
 */
public class JSWriterTest {

	private String outputPath;
	private List<Summarizer> summarizedWarnings;
	private JSWriter jsWriter = JSWriter.getInstance();

	/**
	 * Intitialize the things that are needed.
	 * 
	 * @throws IOException
	 *             throws an exception if problem is encountered while reading
	 *             the files.
	 */
	@Before
	public void initialize() throws IOException {
		ProjectInfoFinder pif = new ProjectInfoFinder();
		pif.findFiles(new File(System.getProperty("user.dir") + "/src/test/resources"));
		outputPath = "./src/test/resources/testOutput.js";
		List<Warning> list = new ArrayList<Warning>();
		String filePath = ProjectInfoFinder.getClassPaths().stream()
				.filter(path -> path.endsWith("src\\test\\resources\\ExampleClass.java")).findFirst().get();
		list.add(new CheckStyleWarning(filePath, "ExampleClass.java", 5, "test", "test", "Class"));
		WarningGrouper wg = new WarningGrouper(list);
		summarizedWarnings = wg.groupBy(Criteria.PACKAGES);
		jsWriter.setSummarizedWarnings(summarizedWarnings);

		// make sure that the file does not already exist by coincidence.
		File file = new File(outputPath);
		if (file.exists()) {
			file.delete();
		}

	}

	/**
	 * Cleanup the files that were created and also the attributes of
	 * ProjectInfoFinder.
	 */
	@After
	public void cleanup() {
		File file = new File(outputPath);
		file.delete();
		ProjectInfoFinder.getClassLocs().clear();
		ProjectInfoFinder.getClassPaths().clear();
		ProjectInfoFinder.getClassPackage().clear();
		ProjectInfoFinder.getPackages().clear();
	}

	/**
	 * Test writing summarized warnings to a file.
	 * 
	 * @throws IOException
	 *             throws an exception if something has happened when writing to
	 *             a file.
	 */
	@Test
	public void testWriteToJsonFormat() throws IOException {
		jsWriter.writeToJSFormat(outputPath);

		File file = new File(outputPath);
		boolean fileWritten = file.exists();
		assertTrue(fileWritten);
	}

	/**
	 * Test changing the summarizedWarnings.
	 */
	@Test
	public void testChangingSummarizedWarnings() {
		jsWriter.setSummarizedWarnings(new ArrayList<Summarizer>());
		assertSame(0, jsWriter.getSummarizedWarnings().size());
	}

}
