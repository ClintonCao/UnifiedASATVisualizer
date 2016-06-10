/*
 * This function will map each specific warning category to the upper level classification 
 */
function categorizeWarningType(classification) {
	switch(classification) {
		case 'Check':
			return 0;
			break;
		case 'Concurrency':
			return 0;
			break;
		case 'ErrorHandling':
			return 0;
			break;
		case 'Interface':
			return 0;
			break;
		case 'Logic':
			return 0;
			break;
		case 'Migration':
			return 0;
			break;
		case 'Resource':
			return 0;
			break;
		case 'Best Practices':
			return 1;
			break;
		case 'Code Structure':
			return 1;
			break;
		case 'Documentation Conventions':
			return 1;
			break;
		case 'Metric':
			return 1;
			break;
		case 'Naming Conventions':
			return 1;
			break;
		case 'Object Oriented Design':
			return 1;
			break;
		case 'Refactorings - Simplifications':
			return 1;
			break;
		case 'Refactorings - Redundancies':
			return 1;
			break;
		case 'Style Conventions':
			return 1;
			break;
		case 'Other':
			return 2;
			break;
		case 'Regular Expressions':
			return 2;
			break;
		case 'Tool Specific':
			return 2;
			break;
		default:
			break;
	}
}