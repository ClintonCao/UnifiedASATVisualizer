package BlueTurtle.writers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import BlueTurtle.summarizers.Summarizer;

/**
 * This class can be used to write information of packages to a JavaScript file.
 * 
 * @author BlueTurtle.
 *
 */
public class JSWriter {

	private List<Summarizer> packageSummarizers;

	/**
	 * Constructor.
	 * 
	 * @param packageSummarizers
	 *            list of package summarizers.
	 */
	public JSWriter(List<Summarizer> packageSummarizers) {
		setPackageSummarizers(packageSummarizers);
	}

	/**
	 * Write the information of the packages to a JavaScript file.
	 * 
	 * @param outputPath
	 * @throws IOException
	 *             throws an exception if there was a problem in the process of
	 *             writing to file.
	 */
	public void writToJS(String outputPath) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath));
		
		for (Summarizer ps : packageSummarizers) {
			writer.write("var "+ ps.getPackageName() + " = {");
			writer.newLine();
			writer.write('}');
		}
		
		writer.flush();
		writer.close();
	}

	/**************************************/
	/****** Getters and Setters **********/
	/************************************/

	/**
	 * Get the list of package summarizers.
	 * 
	 * @return a ist of package summarizer.
	 */
	public List<Summarizer> getPackageSummarizers() {
		return packageSummarizers;
	}

	/**
	 * Set the list of package summarizers.
	 * 
	 * @param packageSummarizers
	 *            the list of package summarizers.
	 */
	public void setPackageSummarizers(List<Summarizer> packageSummarizers) {
		this.packageSummarizers = packageSummarizers;
	}
}
