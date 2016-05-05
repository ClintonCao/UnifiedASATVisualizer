package BlueTurtle.summarizers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import BlueTurtle.warnings.Warning;

/**
 * This class can be used to summarize warnings.
 * 
 * @author BlueTurtle.
 *
 */
@SuppressWarnings("checkstyle:visibilitymodifier")
public abstract class Summarizer {

	protected String packageName;
	protected int numberOfWarnings;
	protected Set<String> warningTypes;

	/**
	 * Constructor.
	 * 
	 * @param packageName
	 *            the name of the package that the summarizer is working on.
	 */
	public Summarizer(String packageName) {
		setPackageName(packageName);
		setWarningTypes(new HashSet<String>());
		setNumberOfWarnings(0);
	}

	/**
	 * Summarise the result of the warnings.
	 * 
	 * @param warnings
	 *            the list of warnings to be summarized.
	 */
	public abstract void summarise(List<Warning> warnings);

	/**
	 * Check whether two summarizer are the same.
	 * 
	 * @param other
	 *            the other summarizer.
	 */
	public abstract boolean equals(Object other);

	/**************************************/
	/****** Getters and Setters **********/
	/************************************/

	/**
	 * Get the name of the package.
	 * 
	 * @return the name of the package.
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * Set the name of the package.
	 * 
	 * @param packageName
	 *            the name of the package.
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	/**
	 * Get the number of warning that this package has.
	 * 
	 * @return the number of warnings of this package.
	 */
	public int getNumberOfWarnings() {
		return numberOfWarnings;
	}

	/**
	 * Set the number of warnings.
	 * 
	 * @param numberOfWarnings
	 *            the number of warnings.
	 */
	public void setNumberOfWarnings(int numberOfWarnings) {
		this.numberOfWarnings = numberOfWarnings;
	}

	/**
	 * Get the types of warning for this summarizer.
	 * 
	 * @return types of warning.
	 */
	public Set<String> getWarningTypes() {
		return warningTypes;
	}

	/**
	 * Set the types of warning for this summarizer.
	 * 
	 * @param warningTypes
	 *            list of warning types.
	 */
	public void setWarningTypes(HashSet<String> warningTypes) {
		this.warningTypes = warningTypes;
	}

}
