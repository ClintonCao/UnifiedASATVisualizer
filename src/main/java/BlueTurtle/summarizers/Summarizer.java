package BlueTurtle.summarizers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import BlueTurtle.warnings.Warning;
import lombok.Getter;
import lombok.Setter;

/**
 * This class can be used to summarize warnings.
 * 
 * @author BlueTurtle.
 *
 */
@SuppressWarnings("checkstyle:visibilitymodifier")
public abstract class Summarizer {

	@Getter @Setter protected String packageName;
	@Getter @Setter protected int numberOfWarnings;
	@Getter @Setter protected Set<String> warningTypes;
	@Getter @Setter protected int numberOfCheckStyleWarnings;
	@Getter @Setter protected int numberOfPMDWarnings;
	@Getter @Setter protected int numberOfFindBugsWarnings;

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
}
