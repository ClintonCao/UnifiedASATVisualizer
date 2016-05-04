var inputData2 = [
{
  "fileName": "Warning.java",
  "amountOfWarnings": 5,
  "filePath": "C:\\Users\\Clinton\\Documents\\GitHub\\ContextProject-TSE\\src\\main\\java\\BlueTurtle\\warnings\\Warning.java",
  "warningTypes": [
    "CheckStyle", "PMD"
  ],
  "warningList": [
    {
      "line": 1,
      "message": "Name BlueTurtle.warnings must match pattern ^[a-z]+(\\.[a-zA-Z_][a-zA-Z0-9_]*)*$.",
      "ruleName": "PackageName",
      "fileName": "Warning.java",
      "type": "CheckStyle",
      "filePath": "C:\\Users\\Clinton\\Documents\\GitHub\\ContextProject-TSE\\src\\main\\java\\BlueTurtle\\warnings\\Warning.java"
    },
    {
      "line": 13,
      "message": "Variable category must be private and have accessor methods.",
      "ruleName": "VisibilityModifier",
      "fileName": "Warning.java",
      "type": "CheckStyle",
      "filePath": "C:\\Users\\Clinton\\Documents\\GitHub\\ContextProject-TSE\\src\\main\\java\\BlueTurtle\\warnings\\Warning.java"
    },
    {
      "line": 14,
      "message": "Variable filename must be private and have accessor methods.",
      "ruleName": "VisibilityModifier",
      "fileName": "Warning.java",
      "type": "PMD",
      "filePath": "C:\\Users\\Clinton\\Documents\\GitHub\\ContextProject-TSE\\src\\main\\java\\BlueTurtle\\warnings\\Warning.java"
    },
    {
      "line": 15,
      "message": "Variable type must be private and have accessor methods.",
      "ruleName": "VisibilityModifier",
      "fileName": "Warning.java",
      "type": "CheckStyle",
      "filePath": "C:\\Users\\Clinton\\Documents\\GitHub\\ContextProject-TSE\\src\\main\\java\\BlueTurtle\\warnings\\Warning.java"
    },
    {
      "line": 20,
      "message": "Unused @param tag for filePath.",
      "ruleName": "JavadocMethod",
      "fileName": "Warning.java",
      "type": "CheckStyle",
      "filePath": "C:\\Users\\Clinton\\Documents\\GitHub\\ContextProject-TSE\\src\\main\\java\\BlueTurtle\\warnings\\Warning.java"
    }
  ]
},
{
  "fileName": "Chill.java",
  "amountOfWarnings": 5,
  "filePath": "C:\\Users\\Clinton\\Documents\\GitHub\\ContextProject-TSE\\src\\main\\java\\BlueTurtle\\warnings\\Warning.java",
  "warningTypes": [
    "CheckStyle", "PMD"
  ],
  "warningList": [
    {
      "line": 1,
      "message": "Name BlueTurtle.warnings must match pattern ^[a-z]+(\\.[a-zA-Z_][a-zA-Z0-9_]*)*$.",
      "ruleName": "PackageName",
      "fileName": "Warning.java",
      "type": "CheckStyle",
      "filePath": "C:\\Users\\Clinton\\Documents\\GitHub\\ContextProject-TSE\\src\\main\\java\\BlueTurtle\\warnings\\Warning.java"
    },
    {
      "line": 13,
      "message": "Variable category must be private and have accessor methods.",
      "ruleName": "VisibilityModifier",
      "fileName": "Warning.java",
      "type": "CheckStyle",
      "filePath": "C:\\Users\\Clinton\\Documents\\GitHub\\ContextProject-TSE\\src\\main\\java\\BlueTurtle\\warnings\\Warning.java"
    },
    {
      "line": 14,
      "message": "Variable filename must be private and have accessor methods.",
      "ruleName": "VisibilityModifier",
      "fileName": "Warning.java",
      "type": "PMD",
      "filePath": "C:\\Users\\Clinton\\Documents\\GitHub\\ContextProject-TSE\\src\\main\\java\\BlueTurtle\\warnings\\Warning.java"
    },
    {
      "line": 15,
      "message": "Variable type must be private and have accessor methods.",
      "ruleName": "VisibilityModifier",
      "fileName": "Warning.java",
      "type": "CheckStyle",
      "filePath": "C:\\Users\\Clinton\\Documents\\GitHub\\ContextProject-TSE\\src\\main\\java\\BlueTurtle\\warnings\\Warning.java"
    },
    {
      "line": 20,
      "message": "Unused @param tag for filePath.",
      "ruleName": "JavadocMethod",
      "fileName": "Warning.java",
      "type": "CheckStyle",
      "filePath": "C:\\Users\\Clinton\\Documents\\GitHub\\ContextProject-TSE\\src\\main\\java\\BlueTurtle\\warnings\\Warning.java"
    }
  ]
}
];


// replace \ fpr / in path for usages
function replaceAll(stringObject, target, replacement){
	return stringObject.split(target).join(replacement);
}

function filterType(acceptedTypes){
	for (i = 0; i < inputData2.length; i++) {
		var classObject = inputData2[i];
		for (j = 0; j < classObject.warningList.length; j++) { 
			var warning = classObject.warningList[j]
			if($.inArray(warning.type, acceptedTypes) > -1){
				console.log(warning.line);
			}
		}
	}
}

function filterRuleName(acceptedRuleNames){
	var classArray = []
	for (i = 0; i < inputData2.length; i++) {
		var classObject = new Object();
		classObjectJson = inputData2[i];
		classObject.amountOfWarnings = 0;
		classObject.fileName = classObjectJson.fileName;
		for (j = 0; j < classObjectJson.warningList.length; j++) { 
			var warningJson = classObjectJson.warningList[j]
			if($.inArray(warningJson.ruleName, acceptedRuleNames) > -1){
				classObject.amountOfWarnings++;
			}
		}
		classArray.push(classObject)
	}
	return classArray;
}

function createJson(classes){
	var jsonArr = [];
	for (var i = 0; i < classes.length; i++) {
		var fileName = classes[i].fileName;
		var amountOfWarnings = classes[i].amountOfWarnings;
		jsonArr.push({
			fileName: fileName,
			warnings: amountOfWarnings,
			value: Math.floor(Math.random() * 100) + 10
		});
	}
	
	return [{
			fileName: "Character",
			values: jsonArr
	}]
	
}

var acceptedTypes =["CheckStyle"];
var acceptedRuleNames =["PackageName","JavadocMethod"];
var classes = filterRuleName(acceptedRuleNames);
console.log(createJson(classes));
var inputData = createJson(classes);
//filterType(acceptedTypes);




