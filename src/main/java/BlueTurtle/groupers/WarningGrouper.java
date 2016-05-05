package BlueTurtle.groupers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import BlueTurtle.interfaces.Grouper;
import BlueTurtle.summarizers.ComponentSummarizer;
import BlueTurtle.summarizers.PackageSummarizer;
import BlueTurtle.summarizers.Summarizer;
import BlueTurtle.warnings.Warning;

/**
 * This class is used to group warnings together (by their type or by
 * components).
 * 
 * @author BlueTurtle.
 *
 */
public class WarningGrouper implements Grouper {

	private HashMap<String, String> componentsInfo;
	private Set<String> packagesNames;
	private List<Warning> warningList;

	/**
	 * Contructor.
	 * 
	 * @param componentsInfo
	 *            HashMap containing information of the components.
	 * @param packagesNames
	 *            list containing the names of the packages.
	 * @param wList
	 *            list of warnings
	 */
	public WarningGrouper(HashMap<String, String> componentsInfo, Set<String> packagesNames, List<Warning> wList) {
		setComponentsInfo(componentsInfo);
		setPackagesName(packagesNames);
		setWarningList(wList);
	}

	/**
	 * Group things together, based on the criteria.
	 * 
	 * @param criteria
	 *            the criteria for grouping things together.
	 */
	@Override
	public List<Summarizer>groupBy(String criteria) {
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
			String packageName = findPackageName(filePath);
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
			PackageSummarizer ps = new PackageSummarizer(p, componentsInfo);
			ps.summarise(warningList);
			result.add(ps);
		}

		return result;
	}

	/**
	 * Find the name of the package from a path.
	 * 
	 * @param filePath
	 *            the file path.
	 * @return the name of the package.
	 */
	public static String findPackageName(String filePath) {
		String packageName = "default";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			String line = reader.readLine();
			String[] packageInfo = line.split(" ");
			if (packageInfo[0].equals("package")) {
				packageName = packageInfo[1].split(";")[0];
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return packageName;
	}

	/**************************************/
	/****** Getters and Setters **********/
	/************************************/

	/**
	 * Get the information of the components.
	 * 
	 * @return a HashMap containing the information of the components.
	 */
	public HashMap<String, String> getComponentsInfo() {
		return componentsInfo;
	}

	/**
	 * Set the information of the components.
	 * 
	 * @param componentsInfo
	 *            HashMap containing the information of the components.
	 */
	public void setComponentsInfo(HashMap<String, String> componentsInfo) {
		this.componentsInfo = componentsInfo;
	}

	/**
	 * Get the list containing the names of the packages.
	 * 
	 * @return list containing the names of the packages.
	 */
	public Set<String> getPackagesName() {
		return packagesNames;
	}

	/**
	 * Set the list containing the names of the packages.
	 * 
	 * @param packagesName
	 *            list containing the names of the packages.
	 */
	public void setPackagesName(Set<String> packagesName) {
		this.packagesNames = packagesName;
	}

	/**
	 * Get the list containing the names of the packages.
	 * 
	 * @return a list of warnings.
	 */
	public List<Warning> getWarningList() {
		return warningList;
	}

	/**
	 * Set the list containing the names of the packages.
	 * 
	 * @param wList
	 *            list of warnings.
	 */
	public void setWarningList(List<Warning> wList) {
		this.warningList = wList;
	}

}
