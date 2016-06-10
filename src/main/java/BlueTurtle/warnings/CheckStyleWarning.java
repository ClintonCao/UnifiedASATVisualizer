package BlueTurtle.warnings;

/**
 * This class is used to represent a checkstyle warning.
 * 
 * @author BlueTurtle.
 *
 */
public class CheckStyleWarning extends Warning {


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
	 * 
	 * @param ruleName
	 *            the rule name of the warning.
	 * @param classification
	 *            of the violated rule of the warning.
	 */
	public CheckStyleWarning(String filePath, String filename, int line, String message, String ruleName, String classification) {
		super(filePath, filename, line, "CheckStyle", ruleName, message, classification);
		setMessage(message.replaceAll("'", ""));
	}

	/**
	 * Check whether two CheckStyle warnings are the same.
	 * 
	 * @param other
	 *            the other warning.
	 * @return a boolean
	 */
	@Override
	public boolean equals(Object other) {

		if (!(other instanceof CheckStyleWarning)) {
			return false;
		}

		CheckStyleWarning that = (CheckStyleWarning) other;

		// fixed SimplifyBooleanReturn, Conditional logic can be removed.
		return (ruleName.equals(that.ruleName) && filePath.equals(that.filePath) && fileName.equals(that.fileName) && line == that.line
				&& message.equals(that.message) && classification.equals(that.classification) && type.equals(that.type));

	}

	/**
	 * HashCode for the CheckStyle Warning.
	 */
	@Override
	public int hashCode() {
		return java.util.Objects.hash(filePath, fileName, type, line, message, ruleName, classification);
	}
	
	/**
	 * toString method for CheckStylWarning.
	 */
	@Override
	public String toString() {
		return "CheckStyleWarning [line=" + line + ", message=" + message + ", classification=" + classification
				+ ", fileName=" + fileName + ", type=" + type + ", filePath=" + filePath + ", ruleName=" + ruleName
				+ "]";
	}
	
}
