/**
 * This object will map each specific warning category to the upper level classification 
 */
var categoryMapper = (function() {	

	// all different warning categories
	var FunctionalDefects =["Check", "Concurrency", "ErrorHandling", "Interface", "Logic", "Migration", "Resource" ]
	var MaintainabilityDefects = [ "Best Practices", "Code Structure", "Documentation Conventions", "Metric", "Naming Conventions", "Object Oriented Design", "Refactorings - Simplifications", "Refactorings - Redundancies", "Style Conventions"]
	var Other = ["Other", "Regular Expressions", "Tool Specific"]
	var allCategories = [FunctionalDefects, MaintainabilityDefects, Other];
	
	// all labels
	var FunctionalDefectsLabel =["CheckLabel", "ConcurrencyLabel", "ErrorHandlingLabel", "InterfaceLabel", "LogicLabel", "MigrationLabel", "ResourceLabel" ]
	var MaintainabilityDefectsLabel = [ "BestPracticesLabel", "CodeStructureLabel", "DocConventionsLabel", "MetricLabel", "NamingConventionsLabel", "OODesignLabel", "SimplificationsLabel", "RedundanciesLabel", "StyleConventionsLabel"]
	var OtherLabel = ["OtherLabel", "RegularExpressionsLabel", "ToolSpecificLabel"]
	var allCategoriesLabel = [FunctionalDefectsLabel, MaintainabilityDefectsLabel, OtherLabel];
	
	
	
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
		},
		getDefects: function(index){
			return allCategories[index];
		},
		getDefectLabels: function(index){
			return allCategoriesLabel[index];
		}
		
	}
	
}());