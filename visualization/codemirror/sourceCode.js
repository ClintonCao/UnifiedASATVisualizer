
//chrome.exe --disable-web-security
var currentWarnings = [];
var currentTooltip;

var sourceCode = (function() {

function highlight(lineNumber, type){
	$( '.CodeMirror-code').children().each(function () {
       if ($(this).find('.CodeMirror-gutter-wrapper').find('.CodeMirror-linenumber').text() == lineNumber ){
		  switch(type) {
		case 'CheckStyle':
			$(this).css('background','#386938');
			break;
		case 'PMD':
			$(this).css('background','#88120a');
			break;
		case 'FindBugs':
			$(this).css('background','#043e70');
			break;
		  }
	   }
    });
}

function setLabels(lineNumber, type, cat, message) {
	$( '.CodeMirror-code').children().each(function () {
		
		if ($(this).find('.CodeMirror-gutter-wrapper').find('.CodeMirror-linenumber').text() == lineNumber ){

			if(currentWarnings[currentWarnings.length - 1] == $(this).find('.CodeMirror-gutter-wrapper').find('.CodeMirror-linenumber').text()) {
				
				//var height = currentWarnings

				/*
				 * Creates a tooltip that will be shown on hover over a node
				 */
				// var tooltip = d3.select("#chart-and-code")
				//     .append("div")
				// 	.attr("class","d3-tip2")
				// 	.attr("id", "tip-sourceCode")
				// 	.style("width", 300)
				//     .style("position", "absolute")
				//     .style("z-index", "10")
				//     .style("visibility", "hidden");

				console.log("Duplicate");

				currentTooltip.innerHTML = "<br><br>-" + type + "<br>-" + cat + "<br>-" + message + "<br>";
	               

			} else {
				/*
				 * Creates a tooltip that will be shown on hover over a node
				 */
				var tooltip = d3.select("#chart-and-code")
				    .append("div")
					.attr("class","d3-tip2")
					.attr("id", "tip-sourceCode")
					.style("width", 300)
				    .style("position", "absolute")
				    .style("z-index", "10")
				    .style("visibility", "hidden");

				currentWarnings = [];
				currentHeight = document.getElementById('tip-sourceCode').clientHeight;

				$(this).mouseenter(function(){
					console.log("1: " + message);
					tooltip.html(lineNumber + ": " + "<br>-" + type + "<br>-" + cat + "<br>-" + message + "<br>");
	                tooltip.style("visibility", "visible");
				});
				$(this).mousemove(function(){
					tooltip.style("top", (event.pageY - 130) + "px").style("left", (event.pageX - 280) + "px");
				});
				$(this).mouseleave(function(){
					tooltip.style("visibility", "hidden");
				});
				currentTooltip = tooltip;
			}

			currentWarnings.push(lineNumber);
		}
	});
}
function setBackButton(fileName){
	$('#back-div').html(fileName);
	$('#back-div').click(function() {
	  //treeMapBuilder
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
	show: function(d){
		var chartDiv = document.getElementById("code-div");
			chartDiv.style.visibility = 'visible';
			document.getElementById("chart").style.visibility = 'hidden';
			
			displayCode(d.filePath);
			var warnings = getWarningLines(d.fileName);
			for( var i =0 ; i < warnings.warningList.length; i ++ ){
				highlight(warnings.warningList[i].line, warnings.warningList[i].type);
			}
			setBackButton(d.fileName);
	},
	hide: function(){
		var myNode = document.getElementById("foo");
		while (myNode.firstChild) {
			myNode.removeChild(myNode.firstChild);
		}
		var chartDiv = document.getElementById("code-div");
			chartDiv.style.visibility = 'hidden';
			document.getElementById("chart").style.visibility = 'visible';
	},
	setLabelsWarnings: function(fileName) {
		var warnings = getWarningLines(fileName);
		for( var i =0 ; i < warnings.warningList.length; i ++ ){
			setLabels(warnings.warningList[i].line, warnings.warningList[i].type, warnings.warningList[i].cat, warnings.warningList[i].message);
		}
	}
}


}());