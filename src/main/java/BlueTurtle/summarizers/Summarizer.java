package BlueTurtle.summarizers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import BlueTurtle.gui.GUIController.ASAT;
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
	public void incrementNumberOfWarnings(ASAT type) {
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
	
}
