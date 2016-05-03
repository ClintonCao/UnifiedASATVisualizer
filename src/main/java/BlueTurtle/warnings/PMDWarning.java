package BlueTurtle.warnings;

/**
 * This class is used to represent a PMD warning.
 * 
 * @author BlueTurtle.
 *
 */
public class PMDWarning extends Warning {

	private int line;
	/**
	 * Constructor.
	 * 
	 * @param filePath
	 *            the path to the file where the warning is located.
	 * @param filename
	 *            the name of the file where the warning is located.
	 * @param line
	 *            the line number where the warning is located.
	 * @param className
	 *            the class of the warning.
	 * @param packageName
	 *            the package of the warning.
	 * @param ruleName
	 *            the rule name of the warning.
	 * @param ruleSet
	 *            the ruleSet of the warning.
	 * @param method
	 *            the method of the warning.
	 */
	public PMDWarning(String filePath, String filename, int line, String packageName, String ruleSet, String method,  String ruleName) {
		super(filePath, filename, "PMD", ruleName);
		setLine(line);
		setPackageName(packageName);
		setRuleSet(ruleSet);
		setMethod(method);
	}
	
	private String ruleSet;
	private String method;
	private String packageName;

	/**
	 * Check whether two PMD warnings are the same.
	 * 
	 * @param other
	 *            the other warning.
	 * @return a boolean
	 */
	@Override
	public boolean equals(Object other) {

		if (!(other instanceof PMDWarning)) {
			return false;
		}

		PMDWarning that = (PMDWarning) other;
		if (filePath.equals(that.filePath) && fileName.equals(that.fileName) && line == that.line
				&& ruleName.equals(that.ruleName) && packageName.equals(that.packageName) && type.equals(that.type) 
				&& ruleSet.equals(that.ruleSet) && method.equals(that.method)) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Write the values of the attributes to a file (in JSON format).
	 * 
	 * @param outputFilePath
	 *            the path of the output file.
	 */
	@Override
	public void toJson(String outputFilePath) {
		// TODO Auto-generated method stub

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
	 * Set the package for the PMD warning.
	 * 
	 * @param packageName
	 *            the package of the warning
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	/**
	 * Get the package from the PMD warning.
	 * 
	 * @return the package from the PMD warning.
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * Get the ruleSet from the PMD warning.
	 * 
	 * @return the ruleSet from the PMD warning.
	 */
	public String getRuleSet() {
		return ruleSet;
	}
	
	/**
	 * Set the ruleSet for the PMD warning.
	 * 
	 * @param ruleSet
	 *            the ruleSet of the warning.
	 */
	public void setRuleSet(String ruleSet) {
		this.ruleSet = ruleSet;
	}	

	/**
	 * Get the method of the PMD warning.
	 * 
	 * @return the method of the PMD warning.
	 */
	public String getMethod() {
		return method;
	}
	
	/**
	 * Set the method for the PMD warning.
	 * 
	 * @param method
	 *            the method of the warning.
	 */
	public void setMethod(String method) {
		this.method = method;
	}	
	
}
