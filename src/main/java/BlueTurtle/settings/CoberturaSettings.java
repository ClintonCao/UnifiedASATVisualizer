package BlueTurtle.settings;

import java.io.FileNotFoundException;
import java.io.IOException;

import BlueTurtle.interfaces.Settings;

/**
 * Class that represents the settings for Cobertura.
 * 
 * @author BlueTurtle.
 *
 */
public class CoberturaSettings implements Settings {
	private String defaultOutputFilePath = "./Runnables/Testcode/cobertura.xml";

	/**
	 * Reads setting from a file.
	 * 
	 * @throws FileNotFoundException
	 *             throws an exception if file cannot be found.
	 * @throws IOException
	 *             throws an exception if a problem is encountered while reading
	 *             the file.
	 */
	public void readSettings() throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * Writes settings to a file.
	 */
	public void writeSettings() {
		// TODO Auto-generated method stub
	}

	public String getDefaultOutputFilePath() {
		return defaultOutputFilePath;
	}

	public void setDefaultOutputFilePath(String defaultOutputFilePath) {
		this.defaultOutputFilePath = defaultOutputFilePath;
	}
}
