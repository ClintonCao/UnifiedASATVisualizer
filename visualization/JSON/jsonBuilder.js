/**
 * A class which holds all functions that uses JSON 
 */

/**
 * Filter on type of tool and/or warnings
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
	  		classObject.message = classObjectJson.message;
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
		  			switch(categoryMapper.categorizeWarningType(warningJson.classification)) {
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

/**
 * Counts for a specific ASAT how many warnings there are
 */
function getTotalASATWarning(warningType, className) {
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
				var warningJson = classObjectJson.warningList[j];
				if(className.indexOf("java") > -1) {
					if(warningJson.type == warningType && ($.inArray(warningJson.classification, acceptedCategories) > -1) && warningJson.fileName == className) {
		  				classObject.amountOfWarnings++;
					}
				} else {
					if(warningJson.type == warningType && ($.inArray(warningJson.classification, acceptedCategories) > -1)) {
		  				classObject.amountOfWarnings++;
					}
				}
	  		}
	  		classArray.push(classObject);
		}
		classArray.packageName = package.packageName;
		packageArray.push(classArray)
	}
	packageArray.warningType = warningType;
	return packageArray;
}

/**
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
					if(($.inArray(warningJson.classification, acceptedCategories) > -1) && ($.inArray(warningJson.type, acceptedTypes) > -1)) {
						classObject.amountOfWarnings++;
						var warningObject = new Object();
						warningObject.line = warningJson.line;
						warningObject.type = warningJson.type;
						warningObject.message = warningJson.message;
						warningObject.cat = warningJson.classification;
						classObject.warningList.push(warningObject);
					}
				}
			}
		}
	}
	return classObject;
}
/**
 * Counts for a specific category how many warnings there are
 */
function getTotalCategoryWarning(warningType, className) {
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
				var warningJson = classObjectJson.warningList[j];
				if(className.indexOf("java") > -1) {
					if($.inArray(warningJson.type, acceptedTypes) > -1 && warningJson.classification == warningType && warningJson.fileName == className) {
			  			classObject.amountOfWarnings++;
					}
				} else {
					if($.inArray(warningJson.type, acceptedTypes) > -1 && warningJson.classification == warningType) {
			  			classObject.amountOfWarnings++;
					}
				}
	  		}
	  		classArray.push(classObject);
		}
		classArray.packageName = package.packageName;
		packageArray.push(classArray)
	}
	packageArray.warningType = warningType;
	return packageArray;
}

/**
 * Creates a JSON file that could be used by the tree map
 */
function createJsonTreeMap(packages){
	return createPackageJsonTreeMap(packages);
}

/**
 * Creates package part of JSON
 */
function createPackageJsonTreeMap(packages) {
	var jsonArrPackage = [];
	var upperLevelLoc = upperLevelTotal = upperLevelCSW = upperLevelPMDW = upperLevelFBW = upperLevelFD = upperLevelMD =  upperLevelOD = 0;
	for(var p =0; p < packages.length; p++){
		var classes = packages[p];
		var tuple = createClassJsonTreeMap(classes);
		var jsonArrClass = tuple[0];
		var middleLevelLOC = tuple[1];
		upperLevelCSW += classes.amountOfCheckStyleWarnings; upperLevelPMDW += classes.amountOfPMDWarnings;
		upperLevelFBW += classes.amountOfFindBugsWarnings; upperLevelFD += classes.amountOfFunctionalDefects;
		upperLevelMD += classes.amountOfMaintainabilityDefects; upperLevelOD += classes.amountOfOtherDefects;
		upperLevelTotal += classes.amountOfWarnings; upperLevelLoc += middleLevelLOC;
		jsonArrPackage.push(
			{
				fileName: classes.packageName, 
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
	return jsonArrPackage;
}

/**
 * Creates class part of JSON
 */
function createClassJsonTreeMap(classes) {
	var jsonArrClass = [];
	var middleLevelLOC = 0;
	for (var i = 0; i < classes.length; i++) {
		jsonArrClass.push({
			fileName: classes[i].fileName,
			filePath: classes[i].filePath,
			message: classes[i].message,
			loc: classes[i].loc,
			warnings: classes[i].amountOfWarnings,
			warningsCheckStyle: classes[i].amountOfCheckStyleWarnings,
			warningsPMD: classes[i].amountOfPMDWarnings,
			warningsFindBugs: classes[i].amountOfFindBugsWarnings,
			warningsFunctionalDefects: classes[i].amountOfFunctionalDefects,
			warningsMaintainabilityDefects: classes[i].amountOfMaintainabilityDefects,
			warningsOtherDefects: classes[i].amountOfOtherDefects,
			value: classes[i].loc
		});
		middleLevelLOC += classes[i].loc;
	}
	return [jsonArrClass, middleLevelLOC];
}


/**
 * Replace \ for / in path for usage
 */ 
function replaceAll(stringObject, target, replacement){
	return stringObject.split(target).join(replacement);
}