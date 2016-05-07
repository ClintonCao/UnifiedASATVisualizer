package BlueTurtle.TSE;

import java.io.IOException;
import java.util.ArrayList;

import BlueTurtle.interfaces.Controller;
import BlueTurtle.settings.CheckStyleSettings;
import BlueTurtle.settings.CoberturaSettings;
import BlueTurtle.settings.PMDSettings;

public class JavaController implements Controller {
	private Analyser analyser;
	private static String userDir = System.getProperty("user.dir");
	private PMDSettings pmdSettings = new PMDSettings();
	private PMDCommandBuilder pmdCommandBuilder = new PMDCommandBuilder(pmdSettings);
	private CheckStyleSettings checkStyleSettings = new CheckStyleSettings();
	private CheckStyleCommandBuilder checkStyleCommandBuilder = new CheckStyleCommandBuilder(checkStyleSettings);

	/**
	 * Execute controller.
	 * @throws IOException 
	 */
	public void execute() throws IOException {
		ArrayList<AnalyserCommand> commands = new ArrayList<AnalyserCommand>();
		
		String[] pmdCommands = pmdCommandBuilder.buildCommand();
		AnalyserCommand c1 = new AnalyserCommand(pmdSettings, pmdCommands);
		commands.add(c1);

		String[] checkStyleCommands = checkStyleCommandBuilder.buildCommand();
		AnalyserCommand c2 = new AnalyserCommand(checkStyleSettings, checkStyleCommands);
		commands.add(c2);

		String[] coberturaCommands = {"C:/Users/michiel/workspace/Contextproject-TSE/Runnables/cobertura-2.1.1/cobertura-report.bat", "--format", "xml", "--destination", userDir + "/Runnables/Testcode/cobertura", userDir + "/Runnables/Testcode/"};
		CoberturaSettings coberturaSettings = new CoberturaSettings();
		AnalyserCommand c3 = new AnalyserCommand(coberturaSettings, coberturaCommands);
		commands.add(c3);
		
//		ProcessBuilder pb = new ProcessBuilder(coberturaCommands);
//		pb.start();

		setAnalyser(new Analyser(commands));

		analyser.analyse();
	}

	public void setAnalyser(Analyser analyser) {
		this.analyser = analyser;
	}

	public Analyser getAnalyser() {
		return this.analyser;
	}
	
	public static String getUserDir() {
		return userDir;
	}
	
	public static void setUserDir(String newUserDir) {
		userDir = newUserDir;
	}
}
