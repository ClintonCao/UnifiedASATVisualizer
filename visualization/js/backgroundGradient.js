
var backgroundGradient = (function() {
	
	var greenTints = [d3.rgb("#243523"), d3.rgb('#28cb1c')];	
	var greenScale = d3.scale.linear().domain([0, 100]).interpolate(d3.interpolateHcl).range(greenTints);
	var blueTints = [d3.rgb("#2b2a47"), d3.rgb('#221bc2')];	
	var blueScale = d3.scale.linear().domain([0, 100]).interpolate(d3.interpolateHcl).range(blueTints);
	var redTints = [d3.rgb("#423030"), d3.rgb('#cd2828')];	
	var redScale = d3.scale.linear().domain([0, 100]).interpolate(d3.interpolateHcl).range(redTints);
	
	return {

        getBackground: function(svg,ratioArray,weight, id) {
			var total = ratioArray[0] + ratioArray[1] + ratioArray[2];
			if ( total == 0 ) {
				var firstRatio = 0;
				var firstRatio1 = 0.01;
				var secondRatio = 0;
				var secondRatio1 = 0.01;
				var end = 0;
			}else{
				var firstRatio = ratioArray[0] / total * 100;
				var firstRatio1 = firstRatio + 0.01;
				var secondRatio = ( ratioArray[0] + ratioArray[1]) / total * 100;
				var secondRatio1 = secondRatio + 0.01;
				var end = 100;
			}
			var firstEdge = firstRatio + "%";
			var firstEdge1 = firstRatio1 + "%";
			var secondEdge = secondRatio + "%";
			var secondEdge1 = secondRatio1 + "%";
			var endEdge = end + "%";
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
		.attr("stop-color", greenScale(weight*100))
		.attr("stop-opacity", 1);
		
	gradient.append("stop")
		.attr("offset", firstEdge)
		.attr("stop-color",  greenScale(weight*100))
		.attr("stop-opacity", 1);
		
	gradient.append("stop")
		.attr("offset", firstEdge)
		.attr("stop-color", redScale(weight*100))
		.attr("stop-opacity", 1);
	
	gradient.append("stop")
		.attr("offset", secondEdge)
		.attr("stop-color", redScale(weight*100))
		.attr("stop-opacity", 1);
		
	gradient.append("stop")
		.attr("offset", secondEdge1)
		.attr("stop-color", blueScale(weight*100))
		.attr("stop-opacity", 1);
		
	gradient.append("stop")
		.attr("offset", endEdge)
		.attr("stop-color", blueScale(weight*100))
		.attr("stop-opacity", 1);
		
		return gradient;
		}
    }
	
	
}());