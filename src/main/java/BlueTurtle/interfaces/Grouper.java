package BlueTurtle.interfaces;

import java.util.List;

import BlueTurtle.groupers.WarningGrouper.Criteria;
import BlueTurtle.summarizers.Summarizer;

/**
 * This interface is used for a grouper (class that groups things together).
 * 
 * @author BlueTurtle.
 *
 */
public interface Grouper {
	/**
	 * Group things based on the given criteria.
	 * 
	 * @param criteria
	 *            list of criterium for grouping things together.
	 * @return a list of things that are grouped together.
	 */
	List<Summarizer> groupBy(Enum<Criteria> criteria);
}
