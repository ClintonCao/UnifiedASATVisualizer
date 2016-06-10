package BlueTurtle.writers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import BlueTurtle.TSE.CodeFile;
import BlueTurtle.gui.GUIController;
import BlueTurtle.summarizers.Summarizer;
import lombok.Getter;
import lombok.Setter;

/**
 * This class can be used to write the output of the analyzer to JavaScript
 * format.
 * 
 * @author BlueTurtle.
 *
 */
@SuppressWarnings("checkstyle:nowhitespacebefore")
public final class JSWriter {

	private static JSWriter jsWriter = null;

	@Getter @Setter private List<Summarizer> summarizedWarnings;

	/**
	 * Constructor. Only this class can instantiate itself.
	 */
	private JSWriter() {
	}

	/**
	 * Get an instance of this class.
	 * 
	 * @return an instance of this class.
	 */
	public static synchronized JSWriter getInstance() {
		if (jsWriter == null) {
			jsWriter = new JSWriter();
		}
		return jsWriter;
	}

	/**
	 * Write the summarized warnings to a file in JavaScript format.
	 * 
	 * @param outputFilePath
	 *            path to write the output to.
	 * @throws IOException
	 *             throws an exception if something went wrong in the process of
	 *             writing to file.
	 */
	public void writeToJSFormat(String outputFilePath) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath));
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(summarizedWarnings) + ';';
		writer.write("var inputData = ");
		writer.newLine();
		writer.write(json);
		writer.newLine();
		writer.write("var projectName = " + '"' + GUIController.getProjectPath().substring(
				GUIController.getProjectPath().lastIndexOf(File.separator) + 1,
				GUIController.getProjectPath().length()) + '"' + ';') ;
		writer.flush();
		writer.close();
	}

	/**
	 * Write the codefiles as JSON to a javascript file.
	 * 
	 * @param codeFiles
	 *            codefiles to write.
	 * @param outputFilePath
	 *            file to write to.
	 * @throws IOException
	 *             if outputfile is not found, inaccessible, etc.
	 */
	public void writeSourceCodeToJS(ArrayList<CodeFile> codeFiles, String outputFilePath) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath));
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(codeFiles) + ';';
		writer.write("var codeExport = ");
		writer.newLine();
		writer.write(json);
		writer.flush();
		writer.close();
	}
}
