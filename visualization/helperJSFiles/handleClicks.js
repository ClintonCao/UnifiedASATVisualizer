var CheckStyleElement = document.getElementById("checkStyleLabel");
var PMDElement = document.getElementById("PMDLabel");
var FindBugsElement = document.getElementById("FindBugsLabel");
var MaintainabilityDefectsElement = document.getElementById("maintainabilityDefectsLabel");
var FunctionDefectsElement = document.getElementById("functionalDefectsLabel");
var OtherElement = document.getElementById("otherLabel");

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
	CheckStyleElement.style.color = "#12B212";
	PMDElement.style.color = "#ED4337"
	FindBugsElement.style.color = "#75B4EB";
	MaintainabilityDefectsElement.style.color = "#FFFFFF";
	FunctionDefectsElement.style.color = "#FFFFFF";
	OtherElement.style.color = "#FFFFFF";
}

/*
 * Colors based on Categories so the labels are given colours
 */
function setCategoriesColoured() {
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
	if ( radioButton.value == "normal" ){
		setAllLabelsWhite();
		backgroundObject.setColorMethod(0);
	} else if ( radioButton.value == "asat" ){
		setASATColoured();
		backgroundObject.setColorMethod(1);
	} else if ( radioButton.value == "category" ){
		setCategoriesColoured();
		backgroundObject.setColorMethod(2);
	}
}

/*
 * Handles click for relative
 */
function handleClickRelativeColours(radioButton) {
	var normalButtonElement = document.getElementById("normalButton");
	var asatButtonElement = document.getElementById("asatButton");
	var categoryButtonElement = document.getElementById("categoryButton");

	if($(radioButton).prop('checked')) {
		backgroundObject.setColorsRelative();
	} else {
		backgroundObject.setColorsAbsolute();
	}

	if ( document.getElementById("normalButton").checked ){
		setAllLabelsWhite();
		backgroundObject.setColorMethod(0);
	} else if ( document.getElementById("asatButton").checked ){
		setASATColoured();
		backgroundObject.setColorMethod(1);
	} else if ( document.getElementById("categoryButton").checked ){
		setCategoriesColoured();
		backgroundObject.setColorMethod(2);
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