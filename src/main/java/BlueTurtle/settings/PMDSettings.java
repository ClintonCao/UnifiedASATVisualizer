package BlueTurtle.settings;

import BlueTurtle.interfaces.Settings;

public class PMDSettings implements Settings {
	boolean enabled;
	private String defaultOutputFilePath = "./Runnables/Testcode/PMD.xml";

	public void readSettings() {
			
	}

	public void writeSettings() {
		
	}
	
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getDefaultOutputFilePath() {
		return defaultOutputFilePath;
	}

	public void setDefaultOutputFilePath(String defaultOutputFilePath) {
		this.defaultOutputFilePath = defaultOutputFilePath;
	}

}
