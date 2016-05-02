package BlueTurtle.TSE;

public interface Settings {
	
	public void readSettings();
	
	public void writeSettings();
	
	public String getOutputFilePath();
	
	public void setOutputFilePath(String outputFilePath);
}
