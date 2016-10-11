package BlueTurtle.TSE;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import BlueTurtle.gui.GUIController.ASAT;

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
	@Getter @Setter private static String userDir = System.getProperty("user.dir"); //NOPMD - caused by lombok.
	@Getter @Setter private static String checkStyleOutputFile; //NOPMD - caused by lombok.
	@Getter @Setter private static String pmdOutputFile; //NOPMD - caused by lombok.
	@Getter @Setter private static String findBugsOutputFile; //NOPMD - caused by lombok.
	
	@Getter @Setter private static ArrayList<String> checkStyleOutputFiles; //NOPMD - caused by lombok.
	@Getter @Setter private static ArrayList<String> pmdOutputFiles; //NOPMD - caused by lombok.
	@Getter @Setter private static ArrayList<String> findBugsOutputFiles; //NOPMD - caused by lombok.

	/**
	 * Execute controller. A command is constructed for every ASAT which needs
	 * to be run.
	 * 
	 * @throws IOException
	 *             throws an exception if a problem is encountered when
	 *             executing the commands.
	 */
	public void execute() throws IOException {
		new JSONFormatter().format();
	}

	/**
	 * Set the output path for the ASAT.
	 * 
	 * @param asat
	 *            the asat type.
	 * @param file
	 *            the output file.
	 */
	public static void setASATOutput(ASAT asat, File file) {
		if (file == null) {
			return;
		}
		switch (asat) {
		case PMD:
			pmdOutputFile = file.getAbsolutePath();
			break;
		case CheckStyle:
			checkStyleOutputFile = file.getAbsolutePath();
			break;
		case FindBugs:
			findBugsOutputFile = file.getAbsolutePath();
			break;
		default:
			break;
		}
	}
	
	/**
	 * Set the output paths for the ASAT.
	 * 
	 * @param asat
	 *            the ASAT type.
	 * @param filePaths
	 *            the list of output file paths.
	 */
	public static void setASATOutputFiles(ASAT asat, ArrayList<String> filePaths) {
		
		System.out.println(asat);
		if (filePaths == null || filePaths.isEmpty()) {
			return;
		}
		switch (asat) {
		case PMD:
			pmdOutputFiles = filePaths;
			break;
		case CheckStyle:
			checkStyleOutputFiles = filePaths;
			break;
		case FindBugs:
			findBugsOutputFiles = filePaths;
			break;
		default:
			break;
		}
	}
}
