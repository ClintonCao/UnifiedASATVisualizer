
var backgroundObject = (function() {
	var maxConstant = 100;
	var colorMethod = 0;
	
	var twoColors, color,greenTints,greenScale,blueTints, blueScale,redTints,redScale,grayTints,grayScale;
	reloadColorScale();
	
	function reloadColorScale(){
		console.log(maxConstant);
		twoColors = [d3.rgb("#00a700"), d3.rgb('#a90000')];
		color = d3.scale.linear().domain([0, maxConstant]).interpolate(d3.interpolateHcl).range(twoColors);
		console.log(color(100));
		greenTints = [d3.rgb("#8c9b8b"), d3.rgb('#0b9c01')];
		greenScale = d3.scale.linear().domain([0, maxConstant]).interpolate(d3.interpolateHcl).range(greenTints);
		blueTints = [d3.rgb("#a7b1bc"), d3.rgb('#06387b')];	
		blueScale = d3.scale.linear().domain([0, maxConstant]).interpolate(d3.interpolateHcl).range(blueTints);
		redTints = [d3.rgb("#c2b6b6"), d3.rgb('#8e0000')];	
		redScale = d3.scale.linear().domain([0, maxConstant]).interpolate(d3.interpolateHcl).range(redTints);
		grayTints = [d3.rgb("#a2a2a2"), d3.rgb('#a2a2a2')];	
	}
	
	/*
	 *
	 * Calculates the worst case of #warnings/loc
	 *
	 */ 
	function getRelativeWarnings() {
		var worstRatio = 0;
		for(var p =0; p < inputData.length; p++){
			var package = inputData[p];
			var classesArray = package.classes;
			for (i = 0; i < classesArray.length; i++) {
				classObjectJson = classesArray[i];
				var numberOfWarnings = 0;
				for (j = 0; j < classObjectJson.warningList.length; j++) { 
					numberOfWarnings++;
		  		}
		  		var curRatio = numberOfWarnings / classObjectJson.loc;
		  		if(curRatio > worstRatio) {
		  			worstRatio = curRatio;
		  		}
			}
		}
		return (worstRatio * 100);
	}
	function getNormalColors() {
			return d3.scale.linear().domain([0, maxConstant]).interpolate(d3.interpolateHcl).range(twoColors);
	}
	function calculateBackgroundGradient(svg,ratioArray,weight, id,x,y){
		var total = ratioArray[0] + ratioArray[1] + ratioArray[2];
			if ( total == 0 ) {
				var firstRatio = 0;
				var firstRatio1 = 0.01;
				var secondRatio = 0;
				var secondRatio1 = 0.01;
				var end = 0;
			}else{
				var firstRatio = ratioArray[0] / total;
				var secondRatio = ( ratioArray[0] + ratioArray[1]) / total;

				var tuple = gradientCalculator.calculate(x,y, firstRatio, secondRatio);
				firstRatio = tuple[0]
				secondRatio = tuple[1]
				var firstRatio1 = firstRatio + 0.01;
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
		.attr("y2", "100%")
		.attr("spreadMethod", "pad");
	console.log(weight);
	gradient.append("stop")
		.attr("offset", "0%")
		.attr("stop-color", greenScale(weight*maxConstant))
		.attr("stop-opacity", 1);
		
	gradient.append("stop")
		.attr("offset", firstEdge)
		.attr("stop-color",  greenScale(weight*maxConstant))
		.attr("stop-opacity", 1);
		
	gradient.append("stop")
		.attr("offset", firstEdge)
		.attr("stop-color", redScale(weight*maxConstant))
		.attr("stop-opacity", 1);
	
	gradient.append("stop")
		.attr("offset", secondEdge)
		.attr("stop-color", redScale(weight*maxConstant))
		.attr("stop-opacity", 1);
		
	gradient.append("stop")
		.attr("offset", secondEdge1)
		.attr("stop-color", blueScale(weight*maxConstant))
		.attr("stop-opacity", 1);
		
	gradient.append("stop")
		.attr("offset", endEdge)
		.attr("stop-color", blueScale(weight*maxConstant))
		.attr("stop-opacity", 1);
		return gradient;
	}
	function calculateBackground(svg, weight, id){
		
		var currentColorScale = getNormalColors();
	
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
			.attr("stop-color", currentColorScale(weight*maxConstant))
			.attr("stop-opacity", 1);
		return gradient;
	}
	return {
        getBackground: function(svg,ratioTuple,weight, id,x,y) {
			if ( weight > 1 ){ weight = 1;}
			switch(colorMethod) {
				case 0:
					return calculateBackground(svg, weight, id);
					break;
				case 1:
					return calculateBackgroundGradient(svg,ratioTuple[0],weight, id,x,y);
					break;
				case 2:
					return calculateBackgroundGradient(svg,ratioTuple[1],weight, id,x,y);
					break;
			} 
		},
		setColorsRelative: function(){
			maxConstant = getRelativeWarnings();
			reloadColorScale();
		},
		setColorsAbsolute: function(){
			maxConstant = 100;
			reloadColorScale();
		},
		setColorMethod: function(index){
			colorMethod = index;
		},
		getRatios: function(d){
				ratioArrayASAT = [];
				var constant = 100;
                var ratioCheckStyle = Math.round(constant * d.warningsCheckStyle / d.value);	
				if ( ratioCheckStyle > maxConstant ) { ratioCheckStyle = maxConstant; }
				ratioArrayASAT.push(ratioCheckStyle);
                var ratiowarningsPMD = Math.round(constant * d.warningsPMD / d.value);	
				if ( ratiowarningsPMD > maxConstant ) { ratiowarningsPMD = maxConstant; }
				ratioArrayASAT.push(ratiowarningsPMD);
                var ratioFindBugs = Math.round(constant * d.warningsFindBugs / d.value);	
				if ( ratioFindBugs > maxConstant ) { ratioFindBugs = maxConstant; }
				ratioArrayASAT.push(ratioFindBugs);

				ratioArrayCategory = [];
                var ratioFunctionalDefects = Math.round(constant * d.warningsFunctionalDefects / d.value);	
				if ( ratioFunctionalDefects > maxConstant ) { ratioFunctionalDefects = maxConstant; }
				ratioArrayCategory.push(ratioFunctionalDefects);
				
                var ratioMaintainabilityDefects = Math.round(constant * d.warningsMaintainabilityDefects / d.value);	
				if ( ratioMaintainabilityDefects > maxConstant ) { ratioMaintainabilityDefects = maxConstant; }
				ratioArrayCategory.push(ratioMaintainabilityDefects);
				
                var ratioOtherDefects = Math.round(constant * d.warningsOtherDefects / d.value);	
				if ( ratioOtherDefects > maxConstant ) { ratioOtherDefects = maxConstant; }
				ratioArrayCategory.push(ratioOtherDefects);
				return [ratioArrayASAT,ratioArrayCategory]
		}
    }
	
}());