package BlueTurtle.TSE;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import BlueTurtle.commandbuilders.CheckStyleCommandBuilder;
import BlueTurtle.commandbuilders.CoberturaCommandBuilder;
import BlueTurtle.commandbuilders.CommandBuilder;
import BlueTurtle.commandbuilders.PMDCommandBuilder;
import BlueTurtle.finders.PackageNameFinder;
import BlueTurtle.groupers.WarningGrouper;
import BlueTurtle.interfaces.Controller;
import BlueTurtle.parsers.CheckStyleXMLParser;
import BlueTurtle.parsers.PMDXMLParser;
import BlueTurtle.parsers.XMLParser;
import BlueTurtle.settings.CheckStyleSettings;
import BlueTurtle.settings.CoberturaSettings;
import BlueTurtle.settings.PMDSettings;
import BlueTurtle.summarizers.Summarizer;
import BlueTurtle.warnings.Warning;
import BlueTurtle.writers.JSWriter;

/**
 * JavaController controls the analyser to make it analyse java code. It
 * constructs an AnalyserCommand for every ASAT which has to be run and passes
 * this to the analyser.
 * 
 * @author BlueTurtle.
 *
 */
public class JavaController implements Controller {
	private Analyser analyser;
	private static String userDir = System.getProperty("user.dir");
	private CommandBuilder commandBuilder;
	private PMDSettings pmdSettings = new PMDSettings();
	private CheckStyleSettings checkStyleSettings = new CheckStyleSettings(new File("CheckStyle_Settings.xml"));
	private CoberturaSettings coberturaSettings = new CoberturaSettings();
	private JSONFormatter jsonFormatter = new JSONFormatter();

	/**
	 * Execute controller.
	 * 
	 * @throws IOException
	 *             throws an exception if a problem is encountered when
	 *             executing the commands.
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
		
		jsonFormatter.format();
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
