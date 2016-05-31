package BlueTurtle.summarizers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import BlueTurtle.warnings.Warning;
import lombok.Getter;

/**
 * This class can be used to summarize the warnings according to their categories in GDC.
 * 
 * @author BlueTurtle.
 *
 */
public class CategorySummarizer extends Summarizer {

	@Getter private String category;
	@Getter private List<Warning> warningList;
	@Getter private HashMap<String, String> categoryInfo;

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
		this.category = category;
		this.categoryInfo = categoryInfo;
		this.warningList = new ArrayList<Warning>();
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
			if (currentC.equals(category)) {
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
		
		return (category.equals(that.getCategory()) 
				&& warningList.equals(that.warningList) 
				&& numberOfWarnings == that.numberOfWarnings
				&& categoryInfo == that.categoryInfo);
	}

	/**
	 * HashCode for CatergorySummarizer.
	 */
	@Override
	public int hashCode() {
		return java.util.Objects.hash(category, warningList, numberOfWarnings, categoryInfo);
	}
	
}
