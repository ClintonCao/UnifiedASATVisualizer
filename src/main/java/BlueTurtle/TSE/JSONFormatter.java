package BlueTurtle.TSE;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import BlueTurtle.finders.PackageNameFinder;
import BlueTurtle.groupers.WarningGrouper;
import BlueTurtle.parsers.CheckStyleXMLParser;
import BlueTurtle.parsers.GDCParser;
import BlueTurtle.parsers.PMDXMLParser;
import BlueTurtle.parsers.XMLParser;
import BlueTurtle.summarizers.Summarizer;
import BlueTurtle.warnings.Warning;
import BlueTurtle.writers.JSWriter;

public class JSONFormatter {
	private XMLParser xmlParser;
	private GDCParser gdcParser = new GDCParser();
	HashMap<String, String> categoryInfo = gdcParser.parseFile("./src/main/resources/asat-gdc-mapping.html");
	
	public void format() throws IOException {
		List<Warning> checkStyleWarnings = parseCheckStyleXML();
		List<Warning> pmdWarnings = parsePMDXML();
		List<Warning> totalWarnings = new ArrayList<Warning>();
		totalWarnings.addAll(checkStyleWarnings);
		totalWarnings.addAll(pmdWarnings);
		
		writeJSON(totalWarnings);
	}
	
	private List<Warning> parseCheckStyleXML() throws IOException {
		xmlParser = new CheckStyleXMLParser();
		
		List<Warning> checkStyleWarnings = xmlParser.parseFile(JavaController.getUserDir() + "/Runnables/Testcode/checkstyle.xml", categoryInfo);
		
		return checkStyleWarnings;
	}
	
	private List<Warning> parsePMDXML() throws IOException {
		xmlParser = new PMDXMLParser();
		
		List<Warning> PMDWarnings = xmlParser.parseFile(JavaController.getUserDir() + "/Runnables/Testcode/pmd.xml", categoryInfo);
		
		return PMDWarnings;
	}
	
	private void writeJSON(List<Warning> warnings) throws IOException {
		HashMap<String, String> componentsInfo = new HashMap<String, String>();
		Set<String> packagesNames = new HashSet<String>();

		for (Warning w : warnings) {
			componentsInfo.put(w.getFileName(), w.getFilePath());
			packagesNames.add(PackageNameFinder.findPackageName(w.getFilePath()));
		}

		WarningGrouper wg = new WarningGrouper(componentsInfo, packagesNames, warnings);
		List<Summarizer> list = wg.groupBy("packages");

		JSWriter jwriter = new JSWriter(list);
		jwriter.writeToJSFormat("./src/main/resources/SummarizedOuput.js");	
	}
}
