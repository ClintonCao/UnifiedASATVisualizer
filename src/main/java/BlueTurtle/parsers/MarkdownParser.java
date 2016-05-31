package BlueTurtle.parsers;

import java.util.HashMap;

/**
 * This class can be used to parse a Markdown file.
 * 
 * @author BlueTurtle.
 *
 */
public abstract class MarkdownParser {

	/**
	 * Parse a Markdown file.
	 * 
	 * @param filePath
	 *            the path to the file that needs to be parsed.
	 * @return returns a HashMap.
	 */
	public abstract HashMap<String, String> parseFile(String filePath);
}
