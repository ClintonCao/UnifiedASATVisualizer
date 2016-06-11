/*
 * This class holds all colours that we defined and use in multiple places
 */
var colours = (function() {

	var white = "#FFFFFF";
	var black = "#000000";
	var grey = "#A2A2A2"

	var darkGreen = "#386938";
	var darkRed = "#88120a";
	var darkBlue = "#043e70";
	var darkOrange = "#B13E0F";

	var normalGreen = "#12B212";
	var normalRed = "#ED4337";
	var normalBlue = "#75B4EB";
	var normalOrange = "#FF7D40";

	var lightGreen = "#679a64";
	var lightRed = "#9c7777";
	var lightBlue = "#6f87a2";
	var lightOrange = "#F59C6F";

	return {
        white: function() { return white; },
        black: function() { return black; },
        grey: function() { return grey; },
        darkGreen: function() { return darkGreen; }, 
		darkRed: function() { return darkRed; }, 
        darkBlue: function() { return darkBlue; },
        darkOrange: function() { return darkOrange; },
 		normalGreen: function() { return normalGreen; }, 
		normalRed: function() { return normalRed; }, 
		normalBlue: function() { return normalBlue; },
		normalOrange: function() { return normalOrange; },
		lightGreen: function() { return lightGreen; }, 
		lightRed: function() { return lightRed; }, 
		lightBlue: function() { return lightBlue; },
		lightOrange: function() { return lightOrange; }
    }
    
}());