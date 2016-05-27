package BlueTurtle.groupers;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import BlueTurtle.finders.PackageNameFinder;
import BlueTurtle.summarizers.ComponentSummarizer;
import BlueTurtle.summarizers.PackageSummarizer;
import BlueTurtle.summarizers.Summarizer;
import BlueTurtle.warnings.Warning;
import lombok.Getter;

/**
 * This class is used to group warnings together (by their type or by
 * components).
 * 
 * @author BlueTurtle.
 *
 */
public class WarningGrouper implements Grouper {

	/**
	 * The types of criteria for grouping warnings.
	 * 
	 * @author BlueTurtle.
	 *
	 */
	public enum Criteria {
		COMPONENTS, PACKAGES
	}

	@Getter private HashMap<String, String> componentsInfo;
	@Getter private Set<String> packagesNames;
	@Getter private EnumMap<Criteria, List<Summarizer>> summarizedWarnings;
	@Getter private List<Warning> warningList;

	/**
	 * Constructor.
	 * 
	 * @param componentsInfo
	 *            the information of the components.
	 * @param packagesNames
	 *            the names of all packages.
	 * @param warningList
	 *            the list of warnings.
	 */
	public WarningGrouper(HashMap<String, String> componentsInfo, Set<String> packagesNames, List<Warning> warningList) {
		this.componentsInfo = componentsInfo;
		this.packagesNames = packagesNames;
		this.warningList = warningList;
		this.summarizedWarnings = new EnumMap<Criteria, List<Summarizer>>(Criteria.class);
		summarizedWarnings.put(Criteria.COMPONENTS, groupByComponent());
		summarizedWarnings.put(Criteria.PACKAGES, groupByPackage());
	}

	/**
	 * Group things together, based on the criteria.
	 * 
	 * @param criteria
	 *            the criteria for grouping things together.
	 */
	@Override
	public List<Summarizer> groupBy(Enum<Criteria> criteria) {
		return summarizedWarnings.get(criteria);
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

	/**
	 * Check whether two Warning grouper are equal.
	 * 
	 * @param other
	 *            the other WarningGrouper object
	 */
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof WarningGrouper)) {
			return false;
		}

		WarningGrouper that = (WarningGrouper) other;

		// fix SimplifyBooleanReturn, Conditional logic can be removed.
		return (componentsInfo.equals(that.getComponentsInfo()) && packagesNames.equals(that.getPackagesNames())
				&& warningList.equals(that.getWarningList()) && summarizedWarnings.equals(that.summarizedWarnings));
	}
	
	/**
	 * HashCode for WarningGrouper.
	 */
	@Override
	public int hashCode() {
		return java.util.Objects.hash(componentsInfo, packagesNames, warningList, summarizedWarnings);
	}
	
}
