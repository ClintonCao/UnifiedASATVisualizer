package BlueTurtle.summarizers;

import java.util.ArrayList;
import java.util.List;

import BlueTurtle.groupers.WarningGrouper;
import BlueTurtle.warnings.Warning;

/**
 * This class can be used to summarise the warnings for a specific component.
 * 
 * @author BlueTurtle.
 *
 */
public class ComponentSummarizer extends Summarizer {

	private String fileName;
	private String filePath;
	private List<Warning> warningList;

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

			String pn = WarningGrouper.findPackageName(w.getFilePath());
			if (w.getFileName().equals(getFileName()) && pn.equals(getPackageName())) {

				if (!warningTypes.contains(w.getType())) {
					warningTypes.add(w.getType());
				}
				warningList.add(w);
				numberOfWarnings++;
			}

		}

	}

	/**************************************/
	/****** Getters and Setters **********/
	/************************************/

	/**
	 * Get the name of the component.
	 * 
	 * @return the name of the component.
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Set the name of the component.
	 * 
	 * @param fileName
	 *            the name of the component.
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Get the file path.
	 * 
	 * @return the file path.
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * Set the file path.
	 * 
	 * @param filePath
	 *            the path of the file.
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * Get the list of warning in this component.
	 * 
	 * @return list of warning in this component.
	 */
	public List<Warning> getWarningList() {
		return warningList;
	}

	/**
	 * Set the list of warnings in this component.
	 * 
	 * @param warnings
	 *            list of warning.
	 */
	public void setWarningList(List<Warning> warnings) {
		this.warningList = warnings;
	}

}
