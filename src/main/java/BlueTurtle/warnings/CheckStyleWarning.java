package BlueTurtle.warnings;

/**
 * This class is used to represent a checkstyle warning.
 * 
 * @author BlueTurtle.
 *
 */
public class CheckStyleWarning extends Warning {

	private int line;
	private String message;

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
	 * @param Classification
	 *            of the violated rule of the warning.
	 */
	public CheckStyleWarning(String filePath, String filename, int line, String message, String ruleName, String classification) {
		super(filePath, filename, "CheckStyle", ruleName, classification);
		setLine(line);
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
		if (filePath.equals(that.filePath) && fileName.equals(that.fileName) && line == that.line
				&& message.equals(that.message) && classification.equals(that.classification) && type.equals(that.type)) {
			return true;
		} else {
			return false;
		}

	}

	
	/*************************************/
	/*** Getters and setters ************/
	/***********************************/

	/**
	 * Get the line number where the warning is located.
	 * 
	 * @return the line number where the warning is located.
	 */
	public int getLine() {
		return line;
	}

	/**
	 * Set line number where the warning is located.
	 * 
	 * @param line
	 *            the line number where the warning is located.
	 */
	public void setLine(int line) {
		this.line = line;
	}

	/**
	 * Get the message of the CheckStyle warning.
	 * 
	 * @return the message of the CheckStyle warning.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Set the message of the CheckStyle warning.
	 * 
	 * @param message
	 *            the message of the warning (i.e. description of what caused
	 *            the warning.)
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
