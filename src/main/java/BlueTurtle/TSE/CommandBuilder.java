package BlueTurtle.TSE;

import BlueTurtle.interfaces.Settings;
import java.util.ArrayList;

/**
 * Builds commands for running an ASAT according to the settings.
 * @author Michiel
 *
 */
public abstract class CommandBuilder {
	protected ArrayList<String> commands;
	private Settings settings;
	
	/**
	 * @return console command, each argument is a separate value in the array.
	 */
	public abstract String[] buildCommand();
	
	public Settings getSettings(){
		return settings;
	}
	
	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	public ArrayList<String> getCommands() {
		return commands;
	}

	public void setCommands(ArrayList<String> commands) {
		this.commands = commands;
	}


}
