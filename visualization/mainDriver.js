var acceptedTypes =[];
var acceptedRuleNames =["PackageName","JavadocMethod"];

// Run first time
runTreeMap();
	
// on click checkbox
// sat type list changed and chart updated	
function handleClickTypeSat(cb) {
	if(document.getElementById('treemapButton').checked){
		if(cb.name == "sat"){
			var value = cb.value;
			var booleanChecked = cb.checked;  
			if(booleanChecked) {
				acceptedTypes.push(cb.value)
			} else{
				var index = acceptedTypes.indexOf(cb.value);
		  		console.log(index);
				if (index > -1) {
		    		acceptedTypes.splice(index, 1);
				}
			}
			runTreeMap();
		}
	} else if (document.getElementById('graphButton').checked) {
		if(cb.name == "sat"){
			var value = cb.value;
			var booleanChecked = cb.checked;  
			if(booleanChecked) {
				acceptedTypes.push(cb.value)
			} else{
				var index = acceptedTypes.indexOf(cb.value);
		  		console.log(index);
				if (index > -1) {
		    		acceptedTypes.splice(index, 1);
				}
			}
			removeChart();
			runGraph(graphJSON);
		}
	}
}

function handleClickVisualiser(radioButton){
		if(radioButton.value=="graph"){
			removeChart();
			runGraph(graphJSON);
		} else if (radioButton.value=="treemap"){
			removeChart();
			runTreeMap();
		}
}

function runTreeMap(){
	var element = document.getElementById("main-title");
    element.innerHTML = "Amount of warnings";

	var packages = filterTypeRuleNameTreeMap(acceptedTypes, acceptedRuleNames);
	var inputData = createJson(packages);	
	main({
    title: ""
	}, {
    fileName: "Test Project",
    values: inputData
	});	
}