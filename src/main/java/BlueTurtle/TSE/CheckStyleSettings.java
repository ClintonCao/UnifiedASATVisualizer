package BlueTurtle.TSE;

public class CheckStyleSettings implements Settings {
	private static String outputFilePath = "./Runnables/Testcode/checkstyle.xml";

	public void readSettings() {
		// TODO Auto-generated method stub
		
	}

	public void writeSettings() {
		// TODO Auto-generated method stub
		
	}

	public String getOutputFilePath() {
		return outputFilePath;
	}

	public void setOutputFilePath(String outputFilePath) {
		CheckStyleSettings.outputFilePath = outputFilePath;
	}

}
