package BlueTurtle.TSE;

import java.io.IOException;
import java.util.ArrayList;
import BlueTurtle.commandbuilders.CheckStyleCommandBuilder;
import BlueTurtle.commandbuilders.CoberturaCommandBuilder;
import BlueTurtle.commandbuilders.CommandBuilder;
import BlueTurtle.commandbuilders.FindBugsCommandBuilder;
import BlueTurtle.commandbuilders.PMDCommandBuilder;
import BlueTurtle.interfaces.Controller;
import BlueTurtle.settings.CheckStyleSettings;
import BlueTurtle.settings.CoberturaSettings;
import BlueTurtle.settings.FindBugsSettings;
import BlueTurtle.settings.PMDSettings;
import lombok.Getter;
import lombok.Setter;

/**
 * JavaController controls the analyser to make it analyse java code. It
 * constructs an AnalyserCommand for every ASAT which has to be run and passes
 * this to the analyser.
 * 
 * @author BlueTurtle.
 *
 */
public class JavaController implements Controller {
	@Getter @Setter private Analyser analyser;
	@Getter @Setter private static String userDir = System.getProperty("user.dir");
	private CommandBuilder commandBuilder;
	private PMDSettings pmdSettings = PMDSettings.getInstance();
	private CheckStyleSettings checkStyleSettings = CheckStyleSettings.getInstance();
	private CoberturaSettings coberturaSettings = new CoberturaSettings();
	private JSONFormatter jsonFormatter = new JSONFormatter();
	private FindBugsSettings findBugsSettings = FindBugsSettings.getInstance();
	
	/**
	 * Execute controller. A command is constructed for every ASAT which needs to be run. 
	 * 
	 * @throws IOException
	 *             throws an exception if a problem is encountered when
	 *             executing the commands.
	 */
	public void execute() throws IOException {
		ArrayList<AnalyserCommand> commands = new ArrayList<AnalyserCommand>();

		commandBuilder = new PMDCommandBuilder(pmdSettings);
		String[] pmdCommands = commandBuilder.buildCommand();
		AnalyserCommand c1 = new AnalyserCommand(pmdSettings.getDefaultOutputFilePath(), pmdCommands, true);
		commands.add(c1);

		commandBuilder = new CheckStyleCommandBuilder(checkStyleSettings);
		String[] checkStyleCommands = commandBuilder.buildCommand();
		AnalyserCommand c2 = new AnalyserCommand(checkStyleSettings.getDefaultOutputFilePath(), checkStyleCommands, false);
		commands.add(c2);

		commandBuilder = new CoberturaCommandBuilder(coberturaSettings);
		String[] coberturaCommands = commandBuilder.buildCommand();
		AnalyserCommand c3 = new AnalyserCommand(coberturaSettings.getDefaultOutputFilePath(), coberturaCommands, false);
		commands.add(c3);
		
		commandBuilder = new FindBugsCommandBuilder(findBugsSettings);
		String[] findBugsCommands = commandBuilder.buildCommand();
		AnalyserCommand c4 = new AnalyserCommand(findBugsSettings.getDefaultOutputFilePath(), findBugsCommands, false);
		commands.add(c4);

		setAnalyser(new Analyser(commands));

		analyser.analyse();
		
		jsonFormatter.format();
	}
}
