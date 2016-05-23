package BlueTurtle.TSE;

import java.io.IOException;

import BlueTurtle.gui.GUI;
import BlueTurtle.interfaces.Controller;

/**
 * Temporary main class to run the commands.
 * 
 * @author BlueTurtle.
 *
 */
public class Main {

	/**
	 * All the modes (programming languages).
	 * 
	 * @author BlueTurtle.
	 *
	 */
	enum Mode {
		JAVA
	}

	static Controller controller;
	static Mode currentMode = Mode.JAVA;

	/**
	 * Main method.
	 * 
	 * @param args
	 *            the arguments.
	 * @throws IOException
	 *             throws an exception if a problem is encountered during the
	 *             execution of the controller.
	 */
	public static void main(String[] args) throws IOException {
		
		switch (currentMode) {
		case JAVA:
			controller = new JavaController();
			GUI gui = new GUI();
			gui.startGUI();
			break;
		default:
			break;
		}
		System.out.println("Done.");
	}
	
	public static void runVisualization() throws IOException {
		controller.execute();
	}
}
