package BlueTurtle.settings;

import BlueTurtle.interfaces.Settings;

public class PMDSettings implements Settings {
	private String defaultOutputFilePath = "./Runnables/Testcode/PMD.xml";

	public void readSettings() {
		// TODO Auto-generated method stub			
	}

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
