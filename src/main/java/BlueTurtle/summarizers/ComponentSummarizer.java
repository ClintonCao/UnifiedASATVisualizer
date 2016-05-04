package BlueTurtle.summarizers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import BlueTurtle.interfaces.Summarizer;
import BlueTurtle.warnings.Warning;

/**
 * This class can be used to summarize the warnings for a specific component.
 * 
 * @author BlueTurtle.
 *
 */
public class ComponentSummarizer implements Summarizer {

	private String fileName;
	private int amountOfWarnings;
	private String filePath;
	private HashSet<String> warningTypes;
	private List<Warning> warningList;

	/**
	 * Constructor.
	 * 
	 * @param fileName
	 *            the name of the component.
	 * @param filePath
	 *            the path to the component.
	 */
	public ComponentSummarizer(String fileName, String filePath) {
		setFileName(fileName);
		setAmountOfWarnings(0);
		setWarningTypes(new HashSet<String>());
		setWarningList(new ArrayList<Warning>());
	}

	/**
	 * Summarise the warnings.
	 * 
	 * @param warnings
	 *            the list of warnings to be summarized.
	 */
	@Override
	public void summarize(List<Warning> warnings) {
		for (Warning w : warnings) {
			if (w.getFileName().equals(getFileName())) {
				if (!warningTypes.contains(w.getType())) {
					warningTypes.add(w.getType());
				}
				warningList.add(w);
				amountOfWarnings++;
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
	 * @param component
	 *            the new name of the component.
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Get the amount of warning that this component has.
	 * 
	 * @return the amount of warnings of this component.
	 */
	public int getAmountOfWarnings() {
		return amountOfWarnings;
	}

	/**
	 * Set the amount of warnings
	 * 
	 * @param amountOfWarnings
	 */
	public void setAmountOfWarnings(int amountOfWarnings) {
		this.amountOfWarnings = amountOfWarnings;
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
	 * Get the types of warning in this component.
	 * 
	 * @return types of warning in this component.
	 */
	public HashSet<String> getWarningTypes() {
		return warningTypes;
	}

	/**
	 * Set the types of warning in this component.
	 * 
	 * @param warningTypes
	 *            list of warning types.
	 */
	public void setWarningTypes(HashSet<String> warningTypes) {
		this.warningTypes = warningTypes;
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
	 * @param warningList
	 *            list of warning.
	 */
	public void setWarningList(List<Warning> warnings) {
		this.warningList = warnings;
	}

}
