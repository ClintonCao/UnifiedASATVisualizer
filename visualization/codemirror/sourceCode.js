var sourceCode = (function() {

	var currentAsatWarnings = currentCatWarnings = currentMessageWarnings = [];
	var totalAsats = totalCats = [false, false, false];
	var latestAsatSet = latestCatSet = "";
	var currentTooltip;
	var curLine = -1;
	var	localD;
	var	localCurPath;
	var colorMethod = 0;

	// Gives a specific line a specific colour
	function colorOne(line) {
		$(line).css('background',colours.normalPurple());
		$(line).find('.CodeMirror-gutter-wrapper').find('.CodeMirror-linenumber').css('background',colours.normalPurple());
	}
	// Gives a specific line a specific colour
	function colorTwo(line) {
		$(line).css('background',colours.darkGreenGradient());
		$(line).find('.CodeMirror-gutter-wrapper').find('.CodeMirror-linenumber').css('background',colours.darkGreenGradient());
	}
	// Gives a specific line a specific colour
	function colorThree(line) {
		$(line).css('background',colours.darkBlue());
		$(line).find('.CodeMirror-gutter-wrapper').find('.CodeMirror-linenumber').css('background',colours.darkBlue());
	}
	
	// Gives a specific line multiple colours
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
				} 
			}
		} else if(blue) {
			if(purple) {
				$(line).css('background','url(images/bp.png)');
				$(line).find('.CodeMirror-gutter-wrapper').find('.CodeMirror-linenumber').css('background','url(images/bp.png)');
			}
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

	function removeLatestAsatAndCat() {
		switch(latestAsatSet) {
			case 'CheckStyle':
				totalAsats[0] = false;
				break;
			case 'PMD':
				totalAsats[1] = false;
				break;
			case 'FindBugs':
				totalAsats[2] = false;
				break;
		}
		switch(latestCatSet) {
			// Functional Defects
			case 0:
				totalCats[0] = false;
				break;
			// Maintainability Defects
			case 1:
				totalCats[1] = false;
				break;
			// Other
			case 2:
				totalCats[2] = false;
				break;
		}
	}

	function checkAsatAndCat(type, cat) {
		latestAsatSet = type;
		latestCatSet = cat;
		switch(type) {
			case 'CheckStyle':
				totalAsats[0] = true;
				break;
			case 'PMD':
				totalAsats[1] = true;
				break;
			case 'FindBugs':
				totalAsats[2] = true;
				break;
		}
		switch(categoryMapper.categorizeWarningType(cat)) {
			// Functional Defects
			case 0:
				totalCats[0] = true;
				break;
			// Maintainability Defects
			case 1:
				totalCats[1] = true;
				break;
			// Other
			case 2:
				totalCats[2] = true;
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
		checkAsatAndCat(type, cat);
		if( curLine != lineNumber ) {
			currentAsatWarnings.pop();
			currentCatWarnings.pop();
			currentMessageWarnings.pop();
			removeLatestAsatAndCat();
			highlight(curLine);
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
	 * Clear all three arrays
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

	// Will set up CodeMirror with the given class
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
		// will set up CodeMirror and shows it to the user
		show: function(d, curPath) {
			localD = d;
			localCurPath = curPath;
			emptyArrays();
			curLine = -1;
			
			$('input.relative').attr('disabled','disabled');
			$('#normalButton').attr('disabled','disabled');
			document.getElementById("code-div").style.visibility = 'visible';
			document.getElementById("chart").style.visibility = 'hidden';
			document.getElementById('normalColourLabel').style.textDecoration = 'line-through';
			document.getElementById('relativeLabel').style.textDecoration = 'line-through';
			
			displayCode(d.filePath);
			var warnings = getWarningLines(d.fileName);
			curLine = warnings.warningList[0].line;
			for( var i =0 ; i < warnings.warningList.length; i ++ ){
				setLabels(warnings.warningList[i].line, warnings.warningList[i].type, warnings.warningList[i].cat, warnings.warningList[i].message);
			}
			highlight(curLine, warnings.warningList[warnings.warningList.length-1].type, warnings.warningList[warnings.warningList.length-1].cat);
			var childs = $( '.CodeMirror-code').children()
			var child = childs[curLine - 1];
			setToolTip(child);
		},

		// hides code mirror to show the treemap
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

		// reloads the content of the current CodeMirror according to filtered ASATs and/or categories
		fullReload: function() {
			sourceCode.hide();
			sourceCode.show(localD, localCurPath);
			$('.CodeMirror').width(opts.width).height(opts.height - 30);
		},

		// either 0 for ASATs filter or 1 for categories filter
		setColorMethod: function(method) {
			colorMethod = method;
		}
	}
}());