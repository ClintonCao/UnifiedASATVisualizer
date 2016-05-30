/*
 * Link all categories with the right descriptions
 */
function defineHovers() {
    var allGDCHovers = ["#GDCFunctionalDefects", "#GDCCheck", "#GDCConcurrency", "#GDCErrorHandling", "#GDCInterface",
         "#GDCLogic", "#GDCMigration", "#GDCResource", "#GDCMaintainabilityDefects", "#GDCBestPractices", "#GDCCodeStructure", 
            "#GDCDocConventions", "#GDCMetric", "#GDCNamingConventions", "#GDCOODesign", "#GDCSimplifications", "#GDCRedundancies",
                "#GDCStyleConventions", "#GDCOther", "#GDCRegularExpressions", "#GDCToolSpecific"];

    for(var i = 0; i < allGDCHovers.length; i++) {
        showHover(allGDCHovers[i]);
    }
}

/*
 * Enables the hover effect for a given category label
 */
function showHover(category) {
    var hover = category + "-hover";
    $(document).ready(function(){$(category).mousemove(function(event) { 
        var left = $(this).offset().left;
        if(category == "#GDCFunctionalDefects" || category == "#GDCMaintainabilityDefects") {
            var top = $(this).offset().top + 45;
        } else {
            var top = $(this).offset().top + 25;
        }
        $(hover).css({top: top,left: left}).show();});
        $(category).mouseout(function() {$(hover).hide();});
    });
}