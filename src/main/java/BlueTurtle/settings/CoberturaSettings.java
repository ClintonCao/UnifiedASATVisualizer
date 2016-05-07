package BlueTurtle.settings;

import java.io.FileNotFoundException;
import java.io.IOException;

import BlueTurtle.interfaces.Settings;

public class CoberturaSettings implements Settings {
	private String defaultOutputFilePath = "./Runnables/Testcode/cobertura.xml";

	public void readSettings() throws FileNotFoundException, IOException {
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
