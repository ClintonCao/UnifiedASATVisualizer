var acceptedTypes =["CheckStyle"];
var acceptedRuleNames =["PackageName","JavadocMethod"];
var classes = filterTypeRuleName(acceptedTypes, acceptedRuleNames);
console.log(createJson(classes));
var inputData = createJson(classes);	



// call method main with title and input data	
main({
    title: "Amount of warnings"
}, {
    fileName: "Test Project",
    values: getInputData()
});


function getInputData(){
	var checkStyleData = inputData;
	return inputData;	
}


	
function handleClick(cb) {
  if(cb.name == "sat"){
  	console.log(cb.value);
	var value = cb.value;
	var booleanChecked = cb.checked;  
	if(booleanChecked){
		acceptedTypes = [cb.value];
	}
	inputData = createJson(classes);
	main({
    title: "Amount of warnings"
	}, {
    fileName: "Test Project",
    values: inputData
	});	
  }
}