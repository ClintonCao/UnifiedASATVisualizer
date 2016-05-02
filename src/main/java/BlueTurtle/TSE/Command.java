package BlueTurtle.TSE;

public class Command {
	private Settings settings;
	private String[] args;
	
	public Command(Settings settings, String[] args) {
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
}
