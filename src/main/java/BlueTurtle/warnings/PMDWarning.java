package BlueTurtle.warnings;

import lombok.Getter;
import lombok.Setter;

/**
 * This class is used to represent a PMD warning.
 * 
 * @author BlueTurtle.
 *
 */
public class PMDWarning extends Warning {

	@Getter @Setter private int line;
	@Getter @Setter private String method;
	@Getter @Setter private String packageName;
	@Getter @Setter private String ruleSet;

	
	/**
	 * Constructor.
	 * 
	 * @param filePath
	 *            the path to the file where the warning is located.
	 * @param filename
	 *            the name of the file where the warning is located.
	 * @param line
	 *            the line number where the warning is located.
	 * @param packageName
	 *            the package of the warning.
	 * @param ruleName
	 *            the rule name of the warning.
	 * @param ruleSet
	 *            the ruleSet of the warning.
	 * @param method
	 *            the method of the warning.
	 * @param classification
	 *            of the violated rule of the warning.
	 */
	public PMDWarning(String filePath, String filename, int line, String packageName, String message, String ruleSet,  String ruleName, String classification) {
		super(filePath, filename, "PMD", ruleName, message, classification);
		setLine(line);
		setRuleSet(ruleSet);
		setPackageName(packageName);
		setMethod(method);
	}

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

		return (filePath.equals(that.filePath) && fileName.equals(that.fileName) && line == that.line
				&& classification.equals(that.classification) && packageName.equals(that.packageName) && type.equals(that.type) 
				&& ruleSet.equals(that.ruleSet) && method.equals(that.method) && ruleName.equals(that.ruleName));
	}
	
	/**
	 * HashCode for the CheckStyle Warning.
	 */
	@Override
	public int hashCode() {
		return java.util.Objects.hash(filePath, fileName, type, line, packageName, ruleSet, method, ruleName, classification);
	}

	/**
	 * toString method for PMDWarning.
	 */
	@Override
	public String toString() {
		return "PMDWarning [line=" + line + ", method=" + method + ", packageName=" + packageName + ", classification="
				+ classification + ", fileName=" + fileName + ", type=" + type + ", filePath=" + filePath
				+ ", ruleName=" + ruleName + ", ruleSet=" + ruleSet + "]";
	}
	
}
