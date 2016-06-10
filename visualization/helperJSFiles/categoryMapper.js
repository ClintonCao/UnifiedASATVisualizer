/*
 * This function will map each specific warning category to the upper level classification 
 */
 

var categoryMapper = (function() {	
	var FunctionalDefects =["Check", "Concurrency", "ErrorHandling", "Interface", "Logic", "Migration", "Resource" ]
	var MaintainabilityDefects = [ "Best Practices", "Code Structure", "Documentation Conventions", "Metric", "Naming Conventions", "Object Oriented Design", "Refactorings - Simplifications", "Refactorings - Redundancies", "Style Conventions"]
	var Other = ["Other", "Regular Expressions", "Tool Specific"]
	
return {
	
	categorizeWarningType: function(classification) {
		if ( FunctionalDefects.indexOf(classification) > 0 ){
			return 0;
		}
		if ( MaintainabilityDefects.indexOf(classification) > 0 ){
			return 1;
		}
		if ( Other.indexOf(classification) > 0 ){
			return 2;
		}else{
			return -1;
		}
	}

}
}());