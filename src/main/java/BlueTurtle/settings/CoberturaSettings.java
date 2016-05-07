package BlueTurtle.settings;

import java.io.FileNotFoundException;
import java.io.IOException;

import BlueTurtle.interfaces.Settings;

public class CoberturaSettings implements Settings {
	private String outputFilePath = "./Runnables/Testcode/cobertura/coverage.xml";

	@Override
	public void readSettings() throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeSettings() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getOutputFilePath() {
		return outputFilePath;
	}

	@Override
	public void setOutputFilePath(String outputFilePath) {
		this.outputFilePath = outputFilePath;
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
