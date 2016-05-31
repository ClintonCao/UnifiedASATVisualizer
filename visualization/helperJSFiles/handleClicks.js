
/*
 * Handles click on checkboxes for showing results of different tools
 */
function handleClickTreeMapTypeSat(value, checked) {
	if (checked) {
		var index = acceptedTypes.indexOf(value);
        if (index < 0) {
			acceptedTypes.push(value)
        }
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
	if ( radioButton.value == "normalAbsolute" ){
		backgroundObject.setColorMethod(0);
	}else if ( radioButton.value == "normalRelative" ){
		backgroundObject.setColorMethod(1);
	}else if ( radioButton.value == "asatAbsolute" ){
		backgroundObject.setColorMethod(2);
	}else if ( radioButton.value == "asatRelative" ){
		backgroundObject.setColorMethod(3);
	}else if ( radioButton.value == "categoryAbsolute" ){
		backgroundObject.setColorMethod(4);
	}else if ( radioButton.value == "categoryRelative" ){
		backgroundObject.setColorMethod(5);
	}
}


/*
* handles the clicks on Sat categories
*/
function handleClickCategorySat(value, checked) {
		
 	if (checked) {
        var index = acceptedCategories.indexOf(value);
       if (index < 0) {
			acceptedCategories.push(value)
       }
    } else {
        var index = acceptedCategories.indexOf(value);
        if (index > -1) {
            acceptedCategories.splice(index, 1);
        }
    }
    //$('.toggle').attr('data-toggle');
    //setTimeout(function(){ }, 100);
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