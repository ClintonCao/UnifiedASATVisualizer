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
	var packageArray = []
  	for (p = 0; p < inputData.length; p++) {
  		var package = inputData[p];
		var classesArray = package.classes;
		var classArray = []
		for (i = 0; i < classesArray.length; i++) {
	  		var classObject = new Object();
	  		classObjectJson = classesArray[i];
	  		classObject.amountOfWarnings = 0;
	  		classObject.loc = classObjectJson.loc;
	  		classObject.fileName = classObjectJson.fileName;
	  		for (j = 0; j < classObjectJson.warningList.length; j++) { 
				var warningJson = classObjectJson.warningList[j]
				//tmp disabled the acceptedrule filter
				if($.inArray(warningJson.type, acceptedTypes) > -1 && false || ($.inArray(warningJson.ruleName, acceptedCategories) > -1)) {
		  			classObject.amountOfWarnings++;
				}
	  		}
	  		classArray.push(classObject)
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
				var amountOfWarnings = classes[i].amountOfWarnings;
				jsonArrClass.push({
					fileName: fileName,
					warnings: amountOfWarnings,
					value: linesOfCode
				});
			}
			jsonArrPackage.push({fileName: classes.packageName,values: jsonArrClass});
		}
	return [{fileName: "Project",values: jsonArrPackage}]
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
  		console.log(jsonArrPackage);
	}
	return {nodes: jsonArrPackage, links: [{"source":0, "target":1, "value":11}] }
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
	        	console.log(classes[i].loc);
	        	jsonArrClass.push({
	          	fileName: fileName,
	          	loc: linesOfCode,
	          	warnings: amountOfWarnings
	        	});
	      	}
    	}
  	}
	return {nodes: jsonArrClass, links: [{"source":0, "target":1, "value":11}] }
}