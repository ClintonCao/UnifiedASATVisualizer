/*
 * Global variabeles that are used across multiple classes
 */
var graphTrace = [];
var graphTraceIndex = 0;
var acceptedTypes =[];
var acceptedRuleNames =["PackageName","JavadocMethod"];

// Run first time
runTreeMap();
	
/*
 * Handles click on checkboxes for showing results of different tools
 */
function handleClickTreeMapTypeSat(value){
			if(acceptedTypes.indexOf(value)<0) {
				acceptedTypes.push(value);
			} else{
				var index = acceptedTypes.indexOf(value);
				if (index > -1) {
		    		acceptedTypes.splice(index, 1);
				}
			}
}
function handleClickTypeSat(cb) {
	if(document.getElementById('treemapButton').checked){
		/*
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
		}*/
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
				var packages = filterTypeRuleName(acceptedTypes, acceptedRuleNames);
				var inputData = createJsonGraphPackages(packages);
				runGraph(inputData);
			} else {
				var packages = filterTypeRuleName(acceptedTypes, acceptedRuleNames);
				console.log()
				var inputData = createJsonGraphClasses(packages, sessionStorage.getItem('packageName'));
				runGraph(inputData);
			}
		}
	}
}

/*
 * Toggles between the graph and tree map visualization
 */
function handleClickVisualiser(radioButton){
	if(radioButton.value=="graph"){
		runGraph();
	} else if (radioButton.value=="treemap"){
		runTreeMap();
	}
}
function getFilteredJSON(){
	var packages = filterTypeRuleName(acceptedTypes, acceptedRuleNames);
	return createJsonTreeMap(packages);	
}

/*
 * Setup tree map and shows it
 */
function runTreeMap(){
	removeChart();
	var element = document.getElementById("main-title");
    element.innerHTML = "Amount of warnings";
	var packages = filterTypeRuleName(acceptedTypes, acceptedRuleNames);
	var inputData = createJsonTreeMap(packages);	
	createTreeMap({title: ""}, {fileName: "Test Project", values: getFilteredJSON()});	
}

/*
 * Setup graph and shows it
 */
function runGraph(){
	removeChart();
	packagesLevel = true;
	graphTraceIndex = 0;

	var packages = filterTypeRuleName(acceptedTypes, acceptedRuleNames);
	var inputData = createJsonGraphPackages(packages);
	console.log(inputData);
	createGraph(inputData);
}
