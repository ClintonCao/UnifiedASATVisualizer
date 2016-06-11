package BlueTurtle.warnings;

import lombok.Getter;
import lombok.Setter;

/**
 * This class is used to represent a warning.
 * 
 * @author BlueTurtle.
 *
 */
@SuppressWarnings("checkstyle:visibilitymodifier")
public abstract class Warning {

	@Getter @Setter protected String classification;
	@Getter @Setter protected String fileName;
	@Getter @Setter protected String type;
	@Getter @Setter protected String filePath;
	@Getter @Setter protected String ruleName;
	@Getter @Setter protected String message;
	@Getter @Setter protected int line;


	/**
	 * Constructor.
	 * 
	 * @param filePath
	 *            the path to the file where the warning is located.
	 * @param filename
	 *            the name of the file where the warning is located.
	 * @param line
	 *            the line number where the warning is located.
	 * @param type
	 *            the type of the warning e.g. PMD.
	 * @param ruleName
	 *            the violated rule name of the warning.
	 * @param message
	 *            the message of the warning.
	 * @param classification
	 *            of the violated rule of the warning.
	 */
	public Warning(String filePath, String filename, int line, String type, String ruleName, String message, String classification) {
		setFileName(filename);
		setFilePath(filePath);
		setLine(line);
		setType(type);
		setRuleName(ruleName);
		setClassification(classification); 
		setMessage(message);
	}
	
}
