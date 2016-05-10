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
	protected int numberOfCheckStyleWarnings;
	protected int numberOfPMDWarnings;
	protected int numberOfFindBugsWarnings;

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
		setNumberOfCheckStyleWarnings(0);
		setNumberOfPMDWarnings(0);
		setNumberOfFindBugsWarnings(0);
	}

	/**
	 * Increment the number of warnings.
	 * 
	 * @param type
	 *            the type of the warning.
	 */
	public void incrementNumberOfWarnings(String type) {
		switch (type) {
		case "CheckStyle":
			numberOfCheckStyleWarnings++;
			numberOfWarnings++;
			break;
		case "PMD":
			numberOfPMDWarnings++;
			numberOfWarnings++;
			break;
		case "FindBugs":
			numberOfFindBugsWarnings++;
			numberOfWarnings++;
			break;
		default:
			throw new IllegalArgumentException("This is not a right type of warning");
		}
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
	 * Get the number of warnings.
	 * 
	 * @return the number of warnings.
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
	 * Get the number of CheckStyle warnings.
	 * 
	 * @return the number of CheckStyle Warnings.
	 */
	public int getNumberOfCheckStyleWarnings() {
		return numberOfCheckStyleWarnings;
	}

	/**
	 * Set the number of CheckStyle warnings.
	 * 
	 * @param numberOfCheckStyleWarnings
	 *            the number of CheckStyle warnings.
	 */
	public void setNumberOfCheckStyleWarnings(int numberOfCheckStyleWarnings) {
		this.numberOfCheckStyleWarnings = numberOfCheckStyleWarnings;
	}

	/**
	 * Get the number of PMD warnings.
	 * 
	 * @return the number of PMD warnings.
	 */
	public int getNumberOfPMDWarnings() {
		return numberOfPMDWarnings;
	}

	/**
	 * Set the number of PMD warnings.
	 * 
	 * @param numberOfPMDWarnings
	 *            the number of PMD warnings.
	 */
	public void setNumberOfPMDWarnings(int numberOfPMDWarnings) {
		this.numberOfPMDWarnings = numberOfPMDWarnings;
	}

	/**
	 * Get the number of FindBugs warnings.
	 * 
	 * @return the number of FindBugs warnings.
	 */
	public int getNumberOfFindBugsWarnings() {
		return numberOfFindBugsWarnings;
	}

	/**
	 * Set the number of FindBugs warnings.
	 * 
	 * @param numberOfFindBugsWarnings
	 *            the number of FindBugs warnings.
	 */
	public void setNumberOfFindBugsWarnings(int numberOfFindBugsWarnings) {
		this.numberOfFindBugsWarnings = numberOfFindBugsWarnings;
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
