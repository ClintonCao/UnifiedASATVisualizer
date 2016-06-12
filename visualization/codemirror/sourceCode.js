

var sourceCode = (function() {

var currentAsatWarnings = [];
var currentCatWarnings = [];
var currentMessageWarnings = [];
var currentTooltip;
var curLine = -1;
var	localD;
var	localCurPath;
var colorMethod = 0;


function highlight(lineNumber, type, cat){
	var childs = $( '.CodeMirror-code').children()
	var child = childs[lineNumber - 1];
   
	$(child).find('.CodeMirror-gutter-wrapper').find('.CodeMirror-linenumber').css( 'cursor', 'crosshair' );
	$(child).css( 'cursor', 'crosshair' );
	if(colorMethod == 0) {
		switch(type) {
			case 'CheckStyle':
				$(child).css('background','#386938');
				$(child).find('.CodeMirror-gutter-wrapper').find('.CodeMirror-linenumber').css('background','#386938');
				break;
			case 'PMD':
				$(child).css('background','#88120a');
				$(child).find('.CodeMirror-gutter-wrapper').find('.CodeMirror-linenumber').css('background','#88120a');
				break;
			case 'FindBugs':
				$(child).css('background','#043e70');
				$(child).find('.CodeMirror-gutter-wrapper').find('.CodeMirror-linenumber').css('background','#043e70');
				break;
		}
	} else if(colorMethod == 1) {
		switch(categoryMapper.categorizeWarningType(cat)) {
			case 0:
				$(child).css('background','#386938');
				$(child).find('.CodeMirror-gutter-wrapper').find('.CodeMirror-linenumber').css('background','#386938');
				break;
			case 1:
				$(child).css('background','#88120a');
				$(child).find('.CodeMirror-gutter-wrapper').find('.CodeMirror-linenumber').css('background','#88120a');
				break;
			case 2:
				$(child).css('background','#043e70');
				$(child).find('.CodeMirror-gutter-wrapper').find('.CodeMirror-linenumber').css('background','#043e70');
				break;
		}
	}
}

function setLabels(lineNumber, type, cat, message) {
	var childs = $( '.CodeMirror-code').children()
	var child = childs[lineNumber - 1];
			currentAsatWarnings.push(type);
			currentCatWarnings.push(cat);
			currentMessageWarnings.push(message);
		if( curLine != lineNumber)  {
			 /*
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
function displayCode(pathID){	
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
		
	readAllCode: function(){	
	},
	show: function(d, curPath){
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
	hide: function(){
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
	fullReload: function(){
		sourceCode.hide();
		sourceCode.show(localD, localCurPath);
		$('.CodeMirror').width(opts.width).height(opts.height - 30);
	},
	setColorMethod: function(method) {
		console.log("Method: " + method);
		colorMethod = method;
	}
}


}());