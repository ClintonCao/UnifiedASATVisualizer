package BlueTurtle.parsers;

import java.util.HashMap;
import java.util.List;

import BlueTurtle.interfaces.Parser;
import BlueTurtle.warnings.Warning;

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
	 */
	public abstract HashMap<String, String> parseFile(String filePath);
}
