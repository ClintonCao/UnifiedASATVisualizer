package BlueTurtle.parsers;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
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
public class NewFindBugsXMLParser extends XMLParser {

	/**
	 * Parse a FindBugs report file.
	 * 
	 * @param xmlFilePath
	 *            the location of the FindBugs report.
	 * @return a list of FindBugs warnings.
	 */
	@Override
	public List<Warning> parseFile(String xmlFilePath, HashMap<String, String> categoryInfo) {
		// List to store the warnings.
		List<Warning> findBugsWarnings = new LinkedList<Warning>();
		
		try {

			// Instantiate things that are necessary for the parser.
			File inputFile = new File(xmlFilePath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

			// Parse the file.
			Document doc = dBuilder.parse(inputFile);

			// Normalize the elements of the document.
			doc.getDocumentElement().normalize();		

			// Get all list of files where there are warnings.
			NodeList nList = doc.getElementsByTagName("file");

			for (int i = 0; i < nList.getLength(); i++) {
				// Get the file from the list.
				Node file = nList.item(i);

				if (file.getNodeType() == Node.ELEMENT_NODE) {
					// Convert the node to an element.
					Element fileElement = (Element) file;

					// Get the name of the file where the warning is from.
					String fileName = fileElement.getAttribute("classname");
					
					// Get all the warnings.
					NodeList warningList = fileElement.getElementsByTagName("BugInstance");
					
					addWarnings(fileName, warningList, nList, findBugsWarnings, categoryInfo);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return findBugsWarnings;
	}
	

	/**
	 * add individual warning to the warningList.
	 * 
	 * @param fileName is the file name of the warning.
	 * @param warningList is a list of warnings.
	 * @param nList is the node list.
	 * @param findBugsWarnings is the findBugs warnings.
	 * @param categoryInfo is the category information.
	 * @return a list of FindBugs warnings.
	 */
	public List<Warning> addWarnings(String fileName, NodeList warningList, NodeList nList, List<Warning> findBugsWarnings, HashMap<String, String> categoryInfo) {
		
		for (int j = 0; j < warningList.getLength(); j++) {
			// Get the warning from the list of warnings.
			Node warning = warningList.item(j);

			if (warning.getNodeType() == Node.ELEMENT_NODE) {
				// Convert the node to an element.
				Element warningElement = (Element) warning;

				// message of warning
				String message = warningElement.getAttribute("message");

				// category of warning
				String category = warningElement.getAttribute("category");
				
				// priority of warning
				String priority = warningElement.getAttribute("priority");
				
				// line number where the warning is located.
				int line = Integer.parseInt(warningElement.getAttribute("lineNumber"));

				// Get the category of the warning.
				String ruleName = warningElement.getAttribute("type");
				
				String classification = categoryInfo.get(ruleName);
				
				ArrayList<String> classPaths = ProjectInfoFinder.getClassPaths();
				
				String fileNWithSep = fileName.replaceAll("\\.", Matcher.quoteReplacement(File.separator)) + ".java";
				
				String filePath = " ";
				
				// Loop through all classPathes.
				for(int i = 0; i < classPaths.size(); i++) {
					if (classPaths.get(i).endsWith(fileNWithSep)){
						filePath = classPaths.get(i);
					}
				}
				// Get the name of the file where the warning is from.
				fileName = fileNWithSep.substring(fileNWithSep.lastIndexOf(File.separatorChar) + 1, fileNWithSep.length());
				
				// file path is set to empty string, because we are going to set the File path through
				// ProjectInfoFinder class.
				FindBugsWarning fbw = new FindBugsWarning(filePath, fileName, line, message, category, priority, ruleName, classification);
				
				// Add warning to the list of warnings.
				findBugsWarnings.add(fbw);
			}
		}
		return findBugsWarnings;
	}
	

}
