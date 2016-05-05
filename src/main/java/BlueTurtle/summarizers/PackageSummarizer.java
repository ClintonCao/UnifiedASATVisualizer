package BlueTurtle.summarizers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import BlueTurtle.finders.PackageNameFinder;
import BlueTurtle.warnings.Warning;

/**
 * This class summarises the warnings of a specific package.
 * 
 * @author BlueTurtle.
 *
 */
public class PackageSummarizer extends Summarizer {

	private List<ComponentSummarizer> classes;

	/**
	 * Contructor.
	 * 
	 * @param packageName
	 *            the name of the package.
	 */
	public PackageSummarizer(String packageName) {
		super(packageName);
		setClasses(new ArrayList<ComponentSummarizer>());
	}

	/**
	 * Summarise the warnings for this package.
	 * 
	 * @param warnings
	 *            list of warnings
	 */
	@Override
	public void summarise(List<Warning> warnings) {
		HashMap<String, String> classesInfo = findOwnClasses(warnings);

		for (Entry<String, String> ci : classesInfo.entrySet()) {
			ComponentSummarizer cs = new ComponentSummarizer(ci.getKey(), ci.getValue(), packageName);
			cs.summarise(warnings);
			classes.add(cs);
			warningTypes.addAll(cs.getWarningTypes());
			numberOfWarnings += cs.getNumberOfWarnings();
		}
	}

	/**
	 * Find the classes that belongs to this package.
	 * 
	 * @param warnings
	 *            the list of warnings.
	 * @return a HashMap containing the classes and their file path that is from
	 *         this package.
	 */
	public HashMap<String, String> findOwnClasses(List<Warning> warnings) {
		HashMap<String, String> ownClasses = new HashMap<String, String>();

		for (Warning w : warnings) {
			String fileName = w.getFileName();
			String filePath = w.getFilePath();

			if (PackageNameFinder.findPackageName(filePath).equals(packageName) && !ownClasses.containsKey(fileName)) {
				ownClasses.put(fileName, filePath);
			}

		}

		return ownClasses;
	}

	/**
	 * Check whether two PackageSummarizer are equal.
	 * 
	 * @param other
	 *            the other PacakgeSummarizer.
	 */
	@Override
	public boolean equals(Object other) {

		if (!(other instanceof PackageSummarizer)) {
			return false;
		}

		PackageSummarizer that = (PackageSummarizer) other;

		if (packageName.equals(that.packageName) && numberOfWarnings == that.numberOfWarnings
				&& classes.equals(that.classes) && warningTypes.equals(that.warningTypes)) {
			return true;
		} else {
			return false;
		}

	}

	/**************************************/
	/****** Getters and Setters **********/
	/************************************/

	/**
	 * Get the list of classes(and their warnings) in this package.
	 * 
	 * @return list of classes(and their warnings) in this package.
	 */
	public List<ComponentSummarizer> getClasses() {
		return classes;
	}

	/**
	 * Set the list of ComponentSummarizers in this package.
	 * 
	 * @param cs
	 *            list of ComponentSummarizers.
	 */
	public void setClasses(List<ComponentSummarizer> cs) {
		this.classes = cs;
	}


}
