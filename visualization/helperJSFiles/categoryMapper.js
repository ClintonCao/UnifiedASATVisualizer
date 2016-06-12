/**
 * This object will map each specific warning category to the upper level classification 
 */
var categoryMapper = (function() {	

	// all different warning categories
	var FunctionalDefects =["Check", "Concurrency", "ErrorHandling", "Interface", "Logic", "Migration", "Resource" ]
	var MaintainabilityDefects = [ "Best Practices", "Code Structure", "Documentation Conventions", "Metric", "Naming Conventions", "Object Oriented Design", "Refactorings - Simplifications", "Refactorings - Redundancies", "Style Conventions"]
	var Other = ["Other", "Regular Expressions", "Tool Specific"]
	var allCategories = [FunctionalDefects, MaintainabilityDefects, Other];
	
	return {
		// maps a given category to one of the three main categories
		categorizeWarningType: function(classification) {
			for ( var index =0; index < allCategories.length; index++){
				if ( allCategories[index].indexOf(classification) >= 0 ){
					return index;
				}
			}
			return -1;
		},
		warningsFromCategorize: function(category) {
				return allCategories[category];
		}
	}
	
}());