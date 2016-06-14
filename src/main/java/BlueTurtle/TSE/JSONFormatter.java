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
				
		parseFile(new CheckStyleXMLParser(), JavaController.getCheckStyleOutputFile());

		parseFile(new PMDXMLParser(), JavaController.getPmdOutputFile());

		parseFile(new FindBugsXMLParser(), JavaController.getFindBugsOutputFile());
		
		writeJSON();
	}
	
	/**
	 * Parse ASAT output and produce list of warnings.
	 * 
	 * @return List of warnings.
	 * @throws IOException
	 *             File not found.
	 */
	private void parseFile(XMLParser xmlParser, String filePath) throws IOException {
		File file = new File(filePath);
		if(!file.exists()) {
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
