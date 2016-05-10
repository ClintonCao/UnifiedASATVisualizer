package BlueTurtle.commandbuilders;

import java.util.ArrayList;

import BlueTurtle.TSE.CommandBuilder;
import BlueTurtle.TSE.JavaController;
import BlueTurtle.settings.CheckStyleSettings;

/**
 * @author Michiel
 *
 */
public class CheckStyleCommandBuilder extends CommandBuilder {
	
	/**
	 * Constructor.
	 * @param checkStyleSettings
	 */
	public CheckStyleCommandBuilder(CheckStyleSettings checkStyleSettings) {
		this.commands = new ArrayList<String>();
		this.setSettings(checkStyleSettings);
	}
	
	/**
	 * Checkstyle is run as a .jar. So the command always starts with java -jar.
	 */
	@Override
	public String[] buildCommand() {
		commands.add("java");
		commands.add("-jar");
		commands.add(JavaController.getUserDir() + "/Runnables/checkstyle-6.18-all.jar");
		commands.add("-c");
		commands.add(((CheckStyleSettings) getSettings()).getConfigFile());
		commands.add("-f");
		commands.add("xml");
		commands.add(JavaController.getUserDir() + "/Runnables/Testcode/");
		String[] retCommands = commands.toArray(new String[commands.size()]);
		return retCommands;
	}
	
}
