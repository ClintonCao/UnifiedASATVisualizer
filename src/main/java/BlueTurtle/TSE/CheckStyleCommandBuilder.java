package BlueTurtle.TSE;

import java.util.ArrayList;

import BlueTurtle.interfaces.CommandBuilder;
import BlueTurtle.settings.CheckStyleSettings;

public class CheckStyleCommandBuilder implements CommandBuilder {
	private ArrayList<String> commands = new ArrayList<String>();
	private CheckStyleSettings checkStyleSettings;
	
	public CheckStyleCommandBuilder(CheckStyleSettings checkStyleSettings) {
		this.setSettings(checkStyleSettings);
	}
	
	public String[] buildCommand() {
		commands.add("java");
		commands.add("-jar");
		commands.add(JavaController.getUserDir() + "/Runnables/checkstyle-6.18-all.jar");
		commands.add("-c");
		commands.add("/sun_checks.xml");
		commands.add("-f");
		commands.add("xml");
		commands.add(JavaController.getUserDir() + "/Runnables/Testcode/");
		String[] retCommands = commands.toArray(new String[commands.size()]);
		return retCommands;
	}

	public CheckStyleSettings getSettings() {
		return checkStyleSettings;
	}

	public void setSettings(CheckStyleSettings checkStyleSettings) {
		this.checkStyleSettings = checkStyleSettings;
	}

}
