var acceptedTypes =[];
var acceptedRuleNames =["PackageName","JavadocMethod"];
//run first time
main({
    title: "Amount of warnings"
	}, {
    fileName: "Test Project",
    values: loadJson()
	});	
	
	
// on click checkbox
// sat type list changed and chart updated	
function handleClickTypeSat(cb) {
  if(cb.name == "sat"){
  	console.log(cb.value);
	var value = cb.value;
	var booleanChecked = cb.checked;  
	if(booleanChecked){
		acceptedTypes.push(cb.value)
	}else{
		var index = acceptedTypes.indexOf(cb.value);
  		console.log(index);
		if (index > -1) {
    		acceptedTypes.splice(index, 1);
		}
	}
  	console.log(acceptedTypes);
	main({
    title: "Amount of warnings"
	}, {
    fileName: "Test Project",
    values: loadJson()
	});	
  }
}

function loadJson(){
	var packages = filterTypeRuleName(acceptedTypes, acceptedRuleNames);
	var inputData = createJson(packages);	
	return inputData
}