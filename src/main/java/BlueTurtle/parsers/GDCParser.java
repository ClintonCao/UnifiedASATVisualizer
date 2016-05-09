package BlueTurtle.parsers;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 * This class can be used to parse the General Defect Classification (GDC) grouping to a file.
 * 
 * @author BlueTurtle.
 * 
 * @reference Moritz Beller, Radjino Bholanath, Shane McIntosh, Andy Zaidman: Analyzing
 * the State of Static Analysis: A Large-Scale Evaluation in Open Source Software
 * in 23rd IEEE International Conference on Software Analysis, Evolution, and
 * Reengineering (SANER), Osaka (Japan), 2016.
 *
 */
public class GDCParser extends MarkdownParser {
	
	private HashMap<String, List<String>> categoryInfo;
	

	/**
	 * Parse a GDC grouping file.
	 * 
	 * @param mdFilePath
	 *            the location of the GDC grouping file.
	 * @return a Hashmap of category informations.
	 */
	@Override
	public HashMap<String, List<String>> parseFile(String mdFilePath) {
		// the hashmap to store the category informations.
		categoryInfo = new HashMap<String, List<String>>();
		
		try{
			// Instantiate things that are necessary for the parser.
			File inputFile = new File(mdFilePath);
			
			// parse the file.
			Document doc = Jsoup.parse(inputFile, "UTF-8");
			Element checkStyle = doc.getElementById("checkstyle-version-6.6");
			
			Elements odds =  doc.getElementsByClass("odd");
			Elements evens = doc.getElementsByClass("even");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return categoryInfo;
		
	}

}
