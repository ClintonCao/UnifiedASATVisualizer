package BlueTurtle.commandbuilders;

import BlueTurtle.interfaces.Settings;
import lombok.Getter;
import lombok.Setter;

/**
 * Builds commands for running an ASAT according to the settings.
 * 
 * @author BlueTurtle.
 *
 */
@SuppressWarnings("checkstyle:visibilitymodifier")
public abstract class CommandBuilder {
	@Getter @Setter private Settings settings;
	
	/**
	 * Build a command.
	 * 
	 * @return console command, each argument is a separate value in the array.
	 */
	public abstract String[] buildCommand();
}
