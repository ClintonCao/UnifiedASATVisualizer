var sourceCode = (function() {

	var currentAsatWarnings = currentCatWarnings = currentMessageWarnings = [];
	var totalAsats = totalCats = [false, false, false];
	var latestAsatSet = latestCatSet = "";
	var currentTooltip;
	var curLine = -1;
	var	localD;
	var	localCurPath;
	var colorMethod = 0;

	/**
	 * Gives a specific line a specific colour
	 */
	function colorOne(line) {
		$(line).css('background',colours.normalPurple());
		$(line).find('.CodeMirror-gutter-wrapper').find('.CodeMirror-linenumber').css('background',colours.normalPurple());
	}

	/**
	 * Gives a specific line a specific colour
	 */
	function colorTwo(line) {
		$(line).css('background',colours.normalGreenGradient());
		$(line).find('.CodeMirror-gutter-wrapper').find('.CodeMirror-linenumber').css('background',colours.normalGreenGradient());
	}

	/**
	 * Gives a specific line a specific colour
	 */
	function colorThree(line) {
		$(line).css('background',colours.normalBlue());
		$(line).find('.CodeMirror-gutter-wrapper').find('.CodeMirror-linenumber').css('background',colours.normalBlue());
	}
	
	/**
	 * Gives a specific colour or a mix of colours
	 */
	function multipleColors(line, green, blue, purple) {
		if(green) {
			if(blue) {
				if(purple) {
					$(line).css('background','url(images/bpg.png)');
					$(line).find('.CodeMirror-gutter-wrapper').find('.CodeMirror-linenumber').css('background','url(images/bpg.png)');
				} else {
					$(line).css('background','url(images/bg.png)');
					$(line).find('.CodeMirror-gutter-wrapper').find('.CodeMirror-linenumber').css('background','url(images/bg.png)');
				}
			} else {
				if(purple) {
					$(line).css('background','url(images/gp.png)');
					$(line).find('.CodeMirror-gutter-wrapper').find('.CodeMirror-linenumber').css('background','url(images/gp.png)');
				} else {
					colorTwo(line);
				}
			}
		} else if(blue) {
			if(purple) {
				$(line).css('background','url(images/bp.png)');
				$(line).find('.CodeMirror-gutter-wrapper').find('.CodeMirror-linenumber').css('background','url(images/bp.png)');
			} else {
				colorThree(line);
			}
		} else if(purple) {
			colorOne(line);
		}
		
	}
	
	/**
	 * Highlights a line according to either the ASAT or the category
	 */
	function highlight(lineNumber, type, cat) {
		var childs = $( '.CodeMirror-code').children()
		var child = childs[lineNumber - 1];
		if(colorMethod == 0) {
			highlightASATs(child, type);
		} else if(colorMethod == 1) {
			highlightCategories(child, cat);
		}
	}

	/**
	 * Highlights a line according to the ASATs
	 */
	function highlightASATs(child, type) {
		var count = 0;
		for(var i = 0; i < totalCats.length; i++ ) {
			if(totalCats[i]) {
				count++;
			}
		}
		if(count > 1) {
			multipleColors(child, totalAsats[1], totalAsats[0], totalAsats[2]);
		} else {
			switch(type) {
				case 'CheckStyle':
					colorThree(child);
					break;
				case 'PMD':
					colorTwo(child);
					break;
				case 'FindBugs':
					colorOne(child);
					break;
			}
		}
	}

	/**
	 * Highlights a line according to the category
	 */
	function highlightCategories(child, cat) {
		var count = 0;
		for(var i = 0; i < totalAsats.length; i++ ) {
			if(totalAsats[i]) {
				count++;
			}
		}
		if(count > 1) {
			multipleColors(child, totalCats[2], totalCats[1], totalCats[0]);
		} else {
			switch(categoryMapper.categorizeWarningType(cat)) {
				// Functional Defects
				case 0:
					colorOne(child);
					break;
				// Maintainability Defects
				case 1:
					colorThree(child);
					break;
				// Other
				case 2:
					colorTwo(child);
					break;
			}
		}
	}

	/**
	 * Removes the specific asat and category if it
	 * was not in the array
	 */
	function removeLatestAsatAndCat() {
		var containsAsat = false;
		var containsCat = false;
		for(var i = 0; i < currentAsatWarnings.length; i++) {
			if(currentAsatWarnings[i] == latestAsatSet) {
				containsAsat = true;
			}
			if(currentCatWarnings[i] == latestCatSet) {
				containsCat = true;
			}
		}
		if(!containsAsat) {
			checkAsatAndCat(latestAsatSet, "", false);
		}
		if(!containsCat) {
			checkAsatAndCat("", latestCatSet, false);
		}
	}

	/**
	 * Sets the type and category to true or false
	 */
	function checkAsatAndCat(type, cat, trueOrFalse) {
		latestAsatSet = type;
		latestCatSet = cat;
		switch(type) {
			case 'CheckStyle':
				totalAsats[0] = trueOrFalse;
				break;
			case 'PMD':
				totalAsats[1] = trueOrFalse;
				break;
			case 'FindBugs':
				totalAsats[2] = trueOrFalse;
				break;
		}
		switch(categoryMapper.categorizeWarningType(cat)) {
			// Functional Defects
			case 0:
				totalCats[0] = trueOrFalse;
				break;
			// Maintainability Defects
			case 1:
				totalCats[1] = trueOrFalse;
				break;
			// Other
			case 2:
				totalCats[2] = trueOrFalse;
				break;
		}
	}

	/**
	 * Set the information for when the user is hovering over a warning
	 */
	function setLabels(lineNumber, type, cat, message) {
		currentAsatWarnings.push(type);
		currentCatWarnings.push(cat);
		currentMessageWarnings.push(message);
		checkAsatAndCat(type, cat, true);
		if( curLine != lineNumber ) {
			currentAsatWarnings.pop();
			currentCatWarnings.pop();
			currentMessageWarnings.pop();
			removeLatestAsatAndCat();
			highlight(curLine, currentAsatWarnings[currentAsatWarnings.length - 1], currentCatWarnings[currentCatWarnings.length - 1]);
			var childs = $( '.CodeMirror-code').children()
			var child = childs[curLine - 1];
			setToolTip(child);
			curLine = lineNumber;
			emptyArrays();
			currentAsatWarnings.push(type);
			currentCatWarnings.push(cat);
			currentMessageWarnings.push(message);
		} 
	}

	/**
	 * Clear all different arrays to standard values
	 */
	function emptyArrays() {
		currentAsatWarnings = [];
		currentCatWarnings = [];
		currentMessageWarnings = [];
		totalAsats = [false, false, false];
		totalCats = [false, false, false];
	}

	/**
	 * Creates a tooltip that will be shown on hover over a node
	 */
	function setToolTip(child) {
		var tooltip = d3.select("#chart-and-code").append("div").attr("class","d3-tip2").style("width", 300).style("position", "absolute").style("z-index", "10").style("visibility", "hidden");
		var textInTooltip = "Line " + curLine + ": ";
		for(var i = 0; i < currentAsatWarnings.length; i++) {
			textInTooltip += "<br>-" + currentAsatWarnings[i] + "<br>-" + currentCatWarnings[i] + "<br>-" + currentMessageWarnings[i] + "<br>"
		}
		
		$(child).mouseenter(function(){
			tooltip.html(textInTooltip);
            tooltip.style("visibility", "visible");
		});
		$(child).mousemove(function(){
			tooltip.style("top", (event.pageY - 130) + "px").style("left", (event.pageX - 280) + "px");
		});
		$(child).mouseleave(function(){
			tooltip.style("visibility", "hidden");
		});
	}

	/**
	 * It disables the normal and relative options
	 * Those are not needed when showing the source code
	 */
	function disableCertainOptions() {
		$('input.relative').attr('disabled','disabled');
		$('#normalButton').attr('disabled','disabled');
		document.getElementById("code-div").style.visibility = 'visible';
		document.getElementById("chart").style.visibility = 'hidden';
		document.getElementById('normalColourLabel2').style.textDecoration = 'line-through';
		document.getElementById('normalColourLabel2').style.cursor = 'default';
		document.getElementById('relativeLabel').style.textDecoration = 'line-through';
	}

	/**
	 * Will set up CodeMirror with the given class
	 */
	function displayCode(pathID) {	
		for ( var i = 0; i < codeExport.length; i++){	
			if (codeExport[i].path == pathID){
				var value = codeExport[i].code.substring(1, codeExport[i].code.length -2);
			}
		}
		var editor = CodeMirror(document.body.getElementsByTagName("article")[0], 
		{	value: value, lineNumbers: true, mode: "javascript", keyMap: "sublime", readOnly: "nocursor", autoCloseBrackets: true,
		    matchBrackets: true, showCursorWhenSelecting: true, theme: "monokai", tabSize: 2
		});
	}

	return {
		/**
	 	 * Will set up CodeMirror and shows it to the user
	 	 */
		show: function(d, curPath) {
			localD = d;
			localCurPath = curPath;
			emptyArrays();
			disableCertainOptions();
			displayCode(d.filePath);
			var warnings = getWarningLines(d.fileName);
			if(warnings.warningList.length > 0) {
				curLine = warnings.warningList[0].line;
			}
			for( var i =0 ; i < warnings.warningList.length; i ++ ){
				setLabels(warnings.warningList[i].line, warnings.warningList[i].type, warnings.warningList[i].cat, warnings.warningList[i].message);
			}
			if(warnings.warningList.length > 0) {
				highlight(curLine, warnings.warningList[warnings.warningList.length-1].type, warnings.warningList[warnings.warningList.length-1].cat);
				var childs = $( '.CodeMirror-code').children();
				var child = childs[curLine - 1];
				setToolTip(child);
			}
		},

		/**
	 	 * Hides code mirror to show the treemap
	 	 */
		hide: function() {
			$('input.relative').removeAttr("disabled");
			$('#normalButton').removeAttr("disabled");
			document.getElementById('relativeLabel').style.textDecoration = 'none';
			document.getElementById('normalColourLabel').style.textDecoration = 'none';
			var myNode = document.getElementById("code-article");
			while (myNode.firstChild) {
				myNode.removeChild(myNode.firstChild);
			}
			var chartDiv = document.getElementById("code-div");
				chartDiv.style.visibility = 'hidden';
				document.getElementById("chart").style.visibility = 'visible';
		},

		/**
	 	 * Reloads the content of the current CodeMirror according to filtered ASATs and/or categories
	 	 */
		fullReload: function() {
			sourceCode.hide();
			sourceCode.show(localD, localCurPath);
			$('.CodeMirror').width(opts.width).height(opts.height - 30);
		},

		/**
	 	 * Either 0 for ASATs filter or 1 for categories filter
	 	 */
		setColorMethod: function(method) {
			colorMethod = method;
		}
	}
}());