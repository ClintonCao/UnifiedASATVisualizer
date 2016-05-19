package BlueTurtle.commandbuilders;

import BlueTurtle.TSE.JavaController;
import BlueTurtle.settings.PMDSettings;

/**
 * Class to build commands for PMD.
 * 
 * @author BlueTurtle.
 *
 */
public class PMDCommandBuilder extends CommandBuilder {

	/**
	 * Constructor.
	 * 
	 * @param pmdSettings
	 *            the settings for PMD.
	 */
	public PMDCommandBuilder(PMDSettings pmdSettings) {
		super();
		this.setSettings(pmdSettings);
	}

	/**
	 * Build the command.
	 * 
	 * @return a string array that represents the command.
	 */
	@Override
	public String[] buildCommand() {
		commands.add("java");
		commands.add("-jar");
		commands.add(JavaController.getUserDir() + "/Runnables/pmd-bin-4.2.6/lib/pmd-4.2.6.jar");
		commands.add(JavaController.getUserDir() + "/Runnables/Testcode/");
		commands.add("xml");
		commands.add("basic");
		commands.add(">");
		commands.add(JavaController.getUserDir() + "Runnables/Testcode/PMD.xml");
		String[] retCommands = commands.toArray(new String[commands.size()]);
		return retCommands;
	}
}
