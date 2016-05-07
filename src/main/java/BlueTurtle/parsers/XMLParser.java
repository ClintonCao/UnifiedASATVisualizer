package BlueTurtle.parsers;

import java.util.List;

import BlueTurtle.interfaces.Parser;
import BlueTurtle.warnings.Warning;

/**
 * This class can be used to parse a XML file.
 * 
 * @author BlueTurtle.
 *
 */
public abstract class XMLParser implements Parser {

	/**
	 * Parse a XML file.
	 * 
	 * @param filePath
	 *            the path to the file that needs to be parsed.
	 */
	public abstract List<Warning> parseFile(String filePath);
}
