package BlueTurtle.settings;

import java.io.FileNotFoundException;
import java.io.IOException;

import BlueTurtle.interfaces.Settings;

public class CheckStyleSettings implements Settings {
	private String outputFilePath = "./Runnables/Testcode/checkstyle.xml";

	public void readSettings() throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		
	}

	public void writeSettings() {
		// TODO Auto-generated method stub
		
	}

	public String getOutputFilePath() {
		// TODO Auto-generated method stub
		return outputFilePath;
	}

	public void setOutputFilePath(String outputFilePath) {
		this.outputFilePath = outputFilePath;	
	}

	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setEnabled(boolean enabled) {
		// TODO Auto-generated method stub
		
	}

}
