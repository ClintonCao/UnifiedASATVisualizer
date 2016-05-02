package BlueTurtle.warnings;

import BlueTurtle.interfaces.Violation;

/**
 * This class is used to represent a warning.
 * 
 * @author BlueTurtle.
 *
 */
public abstract class Warning implements Violation {

	protected String ruleName;
	protected String fileName;
	protected String type;
	protected String filePath;

	/**
	 * Constructor.
	 * 
	 * @param filePath
	 *            the path to the file where the warning is located.
	 * @param filename
	 *            the name of the file where the warning is located.
	 * @param type
	 *            the type of the warning e.g. PMD.
	 * @param ruleName
	 *            the violated rule name of the warning.
	 */
	public Warning(String filePath, String filename, String type, String ruleName) {
		setFileName(filename);
		setFilePath(filePath);
		setType(type);
		setRuleName(ruleName); // Need the GDC to set the category.
	}
	
	/**
	 * Check whether two warnings are the same exact warning.
	 * @param other the other warning.
	 * @return a boolean.
	 */
	@Override
	public abstract boolean equals(Object other);

	/**
	 * Write the values of the attribute to a file, in JSON format.
	 * 
	 */
	public abstract void toJson(String outputFilePath);

	/*************************************/
	/*** Getters and setters ************/
	/***********************************/

	/**
	 * Get the violated rule name.
	 * 
	 * @return the name of the violated rule.
	 */
	public String getRuleName() {
		return ruleName;
	}

	/**
	 * Set the name of the violated rule.
	 * 
	 * @param ruleName
	 *            the of the violated rule.
	 */
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	/**
	 * Get the file name where the warning is from.
	 * 
	 * @return the file name where the warning is from.
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Set the file name where the warning is from.
	 * 
	 * @param fileName
	 *            the file name where the warning is from.
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Get the type of the warning.
	 * 
	 * @return the type of the warning.
	 */
	public String getType() {
		return type;
	}

	/**
	 * Set the type of the warning.
	 * 
	 * @param type
	 *            the type of the warning.
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Get the path to the file where the warning is located.
	 * 
	 * @return the path to the file where the warning is located.
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * Set the path to the file where the warning is located.
	 * 
	 * @param filePath
	 *            the path to the file where the warning is located.
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}
