
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
* handles the clicks on Sat categories
*/
function handleClickCategorySat(checkbox) {	
 	if (checkbox.checked) {
		acceptedCategories.push(checkbox.value)
    } else {
        var index = acceptedCategories.indexOf(checkbox.value);
        if (index > -1) {
            acceptedCategories.splice(index, 1);
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