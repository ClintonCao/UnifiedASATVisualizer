package BlueTurtle.groupers;

import java.util.List;

import BlueTurtle.interfaces.Grouper;
import BlueTurtle.warnings.Warning;

/**
 * This class is used to group warnings together (by their type or by
 * components).
 * 
 * @author BlueTurtle.
 *
 */
public class WarningGrouper implements Grouper {

	/**
	 * Group the warnings by their type e.g. group all CheckStyle warnings
	 * together.
	 * 
	 * @param warnings
	 *            the list of warnings to be grouped.
	 */
	@Override
	public void groupByWaningType(List<Warning> warnings) {
		// TODO Auto-generated method stub

	}

	/**
	 * Group warnings by the component that they are from.
	 * 
	 * @param warnings
	 *            the list of warnings to be grouped.
	 */
	@Override
	public void groupByComponents(List<Warning> warnings) {
		// TODO Auto-generated method stub

	}

	/**
	 * Group warnings together by the category that they are from. This is done
	 * using the General Defect Classification.
	 * 
	 * @param warnings
	 *            the list of warnings tot be grouped.
	 */
	@Override
	public void groupByCategory(List<Warning> warnings) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Group the warnings by the package they are from.
	 * 
	 * @param warnings
	 *            the list of warnings to be grouped.
	 */
	@Override
	public void groupByPackage(List<Warning> warnings) {
		// TODO Auto-generated method stub
		
	}

}
