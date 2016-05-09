package BlueTurtle.TSE;

import java.util.ArrayList;

import BlueTurtle.settings.CheckStyleSettings;

public class CheckStyleCommandBuilder extends CommandBuilder {
		
	public CheckStyleCommandBuilder(CheckStyleSettings checkStyleSettings) {
		this.commands = new ArrayList<String>();
		this.setSettings(checkStyleSettings);
	}
	
	@Override
	public String[] buildCommand() {
		commands.add("java");
		commands.add("-jar");
		commands.add(JavaController.getUserDir() + "/Runnables/checkstyle-6.18-all.jar");
		commands.add("-c");
		commands.add(((CheckStyleSettings) getSettings()).getConfigFile());
		//commands.add("/sun_checks.xml");
		commands.add("-f");
		commands.add("xml");
		commands.add(JavaController.getUserDir() + "/Runnables/Testcode/");
		String[] retCommands = commands.toArray(new String[commands.size()]);
		return retCommands;
	}
	
}
