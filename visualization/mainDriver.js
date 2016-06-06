/*
 * Global variabeles that are used across multiple classes
 */
var acceptedTypes = [];
var acceptedCategories = [];

defineHovers();
runTreeMap();
addAllAcceptedTypesAndCategories();
setAllCheckboxesOnDefault();

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

    checkStyleElement.innerHTML = '&thinsp; CheckStyle(' + CS + ")";
    PMDElement.innerHTML = "&thinsp; PMD(" + PMD + ")";
    findBugsElement.innerHTML = "&thinsp; FindBugs(" + FB + ")";
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
    FuncDefElement.innerHTML = ' Functional Defects (' + (Check + Conc + ErrorH + Inter + Logic + Mig + Res) + ")";
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
    MainDefElement.innerHTML = ' Maintainability Defects (' + (Prac + Struc + DocConv + Metric + NamConv + OOD + Simp + Red + StyleConv) + ")";
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
    OthElement.innerHTML = " Other (" + (Other + RegExpr + Tools) + ")";
}

//Setup tree map and shows it
function runTreeMap() {
    var title = document.getElementById("main-title");
    title.innerHTML = "Treemap view of project <a class='form' target='_blank' href='https://docs.google.com/forms/d/1HDEeIx4Dzvxd2Aa_hiNsSMrKt0K6smW7YciHGzabKlw/viewform?c=0&w=1&usp=mail_form_link'>- Feedback-form</a>";
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