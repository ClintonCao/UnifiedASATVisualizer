/**
 * Will calculate the number of current warnings for each ASAT and then sets it
 */
function updateASATWarningsCount(d) {
	var mainLabels = ["checkStyleLabel", "PMDLabel", "FindBugsLabel"];
	var mainTitles = ["CheckStyle", "PMD", "FindBugs"];
	var cat = categoryMapper.getDefects(i);
	for ( var index = 0; index < mainTitles.length; index++) {
		var warning = sumNodeForASAT(d, getTotalASATWarning(mainTitles[index], d.fileName));
		document.getElementById(mainLabels[index]).innerHTML = '&thinsp;&thinsp;'+ mainTitles[index] + '&thinsp;(' + warning + ")";
	}
}

/**
 * Will calculate the number of current warnings for the function defects category and then sets it
 */
function updateDefectsCount(d) {
	var mainLabels = ["FuncDefLabel", "MainDefLabel", "OthLabel"];
	var mainLabelsTitles = ["Functional Defects", "Maintainability Defects", "Other"];
	for ( var i =0; i< 3; i++) {
		var total = 0;
		var catLabel = categoryMapper.getDefectLabels(i);
		var cat = categoryMapper.getDefects(i);
		for ( var index =0; index < cat.length; index++) {
			var warning = sumNodeForASAT(d, getTotalCategoryWarning(cat[index], d.fileName));
			document.getElementById(catLabel[index]).innerHTML = '&thinsp; '+ cat[index] + ' (' + warning + ")";
			if( acceptedCategories.indexOf(cat[index]) >= 0) {
				total += warning;
			}
		}
		document.getElementById(mainLabels[i]).innerHTML = ' ' + mainLabelsTitles[i] + ' (' +  total + ") &thinsp;";
	}
}

/**
 * Sums for each node how many warnings they have.
 */
function sumNodeForASAT(d, root) {
    var nodeAndSummation = [];
    var sum = 0;
    if (d.fileName == projectName) {
        return sumHighestLevelNode(root);
    } else {
        return sumOtherLevelsNode(d, root);
    }
    return -1;
}

/**
 * Sums for each package how many warnings it has
 */
function sumHighestLevelNode(root) {
    sum = 0;
    for (var i = 0; i < root.length; i++) {
        for (var j = 0; j < root[i].length; j++) {
            sum += root[i][j].amountOfWarnings;
         }
     }
     return sum;
}

/**
 * Sums for class or source code level the amount of warnings
 */
function sumOtherLevelsNode(d, root) {
    sum = 0;
    for (var i = 0; i < root.length; i++) {
        if(treeMapBuilder.getSourceCodeLevel()) {
            if (root[i].packageName == d.parent.fileName) {
                for (var j = 0; j < root[i].length; j++) {
                    if(treeMapBuilder.getSourceCodeLevel() && root[i][j].fileName == treeMapBuilder.getCurrentClassName()) {
                        return root[i][j].amountOfWarnings;
                    }
                    sum += root[i][j].amountOfWarnings;
                }
                return sum;
            }
        } else {
            if (root[i].packageName == d.fileName) {
                for (var j = 0; j < root[i].length; j++) {
                    sum += root[i][j].amountOfWarnings;
                }
                return sum;
            }
        }
    }
}