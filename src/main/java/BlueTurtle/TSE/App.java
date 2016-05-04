package BlueTurtle.TSE;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import BlueTurtle.parsers.CheckStyleXMLParser;
import BlueTurtle.parsers.FindBugsXMLParser;
import BlueTurtle.parsers.PMDXMLParser;
import BlueTurtle.parsers.XMLParser;
import BlueTurtle.summarizers.ComponentSummarizer;
import BlueTurtle.warnings.Warning;

/**
 * This class is the main driver of the whole system.
 * 
 * @author BlueTurtle.
 *
 */
public class App {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Which ASAT do you wish to run? (CheckStyle, PMD, FindBugs)");
		String asat = sc.next();
		switch(asat) {
		case "CheckStyle":
			XMLParser parser1 = new CheckStyleXMLParser();
			List<Warning> checkStyleWarnings = parser1.parseFile("./resources/exampleCheckstyle1.xml");
			
			System.out.println("amount of CheckStyle Warnings:" + " " + checkStyleWarnings.size());
			
			for (Warning w : checkStyleWarnings) {
				System.out.println("Violated Rule Name: " + w.getRuleName());
			}
			break;
		case "PMD":
			XMLParser parser2 = new PMDXMLParser();
			List<Warning> PMDWarnings = parser2.parseFile("./resources/examplePmd2.xml");
			
			System.out.println("amount of PMD Warnings:" + " " + PMDWarnings.size());
			
			for (Warning w : PMDWarnings) {
				System.out.println("Violated Rule Name: " + w.getRuleName());
			}
			break;
		case "FindBugs":
			XMLParser parser3 = new FindBugsXMLParser();
			List<Warning> FindBugsWarnings = parser3.parseFile("./resources/exampleFindbugs1.xml");
			
			System.out.println("amount of FindBugs Warnings:" + " " + FindBugsWarnings.size());
			
			for (Warning w : FindBugsWarnings) {
				System.out.println("Violated Rule Name: " + w.getRuleName());
				System.out.println("file path: " + w.getFilePath());
			}
			break;			
		default:
			System.out.println("Please enter a valid name for ASAT");
		}

	}
	
	
	public static void jsonTest() throws IOException {
		XMLParser parser = new CheckStyleXMLParser();
		List<Warning> checkStyleWarnings = parser.parseFile("./resources/exampleCheckstyle1.xml");
		
		System.out.println("amount of CheckStyle Warnings:" + " " + checkStyleWarnings.size());
		
		for (Warning w : checkStyleWarnings) {
			System.out.println("Violated Rule Name: " + w.getRuleName());
		}
		
		Warning w = checkStyleWarnings.get(0);
		
		ComponentSummarizer cs = new ComponentSummarizer(w.getFileName(), w.getFilePath());
		
		cs.summarize(checkStyleWarnings);
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		String json = gson.toJson(cs);
		
		File myFile = new File("./resources/component.json");
		FileOutputStream fOut = new FileOutputStream(myFile);
		OutputStreamWriter writer = new OutputStreamWriter(fOut);
		writer.append(json);
		writer.close();
		fOut.close();
		
		System.out.println(json);
	}
}
