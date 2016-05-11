package BlueTurtle.finders;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LOCFinder {

	/**
	 * Find the number of (physical) lines for a source code file.
	 * 
	 * @param filePath
	 *            the path to the source code file.
	 * @return the number of (physical) lines.
	 * @throws IOException
	 *             throws an exception if a problem is encountered when reading
	 *             the file.
	 */
	public static int findLOC(String filePath) throws IOException {
		int numLines = 0;
		BufferedReader reader = new BufferedReader(new FileReader(filePath));

		String line = reader.readLine();
		while (line != null) {
			if (!line.isEmpty()) {
				numLines++;
			}
			line = reader.readLine();
		}
		reader.close();

		return numLines;
	}
}
