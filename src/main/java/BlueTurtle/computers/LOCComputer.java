package BlueTurtle.computers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class can be used to find the (physical) lines of code for a file.
 * 
 * @author BlueTurtle.
 *
 */
@SuppressWarnings("checkstyle:hideutilityclassconstructor")
public class LOCComputer {

	/**
	 * Find the number of (physical) lines for a source code file.
	 * 
	 * @param filePath
	 *            the path to the source code file.
	 * @return the number of (physical) lines.
	 * @throws IOException
	 *             throws an exception if problem is encountered while reading
	 *             the file.
	 */
	public static int computeLOC(String filePath) throws IOException {
		int numLines = 0;
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		String line = reader.readLine();
		while (line != null) {
			if (!line.isEmpty() && !line.startsWith("import")) {
				numLines++;
			}
			line = reader.readLine();
		}
		reader.close();
		return numLines;
	}
}
