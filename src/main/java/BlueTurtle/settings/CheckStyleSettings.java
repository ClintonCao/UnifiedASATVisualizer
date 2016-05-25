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

import lombok.Getter;
import lombok.Setter;

/**
 * Class that represents the settings for CheckStyle.
 * 
 * @author BlueTurtle.
 *
 */
public class CheckStyleSettings implements Settings {
	private static CheckStyleSettings instance = null;

	@Getter @Setter private File sourceFile = Paths.get("resources", "asatSettings", "CheckStyle_Settings.xml").toFile();
	@Getter @Setter private String configFile;
	@Getter @Setter private String defaultOutputFilePath = Paths.get("Runnables", "Testcode", "checkstyle.xml").toString();

	/**
	 * Constructor.
	 * 
	 * @param sourceFile
	 *            the file of the setting.
	 */
	private CheckStyleSettings() {
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
	 * Get the instance of this class.
	 * 
	 * @return The singleton instance of CheckstyleSettings.
	 */
	public static CheckStyleSettings getInstance() {
		if (instance == null) {
			instance = new CheckStyleSettings();
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

		setConfigFile(doc.getElementsByTagName("config").item(0).getTextContent());
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
		Element rootElement = doc.createElement("config");
		rootElement.appendChild(doc.createTextNode(configFile));
		doc.appendChild(rootElement);

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(sourceFile);

		transformer.transform(source, result);
	}
}
