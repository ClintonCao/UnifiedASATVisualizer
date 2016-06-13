var sourceCode = (function() {

	var currentAsatWarnings = [];
	var currentCatWarnings = [];
	var currentMessageWarnings = [];
	var currentTooltip;
	var curLine = -1;
	var	localD;
	var	localCurPath;
	var colorMethod = 0;

	// Gives a specific line a specific colour
	function colorOne(line) {
		$(line).css('background',colours.darkPurple());
		$(line).find('.CodeMirror-gutter-wrapper').find('.CodeMirror-linenumber').css('background',colours.darkPurple());
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

	// Highlights a line according to either the ASAT or the category
	function highlight(lineNumber, type, cat) {
		var childs = $( '.CodeMirror-code').children()
		var child = childs[lineNumber - 1];
	   
		$(child).find('.CodeMirror-gutter-wrapper').find('.CodeMirror-linenumber').css( 'cursor', 'crosshair' );
		$(child).css( 'cursor', 'crosshair' );
		if(colorMethod == 0) {
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
		} else if(colorMethod == 1) {
			switch(categoryMapper.categorizeWarningType(cat)) {
				case 0:
					colorOne(child);
					break;
				case 1:
					colorThree(child);
					break;
				case 2:
					colorTwo(child);
					break;
			}
		}
	}

	// Set the information for when the user is hovering over a warning
	function setLabels(lineNumber, type, cat, message) {
		var childs = $( '.CodeMirror-code').children()
		var child = childs[lineNumber - 1];
				currentAsatWarnings.push(type);
				currentCatWarnings.push(cat);
				currentMessageWarnings.push(message);
			if( curLine != lineNumber)  {
				/**
				 * Creates a tooltip that will be shown on hover over a node
				 */
				curLine = lineNumber;
				var tooltip = d3.select("#chart-and-code")
				    .append("div")
					.attr("class","d3-tip2")
					.style("width", 300)
				    .style("position", "absolute")
				    .style("z-index", "10")
				    .style("visibility", "hidden");

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

				currentAsatWarnings = [];
				currentCatWarnings = [];
				currentMessageWarnings = [];
			}
	}

	// Will set up CodeMirror with the given class
	function displayCode(pathID) {	
		for ( var i = 0; i < codeExport.length; i++){	
			if (codeExport[i].path == pathID){
				var value = codeExport[i].code.substring(1, codeExport[i].code.length -2);
			}
		}
		var editor = CodeMirror(document.body.getElementsByTagName("article")[0], {
		    value: value,
		    lineNumbers: true,
		    mode: "javascript",
		    keyMap: "sublime",
			readOnly: "nocursor",
		    autoCloseBrackets: true,
		    matchBrackets: true,
		    showCursorWhenSelecting: true,
		    theme: "monokai",
		    tabSize: 2
		});
		}

	return {
		// will set up CodeMirror and shows it to the user
		show: function(d, curPath) {
			localD = d;
			localCurPath = curPath;
			currentAsatWarnings = [];
			currentCatWarnings = [];
			currentMessageWarnings = [];
			curLine = -1;
			
			$('input.relative').attr('disabled','disabled');
			$('#normalButton').attr('disabled','disabled');
			var chartDiv = document.getElementById("code-div");

			chartDiv.style.visibility = 'visible';
			document.getElementById("chart").style.visibility = 'hidden';
			document.getElementById('normalColourLabel').style.textDecoration = 'line-through';
			document.getElementById('relativeLabel').style.textDecoration = 'line-through';
			
			displayCode(d.filePath);
			var warnings = getWarningLines(d.fileName);
			for( var i =0 ; i < warnings.warningList.length; i ++ ){
				highlight(warnings.warningList[i].line, warnings.warningList[i].type, warnings.warningList[i].cat);
			}
			var warnings = getWarningLines(d.fileName);
			for( var i =0 ; i < warnings.warningList.length; i ++ ){
				setLabels(warnings.warningList[i].line, warnings.warningList[i].type, warnings.warningList[i].cat, warnings.warningList[i].message);
			}
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