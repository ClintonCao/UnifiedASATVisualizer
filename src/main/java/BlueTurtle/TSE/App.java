package BlueTurtle.TSE;

import java.util.List;

import BlueTurtle.parsers.CheckStyleXMLParser;
import BlueTurtle.parsers.XMLParser;
import BlueTurtle.warnings.Warning;

/**
 * This class is the main driver of the whole system.
 * 
 * @author BlueTurtle.
 *
 */
public class App {
	
	public static void main(String[] args) {
		XMLParser parser = new CheckStyleXMLParser();
		List<Warning> checkStyleWarnings = parser.parseFile("./resources/exampleCheckstyle1.xml");
		
		System.out.println("amount of CheckStyle Warnings:" + " " + checkStyleWarnings.size());
		
		for (Warning w : checkStyleWarnings) {
			System.out.println("Violated Rule Name: " + w.getRuleName());
		}
	}
}
