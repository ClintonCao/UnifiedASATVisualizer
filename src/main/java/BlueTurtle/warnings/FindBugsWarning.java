package BlueTurtle.warnings;

/**
 * This class is used to represent a FindBugs warning.
 * 
 * @author BlueTurtle.
 *
 */
public class FindBugsWarning extends Warning {

	private int lineNumber;
	private String message;
	private String category;
	private String priority;
	
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
	 * @param Classification
	 *            of the violated rule of the warning.
	 */
	public FindBugsWarning(String filePath, String filename, int line, String message, String category, String priority, String ruleName, String classification) {
		super(filePath, filename, "FindBugs", ruleName, classification);
		setLine(line);
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
		if (filePath.equals(that.filePath) && fileName.equals(that.fileName) && lineNumber == that.lineNumber
				&& message.equals(that.message) && category.equals(that.category) && classification.equals(that.classification)
				&& priority.equals(that.priority) && type.equals(that.type)) {
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
		return lineNumber;
	}

	/**
	 * Set line number where the warning is located.
	 * 
	 * @param line
	 *            the line number where the warning is located.
	 */
	public void setLine(int line) {
		this.lineNumber = line;
	}

	/**
	 * Get the message of the FindBugs warning.
	 * 
	 * @return the message of the FindBugs warning.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Set the message of the FindBugs warning.
	 * 
	 * @param message
	 *            the message of the warning (i.e. description of what caused
	 *            the warning.)
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Get the category of the FindBugs warning.
	 * 
	 * @return the category of the FindBugs warning.
	 */
	public String getCategory() {
		return message;
	}

	/**
	 * Set the category of the FindBugs warning.
	 * 
	 * @param category
	 *            the category of the warning.
	 */
	public void setCategory(String category) {
		this.category = category;
	}	

	/**
	 * Get the priority of the FindBugs warning.
	 * 
	 * @return the priority of the FindBugs warning.
	 */
	public String getPriority() {
		return priority;
	}

	/**
	 * Set the priority of the FindBugs warning.
	 * 
	 * @param priority
	 *            the priority of the warning.
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}	
	
}
