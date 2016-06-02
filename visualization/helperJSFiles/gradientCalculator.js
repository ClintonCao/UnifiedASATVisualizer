var gradientCalculator = (function() {

	var surface, surfHead, mainSurf, surfTail, firstBoundary, secondBoundary, totalLineLength;

	function measurements(x, y) {
		if( x > y) {
			var temp = x;
			x = y;
			y = temp;
		}
		var b = y - x;
		var v = Math.sqrt(2 * x * x);
		var w = Math.sqrt(2 * y * y);
		var t = 0.5 * w - v;
		var p = y - 2 * x;
		firstBoundary = 0.5*v;
		secondBoundary = v + t;
		totalLineLength = Math.sqrt(2 * b * b) * 0.5 + v;

		surfHead = 0.5 * firstBoundary *v;
		mainSurf = (secondBoundary - firstBoundary) * v;
		surfTail = surfHead;

		surface = surfHead + mainSurf + surfTail;

		// console.log("Line total length: ");
		// console.log(totalLineLength);
		// console.log("v: ");
		// console.log(v);
		// console.log("Surfhead: ");
		// console.log(surfHead);
		// console.log("Main surf: ");
		// console.log(mainSurf);
		// console.log("Tail surf: ");
		// console.log(surfTail);
		// console.log("x * y surf: ");
		// console.log(x * y);

		// console.log("First bound: ");
		// console.log(firstBoundary);
		// console.log("Second Bound: ");
		// console.log(secondBoundary);
		// console.log("Last bound: ");
		// console.log(totalLineLength);

	}

	function calculateBoundaries(a, b) {
		var aLoc = calculateSpecificBoundary(a);
		var bLoc = calculateSpecificBoundary(a + b);
		return [aLoc/totalLineLength * 100, bLoc/totalLineLength * 100];
	}

	function calculateSpecificBoundary(z) {
		var zSurf = z * surface;
		var zLocation = 0;
		if(zSurf > surfHead) {
			console.log(zLocation);
			zLocation += firstBoundary;
			zSurf -= surfHead;
			if(zSurf > mainSurf) {
				zSurf -= mainSurf;
				zLocation = secondBoundary;
				return inSurfTail(zSurf, zLocation);
			} else {
				console.log(zLocation);
				return inMainSurf(zSurf, zLocation);
			}
		} else {
			return inSurfHead(zSurf);
		}
	}

	function inSurfHead(remainder) {
		var occupiedSurf = remainder / surfHead;
		var length = Math.sqrt(occupiedSurf);
		return length;
	}

	function inMainSurf(remainder, curLength) {
		console.log("remainder");
		console.log(remainder);
		var occupiedSurf = remainder / mainSurf;
		var extraLength = occupiedSurf * (secondBoundary - firstBoundary);
		console.log("extraLength");
		console.log(extraLength);
		return curLength + extraLength;
	}	

	function inSurfTail(remainder, curLength) {
		var occupiedSurf = remainder / surfTail;
		var length = 1 - Math.sqrt(occupiedSurf);
		return curLength + length; 
	}

	return {
		calculate: function(x, y, a, b) {
			measurements(x, y);
			//onsole.log("44");
			return calculateBoundaries(a, b);
		}
	}

}());