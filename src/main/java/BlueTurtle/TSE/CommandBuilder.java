package BlueTurtle.TSE;

import BlueTurtle.interfaces.Settings;

public abstract class CommandBuilder {
	public abstract String[] buildCommand();
	
	public abstract Settings getSettings();
}
