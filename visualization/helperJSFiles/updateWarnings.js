
/*
 * Will set the number of current warnings for each ASAT
 */
function updateASATWarningsCount(d) {
    var CheckStyleWarnings = sumNodeForASAT(d, getTotalASATWarning("CheckStyle", currentClassName));
    var PMDWarnings = sumNodeForASAT(d, getTotalASATWarning("PMD", currentClassName));
    var FindBugsWarnings = sumNodeForASAT(d, getTotalASATWarning("FindBugs", currentClassName));
    appendInfoToSAT(CheckStyleWarnings, PMDWarnings, FindBugsWarnings);
}

/*
 * Will set the number of current warnings for the function defects category
 */
function updateFunctionalDefectsCount(d) {
    var CheckWarnings = sumNodeForASAT(d, getTotalCategoryWarning("Check", currentClassName));
    var ConcWarnings = sumNodeForASAT(d, getTotalCategoryWarning("Concurrency", currentClassName));
    var ErrorWarnings = sumNodeForASAT(d, getTotalCategoryWarning("ErrorHandling", currentClassName));
    var InterfaceWarnings = sumNodeForASAT(d, getTotalCategoryWarning("Interface", currentClassName));
    var LogicWarnings = sumNodeForASAT(d, getTotalCategoryWarning("Logic", currentClassName));
    var MigrationWarnings = sumNodeForASAT(d, getTotalCategoryWarning("Migration", currentClassName));
    var ResourceWarnings = sumNodeForASAT(d, getTotalCategoryWarning("Resource", currentClassName));
    appendInfoToFunctionalDefects(CheckWarnings, ConcWarnings, ErrorWarnings, InterfaceWarnings, LogicWarnings, MigrationWarnings, ResourceWarnings);
}

/*
 * Will set the number of current warnings for the maintainability defects category
 */
function updateMaintainabilityDefectsCount(d) {
    var BestPracticeWarnings = sumNodeForASAT(d, getTotalCategoryWarning("Best Practices", currentClassName));
    var CodeStructureWarnings = sumNodeForASAT(d, getTotalCategoryWarning("Code Structure", currentClassName));
    var DocConventionsWarnings = sumNodeForASAT(d, getTotalCategoryWarning("Documentation Conventions", currentClassName));
    var MetricWarnings = sumNodeForASAT(d, getTotalCategoryWarning("Metric", currentClassName));
    var NamingConventionsWarnings = sumNodeForASAT(d, getTotalCategoryWarning("Naming Conventions", currentClassName));
    var OODesignWarnings = sumNodeForASAT(d, getTotalCategoryWarning("Object Oriented Design", currentClassName));
    var SimplificationsWarnings = sumNodeForASAT(d, getTotalCategoryWarning("Refactorings - Simplifications", currentClassName));
    var ReduncanciesWarnings = sumNodeForASAT(d, getTotalCategoryWarning("Refactorings - Redundancies", currentClassName));
    var StyleConventionsWarnings = sumNodeForASAT(d, getTotalCategoryWarning("Style Conventions", currentClassName));
    appendInfoToMaintainabilityDefects(BestPracticeWarnings, CodeStructureWarnings, DocConventionsWarnings, MetricWarnings, NamingConventionsWarnings, OODesignWarnings, SimplificationsWarnings, ReduncanciesWarnings, StyleConventionsWarnings);
}

/*
 * Will set the number of current warnings for the other defects category
 */
function updateOtherDefectsCount(d) {
    var OtherWarnings = sumNodeForASAT(d, getTotalCategoryWarning("Other", currentClassName));
    var RegularExpressionsWarnings =sumNodeForASAT(d, getTotalCategoryWarning("Regular Expressions", currentClassName));
    var ToolSpecificWarnings = sumNodeForASAT(d, getTotalCategoryWarning("Tool Specific", currentClassName));
    appendInfoToOtherDefects(OtherWarnings, RegularExpressionsWarnings, ToolSpecificWarnings);
}