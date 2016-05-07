package BlueTurtle.TSE;

import java.util.ArrayList;

import BlueTurtle.settings.PMDSettings;

public class PMDCommandBuilder {
	private ArrayList<String> commands = new ArrayList<String>();
	private PMDSettings pmdSettings;
	
	public PMDCommandBuilder(PMDSettings pmdSettings) {
		this.setPmdSettings(pmdSettings);
	}
	
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

	public PMDSettings getPmdSettings() {
		return pmdSettings;
	}

	public void setPmdSettings(PMDSettings pmdSettings) {
		this.pmdSettings = pmdSettings;
	}
}
