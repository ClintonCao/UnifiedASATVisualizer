package BlueTurtle.commandbuilders;

import BlueTurtle.interfaces.Settings;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/**
 * Builds commands for running an ASAT according to the settings.
 * 
 * @author BlueTurtle.
 *
 */
@SuppressWarnings("checkstyle:visibilitymodifier")
public abstract class CommandBuilder {
	@Getter @Setter protected ArrayList<String> commands;
	@Getter @Setter private Settings settings;
	
	/**
	 * Abstract constructor for CommandBuilder, commands is initialized.
	 */
	public CommandBuilder() {
		commands = new ArrayList<String>();
	}

	/**
	 * Build a command.
	 * 
	 * @return console command, each argument is a separate value in the array.
	 */
	public abstract String[] buildCommand();
}
