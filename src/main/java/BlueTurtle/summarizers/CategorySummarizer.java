package BlueTurtle.summarizers;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import BlueTurtle.warnings.Warning;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * This class can be used to summarize the warnings according to their categories in GDC.
 * 
 * @author BlueTurtle.
 *
 */
@EqualsAndHashCode
public class CategorySummarizer extends Summarizer {

	@Getter @Setter private String category;
	@Getter @Setter private List<Warning> warningList;
	@Getter @Setter private HashMap<String, String> categoryInfo;

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
			if (currentC == category) {
				warningList.add(w);
				numberOfWarnings++;
			}
		}

	}
}
