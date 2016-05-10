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
function filterTypeRuleName(acceptedTypes, acceptedRuleNames){
	var packageArray = []
  	for (p = 0; p < inputData2.length; p++) {
  		var package = inputData2[p];
		var classesArray = package.classes;
		var classArray = []
		for (i = 0; i < classesArray.length; i++) {
	  		var classObject = new Object();
	  		classObjectJson = classesArray[i];
	  		classObject.amountOfWarnings = 0;
	  		classObject.fileName = classObjectJson.fileName;
	  		for (j = 0; j < classObjectJson.warningList.length; j++) { 
				var warningJson = classObjectJson.warningList[j]
				//tmp disabled the acceptedrule filter
				if($.inArray(warningJson.type, acceptedTypes) > -1 && ($.inArray(warningJson.ruleName, acceptedRuleNames) > -1 || true)) {
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
				var amountOfWarnings = classes[i].amountOfWarnings;
				jsonArrClass.push({
					fileName: fileName,
					warnings: amountOfWarnings,
					value: Math.floor(Math.random() * 20)	
				});
			}
			jsonArrPackage.push({fileName: classes.packageName,values: jsonArrClass});
		}
	return [{fileName: "Project",values: jsonArrPackage}]
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
	        	var amountOfWarnings = classes[i].amountOfWarnings;
	        	jsonArrClass.push({
	          	fileName: fileName,
	          	loc: Math.floor(Math.random() * 20),
	          	warnings: amountOfWarnings
	        	});
	      	}
    	}
  	}
	return {nodes: jsonArrClass, links: [{"source":0, "target":1, "value":11}] }
}