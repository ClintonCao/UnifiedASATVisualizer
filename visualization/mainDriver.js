/*
 * Global variabeles that are used across multiple classes
 */
var graphTrace = [];
var graphTraceIndex = 0;
var acceptedTypes = [];
var acceptedCategories = [];

// Run first time
runTreeMap();
setAllCheckboxesOnDefault();

function setAllCheckboxesOnDefault() {
	$(".updateContent").prop('checked', false); 
	$("#treemapButton").prop('checked', true);
	$("#absoluteButton").prop('checked', true);
	$(".FunctionalDefects").click();
	$(".MaintainabilityDefects").click();
	$(".StyleConventions").click();
}


/*
 * Handles click on checkboxes for showing results of different tools
 */
function handleClickTreeMapTypeSat(value, checked) {
 
	if (checked) {
		acceptedTypes.push(value)
    } else {
        var index = acceptedTypes.indexOf(value);
        if (index > -1) {
            acceptedTypes.splice(index, 1);
        }
    }
}

/*
 * Handles click on checkboxes for using different colorscales
 */
function handleClickColorScale(radioButton) {
	if ( radioButton.value == "absolute" ){
		colorScale.colorsAbsolute()
	}else{
		colorScale.colorsRelative()
	}
	if (document.getElementById('graphButton').checked) {
		removeChart();
		createGraph(graphTrace[graphTraceIndex]);
	}
}

/*
 * toggle all category checkboxes of a group
 */
function handleClickCategory(category) {
	// for next week
	/*
	if( category == "FunctionDefects" ){
		$('.FunctionalDefects').click()
		
		
	}else if( category == "MaintainabilityDefects" ){
		$('.MaintainabilityDefects').click()
		
		
	}else if( category == "StyleConventions" ){
		$('.StyleConventions').click()
	}*/
}

// individual clicks
function handleClickCategorySat(checkbox) {	
 	if (checkbox.checked) {
		acceptedCategories.push(checkbox.value)
    } else {
        var index = acceptedCategories.indexOf(checkbox.value);
        if (index > -1) {
            acceptedCategories.splice(index, 1);
        }
    }
	console.log(acceptedCategories);
}


// Delete the entire chart from the page.
function removeChart() {
    var chartNode = document.getElementById("chart");
    while (chartNode.firstChild) {
        chartNode.removeChild(chartNode.firstChild);
    }
}

function appendInfoToSAT(CS, PMD, FB) {
    var checkStyleElement = document.getElementById("checkStyleLabel");
    var PMDElement = document.getElementById("PMDLabel");
    var findBugsElement = document.getElementById("FindBugsLabel");

    checkStyleElement.innerHTML = 'CheckStyle (' + CS + ")";
    PMDElement.innerHTML = "PMD (" + PMD + ")";
    findBugsElement.innerHTML = "FindBugs (" + FB + ")";
}

function handleClickTypeSat(cb) {
    if (document.getElementById('treemapButton').checked) {
        // Is taken care off in the treemap self
        // this way the treemap can refresh at current level.
    } else if (document.getElementById('graphButton').checked) {
        if (cb.name == "sat") {
            var value = cb.value;
            handleClickTreeMapTypeSat(value, cb.checked);
            removeChart();
    if (packagesLevel) {
        var packages = filterTypeRuleName(acceptedTypes, acceptedCategories);
        var input = createJsonGraphPackages(packages);

        if (typeof graphTrace[graphTraceIndex] === 'undefined') {
            graphTrace.push(input);
        } else {
            graphTrace[graphTraceIndex] = input;
        }
        createGraph(graphTrace[graphTraceIndex]);
    } else {
        var packages = filterTypeRuleName(acceptedTypes, acceptedCategories);
        var input = createJsonGraphClasses(packages, sessionStorage.getItem('packageName'));

        if (typeof graphTrace[graphTraceIndex] === 'undefined') {
            graphTrace.push(input);
        } else {
            graphTrace[graphTraceIndex] = input;
        }
        createGraph(graphTrace[graphTraceIndex]);
    }
        }
    }
}

/*
 * Toggles between the graph and tree map visualization
 */
function handleClickVisualiser(radioButton) {
    if (radioButton.value == "graph") {
        runGraph();
    } else if (radioButton.value == "treemap") {
        runTreeMap();
    }
}

function getFilteredJSON() {
    var packages = filterTypeRuleName(acceptedTypes, acceptedCategories);
    return createJsonTreeMap(packages);
}

/*
 * Setup tree map and shows it
 */
function runTreeMap() {
    removeChart();
    var title = document.getElementById("main-title");
    title.innerHTML = "Treemap view of project";
    var graphButtonDiv = document.getElementById("sub-title");
    graphButtonDiv.style.display = 'none';

    var packages = filterTypeRuleName(acceptedTypes, acceptedCategories);

    treeMapBuilder.createTreeMap({
        title: ""
    }, {
        fileName: "Test Project",
        values: getFilteredJSON()
    });
}

/*
 * Setup graph and shows it
 */
function runGraph() {
    removeChart();
    packagesLevel = true;
    graphTraceIndex = 0;
    var graphButtonDiv = document.getElementById("sub-title");
    graphButtonDiv.style.display = 'inline';
    var graphButton = document.getElementById('back-button');
    graphButton.firstChild.data = "This is the upperview";
    graphButton.disabled = true;

    var packages = filterTypeRuleName(acceptedTypes, acceptedCategories);

    var input = createJsonGraphPackages(packages);

    if (typeof graphTrace[graphTraceIndex] === 'undefined') {
        graphTrace.push(input);
    } else {
        graphTrace[graphTraceIndex] = input;
    }
    createGraph(graphTrace[graphTraceIndex]);
}