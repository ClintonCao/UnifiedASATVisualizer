package BlueTurtle.parsers;

import java.io.File;
import java.util.HashMap;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import lombok.Getter;


/**
 * This class can be used to parse the General Defect Classification (GDC) grouping to a file.
 * 
 * @author BlueTurtle.
 * 
 * Reference: Moritz Beller, Radjino Bholanath, Shane McIntosh, Andy Zaidman: Analyzing
 * the State of Static Analysis: A Large-Scale Evaluation in Open Source Software
 * in 23rd IEEE International Conference on Software Analysis, Evolution, and
 * Reengineering (SANER), Osaka (Japan), 2016.
 *
 */
public final class GDCParser extends MarkdownParser {
	
	private static GDCParser gdcParser = null;
	
	@Getter
	private static HashMap<String, String> categoryInfo;
	
	/**
	 * Constructor. Only through this can GDCParser instantiate itself.
	 */
	private GDCParser() {
	}

	/**
	 * Get an instance of GDCParser.
	 * 
	 * @return an instance of current class.
	 */
	public static synchronized GDCParser getInstance() {
		if (gdcParser == null) {
			gdcParser = new GDCParser();
		}
		return gdcParser;
	}

	/**
	 * Parse a GDC grouping file.
	 * 
	 * @param mdFilePath
	 *            the location of the GDC grouping file.
	 * @return a Hashmap of category informations.
	 */
	public HashMap<String, String> parseFile(String mdFilePath) {
		// the hashmap to store the category informations.
		categoryInfo = new HashMap<String, String>();
		
		try {
			// Instantiate things that are necessary for the parser.
			File inputFile = new File(mdFilePath);
			
			// parse the file.
			Document doc = Jsoup.parse(inputFile, "UTF-8");
			
			// select all the rows in the tables.
			Elements rows = doc.select("tbody").select("tr");

			// loop through each row of the entire GDC table
			for (int i = 0; i < rows.size(); i++) {
				Elements cols = rows.get(i).select("td");
				// first column is the warning name.
				String warning = cols.get(0).text();
				// second column is the category of warning.
				String category = cols.get(1).text();

				// update the warningList of the category.
				categoryInfo.put(warning, category);
			}
						
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return categoryInfo;
		
	}

}
