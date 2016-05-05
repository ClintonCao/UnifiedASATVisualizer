package BlueTurtle.writers;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import BlueTurtle.groupers.WarningGrouper;
import BlueTurtle.summarizers.Summarizer;
import BlueTurtle.warnings.CheckStyleWarning;
import BlueTurtle.warnings.Warning;

/**
 * Tests for the JsonWriter class.
 * 
 * @author BlueTurtle.
 *
 */
public class JsonWriterTest {

	private String outputPath;
	private List<Summarizer> summarizedWarnings;

	/**
	 * Intitialize the things that are needed.
	 */
	@Before
	public void initialize() {
		outputPath = "./resources/testOutput.json";
		HashMap<String, String> componentsInfo = new HashMap<String, String>();
		componentsInfo.put("ExampleClass.java", "./resources/ExampleClass.txt");
		Set<String> packagesNames = new HashSet<String>();
		packagesNames.add("SomePackage.subpackage");
		List<Warning> list = new ArrayList<Warning>();
		list.add(new CheckStyleWarning("./resources/ExampleClass.txt", "ExampleClass.java", 5, "test", "test"));
		WarningGrouper wg = new WarningGrouper(componentsInfo, packagesNames, list);
		summarizedWarnings = wg.groupBy("packages");
		
		// make sure that the file does not already exist by coincidence.
		File file = new File(outputPath);
		if (file.exists()) {
			file.delete();
		}
		
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
		JsonWriter jwriter = new JsonWriter(summarizedWarnings);
		jwriter.writeToJsonFormat(outputPath);

		File file = new File(outputPath);
		boolean fileWritten = file.exists();
		assertTrue(fileWritten);
	}

}
