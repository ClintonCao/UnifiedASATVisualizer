package BlueTurtle.commandbuilders;

import BlueTurtle.TSE.JavaController;
import BlueTurtle.settings.FindBugsSettings;

/**
 * Builds command line command for running FindBugs.
 * @author michiel
 *
 */
public class FindBugsCommandBuilder extends CommandBuilder {

	public FindBugsCommandBuilder(FindBugsSettings findBugsSettings) {
		super();
		this.setSettings(findBugsSettings);
	}

	/**
	 * Build the command.
	 * 
	 * @return a string array that represents the command.
	 */
	@Override
	public String[] buildCommand() {

		commands.add(JavaController.getUserDir() + "/Runnables/findbugs-3.0.1/bin/findbugs.bat");
		commands.add("-textui");
		commands.add("-maxHeap");
		commands.add("256");
		commands.add("-nested:false");
		commands.add("-output");
		commands.add(JavaController.getUserDir() + "/Runnables/Testcode/findbugs.xml");
		commands.add("-effort:min");
		commands.add("-low");
		commands.add("-sortByClass");
		commands.add("-xml:withMessages");
		commands.add(JavaController.getUserDir() + "/Runnables/context_findbugs.jar");
				//C:\Program Files (x86)\findbugs-3.0.1\bin>findbugs -textui -maxHeap 200 -nested:false -output C:\Users\michiel\workspace\Contextproject-TSE\Runnables\Testcode\f
				//indbugs.html -effort:min -low -sortByClass -html:fancy.xsl C:\Users\michiel\workspace\Contextproject-TSE\Runnables\context_findbugs.jar
		
		String[] retCommands = commands.toArray(new String[commands.size()]);
		return retCommands;
	}
}
