package BlueTurtle.TSE;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;

/**
 * Analyses java projects.
 * 
 * @author BlueTurtle.
 */
public class Analyser {
	private ArrayList<AnalyserCommand> commands;

	/**
	 * Constructor.
	 * 
	 * @param commands
	 *            the commands for the analyser.
	 */
	public Analyser(ArrayList<AnalyserCommand> commands) {
		this.commands = commands;
	}

	/**
	 * Analyse creates a ProcessBuilder for each command. The output is
	 * redirected to the output file specified in the AnalyserCommand.
	 * 
	 * @throws IOException
	 *             throws an exception if a problem is encountered while
	 *             building the processes.
	 */
	public void analyse() throws IOException {

		for (AnalyserCommand command : commands) {
			ProcessBuilder pb = new ProcessBuilder(command.getArgs());
			pb.redirectOutput(Redirect.INHERIT);
			pb.redirectError(Redirect.INHERIT);
			Process process = pb.start();
			try {
				process.waitFor();
			} catch (Exception e) {
				e.printStackTrace();
			}
			process.getOutputStream().flush();
			process.destroy();
		}
	}

	public ArrayList<AnalyserCommand> getCommands() {
		return commands;
	}

	public void setCommands(ArrayList<AnalyserCommand> commands) {
		this.commands = commands;
	}
}
