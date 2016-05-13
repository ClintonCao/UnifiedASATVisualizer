package BlueTurtle.settings;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import BlueTurtle.interfaces.Settings;

/**
 * Class that represents the settings for PMD.
 * 
 * @author BlueTurtle.
 *
 */
public class PMDSettings implements Settings {
	private static PMDSettings instance = null;
	
	private File sourceFile = Paths.get("resources", "asatSettings", "PMD_Settings.xml").toFile();
	private String defaultOutputFilePath = Paths.get("Runnables", "Testcode", "PMD.xml").toString();

	/**
	 * Constructor.
	 * 
	 * @param sourceFile
	 *            the file of the setting.
	 */
	private PMDSettings() {
		try {
			readSettings();
		} catch (IOException | SAXException | ParserConfigurationException e) {
			e.printStackTrace();
		}

		try {
			writeSettings();
		} catch (ParserConfigurationException | TransformerException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @return
	 * 			The singleton instance of PMDSettings.
	 */
	public static PMDSettings getInstance() {
		if(instance == null) {
			instance = new PMDSettings();
		}
		return instance;
	}

	/**
	 * Read the setting from the source file.
	 * 
	 * @throws FileNotFoundException
	 *             throws an exception if the file cannot be found.
	 * @throws IOException
	 *             throws an exception if there is a problem encountered while
	 *             parsing the file.
	 * @throws SAXException
	 *             builder throws an exception if problem is encountered.
	 * @throws ParserConfigurationException
	 *             throws an exception if a problem is encountered for the parse
	 *             configuration.
	 */
	private void readSettings() throws FileNotFoundException, IOException, SAXException, ParserConfigurationException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(sourceFile);
		doc.getDocumentElement().normalize();
	}

	/**
	 * Write the settings so a file.
	 * 
	 * @throws ParserConfigurationException
	 *             throws an exception if a problem is encountered for the parse
	 *             configuration.
	 * @throws TransformerException
	 *             throws an exception if the transformer has encountered a
	 *             problem.
	 */
	private void writeSettings() throws ParserConfigurationException, TransformerException {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("placeholder");
		rootElement.appendChild(doc.createTextNode("placeholder"));
		doc.appendChild(rootElement);

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(sourceFile);

		transformer.transform(source, result);
	}

	public String getDefaultOutputFilePath() {
		return defaultOutputFilePath;
	}

	public void setDefaultOutputFilePath(String defaultOutputFilePath) {
		this.defaultOutputFilePath = defaultOutputFilePath;
	}

	public File getSourceFile() {
		return sourceFile;
	}

	public void setSourceFile(File sourceFile) {
		this.sourceFile = sourceFile;
	}
}
