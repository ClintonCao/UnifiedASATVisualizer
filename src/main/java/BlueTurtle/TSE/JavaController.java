package BlueTurtle.TSE;

import java.io.IOException;
import java.util.ArrayList;

public class JavaController implements Controller {

	private Analyser analyser;
	private static String userDir = System.getProperty("user.dir");
	
	/**
	 * Execute controller.
	 * @throws IOException 
	 */
	public void execute() throws IOException {
    	ArrayList<Command> commands = new ArrayList<Command>();
		
    	String[] pmdCommands = {"java", "-jar", userDir + "/Runnables/pmd-bin-4.2.6/lib/pmd-4.2.6.jar", userDir + "/Runnables/Testcode/ClintonCode.java", "xml", "basic"};
    	PMDSettings pmdSettings = new PMDSettings();
    	Command c1 = new Command(pmdSettings, pmdCommands);
    	commands.add(c1);
    	
    	String[] checkStyleCommands = {"java", "-jar", userDir + "/Runnables/checkstyle-6.18-all.jar", "-c", "/sun_checks.xml", "-f", "xml", userDir + "/Runnables/Testcode/ClintonCode.java"};
    	CheckStyleSettings checkStyleSettings = new CheckStyleSettings();
    	Command c2 = new Command(checkStyleSettings, checkStyleCommands);
    	
    	commands.add(c2);
    	
    	setAnalyser(new Analyser(commands));
    	
		analyser.analyse();
	}
	
	public void setAnalyser(Analyser analyser) {
		this.analyser = analyser;
	}
	
	public Analyser getAnalyser() {
		return this.analyser;
	}
}
