package BlueTurtle.TSE;

import java.io.IOException;

import BlueTurtle.interfaces.Controller;

/**
 * Hello world!
 */
public class Main {
	
	enum mode {
		JAVA
	}
	
	static Controller controller;
	static mode currentMode = mode.JAVA;
	
    public static void main(String[] args) throws IOException {
    	switch (currentMode) {
    		case JAVA: 
    			controller = new JavaController();
    			break;
    		default:
    			break;
    	}
    	controller.execute();
    	System.out.println("Done.");
    }
}
