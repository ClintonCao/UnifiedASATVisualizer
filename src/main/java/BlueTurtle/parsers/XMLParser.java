package BlueTurtle.parsers;

import java.util.HashMap;
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
	 * @param categoryInfo
	 *            the category information from GDC.
	 * 
	 * @return a list of warnings.
	 */
	public abstract List<Warning> parseFile(String filePath, HashMap<String, String> categoryInfo);

	/**
	 * Classify the rule name to the correct classification according to
	 * categoryInfo.
	 * 
	 * @param ruleName
	 *            the violated rule name of the warning.
	 * 
	 * @param categoryInfo
	 *            the information from GDC.
	 * 
	 * @return the category of a warning(based on the GDC).
	 */
	public static String classify(String ruleName, HashMap<String, String> categoryInfo) {
		String classification = categoryInfo.get(ruleName);
		return classification;
	}
}
