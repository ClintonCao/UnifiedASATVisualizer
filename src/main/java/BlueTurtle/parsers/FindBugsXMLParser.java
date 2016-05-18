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
			
			// Get the list of file path of the project.
			NodeList pathsList = doc.getElementsByTagName("Project");
			
			NodeList srcList = doc.getElementsByTagName("SrcDir");

			String pathFront = "";
			
			if (pathsList != null && pathsList.getLength() > 0) {
				Element pathElement = (Element) pathsList.item(0);
				srcList = pathElement.getElementsByTagName("SrcDir");
			}			

			// Get all list of files where there are warnings.
			NodeList nList = doc.getElementsByTagName("file");

			for (int i = 0; i < nList.getLength(); i++) {
				// Get the file from the list.
				Node file = nList.item(i);

				if (file.getNodeType() == Node.ELEMENT_NODE) {
					// Convert the node to an element.
					Element fileElement = (Element) file;

					// Get the class name where the warning is from.
					String className = fileElement.getAttribute("classname");
					
					// split the class name into a string array.
					String [] classArray = className.split("\\.");
					
					// the last one is the class name.
					className = classArray[classArray.length - 1];
					
					// concatenate the source path with the class name.
					String fileN = className + ".java";
					
					fileN = fileN.substring(fileN.lastIndexOf(File.separatorChar) + 1, fileN.length());

					// get the file path from the file name.
					String filePath = new File(fileN).getCanonicalPath();
					
					
					/***********************************************************/
					/*********This part is for get absolute file path***********/
					
					// Get the class name where the warning is from.
					String classN = fileElement.getAttribute("classname");
					
					// replace the . with \\ in the file name.
					String cN = classN.replaceAll("\\.", "\\\\");
					
					pathFront = srcList.item(0).getTextContent();
		
					// concatenate the source path with the class name.
					String fileConcate = pathFront + "\\" + cN + ".java";
	
					// get the absoluteFilePath.
					String absoluteFilePath = new File(fileConcate).getAbsolutePath();
					
					/***********************************************************/
					/*********This part is for get absolute file path***********/
					
					// Get the name of the file where the warning is from.
					String fileName = filePath.substring(filePath.lastIndexOf(File.separatorChar) + 1, filePath.length());
					
					// Get all the warnings.
					NodeList warningList = fileElement.getElementsByTagName("BugInstance");

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

							FindBugsWarning fbw = new FindBugsWarning(filePath, fileName, line, message, category, priority, ruleName, classification);
							
							fbw.setAbsoluteFilePath(absoluteFilePath);
							// Add warning to the list of warnings.
							findBugsWarnings.add(fbw);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return findBugsWarnings;
	}
	

}
