var backgroundObject = (function() {
	var maxConstant = 100;
	var colorMethod = 0;
	var twoColors, color, greenTints, greenScale, blueTints, blueScale, redTints, redScale, grayTints, grayScale;
	var firstRatioBegin, firstRatioEnd, SecondRatioBegin, SecondRatioEnd, ThirdRatioBegin, ThirdRatioEnd, firstEdgeBegin, firstEdgeEnd, SecondEdgeBegin, SecondEdgeEnd, ThridEdgeBegin, ThirdEdgeEnd;
	reloadColorScale();
	
	function reloadColorScale(){
		twoColors = [d3.rgb("#00a700"), d3.rgb('#a90000')];
		color = d3.scale.linear().domain([0, maxConstant]).interpolate(d3.interpolateHcl).range(twoColors);
		greenTints = [d3.rgb("#8c9b8b"), d3.rgb('#0b9c01')];
		greenScale = d3.scale.linear().domain([0, maxConstant]).interpolate(d3.interpolateHcl).range(greenTints);
		blueTints = [d3.rgb("#a7b1bc"), d3.rgb('#06387b')];	
		blueScale = d3.scale.linear().domain([0, maxConstant]).interpolate(d3.interpolateHcl).range(blueTints);
		redTints = [d3.rgb("#c2b6b6"), d3.rgb('#8e0000')];	
		redScale = d3.scale.linear().domain([0, maxConstant]).interpolate(d3.interpolateHcl).range(redTints);
		grayTints = [d3.rgb("#a2a2a2"), d3.rgb('#a2a2a2')];	
		grayScale =  d3.scale.linear().domain([0, 0]).interpolate(d3.interpolateHcl).range(grayTints);
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
	function setToPercentages() {
		firstEdgeBegin = firstRatioBegin + "%";
		firstEdgeEnd = firstRatioEnd + "%";
		SecondEdgeBegin = SecondRatioBegin + "%";
		SecondEdgeEnd = SecondRatioEnd + "%";
		ThridEdgeBegin = ThridRatioBegin + "%";
		ThirdEdgeEnd = ThirdRatioEnd + "%";
	}
	function setGreenScale(gradient, start, end, weight) {
		gradient.append("stop")
			.attr("offset", start)
			.attr("stop-color", greenScale(weight*100))
			.attr("stop-opacity", 1);
		gradient.append("stop")
			.attr("offset", end)
			.attr("stop-color", greenScale(weight*100))
			.attr("stop-opacity", 1);
	}
	function setRedScale(gradient, start, end, weight) {
		gradient.append("stop")
			.attr("offset", start)
			.attr("stop-color", redScale(weight*100))
			.attr("stop-opacity", 1);
		gradient.append("stop")
			.attr("offset", end)
			.attr("stop-color", redScale(weight*100))
			.attr("stop-opacity", 1);
	}
	function setBlueScale(gradient, start, end, weight) {
		gradient.append("stop")
			.attr("offset", start)
			.attr("stop-color", blueScale(weight*100))
			.attr("stop-opacity", 1);
		gradient.append("stop")
			.attr("offset", end)
			.attr("stop-color", blueScale(weight*100))
			.attr("stop-opacity", 1);
	}
	function createGradientColours(gradient, onlyOneColour, whichOne, weight, ratioArray) {
		console.log("R array: " + ratioArray);
		if(onlyOneColour) {
			switch(whichOne) {
				case 0:
					setGreenScale(gradient, "0%", "100%", weight);
					return gradient;
				case 1:
					setRedScale(gradient, "0%", "100%", weight);
					return gradient;
				case 2:
					setBlueScale(gradient, "0%", "100%", weight);
					return gradient;
				default:
					return gradient;
			}
		} else {
			if(ratioArray[0] == 0 && ratioArray[1] != 0 && ratioArray[2] != 0) {			
				setRedScale(gradient, "0%", SecondEdgeEnd, weight);
				ThridEdgeBegin = (SecondRatioEnd + 0.01) + "%";
				setBlueScale(gradient, ThridEdgeBegin, "100%", weight);
			} else if(ratioArray[1] == 0 && ratioArray[0] != 0 && ratioArray[2] != 0) {
				setGreenScale(gradient, "0%", firstEdgeEnd, weight);
				ThridEdgeBegin = (firstRatioEnd + 0.01) + "%";
				setBlueScale(gradient, ThridEdgeBegin, "100%", weight);
			} else if(ratioArray[2] == 0 && ratioArray[1] != 0 && ratioArray[0] != 0) {
				setGreenScale(gradient, "0%", firstEdgeEnd, weight);
				SecondEdgeBegin = (firstRatioEnd + 0.01) + "%";
				setRedScale(gradient, SecondEdgeBegin, "100%", weight);
			} else {
				setGreenScale(gradient, "0%", firstEdgeEnd, weight)
				setRedScale(gradient, SecondEdgeBegin, SecondEdgeEnd, weight);
				setBlueScale(gradient, ThridEdgeBegin, "100%", weight);
			}
			return gradient;
		}
	}
	function calculateBackgroundGradient(svg,ratioArray,weight, id,x,y){
		var total = ratioArray[0] + ratioArray[1] + ratioArray[2];
		
		var tuple = gradientCalculator.calculate(x,y, ratioArray[0], ratioArray[1], ratioArray[2]);
		var tupleAngle = gradientCalculator.get45Angle(x,y);

		firstRatioBegin = 0;
		firstRatioEnd = tuple[0];
		SecondRatioBegin = firstRatioEnd + 0.01;
		SecondRatioEnd = tuple[1]
		ThridRatioBegin = SecondRatioEnd + 0.01;
		ThirdRatioEnd = 100;

		if(total == 0) {
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
				.attr("stop-color", grayScale(0))
				.attr("stop-opacity", 1);
			gradient.append("stop")
				.attr("offset", "100%")
				.attr("stop-color",  grayScale(0))
				.attr("stop-opacity", 1);
			return gradient;
		} else {
			var gradient = svg.append("defs")	
			  	.append("linearGradient")
				.attr("id", "gradient" + id)
				.attr("x1", "0%")
				.attr("y1", "0%")
				.attr("x2", tupleAngle[0])
				.attr("y2", tupleAngle[1])
				.attr("spreadMethod", "pad");
			if(ratioArray[0] == 100) {
				setToPercentages();
				return createGradientColours(gradient, true, 0, weight, ratioArray);
			} else if(ratioArray[1] == 100) {
				setToPercentages();
				return createGradientColours(gradient, true, 1, weight, ratioArray);
			} else if(ratioArray[2] == 100) {
				setToPercentages();
				return createGradientColours(gradient, true, 2, weight, ratioArray);
			} else {
				setToPercentages();
				return createGradientColours(gradient, false, -1, weight, ratioArray);
			}
		}
	}
	function checkIfNot0(number) {
		if(number == 0) {
			return 0.1;
		} else {
			return number;
		}
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
			.attr("stop-color", currentColorScale(weight*100))
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
			var warningsTotal = checkIfNot0(d.warnings);

            var ratioCheckStyle = Math.round(constant * d.warningsCheckStyle / warningsTotal);	
			ratioArrayASAT.push(ratioCheckStyle);
			
            var ratiowarningsPMD = Math.round(constant * d.warningsPMD / warningsTotal);	
			ratioArrayASAT.push(ratiowarningsPMD);
			
            var ratioFindBugs = Math.round(constant * d.warningsFindBugs / warningsTotal);	
			ratioArrayASAT.push(ratioFindBugs);
			
			ratioArrayCategory = [];
            var ratioFunctionalDefects = Math.round(constant * d.warningsFunctionalDefects / warningsTotal);	
			ratioArrayCategory.push(ratioFunctionalDefects);
			
            var ratioMaintainabilityDefects = Math.round(constant * d.warningsMaintainabilityDefects / warningsTotal);	
			ratioArrayCategory.push(ratioMaintainabilityDefects);
			
            var ratioOtherDefects = Math.round(constant * d.warningsOtherDefects / warningsTotal);	
			ratioArrayCategory.push(ratioOtherDefects);
		
			return [ratioArrayASAT,ratioArrayCategory]
		}
    }
	
}());