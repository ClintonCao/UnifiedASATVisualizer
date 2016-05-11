package BlueTurtle.TSE;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import BlueTurtle.finders.PackageNameFinder;
import BlueTurtle.groupers.WarningGrouper;
import BlueTurtle.interfaces.Controller;
import BlueTurtle.parsers.CheckStyleXMLParser;
import BlueTurtle.parsers.PMDXMLParser;
import BlueTurtle.parsers.XMLParser;
import BlueTurtle.summarizers.Summarizer;
import BlueTurtle.warnings.Warning;
import BlueTurtle.writers.JSWriter;

/**
 * Temporary main class to run the commands.
 * 
 * @author BlueTurtle.
 *
 */
public class Main {

	/**
	 * All the modes (programming languages).
	 * 
	 * @author BlueTurtle.
	 *
	 */
	enum mode {
		JAVA
	}

	static Controller controller;
	static mode currentMode = mode.JAVA;

	/**
	 * Maind method.
	 * 
	 * @param args
	 *            the arguments.
	 * @throws IOException
	 *             throws an exception if a problem is encountered during the
	 *             execution of the controller.
	 */
	public static void main(String[] args) throws IOException {
		switch (currentMode) {
		case JAVA:
			controller = new JavaController();
			break;
		default:
			break;
		}
		controller.execute();
		
		XMLParser parser = new CheckStyleXMLParser();
			
		List<Warning> checkStyleWarnings = parser.parseFile(JavaController.getUserDir() + "/Runnables/Testcode/checkstyle.xml");

		System.out.println("amount of CheckStyle Warnings:" + " " + checkStyleWarnings.size());

		for (Warning w : checkStyleWarnings) {
			System.out.println("Violated Rule Name: " + w.getRuleName());
		}

		HashMap<String, String> componentsInfo = new HashMap<String, String>();
		Set<String> packagesNames = new HashSet<String>();

		for (Warning w : checkStyleWarnings) {
			componentsInfo.put(w.getFileName(), w.getFilePath());
			packagesNames.add(PackageNameFinder.findPackageName(w.getFilePath()));
		}

		WarningGrouper wg = new WarningGrouper(componentsInfo, packagesNames, checkStyleWarnings);
		List<Summarizer> list = wg.groupBy("packages");

		JSWriter jwriter = new JSWriter(list);
		jwriter.writeToJSFormat("./src/main/resources/SummarizedOuput.js");
		
		System.out.println("Done.");
	}
}
