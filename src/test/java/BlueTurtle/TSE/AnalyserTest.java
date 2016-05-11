package BlueTurtle.TSE;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

/**
 * Unit test for simple Analyser.
 */
public class AnalyserTest {

	/**
	 * @throws IOException 
	 */
	@Test
	public void testCheckStyleOutput() throws IOException {
		JavaController javacontroller = new JavaController();
		javacontroller.execute();
		BufferedReader br = new BufferedReader(new FileReader(JavaController.getUserDir() + "/Runnables/Testcode/checkstyle.xml"));
		assert(br.readLine() != null);
	}
	

}
