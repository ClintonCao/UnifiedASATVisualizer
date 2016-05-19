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
		for (i = 0; i < classesArray.length; i++) {
	  		var classObject = new Object();
	  		classObjectJson = classesArray[i];
	  		classObject.amountOfCheckStyleWarnings = 0;
	  		classObject.amountOfPMDWarnings = 0;
	  		classObject.amountOfFindBugsWarnings = 0;
	  		classObject.amountOfWarnings = 0;
	  		classObject.loc = classObjectJson.loc;
	  		classObject.fileName = classObjectJson.fileName;
	  		for (j = 0; j < classObjectJson.warningList.length; j++) { 
				var warningJson = classObjectJson.warningList[j];
				if($.inArray(warningJson.type, acceptedTypes) > -1 && ($.inArray(warningJson.classification, acceptedCategories) > -1)) {
		  			classObject.amountOfWarnings++;
				}
	  		}
	  		classArray.push(classObject);
		}
	classArray.packageName = package.packageName;
	packageArray.push(classArray);
  	}
	return packageArray;
}

/*
 *
 * Counts for a specefic ASAT how many warnings there are
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
				if(warningJson.type == warningType) {
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
			jsonArrPackage.push({fileName: classes.packageName,values: jsonArrClass});
		}
	return [{fileName: "Project",values: jsonArrPackage}];
}

/*
 *
 * Creates a JSON file that could be used by the graph for package level
 *
 */
function createJsonGraphPackages(packages){
	var jsonArrPackage = [];
 	for(var p =0; p < packages.length; p++){
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
	return {nodes: jsonArrPackage, links: [{"source":0, "target":1, "value":11}] };
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
      	var classes = packages[p];
	      	for (var i = 0; i < classes.length; i++) {
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
	return {nodes: jsonArrClass, links: [{"source":0, "target":1, "value":11}] };
}