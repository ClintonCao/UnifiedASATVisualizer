package BlueTurtle.interfaces;

import java.util.List;

import BlueTurtle.warnings.Warning;

/**
 * Interface for a parser. The parser has only one method, which is to parse a
 * file.
 * 
 * @author BlueTurtle.
 *
 */
public interface Parser {

	/**
	 * Parse the file.
	 * 
	 * @param filePath
	 *            the location of the file.
	 *            
	 * @return a list containing warning objects.
	 */
	List<Warning> parseFile(String filePath);

}
