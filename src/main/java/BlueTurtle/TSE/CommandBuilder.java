package BlueTurtle.TSE;

import BlueTurtle.interfaces.Settings;
import java.util.ArrayList;

public abstract class CommandBuilder {
	protected ArrayList<String> commands;
	private Settings settings;
	
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
