package BlueTurtle.computers;

import java.io.BufferedReader;
import java.io.FileReader;

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
	 */
	public static int computeLOC(String filePath) {
		int numLines = 0;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			String line = reader.readLine();
			while (line != null) {
				if (!line.isEmpty() && !line.startsWith("import")) {
					numLines++;
				}
				
				line = reader.readLine();
			}

			reader.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return numLines;
	}
}
