package BlueTurtle.parsers;

import org.w3c.dom.NodeList;

import BlueTurtle.finders.ProjectInfoFinder;
import BlueTurtle.warnings.CheckStyleWarning;
import BlueTurtle.warnings.Warning;

import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;

/**
 * This class can be used to parse a CheckStyle XML output file.
 * 
 * @author BlueTurtle.
 *
 */
public class CheckStyleXMLParser extends XMLParser {

	/**
	 * Parse a CheckStyle report file.
	 * 
	 * @param xmlFilePath
	 *            the location of the CheckStyle report.
	 * @return a list of CheckStyle warnings.
	 */
	@Override
	public List<Warning> parseFile(String xmlFilePath) {
		// List to store the warnings.
		List<Warning> checkStyleWarnings = new LinkedList<Warning>();

			// Get all list of files where there are warnings.
			NodeList nList = setUp(xmlFilePath);
			
			// if there are no files with warnings, there return an empty list of warnings.
			if (nList == null)
				return checkStyleWarnings;

			for (int i = 0; i < nList.getLength(); i++) {
				// Get the file from the list.
				Node file = nList.item(i);

				if (file.getNodeType() == Node.ELEMENT_NODE) {
					// Convert the node to an element.
					Element fileElement = (Element) file;

					// Get the path of the file where the warning is from.
					String filePath = fileElement.getAttribute("name");

					// Get the name of the file where the warning is from.
					String fileName = filePath.substring(filePath.lastIndexOf("src") + 3, filePath.length());

					// Get all the warnings.
					NodeList warningList = fileElement.getElementsByTagName("error");

					addWarnings(fileName, warningList, checkStyleWarnings);} }

		return checkStyleWarnings;
	}

	/**
	 * Add individual warning to the warningList.
	 * 
	 * @param fileName
	 *            is the file name of the warning.
	 * @param warningList
	 *            is a list of warnings.
	 * @param checkStyleWarnings
	 *            is the CheckStyle warnings.
	 * @return a list of CheckStyle warnings.
	 */
	public List<Warning> addWarnings(String fileName, NodeList warningList, List<Warning> checkStyleWarnings) {
		for (int j = 0; j < warningList.getLength(); j++) {
			// Get the warning from the list of warnings.
			Node warning = warningList.item(j);

			if (warning.getNodeType() == Node.ELEMENT_NODE) {
				// Convert the node to an element.
				Element warningElement = (Element) warning;

				// message of warning
				String message = warningElement.getAttribute("message");

				// line number where the warning is located.
				int line = Integer.parseInt(warningElement.getAttribute("line"));

				// Get the category of the warning.
				String ruleName = getRuleName(warningElement.getAttribute("source"));

				String classification = classify(ruleName);

				// replace the backward slash in the file name with file separator.
				String fileNWithSep = fileName.replaceAll("\\\\", Matcher.quoteReplacement(File.separator));

				// for-loop in stream, find correct filePath.
				String filePath = ProjectInfoFinder.getClassPaths().stream().filter(p -> p.endsWith(fileNWithSep)).findFirst().get();

				// Get the name of the file where the warning is from.
				String finalFileName = fileNWithSep.substring(fileNWithSep.lastIndexOf(File.separatorChar) + 1, fileNWithSep.length());

				// Add warning to the list of warnings.
				checkStyleWarnings.add(new CheckStyleWarning(filePath, finalFileName, line, message, ruleName, classification));}}
		
		return checkStyleWarnings;

	}

	/**
	 * Get the rule name of the CheckStyle warning.
	 * 
	 * @param source
	 *            the source of the check.
	 * @return the category of the CheckStyle warning.
	 */
	public static String getRuleName(String source) {
		// Remove the substring "Check" from the source.
		String[] temp = source.split("Check");

		// Build the string back.
		source = Arrays.toString(temp);

		// Return only the name of the check.
		return source.substring(source.lastIndexOf('.') + 1, source.length() - 1);
	}

}
