/*
 * Global variabeles that are used across multiple classes
 */
var acceptedTypes = [];
var acceptedCategories = [];

// Run first time
runTreeMap();
setAllCheckboxesOnDefault();

function setAllCheckboxesOnDefault() {
		 console.log("setAllCheckboxesOnDefaultd");
	$(".updateContent").prop('checked', false); 
	$("#treemapButton").prop('checked', true);
	$("#absoluteButton").prop('checked', true);
	$(".FunctionalDefects").click();
	$(".MaintainabilityDefects").click();
	$(".StyleConventions").click();
}

// Delete the entire chart from the page.
function removeChart() {
    var chartNode = document.getElementById("chart");
    while (chartNode.firstChild) {
        chartNode.removeChild(chartNode.firstChild);
    }
}

// Add total amount of warnings to the Sat types in the menu
function appendInfoToSAT(CS, PMD, FB) {
    var checkStyleElement = document.getElementById("checkStyleLabel");
    var PMDElement = document.getElementById("PMDLabel");
    var findBugsElement = document.getElementById("FindBugsLabel");

    checkStyleElement.innerHTML = ' CheckStyle (' + CS + ")";
    PMDElement.innerHTML = " PMD (" + PMD + ")";
    findBugsElement.innerHTML = " FindBugs (" + FB + ")";
}

//Setup tree map and shows it
function runTreeMap() {
    removeChart();
    var title = document.getElementById("main-title");
    title.innerHTML = "Treemap view of project";
    var graphButtonDiv = document.getElementById("sub-title");
    graphButtonDiv.style.display = 'none';

    var packages = filterTypeRuleName(acceptedTypes, acceptedCategories);
    var finalJson =  createJsonTreeMap(packages);
	
    treeMapBuilder.createTreeMap({
        title: ""
    }, {
        fileName: "Test Project",
        values: finalJson
    });
}

//Setup graph and shows it
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