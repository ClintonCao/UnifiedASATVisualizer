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
    	switch(currentMode) {
    		case JAVA: javaMode(); 
    		break;
    	}
    	controller.execute();
    }
    
    public static void javaMode() {
    	controller.setAnalyser(new JavaAnalyser());
    }
}
