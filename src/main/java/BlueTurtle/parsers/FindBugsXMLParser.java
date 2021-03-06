package BlueTurtle.parsers;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import BlueTurtle.finders.ProjectInfoFinder;
import BlueTurtle.warnings.FindBugsWarning;
import BlueTurtle.warnings.Warning;

/**
 * This class can be used to parse a FindBugs XML output file.
 * 
 * @author BlueTurtle.
 *
 */
public class FindBugsXMLParser extends XMLParser {

	/**
	 * Parse a FindBugs report file.
	 * 
	 * @param xmlFilePath
	 *            the location of the FindBugs report.
	 * @return a list of FindBugs warnings.
	 */
	@Override
	public List<Warning> parseFile(String xmlFilePath) {

		// List to store the warnings.
		List<Warning> findBugsWarnings = new LinkedList<Warning>();

		// Get all list of files where there are warnings.
		NodeList nList = setUp(xmlFilePath);

		// if there are no files with warnings, there return an empty list of
		// warnings.
		if (nList == null) {
			return findBugsWarnings;
		}

		for (int i = 0; i < nList.getLength(); i++) {
			// Get the file from the list.
			Node file = nList.item(i);

			// Convert the node to an element.
			Element fileElement = (Element) file;

			// Get the name of the file where the warning is from.
			String fileName = fileElement.getAttribute("classname");

			// Get all the warnings.
			NodeList warningList = fileElement.getElementsByTagName("BugInstance");

			addWarnings(fileName, warningList, findBugsWarnings, nList);
		}

		return findBugsWarnings;
	}

	/**
	 * add individual warning to the warningList.
	 * 
	 * @param fileName
	 *            is the file name of the warning.
	 * @param warningList
	 *            is a list of warnings.
	 * @param nList
	 *            is the node list.
	 * @param findBugsWarnings
	 *            is the findBugs warnings.
	 */
	public void addWarnings(String fileName, NodeList warningList, List<Warning> findBugsWarnings, NodeList nList) {

		for (int j = 0; j < warningList.getLength(); j++) {
			// Get the warning from the list of warnings.
			Node warning = warningList.item(j);

			// Convert the node to an element.
			Element warningElement = (Element) warning;

			// The message contained by the warning.
			String message = warningElement.getAttribute("message");

			// The category of warning.
			String category = warningElement.getAttribute("category");

			// The priority of warning, can be high or low.
			String priority = warningElement.getAttribute("priority");

			// line number where the warning is located.
			int line = Integer.parseInt(warningElement.getAttribute("lineNumber"));

			// Get the category of the warning.
			String ruleName = warningElement.getAttribute("type");

			String classification = classify(ruleName);

			try {
				// replace the dot in the file name with file separator.
				String fileNWithSep = fileName.replaceAll("\\.", Matcher.quoteReplacement(File.separator)) + ".java";

				// for-loop in stream.
				String filePath = ProjectInfoFinder.getClassPaths().stream().filter(p -> p.endsWith(fileNWithSep))
						.findFirst().get();

				// Get the name of the file where the warning is from.
				String finalFileName = fileNWithSep.substring(fileNWithSep.lastIndexOf(File.separatorChar) + 1,
						fileNWithSep.length());

				// Construct the new FindBugsWarning.
				FindBugsWarning fbw = new FindBugsWarning(filePath, finalFileName, line, message, category, priority,
						ruleName, classification);

				// Add warning to the list of warnings.
				findBugsWarnings.add(fbw);
			} catch (NoSuchElementException e) {
				// TODO (MMB) perhaps add some more advanced error state
				// handling?
				// Optional did not return a file Name
			}
		}
	}

}
