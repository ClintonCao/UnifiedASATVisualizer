package BlueTurtle.settings;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

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

public class CheckStyleSettings implements Settings {
	private File sourceFile;
	private String configFile;
	private String defaultOutputFilePath = "./Runnables/Testcode/checkstyle.xml";
	
	public CheckStyleSettings(File sourceFile) {
		setSourceFile(sourceFile);
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

	private void readSettings() throws FileNotFoundException, IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(sourceFile);
        doc.getDocumentElement().normalize();
    
        setConfigFile(doc.getElementsByTagName("config").item(0).getTextContent());
	}

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

	public String getDefaultOutputFilePath() {
		return defaultOutputFilePath;
	}

	public void setDefaultOutputFilePath(String defaultOutputFilePath) {
		this.defaultOutputFilePath = defaultOutputFilePath;
	}

	public String getConfigFile() {
		return configFile;
	}

	public void setConfigFile(String configFile) {
		this.configFile = configFile;
	}

	public File getSourceFile() {
		return sourceFile;
	}

	public void setSourceFile(File sourceFile) {
		this.sourceFile = sourceFile;
	}
}
