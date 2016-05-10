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
				if (index > -1) {
		    		acceptedTypes.splice(index, 1);
				}
			}
			//runTreeMap();
		}
	} else if (document.getElementById('graphButton').checked) {
		if(cb.name == "sat"){
			var value = cb.value;
			var booleanChecked = cb.checked;  
			if(booleanChecked) {
				acceptedTypes.push(cb.value)
			} else{
				var index = acceptedTypes.indexOf(cb.value);
				if (index > -1) {
		    		acceptedTypes.splice(index, 1);
				}
			}
			removeChart();
			if(packagesLevel) {
				runGraph(graphJSON);
			} else {
				runGraph(window[sessionStorage.getItem('packageVariable')]);
			}
		}
	}
}

function handleClickVisualiser(radioButton){
	if(radioButton.value=="graph"){
		removeChart();
		packagesLevel = true;
		runGraph(graphJSON);
	} else if (radioButton.value=="treemap"){
		removeChart();
		runTreeMap();
	}
}

function getFilteredJSON(){
	var packages = filterTypeRuleNameTreeMap(acceptedTypes, acceptedRuleNames);
	return createJson(packages);	
}

function runTreeMap(){
	var element = document.getElementById("main-title");
    element.innerHTML = "Amount of warnings";
		
	main({
    title: ""
	}, {
    fileName: "Test Project",
    values: getFilteredJSON()
	});	
}

