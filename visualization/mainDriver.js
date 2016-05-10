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
				if (index > -1) {
		    		acceptedTypes.splice(index, 1);
				}
			}
			removeChart();
			if(packagesLevel) {
				var packages = filterTypeRuleName(acceptedTypes, acceptedRuleNames);
				var inputData = createJsonGraphPackages(packages);

				if(graphTrace.indexOf(graphTraceIndex) == -1) {
					graphTrace.push(inputData);
				} else if(graphTrace.indexOf(graphTraceIndex) == 0) {
					graphTrace[graphTraceIndex] = inputData;
				}

				createGraph(graphTrace[graphTraceIndex]);
			} else {
				var packages = filterTypeRuleName(acceptedTypes, acceptedRuleNames);
				var inputData = createJsonGraphClasses(packages, sessionStorage.getItem('packageName'));

				if(graphTrace.indexOf(graphTraceIndex) == -1) {
					graphTrace.push(inputData);
				} else if(graphTrace.indexOf(graphTraceIndex) == 0) {
					graphTrace[graphTraceIndex] = inputData;
				}

				createGraph(graphTrace[graphTraceIndex]);
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

/*
 * Setup tree map and shows it
 */
function runTreeMap(){
	removeChart();
	var title = document.getElementById("main-title");
	title.innerHTML = "Amount of warnings";
	var graphButtonDiv = document.getElementById("sub-title");
	graphButtonDiv.style.display = 'none';

	var packages = filterTypeRuleName(acceptedTypes, acceptedRuleNames);
	var inputData = createJsonTreeMap(packages);	
	createTreeMap({
    title: ""
	}, {
    fileName: "Test Project",
    values: inputData
	});	
}

/*
 * Setup graph and shows it
 */
function runGraph(){
	removeChart();
	packagesLevel = true;
	graphTraceIndex = 0;
	var graphButtonDiv = document.getElementById("sub-title");
	graphButtonDiv.style.display = 'inline';
	var graphButton = document.getElementById('back-button');
	graphButton.firstChild.data = "This is the upperview";
	graphButton.disabled = true;

	var packages = filterTypeRuleName(acceptedTypes, acceptedRuleNames);
	var inputData = createJsonGraphPackages(packages);

	if(graphTrace.indexOf(graphTraceIndex) == -1) {
		graphTrace.push(inputData);
	} else if(graphTrace.indexOf(graphTraceIndex) == 0) {
		graphTrace[graphTraceIndex] = inputData;
	}

	console.log(inputData);
	createGraph(inputData);
}