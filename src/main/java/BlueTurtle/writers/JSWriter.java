package BlueTurtle.writers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import BlueTurtle.summarizers.Summarizer;

/**
 * This class can be used to write the output of the analyzer to JavaScript
 * format.
 * 
 * @author BlueTurtle.
 *
 */
public class JSWriter {

	private List<Summarizer> summarizedWarnings;

	/**
	 * Contructor.
	 * 
	 * @param summarizedWarnings
	 *            the list of summarizer(where the warnings are summarized).
	 */
	public JSWriter(List<Summarizer> summarizedWarnings) {
		setSummarizedWarnings(summarizedWarnings);
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
		String json = gson.toJson(summarizedWarnings);
		json += ';';
		
		writer.write("var inputData = ");
		writer.newLine();
		writer.write(json);
		writer.flush();
		writer.close();
	}

	/**
	 * Get the list of summarized warnings.
	 * 
	 * @return a list of summarizers.
	 */
	public List<Summarizer> getSummarizedWarnings() {
		return summarizedWarnings;
	}

	/**
	 * Set the list of summarized warnings.
	 * 
	 * @param summarizedWarnings
	 *            list of summarizers.
	 */
	public void setSummarizedWarnings(List<Summarizer> summarizedWarnings) {
		this.summarizedWarnings = summarizedWarnings;
	}

}
