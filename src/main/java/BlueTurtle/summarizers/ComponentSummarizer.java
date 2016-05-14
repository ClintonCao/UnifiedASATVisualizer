package BlueTurtle.summarizers;

import java.util.ArrayList;
import java.util.List;

import BlueTurtle.finders.LOCFinder;
import BlueTurtle.finders.PackageNameFinder;
import BlueTurtle.warnings.Warning;
import lombok.Getter;
import lombok.Setter;

/**
 * This class can be used to summarise the warnings for a specific component.
 * 
 * @author BlueTurtle.
 *
 */
public class ComponentSummarizer extends Summarizer {

	@Getter @Setter private String fileName;
	@Getter @Setter private String filePath;
	@Getter @Setter private List<Warning> warningList;
	@Getter @Setter private int loc;

	/**
	 * Constructor.
	 * 
	 * @param fileName
	 *            the name of the component.
	 * @param filePath
	 *            the path to the component.
	 * @param packageName
	 *            the name of the package where the component is from.
	 */
	public ComponentSummarizer(String fileName, String filePath, String packageName) {
		super(packageName);
		setFileName(fileName);
		setFilePath(filePath);
		int loc = LOCFinder.findLOC(filePath);
		setLoc(loc);
		setWarningList(new ArrayList<Warning>());
	}

	/**
	 * Summarise the warnings.
	 * 
	 * @param warnings
	 *            the list of warnings to be summarized.
	 */
	@Override
	public void summarise(List<Warning> warnings) {
		for (Warning w : warnings) {

			String pn = PackageNameFinder.findPackageName(w.getFilePath());
			if (w.getFileName().equals(getFileName()) && pn.equals(getPackageName())) {
				String warningType = w.getType();

				if (!warningTypes.contains(warningType)) {
					warningTypes.add(w.getType());
				}
				warningList.add(w);
				incrementNumberOfWarnings(warningType);
			}

		}

	}

	/**
	 * Check whether two ComponentSummarizer are equal.
	 * 
	 * @param other
	 *            the other ComponentSummarizer.
	 */
	@Override
	public boolean equals(Object other) {

		if (!(other instanceof ComponentSummarizer)) {
			return false;
		}

		ComponentSummarizer that = (ComponentSummarizer) other;

		return (packageName.equals(that.packageName) && fileName.equals(that.fileName) && filePath.equals(that.filePath)
				&& warningList.equals(that.warningList) && numberOfWarnings == that.numberOfWarnings
				&& warningTypes.equals(that.warningTypes));

	}
}
