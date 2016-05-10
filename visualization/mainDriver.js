var acceptedTypes =[];
var acceptedRuleNames =["PackageName","JavadocMethod"];

// Run first time
runTreeMap();
var graphJSON;

loadJSON(function(response) {
  // Parse JSON string into object
    var actual_JSON = JSON.parse(response);
    graphJSON = actual_JSON
});


	
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
				runGraph(graphJSON);
			} else {
				console.log(window['packageVariable'])
				runGraph(window[sessionStorage.getItem('packageVariable')]);
			}
		}
	}
}

function loadJSON(callback) {   

    var xobj = new XMLHttpRequest();
    xobj.overrideMimeType("application/json");
    xobj.open('GET', 'test.json', true); // Replace 'my_data' with the path to your file
    xobj.onreadystatechange = function () {
									          if (xobj.readyState == 4 && xobj.status == "200") {
									            // Required use of an anonymous callback as .open will NOT return a value but simply returns undefined in asynchronous mode
									            callback(xobj.responseText);
									          }
    									  };
    xobj.send(null);  
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

function runTreeMap(){
	var element = document.getElementById("main-title");
    element.innerHTML = "Amount of warnings";

	var packages = filterTypeRuleNameTreeMap(acceptedTypes, acceptedRuleNames);
	var inputData = createJson(packages);	
	main({
    title: ""
	}, {
    fileName: "Test Project",
    values: inputData
	});	
}