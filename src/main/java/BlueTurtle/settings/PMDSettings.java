package BlueTurtle.settings;

import BlueTurtle.interfaces.Settings;

public class PMDSettings implements Settings {
	boolean enabled;
	private String outputFilePath = "./Runnables/Testcode/output.xml";

	public void readSettings() {
			
	}

	public void writeSettings() {
		
	}

	public String getOutputFilePath() {
		return outputFilePath;
	}

	public void setOutputFilePath(String outputFilePath) {
		this.outputFilePath = outputFilePath;
	}
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
