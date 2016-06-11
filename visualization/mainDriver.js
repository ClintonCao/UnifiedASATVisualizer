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
	$(".DefectCategory").prop('checked', true);
	
	$(".FunctionalDefects").click();
	$(".MaintainabilityDefects").click();
	$(".StyleConventions").click();
	$(".sats").click();
}

/*
 * In the first run all ASATs and categories are included
 */
function addAllAcceptedTypesAndCategories(){
	var arrayCat = [".MaintainabilityDefects",".FunctionalDefects",".StyleConventions"];
	var arrayTypes = [".sats"];
	toggleAcceptedCategories(arrayCat, true);
	toggleAcceptedTypes(arrayTypes, true);
}
function toggleAcceptedCategories(array, checked){
	for (var index =0; index < array.length; index++){
		for ( var i = 0; i < $(array[index]).size(); i ++){
			handleClickCategorySat($(array[index])[i].value, checked);
		}
	}
}
function toggleAcceptedTypes(array, checked){
	for (var index =0; index < array.length; index++){
		for ( var i = 0; i < $(array[index]).size(); i ++){
			handleClickTreeMapTypeSat($(array[index])[i].value, checked);
		}
	}
}

/*
 * Delete the treemap chart from the page
 */
function removeChart() {
    var chartNode = document.getElementById("chart");
    while (chartNode.firstChild) {
        chartNode.removeChild(chartNode.firstChild);
    }
}

/*
 * Sets up the treemap and shows it
 */
function runTreeMap() {
    var title = document.getElementById("main-title");
    title.innerHTML = "𝕌𝔸𝕍";

    var packages = filterTypeRuleName(acceptedTypes, acceptedCategories);
    var finalJson =  createJsonTreeMap(packages);
    treeMapBuilder.createTreeMap({
        title: ""
    }, {
        fileName: projectName,
        values: finalJson
    });
}