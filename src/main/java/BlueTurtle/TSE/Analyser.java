package BlueTurtle.TSE;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;

/**
 * Analyses java projects.
 * @author michiel
 */
public class Analyser {
	private ArrayList<CommandUnit> commands; 
	
	/**
	 * Constructor.
	 * @param commands
	 */
	public Analyser(ArrayList<CommandUnit> commands) {
		this.commands = commands;
	}
	
	/**
	 * Analyse creates a ProcessBuilder for each command. The output is redirected to the output file specified in Command.
	 * @throws IOException
	 */
	public void analyse() throws IOException {
		
		for(CommandUnit command: commands) {
			ProcessBuilder pb = new ProcessBuilder(command.getArgs());
			pb.redirectOutput(Redirect.to(new File(command.getOutputFilePath())));
	        pb.redirectError(Redirect.INHERIT);
	        pb.start();
		}
		
	}
}
