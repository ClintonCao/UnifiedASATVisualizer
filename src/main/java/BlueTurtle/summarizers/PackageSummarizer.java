package BlueTurtle.summarizers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import BlueTurtle.warnings.Warning;

/**
 * This class summarises the warnings of a specific package.
 * 
 * @author BlueTurtle.
 *
 */
public class PackageSummarizer extends Summarizer {

	private List<ComponentSummarizer> classes;
	private HashMap<String, String> componentsInfo;

	/**
	 * Contructor.
	 * 
	 * @param packageName
	 *            the name of the package.
	 * @param componentsInfo
	 *            a HashMap containing information of classes.
	 */
	public PackageSummarizer(String packageName, HashMap<String, String> componentsInfo) {
		super(packageName);
		setClasses(new ArrayList<ComponentSummarizer>());
		setComponentsInfo(componentsInfo);
	}

	/**
	 * Summarise the warnings for this package.
	 * 
	 * @param warnings
	 *            list of warnings
	 */
	@Override
	public void summarise(List<Warning> warnings) {
		for (Entry<String, String> ci : componentsInfo.entrySet()) {
			ComponentSummarizer cs = new ComponentSummarizer(ci.getKey(), ci.getValue(), packageName);
			cs.summarise(warnings);
			classes.add(cs);
			warningTypes.addAll(cs.getWarningTypes());
			numberOfWarnings += cs.getNumberOfWarnings();
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

	/**
	 * Get all the information of the classes.
	 * 
	 * @return a HashMap containing information of the classes.
	 */
	public HashMap<String, String> getComponentsInfo() {
		return componentsInfo;
	}

	/**
	 * Set the information of the classes.
	 * 
	 * @param componentsInfo
	 *            HashMap containing information of classes.
	 */
	public void setComponentsInfo(HashMap<String, String> componentsInfo) {
		this.componentsInfo = componentsInfo;
	}

}
