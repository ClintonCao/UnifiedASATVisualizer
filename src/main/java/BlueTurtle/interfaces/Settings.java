package BlueTurtle.interfaces;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Settings {
	
	public void readSettings() throws FileNotFoundException, IOException;
	
	public void writeSettings();
	
	public String getDefaultOutputFilePath();
	
	public void setDefaultOutputFilePath(String defaultOutputFilePath);
	
	public boolean isEnabled();
	
	public void setEnabled(boolean enabled);
}
