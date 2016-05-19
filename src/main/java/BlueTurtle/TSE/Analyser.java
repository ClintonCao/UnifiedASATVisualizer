package BlueTurtle.TSE;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

/**
 * Analyses java projects.
 * 
 * @author BlueTurtle.
 */
@AllArgsConstructor
public class Analyser {
	@Setter @Getter private ArrayList<AnalyserCommand> commands;

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
			if(command.isRedirectOutput()) {
				pb.redirectOutput(new File(command.getDefaultOutputFilePath()));
			} else {
				pb.redirectOutput(Redirect.INHERIT);
			}
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
}
