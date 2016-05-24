package BlueTurtle.summarizers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import BlueTurtle.warnings.Warning;
import lombok.Getter;

/**
 * This class can be used to summarize warnings.
 * 
 * @author BlueTurtle.
 *
 */
@SuppressWarnings("checkstyle:visibilitymodifier")
public abstract class Summarizer {
	
	/**
	 * Enums to define type of warnings.
	 * @author BlueTurtle.
	 *
	 */
	enum ASATs {
		CheckStyle, PMD, FindBugs;
	}
	
	@Getter protected String packageName;
	@Getter protected int numberOfWarnings;
	@Getter protected Set<String> warningTypes;
	@Getter protected int numberOfCheckStyleWarnings;
	@Getter protected int numberOfPMDWarnings;
	@Getter protected int numberOfFindBugsWarnings;

	/**
	 * Constructor.
	 * 
	 * @param packageName
	 *            the name of the package that the summarizer is working on.
	 */
	public Summarizer(String packageName) {
		this.packageName = packageName;
		this.warningTypes = new HashSet<String>();
		this.numberOfWarnings = 0;
		this.numberOfCheckStyleWarnings = 0;
		this.numberOfPMDWarnings = 0;
		this.numberOfFindBugsWarnings = 0;
	}

	/**
	 * Increment the number of warnings.
	 * 
	 * @param type
	 *            the type of the warning.
	 */
	public void incrementNumberOfWarnings(ASATs type) {
		switch (type) {
		case CheckStyle:
			numberOfCheckStyleWarnings++;
			numberOfWarnings++;
			break;
		case PMD:
			numberOfPMDWarnings++;
			numberOfWarnings++;
			break;
		case FindBugs:
			numberOfFindBugsWarnings++;
			numberOfWarnings++;
			break;
		default:
			// Unreachable
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
	@Override
	public abstract boolean equals(Object other);
	
	/**
	 * HashCode for summarizer.
	 */
	@Override
	public abstract int hashCode();
	
}
