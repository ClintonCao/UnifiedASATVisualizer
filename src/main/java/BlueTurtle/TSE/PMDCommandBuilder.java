package BlueTurtle.TSE;

import java.util.ArrayList;

import BlueTurtle.settings.PMDSettings;

/**
 *
 * @author Michiel
 *
 */
public class PMDCommandBuilder extends CommandBuilder {
	private ArrayList<String> commands = new ArrayList<String>();
	
	public PMDCommandBuilder(PMDSettings pmdSettings) {
		this.setSettings(pmdSettings);
	}
	
	@Override
	public String[] buildCommand() {
		commands.add("java");
		commands.add("-jar");
		commands.add(JavaController.getUserDir() + "/Runnables/pmd-bin-4.2.6/lib/pmd-4.2.6.jar");
		commands.add(JavaController.getUserDir() + "/Runnables/Testcode/");
		commands.add("xml");
		commands.add("basic");
		String[] retCommands = commands.toArray(new String[commands.size()]);
		return retCommands;
	}

}
