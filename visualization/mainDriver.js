/*
 * Global variabeles that are used across multiple classes
 */
var acceptedTypes = [];
var acceptedCategories = [];

defineHovers();
runTreeMap();
addAllAcceptedTypesAndCategories();
setAllCheckboxesOnDefault();

var images = [
  "images/bg1.png",
  "images/bg2.png",
  "images/bg3.png",
  "images/bg4.png"  
];
var $body = $("body"),
    $bg = $("#bg"),
    n = images.length,
    c = 0; // Loop Counter

// Preload Array of images...
for(var i=0; i<n; i++){
  var tImg = new Image();
  tImg.src = images[i];
}

$bg.css({backgroundImage : "url("+images[c]+")"}); 

(function loopBg(){
  $bg.hide().css({backgroundImage : "url("+images[++c%n]+")"}).delay(2000).fadeTo(1200, 1, function(){
    $body.css({backgroundImage : "url("+images[c%n]+")"}); 
    loopBg();
  });
}());

function setAllCheckboxesOnDefault() {
	$(".updateContent").prop('checked', false); 
	$("#treemapButton").prop('checked', true);
	$("#absoluteButton").prop('checked', true);
	$(".FunctionalDefects").click();
	$(".MaintainabilityDefects").click();
	$(".StyleConventions").click();
	$(".sats").click();
}
// add all types and categories for first run
function addAllAcceptedTypesAndCategories(){
	for ( var i = 0; i < $(".FunctionalDefects").size(); i ++){
		handleClickCategorySat($(".FunctionalDefects")[i].value, true);
	}
	for ( var i = 0; i < $(".MaintainabilityDefects").size(); i ++){
		handleClickCategorySat($(".MaintainabilityDefects")[i].value, true);
	}
	for ( var i = 0; i < $(".StyleConventions").size(); i ++){
		handleClickCategorySat($(".StyleConventions")[i].value, true);
	}
	for ( var i = 0; i < $(".sats").size(); i ++){
		handleClickTreeMapTypeSat($(".sats")[i].value, true);
	}
}

// Delete the entire chart from the page.
function removeChart() {
    var chartNode = document.getElementById("chart");
    while (chartNode.firstChild) {
        chartNode.removeChild(chartNode.firstChild);
    }
}

// Define all the hover functions for the filterable categories
function defineHovers() {
    var allGDCHovers = ["#GDCFunctionalDefects", "#GDCCheck", "#GDCConcurrency", "#GDCErrorHandling", "#GDCInterface",
         "#GDCLogic", "#GDCMigration", "#GDCResource", "#GDCMaintainabilityDefects", "#GDCBestPractices", "#GDCCodeStructure", 
            "#GDCDocConventions", "#GDCMetric", "#GDCNamingConventions", "#GDCOODesign", "#GDCSimplifications", "#GDCRedundancies",
                "#GDCStyleConventions", "#GDCOther", "#GDCRegularExpressions", "#GDCToolSpecific"];

    for(var i = 0; i < allGDCHovers.length; i++) {
        showHover(allGDCHovers[i]);
    }
}

// Add total amount of warnings to the Sat types in the menu
function appendInfoToSAT(CS, PMD, FB) {
    var checkStyleElement = document.getElementById("checkStyleLabel");
    var PMDElement = document.getElementById("PMDLabel");
    var findBugsElement = document.getElementById("FindBugsLabel");

    checkStyleElement.innerHTML = '&nbsp; CheckStyle (' + CS + ")";
    PMDElement.innerHTML = "&nbsp; PMD (" + PMD + ")";
    findBugsElement.innerHTML = "&nbsp; FindBugs (" + FB + ")";
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