var gradientCalculator = (function() {

	var surface, surfHead, mainSurf, surfTail, firstBoundary, secondBoundary, totalLineLength;

	/**
	* calculate the 3 different areas in the square.
	* 2 traingles and the center part: head, main, tail
	*/
	function measurements(x, y) {
		if( x > y) {
			var temp = x;x = y;y = temp;
		}
		var b = y - x;
		var v = Math.sqrt(2 * x * x);
		var w = Math.sqrt(2 * y * y);
		var t = 0.5 * w - v;
		var p = y - 2 * x;
		firstBoundary = 0.5*v;
		totalLineLength = Math.sqrt(2 * b * b) * 0.5 + v;
		secondBoundary = totalLineLength - firstBoundary;

		surfHead = 0.5 * v * v;
		mainSurf = (secondBoundary - firstBoundary) * v;
		surfTail = surfHead;

		surface = surfHead + mainSurf + surfTail;

	}
	/**
	* calculate the right boudaries depending on the rations of a,b,c
	*/
	function calculateBoundaries(a, b, total) {
		
		var aLoc = calculateSpecificBoundary(a, total);
		var bLoc = calculateSpecificBoundary(a + b, total);
				
		return [aLoc/totalLineLength * 100, bLoc/totalLineLength * 100];
	}

	/**
	* distinguish all cases where the boundary of the gradient is in part 1,2 or 3.
	*/ 
	function calculateSpecificBoundary(z, total) {
		var zSurf = (z/total) * surface;
		var zLocation = 0;
		if(zSurf > surfHead) {
			zLocation = firstBoundary;
			zSurf -= surfHead;
			if(zSurf > mainSurf) {
				zSurf -= mainSurf;
				zLocation = secondBoundary;
				return inSurfTail(zSurf, zLocation);
			} else {
				return inMainSurf(zSurf, zLocation);
			}
		} else {
			return inSurfHead(zSurf);
		}
	}
	/**
	* the case the boundary is in the head part
	*/
	function inSurfHead(remainder) {
		var occupiedSurf = remainder / surfHead;
		var length = Math.sqrt(occupiedSurf);
		return length * firstBoundary;
	}

	/**
	* the case the boundary is in the main part
	*/
	function inMainSurf(remainder, curLength) {
		var occupiedSurf = remainder / mainSurf;
		var extraLength = occupiedSurf * (secondBoundary - firstBoundary);
		return curLength + extraLength;
	}	

	/**
	* the case the boundary is in the tail part
	*/
	function inSurfTail(remainder, curLength) {
		var occupiedSurf = remainder / surfTail;
		var length
		if(1 - occupiedSurf < 0.05){ length = 1; }
		else if(1 - occupiedSurf > 0.95){ length = 0; }
		else{ length =  occupiedSurf* occupiedSurf; }
		return curLength + length * firstBoundary;
	}

	return {
		/**
		* the public method to call for calculating the boundaries in the gradient.
		*/
		calculate: function(x, y, a, b ,c) {
			if(x == 0) {
				x = 0.01;
			}
			if(y == 0) {
				y = 0.01;
			}
			measurements(x, y);
			return calculateBoundaries(a, b, a+b+c);
		},
		/**
		* method to calculate the direction of the gradient for a perfect 45 degree angle.
		* But this doesn't work for some reason maybe in the future a nice extra feature.
		*/
		get45Angle: function (x,y){
			if ( x > y ){
				var ratio  = (100* (y/x)) + "%";
				if(ratio == "-Infinity%") {
					ratio = "100%";
				}
				return [ratio,"100%"]
			}else{
				var ratio  = (100* (x/y)) + "%";
				if(ratio == "-Infinity%") {
					ratio = "100%";
				}
				return ["100%", ratio]
			}
		}
	}

}());