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
		totalLineLength = Math.sqrt(2 * b * b) * 0.5 + v;
		secondBoundary = totalLineLength - firstBoundary;

		surfHead = 0.5 * v *v;
		mainSurf = (secondBoundary - firstBoundary) * v; //x * y - surfHead* 2;
		surfTail = surfHead;

		surface = surfHead + mainSurf + surfTail;

	}

	function calculateBoundaries(a, b, total) {
		
		var aLoc = calculateSpecificBoundary(a, total);
		var bLoc = calculateSpecificBoundary(a + b, total);
				
		return [aLoc/totalLineLength * 100, bLoc/totalLineLength * 100];
	}

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

	function inSurfHead(remainder) {
		var occupiedSurf = remainder / surfHead;
		var length = Math.sqrt(occupiedSurf);
		return length * firstBoundary;
	}

	function inMainSurf(remainder, curLength) {
		var occupiedSurf = remainder / mainSurf;
		var extraLength = occupiedSurf * (secondBoundary - firstBoundary);
		return curLength + extraLength;
	}	

	function inSurfTail(remainder, curLength) {
		var occupiedSurf = remainder / surfTail;
		var length
		if(1 - occupiedSurf < 0.05){ length = 1; }
		else if(1 - occupiedSurf > 0.95){ length = 0; }
		else{ length =  occupiedSurf* occupiedSurf; }
		return curLength + length * firstBoundary;
	}

	return {
		calculate: function(x, y, a, b ,c) {
			if(x == 0) {
				x = 0.01;
			}
			if(y == 0) {
				y = 0.01;
			}
			measurements(x, y);
			console.log("x: " + x);
			console.log("y: " + y);
			console.log("a: " + a);
			console.log("b: " + b);
			console.log("c: " + c);
			return calculateBoundaries(a, b, a+b+c);
		},
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