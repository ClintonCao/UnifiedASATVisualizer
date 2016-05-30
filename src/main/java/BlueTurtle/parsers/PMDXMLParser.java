package BlueTurtle.parsers;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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

					// Get the path of the file where the warning is from.
					String filePath = fileElement.getAttribute("name");

					// Get the name of the file where the warning is from.
					String fileName = filePath.substring(filePath.lastIndexOf('\\') + 1, filePath.length());

					// Get all the warnings.
					NodeList warningList = fileElement.getElementsByTagName("violation");
					
					addWarnings(filePath, fileName, warningList, pmdWarnings);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pmdWarnings;
	}
	
	/**
	 * add individual warning to the warningList.
	 * 
	 * @param filePathis the file path of the warning
	 * @param fileName is the file name of the warning.
	 * @param warningList is a list of warnings.
	 * @param pmdWarnings is list of PMD warnings.
	 */
	public void addWarnings(String filePath, String fileName, NodeList warningList, List<Warning> pmdWarnings) {
		
		for (int j = 0; j < warningList.getLength(); j++) {
			// Get the warning from the list of warnings.
			Node warning = warningList.item(j);

			if (warning.getNodeType() == Node.ELEMENT_NODE) {
				// Convert the node to an element.
				Element warningElement = (Element) warning;

				// packageName of warning
				String packageName = warningElement.getAttribute("package");

				// ruleSet of warning
				String ruleSet = warningElement.getAttribute("ruleset");
				
				// method of warning
				String method = warningElement.getAttribute("method");
				
				// line number where the warning is located.
				int line = Integer.parseInt(warningElement.getAttribute("beginline"));

				// Get the category of the warning.
				String ruleName = warningElement.getAttribute("rule");
				
				// PMD rule name is a special concatenation of rule set and rule name
				String pmdRN = ruleSet.replace(" ", "").toLowerCase() + ".xml/" + ruleName; 
				
				// find the correct classification given the rule name and the rule set.
				String classification = classify(pmdRN);

				// Add warning to the list of warnings.
				pmdWarnings.add(new PMDWarning(filePath, fileName, line, packageName, ruleSet, method, ruleName, classification));
			}
		}
	}
	
		

}
