package BlueTurtle.warnings;

import lombok.Getter;
import lombok.Setter;

/**
 * This class is used to represent a FindBugs warning.
 * 
 * @author BlueTurtle.
 *
 */
public class FindBugsWarning extends Warning {

	@Getter @Setter private int lineNumber;
	@Getter @Setter private String message;
	@Getter @Setter private String category;
	@Getter @Setter private String priority;
	@Getter @Setter private String absoluteFilePath;

	
	/**
	 * Constructor.
	 * 
	 * @param filePath
	 *            the path to the file where the warning is located.
	 * @param filename
	 *            the name of the file where the warning is located.
	 * @param line
	 *            the line number where the warning is located.
	 * @param message
	 *            the message of the warning.
	 * @param category
	 *            the category of the warning.
	 * @param priority
	 *            the priority of the warning.            
	 * @param ruleName
	 *            the rule name of the warning.
	 * @param classification
	 *            of the violated rule of the warning.
	 */
	public FindBugsWarning(String filePath, String filename, int line, String message, String category, String priority, String ruleName, String classification) {
		super(filePath, filename, "FindBugs", ruleName, classification);
		setLineNumber(line);
		setMessage(message);
		setCategory(category);
		setPriority(priority);
	}
	

	/**
	 * Check whether two FindBugs warnings are the same.
	 * 
	 * @param other
	 *            the other warning.
	 * @return a boolean
	 */
	@Override
	public boolean equals(Object other) {

		if (!(other instanceof FindBugsWarning)) {
			return false;
		}

		FindBugsWarning that = (FindBugsWarning) other;

		return (filePath.equals(that.filePath) && fileName.equals(that.fileName) && lineNumber == that.lineNumber
				&& message.equals(that.message) && category.equals(that.category) && classification.equals(that.classification)
				&& priority.equals(that.priority) && type.equals(that.type) && ruleName.equals(that.ruleName));
	}
	
	/**
	 * HashCode for the FindBugsWarning.
	 */
	@Override
	public int hashCode() {
		return java.util.Objects.hash(filePath, fileName, type, lineNumber, message, category, priority, ruleName, classification);
	}
}
