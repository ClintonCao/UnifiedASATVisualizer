package BlueTurtle.TSE;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ProcessBuilder.Redirect;
import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	String command = "java -jar C:\\Users\\michiel\\Desktop\\pmd-bin-4.2.6\\lib\\pmd-4.2.6.jar C:\\Users\\michiel\\workspace\\Algoritmiek\\src\\clintoncode xml basic";
    	String c2 = "java -jar C:/Users/michiel/Desktop/pmd-bin-4.2.6/lib/pmd-4.2.6.jar C:/Users/michiel/workspace/Algoritmiek/src/clintoncode xml basic";
        System.out.println( "Hello World!" );
        Runtime rt = Runtime.getRuntime();

        ProcessBuilder pb = new ProcessBuilder("java", "-jar", "C:\\Users\\michiel\\Desktop\\pmd-bin-4.2.6\\lib\\pmd-4.2.6.jar", "C:\\Users\\michiel\\workspace\\Algoritmiek\\src\\clintoncode", "xml", "basic");
        pb.directory(new File("C:/"));
       
        pb.redirectOutput(Redirect.to(new File("C:/Temp/new.txt")));
        pb.redirectError(Redirect.INHERIT);
        try {
			Process p = pb.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
