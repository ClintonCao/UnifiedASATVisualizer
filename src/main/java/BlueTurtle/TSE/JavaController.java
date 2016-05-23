package BlueTurtle.TSE;

import java.io.File;
import java.io.IOException;

import BlueTurtle.gui.GUIController.ASAT;
import BlueTurtle.interfaces.Controller;
import lombok.Getter;
import lombok.Setter;

/**
 * JavaController controls the analyser to make it analyse java code. It
 * constructs an AnalyserCommand for every ASAT which has to be run and passes
 * this to the analyser.
 * 
 * @author BlueTurtle.
 *
 */
public class JavaController implements Controller {
	@Getter @Setter private static String userDir = System.getProperty("user.dir");
	@Getter @Setter private static String checkStyleOutputFile;
	@Getter @Setter private static String PMDOutputFile;
	@Getter @Setter private static String findBugsOutputFile;

	/**
	 * Execute controller. A command is constructed for every ASAT which needs to be run. 
	 * 
	 * @throws IOException
	 *             throws an exception if a problem is encountered when
	 *             executing the commands.
	 */
	public void execute() throws IOException {
		JSONFormatter jsonFormatter = new JSONFormatter();
		jsonFormatter.format();
	}
	
	public static void setASATOutput(ASAT asat, File file) {
		if(file == null) {
			return;
		}
		switch (asat) {
			case PMD:
				PMDOutputFile = file.getAbsolutePath();
				break;
			case Checkstyle:
				checkStyleOutputFile = file.getAbsolutePath();
				break;
			case Findbugs:
				findBugsOutputFile = file.getAbsolutePath();
				break;
		default:
			break;
		}
	}
}
