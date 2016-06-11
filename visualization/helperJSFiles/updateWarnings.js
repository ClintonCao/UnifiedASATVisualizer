/*
 * Will calculate the number of current warnings for each ASAT and then sets it
 */
function updateASATWarningsCount(d) {
    var CheckStyleWarnings = sumNodeForASAT(d, getTotalASATWarning("CheckStyle", d.fileName));
    var PMDWarnings = sumNodeForASAT(d, getTotalASATWarning("PMD", d.fileName));
    var FindBugsWarnings = sumNodeForASAT(d, getTotalASATWarning("FindBugs", d.fileName));
    appendInfoToSAT(CheckStyleWarnings, PMDWarnings, FindBugsWarnings);
}

/*
 * Will calculate the number of current warnings for the function defects category and then sets it
 */
function updateFunctionalDefectsCount(d) {
    var CheckWarnings = sumNodeForASAT(d, getTotalCategoryWarning("Check", d.fileName));
    var ConcWarnings = sumNodeForASAT(d, getTotalCategoryWarning("Concurrency", d.fileName));
    var ErrorWarnings = sumNodeForASAT(d, getTotalCategoryWarning("ErrorHandling", d.fileName));
    var InterfaceWarnings = sumNodeForASAT(d, getTotalCategoryWarning("Interface", d.fileName));
    var LogicWarnings = sumNodeForASAT(d, getTotalCategoryWarning("Logic", d.fileName));
    var MigrationWarnings = sumNodeForASAT(d, getTotalCategoryWarning("Migration", d.fileName));
    var ResourceWarnings = sumNodeForASAT(d, getTotalCategoryWarning("Resource", d.fileName));
    appendInfoToFunctionalDefects(CheckWarnings, ConcWarnings, ErrorWarnings, InterfaceWarnings, LogicWarnings, MigrationWarnings, ResourceWarnings);
}

/*
 * Will calculate the number of current warnings for the maintainability defects category and then sets it
 */
function updateMaintainabilityDefectsCount(d) {
    var BestPracticeWarnings = sumNodeForASAT(d, getTotalCategoryWarning("Best Practices", d.fileName));
    var CodeStructureWarnings = sumNodeForASAT(d, getTotalCategoryWarning("Code Structure", d.fileName));
    var DocConventionsWarnings = sumNodeForASAT(d, getTotalCategoryWarning("Documentation Conventions", d.fileName));
    var MetricWarnings = sumNodeForASAT(d, getTotalCategoryWarning("Metric", d.fileName));
    var NamingConventionsWarnings = sumNodeForASAT(d, getTotalCategoryWarning("Naming Conventions", d.fileName));
    var OODesignWarnings = sumNodeForASAT(d, getTotalCategoryWarning("Object Oriented Design", d.fileName));
    var SimplificationsWarnings = sumNodeForASAT(d, getTotalCategoryWarning("Refactorings - Simplifications", d.fileName));
    var ReduncanciesWarnings = sumNodeForASAT(d, getTotalCategoryWarning("Refactorings - Redundancies", d.fileName));
    var StyleConventionsWarnings = sumNodeForASAT(d, getTotalCategoryWarning("Style Conventions", d.fileName));
    appendInfoToMaintainabilityDefects(BestPracticeWarnings, CodeStructureWarnings, DocConventionsWarnings, MetricWarnings, NamingConventionsWarnings, OODesignWarnings, SimplificationsWarnings, ReduncanciesWarnings, StyleConventionsWarnings);
}

/*
 * Will calculate the number of current warnings for the other defects category and then sets it
 */
function updateOtherDefectsCount(d) {
    var OtherWarnings = sumNodeForASAT(d, getTotalCategoryWarning("Other", d.fileName));
    var RegularExpressionsWarnings =sumNodeForASAT(d, getTotalCategoryWarning("Regular Expressions", d.fileName));
    var ToolSpecificWarnings = sumNodeForASAT(d, getTotalCategoryWarning("Tool Specific", d.fileName));
    appendInfoToOtherDefects(OtherWarnings, RegularExpressionsWarnings, ToolSpecificWarnings);
}

/*
 * Sums for each node how many warnings they have.
 */
function sumNodeForASAT(d, root) {
    var nodeAndSummation = [];
    var sum = 0;
    if (d.fileName == projectName) {
        for (var i = 0; i < root.length; i++) {
            for (var j = 0; j < root[i].length; j++) {
                sum += root[i][j].amountOfWarnings;
             }
         }
         return sum;
    } else {
        for (var i = 0; i < root.length; i++) {
            if(sourceCodeLevel) {
                if (root[i].packageName == d.parent.fileName) {
                    for (var j = 0; j < root[i].length; j++) {
                        if(sourceCodeLevel && root[i][j].fileName == currentClassName) {
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
    return -1;
}

/*
 * Add total amount of active warnings for each ASAT in the left menu
 */
function appendInfoToSAT(CS, PMD, FB) {
    document.getElementById("checkStyleLabel").innerHTML = '&thinsp; CheckStyle&thinsp;(' + CS + ")";
    document.getElementById("PMDLabel").innerHTML = "&thinsp; PMD&thinsp;(" + PMD + ")";
    document.getElementById("FindBugsLabel").innerHTML = "&thinsp; FindBugs&thinsp;(" + FB + ")";
}

/*
 * Add total amount of active warnings to each sub category within the functional defects in the right menu
 */
function appendInfoToFunctionalDefects(Check, Conc, ErrorH, Inter, Logic, Mig, Res) {
    document.getElementById("CheckLabel").innerHTML = '&thinsp; Check (' + Check + ")";
    document.getElementById("ConcurrencyLabel").innerHTML = "&thinsp; Concurrency (" + Conc + ")";
    document.getElementById("ErrorHandlingLabel").innerHTML = "&thinsp; Error Handling (" + ErrorH + ")";
    document.getElementById("InterfaceLabel").innerHTML = '&thinsp; Interface (' + Inter + ")";
    document.getElementById("LogicLabel").innerHTML = "&thinsp; Logic (" + Logic + ")";
    document.getElementById("MigrationLabel").innerHTML = "&thinsp; Migration (" + Mig + ")";
    document.getElementById("ResourceLabel").innerHTML = '&thinsp; Resource (' + Res + ")";
    document.getElementById("FuncDefLabel").innerHTML = ' Functional Defects (' + 
        sumFunctionalDefects(Check, Conc, ErrorH, Inter, Logic, Mig, Res) + ") &thinsp;";
}

/*
 * Add total amount of active warnings to each sub category within the maintainability defects in the right menu
 */
function appendInfoToMaintainabilityDefects(Prac, Struc, DocConv, Metric, NamConv, OOD, Simp, Red, StyleConv) {
    document.getElementById("BestPracticesLabel").innerHTML = '&thinsp; Best Practices (' + Prac + ")";
    document.getElementById("CodeStructureLabel").innerHTML = "&thinsp; Code Structure (" + Struc + ")";
    document.getElementById("DocConventionsLabel").innerHTML = "&thinsp; Doc. Conventions (" + DocConv + ")";
    document.getElementById("MetricLabel").innerHTML = '&thinsp; Metric (' + Metric + ")";
    document.getElementById("NamingConventionsLabel").innerHTML = "&thinsp; Naming Conventions (" + NamConv + ")";
    document.getElementById("OODesignLabel").innerHTML = "&thinsp; OO Design (" + OOD + ")";
    document.getElementById("SimplificationsLabel").innerHTML = '&thinsp; Simplifications (' + Simp + ")";
    document.getElementById("RedundanciesLabel").innerHTML = "&thinsp; Redundancies (" + Red + ")";
    document.getElementById("StyleConventionsLabel").innerHTML = '&thinsp; Style Conventions (' + StyleConv + ")";
    document.getElementById("MainDefLabel").innerHTML = ' Maintainability Defects (' + 
        sumMaintainabilityDefects(Prac, Struc, DocConv, Metric, NamConv, OOD, Simp, Red, StyleConv) + ") &thinsp; ";
}

/*
 * Add total amount of active warnings to each sub category within the other category in the right menu
 */
function appendInfoToOtherDefects(Other, RegExpr, Tools) {
    document.getElementById("OtherLabel").innerHTML = '&thinsp; Other (' + Other + ")";
    document.getElementById("RegularExpressionsLabel").innerHTML = "&thinsp; Regular Expressions (" + RegExpr + ")";
    document.getElementById("ToolSpecificLabel").innerHTML = "&thinsp; Tool Specific(" + Tools + ")";
    document.getElementById("OthLabel").innerHTML = " Other (" + sumOtherDefects(Other, RegExpr, Tools) + ") &thinsp;";
}

/*
 * Sums how many active functional defects there are
 */
function sumFunctionalDefects(Check, Conc, ErrorH, Inter, Logic, Mig, Res) {
    var summation = 0;
    for(var i = 0; i < acceptedCategories.length; i++) {
        switch(acceptedCategories[i]) {
            case 'Check':
                summation += Check;
                break;
            case 'Concurrency':
                summation += Conc;
                break;
            case 'ErrorHandling':
                summation += ErrorH;
                break;
            case 'Interface':
                summation += Inter;
                break;
            case 'Logic':
                summation += Logic;
                break;
            case 'Migration':
                summation += Mig;
                break;
            case 'Resource':
                summation += Res;
                break;
        }
    }
    return summation;
}

/*
 * Sums how many active maintainability defects there are
 */
function sumMaintainabilityDefects(Prac, Struc, DocConv, Metric, NamConv, OOD, Simp, Red, StyleConv) {
    var summation = 0;
    for(var i = 0; i < acceptedCategories.length; i++) {
        switch(acceptedCategories[i]) {
            case 'Best Practices':
                summation += Prac;
                break;
            case 'Code Structure':
                summation += Struc;
                break;
            case 'Documentation Conventions':
                summation += DocConv;
                break;
            case 'Metric':
                summation += Metric;
                break;
            case 'Naming Conventions':
                summation += NamConv;
                break;
            case 'Object Oriented Design':
                summation += OOD;
                break;
            case 'Refactorings - Simplifications':
                summation += Simp;
                break;
            case 'Refactorings - Redundancies':
                summation += Red;
                break;
            case 'Style Conventions':
                summation += StyleConv;
                break;
        }
    }
    return summation;
}

/*
 * Sums how many active other defects there are
 */
function sumOtherDefects(Other, RegExpr, Tools) {
    var summation = 0;
    for(var i = 0; i < acceptedCategories.length; i++) {
        switch(acceptedCategories[i]) {
            case 'Other':
                summation += Other;
                break;
            case 'Regular Expressions':
                summation += RegExpr;
                break;
            case 'Tool Specific':
                summation += Tools;
                break;
        }
    }
    return summation;
}