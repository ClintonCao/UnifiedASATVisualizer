package BlueTurtle.TSE;

public class AnalyserCommand {
	private String defaultOutputFilePath;
	private String[] args;
	
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
