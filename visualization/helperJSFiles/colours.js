/**
 * This class holds all colours that we defined and use in multiple places
 */
var colours = (function() {

	// all basic colours
	var white = "#FFFFFF";
	var black = "#000000";
	var grey = "#A2A2A2"

	// all dark colours
	var darkGreen = "#386938";
	var darkRed = "#88120a";
	var darkBlue = "#013b76";
	var darkGreenGradient = "#2c5207";
	var darkPurple = "#46102e";

	// all normal colours
	var normalGreen = "#12B212";
	var normalRed = "#ED4337";
	var normalBlue = "#3182bd";
	var normalGreenGradient = "#64aa2d";
	var normalPurple = "#a83e6b";

	// all light colours
	var lightGreen = "#679a64";
	var lightRed = "#9c7777";
	var lightBlue = "#bdd7e7";
	var lightGreenGradient = "#82d78c";
	var lightPurple = "#ebafbe";

    /**
     * All public functions that we return the defined colours
     */
	return {
        white: function() { return white; },
        black: function() { return black; },
        grey: function() { return grey; },
        darkGreen: function() { return darkGreen; }, 
		darkRed: function() { return darkRed; }, 
        darkBlue: function() { return darkBlue; },
        darkGreenGradient: function() { return darkGreenGradient; },
        darkPurple: function() { return darkPurple },
 		normalGreen: function() { return normalGreen; }, 
		normalRed: function() { return normalRed; }, 
		normalBlue: function() { return normalBlue; },
		normalGreenGradient: function() { return normalGreenGradient; },
		normalPurple: function() { return normalPurple },
		lightGreen: function() { return lightGreen; }, 
		lightRed: function() { return lightRed; }, 
		lightBlue: function() { return lightBlue; },
		lightGreenGradient: function() { return lightGreenGradient; },
		lightPurple: function() { return lightPurple; }
    }
    
}());