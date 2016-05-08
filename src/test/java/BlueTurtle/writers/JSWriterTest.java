package BlueTurtle.writers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import BlueTurtle.summarizers.Summarizer;

/**
 * Test class for JSWriter class.
 * 
 * @author BlueTurtle.
 *
 */
public class JSWriterTest {
	
	private String outputPath;

	/**
	 * Make sure that there's not a file already written in the folder by
	 * coincidence.
	 */
	@Before
	public void initialize() {
		outputPath = "./resources/testExample.js";
		File file = new File(outputPath);
		if (file.exists()) {
			file.delete();
		}
		
	}
	
	/**
	 * Clean up the files that were created for testing.
	 */
	@After
	public void cleanup() {
		File file = new File(outputPath);
		file.delete();
	}

	/**
	 * Test that a file is written.
	 * 
	 * @throws IOException
	 *             throws an exception if problem is encountered while writing.
	 */
	@Test
	public void testFileIsWritten() throws IOException {
		JSWriter writer = new JSWriter(new ArrayList<Summarizer>());
		writer.writePackageToJS(outputPath);
		File file = new File(outputPath);
		assertTrue(file.exists());
	}
	
	/**
	 * Test the checkForComma method that should return true.
	 */
	@Test
	public void testCheckForCommaTrue() {
		JSWriter writer = new JSWriter(new ArrayList<Summarizer>());
		assertTrue(writer.checkCommaNeeded(3, 4));
	}
	
	/**
	 * Test the checkForComma method that should return false.
	 */
	@Test
	public void testCheckForCommaFalse() {
		JSWriter writer = new JSWriter(new ArrayList<Summarizer>());
		assertFalse(writer.checkCommaNeeded(3, 3));
	}

}
