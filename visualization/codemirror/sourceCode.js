
//chrome.exe --disable-web-security
var currentAsatWarnings = [];
var currentCatWarnings = [];
var currentMessageWarnings = [];
var currentTooltip;
var curLine = 1;

var sourceCode = (function() {

function highlight(lineNumber, type){
		   var childs = $( '.CodeMirror-code').children()
		   var child = childs[lineNumber - 1];
		   
			$(child).find('.CodeMirror-gutter-wrapper').find('.CodeMirror-linenumber').css( 'cursor', 'crosshair' );
			$(child).css( 'cursor', 'crosshair' );
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
}

function setLabels(lineNumber, type, cat, message) {
	var childs = $( '.CodeMirror-code').children()
	var child = childs[lineNumber - 1];
	var child2 = childs[curLine - 1]
		if( curLine == $(child).find('.CodeMirror-gutter-wrapper').find('.CodeMirror-linenumber').text()) {
			currentAsatWarnings.push(type);
			currentCatWarnings.push(cat);
			currentMessageWarnings.push(message);

		} else {

			/*
			 * Creates a tooltip that will be shown on hover over a node
			 */
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

			$(child2).mouseenter(function(){
				tooltip.html(textInTooltip);
                tooltip.style("visibility", "visible");
			});
			$(child2).mousemove(function(){
				tooltip.style("top", (event.pageY - 130) + "px").style("left", (event.pageX - 280) + "px");
			});
			$(child2).mouseleave(function(){
				tooltip.style("visibility", "hidden");
			});

			curLine = $(child).find('.CodeMirror-gutter-wrapper').find('.CodeMirror-linenumber').text();
			currentAsatWarnings = [type];
			currentCatWarnings = [cat];
			currentMessageWarnings = [message];
		}
}
function setBackButton(d, curPath){
		var packagePath = curPath.substring(0, curPath.lastIndexOf("/") + 1);
		packagePath = packagePath.substring(0, packagePath.length - 2);
        var pathFirstPart = packagePath.substring(0, packagePath.lastIndexOf("/") + 1);
        var pathSecondPart = packagePath.split(/[/ ]+/).pop();
        $('#current-path').html(pathFirstPart + " <span id='currentLocation'>" + pathSecondPart + "</span>");

		$('#back-div').click(function() {
    		$('input.updateContent').attr('disabled',false);
    		$('#current-path').html(packagePath);
		  	sourceCode.hide();
		});
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
		$('input.relative').attr('disabled','disabled');
		var chartDiv = document.getElementById("code-div");
		chartDiv.style.visibility = 'visible';
		document.getElementById("chart").style.visibility = 'hidden';
		document.getElementById('relativeLabel').style.textDecoration = 'line-through';
		
		displayCode(d.filePath);
		var warnings = getWarningLines(d.fileName);
		for( var i =0 ; i < warnings.warningList.length; i ++ ){
			console.log(warnings.warningList[i] );
			console.log("warning light: "+ warnings.warningList[i].line );
			highlight(warnings.warningList[i].line, warnings.warningList[i].type);
		}
		setBackButton(d, curPath);
		var warnings = getWarningLines(d.fileName);
		for( var i =0 ; i < warnings.warningList.length; i ++ ){
			console.log(warnings.warningList[i] );
			console.log("warning label: "+ warnings.warningList[i].line );
			setLabels(warnings.warningList[i].line, warnings.warningList[i].type, warnings.warningList[i].cat, warnings.warningList[i].message);
		}
	},
	hide: function(){
		$('input.relative').removeAttr("disabled");
		document.getElementById('relativeLabel').style.textDecoration = 'none';
		var myNode = document.getElementById("code-article");
		while (myNode.firstChild) {
			myNode.removeChild(myNode.firstChild);
		}
		var chartDiv = document.getElementById("code-div");
			chartDiv.style.visibility = 'hidden';
			document.getElementById("chart").style.visibility = 'visible';
	}
}


}());