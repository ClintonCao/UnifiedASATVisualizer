package BlueTurtle.TSE;

import BlueTurtle.interfaces.Settings;

public class AnalyserCommand {
	private Settings settings;
	private String[] args;
	
	public AnalyserCommand(Settings settings, String[] args) {
		this.setSettings(settings);
		this.setArgs(args);
	}

	public String[] getArgs() {
		return args;
	}

	public void setArgs(String[] args) {
		this.args = args;
	}


	public Settings getSettings() {
		return settings;
	}


	public void setSettings(Settings settings) {
		this.settings = settings;
	}
	
	public String getOutputFilePath() {
		return settings.getOutputFilePath();
	}

	public void setOutputFilePath(String outputFilePath) {
		settings.setOutputFilePath(outputFilePath);
	}
}
