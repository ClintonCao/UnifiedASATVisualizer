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
		ArrayList<CommandUnit> commands = new ArrayList<CommandUnit>();

		String[] pmdCommands = {"java", "-jar", userDir + "/Runnables/pmd-bin-4.2.6/lib/pmd-4.2.6.jar", userDir + "/Runnables/Testcode/", "xml", "basic"};
		PMDSettings pmdSettings = new PMDSettings();
		CommandUnit c1 = new CommandUnit(pmdSettings, pmdCommands);
		commands.add(c1);

		String[] checkStyleCommands = {"java", "-jar", userDir + "/Runnables/checkstyle-6.18-all.jar", "-c", "/sun_checks.xml", "-f", "xml", userDir + "/Runnables/Testcode/"};
		CheckStyleSettings checkStyleSettings = new CheckStyleSettings();
		CommandUnit c2 = new CommandUnit(checkStyleSettings, checkStyleCommands);

		commands.add(c2);
		//C:\Users\michiel\workspace\Contextproject-TSE\Runnables\cobertura-2.1.1\cobertura-report.bat --format xml --destination C:\Users\michiel\workspace\Contextproject-TSE\Runnables\Testcode\cobertura C:\Users\michiel\workspace\Contextproject-TSE\Runnables\Testcode\ClintonCode
		//cobertura-report.bat --format html --datafile C:\MyProject\build\cobertura.ser --destination C:\MyProject\reports\coverage C:\MyProject\src

		String[] coberturaCommands = {"C:/Users/michiel/workspace/Contextproject-TSE/Runnables/cobertura-2.1.1/cobertura-report.bat", "--format", "xml", "--destination", userDir + "/Runnables/Testcode/cobertura", userDir + "/Runnables/Testcode/"};

		ProcessBuilder pb = new ProcessBuilder(coberturaCommands);
		pb.start();


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
