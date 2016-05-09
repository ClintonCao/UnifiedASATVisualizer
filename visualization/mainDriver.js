var acceptedTypes =[];
var acceptedRuleNames =["PackageName","JavadocMethod"];

// Run first time
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
			removeChart();
			runGraph(graphJSON);
			document.getElementById("checkstyleButton").disabled = true; 
			document.getElementById("pmdButton").disabled = true; 
			document.getElementById("findBugsButton").disabled = true;
			document.getElementById("checkstyleButton").checked = false;
			document.getElementById("pmdButton").checked = false;
			document.getElementById("findBugsButton").checked = false;
		} else if (radioButton.value=="treemap"){
			removeChart();
			runTreeMap();
			document.getElementById("checkstyleButton").disabled = false; 
			document.getElementById("pmdButton").disabled = false; 
			document.getElementById("findBugsButton").disabled = false; 
		}
}

function runTreeMap(){
	var element = document.getElementById("main-title");
    element.innerHTML = "Amount of warnings";

	var packages = filterTypeRuleName(acceptedTypes, acceptedRuleNames);
	var inputData = createJson(packages);	
	main({
    title: ""
	}, {
    fileName: "Test Project",
    values: inputData
	});	
}