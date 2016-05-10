package BlueTurtle.interfaces;

import java.io.IOException;

/**
 * The controller interface.
 * 
 * @author BlueTurtle.
 *
 */
public interface Controller {

	/**
	 * Execute controller.
	 * 
	 * @throws IOException
	 *             throws an exception if a problem is encountered during
	 *             execution.
	 */
	void execute() throws IOException;

}
