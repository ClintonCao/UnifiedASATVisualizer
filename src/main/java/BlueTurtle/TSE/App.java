package BlueTurtle.TSE;

import java.util.List;
import java.util.Scanner;

import BlueTurtle.parsers.CheckStyleXMLParser;
import BlueTurtle.parsers.PMDXMLParser;
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
		Scanner sc = new Scanner(System.in);
		System.out.println("Which ASAT do you wish to run? (CheckStyle, PMD)");
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
		default:
			System.out.println("Please enter a valid name for ASAT");
			break;
		}
		
		
		
		
	

	}
}
