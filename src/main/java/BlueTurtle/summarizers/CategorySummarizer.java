package BlueTurtle.summarizers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import BlueTurtle.warnings.Warning;

/**
 * This class can be used to summarize the warnings according to their categories in GDC.
 * 
 * @author BlueTurtle.
 *
 */
public class CategorySummarizer extends Summarizer {

	private String category;
	private List<Warning> warningList;
	private HashMap<String, String> categoryInfo;

	/**
	 * Constructor.
	 * 
	 * @param category
	 *            the name of the category it's going to group in.
	 * @param categoryInfo
	 *            the GDC list of category information.
	 * @param packageName
	 *            the name of the package where the component is from.
	 */
	public CategorySummarizer(String category, String packageName, HashMap<String, String> categoryInfo) {
		super(packageName);
		setCategory(category);
		setCategoryInfo(categoryInfo);
		setWarningList(new ArrayList<Warning>());
	}

	/**
	 * Summarize the warnings.
	 * 
	 * @param warnings
	 *            the list of warnings to be summarized.
	 */
	@Override
	public void summarise(List<Warning> warnings) {
		for (Warning w : warnings) {
			
			String rule = w.getRuleName();
			String currentC = categoryInfo.get(rule);
			if (currentC == category){
				warningList.add(w);
				numberOfWarnings++;
			}
		}

	}

	
	/**
	 * Check whether two CategorySummarizer are equal.
	 * 
	 * @param other
	 *            the other CategorySummarizer.
	 */
	@Override
	public boolean equals(Object other) {

		if (!(other instanceof CategorySummarizer)) {
			return false;
		}

		CategorySummarizer that = (CategorySummarizer) other;

		if (category.equals(that.getCategory()) 
				&& warningList.equals(that.warningList) 
				&& numberOfWarnings == that.numberOfWarnings
				&& categoryInfo == that.categoryInfo) {
			return true;
		} else {
			return false;
		}

	}

	/**************************************/
	/****** Getters and Setters **********/
	/************************************/

	/**
	 * Get the category from GDC.
	 * 
	 * @return the name of the category.
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Set the category from GDC.
	 * 
	 * @param category
	 *            the name of the category.
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * Get the category information from GDC.
	 * 
	 * @return categoryInfo.
	 */
	public HashMap<String, String> getCategoryInfo() {
		return categoryInfo;
	}

	/**
	 * Set the category information from GDC.
	 * 
	 * @param categoryInfo
	 *            the information table of the categories.
	 */
	public void setCategoryInfo(HashMap<String, String> categoryInfo) {
		this.categoryInfo = categoryInfo;
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
