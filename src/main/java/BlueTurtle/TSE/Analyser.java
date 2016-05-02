package BlueTurtle.TSE;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;

/**
 * Analyses java projects.
 * @author michiel
 *
 */
public class Analyser {
	
	private ArrayList<Command> commands; 
	
	public Analyser(ArrayList<Command> commands) {
		this.commands = commands;
	}
	
	public void analyse() throws IOException {
		
		for(Command command: commands) {
			ProcessBuilder pb = new ProcessBuilder(command.getArgs());
	        pb.redirectOutput(Redirect.to(new File(command.getSettings().getOutputFilePath())));
	        pb.redirectError(Redirect.INHERIT);
	        pb.start();
		}
	}
}
