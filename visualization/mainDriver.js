/*
 * Global variabeles that are used across multiple classes
 */
var acceptedTypes = [];
var acceptedCategories = [];
var version = detectIE();
if(version == false) {
	defineHovers();
	runTreeMap();
	addAllAcceptedTypesAndCategories();
	setAllCheckboxesOnDefault();
} else {
	document.getElementById("main-title").innerHTML = "We are sorry, IE and Edge are not supported. <br> Please use Firefox, Chrome or Safari instead.";
	$("#wrapper").hide();
}


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

/**
 * Detects IE
 * returns version of IE or false, if browser is not Internet Explorer
 */
function detectIE() {
  var ua = window.navigator.userAgent;

  // Test values; Uncomment to check result …

  // IE 10
  // ua = 'Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; Trident/6.0)';
  
  // IE 11
  // ua = 'Mozilla/5.0 (Windows NT 6.3; Trident/7.0; rv:11.0) like Gecko';
  
  // Edge 12 (Spartan)
  // ua = 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36 Edge/12.0';
  
  // Edge 13
  // ua = 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2486.0 Safari/537.36 Edge/13.10586';

  var msie = ua.indexOf('MSIE ');
  if (msie > 0) {
    // IE 10 or older => return version number
    return parseInt(ua.substring(msie + 5, ua.indexOf('.', msie)), 10);
  }

  var trident = ua.indexOf('Trident/');
  if (trident > 0) {
    // IE 11 => return version number
    var rv = ua.indexOf('rv:');
    return parseInt(ua.substring(rv + 3, ua.indexOf('.', rv)), 10);
  }

  var edge = ua.indexOf('Edge/');
  if (edge > 0) {
    // Edge (IE 12+) => return version number
    return parseInt(ua.substring(edge + 5, ua.indexOf('.', edge)), 10);
  }

  // Other browsers
  return false;
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
    document.getElementById("main-title").innerHTML = "𝕌𝔸𝕍";

    var packages = filterTypeRuleName(acceptedTypes, acceptedCategories);
    var finalJson =  createJsonTreeMap(packages);
    treeMapBuilder.createTreeMap({
        title: ""
    }, {
        fileName: projectName,
        values: finalJson
    });
}