/**
 * This class holds all colours that we defined and use in multiple places
 */
var colours = (function() {
	// All basic colours
	var white = "#FFFFFF";
	var black = "#000000";
	var grey = "#A2A2A2"
	// Always in order of light - normal - dark
	var greenNormal = ["#679a64", "#12B212", "#386938"];
	var red = ["#9c7777", "#ED4337", "#88120a"];
	var blue = ["#bdd7e7", "#3182bd", "#013b76"];
	var purple = ["#ebafbe", "#a83e6b", "#46102e"];
	var greenGradient = ["#82d78c", "#64aa2d", "#2c5207"];
	// Index for kind of color
	var lightColor = 0;
	var normalColor = 1;
	var darkColor = 2;

    /**
     * All public functions that we return the defined colours
     */
	return {
        white: function() { return white; },
        black: function() { return black; },
        grey: function() { return grey; },
        darkGreen: function() { return greenNormal[darkColor]; }, 
		darkRed: function() { return red[darkColor]; }, 
        darkBlue: function() { return blue[darkColor]; },
        darkGreenGradient: function() { return greenGradient[darkColor]; },
        darkPurple: function() { return purple[darkColor] },
 		normalGreen: function() { return greenNormal[normalColor]; }, 
		normalRed: function() { return red[normalColor]; }, 
		normalBlue: function() { return blue[normalColor]; },
		normalGreenGradient: function() { return greenGradient[normalColor]; },
		normalPurple: function() { return purple[normalColor] },
		lightGreen: function() { return greenNormal[lightColor]; }, 
		lightRed: function() { return red[lightColor]; }, 
		lightBlue: function() { return blue[lightColor]; },
		lightGreenGradient: function() { return greenGradient[lightColor]; },
		lightPurple: function() { return purple[lightColor]; }
    }
    
}());