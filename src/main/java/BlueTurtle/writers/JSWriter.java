package BlueTurtle.writers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import BlueTurtle.summarizers.ComponentSummarizer;
import BlueTurtle.summarizers.PackageSummarizer;
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
	 *            the path where to write to.
	 * @throws IOException
	 *             throws an exception if a problem is encountered while writing
	 *             to the file
	 */
	public void writePackageToJS(String outputPath) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath));

		for (int i = 0; i < getPackageSummarizers().size(); i++) {
			PackageSummarizer ps = (PackageSummarizer) packageSummarizers.get(i);
			writer.write("var " + ps.getPackageName().replace('.', '-') + " = {");
			writer.newLine();
			writer.write("\t" + '"' + "nodes" + '"' + ":[");
			writer.newLine();

			for (int j = 0; j < ps.getClasses().size(); j++) {
				ComponentSummarizer cs = ps.getClasses().get(j);
				writeComponentToJS(cs, writer, j);

				if (checkCommaNeeded(j, ps.getClasses().size() - 1)) {
					writer.write(',');
				}

				writer.newLine();
			}

			writer.write("\t" + "],");
			writer.newLine();

			writer.write("\t" + '"' + "links" + '"' + ":[");
			writer.newLine();
			writeLinks(writer);
			writer.write("\t" + ']');
			writer.newLine();
			writer.write("};");
			writer.newLine();
			writer.newLine();
		}

		writer.flush();
		writer.close();
	}

	/**
	 * Write the summarized information of a component to a JavaScript file.
	 * 
	 * @param cs
	 *            the component summarizer.
	 * @param writer
	 *            the writer.
	 * @param index
	 *            the index of the component summarizer in the list of
	 *            components summarizers of a package.
	 * @throws IOException
	 *             throws an exception if a problem is encountered while writing
	 *             to the file
	 */
	public void writeComponentToJS(ComponentSummarizer cs, BufferedWriter writer, int index) throws IOException {
		writer.write("\t" + "\t" + '{');
		writer.write('"' + "name" + '"' + ':');
		writer.write('"' + cs.getFileName() + '"' + ',');
		writer.write('"' + "group" + '"' + ':');
		writer.write('"' + cs.getPackageName() + '"' + ',');
		writer.write('"' + "loc" + '"' + ':');
		writer.write("50,");
		writer.write('"' + "warnings" + '"' + ':');
		writer.write(String.valueOf(cs.getNumberOfWarnings()));
		writer.write(',');
		writer.write('"' + "num" + '"' + ':');
		writer.write(String.valueOf(index) + ',');
		writer.write('"' + "url" + '"' + ':');
		writer.write('"' + "index.html" + '"');
		writer.write('}');
	}

	/**
	 * 
	 * Write the links between packages. This method is still under development.
	 * The current code here is for testing purposes.
	 *
	 * @param writer
	 *            the writer.
	 * @throws IOException
	 *             throws an exception if a problem is encountered while writing
	 *             to the file
	 */
	public void writeLinks(BufferedWriter writer) throws IOException {
		int size = getPackageSummarizers().size();

		for (int i = 0; i < size; i++) {
			writer.write("\t" + "  {");
			writer.write('"' + "source" + '"' + ':');
			writer.write('"' + packageSummarizers.get(i).getPackageName() + '"' + ',');
			writer.write('"' + "target" + '"' + ':');
			writer.write('"' + "Project" + '"' + ',');
			writer.write('"' + "value" + '"' + ':');
			writer.write(String.valueOf(1) + '}');

			if (checkCommaNeeded(i, size - 1)) {
				writer.write(',');
			}
			writer.newLine();
		}

		for (int i = 0; i < size; i++) {
			for (int j = i + 1; j < size; j++) {
				writer.write("\t" + "  {");
				writer.write('"' + "source" + '"' + ':');
				writer.write('"' + packageSummarizers.get(i).getPackageName() + '"' + ',');
				writer.write('"' + "target" + '"' + ':');
				writer.write('"' + packageSummarizers.get(j).getPackageName() + '"' + ',');
				writer.write('"' + "value" + '"' + ':');
				writer.write(String.valueOf((int) (Math.random() * 14 + 1)) + '}');

				if (checkCommaNeeded(i, size)) {
					writer.write(',');
				}

				writer.newLine();

				writer.write("\t" + "  {");
				writer.write('"' + "source" + '"' + ':');
				writer.write('"' + packageSummarizers.get(j).getPackageName() + '"' + ',');
				writer.write('"' + "target" + '"' + ':');
				writer.write('"' + packageSummarizers.get(i).getPackageName() + '"' + ',');
				writer.write('"' + "value" + '"' + ':');
				writer.write(String.valueOf((int) (Math.random() * 14 + 1)) + '}');

				if (checkCommaNeeded(j, size - 1)) {
					writer.write(',');
				}
				writer.newLine();
			}
		}

	}

	/**
	 * Check whether a comma is needed after writing a line.
	 * 
	 * @param index
	 *            the current index.
	 * @param lastIndex
	 *            the index of the last element.
	 * @return a boolean
	 */
	public boolean checkCommaNeeded(int index, int lastIndex) {
		return index != lastIndex;
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
