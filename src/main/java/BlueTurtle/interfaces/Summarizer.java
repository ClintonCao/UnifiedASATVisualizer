package BlueTurtle.interfaces;

import java.util.List;

import BlueTurtle.warnings.Warning;

/**
 * Interface a summarizer class.
 * 
 * @author BlueTurtle
 *
 */
public interface Summarizer {

	/**
	 * Summarise the information of the warnings.
	 * 
	 * @param warnings
	 *            the list of warnings to be summarized.
	 */
	void summarize(List<Warning> warnings);
}
