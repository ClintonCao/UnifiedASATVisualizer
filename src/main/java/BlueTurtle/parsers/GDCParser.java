package BlueTurtle.parsers;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
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
			
			// select all the rows in the tables.
			Elements rows =doc.select("tbody").select("tr");

			// loop through each row of the entire GDC table
			for(int i = 0; i < rows.size(); i++){
				Elements cols = rows.get(i).select("td");
				// first column is the warning name.
				String warning = cols.get(0).text();
				// second column is the category of warning.
				String category = cols.get(1).text();
				// initialize an empty warningList.
				List<String> warningList = new ArrayList<String>();

				// if the categoryInfo already has that category.
				if(categoryInfo.containsKey(category)){
				 // get the the current warningList
				 warningList = categoryInfo.get(category);
				 // add the new warning
				 warningList.add(warning);
				 // if the categoryInfo does not contain the current category
				}else {
					warningList.add(warning);
				}
				// update the warningList of the category.
				categoryInfo.put(category, warningList);
			}
						
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return categoryInfo;
		
	}

}
