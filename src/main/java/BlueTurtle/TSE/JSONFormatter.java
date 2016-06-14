package BlueTurtle.TSE;

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

/**
 * JSONFormatter reads the output of Checkstyle, Findbugs and PMD and produces a
 * summarized defect output.
 * 
 * @author BlueTurtle.
 *
 */
public class JSONFormatter {
	private XMLParser xmlParser;
	private List<Warning> totalWarnings = new ArrayList<Warning>();

	/**
	 * Produces a list of warnings for by reading the output of PMD, Checkstyle
	 * and Findbugs. Then converts it to JSON format and writes it to a
	 * JavaScript file.
	 * 
	 * @throws IOException
	 *             File not found.
	 */
	public void format() throws IOException {
				
		parseCheckStyleXML(new File(JavaController.getCheckStyleOutputFile()));

		parsePMDXML(new File(JavaController.getPmdOutputFile()));

		parseFindBugsXML(new File(JavaController.getFindBugsOutputFile()));
		
		writeJSON();
	}
	
	/**
	 * Parse CheckStyle output and produce list of warnings.
	 * 
	 * @return List of warnings.
	 * @throws IOException
	 *             File not found.
	 */
	private void parseCheckStyleXML(File file) throws IOException {
		if(!file.exists()) {
			return;
		}
		xmlParser = new CheckStyleXMLParser();
		totalWarnings.addAll(xmlParser.parseFile(JavaController.getCheckStyleOutputFile()));
	}

	/**
	 * Parse PMD output and produce list of warnings.
	 * 
	 * @return List of warnings.
	 * @throws IOException
	 *             File not found.
	 */
	private void parsePMDXML(File file) throws IOException {
		if(!file.exists()) {
			return;
		}
		xmlParser = new PMDXMLParser();
		totalWarnings.addAll(xmlParser.parseFile(JavaController.getPmdOutputFile()));
	}

	/**
	 * Parse FindBugs output and produce list of warnings.
	 * 
	 * @return List of warnings.
	 * @throws IOException
	 *             File not found.
	 */
	private void parseFindBugsXML(File file) throws IOException {
		if(!file.exists()) {
			return;
		}
		xmlParser = new FindBugsXMLParser();
		totalWarnings.addAll(xmlParser.parseFile(JavaController.getFindBugsOutputFile()));
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
