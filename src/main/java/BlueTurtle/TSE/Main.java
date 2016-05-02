package BlueTurtle.TSE;

/**
 * Hello world!
 */
public class Main {
	
	enum mode {
		JAVA
	}
	
	static Controller controller = new Controller();
	static mode currentMode = mode.JAVA;
	
    public static void main(String[] args) {
    	switch (currentMode) {
    		case JAVA: 
    			javaMode(); 
    			break;
    		default:
    			break;
    	}
    	controller.execute();
    	System.out.println("Done.");
    }
    
    public static void javaMode() {
    	controller.setAnalyser(new JavaAnalyser());
    }
}
