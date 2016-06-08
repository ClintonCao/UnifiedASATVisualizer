/*
 * Global variabeles that are used across multiple classes
 */
var acceptedTypes = [];
var acceptedCategories = [];

defineHovers();
runTreeMap();
addAllAcceptedTypesAndCategories();
setAllCheckboxesOnDefault();

function goToRelevantLevel(d, fromSourceCode, nodePath, allNodes) {
	nodePath.pop();
	allNodes.pop();
	console.log("the D: ");
	console.log(fromSourceCode);
	if(fromSourceCode) {
		$('input.updateContent').attr('disabled', false);
		//$('#current-path').html(packagePath);
  		sourceCode.hide();
	} else {
		removeChart();
	}	
	var packages = filterTypeRuleName(acceptedTypes, acceptedCategories);
    var finalJson =  createJsonTreeMap(packages);
    treeMapBuilder.navigationUp({
        title: ""
    }, {
        fileName: projectName,
        values: finalJson
    }, d, nodePath, allNodes);
}
/*
 * Will set all available checkboxes on checked
 */
function setAllCheckboxesOnDefault() {
	$(".updateContent").prop('checked', false); 
	$("#treemapButton").prop('checked', true);
	$("#normalButton").prop('checked', true);
	$(".FunctionalDefects").click();
	$(".MaintainabilityDefects").click();
	$(".StyleConventions").click();
	$(".sats").click();
}

/*
 * In the first run all ASATs and categories are included
 */
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

// Add total amount of warnings to the different ASATs in the left menu
function appendInfoToSAT(CS, PMD, FB) {
    var checkStyleElement = document.getElementById("checkStyleLabel");
    var PMDElement = document.getElementById("PMDLabel");
    var findBugsElement = document.getElementById("FindBugsLabel");

    checkStyleElement.innerHTML = '&thinsp; CheckStyle&thinsp;(' + CS + ")";
    PMDElement.innerHTML = "&thinsp; PMD&thinsp;(" + PMD + ")";
    findBugsElement.innerHTML = "&thinsp; FindBugs&thinsp;(" + FB + ")";
}

/*
 * Sum how many active functional defects there are
 */
function sumFunctionalDefects(Check, Conc, ErrorH, Inter, Logic, Mig, Res) {
	var summation = 0;
	for(var i = 0; i < acceptedCategories.length; i++) {
		switch(acceptedCategories[i]) {
			case 'Check':
				summation += Check;
				break;
			case 'Concurrency':
				summation += Conc;
				break;
			case 'ErrorHandling':
				summation += ErrorH;
				break;
			case 'Interface':
				summation += Inter;
				break;
			case 'Logic':
				summation += Logic;
				break;
			case 'Migration':
				summation += Mig;
				break;
			case 'Resource':
				summation += Res;
				break;
		}
	}
	return summation;
}

/*
 * Sum how many active maintainability defects there are
 */
function sumMaintainabilityDefects(Prac, Struc, DocConv, Metric, NamConv, OOD, Simp, Red, StyleConv) {
	var summation = 0;
	for(var i = 0; i < acceptedCategories.length; i++) {
		switch(acceptedCategories[i]) {
			case 'Best Practices':
				summation += Prac;
				break;
			case 'Code Structure':
				summation += Struc;
				break;
			case 'Documentation Conventions':
				summation += DocConv;
				break;
			case 'Metric':
				summation += Metric;
				break;
			case 'Naming Conventions':
				summation += NamConv;
				break;
			case 'Object Oriented Design':
				summation += OOD;
				break;
			case 'Refactorings - Simplifications':
				summation += Simp;
				break;
			case 'Refactorings - Redundancies':
				summation += Red;
				break;
			case 'Style Conventions':
				summation += StyleConv;
				break;
		}
	}
	return summation;
}

/*
 * Sum how many active other defects there are
 */
function sumOtherDefects(Other, RegExpr, Tools) {
	var summation = 0;
	for(var i = 0; i < acceptedCategories.length; i++) {
		switch(acceptedCategories[i]) {
			case 'Other':
				summation += Other;
				break;
			case 'Regular Expressions':
				summation += RegExpr;
				break;
			case 'Tool Specific':
				summation += Tools;
				break;
		}
	}
	return summation;
}

// Add total amount of warnings to each sub category within the functional defects in the right menu
function appendInfoToFunctionalDefects(Check, Conc, ErrorH, Inter, Logic, Mig, Res) {
    var CheckElement = document.getElementById("CheckLabel");
    var ConcElement = document.getElementById("ConcurrencyLabel");
    var ErrorElement = document.getElementById("ErrorHandlingLabel");
    var InterfaceElement = document.getElementById("InterfaceLabel");
    var LogicElement = document.getElementById("LogicLabel");
    var MigrationElement = document.getElementById("MigrationLabel");
    var ResourceElement = document.getElementById("ResourceLabel");
    var FuncDefElement = document.getElementById("FuncDefLabel");

    CheckElement.innerHTML = '&thinsp; Check (' + Check + ")";
    ConcElement.innerHTML = "&thinsp; Concurrency (" + Conc + ")";
    ErrorElement.innerHTML = "&thinsp; Error Handling (" + ErrorH + ")";
    InterfaceElement.innerHTML = '&thinsp; Interface (' + Inter + ")";
    LogicElement.innerHTML = "&thinsp; Logic (" + Logic + ")";
    MigrationElement.innerHTML = "&thinsp; Migration (" + Mig + ")";
    ResourceElement.innerHTML = '&thinsp; Resource (' + Res + ")";
    var sum = sumFunctionalDefects(Check, Conc, ErrorH, Inter, Logic, Mig, Res);
    FuncDefElement.innerHTML = ' Functional Defects (' + sum + ")";
}

// Add total amount of warnings to each sub category within the maintainability defects in the right menu
function appendInfoToMaintainabilityDefects(Prac, Struc, DocConv, Metric, NamConv, OOD, Simp, Red, StyleConv) {
    var BestPracticesElement = document.getElementById("BestPracticesLabel");
    var CodeStructureElement = document.getElementById("CodeStructureLabel");
    var DocConventionsElement = document.getElementById("DocConventionsLabel");
    var MetricElement = document.getElementById("MetricLabel");
    var NamingConventionsElement = document.getElementById("NamingConventionsLabel");
    var OODesignElement = document.getElementById("OODesignLabel");
    var SimplificationsElement = document.getElementById("SimplificationsLabel");
    var RedundanciesElement = document.getElementById("RedundanciesLabel");
    var StyleConventionsElement = document.getElementById("StyleConventionsLabel");
    var MainDefElement = document.getElementById("MainDefLabel");

    BestPracticesElement.innerHTML = '&thinsp; Best Practices (' + Prac + ")";
    CodeStructureElement.innerHTML = "&thinsp; Code Structure (" + Struc + ")";
    DocConventionsElement.innerHTML = "&thinsp; Doc. Conventions (" + DocConv + ")";
    MetricElement.innerHTML = '&thinsp; Metric (' + Metric + ")";
    NamingConventionsElement.innerHTML = "&thinsp; Naming Conventions (" + NamConv + ")";
    OODesignElement.innerHTML = "&thinsp; OO Design (" + OOD + ")";
    SimplificationsElement.innerHTML = '&thinsp; Simplifications (' + Simp + ")";
    RedundanciesElement.innerHTML = "&thinsp; Redundancies (" + Red + ")";
    StyleConventionsElement.innerHTML = '&thinsp; Style Conventions (' + StyleConv + ")";
    var sum = sumMaintainabilityDefects(Prac, Struc, DocConv, Metric, NamConv, OOD, Simp, Red, StyleConv);
    MainDefElement.innerHTML = ' Maintainability Defects (' + sum + ")";
}

// Add total amount of warnings to each sub category within the other category in the right menu
function appendInfoToOtherDefects(Other, RegExpr, Tools) {
    var OtherElement = document.getElementById("OtherLabel");
    var RegularExpressionsElement = document.getElementById("RegularExpressionsLabel");
    var ToolSpecificElement = document.getElementById("ToolSpecificLabel");
    var OthElement = document.getElementById("OthLabel");

    OtherElement.innerHTML = '&thinsp; Other (' + Other + ")";
    RegularExpressionsElement.innerHTML = "&thinsp; Regular Expressions (" + RegExpr + ")";
    ToolSpecificElement.innerHTML = "&thinsp; Tool Specific(" + Tools + ")";
    var sum = sumOtherDefects(Other, RegExpr, Tools);
    OthElement.innerHTML = " Other (" + sum + ")";
}

//Setup tree map and shows it
function runTreeMap() {
    var title = document.getElementById("main-title");
    title.innerHTML = "'Future name of tool'";

    var packages = filterTypeRuleName(acceptedTypes, acceptedCategories);
    var finalJson =  createJsonTreeMap(packages);
    treeMapBuilder.createTreeMap({
        title: ""
    }, {
        fileName: projectName,
        values: finalJson
    });
}

//Setup graph and shows it
function runGraph() {
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