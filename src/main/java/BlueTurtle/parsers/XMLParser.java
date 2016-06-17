package BlueTurtle.parsers;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import BlueTurtle.warnings.Warning;

/**
 * This class can be used to parse a XML file.
 * 
 * @author BlueTurtle.
 *
 */
public abstract class XMLParser implements Parser {
	
	private static String mdFilePath = "./src/main/resources/asat-gdc-mapping.html";


	/**
	 * Parse a XML file.
	 * 
	 * @param filePath
	 *            the path to the file that needs to be parsed.
	 * @return a list of warnings.
	 */
	public abstract List<Warning> parseFile(String filePath);

	/**
	 * Set up.
	 * 
	 * @param xmlFilePath
	 *            the location of the CheckStyle report.
	 * @return a list of files where there are warnings.
	 */
	public NodeList setUp(String xmlFilePath) {
		// set up an empty list.
		NodeList nList = null;
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
			nList = doc.getElementsByTagName("file");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return nList;
	}
	
	/**
	 * Classify the rule name to the correct classification according to
	 * categoryInfo.
	 * 
	 * @param ruleName
	 *            the violated rule name of the warning.
	 *
	 * @return the category of a warning(based on the GDC).
	 */
	public static String classify(String ruleName) {
		GDCParser gp = GDCParser.getInstance();
		gp.parseFile(mdFilePath);
		HashMap<String, String> categoryInfo = GDCParser.getCategoryInfo();
		String finalRuleName = categoryInfo.keySet().stream().filter(p -> p.endsWith(ruleName)).findFirst().get();
		return categoryInfo.get(finalRuleName);
	}
}
