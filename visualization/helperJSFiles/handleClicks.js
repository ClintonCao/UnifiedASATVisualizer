
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
 * Sets all ASAT and Categorie labels to white
 */
function setAllLabelsWhite() {
	var CheckStyleElement = document.getElementById("checkStyleLabel");
	var PMDElement = document.getElementById("PMDLabel");
	var FindBugsElement = document.getElementById("FindBugsLabel");
	var MaintainabilityDefectsElement = document.getElementById("maintainabilityDefectsLabel");
	var FunctionDefectsElement = document.getElementById("functionalDefectsLabel");
	var OtherElement = document.getElementById("otherLabel");
	CheckStyleElement.style.color = "#FFFFFF";
	PMDElement.style.color = "#FFFFFF";
	FindBugsElement.style.color = "#FFFFFF";
	MaintainabilityDefectsElement.style.color = "#FFFFFF";
	FunctionDefectsElement.style.color = "#FFFFFF";
	OtherElement.style.color = "#FFFFFF";
}

/*
 * Colors based on ASAT so the labels are given colours
 */
function setASATColoured() {
	var CheckStyleElement = document.getElementById("checkStyleLabel");
	var PMDElement = document.getElementById("PMDLabel");
	var FindBugsElement = document.getElementById("FindBugsLabel");
	var MaintainabilityDefectsElement = document.getElementById("maintainabilityDefectsLabel");
	var FunctionDefectsElement = document.getElementById("functionalDefectsLabel");
	var OtherElement = document.getElementById("otherLabel");
	CheckStyleElement.style.color = "#12B212";
	PMDElement.style.color = "#75B4EB";
	FindBugsElement.style.color = "#ED4337";
	MaintainabilityDefectsElement.style.color = "#FFFFFF";
	FunctionDefectsElement.style.color = "#FFFFFF";
	OtherElement.style.color = "#FFFFFF";
}

/*
 * Colors based on Categories so the labels are given colours
 */
function setCategoriesColoured() {
	var CheckStyleElement = document.getElementById("checkStyleLabel");
	var PMDElement = document.getElementById("PMDLabel");
	var FindBugsElement = document.getElementById("FindBugsLabel");
	var MaintainabilityDefectsElement = document.getElementById("maintainabilityDefectsLabel");
	var FunctionDefectsElement = document.getElementById("functionalDefectsLabel");
	var OtherElement = document.getElementById("otherLabel");
	CheckStyleElement.style.color = "#FFFFFF";
	PMDElement.style.color = "#FFFFFF";
	FindBugsElement.style.color = "#FFFFFF";
	MaintainabilityDefectsElement.style.color = "#ED4337";
	FunctionDefectsElement.style.color = "#12B212";
	OtherElement.style.color = "#75B4EB";
}
/*
 * Handles click on checkboxes for using different colorscales
 */
function handleClickColorScale(radioButton) {
	if ( radioButton.value == "normalAbsolute" ){
		setAllLabelsWhite();
		backgroundObject.setColorMethod(0);
	}else if ( radioButton.value == "normalRelative" ){
		setAllLabelsWhite();
		backgroundObject.setColorMethod(1);
	}else if ( radioButton.value == "asatAbsolute" ){
		setASATColoured();
		backgroundObject.setColorMethod(2);
	}else if ( radioButton.value == "asatRelative" ){
		setASATColoured();
		backgroundObject.setColorMethod(3);
	}else if ( radioButton.value == "categoryAbsolute" ){
		setCategoriesColoured();
		backgroundObject.setColorMethod(4);
	}else if ( radioButton.value == "categoryRelative" ){
		setCategoriesColoured();
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