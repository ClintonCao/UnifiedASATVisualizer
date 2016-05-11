package BlueTurtle.TSE;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import BlueTurtle.finders.PackageNameFinder;
import BlueTurtle.groupers.WarningGrouper;
import BlueTurtle.parsers.CheckStyleXMLParser;
import BlueTurtle.parsers.PMDXMLParser;
import BlueTurtle.parsers.XMLParser;
import BlueTurtle.summarizers.Summarizer;
import BlueTurtle.warnings.Warning;
import BlueTurtle.writers.JSWriter;

public class JSONFormatter {
	private XMLParser parser;
	
	public void format() throws IOException {
		this.parseCheckStyleXML();
		//this.parsePMDXML();
	}
	
	private void parseCheckStyleXML() throws IOException {
		parser = new CheckStyleXMLParser();
		
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
	}
	
	private void parsePMDXML() throws IOException {
		parser = new PMDXMLParser();
		
		List<Warning> PMDWarnings = parser.parseFile(JavaController.getUserDir() + "/Runnables/Testcode/pmd.xml");

		System.out.println("amount of PMD Warnings:" + " " + PMDWarnings.size());

		for (Warning w : PMDWarnings) {
			System.out.println("Violated Rule Name: " + w.getRuleName());
		}

		HashMap<String, String> componentsInfo = new HashMap<String, String>();
		Set<String> packagesNames = new HashSet<String>();

		for (Warning w : PMDWarnings) {
			componentsInfo.put(w.getFileName(), w.getFilePath());
			packagesNames.add(PackageNameFinder.findPackageName(w.getFilePath()));
		}

		WarningGrouper wg = new WarningGrouper(componentsInfo, packagesNames, PMDWarnings);
		List<Summarizer> list = wg.groupBy("packages");

		JSWriter jwriter = new JSWriter(list);
		jwriter.writeToJSFormat("./src/main/resources/SummarizedOuput.js");	
	}
}
