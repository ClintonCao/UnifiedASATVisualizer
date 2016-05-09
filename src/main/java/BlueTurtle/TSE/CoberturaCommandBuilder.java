package BlueTurtle.TSE;

import java.util.ArrayList;

import BlueTurtle.interfaces.Settings;
import BlueTurtle.settings.CoberturaSettings;

public class CoberturaCommandBuilder extends CommandBuilder {
	private ArrayList<String> commands = new ArrayList<String>();
	
	public CoberturaCommandBuilder(CoberturaSettings coberturaSettings) {
		this.setSettings(coberturaSettings);
	}
	
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
