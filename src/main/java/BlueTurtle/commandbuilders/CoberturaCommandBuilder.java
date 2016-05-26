package BlueTurtle.commandbuilders;

import java.util.ArrayList;

import BlueTurtle.TSE.JavaController;
import BlueTurtle.settings.CoberturaSettings;

/**
 * Class to build commands for Cobertura.
 * 
 * @author BlueTurtle.
 */
public class CoberturaCommandBuilder extends CommandBuilder {

	/**
	 * Constructor.
	 * 
	 * @param coberturaSettings
	 *            the settings for cobetura.
	 */
	public CoberturaCommandBuilder(CoberturaSettings coberturaSettings) {
		super();
		this.setSettings(coberturaSettings);
	}

	/**
	 * {@inheritDoc} Cobertura is run as a .bat file. Output is to
	 * Runnables/Testcode/cobertura for now.
	 * 
	 * @return return a string array that represents the command.
	 */
	@Override
	public String[] buildCommand() {
		ArrayList<String> commands  = new ArrayList<String>();
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
