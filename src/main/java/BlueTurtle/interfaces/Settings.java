package BlueTurtle.interfaces;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.xml.sax.SAXException;

public interface Settings {

	String getDefaultOutputFilePath();
	
	void setDefaultOutputFilePath(String defaultOutputFilePath);
}
