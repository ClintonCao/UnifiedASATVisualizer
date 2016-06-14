package BlueTurtle.TSE;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a file of code on the system. Consists of two strings:
 * the path to the file and the contents of the file. 
 * @author michiel
 *
 */
public class CodeFile {
	@Getter @Setter private String path;
	@Getter @Setter private String code = "";
	
	/**
	 * Reads the code from a file and saves it in the code field.
	 * @param file 
	 * 			file to read from.
	 * @throws IOException
	 * 			if file is not found, inaccessible, etc.
	 */
	public void getCodeFromFile(File file) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));

		String nextLine;
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append('"');
		while ((nextLine = reader.readLine()) != null) {
			stringBuilder.append(nextLine);
			stringBuilder.append("\n");
		}
		stringBuilder.append('"');
		setCode(stringBuilder.toString());
		reader.close();
	}
}
