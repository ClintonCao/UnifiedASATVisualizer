package BlueTurtle.TSE;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;

/**
 * Hello world!
 */
public class App {
    public static void main( String[] args ) {
    	String userDir = System.getProperty("user.dir");
        ProcessBuilder pb = new ProcessBuilder("java", "-jar", userDir + "/Runnables/pmd-bin-4.2.6/lib/pmd-4.2.6.jar", userDir + "/Runnables/Testcode/ClintonCode.java", "xml", "basic");
        pb.redirectOutput(Redirect.to(new File("./Runnables/Testcode/output.txt")));
        pb.redirectError(Redirect.INHERIT);
        try {
			Process p = pb.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
        System.out.println("Done.");
    }
}
