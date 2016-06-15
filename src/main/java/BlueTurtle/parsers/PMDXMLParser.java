package BlueTurtle.parsers;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import BlueTurtle.finders.ProjectInfoFinder;
import BlueTurtle.warnings.PMDWarning;
import BlueTurtle.warnings.Warning;

/**
 * This class can be used to parse a PMD XML output file.
 * 
 * @author BlueTurtle.
 *
 */
public class PMDXMLParser extends XMLParser {

	/**
	 * Parse a PMD report file.
	 * 
	 * @param xmlFilePath
	 *            the location of the PMD report.
	 * @return a list of PMD warnings.
	 */
	@Override
	public List<Warning> parseFile(String xmlFilePath) {
		// List to store the warnings.
		List<Warning> pmdWarnings = new LinkedList<Warning>();

			// Get all list of files where there are warnings.
			NodeList nList = setUp(xmlFilePath);

			// if there are no files with warnings, there return an empty list of warnings.
			if (nList == null) {
				return pmdWarnings;
			}
			
			for (int i = 0; i < nList.getLength(); i++) {
				// Get the file from the list.
				Node file = nList.item(i);


					// Convert the node to an element.
					Element fileElement = (Element) file;

					// Get the path of the file where the warning is from.
					String filePath = fileElement.getAttribute("name");

					// Get the name of the file where the warning is from.
					String fileName = filePath.substring(filePath.lastIndexOf("src") + 3, filePath.length());
					
					addWarnings(fileName, fileElement, pmdWarnings);


			}

		return pmdWarnings;
	}

	/**
	 * Add individual warning to the warningList.
	 * 
	 * @param fileName
	 *            is the file name of the warning.
	 * @param fileElement
	 *            is a file element which contains all the warnings.
	 * @param pmdWarnings
	 *            is list of PMD warnings.
	 */
	public void addWarnings(String fileName, Element fileElement, List<Warning> pmdWarnings) {

		// Get all the warnings.
		NodeList warningList = fileElement.getElementsByTagName("violation");
		
		for (int j = 0; j < warningList.getLength(); j++) {
			// Get the warning from the list of warnings.
			Node warning = warningList.item(j);

				// Convert the node to an element.
				Element warningElement = (Element) warning;

				// package which warning is in.
				String packageName = warningElement.getAttribute("package");

				// the ruleSet of warning.
				String ruleSet = warningElement.getAttribute("ruleset");

				// method of warning.
				String method = warningElement.getAttribute("method");

				// line number where the warning is located.
				int line = Integer.parseInt(warningElement.getAttribute("beginline"));

				// Get the category of the warning.
				String ruleName = warningElement.getAttribute("rule");

				// PMD rule name is a special concatenation of rule set and rule name.
//				String pmdRN = ruleSet.replace(" ", "").toLowerCase() + ".xml/" + ruleName;

				// find the correct classification given the rule name and the rule set.
				String classification = classify(ruleName);
				
				// debug info
				if(classification == null) System.out.println("rule name: " + ruleName);

				// replace the backward slash in the file name with file separator.
				String fileNWithSep = fileName.replaceAll("\\\\", Matcher.quoteReplacement(File.separator));

				// for-loop in stream, find correct filePath.
				String filePath = ProjectInfoFinder.getClassPaths().stream().filter(p -> p.endsWith(fileNWithSep)).findFirst().get();

				// Get the name of the file where the warning is from.
				String finalFileName = fileNWithSep.substring(fileNWithSep.lastIndexOf(File.separatorChar) + 1, fileNWithSep.length());

				// Retrieve the message corresponds to this warning.
				String message = fileElement.getElementsByTagName("violation").item(j).getTextContent();

				// Add warning to the list of warnings.
				pmdWarnings.add(new PMDWarning(filePath, finalFileName, line, packageName, ruleSet, method, ruleName, message, classification));
		}
	}

}
