package BlueTurtle.uav;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import BlueTurtle.groupers.WarningGrouper;
import BlueTurtle.groupers.WarningGrouper.Criteria;
import BlueTurtle.parsers.CheckStyleXMLParser;
import BlueTurtle.parsers.FindBugsXMLParser;
import BlueTurtle.parsers.PMDXMLParser;
import BlueTurtle.parsers.XMLParser;
import BlueTurtle.summarizers.Summarizer;
import BlueTurtle.warnings.Warning;
import BlueTurtle.writers.JSWriter;
import lombok.Getter;
import lombok.Setter;

/**
 * JSONFormatter reads the output of Checkstyle, Findbugs and PMD and produces a
 * summarized defect output.
 * 
 * @author BlueTurtle.
 *
 */
public class JSONFormatter {
	@Getter @Setter private List<Warning> totalWarnings = new ArrayList<Warning>();

	/**
	 * Produces a list of warnings for by reading the output of PMD, Checkstyle
	 * and Findbugs. Then converts it to JSON format and writes it to a
	 * JavaScript file.
	 * 
	 * @throws IOException
	 *             File not found.
	 */
	public void format() throws IOException {
				
		parseListOfFiles(new CheckStyleXMLParser(), JavaController.getCheckStyleOutputFiles());

		parseListOfFiles(new PMDXMLParser(), JavaController.getPmdOutputFiles());

		parseListOfFiles(new FindBugsXMLParser(), JavaController.getFindBugsOutputFiles());
		
		writeJSON();
	}
	
	/**
	 * Parse the list of output files for an ASAT.
	 * @param xmlParser 
	 * 				the parser for parsing the xml files.
	 * @param filePaths
	 * 				the list containing the paths of the output files of an ASAT.
	 * @throws IOException 
	 * 				throws an exception if problem is encountered while parsing the files.
	 */
	private void parseListOfFiles(XMLParser xmlParser, ArrayList<String> filePaths) throws IOException {
		if (filePaths != null) {
			for (String path : filePaths) {
				parseFile(xmlParser, path);
			}
		}
	}

	/**
	 * Parse an single output file of an ASAT.
	 * 
	 * @throws IOException
	 *             Throws an exception if problem is encountered while parsing the file.
	 */
	private void parseFile(XMLParser xmlParser, String filePath) throws IOException {
		if (!new File(filePath).exists()) {
			return;
		}
		totalWarnings.addAll(xmlParser.parseFile(filePath));
	}

	/**
	 * Groups the warnings together by packages and writes it as JSON output to
	 * a JavaScript file.
	 * 
	 * @param warnings
	 *            List of warnings to work with.
	 * @throws IOException
	 *             Output file not found.
	 */
	private void writeJSON() throws IOException {
		WarningGrouper wg = new WarningGrouper(totalWarnings);
		List<Summarizer> list = wg.groupBy(Criteria.PACKAGES);

		JSWriter jwriter = JSWriter.getInstance();
		jwriter.setSummarizedWarnings(list);
		jwriter.writeToJSFormat("visualization/JSON/outputWarningsJSON.js");	

	}
}
