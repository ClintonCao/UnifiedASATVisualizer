var acceptedTypes =[];
var acceptedRuleNames =["PackageName","JavadocMethod"];
//run first time
runTreeMap();
	
	
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
	runTreeMap();
	
  }
}

function handleClickVisualiser(radioButton){
		if(radioButton.value=="graph"){
  			console.log("graph");
			removeChart();
			runGraph(graphJSON);
		}else if (radioButton.value=="treemap"){
			removeChart();
			runTreeMap();
		}
}

function runTreeMap(){
	
	var packages = filterTypeRuleName(acceptedTypes, acceptedRuleNames);
	var inputData = createJson(packages);	
	main({
    title: "Amount of warnings"
	}, {
    fileName: "Test Project",
    values: inputData
	});	
}