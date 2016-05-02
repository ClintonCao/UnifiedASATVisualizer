package BlueTurtle.TSE;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Settings {
	
	public void readSettings() throws FileNotFoundException, IOException;
	
	public void writeSettings();
	
	public String getOutputFilePath();
	
	public void setOutputFilePath(String outputFilePath);
	
	public boolean isEnabled();
	
	public void setEnabled(boolean enabled);
}
