package BlueTurtle.TSE;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import BlueTurtle.finders.PackageNameFinder;
import BlueTurtle.parsers.CheckStyleXMLParser;
import BlueTurtle.parsers.FindBugsXMLParser;
import BlueTurtle.parsers.GDCParser;
import BlueTurtle.parsers.PMDXMLParser;
import BlueTurtle.parsers.XMLParser;
import BlueTurtle.warnings.Warning;

/**
 * JSONFormatter reads the output of Checkstyle, Findbugs and PMD and produces a summarized defect output.
 * @author michiel
 *
 */
public class JSONFormatter {
	private XMLParser xmlParser;
	private GDCParser gdcParser = new GDCParser();
	HashMap<String, String> categoryInfo = gdcParser.parseFile("./src/main/resources/asat-gdc-mapping.html");
	
	/**
	 * Produces a list of warnings for by reading the output of PMD, Checkstyle and Findbugs. 
	 * Then converts it to JSON format and writes it to a JavaScript file.
	 * @throws IOException
	 * 						File not found.
	 */
	public void format() throws IOException {
		List<Warning> checkStyleWarnings = parseCheckStyleXML();
		List<Warning> pmdWarnings = parsePMDXML();
		//List<Warning> findBugsWarnings = parseFindBugsXML();
		List<Warning> totalWarnings = new ArrayList<Warning>();
		totalWarnings.addAll(checkStyleWarnings);
		totalWarnings.addAll(pmdWarnings);
		//totalWarnings.addAll(findBugsWarnings);
		
		writeJSON(totalWarnings);
	}
	
	/**
	 * Parse CheckStyle output and produce list of warnings.
	 * @return 
	 * 			List of warnings.
	 * @throws IOException
	 * 			File not found.
	 */
	private List<Warning> parseCheckStyleXML() throws IOException {
		xmlParser = new CheckStyleXMLParser();
		
		List<Warning> checkStyleWarnings = xmlParser.parseFile(JavaController.getCheckStyleOutputFile(), categoryInfo);
		
		return checkStyleWarnings;
	}
	
	/**
	 * Parse PMD output and produce list of warnings.
	 * @return 
	 * 			List of warnings.
	 * @throws IOException
	 * 			File not found.
	 */
	private List<Warning> parsePMDXML() throws IOException {
		xmlParser = new PMDXMLParser();
		
		List<Warning> PMDWarnings = xmlParser.parseFile(JavaController.getPMDOutputFile(), categoryInfo);
		
		return PMDWarnings;
	}
	
	/**
	 * Parse FindBugs output and produce list of warnings.
	 * @return 
	 * 			List of warnings.
	 * @throws IOException
	 * 			File not found.
	 */
	private List<Warning> parseFindBugsXML() throws IOException {
		xmlParser = new FindBugsXMLParser();
		List<Warning> findBugsWarnings = xmlParser.parseFile(JavaController.getFindBugsOutputFile(), categoryInfo);
		return findBugsWarnings;
	}
	
	/**
	 * Groups the warnings together by packages and writes it as JSON output to a JavaScript file.
	 * @param warnings
	 * 				List of warnings to work with.
	 * @throws IOException
	 * 				Output file not found.
	 */
	private void writeJSON(List<Warning> warnings) throws IOException {
		HashMap<String, String> componentsInfo = new HashMap<String, String>();
		Set<String> packagesNames = new HashSet<String>();

		for (Warning w : warnings) {
			componentsInfo.put(w.getFileName(), w.getFilePath());
			packagesNames.add(PackageNameFinder.findPackageName(w.getFilePath()));
		}

//		WarningGrouper wg = new WarningGrouper(componentsInfo, packagesNames, warnings);
//		List<Summarizer> list = wg.groupBy("packages");
//
//		JSWriter jwriter = new JSWriter(list);
//		jwriter.writeToJSFormat("./src/main/resources/SummarizedOuput.js");	
	}
}
