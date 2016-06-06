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
		var FDW = 0;
		var MDW = 0;
		var ODW = 0;
		var LOC = 0;
		for (i = 0; i < classesArray.length; i++) {
	  		var classObject = new Object();
	  		classObjectJson = classesArray[i];
	  		classObject.amountOfWarnings = 0;
	  		classObject.amountOfCheckStyleWarnings = 0;
			classObject.amountOfPMDWarnings = 0;
			classObject.amountOfFindBugsWarnings = 0;
			classObject.amountOfFunctionalDefects = 0;
			classObject.amountOfMaintainabilityDefects = 0;
			classObject.amountOfOtherDefects = 0;
	  		classObject.loc = classObjectJson.loc;
	  		classObject.filePath = classObjectJson.filePath;
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
		  			switch(categorizeWarningType(warningJson.classification)) {
		  				case 0:
		  					classObject.amountOfFunctionalDefects++;
		  					break;
		  				case 1:
		  					classObject.amountOfMaintainabilityDefects++;
		  					break;
		  				case 2:
		  					classObject.amountOfOtherDefects++;
		  					break;
		  				default: 
		  					break;
		  			}
				}
	  		}
	  		CSW += classObject.amountOfCheckStyleWarnings;
	  		PMDW += classObject.amountOfPMDWarnings;
	  		FBW += classObject.amountOfFindBugsWarnings;
	  		FDW += classObject.amountOfFunctionalDefects;
	  		MDW += classObject.amountOfMaintainabilityDefects;
	  		ODW += classObject.amountOfOtherDefects;
	  		LOC += classObject.loc;
	  		classArray.push(classObject);
		}
	classArray.packageName = package.packageName;
	classArray.LOC = LOC;
	classArray.amountOfCheckStyleWarnings = CSW;
	classArray.amountOfPMDWarnings = PMDW;
	classArray.amountOfFindBugsWarnings = FBW;
	classArray.amountOfFunctionalDefects = FDW;
	classArray.amountOfMaintainabilityDefects = MDW;
	classArray.amountOfOtherDefects = ODW;
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
	var classObject = new Object();
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
 * Get object with all warnings with lines from a certain class
 * filterd on warning type
 */
function getWarningLines(className) {
	for(var p =0; p < inputData.length; p++){
		var package = inputData[p];
		var classesArray = package.classes;
		var classArray = [];
		for (i = 0; i < classesArray.length; i++) {
			classObjectJson = classesArray[i];
			if(className == classObjectJson.fileName){
				var classObject = new Object();
				classObject.amountOfWarnings = 0;
				classObject.fileName = classObjectJson.fileName;
				classObject.warningList = []
				for (j = 0; j < classObjectJson.warningList.length; j++) { 
					var warningJson = classObjectJson.warningList[j]
					if(($.inArray(warningJson.classification, acceptedCategories) > -1)) {
						classObject.amountOfWarnings++;
						var warningObject = new Object();
						warningObject.line = warningJson.line;
						warningObject.type = warningJson.type;
						warningObject.cat = warningJson.classification;
						classObject.warningList.push(warningObject);
					}
				}
			}
		}
	}
	return classObject;
}
/*
 *
 * Counts for a specific category how many warnings there are
 *
 */
function getTotalCategoryWarning(warningType) {
	var packageArray = [];
	var classObject = new Object();
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
				if($.inArray(warningJson.type, acceptedTypes) > -1 && warningJson.classification == warningType) {
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
	var upperLevelLoc = 0;
	var upperLevelTotal = 0;
	var upperLevelCSW = 0;
	var upperLevelPMDW = 0;
	var upperLevelFBW = 0;
	var upperLevelFD = 0;
	var upperLevelMD = 0;
	var upperLevelOD = 0;
		for(var p =0; p < packages.length; p++){
			var jsonArrClass = [];
			var classes = packages[p];
			var middleLevelLOC = 0;
			for (var i = 0; i < classes.length; i++) {
				var fileName = classes[i].fileName;
				var linesOfCode = classes[i].loc;
				jsonArrClass.push({
					fileName: fileName,
					filePath: classes[i].filePath,
					loc: linesOfCode,
					warnings: classes[i].amountOfWarnings,
					warningsCheckStyle: classes[i].amountOfCheckStyleWarnings,
					warningsPMD: classes[i].amountOfPMDWarnings,
					warningsFindBugs: classes[i].amountOfFindBugsWarnings,
					warningsFunctionalDefects: classes[i].amountOfFunctionalDefects,
					warningsMaintainabilityDefects: classes[i].amountOfMaintainabilityDefects,
					warningsOtherDefects: classes[i].amountOfOtherDefects,
					value: linesOfCode
				});
				middleLevelLOC += classes[i].loc;
			}

			upperLevelCSW += classes.amountOfCheckStyleWarnings;
			upperLevelPMDW += classes.amountOfPMDWarnings;
			upperLevelFBW += classes.amountOfFindBugsWarnings;
			upperLevelFD += classes.amountOfFunctionalDefects;
			upperLevelMD += classes.amountOfMaintainabilityDefects;
			upperLevelOD += classes.amountOfOtherDefects;
			upperLevelTotal += classes.amountOfWarnings;
			upperLevelLoc += middleLevelLOC;

			jsonArrPackage.push(
				{
					fileName: classes.packageName, 
					filePath: classes.filePath,
					loc: middleLevelLOC,
					values: jsonArrClass,
					warnings: classes.amountOfWarnings,
					warningsCheckStyle: classes.amountOfCheckStyleWarnings,
					warningsPMD: classes.amountOfPMDWarnings,
					warningsFindBugs: classes.amountOfFindBugsWarnings,
					warningsFunctionalDefects: classes.amountOfFunctionalDefects,
					warningsMaintainabilityDefects: classes.amountOfMaintainabilityDefects,
					warningsOtherDefects: classes.amountOfOtherDefects
				});
		}
	return [
		{
			fileName: "Project",
			loc: upperLevelLoc,
			values: jsonArrPackage,
			warnings: upperLevelTotal,
			warningsCheckStyle: upperLevelCSW,
			warningsPMD: upperLevelPMDW,
			warningsFindBugs: upperLevelFBW,
			warningsFunctionalDefects: upperLevelFD,
			warningsMaintainabilityDefects: upperLevelMD,
			warningsOtherDefects: upperLevelOD
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