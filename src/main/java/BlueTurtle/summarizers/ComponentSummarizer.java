package BlueTurtle.summarizers;

import java.util.ArrayList;
import java.util.List;

import BlueTurtle.finders.ProjectInfoFinder;
import BlueTurtle.gui.GUIController.ASAT;
import BlueTurtle.warnings.Warning;
import lombok.Getter;

/**
 * This class can be used to summarise the warnings for a specific component.
 * 
 * @author BlueTurtle.
 *
 */
public class ComponentSummarizer extends Summarizer {

	@Getter private String fileName;
	@Getter private String filePath;
	@Getter private List<Warning> warningList;
	@Getter private int loc;

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
		this.fileName = fileName;
		this.filePath = filePath;
		this.loc = ProjectInfoFinder.getClassLocs().get(filePath);
		this.warningList = new ArrayList<Warning>();
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

			String pn = ProjectInfoFinder.getClassPackage().get(w.getFilePath());
			if (w.getFileName().equals(getFileName()) && pn.equals(getPackageName())) {
				String warningType = w.getType();
				if (!warningTypes.contains(warningType)) {
					warningTypes.add(w.getType());
				}
				warningList.add(w);
				incrementNumberOfWarnings(ASAT.valueOf(warningType));
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
	
	/**
	 * HashCode for ComponentSummarizer.
	 */
	@Override
	public int hashCode() {
		return java.util.Objects.hash(packageName, fileName, filePath, warningList, numberOfWarnings, warningTypes);
	}

	@Override
	public String toString() {
		return "ComponentSummarizer [fileName=" + fileName + ", filePath=" + filePath + ", warningList=" + warningList
				+ ", loc=" + loc + ", packageName=" + packageName + ", numberOfWarnings=" + numberOfWarnings
				+ ", warningTypes=" + warningTypes + ", numberOfCheckStyleWarnings=" + numberOfCheckStyleWarnings
				+ ", numberOfPMDWarnings=" + numberOfPMDWarnings + ", numberOfFindBugsWarnings="
				+ numberOfFindBugsWarnings + "]";
	}
	
}
