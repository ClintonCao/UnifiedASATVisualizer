package BlueTurtle.interfaces;

/**
 * Interface for a warning object.
 * 
 * @author BlueTurtle.
 *
 */
public interface Violation {

	/**
	 * Write the value of the attributes in json format.
	 * 
	 * @param outputFileName
	 *            the name of the file to write to.
	 */
	void toJson(String outputFileName);

}
