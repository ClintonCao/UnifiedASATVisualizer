
var backgroundObject = (function() {
	var maxConstant = 100;
	var colorMethod = 0;
	
	var twoColors, color,greenTints,greenScale,blueTints, blueScale,redTints,redScale,grayTints,grayScale;
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
	function calculateBackgroundGradient(svg,ratioArray,weight, id,x,y){
		var total = ratioArray[0] + ratioArray[1] + ratioArray[2];
		//console.log("r array: " + ratioArray);
		var tuple = gradientCalculator.calculate(x,y, ratioArray[0], ratioArray[1], ratioArray[2]);
		var firstRatioBegin = 0;
		var firstRatioEnd = tuple[0];
		var SecondRatioBegin = firstRatioEnd + 0.01;
		var SecondRatioEnd = tuple[1]
		var ThridRatioBegin = SecondRatioEnd + 0.01;
		var ThirdRatioEnd = 100;

		console.log(ratioArray);

		if(total == 0) {
			var gradient = svg.append("defs")
			.append("linearGradient")
			.attr("id", "gradient" + id)
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
			if(ratioArray[0] == 100) {
				SecondRatioBegin = 0;
				SecondRatioEnd = 0;
				ThridRatioBegin = 0;
				ThirdRatioEnd = 0;
			} else if(ratioArray[1] == 100) {
				ThridRatioBegin = 0;
				ThirdRatioEnd = 0;
			} else if(ratioArray[2] == 100) {
				SecondRatioBegin = 0;
				SecondEdgeEnd = 0;
				ThridRatioBegin = 0;
				ThirdRatioEnd = 0;
			}
		}
		
		var firstEdgeBegin = firstRatioBegin + "%";
		var firstEdgeEnd = firstRatioEnd + "%";
		var SecondEdgeBegin = SecondRatioBegin + "%";
		var SecondEdgeEnd = SecondRatioEnd + "%";
		var ThridEdgeBegin = ThridRatioBegin + "%";
		var ThirdEdgeEnd = ThirdRatioEnd + "%";

		console.log(firstEdgeEnd);
		console.log(SecondEdgeEnd);
		console.log(ThirdEdgeEnd);

	   	var tupleAngle = gradientCalculator.get45Angle(x,y);
       	var gradient = svg.append("defs")	
					  	.append("linearGradient")
						.attr("id", "gradient" + id)
						.attr("x1", "0%")
						.attr("y1", "0%")
						.attr("x2", tupleAngle[0])
						.attr("y2", tupleAngle[1])
						.attr("spreadMethod", "pad");

	gradient.append("stop")
		.attr("offset", firstEdgeBegin)
		.attr("stop-color", greenScale(weight*100))
		.attr("stop-opacity", 1);
		
	gradient.append("stop")
		.attr("offset", firstEdgeEnd)
		.attr("stop-color",  greenScale(weight*100))
		.attr("stop-opacity", 1);
		
	gradient.append("stop")
		.attr("offset", SecondEdgeBegin)
		.attr("stop-color", redScale(weight*100))
		.attr("stop-opacity", 1);
	
	gradient.append("stop")
		.attr("offset", SecondEdgeEnd)
		.attr("stop-color", redScale(weight*100))
		.attr("stop-opacity", 1);
		
	gradient.append("stop")
		.attr("offset", ThridEdgeBegin)
		.attr("stop-color", blueScale(weight*100))
		.attr("stop-opacity", 1);
		
	gradient.append("stop")
		.attr("offset", ThirdEdgeEnd)
		.attr("stop-color", blueScale(weight*100))
		.attr("stop-opacity", 1);
		return gradient;
	}
	function checkIfNot0(number) {
		if(number == 0) {
			return 0.000001;
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
				if ( ratioCheckStyle > maxConstant ) { ratioCheckStyle = maxConstant; }
				ratioArrayASAT.push(ratioCheckStyle);
				//console.log("---------------------------CS------------------------");
				//console.log(d.warningsCheckStyle);
				//console.log(ratioArrayASAT);
                var ratiowarningsPMD = Math.round(constant * d.warningsPMD / warningsTotal);	
				if ( ratiowarningsPMD > maxConstant ) { ratiowarningsPMD = maxConstant; }
				ratioArrayASAT.push(ratiowarningsPMD);
				//console.log("---------------------------PMD------------------------");
				//console.log(d.warningsPMD);
				//console.log(ratioArrayASAT);
                var ratioFindBugs = Math.round(constant * d.warningsFindBugs / warningsTotal);	
				if ( ratioFindBugs > maxConstant ) { ratioFindBugs = maxConstant; }
				ratioArrayASAT.push(ratioFindBugs);
				//console.log("---------------------------FB------------------------");
				//console.log(d.warningsFindBugs);
				//console.log(ratioArrayASAT);

				ratioArrayCategory = [];
                var ratioFunctionalDefects = Math.round(constant * d.warningsFunctionalDefects / warningsTotal);	
				if ( ratioFunctionalDefects > maxConstant ) { ratioFunctionalDefects = maxConstant; }
				ratioArrayCategory.push(ratioFunctionalDefects);
				//console.log("---------------------------Func------------------------");
				//console.log(d.warningsFunctionalDefects);
				//console.log(ratioArrayCategory);
				
                var ratioMaintainabilityDefects = Math.round(constant * d.warningsMaintainabilityDefects / warningsTotal);	
				if ( ratioMaintainabilityDefects > maxConstant ) { ratioMaintainabilityDefects = maxConstant; }
				ratioArrayCategory.push(ratioMaintainabilityDefects);
				//console.log("---------------------------Main------------------------");
				//console.log(d.warningsMaintainabilityDefects);
				//console.log(ratioArrayCategory);
				
                var ratioOtherDefects = Math.round(constant * d.warningsOtherDefects / warningsTotal);	
				if ( ratioOtherDefects > maxConstant ) { ratioOtherDefects = maxConstant; }
				ratioArrayCategory.push(ratioOtherDefects);
				//console.log("---------------------------Other------------------------");
				//console.log(d.warningsOtherDefects);
				//console.log(ratioArrayCategory);
				return [ratioArrayASAT,ratioArrayCategory]
		}
    }
	
}());