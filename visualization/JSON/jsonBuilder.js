/*
 * A class which holds all functions that uses JSON 
 */

/*
 *
 * Replace \ for / in path for usage
 *
 */ 
function replaceAll(stringObject, target, replacement){
	return stringObject.split(target).join(replacement);
}

/*
 *
 * Filter on type of tool and/or warnings
 *
 */
function filterTypeRuleName(acceptedTypes, acceptedCategories){
	var packageArray = [];
  	for (p = 0; p < inputData.length; p++) {
  		var package = inputData[p];
		var classesArray = package.classes;
		var classArray = [];
		var CSW = 0;
		var PMDW = 0;
		var FBW = 0;
		for (i = 0; i < classesArray.length; i++) {
	  		var classObject = new Object();
	  		classObjectJson = classesArray[i];
	  		classObject.amountOfWarnings = 0;
	  		classObject.amountOfCheckStyleWarnings = 0;
			classObject.amountOfPMDWarnings = 0;
			classObject.amountOfFindBugsWarnings = 0;
	  		classObject.loc = classObjectJson.loc;
	  		classObject.fileName = classObjectJson.fileName;
	  		for (j = 0; j < classObjectJson.warningList.length; j++) { 
				var warningJson = classObjectJson.warningList[j];
				if($.inArray(warningJson.type, acceptedTypes) > -1 && ($.inArray(warningJson.classification, acceptedCategories) > -1)) {
		  			classObject.amountOfWarnings++;
		  			switch(warningJson.type) {
		  				case 'CheckStyle':
		  					classObject.amountOfCheckStyleWarnings++;
		  					break;
		  				case 'PMD':
		  					classObject.amountOfPMDWarnings++;
		  					break;
		  				case 'FindBugs':
		  					classObject.amountOfFindBugsWarnings++;
		  					break;
		  				default:
		  					break;
		  			}
				}
	  		}
	  		CSW += classObject.amountOfCheckStyleWarnings;
	  		PMDW += classObject.amountOfPMDWarnings;
	  		FBW += classObject.amountOfFindBugsWarnings;
	  		classArray.push(classObject);
		}
	classArray.packageName = package.packageName;
	classArray.amountOfCheckStyleWarnings = CSW;
	classArray.amountOfPMDWarnings = PMDW;
	classArray.amountOfFindBugsWarnings = FBW;
	packageArray.push(classArray);
  	}
	return packageArray;
}

/*
 *
 * Counts for a specific ASAT how many warnings there are
 *
 */
function getTotalASATWarning(warningType) {
	var packageArray = [];
	for(var p =0; p < inputData.length; p++){
		var package = inputData[p];
		var classesArray = package.classes;
		var classArray = [];
		for (i = 0; i < classesArray.length; i++) {
			classObjectJson = classesArray[i];
			var classObject = new Object();
			classObject.amountOfWarnings = 0;
			classObject.fileName = classObjectJson.fileName;
			for (j = 0; j < classObjectJson.warningList.length; j++) { 
				var warningJson = classObjectJson.warningList[j]
				if(warningJson.type == warningType && ($.inArray(warningJson.classification, acceptedCategories) > -1)) {
		  			classObject.amountOfWarnings++;
				}
	  		}
	  		classArray.push(classObject);
		}
		classArray.packageName = package.packageName;
		packageArray.push(classArray)
	}
	return packageArray;
}

/*
 *
 * Creates a JSON file that could be used by the tree map
 *
 */
function createJsonTreeMap(packages){
	var jsonArrPackage = [];
	var upperLevelCSW = 0;
	var upperLevelPMDW = 0;
	var upperLevelFBW = 0;
		for(var p =0; p < packages.length; p++){
			var jsonArrClass = [];
			var classes = packages[p];
			for (var i = 0; i < classes.length; i++) {
				var fileName = classes[i].fileName;
				var linesOfCode = classes[i].loc;
				jsonArrClass.push({
					fileName: fileName,
					warnings: classes[i].amountOfWarnings,
					warningsCheckStyle: classes[i].amountOfCheckStyleWarnings,
					warningsPMD: classes[i].amountOfPMDWarnings,
					warningsFindBugs: classes[i].amountOfFindBugsWarnings,
					value: linesOfCode
				});
			}

			upperLevelCSW += classes.amountOfCheckStyleWarnings;
			upperLevelPMDW += classes.amountOfPMDWarnings;
			upperLevelFBW += classes.amountOfFindBugsWarnings;

			jsonArrPackage.push(
				{
					fileName: classes.packageName, 
					values: jsonArrClass,
					warningsCheckStyle: classes.amountOfCheckStyleWarnings,
					warningsPMD: classes.amountOfFindBugsWarnings,
					warningsFindBugs: classes.amountOfFindBugsWarnings
				});
		}
	return [
		{
			fileName: "Project",
			values: jsonArrPackage,
			warningsCheckStyle: upperLevelCSW,
			warningsPMD: upperLevelPMDW,
			warningsFindBugs: upperLevelFBW
		}];
}

/*
 *
 * Creates a JSON file that could be used by the graph for package level
 *
 */
function createJsonGraphPackages(packages){
	var jsonArrPackage = [];
	var linksArray = [];
 	for(var p =0; p < packages.length; p++){
 		for(var z = p+1; z < packages.length; z++) {
 			if((Math.floor(Math.random() * 10) + 1) <= 8) {
 				linksArray.push({"source":p, "target":z, "value": 10});
 			}
 		}
	  	var jsonArrClass = [];
	  	var classes = packages[p];
	  	var totalWarningsPackage = 0;
	  	var numberOfClasses = 0;
	  	var totalLines = 0;
      	for (var i = 0; i < classes.length; i++) {
        	var fileName = classes[i].fileName;
        	var linesOfCode = classes[i].loc;
        	var amountOfWarnings = classes[i].amountOfWarnings;
        	totalWarningsPackage += amountOfWarnings;
        	totalLines += classes[i].loc;
        	numberOfClasses++;
        	jsonArrClass.push({
          	fileName: fileName,
          	loc: linesOfCode,
          	warnings: amountOfWarnings
        	});
      	}
      	jsonArrPackage.push({fileName: classes.packageName, numberOfClasses: numberOfClasses, totalWarnings:totalWarningsPackage, loc:totalLines, classes: jsonArrClass});
	}
	return {nodes: jsonArrPackage, links: linksArray };
}

/*
 *
 * Creates a JSON file that could be used by the graph for project level
 *
 */
function createJsonGraphClasses(packages, packageName){
 	for(var p =0; p < packages.length; p++){
    	if(packages[p].packageName == packageName) {
      	var jsonArrClass = [];
      	var linksArray = [];
      	var classes = packages[p];
	      	for (var i = 0; i < classes.length; i++) {
	      		for(var z = i+1; z < classes.length; z++) {
	      			if((Math.floor(Math.random() * 10) + 1) <= 2) {
		 				linksArray.push({"source":i, "target":z, "value": 10});
		 			}
		 		}
	        	var fileName = classes[i].fileName;
	        	var linesOfCode = classes[i].loc;
	        	var amountOfWarnings = classes[i].amountOfWarnings;
	        	jsonArrClass.push({
	          	fileName: fileName,
	          	loc: linesOfCode,
	          	warnings: amountOfWarnings
	        	});
	      	}
    	}
  	}
	return {nodes: jsonArrClass, links: linksArray };
}