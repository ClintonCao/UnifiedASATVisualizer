package BlueTurtle.TSE;

import java.util.ArrayList;

import BlueTurtle.interfaces.CommandBuilder;
import BlueTurtle.interfaces.Settings;
import BlueTurtle.settings.CoberturaSettings;

public class CoberturaCommandBuilder implements CommandBuilder {
	private ArrayList<String> commands = new ArrayList<String>();
	private CoberturaSettings coberturaSettings;
	
	public CoberturaCommandBuilder(CoberturaSettings coberturaSettings) {
		this.coberturaSettings = coberturaSettings;
	}
	
	public String[] buildCommand() {
		//String[] coberturaCommands = {"C:/Users/michiel/workspace/Contextproject-TSE/Runnables/cobertura-2.1.1/cobertura-report.bat", "--format", "xml", 
			//	"--destination", userDir + "/Runnables/Testcode/cobertura", userDir + "/Runnables/Testcode/"};
		commands.add("C:/Users/michiel/workspace/Contextproject-TSE/Runnables/cobertura-2.1.1/cobertura-report.bat");
		commands.add("--format");
		commands.add("xml");
		commands.add("--destination");
		commands.add(JavaController.getUserDir() + "/Runnables/Testcode/cobertura");
		commands.add(JavaController.getUserDir() + "/Runnables/Testcode/");
		String[] retCommands = commands.toArray(new String[commands.size()]);
		return retCommands;
	}
	
	public Settings getSettings() {
		return coberturaSettings;
	}
	
	public void setSettings(CoberturaSettings coberturaSettings) {
		this.coberturaSettings = coberturaSettings;
	}

}
