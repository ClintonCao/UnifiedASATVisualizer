package BlueTurtle.TSE;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import BlueTurtle.interfaces.Controller;
import BlueTurtle.settings.CheckStyleSettings;
import BlueTurtle.settings.CoberturaSettings;
import BlueTurtle.settings.PMDSettings;

/**
 * JavaController controls the analyser to make it analyse java code. 
 * It constructs an AnalyserCommand for every ASAT which has to be run and passes this to the analyser.
 * @author Michiel
 *
 */
public class JavaController implements Controller {
	private Analyser analyser;
	private static String userDir = System.getProperty("user.dir");
	private CommandBuilder commandBuilder;
	private PMDSettings pmdSettings = new PMDSettings();
	private CheckStyleSettings checkStyleSettings = new CheckStyleSettings(new File("CheckStyle_Settings.xml"));
	private CoberturaSettings coberturaSettings = new CoberturaSettings();
	
	/**
	 * Execute controller.
	 * @throws IOException 
	 */
	public void execute() throws IOException {
		ArrayList<AnalyserCommand> commands = new ArrayList<AnalyserCommand>();
		
		commandBuilder = new PMDCommandBuilder(pmdSettings);
		String[] pmdCommands = commandBuilder.buildCommand();
		AnalyserCommand c1 = new AnalyserCommand(pmdSettings.getDefaultOutputFilePath(), pmdCommands);
		commands.add(c1);

		commandBuilder = new CheckStyleCommandBuilder(checkStyleSettings);
		String[] checkStyleCommands = commandBuilder.buildCommand();
		AnalyserCommand c2 = new AnalyserCommand(checkStyleSettings.getDefaultOutputFilePath(), checkStyleCommands);
		commands.add(c2);
		
		commandBuilder = new CoberturaCommandBuilder(coberturaSettings);
		String[] coberturaCommands = commandBuilder.buildCommand();
		AnalyserCommand c3 = new AnalyserCommand(coberturaSettings.getDefaultOutputFilePath(), coberturaCommands);
		commands.add(c3);
		
		setAnalyser(new Analyser(commands));

		analyser.analyse();
	}

	public void setAnalyser(Analyser analyser) {
		this.analyser = analyser;
	}

	public Analyser getAnalyser() {
		return this.analyser;
	}
	
	public static String getUserDir() {
		return userDir;
	}
	
	public static void setUserDir(String newUserDir) {
		userDir = newUserDir;
	}
}
