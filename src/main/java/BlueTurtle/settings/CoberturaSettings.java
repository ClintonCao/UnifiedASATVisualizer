package BlueTurtle.settings;

import java.io.FileNotFoundException;
import java.io.IOException;

import BlueTurtle.interfaces.Settings;

public class CoberturaSettings implements Settings {
	private String defaultOutputFilePath = "./Runnables/Testcode/cobertura.xml";

	@Override
	public void readSettings() throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeSettings() {
		// TODO Auto-generated method stub
		
	}

	public String getDefaultOutputFilePath() {
		return defaultOutputFilePath;
	}

	public void setDefaultOutputFilePath(String defaultOutputFilePath) {
		this.defaultOutputFilePath = defaultOutputFilePath;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setEnabled(boolean enabled) {
		// TODO Auto-generated method stub
		
	}

}
