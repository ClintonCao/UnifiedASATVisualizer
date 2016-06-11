/*
 * This class holds all colours that we defined and use in multiple places
 */
var colours = (function() {

	var white = "#FFFFFF";
	var black = "#000000";

	var darkGreen = "#386938";
	var darkRed = "#88120a";
	var darkBlue = "#043e70";

	var normalGreen = "#12B212";
	var normalRed = "#ED4337";
	var normalBlue = "#75B4EB";

	var lightGreen = "";
	var lightRed = "";
	var lightBlue = "";

	return {
        white: function() { return white; },
        black: function() { return black; },
        darkGreen: function() { return darkGreen; }, 
		darkRed: function() { return darkRed; }, 
        darkBlue: function() { return darkBlue; },
 		normalGreen: function() { return normalGreen; }, 
		normalRed: function() { return normalRed; }, 
		normalBlue: function() { return normalBlue; }
    }

}());