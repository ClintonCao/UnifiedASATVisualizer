package BlueTurtle.commandbuilders;

import java.util.ArrayList;

import BlueTurtle.TSE.CommandBuilder;
import BlueTurtle.TSE.JavaController;
import BlueTurtle.settings.CoberturaSettings;

/**
 * @author Michiel
 */
public class CoberturaCommandBuilder extends CommandBuilder {
	
	/**
	 * Constructor.
	 * @param coberturaSettings
	 */
	public CoberturaCommandBuilder(CoberturaSettings coberturaSettings) {
		commands = new ArrayList<String>();
		this.setSettings(coberturaSettings);
	}
	
	/**
	 * {@inheritDoc}
	 * Cobertura is run as a .bat file. Output is to Runnables/Testcode/cobertura for now.
	 * @return
	 */
	@Override
	public String[] buildCommand() {
		commands.add(JavaController.getUserDir() + "/Runnables/cobertura-2.1.1/cobertura-report.bat");
		commands.add("--format");
		commands.add("xml");
		commands.add("--destination");
		commands.add(JavaController.getUserDir() + "/Runnables/Testcode/cobertura");
		commands.add(JavaController.getUserDir() + "/Runnables/Testcode/");
		String[] retCommands = commands.toArray(new String[commands.size()]);
		return retCommands;
	}
	
}
