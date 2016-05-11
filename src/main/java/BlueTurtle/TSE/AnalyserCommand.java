package BlueTurtle.TSE;

/**
 * Represents a basic unit of work for the analyser to process.
 * 
 * @author BlueTurtle.
 *
 */
public class AnalyserCommand {
	private String defaultOutputFilePath;
	private String[] args;

	/**
	 * Constructor.
	 * 
	 * @param defaultOutputFilePath
	 *            the output of the result.
	 * @param args
	 *            - command line arguments.
	 */
	public AnalyserCommand(String defaultOutputFilePath, String[] args) {
		this.setDefaultOutputFilePath(defaultOutputFilePath);
		this.setArgs(args);
	}

	public String[] getArgs() {
		return args;
	}

	public void setArgs(String[] args) {
		this.args = args;
	}

	public String getDefaultOutputFilePath() {
		return defaultOutputFilePath;
	}

	public void setDefaultOutputFilePath(String defaultOutputFilePath) {
		this.defaultOutputFilePath = defaultOutputFilePath;
	}
}
