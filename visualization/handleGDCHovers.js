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
         $(category).mouseout(function() {$(hover).hide();});});
}