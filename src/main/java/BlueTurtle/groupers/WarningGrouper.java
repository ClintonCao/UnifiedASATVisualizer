package BlueTurtle.groupers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import BlueTurtle.finders.PackageNameFinder;
import BlueTurtle.interfaces.Grouper;
import BlueTurtle.summarizers.ComponentSummarizer;
import BlueTurtle.summarizers.PackageSummarizer;
import BlueTurtle.summarizers.Summarizer;
import BlueTurtle.warnings.Warning;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * This class is used to group warnings together (by their type or by
 * components).
 * 
 * @author BlueTurtle.
 *
 */
@EqualsAndHashCode
@AllArgsConstructor
public class WarningGrouper implements Grouper {

	@Getter @Setter private HashMap<String, String> componentsInfo;
	@Getter @Setter private Set<String> packagesNames;
	@Getter @Setter private List<Warning> warningList;

	/**
	 * Group things together, based on the criteria.
	 * 
	 * @param criteria
	 *            the criteria for grouping things together.
	 */
	@Override
	public List<Summarizer> groupBy(String criteria) {
		switch (criteria) {
		case "components":
			return groupByComponent();
		case "packages":
			return groupByPackage();
		default:
			return null;
		}
	}

	/**
	 * Group warnings by the component that they are from.
	 * 
	 * @return a list of Summarizer objects.
	 */
	public List<Summarizer> groupByComponent() {
		List<Summarizer> csList = new ArrayList<Summarizer>();

		for (Entry<String, String> ci : getComponentsInfo().entrySet()) {
			String fileName = ci.getKey();
			String filePath = ci.getValue();
			String packageName = PackageNameFinder.findPackageName(filePath);
			ComponentSummarizer cs = new ComponentSummarizer(fileName, filePath, packageName);
			cs.summarise(getWarningList());
			csList.add(cs);
		}

		return csList;
	}

	/**
	 * Group the warnings by the package they are from.
	 * 
	 * @return a list of summarizer objects.
	 */
	public List<Summarizer> groupByPackage() {
		List<Summarizer> result = new ArrayList<Summarizer>();

		for (String p : packagesNames) {
			PackageSummarizer ps = new PackageSummarizer(p);
			ps.summarise(warningList);
			result.add(ps);
		}

		return result;
	}
}
