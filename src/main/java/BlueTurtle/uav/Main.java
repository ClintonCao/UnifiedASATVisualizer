package BlueTurtle.uav;

import java.io.IOException;


import BlueTurtle.gui.GUI;

/**
 * Main class to run the system.
 * 
 * @author BlueTurtle.
 *
 */
public final class Main {

	/**
	 * All the modes (programming languages).
	 * 
	 * @author BlueTurtle.
	 *
	 */
	enum Mode {
		JAVA
	}

	private static Controller controller;
	private static Mode currentMode = Mode.JAVA;
	
	/**
	 * Private constructor for this utility class.
	 */
	private Main() {
		// not called
	}
	
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

	/**
	 * Run compute all the necessary information needed for the visualization.
	 * 
	 * @throws IOException
	 *             throws an exception if problem is encountered while computing
	 *             the information.
	 */
	public static void runVisualization() throws IOException {
		controller.execute();
	}
}
