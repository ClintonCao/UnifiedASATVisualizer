package BlueTurtle.TSE;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import lombok.Getter;
import lombok.Setter;

public class CodeFile {
	@Getter @Setter private String path;
	@Getter @Setter private String code = "";

	public void getCodeFromFile(File file) throws IOException {
		BufferedReader writer = new BufferedReader(new FileReader(file));
		String nextLine;
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append('"');
		while((nextLine = writer.readLine()) != null) {
			stringBuilder.append(nextLine);
			stringBuilder.append("\n");
		}
		stringBuilder.append('"');
		setCode(stringBuilder.toString());
		writer.close();
	}
}
