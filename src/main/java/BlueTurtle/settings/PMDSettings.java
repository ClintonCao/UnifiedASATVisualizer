package BlueTurtle.settings;

import BlueTurtle.interfaces.Settings;

/**
 * Class that represents the settings for PMD.
 * 
 * @author BlueTurtle.
 *
 */
public class PMDSettings implements Settings {
	private String defaultOutputFilePath = "./Runnables/Testcode/PMD.xml";

	/**
	 * Reads setting from a file.
	 */
	public void readSettings() {
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
