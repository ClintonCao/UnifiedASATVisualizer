package BlueTurtle.interfaces;

import java.util.List;

import BlueTurtle.warnings.Warning;

/**
 * This is the interface for groupers (classes that group warnings together).
 * 
 * @author BlueTurtle.
 *
 */
public interface Grouper {

	/**
	 * Group warnings together by their type e.g. group all CheckStyle Warnings
	 * together.
	 * 
	 * @param Warnings
	 *            the list of warnings that needs to be grouped.
	 */
	void groupByWaningType(List<Warning> warnings);

	/**
	 * Group warnings together by the components that they are from e.g. group
	 * warnings that are from the same class together.
	 * 
	 * @param warnings
	 *            the list of warnings that needs to be grouped.
	 */
	void groupByComponents(List<Warning> warnings);

	/**
	 * Group warnings together by the category that they are from. This is done
	 * using the General Defect Classification.
	 * 
	 * @param warnings
	 *            the list of warnings tot be grouped.
	 */
	void groupByCategory(List<Warning> warnings);

	/**
	 * Group the warnings by the package they are from.
	 * 
	 * @param warnings
	 *            the list of warnings to be grouped.
	 */
	void groupByPackage(List<Warning> warnings);
}
