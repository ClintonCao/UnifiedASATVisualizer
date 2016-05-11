package BlueTurtle.commandbuilders;

import java.util.ArrayList;
import BlueTurtle.settings.FindBugsSettings;

public class FindBugsCommandBuilder extends CommandBuilder {

	public FindBugsCommandBuilder(FindBugsSettings findBugsSettings) {
		this.commands = new ArrayList<String>();
		this.setSettings(findBugsSettings);
	}

	@Override
	public String[] buildCommand() {


				//C:\Program Files (x86)\findbugs-3.0.1\bin>findbugs -textui -maxHeap 200 -nested:false -output C:\Users\michiel\workspace\Contextproject-TSE\Runnables\Testcode\f
				//indbugs.html -effort:min -low -sortByClass -html:fancy.xsl C:\Users\michiel\workspace\Contextproject-TSE\Runnables\context_findbugs.jar
		
		String[] retCommands = commands.toArray(new String[commands.size()]);
		return retCommands;
	}
}
