package BlueTurtle.commandbuilders;

import BlueTurtle.TSE.JavaController;
import BlueTurtle.settings.CheckStyleSettings;

/**
 * Class to build commands for CheckStyle.
 * 
 * @author BlueTurtle.
 *
 */
public class CheckStyleCommandBuilder extends CommandBuilder {

	/**
	 * Constructor.
	 * 
	 * @param checkStyleSettings
	 *            the setting for CheckStyle.
	 */
	public CheckStyleCommandBuilder(CheckStyleSettings checkStyleSettings) {
		super();
		this.setSettings(checkStyleSettings);
	}

	/**
	 * Checkstyle is run as a .jar. So the command always starts with java -jar.
	 * 
	 * @return returns a string array that represents the command.
	 */
	@Override
	public String[] buildCommand() {
		commands.add("java");
		commands.add("-jar");
		commands.add(JavaController.getUserDir() + "/Runnables/checkstyle-6.18-all.jar");
		commands.add("-o");
		commands.add(JavaController.getUserDir() + "/Runnables/Testcode/checkstyle.xml");
		commands.add("-c");
		commands.add(((CheckStyleSettings) getSettings()).getConfigFile());
		commands.add("-f");
		commands.add("xml");
		commands.add(JavaController.getUserDir() + "/Runnables/Testcode/");
		String[] retCommands = commands.toArray(new String[commands.size()]);
		return retCommands;
	}

}
