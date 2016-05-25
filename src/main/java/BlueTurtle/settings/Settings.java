package BlueTurtle.settings;

/**
 * Interface for settings.
 * 
 * @author BlueTurtle.
 *
 */
public interface Settings {

	/**
	 * Get the output path of the settings file.
	 * 
	 * @return the output path of the settings file.
	 */
	String getDefaultOutputFilePath();

	/**
	 * Set the output path for the settings file.
	 * 
	 * @param defaultOutputFilePath
	 *            the path for the output file.
	 */
	void setDefaultOutputFilePath(String defaultOutputFilePath);
}
