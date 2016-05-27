
var backgroundGradient = (function() {
	
	
	function componentToHex(c) {
    var hex = c.toString(16);
    return hex.length == 1 ? "0" + hex : hex;
}

function rgbToHex(r, g, b) {
    return "#" + componentToHex(r) + componentToHex(g) + componentToHex(b);
}



	return {

        getBackground: function(svg,ratio, id) {
			var edge = ratio + "%";
			var ratio1 = ratio + 0.01;
			var edge1 = ratio1 + "%";
			console.log(edge);
			console.log(edge1);
           var gradient = svg.append("defs")
	  .append("linearGradient")
		.attr("id", "gradient" + id)
		.attr("x1", "0%")
		.attr("y1", "0%")
		.attr("x2", "100%")
		.attr("y2", "0%")
		.attr("spreadMethod", "pad");
	
	gradient.append("stop")
		.attr("offset", "0%")
		.attr("stop-color", "#0c0")
		.attr("stop-opacity", 1);
		
	gradient.append("stop")
		.attr("offset", edge)
		.attr("stop-color", "#0c0")
		.attr("stop-opacity", 1);
		
	gradient.append("stop")
		.attr("offset", edge1)
		.attr("stop-color", "#c00")
		.attr("stop-opacity", 1);
	
	gradient.append("stop")
		.attr("offset", "66%")
		.attr("stop-color", "#c00")
		.attr("stop-opacity", 1);
		
	gradient.append("stop")
		.attr("offset", "66.1%")
		.attr("stop-color", "#163cff")
		.attr("stop-opacity", 1);
		
	gradient.append("stop")
		.attr("offset", "100%")
		.attr("stop-color", "#163cff")
		.attr("stop-opacity", 1);
		
		return gradient;
		}
    }
	
	
}());